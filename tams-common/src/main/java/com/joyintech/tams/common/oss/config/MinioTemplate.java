/*
 * Copyright (C),  兆尹科技有限公司
 * FileName: MinioTemplate.java
 * Author:   Administrator
 * Date:     2022年8月2日 下午1:59:55
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.joyintech.tams.common.oss.config;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 〈Minio操作模板〉<br>
 * 〈功能详细描述〉
 *
 * @author ZY22350
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MinioTemplate {

    private static final Logger logger = LoggerFactory.getLogger(MinioTemplate.class);

    private MinioClient minioClient;
    private MinioProperties minioProperties;
    
    private final String separator = "/";

    public MinioTemplate(MinioProperties minioProperties) {
        this.minioProperties = minioProperties;
        this.minioClient = MinioClient.builder()
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .endpoint(minioProperties.getEndpoint()).build();
    }

    /**
     * 
     * 功能描述: <br>
     * 〈组装带当前日期的路径文件〉
     *
     * @param dirPath
     * @param filename file.jpg
     * @return dirPath/yyyy/MM/dd/file.jpg
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private String builderDateFilePath(String dirPath, String filename) {
        StringBuilder stringBuilder = new StringBuilder(50);
        if (!StringUtils.isEmpty(dirPath)) {
            stringBuilder.append(dirPath);
            if(!StringUtils.endsWith(dirPath, separator)){
                stringBuilder.append(separator);
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String todayStr = sdf.format(new Date());
        stringBuilder.append(todayStr);
        if(!StringUtils.endsWith(filename, separator)){
            stringBuilder.append(separator);
        }
        stringBuilder.append(filename);
        return stringBuilder.toString();
    }

    /**
     * 
     * 功能描述: <br>
     * 〈简单组装路径〉
     *
     * @param dirPath
     * @param filename
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private String builderFilePath(String dirPath, String filename) {
        StringBuilder stringBuilder = new StringBuilder(50);
        if (!StringUtils.isEmpty(dirPath)) {
            stringBuilder.append(dirPath);
            if(!StringUtils.endsWith(dirPath, separator)){
                stringBuilder.append(separator);
            }
        }
        //去掉文件前文件夹分隔符
        if(StringUtils.startsWith(filename, separator)){
            filename = filename.substring(1);
        }
        stringBuilder.append(filename);
        return stringBuilder.toString();
    }

    /**
     * 
     * 功能描述: <br>
     * 〈上传文件，目前针对图片类的jpg\tiff\gif\jfif\png\tif\ico\jpeg\wbmp\fax\net\jpe\rp结尾的实现了bucket为public的预览功能〉
     *
     * @param prefix 文件路径，可以为空
     * @param fileName 文件名-注意此处为不带路径的文件名（如：test.jpg、demo.docx等）
     * @param autoDatePath 是否自动填充当前日期到路径
     * @param is 文件流
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public String uploadFile(String prefix, String fileName, Boolean autoDatePath, InputStream is) {
        if (logger.isDebugEnabled()) {
            logger.debug("minio上传文件开始，文件名:{}", fileName);
        }
        if (StringUtils.isBlank(fileName) || StringUtils.indexOf(fileName, '.') == -1) {
            throw new IllegalArgumentException("文件名格式不正确");
        }
        if (is == null) {
            throw new IllegalArgumentException("输入流为空");
        }
        String filePath = "";
        if (autoDatePath) {
            filePath = builderDateFilePath(prefix, fileName);
        } else {
            filePath = builderFilePath(prefix, fileName);
        }
        try {
            PutObjectArgs putObjectArgs = PutObjectArgs.builder().object(filePath)
                    .contentType(ViewContentTypeEnum.getContentType(fileName)).bucket(minioProperties.getBucket())
                    .stream(is, is.available(), -1).build();
            minioClient.putObject(putObjectArgs);
            StringBuilder urlPath = new StringBuilder(minioProperties.getReadPath());
            urlPath.append(separator + minioProperties.getBucket());
            if(!filePath.startsWith(separator)) {
                urlPath.append(separator);
            }
            urlPath.append(filePath);
            return urlPath.toString();
        } catch (Exception ex) {
            logger.error("上传文件异常", ex);
            throw new RuntimeException("上传文件失败",ex);
        }
    }

    /**
     * 
     * 功能描述: <br>
     * 〈删除文件,按照文件路径进行删除〉
     *
     * @param pathUrl 文件全路径
     * @return 成功返回true，异常返回false
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public boolean delete(String pathUrl) {
        if (logger.isDebugEnabled()) {
            logger.debug("minio删除文件开始，文件名:{}", pathUrl);
        }
        //针对转化文件夹分隔符进行转化处理
        pathUrl = systemDiffConvertPath(pathUrl);
        String key = pathUrl.replace(minioProperties.getEndpoint() + "/", "");
        int index = key.indexOf(separator);
        String bucket = key.substring(0, index);
        String filePath = key.substring(index + 1);
        // 删除Objects
        RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder().bucket(bucket).object(filePath).build();
        try {
            minioClient.removeObject(removeObjectArgs);
            return true;
        } catch (Exception e) {
            logger.error("Minio删除文件异常，pathUrl:{}", pathUrl);
        }
        return false;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈下载文件-通过文件全路径〉
     *
     * @param pathUrl 文件全路径
     * @return 文件字节流
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public byte[] downLoadFile(String pathUrl) {
        if (logger.isDebugEnabled()) {
            logger.debug("minio下载文件开始，文件名:{}", pathUrl);
        }
        //针对转化文件夹分隔符进行转化处理
        pathUrl = systemDiffConvertPath(pathUrl);
        String key = pathUrl.replace(minioProperties.getEndpoint() + "/", "");
        int index = key.indexOf(separator);
        String bucket = key.substring(0, index);
        String filePath = key.substring(index + 1);
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            try {
                inputStream = minioClient.getObject(GetObjectArgs.builder().bucket(bucket).object(filePath).build());
            } catch (Exception e) {
                logger.error("Minio下载文件异常。pathUrl:{}", pathUrl);
                return null;
            }
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int rc = 0;
            while (true) {
                try {
                    if (!((rc = inputStream.read(buff, 0, 100)) > 0))
                        break;
                } catch (IOException e) {
                    logger.error("读取文件流异常", e);
                }
                byteArrayOutputStream.write(buff, 0, rc);
            }
            return byteArrayOutputStream.toByteArray();
        } finally {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    logger.error("关闭写入流异常", e);
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("关闭读取流异常", e);
                }
            }
        }
    }
    /**
     * 
     * 功能描述: <br>
     * 〈上传文件，只有流，返回对象objectKey〉
     *  此方法为使用系统默认配置的minio的bucket作为对象，要求实际文件的类型需要业务系统自身进行处理
     * @param is
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public String uploadObject(InputStream is){
        String uuid = UUID.randomUUID().toString().replace("-","");
        if (logger.isDebugEnabled()) {
            logger.debug("minio上传对象开始，对象id:{}", uuid);
        }
        try{
            PutObjectArgs putObjectArgs = PutObjectArgs.builder().object(uuid)
                    .contentType(ViewContentTypeEnum.DEFAULT.getType()).bucket(minioProperties.getBucket())
                    .stream(is, is.available(), -1).build();
            minioClient.putObject(putObjectArgs);
            return uuid;
        }catch(Exception e){
            logger.error("上传对象{}异常",uuid,e);
            throw new RuntimeException("Minio上传对象异常",e);
        }
    }
    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param objectKey
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public boolean deleteObject(String objectKey){
        if(StringUtils.isBlank(objectKey)){
            throw new IllegalArgumentException("objectKey为空");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("minio删除对象开始，对象id:{}", objectKey);
        }
     // 删除Objects
        RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder().bucket(minioProperties.getBucket()).object(objectKey).build();
        try {
            minioClient.removeObject(removeObjectArgs);
            return true;
        } catch (Exception e) {
            logger.error("Minio删除文件异常，objectKey:{}", objectKey);
        }
        return false;
        
    }
    /**
     * 
     * 功能描述: <br>
     * 〈下载Object〉
     *
     * @param objectKey
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public byte[] downLoadObject(String objectKey) {
        if(StringUtils.isBlank(objectKey)){
            throw new IllegalArgumentException("objectKey为空");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("minio下载对象开始，对象id:{}", objectKey);
        }
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            try {
                inputStream = minioClient.getObject(GetObjectArgs.builder().bucket(minioProperties.getBucket()).object(objectKey).build());
            } catch (Exception e) {
                logger.error("Minio下载文件异常.ObjectKey:{}",objectKey);
            }

            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int rc = 0;
            while (true) {
                try {
                    if (!((rc = inputStream.read(buff, 0, 100)) > 0))
                        break;
                } catch (IOException e) {
                    logger.error("读取文件流异常", e);
                }
                byteArrayOutputStream.write(buff, 0, rc);
            }
            return byteArrayOutputStream.toByteArray();
        } finally {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    logger.error("关闭写入流异常", e);
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("关闭读取流异常", e);
                }
            }
        }
    }
    /**
     * @Author ZY22350
     * @Description //通过文件相关名称获取远程服务生成的文件全路径名
     * @Date 13:55 2022/8/3
     * @Param [prefix, fileName, autoDatePath]
     * @return java.lang.String
     **/
    public String convertFilePathName(String prefix,String fileName,boolean autoDatePath){
        if(!StringUtils.isBlank(fileName)){
            String filePath = "";
            if (autoDatePath) {
                filePath = builderDateFilePath(prefix, fileName);
            } else {
                filePath = builderFilePath(prefix, fileName);
            }
            StringBuilder urlPath = new StringBuilder(minioProperties.getReadPath());
            urlPath.append(separator + minioProperties.getBucket());
            if(!filePath.startsWith(separator)) {
                urlPath.append(separator);
            }
            urlPath.append(filePath);
            return urlPath.toString();
        }
        return null;
    }
    /**
     * @Author ZY22350
     * @Description //获取文件对象名称
     * @Date 17:06 2022/8/4
     * @Param [remoteFileName]
     * @return java.lang.String
     **/
    public String convertSimpleFilePathName(String remoteFileName){
        remoteFileName = systemDiffConvertPath(remoteFileName);
        String key = remoteFileName.replace(minioProperties.getEndpoint() + "/", "");
        int index = key.indexOf(separator);
        String bucket = key.substring(0, index);
        String filePath = key.substring(index + 1);
        return filePath;
    }
    /**
     * @Author ZY22350
     * @Description //不同操作系统文件转化后路径问题处理
     * @Date 21:32 2022/8/4
     * @Param [filePath]
     * @return java.lang.String
     **/
    private String systemDiffConvertPath(String filePath){
        //针对转化文件夹分隔符进行转化处理
        String pathUrl = filePath.replace(":\\","://");
        pathUrl = pathUrl.replace("\\","/");
        if(pathUrl.indexOf("://") < 0){ //解决linux使用File获取的名称http://变成http:/问题
            pathUrl.replace(":/","://");
        }
        return pathUrl;
    }
}
