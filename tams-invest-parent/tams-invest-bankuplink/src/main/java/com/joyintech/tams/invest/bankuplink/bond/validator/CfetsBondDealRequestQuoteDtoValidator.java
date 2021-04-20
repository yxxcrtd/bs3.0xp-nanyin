/*
 * 文件名：CfetsBondDealRequestQuoteDtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzhuangzhuang
 * 修改时间：2020年5月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.bankuplink.bond.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.EtrEntrustMapper;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.invest.bankuplink.bond.dto.CfetsBondDealDialogueQuoteDto;
import com.joyintech.tams.invest.bankuplink.bond.dto.CfetsBondDealRequestQuoteDto;
import com.joyintech.tams.invest.bankuplink.bond.dto.CounterInfoDto;
import com.joyintech.tams.invest.bankuplink.bond.service.CfetsBondDealQuotedPrice003Service;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;

/**
 * 请求对话报价验证类
 *
 * @author wangzhuangzhuang
 * @version 1.0 2020年5月19日
 * @see CfetsBondDealRequestQuoteDtoValidator
 * @since 1.0
 */
@Component
public class CfetsBondDealRequestQuoteDtoValidator implements BeanValidator {

    /**
     * {@linkplain}
     */
    @Autowired
    private EtrEntrustMapper etrEntrustMapper;

    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsBondDealQuotedPrice003Service cfetsBondDealQuotedPrice003Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto = (CfetsBondDealRequestQuoteDto)value;
        // 验证信息
        List<ValidMessage> validList = new ArrayList<ValidMessage>();
        String isDzh = cfetsBondDealRequestQuoteDto.getIsDzh();
        //该报价是否在流程中
        if(StringUtil.isNotEmpty(cfetsBondDealRequestQuoteDto.getQuoteId())){
            validList.addAll(cfetsBondDealQuotedPrice003Service.isInWflProcess(cfetsBondDealRequestQuoteDto.getQuoteId(),cfetsBondDealRequestQuoteDto.getPageId(),cfetsBondDealRequestQuoteDto.getWorkflowId()));
        }
        if (StringUtil.equals(cfetsBondDealRequestQuoteDto.getOperatorType(), "save")
            || StringUtil.equals(cfetsBondDealRequestQuoteDto.getOperatorType(), "submit")) {
            if (StringUtil.isNotEmpty(cfetsBondDealRequestQuoteDto.getAvaNum())) {
            	if (cfetsBondDealRequestQuoteDto.getAvaNum().compareTo(BigDecimal.ZERO) <= 0) {
                    validList.add(new ValidMessage("avaNum", "券面总额必须大于0！"));
                }
                // 券面需要为十万的整数倍（接口规则要求）
                String numStr = cfetsBondDealRequestQuoteDto.getAvaNum().toPlainString();
                if (Double.parseDouble(numStr) % 10.0d > 0) {
                    validList.add(new ValidMessage("avaNum", this.getMessage("QUOTE_UPLOAD_038", cfetsBondDealRequestQuoteDto.getAvaNum().toPlainString())));
                }
            }
        }
        
      //多组合
        if(StringUtil.equals(isDzh,"Y")){
            if (StringUtil.isEmptyList(cfetsBondDealRequestQuoteDto.getCfetsBondDealRequestQuoteDtoList())) {
                validList.add(new ValidMessage("",getMessage("组合分配中必须存在至少一条指令")));
                return validList;
            }else{
                BigDecimal avaNumTotal = new BigDecimal(0);
                for (CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuote : cfetsBondDealRequestQuoteDto.getCfetsBondDealRequestQuoteDtoList()) {
                    if(null==cfetsBondDealRequestQuote.getAvaNum()){
                        validList.add(new ValidMessage("",getMessage("组合分配中券面总额（万元）不可为空")));
                        return validList;
                    }
                    avaNumTotal = ArithmeticUtil.doAdd(avaNumTotal,cfetsBondDealRequestQuote.getAvaNum());
                    cfetsBondDealRequestQuoteDto.setEntrustId(cfetsBondDealRequestQuote.getEntrustId());
                    	验证委托状态(cfetsBondDealRequestQuoteDto, validList);
                    //指令委托金额带出 下面计算剩余额度 需要
                    cfetsBondDealRequestQuote.setEntrustAmt(cfetsBondDealRequestQuoteDto.getEntrustAmt());
                    cfetsBondDealRequestQuoteDto.setEntrustId(StringUtil.EMPTY);
                    if (null != validList && !StringUtil.isEmptyList(validList)) {
                        return validList;
                    }
                }
                if(avaNumTotal.compareTo(cfetsBondDealRequestQuoteDto.getAvaNum())!=0){
                    validList.add(new ValidMessage("",getMessage("报价券面总额和各指令分配后券面总额不相等")));
                }
            }
        }else {
        	if (StringUtil.isNotEmpty(cfetsBondDealRequestQuoteDto.getEntrustId())) {
        		验证委托状态(cfetsBondDealRequestQuoteDto,validList);
            }
            else{
                // 委托为空的场合只有新建模式且系统参数为软校验
                if (!StringUtil.equals(CacheUtil.getSysParam("ibuFlag_startDialogueQuoteWithNoEntrust"), "02")) {
                    // 回复页面-成交按钮校验
                    if (StringUtil.equals(cfetsBondDealRequestQuoteDto.getCheckType(), "answerClinchDeal")) {
                        validList.add(new ValidMessage("entrustId", this.getMessage("CFETS_COMMON_E_039")));
                    }
                    else{
                        validList.add(new ValidMessage("entrustId", this.getMessage("CFETS_COMMON_E_004")));
                    }
                }
            }
        }

        if (StringUtil.isNotEmpty(cfetsBondDealRequestQuoteDto.getEntrustId())) {
//            EtrEntrust etrEntrust = etrEntrustMapper.selectByPrimaryKey(cfetsBondDealRequestQuoteDto.getEntrustId());
            Map<String, Object> param = new HashMap<>();
            param.put("entrustId", cfetsBondDealRequestQuoteDto.getEntrustId());
            param.put("quoteId", cfetsBondDealRequestQuoteDto.getQuoteId());
            BigDecimal dealedAmt = cfetsBondDealQuotedPrice003Service.queryEntrustDealedAmt(param);
            // 查询库里最新的状态
            CfetsBondDealRequestQuoteDto dto = cfetsBondDealQuotedPrice003Service.getBondRequestQuote(
                cfetsBondDealRequestQuoteDto.getQuoteId(),null);
//            cfetsBondDealRequestQuoteDto.setEntrustAmt(etrEntrust.getEntrustAmt() == null ? etrEntrust.getEntrustNum().multiply(new BigDecimal(100)) : etrEntrust.getEntrustAmt());
//            if (etrEntrust != null) {
//                if (StringUtil.equals(etrEntrust.getEntrustStatus(),
//                    BussinessDictCodeList.委托状态_已关闭.getValue())
//                    || StringUtil.equals(etrEntrust.getEntrustStatus(),
//                        BussinessDictCodeList.委托状态_已撤回.getValue())) {
//                    validList.add(
//                        new ValidMessage("entrustId", this.getMessage("CFETS_COMMON_E_007")));
//                }
//            }
//            if (!StringUtil.equals(CacheUtil.getSysParam("ibuFlag_bondOverEntrustAvaAmt"), "02")) {
//                EtrEntrust entrustDto = etrEntrustMapper.selectByPrimaryKey(cfetsBondDealRequestQuoteDto.getEntrustId());
//			    BigDecimal entrustAmt = entrustDto.getEntrustNum() == null ? BigDecimal.ZERO : entrustDto.getEntrustNum();
//			    BigDecimal usedShareAmt = cfetsBondDealQuotedPrice003Service.checkUsedShareAmt(cfetsBondDealRequestQuoteDto);//已占用额度
//			    if (usedShareAmt.add(cfetsBondDealRequestQuoteDto.getAvaNum().multiply(new BigDecimal(10000)).divide(new BigDecimal(100))).compareTo(entrustAmt) > 0) {
//				    validList.add(new ValidMessage("avaNum",this.getMessage("CFETS_COMMON_E_010")));
//			    }
//            }

            // 回复页面-成交按钮校验
            if (StringUtil.equals(cfetsBondDealRequestQuoteDto.getCheckType(), "answerClinchDeal")) {
            	boolean flag1 = StringUtil.equals(dto.getTradeStatus(), BussinessDictCodeList.银行间上行报价状态_已回复.getValue());
            	boolean flag2 = !StringUtil.isNotEmpty(dto.getSendStatus());
                // 判断是否满足成交条件
                if (!(flag1 && flag2)) {
                    validList.add(new ValidMessage("quoteId", this.getMessage("CFETS_COMMON_E_020")));
                }
                // 银行间报价-现券买卖报价-超委托剩余额度校验开关（01 硬阻挡 02 软阻挡）
                if (!StringUtil.equals(CacheUtil.getSysParam("ibuFlag_bondOverEntrustAvaAmt"), "02")) {
                    List<CounterInfoDto> list = cfetsBondDealRequestQuoteDto.getCounterInfoList();
                    if (!StringUtil.isEmptyList(list)) {
                        CounterInfoDto counterInfoDto = list.get(0);
                        BigDecimal shareAmt = counterInfoDto.getShareAmt();
                        if (StringUtil.isNotEmpty(shareAmt)
                            && ArithmeticUtil.multiply(shareAmt, new BigDecimal(10000)).compareTo(
                                ArithmeticUtil.subtract(
                                    cfetsBondDealRequestQuoteDto.getEntrustAmt(),
                                    dealedAmt)) > 0) {
                            validList.add(new ValidMessage(this.getMessage("CFETS_COMMON_E_021")));
                        }
                    }
                }
                // 银行间报价-现券买卖报价-超委托的价格校验开关（01 硬阻挡 02 软阻挡）
                if (!StringUtil.equals(CacheUtil.getSysParam("ibuFlag_bondOverEntrustPrice"), "02")) {
                    cfetsBondDealQuotedPrice003Service.checkCalcFieldEntrust(cfetsBondDealRequestQuoteDto, validList, null, true);
                }
            }
            // 页面点击保存,提交校验
            else {
                if (!StringUtil.equals(CacheUtil.getSysParam("ibuFlag_bondOverEntrustAvaAmt"), "02")
                    && (cfetsBondDealRequestQuoteDto.getAvaNum() == null
                        || ArithmeticUtil.multiply(cfetsBondDealRequestQuoteDto.getAvaNum(),
                            new BigDecimal(10000)).compareTo(ArithmeticUtil.subtract(
                                cfetsBondDealRequestQuoteDto.getEntrustAmt(), dealedAmt)) > 0)) {
                    validList.add(new ValidMessage("avaNum", this.getMessage("CFETS_COMMON_E_023")));
                }

            }
        }
        return validList;
    }
    
    private void 验证委托状态(CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto,List<ValidMessage> validList){
        String entrustId = cfetsBondDealRequestQuoteDto.getEntrustId();
        EtrEntrust etrEntrust = etrEntrustMapper.selectByPrimaryKey(entrustId);
        cfetsBondDealRequestQuoteDto.setEntrustAmt(etrEntrust.getEntrustAmt() == null ? etrEntrust.getEntrustNum().multiply(new BigDecimal(100)) : etrEntrust.getEntrustAmt());
        if (etrEntrust != null) {
            if (StringUtil.equals(etrEntrust.getEntrustStatus(),
                    BussinessDictCodeList.委托状态_已关闭.getValue())
                    || StringUtil.equals(etrEntrust.getEntrustStatus(),
                    BussinessDictCodeList.委托状态_已撤回.getValue())) {
                validList.add(new ValidMessage( this.getMessage("CFETS_COMMON_E_007")+"委托编号为："+ entrustId));
            }
            // 校验委托完成度
            if (!StringUtil.equals(etrEntrust.getEntrustStatus(),
                    BussinessDictCodeList.委托状态_已接收.getValue())) {
                validList.add(new ValidMessage( this.getMessage("CFETS_COMMON_E_002")+"委托编号为："+ entrustId));
            }
            // 验证委托价格
            if (!StringUtil.equals(CacheUtil.getSysParam("ibuFlag_bondOverEntrustPrice"), "02")) {
                cfetsBondDealQuotedPrice003Service.checkCalcFieldEntrust(cfetsBondDealRequestQuoteDto, validList, null, true);
            }
        }
    }
}