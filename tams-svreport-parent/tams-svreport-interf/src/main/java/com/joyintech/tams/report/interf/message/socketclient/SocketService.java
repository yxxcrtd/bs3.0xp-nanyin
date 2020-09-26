package com.joyintech.tams.report.interf.message.socketclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2017/7/12 0012.
 * <p>
 * socket通信服务端
 */
/*public class SocketService {

    public static void main(String[] args) throws IOException {
        SocketService socketService = new SocketService();
        socketService.oneServer();
    }

    public void oneServer() {
        try {
            Socket service;
            ServerSocket ss = new ServerSocket(5200);
            System.out.println("服务器已启动,等到客户端的连接...");
            Socket socket = ss.accept(); // 服务器收到客户端的数据后,创建与此客户端对话的Socket
            DataInputStream in = new DataInputStream(socket.getInputStream());
            System.out.println("服务器接受到客户端的连接请求:" + in.readUTF());// 用于接收客户端 发来的数据的输入流
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String sb = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<service>\n" +
                    " <SYS_HEAD>\n" +
                    "   <SvcId>11003000003</SvcId>\n" +
                    "   <SvcScnId>01</SvcScnId>\n" +
                    "   <CnsmSysId>000001</CnsmSysId>\n" +
                    "   <PrvdSysId>000002</PrvdSysId>\n" +
                    "   <SrcSysId>000001</SrcSysId >\n" +
                    "   <CnsmSysSeqNo>0000012015010212563324</CnsmSysSeqNo>\n" +
                    "   <PrvdSysSeqNo>0000022015010212563553</PrvdSysSeqNo>\n" +
                    "   <SrcSysSeqNo>0000012015010212563324</SrcSysSeqNo>\n" +
                    "   <TranDate>20120102</TranDate>\n" +
                    "   <TranTime>12563324</TranTime>\n" +
                    "   <TranRetSt>S</TranRetSt>\n" +
                    "   <array>\n" +
                    "   <RetInf>\n" +
                    "   <RetCode>000000000000</RetCode>\n" +
                    "   <RetMsg>交易成功</RetMsg>\n" +
                    "   </RetInf>\n" +
                    "   </array>\n" +
                    " </SYS_HEAD>\n" +
                    " <APP_HEAD>\n" +
                    "   <TellerId>00012545</TellerId>\n" +
                    "   <BranchId>0099</BranchId>  \n" +
                    "   <TlrPswd>800800</TlrPswd>\n" +
                    "   <TlrLvl>000220130707</TlrLvl>\n" +
                    " </APP_HEAD>\n" +
                    " <BODY>\n" +
                    "  <AuthTlrStts>0</AuthTlrStts>\n" +
                    "  <InfoNo>WMA2004</InfoNo>\n" +
                    " </BODY>\n" +
                    "</service>";
            out.writeUTF(sb.toString());//服务器响应给客户端
            socket.close();
            ss.close();
        } catch (Exception e) {//出错，打印出错信息
            System.out.println("Error." + e);
        }
    }
}*/
