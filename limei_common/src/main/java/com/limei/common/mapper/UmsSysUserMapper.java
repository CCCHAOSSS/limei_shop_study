package com.limei.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.limei.common.domain.entity.UmsSysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UmsSysUserMapper extends BaseMapper<UmsSysUser> {

    UmsSysUser selectUserByUserName(@Param("account") String account, @Param("accountType") int accountType);

}
