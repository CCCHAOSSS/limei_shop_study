package com.limei.common.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.jwt.JWTUtil;
import com.limei.common.constants.HttpStatus;
import com.limei.common.domain.dto.LoginDto;
import com.limei.common.domain.vo.LoginUserVO;
import com.limei.common.exception.ServiceException;
import com.limei.common.service.IAuthService;
import com.limei.common.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@Primary
public class AuthServiceImpl implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils; //通过构造方法的形式引用过来

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils){
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }



    /**
     * login方法
     * 通过security
     * */
    @Override
    public String login(LoginDto loginDto){
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(loginDto.getAccount(), loginDto.getPassword());

        //调用 loaduserbyusername方法
        Authentication authenticate = authenticationManager.authenticate(authentication);

        // 获取用户信息 返回的试UserDetail
        LoginUserVO loginUser = (LoginUserVO) authenticate.getPrincipal();
        // notNull 说明有数据生成token
        if(ObjectUtil.isNull(loginUser)){
            throw new ServiceException(HttpStatus.UNAUTH0RIZED, "认证失败");

        }


        //根据loginuser去创建token,此处token由UUID编码成的字符串
        String token = jwtUtils.createToken(loginUser);
        log.info("token====>{}",token);
        return token;

    }



}
