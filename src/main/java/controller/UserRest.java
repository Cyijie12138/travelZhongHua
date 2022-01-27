package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pojo.RequestResult;
import pojo.RequestResultSim;
import pojo.User;
import service.UserService;

@Controller
public class UserRest {
	@Autowired
	UserService userService;
	@Autowired
	RequestResult<User> result;
	@Autowired
	RequestResultSim<User> result_1;
	
	
	@RequestMapping(path = "/rest/user/selectAll", method=RequestMethod.POST)
	@ResponseBody
	public RequestResult<User> selectAll(@RequestBody User user) {
		System.out.println("调用了查询用户接口");
		List<User> user_list = userService.selectAll(user);
		result.setCode(200);
		result.setMessage("成功获取用户");
		result.setSuccess(true);
		result.setData(user_list);
		System.out.println(result);
		return result;
		
	}
	

	@RequestMapping(path = "/rest/user/userLogin", method=RequestMethod.GET)
	@ResponseBody
	public RequestResultSim<User> userLogin(String uname,String upassword){
		System.out.println("调用了用户登录接口");
		User user = userService.userLogin(uname,upassword);
		if(user!=null) {
			result_1.setCode(200);
			result_1.setMessage("用户名密码正确");
			result_1.setSuccess(true);
			result_1.setData(user);
			System.out.println(result_1);
			return result_1;
		}else {
			result_1.setCode(201);
			result_1.setMessage("用户名或密码不正确");
			result_1.setSuccess(false);
			result_1.setData(user);
			System.out.println(result_1);
			return result_1;
		}
		
	}
	
	@RequestMapping(path = "/rest/user/userRegister", method=RequestMethod.POST)
	@ResponseBody
	public RequestResultSim<User> userRegister(@RequestBody User user){
		System.out.println("进入到注册用户接口---"+user);
		int result = userService.userInsert(user);
		if(result>=1) {
			System.out.println("注册成功");
			result_1.setCode(200);
			result_1.setMessage("用户注册成功");
			result_1.setSuccess(true);
			result_1.setData(user);
			return result_1;
		}else {
			System.out.println("注册失败");
			result_1.setCode(201);
			result_1.setMessage("用户注册失败");
			result_1.setSuccess(false);
			result_1.setData(null);
			return result_1;
		}
	}
}
