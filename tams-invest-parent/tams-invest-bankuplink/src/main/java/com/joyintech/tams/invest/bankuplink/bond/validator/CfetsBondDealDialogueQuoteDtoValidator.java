/*
 * 文件名：CfetsBondDeal.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2020年5月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.bankuplink.bond.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.ajax.AjaxResult;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.EtrEntrustMapper;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.invest.bankuplink.bond.dto.BondQuoteInfoDto;
import com.joyintech.tams.invest.bankuplink.bond.dto.CfetsBondDealDialogueQuoteDto;
import com.joyintech.tams.invest.bankuplink.bond.service.CfetsBondDealQuotedPrice002Service;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;

/**
 * 现券对话报价验证类
 * 
 * @author zhangliang
 * @version 1.0 2020年5月14日
 * @see CfetsBondDealDialogueQuoteDtoValidator
 * @since 1.0
 */
@Component
public class CfetsBondDealDialogueQuoteDtoValidator implements BeanValidator {

    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsBondDealQuotedPrice002Service cfetsBondDealQuotedPrice002Service;

    /**
     * {@linkplain}
     */
    @Autowired
    private EtrEntrustMapper etrEntrustMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        CfetsBondDealDialogueQuoteDto cfetsBondDealDialogueQuoteDto = (CfetsBondDealDialogueQuoteDto)value;
        // 验证信息
        List<ValidMessage> validList = new ArrayList<ValidMessage>();
        String isDzh = cfetsBondDealDialogueQuoteDto.getIsDzh();
        //该报价是否在流程中
        if(StringUtil.isNotEmpty(cfetsBondDealDialogueQuoteDto.getQuoteId())){
            validList.addAll(cfetsBondDealQuotedPrice002Service.isInWflProcess(cfetsBondDealDialogueQuoteDto.getQuoteId(),cfetsBondDealDialogueQuoteDto.getPageId(),cfetsBondDealDialogueQuoteDto.getWorkflowId()));
        }
        if (!StringUtil.equals(cfetsBondDealDialogueQuoteDto.getOperatorType(), "refuse")) {
            if (!StringUtil.isNotEmpty(cfetsBondDealDialogueQuoteDto.getPortfolioId())&&StringUtil.isEmptyList(cfetsBondDealDialogueQuoteDto.getCfetsBondDealDialogueQuoteDtoList())
                    && !StringUtil.equals(isDzh,"Y")) {
                validList.add(new ValidMessage("portfolioId", this.getMessage("FW_E_0006", this.getMessage("CfetsBondDealQuotedPrice002.portfolioName"))));
            }
        }
        
        if (StringUtil.equals(cfetsBondDealDialogueQuoteDto.getOperatorType(), "save")
            || StringUtil.equals(cfetsBondDealDialogueQuoteDto.getOperatorType(), "submit")) {
            if (StringUtil.isNotEmpty(cfetsBondDealDialogueQuoteDto.getAvaNum())) {
                if (cfetsBondDealDialogueQuoteDto.getAvaNum().compareTo(BigDecimal.ZERO) <= 0) {
                    validList.add(new ValidMessage("avaNum", "券面总额必须大于0！"));
                }
                // 券面需要为1000的整数倍（接口规则要求）
                String numStr = ArithmeticUtil.multiply(cfetsBondDealDialogueQuoteDto.getAvaNum(),
                    BigDecimal.valueOf(10)).toPlainString();
                if (Double.parseDouble(numStr) % 1.0d > 0) {
                    validList.add(new ValidMessage("avaNum", this.getMessage("QUOTE_UPLOAD_038_2", cfetsBondDealDialogueQuoteDto.getAvaNum().toPlainString())));
                }
            }
            if(StringUtil.isNotEmpty(cfetsBondDealDialogueQuoteDto.getQuoteId())){
            	CfetsBondDealDialogueQuoteDto tradeStatusDto = cfetsBondDealQuotedPrice002Service
						.getBondDialogueQuote(cfetsBondDealDialogueQuoteDto.getQuoteId(), null);
            	if(StringUtils.equals(tradeStatusDto.getTradeStatus(), BussinessDictCodeList.银行间上行报价状态_已成交.getValue()) ||
            			StringUtils.equals(tradeStatusDto.getTradeStatus(), BussinessDictCodeList.银行间上行报价状态_已终止.getValue())){
            		validList.add(new ValidMessage("tradeStatus", this.getMessage("CFETS_COMMON_E_040")));
            	}
            }
        }
        //多组合
        if(StringUtil.equals(isDzh,"Y")){
            if (!StringUtil.equals(cfetsBondDealDialogueQuoteDto.getOperatorType(), "refuse")) {
                if (StringUtil.isEmptyList(cfetsBondDealDialogueQuoteDto.getCfetsBondDealDialogueQuoteDtoList())) {
                    validList.add(new ValidMessage("",getMessage("组合分配中必须存在至少一条指令")));
                    return validList;
                }else{
                    Map<String,Object> map = new HashMap<>();
                    BigDecimal avaNumTotal = new BigDecimal(0);
                    String entrustIdList = StringUtil.EMPTY;
                    for (CfetsBondDealDialogueQuoteDto cfetsBondDealDialogueQuote : cfetsBondDealDialogueQuoteDto.getCfetsBondDealDialogueQuoteDtoList()) {
                        entrustIdList = entrustIdList+cfetsBondDealDialogueQuote.getEntrustId()+",";
                        if(null==cfetsBondDealDialogueQuote.getAvaNum()){
                            validList.add(new ValidMessage("",getMessage("组合分配中券面总额（万元）不可为空")));
                            return validList;
                        }
                        avaNumTotal = ArithmeticUtil.doAdd(avaNumTotal,cfetsBondDealDialogueQuote.getAvaNum());
                        cfetsBondDealDialogueQuoteDto.setEntrustId(cfetsBondDealDialogueQuote.getEntrustId());
                        验证委托状态(cfetsBondDealDialogueQuoteDto, validList);
                        //指令委托金额带出 下面计算剩余额度 需要
                        cfetsBondDealDialogueQuote.setEntrustAmt(cfetsBondDealDialogueQuoteDto.getEntrustAmt());
                        cfetsBondDealDialogueQuoteDto.setEntrustId(StringUtil.EMPTY);
                        if (null != validList && !StringUtil.isEmptyList(validList)) {
                            return validList;
                        }
                    }
                    if(avaNumTotal.compareTo(cfetsBondDealDialogueQuoteDto.getAvaNum())!=0){
                        validList.add(new ValidMessage("",getMessage("报价券面总额必须和各指令分配后券面总额汇总值相等")));
                    }
                    //汇总提交是否满足
                    map.put("entrustIdList",entrustIdList);
                    AjaxResult ar = cfetsBondDealQuotedPrice002Service.checkEntrustList(map);
                    if(!StringUtil.isEmptyList(ar.getErrorList())){
                        return ar.getErrorList();
                    }
                }
            }
        }
        else{
            if (StringUtil.isNotEmpty(cfetsBondDealDialogueQuoteDto.getEntrustId())) {
                验证委托状态(cfetsBondDealDialogueQuoteDto,validList);
            }
            else{
                // 委托为空的场合只有新建模式且系统参数为软校验
                if (StringUtil.equals(cfetsBondDealDialogueQuoteDto.getQuotaPageModel(), "new")
                        && !StringUtil.equals(CacheUtil.getSysParam("ibuFlag_startDialogueQuoteWithNoEntrust"), "02")) {
                    validList.add(new ValidMessage("entrustId", this.getMessage("CFETS_COMMON_E_004")));
                }
            }
         }
        // 对话报价-回复
        if (StringUtil.equals(cfetsBondDealDialogueQuoteDto.getQuotaPageModel(), "dialogueResponse")) {
            // 需要区分成交和拒绝，拒绝只需要判断状态 是否变化
            // 查询报价信息检查状态是否变化
            if (StringUtil.isNotEmpty(cfetsBondDealDialogueQuoteDto.getQuoteId())) {
				CfetsBondDealDialogueQuoteDto cfetsBondDealDialogueQuoteDtoNew = cfetsBondDealQuotedPrice002Service
						.getBondDialogueQuote(cfetsBondDealDialogueQuoteDto.getQuoteId(), null);
                BondQuoteInfoDto bondQuoteInfoDto = new BondQuoteInfoDto();
                BeanUtil.copyPropertiesIgnoreNull(bondQuoteInfoDto,
                    cfetsBondDealDialogueQuoteDtoNew);
                if (!StringUtil.equals(bondQuoteInfoDto.getAnswerFlag(), "01")) {
                    validList.add(new ValidMessage(this.getMessage("CFETS_COMMON_E_008")));
                }
            }
            if (StringUtil.equals(cfetsBondDealDialogueQuoteDto.getOperatorType(), "done")) {
                //多组合
                if(StringUtil.equals(isDzh,"Y")){
                    for (CfetsBondDealDialogueQuoteDto cfetsBondDealDialogueQuote : cfetsBondDealDialogueQuoteDto.getCfetsBondDealDialogueQuoteDtoList()) {
                        CfetsBondDealDialogueQuoteDto cfetsBondDealDialogueQuoteDoneDto = new CfetsBondDealDialogueQuoteDto();
                        cfetsBondDealDialogueQuoteDoneDto.setQuoteId(cfetsBondDealDialogueQuoteDto.getQuoteId());
                        cfetsBondDealDialogueQuoteDoneDto.setEntrustId(cfetsBondDealDialogueQuote.getEntrustId());
                        cfetsBondDealDialogueQuoteDoneDto.setAvaNum(cfetsBondDealDialogueQuote.getAvaNum());
                        cfetsBondDealDialogueQuoteDoneDto.setEntrustAmt(cfetsBondDealDialogueQuote.getEntrustAmt());
                        指令额度判断(cfetsBondDealDialogueQuoteDoneDto,validList);
                        if(!StringUtil.isEmptyList(validList)){
                            return validList;
                        }
                    }
                }
                else{
                    if (StringUtil.isNotEmpty(cfetsBondDealDialogueQuoteDto.getEntrustId())) {
                        指令额度判断(cfetsBondDealDialogueQuoteDto,validList);
                    }
                    else {
                        if (!StringUtil.equals(
                            CacheUtil.getSysParam("ibuFlag_answerQuoteWithOutEntrust"), "02")) {
                            validList.add(
                                new ValidMessage("entrustId", this.getMessage("CFETS_COMMON_E_009")));
                        }
                    }
                }
            }
        }
        else {
            // 7) 需要验证交易的偏离度是否超参数值。交易偏离度=（交易净价-上一日中债估值净价）/上一日中债估值净价*100%；上一日净价为小于平台日的最新净价
            if (!StringUtil.equals(CacheUtil.getSysParam("ibuFlag_dealDeviation"), "02")
                && StringUtil.isNotEmpty(CacheUtil.getSysParam("ibuVar_dealDeviationVal"))){
                cfetsBondDealQuotedPrice002Service.checkDealDeviation(cfetsBondDealDialogueQuoteDto, validList, null, true);
            }

            if (!StringUtil.isNotEmpty(cfetsBondDealDialogueQuoteDto.getCalculateField())) {
                validList.add(
                    new ValidMessage(this.getMessage("CFETS_COMMON_E_011")));
            }

            if (StringUtil.isNotEmpty(cfetsBondDealDialogueQuoteDto.getAssetTypeOne())) {
                if (StringUtil.equals(cfetsBondDealDialogueQuoteDto.getAssetTypeOne(),
                    BussinessDictCodeList.平安资产一级分类_资产支持证券.getValue())) {
                    if (!StringUtil.isNotEmpty(cfetsBondDealDialogueQuoteDto.getParValue())) {
                        validList.add(new ValidMessage("parValue", this.getMessage("FW_E_0006",
                            this.getMessage("CfetsBondDealQuotedPrice002.perHundAmt"))));
                    }
                    if (!StringUtil.isNotEmpty(cfetsBondDealDialogueQuoteDto.getParValueAmt())) {
                        validList.add(new ValidMessage("parValueAmt", this.getMessage("FW_E_0006",
                            this.getMessage("CfetsBondDealQuotedPrice002.prinAmt"))));
                    }
                    if (!StringUtil.equals(cfetsBondDealDialogueQuoteDto.getCalculateField(),
                        BussinessDictCodeList.计算选择方式_净价.getValue())) {
                        validList.add(new ValidMessage(this.getMessage("CFETS_COMMON_E_036")));
                    }
                }
            }

            if (StringUtil.isNotEmpty(cfetsBondDealDialogueQuoteDto.getIsRight())
                && StringUtil.equals(cfetsBondDealDialogueQuoteDto.getIsRight(), "Y")) {
                if (!StringUtil.isNotEmpty(cfetsBondDealDialogueQuoteDto.getExerYield())) {
                    validList.add(new ValidMessage("exerYield", this.getMessage("FW_E_0006",
                        this.getMessage("CfetsBondDealQuotedPrice002.exec"))));
                }
            }

            if (!StringUtil.equals(CacheUtil.getSysParam("ibuFlag_bondOverEntrustAvaAmt"), "02")){
            	if(StringUtil.isNotEmpty(cfetsBondDealDialogueQuoteDto.getEntrustId())){
//            		CfetsBondDealDialogueQuoteDto entrustDto = cfetsBondDealQuotedPrice002Service.queryEntrustInfoByEntrustId(cfetsBondDealDialogueQuoteDto.getEntrustId());
//        			BigDecimal entrustAmt = entrustDto.getEntrustNum() == null ? BigDecimal.ZERO : entrustDto.getEntrustNum().divide(new BigDecimal(100));//券面总额/100
//        			BigDecimal usedShareAmt = cfetsBondDealQuotedPrice002Service.checkUsedShareAmt(cfetsBondDealDialogueQuoteDto);//已占用额度
//        			if (usedShareAmt.add(cfetsBondDealDialogueQuoteDto.getAvaNum().multiply(new BigDecimal(10000)).divide(new BigDecimal(100))).compareTo(entrustAmt) > 0) {
//        				validList.add(new ValidMessage("avaNum",this.getMessage("CFETS_COMMON_E_010")));
//        			}
                    cfetsBondDealQuotedPrice002Service.checkEntrustAvaNum(cfetsBondDealDialogueQuoteDto, validList, null, true);
            	}
            }

            if (StringUtil.equals(cfetsBondDealDialogueQuoteDto.getQuotaPageModel(), "marketResponse")) {
            	if (StringUtil.isEmpty(cfetsBondDealDialogueQuoteDto.getEntrustId())) {
            		if (!StringUtil.equals(CacheUtil.getSysParam("ibuFlag_answerQuoteWithOutEntrust"), "02")) {
                        validList.add(new ValidMessage("entrustId", this.getMessage("CFETS_COMMON_E_009")));
                    }
            	}

            	// 回复量不能超请求报价的券面总额
                if (cfetsBondDealDialogueQuoteDto.getResponseAmt() != null) {
                	if (cfetsBondDealDialogueQuoteDto.getAvaNum() != null) {
                        if (cfetsBondDealDialogueQuoteDto.getResponseAmt().compareTo(
                            cfetsBondDealDialogueQuoteDto.getAvaNum()) > 0) {
                            validList.add(
                                new ValidMessage("responseAmt", this.getMessage("CFETS_COMMON_E_013")));
                        }
                    }
                	if (cfetsBondDealDialogueQuoteDto.getResponseAmt().compareTo(BigDecimal.ZERO) <= 0) {
                        validList.add(new ValidMessage("avaNum", "回复量必须大于0！"));
                    }
                    // 券面需要为1000的整数倍（接口规则要求）
                    String numStr = ArithmeticUtil.multiply(cfetsBondDealDialogueQuoteDto.getResponseAmt(),
                        BigDecimal.valueOf(10)).toPlainString();
                    if (Double.parseDouble(numStr) % 1.0d > 0) {
                        validList.add(new ValidMessage("responseAmt", "回复量" + cfetsBondDealDialogueQuoteDto.getResponseAmt().toPlainString() + "需要为1000元的整数倍"));
                    }
                }
            }
        }

        return validList;
    }

    private void 验证委托状态(CfetsBondDealDialogueQuoteDto cfetsBondDealDialogueQuoteDto,List<ValidMessage> validList){
        String entrustId = cfetsBondDealDialogueQuoteDto.getEntrustId();
        EtrEntrust etrEntrust = etrEntrustMapper.selectByPrimaryKey(entrustId);
        cfetsBondDealDialogueQuoteDto.setEntrustAmt(etrEntrust.getEntrustAmt() == null ? etrEntrust.getEntrustNum().multiply(new BigDecimal(100)) : etrEntrust.getEntrustAmt());
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
                cfetsBondDealQuotedPrice002Service.checkCalcFieldEntrust(cfetsBondDealDialogueQuoteDto, validList, null, true);
            }
        }
    }

    private void 指令额度判断(CfetsBondDealDialogueQuoteDto cfetsBondDealDialogueQuoteDto,List<ValidMessage> validList){
        if (cfetsBondDealDialogueQuoteDto.getAvaNum() != null) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("entrustId", cfetsBondDealDialogueQuoteDto.getEntrustId());
            if (StringUtil.isNotBlank(cfetsBondDealDialogueQuoteDto.getQuoteId())) {
                map.put("quoteId", cfetsBondDealDialogueQuoteDto.getQuoteId());
            }
            BigDecimal dealedAmt = cfetsBondDealQuotedPrice002Service.queryEntrustDealedAmt(
                    map);
            BigDecimal avaAmt = ArithmeticUtil.subtract(
                    cfetsBondDealDialogueQuoteDto.getEntrustAmt(), dealedAmt);
            if (ArithmeticUtil.multiply(cfetsBondDealDialogueQuoteDto.getAvaNum(), new BigDecimal(10000)).compareTo(avaAmt) > 0) {
                validList.add(new ValidMessage("responseAmt",
                        this.getMessage("CFETS_COMMON_E_010") + avaAmt.toPlainString()));
            }
        }
    }
}
