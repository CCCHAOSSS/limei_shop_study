package com.limei.common.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.limei.common.constants.HttpStatus;
import com.limei.common.domain.dto.UmsMenuParamDto;
import com.limei.common.domain.entity.UmsMenu;
import com.limei.common.domain.entity.UmsRole;
import com.limei.common.domain.vo.RouterVO;
import com.limei.common.exception.ServiceException;
import com.limei.common.mapper.UmsMenuMapper;
import com.limei.common.mapper.UmsRoleMapper;
import com.limei.common.response.LiMeiPageResult;
import com.limei.common.response.LiMeiResult;
import com.limei.common.service.IUmsMenuService;
import com.limei.common.utils.security.LiMeiSecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UmsMenuServiceImpl extends ServiceImpl<UmsMenuMapper, UmsMenu> implements IUmsMenuService {

    private final UmsRoleMapper roleMapper;
    public UmsMenuServiceImpl(UmsRoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }


    /**
     * 获取个人的菜单列表
    * */
    @Override
    public List<RouterVO> searchSelfMenu() {
        Long userId = LiMeiSecurityUtil.getUserId();
//        System.out.println("userId=====>" + userId);
        List<Long> roleIds = roleMapper.selectByUserId(userId);
//        System.out.println("roleIds====>" + roleIds);
        List<UmsMenu> menuList = baseMapper.selectByRoleIds(roleIds);
//        System.out.println("menuList====>" + roleIds);

        //通过递归设置菜单的树形结构
        // 获取所有的1级菜单
        // 遍历1级菜单，获取它所有的子元素【其他数据的parent_id = 当前元素的id】

        List<RouterVO> router = getRouter(menuList);
        return router;
    }

    @Override
    public List<RouterVO> searchSMenuList() {
        List<UmsMenu> menuList = baseMapper.selectList(null);
        List<RouterVO> router = getRouter(menuList);
        return router;
    }


    /*
    * 添加菜单
    * */
    @Override
    public int saveMenu(UmsMenu umsMenu) {
        String creater = LiMeiSecurityUtil.getUserName();
        umsMenu.setCreator(creater);    //在get方法里面已经做了是否登录的判断
        umsMenu.setUpdater(creater);

        //判断是否存在
        Long parentId = umsMenu.getParent_id();
        String path = umsMenu.getPath();
        String menuName = umsMenu.getMenuName();
        LambdaQueryWrapper<UmsMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UmsMenu::getParent_id, parentId).eq(UmsMenu::getMenuName, menuName).or().eq(UmsMenu::getPath,path);

        Long count = baseMapper.selectCount(wrapper);
        if(count > 0){
            throw new ServiceException(HttpStatus.ERROR, "该菜单已存在，或路径已存在");

        }
        return baseMapper.insert(umsMenu);
    }

    
    
    /**
    * 根据id查询详情
    * */
    @Override
    public UmsMenu searchInfo(Long id) {
        UmsMenu menu = baseMapper.selectById(id);
        return menu;

    }

    @Override
    public int updateMenu(UmsMenu umsMenu) {
        String creater = LiMeiSecurityUtil.getUserName();
        umsMenu.setUpdater(creater);

        //判断是否存在
        Long parentId = umsMenu.getParent_id();
        String path = umsMenu.getPath();
        String menuName = umsMenu.getMenuName();
        LambdaQueryWrapper<UmsMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UmsMenu::getParent_id, parentId).eq(UmsMenu::getMenuName, menuName).or().eq(UmsMenu::getPath,path);
        Long count = baseMapper.selectCount(wrapper);
        if(count > 0){
            throw new ServiceException(HttpStatus.ERROR, "该菜单已存在，或路径已存在");

        }
        return baseMapper.updateById(umsMenu);
    }

    @Override
    public int removeMenu(Long[] ids) {
        return baseMapper.deleteBatchIds(Arrays.asList(ids));  //aslist返回的数据不能对它做写和删除操作
    }

    @Override
    public LiMeiPageResult<UmsMenu> selectList(UmsMenuParamDto menuParamDto) {

        LambdaQueryWrapper<UmsMenu> wrapper = new LambdaQueryWrapper<>();
        //搜索条件
        if(StrUtil.isNotEmpty(menuParamDto.getMenuName())){
            wrapper.eq(UmsMenu::getMenuName, menuParamDto.getMenuName());
        }
        if(StrUtil.isNotEmpty(menuParamDto.getPerms())){
            wrapper.eq(UmsMenu::getPerms, menuParamDto.getPerms());
        }

        //分页查询时一定先查一级目录 getParent_id=0
        wrapper.eq(UmsMenu::getParent_id, 0);
        LiMeiPageResult<UmsMenu> parentMenuList = baseMapper.selectPage(menuParamDto, wrapper);//只是一级目录的数据
        List<UmsMenu> records = parentMenuList.getList();
        //还要查询子目录
        List<Long> parentIds = records.stream().map(UmsMenu::getId).toList();
        //根据父id查询所有的子菜单  用in(不要用for循环去查数据库)
        //前面in查子菜单 后面查自身.上面是查子菜单，这里才是返回的数据
        List<UmsMenu> umsMenus = baseMapper.selectList(new LambdaQueryWrapper<UmsMenu>().in(UmsMenu::getParent_id, parentIds).or().in(UmsMenu::getId, parentIds));

        //通过递归构建children
        List<UmsMenu> menuList = buildChildren(umsMenus);
        // 返回两个数据：total：总数据量  data：数据
        parentMenuList.setList(menuList);

        return parentMenuList;
    }


    private List<RouterVO> getRouter(List<UmsMenu> menuList){
        List<RouterVO> routerVOS = new ArrayList<>();

        List<UmsMenu> parentMenu = menuList.stream().filter(item ->item.getParent_id()==0).collect(Collectors.toList());
        for(UmsMenu menu : parentMenu){
            RouterVO routerVO = new RouterVO();
            BeanUtil.copyProperties(menu, routerVO);
            routerVOS.add(routerVO);

        }
        for(RouterVO routerVO : routerVOS){
            //获取所有的子节点
            List<RouterVO> childrenList = buildTree(menuList, routerVO.getId());
            routerVO.setChildren(childrenList);
        }

        return routerVOS;
    }


    /**
     * 获取字子节点,递归获取
    * */
    private  List<RouterVO> buildTree(List<UmsMenu> allMenu, Long parenId){
        ArrayList<RouterVO> childrenList = new ArrayList<>();
        for(UmsMenu menu : allMenu){
            //判断
            if(menu.getParent_id().equals(parenId)){
                RouterVO routerVO = new RouterVO();
                BeanUtil.copyProperties(menu, routerVO);
                childrenList.add((routerVO));
            }
        }

        // childrenList可能还有子节点
        for (RouterVO childrenItem : childrenList) {
            childrenItem.setChildren(buildTree(allMenu, childrenItem.getId()));
        }
        return childrenList;
    }

    private List<UmsMenu> buildChildren(List<UmsMenu> menuList){
        List<UmsMenu> parentMenu = menuList.stream().filter(item ->item.getParent_id()==0).collect(Collectors.toList());
        for(UmsMenu routerVO : parentMenu){
            //获取所有的子节点
            List<UmsMenu> childrenList = buildMenuTree(menuList, routerVO.getId());
            routerVO.setChildren(childrenList);
        }
        return parentMenu;
    }


    /**
     * 获取字子节点,递归获取
     * */
    private  List<UmsMenu> buildMenuTree(List<UmsMenu> allMenu, Long parenId){
        ArrayList<UmsMenu> childrenList = new ArrayList<>();
        for(UmsMenu menu : allMenu){
            //判断
            if(menu.getParent_id().equals(parenId)){
                childrenList.add((menu));
            }
        }

        // childrenList可能还有子节点
        for (UmsMenu childrenItem : childrenList) {
            childrenItem.setChildren(buildMenuTree(allMenu, childrenItem.getId()));
        }
        return childrenList;
    }



}