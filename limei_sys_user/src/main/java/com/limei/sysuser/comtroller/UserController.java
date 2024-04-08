package com.limei.sysuser.comtroller;

import com.limei.common.response.LiMeiResult;
import com.limei.sysuser.domain.vo.UserInfoVO;
import com.limei.sysuser.service.impl.ISysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author limei
 * @date 2024/3/18 15:24
 */


@RestController
@RequestMapping("sys/user")
public class UserController {

    private final ISysUserService userService;

    public UserController(ISysUserService userService) {
        this.userService = userService;
    }


    @GetMapping("/info")
    public LiMeiResult searchUserInfo(){
        //还需要其他信息可以在UserInfoVO中添加
        // 为啥不使用实体类UmsSysUser呢（难道是因为信息太多了？
        return LiMeiResult.success(userService.searchUserInfo());

    }
}
