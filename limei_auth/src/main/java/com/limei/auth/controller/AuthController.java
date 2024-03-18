package com.limei.auth.controller;
import com.limei.common.service.IAuthService;
import lombok.extern.slf4j.Slf4j;
import com.limei.common.domain.dto.LoginDto;
import com.limei.common.response.LiMeiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@Slf4j
public class AuthController {

//    @Autowired
    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    /**
     * 系统用户登录
    * */
    @PostMapping("sys")
    public LiMeiResult sysLogin(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);
        return LiMeiResult.success().put("token", token);
        // token里面不要放重要的东西

    }
}
