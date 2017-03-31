package com.zrb.baseapp.tools;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonParsing {
	
	/**
	 * 判断服务器返回值的成功或者失败
	 * @param json				json数据
	 * @return					返回成功或者失败	true成功，false失败
	 */
	public static boolean getState(String json) {
		JSONObject jso;
		try {
			jso = new JSONObject(json);
			String okStauts = jso.getString("state");
			if("200".equals(okStauts)){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			MyLogUtils.e("解析原始数据异常e"+e.getMessage());
		}
		return false;
	}
	/**
	 * 判断服务器返回值的成功或者失败
	 * @param json				json数据
	 * @return					返回成功或者失败	true成功，false失败
	 */
	public static boolean getStateIsEmpty(String json){
		JSONObject jso;
		try {
			jso = new JSONObject(json);
			String okStauts = jso.getString("state");
			if(okStauts == null || "".equals(okStauts)){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			MyLogUtils.e("解析原始数据异常e"+e.getMessage());
			return true;
		}
	}
	
	/**
	 * 是否异地登陆
	 * @param json
	 * @return 
	 */
	public static boolean isLogin(String json){
		JSONObject jso;
		try {
			jso = new JSONObject(json);
			String okStauts = jso.getString("state");
			if("90006".equals(okStauts)){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			MyLogUtils.e("解析原始数据异常e"+e.getMessage());
		}
		return false;
	}
	
	/**
	 * 获取message
	 * @param json			json数据
	 * @return				返回错误信息
	 */
	public static String getMsg(String json) {
		JSONObject jso;
		String message = "";
		try {
			jso = new JSONObject(json);
			message = jso.getString("msg");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return TextUtil.decodeUnicode(message);
	}
	/**
	 * 获取data
	 * @param json			json数据
	 * @return				返回错误信息
	 */
	public static String getData(String json) {
		JSONObject jso;
		String message = "";
		try {
			jso = new JSONObject(json);
			message = jso.getString("data");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return message;
	}
	/**
	 * 
	 * @param Json
	 * @param key
	 * @return
	 */
	public static String getJson(String Json, String key){
		JSONObject jso;
		String message = "";
		try {
			jso = new JSONObject(Json);
			message = jso.getString(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return message;
	}
	
//	
//	/**
//	 * 获取验证码
//	 * @param json			json数据
//	 * @return				返回验证码的session
//	 */
//	public static String getCode(String json) {
//		JSONObject jso;
//		String vcjsessionid = "";
//		try {
//			jso = new JSONObject(json);
//			JSONObject o = new JSONObject(jso.getString("details"));
//			vcjsessionid = o.getString("vcjsessionid");
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		return vcjsessionid;
//
//	}
	
	

}
