package com.nan.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nan.entity.MenuPermission;
import com.nan.mapper.MenuPermissionMapper;
import com.nan.service.MenuPermissionService;

/**
 * <p>
 * 菜单权限 服务实现类
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Service
public class MenuPermissionServiceImpl extends ServiceImpl<MenuPermissionMapper, MenuPermission> 
implements MenuPermissionService {

	@Override
	public List<MenuPermission> list(int pageNum, int pageSize, String sortName, 
			String sortOrder, String search) {
		List<MenuPermission> results = new ArrayList<>();
		List<MenuPermission> list =  baseMapper.list(pageNum,pageSize,sortName,sortOrder,search);
		if (list != null) {
			results = list;
		}
		return results;
	}
	
}
