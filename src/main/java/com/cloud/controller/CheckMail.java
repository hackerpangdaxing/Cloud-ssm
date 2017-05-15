package com.cloud.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloud.model.RegisterUser;
import com.cloud.service.ICheckUserService;

@Controller
public class CheckMail {
	@Autowired
	@Qualifier("CheckUserServiceImpl")
	private ICheckUserService checkService;
	
	/**
	 * 激活用户
	 * @param name
	 * @param token
	 * @param type
	 * @throws IOException 
	 */
	@RequestMapping("{name}/mail")
	public void checkMailStatus(@PathVariable("name")String name,String token,String type,HttpServletResponse response) throws IOException{
		PrintWriter writer = response.getWriter();
			RegisterUser user = new RegisterUser();
			user.setName(name);
			user.setToken(token);
			user.setType(type);
			writer.print(checkService.checkMailStatus(user));
	}
}
