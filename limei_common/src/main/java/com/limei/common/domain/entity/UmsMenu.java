package com.limei.common.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data  //这个是lambok注解，集成了对象构造方法（不包括无参构造）、get、set、tostring方法等
// 不用手动写（写出来），减少臃肿
@TableName("ums_menu")
public class UmsMenu implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long parent_id;
    @NotNull(message = "请填写菜单名")
    private String menuName;
    private String path;
    private String component_path;
    @NotNull(message = "权限不能为空")
    private String perms;
    @NotNull(message = "icon不能为空")
    private String icon;
    @NotNull(message = "类型不能为空")
    private Integer menuType;
    private Integer sort;
    private Integer status;

    private String creator;
    private String updater;
    private String remark;

    //加入注解会自动填充，动作：INSERT_UPDATE
    // 即更新和插入两种操作都会修改updateTime
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    //加入注解会自动填充，动作：INSERT
    // 插入（新记录）时会createTime
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private List<UmsMenu> children = new ArrayList<>();


}
