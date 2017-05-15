package com.cloud.service.websocket;

import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.cloud.model.ActivityRoom;
import com.cloud.model.RoomUser;
import com.cloud.model.StudentUser;
import com.cloud.model.TeacherUser;
import com.cloud.model.User;
import com.cloud.utils.constant.Constant;

	/**
	 * 
	 * @author 胖大星
	 *	serverHttpRequest可以获取网页的request，并且从而获取servletContext。
	 *	然后通过servletContext获取两个全局的集合类数据
	 *	通过request可以获取网址的参数
	 *	然后通过集合类和参数获取集合类中相应的ActivityRoom和activityid。
	 *	然后要判断各种参数的存在，如果不存在即报错和return false拦截掉。
	 * 最后通过map把需要的参数传到WebSocketHandler里面。
	 */

public class MyHandshakeInterceptor implements HandshakeInterceptor{

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler, Exception e) {
		
	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, 
			Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		ServletContext servletContext = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest().getServletContext();
        Map<Integer, ActivityRoom> ActivityRooms = (Map<Integer, ActivityRoom>) servletContext.getAttribute("ActivityRooms");
		ArrayList<Integer> activityid = (ArrayList<Integer>) servletContext.getAttribute("activityIds");    
		//ArrayList<Object> user = (ArrayList<Object>) servletContext.getAttribute("User");
		//Map<Integer,RoomUser> roomUser = (Map<Integer,RoomUser>) servletContext.getAttribute("RoomUser");
		
        ActivityRoom activityRoom = ActivityRooms.get(activityid);
      
        String userId = "";
//        if (roomUser == null) {
//			roomUser = new HashMap<>();
//			servletContext.setAttribute("roomUser", roomUser);
////		}
//        if (user == null) { 
//        	user = new ArrayList<Object>();
//		    servletContext.setAttribute("User", user); 
//		 }
        
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            Integer id = parseInt(((ServletServerHttpRequest) serverHttpRequest).
            		getServletRequest().getParameter("activityid"));   //  用户发送的请求ID
            // userId = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest().getParameter("userId");
            
            HttpSession session = ((ServletServerHttpRequest) serverHttpRequest).
            		getServletRequest().getSession();
          
            
            for (Entry<Integer, ActivityRoom> activity : ActivityRooms.entrySet()) {  
            	  if ( activity.getKey().equals(id) ) {
                      activityRoom =  activity.getValue();
                      //获取用户类型
                      String usertype = (String) session.getAttribute(Constant.USER_TYPE);
                      if( usertype.equals("student") ) {
                    	  StudentUser studentUser =  (StudentUser) session.getAttribute(Constant.STUDNET_SESSION);
                    	  System.out.println("studentUser:"+studentUser);
                          map.put("USER", studentUser);
                          map.put("USER_TYPE", usertype);
                          map.put("WEBSOCKET_USERNAME", studentUser.getName());
                      }
                      else if ( usertype.equals("teacher")) {
                    	  TeacherUser teacherUser = (TeacherUser) session.getAttribute(Constant.TEACHER_SESSION);
                          map.put("USER", teacherUser);
                          map.put("USER_TYPE", usertype);
                          map.put("WEBSOCKET_USERNAME", teacherUser.getName());
                      }
                      else {
                          return false;
                      }
                  }
            	  
            }  
            if (id == null  || activityRoom == null ) {
                return false;
            }
            
            // 使用userName区分WebSocketHandler，以便定向发送消息
            // String userName = (String) session.getAttribute("WEBSOCKET_USERNAME");

            map.put("WEBSOCKET_ACTIVITY",id);
            map.put("ACTIVITY_ROOMS", ActivityRooms);
            map.put("SERVLET_CONTEXT",servletContext);
           // map.put("USER_LIST", user);
            //map.put("ROOMUSER", roomUser);
            return true;
        }
        return false;
	}

}
