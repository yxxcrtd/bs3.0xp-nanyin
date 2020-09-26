/*
 * 文件名：FileDownloadController.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：鍒樻憾瀹?-Dosgi.requiredJavaVersion=1.8
 * 修改时间：2016年12月20日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.common.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.controller.FileDownloadController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;


/**
 * 文件下载controller
 * 
 * @author 刘溶容
 * @version 1.0 2016年12月20日
 * @see FileDownloadController
 * @since 1.0
 */
@Controller
@RequestMapping("/svreport/fileProcess")
public class ChbFileDownloadController extends BaseController {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ChbFileDownloadController.class);

    /**
     * 文件下载
     * 
     * @param response 请求参数
     * @throws IOException 异常
     */
    @RequestMapping(value = "/download")
    @ResponseBody
    public void downloadFile(HttpServletResponse response){
        FileInputStream fits=null;
        InputStream fis=null;
        OutputStream toClient=null;
        try{
        Map<String, Object> paramMap = RequestUtil.getPageParam();
        String path = (String)paramMap.get("path");
        File file = new File(path);
        String filename = file.getName();
        fits=new FileInputStream(path);
        fis = new BufferedInputStream(fits);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fits.close();
        fis.close();
        response.reset();
        // filename = new String(filename.getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
        // 去掉filename的时间戳 add by 何阳阳 2017/01/04 start
        int index = filename.lastIndexOf("_");
        String[] names = StringUtil.split(filename, ".");
        if (index > 0 && names.length == 2) {
            // if(filename.substring(index + 1).length() == 18 + names[1].length()) {
            filename = filename.substring(0, index) + "." + names[1];
            // }
        }
        // add by 何阳阳 2017/01/04 end
        response.addHeader("Content-Disposition",
            "attachment;filename=" + java.net.URLEncoder.encode(filename, "UTF-8"));
        response.addHeader("Content-Length", "" + file.length());
        toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
        }
        catch(IOException e){ 
//            e.printStackTrace();
            logger.error(e.getMessage(),e);
        }
        finally{
          if(fits!=null){
              try {
                fits.close();
            }
            catch (IOException e) {
//                e.printStackTrace();
                logger.error(e.getMessage(),e);
            }
          }  
          if(fis!=null){
              try {
                  fis.close();
              }
              catch (IOException e) {
//                  e.printStackTrace();
                  logger.error(e.getMessage(),e);
              }
          }  
          if(toClient!=null){
              try {
                  toClient.close();
              }
              catch (IOException e) {
//                  e.printStackTrace();
                  logger.error(e.getMessage(),e);
              }
          }  
        }
    }
}
