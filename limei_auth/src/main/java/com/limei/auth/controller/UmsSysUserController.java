package com.limei.auth.controller;

import com.limei.common.domain.entity.UmsSysUser;
import com.limei.common.service.IUmsSysUserService;
import com.limei.common.response.LiMeiResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ums/sysuser")
public class UmsSysUserController {


//    private final IUmsSysUserService sysUserService;
//
////    public UmsSysUserController(){}
//
//    public UmsSysUserController(IUmsSysUserService sysUserService) {
//        this.sysUserService = sysUserService;
//    }

    @Resource(name="umsSysUserServiceImpl")
//    @Autowired
    private IUmsSysUserService sysUserService;

    /**
     * 新增用户接口
     * */

    @PostMapping
    public LiMeiResult addSysUser(@RequestBody UmsSysUser user){
        boolean flag = sysUserService.save(user);
        if(flag){
            return LiMeiResult.success();
        }
        return LiMeiResult.error();
    }

    /**
     * 查询用户列表
    * */
    @GetMapping
    public LiMeiResult searchList(){
        List<UmsSysUser> list = sysUserService.list();
        list.forEach(System.out::println);
        return LiMeiResult.success().put("data", list);
    }
}
