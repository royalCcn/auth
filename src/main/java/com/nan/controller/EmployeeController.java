package com.nan.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nan.common.Contants;
import com.nan.entity.Employee;
import com.nan.service.EmployeeService;
import com.nan.util.FileUtil;
import com.nan.util.JsonUtil;

/**
 * <p>
 * 员工 前端控制器
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Resource
	private EmployeeService employeeService;
	private JSONObject returnJson = JsonUtil.init();
	private boolean isDelete = Contants.isDelete;
	private boolean isSuccess = Contants.isSuccess;
	
	@RequestMapping("/register")
	@ResponseBody
	public String register(HttpServletRequest request, HttpServletResponse response, 
			ModelMap map) {
		String id = request.getParameter("id");
		String empName = request.getParameter("empName");
		String empCode = request.getParameter("empCode");
		String account = request.getParameter("account");
		String pwd = request.getParameter("pwd");

		Employee employee;
		if (id != null && !"".equals(id)) {
			employee = employeeService.getById(id);
		} else {
			employee = new Employee();
		}

		if (employee != null) {
			if (empName != null && !"".equals(empName)) {
				employee.setEmpName(empName);
			}
			if (empCode != null && !"".equals(empCode)) {
				employee.setEmpCode(empCode);
			}
			if (account != null && !"".equals(account)) {
				employee.setAccount(account);
			}
			if (pwd != null && !"".equals(pwd)) {
				employee.setPwd(pwd);
			}
			isSuccess = employeeService.saveOrUpdate(employee);
			if (!isSuccess) {
				returnJson.put("code", Contants.code_fail);
				returnJson.put("message", Contants.msg_fail);
			} else {
				returnJson.put("data", employee);
			}
		}
		return returnJson.toString();
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response, 
			ModelMap modelMap) {
		String account = request.getParameter("account");
		String pwd = request.getParameter("pwd");
		
		QueryWrapper<Employee> wrapper = new QueryWrapper<>();
		wrapper.eq("account", account);
		wrapper.eq("pwd", pwd);
		
		Employee employee = employeeService.getOne(wrapper);
		if (employee != null) {
			returnJson.put("code", Contants.code_success);
			returnJson.put("message", Contants.msg_success);
			returnJson.put("data", employee);
		} else {
			returnJson.put("code", Contants.code_fail);
			returnJson.put("message", Contants.msg_fail);
		}
		return returnJson.toString();
	}

	@RequestMapping("/unlocked")
	@ResponseBody
	public String unlocked(HttpServletRequest request, HttpServletResponse response, 
			ModelMap modelMap) {
		String empId = request.getParameter("empId");
		String pwd = request.getParameter("pwd");
		
		QueryWrapper<Employee> wrapper = new QueryWrapper<>();
		wrapper.eq("id", empId);
		
		Employee employee = employeeService.getOne(wrapper);
		if (employee != null) {
			if (employee.getPwd() != null && !"".equals(employee.getPwd())) {
				if (employee.getPwd().equals(pwd)) {
					returnJson.put("code", Contants.code_success);
					returnJson.put("message", Contants.msg_success);
					returnJson.put("data", employee);
				} else {
					returnJson.put("code", Contants.code_fail);
					returnJson.put("message", Contants.msg_fail);
				}
			}
		}
		return returnJson.toString();
	}
	
	@RequestMapping("/reset")
	@ResponseBody
	public String reset(HttpServletRequest request, HttpServletResponse response, 
			ModelMap map) {
		String empName = request.getParameter("empName");
		String account = request.getParameter("account");
		String pwd = request.getParameter("pwd");
		
		QueryWrapper<Employee> wrapper = new QueryWrapper<Employee>();
		wrapper.eq("emp_name", empName);
		wrapper.eq("account", account);
		
		Employee employee = employeeService.getOne(wrapper);
		if (employee != null) {
			employee.setPwd(pwd);
		}
		isSuccess = employeeService.saveOrUpdate(employee);
		if (!isSuccess) {
			returnJson.put("code", Contants.code_fail);
			returnJson.put("message", Contants.msg_fail);
		} else {
			returnJson.put("code", Contants.code_success);
			returnJson.put("message", Contants.msg_success);
			returnJson.put("data", employee);
		}
		return returnJson.toString();
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping("/save")
	public String save(HttpServletRequest request, HttpServletResponse response, 
			ModelMap map,@RequestBody Employee employee) {
		if (employee != null) {
			if (employee.getEmpName() != null && !"".equals(employee.getEmpName())) {
				employee.setEmpName(employee.getEmpName());
			}
			if (employee.getEmpCode() != null && !"".equals(employee.getEmpCode())) {
				employee.setEmpCode(employee.getEmpCode());
			}
			if (employee.getAccount() != null && !"".equals(employee.getAccount())) {
				employee.setAccount(employee.getAccount());
			}
			if (employee.getPwd() != null && !"".equals(employee.getPwd())) {
				employee.setPwd(employee.getPwd());
			}
			if (employee.getEmpType() != null && !"".equals(employee.getEmpType())) {
				employee.setEmpType(employee.getEmpType());
			}
			if (employee.getPhone() != null && !"".equals(employee.getPhone())) {
				employee.setPhone(employee.getPhone());
			}
			if (employee.getAddress() != null && !"".equals(employee.getAddress())) {
				employee.setAddress(employee.getAddress());
			}
			if (employee.getHireDate() != null && !"".equals(employee.getHireDate())) {
				employee.setHireDate(employee.getHireDate());
			}
			if (employee.getGraduation() != null && !"".equals(employee.getGraduation())) {
				employee.setGraduation(employee.getGraduation());
			}
			if (employee.getEducation() != null && !"".equals(employee.getEducation())) {
				employee.setEducation(employee.getEducation());
			}
			if (!"".equals(employee.getSta())) {
				employee.setSta(employee.getSta());
			}
			if (employee.getInfo() != null && !"".equals(employee.getInfo())) {
				employee.setInfo(employee.getInfo());
			}
			
			//图片上传
			Map<String, String> imageMap;
			try {
				imageMap = FileUtil.imgUpload(request, response);
				if (imageMap != null && imageMap.size() > 0) {
					for (String key : imageMap.keySet()) {
						if ("image".equals(key)) {
							employee.setImage(imageMap.get("image"));
						}
					}
				}
			} catch (IOException e) {
				returnJson.put("code", Contants.code_fail);
				returnJson.put("message", Contants.msg_success);
				e.printStackTrace();
			}
			
			//文件上传
			Map<String, String> fileMap;
			try {
				fileMap = FileUtil.upload(request);
				if (fileMap != null && fileMap.size() > 0) {
					for (String key : fileMap.keySet()) {
						if ("empResume".equals(key)) {
							employee.setEmpResume(fileMap.get("empResume"));
						}
					}
				}
			} catch (IOException e) {
				returnJson.put("code", Contants.code_fail);
				returnJson.put("message", Contants.msg_success);
				e.printStackTrace();
			}
			isSuccess = employeeService.saveOrUpdate(employee);
			if (!isSuccess) {
				returnJson.put("code", Contants.code_fail);
				returnJson.put("message", Contants.msg_fail);
			} 
		}
		return returnJson.toString();
	}
	
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request, HttpServletResponse response, 
			ModelMap modelMap) {
		Employee employee;
		String id = request.getParameter("id");
		if (id != null && !"".equals(id)) {
			employee = employeeService.getById(id);
		} else {
			employee = new Employee();
			employee.setSta(0);
		}
		modelMap.put("object", employee);

		return "employee/edit.html";
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
			isDelete = employeeService.removeByIds(idList);
		}
		if (!isDelete) {
			returnJson.put("code", Contants.code_fail);
			returnJson.put("message", Contants.msg_success);
		} 
		return returnJson.toString();
	}

	@RequestMapping("/list")
	@ResponseBody
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
		List<Employee> results = new ArrayList<>();
		results = employeeService.list(pageNum, pageSize, sortName, sortOrder, search);
		count = employeeService.count();
		
		returnJson.put("pageNum", pageNum);
		returnJson.put("pageSize", pageSize);
		returnJson.put("total", count);
		returnJson.put("data", JSONArray.toJSON(results));
		
		return returnJson.toString();
	}
	
}
