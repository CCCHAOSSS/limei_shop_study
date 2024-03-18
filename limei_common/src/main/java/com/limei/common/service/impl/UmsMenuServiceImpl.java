package com.limei.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.limei.common.domain.entity.UmsMenu;
import com.limei.common.mapper.UmsMenuMapper;
import com.limei.common.service.IUmsMenuService;
import org.springframework.stereotype.Service;

@Service
public class UmsMenuServiceImpl extends ServiceImpl<UmsMenuMapper, UmsMenu> implements IUmsMenuService {

}