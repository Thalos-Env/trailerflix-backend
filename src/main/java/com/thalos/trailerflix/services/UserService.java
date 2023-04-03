package com.thalos.trailerflix.services;

import com.thalos.trailerflix.dtos.UserDTO;
import com.thalos.trailerflix.dtos.UserInsertDTO;
import com.thalos.trailerflix.entities.User;
import com.thalos.trailerflix.exceptions.InternalServerException;
import com.thalos.trailerflix.exceptions.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.thalos.trailerflix.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);

        return userPage.map(UserDTO::new);
    }

    public UserDTO createUser(UserInsertDTO userInsertDTO) {
        User userEmail = userRepository.findByEmail(userInsertDTO.getEmail());
        if (userEmail != null) throw new InternalServerException("Este email já está cadastrado");

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(userInsertDTO.getEmail());
        user.setName(userInsertDTO.getName());
        user.setProfile(userInsertDTO.getProfile());
        user.setPassword(passwordEncoder.encode(userInsertDTO.getPassword()));
//        user.isEnabled();
        user = userRepository.save(user);
        return new UserDTO(user);
    }

    public void sendSimpleMessage(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        User user = userRepository.findByEmail(to);
        if (user == null) throw new ObjectNotFoundException("Este usuário não existe.");

        String subject = "Here's the link to reset your password";
        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=/reset-password/" + user.getResetPasswordToken() + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";


        message.setFrom("murilo.bot100@gmail.com");
        message.setTo(to);
        message.setText(content);
        message.setSubject(subject);

        javaMailSender.send(message);
    }
}
