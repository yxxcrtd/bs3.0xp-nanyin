/*
 * 文件名：MarketRiskSceneManage002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：汪海
 * 修改时间：2019年9月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneFactorTypeDto;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneManage002Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.service.MarketRiskSceneManage002Service;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.service.MarketRiskSceneManage003Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;


/**
 * 市场性风险-新建情景
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019年9月4日
 * @see MarketRiskSceneManage002Controller
 * @since 1.0
 */
@Controller
@PageId("MarketRiskSceneManage002")
@RequestMapping("/invest/risk/stresstestmanage/marketriskscenemanage/MarketRiskSceneManage002")
public class MarketRiskSceneManage002Controller extends BaseController {
    
    /**
     * 情景新建002service注入
     */
    @Autowired
    private MarketRiskSceneManage002Service marketRiskSceneManage002Service;
    
    @Autowired
    private MarketRiskSceneManage003Service marketRiskSceneManage003Service;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String sceneId = getParam("sceneId");
        //下拉字典配置
        setSelectOptionToModel(model, DictCode.情景类型, DictCode.情景风险分类, DictCode.申赎净流出基础选择, DictCode.开放模式,
        		DictCode.风险因子管理_市场风险因子分类);
        
        //工作流配置
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (StringUtil.isNotEmpty(workflowId)) {
            MarketRiskSceneManage002Dto dto = marketRiskSceneManage002Service.getBussinessEntityByWorkflowId(
                workflowId);
            if(StringUtil.equals(dto.getOperateType(), "delete")){
            	sceneId = dto.getSceneId();
            	//删除工作流传入的数据是情景管理页面的数据，有缺失，需要重新赋值
            	MarketRiskSceneManage002Dto sceneTmplDto = marketRiskSceneManage002Service.selectSceneTmpl();
            	dto = marketRiskSceneManage002Service.querySceneById(sceneId);
            	dto.setTmplId(sceneTmplDto.getTmplId());
            	dto.setTmplName(sceneTmplDto.getTmplName());
            }else if(StringUtil.isNotEmpty(dto.getSceneId()) && StringUtil.isEmpty(sceneId)){
            	sceneId = dto.getSceneId();
            }
            dto.setWorkflowId(workflowId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute(GlobalConstant.WORKFLOW_ID, workflowId);
            
        }else if (StringUtil.isNotEmpty(sceneId) || StringUtil.equals(getParam("pageModel"), "2")) {//编辑模式或查看情景详情
        	MarketRiskSceneManage002Dto sceneTmplDto = marketRiskSceneManage002Service.selectSceneTmpl();
            MarketRiskSceneManage002Dto dto = marketRiskSceneManage002Service.querySceneById(sceneId);
            dto.setTmplId(sceneTmplDto.getTmplId());
        	dto.setTmplName(sceneTmplDto.getTmplName());
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }else{//新建模式
            MarketRiskSceneManage002Dto dto = marketRiskSceneManage002Service.selectSceneTmpl();
            dto.setNewType(DictCodeListRsk.是否_是.getValue());
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        
        if(StringUtil.isNotEmpty(sceneId)){
            model.addAttribute("sceneId", sceneId);
        }
        
        return super.init(model);
        
    }

    /**
     * 获取市场性风险因子类
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/queryMarketRiskFactor")
    public List<MarketRiskSceneFactorTypeDto> queryMarketRiskFactor() throws Exception {
        //市场性风险因子分类
        List<MarketRiskSceneFactorTypeDto> factorList = new ArrayList<MarketRiskSceneFactorTypeDto>();
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String sceneId = getParam("sceneId");
        //如果是流程中
        if(StringUtil.isNotEmpty(workflowId)){		//工作流中
        	MarketRiskSceneManage002Dto dto = marketRiskSceneManage002Service.getBussinessEntityByWorkflowId(
                    workflowId);
        	if(StringUtil.equals(dto.getOperateType(), "delete")){			//删除流程，关联因子从数据库中取，或者是启用停用流程
        		factorList = marketRiskSceneManage002Service.queryFactorTypeList(sceneId);
        	}else if(dto.getFtrTypeList() == null && sceneId!= null){			//新建或修改流程，关联因子从流程数据中取
        		factorList = marketRiskSceneManage002Service.queryFactorTypeList(sceneId);
        	}else{
        		factorList = dto.getFtrTypeList();
        	}
        }else{			//工作流外，分为新建和修改两种情况
        	if(StringUtil.equals(getParam("pageModel"), "0")){		//新建情景时，关联因子初始化
        		factorList = marketRiskSceneManage002Service.queryFactorTypeList("");
        	}else{		//修改情景时，关联因子从数据库中取
        		factorList = marketRiskSceneManage002Service.queryFactorTypeList(sceneId);
        	}
        }
        return factorList;
    }
    
    /**
     * 获取产品指标值
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/queryIndexTypePro")
    public List<MarketRiskSceneManage002Dto> queryIndexTypePro() throws Exception {
        String modelId = getParam("modelId");
        
        return marketRiskSceneManage002Service.selectTmplIndByType(modelId, DictCodeListRsk.压力测试指标类型_产品指标.getValue());
    }
    
    /**
     * 获取承压指标值
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/queryIndexTypeStr")
    public List<MarketRiskSceneManage002Dto> queryIndexTypeStr() throws Exception {
        String modelId = getParam("modelId");
        return marketRiskSceneManage002Service.selectTmplIndByType(modelId, DictCodeListRsk.压力测试指标类型_承压指标.getValue());
    }
    
    /**
     * 保存按钮点击时的check
     * @return check结果
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated MarketRiskSceneManage002Dto dto, BindingResult result ) {
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
    public AjaxResult save(@RequestBody @Validated MarketRiskSceneManage002Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<MarketRiskSceneManage002Dto> processParameter = new ProcessParameter<MarketRiskSceneManage002Dto>();
        WorkFlowParameter<MarketRiskSceneManage002Dto> params = new WorkFlowParameter<MarketRiskSceneManage002Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        // 情景代码作为业务编号
        if(StringUtil.isEmpty(dto.getSceneId())){
        	String sceneId = "Mrk1"+StringUtil.leftPad(marketRiskSceneManage003Service.getNewSceneId(), 4, "0");
        	dto.setSceneId(sceneId);
        }
        dto.setOperateType("edit");
        String bussinessCode = dto.getSceneId();
        params.setBusinessCode(bussinessCode);
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            params.setBusinessType(BussinessDictCodeList.情景管理_新建情景.getValue());
        }else{
            params.setBusinessType(BussinessDictCodeList.情景管理_修改情景.getValue());
        }
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(dto.getPageModel()==GlobalConstant.DETAIL_PAGE_MODEL_NEW? getMessage("MarketRiskSceneManage002"):getMessage("MarketRiskSceneManage002.edit"),
                getMessage("MarketRiskSceneManage002.sceneName"), GlobalConstant.OPEN_PARENTHESIS,
                dto.getSceneName(), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            processParameter.setOperationType(OperationType.NEW);
        }else{
            processParameter.setOperationType(OperationType.MODIFY);
        }
        
        String msg = marketRiskSceneManage002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
}
