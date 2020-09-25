package com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto.RskAccessListDto;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.service.AssetNameManagementService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 名单管理--资产名单管理
 * @author 张年
 * @version 1.0 2019年07月11日
 * @see AssetNameManagement002Controller
 * @since 1.0
 */

@Controller
@PageId("AssetNameManagement002")
@RequestMapping("/invest/risk/namelistmanage/assetnamelist/AssetNameManagement002")
public class AssetNameManagement002Controller extends BaseController {

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
