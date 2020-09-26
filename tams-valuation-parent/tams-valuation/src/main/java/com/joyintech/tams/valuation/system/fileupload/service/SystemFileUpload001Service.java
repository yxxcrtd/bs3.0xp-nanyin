package com.joyintech.tams.valuation.system.fileupload.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.tams.valuation.common.utils.SftpTools;

/**
 * 系统文件上传service
 * 
 * @author Administrator
 *
 */
@Service
public class SystemFileUpload001Service {

	@Autowired
	private ProgressBarService progressBarService;
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(SystemFileUpload001Service.class);

	/**
	 * 读取文件并上传到文件服务器
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void readFile(HttpServletRequest request) throws Exception {
		// 文件本地路径
		String filePath = request.getParameter("fileName");
		
		// 文件名称
		String fileName = FileNameUtil.formatFileName(filePath);
		
		File file = new File(filePath);
		boolean rec = file.renameTo(new File(fileName));
		if(rec){
			logger.error("处理成功");
		}
		
		String execDate = request.getParameter("execDate");

        String ip = CacheUtil.getSysParam("FILEUPLOAD_HOST");// 主机IP
        Integer port = Integer.parseInt(CacheUtil.getSysParam("FILEUPLOAD_PORT"));// 主机端口
        String username = CacheUtil.getSysParam("FILEUPLOAD_USERNAME");// 主机用户名
        String password = CacheUtil.getSysParam("FILEUPLOAD_PASSWORD");// 主机密码或秘钥地址
        String sftpLoginType = CacheUtil.getSysParam("SFTP_LOGIN_TYPE");// SFTP登录方式

		// 主机远程路径
		String remotePath = CacheUtil.getSysParam("FILEUPLOAD_REMOTE_PATH") + "/"
				+ DateUtil.format(DateUtil.parse(execDate), DateUtil.DEFAULT_OTHER_FORMAT);

		SftpTools sftpTools = new SftpTools();
		// sftp登录
		sftpTools.login(username, password, ip, port, sftpLoginType);

		sftpTools.putFile(fileName, remotePath);
		// 关闭登录
		sftpTools.close();

		progressBarService.updatePercent("FileUpload001", ProgressBarService.FILE_UPLOAD_ID, 1D, false);
	}

}
