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
}
