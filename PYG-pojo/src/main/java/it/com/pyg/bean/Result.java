package it.com.pyg.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装返回结果
 */
public class Result implements Serializable{
	private static final long serialVersionUID = 1L;
	private boolean success; // 是否成功
	private String message; // 返回信息
	private int code; // 状态码,200 成功,100失败
	private Map<String, Object> data = new HashMap<String, Object>(); // 返回数据

	public Result() {
	}

	public Result(boolean success, String message, int code, Map<String, Object> data) {
		super();
		this.success = success;
		this.message = message;
		this.code = code;
		this.data = data;
	}

	// 返回 Result 对象可以实现 add() 方法的连续调用
	public Result add(String key, Object value) { // 添加返回给客户端的数据
		this.getData().put(key, value);
		return this;
	}

	// 成功时可以快捷调用的方法
	public static Result success(String message, Map<String, Object> data) {
		Result result = new Result();
		result.setSuccess(true);
		result.setCode(200);
		result.setMessage(message);
		result.setData(data);
		return result;
	}

	// 失败时可以快捷调用的方法
	public static Result fail(String message, Map<String, Object> data) {
		Result result = new Result();
		result.setSuccess(false);
		result.setCode(100);
		result.setMessage(message);
		result.setData(data);
		return result;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
