package com.limei.common.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limei.common.request.PageParam;
import com.limei.common.response.LiMeiPageResult;
import org.apache.ibatis.annotations.Param;

/**
 * @author limei
 * @date 2024/3/18 19:49
 */
public interface BaseMapperX<T> extends BaseMapper<T> {
    /**
     * 对分页的封装
     * 参数1 条件，分页数据
     * 参数2 wrapper:查询条件
    * */
    default LiMeiPageResult<T> selectPage(PageParam pageParam,@Param("ew") Wrapper<T> wrapper){
        //定义分类
        IPage<T> page = new Page<>();
        //设置页码
        page.setCurrent(pageParam.getPageNum());
        page.setSize(pageParam.getPageSize());
        //查询逻辑
        selectPage(page, wrapper);
        return new LiMeiPageResult<>(page.getRecords(), page.getTotal());
    }
}
