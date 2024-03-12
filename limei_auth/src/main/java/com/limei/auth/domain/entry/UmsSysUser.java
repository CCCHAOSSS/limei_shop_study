package com.limei.auth.domain.entry;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

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
    private String avater;
    private String password;

    private Integer status;
    private String creater;
    private String updater;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private String remake;

    // 逻辑删除，MyBatis-Plus默认 0为未删除，0为已删除
    @TableLogic
    private Integer deleter;


}
