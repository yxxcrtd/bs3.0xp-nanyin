package com.jixu.adapter.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

public class AdapterWebserviceIntefUtil {
	/** 配置文件map集合 */
	private static Map<String, Properties> map = new HashMap<String, Properties>();
	
	// 时间戳格式
    public static final String DATE_FORMAT_YYMMDD = "yyMMdd";
    public static final String DATE_FORMAT_SSSSSS = "yyyy-MM-dd HH:mm:ss.SSSSSS" ;
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
    public static final String TIME_FORMAT_HH_MM_SS = "HH:mm:ss";
	/**
	 * 获取指定配置的配置信息
	 * @param fileName
	 * 		配置文件名
	 * @return
	 * @throws IOException 
	 */
	public static Properties getProperties(String fileName) throws IOException {
		
		// 从map集合中获取配置属性
		Properties prop = map.get(fileName);
		// 指定配置文件没有装载的场合，重新装载
		if (null == prop) {
			prop = new Properties();
			// 读取配置文件信息
			InputStream in = AdapterWebserviceIntefUtil.class.getClassLoader().getResourceAsStream(fileName);
			// 加载配置文件信息
			prop.load(in);
			// 往map集合中追加配置文件属性
			map.put(fileName, prop);
		} 
		return prop;
	}
	/**
	 * 不足8为补0
	 * @param k
	 * @return
	 */
	public static String getUnique8Num(int k) {
		return String.format("%08d",k);
	}
	/**
	 * 不足10为补0
	 * @param k
	 * @return
	 */
	public static String getUnique10Num() {
		return String.format("%018d",0);
	}
	
    
    /**
     * 
     * <通过该方法向ESB推送文件>
     * @param localFile
     * @param remoteFile
     * @param tranCode
     * @param CFG
     * @param configPath
     * @return
     * @throws FtpException
     * @see
     * @since 1.0
     */
   /* public static String putFile(String localFile, String remoteFile, String tranCode, int CFG, String configPath)
        throws FtpException
      {
        FtpClientConfig config = FtpClientConfig.getInstance();
        config.loadConf(configPath);
        FtpPut put = new FtpPut(localFile, remoteFile, tranCode, CFG, config);
        String filePath = put.doPutFile();
        System.out.println(filePath);
        put.close();
        return filePath;
      }*/
      
    /**
     * 
     * <通过该方法获取ESB文件>
     * @param remoteFile 文件传输系统文件路径，文件提供方提前约定
     * @param localFile 文件调用放，系统文件保存路径
     * @param tranCode 文件传输代码
     * @param CFG 0不处理，1加密，2压缩
     * @param configPath FtpClientConfig.properties文件信息
     * @throws FtpException
     * @see
     * @since 1.0
     */
   /* public static boolean getFile(String remoteFile, String localFile, 
                           String tranCode, int CFG, String configPath)throws FtpException {
        FtpClientConfig config = FtpClientConfig.getInstance();
        config.loadConf(configPath);
        FtpGet get = new FtpGet(remoteFile, localFile, tranCode, CFG, config);
        boolean flag = get.doGetFile();
        get.close();
        return flag ;
    }*/
	
	
	
}
