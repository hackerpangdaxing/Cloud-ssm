package com.cloud.service.websocket;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
/**
 * 用户请求/响应监听器
 * 
 * 此监听器的主要作用是让用户每次向websocket发出的请求，都带上session信息
 *
 */
public class RequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent request) {
        // TODO Auto-generated method stub
         ((HttpServletRequest) request.getServletRequest()).getSession();
    }

    @Override
    public void requestDestroyed(ServletRequestEvent arg0) {
        // TODO Auto-generated method stub

    }

}