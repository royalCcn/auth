package com.nan.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nan.entity.Employee;

/**
 * <p>
 * 员工 服务类
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
public interface EmployeeService extends IService<Employee> {
	
	public Employee getLoginUser();
	
	List<Employee> list(int pageNum, int pageSize, String sortName, String sortOrder, String search);

}
