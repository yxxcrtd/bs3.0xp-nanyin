package com.joyintech.tams.common.oss.service.impl;

import com.joyintech.tams.common.oss.config.MinioTemplate;
import com.joyintech.tams.common.oss.service.FileRemoteService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * ClassName MinioFileRemoteService
 * Description 使用minio进行远程文件对象服务
 *
 * @Author ZY22350
 * @Date 2022/8/3 9:39
 * @Version 1.0.0
 **/

@Service("minioFileRemoteService")
public class MinioFileRemoteService implements FileRemoteService {
    private static final Logger logger = LoggerFactory.getLogger(MinioFileRemoteService.class);

    @Autowired
    private MinioTemplate minioTemplate;

    @Override
    public String uploadFile(File file) {
        if(file == null){
            throw new NullPointerException("待上传文件为空");
        }
        String filePathName = file.getAbsolutePath();
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(file);
            return uploadFile(filePathName,fis);
        }catch(Exception e){
            logger.error("file:{},上传发生异常",filePathName,e);

        }finally{
            if(fis != null){
                try {
                    fis.close();
                }catch(IOException e){
                    logger.error("关闭流发生异常",e);
                }
            }
        }
        return null;

    }

    @Override
    public String uploadFile(String filePathName, InputStream is) {
        if(StringUtils.isBlank(filePathName)){
            throw new NullPointerException("文件全路径名为空");
        }
        if(is == null){
            throw new NullPointerException("输入流为空");
        }
        return uploadFile(filePathName,null,is);
    }

    @Override
    public String uploadFile(String fileName, String prefix, InputStream is) {
        if(logger.isDebugEnabled())
            logger.debug("minio操作上传文件，文件名:{}",fileName);
        return minioTemplate.uploadFile(prefix, fileName, false, is);
    }

    @Override
    public boolean deleteFile(String pathFile) {
        if(logger.isDebugEnabled())
            logger.debug("minio操作删除文件:{}",pathFile);
        return minioTemplate.delete(pathFile);
    }

    @Override
    public byte[] downloadFile(String pathFile) {
        if(logger.isDebugEnabled())
            logger.debug("minio操作下载文件，{}",pathFile);
        return minioTemplate.downLoadFile(pathFile);
    }

    @Override
    public String getRemoteFilePathName(String localPathFile) {
        if(logger.isDebugEnabled())
            logger.debug("minio远程获取本地文件{},远程文件路径",localPathFile);
       String remoteFilePathName = minioTemplate.convertFilePathName(null,localPathFile,false);
        if(logger.isDebugEnabled())
            logger.debug("minio获取远程文件全路径名为:{}",remoteFilePathName);
       return remoteFilePathName;
    }

    @Override
    public String getSimpleFilePathName(String remoteFilePath) {
        return minioTemplate.convertSimpleFilePathName(remoteFilePath);
    }
}
