package pojo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
@Controller
public class RequestResult<T>{
	int code;
	String message;
	boolean success;
	List<T> data;
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
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public void setMapData(List<Map> data) {
		this.data = (List<T>) data;
		// TODO Auto-generated method stub
		
	}
	
}
