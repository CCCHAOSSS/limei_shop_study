package com.limei.auth.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.limei.auth.domain.entry.UmsRole;
import com.limei.auth.domain.mapper.UmsRoleMapper;
import com.limei.auth.domain.service.IUmsRoleService;
import org.springframework.stereotype.Service;

@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements IUmsRoleService {
}
