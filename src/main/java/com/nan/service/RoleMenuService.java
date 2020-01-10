package com.nan.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nan.entity.RoleMenu;

/**
 * <p>
 * 角色菜单 服务类
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
public interface RoleMenuService extends IService<RoleMenu> {
	
	List<RoleMenu> list(int pageNum, int pageSize, String sortName, String sortOrder, String search);

}
