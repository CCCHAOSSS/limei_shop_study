package com.limei.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.limei.common.domain.entity.UmsRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UmsRoleMapper extends BaseMapper<UmsRole> {
    List<Long> selectByUserId(@Param("userId") Long userId);
}
