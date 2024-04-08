package com.limei.sysuser.comtroller;

import com.limei.common.domain.entity.UmsMenu;
import com.limei.common.domain.vo.RouterVO;
import com.limei.common.response.LiMeiPageResult;
import com.limei.common.response.LiMeiResult;
import com.limei.common.service.IUmsMenuService;
import com.limei.common.domain.dto.UmsMenuParamDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author limei
 * @date 2024/3/16 12:37
 */

@RestController
@RequestMapping("sys/menu")
public class MenuController {

    private final IUmsMenuService menuService;


    public MenuController(IUmsMenuService menuService){
        this.menuService = menuService;
    }

    @PostMapping
    public LiMeiResult saveMenu(@Valid @RequestBody UmsMenu umsMenu){
        int row =  menuService.saveMenu(umsMenu);
        if(row > 0){

            return LiMeiResult.success();
        }
        return LiMeiResult.error("新增菜单失败");
    }


    @PutMapping
    public LiMeiResult updateMenu(@Valid @RequestBody UmsMenu umsMenu){
        int row =  menuService.updateMenu(umsMenu);
        if(row > 0){

            return LiMeiResult.success();
        }
        return LiMeiResult.error("修改菜单失败");
    }

    /**
     * 查询自己菜单
     * */
    @GetMapping("self")
    public LiMeiResult searchSelfMenu(){
        //获取当前用户id，都在SecurityContextHolder中

        List<RouterVO> routerVOList = menuService.searchSelfMenu();
        return LiMeiResult.success(routerVOList);
    }

    /**
     * 查询所有菜单
     * */
    @GetMapping("list")
    public LiMeiResult list(UmsMenuParamDto menuParamDto){  //UmsMenuParamDto相当于查询条件

        LiMeiPageResult<UmsMenu> PageResult = menuService.selectList(menuParamDto);
        return LiMeiResult.success(PageResult);




    }

    /**
     * 根据id查询详情
     * */
    @GetMapping("{id}")
    public LiMeiResult searchInfo(@PathVariable("id") Long id){
        UmsMenu menu = menuService.searchInfo(id);
        return LiMeiResult.success(menu);
    }

    /**
     * 删除数据
     * */
    @DeleteMapping()
    //delete请求不会把数据放到body里
    public LiMeiResult searchInfo(@RequestBody Long[] ids){
        int row = menuService.removeMenu(ids);
        if(row > 0){
            return LiMeiResult.success();
        }
        return LiMeiResult.error();
    }

}
