package com.joyintech.interf.bankname.cfetsup.utils;


import com.joyintech.interf.bankname.cfetsup.exception.FTPDirectoryAccessException;
import com.joyintech.interf.bankname.cfetsup.exception.FTPDownloadException;
import com.joyintech.interf.bankname.cfetsup.exception.FTPLoginException;
import com.joyintech.interf.bankname.cfetsup.exception.FTPUploadException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author LuoHaizhi
 *
 */
public class FTPHelper {

	private static final Log LOGGER = LogFactory.getLog(FTPHelper.class);
	private static final String ENCODING_GBK = "GBK";
	private static final String ENCODING_GB2312 = "GB2312";
	private static final String ENCODING_ISO_8859_1 = "ISO-8859-1";
	
	public FTPClient client;

	public void connectServer(String username, String password, String ip, int port) throws FTPLoginException {
		
		try {
			String newPassword = new String(Base64Utils.decode(password.getBytes(ENCODING_GBK)), ENCODING_GBK);
			client = new FTPClient();
			client.setConnectTimeout(15000);
			client.connect(ip, port);
			boolean result = client.login(username, newPassword);
			if (!result) {
				throw new FTPLoginException("用户名或密码错误！");
			}
			client.setControlEncoding(ENCODING_GBK);
			client.enterLocalActiveMode();
			client.setFileType(FTP.BINARY_FILE_TYPE);
			client.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
			client.setFileStructure(FTP.FILE_STRUCTURE);
		} catch (Exception e) {
			throw new FTPLoginException(e.getMessage(), e);
		}
	}

	
	public void closeServer() throws IOException {
		
		try {
			if (null != client) {
				client.logout();
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			
			if(null != client && client.isConnected()) {
				
				try {
					client.disconnect();
				} catch (IOException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		}
	}
	
	
	public void upload(String uploadPath, InputStream is, String fileName) throws FTPUploadException {
		this.upload(uploadPath, is, fileName, false);
	}

	
	public void upload(String uploadPath, InputStream is, String fileName, boolean isCloseInput) throws FTPUploadException {
		
		try {
			String name = new String(fileName.getBytes(ENCODING_GBK), ENCODING_ISO_8859_1);
			String path = new String(uploadPath.getBytes(ENCODING_GBK), ENCODING_ISO_8859_1);
			boolean result = client.changeWorkingDirectory(path);
			if(!result) {
				throw new FTPDirectoryAccessException("访问 " + uploadPath + " 目录失败！");
			}
			result = client.storeFile(name, is);
			if(!result) {
				throw new FTPUploadException("文件 " + fileName + " 上传失败！");
			}
		} catch (Exception e) {
			throw new FTPUploadException(e.getMessage(), e);
		} finally {
			if(null != is && isCloseInput) {
				try {
					is.close();
				} catch (IOException e) {
				
				}
			}
		}
	}	
	
	
	public ByteArrayOutputStream downloadToOutputStream(String downloadPath, String fileName) 
			throws FTPDownloadException {
		
		try {
			String name = new String(fileName.getBytes(ENCODING_GBK), ENCODING_ISO_8859_1);
			String path = new String(downloadPath.getBytes(ENCODING_GBK), ENCODING_ISO_8859_1);
			boolean result = client.changeWorkingDirectory(path);
			if(!result) {
				throw new FTPDirectoryAccessException("访问 " + downloadPath + " 目录失败！");
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream(10240);
			result = client.retrieveFile(name, baos);
			if(!result) {
				throw new FTPDownloadException("文件 " + fileName + " 下载失败！");
			}
			return baos;
		} catch (Exception e) {
			throw new FTPDownloadException(e.getMessage(), e);
		}
	}	
	
	
	private void deleteFile(String realPath) {
		
		if(realPath != null) {
			File file = new File(realPath);
			if(file.exists()) {
				boolean f = file.delete();
				if(!f) {
					LOGGER.error("File " + realPath + " delete failed！");
				}
			}
		}
	}
	
	
	private String createTempFileName(String fileName) {
		
		return fileName + "_FTP" + System.currentTimeMillis();
	}
	
	public void downloadFiles(String downloadPath, String savePath, String [] filenames) throws IOException, FTPDirectoryAccessException, FTPDownloadException {
		LOGGER.info("访问 " + downloadPath + " 目录！");
		boolean changeWorkingDirectory = client.changeWorkingDirectory(downloadPath);
		if(!changeWorkingDirectory) {
			LOGGER.error("访问 " + downloadPath + " 目录失败！");
			throw new FTPDirectoryAccessException("访问 " + downloadPath + " 目录失败！");
		}
		File saveDir = new File(savePath);
		
		if(!saveDir.exists() || !saveDir.isDirectory()) {
			LOGGER.info("创建" + saveDir + "文件夹");
			boolean flag = saveDir.mkdir();
			LOGGER.info("创建" + saveDir + "文件夹 flag : " + flag);
		}
		List<String> asList = Arrays.asList(filenames);
		LOGGER.info("调用 listNames 查询当前目录文件.");
		client.enterLocalPassiveMode();
		for (String s : client.listNames(downloadPath)) {
			LOGGER.info("遍历到文件:" + s);
			String fn = s.replaceAll("/", "");
			if (!asList.contains(fn)) {
				continue;
			}
			LOGGER.info("下载文件"+fn);
			OutputStream ios = new FileOutputStream(new File(savePath + fn));
			try {
				boolean b = client.retrieveFile(new String(s.getBytes(ENCODING_GB2312),ENCODING_ISO_8859_1), ios);
				LOGGER.info("下载文件"+fn + " 结果:" + b);
			} catch (Exception e) {
				LOGGER.error("文件下载失败");
				throw new FTPDownloadException("文件下载失败");
			} finally {
				try {
					ios.close();
				} catch (Exception e) {
				}
			}
		}
		
	}
	public void downloadDir(String downloadPath, String savePath) throws IOException, FTPDirectoryAccessException, FTPDownloadException {
		boolean changeWorkingDirectory = client.changeWorkingDirectory(downloadPath);
		if(!changeWorkingDirectory) {
			throw new FTPDirectoryAccessException("访问 " + downloadPath + " 目录失败！");
		}
		File saveDir = new File(savePath);
		
		if(!saveDir.exists() || !saveDir.isDirectory()) {
			saveDir.mkdir();
		}
		
    	for (String s : client.listNames(downloadPath)) {
    		//String filename = new String(s.getBytes("ISO-8859-1"),"UTF-8");
           // File localFile = new File(savePath + filename);
            OutputStream ios = new FileOutputStream(savePath + s);
            try {
            	client.retrieveFile(s, ios);
            	System.out.println(s + " 下载完成! ");
			} catch (Exception e) {
				throw new FTPDownloadException("文件下载失败");
			} finally {
				try {
					ios.close();
				} catch (Exception e) {
				}
			}
        }
		
	}
	
	public String download(String downloadPath, String fileName, String tempSavePath) throws FTPDownloadException {
		
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		String realPath = null;
		try {
			
			String name = new String(fileName.getBytes(ENCODING_GBK), ENCODING_ISO_8859_1);
			String path = new String(downloadPath.getBytes(ENCODING_GBK), ENCODING_ISO_8859_1);
			boolean result = client.changeWorkingDirectory(path);
			if(!result) {
				throw new FTPDirectoryAccessException("访问 " + downloadPath + " 目录失败！");
			}
			if(!tempSavePath.endsWith("/")) {
				realPath = tempSavePath + "/" + createTempFileName(fileName);
			} else {
				realPath = tempSavePath + createTempFileName(fileName);
			}
			fos = new FileOutputStream(realPath);
			// 2097152 = 1024 * 1024 * 2
			bos = new BufferedOutputStream(fos, 2097152);
			result = client.retrieveFile(name, bos);
			bos.flush();
			if(!result) {
				throw new FTPDownloadException("文件 " + fileName + " 下载失败！");
			}
			return realPath;
		} catch (Exception e) {
			
			// 必须先关闭流 才能删除成功
			if(null != bos) {
				try {
					bos.close();
				} catch (IOException e2) {
					LOGGER.error(e2.getMessage(), e2);
				}
			}
			
			if(null != fos) {
				
				try {
					fos.close();
				} catch (IOException e2) {
					LOGGER.error(e2.getMessage(), e2);
				}
			}
			
			try {
				this.deleteFile(realPath); // 删除临时文件
			} catch (Exception e2){
				LOGGER.error(e2.getMessage(), e2);
			}
			
			throw new FTPDownloadException(e.getMessage(), e);
			
		} finally {
			
			if(null != bos) {
				try {
					bos.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
			
			if(null != fos) {
				
				try {
					fos.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		}
	}
	
	
	public String [] listFiles(String logPath) throws FTPDirectoryAccessException, IOException {
		
		String path = new String(logPath.getBytes(ENCODING_GBK), ENCODING_ISO_8859_1);
		boolean result = client.changeWorkingDirectory(path);
		if(!result) {
			throw new FTPDirectoryAccessException("访问 " + logPath + " 目录失败！");
		}
		String [] names = client.listNames();
		return names;
	}
	
}
