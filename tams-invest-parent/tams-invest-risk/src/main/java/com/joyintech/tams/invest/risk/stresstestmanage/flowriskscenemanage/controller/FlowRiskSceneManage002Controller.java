/*
 * 文件名：FlowRiskSceneManage002.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.DataDict;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.SttFactorTips;
import com.joyintech.tams.common.base.entity.SttSceneFtrtips;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskflowassets.dto.FlowRiskFlowAssets001Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskSceneManage002Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskSceneManageListDto;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.service.FlowRiskSceneManage002Service;
import com.joyintech.tools.collectors.JoyinCollectors;
import com.joyintech.tools.util.ListUtils;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 流动性风险-情景管理新建
 * @author wangkang
 * @version 1.0 2019年8月13日
 * @see FlowRiskSceneManage002Controller
 * @since 1.0
 */
@Controller
@PageId("FlowRiskSceneManage002")
@RequestMapping("/invest/risk/stresstestmanage/flowriskscenemanage/FlowRiskSceneManage002")
public class FlowRiskSceneManage002Controller extends BaseController {

    @Autowired
    private FlowRiskSceneManage002Service flowRiskSceneManage002Service;
    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String sceneId = getParam("sceneId");
        String OldSceneName = getParam("sceneName");
        setSelectOptionToModel(model, DictCode.情景类型, DictCode.情景风险分类, DictCode.申赎净流出基础选择, DictCode.流动性风险因子, DictCode.情景二级类型, DictCode.开放模式);
        List<SelectOptionDto> options = new ArrayList<>();
        List<FlowRiskSceneManageListDto> list= flowRiskSceneManage002Service.queryFlowSttFactor();
        for(FlowRiskSceneManageListDto sceMaDto:list){
            SelectOptionDto selDto1 = new SelectOptionDto();
            selDto1.setId(sceMaDto.getFtrId());
            selDto1.setText(sceMaDto.getFtrName());
            options.add(selDto1);
        }
        model.addAttribute("ftrRemarkList",JsonUtils.objectArray2Json(list.toArray()));
        model.addAttribute("FLOW_RISK_FACTOR_ONE", JsonUtils.objectArray2Json(options.toArray()));
        
//        SelectDto selectNetOutfDto = this.getSelectDataFromDataDic(DictCode.申赎净流出基础选择, null);
//        List<SelectOptionDto> optionNetOutf = selectNetOutfDto.getOptions();
//        List<SelectOptionDto> optionNetOutf = new ArrayList<>();
//        SelectOptionDto selDto3 = new SelectOptionDto();
//        selDto3.setId(list.get(2).getFtrId());
//        selDto3.setText(list.get(2).getFtrName());
//        optionNetOutf.add(selDto3);
//        Map<String,List<SelectOptionDto>> mapNetOutf = optionNetOutf.stream().collect(JoyinCollectors.groupingBy(x->x.getId().toString()));
//        model.addAttribute("mapNetOutf",mapNetOutf);
        
        setSelectOptionToModel(model, DictCode.流动性风险因子);
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            FlowRiskSceneManage002Dto dto = flowRiskSceneManage002Service.getBussinessEntityByWorkflowId(
                workflowId);
            dto.setWorkflowId(workflowId);
            if(!StringUtil.isNotEmpty(dto.getOpenmode())){
                dto.setOpenmode(dto.getOpenMode());
            }
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute(GlobalConstant.WORKFLOW_ID, workflowId);
        }else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(sceneId)) {//编辑模式
            FlowRiskSceneManage002Dto dto = flowRiskSceneManage002Service.selectSceneTmpl();
            FlowRiskSceneManage002Dto sceDto = flowRiskSceneManage002Service.querySceneById(sceneId);
            FlowRiskSceneManage002Dto sceAndModDto = flowRiskSceneManage002Service.queryScentType2AndMode(sceneId);
            dto.setSceneId(sceDto.getSceneId());
            dto.setSceneName(sceDto.getSceneName());
            dto.setOldSceneName(sceDto.getSceneName());
            dto.setRemark(sceDto.getRemark());
            if(StringUtil.isNotEmpty(sceAndModDto)){
            	dto.setSceneType2(sceAndModDto.getSceneType2());
                dto.setOpenmode(sceAndModDto.getOpenmode());
            }
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }else{//新建模式
            FlowRiskSceneManage002Dto dto = flowRiskSceneManage002Service.selectSceneTmpl();
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        
        if(StringUtil.isNotEmpty(sceneId)){
            model.addAttribute("sceneId", sceneId);
        }
        model.addAttribute("PrdScl01002", DictCodeListRsk.流动性风险因子_产品赎回比例.getValue());
        model.addAttribute("PrdScl01001", DictCodeListRsk.流动性风险因子_申赎净流出增加比例.getValue());
        model.addAttribute("AssLqu01001", DictCodeListRsk.流动性风险因子_二级流动性资产变化率.getValue());
        return super.init(model);
        
        
        
    }
    
    /**
     * 获取产品指标值
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/queryIndexTypePro")
    public List<FlowRiskSceneManage002Dto> queryIndexTypePro() throws Exception {
        String modelId = getParam("modelId");
        
        return flowRiskSceneManage002Service.selectTmplIndByType(modelId, DictCodeListRsk.压力测试指标类型_产品指标.getValue());
//        return flowRiskSceneManage002Service.queryIndexTypePro();
    }
    
    /**
     * 获取承压指标值
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/queryIndexTypeStr")
    public List<FlowRiskSceneManage002Dto> queryIndexTypeStr() throws Exception {
        String modelId = getParam("modelId");
        return flowRiskSceneManage002Service.selectTmplIndByType(modelId, DictCodeListRsk.压力测试指标类型_承压指标.getValue());
    }
    /**
     * 产品规模变化因子
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/queryFtrOne")
    public List<FlowRiskSceneManageListDto> queryFtrOne() throws Exception {
    	List<FlowRiskSceneManageListDto> list = new ArrayList<>();
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String sceneId = getParam("sceneId");
        if(StringUtil.isNotEmpty(workflowId)){
            
            FlowRiskSceneManage002Dto dto1 = flowRiskSceneManage002Service.getBussinessEntityByWorkflowId(
                workflowId);
            list = dto1.getFtrList1();
            if(StringUtil.isEmptyList(list)){
                list = new ArrayList<>();
                List<FlowRiskSceneManageListDto> facLi = flowRiskSceneManage002Service.queryFtrBySceId(dto1.getSceneId(),"Lqu01");
                for(FlowRiskSceneManageListDto facLiDto:facLi){
                    if(StringUtil.equals(facLiDto.getFtrId(), "PrdScl01001")){
                        SttSceneFtrtips tips = flowRiskSceneManage002Service.queryNetOutflow(dto1.getSceneId());
                        if(StringUtil.isNotEmpty(tips)){
                            facLiDto.setNetOutf(tips.getParamValue());
                        }
                    }
                }
                list.addAll(facLi);
            }
        }
        else{//新建模式
            if(StringUtil.equals(getParam("pageModel"), "0")){
                List<FlowRiskSceneManageListDto> facLis = flowRiskSceneManage002Service.queryFlowSttFactor();
//                FlowRiskSceneManage002Dto dto2 = new FlowRiskSceneManage002Dto();
//                dto2.setFtrId(DictCodeList.流动性风险因子_产品赎回比例.getValue());
//                dto2.setFtrName(SysDataUtil.getNameFromDataDict(DictCode.流动性风险因子, DictCodeList.流动性风险因子_产品赎回比例.getValue()));
                if(facLis.size()!=0){
                    list.add(facLis.get(0));
                }
            }//编辑模式
            else if(StringUtil.equals(getParam("pageModel"), "1") && StringUtil.isNotEmpty(sceneId) || StringUtil.equals(getParam("pageModel"), "2")){
                List<FlowRiskSceneManageListDto> facLi = flowRiskSceneManage002Service.queryFtrBySceId(sceneId,"Lqu01");
                for(FlowRiskSceneManageListDto facLiDto:facLi){
                    if(StringUtil.equals(facLiDto.getFtrId(), "PrdScl01001")){
                        SttSceneFtrtips tips = flowRiskSceneManage002Service.queryNetOutflow(sceneId);
                        if(StringUtil.isNotEmpty(tips)){
                            facLiDto.setNetOutf(tips.getParamValue());
                        }
                    }
                }
                list.addAll(facLi);
            }
            
        }
        return list;
    }
    /**
     * 资产变现能力因子
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/queryFtrTwo")
    public List<FlowRiskSceneManageListDto> queryFtrTwo() throws Exception {
    	List<FlowRiskSceneManageListDto> list = new ArrayList<>();
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String sceneId = getParam("sceneId");
        if(StringUtil.isNotEmpty(workflowId)){
            FlowRiskSceneManage002Dto dto1 = flowRiskSceneManage002Service.getBussinessEntityByWorkflowId(
                workflowId);
            list = dto1.getFtrList2();
            if(StringUtil.isEmptyList(list)){
                list = new ArrayList<>();
                List<FlowRiskSceneManageListDto> facLi = flowRiskSceneManage002Service.queryFtrBySceId(dto1.getSceneId(), "Lqu02");
                list.addAll(facLi);
            }
        }
        else{//新建模式
            if(StringUtil.equals(getParam("pageModel"), "0")){
                List<FlowRiskSceneManageListDto> facLis = flowRiskSceneManage002Service.queryFlowSttFactor2();
                list.addAll(facLis);
            }//编辑模式
            else if(StringUtil.equals(getParam("pageModel"), "1") ||StringUtil.equals(getParam("pageModel"), "2")){
                List<FlowRiskSceneManageListDto> facLi = flowRiskSceneManage002Service.queryFtrBySceId(sceneId, "Lqu02");
                list.addAll(facLi);
            }
            
        }
        return list;
    }
    /**
     * 保存按钮点击时的check
     * @return check结果
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated FlowRiskSceneManage002Dto dto, BindingResult result ) {
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
    public AjaxResult save(@RequestBody @Validated FlowRiskSceneManage002Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        //新建设置情景代码
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
        	//start修改情景代码格式-sunhui
            //String sceCode=dto.getSceneId();//获取dto中情景代码（此时是工作流业务编号）
            String riskTypeCode=dto.getRiskType();//获取dto适用风险分类CODE
            String sceneTypeCode=dto.getSceneType();//获取dto情景类型CODE
            String finSceCode=IDGenerator.generate(12).substring(0, 8);
            if(riskTypeCode.equals("01")){//综合风险
             finSceCode="Gnr".concat(Integer.valueOf(sceneTypeCode).toString()).concat(finSceCode);
            }else if(riskTypeCode.equals("02")){//市场风险
             finSceCode="Mrk".concat(Integer.valueOf(sceneTypeCode).toString()).concat(finSceCode);
            }else if(riskTypeCode.equals("03")){//流动性风险
             finSceCode="Lqu".concat(Integer.valueOf(sceneTypeCode).toString()).concat(finSceCode);
            }
            dto.setSceneId(finSceCode);//重新设置dto的情景代码
          //end修改情景代码格式-sunhui
//            dto.setSceneId(UUID.randomUUID().toString().replaceAll("-", ""));
        }
        ProcessParameter<FlowRiskSceneManage002Dto> processParameter = new ProcessParameter<FlowRiskSceneManage002Dto>();
        WorkFlowParameter<FlowRiskSceneManage002Dto> params = new WorkFlowParameter<FlowRiskSceneManage002Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        // 情景代码作为业务编号
        String bussinessCode = dto.getSceneId();
        params.setBusinessCode(bussinessCode);
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            params.setBusinessType(BussinessDictCodeList.情景管理_新建情景.getValue());
        }else{
            params.setBusinessType(BussinessDictCodeList.情景管理_修改情景.getValue());
        }
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(dto.getPageModel()==GlobalConstant.DETAIL_PAGE_MODEL_NEW? getMessage("FlowRiskSceneManage002"):getMessage("FlowRiskSceneManage002.edit"),
                GlobalConstant.OPEN_PARENTHESIS, bussinessCode, GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("FlowRiskSceneManage002.sceneName"), GlobalConstant.OPEN_PARENTHESIS,
                dto.getSceneName(), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            processParameter.setOperationType(OperationType.NEW);
        }else{
            processParameter.setOperationType(OperationType.MODIFY);
        }
        
        String msg = flowRiskSceneManage002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

}
