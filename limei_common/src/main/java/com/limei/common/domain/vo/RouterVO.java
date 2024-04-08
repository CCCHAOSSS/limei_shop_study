package com.limei.common.domain.vo;

import com.limei.common.domain.entity.UmsMenu;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author limei
 * @date 2024/3/16 13:54
 */
@Data
public class RouterVO implements Serializable {

    private Long id;
    private Long parentId;
    private String menuName;
    private String path;
    private String component_path;
    private String icon;
    private Integer menuType;
    private List<RouterVO> children = new ArrayList<>();

}
