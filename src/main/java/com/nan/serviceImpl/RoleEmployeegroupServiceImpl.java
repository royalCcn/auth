package com.nan.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nan.entity.RoleEmployeegroup;
import com.nan.mapper.RoleEmployeegroupMapper;
import com.nan.service.RoleEmployeegroupService;

/**
 * <p>
 * 角色员工组 服务实现类
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Service
public class RoleEmployeegroupServiceImpl extends ServiceImpl<RoleEmployeegroupMapper, RoleEmployeegroup> 
implements RoleEmployeegroupService {

	@Override
	public List<RoleEmployeegroup> list(int pageNum, int pageSize, String sortName, 
			String sortOrder, String search) {
		List<RoleEmployeegroup> results = new ArrayList<>();
		List<RoleEmployeegroup> list =  baseMapper.list(pageNum,pageSize,sortName,sortOrder,search);
		if (list != null) {
			results = list;
		}
		return results;
	}

}
