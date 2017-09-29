package com.jhon.util;

import org.json.JSONObject;

/**
 * <p>功能描述</br> 统一返回结果处理</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName JSONResult
 * @date 2017/9/29 15:28
 */
public class JSONResult {
	/**
	 * 返回结果对象的封装
	 *
	 * @param status  状态
	 * @param message 提示信息
	 * @param result  结果对象
	 * @return 放回json格式
	 */
	public static String fillResultString(Integer status, String message, Object result) {
		JSONObject jsonObject = new JSONObject() {{
			put("status", status);
			put("message", message);
			put("result", result);
		}};
		return jsonObject.toString();
	}
}
