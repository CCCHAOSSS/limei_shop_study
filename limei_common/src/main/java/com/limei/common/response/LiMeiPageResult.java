package com.limei.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author limei
 * @date 2024/3/18 19:26
 */
@Data
@AllArgsConstructor     //注解 全参构造
@NoArgsConstructor      // 无参构造
public class LiMeiPageResult<T> implements Serializable {
    private List<T> list;
    private long total;
}
