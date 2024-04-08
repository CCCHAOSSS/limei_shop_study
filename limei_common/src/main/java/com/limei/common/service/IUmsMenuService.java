package com.limei.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.limei.common.domain.dto.UmsMenuParamDto;
import com.limei.common.domain.entity.UmsMenu;
import com.limei.common.domain.vo.RouterVO;
import com.limei.common.response.LiMeiPageResult;

import java.util.List;

public interface IUmsMenuService extends IService<UmsMenu> {
     List<RouterVO> searchSelfMenu();

    List<RouterVO> searchSMenuList();

    int saveMenu(UmsMenu umsMenu);

    UmsMenu searchInfo(Long id);

    int updateMenu(UmsMenu umsMenu);

    int removeMenu(Long[] ids);

    LiMeiPageResult<UmsMenu> selectList(UmsMenuParamDto menuParamDto);
}
