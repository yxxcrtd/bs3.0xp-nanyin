package com.jixu.adapter.writer;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.tool.SFTPTool;
import com.jixu.common.type.DataCounter;
import com.jixu.common.type.DataReadResult;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.util.AESUtil;

/**
 * sftp上传文件
 * @author wangqidong
 * @version 1.0 2019年7月13日
 * @see WriteSftpFile
 * @since 1.0
 */
@Component("adptWriteSftpFile")
public  class WriteSftpFile extends WriteFileBasic{
	
	public boolean writeFile(InterfaceSource source, DataCounter dataCounter) throws Exception {
		if(source.getChannelType()==InterfaceConfig.SftpType){
			String backupPath=parseDateFolder(source.getBackupPath());
			String fileNameWild=source.getFileName();
			String username=source.getChannelAccount();
	    	String pwd=AESUtil.Decrypt(source.getChannelPWD());
	    	String urlStr=source.getOutFilepath();
	    	String[] urlsArr=urlStr.split(":");
	    	if(urlsArr.length!=3) {
	    		String errormsg="FTP连接地址不正确(Host:Port:FilePath): " + urlStr;
	    		throw new Exception(errormsg);
	    	}
	    	
	    	String host=urlsArr[0];
	    	
	    	int port=Integer.parseInt(urlsArr[1]);
	    	String toPath=urlsArr[2];
	    	toPath=parseDateFolder(toPath);
	    	
	    	String fromPath=source.getFilepath();
	    	fromPath=parseDateFolder(fromPath);
	    	
	    	SFTPTool tool=new SFTPTool(username, pwd, host, port);
	    	
	    	HashMap<String, Object> outparams=dataCounter.getOutParamsMap();
	    	if(outparams!=null) {
		    	if(outparams.containsKey("P_FOLDER")) {
		    		fromPath=outparams.get("P_FOLDER").toString();
		    		if(outparams.containsKey("P_FILENAME")) {
		    			fileNameWild=outparams.get("P_FILENAME").toString();//支持正则表达式
		    		}
		    	}
	    	}
	    	
	    	tool.putFileOrDir(fromPath, toPath,backupPath,fileNameWild);
	    	dataCounter.setFetchRecords(tool.getDownLoadNums());
	    	dataCounter.setTotalRecords(tool.getDownLoadNums());
		}
		return false;
	}

	@Override
	public int getFileType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean writeFile(InterfaceSource interfaceSource, DataReadResult dataSet) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
}
