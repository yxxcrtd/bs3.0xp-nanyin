package com.joyintech.tams.svreport.pbc.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;


/**
 * 描述：
 * 
 * @since 1.9.4
 * @version 1.9.4
 * @author wangbo
 * @创建时间：2016年11月2日
 * @修改记录：
 */
@Service
public class PbccomUtil {
	/* log4j */
	private static Logger log = Logger.getLogger(PbccomUtil.class);

	private static String file_name = "/pbccomConfig.properties";


	public String getConfig(String key) {
		String ret = "";
		Properties prop = new Properties();
		InputStream in = null;
		try {
			in = PbccomUtil.class .getResourceAsStream(file_name);
			prop.load(in); // /加载属性列表
			Iterator<String> it = prop.stringPropertyNames().iterator();
			while (it.hasNext()) {
				String key2 = it.next();
				if (key.equals(key2)) {
					ret = prop.getProperty(key);
					in.close();
					return ret.trim();
				}
			}
		} catch (Exception e) {
			log.error("", e);
		}finally {
			if (in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return ret.trim();
	}
}