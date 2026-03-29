package com.amk.bank.amk.service;

import com.amk.bank.amk.constant.LoginResponse;
import com.amk.bank.amk.dto.LoginDto;

public interface AuthService {
	LoginResponse login(LoginDto loginDto);
}
