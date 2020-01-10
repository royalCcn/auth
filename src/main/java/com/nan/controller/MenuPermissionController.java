package com.nan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nan.common.Contants;
import com.nan.entity.MenuPermission;
import com.nan.service.MenuPermissionService;
import com.nan.util.JsonUtil;

/**
 * <p>
 * 菜单权限 前端控制器
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@RestController
@RequestMapping("/menu-permission")
public class MenuPermissionController {
	
	@Autowired
	private MenuPermissionService menuPermissionService;
	private JSONObject returnJson = JsonUtil.init();
	private boolean isDelete = Contants.isDelete;
	private boolean isSuccess = Contants.isSuccess;
	
	@RequestMapping("/save")
	public String save(HttpServletRequest request, HttpServletResponse response, 
			ModelMap map, @RequestBody MenuPermission menuPermission) {
		if (menuPermission != null) {
			if (menuPermission.getMenuId() != null 
					&& !"".equals(menuPermission.getMenuId())) {
				menuPermission.setMenuId(menuPermission.getMenuId());
			}
			if (menuPermission.getPermissionId() != null 
					&& !"".equals(menuPermission.getPermissionId())) {
				menuPermission.setPermissionId(menuPermission.getPermissionId());
			}
			isSuccess = menuPermissionService.saveOrUpdate(menuPermission);
			if (!isSuccess) {
				returnJson.put("code", Contants.code_fail);
				returnJson.put("message", Contants.msg_fail);
			} 
		}
		return returnJson.toString();
	}
	
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request, HttpServletResponse response, 
			ModelMap modelMap, @RequestBody MenuPermission menuPermission) {
		returnJson.put("data", JSONObject.toJSONString(menuPermission));

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
			isDelete = menuPermissionService.removeByIds(idList);
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
		List<MenuPermission> results = new ArrayList<>();
		results = menuPermissionService.list(pageNum, pageSize, sortName, sortOrder, search);
		count = menuPermissionService.count();
		
		returnJson.put("pageNum", pageNum);
		returnJson.put("pageSize", pageSize);
		returnJson.put("count", count);
		returnJson.put("data", JSONArray.toJSON(results));
		
		return returnJson.toString();
	}

}
