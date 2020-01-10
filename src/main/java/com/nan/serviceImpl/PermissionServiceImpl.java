package com.nan.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nan.entity.Permission;
import com.nan.mapper.PermissionMapper;
import com.nan.service.PermissionService;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

	@Override
	public List<Permission> list(int pageNum, int pageSize, String sortName, String sortOrder, String search) {
		List<Permission> results = new ArrayList<>();
		List<Permission> list =  baseMapper.list(pageNum,pageSize,sortName,sortOrder,search);
		if (list != null) {
			results = list;
		}
		return results;
	}
	
}
