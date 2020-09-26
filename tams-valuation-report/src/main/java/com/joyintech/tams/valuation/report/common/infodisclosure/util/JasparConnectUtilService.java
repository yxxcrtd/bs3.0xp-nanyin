package com.joyintech.tams.valuation.report.common.infodisclosure.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.valuation.report.common.base.service.ServiceFromJar;
import com.joyintech.tams.valuation.report.common.constant.DateUtil;
import com.joyintech.tams.valuation.report.common.util.ReportGlobalConstant;

/**
 * jaspar连接公共类
 * @author wangmin
 * 2018-12-13
 *
 */
@Service("valuationJasparConnectUtilService")
public class JasparConnectUtilService {
	@Autowired
	//private FileAttachmentProcess fileAttachmentProcess;
	private ServiceFromJar fileAttachmentProcess;
	
	
	/**
	 * 连接Jaspar并将url上的连接下载到本地固定位置
	 * @param strUrl 连接url
	 * @param fileName 需要下载的文件名
	 * @param fileType 下载文件类型
	 * @return
	 * @throws Exception
	 */
	public UploadFile connectJasparAndDownload(String strUrl, String fileName,
			String fileType) throws Exception{
		
		URL connUrl = new URL(strUrl);
		fileName = fileName + GlobalConstant.UNDER_LINE + DateUtil.getNewSysDate() +GlobalConstant.POINT+ fileType;
		
		URLConnection conn = connUrl.openConnection();
		// 防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        InputStream inputStream = null;
        FileOutputStream fos = null;
        File localFile = null;
        try {
            inputStream = conn.getInputStream();
            // 获取自己数组
            byte[] getData = readInputStream(inputStream);

            String path = FileNameUtil.createUploadFilePath("");

            localFile = new File(path + File.separator + fileName);
            File dir = localFile.getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }

            fos = new FileOutputStream(localFile);
            fos.write(getData);
        }
        catch (IOException e) {
            throw e;
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
     * 获取报表全路径
     * @param serverUrl 服务器url
     * @param serverUser 用户
     * @param serverPwd  密码
     * @param infoDisclosureGenAndSendEntity 批处理配置
     * @param check 与报表服务器通信时是否发送完整文件校验字段
     * @return
     */
    public String getAllHtmlUrl(String serverUrl, String serverUser, String serverPwd, String  path, String uri, RepReveal repReveal,String fileType){
    	String url= serverUrl + ReportGlobalConstant.MID_URL + path + ReportGlobalConstant.OBLIQUE_LINE + uri + GlobalConstant.POINT+fileType ;
    	String paramStr = ReportGlobalConstant.QUESTION_MARK + ReportGlobalConstant.J_USERNAME + serverUser + ReportGlobalConstant.AMPERS_AND + ReportGlobalConstant.J_PASSWORD + serverPwd;
    	if(StringUtil.isNotEmpty(repReveal.getProdid())){
    		paramStr = paramStr + ReportGlobalConstant.AMPERS_AND + "prodId=" + repReveal.getProdid();
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
	 * 连接Jaspar并将url上的连接下载到本地固定位置
	 * @param strUrl 连接url
	 * @param fileName 需要下载的文件名
	 * @param fileType 下载文件类型
	 * @return
	 * @throws Exception
	 */
	public String  connectJasparAndGenHtml(String strUrl) throws Exception{
		
		URL connUrl = new URL(strUrl);
		
		URLConnection conn = connUrl.openConnection();
		// 防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        InputStream inputStream = conn.getInputStream();
        
        // 获取自己数组
        String getData = readInputStreamToString(inputStream);
        if (inputStream != null) {
            inputStream.close();
        }
		return getData;
	}
	 /**
     * 从输入流中获取字节数组
     * 
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static String readInputStreamToString(InputStream inputStream) throws IOException {
    	BufferedReader tBufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
    	 
        StringBuffer tStringBuffer = new StringBuffer();

        String sTempOneLine = new String("");

        while ((sTempOneLine = tBufferedReader.readLine()) != null){

            tStringBuffer.append(sTempOneLine);

        }
        return tStringBuffer.toString();
    }
}
