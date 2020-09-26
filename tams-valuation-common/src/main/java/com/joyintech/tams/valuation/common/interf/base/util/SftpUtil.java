package com.joyintech.tams.valuation.common.interf.base.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.joyintech.framework.util.StringUtil;

/**
 * sftp 工具
 *
 * @author 王波
 * @version 1.0 2018年7月12日
 * @see SftpUtil
 * @since 1.0
 */
public class SftpUtil {

	private ChannelSftp sftp = new ChannelSftp();

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(SftpUtil.class);

	private Session sshSession;

	/**
	 * 日志工具类
	 */
	public class SettleLogger implements com.jcraft.jsch.Logger {
		public boolean isEnabled(int level) {
			return true;
		}

		public void log(int level, String msg) {
			logger.info(msg);
		}
	}

	/**
	 * Sftp 登录
	 *
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @param host
	 *            主机名
	 * @param port
	 *            端口
	 * @throws JSchException
	 */
	public void login(String userName, String password, String host, int port) throws Exception {
		try {
			// 接口 SFTP 服务配置
			JSch jsch = new JSch();
			sshSession = jsch.getSession(userName, host, port);
			com.jcraft.jsch.Logger jschLogger = new SettleLogger();
			JSch.setLogger(jschLogger);
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
		} catch (JSchException e) {
			logger.error("", e);
			throw new Exception("登录文件服务器失败");
		}
	}

	/**
	 * Sftp 登录
	 *
	 * @param userName
	 *            用户名
	 * @param identity
	 *            私钥
	 * @param host
	 *            主机名
	 * @param port
	 *            端口
	 * @throws JSchException
	 */
	public void loginById(String userName, String identity, String host, int port) throws Exception {
		try {
			// 接口 SFTP 服务配置
			JSch jsch = new JSch();
			com.jcraft.jsch.Logger jschLogger = new SettleLogger();
			JSch.setLogger(jschLogger);
			jsch.addIdentity(identity);
			sshSession = jsch.getSession(userName, host, port);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
		} catch (JSchException e) {
			logger.error("", e);
			throw new Exception("登录文件服务器失败");
		}
	}

	/**
	 * 关闭SFTP连接 <一句话功能简述> <功能详细描述>
	 */
	public void close() {
		if (sftp.isConnected()) {
			sftp.disconnect();
			logger.info("成功关闭连接SFTP！");
		}
		if (sshSession != null) {
			sshSession.disconnect();
			sshSession = null;
		}
	}

	/**
	 * 列出文件名
	 *
	 * @param path
	 *            路径
	 * @param fileNamePart
	 *            文件名部分
	 * @return
	 * @throws SftpException
	 * @see
	 * @since 1.0
	 */
	public List<String> listFileName(String path, String fileNamePart) throws Exception {
		List<String> res = new ArrayList<>();
		try {
			// 获取当前路径
			String curDir = sftp.pwd();
			//add by liupengfei for 多级法人改造 S
			if(MultilevelDataAuthorityUtil.isEnabled()){
				List<String> dics =  this.getSFtpFile(path);
				for (String dic:dics) {
					if(!dic.contains(".")){
						sftp.cd(path+'/'+dic);
					}
					else{
						sftp.cd(path);
					}
					String fileName = "*" + fileNamePart + "*";
					@SuppressWarnings("unchecked")
					Vector<LsEntry> vector = (Vector<LsEntry>) sftp.ls(fileName);
					for (LsEntry lsEntry : vector) {
						if (!".".equals(lsEntry.getFilename()) && !"..".equals(lsEntry.getFilename())) {
							if(dic.contains(".")){
								res.add(lsEntry.getFilename());
							}
							else{
								res.add(dic+"/"+lsEntry.getFilename());
							}
						}
					}
					// 退出当前路径
					sftp.cd(curDir);
					if(dic.contains(".")){//不存在文件夹和文件同级的情况
						break;
					}
				}
			}
			//add by liupengfei for 多级法人改造 E
			else{
				sftp.cd(path);
				String fileName = "*" + fileNamePart + "*";
				@SuppressWarnings("unchecked")
				Vector<LsEntry> vector = (Vector<LsEntry>) sftp.ls(fileName);
				for (LsEntry lsEntry : vector) {
					if (!".".equals(lsEntry.getFilename()) && !"..".equals(lsEntry.getFilename())) {
						res.add(lsEntry.getFilename());
					}
				}
			}
			// 退出当前路径
			sftp.cd(curDir);
		} catch (SftpException e) {
			logger.error("列出文件名称失败！", e);
			//throw new Exception("没有文件！");
		}
		return res;
	}

	/**
	 * 下载文件或文件夹
	 *
	 * @param remoteFileName
	 * @param localDir
	 * @throws Exception
	 */
	public void downFileOrDir(String remoteFileName, String localDir) throws Exception {
		try {
			String curDir = "";
			File file = new File(remoteFileName);
			File temp = new File(localDir);
			if (!temp.exists()) {
				temp.mkdirs();
			}
			if (isDir(remoteFileName)) {
				List<String> names = getSFtpFile(remoteFileName);
				curDir = sftp.pwd();
				sftp.cd(remoteFileName);
				for (int i = 0; i < names.size(); i++) {
					String fileName = names.get(i);
					logger.debug("下載文件：" + names.get(i) + "^^^^^^^^^^^^^^");
					if (isDir(names.get(i))) {
						// 文件夹
						sftp.cd(curDir);
						downFileOrDir(remoteFileName + '/' + names.get(i), localDir + File.separator + names.get(i));
					} else {
						String localfileName = localDir + File.separator + fileName;
						File localfile = new File(localfileName);
						if (!localfile.exists()) {
							sftp.get(names.get(i), localfileName);
						} else {
							logger.debug("开始删除文件");
							boolean rec = localfile.delete();
							if (rec) {
								logger.debug("文件已经删除");
							}
							sftp.get(names.get(i), localfileName);
						}
					}
				}
			} else {
				try {
					sftp.ls(remoteFileName);
				} catch (SftpException e) {
					logger.info("服务器上没有该目录：" + remoteFileName);
					return;
				}
				File localfile = new File(localDir + File.separator + file.getName());
				String localfileName = localDir + File.separator + file.getName();
				if (!localfile.exists()) {
					sftp.get(file.getName(), localfileName);
				} else {
					logger.debug("开始删除文件");
					boolean rec = localfile.delete();
					if (rec) {
						logger.debug("文件已经删除");
					}
					sftp.get(file.getName(), localfileName);
				}
				sftp.cd("..");
			}
			logger.info("下载成功！");

		} catch (SftpException e) {
			logger.error("下载失败！", e);
			throw new Exception("下载文件或目录失败");
		}

	}

	/**
	 * 下载文件
	 *
	 * @param remoteFile
	 * @param localDir
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	public void getFile(String remoteFile, String localDir) throws Exception {
		File localFile = new File(localDir);
		if (localFile.exists()) {
			// 本地目录不是目录，先删除
			if (!localFile.isDirectory()) {
				boolean rec =localFile.delete();
				if(rec){
					logger.debug("");
				}
			}
		} else {
			// 创建本地目录
			localFile.mkdirs();
		}
		try {
			sftp.get(remoteFile, localDir);
		} catch (SftpException e) {
			logger.error("连接失败！", e);
			throw new Exception("下载文件失败");
		}
	}

	/**
	 * 通过进入目录方式判断是否为目录
	 *
	 * @param path
	 * @return
	 */
	private boolean isDir(String path) {
		String curDir = "";
		try {
			curDir = sftp.pwd();
		} catch (SftpException e1) {
			logger.error("", e1);
		}
		try {
			sftp.cd(path);
		} catch (SftpException e) {
			return false;
		}
		try {
			sftp.cd(curDir);
		} catch (SftpException e) {
			logger.error("", e);
		}
		return true;
	}

	/**
	 * 列出目录下的文件
	 *
	 * @param directory
	 *            要列出的目录 /opt/wind/20141215/SRC_BONDENCASHYIELD20141215.txt
	 * @return
	 * @throws SftpException
	 */
	@SuppressWarnings("unchecked")
	public List<String> getSFtpFile(String pathname) throws SftpException {
		Vector<LsEntry> vector = null;
		List<String> res = new ArrayList<>();
		try {
			vector = sftp.ls(pathname);
		} catch (SftpException e) {
			logger.error("未找到文件:" + pathname, e);
		}
		if (vector != null) {
			for (LsEntry lsEntry : vector) {
				if (!".".equals(lsEntry.getFilename()) && !"..".equals(lsEntry.getFilename())) {
					res.add(lsEntry.getFilename());
				}
			}
		}
		return res;
	}
	/**
	 * 支持在SFTP 服务器上创建多级目录
	 *
	 * @param remote
	 * @throws SftpException
	 * @see
	 * @since 1.0
	 */
	public void mkdir(String remote) throws SftpException {
		String curdir = sftp.pwd();
		if (remote == null) {
			logger.error("路径不能为空！");
			return;
		}
		String[] remotes = remote.split("/");
		if (remote.startsWith("/")) {
			// 绝对路径从/下开始递归创建
			sftp.cd("/");
		}
		for (String dir : remotes) {
			if (StringUtil.isEmpty(dir)) {
				continue;
			}
			try {
				sftp.ls(dir);
			}
			catch (SftpException e) {
				System.out.println("服务器上没有该目录：" + dir);
				sftp.mkdir(dir);
			}
			sftp.cd(dir);
		}
		sftp.cd(curdir);
	}

	/**
	 * sftp将本地文件上传到远程服务器
	 *
	 * @param local 本地文件路径
	 * @param remote 远程文件路径
	 * @throws SftpException
	 */
	public void putFile(String local, String remote) throws SftpException {
		try {
			sftp.ls(remote);
		}
		catch (SftpException e) {
			logger.info("服务器上没有该目录：" + remote);
			mkdir(remote);
		}
		sftp.put(local, remote);
		logger.info("文件上传成功");
	}

	/**
	 * sftp将本地文件上传到远程服务器(新建个日期目录，如20180911)
	 *
	 * @param local 本地文件路径
	 * @param remote 远程文件路径
	 * @throws SftpException
	 */
	public void putFileDate(String local, String remote, String date) throws SftpException {
		String curDir = "";
		String curDir1 = "";
		try {
			sftp.ls(remote);
		}
		catch (SftpException e) {
			logger.info("服务器上没有该目录：" + remote);
			mkdir(remote);
		}
		curDir = sftp.pwd();
		sftp.cd(remote);
		curDir1 = sftp.pwd();
		try {
			sftp.ls(date);
		}
		catch (SftpException e) {
			logger.info("服务器上没有该目录：" + curDir + '/' + date);
			mkdir(date);
		}
		sftp.cd(date);
		sftp.cd(curDir1);
		sftp.put(local, date);
		logger.info("文件上传成功");
	}
}
