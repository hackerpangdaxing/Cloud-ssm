package com.cloud.testmybatis;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMail {
	private static Transport transport;

	public static void main(String[] args) throws MessagingException {
		Properties props = new Properties();
		// 开启debug调试
	//	props.setProperty("mail.debug", "true");
	
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
		Message msg = new MimeMessage(session);
		//设置邮件的主题
		msg.setSubject("JavaMail测试");
		// 设置邮件内容
		msg.setText("啦啦啦");
		// 设置发件人
		msg.setFrom(new InternetAddress("1090336400@qq.com"));
		
		transport = session.getTransport();
		// 连接邮件服务器   授权码：yvxlnditghawhjhh
		transport.connect("1090336400", "yvxlnditghawhjhh");
		// 发送邮件
		for(int i=0;i<5;i++){
			transport.sendMessage(msg, new Address[] {new InternetAddress("947973941@qq.com")});
			// 关闭连接
		}
			transport.close();
		}
}