package com.limei.common.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@TableName("ums_role")
// 实现serialization接口的目的是做序列化
public class UmsRole implements Serializable {

    @TableId
    private Long roleId;
    private String roleName;
    private String roleLabel;
    private Integer sort;

    private Integer status;
    private String creator;
    private String updater;
    private String remark;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    @TableLogic
    private Integer deleted;
}
