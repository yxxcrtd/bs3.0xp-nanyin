/*
 * Copyright (C),  兆尹科技有限公司
 * FileName: FileRemoteService.java
 * Author:   Administrator
 * Date:     2022年8月2日 下午2:11:27
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.joyintech.tams.common.oss.service;

import com.joyintech.framework.base.dto.UploadFile;

import java.io.File;
import java.io.InputStream;

/**
 * 〈文件远程服务处理服务〉<br> 
 * 〈功能详细描述〉
 *
 * @author ZY22350
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface FileRemoteService {
    /**
     * @Author ZY22350
     * @Description //上传文件
     * @Date 9:43 2022/8/3
     * @Param [file-待上传文件]
     * @return java.lang.String
     **/
    String uploadFile(File file);
    /**
     * @Author ZY22350
     * @Description //上传文件
     * @Date 9:44 2022/8/3
     * @Param [filePathName-文件路径全名, is-文件输入流]
     * @return java.lang.String
     **/
    String uploadFile(String filePathName,InputStream is);
    /**
     * @Author ZY22350
     * @Description //上传文件
     * @Date 9:32 2022/8/3
     * @Param [fileName-文件名称, prefix-文件路径, is-输入流]
     * @return java.lang.String 文件远程全路径
     **/
    String uploadFile(String fileName,String prefix,InputStream is);
    /**
     * @Author ZY22350
     * @Description //删除文件，通过文件全路径名
     * @Date 9:37 2022/8/3
     * @Param [pathFile-文件远程服务器全路径名]
     * @return boolean
     **/
    boolean deleteFile(String pathFile);
    /**
     * @Author ZY22350
     * @Description //下载文件，通过文件全路径名
     * @Date 9:38 2022/8/3
     * @Param [pathFile-文件全路径名]
     * @return byte[]-返回字节流
     **/
    byte[] downloadFile(String pathFile);

    /**
     * @Author ZY22350
     * @Description //通过本地文件名获取文件服务全名
     * @Date 13:57 2022/8/3
     * @Param [localPathFile]
     * @return java.lang.String
     **/
    String getRemoteFilePathName(String localPathFile);
    /**
     * @Author ZY22350
     * @Description //远程文件名获取简短文件路径
     * @Date 17:03 2022/8/4
     * @Param [remoteFilePath]
     * @return java.lang.String
     **/
    String getSimpleFilePathName(String remoteFilePath);
    /**
     * @Author ZY22350
     * @Description //获取文件变成字节流
     * @Date 11:34 2022/8/5
     * @Param [filePath]
     * @return byte[]
     **/
    byte[] getFile2ByteArray(String filePath);
    /**
     * @Author ZY22350
     * @Description //上传文件，上传的文件会同步进入SysUploadFileInfo表，通过uploadFile，此处需要调用方组装UploadFile,其中srcName为相关报表展示文件名,
     * File主要为了获取实际需要上传的文件路径（<b>此处使用相对路径，切记</b>),fileNo为唯一值，作为文件的 唯一对象存储</br>
     * 返回对象中的FileName如果传输的file中有srcName，返回此处名称，如果没有，以targetFile的名称添加时间戳进行返回
     * @Date 11:41 2022/8/5
     * @Param [file-UploadFile对象,is-实际输入流]
     * @return com.joyintech.framework.base.dto.UploadFile
     **/
    UploadFile uploadFile(UploadFile file,InputStream is);
    /**
     * @Author ZY22350
     * @Description //临时文件拷贝到UploadFile需要上传的对象中，上传的文件会同步进入SysUploadFileInfo表
     * @Date 11:49 2022/8/5
     * @Param [tempFilePath-临时文件地址，此处使用绝对地址-要求临时文件存在，若不存在报异常, file-uploadFile对象]
     * @return com.joyintech.framework.base.dto.UploadFile
     **/
    UploadFile tempFileCopy(String tempFilePath,UploadFile file);
    /**
     * @Author ZY22350
     * @Description //通过fileNo获取文件字节流，此处fileNo查询SysUploadFileInfo
     * @Date 11:52 2022/8/5
     * @Param [fileNo]
     * @return byte[]
     **/
    byte[] getFileBytesByUploadFileNo(String fileNo);
    /**
     * @Author ZY22350
     * @Description //通过fielNo，获取UploadFile对象【此处获取的File本地不一定存在】
     * @Date 13:23 2022/8/5
     * @Param [fileNo]
     * @return com.joyintech.framework.base.dto.UploadFile
     **/
    UploadFile getUploadFileByFileNo(String fileNo);


}
