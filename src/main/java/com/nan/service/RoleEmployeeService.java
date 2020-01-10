package com.nan.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nan.entity.RoleEmployee;

/**
 * <p>
 * 员工角色 服务类
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
public interface RoleEmployeeService extends IService<RoleEmployee> {
	
	List<RoleEmployee> list(int pageNum, int pageSize, String sortName, String sortOrder, String search);

}
