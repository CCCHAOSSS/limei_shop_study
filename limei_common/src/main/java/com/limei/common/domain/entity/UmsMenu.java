package com.limei.common.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
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

    private String creator;
    private String updater;
    private String remark;

    //加入注解会自动填充，动作：INSERT_UPDATE
    // 即更新和插入两种操作都会修改updateTime
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    //加入注解会自动填充，动作：INSERT
    // 插入（新记录）时会createTime
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    @TableLogic
    private Integer deleted;
}
