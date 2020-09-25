///*
// * 文件名：ThreadHandler.java
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
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
//import java.net.Socket;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.joyintech.tams.disclosure.common.socket.util.SocketUtils;
//
///**
// * socket服务端多线程业务处理
// * 
// * @author ZhangYangFei
// * @version 1.0 2018年12月10日
// * @see SocketThreadHandler
// * @since 1.0
// */
//public class SocketThreadHandler implements Runnable {
//
//    /**
//     * 日志
//     */
//    private static Logger logger = LoggerFactory.getLogger(SocketServer.class);
//
//    private Socket socket;
//
//    public SocketThreadHandler(Socket client) {
//        socket = client;
//        new Thread(this).start();
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void run() {
//        handle();
//    }
//
//    /**
//     * 处理事件
//     *
//     * @param selectionKey
//     * @throws IOException
//     */
//    private void handle() {
//        PrintWriter printWriter = null;
//        BufferedReader bufferedReader = null;
//        InputStreamReader inputStreamReader = null;
//        // 报文前长度信息占位，配置为0表示不存在占位
//        String length = SocketUtils.getPropertyValue("listener.length");
//        // 编码
//        String encode = SocketUtils.getPropertyValue("listener.encode");
//        try {
//            // 1.读取客户端数据
//            inputStreamReader = new InputStreamReader(socket.getInputStream(), encode);
//            // 2.解析客户端发送的信息
//            // 接受消息
//            String reciveStr = "";
//            // 读取指定长度
//            if (null != length && !"".equals(length) && 0 < Integer.parseInt(length)) {
//                // 报文总长
//                int reciveLent = 0;
//                // 先截取报文前长度=length的内容
//                char cbuf1[] = new char[Integer.parseInt(length)];
//                inputStreamReader.read(cbuf1);
//                String reciveLentTmp = "";
//                for (int i = 0; i < cbuf1.length; i++) {
//                    reciveLentTmp += cbuf1[i];
//                }
//                reciveLent = Integer.parseInt(reciveLentTmp);
//                // 读取前length位以外的报文信息
////                char cbuf2[] = new char[reciveLent - Integer.parseInt(length)];
//                char cbuf2[] = new char[reciveLent];
//                inputStreamReader.read(cbuf2);// 报文去除前length位后剩余的部分
//                for (int i = 0; i < cbuf2.length; i++ ) {
//                    reciveStr += cbuf2[i];
//                }
//            }
//            // 读取全部
//            else {
//                bufferedReader = new BufferedReader(inputStreamReader);
//                String temp = null;
//                while ((temp = bufferedReader.readLine()) != null) {
//                    reciveStr += temp;
//                }
//            }
//            logger.info("服务端接受消息：" + reciveStr);
//
//            // 3.业务处理
//            String sendStr = businessHandle(reciveStr);
//            if (null != length && !"".equals(length) && 0 < Integer.parseInt(length)) {
//                // 拼接报文长度字符串
////                sendStr = SocketUtils.fixedLength(sendStr, Integer.parseInt(length)) + sendStr;
//            }
//            // 4.返回消息
//            printWriter = new PrintWriter(
//                new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), encode)));
//            logger.info("服务端返回消息：" + sendStr);
//            printWriter.print(sendStr);
//            printWriter.flush();
//        }
//        catch (IOException e) {
//        	logger.error("error:",e);
//        }
//        finally {
//            // 先打开的后关闭，后打开的先关闭
//            if (null != printWriter) {
//                printWriter.close();
//            }
//            try {
//                if (null != bufferedReader) {
//                    bufferedReader.close();
//                }
//            }
//            catch (IOException e) {
//                logger.error("io流BufferedReader关闭异常");
//                logger.error("error:",e);
//            }
//            finally {
//                try {
//                    if (null != inputStreamReader) {
//                        inputStreamReader.close();
//                    }
//                }
//                catch (IOException e) {
//                    logger.error("io流InputStreamReader关闭异常");
//                    logger.error("error:",e);
//                }
//            }
//        }
//    }
//
//    /**
//     * 业务处理
//     * 
//     * @param reciveStr 报文消息
//     * @return 业务处理后返回消息
//     * @see
//     * @since 1.0
//     */
//    private String businessHandle(String reciveStr) {
//        String busiMsg = "";
//        SocketBusinessHandler businessHandler =  new SocketBusinessHandler();
//        busiMsg = businessHandler.doBusiness(reciveStr);
//        return busiMsg;
//    }   
//}
