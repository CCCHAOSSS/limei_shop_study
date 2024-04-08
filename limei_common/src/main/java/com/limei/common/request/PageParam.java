package com.limei.common.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author limei
 * @date 2024/3/18 19:51
 */
@Data
public class PageParam implements Serializable {


    //当前页
    private long pageNum = 1;
    //每月的数据量
    private long pageSize = 10L;
}
