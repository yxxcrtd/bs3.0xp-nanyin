/*
 * 文件名：SocketClient.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：恒丰银行资管系统 修改人：renjian 修改时间：2017年2月27日 修改内容：新建 系统名称：恒丰银行资管系统
 */
package com.joyintech.tams.report.interf.message.socketclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

/**
 * Created by Administrator on 2017/7/12 0012.
 * <p>
 * socket通信客户端
 */
public class SocketClient {
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(com.joyintech.tams.report.interf.common.util.SocketClient.class);

    /**客户端SORCET通信
     * @param strXML
     * @return
     */
   /* public static String SocketClient(String strXML) {
        String res = null;
        try {
            Socket socket = new Socket("127.0.0.1", 5200);
            socket.setSoTimeout(60000);
            logger.debug("客户端以连接...");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(strXML);//把报文结构发送到服务器端
            DataInputStream in = new DataInputStream(socket.getInputStream());
            res = in.readUTF();//读取服务器端返回xml
            logger.debug("读取服务器返回数据:" + res);
            socket.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return res;
    }*/
}