package com.limei.sysuser.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.limei.common.constants.HttpStatus;
import com.limei.common.domain.entity.UmsSysUser;
import com.limei.common.domain.vo.LoginUserVO;
import com.limei.common.exception.ServiceException;
import com.limei.common.mapper.UmsSysUserMapper;
import com.limei.common.utils.security.LiMeiSecurityUtil;
import com.limei.sysuser.domain.vo.UserInfoVO;
import com.limei.sysuser.service.impl.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author limei
 * @date 2024/3/18 15:34
 */

@Service
@Slf4j
public class SysUserServiceImpl implements ISysUserService {
    private final UmsSysUserMapper sysUserMapper;

    public SysUserServiceImpl(UmsSysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public UserInfoVO searchUserInfo(){
        // 获取用户的id
        Long userId = LiMeiSecurityUtil.getUserId();
        UmsSysUser umsSysUser = sysUserMapper.selectById(userId);
        if(ObjectUtil.isNull(umsSysUser)){
            throw new ServiceException(HttpStatus.FORBIDDEN,"");
        }

        UserInfoVO userInfoVO = new UserInfoVO();
        // userInfoVO中的属性和umsSysUser中的属性相同（数量不同）
        // 可以用BeanUtil.copyProperties
        BeanUtil.copyProperties(umsSysUser, userInfoVO);

        return userInfoVO;

    }
}
