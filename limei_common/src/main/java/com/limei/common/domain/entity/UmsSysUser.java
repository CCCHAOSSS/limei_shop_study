package com.limei.common.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@TableName("ums_sys_user") //指定数据库表名
public class UmsSysUser implements Serializable {
    @TableId
    // 使用包装类 为啥呢（？
    private Long id;
    private String username;
    private String nickname;
    private String email;
    private String mobile;

    private Integer sex;
    private String avatar;
    private String password;

    private Integer status;
    private String creator;
    private String updater;
    private String remark;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    // 逻辑删除，MyBatis-Plus默认 0为未删除，0为已删除
    @TableLogic
    private Integer deleted;

    //角色信息
    @TableField(select = false)    // 不在表里的字段
    private List<UmsRole> roleList = new ArrayList<>(); //直接new出来，防止空指针异常

    @TableField(exist = false)
    private List<String> perms = new ArrayList<>();


}
