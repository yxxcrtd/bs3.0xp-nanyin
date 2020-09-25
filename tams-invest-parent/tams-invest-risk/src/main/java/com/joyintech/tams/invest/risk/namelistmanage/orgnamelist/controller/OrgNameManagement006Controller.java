package com.joyintech.tams.invest.risk.namelistmanage.orgnamelist.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 名单管理--机构名单新建
 * @author 张年
 * @version 1.0 2019年07月11日
 * @see OrgNameManagement006Controller
 * @since 1.0
 */
@Controller
@PageId("OrgNameManagement006")
@RequestMapping("/invest/risk/namelistmanage/orgnamelist/OrgNameManagement006")
public class OrgNameManagement006Controller extends BaseController {

    @Override
    public String init(Model model) {
        return super.init(model);
    }
}
