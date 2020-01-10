package com.nan.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nan.entity.RolePermission;
import com.nan.mapper.RolePermissionMapper;
import com.nan.service.RolePermissionService;

/**
 * <p>
 * 角色权限 服务实现类
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> 
implements RolePermissionService {

	@Override
	public List<RolePermission> list(int pageNum, int pageSize, String sortName, 
			String sortOrder, String search) {
		List<RolePermission> results = new ArrayList<>();
		List<RolePermission> list =  baseMapper.list(pageNum,pageSize,sortName,sortOrder,search);
		if (list != null) {
			results = list;
		}
		return results;
	}

}
