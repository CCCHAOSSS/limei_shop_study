package com.limei.auth.domain.entry;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@TableName("ums_role")
// 实现serialization接口的目的是做序列化
public class UmsRole implements Serializable {

    @TableId
    private Long id;
    private String roleName;
    private String roleLabel;
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
