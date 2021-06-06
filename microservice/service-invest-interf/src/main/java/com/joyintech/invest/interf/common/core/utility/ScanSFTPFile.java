package com.joyintech.invest.interf.common.core.utility;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

public class ScanSFTPFile implements Runnable{
	
	private ScanSFTPEntity entity;
	
	public ScanSFTPFile(ScanSFTPEntity entity) {
		this.entity = entity;
	}



	@Override
	public void run() {
		//sftp用户名
		String sftpUserName ="";
		//sftp密码
		String sftpWord;
		//读取配置文件，获取请求资管的接口地址
		//sftp服务ip
		String sftpIp ="";
		//sftp服务端口
		String sftpPort ="";
		
		Properties prop = null;
		try {

			try {
				prop = WebserviceIntefUtil.getProperties("webservice.properties");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (prop != null){
				sftpUserName = prop.getProperty("sftpUserName");
				sftpWord= prop.getProperty("sftpWord");
				sftpIp = prop.getProperty("sftpIp");
				sftpPort = prop.getProperty("sftpPort");
				int port = Integer.parseInt(sftpPort);

				// 创建sftp工具类对象
				SFTPTool sftptool = new SFTPTool(sftpUserName, sftpWord, sftpIp, port);
				String sftpFilePath = entity.getSftpFilePath();

				while(true){
					try {
						//获取sftp服务该路径下的文件
						List<String> sFtpFileList = sftptool.getSFtpFile(sftpFilePath);
						if(sFtpFileList != null && sFtpFileList.size()>0){//如果扫描到文件，则为entity对象赋值成功
							entity.setScanCode("1");
							entity.setScanStatus("成功");
							entity.setEndTime(new Date().getTime());
							break;
						}else{
							try {

								entity.setEndTime(new Date().getTime());
								entity.setScanCode("0");
								entity.setScanStatus("失败");
								if((entity.getEndTime() - entity.getStartTime()) > 6*60*1000){//如果6分钟还未扫描到文件，则跳出循环
									break;
								}
								//如果没有扫描的文件，则睡1秒
								Thread.currentThread().sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					} catch (SftpException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (JSchException e) {
			e.printStackTrace();
		}
		
	}

}
