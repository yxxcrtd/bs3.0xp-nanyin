package com.joyintech.tams.valuation.filecommonutil;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/valuation/filecommonutil/FileCommon")
public class FileCommonController extends BaseController {

    @Autowired(required = false)
    private FileAttachmentProcess fileAttachmentProcess;

    @Override
    public String init(Model model) {
        String businessId = getParam("businessId");
        if(null != fileAttachmentProcess && StringUtil.isNotEmpty(businessId)) {
            List<UploadFile> files = fileAttachmentProcess.fetchByBusinessCode(businessId);
            model.addAttribute("attachmentFiles", JsonUtils.object2Json(files));
        }
        // 画面参数设置
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"), "fileCommonDto"));
        return "valuation/fileupload/FileCommon";
    }
}
