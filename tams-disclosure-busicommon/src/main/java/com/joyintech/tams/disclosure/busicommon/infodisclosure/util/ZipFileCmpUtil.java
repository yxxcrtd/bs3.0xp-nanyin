package com.joyintech.tams.disclosure.busicommon.infodisclosure.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;

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
//		String[] split = null;
//		if(StringUtil.isNotEmpty(repReveal.getFilepath())) {
//			split = repReveal.getFilepath().split("/");
//		}
//		String temp = null;
//		if(StringUtil.isNotEmpty(split) && split.length > 0) {
//			temp = split[split.length - 1];
//		}
//		List<String> tempList = StringUtil.splitToList("_", temp);
//		if(StringUtil.isNotEmpty(tempList) && tempList.size() > 1) {
//			tempList.remove(tempList.size() - 1);
//		}
		String fileName = repReveal.getFilename();
		String cmd = "sh " + shFilePath + "FilesToXml.sh " + repReveal.getFilepath()
			+ " " + fileName + " " + DateUtil.format(new Date()) + " " + repReveal.getId();
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
	public static File isToFile(InputStream inputStream) {
		File tmp = null;
		OutputStream os = null;
		try {
			tmp = File.createTempFile("lzq", ".tmp", new File("/"));
			 os = new FileOutputStream(tmp);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(os!=null)
				{
					os.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	    return tmp;
	 }
}
