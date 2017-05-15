package com.cloud.utils;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import com.cloud.model.RegisterUser;


public class SendMailUtil {
	private static Transport transport;
	/**
	 * 发送邮件
	 * @param user
	 * @return sendError token
	 */
	public static String SendMail(RegisterUser user) {
		String name = user.getName();
		String type = user.getType();
		String token = user.getToken();
		Properties props = new Properties();
		// 开启debug调试
		//props.setProperty("mail.debug", "true");
		// 发送服务器需要身份验证
		props.setProperty("mail.smtp.auth", "true");
		// 设置邮件服务器主机名
		props.setProperty("mail.host", "smtp.qq.com");
		// 发送邮件协议名称
		props.setProperty("mail.transport.protocol", "smtp");
		//设置端口
		/*props.setProperty("mail.stmp.socketFactory.port", "465");*/
		
		props.setProperty("mail.smtp.starttls.enable", "true");
		// 设置环境信息
		Session session = Session.getInstance(props);
		
		// 创建邮件对象
		MimeMessage msg = new MimeMessage(session);
		//设置邮件的主题
		try {
			msg.setSubject("智能云课堂账户激活认证","UTF-8");
			// 设置邮件内容
			String s = "点击下面链接激活账号，24小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！";
						StringBuffer sb=new StringBuffer(s);
				        sb.append("https://pangdaxing.cn/Cloud/");
				        sb.append(name);
				        sb.append("/mail");
				        sb.append("?token=");
				        sb.append(token);
				        sb.append("&&type=");
				        sb.append(type);
				        
					msg.setText(sb.toString(),"UTF-8");
					// 设置发件人
					msg.setFrom(new InternetAddress("1262466460@qq.com"));
					
					transport = session.getTransport();
					// 连接邮件服务器
					transport.connect("1262466460", "ehhcvuqpvdgzfheh");
					// 发送邮件
					transport.sendMessage(msg, new Address[] {new InternetAddress(user.getMail())});
					// 关闭连接
					transport.close();
			 
		} catch (MessagingException e) {
			return "sendError";
		}
		return null;
	}
}