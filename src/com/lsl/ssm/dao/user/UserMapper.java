package com.lsl.ssm.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lsl.ssm.pojo.User;

public interface UserMapper {

	/**
	 * 新增用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int add(User user) throws Exception;
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int update(User user) throws Exception;
	
	/**
	 * 获取所有用户信息列表
	 * @return
	 * @throws Exception
	 */
	public List<User> getUserList() throws Exception;
	
	/**
	 * 根据IP获取用户信息
	 * @param ip
	 * @return
	 * @throws Exception
	 */
	public User getUserByIp(@Param("ip") String ip) throws Exception;
}
