package com.joyintech.tams.report.interf.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.tams.report.interf.common.dto.SocketEntity;

public class SocketClient {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(SocketClient.class);

    public static String sendAndResult(SocketEntity entity) {
        String returnStr = "";
        logger.info("操作类型：[Socket客户端], Message：[开始]");
        logger.info("IP:[" + entity.getIp() + "]");
        logger.info("PORT:[" + entity.getPort() + "]");
        logger.info("ENCODE:[" + entity.getEncode() + "]");
        logger.info("TIMEOUT:[" + entity.getTimeOut() + "]");
        // 参数检查
        if (!checkParameter(entity)) {
            logger.info("操作类型：[Socket客户端], Message：[参数检查失败]");
            return returnStr;
        }
        logger.info("操作类型：[Socket客户端], Message：[参数检查成功]");
        // 使用指定的服务器地址和端口号创建Socket
        Socket socket = createSocket(entity);
        if (socket == null) {
            logger.info("操作类型：[Socket客户端], Message：[Socket创建失败]");
            return returnStr;
        }
        logger.info("操作类型：[Socket客户端], Message：[Socket创建成功]");
        // 发送的XML报文字符串
        String xmlStr = entity.getSendMsg();
        if (null == xmlStr || "".equals(xmlStr)) {
            logger.info("操作类型：[Socket客户端], Message：[请求的报文为空]");
            return returnStr;
        }
        try {
            // 由Socket对象获得输出流
            OutputStream out = socket.getOutputStream();
            // 报文长度
            int length = xmlStr.getBytes(entity.getEncode()).length;
            // 报文的6位头信息
            String len = String.format("%06d", new Object[] {Integer.valueOf(length)});
            // 发送的报文
            xmlStr = len + xmlStr;
            // 把报文写入输入流
            out.write(xmlStr.getBytes(entity.getEncode()));
            // 刷新输入流
            out.flush();
            logger.info("操作类型：[Socket客户端], Message：[发送的请求报文]");
            logger.info(xmlStr);

            // 由Socket对象获得输出流
            InputStream in = socket.getInputStream();
            // 读取报文头信息
            byte[] lenthData = new byte[6];
            int returnLen = in.read(lenthData);
            if (returnLen != 6) {
                logger.info("操作类型：[Socket客户端], Message：[接收的响应报文长度不符合规范：+"
                            + ((returnLen <= 0) ? 0 : returnLen) + "]报文头内容" + "：["
                            + new String(lenthData, entity.getEncode()) + "]");
                return returnStr;
            }

            len = new String(lenthData, entity.getEncode());
            logger.info("操作类型：[Socket客户端], Message：[接收的响应报文长度：+" + len + "]");
            // 接收到的报文长度
            int dataLen = Integer.valueOf(len).intValue();
            byte[] data = new byte[dataLen];
            int reallyLen = in.read(data);
            if (reallyLen != -1) {
                // 服务端返回的报文
                returnStr = new String(data, entity.getEncode());
            }
            // 返回的信息
            entity.setReturnMsg(returnStr);
            logger.info("操作类型：[Socket客户端], Message：[接收的响应报文]");
            logger.info(returnStr);

            // 关闭
            in.close();
            out.close();

        }
        catch (SocketTimeoutException e) {
            logger.error("超时异常！");
            // 异常信息
            logger.error(e.toString());
        }
        catch (IOException e) {
            logger.error("通信异常！");
            // 异常信息
            logger.error(e.toString());
        }
        finally {
            if (null != socket && !socket.isClosed()) {
                try {
                    socket.close();
                }
                catch (IOException e) {
                    logger.error("socket 关闭失败");
                    logger.error(e.getMessage(),e);
                }
            }
        }

        return returnStr;
    }

    /**
     * 创建socket
     * 
     * @param entity 创建socket需要的参数
     * @return Socket 创建好的socket
     * @see
     * @since 1.0
     */
    private static Socket createSocket(SocketEntity entity) {
        Socket socket = null;
        try {
            // 1.使用指定的服务器和端口创建Socket
            socket = new Socket(entity.getIp(), entity.getPort());
            socket.setSoTimeout(entity.getTimeOut());
        }
        catch (ConnectException e) {
            logger.error("连接异常");
            // 异常信息
            logger.error(e.toString());
            return null;
        }
        catch (Exception e) {
            logger.error("连接异常");
            // 异常信息
            logger.error(e.toString());
            if (null != socket && !socket.isClosed()) {
                try {
                    socket.close();
                }
                catch (IOException e1) {
                    logger.error("socket 关闭失败");
                    logger.error(e.getMessage(),e);
                }

            }
            return null;
        }
        finally {
        	try {
            	if(socket !=null)
            		socket.close();
            } catch (Exception e) {
            	logger.error(e.getMessage(),e);
             }
         }
        return socket;
    }

    /**
     * 参数检查
     * 
     * @param entity
     * @return
     */
    private static Boolean checkParameter(SocketEntity entity) {

        // Socket实体不存在的场合
        if (null == entity) {
            logger.info("操作类型：[Socket客户端], Message：[Socket实体不存在]");
            return false;
        }
        // IP
        if (null == entity.getIp() || "".equals(entity.getIp())) {
            logger.info("操作类型：[Socket客户端], Message：[IP不存在]");
            return false;
        }
        // 端口
        if (null == entity.getPort()) {
            logger.info("操作类型：[Socket客户端], Message：[端口号不存在]");
            return false;
        }
        // 通讯时间
        if (null == entity.getTimeOut()) {
            logger.info("操作类型：[Socket客户端], Message：[通讯时间不存在]");
            return false;
        }

        // 编码
        if (null == entity.getEncode() || "".equals(entity.getEncode())) {
            logger.info("操作类型：[Socket客户端], Message：[编码不存在]");
            return false;
        }

        return true;

    }
}
