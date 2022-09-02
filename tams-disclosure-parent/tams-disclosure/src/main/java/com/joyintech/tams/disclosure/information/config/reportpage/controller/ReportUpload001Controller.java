package com.joyintech.tams.disclosure.information.config.reportpage.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.listener.FileUploadListener;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.disclosure.infoCompliance.dto.UploadFileDto;
import com.joyintech.tams.disclosure.infoCompliance.service.InfoCompliance002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@PageId("ReportUpload001")
@RequestMapping("/disclosure/information/reportpage/ReportUpload001")
public class ReportUpload001Controller extends BaseController {

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
            if(null != fileAttachmentProcess && StringUtil.isNotEmpty(businessId) && !StringUtil.equals(DictCodeList.是否_是.getValue(), COPY_FLAG)) {
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
        // 画面参数设置
        model.addAttribute("businessId",businessId);
        model.addAttribute("fileType",fileType);
        return super.init(model);
    }

}
