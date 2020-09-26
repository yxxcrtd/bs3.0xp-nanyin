package com.joyintech.tams.valuation.report.common.infodisclosure.util;

import java.io.*;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.valuation.report.common.constant.DateUtil;

/**
 * 调用sh脚本生成xml文件
 * 
 * @author guozhongbao
 *
 */
public class ZipFileCmpUtil {
	
	/**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ZipFileCmpUtil.class);
	
	public static InputStream callCMD(RepReveal repReveal) {
		String shFilePath = CacheUtil.getSysParam("REPORT_FILE");
		String[] split = repReveal.getFilepath().split("/");
		String fileName = split[split.length - 1].split("_")[0];
		String cmd = "sh " + shFilePath + "FilesToXml.sh " + repReveal.getFilepath()
			+ " " + fileName + " " + DateUtil.format(new Date());
		logger.info(cmd);
		Process proess;
		try {
			proess = Runtime.getRuntime().exec(cmd);
			int waitFor = proess.waitFor();
			if(waitFor == 0){
				logger.info("xml文件生成成功");
				return proess.getInputStream();
			}
//			InputStreamReader ir = new InputStreamReader(is);
//			LineNumberReader input = new LineNumberReader(ir);
//			String line = "";
//			while((line = input.readLine()) != null) {
//				System.out.println(line);
//			}
		}catch(Exception e) {
			logger.info("Exception:" + e);
		}
		return null;
	}
	
	/**
	 * inputStream转file
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static File isToFile(InputStream inputStream) throws IOException{
	 	File tmp = File.createTempFile("lzq", ".tmp", new File("/"));
		OutputStream os = null;
		try {
			os = new FileOutputStream(tmp);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
		}
		catch (IOException e) {
			throw e;
		}
		finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					logger.error("", e);
				}
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					logger.error("", e);
				}
			}
		}


	    return tmp;
	 }
}
