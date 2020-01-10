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
import com.nan.entity.Application;
import com.nan.service.ApplicationService;
import com.nan.util.JsonUtil;

/**
 * <p>
 * 应用系统 前端控制器
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@RestController
@RequestMapping("/application")
public class ApplicationController {
	
	@Resource
	private ApplicationService applicationService;
	private JSONObject returnJson = JsonUtil.init();
	private boolean isDelete = Contants.isDelete;
	private boolean isSuccess = Contants.isSuccess;

	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping("/save")
	public String save(HttpServletRequest request, HttpServletResponse response, 
			ModelMap map, @RequestBody Application application) {
		if (application != null) {
			if (application.getAppName() != null && !"".equals(application.getAppName())) {
				application.setAppCode(application.getAppName());
			}
			if (application.getAppCode() != null && !"".equals(application.getAppCode())) {
				application.setAppCode(application.getAppCode());
			}
			if (!"".equals(application.getSta())) {
				application.setSta(application.getSta());
			}
			if (application.getInfo() != null && !"".equals(application.getInfo())) {
				application.setInfo(application.getInfo());
			}
			isSuccess = applicationService.saveOrUpdate(application);
			if (!isSuccess) {
				returnJson.put("code", Contants.code_fail);
				returnJson.put("message", Contants.msg_fail);
			} 
		}
		return returnJson.toString();
	}
	
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request, HttpServletResponse response, 
			ModelMap modelMap, @RequestBody Application application) {
		if (application.getId() == null || "".equals(application.getId())) {
			application.setSta(0);
		} 
		returnJson.put("data", JSONObject.toJSONString(application));
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
			isDelete = applicationService.removeByIds(idList);
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
		List<Application> results = new ArrayList<>();
		results = applicationService.list(pageNum, pageSize, sortName, sortOrder, search);
		count = applicationService.count();

		returnJson.put("pageNum", pageNum);
		returnJson.put("pageSize", pageSize);
		returnJson.put("count", count);
		returnJson.put("data", JSONArray.toJSON(results));
		
		return returnJson.toString();
	}

}
