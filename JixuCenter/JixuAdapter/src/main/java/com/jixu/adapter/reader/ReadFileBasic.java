package com.jixu.adapter.reader;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.jixu.adapter.dao.intf.InterfaceColConfigDao;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.util.AESUtil;
import com.joyintech.framework.util.StringUtil;
import com.jixu.common.pojo.InterfaceColConfig;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.tool.FTPTool;
import com.jixu.common.tool.SFTPTool;
import com.jixu.common.tool.WildcardMatch;
import com.jixu.common.type.DataCounter;
import com.jixu.common.type.DataReadResult;

/** 
 * @ClassName 		ReadFileBasic
 * @author  		Pan Wei 
 * @Date 			2016年12月5日 下午3:13:02 	
 * @Description		[]  
 */

public abstract class ReadFileBasic implements ReadDataFile{
	
	@Autowired
	protected InterfaceColConfigDao interfaceColConfigDao;
	
	protected int readLineInOneFile=100000;
	
	private String DEFAULT_FORMAT = "yyyyMMdd";
	
	protected List<String> getReadFiles(InterfaceSource interfaceSource){
		String folderPath=interfaceSource.getFilepath();
		folderPath=parseDateFolder(folderPath);
		String filename=interfaceSource.getFileName();
		String compareFilePath=folderPath+"/"+filename;
		compareFilePath=compareFilePath.replaceAll("/", "");
		compareFilePath=compareFilePath.replaceAll("\\\\", "");
		List<String> fileList=new ArrayList<String>();
		File folder=new File(folderPath);
		if(folder.isDirectory()){
			getDirectory(folderPath, fileList, compareFilePath);
		}
		
		Collections.sort(fileList);
		return fileList;
	}
	
	/**
	 * 递归读取所有文件
	 * @param filepath
	 * @param fileList
	 */
	private void getDirectory(String filepath, List<String> fileList, String comparePath){
		WildcardMatch wildcardMatch = new WildcardMatch();
		File file = new File(filepath);
		if (file.isDirectory()) {
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				String filefullname=filepath + "/" + filelist[i];
				File readfile = new File(filefullname);
				if (readfile.isDirectory()) {
					getDirectory(filefullname, fileList, comparePath);
				}else{
					String tmpCompare=filefullname.replaceAll("/", "");
					tmpCompare=tmpCompare.replaceAll("\\\\", "");
					if(wildcardMatch.wildcardMatch(comparePath, tmpCompare)){
						fileList.add(filefullname);
					}
				}
			}
		}
	}
	
	/**
	 * 如果是txt,csv等文本格式,可以通过本方法把字段放入fileContainer容器
	 * @param fileContainer
	 * @param line
	 * @param source
	 * @param readLineNum
	 * @return
	 */
	public boolean preFilterLine(List<List<Object>> fileContainer, String line, InterfaceSource source, int readLineNum){
		//从第几行开始读取
		if(readLineNum>=source.getTableStartLine()){
			if(source.getTableEndChar()!=null && !source.getTableEndChar().equals("")){
				//是否有结束符
				if(line.contains(source.getTableEndChar()))
					return false;
			}
			List<Object> fieldsList=new ArrayList<Object>();
			//to-do pending, spilit分隔,应该还有性能提升空间
			if(source.getTableSpilitChar()==null || source.getTableSpilitChar().equals("")){
				fieldsList.add(line);
			}else{
				String splitChar=source.getTableSpilitChar();
				splitChar=getSplitWrite(splitChar);
				String[] fields=line.split(Pattern.quote(splitChar));
				for(String field:fields){
					fieldsList.add(field);
				}
			}
			fileContainer.add(fieldsList);
		}
		return true;
	}
	
	@Override
	public DataReadResult readFileData(InterfaceSource interfaceSource, DataCounter dataCounter) throws Exception {
		DataReadResult result=null;
		if(interfaceSource.getChannelType()==InterfaceConfig.ftpType) {
			//临时解决方案：如果配置FTP形式，适配器只提供下载文件功能
			//之后再配置适配器进行本地文件的读取和解析
			result=putFilesByFTP(interfaceSource, dataCounter);
		}else {
			result=readFileDataResult(interfaceSource,dataCounter);
			List<List<Object>> resultList=result.getDataResult();
			//获取配置的最大值参数，目前用于记录下来上一次获取数据的时候的某列最大值是多少，传给下游的信批系统使用
			HashMap<Integer, Object> maxParams=getMaxParam(interfaceSource, resultList);
			
			//文件类型的一次性取数完成，不会分成多次取数
			if(getFileType()!=InterfaceConfig.csv && getFileType()!=InterfaceConfig.txt){
				int size=resultList.size();
				dataCounter.setFetchRecords(size);
				dataCounter.setTotalRecords(size);
			}
			
			dataCounter.setMaxCycleMap(maxParams);
			dataCounter.setCheckFlag(true);
		}
		return result;
	}
	
	protected abstract DataReadResult readFileDataResult(InterfaceSource interfaceSource,DataCounter dataCounter) throws Exception;
	
	
    
    //获取文件里的某列的最大值
    private HashMap<Integer, Object> getMaxParam(InterfaceSource interfaceSource, List<List<Object>> resultList){
    	HashMap<Integer, Object> cycleMap=new HashMap<Integer, Object>();
    	List<InterfaceColConfig> cfgList=interfaceColConfigDao.loadCycleMaxParam(interfaceSource.getSourceID());
    	for(InterfaceColConfig config:cfgList){
    		String[] params=config.getFuncParams().split(",");
    		List<String> preresults=new ArrayList<String>();
    		if(params.length==2){
    			int colNum=Integer.valueOf(params[0])-1;
    			for(List<Object> fieldsList:resultList){
    				for(int i=0;i<fieldsList.size();i++){
    					if(i==colNum){
    						//根据参数配置里，取数该列所有的值
    						preresults.add(fieldsList.get(i).toString());
    					}
    				}
    			}
    			
    			//获取list里的最大值
    			Object maxObj=Collections.max(preresults);
				cycleMap.put(config.getId(), maxObj);
			}
    	}
    	
    	return cycleMap;
    }
    
    /**
     * 通过ftp下载文件
     * @param source
     * @param dataCounter
     * @return
     * @throws Exception
     */
    private DataReadResult putFilesByFTP(InterfaceSource source, DataCounter dataCounter) throws Exception{
    	DataReadResult result=new DataReadResult();
    	String backupPath = parseDateFolder(source.getBackupPath());;
    	String username=source.getChannelAccount();
    	String pwd=AESUtil.Decrypt(source.getChannelPWD());
    	String urlStr=source.getFilepath();
    	String[] urlsArr=urlStr.split(":");
    	if(urlsArr.length!=3) {
    		String errormsg="FTP连接地址不正确(Host:Port:FilePath): " + urlStr;
    		throw new Exception(errormsg);
    	}
    	
    	String host=urlsArr[0];
    	int port=Integer.parseInt(urlsArr[1]);
    	String fromPath=urlsArr[2];
    	fromPath=parseDateFolder(fromPath);
    	
    	String toPath=source.getFileName();
    	toPath=parseDateFolder(toPath);
    	
    	if(port==21){
            FTPTool tool=new FTPTool(username, pwd, host, port);
            tool.downFileOrDir(fromPath, toPath,backupPath);
            dataCounter.setFetchRecords(tool.getDownLoadNums());
            dataCounter.setTotalRecords(tool.getDownLoadNums());
        }else{
            
            SFTPTool tool=new SFTPTool(username, pwd, host, port);
            tool.downFileOrDir(fromPath, toPath,backupPath);
            dataCounter.setFetchRecords(tool.getDownLoadNums());
            dataCounter.setTotalRecords(tool.getDownLoadNums());
        }
    	return result;
    }
    
    /**
	 * 解析带有特殊日期字符的路径
	 * @param folderPath
	 * @return
	 */
	private String parseDateFolder(String folderPath) {
		//判断是否包含双斜杠，若包含双斜杠，则要求后面必须跟一个日期格式类型 add by wqd
		if(StringUtil.isNotEmpty(folderPath) && folderPath.contains("//")){
			String ach="\\\\";
			String bch="//";
			int apoint=folderPath.indexOf(ach);
			int bpoint=folderPath.indexOf(bch);
			String datetime = "";
			if(apoint>=0 && bpoint>apoint){
				datetime=folderPath.substring(apoint+2, bpoint);
			}
			String[] path = folderPath.split("\\\\");
			DEFAULT_FORMAT = datetime.trim();
			SimpleDateFormat date = new SimpleDateFormat(DEFAULT_FORMAT);
			datetime = date.format(new Date());
			folderPath = path[0]+"/"+datetime;
		}
		return folderPath;
	}
	
	/**
	 * 处理分隔符，有恒生的byte分隔符特殊处理
	 * @param splitstr
	 * @return
	 */
	protected String getSplitWrite(String splitstr) {
		if(splitstr==null) {
			return splitstr;
		}else if(splitstr.startsWith("byte{") && splitstr.endsWith("}")) {
			//针对用byte分隔的，目前用在恒生ta系统
			splitstr=splitstr.substring(5, splitstr.length()-1);
			String[] splitStrArrs=splitstr.split(",");
			byte[] bytessplit=new byte[splitStrArrs.length];
			for(int i=0;i<splitStrArrs.length;i++) {
				bytessplit[i]=Byte.valueOf(splitStrArrs[i]);
			}
			splitstr=new String(bytessplit);
		}
		return splitstr;
	}
	
	/**
	 * 获取文件开头过滤的行数
	 * @param source
	 * @return
	 */
	protected int getIgnoreLines(InterfaceSource source) {
		int ignore=0;
		int startLine=source.getTableStartLine();
		if(startLine>=1) {
			//填写1，则是第一行开始读
			//填写2，则是第二行开始读，过滤了一行
			//填写0，也是从第一行开始读，因此不减1
			ignore=startLine-1;
		}
		return ignore;
	}
}
