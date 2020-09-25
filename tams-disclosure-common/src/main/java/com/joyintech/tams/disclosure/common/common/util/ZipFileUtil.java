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
        try { 
                proess = Runtime.getRuntime().exec( message );  
                InputStreamReader ir = new InputStreamReader(proess.getInputStream()); 
                LineNumberReader input = new LineNumberReader(ir); 
                String line = ""; 
                while((line = input.readLine()) != null){ 
                    System.out.println(line); 
                } 
                return line; 
            } catch (IOException e) { 
                logger.info("fileToxml IOException："+e); 
            } 
        return null; 

    }
}
