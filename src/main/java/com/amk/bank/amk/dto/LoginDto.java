package com.amk.bank.amk.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {
	@NotBlank(message = "User is required")
	private String user;
	@NotBlank(message = "Password is required")
	private String password;
}
