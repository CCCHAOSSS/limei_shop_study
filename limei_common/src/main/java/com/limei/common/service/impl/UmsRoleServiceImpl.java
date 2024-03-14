package com.limei.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.limei.common.domain.entity.UmsRole;
import com.limei.common.mapper.UmsRoleMapper;
import com.limei.common.service.IUmsRoleService;
import org.springframework.stereotype.Service;

@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements IUmsRoleService {
}
