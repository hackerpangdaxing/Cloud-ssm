package com.cloud.service.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.model.ActivityMessage;
import com.cloud.model.ActivityRoom;
import com.cloud.model.RoomUser;
import com.cloud.model.StudentUser;
import com.cloud.model.TeacherUser;
import com.cloud.model.User;
import com.cloud.testmybatis.JacksonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.json.JsonObject;
import javax.naming.Context;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 * @author 胖大星
 *         websocket相关的配置，HandshakeInterceptor传过来的参数用webSocketSession.getAttributes()获取。
 *         为什么要在这里绑定数据就因为有个websocketsession在这里才有。
 * @see 测试方法：
 *      http://localhost:8080/Cloud/createAcitvity?activityId=1555&teacherId=1&questionsId=1
 *      var socket = new
 *      WebSocket("ws://localhost:8080/Cloud/hello.do?activityid=1555");
 *      socket.onmessage=function(data){ console.log(data) };
 *      socket.send('101251')
 */

public class WebSocketHander  extends TextWebSocketHandler {
	ServletContext servletContext;
	private static Map<Integer, ActivityRoom> ActivityRooms;
	ArrayList<Object> userList = new ArrayList<>();
	private static final Map<String,WebSocketSession> users; static { users = new HashMap<String,WebSocketSession>(); }
	/**
	 * 当客户端有消息发送过来时，会进入此方法进行处理
	 * session:消息来源者
	 * message：封装了发送过来的消息信息
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	    super.handleTextMessage(session, message);	    
		
	    
	    //如果想取得原始session中封存的用户身份等信息，可通过：session.getAttributes().get(名称)方法取得 	 
//	   String userName = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
	   
	    //通过message.getPayload()方法获取客户端发送过来的有效信息
	    String content = message.getPayload();
	   // System.out.println(content);
	    ObjectMapper objectMapper = new ObjectMapper();
	    ActivityMessage activityMessage = (ActivityMessage) objectMapper.readValue(content, ActivityMessage.class );
	    ActivityRoom activityRoom = ActivityRooms.get( activityMessage.getActivityId() );
	    if("server".equals( activityMessage.getState() ) ){//发送过来的信息为服务器接收的系统信息，则处理相关系统级业务逻辑，此处只做原样信息返回
	        //==============================
	        //  发给服务器的信息，作为保留
	        //==============================
	        //session.sendMessage(returnMessage);
	    }else if("broadcast".equals(activityMessage.getState())){//发送过来的信息为广播信息,则调用全部在线人员接收信息方法
	        //TextMessage returnMessage = new TextMessage("{'token':'broadcast','fromUser':'"+activityMessage.getUsername()+"','content':'"+activityMessage.getMessage()+"'}");
	        sendMessageToAll(activityRoom, activityMessage);
	    }else if("out".equals(activityMessage.getState())){
	        removeRoomuser(activityRoom, activityMessage);
	        //System.out.println(content);
	        sendMessageToAll(activityRoom, activityMessage);
	    }else if ("join".equals(activityMessage.getState())){
	    	ArrayList<String> userlist = new ArrayList<>();
	    	for ( Entry<Integer,RoomUser> roomUser : activityRoom.getRoomUser().entrySet()) {
	    		if (roomUser.getValue().getTeacheruser() != null) {
		    		userlist.add(roomUser.getValue().getTeacheruser().getName());	    			
	    		}
	    		else if (roomUser.getValue().getStudentuser() != null) {
		    		userlist.add(roomUser.getValue().getStudentuser().getName());	    			
	    		}
			}
	    	activityMessage.setData(objectMapper.writeValueAsString(userlist));
	    	sendMessageToAll(activityRoom, activityMessage);
	    }
	  //发给个人接收
        //==============================
        //  发给服个人的信息，作为保留
        //==============================
        //sendMessageToUser(activityMessage.getSendTo(),returnMessage);
	}

	private void removeRoomuser(ActivityRoom activityRoom, ActivityMessage activityMessage) {
		// TODO Auto-generated method stub
		activityRoom.getRoomUser().remove(activityMessage.getUserId());
	}

	private void sendMessageToAll(ActivityRoom activityRoom, ActivityMessage activityMessage) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println(activityRoom+"++++++++++++++++++++++++++++++++++++++");
		ObjectMapper objectMapper = new ObjectMapper();
		for ( Entry<Integer,RoomUser> roomUser : activityRoom.getRoomUser().entrySet()) {
			roomUser.getValue().getWebSocketSession().sendMessage(
					new TextMessage(
							objectMapper.writeValueAsString(activityMessage)
							)
					);
		}
		
	}

	/**
	 * 用户成功连接成功后会调用该方法
	 * 说明：次方法中，我们在实际生产环境中可能用到的场景如：
	 * 1.用户上线之后，接收自身的离线消息。
	 * 2.刷新全局在线用户列表
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		/*String userType = (String) session.getAttributes().get("USER_TYPE");
		if(userType != null){
			if (userType.equals("student")) {
			StudentUser studentUser = (StudentUser) session.getAttributes().get("USER");
			  users.put(studentUser.getName(), session);
		} else if (userType.equals("teacher")) {
			TeacherUser teacherUser = (TeacherUser) session.getAttributes().get("USER");
				  users.put(teacherUser.getName(), session);
		}
		}
	        发送给当前登录用户在线用户列表信息
	        sendMessageToUsers(new TextMessage("{'token':'refreshlines','list':"+getlineUsers()+"}"));*/
	        Integer activityId = (Integer) session.getAttributes().get("WEBSOCKET_ACTIVITY");
			ActivityRooms = (Map<Integer, ActivityRoom>) session.getAttributes().get("ACTIVITY_ROOMS");
			String userType = (String) session.getAttributes().get("USER_TYPE");
			Map<Integer, RoomUser> roomUser = (Map<Integer, RoomUser>) session.getAttributes().get("ROOMUSER");
			 //userList = (ArrayList<Object>) session.getAttributes().get("USER_LIST");
			RoomUser roomuser = null;
			
			// 添加用户到房间
			//仅当用户存在时执行
			if(userType != null){
				if (userType.equals("student")) {
					StudentUser studentUser = (StudentUser) session.getAttributes().get("USER");
					roomuser = new RoomUser(session, studentUser, activityId);
					//userList.add(studentUser);
				} else if (userType.equals("teacher")) {
					TeacherUser teacherUser = (TeacherUser) session.getAttributes().get("USER");
					//userList.add(teacherUser);
					roomuser = new RoomUser(session, teacherUser, activityId);
				}
			}
			
			ActivityRoom activityRoom = ActivityRooms.get(activityId);
			activityRoom.addRoomUser(roomuser);
			ActivityRoom roomdata = new ActivityRoom();
			roomdata.setActivityid(activityRoom.getActivityid());
			roomdata.setChatRoom(activityRoom.getChatRoom());
			roomdata.setEduEvaluate(activityRoom.getEduEvaluate());
			roomdata.setQuestionBank(activityRoom.getQuestionBank());
			Object s = session.getAttributes().get("USER");
			 sendInfoToAll(session, roomdata , s);
			
	    }
	  /**
	   * 发送房间信息
	 * @param activityRoom
	 * @throws Exception
	 */
	private void sendInfoToAll(WebSocketSession webSocketSession, ActivityRoom roomdata, Object s) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println(writeValueAsString+"============================");
	
		ObjectMapper objectMapper = new ObjectMapper();
		webSocketSession.sendMessage(
					new TextMessage(
							objectMapper.writeValueAsString(roomdata)
							)
					);
	
	   }
	/**
	 * 给某个用户发送消息
	 *
	 * @param userName
	 * @param message
	 */
	public void sendMessageToUser(String username, TextMessage message) {
	    WebSocketSession user = users.get(username);
	    /*
	     * 判定接收信息的一方是否存在并且在线状态，如果在线就发送
	     * 说明：在这个地方，真实的应用里面，处理逻辑应该是先在当前在线用户列表里面，先取指定的用户是否在线，如果不在线，则再去数据库中查找该用户是否存在，如果存在，则该消息
	     * 将加入离线消息存储逻辑处理，待该用户上线时，服务器推送离线消息给该用户。
	     */
	    if(null != user && user.isOpen()){
	         try {
	            user.sendMessage(message);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }


	}


	/**
	 * 给所有在线用户发送消息
	 *
	 * @param message
	 */
	public void sendMessageToUsers(TextMessage message) {

	    for (Map.Entry<String,WebSocketSession> entry : users.entrySet()) {  

	         try {
	             if (entry.getValue().isOpen()) {
	                 entry.getValue().sendMessage(message);
	             }
	         } catch (IOException e) {
	             e.printStackTrace();
	         }

	    }  

	}

	/**
	 * 返回在线用户列表，json字符串格式
	 * @return
	 */
	public String getlineUsers(){
	    return JSON.toJSONString(users.keySet());
	}


	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
	    if(session.isOpen()){
	        session.close();
	    }
	   System.out.println("传输处理错误......");
	    users.remove(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
	    System.out.println("websocket connection closed......");
	    users.remove(session);
	}

	@Override
	public boolean supportsPartialMessages() {
	    return false;
	}

}
