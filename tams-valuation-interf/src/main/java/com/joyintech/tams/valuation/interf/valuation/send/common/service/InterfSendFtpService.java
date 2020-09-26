/*
 * 文件名：InterfSendService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2019年4月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.common.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.interf.valuation.send.utils.SendFtpUtil;
import com.joyintech.tams.valuation.common.interf.base.util.SftpUtil;
import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;
import com.joyintech.tams.valuation.interf.valuation.send.common.entity.SendWriterEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangqidong
 * @version 1.0 2019年4月25日
 * @see InterfSendService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class InterfSendFtpService {
    
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void doSend(InterfExecuteInfo executeInfo,SendWriterEntity item) throws Exception{
      
        SftpUtil sftpUtil = new SftpUtil();
            try {   
                String ip = executeInfo.getIp();// 主机IP
                Integer port = executeInfo.getPort();// 主机端口
                String username = executeInfo.getUserName();// 主机用户名
                String password = executeInfo.getPassWord();// 主机密码或秘钥地址
                String date = DateUtil.format(executeInfo.getInterfDate(), DateUtil.DEFAULT_OTHER_FORMAT);
                String local = executeInfo.getLocalRootPath() + File.separator + executeInfo.getSystemId() + File.separator + date + File.separator + item.getTmpEName()+"_"+date+".xml";
                String remote = executeInfo.getRemotepath() + "/" + executeInfo.getSystemId();
                
                sftpUtil.login(username, password, ip, port);// sftp登录
                sftpUtil.putFileDate(local,remote,date); // 上传至文件服务器
                sftpUtil.close(); // 关闭登录
            } catch (Exception e) {
                sftpUtil.close();// 关闭登录
                e.printStackTrace();
                String errorInfo = executeInfo.getTaskName()+"  数据文件回传失败";
                logger.error(errorInfo + "   错误信息：" + e.getMessage());
                throw e;
            }
    }
    
    public void doSendByBooksetId(InterfExecuteInfo executeInfo,String tmpEName, Date exeDate,String bookSetId, Date sysDate) throws Exception{
        
        SftpUtil sftpUtil = new SftpUtil();
            try {   
                String ip = executeInfo.getIp();// 主机IP
                Integer port = executeInfo.getPort();// 主机端口
                String username = executeInfo.getUserName();// 主机用户名
                String password = executeInfo.getPassWord();// 主机密码
                String date = DateUtil.format(sysDate, DateUtil.DEFAULT_OTHER_FORMAT);
                String local = executeInfo.getLocalRootPath() + File.separator + executeInfo.getSystemId() + File.separator + 
                		date + File.separator + tmpEName+"_"+bookSetId + "_" + DateUtil.format(exeDate, DateUtil.DEFAULT_OTHER_FORMAT) + "_" +
                		DateUtil.format(sysDate, "HHmmss")+".xml";
                String remote = executeInfo.getRemotepath() + "/" + executeInfo.getSystemId();
                
                sftpUtil.login(username, password, ip, port);// sftp登录
                sftpUtil.putFileDate(local,remote,date); // 上传至文件服务器
                sftpUtil.close(); // 关闭登录
            } catch (Exception e) {
                sftpUtil.close();// 关闭登录
                e.printStackTrace();
                String errorInfo = executeInfo.getTaskName()+"  数据文件回传失败";
                logger.error(errorInfo + "   错误信息：" + e.getMessage());
                throw e;
            }
    }
    
    public void doSendMyByBooksetId(InterfExecuteInfo executeInfo,String tmpEName, Date exeDate,String bookSetId, Date sysDate) throws Exception{
        
    	SendFtpUtil sftpUtil = new SendFtpUtil();
            try {  
            	
                String ip = executeInfo.getIp();// 主机IP
                Integer port = executeInfo.getPort();// 主机端口
                String username = executeInfo.getUserName();// 主机用户名
                String password = executeInfo.getPassWord();// 秘钥地址
                String date = DateUtil.format(sysDate, DateUtil.DEFAULT_OTHER_FORMAT);
                
                sftpUtil.loginById(username, password, ip, port);
                
                logger.info("净值下发接口: sftp登录成功！");
                
                String local = executeInfo.getLocalRootPath() + File.separator + executeInfo.getSystemId() + File.separator + 
                		date + File.separator + tmpEName+"_"+bookSetId + "_" + DateUtil.format(exeDate, DateUtil.DEFAULT_OTHER_FORMAT) + "_" +
                		DateUtil.format(sysDate, "HHmmss")+".xml";
                String remote = executeInfo.getRemotepath() + "/" + executeInfo.getSystemId();
                
				// 上传文件
				sftpUtil.putFileDate(local, remote,date);
				logger.info(executeInfo.getTaskName() + ": sftp上传文件成功！");
				
                sftpUtil.close(); // 关闭登录
                logger.info(executeInfo.getTaskName() + ": sftp关闭成功！");
            } catch (Exception e) {
                sftpUtil.close();// 关闭登录
                e.printStackTrace();
                String errorInfo = executeInfo.getTaskName()+": 数据文件回传失败";
                logger.error(errorInfo + "   错误信息：" + e.getMessage());
                throw e;
            }
    }
   
}
