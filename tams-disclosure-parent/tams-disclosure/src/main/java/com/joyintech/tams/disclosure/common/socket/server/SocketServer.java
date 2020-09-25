///*
// * 文件名：ReportSocketServer.java
// * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
// * 描述：估值核算系统V1.0
// * 修改人：ZhangYangFei
// * 修改时间：2018年12月10日
// * 修改内容：新建
// * 系统名称：估值核算系统V1.0
// */
//
//package com.joyintech.tams.disclosure.common.socket.server;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.joyintech.tams.disclosure.common.socket.util.SocketUtils;
//
///**
// * socket服务端
// * 
// * @author ZhangYangFei
// * @version 1.0 2018年12月10日
// * @see SocketServer
// * @since 1.0
// */
//public class SocketServer {
//
//    /**
//     * 日志
//     */
//    private static Logger logger = LoggerFactory.getLogger(SocketServer.class);
//
//    /**
//     * 启动socket服务端
//     * 
//     * @see
//     * @since 1.0
//     */
//    public void startSocketServer() {
//        //监听端口
//        int port = Integer.parseInt(SocketUtils.getPropertyValue("listener.port"));
//        try {
//            @SuppressWarnings("resource")
//            ServerSocket serverSocket = new ServerSocket(port);
//            logger.info("Socket服务端进入启动状态，端口=" + port);
//            Socket client = null;
//            while (true) {
//                // 一旦有堵塞, 则表示服务器与客户端获得了连接
//                client = serverSocket.accept();
//                logger.info("Socket服务端与客户端获得了连接...");
//                // 处理这次连接
//                new SocketThreadHandler(client);
//            }
//        }
//        catch (IOException e) {
////            e.printStackTrace();
//        	logger.error("error:",e);
//            logger.error("Socket服务端异常: " + e.getMessage());
//        }
//    }
//}
