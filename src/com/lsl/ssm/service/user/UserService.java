package com.lsl.ssm.service.user;

import java.util.List;

import com.lsl.ssm.pojo.User;

public interface UserService {

	/**
	 * 保存新增用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean addUser(User user) throws Exception;
	
	/**
	 * 获取用户列表
	 * @return
	 * @throws Exception
	 */
	public List<User> getUserList() throws Exception;
	
	/**
	 * 根据用户IP获取用户信息
	 * @param ip
	 * @return
	 * @throws Exception
	 */
	public User getUserByIP(String ip) throws Exception;
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean update(User user) throws Exception;
}
