package com.joyintech.tams.invest.risk.namelistmanage.orgnamelist.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto.RskAccessListDetailDto;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto.RskAccessListDto;
import com.joyintech.tams.invest.risk.namelistmanage.orgnamelist.service.OrgNameManagementDetailService;
import com.joyintech.tams.invest.risk.namelistmanage.orgnamelist.service.OrgNameManagementService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 名单管理--机构名单明细维护
 * @author 张年
 * @version 1.0 2019年07月11日
 * @see OrgNameManagement003Controller
 * @since 1.0
 */

@Controller
@PageId("OrgNameManagement003")
@RequestMapping("/invest/risk/namelistmanage/orgnamelist/OrgNameManagement003")
public class OrgNameManagement003Controller extends BaseController {

    @Autowired
    private OrgNameManagementService orgNameManagementService;

    @Autowired
    private OrgNameManagementDetailService orgNameManagementDetailService;

    @Override
    public String init(Model model) {
        String listId = this.getParam("listId");
        String workflowId = getParam("workflowId");
        String listName = this.getParam("listName");
        String listDetailType =  this.getParam("listDetailType");
        String operateStyle = this.getParam("operateStyle");
        model.addAttribute("RSK_OPERATE_PS_01", DictCodeListExt2.名单操作方向_移出名单.getValue());
        model.addAttribute("RSK_OPERATE_PS_02", DictCodeListExt2.名单操作方向_移入名单.getValue());
        model.addAttribute("RSK_OPERATE_PS_03", DictCodeListExt2.名单操作方向_修改.getValue());
        model.addAttribute("RSK_OPERATE_PS_99", DictCodeListExt2.名单操作方向_不变.getValue());
        model.addAttribute("RSK_OPERATE_PS_NAME_01", DictCodeListExt2.名单操作方向_移出名单.getCodeName());
        model.addAttribute("RSK_OPERATE_PS_NAME_02", DictCodeListExt2.名单操作方向_移入名单.getCodeName());
        model.addAttribute("RSK_OPERATE_PS_NAME_03", DictCodeListExt2.名单操作方向_修改.getCodeName());
        model.addAttribute("RSK_OPERATE_PS_NAME_99", DictCodeListExt2.名单操作方向_不变.getCodeName()); 
        model.addAttribute("sumCount",orgNameManagementDetailService.getNameDetailCount(listId));
        //不来自于代办
        if(WorkFlowUtil.isNotFromToDoList()&& StringUtil.isNotEmpty(listId)) {
            //根据主键获取业务实体--标的金融产品基本信息
           /* RskAccessListDto finBondTargetAssetDto = finBondTargetAssetService.getBasicInfo(
                finprodId);
            commonBusinessService.setTradeMarketId(finBondTargetAssetDto,model);*/
            model.addAttribute("listName",listName);
            model.addAttribute("listId",listId);
            model.addAttribute("entity", new RskAccessListDto());
        }//来自于代办
        else if(!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)){
            //根据工作流与留痕处理提供的共通方法获取业务实体
            RskAccessListDto rskAccessListDto = orgNameManagementService.getBussinessEntityByWorkflowId(workflowId);
            //设置到model中
            if (CollectionUtils.isEmpty(rskAccessListDto.getRskAccessListDetailList())) {
                Map<String, Object> selectParam = new HashMap<String, Object>();
                selectParam.put("listId", rskAccessListDto.getListId()); 
                Page<RskAccessListDetailDto> detailPage = orgNameManagementDetailService.selectPageData(selectParam);
                List<RskAccessListDetailDto> detailList = new ArrayList<>();
                detailPage.getRoot().stream().forEach(item->{
                    item.setOperatePs("01");
                    detailList.add(item);
                });
                rskAccessListDto.setRskAccessListDetailList(detailList);
            } 
            model.addAttribute("sumCount",orgNameManagementDetailService.getNameDetailCount(rskAccessListDto.getListId()));
            model.addAttribute("entity", rskAccessListDto);
            model.addAttribute("listName",rskAccessListDto.getListName());
            model.addAttribute("listId",rskAccessListDto.getListId());
        }
        model.addAttribute("listDetailType",listDetailType);
        model.addAttribute("operateStyle",operateStyle);
        model.addAttribute("workflowId",workflowId);
        return super.init(model);
    }

    /**
     * 初始化查询（名单明细）
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RskAccessListDetailDto> selectAll()
        throws Exception {
        Page<RskAccessListDetailDto> page = orgNameManagementService.selectForDetailPage(
            RequestUtil.getParameters(getRequest()));
        return page;
    }


    /**
     * check--保存
     * @param dto
     * @param result
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) RskAccessListDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     *
     * 保存
     * @param dto
     * @param br
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) RskAccessListDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        WorkFlowParameter<RskAccessListDto> params = new WorkFlowParameter<RskAccessListDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getListId());
        params.setBusinessType(DictCodeList.共通业务类型_机构名单管理.getValue());
        // 设置流程业务说明
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("AssetNameManagement001.listId")+
                GlobalConstant.OPEN_PARENTHESIS+entity.getListId(), GlobalConstant.CLOSE_PARENTHESIS+
                getMessage("AssetNameManagement001.listName")+GlobalConstant.OPEN_PARENTHESIS+
                entity.getListName()+GlobalConstant.CLOSE_PARENTHESIS);
        });
        // 给参数变成ProcessParameter而非WorkFlowParameter
        ProcessParameter<RskAccessListDto> parameter = new ProcessParameter<RskAccessListDto>();
        parameter.setWorkFlowParameter(params);
        // 系统页面表IS_NEED_WORKFLOW 是否走流程，01-是，02-否
        String msg = orgNameManagementService.start(parameter, dto);
        return new AjaxResult(msg);
    }


}
