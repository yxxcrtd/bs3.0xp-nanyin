package com.joyintech.tams.disclosure.information.reveal.detailshow.service;

import com.joyintech.framework.annotation.LogMethod;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.oss.service.FileRemoteService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author 何阳阳
 * @date 2022/8/19 20:29
 */
@Service
public class RepEdit001Service {

    @Resource
    private FileRemoteService minioFileRemoteService;

    @LogMethod(pageId = "RepEdit001", elementId = "btnSaveReport")
    public int saveFile(HttpServletRequest request, String outputFilename ) throws IOException {
        int ret = 0;
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    // 取得当前上传文件的文件名称
                    String myFileName = StringUtil.trim(file.getOriginalFilename());
                    //String filePath = System.getProperty("user.home");
                    // 如果名称不为空,说明该文件存在，否则说明该文件不存在
                    if (StringUtil.isNotEmpty(myFileName)) {
                        // 文件存储到服务器之前调用监听器

//                        File localFile = new SafelyFile(outputFilename);
//                        File dir = localFile.getParentFile();
//                        if (!dir.exists()) {
//                            dir.mkdirs();
//                        }
//                        if (!localFile.exists()) {
//                            localFile.createNewFile();
//                        }
                        String remoteFilePath = minioFileRemoteService.uploadFile(outputFilename,file.getInputStream());
                        ret = StringUtil.isNotEmpty(remoteFilePath)?1:-1;
                        // file.transferTo(localFile);
//                        ret = FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(localFile));
                        //PoiUtils.copy(file.getName(), localFile.getName());

//                        UploadFile fileInfo = new UploadFile(myFileName, IDGenerator.generate(),
//                                localFile);
//                        fileInfo.setFileName(localFile.getPath());
//                        if(null != fileAttachmentProcess) {
//                            fileAttachmentProcess.upload(fileInfo);
//                        }
                    }
                }
            }
        }
//        progressBarService.updatePercent(pageId, ProgressBarService.FILE_UPLOAD_ID, 0.5D, true);
        return  ret;
    }
}
