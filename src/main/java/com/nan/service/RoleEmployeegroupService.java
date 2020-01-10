package com.nan.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nan.entity.RoleEmployeegroup;

/**
 * <p>
 * 角色员工组 服务类
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
public interface RoleEmployeegroupService extends IService<RoleEmployeegroup> {
	
	List<RoleEmployeegroup> list(int pageNum, int pageSize, String sortName, String sortOrder, String search);

}
