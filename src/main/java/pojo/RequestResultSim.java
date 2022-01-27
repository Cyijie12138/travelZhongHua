package pojo;

import org.springframework.stereotype.Component;

@Component
public class RequestResultSim<T> {
	int code;
	String message;
	boolean success;
	T data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "RequestResultSim [code=" + code + ", message=" + message + ", success=" + success + ", data=" + data
				+ "]";
	}
	public RequestResultSim(int code, String message, boolean success, T data) {
		super();
		this.code = code;
		this.message = message;
		this.success = success;
		this.data = data;
	}
	public RequestResultSim() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
