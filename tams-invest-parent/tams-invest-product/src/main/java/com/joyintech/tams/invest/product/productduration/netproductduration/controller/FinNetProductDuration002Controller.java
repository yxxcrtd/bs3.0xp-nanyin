/*
 * 文件名：FinNetProductDurationControl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zqz
 * 修改时间：2019年1月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.productduration.netproductduration.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
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
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.component.business.trade.impl.BussTrdAvailableCashQueryImplService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.productduration.expire.service.FinProductExpire002Service;
import com.joyintech.tams.invest.product.productduration.netproductduration.dto.FinCashPlanDto;
import com.joyintech.tams.invest.product.productduration.netproductduration.dto.FinNetProductDurationDto;
import com.joyintech.tams.invest.product.productduration.netproductduration.dto.FinNetProductDurationLayerDto;
import com.joyintech.tams.invest.product.productduration.netproductduration.service.FinNetProductDurationService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 净值产品存续期交易录入
 * 〈功能详细描述〉
 * @author zqz
 * @version 1.0 2019年1月15日
 * @see FinNetProductDuration002Controller
 * @since 1.0
 */
@Controller
@PageId("FinNetProductDuration")
@RequestMapping("/invest/product/productduration/netproductDuration/FinNetProductDuration")
public class FinNetProductDuration002Controller extends BaseController{

    @Autowired
    private FinNetProductDurationService finNetProductDurationService;
    
    /**
     * 业务标号生成共通
     */
    @Autowired(required=false)
    private BaseGetBusinessId baseGetBusinessId;
    
    /**
     * 平台日期组件接口
     */
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;
    
    /**
     * 与日期有关日期组件
     */
    @Autowired(required=false)
    private DateCalService dateCalService;
    /**
     * {@linkplain}  add by dingjunfeng
     */
    @Autowired
    private FinProductExpire002Service finProductExpire002Service;
    /**
     * 查询托管户余额 add by dingjunfeng 
     */
    @Autowired
    private BussTrdAvailableCashQueryImplService bussTrdAvailableCashQueryImplService; 
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        setSelectOptionToModel(model, DictCode.币种, DictCode.交易方向);
        String tradeId = getParam("tradeId");
        String dealType = getParam("dealType");
        String workflowId = getParam("workflowId");
        if(!StringUtil.isNotEmpty(tradeId) && (!StringUtil.isNotEmpty(workflowId) || WorkFlowUtil.isNotFromToDoList())){
            //新建 初始化
        	FinNetProductDurationDto finNetProductDurationDto = new FinNetProductDurationDto();  
        	//净值型周期型新增	期次管理方式	系统参数	20190923
        	finNetProductDurationDto.setManeWay(
        			finNetProductDurationService.getSysParameter(DictCodeListExt1.子系统代码_投管.getValue(), BussinessConstant.PERIODIC_MANAGE_INFORMATION));		//debug

            if(StringUtil.isNotEmpty(getParam("remType"))) {
                finNetProductDurationDto.setFinprodMarketId(getParam("finprodId"));

                model.addAttribute("remType", getParam("remType"));

            }

            if (StringUtil.isNotEmpty(finNetProductDurationDto.getFinprodMarketId())){
                Map<String,Object> map = new HashMap<>();
                map.put("initValue",finNetProductDurationDto.getFinprodMarketId());
                map.put("FINPROD_TYPE_TG_F16", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
                List<SelectOptionDto> list = finNetProductDurationService.getFinprodMarketIdList(map);
                if (!StringUtil.isEmptyList(list)) {
                    String [] str = list.get(0).getObject().toString().split(",");
                    model.addAttribute("continueFlag",str.length>=15?str[15]:"");
                }
            }

            model.addAttribute("entity", finNetProductDurationDto);
        }else if(StringUtil.isNotEmpty(tradeId) && (!StringUtil.isNotEmpty(workflowId) || WorkFlowUtil.isNotFromToDoList())){
            //查看 或修改初始化
        	Map<String,Object> param = new HashMap<String,Object>();
        	param.put("tradeId", tradeId);
        	param.put("dealType", dealType);
        	param.put("finprodId", getParam("finProdId"));
        	param.put("profitType", getParam("profitType"));
            FinNetProductDurationDto finNetProductDurationDto = finNetProductDurationService.selectInfo(param);
            
            if(StringUtil.isNotEmpty(finNetProductDurationDto.getBonusShareEndDate03())) {
            	Map<String,Object> param1 = new HashMap<String,Object>();
                param1.put("finprodId", finNetProductDurationDto.getFinprodId());
                param1.put("netValueDate", DateUtil.format(finNetProductDurationDto.getBonusShareEndDate03()));
            	FinNetProductDurationDto netValueDto = finNetProductDurationService.getNetValueInfo(param1);
            	if(finNetProductDurationDto.getBonusShareAmt03()==null&&finNetProductDurationDto.getBonusShareAmtNum03()!=null){
            	    finNetProductDurationDto.setBonusShareAmt03(finNetProductDurationDto.getBonusShareAmtNum03());
            	}
            	if(netValueDto!=null){
            	    finNetProductDurationDto.setBonusShareAfterShareNum03((finNetProductDurationDto.getBonusShareAmt03()==null?
            				BigDecimal.ZERO:finNetProductDurationDto.getBonusShareAmt03())
            				.add(netValueDto.getPurDealNum() == null?BigDecimal.ZERO:netValueDto.getPurDealNum()));
            		// 2019年9月10日17:41:01 魏鹏：分红后份额已经累加过了，再加就是双倍了
            		//finNetProductDurationDto.setBonusShareAfterShareNum03(netValueDto.getPurDealNum()==null?BigDecimal.ZERO:netValueDto.getPurDealNum());
            	} else {
            		finNetProductDurationDto.setBonusShareAfterShareNum03(finNetProductDurationDto.getBonusShareAmt03());
            	}
            }
            String operateType = getParam("operateType");
            finNetProductDurationDto.setOperateType(operateType);
            //净值型周期型新增	期次管理方式	系统参数	20190923
        	finNetProductDurationDto.setManeWay(
        			finNetProductDurationService.getSysParameter(DictCodeListExt1.子系统代码_投管.getValue(), BussinessConstant.PERIODIC_MANAGE_INFORMATION));		//debug
            if (StringUtil.isNotEmpty(finNetProductDurationDto.getFinprodMarketId())){
                Map<String,Object> map = new HashMap<>();
                map.put("initValue",finNetProductDurationDto.getFinprodMarketId());
                map.put("FINPROD_TYPE_TG_F16", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
                List<SelectOptionDto> list = finNetProductDurationService.getFinprodMarketIdList(map);
                if (!StringUtil.isEmptyList(list)) {
                    String [] str = list.get(0).getObject().toString().split(",");
                    model.addAttribute("continueFlag",str.length>=15?str[15]:"");
                }
            }
            model.addAttribute("entity", finNetProductDurationDto);
            //交易类型下拉枚举值根据产品代码联动
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("isCycle", finNetProductDurationDto.getIsCycle());
            map.put("operationType", finNetProductDurationDto.getOperationType());
            map.put("profitType", finNetProductDurationDto.getProfitType()); 
            map.put("finprodId", finNetProductDurationDto.getFinprodId());
            List<SelectOptionDto> selectOptionDtoList = finNetProductDurationService.getDealTypeList(map);
            model.addAttribute("PS", JsonUtils.objectArray2Json(selectOptionDtoList.toArray()));
            if((DictCodeList.交易方向_产品认购.getValue().equals(finNetProductDurationDto.getDealType())
            		||DictCodeList.交易方向_产品申购.getValue().equals(finNetProductDurationDto.getDealType())) &&
            			DictCodeList.是否_是.getValue().endsWith(finNetProductDurationDto.getIsCycle()) && 
            			"02".equals(finNetProductDurationDto.getManeWay())&&
            			(DictCodeListExt2.期限类型_单一固定期限.getValue().equals(finNetProductDurationDto.getTermType())||
            					DictCodeListExt2.期限类型_多固定期限.getValue().equals(finNetProductDurationDto.getTermType()))) {
            	//期限下拉枚举
                Map<String, Object> requestMap = new HashMap<>();
            	requestMap.put("finprodId", finNetProductDurationDto.getFinprodId());	//产品代码
            	List<SelectOptionDto> termDaysList = finNetProductDurationService.getTermDaysList(requestMap);
            	model.addAttribute("subTermDaysSelect", JsonUtils.objectArray2Json(termDaysList.toArray()));
            	model.addAttribute("purTermDaysSelect", JsonUtils.objectArray2Json(termDaysList.toArray()));
            }
            if((DictCodeList.交易方向_产品认购.getValue().equals(finNetProductDurationDto.getDealType())
            		||DictCodeList.交易方向_产品申购.getValue().equals(finNetProductDurationDto.getDealType())) &&
            			DictCodeList.是否_是.getValue().endsWith(finNetProductDurationDto.getIsCycle()) && 
            			"01".equals(finNetProductDurationDto.getManeWay())) {
            	//期次代码下拉枚举
                Map<String, Object> requestMap = new HashMap<>();
                requestMap.put("finprodId", finNetProductDurationDto.getFinprodId());	//产品代码
                if(DictCodeList.交易方向_产品认购.getValue().equals(finNetProductDurationDto.getDealType())) {
                	requestMap.put("vDate", finNetProductDurationDto.getvDate());			//产品起息日
                }else {
                	requestMap.put("vDate", finNetProductDurationDto.getPurConfirmDate());			//认购产品确认日
                }
            	List<SelectOptionDto> periodsList = finNetProductDurationService.getPeriodsList(requestMap);
            	model.addAttribute("subPeriodsSelect", JsonUtils.objectArray2Json(periodsList.toArray()));
            	model.addAttribute("purPeriodsSelect", JsonUtils.objectArray2Json(periodsList.toArray()));
            }
        }else if(!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotBlank(workflowId)){
            //流程初始化
            FinNetProductDurationDto finNetProductDurationDto = finNetProductDurationService.getBussinessEntityByWorkflowId(workflowId);
            // 设置到model中
            model.addAttribute("entity", finNetProductDurationDto);
            if (StringUtil.isNotEmpty(finNetProductDurationDto.getFinprodMarketId())){
                Map<String,Object> map = new HashMap<>();
                map.put("initValue",finNetProductDurationDto.getFinprodMarketId());
                map.put("FINPROD_TYPE_TG_F16", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
                List<SelectOptionDto> list = finNetProductDurationService.getFinprodMarketIdList(map);
                if (!StringUtil.isEmptyList(list)) {
                    String [] str = list.get(0).getObject().toString().split(",");
                    model.addAttribute("continueFlag",str.length>=15?str[15]:"");
                }
            }
            if(finNetProductDurationDto.getBussTrdTransferOrder()!=null 
                && finNetProductDurationDto.getBussTrdTransferOrder().size()>0){
                model.addAttribute("entity_bussRepFinProductInfoDto", 
                       JsonUtils.object2Json(finNetProductDurationDto.getBussTrdTransferOrder().get(0)));
            }
            //交易类型下拉枚举值根据产品代码联动
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("isCycle", finNetProductDurationDto.getIsCycle());
            map.put("operationType", finNetProductDurationDto.getOperationType());
            map.put("profitType", finNetProductDurationDto.getProfitType());           
            map.put("finprodId", finNetProductDurationDto.getFinprodId());
            List<SelectOptionDto> selectOptionDtoList = finNetProductDurationService.getDealTypeList(map);
            model.addAttribute("PS", JsonUtils.objectArray2Json(selectOptionDtoList.toArray()));
            if((DictCodeList.交易方向_产品认购.getValue().equals(finNetProductDurationDto.getDealType())
            		||DictCodeList.交易方向_产品申购.getValue().equals(finNetProductDurationDto.getDealType())) &&
            			DictCodeList.是否_是.getValue().endsWith(finNetProductDurationDto.getIsCycle()) && 
            			"02".equals(finNetProductDurationDto.getManeWay())&&
            			(DictCodeListExt2.期限类型_单一固定期限.getValue().equals(finNetProductDurationDto.getTermType())||
            					DictCodeListExt2.期限类型_多固定期限.getValue().equals(finNetProductDurationDto.getTermType()))) {
            	//期限下拉枚举
                Map<String, Object> requestMap = new HashMap<>();
            	requestMap.put("finprodId", finNetProductDurationDto.getFinprodId());	//产品代码
            	List<SelectOptionDto> termDaysList = finNetProductDurationService.getTermDaysList(requestMap);
            	model.addAttribute("subTermDaysSelect", JsonUtils.objectArray2Json(termDaysList.toArray()));
            	model.addAttribute("purTermDaysSelect", JsonUtils.objectArray2Json(termDaysList.toArray()));
            }
            if((DictCodeList.交易方向_产品认购.getValue().equals(finNetProductDurationDto.getDealType())
            		||DictCodeList.交易方向_产品申购.getValue().equals(finNetProductDurationDto.getDealType())) &&
            			DictCodeList.是否_是.getValue().endsWith(finNetProductDurationDto.getIsCycle()) && 
            			"01".equals(finNetProductDurationDto.getManeWay())) {
            	//期次代码下拉枚举
                Map<String, Object> requestMap = new HashMap<>();
                requestMap.put("finprodId", finNetProductDurationDto.getFinprodId());	//产品代码
                if(DictCodeList.交易方向_产品认购.getValue().equals(finNetProductDurationDto.getDealType())) {
                	requestMap.put("vDate", finNetProductDurationDto.getvDate());			//产品起息日
                }else {
                	requestMap.put("vDate", finNetProductDurationDto.getPurConfirmDate());			//认购产品确认日
                }
            	List<SelectOptionDto> periodsList = finNetProductDurationService.getPeriodsList(requestMap);
            	model.addAttribute("subPeriodsSelect", JsonUtils.objectArray2Json(periodsList.toArray()));
            	model.addAttribute("purPeriodsSelect", JsonUtils.objectArray2Json(periodsList.toArray()));
            }
        }
        Date date = iPlatformDate.getSysDate();
        model.addAttribute("iPlatformDate",DateUtil.format(date));
        model.addAttribute("workflowId",workflowId);
        //添加004权限控制 add by liupeilin 2020-01-15 begin
        if("004".equals(CacheUtil.getSysParam("CUSTOMER_BANK_CODE"))){
            model.addAttribute("BANK_CODE", "004");
        }
        //添加004权限控制 add by liupeilin 2020-01-15 end

        return super.init(model);
    }
    
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) FinNetProductDurationDto dto, BindingResult result) {
    	AjaxResult result2 = new AjaxResult();
    	//提示信息检查
    	List<JoyinMessage> joyinMessages = new ArrayList<>();
        if(result.getAllErrors().isEmpty() &&  StringUtils.isNoneEmpty(dto.getOperateType())) {
            if(StringUtil.equals(dto.getOperateType(), "save")||StringUtil.equals(dto.getOperateType(), "submit")){
            	if(DictCodeList.收益类型_净值型.getValue().equals(dto.getProfitType())){
                	if(DictCodeList.交易方向_产品认购.getValue().equals(dto.getDealType())){
                		
                	}else if(DictCodeList.交易方向_产品申购.getValue().equals(dto.getDealType())){
                		
                	}else if(DictCodeList.交易方向_产品赎回.getValue().equals(dto.getDealType())
                            || DictCodeList.交易方向_产品提前赎回.getValue().equals(dto.getDealType())
                            || DictCodeList.交易方向_产品违约赎回.getValue().equals(dto.getDealType())){
                		if(dateCalService.isDealCalendarHoliday(dto.getRedPayDate(), dto.getCalendarId())){
                        	joyinMessages.add(new JoyinMessage(getMessage("BUSS_COM_E_0039",
                                    getMessage("FinNetProductDuration.payDate")), MessageType.确认消息));
                        }
                		
                	}else if(DictCodeList.交易方向_产品到期.getValue().equals(dto.getDealType())
                            || DictCodeList.交易方向_提前终止.getValue().equals(dto.getDealType())){
                		if(dateCalService.isDealCalendarHoliday(dto.getExpirePayDate(), dto.getCalendarId())){
                        	joyinMessages.add(new JoyinMessage(getMessage("BUSS_COM_E_0039",
                                    getMessage("FinNetProductDuration.payDate")), MessageType.确认消息));
                        }
                	}else if(DictCodeList.交易方向_份额强增.getValue().equals(dto.getDealType()) ||
                    		DictCodeList.交易方向_份额强减.getValue().equals(dto.getDealType())){
                		if(dateCalService.isDealCalendarHoliday(dto.getAddReduceConfirmDate(), dto.getCalendarId())){
                        	joyinMessages.add(new JoyinMessage(getMessage("BUSS_COM_E_0039",
                                    getMessage("FinNetProductDuration.confirmDate")), MessageType.确认消息));
                        }
                	}
                }else if(DictCodeList.收益类型_货币型.getValue().equals(dto.getProfitType())){
                	if(DictCodeList.交易方向_产品认购.getValue().equals(dto.getDealType())){
                		
                	}else if(DictCodeList.交易方向_产品申购.getValue().equals(dto.getDealType())){
                		
                	}else if(DictCodeList.交易方向_产品赎回.getValue().equals(dto.getDealType())
                            || DictCodeList.交易方向_产品提前赎回.getValue().equals(dto.getDealType())
                            || DictCodeList.交易方向_产品违约赎回.getValue().equals(dto.getDealType())){
                		if(dateCalService.isDealCalendarHoliday(dto.getRedPrinPayDate03(), dto.getCalendarId())){
                        	joyinMessages.add(new JoyinMessage(getMessage("BUSS_COM_E_0039",
                                    getMessage("FinNetProductDuration.prinPayDate")), MessageType.确认消息));
                        }
                		if(dateCalService.isDealCalendarHoliday(dto.getRedProfitPayDate03(), dto.getCalendarId())){
                        	joyinMessages.add(new JoyinMessage(getMessage("BUSS_COM_E_0039",
                                    getMessage("FinNetProductDuration.profitPayDate")), MessageType.确认消息));
                        }
                        //软校验  本金金额和托管账户余额 yjm start 20190927
                        Map<String, Object> param = new HashMap<>();
                        param.put("ccy", DictCodeList.币种_人民币_CNY.getValue());
                        param.put("portfolioId", dto.getPortfolioId());
                        param.put("tradeDate", DateUtil.format(iPlatformDate.getSysDate()));
                        param.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());

                        BigDecimal secAcctBalance = BigDecimal.ZERO;
                        if (bussTrdAvailableCashQueryImplService != null && param.get("tradeDate") != null) {
                            secAcctBalance = bussTrdAvailableCashQueryImplService.getSecManageAvailableCash(param);
                        }                       
	                
                        if(dto.getRedPrinAmt03() !=null && dto.getRedPrinAmt03().compareTo(secAcctBalance)>0){
                            joyinMessages.add(new JoyinMessage("交易本金大于托管户剩余现金" + secAcctBalance+"", MessageType.确认消息));
                        }
                        //软校验  本金金额和托管账户余额 yjm end 20190927
                	}else if(DictCodeList.交易方向_产品到期.getValue().equals(dto.getDealType())
                            || DictCodeList.交易方向_提前终止.getValue().equals(dto.getDealType())){
                		if(dateCalService.isDealCalendarHoliday(dto.getExpirePrinPayDate03(), dto.getCalendarId())){
                        	joyinMessages.add(new JoyinMessage(getMessage("BUSS_COM_E_0039",
                                    getMessage("FinNetProductDuration.prinPayDate")), MessageType.确认消息));
                        }
                		if(dateCalService.isDealCalendarHoliday(dto.getExpireProfitPayDate03(), dto.getCalendarId())){
                        	joyinMessages.add(new JoyinMessage(getMessage("BUSS_COM_E_0039",
                                    getMessage("FinNetProductDuration.profitPayDate")), MessageType.确认消息));
                        }
                	}else if(DictCodeList.交易方向_份额强增.getValue().equals(dto.getDealType()) ||
                    		DictCodeList.交易方向_份额强减.getValue().equals(dto.getDealType())){
                		if(dateCalService.isDealCalendarHoliday(dto.getAddReduceConfirmDate03(), dto.getCalendarId())){
                        	joyinMessages.add(new JoyinMessage(getMessage("BUSS_COM_E_0039",
                                    getMessage("FinNetProductDuration.confirmDate")), MessageType.确认消息));
                        }
                	}else if(DictCodeList.交易方向_产品现金分红.getValue().equals(dto.getDealType())){
                		if(dateCalService.isDealCalendarHoliday(dto.getBonusCashBonusDate03(), dto.getCalendarId())){
                        	joyinMessages.add(new JoyinMessage(getMessage("BUSS_COM_E_0039",
                                    getMessage("FinNetProductDuration.bonusDate")), MessageType.确认消息));
                        }
                	}else if(DictCodeList.交易方向_产品红利转投.getValue().equals(dto.getDealType())){
                		if(dateCalService.isDealCalendarHoliday(dto.getBonusShareConfirmDate03(), dto.getCalendarId())){
                        	joyinMessages.add(new JoyinMessage(getMessage("BUSS_COM_E_0039",
                                    getMessage("FinNetProductDuration.shareConfirmDate")), MessageType.确认消息));
                        }
                	}
                }
            }
            if (StringUtil.isEmptyList(joyinMessages)) {
            	String str1 = "";
            	String btnName = "";
            	BussTrdTransferOrderDto order = null;
            	if(dto.getBussTrdTransferOrder()!=null && dto.getBussTrdTransferOrder().size()>0){
            		order = dto.getBussTrdTransferOrder().get(0);
            		if(!StringUtil.isNotEmpty(order.getPayAcctNo())){
            			str1 = "付款账户未填,";
            		}
            		if(!StringUtil.isNotEmpty(order.getRecAcctNo())) {
            			str1 =str1 + "收款账户未填,";
            		}
            	}
            	
            	if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SAVE,
            			dto.getOperateType())) {
            		btnName = "请确认是否保存?";
            	}
            	else if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SUBMIT,
            			dto.getOperateType())) {
            		btnName = "请确认是否提交?";
            	// added by dongenlu 20190311 修改产品端存续期交易的撤销提醒消息错误的问题。 begin
            	} else if (StringUtil.equalsIgnoreCase("revoke",
            			dto.getOperateType())) {
            		btnName = "请确认是否撤销?";
            	// added by dongenlu 20190311 修改产品端存续期交易的撤销提醒消息错误的问题。 end
            	}
            	result2.setData(this.getMessage(str1+btnName));	
            }
            else{
            	AjaxResult wr = commonCheck(result, dto.getOperateType());
            	wr.setJoyinMessages(joyinMessages);
            	return wr;
            }
        }
        else{
        	AjaxResult wr = commonCheck(result, dto.getOperateType());
        	wr.setJoyinMessages(joyinMessages);
        	return wr;
        }
        return result2;
    }
    
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) FinNetProductDurationDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }    
        DictCodeList dictCodeList = finNetProductDurationService.getBussinessDictCodeList(dto);
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            if (StringUtil.isEmpty(dto.getTradeId())) {
                BussinessCodeParameter parameter = new BussinessCodeParameter();
                parameter.setCommonBusinessType(dictCodeList);
                String busiCode = baseGetBusinessId.buildBussinessCode(parameter);
                dto.setTradeId(busiCode);
            }
        }
        ProcessParameter<FinNetProductDurationDto> processParameter = new ProcessParameter<FinNetProductDurationDto>();
        WorkFlowParameter<FinNetProductDurationDto> params = new WorkFlowParameter<FinNetProductDurationDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getTradeId());
        //add by gcl 2019/3/28 start
        if(dictCodeList.getValue().equals("FINPROD18")){
        	params.setBusinessType(DictCodeList.共通业务类型_净值型产品分红方案管理.getValue());
        }else{
        params.setBusinessType(DictCodeList.共通业务类型_净值型产品存续期交易新建.getValue());}
       //add by gcl 2019/3/28 end
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> "净值型产品存续期交易，产品代码" + "(" + entity.getFinprodMarketId() 
            + ")-交易类型 ：" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, entity.getDealType()));
        
        processParameter.setWorkFlowParameter(params);
        String msg = finNetProductDurationService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 
     * 根据产品处理模式(普通、周期)，收益类型(净值、货币)、运作方式(开放、封闭)确认产品支持的交易类型
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getDealTypeList")
    public List<SelectOptionDto> getDealTypeList(@RequestBody FinNetProductDurationDto dto){
        Map<String,Object> map = RequestUtil.getPageParam();
        map.put("isCycle", dto.getIsCycle());
        map.put("operationType", dto.getOperationType());
        map.put("profitType", dto.getProfitType());
        map.put("finprodId", dto.getFinprodId());
        return finNetProductDurationService.getDealTypeList(map);
    }
    
    /**
     * 产品代码的联想下拉框事件--获取用户拥有权限的净值化产品
     * @return 
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getFinprodMarketIdList")
    public List<SelectOptionDto> getFinprodMarketIdList(String pageModel){
        Map<String,Object> map = RequestUtil.getPageParam();
        map.put("FINPROD_TYPE_TG_F16", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        List<String> profitTypeList = new ArrayList<String>();
        profitTypeList.add(DictCodeList.收益类型_净值型.getValue());
        profitTypeList.add(DictCodeList.收益类型_货币型.getValue());
        map.put("profitTypeList", profitTypeList);
        //发行状态 发行成功
        map.put("ISSUE_STATUS_01", DictCodeListExt1.发行状态_发行成功.getValue());
        map.put("ISSUE_STATUS_03", DictCodeListExt1.发行状态_确认发行.getValue());
        //交易方向 提前终止、到期
        //预防产品做了到期交易或者提前终止后，再去查看明细信息时产品代码为空
        if(pageModel == null || !StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_REFERENCE)) {
            map.put("PS_C20", DictCodeList.交易方向_提前终止.getValue());
            map.put("PS_C5", DictCodeList.交易方向_产品到期.getValue());
            map.put("isCancel", DictCodeList.是否_否.getValue());
            map.put("userName", LoginUserUtil.getUserName());
        }

        return finNetProductDurationService.getFinprodMarketIdList(map);
    }
    
    /**
     * 根据产品id和分支 查询期次信息
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getCycleInfo")
    public FinNetProductDurationDto getCycleInfo(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return finNetProductDurationService.getCycleInfo(map);
    }
    
    /**
     * 查询产品 查询日期最近的净值信息
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getNetValueInfo")
    public FinNetProductDurationDto getNetValueInfo(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return finNetProductDurationService.getNetValueInfo(map);
    }
    /**
     * 查询产品 查询日期最近的净值信息
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getNetValueInfo1")
    public FinNetProductDurationDto getNetValueInfo1(){
        Map<String,Object> map = RequestUtil.getPageParam();
        BigDecimal purDealNum = BigDecimal.ZERO;
        FinNetProductDurationDto dto = finNetProductDurationService.getNetValueInfo(map);
        if(dto!=null && dto.getPurDealNum()!=null){
            purDealNum = dto.getPurDealNum();
        }
        BigDecimal tdyBuyShareAmt = finNetProductDurationService.getTdyBuyShareAmt(map);
        if(tdyBuyShareAmt!=null){
            purDealNum = purDealNum.add(tdyBuyShareAmt);
        }
        BigDecimal afterTdySellShareAmt = finNetProductDurationService.getAfterTdySellShareAmt(map);
        if(afterTdySellShareAmt!=null){
            purDealNum = purDealNum.subtract(afterTdySellShareAmt);
        }
        if(dto!=null){
            dto.setPurDealNum(purDealNum);
        }
        
        return dto;
    }
    
    @ResponseBody
    @RequestMapping("doRevoke")
    public AjaxResult doRevoke(@RequestBody @Validated({ValidateGroupDelete.class}) FinNetProductDurationDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        } 
        DictCodeList dictCodeList = finNetProductDurationService.getBussinessDictCodeList(dto);
        ProcessParameter<FinNetProductDurationDto> processParameter = new ProcessParameter<FinNetProductDurationDto>();
        WorkFlowParameter<FinNetProductDurationDto> params = new WorkFlowParameter<FinNetProductDurationDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getTradeId());
        //add by gcl 2019/4/1 start
        if(dictCodeList.getValue().equals("FINPROD18")){
        	params.setBusinessType(DictCodeList.共通业务类型_净值型产品分红方案管理.getValue());
        }else{
            params.setBusinessType(DictCodeList.共通业务类型_净值型产品存续期交易新建.getValue());}
       //add by gcl 2019/4/1 end       
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> "净值型产品存续期交易撤销，产品代码" + "(" + entity.getFinprodMarketId() 
            + ")-交易类型 ：" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, entity.getDealType()));
        
        processParameter.setPageId(this.getPageId());
        processParameter.setOperationType(OperationType.REVOKE);
        processParameter.setWorkFlowParameter(params);
        String msg = finNetProductDurationService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    @ResponseBody
    @RequestMapping("/queryBonusRule")
    public FinCashPlanDto queryBonusRule(@RequestBody FinNetProductDurationDto dto){
        Map<String,Object> map = RequestUtil.getPageParam();
        map.put("finprodId", dto.getFinprodId());
        map.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
        map.put("cashType2", DictCodeListExt1.现金流二级类型_利息.getValue());
        map.put("PS_C9", DictCodeList.交易方向_产品现金分红.getValue());
        map.put("PS_C10", DictCodeList.交易方向_产品红利转投.getValue());
        map.put("payCycle07", DictCodeList.分红规则_日付.getValue());
        map.put("payCycle08", DictCodeList.分红规则_申购同频.getValue());
        map.put("payCycle09", DictCodeList.分红规则_不规则.getValue());
        map.put("TRADE_STATUS01", DictCodeListExt1.交易状态_初始.getValue());
        map.put("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        //add by wm 增加平台日参数 start 2019-06-18
        map.put("PlatformDay", DateUtil.getSysDate());
        map.put("dealType", dto.getDealType());
        //add by wm 增加平台日参数 end 2019-06-18
        FinCashPlanDto finCashPlanDto = finNetProductDurationService.queryBonusRule(map);
        if(StringUtil.equals(DictCodeList.交易方向_产品红利转投.getValue(), dto.getDealType())&&finCashPlanDto!=null){
        	if(finCashPlanDto.getMdate()!=null){
//            	map.put("netValueDate", DateUtil.format(DateUtil.addDay(finCashPlanDto.getMdate(), -1)));
        		map.put("netValueDate", DateUtil.format(finCashPlanDto.getMdate()));
        	}
        	else{
//            	map.put("netValueDate", DateUtil.addDay(DateUtil.getSysDate(), -1));
        		map.put("netValueDate", DateUtil.getSysDate());
        	}
        	FinNetProductDurationDto finNetProductDurationDto = finNetProductDurationService.getNetValueInfo(map);
        	if(finNetProductDurationDto!=null){
        		finCashPlanDto.setBonusShareAmtT(finNetProductDurationDto.getPurDealAmt());
        		finCashPlanDto.setBonusShareAfterShareNumBf(finNetProductDurationDto.getPurDealNum());
        	}
        }
        return finCashPlanDto;
    }
    //add by  dingjunfeng
    @ResponseBody
    @RequestMapping("checkDuraAmt")
    public AjaxResult checkDuraAmt(@Valid @RequestBody FinNetProductDurationDto dto,
                                   BindingResult br) {
        AjaxResult result = commonCheck(br, dto.getOperateType());
        // 提示信息检查
        List<JoyinMessage> joyinMessages = new ArrayList<>();
        dto.setBranch(0l);
        if (DictCodeList.交易方向_产品到期.getValue().equals(dto.getDealType())
            || DictCodeList.交易方向_提前终止.getValue().equals(dto.getDealType())) {
            // dto.setPortfolioId(dto.getPortfolioId());
            dto.setNdate(iPlatformDate.getSysDate());
            Integer bokPositionPrd = finNetProductDurationService.queryMaxDatePtlSecPosition(dto);
            if (bokPositionPrd != null && bokPositionPrd > 0) {
                joyinMessages.add(
                    new JoyinMessage(this.getMessage("产品资产未处置结束！"), MessageType.确认消息));
            }
            if (StringUtil.isNotEmpty(dto.getPortfolioId())) {
                Date expirePayDate = dto.getExpirePayDate();
                BigDecimal expireDealAmt = dto.getExpireDealAmt();
                if(DictCodeList.收益类型_货币型.getValue().equals(dto.getProfitType())){
                    expirePayDate = dto.getExpirePrinPayDate03();
                    expireDealAmt = dto.getExpireDealAmt03();
                }     
                Map<String, Object> map = new HashMap<String, Object>();
                Integer counts = finProductExpire002Service.checkpayAmt("",
                    DateUtil.format(expirePayDate), dto.getPortfolioId(),
                    dto.getFinprodId());
                // FinProductFeePay001Dto dto1 =
                // (FinProductFeePay001Dto)map.get("finProductFeePay001Dto1");
                /*
                 * if(map.get("finProductFeePay001Dto") != null){ joyinMessages.add(new
                 * JoyinMessage(this.getMessage("存在费用尚未支付，请先支付费用"),MessageType.确认消息)); }
                 */
                if (counts != null && counts > 0) {
                    // if(dto1.getFeeName() != null || dto1.getFeeId() != null){
                    joyinMessages.add(
                        new JoyinMessage(this.getMessage("存在费用尚未支付，请先支付费用"), MessageType.确认消息));
                    // }
                }
                map.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
                map.put("tradeDate", DateUtil.format(expirePayDate));
                map.put("ccy", dto.getCcy());
                map.put("portfolioId", dto.getPortfolioId());
                BigDecimal secAcctBalance = bussTrdAvailableCashQueryImplService.getSecManageAvailableCash(
                    map);
                if (secAcctBalance.compareTo(expireDealAmt) < 0) {
                    joyinMessages.add(
                        new JoyinMessage(this.getMessage("产品剩余资金不足，请核实"), MessageType.确认消息));
                }
                //delete by wm 去除此提示 start 2019-07-05
//                else if (secAcctBalance.compareTo(expireDealAmt) > 0) {
//                    joyinMessages.add(
//                        new JoyinMessage(this.getMessage("产品有多余剩余资金，请核实"), MessageType.确认消息));
//                }
                //delete by wm 去除此提示 end 2019-07-05
            }
        }
        //delete by wm 2019-07-10此处校验移至FinNetProductDurationDtoValidator中 start
//        else if (DictCodeList.交易方向_产品提前赎回.getValue().equals(dto.getDealType())
//                 || DictCodeList.交易方向_产品违约赎回.getValue().equals(dto.getDealType())
//                 || DictCodeList.交易方向_产品赎回.getValue().equals(dto.getDealType())) {
//            Date tradeDate = dto.getRedPayDate();
//            Date redConfirmDate = dto.getRedConfirmDate();
//            if(DictCodeList.收益类型_货币型.getValue().equals(dto.getProfitType())){
//                tradeDate = dto.getRedPrinPayDate03();
//                redConfirmDate = dto.getRedConfirmDate03();
//            }
//            Map<String, Object> map = new HashMap<String, Object>();
//            // Integer counts finProductExpire002Service.checkpayAmt("",dto.getRedPayDate() ==
//            // null?null:formatter.format(dto.getRedPayDate()),dto.getPortfolioId(),dto.getFinprodId());
//            map.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
////            map.put("tradeDate", tradeDate == null ? null : DateUtil.format(tradeDate));
//            map.put("ccy", dto.getCcy());
//            map.put("portfolioId", dto.getPortfolioId());
//            map.put("netValueDate", redConfirmDate == null ? null : DateUtil.format(DateUtil.addDay(redConfirmDate, -1)));
//            map.put("dealDate", DateUtil.getSysDate());
//            map.put("finprodId", dto.getFinprodId());
//            // BigDecimal secAcctBalance =
//            // bussTrdAvailableCashQueryImplService.getSecManageAvailableCash(map);
//            // BigDecimal secAcctBalance1 = new BigDecimal(0);
//            // if(StringUtil.isNotEmpty(finNetProductDurationService.getNetValueInfo(map))){
//            // secAcctBalance1 = finNetProductDurationService.getNetValueInfo(map).getPurDealNum();
//            // }
//            /*
//             * if(secAcctBalance1.compareTo(dto.getRedDealNum())<=0){
//             * if(StringUtil.isNotEmpty(dto.getRedDealAmt())){ //Map<String,Object> map =
//             * finProductExpire002Service.checkpayAmt("", formatter.format(dto.getmDate()));
//             * FinProductFeePay001Dto dto1 =
//             * (FinProductFeePay001Dto)map.get("finProductFeePay001Dto1");
//             * if(map.get("finProductFeePay001Dto") != null){ joyinMessages.add(new
//             * JoyinMessage(this.getMessage("存在费用尚未支付，请先支付费用"),MessageType.确认消息)); } if(dto1 !=
//             * null){ if(dto1.getFeeName() != null){ joyinMessages.add(new
//             * JoyinMessage(this.getMessage("存在费用尚未支付，请先支付费用"),MessageType.确认消息)); } }
//             * if(secAcctBalance.compareTo(dto.getRedDealAmt())<0){ joyinMessages.add(new
//             * JoyinMessage(this.getMessage("产品剩余资金不足，请核实"),MessageType.确认消息)); }else
//             * if(secAcctBalance.compareTo(dto.getRedDealAmt())>0){ joyinMessages.add(new
//             * JoyinMessage(this.getMessage("产品有多余剩余资金，请核实"),MessageType.确认消息)); } } }
//             */
//            FinNetProductDurationDto finNetProductDurationDto = finNetProductDurationService.getNetValueInfo(map);
//            if (finNetProductDurationDto == null || finNetProductDurationDto.getPurDealNum() == null
//                    || finNetProductDurationDto.getPurDealNum().compareTo(dto.getRedDealNum()) < 0) {
//                joyinMessages.add(new JoyinMessage(this.getMessage("产品交易份额不足,请核实"), MessageType.确认消息));
//            }
//
//        }
        //delete by wm 2019-07-10此处校验移至FinNetProductDurationDtoValidator中 end
        /*
           * else if(DictCodeList.交易方向_份额强减.getValue().equals(dto.getDealType())){
           * Map<String,Object> map = finProductExpire002Service.checkpayAmt("",
           * formatter.format(dto.getRedConfirmDate())); map.put("acctType",
           * DictCodeListExt1.资金账户类型_产品托管户.getValue()); map.put("tradeDate",
           * formatter.format(dto.getExpirePayDate())); map.put("ccy", dto.getCcy());
           * map.put("portfolioId",dto.getPortfolioId()); map.put("netValueDate",
           * dto.getRedConfirmDate03()==null?null:formatter.format(dto.getRedConfirmDate03()));
           * map.put("dealDate",formatter.format(iPlatformDate.getSysDate())); map.put("finprodId",
           * dto.getFinprodId()); BigDecimal secAcctBalance =
           * bussTrdAvailableCashQueryImplService.getSecManageAvailableCash(map);
           * if(secAcctBalance.compareTo(dto.getExpireDealAmt())<=0){
           * if(StringUtil.isNotEmpty(dto.getRedDealAmt())){ //Map<String,Object> map =
           * finProductExpire002Service.checkpayAmt("", formatter.format(dto.getmDate()));
           * FinProductFeePay001Dto dto1 =
           * (FinProductFeePay001Dto)map.get("finProductFeePay001Dto1");
           * if(map.get("finProductFeePay001Dto") != null){
           * result.setMessage(this.getMessage("存在费用尚未支付，请先支付费用"),MessageType.警告消息); return result;
           * } if(dto1 != null){ if(dto1.getFeeName() != null){
           * result.setMessage(this.getMessage("存在费用尚未支付，请先支付费用"),MessageType.警告消息); return result;
           * } } if(secAcctBalance.compareTo(dto.getRedDealAmt())<0){
           * result.setMessage(this.getMessage("产品剩余资金不足，请核实"),MessageType.警告消息); return result;
           * }else{
           * result.setMessage(this.getMessage("产品剩余资金为("+secAcctBalance+"),请核实"),MessageType.警告消息)
           * ; return result; } } } }
           */
        //modify by wm 2019-06-05 加入指令中付款人账户校验 start
//        if (StringUtil.isNotEmpty(joyinMessages)) {
//            result.setJoyinMessages(joyinMessages);
//        }
        if (StringUtil.isEmptyList(joyinMessages)) {
        	String str1 = "";
        	String btnName = "";
        	BussTrdTransferOrderDto order = null;
        	if(dto.getBussTrdTransferOrder()!=null && dto.getBussTrdTransferOrder().size()>0){
        		order = dto.getBussTrdTransferOrder().get(0);
        		if(!StringUtil.isNotEmpty(order.getPayAcctNo())){
        			str1 = "付款账户未填,";
        		}
        		
        		if(!StringUtil.isNotEmpty(order.getRecAcctNo())) {
        			str1 =str1 + "收款账户未填,";
        		}
        	}
        	if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SAVE,
        			dto.getOperateType())) {
        		btnName = "请确认是否保存?";
        	}
        	else if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SUBMIT,
        			dto.getOperateType())) {
        		btnName = "请确认是否提交?";
        	// added by dongenlu 20190311 修改产品端存续期交易的撤销提醒消息错误的问题。 begin
        	} else if (StringUtil.equalsIgnoreCase("revoke",
        			dto.getOperateType())) {
        		btnName = "请确认是否撤销?";
        	// added by dongenlu 20190311 修改产品端存续期交易的撤销提醒消息错误的问题。 end
        	}
        	result.setData(this.getMessage(str1+btnName));	
        }
        else{
        	result.setJoyinMessages(joyinMessages);
        }
        //modify by wm 2019-06-05 加入指令中付款人账户校验 end
        return result;
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
    public List<FinNetProductDurationLayerDto> queryLayAmtDetailByFinprodId() {
    	
        if (StringUtil.isNotEmpty(this.getParam(GlobalConstant.WORKFLOW_ID))) {
        	ProcessMark pro = finNetProductDurationService.getBusinessInfo(
                    this.getParam(GlobalConstant.WORKFLOW_ID));
        	logger.info("----------------debug_t: 进入待办if（WorkFlowUtil）tradeID = "+this.getParam("tradeId")+"---------------------");
            if (pro != null) {
            	FinNetProductDurationDto entity = JsonUtils.json2Object(
                    pro.getBusinessData(), FinNetProductDurationDto.class);
                return entity.getFinNetProductDurationLayerList();
            }
        }else if(StringUtil.isNotEmpty(this.getParam("tradeId"))){
        	logger.info("----------------debug_t: 进入交易if tradeID = "+this.getParam("tradeId")+"---------------------");
            return finNetProductDurationService.queryLayAmtDetailByBussId(RequestUtil.getPageParam());
        }
        logger.info("----------------debug_t: 进入新建 ---------------------");
        return finNetProductDurationService.queryLayAmtDetailByFinprodId(RequestUtil.getPageParam());
    	
    }
    /**
     * 根据金融产品代码查询投资期限
     * @return 投资周期
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getTermDaysList")
    public List<SelectOptionDto> getTermDaysList(@RequestBody FinNetProductDurationDto dto) {
    	Map<String, Object> requestMap = new HashMap<>();
    	requestMap.put("finprodId", dto.getFinprodId());	//产品代码
        return finNetProductDurationService.getTermDaysList(requestMap);
    }
    /**
     * 根据金融产品代码、起息日查询期次信息
     * @return 期次信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getPeriodsList")
    public List<SelectOptionDto> getPeriodsList(@RequestBody FinNetProductDurationDto dto) {
    	Map<String, Object> requestMap = new HashMap<>();
    	requestMap.put("finprodId", dto.getFinprodId());	//产品代码
    	requestMap.put("vDate", dto.getvDate());			//产品起息日
        return finNetProductDurationService.getPeriodsList(requestMap);
    }
    /**
     * 赎回查询期次信息
     * @return 
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getPeriodInfoList")
    public Page<FinNetProductDurationDto> getPeriodInfoList() {
    	Page<FinNetProductDurationDto> page = new Page<>();
    	Map<String, Object> map = RequestUtil.getPageParam();
    	if (StringUtil.isNotEmpty(this.getParam(GlobalConstant.WORKFLOW_ID))) {
    		ProcessMark pro = finNetProductDurationService.getBusinessInfo(
                    this.getParam(GlobalConstant.WORKFLOW_ID));
        	FinNetProductDurationDto entity = JsonUtils.json2Object(
                pro.getBusinessData(), FinNetProductDurationDto.class);
        	//净值型、周期型产品增加	20190924
        	if(DictCodeList.交易方向_产品赎回.getValue().equals(entity.getDealType())||
        			DictCodeList.交易方向_产品提前赎回.getValue().equals(entity.getDealType())||
        				DictCodeList.交易方向_产品违约赎回.getValue().equals(entity.getDealType())) {
        		List<FinNetProductDurationDto> list = entity.getRedCycleFinNetProductDurationDtoList();
        		page.setRoot(list);
        	}
        	else if(DictCodeList.交易方向_份额强增.getValue().equals(entity.getDealType())||
        				DictCodeList.交易方向_份额强减.getValue().equals(entity.getDealType())) {
        		List<FinNetProductDurationDto> list = entity.getAddReduceCycleFinNetProductDurationDtoList();
        		page.setRoot(list);
        	}
        	else if(DictCodeList.交易方向_产品到期.getValue().equals(entity.getDealType())||
    					DictCodeList.交易方向_提前终止.getValue().equals(entity.getDealType())) {
	    		List<FinNetProductDurationDto> list = entity.getExpireCycleFinNetProductDurationDtoList();
	    		page.setRoot(list);
        	}
        	else {
        		return page;
        	}
        }else if(StringUtil.isNotEmpty(this.getParam("tradeId"))){
        	List<FinNetProductDurationDto> list = finNetProductDurationService.getPeriodInfoListInTrade(map);
        	page.setRoot(list);
        }else {
        	List<FinNetProductDurationDto> list = finNetProductDurationService.getPeriodInfoList(map);
        	page.setRoot(list);
        }
        return page;
    }
    /**
     * 查询单位净值
     * @return 
     * @see
     */
    @ResponseBody
    @RequestMapping("getTrdValueByCdate")
    public BigDecimal getTrdValueByCdate(@RequestBody FinNetProductDurationDto dto) {
    	Map<String, Object> map = RequestUtil.getPageParam();
    	map.put("finprodId", dto.getFinprodId());
    	map.put("cDate", dto.getRedNetValueDate());
        return finNetProductDurationService.getTrdValueByCdate(map);
    }
    /**
     * 查询产品 查询日期最近的净值信息-货币型产品分红专用
     * @return dto 货币型产品当日份额及分红相关信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getNetValueInfoForHB")
    public FinNetProductDurationDto getNetValueInfoForHB(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return finNetProductDurationService.getNetValueInfoForHB(map);
    }
}
