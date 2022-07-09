package com.joyintech.tams.disclosure.information.reveal.detailshow.service;

import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.*;

public class WebOfficeObject {
    private Map<String,Object> params = new HashMap<>();

	private InputStream fileContentStream;
	private String fileName = "";
	private String sendType ="";
	private static final String MsgError = "404"; //设置常量404，说明没有找到对应的文档

	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	/**
	 * @throws FileUploadException 
	 * @throws IOException 
	 * 后台类解析接口
	 * @time:2015-01-09
	 */
	public void parse(HttpServletRequest request) throws FileUploadException, IOException{
		params = JsonUtils.stringToMap(RequestUtil.getPageParam().get("FormData").toString());

		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if ( "LOADFILE".equals(params.get("OPTION").toString()) ) {
			String fname = params.get("FILENAME").toString();
			fname = "E:/poitest/template/产品说明书.docx";
			fileContentStream = new FileInputStream(fname) ;
		} else{

			// 判断 request 是否有文件上传,即多部分请求
			if (multipartResolver.isMultipart(request)) {
				// 转换成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 取得request中的所有文件名
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					// 取得上传文件
					MultipartFile file = multiRequest.getFile(iter.next());
					if (file != null) {
						// 取得当前上传文件的文件名称
						fileName = StringUtil.trim(file.getOriginalFilename());
						//String filePath = System.getProperty("user.home");
						// 如果名称不为空,说明该文件存在，否则说明该文件不存在
						if (StringUtil.isNotEmpty(fileName)) {
							fileContentStream = file.getInputStream();
						}
					}
				}
			}
		}
	}

    /**
     * 将文件的二进制数据设置到信息包中
     * @param response
     * @throws IOException
     */
    public void send(HttpServletResponse response, int codec) throws IOException{
    	try{
    		String getJsonStr = JsonUtils.object2Json(params);
    		response.reset();
    		response.setHeader("RName", new String( getJsonStr.getBytes("gb2312"), "ISO8859-1" ));
    		if ( fileContentStream!=null ) {
				ByteArrayOutputStream output = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int n = 0;
				while (-1 != (n = fileContentStream.read(buffer))) {
					output.write(buffer, 0, n);
				}
				byte[] fileContent = output.toByteArray();
				fileContentStream.close();
				if (fileContent.length != 0) {
					response.setCharacterEncoding("utf-8");
					response.setContentType("application/x-msdownload;charset=utf-8");
					if (codec == 0) {
						response.setContentLength(fileContent.length);
						response.getOutputStream().write(fileContent, 0, fileContent.length);
					} else if (codec == 1) {
						//char[] charsFileBody = Base64.encode(mFileBody);
						//response.setContentLength(charsFileBody.length);
						//System.out.println("mFileBody.length=" + mFileBody.length + " charsFileBody.length=" + charsFileBody.length);
						//response.getOutputStream().write(getBytes(charsFileBody), 0, charsFileBody.length);
					}

					response.getOutputStream().flush();
					response.getOutputStream().close();

				} else {
					response.setHeader("MsgError", WebOfficeObject.MsgError);
				}
				response.flushBuffer();
			}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    } 	
    
}
