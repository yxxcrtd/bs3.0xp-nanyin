package com.joyintech.tams.valuation.interf.valuation.sztinterf.utils;

import com.hex.connector.Connector;
import com.joyintech.tams.valuation.common.sztinterf.constant.DictCodeSztList;
import com.sscc.fdep.mrapi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class SztConnector extends Connector<SztMessage> implements InitializingBean,ApplicationListener<ContextClosedEvent> {
    
    private static Logger logger = LoggerFactory.getLogger(SztConnector.class.getName());

    private int listenThreadCount=2;

    private String filterDestUserId="";
    private String filterSourceUserId="";
    private String filterSourceAppId="";
    private String filterUserData1="";
    private String filterUserData2="";
    private String filterCorrPkgId="";
    
    private String appId;
    private String userId;
    private String appPwd;
    private String mrIp;
    private short mrPort;
    private String mrIpBak;
    private short mrPortBak;
    private byte bizTypeBySend;

    @Override
    public void sendMessage(SztMessage msg) {
        if (null== msg.getBody()){
            throw new RuntimeException("invalid msg body!");
        }

        if (null == msg.getFromAppId()){
            msg.setFromAppId(this.getAppId());
        }
        if (null == msg.getFromUserId()){
            msg.setFromUserId(this.getUserId());
        }

        byte[] data= msg.getBody().getBytes(UTF8);
        String pkgid = mrapi.MrSend(data, msg.getFromUserId(),msg.getFromAppId(),
                msg.getToUserId(), msg.getToAppId(),
                msg.getId(),
                msg.getCorrPkgId(),
                msg.getUserData1(), msg.getUserData2(),
                msg.getExpiredTime(), (byte)0, msg.getUcBizType(), 2000);
        msg.setId(pkgid);
        if (null==pkgid || pkgid.isEmpty()){
            throw new RuntimeException("failed to send sztMessage!");
        }

        logger.info("send sztMessage to {}/{}, pkgId={}, body:{}" + "," +
                msg.getToUserId() + "," + msg.getToAppId() + "," + msg.getId() + "," + msg.getBody());
    }

    public int getListenThreadCount() {
        return listenThreadCount;
    }

    public void setListenThreadCount(int listenThreadCount) {
        this.listenThreadCount = listenThreadCount;
    }


    private class ListenThread extends Thread{
        @Override
        public void run() {
            logger.info("{} starting listen to recv message..." + this.getName());
            while (isWorking()){
                if (! handleRecvMessage()){
                    try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
            logger.info("the sztConnector listen thread is exit!");
        }
    }

    private List<ListenThread> listenThreads=new ArrayList<ListenThread>();

    private volatile int lastRecvEmptyMsgCount=0;

    public SztMessage recvMessage(){
        String destUserId= "";
        String destAppId ="";

        byte[] msgData = mrapi.Mr2Receive3(this.getAppId(),
                this.filterSourceUserId,
                this.filterSourceAppId,
                this.filterDestUserId,
                destUserId, destAppId,
                filterCorrPkgId,
                filterUserData1,
                filterUserData2, 2000);
        if (msgData.length<6){
            lastRecvEmptyMsgCount++;
            if (lastRecvEmptyMsgCount % 100==1){
                //检测深证通网络状态
                int linkOk = mrapi.Mr2IsLinkOK(this.getAppId());
                if(linkOk==0){
                    logger.error("Mr2IsLinkOK={}" + linkOk);
                }
            }
            return null;
        }
        logger.info("recv sztMessage:{}" + new String(msgData,UTF8));

        lastRecvEmptyMsgCount=0;
        SztMessage msg=SztMessage.parseFrom(msgData,UTF8);
        if (null==msg){
            logger.error("invalid stzMessage:{}" + new String(msgData,UTF8));
            return null;
        }
        logger.info("recv sztConfig:{fromUser:"+msg.getFromUserId()+",fromAppId:"+msg.getFromAppId()+"}");

        return msg;
    }

    private boolean handleRecvMessage(){
        SztMessage msg = this.recvMessage();
        if (msg==null){
            return false;
        }
        this.onRecvMessage(msg);
        return true;
    }

    @Override
    protected void onStartListen() {
        if (mrHandle==0){
            return;
        }
        this.working=true;

        for (int i = 0; i < this.listenThreadCount; i++) {
            ListenThread listenThread=new ListenThread();
            listenThread.setName("SztListenThread-" + (i+1));
            this.listenThreads.add(listenThread);
            listenThread.start();
        }
    }

    @Override
    public void close() {
        if (mrHandle==0){
            return;
        }
        if (null != this.getAppId()){
            mrapi.MrDestroy(this.getAppId());
        }
    }

    @Override
    protected void onStopListen() {
        this.working=false;
        if (mrHandle==0){
            return;
        }
        logger.info("stop listen sztConnector!");
        this.close();
        for (ListenThread listenThread : this.listenThreads) {
            this.stopListenThread(listenThread);
        }
        this.listenThreads.clear();
    }

    @SuppressWarnings("deprecation")
	private void stopListenThread(ListenThread listenThread) {
        try {
            listenThread.join(5000);
        } catch (InterruptedException e) {
            logger.warn(e.toString());
        }
        if (listenThread.getState() != Thread.State.TERMINATED){
            listenThread.stop();
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if (null==this.getAppId() || null==this.getMrIp()){
            throw new RuntimeException("深证通参数配置错误!");
        }

        this.init();
    }

    private int mrHandle=0;

    public void init() {
        logger.info("初始化深证通...appId={},MrIp={},MrPort={},MrIpBak={},MrPortBak={}" + "," +
                this.getAppId() + "," + this.getMrIp() + "," + this.getMrPort() + "," + this.getMrIpBak() + "," + this.getMrPortBak());

        mrHandle=mrapi.Mr2Init(this.getAppId(),this.getAppPwd(),this.getMrIp(),this.getMrPort(),this.getMrIpBak(),this.getMrPortBak());

        if (mrHandle == 0){
            logger.error("初始化深证通失败,返回值:{},Version:{}" + "," + mrHandle + "," + convertVersionStr(mrapi.Mr2GetVersion()));
        }else {
            logger.info("初始化深证通成功,返回值:{},Version:{}" + "," + mrHandle + "," + convertVersionStr(mrapi.Mr2GetVersion()));
        }
    }

    private static Charset UTF8=Charset.forName("UTF-8");

    private String convertVersionStr(byte[] ver){
       return new String(ver, UTF8);
    }


    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        this.stopListen();
    }

    public String getFilterDestUserId() {
        return filterDestUserId;
    }

    public void setFilterDestUserId(String filterDestUserId) {
        this.filterDestUserId = filterDestUserId;
    }

    public String getFilterSourceUserId() {
        return filterSourceUserId;
    }

    public void setFilterSourceUserId(String filterSourceUserId) {
        this.filterSourceUserId = filterSourceUserId;
    }

    public String getFilterSourceAppId() {
        return filterSourceAppId;
    }

    public void setFilterSourceAppId(String filterSourceAppId) {
        this.filterSourceAppId = filterSourceAppId;
    }

    public String getFilterUserData1() {
        return filterUserData1;
    }

    public void setFilterUserData1(String filterUserData1) {
        this.filterUserData1 = filterUserData1;
    }

    public String getFilterUserData2() {
        return filterUserData2;
    }

    public void setFilterUserData2(String filterUserData2) {
        this.filterUserData2 = filterUserData2;
    }

    public String getFilterCorrPkgId() {
        return filterCorrPkgId;
    }

    public void setFilterCorrPkgId(String filterCorrPkgId) {
        this.filterCorrPkgId = filterCorrPkgId;
    }
    
    public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAppPwd() {
		return appPwd;
	}

	public void setAppPwd(String appPwd) {
		this.appPwd = appPwd;
	}

	public String getMrIp() {
		return mrIp;
	}

	public void setMrIp(String mrIp) {
		this.mrIp = mrIp;
	}

	public short getMrPort() {
		return mrPort;
	}

	public void setMrPort(short mrPort) {
		this.mrPort = mrPort;
	}

	public String getMrIpBak() {
		return mrIpBak;
	}

	public void setMrIpBak(String mrIpBak) {
		this.mrIpBak = mrIpBak;
	}

	public short getMrPortBak() {
		return mrPortBak;
	}

	public void setMrPortBak(short mrPortBak) {
		this.mrPortBak = mrPortBak;
	}

	public byte getBizTypeBySend() {
		return bizTypeBySend;
	}

	public void setBizTypeBySend(byte bizTypeBySend) {
		this.bizTypeBySend = bizTypeBySend;
	}

	public String sendMessage(SztMessage msg,String str) {
        if (null== msg.getBody()){
            /** modify by yanya on 20200311**/
            logger.info("invalid msg body!");
            return DictCodeSztList.深圳通接口发送结果_INVALID_MSG_BODY.getValue();
            //throw new RuntimeException("invalid msg body!");
        }

        if (null == msg.getFromAppId()){
            msg.setFromAppId(this.getAppId());
        }
        if (null == msg.getFromUserId()){
            msg.setFromUserId(this.getUserId());
        }

        byte[] data= msg.getBody().getBytes(UTF8);
//        mrapi.MrSend(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12);
//        MrSend(byte[] arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9, byte arg10, byte arg11, int arg12)
//        mrapi.Mr2Send(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14);
//        Mr2Send(byte[] arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9, byte arg10, byte arg11, byte arg12, byte arg13, int arg14)


//        String pkgid = mrapi.MrSend(data, msg.getFromUserId(),msg.getFromAppId(),
//                msg.getToUserId(), msg.getToAppId(),
//                msg.getId(),
//                msg.getCorrPkgId(),
//                msg.getUserData1(), msg.getUserData2(),
//                msg.getExpiredTime(), (byte)0, msg.getUcBizType(), 2000);
        String pkgid = mrapi.Mr2Send(data, msg.getFromUserId(),msg.getFromAppId(),
            msg.getToUserId(), msg.getToAppId(),
            msg.getId(),
            msg.getCorrPkgId(),
            msg.getUserData1(), msg.getUserData2(),
            msg.getExpiredTime(), (byte)0, msg.getUcBizType(), (byte)3, (byte)0, 2000);
        msg.setId(pkgid);
        /** modify by yanya on 20200311 增加深圳通返回结果的处理，针对不同的行，处理的方式可能不一样**/
        if (null==pkgid || pkgid.isEmpty()){
            //throw new RuntimeException("failed to send sztMessage!");
            logger.info("failed to send sztMessage, body:{}" + "," + msg.getBody());
            return DictCodeSztList.深圳通接口发送结果_FAILED_TO_SEND_SZTMESSAGE.getValue();
        }

        logger.info("send sztMessage to {}/{}, pkgId={}, body:{}" + "," +
                msg.getToUserId() + "," + msg.getToAppId() + "," + msg.getId() + "," + msg.getBody());
        return pkgid;
    }
}