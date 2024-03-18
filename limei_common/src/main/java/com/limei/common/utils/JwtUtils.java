package com.limei.common.utils;
import com.limei.common.domain.entity.UmsSysUser;
import com.limei.common.domain.vo.LoginUserVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

/**
 * 通过jwt生成和解析token
 * 刷新token
* */

@Component
public class JwtUtils {

    private String secret = "qwertyuioplkjhgfdsazxcvbnm";
    /**
     * 创建token,会将用户的信息存在redis中
     * 可以方便的四线单点登录，实现踢人下线，查看在线用户等功能
     * 可以使用UUID当redis的key：把UID放到jwt里，做成token，传过来时再把token解析成uid，再根据其去redis中查用户信息
    * */
    public String createToken(LoginUserVO loginUserVO) {
        UmsSysUser sysUser = loginUserVO.getSysUser();
        //创建一个map，解析sysUser
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        //将UUID存储到登录用户中，可以在后台系统中去根据token值获取redis中的数据
        loginUserVO.setToken(token);
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("token",token);

        //对token做了编码，前端拿到的token是编码过的
        //
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret) //选择这个算法来加密
                .compact();
    }



    /**
     * 解析token
     * token: jwt:*****(头).*****(body).***
     * */
    public Claims parseToken(String token){
        //解析token
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

    }

}
