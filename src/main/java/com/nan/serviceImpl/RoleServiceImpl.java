package com.nan.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nan.entity.Role;
import com.nan.mapper.RoleMapper;
import com.nan.service.RoleService;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
	
	@Override
	public List<Role> list(int pageNum, int pageSize, String sortName, String sortOrder, String search) {
		List<Role> results = new ArrayList<>();
		List<Role> list =  baseMapper.list(pageNum,pageSize,sortName,sortOrder,search);
		if (list != null) {
			results = list;
		}
		return results;
	}

}
