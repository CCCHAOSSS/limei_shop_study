package com.limei.common.domain.dto;

import com.limei.common.request.PageParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @author limei
 * @date 2024/3/18 18:54
 */
@Data
public class UmsMenuParamDto extends PageParam {
    private String menuName;
    private String perms;


}
