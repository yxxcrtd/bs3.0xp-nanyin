/*
 * 文件名：FtpUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.framework.util.DateUtil;

public class FtpUtil {

	private FTPClient ftpClient;

	private String userName;

	private String password;

	private String host;

	private int port;

	Logger logger = LoggerFactory.getLogger(FtpUtil.class);

	private FileOutputStream fos = null;

	private void connect() throws NumberFormatException, SocketException, IOException {
		ftpClient = new FTPClient();
		ftpClient.connect(host, port);
		int reply = ftpClient.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftpClient.disconnect();
		}
		ftpClient.login(userName, password);
		ftpClient.setBufferSize(256);
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		ftpClient.setControlEncoding("utf8");
		logger.info(DateUtil.getCurrentDateTime() + "登录FTP成功！");
	}

	public void login(String userName, String password, String host, int port)
			throws NumberFormatException, SocketException, IOException {
		this.userName = userName;
		this.password = password;
		this.host = host;
		this.port = port;
		this.connect();
	}

	/**
	 * 根据文件名的部分，查出所有包含字符的文件
	 * 
	 * @param path
	 * @param fileNamePart
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<String> listFileName(String path, String fileNamePart) {
		List<String> res = new ArrayList<String>();
		try {
			if (!ftpClient.isConnected()) {
				this.connect();
			}
			//add by liupengfei for 多级法人改造 S
			if(MultilevelDataAuthorityUtil.isEnabled()){
				List<String> dics =  this.listAllFileName(path);
				for (String dic:dics) {
					if(dic.contains(".")){
						ftpClient.changeWorkingDirectory(path);
						if (dic.contains(fileNamePart)) {
							res.add(dic);
						}
						continue;
					}
					else{
						ftpClient.changeWorkingDirectory(path+'/'+dic);
					}
					String[] names = ftpClient.listNames();
					String[] replyStrings = ftpClient.getReplyStrings();
					for (String replyString : replyStrings) {
						logger.info(replyString);
					}
					if (names != null) {
						for (String name : names) {
							// 文件名包含指定文件
							if (name.contains(fileNamePart)) {
								if(dic.contains(".")) {
									res.add(name);
								}
								else{
									res.add(dic+"/"+name);
								}
							}
						}
					}
					ftpClient.changeToParentDirectory();
				}
			}
			//add by liupengfei for 多级法人改造 S
			ftpClient.changeWorkingDirectory(path);
			String[] names = ftpClient.listNames();
			String[] replyStrings = ftpClient.getReplyStrings();
			for (String replyString : replyStrings) {
				logger.info(replyString);
			}
			if (names != null) {
				for (String name : names) {
					// 文件名包含指定文件
					if (name.contains(fileNamePart)) {
						res.add(name);
					}
				}
			}

			ftpClient.changeToParentDirectory();
		} catch (Exception e) {
			logger.error("列出文件失败!", e);
		}
		return res;
	}

	/**
	 * 查询所有文件
	 * 
	 * @param path
	 * @param fileNamePart
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<String> listAllFileName(String path) {
		List<String> res = new ArrayList<String>();
		try {
			if (!ftpClient.isConnected()) {
				this.connect();
			}
			ftpClient.changeWorkingDirectory(path);
			String[] names = ftpClient.listNames();
			String[] replyStrings = ftpClient.getReplyStrings();
			for (String replyString : replyStrings) {
				logger.info(replyString);
			}
			if (names != null) {
				for (String name : names) {
					res.add(name);
				}
			}

			ftpClient.changeToParentDirectory();
		} catch (Exception e) {
			logger.error("列出文件失败!", e);
		}
		return res;
	}

	/**
	 * 下载远程文件到本地
	 * 
	 * @param remoteFile
	 * @param localDir
	 * @see
	 * @since 1.0
	 */
	public void getFile(String remoteFile, String localDir) {
		try {
			if (!ftpClient.isConnected()) {
				this.connect();
			}
			File file = new File(remoteFile);
			File localFile = new File(localDir);
			if (!localFile.exists()) {
				localFile.mkdirs();
			} else if (!localFile.isDirectory()) {
				boolean rec = localFile.delete();
				if (rec) {
					localFile.mkdirs();
				}
			}
			File localfile = new File(localDir + File.separator + file.getName());
			fos = new FileOutputStream(localfile);
			boolean flag = ftpClient.retrieveFile(remoteFile, fos);
			if (!flag) {
				logger.error(
						"下載文件" + remoteFile + "失败:" + ftpClient.getReplyCode() + "--" + ftpClient.getReplyString());
			}
			fos.flush();
			fos.close();

		} catch (SocketException e) {
			logger.error("连接失败！", e);
		} catch (IOException e) {
			logger.error("下载失败！", e);
		} catch (Exception e) {
			logger.error("下载失败！", e);
		}
	}

	public void downFileOrDir(String remoteFileName, String localDir) {
		try {
			File file = new File(remoteFileName);
			File temp = new File(localDir);
			if (!temp.exists()) {
				temp.mkdirs();
			}
			if (isDir(remoteFileName)) {
				String[] names = ftpClient.listNames();
				for (int i = 0; i < names.length; i++) {
					logger.debug("下載文件：" + names[i] + "^^^^^^^^^^^^^^");
					if (isDir(names[i])) {
						// 文件夹
						downFileOrDir(remoteFileName + '/' + names[i], localDir + File.separator + names[i]);
						ftpClient.changeToParentDirectory();
					} else {
						File localfile = new File(localDir + File.separator + names[i]);
						if (!localfile.exists()) {
							fos = new FileOutputStream(localfile);
							boolean flag = ftpClient.retrieveFile(names[i], fos);
							if (!flag) {
								logger.error("下載文件" + names[i] + "失败:" + ftpClient.getReplyCode() + "--"
										+ ftpClient.getReplyString());
							}
						} else {
							logger.debug("开始删除文件");
							boolean rec = localfile.delete();
							if (rec) {
								logger.debug("文件已经删除");
							}
							fos = new FileOutputStream(localfile);
							boolean flag = ftpClient.retrieveFile(names[i], fos);
							if (!flag) {
								logger.error("下載文件" + names[i] + "失败:" + ftpClient.getReplyCode() + "--"
										+ ftpClient.getReplyString());
							}
						}
					}
				}
			} else {
				File localfile = new File(localDir + File.separator + file.getName());
				if (!localfile.exists()) {
					fos = new FileOutputStream(localfile);
					boolean flag = ftpClient.retrieveFile(remoteFileName, fos);
					if (!flag) {
						logger.error("下載文件" + remoteFileName + "失败:" + ftpClient.getReplyCode() + "--"
								+ ftpClient.getReplyString());
					}
				} else {
					logger.debug("开始删除文件");
					boolean rec = localfile.delete();
					if (rec) {
						logger.debug("文件已经删除");
					}
					fos = new FileOutputStream(localfile);
					boolean flag = ftpClient.retrieveFile(remoteFileName, fos);
					if (!flag) {
						logger.error("下載文件" + remoteFileName + "失败！");
					}
				}
				ftpClient.changeToParentDirectory();
			}
			logger.info("下载成功！");
			if (fos != null) {
				fos.close();
				fos = null;
			}
		} catch (SocketException e) {
			logger.error("连接失败！", e);
		} catch (IOException e) {
			logger.error("下载失败！", e);
		}
	}

	private boolean isDir(String path) {
		boolean flag = false;
		try {
			flag = ftpClient.changeWorkingDirectory(path);
		} catch (IOException e) {
			logger.info("", e);
		}
		return flag;
	}

	public void close() {
		if (fos != null) {
			try {
				fos.close();
			} catch (IOException e) {
				logger.info("", e);
			}
			fos = null;
		}
		try {
			ftpClient.logout();
		} catch (IOException e) {
			logger.info("", e);
		}
		logger.info("退出登录");
		try {
			ftpClient.disconnect();
		} catch (IOException e) {
			logger.info("", e);
		}
		logger.info("关闭连接");
	}

}
