package com.nan.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nan.entity.RoleMenu;
import com.nan.mapper.RoleMenuMapper;
import com.nan.service.RoleMenuService;

/**
 * <p>
 * 角色菜单 服务实现类
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

	@Override
	public List<RoleMenu> list(int pageNum, int pageSize, String sortName, 
			String sortOrder, String search) {
		List<RoleMenu> results = new ArrayList<>();
		List<RoleMenu> list =  baseMapper.list(pageNum,pageSize,sortName,sortOrder,search);
		if (list != null) {
			results = list;
		}
		return results;
	}

}
