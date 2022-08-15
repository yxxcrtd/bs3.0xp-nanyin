package com.joyintech.tams.common.oss.service.impl;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.common.oss.config.MinioTemplate;
import com.joyintech.tams.common.oss.service.FileRemoteService;
import com.joyintech.tams.systemtools.dao.JoyinFSMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

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
    @Autowired
    private JoyinFSMapper joyinFSMapper;

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

    @Override
    public byte[] getFile2ByteArray(String filePath) {
        if(logger.isDebugEnabled())
            logger.debug("读取文件字节流,文件为:{}",filePath);

        if(StringUtils.isBlank(filePath)){
            return null;
        }
        if(filePath.startsWith("http:")||filePath.startsWith("https:")){
            return minioTemplate.downLoadFile(filePath);
        }else{
            FileInputStream fis = null;
            ByteArrayOutputStream bos = null;
            try{
                fis = new FileInputStream(filePath);
                bos = new ByteArrayOutputStream();
                byte[] buff = new byte[1024];
                int rc = 0;
                while (true) {
                    try {
                        if (!((rc = fis.read(buff, 0, 1024)) > 0))
                            break;
                    } catch (IOException e) {
                        logger.error("读取文件流异常", e);
                    }
                    bos.write(buff, 0, rc);
                }
                return bos.toByteArray();
            }catch(Exception e){
                logger.error("读取本地文件报错",e);
            }finally {
                if(bos!=null){
                    try {
                        bos.close();
                    }catch(IOException e){
                        logger.error("关闭输出字节流异常",e);
                    }
                }
                if(fis != null){
                    try{
                        fis.close();
                    }catch(IOException e){
                        logger.error("关闭文件输入流异常",e);
                    }
                }
            }
        }
        return null;
    }

    @Override
    @DataSource(DataSourceType.OTHER)
    public UploadFile uploadFile(UploadFile file, InputStream is) {
        File f;
        String filePath = file.getTargetFile().getPath();
        //针对操作系统不同分隔符进行统一替换
        String newfilePath = filePath.replace(":\\","://");
        newfilePath = newfilePath.replace("\\","/");
        String realPath = minioTemplate.uploadFile(null,newfilePath,false,is);
        if(logger.isDebugEnabled())
            logger.debug("文件上传MINIO成功,{}",filePath);
        f = new SafelyFile(realPath);
        file.setTargetFile(f);
        SysUploadFileInfo systemUploadFile = new SysUploadFileInfo();
        systemUploadFile.setSeqNo(file.getFileNo());
        String fileName = FileNameUtil.formatFileName(file.getTargetFile().getName());
        if(StringUtils.isNotBlank(file.getSrcName())){
            fileName = file.getSrcName();
        }
        systemUploadFile.setFileName(fileName);
        systemUploadFile.setPath(realPath);
        file.setFileName(fileName);
        joyinFSMapper.insert(systemUploadFile);
        return file;
    }

    @Override
    @DataSource(DataSourceType.OTHER)
    public UploadFile tempFileCopy(String tempFilePath, UploadFile file) {
        if(StringUtils.isBlank(tempFilePath) || file ==null){
            throw new IllegalArgumentException("临时文件路径或目标文件为空");
        }
        byte[] bytes = getFile2ByteArray(tempFilePath);
        if(bytes == null){
            throw new NullPointerException("源文件未读取到内容");
        }
        ByteArrayInputStream bis  = null;
        try{
            bis = new ByteArrayInputStream(bytes);
            UploadFile newFile = uploadFile(file,bis);

            return newFile;
        }catch(Exception e){
            logger.error("上传新文件发生异常",e);
        }finally {
            if(bis != null){
                try{
                    bis.close();
                }catch (IOException e){
                    logger.error("关闭字节流异常",e);
                }
            }
        }
        return null;
    }

    @Override
    @DataSource(DataSourceType.OTHER)
    public byte[] getFileBytesByUploadFileNo(String fileNo) {
        SysUploadFileInfo file = null;
        file = joyinFSMapper.selectByPrimaryKey(fileNo);
        if (file != null) {
            return getFile2ByteArray(file.getPath());
        }
        return null;
    }

    @Override
    @DataSource(DataSourceType.OTHER)
    public UploadFile getUploadFileByFileNo(String fileNo) {
        SysUploadFileInfo file = null;
        file = joyinFSMapper.selectByPrimaryKey(fileNo);
        if (file != null) {
            UploadFile uploadFile = new UploadFile(null, fileNo, new SafelyFile(file.getPath()));
            uploadFile.setFileName(file.getFileName());
            return uploadFile;
        }
        return null;
    }

    @Override
    /**
     * @Author ZY22350
     * @Description //判断远程文件是否存在，此处如果是http:远程文件默认存在
     * @Date 14:06 2022/8/12
     * @Param [filePath]
     * @return boolean
     **/
    public boolean remoteFileExists(String filePath) {

        return minioTemplate.checkFileIsExist(filePath);
    }
}
