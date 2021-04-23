package com.joyintech.tams.invest.batch.cfetsmanager.util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.SftpException;
import com.joyintech.framework.util.StringUtil;

public class CFETSFileUtil {

	/**
     * Logger
     */
    private static Logger logger = LoggerFactory.getLogger(CFETSFileUtil.class);
	/**
	 * 
	 * @param sftpServer 服务器地址
	 * @param port  服务器端口号
	 * @param userName 服务器用户名
	 * @param password  服务器用户密码
	 * @param localPath  下载本地保存地址，解压指定目录
	 * @param localFileName  本地保存文件名
	 * @param path  服务器上源文件路径
	 * @param fileName  服务器上源文件名称
	 * @param list  解压文件集合
	 * @return
	 * @throws SftpException
	 * @throws IOException
	 */
	public List<File> downloadFile(String sftpServer, String port, String userName, String password, 
			String localPath, String localFileName, String path, String fileName) 
					throws SftpException, IOException{
		File filePath = new File(localPath);
		if(!filePath.isDirectory()) {
			filePath.mkdirs();//创建文件夹
        	logger.info("IrsSettleReceiveService   未找到日期文件夹，创建"+localPath+" 目录");
		}
		//文件路径+文件名
		String filePathName;
		// add by yeyang 20200826 异常关闭的场景下未断开链接占用资源(增加try finally) start
		SFTPUtil sftp = null;
		try {
			sftp = new SFTPUtil(userName, password, sftpServer, Integer.parseInt(port));
			sftp.login();
			if (StringUtil.equals(StringUtil.substring(localPath, localPath.length() - 1), "/")) {
				filePathName = localPath + localFileName;
			} else {
				filePathName = localPath + "/" + localFileName;
			}
			File file = new File(filePathName);
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			sftp.download(path, fileName, fileOutputStream);
			logger.info("下载文件成功，下载文件为：" + filePathName);
		} finally {
			if (sftp != null) {
				sftp.logout();
			}
		}
		// add by yeyang 20200826 异常关闭的场景下未断开链接占用资源(增加try finally) end
		//解压文件
		return unzip(filePathName, localPath);
	}
	/**
	 * 解压缩文件到本地，并将压缩包中文件信息返回
	 * @param zipFileName  ZIP文件所在目录地址+zip文件名
	 * @param path 指定解压目录
	 * @return
	 * @throws IOException
	 */
	public List<File> unzip(String zipFileName, String path) throws IOException{
		logger.info("解压缩文件，文件为：" + zipFileName + ", 文件路径为："+ path);
		List<File> returnList = new ArrayList<File>();
		//本地解压文件
		Charset gbk = Charset.forName("GBK");
		ZipFile zipFile = new ZipFile(zipFileName, gbk);
		Enumeration e = zipFile.entries();
		ZipEntry zipEntry = null;
		File dest = new File(path);
		while(e.hasMoreElements()) {
			zipEntry = (ZipEntry) e.nextElement();
			String entryName = zipEntry.getName();
			InputStream in= null;
			FileOutputStream out = null;
			try {
				if(zipEntry.isDirectory()) {//创建文件夹
					String name = zipEntry.getName();
					name = name.substring(0, name.length() - 1);
					File f = new File(path + File.separator + name);
					f.mkdirs();
				}else {
					int index = entryName.lastIndexOf("\\");
					if(index != -1) {
						File df = new File(path + File.separator + entryName.substring(0, index));
						df.mkdirs();
					}
					index = entryName.lastIndexOf("/");
					if(index != -1) {
						File df = new File(path + File.separator + entryName.substring(0, index));
						df.mkdirs();
					}
					
					File f = new File(path + File.separator + zipEntry.getName());
					in = zipFile.getInputStream(zipEntry);
					out = new FileOutputStream(f);
					int c;
					byte[] by = new byte[1024];
					while((c = in.read(by)) != -1) {
						out.write(by, 0, c);
					}
					out.flush();
					returnList.add(f);
				}
			} catch (Exception e1) {
				logger.error("CFETSFileUtil  解压缩文件异常" + e);
			} finally {
				if(in != null) {
					try {
						in.close();
					}catch(IOException ex) {
						
					} finally {
						if(out != null) {
							try {
								out.close();
							}catch(IOException ex) {
								
							}
						}
					}
				}
			}
		}
		zipFile.close();
		System.gc();
		logger.info("解压缩文件完成");
		return returnList;
		
	}
}
