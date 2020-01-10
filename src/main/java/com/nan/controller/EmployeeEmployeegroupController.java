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
import com.nan.entity.EmployeeEmployeegroup;
import com.nan.service.EmployeeEmployeegroupService;
import com.nan.util.JsonUtil;

/**
 * <p>
 * 员工员工组 前端控制器
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@RestController
@RequestMapping("/employee-employeegroup")
public class EmployeeEmployeegroupController {
	
	@Autowired
	private EmployeeEmployeegroupService employeeEmployeegroupService;
	private JSONObject returnJson = JsonUtil.init();
	private boolean isDelete = Contants.isDelete;
	private boolean isSuccess = Contants.isSuccess;
	
	@RequestMapping("/save")
	public String save(HttpServletRequest request, HttpServletResponse response, 
			ModelMap map, @RequestBody EmployeeEmployeegroup employeeEmployeegroup) {
		if (employeeEmployeegroup != null) {
			if (employeeEmployeegroup.getEmployeeId() != null 
					&& !"".equals(employeeEmployeegroup.getEmployeeId())) {
				employeeEmployeegroup.setEmployeeId(employeeEmployeegroup.getEmployeeId());
			}
			if (employeeEmployeegroup.getEmployeegroupId() != null 
					&& !"".equals(employeeEmployeegroup.getEmployeegroupId())) {
				employeeEmployeegroup.setEmployeegroupId(employeeEmployeegroup.getEmployeegroupId());
			}
			isSuccess = employeeEmployeegroupService.saveOrUpdate(employeeEmployeegroup);
			if (!isSuccess) {
				returnJson.put("code", Contants.code_fail);
				returnJson.put("message", Contants.msg_fail);
			} 
		}
		return returnJson.toString();
	}
	
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request, HttpServletResponse response, 
			ModelMap modelMap, @RequestBody EmployeeEmployeegroup employeeEmployeegroup) {
		returnJson.put("data", JSONObject.toJSONString(employeeEmployeegroup));

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
			isDelete = employeeEmployeegroupService.removeByIds(idList);
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
		List<EmployeeEmployeegroup> results = new ArrayList<>();
		results = employeeEmployeegroupService.list(pageNum, pageSize, sortName, sortOrder, search);
		count = employeeEmployeegroupService.count();
		
		returnJson.put("pageNum", pageNum);
		returnJson.put("pageSize", pageSize);
		returnJson.put("count", count);
		returnJson.put("data", JSONArray.toJSON(results));
		
		return returnJson.toString();
	}

}
