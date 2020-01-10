package com.nan.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nan.entity.RolePermission;

/**
 * <p>
 * 角色权限 服务类
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
public interface RolePermissionService extends IService<RolePermission> {
	
	List<RolePermission> list(int pageNum, int pageSize, String sortName, String sortOrder, String search);

}
