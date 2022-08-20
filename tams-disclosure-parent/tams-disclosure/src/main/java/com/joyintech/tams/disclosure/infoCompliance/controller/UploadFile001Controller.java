package com.joyintech.tams.disclosure.infoCompliance.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.listener.FileUploadListener;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.disclosure.infoCompliance.dao.InfoComplianceMapper;
import com.joyintech.tams.disclosure.infoCompliance.dto.UploadFileDto;
import com.joyintech.tams.disclosure.infoCompliance.service.InfoCompliance002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
@PageId("UploadFile001")
@RequestMapping("/disclosure/infoCompliance/uploadFile001")
public class UploadFile001Controller extends BaseController {

    @Autowired(
            required = false
    )
    @Qualifier("commonProgressBarService")
    private ProgressBarService progressBarService;

    @Autowired(
            required = false
    )
    private List<FileUploadListener> listeners;

    @Autowired(required = false)
    private FileAttachmentProcess fileAttachmentProcess;

    @Autowired
    private InfoCompliance002Service infoCompliance002Service;

    @Autowired
    private InfoComplianceMapper infoComplianceMapper;


    @Override
    public String init(Model model) {
        String businessId = getParam("businessId");
        String fileType = getParam("fileType");
        //modify by yanya on 20210824 增加是否复制的判定
        String COPY_FLAG = getParam("COPY_FLAG");

        if(StringUtil.isNotEmpty(businessId)){
            Map<String,String> param = new HashMap<String,String>();
            param.put("businessId",businessId);
            param.put("fileType",fileType);
            List<UploadFileDto> files = infoCompliance002Service.getFileByBusinessCode(param);
            model.addAttribute("attachmentFiles", JsonUtils.object2Json(files));
            if(null != fileAttachmentProcess && !StringUtil.equals(DictCodeList.是否_是.getValue(), COPY_FLAG)) {
                List<UploadFile> files2 = fileAttachmentProcess.fetchByBusinessCode(businessId);
                for(UploadFileDto dto:files){
                    for(UploadFile dto2:files2){
                        if(dto.getSeqNo().equals(dto2.getFileNo())){
                            dto.setSize(dto2.getSize());
                        }
                    }
                }
            }
            model.addAttribute("attachmentFiles", JsonUtils.object2Json(files));
        }
        model.addAttribute("businessId",businessId);
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping(value = "/getFilePath", method = RequestMethod.POST)
    String getFilePath(){
        String fileNo = this.getParam("fileNo");
        //通过主键获取文件路径
        String filePath = infoComplianceMapper.getFilePathByKey(fileNo);
        return filePath;
    }

    @RequestMapping(
            value = {"uploadFile"},
            produces = {"text/plain;charset=UTF-8"}
    )
    @ResponseBody
    public String uploadFile(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
        String pageId = StringUtil.trim(request.getParameter("pageId"));
        if (null != this.progressBarService) {
            this.progressBarService.updatePercent(pageId, "uploadFileProgressBar", 0.0D, false);
        }

        String uploadFilePath = CacheUtil.getSysParam("upload_file_path");
        File fileMik = new SafelyFile(uploadFilePath);
        if (!fileMik.exists()) {
            fileMik.mkdirs();
        }

        List<UploadFile> result = new ArrayList();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            Iterator iter = multiRequest.getFileNames();

            while(true) {
                MultipartFile file;
                String myFileName;
                String filePath;
                do {
                    do {
                        if (!iter.hasNext()) {
                            return JsonUtils.object2Json(result);
                        }

                        file = multiRequest.getFile((String)iter.next());
                    } while(file == null);

                    myFileName = StringUtil.trim(file.getOriginalFilename());
                    filePath = System.getProperty("user.home");
                } while(!StringUtil.isNotEmpty(myFileName));

                FileUploadListener listener;
                if (null != this.listeners) {
                    for(Iterator var14 = this.listeners.iterator(); var14.hasNext(); filePath = listener.beforeUpload(request, myFileName)) {
                        listener = (FileUploadListener)var14.next();
                    }
                }


                if (!StringUtil.isNotEmpty(filePath)) {
                    throw new IOException("上传文件格式不正确，请删除后重新上传。");
                }

                File localFile = new SafelyFile(filePath);
                File dir = localFile.getParentFile();
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                if (!localFile.exists()) {
                    localFile.createNewFile();
                }

                this.copyFile(file.getInputStream(), new FileOutputStream(localFile), file.getSize(), pageId);
                UploadFile fileInfo = new UploadFile(myFileName, IDGenerator.generate(), localFile);
                fileInfo.setFileName(localFile.getPath());
                if (null != this.fileAttachmentProcess) {
                    this.fileAttachmentProcess.upload(fileInfo);
                }

                if (null != this.listeners) {
                    this.listeners.forEach((listenerx) -> {
                        listenerx.uploaded(fileInfo);
                    });
                }

                String businessId = request.getParameter("businessId");
                if(StringUtil.isNotEmpty(businessId)){
                    UploadFileDto sysFile = new UploadFileDto();
                    sysFile.setBusinessId(businessId);
                    sysFile.setSeqNo(fileInfo.getFileNo());
                    sysFile.setFileType("03");
                    infoComplianceMapper.updateUplodFile(sysFile);
                }
                fileInfo.setFileName(fileInfo.getSrcName());
                result.add(fileInfo);
            }
        } else {
            return JsonUtils.object2Json(result);
        }
    }

    private int copyFile(InputStream in, OutputStream out, long fileSize, String pageId) throws IOException {
        int byteCount = 0;
        byte[] buffer = new byte[4096];
        boolean var8 = true;

        try {
            int bytesRead;
            while((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                byteCount += bytesRead;
                if (null != this.progressBarService) {
                    this.progressBarService.updatePercent(pageId, "uploadFileProgressBar", (double)byteCount * 0.5D / (double)fileSize, false);
                }
            }

            out.flush();
        } finally {
            try {
                in.close();
            } catch (IOException var18) {
            }

            try {
                out.close();
            } catch (IOException var17) {
            }

        }

        return byteCount;
    }

}
