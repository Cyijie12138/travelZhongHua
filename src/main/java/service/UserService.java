package service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserMapper;
import pojo.User;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	

	public User userLogin(String uname, String upassword) {
		// TODO Auto-generated method stub
		User user = userMapper.getUser(uname);
		if(user==null) {
			return null;
		}else if(user.getUpassword().equals(upassword)){
			return user;
		}else {
			return null;
		}
	}


	public int userInsert(User user) {
		// TODO Auto-generated method stub
		User oldUser = userMapper.getUser(user.getUname());
		if(oldUser!=null) {
			return 0;
		}else {
			//默认时间处理
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			user.setUtime(timestamp);
			int result = userMapper.insert(user);
			return result;
		}
	}


	public List<User> selectAll(User user) {
		// TODO Auto-generated method stub
		List<User> result = userMapper.selectAll(user);
		return result;
	}
	
	

}
