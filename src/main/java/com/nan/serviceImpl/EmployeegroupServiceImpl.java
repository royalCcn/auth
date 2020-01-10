package com.nan.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nan.entity.Employeegroup;
import com.nan.mapper.EmployeegroupMapper;
import com.nan.service.EmployeegroupService;

/**
 * <p>
 * 员工组 服务实现类
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Service
public class EmployeegroupServiceImpl extends ServiceImpl<EmployeegroupMapper, Employeegroup> implements EmployeegroupService {

	@Override
	public List<Employeegroup> list(int pageNum, int pageSize, String sortName, String sortOrder, String search) {
		List<Employeegroup> results = new ArrayList<>();
		List<Employeegroup> list =  baseMapper.list(pageNum,pageSize,sortName,sortOrder,search);
		if (list != null) {
			results = list;
		}
		return results;
	}
	
}
