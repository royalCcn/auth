package com.nan.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nan.entity.EmployeePost;

/**
 * <p>
 * 员工职位 服务类
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
public interface EmployeePostService extends IService<EmployeePost> {
	
	List<EmployeePost> list(int pageNum, int pageSize, String sortName, String sortOrder, String search);

}
