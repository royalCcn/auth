package com.nan.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nan.entity.EmployeeEmployeegroup;
import com.nan.mapper.EmployeeEmployeegroupMapper;
import com.nan.service.EmployeeEmployeegroupService;

/**
 * <p>
 * 员工员工组 服务实现类
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Service
public class EmployeeEmployeegroupServiceImpl extends ServiceImpl<EmployeeEmployeegroupMapper, 
EmployeeEmployeegroup> implements EmployeeEmployeegroupService {

	@Override
	public List<EmployeeEmployeegroup> list(int pageNum, int pageSize, String sortName, 
			String sortOrder, String search) {
		List<EmployeeEmployeegroup> results = new ArrayList<>();
		List<EmployeeEmployeegroup> list =  baseMapper.list(pageNum,pageSize,sortName,sortOrder,search);
		if (list != null) {
			results = list;
		}
		return results;
	}

}
