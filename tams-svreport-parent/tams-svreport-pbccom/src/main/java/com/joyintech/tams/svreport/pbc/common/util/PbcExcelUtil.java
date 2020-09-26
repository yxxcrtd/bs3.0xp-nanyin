package com.joyintech.tams.svreport.pbc.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.framework.util.FileNameUtil;

import net.sf.jxls.transformer.XLSTransformer;

public class PbcExcelUtil {
	
	public static File createFile(Map<Object, Object> data, String exceltemplate, String flieName) {
		File depositfile = null;
		InputStream fis = null;
		FileOutputStream fos = null;
		try {

			// 关联模板
			XLSTransformer transformer = new XLSTransformer();

			String template = null;
			// 模板路径pbc/
			template = FileNameUtil.getTplFile("pbc/" + exceltemplate + ".xls");
			// //windows环境地址
			// if(File.separator.equals("\\")){
			// template = getXmlPath(exceltemplate+".xls");
			// }
			// //linux环境地址
			// if(File.separator.equals("/")){
			// template = getXmlPathLinux(exceltemplate+".xls");
			// }
			// 传放地址
			String depositfilePath = SvrDataUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);

			depositfile = new File(depositfilePath + File.separator + flieName);
			if (!depositfile.getParentFile().exists()) {
				depositfile.getParentFile().mkdirs();
			}

			// 缓存区，一次性读取1024字节
			byte[] buffer = new byte[1024];
			String temp = depositfilePath + "ptl.xls";
			// 原文件
			File srcFile = new File(template);
			// 目标文件
			File destFile = new File(temp);
			if (srcFile.exists()) {
				fis = new FileInputStream(srcFile);
			} else {
				fis = PbcExcelUtil.class.getResourceAsStream("/tpl/" +"pbc/" + exceltemplate + ".xls");
			}
			fos = new FileOutputStream(destFile);
			while (true) {
				// 实际读取的长度是 actuallyReaded,有可能小于1024
				int actuallyReaded = fis.read(buffer);
				// -1表示没有可读的内容了
				if (-1 == actuallyReaded)
					break;
				fos.write(buffer, 0, actuallyReaded);
				fos.flush();
			}
			transformer.transformXLS(temp, data, depositfilePath + File.separator + flieName);
			if (destFile.exists()) {
				destFile.delete();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally{
            try {
            	if(fis !=null)
            	fis.close();
            } catch (IOException e) {
            	e.printStackTrace();
             }
            try {
            	if(fos !=null)
            	fos.close();
            } catch (IOException e) {
            	e.printStackTrace();
             }
         }
		return depositfile;
	}
	
	public static String getXmlPath(String filename) {
		try {
			String path = Thread.currentThread().getContextClassLoader()
					.getResource("").toURI().getPath();
			path = path.replace('/', '\\'); // 将/换成\
			path = path.replace("file:", ""); // 去掉file:
			path = path.substring(1); // 去掉第一个\,如 \D:\JavaWeb...
			path += "tpl\\" + filename;
			path = path.replace("report-batch", "report-parent\\report");
			return path;
		} catch (URISyntaxException e) {
			return null;
		}
	}

	public static String getXmlPathLinux(String filename) {
		try {
			String path = Thread.currentThread().getContextClassLoader()
					.getResource("").toURI().getPath();
			path += "tpl/" + filename;
			path = path.replace("file:", ""); // 去掉file:
			path = path.replace("report-batch", "report-parent/report");
			return path;
		} catch (URISyntaxException e) {
			return null;
		}
	}
	
	/** 
     * 删除指定的Sheet 
     * @param targetFile  目标文件 
     * @param sheetName   Sheet名称 
     */ 
    public static void deleteSheet(File targetFile,List<String> sheetNameList) { 
        try (FileInputStream fis = new FileInputStream(targetFile); 
                HSSFWorkbook wb = new HSSFWorkbook(fis);){ 
            if(sheetNameList != null && sheetNameList.size() > 0){
            	for(String sheetName : sheetNameList){
                	//删除Sheet 
                	wb.removeSheetAt(wb.getSheetIndex(sheetName)); 
                }
                fileWrite(targetFile, wb); 
                fis.close(); 
            }
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    }
    
    /** 
     * 写隐藏/删除后的Excel文件 
     * @param targetFile  目标文件 
     * @param wb          Excel对象 
     * @throws Exception 
     */ 
    public static void fileWrite(File targetFile,HSSFWorkbook wb) throws Exception{
        FileOutputStream fileOut = new FileOutputStream(targetFile); 
        wb.write(fileOut); 
        fileOut.flush(); 
        fileOut.close(); 
    }
    
    /**
     * map的key转换为string
     * @param data
     * @return
     */
 	public static Map<String,Object> convertMapKey2String(Map<Object, Object> data){
 		Map<String,Object> map = new HashMap<String, Object>(); 
 		for(Object key: data.keySet()) {
 			map.put(String.valueOf(key), data.get(key));
 		}
 		return map;
 	}

}
