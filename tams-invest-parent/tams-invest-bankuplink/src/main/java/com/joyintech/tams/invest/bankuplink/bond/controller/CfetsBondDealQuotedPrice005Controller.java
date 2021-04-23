/*
 * 文件名：CfetsBondDealQuotedPrice003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzhuangzhuang
 * 修改时间：2020年5月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.bankuplink.bond.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.bankuplink.bond.dto.CfetsBondDealRequestQuoteDto;
import com.joyintech.tams.invest.bankuplink.bond.dto.CounterInfoDto;
import com.joyintech.tams.invest.bankuplink.bond.service.CfetsBondDealQuotedPrice003Service;
import com.joyintech.tams.invest.bankuplink.common.service.CfetsQuotedCommonService;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 现券买卖-请求报价
 * 
 * @author wangzhuangzhuang
 * @version 2020年5月18日
 * @see CfetsBondDealQuotedPrice005Controller
 * @since 1.0
 */
@Controller
@PageId("CfetsBondDealQuotedPrice005")
@RequestMapping("/invest/bankuplink/bond/CfetsBondDealQuotedPrice005")
public class CfetsBondDealQuotedPrice005Controller extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsBondDealQuotedPrice003Service cfetsBondDealQuotedPrice003Service;
    
    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsQuotedCommonService cfetsQuotedCommonService;
    
    /**
     * {@linkplain}
     */
    @Autowired
	 private IPlatformDate iPlatformDate;
    
    /**
     * {@linkplain}
     */
    @Autowired
    private BaseGetBussinessId bussGetBussinessIdService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, BussinessDictCode.交易方向, BussinessDictCode.清算速度,
            BussinessDictCode.结算方式, BussinessDictCode.清算类型, BussinessDictCode.币种);

        model.addAttribute("test123", "01");
        model.addAttribute("ACCOUNT_SPEED_1", BussinessDictCodeList.清算速度_T加1.getValue());
        model.addAttribute("ACCOUNT_SPEED_0", BussinessDictCodeList.清算速度_T加0.getValue());
        model.addAttribute("CNY", BussinessDictCodeList.币种_人民币.getValue());
        model.addAttribute("SETT_MODE_03", BussinessDictCodeList.结算方式_券款对付.getValue());
        model.addAttribute("CLEAR_TYPE_02", BussinessDictCodeList.清算类型_全额清算.getValue());
        model.addAttribute("isRevert", BussinessDictCodeList.是否_是.getValue());
        model.addAttribute("effTime", CacheUtil.getSysParam("ibuVar_quoteEffTime"));
        // 本方交易员是否可改
        model.addAttribute("ouTraderIdCanEditFlag", CacheUtil.getSysParam("ibuFlag_ouTraderIdCanEdit"));

        CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto = new CfetsBondDealRequestQuoteDto();
        if (StringUtil.equals(this.getParam("workflowStatus"), BussinessDictCodeList.交易流程状态_流程中.getValue())
            || (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotBlank(getParam(GlobalConstant.WORKFLOW_ID)))) {
            model.addAttribute("busWorkflowId",getParam(GlobalConstant.WORKFLOW_ID));
            cfetsBondDealRequestQuoteDto = cfetsBondDealQuotedPrice003Service.getBussinessEntityByWorkflowId(getParam(GlobalConstant.WORKFLOW_ID));
//            cfetsBondDealRequestQuoteDto.setAvaNum(cfetsBondDealRequestQuoteDto.getAvaNum().multiply(new BigDecimal(10000)));
			if (null == cfetsBondDealRequestQuoteDto.getCounterInfoList()
					&& cfetsBondDealRequestQuoteDto.getCounterIdList().size() > 0) {
				List<CounterInfoDto> counterInfoList = new ArrayList<CounterInfoDto>();
				for (String counterId : cfetsBondDealRequestQuoteDto.getCounterIdList()) {
					CounterInfoDto counterInfoDto = new CounterInfoDto();
					counterInfoDto.setCounterId(counterId);
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("initValue", counterId);
					List<SelectOptionDto> lst = cfetsQuotedCommonService.queryMySideList(param);
					if (null != lst && lst.size() > 0) {
						counterInfoDto.setCounterValue(lst.get(0).getText());
					}
					counterInfoList.add(counterInfoDto);
					cfetsBondDealRequestQuoteDto.setCounterInfoList(counterInfoList);
				}
			} else if (null != cfetsBondDealRequestQuoteDto.getCounterInfoList()
					&& cfetsBondDealRequestQuoteDto.getCounterInfoList().size() > 0) {
				for (CounterInfoDto counterInfoDto : cfetsBondDealRequestQuoteDto.getCounterInfoList()) {
					counterInfoDto.setTradeStatus(BussinessDictCodeList.银行间上行报价状态_已回复.getValue());
				}
				model.addAttribute("isRevert", BussinessDictCodeList.是否_是.getValue());
				model.addAttribute("YES_OR_NO_Y", BussinessDictCodeList.是否_是.getValue());
				model.addAttribute("tradeStatus12", BussinessDictCodeList.银行间上行报价状态_已回复.getCodeName());
			}
			if (StringUtils.isNotEmpty(cfetsBondDealRequestQuoteDto.getFinprodId()) && StringUtils.isEmpty(cfetsBondDealRequestQuoteDto.getFinprodMarketId())) {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("initValue",cfetsBondDealRequestQuoteDto.getFinprodId());
				List<SelectOptionDto> lst = cfetsQuotedCommonService.queryFinprodIdList(param);
				if(null != lst && lst.size() > 0){
					JSONObject json = (JSONObject) JSONObject.toJSON(lst.get(0).getObject());
					cfetsBondDealRequestQuoteDto.setFinprodMarketId(json.get("finprodMarketId") != null ? json.get("finprodMarketId").toString() :"");
				}
			}
            /*if (cfetsBondDealRequestQuoteDto.getQuoteId() != null) {
                cfetsBondDealRequestQuoteDto = cfetsBondDealQuotedPrice003Service.getBondRequestQuote(
                    cfetsBondDealRequestQuoteDto.getQuoteId());
            }*/
        }
        else if (StringUtil.isNotEmpty(this.getParam("entrustIdList"))) {
        	List <String> entrustIdList = StringUtil.splitToList(GlobalConstant.COMMA, (String)this.getParam("entrustIdList"));
            List<CfetsBondDealRequestQuoteDto> cfetsBondDealRequestQuoteDtoList = new ArrayList<>();
            cfetsBondDealRequestQuoteDto = cfetsBondDealQuotedPrice003Service.queryEntrustInfoByEntrustId(entrustIdList.get(0));
            BigDecimal avaNum = BigDecimal.ZERO;
            BigDecimal price = cfetsBondDealRequestQuoteDto.getPrice();
            for(String entrustId:entrustIdList){
            	CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuote = cfetsBondDealQuotedPrice003Service.queryEntrustInfoByEntrustId(entrustId);
//            	cfetsBondDealDialogueQuote.
            	avaNum =ArithmeticUtil.doAdd(avaNum,cfetsBondDealRequestQuote.getAvaNum());
            	cfetsBondDealRequestQuote.setEntrustAvaNum(cfetsBondDealRequestQuote.getAvaNum());
            	cfetsBondDealRequestQuote.setAvaNum(null);
            	if(StringUtil.equals(cfetsBondDealRequestQuoteDto.getPs(), BussinessDictCodeList.交易方向_买入.getValue())){
            		if(price.compareTo(cfetsBondDealRequestQuote.getPrice())>0){
            			price=cfetsBondDealRequestQuote.getPrice();
            		}
            	}else{
            		if(price.compareTo(cfetsBondDealRequestQuote.getPrice())<0){
            			price=cfetsBondDealRequestQuote.getPrice();
            		}
            	}
            	cfetsBondDealRequestQuoteDtoList.add(cfetsBondDealRequestQuote);
            }
            cfetsBondDealRequestQuoteDto.setCfetsBondDealRequestQuoteDtoList(cfetsBondDealRequestQuoteDtoList);
            cfetsBondDealRequestQuoteDto.setAvaNum(avaNum);
            cfetsBondDealRequestQuoteDto.setPrice(price);
        }
        else if (StringUtil.isNotEmpty(this.getParam("quoteId"))) {
            cfetsBondDealRequestQuoteDto = cfetsBondDealQuotedPrice003Service.getBondRequestQuote(this.getParam("quoteId"),this.getParam("statusValid"));
            
            cfetsBondDealRequestQuoteDto.setAvaNum(ArithmeticUtil.divide(cfetsBondDealRequestQuoteDto.getAvaNum(),new BigDecimal(10000),2));
            //组合分配列表
            List <CfetsBondDealRequestQuoteDto> cfetsBondDealRequestQuoteDtoList =cfetsBondDealQuotedPrice003Service.getCfetsBondDealRequestQuoteDtoList(this.getParam("quoteId"));
            for(CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuote:cfetsBondDealRequestQuoteDtoList){
            	cfetsBondDealRequestQuote.setMatureYield(cfetsBondDealRequestQuoteDto.getMatureYield());
            	cfetsBondDealRequestQuote.setExerYield(cfetsBondDealRequestQuoteDto.getExerYield());
            }
            cfetsBondDealRequestQuoteDto.setCfetsBondDealRequestQuoteDtoList(cfetsBondDealRequestQuoteDtoList);
            
            
            // 画面打开模式(区分做市报价还是请求报价)
            if (StringUtil.isNotEmpty(this.getParam("quotaPageModel"))) {
                cfetsBondDealRequestQuoteDto.setQuotaPageModel(this.getParam("quotaPageModel"));
            }
            // 区分校验类型
            if (StringUtil.isNotEmpty(this.getParam("checkType"))) {
                cfetsBondDealRequestQuoteDto.setCheckType(this.getParam("checkType"));
            }
            // 复制模式打开时需要把报价编号置空
            if (StringUtil.equals(this.getParam("openModel"), "copy")) {
                cfetsBondDealRequestQuoteDto.setQuoteId(null);
                // 复制的场合券面总额默认为剩余额度
                if (cfetsBondDealRequestQuoteDto.getEntrustId() != null) {
                    CfetsBondDealRequestQuoteDto entrustInfo = cfetsBondDealQuotedPrice003Service.queryEntrustInfoByEntrustId(
                        cfetsBondDealRequestQuoteDto.getEntrustId());
                    if (entrustInfo != null && entrustInfo.getEntrustNum() != null) {
                        cfetsBondDealRequestQuoteDto.setAvaNum(entrustInfo.getAvaNum());
                    }
                }
                else if (!StringUtil.isEmptyList(cfetsBondDealRequestQuoteDto.getCfetsBondDealRequestQuoteDtoList())) {
                    // 多组合场景
                    // 券面总额：默认未完成各单笔指令剩余额度总和
                    // 组合信息：根据各指令带出，券面总额默认为各指令剩余额度。已完成指令不再显示在列表中。
                    BigDecimal tempSumAvaAmt = BigDecimal.ZERO;
                    List<CfetsBondDealRequestQuoteDto> tempEntrustList = new ArrayList<>();
                    for (CfetsBondDealRequestQuoteDto tempDto : cfetsBondDealRequestQuoteDto.getCfetsBondDealRequestQuoteDtoList()) {
                        if (BigDecimal.ZERO.compareTo(tempDto.getEntrustAvaNum()) < 0) {
                            tempDto.setAvaNum(tempDto.getEntrustAvaNum());
                            tempSumAvaAmt = com.joyintech.framework.util.ArithmeticUtil.doAdd(tempSumAvaAmt, tempDto.getEntrustAvaNum());
                            tempEntrustList.add(tempDto);
                        }
                    }
                    cfetsBondDealRequestQuoteDto.setAvaNum(tempSumAvaAmt);
                    cfetsBondDealRequestQuoteDto.setCfetsBondDealRequestQuoteDtoList(tempEntrustList);
                }
            }
            //add by yzg 多机构请求报价分开展示 20210126 start
            /*if(StringUtil.equals(this.getParam("openMode"), "5") && null != cfetsBondDealRequestQuoteDto.getCounterInfoList()
					&& cfetsBondDealRequestQuoteDto.getCounterInfoList().size() > 1) {
            	List<String> arr = StringUtil.splitToList("",this.getParam("cpPartyId").toString());
            	List<CounterInfoDto> list = new ArrayList<>();
            	for(CounterInfoDto counterInfoDto : cfetsBondDealRequestQuoteDto.getCounterInfoList()) {
            		if(arr.contains(counterInfoDto.getCounterId())) {
            			list.add(counterInfoDto);
            		}
            	}
            	cfetsBondDealRequestQuoteDto.setCounterInfoList(list);
            }*/
            //add by yzg 多机构请求报价分开展示 20210126 end
        }
        // 无委托模式
        else {
        	cfetsBondDealRequestQuoteDto.setMySideTrader(cfetsQuotedCommonService.queryLoginTraderId(RequestUtil.getPageParam()));
        }
        // 结算方式
        if (!StringUtil.isNotEmpty(cfetsBondDealRequestQuoteDto.getSettleMethod())) {
        	cfetsBondDealRequestQuoteDto.setSettleMethod(
                BussinessDictCodeList.结算方式_券款对付.getValue());
        }
        // 清算类型
        if (!StringUtil.isNotEmpty(cfetsBondDealRequestQuoteDto.getSettleType())) {
        	cfetsBondDealRequestQuoteDto.setSettleType(
                BussinessDictCodeList.清算类型_全额清算.getValue());
        }
        // 结算币种
        if (!StringUtil.isNotEmpty(cfetsBondDealRequestQuoteDto.getSettleCcy())) {
        	cfetsBondDealRequestQuoteDto.setSettleCcy(BussinessDictCodeList.币种_人民币.getValue());
        }
        // 画面模式
        if (StringUtil.isNotEmpty(this.getParam("quotaPageModel"))) {
        	cfetsBondDealRequestQuoteDto.setQuotaPageModel(this.getParam("quotaPageModel"));
        }
        
        model.addAttribute(GlobalConstant.ENTITY, cfetsBondDealRequestQuoteDto);
        
        // 判断是否是回复按钮跳转的标志
		String isRevert = this.getParam("isRevert");
		//流程中判断
		if(StringUtil.isEmpty(isRevert)&&null!=cfetsBondDealRequestQuoteDto&&StringUtil.equals(cfetsBondDealRequestQuoteDto.getQuotaPageModel(),"response")){
            isRevert = BussinessDictCodeList.是否_是.getValue();
        }
		if (StringUtil.isNotEmpty(isRevert)) {
			model.addAttribute("isRevert", isRevert);
			model.addAttribute("YES_OR_NO_Y", BussinessDictCodeList.是否_是.getValue());
			model.addAttribute("tradeStatus12", BussinessDictCodeList.银行间上行报价状态_已回复.getCodeName());
		} else if (StringUtils.equals(cfetsBondDealRequestQuoteDto.getTradeStatus(),
				BussinessDictCodeList.银行间上行报价状态_成交中.getValue()) ||
				StringUtils.equals(cfetsBondDealRequestQuoteDto.getTradeStatus(),
						BussinessDictCodeList.银行间上行报价状态_已回复.getValue())) {
			model.addAttribute("isRevert", BussinessDictCodeList.是否_是.getValue());
			model.addAttribute("YES_OR_NO_Y", BussinessDictCodeList.是否_是.getValue());
			model.addAttribute("tradeStatus12", BussinessDictCodeList.银行间上行报价状态_已回复.getCodeName());
		}
        // 判断
        String quoteRoleType = this.getParam("quoteRoleType");
        String sendStatus = this.getParam("sendStatus");
        String tradeStatus = this.getParam("tradeStatus");
        if (StringUtil.isNotEmpty(quoteRoleType)) {
            model.addAttribute("initQuoteRoleType", quoteRoleType);
        }
        if (StringUtil.isNotEmpty(sendStatus)) {
            model.addAttribute("initSendStatus", sendStatus);
        }
        if (StringUtil.isNotEmpty(tradeStatus)) {
            model.addAttribute("initTradeStatus", tradeStatus);
        }
        
        // 获取画面选中债券是否为含权债
        if (cfetsBondDealRequestQuoteDto != null
            && StringUtil.isNotBlank(cfetsBondDealRequestQuoteDto.getFinprodId())) {
            model.addAttribute("assetIsRight",
                cfetsBondDealQuotedPrice003Service.selectAssetIsRight(
                    cfetsBondDealRequestQuoteDto.getFinprodId()));
        }
        
        model.addAttribute("cfetsBondDealRequestQuoteDtoList", JsonUtils.object2Json(cfetsBondDealRequestQuoteDto.getCfetsBondDealRequestQuoteDtoList()));
        model.addAttribute("quotaPageModel",this.getParam("quotaPageModel"));
        return super.init(model);
    }

    /**
     * 根据委托代码查询委托基本信息
     * 
     * @param entrustId 委托代码
     * @return 委托基本信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryEntrustInfoByEntrustId")
    public CfetsBondDealRequestQuoteDto queryEntrustInfoByEntrustId(String entrustId) {
        return cfetsBondDealQuotedPrice003Service.queryEntrustInfoByEntrustId(entrustId);
    }

    /**
     * 保存验证
     * 
     * @param CfetsBondDealRequestQuoteDto 对话报价对象
     * @param 处理结果
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid @RequestBody CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto,
                            BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        AjaxResult ar = new AjaxResult();
        StringBuffer sb = new StringBuffer();
        // 委托为空的场合只有新建模式且系统参数为软校验
//        if (StringUtil.isEmpty(cfetsBondDealRequestQuoteDto.getEntrustId()) && StringUtil.equals(
//            CacheUtil.getSysParam("ibuFlag_startDialogueQuoteWithNoEntrust"), "02")) {
//            sb.append(this.getMessage("CFETS_COMMON_E_004"));
//            sb.append(GlobalConstant.HTML_NEWLINE);
//        }

        // 银行间报价-现券买卖报价-超委托的价格校验开关（01 硬阻挡 02 软阻挡）
        if (StringUtil.equals(CacheUtil.getSysParam("ibuFlag_bondOverEntrustPrice"), "02")) {
            cfetsBondDealQuotedPrice003Service.checkCalcFieldEntrust(cfetsBondDealRequestQuoteDto,
                null, sb, false);
        }
        
        if (StringUtil.equals(CacheUtil.getSysParam("ibuFlag_bondOverEntrustAvaAmt"), "02")) {
            List<CfetsBondDealRequestQuoteDto> cfetsBondDealRequestQuoteDtoList = cfetsBondDealRequestQuoteDto.getCfetsBondDealRequestQuoteDtoList();
            for(CfetsBondDealRequestQuoteDto dto : cfetsBondDealRequestQuoteDtoList) {
            	if (dto.getEntrustAvaNum() == null
                        || dto.getAvaNum().compareTo(dto.getEntrustAvaNum()) > 0) {
                        sb.append(this.getMessage("CFETS_COMMON_E_041",dto.getEntrustId()));
                        sb.append(GlobalConstant.HTML_NEWLINE);
                    }
            }
        }

        if (sb.length() > 0) {
            sb.append("请确认是否继续？");
            ar.setData(sb);
        }
        else {
            String msg = this.getMessage("SysCommon.save");
            if (StringUtil.equals(cfetsBondDealRequestQuoteDto.getOperatorType(), "submit")) {
                msg = this.getMessage("SysCommon.submit");
            }
            ar.setData(this.getMessage("COMMON_C_0001", msg));
        }

        return ar;
    }

    /**
     * 成交验证
     * 
     * @param CfetsBondDealRequestQuoteDto 对话报价对象
     * @param 处理结果
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("clinchDealCheck")
    public AjaxResult clinchDealCheck(@Valid @RequestBody CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto,
                                      BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        AjaxResult ar = new AjaxResult();
        StringBuffer sb = new StringBuffer();
        // 银行间报价-现券买卖报价-超委托剩余额度校验开关（01 硬阻挡 02 软阻挡）
        if (StringUtil.equals(CacheUtil.getSysParam("ibuFlag_bondOverEntrustAvaAmt"), "02")
            && StringUtil.isNotEmpty(cfetsBondDealRequestQuoteDto.getEntrustId())) {
            List<CounterInfoDto> list = cfetsBondDealRequestQuoteDto.getCounterInfoList();
            if (!StringUtil.isEmptyList(list)) {
                CounterInfoDto counterInfoDto = list.get(0);
                BigDecimal shareAmt = counterInfoDto.getShareAmt();
                Map<String, Object> param = new HashMap<>();
                param.put("entrustId", cfetsBondDealRequestQuoteDto.getEntrustId());
                param.put("quoteId", cfetsBondDealRequestQuoteDto.getQuoteId());
                BigDecimal dealedAmt = cfetsBondDealQuotedPrice003Service.queryEntrustDealedAmt(
                    param);
                if (StringUtil.isNotEmpty(shareAmt)
                    && ArithmeticUtil.multiply(shareAmt, new BigDecimal(10000)).compareTo(
                        ArithmeticUtil.subtract(cfetsBondDealRequestQuoteDto.getEntrustAmt(),
                            dealedAmt)) > 0) {
                    sb.append(this.getMessage("CFETS_COMMON_E_021"));
                    sb.append(GlobalConstant.HTML_NEWLINE);
                }
            }
        }
        
        // 银行间报价-现券买卖报价-超委托的价格校验开关（01 硬阻挡 02 软阻挡）
        if (StringUtil.equals(CacheUtil.getSysParam("ibuFlag_bondOverEntrustPrice"), "02")) {
            cfetsBondDealQuotedPrice003Service.checkCalcFieldEntrust(cfetsBondDealRequestQuoteDto, null, sb, false);
        }
        
        // 委托为空的场合只有新建模式且系统参数为软校验
        if (StringUtil.equals(CacheUtil.getSysParam("ibuFlag_startDialogueQuoteWithNoEntrust"), "02")
            && !StringUtil.isNotEmpty(cfetsBondDealRequestQuoteDto.getEntrustId())) {
            sb.append(this.getMessage("CFETS_COMMON_E_039"));
            sb.append(GlobalConstant.HTML_NEWLINE);
        }

        if (sb.length() > 0) {
            sb.append("请确认是否继续？");
            ar.setData(sb);
        }
        else {
            ar.setData(this.getMessage("COMMON_C_0001", this.getMessage("SysCommon.save")));
        }

        return ar;
    }

    /**
     * 保存提交
     *
     * @param CfetsBondDealRequestQuoteDto 对话报价对象
     * @return 处理结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("saveData")
    public AjaxResult saveData(@RequestBody CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto) {

        cfetsBondDealQuotedPrice003Service.save(cfetsBondDealRequestQuoteDto);

        return new AjaxResult(this.getMessage("CFETS_COMMON_SAVE"));
    }

    /**
     * 保存提交
     *
     * @param CfetsBondDealRequestQuoteDto 对话报价对象
     * @return 处理结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("submit")
    public AjaxResult submit(@RequestBody CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto) {

        cfetsBondDealQuotedPrice003Service.submit(cfetsBondDealRequestQuoteDto);

        return new AjaxResult(this.getMessage("CFETS_COMMON_SUBMIT"));
    }

    /**
     * 成交
     *
     * @param CfetsBondDealRequestQuoteDto 对话报价对象
     * @return 处理结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("clinchDeal")
    public AjaxResult clinchDeal(@RequestBody CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto) {

        cfetsBondDealQuotedPrice003Service.clinchDeal(cfetsBondDealRequestQuoteDto);

        return new AjaxResult(this.getMessage("CFETS_COMMON_TRADE"));
    }

    /**
     * 现券买卖历史报价 撤销
     * 
     * @param dto
     * @param br
     * @return
     * @throws WorkFlowException
     */
    @ResponseBody
    @RequestMapping("bondRevoke")
    public AjaxResult bondRevoke(@RequestBody CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto)
        throws WorkFlowException {
        ProcessParameter<CfetsBondDealRequestQuoteDto> processParameter = new ProcessParameter<CfetsBondDealRequestQuoteDto>();
        WorkFlowParameter<CfetsBondDealRequestQuoteDto> workFlowParameter = new WorkFlowParameter<CfetsBondDealRequestQuoteDto>();
        workFlowParameter.setPageId("CfetsBondDealQuotedPrice003");
        workFlowParameter.setElementId("CfetsBondDealQuotedPrice003_btn_bondRevoke");
        workFlowParameter.setBusinessCode(cfetsBondDealRequestQuoteDto.getQuoteId());
        workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_银行间上行.getValue());
        workFlowParameter.setComment(
            entity -> getMessage("CFETSHIS_QUOTEID") + "(" + entity.getQuoteId() + ")");
        processParameter.setWorkFlowParameter(workFlowParameter);

        String msg = cfetsBondDealQuotedPrice003Service.start(processParameter,
            cfetsBondDealRequestQuoteDto);

        return new AjaxResult(msg);
    }
    
    @ResponseBody
	@RequestMapping("save")
	public AjaxResult save(@RequestBody CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto, BindingResult br) throws WorkFlowException {
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		if (!StringUtil.isNotEmpty(cfetsBondDealRequestQuoteDto.getQuoteId())) {
			BussinessCodeParameter codeParameter = new BussinessCodeParameter();
			codeParameter.setSeqNo("IBU_002");
			String code = bussGetBussinessIdService != null
					? bussGetBussinessIdService.buildBussinessCode(codeParameter) : IDGenerator.generate();
					cfetsBondDealRequestQuoteDto.setQuoteId(code);
		}
		ProcessParameter<CfetsBondDealRequestQuoteDto> processParameter = new ProcessParameter<CfetsBondDealRequestQuoteDto>();
		WorkFlowParameter<CfetsBondDealRequestQuoteDto> params = cfetsBondDealQuotedPrice003Service.getWorkFlowParameter(cfetsBondDealRequestQuoteDto);
		params.setPageId("CfetsBondDealQuotedPrice005");
		params.setElementId(cfetsBondDealRequestQuoteDto.getElementId());
		params.setBusinessType(DictCodeListExt3.共通业务类型_银行间上行.getValue());
		params.setBusinessCode(cfetsBondDealRequestQuoteDto.getQuoteId());
		cfetsBondDealRequestQuoteDto.setQuoteType(DictCodeListExt3.报价系统区分类型_现券请求报价.getValue());
		params.setComment(entity -> getMessage("请求报价编号") + "(" + entity.getQuoteId() + ")-"
				+ "业务类型" + "(现券买卖" + entity.getQuoteTypeStr() + ")-" + "平台日" + "("
				+ DateUtil.format(iPlatformDate.getSysDate()) + ")");
		processParameter.setWorkFlowParameter(params);

		cfetsBondDealRequestQuoteDto.setInvManagerId(LoginUserUtil.getUserName());// 投资经理
		cfetsBondDealRequestQuoteDto.setCreateUser(LoginUserUtil.getUserName());// 用户名
		cfetsBondDealRequestQuoteDto.setCreateDept(LoginUserUtil.getDeptCode());// 用户部门
		cfetsBondDealRequestQuoteDto.setCreateTime(new Date());// 创建时间
		String msg = cfetsBondDealQuotedPrice003Service.start(processParameter, cfetsBondDealRequestQuoteDto);
		//cfetsBondDealQuotedPrice003Service.insertWflBondRequestQuote(cfetsBondDealRequestQuoteDto);
		return new AjaxResult(msg);
	}
}
