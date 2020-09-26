package com.joyintech.tams.report.submitmaintenance.equityinfo.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;



/**
 * Description:写入文件的单例模式，避免文件写入问题
 * 
 * @author 永丰基地
 * 
 */
public class FileIO {

	private static final String GBK = "gbk";

	private FileIO() {
	}

	private static final FileIO fio = new FileIO();
   

	/** 预定义编码方式写入 */
	public static void write(String path, String content) {
		fio.writeByFileOutputStream(path, content);
	}

	/** 自定义编码方式写入 **/
	public static void write(String path, String content, String encode) {
		fio.writeByFileOutputStream(path, content, encode);
	}

	/**
	 * 预定义以gbk编码写入文件
	 * 
	 * @param path
	 * @param content
	 */
	private void writeByFileOutputStream(String path, String content) {
		writeFile(path, content, GBK);
	}

	/**
	 * 自定义编码方式写入文件内容
	 * 
	 * @param path
	 * @param content
	 * @param encode
	 */
	private void writeByFileOutputStream(String path, String content,
			String encode) {
		writeFile(path, content, encode);
	}

	private void writeFile(String path, String content, String encode) {
		File f = new File(path);
		File file = f.getParentFile();
		if (!file.exists()) {
			file.mkdirs();
		}
		OutputStreamWriter write = null;
		BufferedWriter writer = null;
		FileOutputStream fos = null;
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			fos = new FileOutputStream(f);
			write = new OutputStreamWriter(fos, encode);
			writer = new BufferedWriter(write);
			writer.write(content);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		finally {
    		if(writer !=null ){
    			try {
    				writer.close();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    		if(write !=null ){
    			try {
    				write.close();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    		if(fos !=null ){
    			try {
    				fos.close();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
		}
	}

	public static void copy(String oldPath, String newPath) {
		fio.copyFile(oldPath, newPath);
	}

	/** 复制文件的方法 */
	private void copyFile(String oldPath, String newPath) {
		FileInputStream inStream = null;
		FileOutputStream fs = null;
		try {
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				inStream = new FileInputStream(oldPath); // 读入原文件
				File file = new File(newPath);
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
				fs.close();
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		finally {
    		if(inStream !=null ){
    			try {
    				inStream.close();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    		if(fs !=null ){
    			try {
    				fs.close();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
		}
	}

	public static void writeList(String path, List<Map<String, String>> nodes,
			String encode) {
		// TODO Auto-generated method stub
		File f = new File(path);
		File file = f.getParentFile();
		if (!file.exists()) {
			file.mkdirs();
		}
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			/** 设置XML文档格式 */
			OutputFormat outputFormat = OutputFormat.createPrettyPrint();
			/** 设置XML编码方式 */
			outputFormat.setEncoding(CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY,ReportGlobalConstant.SYS_PARA_SALES_FILE_ENCODE));
			/** 设置是否缩进 */
			outputFormat.setIndent(true);
			/** 以四个空格方式实现缩进 */
			outputFormat.setIndent("    ");
			/** 设置是否换行 */
			outputFormat.setNewlines(true);
			/** stringWriter字符串是用来保存XML文档的 */
			StringWriter stringWriter = new StringWriter();
			/** xmlWriter是用来把XML文档写入字符串的(工具) */
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(f),outputFormat);
			/** 创建xml文档 */
			Document document = DocumentHelper.createDocument();
			Element list = document.addElement(ReportGlobalConstant.XML_LIST);
			Element row = null;
			Element field = null;
			xmlWriter.writeOpen(list);
			for (Map<String, String> map : nodes) {
				row = list.addElement(ReportGlobalConstant.XML_ROW);
				for (Map.Entry<String, String> entry : map.entrySet()) {
					field = row.addElement(entry.getKey());
					field.setText(entry.getValue());
				}
				xmlWriter.write(row);
			}
			xmlWriter.writeClose(list);
			xmlWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/* 生成反馈文件
	 * 
	 * @param list
	 */
	public static void sendResponseFile(List<Map<String, String>> list,
		String fileName) {
		String outputPath = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY,ReportGlobalConstant.INTERF_SALE_SEND_PATH)
				+ new SimpleDateFormat("yyyyMMdd").format(new Date())
				+ File.separator + fileName + ReportGlobalConstant.FILE_SUFFIX_RES;

		if(StringUtil.isEmptyList(list)){
			return;
		}
		System.out.println("结果list大小："+list.size());
		/** 反馈文件写入文件中 */
		FileIO.writeList(outputPath, list,
				CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY,ReportGlobalConstant.SYS_PARA_SALES_FILE_ENCODE));
		/** 发送至数据共享 */
		outputPath = outputPath.replaceAll("\\\\", "/");// 路径
		String allPath = outputPath.substring(0,outputPath.lastIndexOf("/")+1);// 路径
		String fileName1 = outputPath.substring(outputPath.lastIndexOf("/")+1);// 文件名
		SFTPconnect_upload(allPath, fileName1);
		
	}
	/**
	 * 生成发送xml文件
	 * 
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	public static void sendRequestFile(List<Map<String, String>> list,
			String fileName) {
		String outputPath =  CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY,ReportGlobalConstant.INTERF_SALE_SEND_PATH)
				+ new SimpleDateFormat("yyyyMMdd").format(new Date())
				+ File.separator + fileName + ".xml";
		if(StringUtil.isEmptyList(list)){
			return;
		}
		System.out.println("结果list大小："+list.size());
		/** 反馈文件写入文件中 */
		FileIO.writeList(outputPath, list,
				 CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY,ReportGlobalConstant.SYS_PARA_SALES_FILE_ENCODE));
		/** 发送至数据共享 */
		outputPath = outputPath.replaceAll("\\\\", "/");// 路径
		String allPath = outputPath.substring(0,outputPath.lastIndexOf("/")+1);// 路径
		String fileName1 = outputPath.substring(outputPath.lastIndexOf("/")+1);// 文件名
		SFTPconnect_upload(allPath, fileName1);
	}
	//上传到文件服务器
    public static void SFTPconnect_upload (String allPath, String fileName1){  
    	//ftp url
    	String url = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY,ReportGlobalConstant.SYS_PARA_SALES_FTP_IP);
        // PORT
        String port = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY,ReportGlobalConstant.SYS_PARA_SALES_FTP_PROT);
        // ftp连接用户名
        String username = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY,ReportGlobalConstant.SYS_PARA_SALES_FTP_USER);
        // ftp密码
        String password = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, ReportGlobalConstant.SYS_PARA_SALES_FTP_PASSWORD); 
    	//临时文件存放目录
//        String localPath = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
    	//发送Sftp销售文件存放目录
        String localPath = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, ReportGlobalConstant.INTERF_SALE_SEND_PATH)
        		+ new SimpleDateFormat("yyyyMMdd").format(new Date())+ File.separator;
    	SftpUtil sftp = new SftpUtil(username, password, url, Integer.parseInt(port));  
        sftp.login();  
        //byte[] buff = sftp.download("/opt", "start.sh");  
        //System.out.println(Arrays.toString(buff));  
        File file = new File(allPath+fileName1);  
        InputStream is;
		try {
			is = new FileInputStream(file);
			sftp.upload(localPath, fileName1, is);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        sftp.logout();  
    } 
}
