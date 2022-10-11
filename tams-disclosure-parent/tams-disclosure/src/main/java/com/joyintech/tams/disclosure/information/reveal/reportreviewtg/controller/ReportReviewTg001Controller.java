package com.joyintech.tams.disclosure.information.reveal.reportreviewtg.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.constant.DictCodeExt1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * 托管复核管理
 * @author zhengxiaolian
 * @version 1.0 2022年10月09日
 * @see ReportReviewTg001Controller
 * @since 1.0
 */

@Controller
@PageId("ReportReviewTg001")
@RequestMapping("/disclosure/information/reveal/reportreviewtg/ReportReviewTg001")
public class ReportReviewTg001Controller extends BaseController {
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCodeExt1.托管复核状态,DictCodeExt1.信披报表类别);
        return super.init(model);
    }
}
