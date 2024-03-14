package com.limei.common.service;

import com.limei.common.domain.dto.LoginDto;

public interface IAuthService {

    String login(LoginDto loginDto);

}
