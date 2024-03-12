package com.limei.auth.domain.entry;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data  //这个是lambok注解，集成了对象构造方法（不包括无参构造）、get、set、tostring方法等
// 不用手动写（写出来），减少臃肿
@TableName("ums_menu")
public class UmsMenu implements Serializable {

    @TableId
    private Long id;
    private Long parent_id;
    private String menuName;
    private String path;
    private String component_path;
    private String perms;
    private String icon;
    private Integer menuType;
    private Integer sort;
    private Integer status;

    private String creater;
    private String updater;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private String remake;

    @TableLogic
    private Integer deleted;
}
