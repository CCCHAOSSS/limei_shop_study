package com.limei.support.config.security;


import cn.hutool.core.util.ObjectUtil;
import com.limei.common.domain.entity.UmsMenu;
import com.limei.common.domain.entity.UmsRole;
import com.limei.common.domain.entity.UmsSysUser;

import com.limei.common.domain.vo.LoginUserVO;
import com.limei.common.mapper.UmsMenuMapper;
import com.limei.common.mapper.UmsSysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SysUserDetailService implements UserDetailsService {
    /**
     * 实现方法
    * */

    private final UmsSysUserMapper sysUserMapper;
    private final UmsMenuMapper umsMenuMapper;

    public SysUserDetailService(UmsSysUserMapper sysUserMapper, UmsMenuMapper umsMenuMapper){
        this.sysUserMapper = sysUserMapper;
        this.umsMenuMapper = umsMenuMapper;
    }


    /**
     * 实现方法，在此方法中根据用户名查询用户
     * 账号：用户名、手机号、邮箱, 通过正则表达式去判断（在后端处理，不在前端选择）
    * */
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException{

        // TODO 验证账号类型
        int accountType = 0;

        // 根据账号查询用户,同时将角色查出来 联查时最好不要超过3张表
        UmsSysUser sysUser = sysUserMapper.selectUserByUserName(account, accountType);

        //权限查询试根据角色查询的，首先获取所有的角色id
        if(ObjectUtil.isNotNull(sysUser)){
            List<UmsRole> roleList = sysUser.getRoleList();
            //取出id
            List<Long> roleIds = roleList.stream().map(UmsRole::getRoleId).collect(Collectors.toList());
            log.info("角色id====》{}", roleIds);
            //查询所有的菜单
            List<UmsMenu> menuList = umsMenuMapper.selectByRoleIds(roleIds);
            //获取list中的权限字段
            List<String> perms = menuList.stream().map(UmsMenu::getPerms).collect(Collectors.toList());
            log.info("权限====》{}", roleIds);
            sysUser.setPerms(perms);

        }

        //根据角色查询权限 menu
        log.info("sysUser========>{}",sysUser);
        LoginUserVO loginUserVO = new LoginUserVO();
        loginUserVO.setSysUser(sysUser);
        loginUserVO.setId((sysUser.getId()));

        return loginUserVO;
    }


}
