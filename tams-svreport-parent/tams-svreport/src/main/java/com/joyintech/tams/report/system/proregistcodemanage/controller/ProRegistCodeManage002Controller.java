package com.joyintech.tams.report.system.proregistcodemanage.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author LiJun
 * @version 1.0 2019年10月23日
 * @see
 * @since 1.0
 */
@Controller
@PageId(value = "ProRegistCodeManage002")
@RequestMapping("/svreport/system/proregistcodemanage/ProRegistCodeManage002")
public class ProRegistCodeManage002Controller extends BaseController {
    @Override
    public String init(Model model) {
        String prodId = this.getParam("prodId");

        return super.init(model);
    }
}