/*
 * 文件名：FinProductPayInt002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年1月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.payint.controller;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import org.apache.commons.lang3.StringUtils;
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
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.settle.BussSettleOrderCommService;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.product.addreduce.ReduceInfoDto;
import com.joyintech.tams.invest.product.payint.dto.FinProductPayInt002Dto;
import com.joyintech.tams.invest.product.payint.dto.FinProductPayInt002IntDetailDto;
import com.joyintech.tams.invest.product.payint.dto.FinProductPayInt003Dto;
import com.joyintech.tams.invest.product.payint.service.FinProductPayInt003Service;
import com.joyintech.tams.invest.product.productelement.build.dto.FinProductBuild002Dto;
import com.joyintech.tams.invest.product.productelement.build.service.FinProductBuild002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.service.LeaveMarkProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 产品中间付息新建
 *
 * @author etliu
 * @version 1.0 2019年1月6日
 * @see FinProductPayInt003Controller
 * @since 1.0
 */
@Controller
@PageId("FinProductPayInt003")
@RequestMapping("/invest/product/payint/FinProductPayInt003")
public class FinProductPayInt003Controller extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductBuild002Service finProductBuild002Service;

    /**
     * FinProductPayInt003Service
     */
    @Autowired
    private FinProductPayInt003Service finProductPayInt003Service;


    /**
     * BaseGetBusinessId
     */
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;
    /**
     * 清算指令共通Service
     */
    @Autowired(required = false)
    private BussSettleOrderCommService bussSettleOrderCommService;
    
    @SuppressWarnings("rawtypes")
    @Autowired(required=false)
    private LeaveMarkProcessService leaveMarkProcessService;

    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.销售部门, DictCode.收益类型,
            DictCode.付息周期, DictCode.币种);
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String totalTradeId = RequestUtil.getParam("tradeId");
        if(totalTradeId==null){
        	totalTradeId = RequestUtil.getParam("bussId");
        }
        String finProdId = RequestUtil.getParam("finprodId");
        //FinProductPayInt003Dto dataDto = new FinProductPayInt003Dto();
        /*if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotBlank(workflowId)) {
            dataDto = (FinProductPayInt003Dto) finProductPayInt003Service.getBussinessEntityByWorkflowId(
                workflowId);
            dataDto.setWorkflowId(workflowId);
        }        
        // 划款信息
        if (dataDto != null && !StringUtil.isEmptyList(dataDto.getBussTrdTransferOrder())) {
            model.addAttribute("entity_FinProductPayInt002",
                JsonUtils.object2Json(dataDto.getBussTrdTransferOrder().get(0)));
        }
        model.addAttribute("entity", dataDto);*/

        model.addAttribute(GlobalConstant.WORKFLOW_ID, "");

        if (!WorkFlowUtil.isNotFromToDoList() && StringUtils.isNotEmpty(workflowId)) {//代办
            // 根据工作流与留痕处理提供的共通方法获取业务实体
        	FinProductPayInt003Dto entity = finProductPayInt003Service.getBussinessEntityByWorkflowId(workflowId);
        	//划款指令
        	if (!StringUtil.isEmptyList(entity.getBussTrdTransferOrder())) {
              model.addAttribute("entity_bussTrdTransferOrder",
                  JsonUtils.object2Json(entity.getBussTrdTransferOrder().get(0)));
        	}
        	//add by yzg 撤销部分数据没带入流程和查看统一逻辑 20200310 start
        	if(StringUtil.equals(entity.getOperateType(), "revoke")) {
        		entity = finProductPayInt003Service.selectProdInfo(entity.getFinprodId(),entity.getBussId());
        		entity.setOperateType("revoke");
        		// 划款信息
                List<BussTrdTransferOrderBase> list = bussSettleOrderCommService.selectOrdersByBizCode(entity.getBussId());
                //划款指令
                if (!StringUtil.isEmptyList(list)) {
                  model.addAttribute("entity_bussTrdTransferOrder",
                      JsonUtils.object2Json(list.get(0)));
                }
        		List<ReduceInfoDto> intDetailDataList= new ArrayList<ReduceInfoDto>();
                //如果是分层产品的付息
                if(DictCodeList.是否_是.getValue().equals(entity.getIsLay())) {
                	List<FinProductPayInt002IntDetailDto> intDetailDataList1 = finProductPayInt003Service.selectLayIntDetailDataList(entity.getFinprodId(),entity.getBussId());
                	convertEntity2OtherEntity(intDetailDataList1,intDetailDataList);
                	entity.setIntDetailDataList(intDetailDataList);
                }else {
                	ReduceInfoDto intDetailDto = new ReduceInfoDto();
                	intDetailDto.setRemainPrinAmt(entity.getPrinAmt());
                	intDetailDto.setInterest(entity.getTheoryCusProfit());
                	intDetailDto.setActInterest(entity.getActCusProfit());
                	intDetailDataList.add(intDetailDto);
                	entity.setIntDetailDataList(intDetailDataList);
                }
        	}
        	//add by yzg 撤销部分数据没带入流程和查看统一逻辑 20200310 end
            // 设置到model中
            model.addAttribute("entity", entity);
            model.addAttribute(GlobalConstant.WORKFLOW_ID,
                    this.getParam(GlobalConstant.WORKFLOW_ID));
        }// 双击打开
        else if (StringUtil.isNotEmpty(totalTradeId) && StringUtil.isNotEmpty(finProdId)) {
            FinProductPayInt003Dto entity = finProductPayInt003Service.selectProdInfo(finProdId,totalTradeId);
            // 划款信息
            List<BussTrdTransferOrderBase> list = bussSettleOrderCommService.selectOrdersByBizCode(totalTradeId);
            //划款指令
            if (!StringUtil.isEmptyList(list)) {
              model.addAttribute("entity_bussTrdTransferOrder",
                  JsonUtils.object2Json(list.get(0)));
            }
            
            List<ReduceInfoDto> intDetailDataList= new ArrayList<ReduceInfoDto>();
            //如果是分层产品的付息
            if(DictCodeList.是否_是.getValue().equals(entity.getIsLay())) {
            	List<FinProductPayInt002IntDetailDto> intDetailDataList1 = finProductPayInt003Service.selectLayIntDetailDataList(finProdId,totalTradeId);
            	convertEntity2OtherEntity(intDetailDataList1,intDetailDataList);
            	entity.setIntDetailDataList(intDetailDataList);
            }else {
            	ReduceInfoDto intDetailDto = new ReduceInfoDto();
            	intDetailDto.setRemainPrinAmt(entity.getPrinAmt());
            	intDetailDto.setInterest(entity.getTheoryCusProfit());
            	intDetailDto.setActInterest(entity.getActCusProfit());
            	intDetailDataList.add(intDetailDto);
            	entity.setIntDetailDataList(intDetailDataList);
            }
            String operateType = getParam("operateType");
            entity.setOperateType(operateType);
            model.addAttribute(GlobalConstant.ENTITY, entity);
        }
        return super.init(model);
    }

    private void convertEntity2OtherEntity(List<FinProductPayInt002IntDetailDto> intDetailDataList1,
			List<ReduceInfoDto> intDetailDataList2) {
    	if(intDetailDataList1 == null || intDetailDataList1.isEmpty()) {
    		return;
    	}
    	intDetailDataList2.clear();
    	
    	for(FinProductPayInt002IntDetailDto tmp : intDetailDataList1) {
    		ReduceInfoDto newEnti = new ReduceInfoDto();
    		newEnti.setRemainPrinAmt(tmp.getIntAccrPrinAmt());
    		newEnti.setProdStyle(tmp.getProdType());
    		newEnti.setInterest(tmp.getTheoryInt());
    		newEnti.setActInterest(tmp.getActInt());
    		newEnti.setFinprodId(tmp.getSonFinprodId());
    		intDetailDataList2.add(newEnti);
    	}
	}

	/**
     * 查询利息明细信息
     *
     * @return 利息明细信息
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findIntDetailInfo")
    @ResponseBody
    public List<ReduceInfoDto> findIntDetailInfo() throws Exception {
        FinProductPayInt003Dto inputDto = BeanUtil.mapToObject(RequestUtil.getPageParam(), FinProductPayInt003Dto.class);
        return finProductPayInt003Service.findIntDetailInfo(RequestUtil.getPageParam());
    }

    /**
     * 画面联动
     * 2.应付客户收益 = sum（应付利息）
     * 3.实付客户收益 = sum（实付利息）
     *
     * @param inputDto 参数
     * @return 画面联动结果
     * @throws Exception Exception
     */
    @RequestMapping("calcCusProfit")
    @ResponseBody
    public FinProductPayInt002Dto calcCusProfit(@RequestBody FinProductPayInt002Dto inputDto)
            throws Exception {
        return finProductPayInt003Service.calcCusProfit(inputDto);
    }

    /**
     * 保存提交前数据检查
     *
     * @param dto 画面参数
     * @param result valid结果
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @RequestMapping("check")
    @ResponseBody
    public AjaxResult check(@RequestBody @Validated FinProductPayInt003Dto dto, BindingResult result) {
        AjaxResult ajaxResult=  new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
		//判断方案是否在流程中

//            ProcessMark processMarkb = new ProcessMark();
//            processMarkb.setPageId("FinProductPayInt003");
//            processMarkb.setBusinessDataPk1(dto.getFinprodId());
//            processMarkb.setBusinessDataPk5(dto.getBusiType());
//            List<String> resultListb = leaveMarkProcessService.getBusinessDataInFlow(processMarkb);
//            if (!StringUtil.isEmptyList(resultListb)) {
//                errorList.add(new ValidMessage(this.getMessage("COMMON_E_0021", dto.getFinprodId(), dto.getBusiType(), dto.getPayintStartDateStr())));
//                ajaxResult.setErrorList(errorList);
//                return ajaxResult;
//            }

        WflValidInputParameter inputParameter = new WflValidInputParameter();
        inputParameter.setPageId("FinProductPayInt003");
        inputParameter.setBusinessDataPk1(dto.getFinprodId());
        inputParameter.setBusinessDataPk5(dto.getBusiType());
        if(StringUtil.isNotEmpty(dto.getWorkflowId())) {
            inputParameter.setWorkflowId(dto.getWorkflowId());
        }
        if(null != mstWorkflowCommonService && mstWorkflowCommonService.isInWflProcess(inputParameter)){
            errorList.add(new ValidMessage(this.getMessage("COMMON_E_0021", dto.getFinprodId(), dto.getBusiType(), dto.getPayintStartDateStr())));
            ajaxResult.setErrorList(errorList);
            return ajaxResult;
        }

        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存提交
     *
     * @param dto 画面参数
     * @return 保存提交结果
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("save")
    @ResponseBody
    AjaxResult save(@RequestBody FinProductPayInt003Dto dto) throws Exception {
        if (StringUtil.isBlank(dto.getBussId())) {
            if (baseGetBusinessId != null) {
                BussinessCodeParameter codeParameter = new BussinessCodeParameter();
                codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_收益型产品中间付息管理);
                dto.setBussId(baseGetBusinessId.buildBussinessCode(codeParameter));
            }
            else {
                dto.setBussId(IDGenerator.generate());
            }
        }
        ProcessParameter<FinProductPayInt003Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<FinProductPayInt003Dto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        workFlowParameter.setPageId(this.getPageId());
        workFlowParameter.setElementId(this.getParam("elementId"));
        workFlowParameter.setBusinessCode(dto.getBussId());
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_收益型产品中间付息管理.getValue());
        workFlowParameter.setComment(
        	entity -> this.getMessage("FinProductPayInt003.prodAbbr") + "(" + entity.getFinprodAbbr() + ")"+
            this.getMessage("FinProductPayInt003.payDate") + "(" + DateUtil.format(entity.getPayDate())+ ")"+
            this.getMessage("FinProductPayInt003.actCusProfit") + "(" + String.valueOf(entity.getActCusProfit())+ ")");
        String msg = finProductPayInt003Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    /**
     * 
     * 根据产品代码查询基本信息
     * @param finprodId
     * @return 基本信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryProdInfoByprodId")
    public FinProductBuild002Dto queryProdInfoByFinprodId(String finprodId) {
        return finProductBuild002Service.queryProdInfoByFinprodId(finprodId);
    }
    /**
     * 产品代码联想下拉框
     * 
     * @return 产品代码列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryProdIdInfoIssueSuc")
    public List<SelectOptionDto> queryProdIdInfoIssueSuc() {
        return finProductPayInt003Service.queryProdIdInfoIssueSuc(RequestUtil.getPageParam());
    }
    
}
