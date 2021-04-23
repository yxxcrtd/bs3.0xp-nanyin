/*
 * 文件名：EtrTradePlace001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年7月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etragrereptrade.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.component.base.group.BasePtlSecPositionInfoService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
//import com.joyintech.tams.invest.entrust.common.aop.EntrustRdsCommonAop;
import com.joyintech.tams.invest.entrust.etragrereptrade.dto.EtrAgreRepTradeDto;
import com.joyintech.tams.invest.entrust.etragrereptrade.service.EtrAgreRepTrade001Service;
import com.joyintech.tams.invest.entrust.etrexchangetrade.service.EtrExchangeTrade001Service;
import com.joyintech.tams.systemtools.util.SysDatadictUtil;
import com.joyintech.tams.valuation.common.batch.finproduct.service.FinProductCommonBatchService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 交易所指令交易（债券股票回购基金场内指令交易）
 * @author chendijun
 * @version 1.0 2020年7月1日
 * @see EtrAgreRepTrade001Controller
 * @since 1.0
 */
@Controller
@PageId("EtrAgreRepTrade001")
@RequestMapping("/invest/entrust/etragrereptrade/EtrAgreRepTrade001")
public class EtrAgreRepTrade001Controller extends BaseController{

	@Autowired
	 private IPlatformDate iPlatformDate;
	
	/**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;

    
    @Autowired
    private EtrExchangeTrade001Service etrExchangeTrade001Service;
    
    @Autowired
    private EtrAgreRepTrade001Service etrAgreRepTrade001Service;
    
    
    @Autowired
    private FinProductCommonBatchService finProductCommonBatchService;

    @Autowired
    private BasePtlSecPositionInfoService basePtlSecPositionInfoService;
    
	public static final String ENTRUST_RECORD_NEW = "new";

	public static final String ENTRUST_RECORD_MODIFY = "modify";
    
    @Override
    public String init(Model model) {
    	this.setSelectOptionToModel(model, DictCode.交易方向, DictCode.交易场所);
    	

        Date platformDate = DateUtil.platDateFromCache();
        model.addAttribute("iPlatformDate", DateUtil.format(platformDate));
        
        //流程id
        String workflowId = "null".equals(getParam(GlobalConstant.WORKFLOW_ID))?null:getParam(GlobalConstant.WORKFLOW_ID);
        model.addAttribute("workflowId", workflowId);
        String entrustId = getParam("entrustId");
        
        //委托代码
      //  String entrustId = "null".equals(getParam("entrustId"))?null:getParam("entrustId");
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_委托监控.getValue());
        model.addAttribute("BACK_LEND_DIRECTION_03", DictCodeList.回购拆借方向_正回购.getValue());
        model.addAttribute("BACK_LEND_DIRECTION_04", DictCodeList.回购拆借方向_逆回购.getValue());
        
        //母页面的pageModel
        String parentPageModel = getParam(GlobalConstant.DETAIL_PAGE_MODEL_KEY);
        model.addAttribute("parentPageModel", parentPageModel);
        
        EtrAgreRepTradeDto entity = new EtrAgreRepTradeDto();
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
        	 //流程代办
            entity = etrAgreRepTrade001Service.getBussinessEntityByWorkflowId(
                workflowId);
            model.addAttribute(GlobalConstant.ENTITY, entity);
            model.addAttribute("workflowId", workflowId);
        }
        else if(StringUtil.isNotEmpty(entrustId)){
            entity = etrAgreRepTrade001Service.getEntrustInfoByEntrustId(RequestUtil.getPageParam());
            model.addAttribute(GlobalConstant.ENTITY, entity);
            model.addAttribute("entityStr", JsonUtils.object2Json(entity));
        }
        model.addAttribute("ENTRUST_RECORD_NEW", ENTRUST_RECORD_NEW);
        model.addAttribute("ENTRUST_RECORD_MODIFY", ENTRUST_RECORD_MODIFY);
       
        //model.addAttribute(GlobalConstant.ENTITY, entity);
		model.addAttribute("TRADE_MARKET_04", DictCodeList.交易流通场所_深圳证券交易所.getValue());
        return super.init(model);
    }
    
    /**
     * 对手方交易员联想文本框初始化。
     * 
     * @return 联想文本框
     */
    @ResponseBody
    @RequestMapping("/selectCounterTrader")
    public List<SuggestDto> selectCounterTrader() {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return etrAgreRepTrade001Service.selectCounterTrader(pageParam);
    }

    /**
     * 本方交易员联想文本框初始化。
     * 
     * @return 联想文本框
     */
    @ResponseBody
    @RequestMapping("/selectPs")
    public List<SelectOptionDto> selectPs() {
    	String tradeMarket = getParam("tradeMarket");
    	String psList = getParam("psList");
    	
    	SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.交易方向, null);
    	if(StringUtil.isNotEmpty(psList)){
    		String[] str = psList.split(",");
    		
    		List<SelectOptionDto> PS = selectDto.getOptions().stream().filter(option -> {
    			for(String s :str ){
    				if(StringUtil.equals(option.getId(),s)){
    					return true;
    				}
    			}
                return false;
            }).collect(Collectors.toList());
    		return PS;
    	}
    	else if(StringUtil.isNotEmpty(tradeMarket) && tradeMarket.equals("04")){
    		List<SelectOptionDto> PS_SZ = selectDto.getOptions().stream().filter(option -> {
                return  StringUtil.equals(option.getId(), DictCodeListExt3.交易方向_正回购.getValue())
                        || StringUtil.equals(option.getId(), DictCodeListExt3.交易方向_逆回购.getValue())
                        || StringUtil.equals(option.getId(), DictCodeListExt3.交易方向_提前购回.getValue())
                        || StringUtil.equals(option.getId(), DictCodeListExt3.交易方向_到期购回.getValue())
                        || StringUtil.equals(option.getId(), DictCodeListExt3.交易方向_到期续做.getValue());
            }).collect(Collectors.toList());
    		return PS_SZ;
    	}
        return selectDto.getOptions();
    }
    
    /**
     * 本方交易员联想文本框初始化。
     * 
     * @return 联想文本框
     */
    @ResponseBody
    @RequestMapping("/selectTrader")
    public List<SuggestDto> selectTrader() {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return etrAgreRepTrade001Service.selectTrader(pageParam);
    }
    /**
     * 本方交易员联想文本框初始化。
     * 
     * @return 联想文本框
     */
    @ResponseBody
    @RequestMapping("/addDate")
    public Map<String,Object> addDate() {
    	Map<String, Object> pageParam = RequestUtil.getPageParam();
    	return etrAgreRepTrade001Service.addDate(pageParam);
         
    }
    /**
     * 本方交易员联想文本框初始化。
     * 
     * @return 联想文本框
     */
    @ResponseBody
    @RequestMapping("/getFaceValue")
    public String getFaceValue() {
    	String repSecId = getParam("repSecId");
    	String iPlatformDate = getParam("iPlatformDate");
    	BigDecimal price =  finProductCommonBatchService.getFaceValue(repSecId,DateUtil.parse(iPlatformDate));
        if(price!=null){
        	return price.toString();
        }
        return "";
    }
    /**
     *查询原成交信息
     * 
     * @return 联想文本框
     */
    @ResponseBody
    @RequestMapping("/getEntrustInfoByTradeId")
    public EtrAgreRepTradeDto getEntrustInfoByTradeId() {
        //成交编号
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return etrAgreRepTrade001Service.getEntrustInfoByTradeId(pageParam);
    }
    /**
     *查询原成交信息
     * 
     * @return 联想文本框
     */
    @ResponseBody
    @RequestMapping("/getEntrustInfoByEntrustId")
    public EtrAgreRepTradeDto getEntrustInfoByEntrustId() {
    	//成交编号
    	Map<String, Object> pageParam = RequestUtil.getPageParam();
    	return etrAgreRepTrade001Service.getEntrustInfoByEntrustId(pageParam);
    }
    
    /**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Validated() EtrAgreRepTradeDto dto,
			BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }

		dto.setInvManagerId(LoginUserUtil.getUserName());
		List<ValidMessage> errorList = new ArrayList<>();
		if(DictCodeListExt3.交易方向_提前购回.getValue().equals(dto.getPs())){
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_协议回购提前购回指令.getValue());
		}else if(DictCodeListExt3.交易方向_到期购回.getValue().equals(dto.getPs())){
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_协议回购到期购回指令.getValue());
		}else if(DictCodeListExt3.交易方向_中途换券.getValue().equals(dto.getPs())){
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_协议回购中途换券指令.getValue());
		}else if(DictCodeListExt3.交易方向_解除质押.getValue().equals(dto.getPs())){
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_协议回购解除质押指令.getValue());
		}else if(DictCodeListExt3.交易方向_到期续做.getValue().equals(dto.getPs())){
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_协议回购到期续做指令.getValue());
		}else{
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_协议回购首期指令.getValue());
		}
		AjaxResult ajax  = etrAgreRepTrade001Service.checkSave(dto);
		if (ajax != null && !StringUtil.isEmptyList(ajax.getErrorList())) {
			return ajax;
		}
        //正回购、质押券出库
        if (DictCodeListExt3.交易方向_正回购.getValue().equals(dto.getPs())) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("portfolioId", dto.getPortfolioId());
            param.put("finprodId", dto.getFinprodId());
            if (null != dto.getSecAcctId()) {
                param.put("secAcctId", dto.getSecAcctId());
            }
            param.put("ccy", DictCodeList.币种_人民币_CNY.getValue());
            dto.setAccAvailablePos(etrExchangeTrade001Service.selectAvailableNum(param));
            if (StringUtil.isNotEmpty(dto.getAccAvailablePos()) && StringUtil.isNotEmpty(dto.getRepSecNum())) {
                if (dto.getAccAvailablePos().compareTo(dto.getRepSecNum()) == -1) {
                    if(CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_POS_SWITCH).equals(DictCodeListExt1.是或否_否.getValue())) {
                        errorList.add(new ValidMessage("repSecNum", "质押数量不能大于可用持仓！"));
                    }
                    else{
                        AjaxResult ajaxResult = new AjaxResult();
                        ajaxResult.setData("质押数量大于可用持仓,是否继续？");
                        ajaxResult.setMessageType(MessageType.确认消息);
                        return ajaxResult;
                    }
                }
            }
        }
        // 中途换券
        if (DictCodeListExt3.交易方向_中途换券.getValue().equals(dto.getPs())) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("portfolioId", dto.getPortfolioId());
            param.put("finprodId", dto.getNewfinprodId());
//            if (null != dto.getSecAcctId()) {
////                param.put("secAcctId", dto.getSecAcctId());
////            }
            param.put("secAcctId", "999999");
            param.put("ccy", DictCodeList.币种_人民币_CNY.getValue());
            dto.setAccAvailablePos(etrExchangeTrade001Service.selectAvailableNum(param));
            if (StringUtil.isNotEmpty(dto.getAccAvailablePos()) && StringUtil.isNotEmpty(dto.getNewRepSecNum())) {
                if (dto.getAccAvailablePos().compareTo(dto.getNewRepSecNum()) == -1) {
                    if(CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_POS_SWITCH).equals(DictCodeListExt1.是或否_否.getValue())) {
                        errorList.add(new ValidMessage("newRepSecNum", "质押数量不能大于可用持仓！"));
                    }
                    else{
                        AjaxResult ajaxResult = new AjaxResult();
                        ajaxResult.setData("质押数量大于可用持仓,是否继续？");
                        ajaxResult.setMessageType(MessageType.确认消息);
                        return ajaxResult;
                    }
                }
            }
        }
        // 逆回购
        if (DictCodeListExt3.交易方向_逆回购.getValue().equals(dto.getPs())) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("portfolioId",dto.getPortfolioId());
            param.put("acctNo",dto.getAcctNo());
            param.put("sysDate",dto.getVdate());
//            BigDecimal accAvailableCash = etrExchangeTrade001Service.selectAccAvailableCash(param);
            Map<String,Object> cashAmountMap =  etrAgreRepTrade001Service.getCashAmount(param);
            BigDecimal accAvailableCash = (BigDecimal)cashAmountMap.get("cashUse");
            if (StringUtil.isNotEmpty(accAvailableCash) && StringUtil.isNotEmpty(dto.getTradeAmt())) {
                if (accAvailableCash.compareTo(dto.getTradeAmt()) == -1) {
                    if(CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH).equals(DictCodeListExt1.是或否_否.getValue())) {
                        errorList.add(new ValidMessage("tradeAmt", this.getMessage("ETR_ERROR_0019")));
                    }
                    else{
                        AjaxResult ajaxResult = new AjaxResult();
                        ajaxResult.setData("回购金额大于指令可用现金,是否继续？");
                        ajaxResult.setMessageType(MessageType.确认消息);
                        return ajaxResult;
                    }
                }
            }
        }
        AjaxResult errorRs = new AjaxResult();
        // 流程中数据校验
//        if(DictCodeListExt3.交易方向_提前购回.getValue().equals(dto.getPs())
//            || DictCodeListExt3.交易方向_到期购回.getValue().equals(dto.getPs())
//            || DictCodeListExt3.交易方向_到期续作.getValue().equals(dto.getPs())){
//            Map<String, Object> param = new HashMap<String, Object>();
//            param.put("portfolioId", dto.getPortfolioId());
//            param.put("finprodId", dto.getTradeId());
//            if (null != dto.getSecAcctId()) {
//                param.put("secAcctId", dto.getSecAcctId());
//            }
//            param.put("ccy", DictCodeList.币种_人民币_CNY.getValue());
//            BigDecimal availableNum = etrExchangeTrade001Service.selectAvailableNum(param);
//            if(availableNum.compareTo(new BigDecimal(0)) < 1){
//                availableNum = etrAgreRepTrade001Service.queryOtherSecPosition(param);
//            }
//            if(availableNum.compareTo(new BigDecimal(0)) < 1){
//                errorList.add(new ValidMessage("该回购已经做过到期相关处理，不能重复处理。"));
//            }
//        }
		if (!StringUtil.isEmptyList(errorList)) {
			errorRs.setErrorList(errorList);
			return errorRs;
		}
		return commonCheck(result, dto.getOperateType());
	}
	/**
	 * 保存/提交
	 * 
	 * @param dto
	 *            dto
	 * @param br
	 *            br
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping("save")
	public AjaxResult save(@RequestBody EtrAgreRepTradeDto dto, BindingResult br) throws Exception {
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		//业务类型
		String businessType = String.valueOf("");
		if(DictCodeListExt3.交易方向_提前购回.getValue().equals(dto.getPs())){
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_协议回购提前购回指令.getValue());
			dto.setBusinessTypeName(DictCodeListExt3.上农商_指令业务类型_协议回购提前购回指令.getValue());
			businessType = DictCodeListExt3.共通业务类型_协议回购提前购回指令.getValue();
		}else if(DictCodeListExt3.交易方向_到期购回.getValue().equals(dto.getPs())){
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_协议回购到期购回指令.getValue());
			dto.setBusinessTypeName(DictCodeListExt3.上农商_指令业务类型_协议回购到期购回指令.getValue());
			businessType = DictCodeListExt3.共通业务类型_协议回购到期购回指令.getValue();
		}else if(DictCodeListExt3.交易方向_中途换券.getValue().equals(dto.getPs())){
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_协议回购中途换券指令.getValue());
			dto.setBusinessTypeName(DictCodeListExt3.上农商_指令业务类型_协议回购中途换券指令.getValue());
			businessType = DictCodeListExt3.共通业务类型_协议回购中途换券指令.getValue();
		}else if(DictCodeListExt3.交易方向_解除质押.getValue().equals(dto.getPs())){
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_协议回购解除质押指令.getValue());
			dto.setBusinessTypeName(DictCodeListExt3.上农商_指令业务类型_协议回购解除质押指令.getValue());
			businessType = DictCodeListExt3.共通业务类型_协议回购解除质押指令.getValue();
		}else if(DictCodeListExt3.交易方向_到期续做.getValue().equals(dto.getPs())){
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_协议回购到期续做指令.getValue());
			dto.setBusinessTypeName(DictCodeListExt3.上农商_指令业务类型_协议回购到期续做指令.getValue());
			businessType = DictCodeListExt3.共通业务类型_协议回购到期续做指令.getValue();
		}else{
			dto.setEntrustBussType(DictCodeListExt3.上农商_指令业务类型_协议回购首期指令.getValue());
			dto.setBusinessTypeName(DictCodeListExt3.上农商_指令业务类型_协议回购首期指令.getValue());
			businessType = DictCodeListExt3.共通业务类型_协议回购首期指令.getValue();
		}
		//add end
		if (!StringUtil.isNotEmpty(dto.getEntrustId())) {
			BussinessCodeParameter param = new BussinessCodeParameter();
			if(DictCodeListExt3.交易方向_提前购回.getValue().equals(dto.getPs())){
				param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_协议回购提前购回指令));
			}else if(DictCodeListExt3.交易方向_到期购回.getValue().equals(dto.getPs())){
				param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_协议回购到期购回指令));
			}else if(DictCodeListExt3.交易方向_中途换券.getValue().equals(dto.getPs())){
				param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_协议回购中途换券指令));
			}else if(DictCodeListExt3.交易方向_解除质押.getValue().equals(dto.getPs())){
				param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_协议回购解除质押指令));

			}else if(DictCodeListExt3.交易方向_到期续做.getValue().equals(dto.getPs())){
                param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_协议回购到期续做指令));
			}else{
				param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_协议回购首期指令));
			}
			//add end
			String code = bussGetBussinessIdService != null ? bussGetBussinessIdService.buildBussinessCode(param) : IDGenerator.generate();
			dto.setEntrustId(code);
		}

		if (StringUtil.equals(dto.getRepPs(),DictCodeList.回购拆借方向_正回购.getValue())){
		    dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue());
        }else {
            dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
        }

		dto.setFromEtrExchange(DictCodeList.是否_是.getValue());
		dto.setBusiPs(dto.getRepPs());
		dto.setAssetType2(DictCodeList.回购类型_质押式协议回购.getValue());
        // ----------add by zhangrui1 20200703 预占功能 start----------
//        if (StringUtil.equals(BussinessConstant.SUBMIT, dto.getOperateType())
//        &&(DictCodeListExt3.交易方向_到期续做.getValue().equals(dto.getPs())
//            || DictCodeListExt3.交易方向_逆回购.getValue().equals(dto.getPs())
//            || DictCodeListExt3.交易方向_正回购.getValue().equals(dto.getPs()) )) {
//            AjaxResult ar = EntrustRdsCommonAop.doPreOccupy(dto,
//                BussinessDictCodeListRds.接口系统ID_预占接口.getValue(),DictCodeList.平安资产类型_质押式回购.getValue());
//            if (StringUtil.isNotEmpty(ar)) {
//                return ar;
//            }
//        }
        // ----------add by zhangrui1 20200703 预占功能 end----------
		/*
		 * if 画面.金融产品类型（投管）.股票 and 画面.交易平台 = 数据字典.交易平台.竞价交易平台 指令业务类型 =
		 * 数据字典.共通业务类型.股票竞价指令
		 * 
		 * if 画面.金融产品类型（投管）.基金 and 画面.交易平台 = 数据字典.交易平台.竞价交易平台 指令业务类型 =
		 * 数据字典.共通业务类型.基金竞价指令
		 * 
		 * if 画面.委托方向 = （数据字典.交易方向.正回购 or 逆回购） and 画面.交易平台 = 数据字典.交易平台.大宗交易平台
		 * 指令业务类型 = 数据字典.共通业务类型.大宗债券质押式回购指令
		 * 
		 * if 画面.金融产品类型（投管）.股票 and 画面.交易平台 = 数据字典.交易平台.大宗交易平台 指令业务类型 =
		 * 数据字典.共通业务类型.大宗股票指令
		 * 
		 * if 画面.金融产品类型（投管）.基金 and 画面.交易平台 = 数据字典.交易平台.大宗交易平台 指令业务类型 =
		 * 数据字典.共通业务类型.大宗基金指令
		 * 
		 * if 画面.金融产品类型（投管）.债券and 画面.交易平台 = 数据字典.交易平台.大宗交易平台 指令业务类型 =
		 * 数据字典.共通业务类型.大宗债券指令
		 */
		//dto.setBusinessTypeName(SysDataUtil.getNameFromDataDict(DictCode.共通业务类型, businessType));
		ProcessParameter<EtrAgreRepTradeDto> processParameter = new ProcessParameter<EtrAgreRepTradeDto>();
		WorkFlowParameter<EtrAgreRepTradeDto> params = etrAgreRepTrade001Service.getWorkFlowParameter(dto);
		params.setPageId("EtrAgreRepTrade001");
		params.setElementId(dto.getElementId());
		params.setBusinessType(businessType);
		params.setBusinessCode(dto.getEntrustId());
		params.setComment(entity -> getMessage("EtrExchangeTrade003.entrustId") + "(" + entity.getEntrustId() + ")-"
				+ "-" + "业务类型"+ "(" + SysDatadictUtil.getNameFromDataDict(DictCode.上农商_指令业务类型,
		entity.getBusinessTypeName()) + ")-"
				+ "平台日" + "(" + DateUtil.format(iPlatformDate.getSysDate()) + ")");
		processParameter.setWorkFlowParameter(params);
		
		dto.setInvManagerId(LoginUserUtil.getUserName());// 投资经理
	    dto.setCreateUser(LoginUserUtil.getUserName());// 用户名
	    dto.setCreateDept(LoginUserUtil.getDeptCode());// 用户部门
	    dto.setCreateTime(new Date());// 创建时间
		String msg = etrAgreRepTrade001Service.start(processParameter, dto);
		return new AjaxResult(msg);
	}

    /**
     * 获取可用可取资金
     */
    @ResponseBody
    @RequestMapping("/getCashAmount")
    public Map<String,Object> getCashAmount() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return etrAgreRepTrade001Service.getCashAmount(param);
    }

}
	
