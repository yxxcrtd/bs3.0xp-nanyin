package com.joyintech.tams.invest.risk.namelistmanage.orgnamelist.controller;


import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto.RskAccessListDto;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.service.AssetNameManagementService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 名单管理--机构名单管理
 * @author 张年
 * @version 1.0 2019年07月11日
 * @see OrgNameManagement002Controller
 * @since 1.0
 */

@Controller
@PageId("OrgNameManagement002")
@RequestMapping("/invest/risk/namelistmanage/orgnamelist/OrgNameManagement002")
public class OrgNameManagement002Controller extends BaseController {

    @Autowired
    private AssetNameManagementService assetNameManagementService;
    @Override
    public String init(Model model) {
        String workflowId = getParam("workflowId");
        if(!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)){
            //根据工作流与留痕处理提供的共通方法获取业务实体
            RskAccessListDto rskAccessListDto = assetNameManagementService.getBussinessEntityByWorkflowId(workflowId);
            //设置到model中
            model.addAttribute("entity", rskAccessListDto);
        }
        return super.init(model);
    }
}
