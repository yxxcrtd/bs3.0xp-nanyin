/*
 * 文件名：FlowRiskFlowAssets001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.flowriskflowassets.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskflowassets.dto.FlowRiskFlowAssets001Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskflowassets.dto.NodeDto;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskflowassets.service.FlowRiskFlowAssets001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 流动性风险-流动性资产配置
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年8月20日
 * @see FlowRiskFlowAssets001Controller
 * @since 1.0
 */
@Controller
@PageId("FlowRiskFlowAssets001")
@RequestMapping("/invest/risk/stresstestmanage/flowriskflowassets/FlowRiskFlowAssets001")
public class FlowRiskFlowAssets001Controller extends BaseController{
    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;
    /**
     * 流动性资产配置service
     */
    @Autowired
    private FlowRiskFlowAssets001Service flowRiskFlowAssets001Service;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // TODO Auto-generated method stub
        
        setSelectOptionToModel(model, DictCode.因子风险分类);
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            FlowRiskFlowAssets001Dto dto = flowRiskFlowAssets001Service.getBussinessEntityByWorkflowId(
                workflowId);
            dto.setWorkflowId(workflowId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }else{
            FlowRiskFlowAssets001Dto dto = flowRiskFlowAssets001Service.selectFlowAssOneMes();
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        return super.init(model);
    }
    
    @ResponseBody
    @RequestMapping("changeSceneType")
    public List<SelectOptionDto> changeSceneType() {
        String riskType = RequestUtil.getParam("riskType");
        List<SelectOptionDto> selectOptionList=new ArrayList<>();
        SelectOptionDto sod1 = new SelectOptionDto();
        sod1.setId("01");
        sod1.setText("情景1");
        sod1.setCol("SCENE_TYPE");
        selectOptionList.add(sod1);
        
        
        if(StringUtil.equals("02", riskType)){
            SelectOptionDto sod2 = new SelectOptionDto();
            sod2.setId("02");
            sod2.setText("情景1");
            sod2.setCol("SCENE_TYPE");
            selectOptionList.add(sod2);
        }
        
        return selectOptionList;
        
    }
    
    /**
     * 资产分类树数据
     * @return 获取资产分类树
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getAssetsSort")
    public List<TreeNode<NodeDto>> getTreeLeft(){
        List<TreeNode<NodeDto>> tree = new ArrayList<>();
        List<FlowRiskFlowAssets001Dto> treeLis = flowRiskFlowAssets001Service.getFloAssTreeData();
        for(FlowRiskFlowAssets001Dto dto:treeLis){
        	//去除银行及现金存款
//        	if(!StringUtil.equals(dto.getpId(), "05")){
            if(!StringUtil.equals(dto.getpDictCode(),"ASSET_CLASS")) {
                TreeNode<NodeDto> nod1 = new TreeNode<NodeDto>();
                nod1.setId(dto.getpId());
                nod1.setName(SysDataUtil.getNameFromDataDict(BussinessDictCode.行内资产二级分类, dto.getpId()));
                nod1.setOpen(true);
                tree.add(nod1);
                String[] nodList = dto.getChild().split(",");
                for (String chiNod : nodList) {
                    //去除现金及活期存款
                    if (!StringUtil.equals(chiNod.substring(chiNod.indexOf("-") + 1, chiNod.length()), BussinessDictCodeList.行内资产三级分类_现金及活期存款.getValue())) {
                        TreeNode<NodeDto> nod11 = new TreeNode<NodeDto>();
                        nod11.setId(chiNod);
                        String a = chiNod.substring(chiNod.indexOf("-") + 1, chiNod.length());
                        nod11.setName(SysDataUtil.getNameFromDataDict(BussinessDictCode.行内资产三级分类, chiNod.substring(chiNod.indexOf("-") + 1, chiNod.length())));
                        nod11.setOpen(true);
                        nod11.setpId(dto.getpId());
                        tree.add(nod11);
                    }
                }
//        	}
            }
            
        }
        
        return tree;
    }
    
    
    /**
     * 获取一级流动性资产树
     * @return 获取一级流动性资产树
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getFlowAssets1")
    public List<TreeNode<NodeDto>> getFlowAssets1(@RequestBody FlowRiskFlowAssets001Dto dto1){
        List<TreeNode<NodeDto>> tree = new ArrayList<>();
        String workflowId = dto1.getWorkflowId();
        if(StringUtil.isNotEmpty(workflowId)){
            FlowRiskFlowAssets001Dto dto = flowRiskFlowAssets001Service.getBussinessEntityByWorkflowId(
                workflowId);
            for(FlowRiskFlowAssets001Dto assetDto:dto.getSelectedFlowAss1()){
                TreeNode<NodeDto> node = new TreeNode<NodeDto>();
                node.setId(assetDto.getId());
                node.setName(assetDto.getName());
                node.setpId(assetDto.getpId());
                tree.add(node);
            }
        }else{
            List<FlowRiskFlowAssets001Dto> list = flowRiskFlowAssets001Service.getFloAssOne();
            for(FlowRiskFlowAssets001Dto flowAss:list){
                TreeNode<NodeDto> nod = new TreeNode<NodeDto>();
                if(StringUtil.equals(flowAss.getLev(), "1")){
//                    nod.setId(flowAss.getDetId().substring(flowAss.getDetId().indexOf("-" + 1 ,flowAss.getDetId().length())));
                    nod.setId(flowAss.getSouceValue());
                    //资产分类转中文
                    if(StringUtil.equals(flowAss.getDictCode(), DictCode.行内资产二级分类.getValue())){
                        nod.setName(SysDataUtil.getNameFromDataDict(BussinessDictCode.行内资产二级分类, flowAss.getSouceValue()));
                    }
                    else if(StringUtil.equals(flowAss.getDictCode(), DictCode.行内资产三级分类.getValue())){
                        nod.setName(SysDataUtil.getNameFromDataDict(BussinessDictCode.行内资产三级分类, flowAss.getSouceValue()));
                    }
                    nod.setpId("E");
                    nod.setOpen(true);
                    tree.add(nod);
                }
                else{
                    nod.setId(flowAss.getDetId());
                    //资产分类转中文
                    if(StringUtil.equals(flowAss.getDictCode(), DictCode.行内资产二级分类.getValue())){
                        nod.setName(SysDataUtil.getNameFromDataDict(BussinessDictCode.行内资产二级分类, flowAss.getSouceValue()));
                    }
                    else if(StringUtil.equals(flowAss.getDictCode(), DictCode.行内资产三级分类.getValue())){
                        nod.setName(SysDataUtil.getNameFromDataDict(BussinessDictCode.行内资产三级分类, flowAss.getSouceValue()));
                    }
                    nod.setpId(flowAss.getfDetId());
                    nod.setOpen(true);
                    tree.add(nod);
                }
            }
            
            
        }
        return tree;
    }
    
    
    /**
     * 获取二级流动性资产树
     * @return 获取二级流动性资产树
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getFlowAssets2")
    public List<TreeNode<NodeDto>> getFlowAssets2(@RequestBody FlowRiskFlowAssets001Dto dto1){
        List<TreeNode<NodeDto>> tree = new ArrayList<>();
        String workflowId = dto1.getWorkflowId();
        if(StringUtil.isNotEmpty(workflowId)){
            FlowRiskFlowAssets001Dto dto = flowRiskFlowAssets001Service.getBussinessEntityByWorkflowId(
                workflowId);
            for(FlowRiskFlowAssets001Dto assetDto:dto.getSelectedFlowAss2()){
                TreeNode<NodeDto> node = new TreeNode<NodeDto>();
                node.setId(assetDto.getId());
                node.setName(assetDto.getName());
                node.setpId(assetDto.getpId());
                tree.add(node);
            }
        }else{
            List<FlowRiskFlowAssets001Dto> list = flowRiskFlowAssets001Service.getFloAssTwo();
            for(FlowRiskFlowAssets001Dto flowAss:list){
                TreeNode<NodeDto> nod = new TreeNode<NodeDto>();
                if(StringUtil.equals(flowAss.getLev(), "1")){
                    nod.setId(flowAss.getSouceValue());
                    //资产分类转中文
                    if(StringUtil.equals(flowAss.getDictCode(), DictCode.行内资产二级分类.getValue())){
                        nod.setName(SysDataUtil.getNameFromDataDict(BussinessDictCode.行内资产二级分类, flowAss.getSouceValue()));
                    }
                    else if(StringUtil.equals(flowAss.getDictCode(), DictCode.行内资产三级分类.getValue())){
                        nod.setName(SysDataUtil.getNameFromDataDict(BussinessDictCode.行内资产三级分类, flowAss.getSouceValue()));
                    }
                    nod.setpId("E");
                    nod.setOpen(true);
                    tree.add(nod);
                }
                else{
                    nod.setId(flowAss.getDetId());
                    //资产分类转中文
                    if(StringUtil.equals(flowAss.getDictCode(), DictCode.行内资产二级分类.getValue())){
                        nod.setName(SysDataUtil.getNameFromDataDict(BussinessDictCode.行内资产二级分类, flowAss.getSouceValue()));
                    }
                    else if(StringUtil.equals(flowAss.getDictCode(), DictCode.行内资产三级分类.getValue())){
                        nod.setName(SysDataUtil.getNameFromDataDict(BussinessDictCode.行内资产三级分类, flowAss.getSouceValue()));
                    }
                    nod.setpId(flowAss.getfDetId());
                    nod.setOpen(true);
                    tree.add(nod);
                }
            }
        }
        return tree;
    }
    
    /**
     * 保存按钮点击时的check
     * @return check结果
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) FlowRiskFlowAssets001Dto dto, BindingResult result ) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 保存
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody FlowRiskFlowAssets001Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FlowRiskFlowAssets001Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<FlowRiskFlowAssets001Dto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        // 配置代码作为业务编号
//        params.setBusinessCode(dto.getPortfolioId());
        String bussinessCode = IDGenerator.generate(20);
        params.setBusinessCode(bussinessCode);
        params.setBusinessType(DictCodeListRsk.共通业务类型_流动性资产设置.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("FlowRiskFlowAssets001.tgtTypeId"),
                GlobalConstant.OPEN_PARENTHESIS, bussinessCode, GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("FlowRiskFlowAssets001.createTime"), GlobalConstant.OPEN_PARENTHESIS,
                iPlatformDate.getSysDate().toString(), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = flowRiskFlowAssets001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

}
