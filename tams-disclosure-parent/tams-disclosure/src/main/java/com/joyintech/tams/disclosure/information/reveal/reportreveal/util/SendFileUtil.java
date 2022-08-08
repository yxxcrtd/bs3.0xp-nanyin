package com.joyintech.tams.disclosure.information.reveal.reportreveal.util;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import org.slf4j.Logger;

import java.io.File;

import static org.slf4j.LoggerFactory.getLogger;

public class SendFileUtil {

    private static Logger logger = getLogger(SendFileUtil.class);
    /**
     * 生成文件，上传到服务器某个目录
     * @param fileName 发送的文件名
     * @param filePath 文件路径
     */
    public static boolean sendToFinanceByFtp(String fileName,String filePath){
        //从系统参数中获取
        String host = CacheUtil.getSysParam("SFTP_DIS_HOST");
        int port = Integer.parseInt(CacheUtil.getSysParam("SFTP_DIS_PORT"));
        String userName = CacheUtil.getSysParam("SFTP_DIS_USERNAME");
        String passWord = CacheUtil.getSysParam("SFTP_DIS_PASWORD");
        String remotePath = CacheUtil.getSysParam("SFTP_DIS_FPATH"); //上传文件至某个路径
        try{
            SftpUtil sftp=new SftpUtil();
            //待测试
            sftp.login(userName,passWord,host,port);
            logger.info("成功登录SFTP:" + host+":"+port);
            sftp.putFileDate(filePath+ File.separator+fileName,remotePath, DateUtil.getCurrentDate());
            sftp.close();
            logger.info(DateUtil.getCurrentDateTime()+"----------"+fileName+"上传成功");
            return true;
        }catch(Exception e){
            e.printStackTrace();
            logger.info(DateUtil.getCurrentDateTime()+"----------"+fileName+"上传失败");
            return false;
        }
    }
}
