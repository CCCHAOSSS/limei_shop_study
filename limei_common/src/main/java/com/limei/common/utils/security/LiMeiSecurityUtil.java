package com.limei.common.utils.security;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.limei.common.constants.HttpStatus;
import com.limei.common.domain.vo.LoginUserVO;
import com.limei.common.exception.ServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 获取登录的用户信息
 * */
public class LiMeiSecurityUtil {

    /**
     * 获取Authentication
    * */
    public static Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();  //线程独立的，不会拿到别人的（不会冲突）

    }

    /***
     * 获取用户
     * */
    public static LoginUserVO getLoginUser(){
        return (LoginUserVO) getAuthentication().getPrincipal();
    }

    /**
     * 获取用户id
     * */
    public static Long getUserId(){
        Long userId = getLoginUser().getId();
        if(ObjectUtil.isNull(userId)){
            throw new ServiceException(HttpStatus.FORBIDDEN, "");
        }
        return userId;

    }

    /**
     * 获取用户名
     * */
    public static String getUserName(){
        String username = getLoginUser().getUsername();
        if(ObjectUtil.isNull(username)){
            throw new ServiceException(HttpStatus.FORBIDDEN, "");
        }
        return username;
    }

}
