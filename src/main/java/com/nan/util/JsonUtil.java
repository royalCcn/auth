package com.nan.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nan.common.Contants;

/**
 * json解析工具
 * @author nan
 */
public class JsonUtil {

	/**
	 * 对象初始化
	 * @return
	 */
	public static JSONObject init() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", Contants.code_success);
		jsonObject.put("message", Contants.msg_success);
		jsonObject.put("data", null);

		return jsonObject;
	}

	/**
	 * 错误信息
	 * @param code
	 * @param message
	 * @return
	 */
	public static String error(String code, String message) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", Contants.code_fail);
		jsonObject.put("message", Contants.msg_fail);

		return jsonObject.toString();
	}
	
	/**
	 * 更新错误对象
	 * @param jsonObject
	 * @param code
	 * @param message
	 * @return
	 */
	public static JSONObject updateError(JSONObject jsonObject, String code, String message) {
		jsonObject.put("code", Contants.code_fail);
		jsonObject.put("message", Contants.msg_fail);
		
		return jsonObject;
	}

	/**
	 * 数据处理
	 * @param list
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JSONArray dataHandle(List<Map> list) {
		JSONArray data = new JSONArray();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		if (list != null && list.size() > 0) {
			for (Map map : list) {
				if (map != null) {
					JSONObject object = new JSONObject();
					Set<String> set = map.keySet();
					Iterator<String> iterator = set.iterator();
					while (iterator.hasNext()) {
						String key = iterator.next();
						if (StringUtils.isNotBlank(key)) {
							// 处理时间
							if (map.get(key) instanceof Date) {
								if (map.get(key) != null) {
									map.put(key, format.format((Date) map.get(key)));
								}
							}
							//处理null
							if ("null".equals(map.get(key)) || map.get(key) == null) {
								object.put(key, "0");
							} else {
								object.put(key, map.get(key));
							}
						}
					}
					data.add(object);
				}
			}
		}
		return data;
	}

	/**
	 * 数据整合
	 * @param list
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String dataLink(List<Map> list) {
		String data = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		if (list != null && list.size() > 0) {
			for (Map map : list) {
				if (map != null) {
					JSONObject object = new JSONObject();
					Set<String> set = map.keySet();
					Iterator<String> iterator = set.iterator();
					while (iterator.hasNext()) {
						String key = iterator.next();
						if (StringUtils.isNotBlank(key)) {
							// 处理时间
							if (map.get(key) instanceof Date) {
								if (map.get(key) != null) {
									map.put(key, format.format((Date) map.get(key)));
								}
							}
							//处理null
							if ("null".equals(map.get(key)) || map.get(key) == null) {
								object.put(key, "0");
							} else {
								object.put(key, map.get(key));
							}
							data += "@@" + map.get(key);
						}
					}
				}
			}
		}
		
		if (StringUtils.isNotBlank(data)) {
			return data.substring(2);
		}else {
			return data;
		}
	}

}
