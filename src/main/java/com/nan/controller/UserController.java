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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nan.common.Contants;
import com.nan.entity.User;
import com.nan.service.UserService;
import com.nan.util.JsonUtil;

/**
 * <p>
 * 人员管理 前端控制器
 * </p>
 *
 * @author nan
 * @since 2019-12-15
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	private JSONObject returnJson = JsonUtil.init();
	private boolean isDelete = Contants.isDelete;
	private boolean isSuccess = Contants.isSuccess;
	
	@RequestMapping("/womenCount")
	public String womenCount() {
		JSONObject countJson = new JSONObject();
		
		JSONObject title = new JSONObject();
		title.put("text", "女童鞋人员统计分析表");
		
		JSONObject tooltip = new JSONObject();
		JSONObject legend = new JSONObject();
		JSONArray legendData = new JSONArray();
		/*legendData.add("1栋");
		legendData.add("2栋");
		legendData.add("3栋");
		legendData.add("4栋");*/
		legend.put("data", legendData);
		
		JSONArray series = new JSONArray();
		JSONObject women = new JSONObject();
		
		series.add(women);
		JSONArray womenData = new JSONArray();
		
		women.put("name", "女童鞋");
		women.put("type", "pie");
		
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.eq("is_in", 0);
		
		//1栋
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("name", "1栋");
		jsonObject1.put("value", userService.count(wrapper));
		
		//2栋
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("name", "2栋");
		jsonObject2.put("value", 60);
		
		//3栋
		JSONObject jsonObject3 = new JSONObject();
		jsonObject3.put("name", "3栋");
		jsonObject3.put("value", 55);
		
		//4栋
		JSONObject jsonObject4 = new JSONObject();
		jsonObject4.put("name", "4栋");
		jsonObject4.put("value", 24);
		
		//5栋
		JSONObject jsonObject5 = new JSONObject();
		jsonObject5.put("name", "5栋");
		jsonObject5.put("value", 5);
		
		//6栋
		JSONObject jsonObject6 = new JSONObject();
		jsonObject6.put("name", "6栋");
		jsonObject6.put("value", 40);
		
		womenData.add(jsonObject1);
		womenData.add(jsonObject2);
		womenData.add(jsonObject3);
		womenData.add(jsonObject4);
		womenData.add(jsonObject5);
		womenData.add(jsonObject6);
		
		women.put("data", womenData);
		
		countJson.put("series", series);
		countJson.put("title", title);
		countJson.put("tooltip", tooltip);
		countJson.put("legend", legend);
		
		returnJson.put("data", countJson);
		
		return returnJson.toString();
	}
	
	@RequestMapping("/manCount")
	public String manCount() {
		JSONObject countJson = new JSONObject();
		
		JSONObject title = new JSONObject();
		title.put("text", "男童鞋人员统计分析表");
		
		JSONObject tooltip = new JSONObject();
		JSONObject legend = new JSONObject();
		JSONArray legendData = new JSONArray();
		/*legendData.add("1栋");
		legendData.add("2栋");
		legendData.add("3栋");
		legendData.add("4栋");*/
		legend.put("data", legendData);
		
		JSONArray series = new JSONArray();
		JSONObject man = new JSONObject();
		series.add(man);
		JSONArray manData = new JSONArray();

		man.put("name", "男童鞋");
		man.put("type", "pie");
		
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.eq("is_in", 0);
		
		//1栋
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("name", "1栋");
		jsonObject1.put("value", userService.count(wrapper));
		
		//2栋
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("name", "2栋");
		jsonObject2.put("value", 53);
		
		//3栋
		JSONObject jsonObject3 = new JSONObject();
		jsonObject3.put("name", "3栋");
		jsonObject3.put("value", 22);
		
		//4栋
		JSONObject jsonObject4 = new JSONObject();
		jsonObject4.put("name", "4栋");
		jsonObject4.put("value", 88);
		
		manData.add(jsonObject1);
		manData.add(jsonObject2);
		manData.add(jsonObject3);
		manData.add(jsonObject4);
		
		man.put("data", manData);
		
		countJson.put("series", series);
		countJson.put("title", title);
		countJson.put("tooltip", tooltip);
		countJson.put("legend", legend);
		
		returnJson.put("data", countJson);
		
		return returnJson.toString();
	}
	
	@RequestMapping("/dormUserCount")
	public String dormUserCount() {
		JSONObject countJson = new JSONObject();
		
		JSONObject title = new JSONObject();
		title.put("text", "在/离校人员统计分析表");
		
		JSONObject tooltip = new JSONObject();
		JSONObject legend = new JSONObject();
		JSONArray legendData = new JSONArray();
		legendData.add("在校");
		legendData.add("离校");
		
		JSONObject xAxis = new JSONObject();
		JSONArray xAxisData = new JSONArray();
		xAxisData.add("2017");
		xAxisData.add("2018");
		xAxisData.add("2019");
		
		JSONArray series = new JSONArray();
		JSONObject man = new JSONObject();
		JSONObject woman = new JSONObject();
		
		series.add(man);
		series.add(woman);
		
		JSONArray manData = new JSONArray();
		JSONArray womanData = new JSONArray();

		man.put("name", "在校");
		man.put("type", "line");
		
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.eq("is_in", 0);
		userService.count(wrapper);
		manData.add(userService.count(wrapper));
		manData.add(6);
		manData.add(2);
		manData.add(8);
		man.put("data", manData);
		
		woman.put("name", "离校");
		woman.put("type", "line");
		
		QueryWrapper<User> wrapper2 = new QueryWrapper<User>();
		wrapper2.eq("is_in", 1);
		userService.count(wrapper2);
		womanData.add(userService.count(wrapper2));
		womanData.add(8);
		womanData.add(6);
		womanData.add(5);
		
		woman.put("data", womanData);
		
		JSONObject yAxis = new JSONObject();

		countJson.put("series", series);
		countJson.put("yAxis", yAxis);
		
		countJson.put("title", title);
		countJson.put("tooltip", tooltip);
		
		legend.put("data", legendData);
		countJson.put("legend", legend);
		
		xAxis.put("data", xAxisData);
		countJson.put("xAxis", xAxis);
		
		returnJson.put("data", countJson);
		
		return returnJson.toString();
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping("/save")
	public String save(HttpServletRequest request, HttpServletResponse response, 
			ModelMap map, @RequestBody User user) {
		if (user != null) {
			if (user.getUserName() != null && !"".equals(user.getUserName())) {
				user.setUserName(user.getUserName());
			}
			if (user.getUserCode() != null && !"".equals(user.getUserCode())) {
				user.setUserCode(user.getUserCode());
			}
			if (!"".equals(user.getIsIn())) {
				user.setIsIn(user.getIsIn());
			}
			if (!"".equals(user.getSta())) {
				user.setSta(user.getSta());
			}
			if (user.getInfo() != null && !"".equals(user.getInfo())) {
				user.setInfo(user.getInfo());
			}
			if (user.getHouseId() != null && !"".equals(user.getHouseId())) {
				user.setHouseId(user.getHouseId());
			}
			isSuccess = userService.saveOrUpdate(user);
			if (!isSuccess) {
				returnJson.put("code", Contants.code_fail);
				returnJson.put("message", Contants.msg_fail);
			} 
		}
		return returnJson.toString();
	}
	
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request, HttpServletResponse response, 
			ModelMap modelMap, @RequestBody User user) {
		if (user.getId() == null || "".equals(user.getId())) {
			user.setSta(0);
		}
		returnJson.put("data", JSONObject.toJSONString(user));

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
			isDelete = userService.removeByIds(idList);
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
		List<User> results = new ArrayList<>();
		results = userService.list(pageNum, pageSize, sortName, sortOrder, search);
		count = userService.count();
		
		returnJson.put("pageNum", pageNum);
		returnJson.put("pageSize", pageSize);
		returnJson.put("count", count);
		returnJson.put("data", JSONArray.toJSON(results));
		
		return returnJson.toString();
	}

}
