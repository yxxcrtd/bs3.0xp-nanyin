package com.joyintech.tams.disclosure.information.config.repreportcheck.service;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.tams.common.oss.service.impl.MinioFileRemoteService;
import com.joyintech.tams.utils.poi.PoiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

@Service
public class ReportCheck003Service {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ReportCheck002Service.class);

    @Autowired
    private MinioFileRemoteService minioFileRemoteService;

    @Autowired
    private FileAttachmentProcess fileAttachmentProcess;

    public void previewReport(String fileNo, HttpServletResponse response) {
        String imagePath = "";
        ByteArrayInputStream bis = null;
        try {
            UploadFile attachment = fileAttachmentProcess.fetch(fileNo);

            // 目标文件
            File targetFile = attachment.getTargetFile();
            //将生成的文件作为输入，转换成html后，发送到response
            byte[] bytes = minioFileRemoteService.getFile2ByteArray(targetFile.getPath());
            if(bytes != null) {
                bis = new ByteArrayInputStream(bytes);
                PoiUtils.docxToHtml(bis,response.getOutputStream(),imagePath);
            }
        } catch (Exception e ) {
            logger.error("查询数据错误。",e);
            e.printStackTrace();
        } finally {
            if(bis != null){
                try{
                    bis.close();
                }catch(IOException e){
                    logger.error("关闭输入字节流异常",e);
                }
            }
        }

    }
}
