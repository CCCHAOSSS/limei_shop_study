package com.limei.common.utils;
import cn.hutool.core.util.StrUtil;
import com.limei.common.constants.CacheConstants;
import com.limei.common.domain.entity.UmsSysUser;
import com.limei.common.domain.vo.LoginUserVO;
import com.limei.common.utils.redis.RedisCacheUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 通过jwt生成和解析token
 * 刷新token
* */

@Component
public class JwtUtils {

    private String secret = "qwertyuioplkjhgfdsazxcvbnm";
    @Autowired
    private RedisCacheUtil redisCacheUtil;

    /**
     * 创建token,会将用户的信息存在redis中
     * 可以方便的四线单点登录，实现踢人下线，查看在线用户等功能
     * 可以使用UUID当redis的key：把UID放到jwt里，做成token，传过来时再把token解析成uid，再根据其去redis中查用户信息
    * */
    public String createToken(LoginUserVO loginUserVO) {
        //创建一个map，解析sysUser
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        //将UUID存储到登录用户中，可以在后台系统中去根据token值获取redis中的数据
        loginUserVO.setToken(token);
        loginUserVO.setLoginTime(System.currentTimeMillis());
        Map<String, Object> claims = new HashMap<>();
        claims.put("token",token);
        //
        refreshToken(loginUserVO);
        //对token做了编码，前端拿到的token是编码过的
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

    /**
     * 获取登录用户
     * 根据token，解析之前从redis中获取
     * 刷新token
    * */
    public Object getLoginUser(HttpServletRequest request) {
        //通过jwt加密过的
        String token = request.getHeader("Limei-Authorization");
        if(StrUtil.isNotEmpty(token)){
            Claims claims = parseToken(token);
            claims.get("token");
            String parsseToken = (String) claims.get("token");

            //redis中获取数据
            LoginUserVO loginUserVO = redisCacheUtil.getCacheObject(CacheConstants.LOGIN_USER_KEY + parsseToken);
            //获取登录的时间
            long loginTime = loginUserVO.getLoginTime();
            long currentTimeMillis = System.currentTimeMillis();

            //判断时间（可以用jdk8中的方法判断
            long millis =  currentTimeMillis /1000/ 60 - loginTime/1000/60;
            if(millis >= 20){
                refreshToken(loginUserVO);
            }

            return loginUserVO;
        }

        return null;
    }

    //刷新token
    private void refreshToken(LoginUserVO loginUserVO){

        redisCacheUtil.setCacheObject(CacheConstants.LOGIN_USER_KEY + loginUserVO.getToken(), loginUserVO, 30, TimeUnit.MINUTES);
    }

}
