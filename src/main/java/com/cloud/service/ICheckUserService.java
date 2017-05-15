package com.cloud.service;

import com.cloud.model.RegisterUser;
public interface ICheckUserService {
	/**
	 * 查找用户
	 * @param name
	 * @param password
	 * @return
	 */
	Object findUser(String name,String password);
	/**
	 *注册 
	 * @param user
	 * @return success(成功） defeat（失败） exist（存在）
	 */
	String RegisterUser(RegisterUser user);
	/**
	 * 查询用户是否激活
	 * @param user
	 */
	String checkMailStatus(RegisterUser user);
	
}
