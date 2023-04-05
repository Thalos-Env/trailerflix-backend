package com.thalos.trailerflix.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResetPasswordDTO {
    private String resetPasswordToken;
    private String newPassword;
}
