package com.cloud.dao;

import org.springframework.stereotype.Component;

import com.cloud.model.RegisterUser;
import com.cloud.model.StudentUser;
import com.cloud.model.TeacherUser;

@Component("ICheckUserDao")
public interface ICheckUserDao {
	/**
	 * 登录
	 * @param name
	 * @param password
	 * @return 返回viewUser
	 */
	TeacherUser findTeacher(String name,String password);
	StudentUser findStudent(String name,String password);
	/**
	 *  注册用户
	 * @param user
	 * @return 0 失败 1成功
	 */
	int Register(RegisterUser user);
	/**
	 * 查找用户是否存在
	 * @param name
	 * @return 找到name成功
	 */
	String findUserName(String name);
	/**
	 * 查询用户的激活状态和激活时间
	 * @param user
	 * @return
	 */
	RegisterUser checkMailStatus(RegisterUser user);
	/**
	 * 激活用户
	 * @param user
	 */
	int updateUser(RegisterUser user);
	/**
	 * 删除用户信息
	 * @param user
	 */
	void deleteUser(RegisterUser user);
	/**
	 * 查找用户类型
	 * @param name
	 */
	public String findType(String name);
}
