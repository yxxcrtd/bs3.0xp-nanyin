/*
 * 文件名：FinProductEstablish002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：ejupiter
 * 修改时间：2018年12月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productduration.establish.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.constant.DictCodeListExt1;

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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
//import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFeeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
//import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.productduration.establish.dto.FinProductEstablish002Dto;
import com.joyintech.tams.invest.product.productduration.establish.dto.FinProductEstablishBranchDto;
import com.joyintech.tams.invest.product.productduration.establish.dto.FinProductEstablishLayerDto;
import com.joyintech.tams.invest.product.productduration.establish.service.FinProductEstablish002Service;
import com.joyintech.tams.invest.product.productelement.build.dto.FinProductBuild002Dto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 产品成立确认
 *
 * @author zhangliang
 * @version 1.0 2018年12月20日
 * @see FinProductEstablish002Controller
 * @since 1.0
 */
@Controller
@PageId(value = "FinProductEstablish002")
@RequestMapping("/invest/product/productduration/establish/FinProductEstablish002")
public class FinProductEstablish002Controller extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductEstablish002Service finProductEstablish002Service;

    /**
     * {@linkplain}
     */
//    @Autowired
//    private BussCommonMapperDelegateService bussCommonMapperDelegateService;

    /**
     * {@linkplain}
     */
//    @Autowired(required = false)
//    private BussTrdTransferOrderService bussTrdTransferOrderService;

    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

    /**
     * 平台日基础组件
     */
//    @Autowired(required = false)
//    private IPlatformDate platformDate;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	setSelectOptionToModel(model, DictCode.计息基础, DictCode.币种, DictCode.是否,
				DictCode.平安划款类型,DictCode.交易方向);
//        model.addAttribute("YES_FLAG", DictCodeList.是否_是.getValue());
//		model.addAttribute("PAY_TYPE_01", DictCodeListExt1.平安划款类型_托管划款.getValue());
        model.addAttribute("ps", DictCodeList.交易方向_产品认购.getValue());
        model.addAttribute("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());

    	String finprodId = this.getParam("finprodId");
    	String tradeId = this.getParam("tradeId");//产品存续期查询双击时参数
    	if(tradeId==null) {
    	    tradeId =this.getParam("bussId");//收益型产品认申购双击参数
    	}
        String workflowId = this.getParam("workflowId");
        if (!StringUtil.isNotEmpty(finprodId) && WorkFlowUtil.isNotFromToDoList()) {
            FinProductEstablish002Dto entity = new FinProductEstablish002Dto();
            //默认平台日期
//            entity.setTradeDate(date);
            entity.setVdate(DateUtil.platDateFromCache());
//            entity.setMdate(DateUtil.addDays(date, 1));
            model.addAttribute("entity", entity);
        }
        else if (StringUtil.isNotEmpty(finprodId) && WorkFlowUtil.isNotFromToDoList()) {
            String effectStatus = this.getParam("effectStatus");
        	FinProductEstablish002Dto entity = finProductEstablish002Service.selectProdInfo(finprodId, tradeId , effectStatus);
        	String operateType = getParam("operateType");
            entity.setOperateType(operateType);
        	model.addAttribute("entity", entity);
        	//划款指令
//        	BussTrdTransferOrderDto bussTrdTransferOrderDto = finProductEstablish002Service.selectBussTrdTransferOrder(entity.getTradeId());
//        	if (bussTrdTransferOrderDto!=null) {
//              model.addAttribute("entity_bussTrdTransferOrder", JsonUtils.object2Json(bussTrdTransferOrderDto));
//          }
//        	List<BussTrdTransferOrderBase> result = bussSettleOrderCommMapper.selectOrdersByBizCode(tradeId, DictCodeListExt1.收支方向_支出.getValue());
        	List<BussTrdTransferOrderBase> result = finProductEstablish002Service.selectOrdersByBizCode(tradeId, effectStatus);
            if(result != null && !result.isEmpty()) {
                  model.addAttribute("entity_bussTrdTransferOrder", JsonUtils.object2Json(result.get(0)));
            }
        }
        else if (!WorkFlowUtil.isNotFromToDoList() && StringUtils.isNotEmpty(workflowId)) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
        	FinProductEstablish002Dto entity = finProductEstablish002Service.getBussinessEntityByWorkflowId(workflowId);
        	//划款指令
        	if (!StringUtil.isEmptyList(entity.getBussTrdTransferOrder())) {
              model.addAttribute("entity_bussTrdTransferOrder",
                  JsonUtils.object2Json(entity.getBussTrdTransferOrder().get(0)));
          }
            // 设置到model中
            model.addAttribute("entity", entity);
        }
        return super.init(model);
    }

    /**
     * 根据金融产品代码查询费用列表
     *
     * @return 费用列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryFeeInfoByFinprodId")
    public List<BussFinProductFeeInfoDto> queryFeeInfoByFinprodId() {
        if (!WorkFlowUtil.isNotFromToDoList()) {
            ProcessMark pro = finProductEstablish002Service.getBusinessInfo(
                this.getParam(GlobalConstant.WORKFLOW_ID));
            if (pro != null) {
                FinProductEstablish002Dto entity = JsonUtils.json2Object(
                    pro.getBusinessData(), FinProductEstablish002Dto.class);
                return entity.getBussFinProductFeeInfoDtoList();
            }
        }
        return finProductEstablish002Service.queryFeeInfoByFinprodId(this.getParam("finprodId"));
    }

    /**
     * 根据金融产品代码查询分层金额明细
     *
     * @return 分层金额明细
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryLayAmtDetailByFinprodId")
    public List<FinProductEstablishLayerDto> queryLayAmtDetailByFinprodId() {
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        if (StringUtil.isNotEmpty(workflowId) || StringUtil.equals(DictCodeListExt1.有效状态_流程中.getValue(), this.getParam("effectStatus"))) {
            ProcessMark pro = finProductEstablish002Service.getBusinessInfo(workflowId);
            if (pro != null) {
                FinProductEstablish002Dto entity = JsonUtils.json2Object(
                    pro.getBusinessData(), FinProductEstablish002Dto.class);
                return entity.getFinProductEstablishLayerDtoList();
            }
        } 
        else if(StringUtil.isNotEmpty(this.getParam("tradeId"))){
            return finProductEstablish002Service.queryLayAmtDetailByBussId(this.getParam("tradeId"));
        }
        return finProductEstablish002Service.queryLayAmtDetailByFinprodId(RequestUtil.getPageParam());
    }

    /**
     * 根据金融产品代码查询分行募集明细
     *
     * @return 分行募集明细
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryBranchRaiseInfoByFinprodId")
    public List<FinProductEstablishBranchDto> queryBranchRaiseInfoByFinprodId() {
        if (!WorkFlowUtil.isNotFromToDoList()) {
            ProcessMark pro = finProductEstablish002Service.getBusinessInfo(
                this.getParam(GlobalConstant.WORKFLOW_ID));
            if (pro != null) {
                FinProductEstablish002Dto entity = JsonUtils.json2Object(
                    pro.getBusinessData(), FinProductEstablish002Dto.class);
                return entity.getFinProductEstablishBranchDtoList();
            }
        }
        else if (StringUtil.isNotEmpty(this.getParam("bussId"))) {
            return finProductEstablish002Service.queryBranchRaiseInfoByBussId(
                this.getParam("bussId"));
        }
        else {
            return finProductEstablish002Service.queryBranchRaiseInfoByFinprodId(
                RequestUtil.getPageParam());
        }

        return Collections.emptyList();
    }

    /**
     * 画面check
     *
     * @param dto 验证对象
     * @param br 验证信息
     * @return 验证结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) FinProductEstablish002Dto dto, BindingResult br, BindingResult rst) {
        AjaxResult ajaxResult= commonCheck(rst, dto.getOperateType());
        if(ajaxResult.getErrorList() != null && ajaxResult.getErrorList().size()>0 ){
        	return  ajaxResult;
        }
    	if (br != null && br.hasErrors()) {
            return new AjaxResult(br);
        }
    	AjaxResult result = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        if (!StringUtil.isNotEmpty(dto.getFinprodId())) {
            errorList.add(new ValidMessage("finprodId", this.getMessage("FIN_PROD_E_0001",
                this.getMessage("FinProductEstablish002.finprodId"))));
        }
        if (!StringUtil.isNotEmpty(dto.getFinprodAbbr())) {
            errorList.add(new ValidMessage("finprodAbbr", this.getMessage("FIN_PROD_E_0001",
                this.getMessage("FinProductEstablish002.finprodAbbr"))));
        }
        if (!StringUtil.isNotEmpty(dto.getFinprodName())) {
            errorList.add(new ValidMessage("finprodName", this.getMessage("FIN_PROD_E_0001",
                this.getMessage("FinProductEstablish002.finprodName"))));
        }
        if (!StringUtil.isNotEmpty(dto.getCcy())) {
            errorList.add(new ValidMessage("ccy", this.getMessage("FIN_PROD_E_0001",
                this.getMessage("FinProductEstablish002.ccy"))));
        }
        if (!StringUtil.isNotEmpty(dto.getVdate())) {
            errorList.add(new ValidMessage("vdate", this.getMessage("FIN_PROD_E_0001",
                this.getMessage("FinProductEstablish002.vdate"))));
        }
        if (!StringUtil.isNotEmpty(dto.getMdate())) {
            errorList.add(new ValidMessage("mdate", this.getMessage("FIN_PROD_E_0001",
                this.getMessage("FinProductEstablish002.mdate"))));
        }
        if (!StringUtil.isNotEmpty(dto.getBasis())) {
            errorList.add(new ValidMessage("basis", this.getMessage("FIN_PROD_E_0001",
                this.getMessage("FinProductEstablish002.basis"))));
        }
        //判断是否续投
        if(StringUtil.equals(dto.getContinueFlag(), DictCodeList.是否_是.getValue())){
        	/*代码注释 add by xy 2019-03-23 start 收益型产品认申购管理新建提交提示输入（页面没有）*/
//	        if (!StringUtil.isNotEmpty(dto.getLastAmt())) {
//	            errorList.add(new ValidMessage("lastAmt", this.getMessage("FIN_PROD_E_0001",
//	                this.getMessage("FinProductEstablish002.lastAmt"))));
//	        }
//	        if (!StringUtil.isNotEmpty(dto.getLastRed())) {
//	            errorList.add(new ValidMessage("lastRed", this.getMessage("FIN_PROD_E_0001",
//	                this.getMessage("FinProductEstablish002.lastRed"))));
//	        }
        	/*代码注释 add by xy 2019-03-23 end 收益型产品认申购管理新建提交提示输入（页面没有）*/
	        if (!StringUtil.isNotEmpty(dto.getLastAmtSub())) {
	            errorList.add(new ValidMessage("lastAmtSub", this.getMessage("FIN_PROD_E_0001",
	                this.getMessage("FinProductEstablish002.lastAmtSub"))));
	        }
	        if (!StringUtil.isNotEmpty(dto.getAccountingAmt())) {
	        	errorList.add(new ValidMessage("accountingAmt", this.getMessage("FIN_PROD_E_0001",
	        			this.getMessage("FinProductEstablish002.accountingAmt"))));
	        }
        }
        if (!StringUtil.isNotEmpty(dto.getCurrentSub())) {
            errorList.add(new ValidMessage("currentSub", this.getMessage("FIN_PROD_E_0001",
                this.getMessage("FinProductEstablish002.currentSub"))));
        }
        //update by wangcheng 2019/3/7
		if(DateUtil.compareDate(dto.getVdate(), dto.getMdate()) > 0){
			errorList.add(new ValidMessage("vdate", "产品成立日不能大于到期日!"));
    	}
		//add by dingjunfeng 2019-3-28
		if(DateUtil.compareDate(DateUtil.platDateFromCache(), dto.getMdate()) > 0){
			errorList.add(new ValidMessage("mdate", "产品到期日必须大于平台日期!"));
    	}

		if(DateUtil.compareDate(dto.getVdate(), dto.getVdate2())< 0){
			//add by wm 增加当认购时确认日小于起息日时的校验 2019-08-01 start
			if(StringUtil.equals( DictCodeList.交易方向_产品认购.getValue(), dto.getPs())){
				errorList.add(new ValidMessage("vdate", "认购交易时，确认日不能小于起息日!"));
			}
			//add by wm 增加当认购时确认日小于起息日时的校验 2019-08-01 end
			else{
				int num = finProductEstablish002Service.selectIsTrade(dto.getFinprodId());
				if(num>0){
					errorList.add(new ValidMessage("vdate", "申购交易,产品成立日不能小于起息日!"));
				}
			}
		}
        //校验收付款账户
        BussTrdTransferOrderDto order = null;
        	if(dto.getBussTrdTransferOrder().size()>0){
        		order = dto.getBussTrdTransferOrder().get(0);
        	}else{
        		errorList.add(new ValidMessage("bussTrdTransferOrder", this.getMessage("划款指令不能为空")));
        	}
        	if(order!=null){
        		if(!StringUtil.isNotEmpty(order.getRecAcctNo())){
        			errorList.add(new ValidMessage("recAcctNo", this.getMessage("收款账户不能为空")));
        		}
        	}

        if (StringUtil.isEmptyList(errorList)) {
        	String str1 = "";
        	String str2 = "";
        	String btnName = "";
        		////update by wangcheng 2019/3/7
        		if(!StringUtil.equals(BussinessConstant.REVOKE ,dto.getOperateType())&&  dto.getVdate().compareTo(dto.getVdate2())!=0){
        			int num = finProductEstablish002Service.selectIsTrade(dto.getFinprodId());
        			if(num==0){
        				str1 = "产品成立日和起息日不是同一天,请确认是否认购?";
        			}
            	}
        	if(order!=null){
	        	if(!StringUtil.isNotEmpty(order.getPayAcctNo())){
	        		str2 = "付款账户未填,";
	        	}
        	}
        	if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SAVE,
    				dto.getOperateType())) {
    			btnName = "请确认是否保存?";
    		}
    		else if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SUBMIT,
    				dto.getOperateType())) {
    			btnName = "请确认是否提交?";
    		}
    		else if (StringUtil.equalsIgnoreCase(BussinessConstant.REVOKE,
    				dto.getOperateType())) {
    			btnName = "请确认是否撤销?";
    		}
    		result.setData(this.getMessage(str1+str2+btnName));
        }
        else {
            result.setErrorList(errorList);
        }
        return result;
    }

    /**
     * 保存操作
     *
     * @param dto dto
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody FinProductEstablish002Dto dto)
        throws Exception {
    	BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
        bussinessCodeParameter.setCommonBusinessType(DictCodeList.共通业务类型_收益型产品认申购管理);
        dto.setTradeId(baseGetBusinessId !=null ? baseGetBusinessId.buildBussinessCode(bussinessCodeParameter):IDGenerator.generate());

        ProcessParameter<FinProductEstablish002Dto> processParameter = new ProcessParameter<FinProductEstablish002Dto>();
        WorkFlowParameter<FinProductEstablish002Dto> params = new WorkFlowParameter<FinProductEstablish002Dto>();
        params.setElementId(dto.getElementId());
        params.setPageId(this.getPageId());
        params.setBusinessCode(dto.getTradeId());
        params.setBusinessType(DictCodeList.共通业务类型_收益型产品认申购管理.getValue());
        // 设置工作流注解
        String message = "认购金额";
        if (DictCodeList.交易方向_产品申购.getValue().equals(dto.getPs())) {
            message = "本期申购";
        }
        String finalMessage = message;
        params.setComment(
            entity -> this.getMessage("FinProductEstablish002.finprodAbbr") + "("
                      + entity.getFinprodAbbr() + ")"
                      + this.getMessage("FinProductEstablish002.finprodId") + "("
                      + entity.getFinprodId() + ")"
                      + this.getMessage("FinProductEstablish002.vdate") + "("
                      + DateUtil.format(entity.getVdate()) + ")"
                      + finalMessage + "("
                      + entity.getCurrentSub() + ")");
        processParameter.setWorkFlowParameter(params);

        // 调用工作流事件
        String msg = finProductEstablish002Service.start(processParameter, dto);
        // 返回工作流错误信息
        return new AjaxResult(msg);
    }

    /**
     *
     * 根据金融产品代码查询基本信息
     * @param finprodId
     * @return 基本信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryProdInfoByFinprodId")
    public FinProductBuild002Dto queryProdInfoByFinprodId(String finprodId, String vdate) {
        return finProductEstablish002Service.queryProdInfoByFinprodId(finprodId, vdate);
    }

    /**
     * 画面check
     *
     * @param dto 验证对象
     * @param br 验证信息
     * @return 验证结果
     * @see
     * @since 1.0
     */
//    @ResponseBody
//    @RequestMapping("check")
//    public AjaxResult check(@RequestBody @Validated({
//        ValidateGroupEdit.class}) FinProductEstablish002Dto dto, BindingResult result) {
//    	return commonCheck(result, dto.getOperateType());
//    }

    /**
     * 产品代码联想下拉框
     *
     * @return 产品代码列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryProdIdInfoIssueSuc")
    public Page<SelectOptionDto> queryProdIdInfoIssueSuc() {
        return finProductEstablish002Service.queryProdIdInfoIssueSuc(RequestUtil.getPageParam());
    }
}
