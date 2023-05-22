package com.thalos.trailerflix.entities;

import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity(name = "tb_user")
public class User implements UserDetails, Serializable {
	private static final long serialVersionUID = 1L;
	
	public User(UUID id, String trailersId, String profile, String name, @Email String email, String password, LocalDate registrationDate) {
		this.id = id;
		this.trailersId = trailersId;
		this.profile = profile;
		this.name = name;
		this.email = email;
		this.password = password;
		this.registrationDate = registrationDate;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
	protected UUID id;
    
    protected String trailersId;
    protected String profile;
    protected String name;
    
    @Email
    protected String email;
    protected String password;
    protected LocalDate registrationDate;

    @PrePersist
    public void prePersist() {
        registrationDate = LocalDate.now(ZoneId.of("GMT-3"));
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(profile));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}