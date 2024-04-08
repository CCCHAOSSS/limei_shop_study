package com.limei.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.limei.common.domain.entity.UmsMenu;
import com.limei.common.domain.entity.UmsRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UmsMenuMapper extends BaseMapperX<UmsMenu> {
    List<UmsMenu> selectByRoleIds(@Param("roleIds") List<Long> roleIds);

}
