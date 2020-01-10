package com.nan.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nan.entity.Permission;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
public interface PermissionService extends IService<Permission> {
	
	List<Permission> list(int pageNum, int pageSize, String sortName, String sortOrder, String search);

}
