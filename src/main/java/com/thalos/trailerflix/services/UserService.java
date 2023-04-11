package com.thalos.trailerflix.services;

import com.thalos.trailerflix.dtos.ResetPasswordDTO;
import com.thalos.trailerflix.dtos.UserDTO;
import com.thalos.trailerflix.dtos.UserInsertDTO;
import com.thalos.trailerflix.entities.User;
import com.thalos.trailerflix.exceptions.InternalServerException;
import com.thalos.trailerflix.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.thalos.trailerflix.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    @Value("${spring.mail.password}")
    private String passwordEmail;

    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);

        return userPage.map(UserDTO::new);
    }

    public User findById(UUID userId) {
        Optional<User> userFound = userRepository.findById(userId);

        return userFound.orElseThrow(() -> new ObjectNotFoundException("User não encontrado."));
    }

    public UserDTO createUser(UserInsertDTO userInsertDTO) throws MessagingException {
        User userEmail = userRepository.findByEmail(userInsertDTO.getEmail());
        if (userEmail != null) throw new InternalServerException("Este email já está cadastrado");

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(userInsertDTO.getEmail());
        user.setName(userInsertDTO.getName());
        user.setProfile(userInsertDTO.getProfile());
        user.setPassword(passwordEncoder.encode(userInsertDTO.getPassword()));
        user.setEnable(false);
        user = userRepository.save(user);

        JavaMailSenderImpl mailSender = this.getJavaMailSender();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setSubject("Link da sua senha.");
        helper.setFrom("murilo.bot100@gmail.com");
        helper.setTo(userInsertDTO.getEmail());

        boolean html = true;
        helper.setText("<p>Olá,</p>"
                        + "<p>Você cadastrou em nosso site.</p>"
                        + "<br>"
                        + "<p>Para confirmar seu e-mail clique no link: <a href=\"/confirm-account/" + user.getId() + "\">Confirmar e-mail</a></p>"
                        + "<br>"
                        + "<p>Ignore this email if you do remember your password, or you have not made the request.</p>"
                , html);

        mailSender.send(message);
        return new UserDTO(user);
    }

    public void confirmEmail(String checkerCode) {
        User user = userRepository.findByCheckerCode(checkerCode)
                .orElseThrow(() -> new ObjectNotFoundException("usuário não encontrado"));
        user.setEnable(true);
        userRepository.save(user);
    }

    public void resendConfirmEmail(String email) throws MessagingException {
        User user = userRepository.findByEmail(email);
        if (user == null) throw new ObjectNotFoundException("Usuário não encontrado");

        JavaMailSenderImpl mailSender = this.getJavaMailSender();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setSubject("Comfirmação de e-mail.");
        helper.setFrom("murilo.bot100@gmail.com");
        helper.setTo(email);

        boolean html = true;
        helper.setText("<p>Olá,</p>"
                        + "<p>Você cadastrou em nosso site.</p>"
                        + "<br>"
                        + "<p>Para confirmar seu e-mail clique no link: <a href=\"http://localhost:3000/confirm-account/" + user.getCheckerCode() + "\">Confirmar e-mail</a></p>"
                        + "<br>"
                        + "<p>Ignore this email if you do remember your password, or you have not made the request.</p>"
                , html);

        mailSender.send(message);
    }
    public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
        User user = userRepository.findByCheckerCode(resetPasswordDTO.getResetPasswordToken())
                      .orElseThrow(() -> new ObjectNotFoundException("usuário não encontrado"));

        user.setCheckerCode(null);
        user.setPassword(passwordEncoder.encode(resetPasswordDTO.getNewPassword()));

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new DataIntegrityViolationException("Não foi possível salvar este usuário");
        }
    }

    public void sendEmailResetPassword(String to) throws MessagingException {
        User user = userRepository.findByEmail(to);

        if (user == null) throw new ObjectNotFoundException("Este usuário não existe.");
        user.setCheckerCode(String.valueOf(UUID.randomUUID()));

        JavaMailSenderImpl mailSender = this.getJavaMailSender();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setSubject("Link da sua senha.");
        helper.setFrom("murilo.bot100@gmail.com");
        helper.setTo(to);

        boolean html = true;
        helper.setText("<p>Olá,</p>"
                        + "<p>Você solicitou acesso a sua senha.</p>"
                        + "<br>"
                        + "<p>Clique no link para alterar sua senha: <a href=\"http://localhost:3000/reset-password/" + user.getCheckerCode() + "\">Trocar senha</a></p>"
                        + "<br>"
                        + "<p>Ignore this email if you do remember your password, or you have not made the request.</p>"
                , html);

        mailSender.send(message);
    }

    public JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("murilo.bot100@gmail.com");
        mailSender.setPassword(passwordEmail);

        Properties props = mailSender.getJavaMailProperties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
//        props.setProperty("mail.debug", "true");
//        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
        mailSender.setJavaMailProperties(props);

        return mailSender;
    }
}
