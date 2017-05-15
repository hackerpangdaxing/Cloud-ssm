package com.cloud.service.impl;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.cloud.model.RegisterUser;
import com.cloud.model.StudentUser;
import com.cloud.model.TeacherUser;
import com.cloud.dao.ICheckUserDao;
import com.cloud.service.ICheckUserService;
import com.cloud.utils.Md5Util;
import com.cloud.utils.SendMailUtil;
@Service("CheckUserServiceImpl")
public class CheckUserServiceImpl implements ICheckUserService{
	@Autowired
	@Qualifier("ICheckUserDao")
	private ICheckUserDao checkDao;
	
	
	public Object findUser(String name, String password) {
		String type = checkDao.findType(name);
		if(type == null){
			return "error";
		}else if("teacher".equals(type)){
			TeacherUser teacher = checkDao.findTeacher(name, password);
				return selectUser(teacher);
		}else if("student".equals(type)){
			StudentUser student = checkDao.findStudent(name, password);
			return selectUser(student);
		}
			
		return "error";
	}
	
	
	public String RegisterUser(RegisterUser user) {
		 String name = user.getName();
		int flag = -1;
		//查找用户，如果存在返回error（注册失败）不存在返回success
		if(!name.equals(checkDao.findUserName(name))){
					String token = getToken(user);
					user.setToken(token);
				    Date date = new Date();
				    user.setRegisterTime(date);
				    user.setStatus(0);
				   flag = checkDao.Register(user);
				if(flag>0){
						String mailUtil = SendMailUtil.SendMail(user);
						if("sendError".equals(token)){
							return "defeat";
						}else{
							return "success";
						}
				}else{
					return "defeat";
				}
		}else{
			return "exist";
		}
		
	}
	public String checkMailStatus(RegisterUser user) {
		RegisterUser active = checkDao.checkMailStatus(user);
		Date date = new Date();
		Date registTime = active.getRegisterTime();
		long beforeTime = date.getTime();
		long afterTime = registTime.getTime();
		long time = Math.abs(afterTime-beforeTime);
		//注册时间 大于一天 失效 重新注册
		if(time <= 86400000){
			int status = active.getStatus();
			//注册状态 未激活0 激活1
			if(0 == status){
				//激活 
				active.setStatus(1);
				if(checkDao.updateUser(active) > 0){
					return "active";
				}else{
					return "noactive";
				}
			}else{
				return "noactive";
			}
					
		}else{
			//删除数据库中的数据
			checkDao.deleteUser(active);
			return "timeOut";
		}
	}
	
	
	
	private String getToken(RegisterUser user){
		String name = user.getName();
		String password = user.getPassword();
		String token = Md5Util.md5Util(name+password);
		return token;
	}

	private Object selectUser(Object obj){
		if(obj == null){
			return "error";
		}else if(obj instanceof TeacherUser){
		TeacherUser	teacherUser = (TeacherUser) obj;
			if(0 == teacherUser.getStatus()){
				return "0";
			}else{
				return teacherUser;
			}
		}else if(obj instanceof StudentUser){
			StudentUser studentUser = (StudentUser) obj;
			if(0 == studentUser.getStatus()){
				return "0";
			}else{
				return studentUser;
			}
		}
		return "error";
	}

}