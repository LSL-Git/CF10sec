package com.lsl.ssm.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsl.ssm.dao.user.UserMapper;
import com.lsl.ssm.pojo.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;
	
	public boolean addUser(User user) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if (userMapper.add(user) > 0) {
			flag = true;
		}
		return flag;
	}

	public User getUserByIP(String ip) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.getUserByIp(ip);
	}

	public List<User> getUserList() throws Exception {
		// TODO Auto-generated method stub
		return userMapper.getUserList();
	}

	public boolean update(User user) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if (userMapper.update(user) > 0) {
			flag = true;
		}
		return flag;
	}

}
