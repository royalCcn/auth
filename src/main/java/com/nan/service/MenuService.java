package com.nan.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nan.entity.Menu;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
public interface MenuService extends IService<Menu> {
	
	public Map<String, Object> getTree(String empName);
	
	List<Menu> list(int pageNum, int pageSize, String sortName, String sortOrder, String search);

}
