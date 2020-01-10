package com.nan.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nan.entity.EmployeePost;
import com.nan.mapper.EmployeePostMapper;
import com.nan.service.EmployeePostService;

/**
 * <p>
 * 员工职位 服务实现类
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Service
public class EmployeePostServiceImpl extends ServiceImpl<EmployeePostMapper, 
EmployeePost> implements EmployeePostService {

	@Override
	public List<EmployeePost> list(int pageNum, int pageSize, String sortName, 
			String sortOrder, String search) {
		List<EmployeePost> results = new ArrayList<>();
		List<EmployeePost> list =  baseMapper.list(pageNum,pageSize,sortName,sortOrder,search);
		if (list != null) {
			results = list;
		}
		return results;
	}
	
}
