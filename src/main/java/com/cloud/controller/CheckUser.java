package com.cloud.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cloud.model.RegisterUser;
import com.cloud.model.StudentUser;
import com.cloud.model.TeacherUser;
import com.cloud.service.ICheckUserService;
import com.cloud.utils.constant.Constant;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class CheckUser {
	@Autowired
	@Qualifier("CheckUserServiceImpl")
	private ICheckUserService checkService;

	/**
	 * 查找用户 0未激活 error用户不存在 success 用户登录成功
	 * 
	 * @param name
	 * @param password
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "sign", method = RequestMethod.GET)
	@ResponseBody
	public void findUser(String name, String password, HttpServletResponse response, HttpServletRequest request) throws IOException {
		PrintWriter pw = response.getWriter();
		Object obj = checkService.findUser(name, password);
		if ("error".equals(obj)) {
			pw.print("error");
		} else if ("0".equals(obj)) {
			pw.print("0");
		} else if (obj instanceof TeacherUser) {
			TeacherUser user = (TeacherUser) obj;
			setMap(obj, request);
			pw.print(new ObjectMapper().writeValueAsString(user));
		} else if (obj instanceof StudentUser) {
			StudentUser user = (StudentUser) obj;
			setMap(obj, request);
			pw.print(new ObjectMapper().writeValueAsString(user));
		}
	}

	/**
	 * 注册用户（name password mail type）
	 * 
	 * @param user
	 * @return 返回成功与否
	 * @throws IOException 
	 */
	@RequestMapping(value = "regist", method = RequestMethod.POST)
	@ResponseBody
	public String RegisterUser(RegisterUser user) throws IOException {
		String flag = null;
		flag = checkService.RegisterUser(user);
		if ("success".equals(flag)) {
			return "success";
		} else if ("defeat".equals(flag)) {
			return "defeat";
		} else if ("exist".equals(flag)) {
			return "exist";
		}
		return "defeat";
	}
	
	@CrossOrigin
	@RequestMapping(value = "wregist", method = RequestMethod.POST)
	public void RegisterUserWang(@RequestBody RegisterUser user, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		String flag = null;
		flag = checkService.RegisterUser(user);
		if ("success".equals(flag)) {
			pw.write( "success");
		} else if ("defeat".equals(flag)) {
			pw.write( "defeat");
		} else if ("exist".equals(flag)) {
			pw.write("exist");
		}
	}

	@RequestMapping("register")
	public void register() {
	}

	@RequestMapping("login")
	public void login() {
	}

	private void setMap(Object obj, HttpServletRequest request) {
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		if (obj instanceof TeacherUser) {
			TeacherUser user = (TeacherUser) obj;
			Map<Integer, TeacherUser> teacherMap = (Map<Integer, TeacherUser>) context
					.getAttribute(Constant.USER_TEACHER);
			if (teacherMap == null) {
				teacherMap = new HashMap<Integer, TeacherUser>();
			}
			teacherMap.put(user.getTeacherId(), user);
			session.setAttribute(Constant.TEACHER_SESSION, user);
			session.setAttribute(Constant.USER_TYPE, user.getType());
			context.setAttribute(Constant.USER_TEACHER, teacherMap);
		} else if (obj instanceof StudentUser) {
			StudentUser user = (StudentUser) obj;
			Map<Integer, StudentUser> studentMap = (Map<Integer, StudentUser>) context
					.getAttribute(Constant.USER_TEACHER);
			if (studentMap == null) {
				studentMap = new HashMap<Integer, StudentUser>();
			}
			studentMap.put(user.getStudentId(), user);
			session.setAttribute(Constant.USER_TYPE, user.getType());
			session.setAttribute(Constant.STUDNET_SESSION, user);
			context.setAttribute(Constant.USER_STUDENT, studentMap);

		}
	}
}
