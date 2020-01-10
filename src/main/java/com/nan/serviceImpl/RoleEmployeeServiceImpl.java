package com.nan.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nan.entity.RoleEmployee;
import com.nan.mapper.RoleEmployeeMapper;
import com.nan.service.RoleEmployeeService;

/**
 * <p>
 * 员工角色 服务实现类
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Service
public class RoleEmployeeServiceImpl extends ServiceImpl<RoleEmployeeMapper, RoleEmployee> 
implements RoleEmployeeService {

	@Override
	public List<RoleEmployee> list(int pageNum, int pageSize, String sortName, 
			String sortOrder, String search) {
		List<RoleEmployee> results = new ArrayList<>();
		List<RoleEmployee> list =  baseMapper.list(pageNum,pageSize,sortName,sortOrder,search);
		if (list != null) {
			results = list;
		}
		return results;
	}

}
