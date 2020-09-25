/*
 * 文件名：FinProductExpire002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productduration.expire.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
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
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.component.business.settle.BussSettleOrderCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.component.business.trade.impl.BussTrdAvailableCashQueryImplService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.product.addreduce.ReduceInfoDto;
import com.joyintech.tams.invest.product.productduration.expire.dto.FinProductExpire002Dto;
import com.joyintech.tams.invest.product.productduration.expire.service.FinProductExpire002Service;
import com.joyintech.tams.invest.product.productduration.netproductduration.dto.FinNetProductDurationDto;
import com.joyintech.tams.invest.product.productduration.netproductduration.service.FinNetProductDurationService;
import com.joyintech.tams.invest.product.productelement.build.dto.FinProductBuild002Dto;
import com.joyintech.tams.invest.product.productelement.build.service.FinProductBuild002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 产品到期确认
 * 
 * @author zhangliang
 * @version 1.0 2019年1月7日
 * @see FinProductExpire002Controller
 * @since 1.0
 */
@Controller
@PageId(value = "FinProductExpire002")
@RequestMapping("/invest/product/productduration/expire/FinProductExpire002")
public class FinProductExpire002Controller extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductExpire002Service finProductExpire002Service;
    

    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;

    /**
     * {@linkplain}
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;
    /**
     * 查询托管户余额 add by dingjunfeng 
     */
    @Autowired
    private BussTrdAvailableCashQueryImplService bussTrdAvailableCashQueryImplService; 
    /**
     * 清算指令共通Service
     */
    @Autowired(required = false)
    private BussSettleOrderCommService bussSettleOrderCommService;
    
    @Autowired
    private FinProductBuild002Service finProductBuild002Service;
    
    /**
     * BussTrdTransferOrderService
     */
    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderService;
    /**
     * add by dingjunfeng 
     */
    @Autowired
    private FinNetProductDurationService finNetProductDurationService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	// 一体化对应etliu start
//        this.setSelectOptionToModel(model, DictCode.销售部门, DictCode.处理模式,
//            DictCode.币种, DictCode.是否, DictCode.划款类型);
        this.setSelectOptionToModel(model, DictCode.销售部门, DictCode.处理模式,DictCode.交易方向,
                DictCode.币种, DictCode.是否, DictCode.平安划款类型, DictCode.资产来源/* ,DictCode.目标客户一级分类*/);
     // 一体化对应etliu end
        model.addAttribute("YES", DictCodeList.是否_是.getValue());
        model.addAttribute("NO", DictCodeList.是否_否.getValue());
        model.addAttribute("F16", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        model.addAttribute("acctNoList", JsonUtils.object2Json(bussTrdTransferOrderService.queryAcctList(null)));
        model.addAttribute("basis360", DictCodeList.计息基础_A_360.getValue());
        /*model.addAttribute("A_360", DictCodeList.计息基础_A_360.getValue());
        model.addAttribute("yesFlag", DictCodeList.是否_是.getValue());
        model.addAttribute("noFlag", DictCodeList.是否_否.getValue());
        model.addAttribute("ps", DictCodeList.交易方向_产品到期.getValue());
        model.addAttribute("ps_fee", DictCodeList.交易方向_产品付费.getValue());
        model.addAttribute("finprodType", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        model.addAttribute("finprodTypeFix", DictCodeListExt1.金融产品类型_投管用_固定管理费.getValue());
        model.addAttribute("finprodTypeTrust", DictCodeListExt1.金融产品类型_投管用_托管费.getValue());
        model.addAttribute("finprodTypeSale", DictCodeListExt1.金融产品类型_投管用_销售费.getValue());
        model.addAttribute("feeTypeFix", DictCodeList.费用品种类型_固定管理费.getValue());
        model.addAttribute("feeTypeTrust", DictCodeList.费用品种类型_托管费.getValue());
        model.addAttribute("feeTypeSale", DictCodeList.费用品种类型_销售手续费.getValue());*/
//        model.addAttribute("ps", DictCodeList.交易方向_产品到期.getValue());
        model.addAttribute("finprodType", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
//        model.addAttribute("platDate", iPlatformDate.getSysDateStr());
        model.addAttribute("formTodoList", !WorkFlowUtil.isNotFromToDoList());
        String workflowId = this.getParam("workflowId");
        String totalTradeId = this.getParam("tradeId");//产品存续期查询双击
        String effectStatus = this.getParam("effectStatus");
        if(totalTradeId==null){
        	totalTradeId = RequestUtil.getParam("busiNo");//收益型产品到期赎回双击
        }
        // 代办
        if ((StringUtil.equals(DictCodeListExt1.有效状态_流程中.getValue(), effectStatus) || !WorkFlowUtil.isNotFromToDoList())
        		&& StringUtils.isNotEmpty(workflowId)) {
        	// 根据工作流与留痕处理提供的共通方法获取业务实体
        	FinProductExpire002Dto entity = finProductExpire002Service.getBussinessEntityByWorkflowId(workflowId);
        	//划款指令
        	if (!StringUtil.isEmptyList(entity.getBussTrdTransferOrder())) {
              model.addAttribute("entity_bussTrdTransferOrder",
                  JsonUtils.object2Json(entity.getBussTrdTransferOrder().get(0)));
           }
            // 设置到model中
            model.addAttribute("entity", entity);
        }// 查看模式
        else if (StringUtil.isNotEmpty(totalTradeId)) {
            FinProductExpire002Dto entity = null;
            if(!StringUtil.isNotEmpty(this.getParam("applyType"))){
                entity = finProductExpire002Service.queryProdExpireByTotleTradeId(totalTradeId);
            } else {
                entity = BeanUtil.mapToObject(RequestUtil.getPageParam(),
                    FinProductExpire002Dto.class);
                entity.setStartDate(DateUtil.parse(this.getParam("startDateStr")));
                entity.setEndDate(DateUtil.parse(this.getParam("endDateStr")));
                entity.setPayDate(DateUtil.parse(this.getParam("payDateStr")));
                entity.setMdate(DateUtil.parse(this.getParam("mdateStr")));
                //add by gcl 2019/3/20 start
                entity.setVdate(DateUtil.parse(this.getParam("vdateStr")));
                //add by gcl 2019/3/20 end
                entity.setmCusRevAmt(entity.getPrinamt());
                entity.setActCusProfit(entity.getActCusProfit());
                entity.setRemark(entity.getRemark());
            }
            // 划款信息
            List<BussTrdTransferOrderBase> list = bussSettleOrderCommService.selectOrdersByBizCode(
                entity.getBusiNo());
            entity.setBussTrdTransferOrder(list.stream().map(item -> {
                BussTrdTransferOrderDto orders = new BussTrdTransferOrderDto();
                BeanUtil.copyPropertiesIgnoreNull(orders, item);
                return orders;
            }).collect(Collectors.toList()));
            
            //FinProductExpire002Dto entity = finProductExpire002Service.getBussinessEntityByWorkflowId(workflowId);
            //划款指令
            if (!StringUtil.isEmptyList(entity.getBussTrdTransferOrder())) {
                model.addAttribute("entity_bussTrdTransferOrder",
                    JsonUtils.object2Json(entity.getBussTrdTransferOrder().get(0)));
            }
            
            //利息明细
            List<ReduceInfoDto> interestlist = finProductExpire002Service.queryInterest(entity.getBusiNo());
            if (!StringUtil.isEmptyList(interestlist)) {
            	//分档时展示列表
                entity.setFinProductExpireMInfoDtoList(interestlist);
                //modify by wm 修改应该客户利息赋值 2019-07-24 start
                //循环遍历利息明细，累加利息赋值给应付客户利息
                BigDecimal cusProfit = BigDecimal.ZERO;
                for (ReduceInfoDto reduceInfoDto : interestlist) {
					if(StringUtil.isNotEmpty(reduceInfoDto.getInterest())){
						cusProfit = cusProfit.add(reduceInfoDto.getInterest());
					}
				}
                entity.setCusProfit(cusProfit);
                //entity.setCusProfit(interestlist.get(0).getInterest());
                //modify by wm 修改应该客户利息赋值 2019-07-24 end
            }
            
            // 设置到model中
            String operateType = getParam("operateType");
            entity.setOperateType(operateType);
            entity.setEffectStatus(effectStatus);
            model.addAttribute(GlobalConstant.ENTITY, entity);
            model.addAttribute("tradeId", entity.getBusiNo());
        } /*// 确认模式
        else {
            FinProductExpire002Dto entity = BeanUtil.mapToObject(RequestUtil.getPageParam(),
                FinProductExpire002Dto.class);
            entity.setStartDate(DateUtil.parse(this.getParam("startDateStr")));
            entity.setEndDate(DateUtil.parse(this.getParam("endDateStr")));
            entity.setPayDate(DateUtil.parse(this.getParam("payDateStr")));
            model.addAttribute(GlobalConstant.ENTITY, entity);
        }*/

        return super.init(model);
    }

    /**
     * 根据金融产品代码查询到期信息
     * 
     * @return 到期信息
     * @see
     * @since 1.0
     */
    /*@ResponseBody
    @RequestMapping("queryMInfoListByFinprodId")
    public List<FinProductExpireMInfoDto> queryMInfoListByFinprodId() {
        if (!WorkFlowUtil.isNotFromToDoList()) {
            ProcessMark pro = finProductExpire002Service.getBusinessInfo(
                this.getParam(GlobalConstant.WORKFLOW_ID));
            if (pro != null) {
                FinProductExpire002Dto entity = (FinProductExpire002Dto)JsonUtils.json2Object(
                    pro.getBusinessData(), FinProductExpire002Dto.class);
                if (entity != null) {
                    return entity.getFinProductExpireMInfoDtoList();
                }
            }
        }
        else if (StringUtil.isNotEmpty(this.getParam("tradeId"))) {
            return finProductExpire002Service.queryMInfoListByTradeId(this.getParam("tradeId"));
        }
        return finProductExpire002Service.queryMInfoListByFinprodId(RequestUtil.getPageParam());
    }*/

    /**
     * 根据金融产品代码查询费用信息
     * 
     * @return 费用信息
     * @see
     * @since 1.0
     */
    /*@ResponseBody
    @RequestMapping("queryFeeInfoListByFinprodId")
    public List<FinProductExpireFeeInfoDto> queryFeeInfoListByFinprodId() {
        if (!WorkFlowUtil.isNotFromToDoList()) {
            ProcessMark pro = finProductExpire002Service.getBusinessInfo(
                this.getParam(GlobalConstant.WORKFLOW_ID));
            if (pro != null) {
                FinProductExpire002Dto entity = (FinProductExpire002Dto)JsonUtils.json2Object(
                    pro.getBusinessData(), FinProductExpire002Dto.class);
                if (entity != null) {
                    return entity.getFinProductExpireFeeInfoDtoList();
                }
            }
        }
        else if (StringUtil.isNotEmpty(this.getParam("tradeId"))) {
            return finProductExpire002Service.queryFeeInfoListByTradeId(this.getParam("tradeId"));
        }
        return finProductExpire002Service.queryFeeInfoListByFinprodId(RequestUtil.getPageParam());
    }*/

    /**
     * 根据金融产品代码查询分行信息
     * 
     * @return 分行信息
     * @see
     * @since 1.0
     */
    /*@ResponseBody
    @RequestMapping("queryBranchInfoListByFinprodId")
    public List<FinProductExpireBranchInfoDto> queryBranchInfoListByFinprodId() {
        if (!WorkFlowUtil.isNotFromToDoList()) {
            ProcessMark pro = finProductExpire002Service.getBusinessInfo(
                this.getParam(GlobalConstant.WORKFLOW_ID));
            if (pro != null) {
                FinProductExpire002Dto entity = (FinProductExpire002Dto)JsonUtils.json2Object(
                    pro.getBusinessData(), FinProductExpire002Dto.class);
                if (entity != null) {
                    return entity.getFinProductExpireBranchInfoDtoList();
                }
            }
        }
        else if (StringUtil.isNotEmpty(this.getParam("tradeId"))) {
            return finProductExpire002Service.queryBranchInfoListByTradeId(
                this.getParam("tradeId"));
        }
        return finProductExpire002Service.queryBranchInfoListByFinprodId(
            RequestUtil.getPageParam());
    }*/
    
    @ResponseBody
    @RequestMapping("checkzl")
    public AjaxResult checkzl(@Valid @RequestBody FinProductExpire002Dto dto,BindingResult br) {
    			AjaxResult result = 	commonCheck(br, dto.getOperateType());
    			Map<String,Object> map = new HashMap<String,Object>();
    			 List<JoyinMessage> joyinMessages = new ArrayList<>();
    			   if(result.getErrorList() != null && result.getErrorList().size()>0 ){
    		        	return  result;
    		        }
    			 if(StringUtil.isNotEmpty(dto.getPtlId())){
                	 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                  Integer counts =  finProductExpire002Service.checkpayAmt("", formatter.format(dto.getMdate()),dto.getPtlId(),dto.getFinprodId());
                  map.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
                  map.put("tradeDate", dto.getPayDateStr());
                  map.put("ccy", dto.getCcy());
                  map.put("portfolioId",dto.getPtlId());
                  BigDecimal secAcctBalance =  bussTrdAvailableCashQueryImplService.getSecManageAvailableCash(map);
                  if(DictCodeList.交易方向_产品到期.getValue().equals(dto.getDealType())||DictCodeList.交易方向_提前终止.getValue().equals(dto.getDealType())){
                	  FinNetProductDurationDto netProductDurationDto = new FinNetProductDurationDto();
                	  netProductDurationDto.setBranch(0l);
                	  netProductDurationDto.setFinprodId(dto.getFinprodId());
                	  netProductDurationDto.setPortfolioId(dto.getPtlId());
                	  netProductDurationDto.setNdate(iPlatformDate.getSysDate());
                	  Integer bokPositionPrd = finNetProductDurationService.queryMaxDatePtlSecPosition(netProductDurationDto);
                     	if(bokPositionPrd!=null && bokPositionPrd>0){
                     	   joyinMessages.add(new JoyinMessage(this.getMessage("产品资产未处置结束！"),MessageType.确认消息));
                      	}
                	 /*  if(map.get("finProductFeePay001Dto") != null){
                     	 joyinMessages.add(new JoyinMessage(this.getMessage("存在费用尚未支付，请先支付费用"),MessageType.确认消息));
                       }*/
                       if(counts != null && counts >0){
                     		 joyinMessages.add(new JoyinMessage(this.getMessage("存在费用尚未支付，请先支付费用"),MessageType.确认消息));
                       }  
                  if(secAcctBalance.compareTo(dto.getmCusRevAmt().add(dto.getActCusProfit()))<0){
                	  joyinMessages.add(new JoyinMessage(this.getMessage("产品剩余资金不足，请核实"),MessageType.确认消息));
                  }
                  //delete by wm 因为有浮动管理费差额中收，故肯定有多余的自己，去掉提示
//                  else if(secAcctBalance.compareTo(dto.getmCusRevAmt().add(dto.getActCusProfit()))>0){
//                	  joyinMessages.add(new JoyinMessage(this.getMessage("产品有多余剩余资金，请核实"),MessageType.确认消息));
//                  }
                }
        	   } 
    			 if(StringUtil.isNotEmpty(joyinMessages)){
    				 result.setJoyinMessages(joyinMessages);
    			 }
                  
    			return result;
    }
    /**
     * 校验产品是否有未支付的费用
     * @param dto
     * @param br
     * @return
     */
   /* @ResponseBody
    @RequestMapping("checkAmt")
    public AjaxResult checkAmt(@Valid @RequestBody FinProductExpire002Dto dto,BindingResult br) {
    	  AjaxResult result = new AjaxResult();
    	     if(StringUtil.isNotEmpty(dto.getPtlId())){
            	 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
              Map<String,Object> map =  finProductExpire002Service.checkpayAmt("", formatter.format(dto.getMdate()));
              FinProductFeePay001Dto dto1 =   (FinProductFeePay001Dto)map.get("finProductFeePay001Dto1");
              if(map.get("finProductFeePay001Dto") != null){
            	  if(dto1 == null){
            		 // result.setData(this.getMessage("存在费用尚未支付，请先支付费用"));
            	  }else if(dto1.getFeeName() == null){
            		  result.setData(this.getMessage("存在费用尚未支付，请先支付费用"));
            	  }
            	 
              }
              if(DictCodeList.交易方向_产品到期.getValue().equals(dto.getDealType())){
              map.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
              map.put("tradeDate", dto.getPayDateStr());
              map.put("ccy", dto.getCcy());
              map.put("portfolioId",dto.getPtlId());
              BigDecimal secAcctBalance =  bussTrdAvailableCashQueryImplService.getSecManageAvailableCash(map);
              if(secAcctBalance.compareTo(dto.getmCusRevAmt().add(dto.getActCusProfit()))<0){
            	  result.setData(this.getMessage("“产品剩余资金不足，请核实"));
              }else{
            	  result.setData(this.getMessage("产品剩余资金为（"+secAcctBalance+"），请核实”")); 
              }
            }
    	   } 
              
    	return result;
    }*/
    /**
     * 保存check
     * 
     * @param dto 费用新建信息
     * @param  验证消息
     * @return 验证结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid @RequestBody FinProductExpire002Dto dto,BindingResult br) {
        //return this.commonCheck(br, dto.getOperateType());
    	 if (br != null && br.hasErrors()) {
             return new AjaxResult(br);
         }
        AjaxResult result = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        
        if (!StringUtil.isNotEmpty(dto.getFinprodId())) {
            errorList.add(new ValidMessage("finprodId", this.getMessage("FIN_PROD_E_0001",
                this.getMessage("FinProductExpire002.prodId"))));
        }
        if (!StringUtil.isNotEmpty(dto.getMdate())) {
            errorList.add(new ValidMessage("mdate", this.getMessage("FIN_PROD_E_0001",
                this.getMessage("FinProductExpire002.mdate"))));
        }
        else{
            /*if (dto.getMdate().compareTo(iPlatformDate.getSysDate()) <= 0) {
                errorList.add(new ValidMessage("mdate",
                    this.getMessage("BUSS_COM_E_0036",
                        this.getMessage("FinProductExpire002.mdate"),
                        this.getMessage("SysCommon.platDate"))));
            }*/
        	//add by gcl 2019/4/08 start
        	if(StringUtil.isNotEmpty(dto.getMdate())&&StringUtil.isNotEmpty(dto.getVdate())){
            if (dto.getMdate().compareTo(dto.getVdate()) <= 0) {
                errorList.add(new ValidMessage("mdate",
                    this.getMessage("BUSS_COM_E_0036",
                        this.getMessage("FinProductExpire002.mdate"),
                        this.getMessage("FinProductExpire002.vdate"))));
            }
          }
        	//add by gcl 2019/4/08 end
        }
       
        if (!StringUtil.isNotEmpty(dto.getPayDate())) {
            errorList.add(new ValidMessage("payDate", this.getMessage("FIN_PROD_E_0001",
                this.getMessage("FinProductExpire002.payDate"))));
        }
//        if (!StringUtil.isNotEmpty(dto.getPrinamt())) {
//            errorList.add(new ValidMessage("prinamt", this.getMessage("FIN_PROD_E_0001",
//                this.getMessage("FinProductExpire002.mInfo.mPrinamt"))));
//        }
        if (!StringUtil.isNotEmpty(dto.getmCusRevAmt())) {
            errorList.add(new ValidMessage("mCusRevAmt", this.getMessage("FIN_PROD_E_0001",
                this.getMessage("FinProductExpire002.mInfo.mCusRevAmt"))));
        }
//        if (!StringUtil.isNotEmpty(dto.getCusProfit())) {
//            errorList.add(new ValidMessage("cusProfit", this.getMessage("FIN_PROD_E_0001",
//                this.getMessage("FinProductExpire002.mInfo.cusProfit"))));
//        }
        if (!StringUtil.isNotEmpty(dto.getActCusProfit())) {
            errorList.add(new ValidMessage("actCusProfit", this.getMessage("FIN_PROD_E_0001",
                this.getMessage("FinProductExpire002.mInfo.actCusProfit"))));
        }
        
        
        if(dto.getBussTrdTransferOrder().size()==0){
                errorList.add(new ValidMessage("recAcctNo", this.getMessage("请生成指令!")));
        }
        else{
            //校验收付款账户
        	BussTrdTransferOrderDto order = dto.getBussTrdTransferOrder().get(0);
        	if(order!=null){
        		if(!StringUtil.isNotEmpty(order.getRecAcctNo())){
        			errorList.add(new ValidMessage("recAcctNo", this.getMessage("收款账户不能为空")));
        		}
        	}
        }
        /*if (!StringUtil.isNotEmpty(dto.getBranch())) {
            errorList.add(new ValidMessage("branch", this.getMessage("FIN_PROD_E_0001",
                this.getMessage("FinProductExpire002.mInfo.table.branch"))));
        }
        if("Y".equals(dto.getIsLay())){
        	if (!StringUtil.isNotEmpty(dto.getProdType())) {
                errorList.add(new ValidMessage("prodType", this.getMessage("FIN_PROD_E_0001",
                    this.getMessage("FinProductExpire002.mInfo.table.prodType"))));
            }
        }
        if (!StringUtil.isNotEmpty(dto.getStartDate())) {
            errorList.add(new ValidMessage("startDate", this.getMessage("FIN_PROD_E_0001",
                this.getMessage("FinProductExpire002.mInfo.table.calcStartDate"))));
        }
        if (!StringUtil.isNotEmpty(dto.getEndDate())) {
            errorList.add(new ValidMessage("endDate", this.getMessage("FIN_PROD_E_0001",
                this.getMessage("FinProductExpire002.mInfo.table.calcEndDate"))));
        }
        if (StringUtil.isNotEmpty(dto.getEndDate())&&StringUtil.isNotEmpty(dto.getStartDate())) {
            if (dto.getEndDate().compareTo(dto.getStartDate()) <= 0) {
                errorList.add(new ValidMessage("endDate",
                    this.getMessage("BUSS_COM_E_0036",
                        this.getMessage("FinProductExpire002.mInfo.table.calcEndDate"),
                        this.getMessage("FinProductExpire002.mInfo.table.calcStartDate"))));
            }
        }
        if (!StringUtil.isNotEmpty(dto.getTdyPrinAmt())) {
            errorList.add(new ValidMessage("tdyPrinAmt", this.getMessage("FIN_PROD_E_0001",
                this.getMessage("FinProductExpire002.mInfo.table.prinamt"))));
        }
        if (!StringUtil.isNotEmpty(dto.getIntAmt())) {
            errorList.add(new ValidMessage("intAmt", this.getMessage("FIN_PROD_E_0001",
                this.getMessage("FinProductExpire002.mInfo.table.intAmt"))));
        }
        if (!StringUtil.isNotEmpty(dto.getActIntAmt())) {
            errorList.add(new ValidMessage("actIntAmt", this.getMessage("FIN_PROD_E_0001",
                this.getMessage("FinProductExpire002.mInfo.table.actIntAmt"))));
        }*/
        if (StringUtil.isEmptyList(errorList)) {
            String btnName = "";
            if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SAVE,
                dto.getOperateType())) {
                btnName = "SysCommon.save";
            }
            else if (StringUtil.equalsIgnoreCase(BussinessConstant.REVOKE,
                    dto.getOperateType())) {
                btnName = "SysCommon.revoke";
            }
            else if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SUBMIT,
                dto.getOperateType())) {
                btnName = "SysCommon.submit";
            }
            result.setData(this.getMessage("COMMON_C_0001", this.getMessage(btnName)));
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
     * @param  验证信息
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody FinProductExpire002Dto dto,BindingResult br)
        throws Exception {
        ProcessParameter<FinProductExpire002Dto> processParameter = new ProcessParameter<FinProductExpire002Dto>();
        WorkFlowParameter<FinProductExpire002Dto> params = new WorkFlowParameter<FinProductExpire002Dto>();
        params.setElementId(dto.getElementId());
        params.setPageId(this.getPageId());
        if (baseGetBusinessId != null) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_收益型产品赎回到期管理);
            dto.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
            dto.setBusiNo(dto.getTradeId());//这个字段是pk2撤销时用到
        }

        params.setBusinessCode(dto.getTradeId());
        params.setBusinessType(DictCodeList.共通业务类型_收益型产品赎回到期管理.getValue());
        // 设置工作流注解
        params.setComment(
            entity -> entity.getFinprodAbbr() + "(" + entity.getFinprodId() + ")"
                      + GlobalConstant.SPLIT + DictCodeList.交易方向_产品到期.getCodeName()
                      + GlobalConstant.SPLIT + this.getMessage("FinProductExpire002.payDate") + "("
                      + entity.getPayDateStr() + ")" + GlobalConstant.SPLIT
                      + this.getMessage("FinProductExpire002.mInfo.mCusRevAmt") + "("
                      + ArithmeticUtil.doAdd(dto.getReduceAmt(),dto.getCusProfit()) + ")");
        processParameter.setWorkFlowParameter(params);
        
        if (BussinessConstant.SUBMIT.equals(dto.getOperateType())) {
            finProductExpire002Service.setFinProductExpireBaseTrdDto(dto);
        }
        // 调用工作流事件
        String msg = finProductExpire002Service.start(processParameter, dto);
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
    @RequestMapping("queryProdInfoByProdId")
    public FinProductBuild002Dto queryProdInfoByProdId(String finprodId) {
        return finProductBuild002Service.queryProdInfoByProdId(finprodId);
    }
    
    /*@ResponseBody
    @RequestMapping("queryProdInfoByMdate")
    public FinProductExpire002Dto queryProdInfoByMdate(FinProductExpire002Dto dto) {
        return finProductExpire002Service.queryProdInfoByMdate(dto);
    }*/
    
    @ResponseBody
    @RequestMapping("check2")
    public AjaxResult check2(@RequestBody @Validated({
        ValidateGroupEdit.class}) FinProductExpire002Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    @ResponseBody
    @RequestMapping("queryProdPs")
    public String queryProdPs(String finprodId,Date endDate) {
        FinProductExpire002Dto dto = new FinProductExpire002Dto();
        dto.setFinprodId(finprodId);
        dto.setMdate(endDate);
        return finProductExpire002Service.getPs(dto);
    }
}
