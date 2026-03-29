package com.amk.bank.amk.constant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoginResponse {
	public static final String SUCCESS = "Success";
	public static final String FAIL = "Fail";
	private String status;
	private String message;
}
