package com.joyintech.tams.disclosure.common.common.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * excel操作
 *
 * @author daiyue
 * @version 1.0 2016年12月1日
 * @see ZipFileUtil
 * @since 1.0
 */
public class ZipFileUtil {
    
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ZipFileUtil.class);


    
//    注：OUTFILENAME是文件绝对路径，shellPath 是FilesToXml.sh 所在目录
//    String cmd = "sh " + shellPath + "FilesToXml.sh " + OUTFILENAME; 
//    logger.info(cmd); 
//    ZipFileCmp.callCMD(cmd);

    public static String callCMD( String message ) { 
        Process proess; 
        InputStreamReader ir = null;
        LineNumberReader input = null;
        try { 
                proess = Runtime.getRuntime().exec( message );  
                ir = new InputStreamReader(proess.getInputStream()); 
                input = new LineNumberReader(ir); 
                String line = ""; 
                while((line = input.readLine()) != null){ 
//                    System.out.println(line);
                    logger.info(line);
                }
                input.close();
                return line; 
            } catch (IOException e) { 
                logger.info("fileToxml IOException："+e); 
            } finally {
            	try {
					input.close();
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
            	try {
					ir.close();
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
            }
        return null; 

    }
}
