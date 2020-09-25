package com.joyintech.tams.disclosure.busicommon.infodisclosure.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.RepInfoMapper;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosuregenandsend.service.InfoDisclosureGenService;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.ReportManager;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.pojo.ReportInfo;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;

/**
 * jaspar连接公共类
 * @author wangmin
 * 2018-12-13
 *
 */
@Service("disclosureJasparConnectUtilService")
public class JasparConnectUtilService {
	@Autowired
	@Qualifier("masterDataSource") DataSource master;
	
	@Autowired
	private ReportManager reportManager;
	
	@Autowired
	private FileAttachmentProcess fileAttachmentProcess;
	
	
	@Autowired
    private JoyinDFSTemplate joyinDFSTemplate;

	@Autowired
	private RepInfoMapper repInfoMapper;
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(InfoDisclosureGenService.class);
	/**
	 * 连接Jaspar并将url上的连接下载到本地固定位置
	 * @param strUrl 连接url
	 * @param fileName 需要下载的文件名
	 * @param fileType 下载文件类型
	 * @param repId 报表id
	 * @param prodId 产品id
	 * @return
	 * @throws Exception
	 */
	public UploadFile connectJasparAndDownload_old(String strUrl, String fileName,// 修改为old方法，不使用 zhangyf 20190219
			String fileType, String repId, String prodId) throws Exception{
		logger.info("连接报表服务器开始=====");
		URL connUrl = new URL(strUrl);
		fileName = fileName + GlobalConstant.UNDER_LINE + DateUtil.getNewSysDate() +GlobalConstant.POINT+ fileType;
		URLConnection conn = connUrl.openConnection();
		logger.info("连接报表服务器结束=====");
		// 防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        InputStream inputStream = conn.getInputStream();

        String filePathName = prodId + GlobalConstant.UNDER_LINE + repId + GlobalConstant.UNDER_LINE + DateUtil.getNewSysDate() +GlobalConstant.POINT+ fileType;
        // 获取自己数组
        byte[] getData = readInputStream(inputStream);
        
        String path = FileNameUtil.createUploadFilePath("");
        logger.info("判断是否有指定文件夹开始=====");
        File localFile = new File(path+ File.separator+filePathName);
        File dir = localFile.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        logger.info("判断是否有指定文件夹结束=====");    
        logger.info("写文件开始=====");

		FileOutputStream fos =null;
        try {
			fos = new FileOutputStream(localFile);
			fos.write(getData);
			logger.info("写文件结束=====");
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		finally {
			if (fos != null) {
				fos.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		}

        UploadFile uploadFile = new UploadFile(fileName, IDGenerator.generate(),
                localFile);
        //存入SYS_UPLOAD_FILE_INFO表
        if(null != fileAttachmentProcess) {
            fileAttachmentProcess.upload(uploadFile);
        }
        
      //add by leipeng 上传文件处理
        SysUploadFileInfo sysUploadFileInfo = new SysUploadFileInfo();
        sysUploadFileInfo.setSeqNo(uploadFile.getFileNo());
        sysUploadFileInfo.setFileName(FileNameUtil.formatFileName(uploadFile.getTargetFile().getName()));
        sysUploadFileInfo.setPath(uploadFile.getTargetFile().getPath());
        joyinDFSTemplate.upload(sysUploadFileInfo);
        
		return uploadFile;
	}
	
	public UploadFile connectJasparAndDownload(String strUrl, String fileName,
			String fileType, String repId, String prodId,Map<String,Object> params) throws Exception{
		if(null!=params.get("endDate")){
    		Date endDate = DateUtil.parse(params.get("endDate").toString());
    		params.put("endDate", new java.sql.Date(endDate.getTime()));
    	}
    	if(null!=params.get("startDate")){
    		Date endDate = DateUtil.parse(params.get("startDate").toString());
    		params.put("startDate", new java.sql.Date(endDate.getTime()));
    	}
		// 通过REP_ID获取TEMPLATE_ID
//		RepInfo repInfo = repInfoMapper.selectByPrimaryKey(repId);
		String templateId = repId;
		ReportInfo r1 = reportManager.getReportByURI(templateId);
		reportManager.setDataSource(master);
		String path = FileNameUtil.createUploadFilePath("");
		File filepath = new File(path);
		if(!filepath.exists()){
			filepath.mkdirs();
		}
		String filePathName = prodId + GlobalConstant.UNDER_LINE + repId + GlobalConstant.UNDER_LINE 
				+ DateUtil.getNewSysDate() +GlobalConstant.POINT+ fileType;
		reportManager.exportReport(r1, params, fileType, path+ File.separator+filePathName);
		fileName =fileName+GlobalConstant.POINT+fileType;
		File localFile = new File(path+ File.separator+filePathName);
		logger.info("UploadFile fileName= "  + path+ File.separator+filePathName);
		UploadFile uploadFile = new UploadFile(fileName, IDGenerator.generate(),
                localFile);
		
		//存入SYS_UPLOAD_FILE_INFO表
        if(null != fileAttachmentProcess) {
            fileAttachmentProcess.upload(uploadFile);
        }
        
      //add by leipeng 上传文件处理
        SysUploadFileInfo sysUploadFileInfo = new SysUploadFileInfo();
        sysUploadFileInfo.setSeqNo(uploadFile.getFileNo());
        sysUploadFileInfo.setFileName(FileNameUtil.formatFileName(uploadFile.getTargetFile().getName()));
        sysUploadFileInfo.setPath(uploadFile.getTargetFile().getPath());
        joyinDFSTemplate.upload(sysUploadFileInfo);
        
		return uploadFile;
		
	}
	/**
	 * 连接Jaspar并将url上的连接下载到本地固定位置
	 * @param strUrl 连接url
	 * @param fileName 需要下载的文件名
	 * @param fileType 下载文件类型
	 * @param repId 报表id
	 * @param prodId 产品id
	 * @return
	 * @throws Exception
	 */
	public UploadFile connectJasparAndExport_old(String strUrl, String fileName,// 修改为old方法，不使用 zhangyf 20190219
			String fileType) throws Exception{
		
		URL connUrl = new URL(strUrl);
		fileName = fileName + GlobalConstant.UNDER_LINE + DateUtil.getNewSysDate() +GlobalConstant.POINT+ fileType;
		
		URLConnection conn = connUrl.openConnection();
		// 防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        InputStream inputStream = conn.getInputStream();

        // 获取自己数组
        byte[] getData = readInputStream(inputStream);
        
        String path = FileNameUtil.createUploadFilePath("");
        
        File localFile = new File(path+ File.separator+fileName);
        File dir = localFile.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }

		FileOutputStream fos = null;

        try {
			fos = new FileOutputStream(localFile);
			fos.write(getData);
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		finally {
			if (fos != null) {
				fos.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		}
        UploadFile uploadFile = new UploadFile(fileName, IDGenerator.generate(),
                localFile);
        //存入SYS_UPLOAD_FILE_INFO表
        if(null != fileAttachmentProcess) {
            fileAttachmentProcess.upload(uploadFile);
        }
        
      //add by leipeng 上传文件处理
        SysUploadFileInfo sysUploadFileInfo = new SysUploadFileInfo();
        sysUploadFileInfo.setSeqNo(uploadFile.getFileNo());
        sysUploadFileInfo.setFileName(FileNameUtil.formatFileName(uploadFile.getTargetFile().getName()));
        sysUploadFileInfo.setPath(uploadFile.getTargetFile().getPath());
        joyinDFSTemplate.upload(sysUploadFileInfo);
        
		return uploadFile;
	}
	public UploadFile connectJasparAndExport(String strUrl, String fileName,
			String fileType,String repId,Map<String,Object> params) throws Exception{
		if(null!=params.get("endDate")){
    		Date endDate = DateUtil.parse(params.get("endDate").toString());
    		params.put("endDate", new java.sql.Date(endDate.getTime()));
    	}
    	if(null!=params.get("startDate")){
    		Date endDate = DateUtil.parse(params.get("startDate").toString());
    		params.put("startDate", new java.sql.Date(endDate.getTime()));
    	}
    	//
    	if(null!=params.get("portnum")){
    		String portnum = params.get("portnum").toString();
    		if(portnum.matches("^(([1-9]{1}\\d*)|([0]{1}))\\.((\\d){0,2})?$")){
    			portnum = portnum.split(".")[0];
    		}
    		params.put("portnum", portnum);
    	}
    	
    	
		// 通过REP_ID获取TEMPLATE_ID
//		RepInfo repInfo = repInfoMapper.selectByPrimaryKey(repId);
//		String templateId = repInfo.getTemplateId();
		ReportInfo r1 = reportManager.getReportByURI(repId);
		reportManager.setDataSource(master);
		String path = FileNameUtil.createUploadFilePath("");
		File filepath = new File(path);
		if(!filepath.exists()){
			filepath.mkdirs();
		}
		//修改生成文件时间戳.避免文件生成少的问题
		fileName = fileName + GlobalConstant.UNDER_LINE + System.currentTimeMillis() +GlobalConstant.POINT+ fileType;
		reportManager.exportReport(r1, params, fileType, path+ File.separator+fileName);

		File localFile = new File(path+ File.separator+fileName);
		UploadFile uploadFile = new UploadFile(fileName, IDGenerator.generate(),
				localFile);
		//存入SYS_UPLOAD_FILE_INFO表
		if(null != fileAttachmentProcess) {
			fileAttachmentProcess.upload(uploadFile);
		}
		
		//add by leipeng 上传文件处理
        SysUploadFileInfo sysUploadFileInfo = new SysUploadFileInfo();
        sysUploadFileInfo.setSeqNo(uploadFile.getFileNo());
        sysUploadFileInfo.setFileName(FileNameUtil.formatFileName(uploadFile.getTargetFile().getName()));
        sysUploadFileInfo.setPath(uploadFile.getTargetFile().getPath());
        joyinDFSTemplate.upload(sysUploadFileInfo);
		return uploadFile;
	}
	/**
     * 从输入流中获取字节数组
     * 
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
    /**
     * 从输入流中获取字节数组
     * 
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static String readInputStreamToString(InputStream inputStream) throws IOException {
    	BufferedReader tBufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    	 
        StringBuffer tStringBuffer = new StringBuffer();

        String sTempOneLine = new String("");

        while ((sTempOneLine = tBufferedReader.readLine()) != null){

            tStringBuffer.append(sTempOneLine);

        }
        return tStringBuffer.toString();
    }
    /**
	 * 连接Jaspar并将url上的连接下载到本地固定位置
	 * @param strUrl 连接url
	 * @param fileName 需要下载的文件名
	 * @param fileType 下载文件类型
	 * @return
	 * @throws Exception
	 */
	public String  connectJasparAndGenHtml(String strUrl) throws Exception{
		
		URL connUrl = new URL(strUrl);
//		fileName = fileName + GlobalConstant.UNDER_LINE + DateUtil.getNewSysDate() +GlobalConstant.POINT+ fileType;
		
		URLConnection conn = connUrl.openConnection();
		// 防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        InputStream inputStream = conn.getInputStream();
        
        // 获取自己数组
        String getData = readInputStreamToString(inputStream);
        
//        String s = getData.toString();
//        String path = FileNameUtil.createUploadFilePath("");
        
//        File localFile = new File(path+ File.separator+fileName);
//        File dir = localFile.getParentFile();
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//            
//        FileOutputStream fos = new FileOutputStream(localFile);
//        fos.write(getData);
//        if (fos != null) {
//            fos.close();
//        }
        if (inputStream != null) {
            inputStream.close();
        }
//        UploadFile uploadFile = new UploadFile(fileName, IDGenerator.generate(),
//                localFile);
//        //存入SYS_UPLOAD_FILE_INFO表
//        if(null != fileAttachmentProcess) {
//            fileAttachmentProcess.upload(uploadFile);
//        }
        
		return getData;
	}
	/**
	 * 连接Jaspar并将url上的xml解析成list格式
	 * @param strUrl 连接url
	 * @param flag 是否是输入条件 true是false不是
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>>  connectJasparAndGenXML(String strUrl,boolean flag) throws Exception{
		
		URL connUrl = new URL(strUrl);
//		fileName = fileName + GlobalConstant.UNDER_LINE + DateUtil.getNewSysDate() +GlobalConstant.POINT+ fileType;
		
		URLConnection conn = connUrl.openConnection();
		// 防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        InputStream inputStream = conn.getInputStream();
        
        // 获取自己数组
//        String getData = readInputStreamToString(inputStream);
//        List<Map<String,String>> list = InfoXmlParseUtil.parse(inputStream);
//        if(StringUtil.isEmptyList(list)){
          List<Map<String,String>> list  = new ArrayList<Map<String,String>>();
          if(flag){
        	  list = InfoXmlInputParseUtil.parse(inputStream);
          }
          else{
        	  list = InfoXmlParseUtil.parse(inputStream);
          }
//        }
//        String s = getData.toString();
//        String path = FileNameUtil.createUploadFilePath("");
        
//        File localFile = new File(path+ File.separator+fileName);
//        File dir = localFile.getParentFile();
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//            
//        FileOutputStream fos = new FileOutputStream(localFile);
//        fos.write(getData);
//        if (fos != null) {
//            fos.close();
//        }
        if (inputStream != null) {
            inputStream.close();
        }
//        UploadFile uploadFile = new UploadFile(fileName, IDGenerator.generate(),
//                localFile);
//        //存入SYS_UPLOAD_FILE_INFO表
//        if(null != fileAttachmentProcess) {
//            fileAttachmentProcess.upload(uploadFile);
//        }
        
		return list;
	}
    /**
     * 获取报表全路径
     * @param serverUrl 服务器url
     * @param serverUser 用户
     * @param serverPwd  密码
     * @param infoDisclosureGenAndSendEntity 批处理配置
     * @param check 与报表服务器通信时是否发送完整文件校验字段
     * @return
     */
    public String getAllHtmlUrl(String serverUrl, String serverUser, String serverPwd, String  path, String uri, RepReveal repReveal){
    	String url= serverUrl + ReportGlobalConstant.MID_URL + path + ReportGlobalConstant.OBLIQUE_LINE + uri + ".html" ;
    	String paramStr = ReportGlobalConstant.QUESTION_MARK + ReportGlobalConstant.J_USERNAME + serverUser + ReportGlobalConstant.AMPERS_AND + ReportGlobalConstant.J_PASSWORD + serverPwd;
    	if(StringUtil.isNotEmpty(repReveal.getProdid())){
    		paramStr = paramStr + ReportGlobalConstant.AMPERS_AND + "portnum=" + repReveal.getProdid();
    	}
    	if(repReveal.getStartDate()!=null){
    		paramStr = paramStr + ReportGlobalConstant.AMPERS_AND + "startDate=" + DateUtil.format(repReveal.getStartDate());
    	}
    	if(repReveal.getEndDate()!=null){
    		paramStr = paramStr + ReportGlobalConstant.AMPERS_AND + "endDate=" + DateUtil.format(repReveal.getEndDate());
    	}
    	url = url+ paramStr;
    	return url;
    }
    /**
     * 获取报表全路径
     * @param serverUrl 服务器url
     * @param serverUser 用户
     * @param serverPwd  密码
     * @param path 
     * @return
     */
    public String getReportsUrl(String serverUrl, String serverUser, String serverPwd, String  path){
    	String url= serverUrl + "/rest_v2/resources";
    	String paramStr = ReportGlobalConstant.QUESTION_MARK + ReportGlobalConstant.J_USERNAME + serverUser +
    			ReportGlobalConstant.AMPERS_AND + ReportGlobalConstant.J_PASSWORD + serverPwd
    			+ReportGlobalConstant.AMPERS_AND +"folderUri="+path+ReportGlobalConstant.AMPERS_AND +"type=reportUnit";
    	url = url+ paramStr;
    	return url;
    }
    /**
     * 获取报表全路径
     * @param serverUrl 服务器url
     * @param serverUser 用户
     * @param serverPwd  密码
     * @param path 
     * @return
     */
    public String getReportsInputsUrl(String serverUrl, String serverUser, String serverPwd, String  path){
    	String url= serverUrl + "/rest_v2/reports"+path+"/inputControls";
    	String paramStr = ReportGlobalConstant.QUESTION_MARK + ReportGlobalConstant.J_USERNAME + serverUser +
    			ReportGlobalConstant.AMPERS_AND + ReportGlobalConstant.J_PASSWORD + serverPwd;
    	url = url+ paramStr;
    	return url;
    }
}
