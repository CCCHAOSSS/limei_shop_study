package com.limei.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.limei.common.domain.entity.UmsSysUser;
import com.limei.common.mapper.UmsSysUserMapper;
import com.limei.common.service.IUmsSysUserService;
import org.springframework.stereotype.Service;

@Service
public class UmsSysUserServiceImpl extends ServiceImpl<UmsSysUserMapper, UmsSysUser> implements IUmsSysUserService {
}
