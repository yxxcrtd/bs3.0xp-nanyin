package com.joyintech.tams.svreport.pbc.common.util;

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
		try {
			InputStream in = PbccomUtil.class .getResourceAsStream(file_name);
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
			in.close();
		} catch (Exception e) {
			log.error("", e);
		}
		return ret.trim();
	}
}