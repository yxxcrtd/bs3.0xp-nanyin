/*
 * 文件名：CfetsBondDealQuotedPrice003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzhuangzhuang
 * 修改时间：2020年5月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.cfets.bankuplink.bond.service;

import com.joyintech.background.component.uplink.interbank.business.dto.BondRequestQuoteDto;
import com.joyintech.background.component.uplink.interbank.business.dto.IbuQuoteBondDto;
import com.joyintech.background.component.uplink.interbank.business.service.IbuMainService;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.bankuplink.bond.service.CfetsBondDealQuotedPriceService;
import com.joyintech.service.bankuplink.ibu.dto.BondQuoteInfoDto;
import com.joyintech.tams.business.common.exception.PositionException;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.business.common.service.PositionHttpRequest;
import com.joyintech.tams.business.common.util.PositionUtil;
import com.joyintech.tams.cfets.bankuplink.bond.dao.CfetsIbuQuoteBondMapper;
import com.joyintech.tams.cfets.bankuplink.bond.dto.CfetsBondDealQuoteCommonDto;
import com.joyintech.tams.cfets.bankuplink.bond.dto.PtlAllocationDto;
import com.joyintech.tams.common.base.dao.EtrEntrustAddMapper;
import com.joyintech.tams.common.base.dao.EtrEntrustMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.bankuplink.bond.dto.CfetsBondDealDialogueQuoteDto;
import com.joyintech.tams.invest.bankuplink.bond.dto.CfetsBondDealRequestQuoteDto;
import com.joyintech.tams.invest.bankuplink.bond.dto.CounterInfoDto;
import com.joyintech.tams.invest.bankuplink.common.service.CfetsQuotedCommonService;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;


/**
 * 现券买卖-请求报价
 */
@Service("vueCfetsBondDealQuotedPrice003Service")
public class CfetsBondDealQuotedPrice003Service extends BaseService<CfetsBondDealRequestQuoteDto> implements BaseProcessService<CfetsBondDealRequestQuoteDto> {
    
    
    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;

    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsQuotedCommonService cfetsQuotedCommonService;

    @Autowired
    private com.joyintech.service.bankuplink.common.service.CfetsQuotedCommonService cfetsQuotedCommonServiceMicro;
    
    /**
     * {@linkplain}
     */
    @Autowired
    private EtrEntrustMapper etrEntrustMapper;

    /**
     * {@linkplain}
     */
    @Autowired
    private EtrEntrustAddMapper etrEntrustAddMapper;
    
    /**
     * FinProductMapper
     */
    @Autowired
    private FinProductMapper finProductMapper;
    
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonServie;

    @Autowired
    private CfetsBondDealQuotedPriceService cfetsBondDealQuotedPriceService;

    @Autowired
    private CfetsBondDealQuotedPrice002Service cfetsBondDealQuotedPrice002Service;

    @Autowired
    private CfetsIbuQuoteBondMapper cfetsIbuQuoteBondMapper;

    @Autowired
    private IbuMainService ibuMainService;

    @Autowired
    private com.joyintech.tams.cfets.bankuplink.bond.service.CfetsQuotedCommonService cfetsQuotedCommonServicevue;

    public AjaxResult checkNotEmpty(CfetsBondDealQuoteCommonDto entity) {
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        //模式
        String sellPara = CacheUtil.getSysParam("SHORT_SELLING_SUPPORTED");
        String ps = entity.getPs();
        if(StringUtil.isEmpty(ps)){
            errorList.add(new ValidMessage("请输入交易方向"));
        }
        if(StringUtil.isEmpty(entity.getFinprodId())){
            errorList.add(new ValidMessage("请输入资产代码"));
        }
        if(StringUtil.equals(ps, DictCodeList.交易方向_买入.getValue())||StringUtil.equals(ps,DictCodeList.交易方向_卖出.getValue())){
            if(StringUtil.isEmpty(entity.getMySideTrader())){
                errorList.add(new ValidMessage("请输入本方交易员"));
            }
            /*if(StringUtil.isEmpty(entity.getCounterId())){
                errorList.add(new ValidMessage("请输入对手方"));
            }
            if(StringUtil.isEmpty(entity.getCounterTrader())&&(StringUtil.equals(entity.getQuoteStyle(),"01")||StringUtil.equals(entity.getQuoteStyle(),"02"))){
                errorList.add(new ValidMessage("请输入对手方交易员"));
            }*/
            if(StringUtil.isEmpty(entity.getCcy())){
                errorList.add(new ValidMessage("请输入币种"));
            }
            if(StringUtil.isEmpty(entity.getSettleSpreedCode())){
                errorList.add(new ValidMessage("请输入清算速度"));
            }
            if(null==entity.getSettleDate()){
                errorList.add(new ValidMessage("请输入结算日期"));
            }
            if(null!=entity.getPrice()&&entity.getPrice().compareTo(BigDecimal.ZERO)<=0){
                errorList.add(new ValidMessage("净价必须大于0"));
            }
            if(null==entity.getShareAmt()){
                errorList.add(new ValidMessage("请输入券面总额"));
            }else{
                if(entity.getShareAmt().compareTo(BigDecimal.ZERO)<=0){
                    errorList.add(new ValidMessage("券面总额必须大于0"));
                }
            }
            if(StringUtil.isEmpty(entity.getSettleMethod())){
                errorList.add(new ValidMessage("请输入结算方式"));
            }
            if(null==entity.getSettleType()){
                errorList.add(new ValidMessage("请输入清算类型"));
            }
            List<com.joyintech.tams.cfets.bankuplink.bond.dto.CounterInfoDto> counterInfoList = entity.getCounterInfoList();
            if(CollectionUtils.isNotEmpty(counterInfoList)){
                counterInfoList.forEach(item ->{
                    if(StringUtil.isEmpty(item.getCounterId())){
                        errorList.add(new ValidMessage("请选择对手方机构"));
                        return;
                    }
                });
            }else{
                errorList.add(new ValidMessage("请添加对手方信息"));
            }
        }
        /*if(StringUtil.equals(sellPara,"02")){
            if(null==entity.getOurEntNum()){
                errorList.add(new ValidMessage("请输入本方券券面总额"));
            }
            if(null==entity.getLendinEntNum()&&StringUtil.equals(ps, DictCodeList.交易方向_卖出.getValue())){
                errorList.add(new ValidMessage("请输入融入券券面总额"));
            }
            if(null==entity.getSellAbleNum()&&StringUtil.equals(ps, DictCodeList.交易方向_买入.getValue())){
                errorList.add(new ValidMessage("请输入卖空购回券面总额"));
            }
        }*/
        if(null==entity.getDealFeeAmt()){
            errorList.add(new ValidMessage("请输入交易手续费"));
        }
        if(null==entity.getSettFeeAmt()){
            errorList.add(new ValidMessage("请输入结算服务费"));
        }
        if(CollectionUtils.isEmpty(entity.getPtlAllocatios())){
            errorList.add(new ValidMessage("组合分配不可为空"));
        }else{
            BigDecimal shareAmtTotal = BigDecimal.ZERO;BigDecimal dealFeeAmtTotal = BigDecimal.ZERO;BigDecimal settFeeamtTotal = BigDecimal.ZERO;
            for (PtlAllocationDto ptlAllocatio : entity.getPtlAllocatios()) {
                if(null==ptlAllocatio.getShareAmt()){
                    errorList.add(new ValidMessage("请输入组合分配券面总额"));
                    break;
                }
                if(null==ptlAllocatio.getDealFeeAmt()){
                    errorList.add(new ValidMessage("请输入组合分配交易手续费"));
                    break;
                }
                if(null==ptlAllocatio.getSettFeeAmt()){
                    errorList.add(new ValidMessage("请输入组合分配结算服务费"));
                    break;
                }
                /*if(StringUtil.equals(sellPara,"02")){
                    if(null==ptlAllocatio.getOurEntNum()){
                        errorList.add(new ValidMessage("请输入组合分配本方券券面总额"));
                        break;
                    }
                    if(null==ptlAllocatio.getLendinEntNum()&&StringUtil.equals(ps, DictCodeList.交易方向_卖出.getValue())){
                        errorList.add(new ValidMessage("请输入组合分配融入券券面总额"));
                        break;
                    }
                    if(null==ptlAllocatio.getSellAbleNum()&&StringUtil.equals(ps, DictCodeList.交易方向_买入.getValue())){
                        errorList.add(new ValidMessage("请输入组合分配卖空购回券面总额"));
                        break;
                    }
                }*/
                if(ptlAllocatio.getAvaNum().compareTo(ptlAllocatio.getShareAmt())<0){
                    errorList.add(new ValidMessage("报价指令剩余额度不能小于券面总额！"));
                }
                if(null!=ptlAllocatio.getShareAmt()){
                    BigDecimal wflShareAmtByEntrustId = cfetsQuotedCommonServicevue.getWflShareAmtByEntrustId(ptlAllocatio.getEntrustId());
                    if(null!=wflShareAmtByEntrustId&&wflShareAmtByEntrustId.compareTo(BigDecimal.ZERO)>0&&ArithmeticUtil.doAdd(ptlAllocatio.getShareAmt(),wflShareAmtByEntrustId).compareTo(ptlAllocatio.getAvaNum())>0){
                        errorList.add(new ValidMessage(StringUtil.join("组合分配券面总额不能超过指令剩余额度!流程中占用额度:",wflShareAmtByEntrustId.toString(),"元,指令代码:",ptlAllocatio.getEntrustId())));
                    }
                }
                shareAmtTotal = ArithmeticUtil.doAdd(shareAmtTotal,ptlAllocatio.getShareAmt());
                dealFeeAmtTotal = ArithmeticUtil.doAdd(dealFeeAmtTotal,ptlAllocatio.getDealFeeAmt());
                settFeeamtTotal = ArithmeticUtil.doAdd(settFeeamtTotal,ptlAllocatio.getSettFeeAmt());
                if(StringUtil.equals(entity.getTradeMarket(),BussinessDictCodeList.交易场所_银行间.getValue())){
                    AjaxResult ajaxResultPartyId = cfetsQuotedCommonServicevue.checkpartyId(ptlAllocatio.getEntrustId(), ptlAllocatio.getPartyId(), DictCodeListExt3.报价系统区分类型_现券请求报价.getValue());
                    if(StringUtil.isNotEmpty(ajaxResultPartyId.getMessage())){
                        errorList.add(new ValidMessage(ajaxResultPartyId.getMessage()));
                    }
                }
            }
            //券面总额   交易手续费  结算服务费  总和需要相等
            if(CollectionUtils.isEmpty(errorList)){
                if(null!=entity.getShareAmt()&&entity.getShareAmt().compareTo(shareAmtTotal)!=0){
                    errorList.add(new ValidMessage("组合分配券面总额总和需要等于券面总额"));
                }
                if(null!=entity.getDealFeeAmt()&&entity.getDealFeeAmt().compareTo(dealFeeAmtTotal)!=0){
                    errorList.add(new ValidMessage("组合分配交易手续费总和需要等于交易手续费"));
                }
                if(null!=entity.getSettFeeAmt()&&entity.getSettFeeAmt().compareTo(settFeeamtTotal)!=0){
                    errorList.add(new ValidMessage("组合分配结算服务费总和需要等于结算服务费"));
                }
            }
        }
        /*if(StringUtil.isNotEmpty(entity.getFinprodId())){
            FinProduct finProduct = finProductMapper.selectByPrimaryKey(entity.getFinprodId());
            if(StringUtil.equals(finProduct.getIsRight(),DictCodeList.是否_是.getValue())){
                Map<String,Object> param = new HashMap<>();
                if (null==entity.getTradeDate()){
                    param.put("sysDate", iPlatformDate.getSysDate());
                }else {
                    param.put("sysDate", entity.getTradeDate());
                }
                param.put("finprodId",entity.getFinprodId());
                //未来有行权计划
                if(cfetsIbuQuoteBondMapper.countBondHasEexrPlan(param)>0){
                    if(null==entity.getPrice()&&null==entity.getMatureYield()&&null==entity.getExerYield()){
                        errorList.add(new ValidMessage("请输入净价或者到期收益率或者行权收益率"));
                    }
                }
            }else{
                if(null==entity.getPrice()&&null==entity.getMatureYield()){
                    errorList.add(new ValidMessage("请输入净价或者到期收益率"));
                }
            }
            //锚定值不可为空
            if(StringUtil.isEmpty(entity.getCalculateField())){
                errorList.add(new ValidMessage("请输入锚定值(净价或者到期收益率或者行权收益率)"));
            }
        }*/
        ajaxResult.setErrorList(errorList);
        return ajaxResult;
    }

    public List<ValidMessage> valid(CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto){
        // 验证信息
        List<ValidMessage> validList = new ArrayList<ValidMessage>();
        String isDzh = cfetsBondDealRequestQuoteDto.getIsDzh();
        //该报价是否在流程中
        if(StringUtil.isNotEmpty(cfetsBondDealRequestQuoteDto.getQuoteId())){
            validList.addAll(isInWflProcess(cfetsBondDealRequestQuoteDto.getQuoteId(),cfetsBondDealRequestQuoteDto.getPageId(),cfetsBondDealRequestQuoteDto.getWorkflowId()));
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
                    /**
                     * 成交无需验证
                     */
                    if (!StringUtil.equals(cfetsBondDealRequestQuoteDto.getCheckType(), "answerClinchDeal")) {
                        Map<String, Object> param = new HashMap<>();
                        param.put("entrustId", cfetsBondDealRequestQuote.getEntrustId());
                        param.put("quoteId", cfetsBondDealRequestQuoteDto.getQuoteId());
                        //去掉本身流程中的数据  如退回再提交
                        param.put("workflowId",cfetsBondDealRequestQuoteDto.getWorkflowId());
                        BigDecimal wflEntrustDealedAmt = cfetsBondDealQuotedPrice002Service.getWflEntrustDealedAmt(param);
                        BigDecimal wflAmt = null==wflEntrustDealedAmt?BigDecimal.ZERO:ArithmeticUtil.divide(wflEntrustDealedAmt,new BigDecimal(10000),2);
                        if (!StringUtil.equals(CacheUtil.getSysParam("ibuFlag_bondOverEntrustAvaAmt"), "02")){
                            if(cfetsBondDealRequestQuote.getAvaNum().compareTo(ArithmeticUtil.subtract(cfetsBondDealRequestQuote.getEntrustAvaNum(),wflAmt))>0){
                                String wflTip = wflAmt.compareTo(BigDecimal.ZERO)!=0?"流程中占用额度:"+ArithmeticUtil.multiply(wflAmt,new BigDecimal(10000)):"";
                                validList.add(new ValidMessage("avaNum", this.getMessage("CFETS_COMMON_E_023")+wflTip));
                                return validList;
                            }
                        }
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
            BigDecimal dealedAmt = queryEntrustDealedAmt(param);
            //去掉本身流程中的数据  如退回再提交
            param.put("workflowId",cfetsBondDealRequestQuoteDto.getWorkflowId());
            //先处理单组合
            BigDecimal wflEntrustDealedAmt = cfetsBondDealQuotedPrice002Service.getWflEntrustDealedAmt(param);
            BigDecimal wflAmt = null==wflEntrustDealedAmt?BigDecimal.ZERO:wflEntrustDealedAmt;
            dealedAmt = ArithmeticUtil.doAdd(dealedAmt,wflAmt);

            // 查询库里最新的状态
            CfetsBondDealRequestQuoteDto dto = getBondRequestQuote(
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
                                        cfetsBondDealRequestQuoteDto.getEntrustAmt(),dealedAmt)) > 0) {
                            String wflTip = wflAmt.compareTo(BigDecimal.ZERO)!=0?"流程中占用额度:"+wflAmt:"";
                            validList.add(new ValidMessage(this.getMessage("CFETS_COMMON_E_021")+wflTip));
                            return validList;
                        }
                    }
                }
                // 银行间报价-现券买卖报价-超委托的价格校验开关（01 硬阻挡 02 软阻挡）
                if (!StringUtil.equals(CacheUtil.getSysParam("ibuFlag_bondOverEntrustPrice"), "02")) {
                    checkCalcFieldEntrust(cfetsBondDealRequestQuoteDto, validList, null, true);
                }
            }
            // 页面点击保存,提交校验
            else {
                if (!StringUtil.equals(CacheUtil.getSysParam("ibuFlag_bondOverEntrustAvaAmt"), "02")
                        && (cfetsBondDealRequestQuoteDto.getAvaNum() == null
                        || ArithmeticUtil.multiply(cfetsBondDealRequestQuoteDto.getAvaNum(),
                        new BigDecimal(10000)).compareTo(ArithmeticUtil.subtract(
                        cfetsBondDealRequestQuoteDto.getEntrustAmt(),dealedAmt)) > 0)) {
                    String wflTip = wflAmt.compareTo(BigDecimal.ZERO)!=0?"流程中占用额度:"+wflAmt:"";
                    validList.add(new ValidMessage("avaNum", this.getMessage("CFETS_COMMON_E_023")+wflTip));
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
                checkCalcFieldEntrust(cfetsBondDealRequestQuoteDto, validList, null, true);
            }
        }
    }

    /**
     * 根据委托代码查询委托基本信息
     * 
     * @param entrustId 委托代码
     * @return 委托基本信息
     * @see
     * @since 1.0
     */
    public CfetsBondDealRequestQuoteDto queryEntrustInfoByEntrustId(String entrustId){
        CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto = new CfetsBondDealRequestQuoteDto();
        Map<String, Object> param = new HashMap<String, Object>();
        // param.put("platDate", iPlatformDate.getSysDateStr());
        param.put("loginUserId", LoginUserUtil.getUserName());
        param.put("entrustId", entrustId);
        List<com.joyintech.service.bankuplink.bond.dto.CfetsBondDealEntrustOrderDto> pageList = cfetsBondDealQuotedPriceService.queryEntrustOrderList(param);
        /*List<CfetsBondDealEntrustOrderDto> pageList = restTemplate.postForList(
            ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsBondDealQuotedPrice/queryEntrustOrderList",
            param, CfetsBondDealEntrustOrderDto.class);*/
        
        if (pageList != null && !StringUtil.isEmptyList(pageList)){
            BeanUtil.copyPropertiesIgnoreNull(cfetsBondDealRequestQuoteDto, pageList.get(0));
        }
        
        // 清算速度
        if (StringUtil.equals(cfetsBondDealRequestQuoteDto.getSettleSpreed(), "0")){
            cfetsBondDealRequestQuoteDto.setSettleSpreed(BussinessDictCodeList.清算速度_T加0.getValue());
        }
        else if (StringUtil.equals(cfetsBondDealRequestQuoteDto.getSettleSpreed(), "1")){
            cfetsBondDealRequestQuoteDto.setSettleSpreed(BussinessDictCodeList.清算速度_T加1.getValue());
        }
        // 交割日期
        Date platDate = iPlatformDate.getSysDate();
        if (StringUtil.equals(cfetsBondDealRequestQuoteDto.getSettleSpreed(), BussinessDictCodeList.清算速度_T加1.getValue())){
            platDate = cfetsQuotedCommonService.getNextWorkDay(platDate);
        }
        cfetsBondDealRequestQuoteDto.setSettleDate(platDate);
        // 结算方式
        cfetsBondDealRequestQuoteDto.setSettleMethod(BussinessDictCodeList.结算方式_券款对付.getValue());
        // 清算类型
        cfetsBondDealRequestQuoteDto.setSettleType(BussinessDictCodeList.清算类型_全额清算.getValue());
        // 生效日期
        cfetsBondDealRequestQuoteDto.setEffDate(CacheUtil.getSysParam("ibuVar_quoteEffTime"));
        
        return cfetsBondDealRequestQuoteDto;
    }
    
    /**
     * 现券请求报价新增<br />
     * 请求报价信息入库
     * 
     * @param cfetsBondDealRequestQuoteDto 现券请求报价信息
     * @return boolean 是否成功
     */
    public boolean save(CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto){
        return cfetsBondDealQuotedPriceService.insertBondRequestQuote(JsonUtils.json2Object(JsonUtils.object2Json(cfetsBondDealRequestQuoteDto),com.joyintech.service.bankuplink.bond.dto.CfetsBondDealRequestQuoteDto.class)
        );
        /*return restTemplate.postForObject(
            ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsBondDealQuotedPrice/insertBondRequestQuote",
            cfetsBondDealRequestQuoteDto, boolean.class);*/
    }
    
    /**
     * 现券请求报价新增<br />
     * 请求报价信息入库
     * 
     * @param cfetsBondDealRequestQuoteDto 现券请求报价信息
     * @return boolean 是否成功
     */
    public boolean submit(CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto){
        com.joyintech.service.bankuplink.bond.dto.CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto1 = new com.joyintech.service.bankuplink.bond.dto.CfetsBondDealRequestQuoteDto();
        BeanUtil.copyPropertiesIgnoreNull(cfetsBondDealRequestQuoteDto1,cfetsBondDealRequestQuoteDto);
        return cfetsBondDealQuotedPriceService.sendBondRequestQuote(cfetsBondDealRequestQuoteDto1);
        /*return restTemplate.postForObject(
            ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsBondDealQuotedPrice/sendBondRequestQuote",
            cfetsBondDealRequestQuoteDto, boolean.class);*/
    }
    /**
     * 现券请求报价回复成交<br />
     * 请求报价信息入库
     * 
     * @param cfetsBondDealRequestQuoteDto 现券请求报价信息回复成交
     * @return boolean 是否成功
     */
    public boolean clinchDeal(CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto){
        com.joyintech.service.bankuplink.bond.dto.CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto1 = new com.joyintech.service.bankuplink.bond.dto.CfetsBondDealRequestQuoteDto();
        BeanUtil.copyPropertiesIgnoreNull(cfetsBondDealRequestQuoteDto1,cfetsBondDealRequestQuoteDto);
        return cfetsBondDealQuotedPriceService.sendBondRequestQuoteDeal(cfetsBondDealRequestQuoteDto1);
        /*return restTemplate.postForObject(
            ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsBondDealQuotedPrice/sendBondRequestQuoteDeal",
            cfetsBondDealRequestQuoteDto, boolean.class);*/
    }
    
    /**
     * 现券请求报价查询<br />
     * 根据主键查询请求报价信息
     * 
     * @param quoteId String 报价编号
     * @return BondDialogueQuoteDto
     */
	public CfetsBondDealRequestQuoteDto getBondRequestQuote(String quoteId, String statusValid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quoteId", quoteId);
		map.put("statusValid", statusValid);
		map.put("loginUserId", LoginUserUtil.getUserName());
        CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto = new CfetsBondDealRequestQuoteDto();
        com.joyintech.service.bankuplink.bond.dto.CfetsBondDealRequestQuoteDto bondRequestQuote = cfetsBondDealQuotedPriceService.getBondRequestQuote(map);
        BeanUtil.copyPropertiesIgnoreNull(cfetsBondDealRequestQuoteDto,bondRequestQuote);
        return cfetsBondDealRequestQuoteDto;
      /*  return restTemplate.postForObject(
				ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsBondDealQuotedPrice/getBondRequestQuote", map,
				CfetsBondDealRequestQuoteDto.class);*/
	}

    /**
     * 根据委托代码查询余额
     * @param map
     * @return
     */
    public BigDecimal queryEntrustDealedAmt(Map<String, Object> map) {
        return cfetsBondDealQuotedPriceService.queryEntrustDealedAmt(map);
        /*return restTemplate.postForObject(ServiceApplication.BANKUPLINK_SERVICE_NAME
                                          + "/CfetsBondDealQuotedPrice/queryEntrustDealedAmt",
            map, BigDecimal.class);*/
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doComplete(CfetsBondDealRequestQuoteDto entity, OperationType type) {
		// 历史报价撤销
        com.joyintech.service.bankuplink.bond.dto.CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto = preparEntity(entity);
        BondQuoteInfoDto bondQuoteInfoDto = new BondQuoteInfoDto();
        BeanUtil.copyPropertiesIgnoreNull(bondQuoteInfoDto,entity);
        if (StringUtil.equals(entity.getOptType(), "revoke")) {
			entity.setQuoteType(BussinessDictCodeList.报价方式_请求报价.getValue());
            cfetsBondDealQuotedPriceService.doHisRevoke(bondQuoteInfoDto);
			/*restTemplate.postForObject(
					ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsBondDealQuotedPrice/doHisRevoke", entity,
					Boolean.class);*/
		} else {
			// 成交
			if (StringUtil.equals(entity.getQuotaPageModel(), "response")) {
				if (StringUtil.equals(entity.getOperatorType(), "done")) {
                    cfetsBondDealQuotedPriceService.sendBondRequestQuoteDeal(cfetsBondDealRequestQuoteDto);
					/*restTemplate.postForObject(ServiceApplication.BANKUPLINK_SERVICE_NAME
							+ "/CfetsBondDealQuotedPrice/sendBondRequestQuoteDeal", entity, boolean.class);*/
				}
			} else if (StringUtil.equals(entity.getQuotaPageModel(), "new")) {
				// 新增
				if (StringUtil.equals(entity.getOperatorType(), "save")) {
					// 保存
                    cfetsBondDealQuotedPriceService.insertBondRequestQuote(cfetsBondDealRequestQuoteDto);
					/*restTemplate.postForObject(ServiceApplication.BANKUPLINK_SERVICE_NAME
							+ "/CfetsBondDealQuotedPrice/insertBondRequestQuote", entity, boolean.class);*/
				} else if (StringUtil.equals(entity.getOperatorType(), "submit")) {
					// 提交
                    cfetsBondDealQuotedPriceService.sendBondRequestQuote(cfetsBondDealRequestQuoteDto);
					/*restTemplate.postForObject(ServiceApplication.BANKUPLINK_SERVICE_NAME
							+ "/CfetsBondDealQuotedPrice/sendBondRequestQuote", entity, boolean.class);*/
				}
			} else {// 撤回
				entity.setQuoteType(BussinessDictCodeList.报价方式_请求报价.getValue());
                cfetsBondDealQuotedPriceService.doHisRevoke(bondQuoteInfoDto);
				/*restTemplate.postForObject(
						ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsBondDealQuotedPrice/doHisRevoke", entity,
						Boolean.class);*/
			}
		}
	}

    /**
     * 验证委托价格
     * @param cfetsBondDealRequestQuoteDto
     * @param validList
     * @param sb
     * @param sysParam
     */
    public void checkCalcFieldEntrust(CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto,
                                      List<ValidMessage> validList, StringBuffer sb,
                                      boolean sysParam) {
        EtrEntrust etrEntrust = etrEntrustMapper.selectByPrimaryKey(cfetsBondDealRequestQuoteDto.getEntrustId());
        EtrEntrustAdd etrEntrustAdd = etrEntrustAddMapper.selectByPrimaryKey(cfetsBondDealRequestQuoteDto.getEntrustId());
        // 勾选机构的券面总额应小于等于剩余委托券面总额
        List<CounterInfoDto> list = cfetsBondDealRequestQuoteDto.getCounterInfoList();
        if (!StringUtil.isEmptyList(list)) {
            CounterInfoDto counterInfoDto = list.get(0);
            
            // 净价、到期收益率 、行权收益率 小数处理
            if (etrEntrust != null) {
                etrEntrust.setPrice(ArithmeticUtil.round(etrEntrust.getPrice(), 4, RoundingMode.HALF_UP));
            }
            if (etrEntrustAdd != null) {
                etrEntrustAdd.setMatureYield(ArithmeticUtil.round(etrEntrustAdd.getMatureYield(), 4, RoundingMode.HALF_UP));
                etrEntrustAdd.setExerYield(ArithmeticUtil.round(etrEntrustAdd.getExerYield(), 4, RoundingMode.HALF_UP));
            }

            if (StringUtil.equals(cfetsBondDealRequestQuoteDto.getPs(),
                BussinessDictCodeList.交易方向_买入.getValue())) {
                if (etrEntrustAdd != null && StringUtil.equals(etrEntrustAdd.getCalMode(),
                    BussinessDictCodeList.计算选择方式_净价.getValue())) {
                    if (counterInfoDto.getUnitCprice() != null
                        && etrEntrust.getPrice() != null
                        && counterInfoDto.getUnitCprice().compareTo(
                            etrEntrust.getPrice()) > 0) {
                        if (sysParam) {
                            validList.add(new ValidMessage(this.getMessage("CFETS_COMMON_E_014")));
                        }
                        else {
                            sb.append(this.getMessage("CFETS_COMMON_E_014"));
                            sb.append(GlobalConstant.HTML_NEWLINE);
                        }
                    }
                }
                else if (etrEntrustAdd != null && StringUtil.equals(etrEntrustAdd.getCalMode(),
                    BussinessDictCodeList.计算选择方式_到期收益率.getValue())) {
                    if (counterInfoDto.getYield() != null
                        && etrEntrustAdd.getMatureYield() != null
                        && counterInfoDto.getYield().compareTo(
                        	ArithmeticUtil.multiply(etrEntrustAdd.getMatureYield(), BigDecimal.valueOf(100d))) < 0) {
                        if (sysParam) {
                            validList.add(new ValidMessage(this.getMessage("CFETS_COMMON_E_015")));
                        }
                        else {
                            sb.append(this.getMessage("CFETS_COMMON_E_015"));
                            sb.append(GlobalConstant.HTML_NEWLINE);
                        }
                    }
                }
                else if (etrEntrustAdd != null && StringUtil.equals(etrEntrustAdd.getCalMode(),
                    BussinessDictCodeList.计算选择方式_行权收益率.getValue())) {
                    if (counterInfoDto.getExerYield() != null
                        && etrEntrustAdd.getExerYield() != null
                        && counterInfoDto.getExerYield().compareTo(
                        	ArithmeticUtil.multiply(etrEntrustAdd.getExerYield(), BigDecimal.valueOf(100d))) < 0) {
                        if (sysParam) {
                            validList.add(new ValidMessage(this.getMessage("CFETS_COMMON_E_016")));
                        }
                        else {
                            sb.append(this.getMessage("CFETS_COMMON_E_016"));
                            sb.append(GlobalConstant.HTML_NEWLINE);
                        }
                    }
                }
            }
            else if (StringUtil.equals(cfetsBondDealRequestQuoteDto.getPs(),
                BussinessDictCodeList.交易方向_卖出.getValue())) {
                if (etrEntrustAdd != null && StringUtil.equals(etrEntrustAdd.getCalMode(),
                    BussinessDictCodeList.计算选择方式_净价.getValue())) {
                    if (counterInfoDto.getUnitCprice() != null
                        && etrEntrust.getPrice() != null
                        && counterInfoDto.getUnitCprice().compareTo(
                            etrEntrust.getPrice()) < 0) {
                        if (sysParam) {
                            validList.add(new ValidMessage(this.getMessage("CFETS_COMMON_E_017")));
                        }
                        else {
                            sb.append(this.getMessage("CFETS_COMMON_E_017"));
                            sb.append(GlobalConstant.HTML_NEWLINE);
                        }
                    }
                }
            }
        }
    }


    /**
     * 验证已占用额度
     * @param cfetsBondDealRequestQuoteDto
     * @return
     */
	public BigDecimal checkUsedShareAmt(CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("entrustId", cfetsBondDealRequestQuoteDto.getEntrustId());
		if(!StringUtil.isNotEmpty(cfetsBondDealRequestQuoteDto.getQuoteId())){
			param.put("quoteId", "");
		}else{
			param.put("quoteId", cfetsBondDealRequestQuoteDto.getQuoteId());
		}
        BigDecimal usedShareAmt = cfetsQuotedCommonServiceMicro.queryUsedShareAmt(param);
		/*BigDecimal usedShareAmt = restTemplate.postForObject(
				ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsQuotedCommon/queryUsedShareAmt",
				param, BigDecimal.class);*/
		return usedShareAmt == null ? BigDecimal.ZERO : usedShareAmt;
	}
	
    
    /**
	 * 根据实际指令类型来生成流程信息 <一句话功能简述> <功能详细描述>
	 * 
	 * @param dto
	 * @return
	 * @see
	 * @since 1.0
	 */
	public WorkFlowParameter<CfetsBondDealRequestQuoteDto> getWorkFlowParameter(CfetsBondDealRequestQuoteDto dto) {
		WorkFlowParameter<CfetsBondDealRequestQuoteDto> parameter = new WorkFlowParameter<CfetsBondDealRequestQuoteDto>();
		// TODO 根据实际指令类型来生成流程信息
		return parameter;
	}

    /**
     * 插入流程表数据
     * @param dto
     * @return
     */
	public Boolean insertWflBondRequestQuote(CfetsBondDealRequestQuoteDto dto) {
        return cfetsBondDealQuotedPriceService.insertWflBondRequestQuote(preparEntity(dto));
		/*return restTemplate.postForObject(
				ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsBondDealQuotedPrice/insertWflBondRequestQuote",
				dto, Boolean.class);*/
	}
	
    // add by etliu 20210309 银行间上下行多组合 start
    /**
     * 计算多组合组合分配列表中的全价总额
     * @param entity
     */
    private void calcListFpriceAmt(CfetsBondDealRequestQuoteDto entity) {
        if (StringUtil.isEmptyList(entity.getCfetsBondDealRequestQuoteDtoList())) {
            return;
        }
        
        for (CfetsBondDealRequestQuoteDto tempDataDto : entity.getCfetsBondDealRequestQuoteDtoList()) {
        }
    }
    // add by etliu 20210309 银行间上下行多组合 end
	
	/**
     * 插入操作
     */
    @Override
    public void doInsert(CfetsBondDealRequestQuoteDto entity,OperationType type){
        Map<String,Object> map = new HashMap<>();
        if ("save".equals(entity.getOperatorType())) {
            return;
        }
        entity.setWorkflowId(entity.getWorkflowId());
        insertWflBondRequestQuote(entity);
        sendPosition(entity,DictCodeListExt3.流程节点_流程提交.getValue(),handSystemAct(entity));
        //doInsetOrUpdateForPtlPositionHold(entity);
    }
    
	@Override
    public void doUpdate(CfetsBondDealRequestQuoteDto entity){
        Map<String,Object> map = new HashMap<>();
        if (!StringUtil.equals("revoke", entity.getOperatorType()) && !StringUtil.equals("delete", entity.getOperatorType())) {
            // 重新insert
        	insertWflBondRequestQuote(entity);
            try{
                sendPosition(entity,DictCodeListExt3.流程节点_流程提交.getValue(),handSystemAct(entity));
            }catch(PositionException e){
                e.printStackTrace(); Message message = PositionUtil.ajaxErrorReturn(e);
                throw new BusinessException("MSG:"+message.getMessage());
            }
            //doInsetOrUpdateForPtlPositionHold(entity);
            ProcessMark newEntity;
            try {
                newEntity = WorkFlowUtil.getBusinessObjectFromRequest(CfetsBondDealDialogueQuoteDto.class, true);
                // 工作流ID
                newEntity.setWorkflowId(entity.getWorkflowId());
                newEntity.setStatus(entity.getStatus());
                newEntity.setBusinessData(JsonUtils.object2Json(entity));
                // 更新业务表数据
                // 操作留痕表
                WorkflowContext.getProcessMarkService().insertIntoProcessMark(newEntity);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	
    /**
     * 退回
     * @param entity
     */
    @Override
	public void doBack(CfetsBondDealRequestQuoteDto entity){
		//回退释放头寸
    	//doDeletePtlPositionHold(entity.getWorkflowId());
        sendPosition(entity,DictCodeListExt3.流程节点_流程退回.getValue(),handSystemAct(entity));
    }
    
    /**
     * 删除操作
     */
    @Override
    public void doDelete(String workflowId) {
    	doDeletePtlPositionHold(workflowId);
        doDeleteWflBondDialogueQuote(workflowId);
    }
    
    /**
     * 删除工作流数据
     * @param workflowId
     */
    private void doDeleteWflBondDialogueQuote(String workflowId) {
        Map<String,Object> param = new HashMap<>();
        param.put("workflowId",workflowId);
        cfetsBondDealQuotedPriceService.doDeleteWflDataForBond(param);
        /*restTemplate.postForObject(ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsBondDealQuotedPrice/doDeleteWflDataForBond", param,
            Boolean.class);*/
    }
    
    
    
    /**
	 * 插入和修改时，指令持仓流水 、现金流水处理
	 * add by amyli 
	 * 20201230
	 * @param dto
	 */
	public void doInsetOrUpdateForPtlPositionHold(CfetsBondDealRequestQuoteDto dto) {
        cfetsBondDealQuotedPriceService.doInsetOrUpdateForPtlPositionHold(preparEntity(dto));
		/*restTemplate.postForObject(ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsBondDealQuotedPrice/doInsetOrUpdateRequestForPtlPositionHold", dto,
				Integer.class);*/
	}
	
	
    /**
     * 删除持仓占用
     * @param workflowId
     */
    private void doDeletePtlPositionHold(String workflowId) {
        Map<String,Object> param = new HashMap<>();
        param.put("workflowId",workflowId);
        param.put("pageWflType","BOND");
        cfetsBondDealQuotedPriceService.doDeletePtlPositionHold(param);
        /*restTemplate.postForObject(ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsBondDealQuotedPrice/doDeletePtlPositionHold", param,
            Boolean.class);*/
    }
    
    /**
     * 查询债券是否含权债
     * @param finprodId 金融产品代码
     * @return 是否含权债
     */
    public String selectAssetIsRight(String finprodId) {
        FinProduct finProduct = finProductMapper.selectByPrimaryKey(finprodId);
        if (finProduct != null) {
            return finProduct.getIsRight();
        }
        
        return DictCodeList.是否_否.getValue();
    }
    
    public List<CfetsBondDealRequestQuoteDto> getCfetsBondDealRequestQuoteDtoList(String quoteId) {
        List<CfetsBondDealRequestQuoteDto> result = new ArrayList<>();
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("quoteId", quoteId);
        map.put("loginUserId", LoginUserUtil.getUserName());
        List<com.joyintech.service.bankuplink.bond.dto.CfetsBondDealRequestQuoteDto> cfetsBondDealRequestQuoteDtoList = cfetsBondDealQuotedPriceService.getCfetsBondDealRequestQuoteDtoList(map);
        for (com.joyintech.service.bankuplink.bond.dto.CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto : cfetsBondDealRequestQuoteDtoList) {
            CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto1 = new CfetsBondDealRequestQuoteDto();
            BeanUtil.copyPropertiesIgnoreNull(cfetsBondDealRequestQuoteDto1,cfetsBondDealRequestQuoteDto);
            result.add(cfetsBondDealRequestQuoteDto1);
        }
        return result;
        /*return restTemplate.postForList(ServiceApplication.BANKUPLINK_SERVICE_NAME
                + "/CfetsBondDealQuotedPrice/getCfetsBondDealRequestQuoteDtoList",
                map, CfetsBondDealRequestQuoteDto.class);*/
	}
    
    /**
     *功能描述   check是否存在流程中
     * @author zhangnian
     * @date 2021/1/15 0015
     * @param  * @param quoteId
     * @return java.util.List<com.joyintech.framework.validation.ValidMessage>
     */
    public List<ValidMessage>  isInWflProcess(String quoteId,String pageId,String workflowId){
        //check流程中
        List<ValidMessage> validList = new ArrayList<ValidMessage>();
        WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
        wflValidInputParameter.setPageId(StringUtil.isNotEmpty(pageId)?pageId:"CfetsBondDealQuotedPrice003");
        //设置报价编号
        wflValidInputParameter.setBusinessDataPk1(quoteId);
        wflValidInputParameter.setWorkflowId(workflowId);
        if(StringUtil.isNotEmpty(mstWorkflowCommonServie)&&mstWorkflowCommonServie.isInWflProcess(wflValidInputParameter)){
            validList.add(new ValidMessage(getMessage("BUSS_COM_E_0023")));
        }
        return validList;
    }

    /**
     * 前端实体转微服务端
     * @param entity
     * @return
     */
    public com.joyintech.service.bankuplink.bond.dto.CfetsBondDealRequestQuoteDto preparEntity(CfetsBondDealRequestQuoteDto entity){
        com.joyintech.service.bankuplink.bond.dto.CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto = JsonUtils.json2Object(JsonUtils.object2Json(entity),com.joyintech.service.bankuplink.bond.dto.CfetsBondDealRequestQuoteDto.class);
        //处理下业务模式
        if(CollectionUtils.isNotEmpty(cfetsBondDealRequestQuoteDto.getCfetsBondDealRequestQuoteDtoList())) {
            Map<String, Object> entrustReclas = new HashMap<>();
            if (StringUtil.equals(entity.getPs(), DictCodeList.交易方向_买入.getValue())) {
                entrustReclas = cfetsQuotedCommonServicevue.isEntrustReclas(entity.getFinprodId(), entity.getPrice(), entity.getSettleDate());
            }
            for (com.joyintech.service.bankuplink.bond.dto.CfetsBondDealRequestQuoteDto item : cfetsBondDealRequestQuoteDto.getCfetsBondDealRequestQuoteDtoList()) {
                if (StringUtil.equals(item.getBusinessModel(), DictCodeListExt3.业务模式_收取合同约定的现金流.getValue()) && entrustReclas.containsKey("isEntrustReclas") && (boolean) entrustReclas.get("isEntrustReclas")) {
                    item.setInvAim(DictCodeListExt3.业务模式_出售.getValue());
                }
            }
        }
        return cfetsBondDealRequestQuoteDto;
    }

    /**
     * 通过报价编号   处理头寸需要的数据
     * @param quoteId
     * @return
     */
    public CfetsBondDealRequestQuoteDto handpositionByQuoteId(String quoteId){
        com.joyintech.tams.invest.bankuplink.bond.dto.CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto = getBondRequestQuote(quoteId,"01");
        cfetsBondDealRequestQuoteDto.setAvaNum(ArithmeticUtil.divide(cfetsBondDealRequestQuoteDto.getAvaNum(),new BigDecimal(10000),2));
        //组合分配列表
        List <CfetsBondDealRequestQuoteDto> cfetsBondDealRequestQuoteDtoList = getCfetsBondDealRequestQuoteDtoList(quoteId);
        for(CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuote:cfetsBondDealRequestQuoteDtoList){
            cfetsBondDealRequestQuote.setMatureYield(cfetsBondDealRequestQuoteDto.getMatureYield());
            cfetsBondDealRequestQuote.setExerYield(cfetsBondDealRequestQuoteDto.getExerYield());
        }
        cfetsBondDealRequestQuoteDto.setCfetsBondDealRequestQuoteDtoList(cfetsBondDealRequestQuoteDtoList);
       /* if(null!=bondRequestQuoteDto&&null!=bondRequestQuoteDto.getIbuQuoteBondDto()){
            IbuQuoteBondDto ibuQuoteBondDto = bondRequestQuoteDto.getIbuQuoteBondDto();
            positionDto.setQuoteId(ibuQuoteBondDto.getQuoteId());
            positionDto.setFinprodId(ibuQuoteBondDto.getFinprodId());
            positionDto.setPs(ibuQuoteBondDto.getPs());
            String settleSpreed = StringUtil.EMPTY;
            if (ibuQuoteBondDto.getSettleSpeed() == 0L) {
                settleSpreed =  BussinessDictCodeList.清算速度_T加0.getValue();
            } else if (ibuQuoteBondDto.getSettleSpeed() == 1L) {
                settleSpreed = BussinessDictCodeList.清算速度_T加1.getValue();
            } else if (ibuQuoteBondDto.getSettleSpeed() == 2L) {
                settleSpreed = BussinessDictCodeList.清算速度_T加2.getValue();
            }
            positionDto.setSettleSpreed(settleSpreed);
            positionDto.setCcy(ibuQuoteBondDto.getSettCcy());
        }*/
        return cfetsBondDealRequestQuoteDto;
    }

    /**
     * 处理系统动作
     * @param entity
     * @return
     */
    public String handSystemAct(CfetsBondDealRequestQuoteDto entity){
        String systemAct = StringUtil.EMPTY;
        if(StringUtil.equals(entity.getQuotaPageModel(), "new")){
            systemAct = DictCodeListExt3.系统动作_新建.getValue();
        }
        if(StringUtil.equals(entity.getQuotaPageModel(),"response")&&StringUtil.equals(entity.getOperatorType(), "done")){
            systemAct = DictCodeListExt3.系统动作_成交.getValue();
        }
        return systemAct;
    }

    public void sendPosition(CfetsBondDealRequestQuoteDto entity,String flowStatus,String systemAct){
        Map<String, Object> stringObjectMap = preparePosition(entity, flowStatus,systemAct);
        if(StringUtil.equals(flowStatus,DictCodeListExt3.流程节点_流程提交.getValue())||StringUtil.equals(flowStatus,DictCodeListExt3.流程节点_流程退回.getValue())){
            if(StringUtil.equals(systemAct,DictCodeListExt3.系统动作_新建.getValue())||StringUtil.equals(systemAct,DictCodeListExt3.系统动作_修改.getValue())||StringUtil.equals(systemAct,DictCodeListExt3.系统动作_成交.getValue())){
                PositionHttpRequest.send(stringObjectMap);
                return;
            }
        }
        if(StringUtil.equals(flowStatus,DictCodeListExt3.流程节点_审批生效.getValue())&&(StringUtil.equals(systemAct,DictCodeListExt3.系统动作_撤回.getValue())||StringUtil.equals(systemAct,DictCodeListExt3.系统动作_关闭.getValue()))){
            PositionHttpRequest.send(stringObjectMap);
            return;
        }
        if(StringUtil.equals(flowStatus,DictCodeListExt3.流程节点_流程提交.getValue())&&StringUtil.equals(systemAct,DictCodeListExt3.系统动作_退回.getValue())){
            PositionHttpRequest.send(stringObjectMap);
            return;
        }
    }


    /**
     * 处理头寸组装
     * @param dto
     * @param flowStatus
     * @return
     */
    public Map<String,Object> preparePosition(CfetsBondDealRequestQuoteDto dto,String flowStatus,String systemAct){
        com.joyintech.service.bankuplink.bond.dto.CfetsBondDealRequestQuoteDto entity = JsonUtils.json2Object(JsonUtils.object2Json(dto),com.joyintech.service.bankuplink.bond.dto.CfetsBondDealRequestQuoteDto.class);
        Date platDate = iPlatformDate.getSysDate();
        final Date settleDate = dto.getSettleDate() == null ? platDate : dto.getSettleDate();
        Map<String,Object> result = new HashMap<>();
        String ps = entity.getPs();
        result.put("interfaceType","Bond001");
        result.put("userId", SecurityUtil.getLoginUser().getUserName());
        result.put("totalTradeId",entity.getQuoteId());
        result.put("flowStatus",flowStatus);
        result.put("modelType", DictCodeListExt1.子系统代码_交易.getValue());
        result.put("balanceCheck","Y");
        if(StringUtil.equals(ps,BussinessDictCodeList.交易方向_行权回售.getValue())){
            result.put("busiType",DictCodeListExt3.头寸业务类型_债券行权交易.getValue());
        }else{
            result.put("busiType", DictCodeListExt3.头寸业务类型_现券交易.getValue());
        }
        result.put("busiAct","12");
        result.put("ps",ps);
        result.put("settleSpeed",entity.getSettleSpreed());
        result.put("tradePlace",BussinessDictCodeList.交易场所_银行间.getValue());
        result.put("systemAct",systemAct);
        result.put("cdate",DateUtil.format(settleDate));
        result.put("ccy",entity.getCcy());
        result.put("finprodId",entity.getFinprodId());
        result.put("bTotalTradeId",entity.getQuoteId());
        if(CollectionUtils.isNotEmpty(entity.getCfetsBondDealRequestQuoteDtoList())){
            //成交按回复方的价格计算现金
            if(CollectionUtils.isNotEmpty(entity.getCounterInfoList())){
                com.joyintech.service.bankuplink.bond.dto.CounterInfoDto counterInfoDto = entity.getCounterInfoList().get(0);
                if(null!=counterInfoDto.getUnitFprice()&&counterInfoDto.getUnitFprice().compareTo(BigDecimal.ZERO)!=0){
                    dto.setUnitFprice(counterInfoDto.getUnitFprice());
                }
            }
            List<Map<String,Object>> ptlList = new ArrayList<>();
            for (com.joyintech.service.bankuplink.bond.dto.CfetsBondDealRequestQuoteDto cfetsBondDealDialogueQuoteDto : entity.getCfetsBondDealRequestQuoteDtoList()) {
                Map<String,Object> pltMap = new HashMap<>();
                String portfolioId = cfetsBondDealDialogueQuoteDto.getPortfolioId();
                pltMap.put("portfolioId", portfolioId);
                pltMap.put("tradeId",entity.getQuoteId());
                pltMap.put("etrId",cfetsBondDealDialogueQuoteDto.getEntrustId());
                pltMap.put("cashAcctId",cfetsIbuQuoteBondMapper.getACCTNO(portfolioId,BussinessDictCodeList.资金账户类型_托管账户.getValue()));
                pltMap.put("secAcctId",cfetsIbuQuoteBondMapper.getSecAcctId(portfolioId,entity.getFinprodId(),entity.getTradeMarket()));
                pltMap.put("secAmount",ArithmeticUtil.multiply(cfetsBondDealDialogueQuoteDto.getAvaNum(),new BigDecimal(10000))); //券面总额
                if(null!=cfetsBondDealDialogueQuoteDto.getFpriceAmt()&&BigDecimal.ZERO.compareTo(cfetsBondDealDialogueQuoteDto.getFpriceAmt())!=0){
                    pltMap.put("cashAmount",cfetsBondDealDialogueQuoteDto.getFpriceAmt());//全价总额
                }else{
                    if(null!=dto.getUnitFprice()&&BigDecimal.ZERO.compareTo(dto.getUnitFprice())!=0){
                        pltMap.put("cashAmount",ArithmeticUtil.multiply(cfetsBondDealDialogueQuoteDto.getAvaNum(),dto.getUnitFprice(),new BigDecimal(100)).setScale(4,RoundingMode.DOWN));
                    }else{
                        pltMap.put("cashAmount",ArithmeticUtil.multiply(cfetsBondDealDialogueQuoteDto.getAvaNum(),new BigDecimal(10000)).setScale(4,RoundingMode.DOWN));
                    }
                }
                //pltMap.put("integrateSecAmount",cfetsBondDealDialogueQuoteDto.getAvaNum());
                //pltMap.put("ownSecAmount",cfetsBondDealDialogueQuoteDto.getAvaNum());
                pltMap.put("etrSecAmount",ArithmeticUtil.multiply(etrEntrustMapper.selectByPrimaryKey(cfetsBondDealDialogueQuoteDto.getEntrustId()).getEntrustNum(),new BigDecimal(100)));
                ptlList.add(pltMap);
            }
            result.put("ptlList",ptlList);
        }
        return result;
    }
}
