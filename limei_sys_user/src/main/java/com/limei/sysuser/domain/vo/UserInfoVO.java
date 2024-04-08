package com.limei.sysuser.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author limei
 * @date 2024/3/18 15:26
 */

@Data
public class UserInfoVO implements Serializable {
    private Long id;
    private String nickname;
    private String avatar;
}
