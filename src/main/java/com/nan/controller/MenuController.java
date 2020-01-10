package com.nan.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nan.common.Contants;
import com.nan.entity.Menu;
import com.nan.service.MenuService;
import com.nan.util.FileUtil;
import com.nan.util.JsonUtil;

/**
 * <p>
 * 菜单 前端控制器
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
	
	@Resource
	private MenuService menuService;
	private JSONObject returnJson = JsonUtil.init();
	private boolean isDelete = Contants.isDelete;
	private boolean isSuccess = Contants.isSuccess;
	
	/**
	 * 显示所有菜单
	 * @param model
	 * @return 
	 */
	@RequestMapping("/tree")
	public String tree(HttpServletRequest request, HttpServletResponse response, 
			ModelMap modelMap) {
		String empName = request.getParameter("empName");
		Map<String, Object> map = menuService.getTree(empName);

		return JSONArray.toJSON(map).toString();
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping("/save")
	public String save(HttpServletRequest request, HttpServletResponse response, 
			ModelMap map, @RequestBody Menu menu) {
		if (menu != null) {
			if (menu.getMenuName() != null && !"".equals(menu.getMenuName())) {
				menu.setMenuName(menu.getMenuName());
			}
			if (menu.getMenuCode() != null && !"".equals(menu.getMenuCode())) {
				menu.setMenuCode(menu.getMenuCode());
			}
			if (!"".equals(menu.getMenuOrder())) {
				menu.setMenuOrder(menu.getMenuOrder());
			}
			if (!"".equals(menu.getMenuLevel())) {
				menu.setMenuLevel(menu.getMenuLevel());
			}
			if (menu.getUrl() != null && !"".equals(menu.getUrl())) {
				menu.setUrl(menu.getUrl());
			}
			if (!"".equals(menu.getSta())) {
				menu.setSta(menu.getSta());
			}
			if (menu.getInfo() != null && !"".equals(menu.getInfo())) {
				menu.setInfo(menu.getInfo());
			}
			if (menu.getMenuId() != null && !"".equals(menu.getMenuId())) {
				menu.setMenuId(menu.getMenuId());
			}
			
			//图片上传
			Map<String, String> fileMap;
			try {
				fileMap = FileUtil.imgUpload(request, response);
				if (fileMap != null && fileMap.size() > 0) {
					for (String key : fileMap.keySet()) {
						if ("icon".equals(key)) {
							menu.setIcon(fileMap.get("icon"));
						}
					}
				}
			} catch (IOException e) {
				returnJson.put("code", Contants.code_fail);
				returnJson.put("message", Contants.msg_fail);
				e.printStackTrace();
			}
			
			isSuccess = menuService.saveOrUpdate(menu);
			if (!isSuccess) {
				returnJson.put("code", Contants.code_fail);
				returnJson.put("message", Contants.msg_fail);
			} 
		}
		return returnJson.toString();
	}
	
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request, HttpServletResponse response, 
			ModelMap modelMap, @RequestBody Menu menu) {
		if (menu.getId() == null || "".equals(menu.getId())) {
			menu.setSta(0);
		}		
		returnJson.put("data", JSONObject.toJSONString(menu));
		
		return returnJson.toString();
	}

	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response, 
			ModelMap modelMap) {
		String ids = request.getParameter("ids");
		if (ids != null && !"".equals(ids)) {
			ArrayList<String> idList = new ArrayList<>();
			String[] idArray = ids.split(",");
			if (idArray != null && idArray.length > 0) {
				for (String id : idArray) {
					idList.add(id);
				}
			}
			isDelete = menuService.removeByIds(idList);
		}
		if (!isDelete) {
			returnJson.put("code", Contants.code_fail);
			returnJson.put("message", Contants.msg_success);
		} 
		return returnJson.toString();
	}

	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, 
			ModelMap modelMap) {
		int count = 0;
		int pageSize = 15;
		int pageNum = 1;
		String sortOrder = "asc";
		String search = request.getParameter("search");
		String sortName = request.getParameter("sortName");
		if (request.getParameter("pageSize") != null && !"".equals(request.getParameter("pageSize"))) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		if (request.getParameter("pageNum") != null && !"".equals(request.getParameter("pageNum"))) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if (request.getParameter("sortOrder") != null && !"".equals(request.getParameter("sortOrder"))) {
			sortOrder = request.getParameter("sortOrder");
		}
		pageNum = (pageNum - 1) * pageSize;
		List<Menu> results = new ArrayList<>();
		results = menuService.list(pageNum, pageSize, sortName, sortOrder, search);
		count = menuService.count();

		returnJson.put("pageNum", pageNum);
		returnJson.put("pageSize", pageSize);
		returnJson.put("count", count);
		returnJson.put("data", JSONArray.toJSON(results));
		
		return returnJson.toString();
	}

}
