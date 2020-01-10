package com.nan.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nan.entity.Role;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
public interface RoleService extends IService<Role> {
	
	List<Role> list(int pageNum, int pageSize, String sortName, String sortOrder, String search);

}
