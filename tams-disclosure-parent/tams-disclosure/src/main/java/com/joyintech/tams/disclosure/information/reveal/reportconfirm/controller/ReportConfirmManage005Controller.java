package com.joyintech.tams.disclosure.information.reveal.reportconfirm.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.base.entity.SysUser;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.service.ReportConfirmManage005Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@PageId("ReportConfirmManage005")
@RequestMapping("/disclosure/information/reveal/reportconfirm/ReportConfirmManage005")
public class ReportConfirmManage005Controller extends BaseController {
    @Autowired
    private ReportConfirmManage005Service reportConfirmManage005Service;

    @Override
    public String init(Model model) {
        return super.init(model);
    }

    @RequestMapping("findPage")
    @ResponseBody
    public Page<SysUser> findPage(){
        Map<String, Object> param= RequestUtil.getPageParam();
        Page<SysUser> prdInfoSelect = reportConfirmManage005Service.findPage(param);
        return prdInfoSelect;
    }
}
