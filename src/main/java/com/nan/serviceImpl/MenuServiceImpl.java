package com.nan.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nan.entity.Menu;
import com.nan.mapper.MenuMapper;
import com.nan.service.MenuService;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
	
	/**
	 * 获取菜单树
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, Object> getTree(String empName) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			List<Menu> allMenu = baseMapper.selectList(null);
			//List<Menu> rootMenu = baseMapper.getMenuByEmpName(empName);
			
			
			// 根节点
			List<Menu> rootMenu = new ArrayList<Menu>();
			for (Menu menu : allMenu) {
				// 父节点是0的为根节点
				if (menu.getMenuLevel() == 0) {
					rootMenu.add(menu);
				}
			}
			
			
			// 排序
			Collections.sort(rootMenu, order());
			for (Menu menu : rootMenu) {
				// 获取根节点下的所有子节点 
				List<Menu> menus = getChild(menu.getId(), allMenu);
				menu.setMenus(menus);
			}
			data.put("code", "1");
			data.put("message", "success");
			data.put("tree", sortTree(rootMenu));
			return data;
		} catch (Exception e) {
			data.put("code", "-1");
			data.put("message", "fail");
			data.put("tree", new ArrayList());
			e.printStackTrace();
			return data;
		}
	}
	
	public JSONArray sortTree(List<Menu> rootMenu) {
		if (rootMenu != null && rootMenu.size() > 0) {
			JSONArray dataArray = new JSONArray();
			for (int i = 0; i < rootMenu.size(); i++) {
				if (rootMenu.get(i) != null) {
					JSONObject dataObject = new JSONObject();
					dataObject.put("text", rootMenu.get(i).getMenuName());
					dataObject.put("href", rootMenu.get(i).getUrl());
					dataObject.put("tags", rootMenu.get(i).getMenus().size());
					dataObject.put("icon", rootMenu.get(i).getIcon());
					
					if (rootMenu.get(i).getMenus() != null && rootMenu.get(i).getMenus().size() > 0) {
						JSONArray nodes = new JSONArray();
						nodes = sortTree(rootMenu.get(i).getMenus());

						dataObject.put("nodes", nodes);
					}
					dataArray.add(dataObject);
				}
			}
			return dataArray;
		}
		return null;
	}
	
	/**
	 * 获取子节点
	 * @param parentId  父节点id
	 * @param allMenu  所有菜单列表
	 * @return  每个根节点下，所有子菜单列表
	 */
	public static List<Menu> getChild(String menuId, List<Menu> allMenu) {
		List<Menu> childList = new ArrayList<Menu>();
		for (Menu menu : allMenu) {
			if (menu.getMenuId() != null && !"".equals(menu.getMenuId()) && 
					menu.getMenuId().equals(menuId)) {
				childList.add(menu);
			}
		}
		// 递归
		for (Menu menu : childList) {
			menu.setMenus(getChild(menu.getId(), allMenu));
		}
		// 排序
		Collections.sort(childList, order());
		// 如果节点下没有子节点，返回一个空List（递归退出）
		if (childList.size() == 0) {
			return new ArrayList<Menu>();
		}
		return childList;
	}

	/**
	 * 根据order排序
	 * @return
	 */
	public static Comparator<Menu> order() {
		Comparator<Menu> comparator = new Comparator<Menu>() {
			@SuppressWarnings("unlikely-arg-type")
			@Override
			public int compare(Menu m1, Menu m2) {
				if (!"".equals(m1.getMenuOrder()) && m1.getMenuOrder() != m2.getMenuOrder()) {
					return m1.getMenuOrder() - m2.getMenuOrder();
				}
				return 0;
			}
		};
		return comparator;
	}
	
	@Override
	public List<Menu> list(int pageNum, int pageSize, String sortName, String sortOrder, String search) {
		List<Menu> results = new ArrayList<>();
		List<Menu> list =  baseMapper.list(pageNum,pageSize,sortName,sortOrder,search);
		if (list != null) {
			results = list;
		}
		return results;
	}

}
