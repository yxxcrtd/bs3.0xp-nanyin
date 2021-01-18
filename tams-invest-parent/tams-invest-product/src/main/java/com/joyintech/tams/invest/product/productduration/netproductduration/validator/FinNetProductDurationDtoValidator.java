/*
 * 文件名：FinNetProductDurationDtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zqz
 * 修改时间：2019年1月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.productduration.netproductduration.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.tams.common.base.dao.FinProductAddMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.PrdMdateAdjust;
import com.joyintech.tams.common.base.entity.PrdSaleParam;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.component.business.trade.BussTrdAvailableCashQueryService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.invest.product.productduration.establish.service.FinProductEstablish001Service;
import com.joyintech.tams.invest.product.productduration.netproductduration.dto.FinNetProductDurationDto;
import com.joyintech.tams.invest.product.productduration.netproductduration.dto.FinNetProductDurationLayerDto;
import com.joyintech.tams.invest.product.productduration.netproductduration.service.FinNetProductDurationService;
import com.joyintech.workflow.engine.service.LeaveMarkProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zqz
 * @version 1.0 2019年1月15日
 * @see FinNetProductDurationDtoValidator
 * @since 1.0
 */
@Component
public class FinNetProductDurationDtoValidator implements BeanValidator{

	@Autowired
    private FinNetProductDurationService finNetProductDurationService;
	
	@Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;
	//add by dingjunfeng
	@Autowired
	private FinProductEstablish001Service finProductEstablish001Service;
	
	@Autowired(required = false)
    private BussTrdAvailableCashQueryService bussTrdAvailableCashQueryService;
	

	@Autowired(required = false)
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;


    /**
     * mstWorkflowCommonService工作流组件
     */
    @Autowired(required = false)
    private LeaveMarkProcessService leaveMarkProcessService;
    @Autowired
    private FinProductAddMapper finProductAddMapper;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        FinNetProductDurationDto dto = (FinNetProductDurationDto) value;
        dto.setBranch(0l);
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        //收益类型：净值型、货币型
        String profitType = dto.getProfitType();
        //是否周期型：Y/N
        String isCycle = dto.getIsCycle();
        //交易类型：认购、申购、赎回等。。
        String dealType = dto.getDealType();
        //add by wm 针对分层产品进行校验的变量 start 2019-06-26
        //产品处理模式  普通、分档等
        String dealMode = dto.getDealMode();
        //分层明细
        List<FinNetProductDurationLayerDto> finNetProductDurationLayerList = dto.getFinNetProductDurationLayerList();
        //add by wm 针对分层产品进行校验的变量 end 2019-06-26
        //校验是否必须生成指令
        boolean isCheckTrdTransferOrder = true;
        //平台日期
        Date sysDate = DateUtil.platDateFromCache();
        
        if (WorkFlowUtil.isNotFromToDoList()) {
	        //产品不能存在其他存续期交易
	        if (finNetProductDurationService.checkBusinessDataInFlow(dto)) {
	        	errorList.add(new ValidMessage(this.getMessage("COMMON_E_0012", dto.getFinprodId())));
	        	return errorList;
	        }

	        if(DictCodeList.是否_是.getValue().equals(isCycle)){
                //add by zxp fixed GBUGTWO-144 bug增加存续期交易同一周期性产品同一款项的同一交易类型同一流程中判断 start 2020-07-12 start
                ProcessMark processMark = new ProcessMark();
                processMark.setBusinessDataPk2(dto.getFinprodId());
                processMark.setBusinessDataPk3(dealType);
               if(DictCodeList.交易方向_产品申购.getValue().equals(dealType)) {
                   processMark.setBusinessDataPk4(dto.getPurPeriodId());//申购期次代码
               }else if(DictCodeList.交易方向_产品认购.getValue().equals(dealType)){
                   processMark.setBusinessDataPk4(dto.getSubPeriodId());//认购期次代码
               }
/*               else if(DictCodeList.交易方向_产品赎回.getValue().equals(dealType)){
                   processMark.setBusinessDataPk4(dto.getRedPeriodId());//赎回期次代码
               }*/
                List<String> resultList = leaveMarkProcessService.getBusinessDataInFlow(processMark);
                if (!StringUtil.isEmptyList(resultList)) {
                    errorList.add(new ValidMessage("同一款项的周期型产品不能存在相同类型交易在流程中！"));
                    return errorList;
                }
                //add by zxp fixed GBUGTWO-144 bug 增加存续期交易同一周期性产品同一款项的同一交易类型同一流程中判断 start 2020-07-12 end
            }else {

                //add by wm 增加存续期交易同一产品同一交易类型同一流程中判断 start 2019-07-08 start
                ProcessMark processMark = new ProcessMark();
                processMark.setBusinessDataPk2(dto.getFinprodId());
                processMark.setBusinessDataPk3(dealType);

                List<String> resultList = leaveMarkProcessService.getBusinessDataInFlow(processMark);
                if (!StringUtil.isEmptyList(resultList)) {
                    errorList.add(new ValidMessage("同一产品不能存在相同类型交易在流程中！"));
                    return errorList;
                }
                //add by wm 增加存续期交易同一产品同一交易类型同一流程中判断  end 2019-07-08 end
            }
        }
        
        if(StringUtil.equals(dto.getOperateType(), "delete")||StringUtil.equals(dto.getOperateType(), "revoke")){
        	//撤销交易不能有后续交易 
        	List<TrdFinProductDealTotal> list = finNetProductDurationService.queryAfterTrdFinProductDealTotal(dto);
        	if(list!=null && list.size()>0){
        		errorList.add(new ValidMessage("有后续交易，不能撤销!"));
	        	return errorList;
        	}
        }
        
        if(DictCodeList.收益类型_净值型.getValue().equals(profitType)){
        	//add by wm 针对分档产品进行校验 start 2019-06-26
        	if(StringUtil.equals(dealMode, DictCodeList.处理模式_分档.getValue())&&StringUtil.isEmptyList(finNetProductDurationLayerList)){
        		errorList.add(new ValidMessage("分档产品请按产品款型进行存续期交易!"));
            }
        	//add by wm 针对分档产品进行校验 end2019-06-26
            if(DictCodeList.交易方向_产品认购.getValue().equals(dealType)){
                //交易金额不为空，且大于0
                if(dto.getSubDealAmt()==null || dto.getSubDealAmt().compareTo(BigDecimal.ZERO)<0){
                    errorList.add(new ValidMessage("subDealAmt",getMessage("FIN_PROD_E_0089")));
                }
                //认购费可以为空，但不能小于0
                if(dto.getSubFee()!=null && dto.getSubFee().compareTo(BigDecimal.ZERO)<0){
                    errorList.add(new ValidMessage("subFee","认购费不能小于0！"));
                }
            	//add by wm 针对分档产品进行校验 start 2019-06-26
                if(StringUtil.equals(dealMode, DictCodeList.处理模式_分档.getValue())&&!StringUtil.isEmptyList(finNetProductDurationLayerList)){
            		for (FinNetProductDurationLayerDto finNetProductDurationLayerDto : finNetProductDurationLayerList) {
            			//交易金额不为空，且大于0
                        if(finNetProductDurationLayerDto.getSubDealLayerAmt()==null || finNetProductDurationLayerDto.getSubDealLayerAmt().compareTo(BigDecimal.ZERO)<0){
                            errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0117",finNetProductDurationLayerDto.getFinprodId(),"交易金额")));
                        }
                        //认购费可以为空，但不能小于0
                        if(finNetProductDurationLayerDto.getSubLayerFee()!=null && finNetProductDurationLayerDto.getSubLayerFee().compareTo(BigDecimal.ZERO)<0){
                            errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0118",finNetProductDurationLayerDto.getFinprodId(),"认购费")));
                        }
					}
                }
                //add by wm 针对分档产品进行校验 end 2019-06-26
                if(StringUtil.equals(dto.getOperateType(), "delete")||StringUtil.equals(dto.getOperateType(), "revoke")){
                	//撤销时，确认日不能小于平台日
                	if(dto.getSubConfirmDate()!=null && dto.getSubConfirmDate().compareTo(sysDate)<0){
                        errorList.add(new ValidMessage("subConfirmDate","仅允许撤销确认日大于等于平台日期的交易！"));
                    }
                	//撤销交易不能有后续交易 
                	List<TrdFinProductDealTotal> list = finNetProductDurationService.queryAfterAssetTrdFinProductDealTotal(dto);
                	if(list!=null && list.size()>0){
                		errorList.add(new ValidMessage("有后续交易，不能撤销!"));
        	        	return errorList;
                	}
                }
                else{
                    //add by wm 估值不支持远期交易，故增加确认日不能小于平台日判断 start 2019-07-05
                    if(dto.getSubConfirmDate()!=null && dto.getSubConfirmDate().compareTo(sysDate)<0){
                        errorList.add(new ValidMessage("subConfirmDate","确认日不能小于平台日！"));
                    }
                    //add by wm 估值不支持远期交易，故增加确认日不能小于平台日判断 end 2019-07-05
                }
                //净值型周期型存续期交易新增校验	2019-10-14
                //周期型隐性管理	投资周期、期次结束日、期次支付日不为空
                if(DictCodeList.是否_是.getValue().equals(dto.getIsCycle()) && "02".equals(dto.getManeWay())) {
                	if(!StringUtil.isNotEmpty(dto.getSubTermDays()) && 
                			(DictCodeListExt2.期限类型_单一固定期限.getValue().equals(dto.getTermType())||
                					DictCodeListExt2.期限类型_多固定期限.getValue().equals(dto.getTermType()))) {
                		errorList.add(new ValidMessage("subTermDays",getMessage("投资周期不能为空")));
                	}
                	if(!StringUtil.isNotEmpty(dto.getSubCycleEndDate())) {
                		errorList.add(new ValidMessage("subCycleEndDate",getMessage("期次结束日不能为空")));
                	}
                	//保存或新建校验期次结束日
                	if(StringUtil.equals(dto.getOperateType(), "save")||StringUtil.equals(dto.getOperateType(), "new")){
                		if(!StringUtil.isNotEmpty(dto.getSubCyclePayDate())) {
                    		errorList.add(new ValidMessage("subCyclePayDate",getMessage("期次支付日不能为空")));
                    	}
                	}
                	//期次结束日不能大于产品到期日
                	if(DictCodeList.是否_是.getValue().equals(dto.getIsCycle()) && StringUtil.isNotEmpty(dto.getSubCycleEndDate()) 
                			&& StringUtil.isNotEmpty(dto.getmDate()) && dto.getSubCycleEndDate().getTime()>dto.getmDate().getTime()) {
                		errorList.add(new ValidMessage("subCycleEndDate",getMessage("期次结束日不能大于产品到期日")));
                	}
                }
                //周期型显性管理	期次代码、期次结束日、期次支付日不为空
                if(DictCodeList.是否_是.getValue().equals(dto.getIsCycle()) && "01".equals(dto.getManeWay())) {
                	if(!StringUtil.isNotEmpty(dto.getSubPeriodId())) {
                		errorList.add(new ValidMessage("subPeriodId",getMessage("期次代码不能为空")));
                	}
                	if(!StringUtil.isNotEmpty(dto.getSubCycleEndDate())) {
                		errorList.add(new ValidMessage("subCycleEndDate",getMessage("期次结束日不能为空")));
                	}
                	//保存或新建校验期次结束日
                	if(StringUtil.equals(dto.getOperateType(), "save")||StringUtil.equals(dto.getOperateType(), "new")){
	                	if(!StringUtil.isNotEmpty(dto.getSubCyclePayDate())) {
	                		errorList.add(new ValidMessage("subCyclePayDate",getMessage("期次支付日不能为空")));
	                	}
                	}
                	//期次结束日不能大于产品到期日
                	if(DictCodeList.是否_是.getValue().equals(dto.getIsCycle()) && StringUtil.isNotEmpty(dto.getSubCycleEndDate()) 
                			&& StringUtil.isNotEmpty(dto.getmDate()) && dto.getSubCycleEndDate().getTime()>dto.getmDate().getTime()) {
                		errorList.add(new ValidMessage("subCycleEndDate",getMessage("期次结束日不能大于产品到期日")));
                	}
                }
            }else if(DictCodeList.交易方向_产品申购.getValue().equals(dealType)){
            	if(dto.getPurDealNum()==null || dto.getPurDealNum().compareTo(BigDecimal.ZERO)<=0){
                    errorList.add(new ValidMessage("purDealNum",getMessage("FIN_PROD_E_0091","交易份额")));
                }
            	if(dto.getPurDealAmt()==null || dto.getPurDealAmt().compareTo(BigDecimal.ZERO)<=0){
                    errorList.add(new ValidMessage("purDealAmt",getMessage("FIN_PROD_E_0091","交易金额")));
                }
            	//起息日当天不能做申购交易 add by wm start 2019-08-15
            	//modify by wm T+0产品时，可以当日做申购，其他的产品需要进行控制 start 2019-09-05
                FinProductAdd finProductAdd  =  finProductAddMapper.selectByPrimaryKey(dto.getFinprodId());
            	if(!(StringUtil.isNotEmpty(finProductAdd.getPurSpeed())&&finProductAdd.getPurSpeed().compareTo(0L)==0)
            			&&dto.getvDate()!=null&&dto.getvDate().compareTo(sysDate)==0){
                    errorList.add(new ValidMessage("vDate",getMessage("该产品起息日当天不能做申购交易")));
            	}
            	//modify by wm T+0产品时，可以当日做申购，其他的产品需要进行控制 end 2019-09-05
            	//起息日当天不能做申购交易 add by wm end 2019-08-15
                //撤销时校验
                if(StringUtil.equals(dto.getOperateType(), "delete")||StringUtil.equals(dto.getOperateType(), "revoke")){
                	//撤销时，确认日不能小于平台日
                	if(dto.getPurConfirmDate()!=null && dto.getPurConfirmDate().compareTo(sysDate)<0){
                        errorList.add(new ValidMessage("purConfirmDate","仅允许撤销确认日大于等于平台日期的交易！"));
                    }
                }
                else{
                	//确认日必须不小于平台日 
                	if(dto.getPurConfirmDate() ==null || dto.getPurConfirmDate().compareTo(sysDate)<0){
                        errorList.add(new ValidMessage("purConfirmDate","确认日不能为空,且必须不小于平台日！"));
                	}
                }
            	//申购费可以为空，但不能小于0
            	if(dto.getPurFee()!=null && dto.getPurFee().compareTo(BigDecimal.ZERO)<0){
                    errorList.add(new ValidMessage("purFee","申购费不能小于0！"));
                }
            	//净值日、单位净值不能为空
                if(dto.getPurNetValueDate() ==null&&!StringUtil.equals(dealMode, DictCodeList.处理模式_分档.getValue())){
                    errorList.add(new ValidMessage("purNetValueDate","净值日不能为空！"));
                }
                //净值日、单位净值不能为空
                if(dto.getPurUnitNetValue() ==null&&!StringUtil.equals(dealMode, DictCodeList.处理模式_分档.getValue())){
                    errorList.add(new ValidMessage("purUnitNetValue","单位净值不能为空！"));
                }
            	//add by wm 针对分档产品进行校验 start 2019-06-26
                if(StringUtil.equals(dealMode, DictCodeList.处理模式_分档.getValue())&&!StringUtil.isEmptyList(finNetProductDurationLayerList)){
            		for (FinNetProductDurationLayerDto finNetProductDurationLayerDto : finNetProductDurationLayerList) {
            			//交易金额不为空，且大于0
                        if(finNetProductDurationLayerDto.getPurDealLayerAmt()==null || finNetProductDurationLayerDto.getPurDealLayerAmt().compareTo(BigDecimal.ZERO)<0){
                            errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0117",finNetProductDurationLayerDto.getFinprodId(),"交易金额")));
                        }
                        if(finNetProductDurationLayerDto.getPurDealLayerNum()==null || finNetProductDurationLayerDto.getPurDealLayerNum().compareTo(BigDecimal.ZERO)<0){
                            errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0117",finNetProductDurationLayerDto.getFinprodId(),"交易份额")));
                        }
                        //申购费可以为空，但不能小于0
                        if(finNetProductDurationLayerDto.getPurLayerFee()!=null && finNetProductDurationLayerDto.getPurLayerFee().compareTo(BigDecimal.ZERO)<0){
                            errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0118",finNetProductDurationLayerDto.getFinprodId(),"申购费")));
                        }
                        //款型对应的净值日、单位净值不能为空
                        if(finNetProductDurationLayerDto.getPurNetValueLayerDate() ==null){
                            errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0119",finNetProductDurationLayerDto.getFinprodId(),"净值日")));
                        }
                        //款型对应的净值日、单位净值不能为空
                        if(finNetProductDurationLayerDto.getPurUnitNetLayerValue() ==null){
                            errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0119",finNetProductDurationLayerDto.getFinprodId(),"单位净值")));
                        }
					}
                }
                //add by wm 针对分档产品进行校验 end 2019-06-26
                
                //净值型周期型存续期交易新增校验	2019-10-14
                //周期型隐性管理	投资周期、期次结束日、期次支付日不为空
                if(DictCodeList.是否_是.getValue().equals(dto.getIsCycle()) && "02".equals(dto.getManeWay())) {
                	if(!StringUtil.isNotEmpty(dto.getPurTermDays())&& 
                			(DictCodeListExt2.期限类型_单一固定期限.getValue().equals(dto.getTermType())||
                					DictCodeListExt2.期限类型_多固定期限.getValue().equals(dto.getTermType()))) {
                		errorList.add(new ValidMessage("purTermDays",getMessage("投资周期不能为空")));
                	}
                	if(!StringUtil.isNotEmpty(dto.getPurCycleEndDate())) {
                		errorList.add(new ValidMessage("purCycleEndDate",getMessage("期次结束日不能为空")));
                	}
                	//保存或新建校验期次结束日
                	if(StringUtil.equals(dto.getOperateType(), "save")||StringUtil.equals(dto.getOperateType(), "new")){
                		if(!StringUtil.isNotEmpty(dto.getPurCyclePayDate())) {
                    		errorList.add(new ValidMessage("purCyclePayDate",getMessage("期次支付日不能为空")));
                    	}
                	}
                	//期次结束日不能大于产品到期日
                	if(DictCodeList.是否_是.getValue().equals(dto.getIsCycle()) && StringUtil.isNotEmpty(dto.getPurCycleEndDate()) 
                			&& StringUtil.isNotEmpty(dto.getmDate()) && dto.getPurCycleEndDate().getTime()>dto.getmDate().getTime()) {
                		errorList.add(new ValidMessage("purCycleEndDate",getMessage("期次结束日不能大于产品到期日")));
                	}
                }
                //周期型显性管理	期次代码、期次结束日、期次支付日不为空
                if(DictCodeList.是否_是.getValue().equals(dto.getIsCycle()) && "01".equals(dto.getManeWay())) {
                	if(!StringUtil.isNotEmpty(dto.getPurPeriodId())) {
                		errorList.add(new ValidMessage("purPeriodId",getMessage("期次代码不能为空")));
                	}
                	if(!StringUtil.isNotEmpty(dto.getPurCycleEndDate())) {
                		errorList.add(new ValidMessage("purCycleEndDate",getMessage("期次结束日不能为空")));
                	}
                	//保存或新建校验期次结束日
                	if(StringUtil.equals(dto.getOperateType(), "save")||StringUtil.equals(dto.getOperateType(), "new")
                			|| StringUtil.equals(dto.getOperateType(), "submit")){
                		if(!StringUtil.isNotEmpty(dto.getPurCyclePayDate())) {
                    		errorList.add(new ValidMessage("purCyclePayDate",getMessage("期次支付日不能为空")));
                    	}
                	}
                	//期次结束日不能大于产品到期日
                	if(DictCodeList.是否_是.getValue().equals(dto.getIsCycle()) && StringUtil.isNotEmpty(dto.getPurCycleEndDate()) 
                			&& StringUtil.isNotEmpty(dto.getmDate()) && dto.getPurCycleEndDate().getTime()>dto.getmDate().getTime()) {
                		errorList.add(new ValidMessage("purCycleEndDate",getMessage("期次结束日不能大于产品到期日")));
                	}
                }
            }else if(DictCodeList.交易方向_产品赎回.getValue().equals(dealType)
            		|| DictCodeList.交易方向_产品提前赎回.getValue().equals(dealType)
            		|| DictCodeList.交易方向_产品违约赎回.getValue().equals(dealType)){
            	//允许提前赎回字段才能提前赎回
            	if(DictCodeList.交易方向_产品提前赎回.getValue().equals(dealType)){
            		PrdSaleParam prdSaleParam = finNetProductDurationService.queryFinProductInfo(dto.getFinprodId());
                	boolean redFlg = false;
                	if (prdSaleParam != null && DictCodeList.是否_是.getValue().equals(prdSaleParam.getRedFlag())) {
                		redFlg = true;
                	} 
                	if (!redFlg) {
                		errorList.add(new ValidMessage("该产品不允许提前赎回！")); 
                	}
            	}
                //赎回 交易份额、交易金额、净金额 必填校验
            	if(dto.getRedDealNum()==null || dto.getRedDealNum().compareTo(BigDecimal.ZERO)<0){
                    errorList.add(new ValidMessage("redDealNum",getMessage("FIN_PROD_E_0091","交易份额")));
                }
            	if(dto.getRedDealAmt()==null || dto.getRedDealAmt().compareTo(BigDecimal.ZERO)<0){
                    errorList.add(new ValidMessage("redDealAmt",getMessage("FIN_PROD_E_0091","交易金额")));
                }
            	if(dto.getRedTotalNetValue()==null || dto.getRedTotalNetValue().compareTo(BigDecimal.ZERO)<0){
                    errorList.add(new ValidMessage("redTotalNetValue",getMessage("FIN_PROD_E_0091","净金额")));
                }
            	if(dto.getRedConfirmDate() ==null){
                    errorList.add(new ValidMessage("redConfirmDate",getMessage("确认日不能为空！")));
            	}
            	else if(dto.getRedPayDate()==null || dto.getRedPayDate().compareTo(dto.getRedConfirmDate())<0){
                    errorList.add(new ValidMessage("redPayDate",getMessage("支付日不能为空且必须大于等于确认日！")));
            	}
            	if(!DictCodeList.是否_是.getValue().equals(dto.getIsCycle())) {
            		//值日、单位净值不能为空 
                    if(dto.getRedNetValueDate() ==null&&!StringUtil.equals(dealMode, DictCodeList.处理模式_分档.getValue())){
                        errorList.add(new ValidMessage("redNetValueDate",getMessage("净值日不能为空！")));
                    }
                    //款型对应的净值日、单位净值不能为空
                    if(dto.getRedUnitNetValue() ==null&&!StringUtil.equals(dealMode, DictCodeList.处理模式_分档.getValue())){
                        errorList.add(new ValidMessage("redUnitNetValue",getMessage("单位净值不能为空！")));
                    }
            	}
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
//                map.put("tradeDate", dto.getRedPayDate() == null ? null : DateUtil.format(dto.getRedPayDate()));
                map.put("ccy", dto.getCcy());
                map.put("portfolioId", dto.getPortfolioId());
                map.put("netValueDate", dto.getRedConfirmDate() == null ? null : DateUtil.format(dto.getRedConfirmDate()));
                map.put("dealDate", sysDate);
            	//add by wm 针对分档产品进行校验 start 2019-06-26
                if(StringUtil.equals(dealMode, DictCodeList.处理模式_分档.getValue())&&!StringUtil.isEmptyList(finNetProductDurationLayerList)){
            		for (FinNetProductDurationLayerDto finNetProductDurationLayerDto : finNetProductDurationLayerList) {
            			//赎回 交易份额、交易金额、净金额 必填校验
                    	if(finNetProductDurationLayerDto.getRedDealLayerNum()==null || finNetProductDurationLayerDto.getRedDealLayerNum().compareTo(BigDecimal.ZERO)<0){
                            errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0117",finNetProductDurationLayerDto.getFinprodId(),"交易份额")));
                        }
                    	if(finNetProductDurationLayerDto.getRedDealLayerAmt()==null || finNetProductDurationLayerDto.getRedDealLayerAmt().compareTo(BigDecimal.ZERO)<0){
                            errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0117",finNetProductDurationLayerDto.getFinprodId(),"交易金额")));
                        }
                    	if(finNetProductDurationLayerDto.getRedTotalNetLayerValue()==null || finNetProductDurationLayerDto.getRedTotalNetLayerValue().compareTo(BigDecimal.ZERO)<0){
                            errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0117",finNetProductDurationLayerDto.getFinprodId(),"净金额")));
                        }
                        //款型对应的净值日、单位净值不能为空
                        if(finNetProductDurationLayerDto.getRedNetValueLayerDate() ==null){
                            errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0119",finNetProductDurationLayerDto.getFinprodId(),"净值日")));
                        }
                        //款型对应的净值日、单位净值不能为空
                        if(finNetProductDurationLayerDto.getRedTotalNetLayerValue() ==null){
                            errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0119",finNetProductDurationLayerDto.getFinprodId(),"单位净值")));
                        }
                        //撤销的时候不校验
                        if(finNetProductDurationLayerDto.getRedDealLayerNum() != null
                        		&&(StringUtil.equals(dto.getOperateType(), "save")||StringUtil.equals(dto.getOperateType(), "submit"))){
                            map.put("finprodId", finNetProductDurationLayerDto.getFinprodId());
                            BigDecimal purDealNum = BigDecimal.ZERO;
                            FinNetProductDurationDto finNetProductDurationDto = finNetProductDurationService.getNetValueInfo(map);
                            if(finNetProductDurationDto!=null && finNetProductDurationDto.getPurDealNum()!=null){
                                  purDealNum = purDealNum.add(finNetProductDurationDto.getPurDealNum());
                            }
                            Long purSpeed =  finNetProductDurationService.getPurSpeed(finNetProductDurationLayerDto.getFinprodId());
                            if(purSpeed!=null && (long)purSpeed == 0){
                                BigDecimal tdyBuyShareAmt = finNetProductDurationService.getTdyBuyShareAmt(map);
                                if(tdyBuyShareAmt!=null){
                                    purDealNum = purDealNum.add(tdyBuyShareAmt);
                                }
                            }
                            BigDecimal afterTdySellShareAmt = finNetProductDurationService.getAfterTdySellShareAmt(map);
                            if(afterTdySellShareAmt!=null){
                                purDealNum = purDealNum.subtract(afterTdySellShareAmt);
                            }
                            
                            if (purDealNum.compareTo(finNetProductDurationLayerDto.getRedDealLayerNum()) < 0) {
                            	errorList.add(new ValidMessage("款型为"+finNetProductDurationLayerDto.getProdType()+"的交易份额不足,请核实"));                  
                            }
                        }
					}
                }
                else  if(dto.getRedDealNum()!=null
                		&&(StringUtil.equals(dto.getOperateType(), "save")||StringUtil.equals(dto.getOperateType(), "submit"))){
                    map.put("finprodId", dto.getFinprodId());
                    
                    BigDecimal purDealNum = BigDecimal.ZERO;
                    FinNetProductDurationDto finNetProductDurationDto = finNetProductDurationService.getNetValueInfo(map);
                    if(finNetProductDurationDto!=null && finNetProductDurationDto.getPurDealNum()!=null){
                          purDealNum = purDealNum.add(finNetProductDurationDto.getPurDealNum());
                    }
                    Long purSpeed =  finNetProductDurationService.getPurSpeed(dto.getFinprodId());
                    if(purSpeed!=null && (long)purSpeed == 0){
                        BigDecimal tdyBuyShareAmt = finNetProductDurationService.getTdyBuyShareAmt(map);
                        if(tdyBuyShareAmt!=null){
                            purDealNum = purDealNum.add(tdyBuyShareAmt);
                        }
                    }
                    
                    // modify by wj 20200501 只查有效交易。。过滤已撤销赎回
                    map.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
                    map.put("isCancel", DictCodeList.是否_否.getValue());
                    BigDecimal afterTdySellShareAmt = finNetProductDurationService.getAfterTdySellShareAmt(map);
                    if(afterTdySellShareAmt!=null){
                        purDealNum = purDealNum.subtract(afterTdySellShareAmt);
                    }
                    
                    if (purDealNum.compareTo(dto.getRedDealNum()) < 0) {
                    	errorList.add(new ValidMessage("产品交易份额不足,请核实"));                  
                    }
                }
                //add by wm 针对分档产品进行校验 end 2019-06-26
                if(StringUtil.equals(dto.getOperateType(), "delete")||StringUtil.equals(dto.getOperateType(), "revoke")){
                	//撤销时，确认日不能小于平台日
                	if(dto.getRedConfirmDate()!=null && dto.getRedConfirmDate().compareTo(sysDate)<0){
                        errorList.add(new ValidMessage("redConfirmDate","仅允许撤销确认日大于等于平台日期的交易！"));
                    }
                }
                else{
                	if(dto.getRedConfirmDate()!=null && dto.getRedConfirmDate().compareTo(sysDate)<0){
                        errorList.add(new ValidMessage("redConfirmDate",getMessage("确认日不能小于平台日！")));
                    }
                }
            	//违约赎回，违约金不能为空
            	if(DictCodeList.交易方向_产品违约赎回.getValue().equals(dealType)){
            		if(dto.getRedLiquidatedDamages()==null || dto.getRedLiquidatedDamages().compareTo(BigDecimal.ZERO)<0){
                        errorList.add(new ValidMessage("redLiquidatedDamages",getMessage("FIN_PROD_E_0091","违约金")));
                    }
            	}
            	
            	if(DictCodeList.交易方向_产品赎回.getValue().equals(dealType) || DictCodeList.交易方向_产品提前赎回.getValue().equals(dealType)) {
            		if(dto.getRedFee() != null && BigDecimal.ZERO.compareTo(dto.getRedFee())>0) {
            			errorList.add(new ValidMessage("redFee",getMessage("BUSS_COM_E_0004","赎回费","0")));
            		}
            		
            		if(dto.getRedPurFeeHd() != null && BigDecimal.ZERO.compareTo(dto.getRedPurFeeHd())>0) {
            			errorList.add(new ValidMessage("redPurFeeHd",getMessage("BUSS_COM_E_0004","申购费(后端)","0")));
            		}
            	}
            	//周期型产品判断选中期次子产品列表不能为空	20191024
            	if(DictCodeList.是否_是.getValue().equals(dto.getIsCycle())) {
            		//期次子产品列表不能为空
            		if(!StringUtil.isNotEmpty(dto.getRedCycleFinNetProductDurationDtoList())
                    		||dto.getRedCycleFinNetProductDurationDtoList().size() == 0){
                        errorList.add(new ValidMessage("redCycleFinNetProductDurationDtoList",getMessage("请选择期次信息")));
                	}
                    //划款指令交易金额与赎回要素交易金额不符
                    if(StringUtil.isNotEmpty(dto.getBussTrdTransferOrder())
                    		&&dto.getBussTrdTransferOrder().size() > 0){
                    	BigDecimal redDealAmt = dto.getBussTrdTransferOrder().get(0).getSettleAmt();
                    	if(redDealAmt.compareTo(dto.getRedTotalNetValue()) != 0){
                    		errorList.add(new ValidMessage(getMessage("指令生成要素已变更，请重新生成划款指令")));
                    	}
                	}
                    //划款指令支付日期与赎回要素支付日不符
                    if(StringUtil.isNotEmpty(dto.getBussTrdTransferOrder())
                    		&&dto.getBussTrdTransferOrder().size() > 0){
                    	Date settleDate = dto.getBussTrdTransferOrder().get(0).getSettleDate();
                    	if(settleDate.compareTo(dto.getRedPayDate()) != 0){
                    		errorList.add(new ValidMessage(getMessage("指令生成要素已变更，请重新生成划款指令")));
                    	}
                	 }
            	}
            }else if(DictCodeList.交易方向_产品到期.getValue().equals(dealType)
            		|| DictCodeList.交易方向_提前终止.getValue().equals(dealType)){
            	
            	if(StringUtil.equals(dto.getOperateType(), "save")||StringUtil.equals(dto.getOperateType(), "submit")){
            		//产品未做过到期或提前终止操作，有则给出提示不予保存
                	TrdFinProductDealTotal cond = new TrdFinProductDealTotal();
                	cond.setFinprodId(dto.getFinprodId());
                	cond.setPs(DictCodeList.交易方向_产品到期.getValue());
                	cond.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
//                	List<TrdFinProductDealTotal> listTrdFinProductDealTotal = restTemplate
//                			.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
//                            + "/FinProductCommon/queryTrdFinProductDealTotalByModel",cond,TrdFinProductDealTotal.class);
                	List<TrdFinProductDealTotal> listTrdFinProductDealTotal = finNetProductDurationService.queryTrdFinProductDealTotalByModel(cond);
                	if(listTrdFinProductDealTotal!=null && listTrdFinProductDealTotal.size()>0){
                		errorList.add(new ValidMessage("产品已经做过到期交易！"));  
                		return errorList;
                	}
                	cond.setPs(DictCodeList.交易方向_提前终止.getValue());
//                	listTrdFinProductDealTotal = restTemplate
//                			.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
//                            + "/FinProductCommon/queryTrdFinProductDealTotalByModel",cond,TrdFinProductDealTotal.class);
                	listTrdFinProductDealTotal = finNetProductDurationService.queryTrdFinProductDealTotalByModel(cond);
                	if(listTrdFinProductDealTotal!=null && listTrdFinProductDealTotal.size()>0){
                		errorList.add(new ValidMessage("产品已经做过提前终止！"));   
                		return errorList;
                	}
                	
                	//需判断资产是否都已处置结束，没有处置完毕则不予保存
//                	BokPositionPrd bokPositionPrd = finNetProductDurationService.queryMaxDateBokPositionPrd(dto);
//                	if(bokPositionPrd!=null && bokPositionPrd.getTdyAssetAmt()!=null 
//                			&& bokPositionPrd.getTdyAssetAmt().compareTo(BigDecimal.ZERO)!=0){
//                		errorList.add(new ValidMessage("产品资产未处置结束！"));   
//                		return errorList;
//                	}
                	//产品到期 交易金额、交易份额校验
                	if(dto.getExpireDealNum()==null || dto.getExpireDealNum().compareTo(BigDecimal.ZERO)<=0){
                        errorList.add(new ValidMessage("expireDealNum",getMessage("FIN_PROD_E_0091","交易份额")));
                    }
                	if(dto.getExpireDealAmt()==null || dto.getExpireDealAmt().compareTo(BigDecimal.ZERO)<=0){
                        errorList.add(new ValidMessage("expireDealAmt",getMessage("FIN_PROD_E_0091","交易金额")));
                    }
                	//周期型不判断净值日、净值日期
                	if(!DictCodeList.是否_是.getValue().equals(dto.getIsCycle())) {
                		//净值日、单位净值不能为空
                        if(dto.getExpireNetValueDate() ==null&&!StringUtil.equals(dealMode, DictCodeList.处理模式_分档.getValue())){
                            errorList.add(new ValidMessage("expireNetValueDate","净值日不能为空！"));
                        }
                        //净值日、单位净值不能为空
                        if(dto.getExpireUnitNetValue() ==null&&!StringUtil.equals(dealMode, DictCodeList.处理模式_分档.getValue())){
                            errorList.add(new ValidMessage("expireUnitNetValue","单位净值不能为空！"));
                        }
                	}
                    if(dto.getExpireConfirmDate()!=null&&dto.getExpireConfirmDate().compareTo(sysDate)<0){
                        errorList.add(new ValidMessage("expireConfirmDate","确认日不能小于平台日！"));
                    }
                    //add by wm 根据需求增加支付日不能小于平台日校验 start 2019-07-31
                    //支付日不能小于确认日 
                    if(dto.getExpireConfirmDate()!=null&&dto.getExpirePayDate()!=null&&
                    		dto.getExpirePayDate().compareTo(dto.getExpireConfirmDate())<0){
                        errorList.add(new ValidMessage("expireConfirmDate","支付日不能小于确认日！"));
                    }
                    //add by wm 根据需求增加支付日不能小于平台日校验 end 2019-07-31
                	if(DictCodeList.交易方向_提前终止.getValue().equals(dealType)){
                		//产品需要做到期日调整，才能做提前终止
                		PrdMdateAdjust condPrdMdateAdjust = new PrdMdateAdjust();
                		condPrdMdateAdjust.setFinprodId(dto.getFinprodId());
                		condPrdMdateAdjust.setAdjustType("01");
//            			List<PrdMdateAdjust> listPrdMdateAdjust = restTemplate
//                    			.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
//                                + "/FinProductCommon/queryPrdMdateAdjustByModel",condPrdMdateAdjust,PrdMdateAdjust.class);
            			
                		List<PrdMdateAdjust> listPrdMdateAdjust = finNetProductDurationService.queryPrdMdateAdjustByModel(condPrdMdateAdjust);
            			if(listPrdMdateAdjust==null || listPrdMdateAdjust.size()<1){
            				errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0092")));
                			return errorList;
            			}
                	}
            	}
            	else{
            		if(dto.getExpireConfirmDate()!=null&&dto.getExpireConfirmDate().compareTo(sysDate)<0){
                        errorList.add(new ValidMessage("expireConfirmDate"," 仅允许撤销确认日大于等于平台日期的交易！"));
                    }
            	}
            	//add by wm 针对分档产品进行校验 start 2019-06-26
                if(StringUtil.equals(dealMode, DictCodeList.处理模式_分档.getValue())&&!StringUtil.isEmptyList(finNetProductDurationLayerList)){
            		for (FinNetProductDurationLayerDto finNetProductDurationLayerDto : finNetProductDurationLayerList) {
            			//产品到期 交易金额、交易份额校验
                    	if(finNetProductDurationLayerDto.getExpireDealLayerNum()==null || finNetProductDurationLayerDto.getExpireDealLayerNum().compareTo(BigDecimal.ZERO)<=0){
                            errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0117",finNetProductDurationLayerDto.getFinprodId(),"交易份额")));
                        }
                    	if(finNetProductDurationLayerDto.getExpireDealLayerAmt()==null || finNetProductDurationLayerDto.getExpireDealLayerAmt().compareTo(BigDecimal.ZERO)<=0){
                            errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0117",finNetProductDurationLayerDto.getFinprodId(),"交易金额")));
                        }
                        //款型对应的净值日、单位净值不能为空
                        if(finNetProductDurationLayerDto.getExpireNetValueLayerDate() ==null){
                            errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0119",finNetProductDurationLayerDto.getFinprodId(),"净值日")));
                        }
                        //款型对应的净值日、单位净值不能为空
                        if(finNetProductDurationLayerDto.getExpireUnitNetLayerValue() ==null){
                            errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0119",finNetProductDurationLayerDto.getFinprodId(),"单位净值")));
                        }
					}
                }
                //add by wm 针对分档产品进行校验 end 2019-06-26
                
                //周期型产品判断选中期次子产品列表不能为空	20191024
            	if(DictCodeList.是否_是.getValue().equals(dto.getIsCycle())) {
            		//期次子产品列表不能为空
                    if(!StringUtil.isNotEmpty(dto.getExpireCycleFinNetProductDurationDtoList())
                    		||dto.getExpireCycleFinNetProductDurationDtoList().size() == 0){
                        errorList.add(new ValidMessage("expireCycleFinNetProductDurationDtoList",getMessage("请选择期次信息")));
                    }
            	}
            }else if(DictCodeList.交易方向_份额强增.getValue().equals(dealType)
            		|| DictCodeList.交易方向_份额强减.getValue().equals(dealType)){
            	//确认日期要小于到期日
            	//modify by wm 确认日要小于到期日，不能包含等于，比较需要大于等于到期日 start 2019-06-20
//            	if(dto.getAddReduceConfirmDate().compareTo(dto.getmDate())>0){
//            		errorList.add(new ValidMessage("addReduceConfirmDate","确认日必须小于产品到期日"));
//            	}
            	if(dto.getAddReduceConfirmDate().compareTo(dto.getmDate())>=0){
            		errorList.add(new ValidMessage("addReduceConfirmDate","确认日必须小于产品到期日！"));
            	}
            	//modify by wm 确认日要小于到期日，不能包含等于，比较需要大于等于到期日 end 2019-06-20
            	//add  by wm 确认日要不小于气息日 start 2019-06-29
            	if(dto.getAddReduceConfirmDate().compareTo(dto.getvDate())<0){
            		errorList.add(new ValidMessage("addReduceConfirmDate","确认日不能小于产品起息日！"));
            	}
            	//add  by wm 确认日要不小于气息日 end 2019-06-29
                if(StringUtil.equals(dto.getOperateType(), "delete")||StringUtil.equals(dto.getOperateType(), "revoke")){
                	if(dto.getAddReduceConfirmDate()!=null&&dto.getAddReduceConfirmDate().compareTo(sysDate)<0){
                		errorList.add(new ValidMessage("addReduceConfirmDate"," 仅允许撤销确认日大于等于平台日期的交易！"));
                	}
                }
                else{
                	if(dto.getAddReduceConfirmDate()!=null&&dto.getAddReduceConfirmDate().compareTo(sysDate)<0){
                		errorList.add(new ValidMessage("addReduceConfirmDate","确认日不能小于平台日！"));
                	}
                }
            	if(dto.getAddReduceDealNum()==null&&dto.getAddReduceDealAmt()==null){
                    errorList.add(new ValidMessage("交易金额与交易份额不能同时为空！"));
            	}
            	//ZGBSS-49 按需求 强增强减交易金额与交易份额至少填写一个，且填写数值必须大于0 20210118 start
            	if(dto.getAddReduceDealNum()!=null && dto.getAddReduceDealNum().compareTo(BigDecimal.ZERO)<=0){
            		errorList.add(new ValidMessage("addReduceDealNum","交易份额必须大于0！"));
            	}
            	if(dto.getAddReduceDealAmt()!=null && dto.getAddReduceDealAmt().compareTo(BigDecimal.ZERO)<=0){
            		errorList.add(new ValidMessage("addReduceDealAmt","交易金额必须大于0！"));
            	}
            	//ZGBSS-49 按需求 强增强减交易金额与交易份额至少填写一个，且填写数值必须大于0 20210118 end
//            	//强增强减 交易金额、交易份额校验
//            	if(dto.getAddReduceDealNum()==null || dto.getAddReduceDealNum().compareTo(BigDecimal.ZERO)<0){
//                    errorList.add(new ValidMessage("addReduceDealNum",getMessage("FIN_PROD_E_0091","交易份额")));
//                }
//            	if(dto.getAddReduceDealAmt()==null || dto.getAddReduceDealAmt().compareTo(BigDecimal.ZERO)<0){
//                    errorList.add(new ValidMessage("addReduceDealAmt",getMessage("FIN_PROD_E_0091","交易金额")));
//                }
            	//周期型不判断净值日、净值日期
            	if(!DictCodeList.是否_是.getValue().equals(dto.getIsCycle())) {
            		//净值日、单位净值不能为空
                    if(dto.getAddReduceNetValueDate() ==null&&!StringUtil.equals(dealMode, DictCodeList.处理模式_分档.getValue())){
                        errorList.add(new ValidMessage("addReduceNetValueDate","净值日不能为空！"));
                    }
                    //净值日、单位净值不能为空
                    if(dto.getAddReduceUnitNetValue() ==null&&!StringUtil.equals(dealMode, DictCodeList.处理模式_分档.getValue())){
                        errorList.add(new ValidMessage("addReduceUnitNetValue","单位净值不能为空！"));
                    }
            	}
                //当强增或者强减交易金额为空时，无需生成指令信息
            	if(dto.getAddReduceDealAmt()==null){
            		isCheckTrdTransferOrder =false;
            	}
            	//add by wm 针对分档产品进行校验 start 2019-06-26
                if(StringUtil.equals(dealMode, DictCodeList.处理模式_分档.getValue())&&!StringUtil.isEmptyList(finNetProductDurationLayerList)){
            		for (FinNetProductDurationLayerDto finNetProductDurationLayerDto : finNetProductDurationLayerList) {
            			//强增强减 交易金额、交易份额校验
            			if(finNetProductDurationLayerDto.getAddReduceDealLayerNum()==null && finNetProductDurationLayerDto.getAddReduceDealLayerAmt()==null){
                            errorList.add(new ValidMessage("款型为"+finNetProductDurationLayerDto.getProdType()+"的交易金额和交易份额不能同时为空！"));
                        }
                    	if(finNetProductDurationLayerDto.getAddReduceDealLayerNum()!=null && finNetProductDurationLayerDto.getAddReduceDealLayerNum().compareTo(BigDecimal.ZERO)<0){
                            errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0130",finNetProductDurationLayerDto.getFinprodId(),"交易份额")));
                        }
                    	if(finNetProductDurationLayerDto.getAddReduceDealLayerAmt()!=null && finNetProductDurationLayerDto.getAddReduceDealLayerAmt().compareTo(BigDecimal.ZERO)<0){
                            errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0130",finNetProductDurationLayerDto.getFinprodId(),"交易金额")));
                        }
                        //款型对应的净值日、单位净值不能为空
                        if(finNetProductDurationLayerDto.getAddReduceNetValueLayerDate() ==null){
                            errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0119",finNetProductDurationLayerDto.getFinprodId(),"净值日")));
                        }
                        //款型对应的净值日、单位净值不能为空
                        if(finNetProductDurationLayerDto.getAddReduceUnitNetLayerValue() ==null){
                            errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0119",finNetProductDurationLayerDto.getFinprodId(),"单位净值")));
                        }
					}
                }
                //add by wm 针对分档产品进行校验 end 2019-06-26
                
                //周期型产品判断选中期次子产品列表不能为空	20191024
            	if(DictCodeList.是否_是.getValue().equals(dto.getIsCycle())) {
            		//期次子产品列表不能为空
                    if(!StringUtil.isNotEmpty(dto.getAddReduceCycleFinNetProductDurationDtoList())
                    		||dto.getAddReduceCycleFinNetProductDurationDtoList().size() == 0){
                        errorList.add(new ValidMessage("addReduceCycleFinNetProductDurationDtoList",getMessage("请选择期次信息")));
                    }
            	}
            }
        }
        else if(DictCodeList.收益类型_货币型.getValue().equals(profitType)){
        	if(DictCodeList.交易方向_产品认购.getValue().equals(dealType)){
                //交易金额不为空，且大于0
                if(dto.getSubDealAmt03()==null || dto.getSubDealAmt03().compareTo(BigDecimal.ZERO)<0){
                    errorList.add(new ValidMessage("subDealAmt03",getMessage("FIN_PROD_E_0089")));
                }
                //认购费可以为空，但不能小于0
                if(dto.getSubFee03()!=null && dto.getSubFee03().compareTo(BigDecimal.ZERO)<0){
                    errorList.add(new ValidMessage("subFee03","认购费不能小于0！"));
                }
                //add by wm 估值不支持远期交易，故增加确认日不能小于平台日判断 start 2019-07-05
                if(StringUtil.equals(dto.getOperateType(), "delete")||StringUtil.equals(dto.getOperateType(), "revoke")){
                    if(dto.getSubConfirmDate03()!=null && dto.getSubConfirmDate03().compareTo(sysDate)<0){
                        errorList.add(new ValidMessage("subConfirmDate"," 仅允许撤销确认日大于等于平台日期的交易！"));
                    }
                }
                else{
                    if(dto.getSubConfirmDate03()!=null && dto.getSubConfirmDate03().compareTo(sysDate)<0){
                        errorList.add(new ValidMessage("subConfirmDate","确认日不能小于平台日！"));
                    }
                }
                //add by wm 估值不支持远期交易，故增加确认日不能小于平台日判断 end 2019-07-05
            }else if(DictCodeList.交易方向_产品申购.getValue().equals(dealType)){
                //交易金额不为空，且大于0
                if(dto.getPurDealAmt03()==null || dto.getPurDealAmt03().compareTo(BigDecimal.ZERO)<0){
                    errorList.add(new ValidMessage("purDealAmt03",getMessage("FIN_PROD_E_0089")));
                }
                //申购费可以为空，但不能小于0
                if(dto.getPurFee03()!=null && dto.getPurFee03().compareTo(BigDecimal.ZERO)<0){
                    errorList.add(new ValidMessage("purFee03","申购费不能小于0！"));
                }
              //起息日当天不能做申购交易 add by wm start 2019-08-15
            	//modify by wm T+0产品时，可以当日做申购，其他的产品需要进行控制 start 2019-09-05
                FinProductAdd finProductAdd  =  finProductAddMapper.selectByPrimaryKey(dto.getFinprodId());
            	if(!(StringUtil.isNotEmpty(finProductAdd.getPurSpeed())&&finProductAdd.getPurSpeed().compareTo(0L)==0)
            			&&dto.getvDate()!=null&&dto.getvDate().compareTo(sysDate)==0){
                    errorList.add(new ValidMessage("vDate",getMessage("该产品起息日当天不能做申购交易")));
            	}
            	//modify by wm T+0产品时，可以当日做申购，其他的产品需要进行控制 end 2019-09-05
            	//起息日当天不能做申购交易 add by wm end 2019-08-15
                //确认日不能小于平台日期
                if(StringUtil.equals(dto.getOperateType(), "delete")||StringUtil.equals(dto.getOperateType(), "revoke")){
                	if(DateUtil.compareDate(dto.getPurConfirmDate03(),sysDate)<0){
                        errorList.add(new ValidMessage("subConfirmDate","仅允许撤销确认日大于等于平台日期的交易！"));
            		}
                }
                else{
                	if(DateUtil.compareDate(dto.getPurConfirmDate03(),sysDate)<0){
            			errorList.add(new ValidMessage("purConfirmDate03",getMessage("FIN_PROD_E_0122")));
            		}
                }
            }else if(DictCodeList.交易方向_产品赎回.getValue().equals(dealType)
            		|| DictCodeList.交易方向_产品提前赎回.getValue().equals(dealType)
            		|| DictCodeList.交易方向_产品违约赎回.getValue().equals(dealType)){
            	//允许提前赎回字段才能提前赎回
            	if(DictCodeList.交易方向_产品提前赎回.getValue().equals(dealType)){
            		PrdSaleParam prdSaleParam = finNetProductDurationService.queryFinProductInfo(dto.getFinprodId());
                	boolean redFlg = false;
                	if (prdSaleParam != null && DictCodeList.是否_是.getValue().equals(prdSaleParam.getRedFlag())) {
                		redFlg = true;
                	} 
                	if (!redFlg) {
                		errorList.add(new ValidMessage("该产品不允许提前赎回！")); 
                	}
            	}
                //赎回 交易本金、收益金额、交易金额、净金额 必填校验
            	if(dto.getRedPrinAmt03()==null || dto.getRedPrinAmt03().compareTo(BigDecimal.ZERO)<0){
                    errorList.add(new ValidMessage("redPrinAmt03",getMessage("FIN_PROD_E_0091","交易本金")));
                }
//            	if(dto.getRedProfit03()==null || dto.getRedProfit03().compareTo(BigDecimal.ZERO)<0){
//                    errorList.add(new ValidMessage("redProfit03",getMessage("FIN_PROD_E_0091","收益金额")));
//                }
            	//产品未做资产投资，属亏损状态，收益金额，显示为负,点击保存可以保存成功
            	if(DictCodeList.交易方向_产品赎回.getValue().equals(dealType) || DictCodeList.交易方向_产品提前赎回.getValue().equals(dealType)){
	            	if(dto.getRedProfit03()==null){
	                    errorList.add(new ValidMessage("redProfit03",getMessage("FIN_PROD_E_0123","收益金额")));
	                }
            	}
            	if(dto.getRedDealAmt03()==null || dto.getRedDealAmt03().compareTo(BigDecimal.ZERO)<0){
                    errorList.add(new ValidMessage("redDealAmt03",getMessage("FIN_PROD_E_0091","交易金额")));
                }
            	if(dto.getRedTotalNetValue03()==null || dto.getRedTotalNetValue03().compareTo(BigDecimal.ZERO)<0){
                    errorList.add(new ValidMessage("redTotalNetValue03",getMessage("FIN_PROD_E_0091","净金额")));
                }
            	//违约赎回，违约金不能为空
            	if(DictCodeList.交易方向_产品违约赎回.getValue().equals(dealType)){
            		if(dto.getRedLiquidatedDamages03()==null || dto.getRedLiquidatedDamages03().compareTo(BigDecimal.ZERO)<0){
                        errorList.add(new ValidMessage("redLiquidatedDamages03",getMessage("FIN_PROD_E_0091","违约金")));
                    }
            	}
            	//收益支付日不能小于确认日
            	if(DictCodeList.交易方向_产品赎回.getValue().equals(dealType) || DictCodeList.交易方向_产品提前赎回.getValue().equals(dealType)){
            		if(DateUtil.compareDate(dto.getRedProfitPayDate03(),dto.getRedConfirmDate03())<0){
            			errorList.add(new ValidMessage("redProfitPayDate03",getMessage("FIN_PROD_E_0120")));
            		}
            	}
            	
            	//本金支付日必须大于等于确认日
            	if(DictCodeList.交易方向_产品赎回.getValue().equals(dealType) || DictCodeList.交易方向_产品提前赎回.getValue().equals(dealType)){
            		if(DateUtil.compareDate(dto.getRedPrinPayDate03(),dto.getRedConfirmDate03())<0){
            			errorList.add(new ValidMessage("redPrinPayDate03",getMessage("FIN_PROD_E_0121")));
            		}
            		//add by wm 根据BSTWO-1156问题增加此校验 start 2019-07-31 
            		if(DateUtil.compareDate(dto.getRedConfirmDate03(),dto.getvDate())<0){
            			errorList.add(new ValidMessage("redConfirmDate03","确认日不能小于起息日！"));
            		}
            		//add by wm 根据BSTWO-1156问题增加此校验 end 2019-07-31 
            	}
            	
            	//确认日不能小于平台日期
            	if(DictCodeList.交易方向_产品赎回.getValue().equals(dealType) || DictCodeList.交易方向_产品提前赎回.getValue().equals(dealType)){
                    if(StringUtil.equals(dto.getOperateType(), "delete")||StringUtil.equals(dto.getOperateType(), "revoke")){
                		if(DateUtil.compareDate(dto.getRedConfirmDate03(),sysDate)<0){
                			errorList.add(new ValidMessage("redConfirmDate03","仅允许撤销确认日大于等于平台日期的交易！"));
                		}
                    }
                    else{
                		if(DateUtil.compareDate(dto.getRedConfirmDate03(),sysDate)<0){
                			errorList.add(new ValidMessage("redConfirmDate03",getMessage("FIN_PROD_E_0122")));
                		}
                    }
            	}
            	
            	//交易本金大于剩余现金，不应保存成功 需求提出改为软校验 
            	if(DictCodeList.交易方向_产品赎回.getValue().equals(dealType) || DictCodeList.交易方向_产品提前赎回.getValue().equals(dealType)){
	            	Map<String, Object> param = new HashMap<>();
	            	param.put("ccy", DictCodeList.币种_人民币_CNY.getValue());
	            	param.put("portfolioId", dto.getPortfolioId());
	            	param.put("tradeDate", DateUtil.format(sysDate));
	            	param.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
	            	
	            	/*BigDecimal secAcctBalance = BigDecimal.ZERO;
	            	if (bussTrdAvailableCashQueryService != null && param.get("tradeDate") != null) {
	            		secAcctBalance = bussTrdAvailableCashQueryService.getSecManageAvailableCash(param);
	                }
	                
	                if(dto.getRedPrinAmt03() !=null && dto.getRedPrinAmt03().compareTo(secAcctBalance)>0){
	            		errorList.add(new ValidMessage("redPrinAmt03",getMessage("FIN_PROD_E_0124","托管户剩余现金",secAcctBalance+"")));
	            	}*/
	            	if(dto.getRedConfirmDate03()!=null
	            			&&(StringUtil.equals(dto.getOperateType(), "save")||StringUtil.equals(dto.getOperateType(), "submit"))){
	            		Map<String, Object> map = new HashMap<String, Object>();
	                    map.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
//	                    map.put("tradeDate", dto.getRedPrinPayDate03() == null ? null : DateUtil.format(dto.getRedPrinPayDate03()));
	                    map.put("ccy", dto.getCcy());
	                    map.put("portfolioId", dto.getPortfolioId());
	                    map.put("netValueDate", DateUtil.format(dto.getRedConfirmDate03()));
	                    map.put("dealDate", sysDate);
	                    map.put("finprodId", dto.getFinprodId());
	                    BigDecimal purDealNum = BigDecimal.ZERO;
	                    FinNetProductDurationDto finNetProductDurationDto = finNetProductDurationService.getNetValueInfo(map);
	                    if(finNetProductDurationDto!=null && finNetProductDurationDto.getPurDealNum()!=null){
	                          purDealNum = purDealNum.add(finNetProductDurationDto.getPurDealNum());
	                    }
	                    Long purSpeed =  finNetProductDurationService.getPurSpeed(dto.getFinprodId());
	                    if(purSpeed!=null && (long)purSpeed == 0){
	                        BigDecimal tdyBuyShareAmt = finNetProductDurationService.getTdyBuyShareAmt(map);
	                        if(tdyBuyShareAmt!=null){
	                            purDealNum = purDealNum.add(tdyBuyShareAmt);
	                        }
	                    }
	                    BigDecimal afterTdySellShareAmt = finNetProductDurationService.getAfterTdySellShareAmt(map);
	                    if(afterTdySellShareAmt!=null){
	                        purDealNum = purDealNum.subtract(afterTdySellShareAmt);
	                    }
	                    if (StringUtil.isNotEmpty(dto.getRedDealNum())&&purDealNum.compareTo(dto.getRedDealNum()) < 0) {
	                    	errorList.add(new ValidMessage("产品交易份额不足,请核实"));                  
	                    }
	            	}
	            	
            	}
            	
            	if(DictCodeList.交易方向_产品赎回.getValue().equals(dealType) || DictCodeList.交易方向_产品提前赎回.getValue().equals(dealType)) {
            		if(dto.getRedFee03() != null && BigDecimal.ZERO.compareTo(dto.getRedFee03())>0) {
            			errorList.add(new ValidMessage("redFee03",getMessage("BUSS_COM_E_0004","赎回费","0")));
            		}
            		
            		if(dto.getRedPurFeeHd03() != null && BigDecimal.ZERO.compareTo(dto.getRedPurFeeHd03())>0) {
            			errorList.add(new ValidMessage("redPurFeeHd03",getMessage("BUSS_COM_E_0004","申购费(后端)","0")));
            		}
            	}
            	
            	
            }else if(DictCodeList.交易方向_产品到期.getValue().equals(dealType)
            		|| DictCodeList.交易方向_提前终止.getValue().equals(dealType)){
            	
            	if(StringUtil.equals(dto.getOperateType(), "save")||StringUtil.equals(dto.getOperateType(), "submit")){
            		//产品未做过到期或提前终止操作，有则给出提示不予保存
                	TrdFinProductDealTotal cond = new TrdFinProductDealTotal();
                	cond.setFinprodId(dto.getFinprodId());
                	cond.setPs(DictCodeList.交易方向_产品到期.getValue());
                	cond.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
//                	List<TrdFinProductDealTotal> listTrdFinProductDealTotal = restTemplate
//                			.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
//                            + "/FinProductCommon/queryTrdFinProductDealTotalByModel",cond,TrdFinProductDealTotal.class);
                	List<TrdFinProductDealTotal> listTrdFinProductDealTotal = finNetProductDurationService.queryTrdFinProductDealTotalByModel(cond);
                	if(listTrdFinProductDealTotal!=null && listTrdFinProductDealTotal.size()>0){
                		errorList.add(new ValidMessage("产品已经做过到期交易！"));  
                		return errorList;
                	}
                	cond.setPs(DictCodeList.交易方向_提前终止.getValue());
//                	listTrdFinProductDealTotal = restTemplate
//                			.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
//                            + "/FinProductCommon/queryTrdFinProductDealTotalByModel",cond,TrdFinProductDealTotal.class);
                	listTrdFinProductDealTotal = finNetProductDurationService.queryTrdFinProductDealTotalByModel(cond);
                	if(listTrdFinProductDealTotal!=null && listTrdFinProductDealTotal.size()>0){
                		errorList.add(new ValidMessage("产品已经做过提前终止！"));  
                		return errorList;
                	}
                	
                	if(DictCodeList.交易方向_提前终止.getValue().equals(dealType)){
                		PrdMdateAdjust condPrdMdateAdjust = new PrdMdateAdjust();
                		condPrdMdateAdjust.setFinprodId(dto.getFinprodId());
                		condPrdMdateAdjust.setAdjustType("01");
//            			List<PrdMdateAdjust> listPrdMdateAdjust = restTemplate
//                    			.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
//                                + "/FinProductCommon/queryPrdMdateAdjustByModel",condPrdMdateAdjust,PrdMdateAdjust.class);
            			List<PrdMdateAdjust> listPrdMdateAdjust = finNetProductDurationService.queryPrdMdateAdjustByModel(condPrdMdateAdjust);
            			if(listPrdMdateAdjust==null || listPrdMdateAdjust.size()<1){
            				errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0092")));
                			return errorList;
            			}
                	}
                	
                	//需判断资产是否都已处置结束，没有处置完毕则不予保存
//                	BokPositionPrd bokPositionPrd = finNetProductDurationService.queryMaxDateBokPositionPrd(dto);
//                	if(bokPositionPrd!=null && bokPositionPrd.getTdyAssetAmt()!=null 
//                			&& bokPositionPrd.getTdyAssetAmt().compareTo(BigDecimal.ZERO)!=0){
//                		errorList.add(new ValidMessage("产品资产未处置结束！"));   
//                		return errorList;
//                	}
                	
                	//产品到期 交易本金、收益金额、交易金额
                	if(dto.getExpirePrinAmt03()==null || dto.getExpirePrinAmt03().compareTo(BigDecimal.ZERO)<0){
                        errorList.add(new ValidMessage("expirePrinAmt03",getMessage("FIN_PROD_E_0091","交易本金")));
                    }
                	if(dto.getExpireDealAmt03()==null || dto.getExpireDealAmt03().compareTo(BigDecimal.ZERO)<0){
                        errorList.add(new ValidMessage("expireDealAmt03",getMessage("FIN_PROD_E_0091","交易金额")));
                    }
                	
                	//收益支付日不能小于确认日
                	if(DateUtil.compareDate(dto.getExpireProfitPayDate03(),dto.getExpireConfirmDate03())<0){
            			errorList.add(new ValidMessage("expireProfitPayDate03",getMessage("FIN_PROD_E_0120")));
            		}
                	
                	//本金支付日必须大于等于确认日
                	if(DateUtil.compareDate(dto.getExpirePrinPayDate03(),dto.getExpireConfirmDate03())<0){
            			errorList.add(new ValidMessage("expirePrinPayDate03",getMessage("FIN_PROD_E_0121")));
            		}
                	//确认日不能小于平台日期
                	if(DateUtil.compareDate(dto.getExpireConfirmDate03(),sysDate)<0){
            			errorList.add(new ValidMessage("expireConfirmDate03",getMessage("FIN_PROD_E_0122")));
            		}	
            	}
            	else{
                	//确认日不能小于平台日期
                	if(DateUtil.compareDate(dto.getExpireConfirmDate03(),sysDate)<0){
            			errorList.add(new ValidMessage("expireConfirmDate03","仅允许撤销确认日大于等于平台日期的交易"));
            		}	
            	}
            }else if(DictCodeList.交易方向_份额强增.getValue().equals(dealType)
            		|| DictCodeList.交易方向_份额强减.getValue().equals(dealType)){
            	//确认日期要小于到期日
            	//modify by wm 确认日要小于到期日，不能包含等于，比较需要大于等于到期日 start 2019-06-20 
//            	if(dto.getAddReduceConfirmDate03().compareTo(dto.getmDate())>0){
//            		errorList.add(new ValidMessage("addReduceConfirmDate03","确认日必须小于产品到期日"));
//            	}
            	if(dto.getAddReduceConfirmDate03().compareTo(dto.getmDate())>=0){
            		errorList.add(new ValidMessage("addReduceConfirmDate03","确认日必须小于产品到期日"));
            	}
            	//modify by wm 确认日要小于到期日，不能包含等于，比较需要大于等于到期日 end 2019-06-20 
            	//add  by wm 确认日要不小于起息日 start 2019-06-29
            	if(dto.getAddReduceConfirmDate03().compareTo(dto.getvDate())<0){
            		errorList.add(new ValidMessage("addReduceConfirmDate","确认日不能小于产品起息日"));
            	}
                if(StringUtil.equals(dto.getOperateType(), "delete")||StringUtil.equals(dto.getOperateType(), "revoke")){
                	if(dto.getAddReduceConfirmDate03().compareTo(sysDate)<0){
                		errorList.add(new ValidMessage("addReduceConfirmDate","仅允许撤销确认日大于等于平台日期的交易"));
                	}
                }
                else{
                	if(dto.getAddReduceConfirmDate03().compareTo(sysDate)<0){
                		errorList.add(new ValidMessage("addReduceConfirmDate","确认日不能小于平台日"));
                	}
                }
            	//add  by wm 确认日要不小于起息日 end 2019-06-29
            	//强增强减 交易金额、交易份额校验
            	if(dto.getAddReduceDealNum03()==null&&dto.getAddReduceDealAmt03()==null){
                    errorList.add(new ValidMessage("交易金额与交易份额不能同时为空"));
            	}
            	if(dto.getAddReduceDealNum03()!=null && dto.getAddReduceDealNum03().compareTo(BigDecimal.ZERO)<=0){
                    errorList.add(new ValidMessage("输入的交易份额必须大于0"));
                }
            	if(dto.getAddReduceDealAmt03()!=null && dto.getAddReduceDealAmt03().compareTo(BigDecimal.ZERO)<=0){
                    errorList.add(new ValidMessage("输入的交易金额必须大于0"));
                }
            	//当强增或者强减交易金额为空时，无需生成指令信息
            	if(dto.getAddReduceDealAmt03()==null){
            		isCheckTrdTransferOrder =false;
            	}

            	if(DictCodeList.交易方向_份额强增.getValue().equals(dealType)){
            		if(dto.getAddReduceDealNum03()!=null&&dto.getAddReduceDealAmt03()!=null  && dto.getAddReduceDealNum03().compareTo(dto.getAddReduceDealAmt03())!=0){
                        errorList.add(new ValidMessage("交易金额与交易份额必须相等"));
                    }/*fixed BS-1695 Bug */
            	}
            }else if(DictCodeList.交易方向_产品现金分红.getValue().equals(dealType)){
            	//计息结束日不能为空
            	if(dto.getBonusCashEndDate03()==null){
            		errorList.add(new ValidMessage("bonusCashEndDate03","计息结束日不能为空"));
            	}
            	//红利下发日不能为空,并且必须大于平台日
            	if(dto.getBonusCashBonusDate03()==null||dto.getBonusCashBonusDate03().compareTo(sysDate)<0){
                    errorList.add(new ValidMessage("bonusCashBonusDate03","红利下发日不能为空,且必须大于等于平台日！"));
                }
            	//分红金额不能为空，且大于0
            	if(dto.getBonusCashBonusAmt03()==null || dto.getBonusCashBonusAmt03().compareTo(BigDecimal.ZERO)<0){
                    errorList.add(new ValidMessage("bonusCashBonusAmt03",getMessage("FIN_PROD_E_0091","分红金额")));
                }
            	else if(dto.getBonusCashBonusAmt03().precision()-dto.getBonusCashBonusAmt03().scale()>30){
                    errorList.add(new ValidMessage("bonusCashBonusAmt03","分红金额输入过大！"));
            	}
            	if(errorList.isEmpty()){
	            	//同一天存在多条计息结束日相同的分红记录时，红利下发日必须相等，否则不给保存
	            	TrdFinProductDealTotal cond = new TrdFinProductDealTotal();
	            	cond.setFinprodId(dto.getFinprodId());
	            	cond.setPs(DictCodeList.交易方向_产品现金分红.getValue());
	            	cond.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
	            	cond.setTradeDate(dto.getBonusCashEndDate03());
//	            	List<TrdFinProductDealTotal> listTrdFinProductDealTotal = restTemplate
//	            			.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
//	                        + "/FinProductCommon/queryTrdFinProductDealTotalByModel",cond,TrdFinProductDealTotal.class);
	            	List<TrdFinProductDealTotal> listTrdFinProductDealTotal = finNetProductDurationService.queryTrdFinProductDealTotalByModel(cond);
	            	
	            	if(listTrdFinProductDealTotal!=null && listTrdFinProductDealTotal.size()>0){
	            		TrdFinProductDealTotal trdFinProductDealTotal = listTrdFinProductDealTotal.get(0);
	            		if(dto.getBonusCashBonusDate03() != null && dto.getBonusCashBonusDate03().compareTo(trdFinProductDealTotal.getSettleDate())!=0){
	            			errorList.add(new ValidMessage("同一天存在多条计息结束日相同的分红记录时，红利下发日必须相等"));
	            		}            		
	            	}
            	}
            }else if(DictCodeList.交易方向_产品红利转投.getValue().equals(dealType)){
            	//红利再投无需生成指令信息
            	isCheckTrdTransferOrder = false;
            	//计息结束日不能为空
            	if(dto.getBonusShareEndDate03()==null){
            		errorList.add(new ValidMessage("bonusShareEndDate03","计息结束日不能为空"));
            	}
            	//再投确认日不能为空
            	if(dto.getBonusShareConfirmDate03()==null ||dto.getBonusShareConfirmDate03().compareTo(sysDate)<0){
                    errorList.add(new ValidMessage("bonusShareConfirmDate03","再投确认日不能为空，且必须大于等于平台日！"));
                }
            	//modify by wm 2019-06-19 start 根据需求去除红利再投金额为负的限制
            	//分红金额不能为空，且大于0
//            	if(dto.getBonusShareAmt03()==null || dto.getBonusShareAmt03().compareTo(BigDecimal.ZERO)<0){
//                    errorList.add(new ValidMessage("bonusShareAmt03",getMessage("FIN_PROD_E_0091","分红金额")));
//                } 
            	if(dto.getBonusShareAmt03()==null &&(!StringUtil.equals(dto.getOperateType(), "delete")&&!StringUtil.equals(dto.getOperateType(), "revoke"))){
            		errorList.add(new ValidMessage("bonusShareAmt03",getMessage("FIN_PROD_E_0099","红利再投金额")));
            	} 
            	//modify by wm 2019-06-19 end 根据需求去除红利再投金额为负的限制
            	//分红后总份额为空，且大于0
            	if(dto.getBonusShareAfterShareNum03()==null || dto.getBonusShareAfterShareNum03().compareTo(BigDecimal.ZERO)<0){
                    errorList.add(new ValidMessage("bonusShareAfterShareNum03",getMessage("FIN_PROD_E_0091","分红后总份额")));
                } 
            	if(errorList.isEmpty()){
            		//同一天存在多条计息结束日相同的分红记录时，红利下发日必须相等，否则不给保存
                	TrdFinProductDealTotal cond = new TrdFinProductDealTotal();
                	cond.setFinprodId(dto.getFinprodId());
                	cond.setPs(DictCodeList.交易方向_产品红利转投.getValue());
                	cond.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
                	cond.setTradeDate(dto.getBonusShareEndDate03());
//                	List<TrdFinProductDealTotal> listTrdFinProductDealTotal = restTemplate
//                			.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
//                            + "/FinProductCommon/queryTrdFinProductDealTotalByModel",cond,TrdFinProductDealTotal.class);
                	List<TrdFinProductDealTotal> listTrdFinProductDealTotal = finNetProductDurationService.queryTrdFinProductDealTotalByModel(cond);
                	if(listTrdFinProductDealTotal!=null && listTrdFinProductDealTotal.size()>0){
                		TrdFinProductDealTotal trdFinProductDealTotal = listTrdFinProductDealTotal.get(0);
                		if(dto.getBonusShareConfirmDate03().compareTo(trdFinProductDealTotal.getSettleDate())!=0){
                			errorList.add(new ValidMessage("同一天存在多条计息结束日相同的分红记录时，再投确认日必须相等"));
                		}            		
                	}
            	}            	
            }
        }
        //add by wm 增加货币型产品认购后续不能存在其他存续期交易控制 2019-06-18 start
        if(DictCodeList.交易方向_产品认购.getValue().equals(dealType)){
        	//周期型产品不校验       20191023
        	if(!DictCodeList.是否_是.getValue().equals(dto.getIsCycle())) {
        		if(finNetProductDurationService.queryIsExistSubTrade(dto)>0){
            		errorList.add(new ValidMessage("后续存在负债端交易!"));
            	}
        	}
        }
        //add by wm 增加货币型产品认购后续不能存在其他存续期交易控制 2019-06-18 end
        if (bussTrdTransferOrderService != null && isCheckTrdTransferOrder) {
            List<ValidMessage> list = bussTrdTransferOrderService.check(dto.getBussTrdTransferOrder());
            errorList.addAll(list);
        } 
        return errorList;
    }

}
