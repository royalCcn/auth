package com.nan.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.nan.entity.Employeegroup;
import com.nan.service.EmployeegroupService;
import com.nan.util.JsonUtil;

/**
 * <p>
 * 员工组 前端控制器
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@RestController
@RequestMapping("/employeegroup")
public class EmployeegroupController {
	
	@Resource
	private EmployeegroupService employeegroupService;
	private JSONObject returnJson = JsonUtil.init();
	private boolean isDelete = Contants.isDelete;
	private boolean isSuccess = Contants.isSuccess;

	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping("/save")
	public String save(HttpServletRequest request, HttpServletResponse response, 
			ModelMap map, @RequestBody Employeegroup employeegroup) {
		if (employeegroup != null) {
			if (employeegroup.getEmpgroupName() != null && !"".equals(employeegroup.getEmpgroupName())) {
				employeegroup.setEmpgroupName(employeegroup.getEmpgroupName());
			}
			if (employeegroup.getEmpgroupCode() != null && !"".equals(employeegroup.getEmpgroupCode())) {
				employeegroup.setEmpgroupCode(employeegroup.getEmpgroupCode());
			}
			if (!"".equals(employeegroup.getSta())) {
				employeegroup.setSta(employeegroup.getSta());
			}
			if (employeegroup.getInfo() != null && !"".equals(employeegroup.getInfo())) {
				employeegroup.setInfo(employeegroup.getInfo());
			}
			if (employeegroup.getEmployeegroupId() != null && !"".equals(employeegroup.getEmployeegroupId())) {
				employeegroup.setEmployeegroupId(employeegroup.getEmployeegroupId());
			} 
			isSuccess = employeegroupService.saveOrUpdate(employeegroup);
			if (!isSuccess) {
				returnJson.put("code", Contants.code_fail);
				returnJson.put("message", Contants.msg_fail);
			} 
		}
		return returnJson.toString();
	}
	
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request, HttpServletResponse response, 
			ModelMap modelMap, @RequestBody Employeegroup employeegroup) {
		if (employeegroup.getId() == null || "".equals(employeegroup.getId())) {
			employeegroup.setSta(0);
		} 
		returnJson.put("data", JSONObject.toJSONString(employeegroup));
		
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
			isDelete = employeegroupService.removeByIds(idList);
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
		List<Employeegroup> results = new ArrayList<>();
		results = employeegroupService.list(pageNum, pageSize, sortName, sortOrder, search);
		count = employeegroupService.count();
		
		returnJson.put("pageNum", pageNum);
		returnJson.put("pageSize", pageSize);
		returnJson.put("count", count);
		returnJson.put("data", JSONArray.toJSON(results));
		
		return returnJson.toString();
	}

}
