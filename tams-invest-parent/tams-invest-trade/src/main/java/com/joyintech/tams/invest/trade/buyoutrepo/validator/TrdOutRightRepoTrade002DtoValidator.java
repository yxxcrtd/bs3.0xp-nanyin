/*
 * 文件名：TrdOutRightRepoTrade002DtoValidator.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：zhangliang
 * 修改时间：2019年2月12日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.buyoutrepo.validator;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.trade.bond.dto.TrdBondInvestTradeDto;
import com.joyintech.tams.invest.trade.buyoutrepo.dto.TrdOutRightRepoTrade002Dto;
import com.joyintech.tams.invest.trade.buyoutrepo.dto.TrdOutRightRepoTradePtl;
import com.joyintech.tams.invest.trade.buyoutrepo.service.TrdOutRightRepoTrade002Service;
import com.joyintech.tams.invest.trade.common.dto.EtrInvestInfoDto;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.common.validator.TradeCommonValidator;
import com.joyintech.tams.invest.trade.trdrepotrade.service.TrdRepoTrade002Service;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 回购资产交易新建画面：TrdOutRightRepoTrade002DtoValidator
 *
 * @author zhangliang
 * @version 1.0 2019年02月12日
 * @see TrdOutRightRepoTrade002DtoValidator
 * @since 1.0
 */
@Component
public class TrdOutRightRepoTrade002DtoValidator implements TradeCommonValidator {

    /**
     * TrdRepoTrade002Service
     */
    @Autowired(required = false)
    private TrdRepoTrade002Service trdRepoTrade002Service;

    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private TrdOutRightRepoTrade002Service trdOutRightRepoTrade002Service;

    /**
     * BussTrdTransferOrderService
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;

    @Autowired
    private TradeCommonService tradeCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        TrdOutRightRepoTrade002Dto inputDto = (TrdOutRightRepoTrade002Dto)value;
        List<ValidMessage> errorList = new ArrayList<>();
        List<TrdOutRightRepoTradePtl> dtoListtotle = inputDto.getTrdOutRightRepoTradePtl();

        // 交易场所为银行间时，银行间成交编号必填
        /*
         * if (DictCodeListExt1.交易场所_银行间.getValue().equals(inputDto.getTradeMarket())) { if (!StringUtil.isNotEmpty(inputDto.getDealBussId())) { ValidMessage
         * validMessage = new ValidMessage("dealBussId", getMessage("BUSS_COM_E_0038", getMessage("TrdOutRightRepoTrade002.bussId")));
         * errorList.add(validMessage); } }
         */
        // 划款指令
        if (bussTrdTransferOrderService != null) {
            List<ValidMessage> list = bussTrdTransferOrderService.check(inputDto.getBussTrdTransferOrder());
            errorList.addAll(list);
        }

        if (inputDto.getTrdOutRightRepoTradePtl()==null) {
            if(StringUtil.isEmpty(inputDto.getPortfolioId())){
                errorList.add(new ValidMessage("portfolioId", "请输入组合代码。")); 
            }
            
            if (StringUtil.isEmpty(inputDto.getChlId()) && StringUtil.isEmpty(inputDto.getSecAcctId())) {
                errorList.add(new ValidMessage("chlId", "通道代码和证券账户不能同时为空！"));
            }
        }else{
            List<TrdOutRightRepoTradePtl> dtoList = inputDto.getTrdOutRightRepoTradePtl();
            for(int i=0;i<dtoList.size();i++){
               if(StringUtil.isEmpty(dtoList.get(i).getPortfolioId())){
                   errorList.add(new ValidMessage("portfolioId", "请输入组合代码。")); 
                   break;
               }
               
               if (StringUtil.isEmpty(inputDto.getChlId()) && StringUtil.isEmpty(dtoList.get(i).getSecManageAcctId())) {
                   errorList.add(new ValidMessage("chlId", "通道代码和证券账户不能同时为空！"));
                   break;
               }
            }
        }
        // 期限品种必输
        if (StringUtil.isEmpty(inputDto.getTermType())) {
            errorList.add(
                new ValidMessage("termType", getMessage("BUSS_COM_E_0038", getMessage("TrdOutRightRepoTrade002.termType"))));
        }
		
		// 画面.本金金额 〉 未完成委托额度 时，提示
//		if (inputDto.getSecTotalPrin() != null && inputDto.getEntrustInfo().getEntrustAmt() != null
//				&& inputDto.getSecTotalPrin().compareTo(inputDto.getEntrustInfo().getEntrustAmt().multiply(new BigDecimal(100))) > 0) {
//			ValidMessage validMessage = new ValidMessage("secTotalPrin",
//					getMessage("TRD_W_0009", getMessage("TrdOutRightRepoTrade002.amtInfo.bondTotalAmt")));
//			errorList.add(validMessage);
//		}
		//画面券面额度大于流程中与已完成额度  提示 add by wangpeng 20190906
        if (dtoListtotle==null) {
            if(inputDto.getSecTotalPrin() != null &&StringUtil.isNotEmpty(inputDto.getEntrustId())){
                BigDecimal entrustNum = tradeCommonService.getEntrustAmtByEntrustId(inputDto.getEntrustId());
                BigDecimal wflPrinAmt = tradeCommonService.getWflPrinAmt(inputDto.getEntrustId(), inputDto.getPortfolioId(), null);
                if(StringUtil.isNotEmpty(inputDto.getWorkflowId()) && !WorkFlowUtil.isNotFromToDoList()){
                    TrdOutRightRepoTrade002Dto dto = trdOutRightRepoTrade002Service.getBussinessEntityByWorkflowId(
                            inputDto.getWorkflowId());
                    wflPrinAmt = ArithmeticUtil.subtract(wflPrinAmt, ArithmeticUtil.divide(dto.getSecTotalPrin(),new BigDecimal(100)));
                }
                if(entrustNum.compareTo(wflPrinAmt.add(ArithmeticUtil.divide(inputDto.getSecTotalPrin(),new BigDecimal(100)))) == -1) {
                     ValidMessage validMessage = new ValidMessage("secTotalPrin",
                             "可用委托份额不足,当前最多可委托份额为:"+ entrustNum.subtract(wflPrinAmt).multiply(new BigDecimal(100)));
                         errorList.add(validMessage);
                }
            }
        }else{
            //买断式回购多组合委托添加 add by zhanggenglin 20200103 start
            for(int i=0;i<dtoListtotle.size();i++){
                if(dtoListtotle.get(i).getTradeAmt() != null &&StringUtil.isNotEmpty(dtoListtotle.get(i).getEntrustId())){
                    BigDecimal entrustNum = tradeCommonService.getEntrustAmtByEntrustId(dtoListtotle.get(i).getEntrustId());
                    BigDecimal wflPrinAmt = tradeCommonService.getWflPrinAmt(dtoListtotle.get(i).getEntrustId(), dtoListtotle.get(i).getPortfolioId(), null);
                    if(StringUtil.isNotEmpty(inputDto.getWorkflowId()) && !WorkFlowUtil.isNotFromToDoList()){
                        TrdOutRightRepoTrade002Dto dto = trdOutRightRepoTrade002Service.getBussinessEntityByWorkflowId(
                                inputDto.getWorkflowId());
                        wflPrinAmt = ArithmeticUtil.subtract(wflPrinAmt, ArithmeticUtil.divide(dtoListtotle.get(i).getTradeAmt() ,new BigDecimal(100)));
                    }
                    if(entrustNum.compareTo(wflPrinAmt.add(ArithmeticUtil.divide(dtoListtotle.get(i).getTradeAmt() ,new BigDecimal(100)))) == -1) {
                         ValidMessage validMessage = new ValidMessage("secTotalPrin",
                                 "可用委托份额不足,当前最多可委托份额为:"+ entrustNum.subtract(wflPrinAmt).multiply(new BigDecimal(100)));
                             errorList.add(validMessage);
                             break;
                    }
                }
            }
            //买断式回购多组合委托添加 add by zhanggenglin 20200103 end
        }
		
		//add  by wangpeng 20190906
		

        // 起息日不能大于到期日
        if (inputDto != null && inputDto.getVdate() != null && inputDto.getMdate() != null) {
            if (DateUtil.compareDate(inputDto.getVdate(), inputDto.getMdate()) > 0) {
                ValidMessage validMessage = new ValidMessage("vdate", getMessage("FW_E_0009",
                    getMessage("TrdOutRightRepoTrade002.vdate"), getMessage("TrdOutRightRepoTrade002.mdate")));
                errorList.add(validMessage);
            }
        }

        if (dtoListtotle==null) {
            if(StringUtil.isNotEmpty(inputDto.getEntrustId())){
                EtrInvestInfoDto  etrInvestInfoDto = tradeCommonService.findEtrInfo(inputDto.getEntrustId());
                //价格模式为限价模式
                //市价模式不校验
                if(StringUtil.equals(DictCodeListExt2.价格模式_限价指令.getValue(), etrInvestInfoDto.getEtrEntrust().getPriceModel())){
                    // 2) 交易方向为逆回购时，修改后的回购利率不能低于委托的回购利率；
                    if (inputDto != null && StringUtil.equals(inputDto.getPs(), DictCodeList.回购拆借方向_逆回购.getValue())) {
                        if (inputDto.getRepoRate() != null && inputDto.getEntrustInfo().getEntrustRepoRate() != null
                            && inputDto.getRepoRate().compareTo(inputDto.getEntrustInfo().getEntrustRepoRate()) < 0) {
                            ValidMessage validMessage = new ValidMessage("repoRate",
                                getMessage("TRD_W_0007", getMessage("TrdOutRightRepoTrade002.amtInfo.repoRate"),
                                    getMessage("TrdOutRightRepoTrade002.amtInfo.repoRate"), getMessage("TrdRepoTrade002.lessthan")));
                            errorList.add(validMessage);
                        }
                    }
                    // 3) 交易方向为正回购时，修改后的回购利率不能高于委托的回购利率；
                    else if (inputDto != null && StringUtil.equals(inputDto.getPs(), DictCodeList.回购拆借方向_正回购.getValue())) {
                        if (inputDto.getRepoRate() != null && inputDto.getEntrustInfo().getEntrustRepoRate() != null
                            && inputDto.getRepoRate().compareTo(inputDto.getEntrustInfo().getEntrustRepoRate()) > 0) {
                            ValidMessage validMessage = new ValidMessage("repoRate",
                                getMessage("TRD_W_0007", getMessage("TrdOutRightRepoTrade002.amtInfo.repoRate"),
                                    getMessage("TrdOutRightRepoTrade002.amtInfo.repoRate"), getMessage("TrdRepoTrade002.morethan")));
                            errorList.add(validMessage);
                        }
                    }
                    
                    //首次净价区间控制，回购交易时，首次净价要在委托的首次净价范围内；
                    if(StringUtil.isNotEmpty(inputDto.getfCprice())&&StringUtil.isNotEmpty(etrInvestInfoDto)
                            &&StringUtil.isNotEmpty(etrInvestInfoDto.getEtrEntrustAdd())
                            &&StringUtil.isNotEmpty(etrInvestInfoDto.getEtrEntrustAdd().getFirstUnitCprice())
                            &&StringUtil.isNotEmpty(etrInvestInfoDto.getEtrEntrustAdd().getFirstUnitCprice2())
                            && inputDto.getfCprice().compareTo(etrInvestInfoDto.getEtrEntrustAdd().getFirstUnitCprice()) < 0
                            && inputDto.getfCprice().compareTo(etrInvestInfoDto.getEtrEntrustAdd().getFirstUnitCprice2()) > 0){
                        ValidMessage validMessage = new ValidMessage("fCprice",
                                "价格模式为限价模式,首次净价要在委托的首次净价范围内!");
                            errorList.add(validMessage);
                    }
                }
                else if(StringUtil.equals(DictCodeListExt2.价格模式_指定价格.getValue(), etrInvestInfoDto.getEtrEntrust().getPriceModel())){
                    if (inputDto != null ) {
                        //  当价格模式为指定价格时，交易时均按照委托的回购利率进行交易；
                        if (inputDto.getRepoRate() != null && inputDto.getEntrustInfo().getEntrustRepoRate() != null
                                && inputDto.getRepoRate().compareTo(inputDto.getEntrustInfo().getEntrustRepoRate()) != 0) {
                            ValidMessage validMessage = new ValidMessage("repoRate",
                                    "价格模式为指定价格，交易时均按照委托的回购利率进行交易!");
                             errorList.add(validMessage);
                        }
                        //首次净价区间控制，回购交易时，首次净价要在委托的首次净价范围内；
                        if(StringUtil.isNotEmpty(inputDto.getfCprice())&&StringUtil.isNotEmpty(etrInvestInfoDto)
                                &&StringUtil.isNotEmpty(etrInvestInfoDto.getEtrEntrustAdd())
                                &&StringUtil.isNotEmpty(etrInvestInfoDto.getEtrEntrustAdd().getFirstUnitCprice())
                                &&StringUtil.isNotEmpty(etrInvestInfoDto.getEtrEntrustAdd().getFirstUnitCprice2())
                                && (inputDto.getfCprice().compareTo(etrInvestInfoDto.getEtrEntrustAdd().getFirstUnitCprice()) < 0
                                        || inputDto.getfCprice().compareTo(etrInvestInfoDto.getEtrEntrustAdd().getFirstUnitCprice2()) > 0)){
                            ValidMessage validMessage = new ValidMessage("repoRate",
                                    "价格模式为指定价格，首次净价应在委托的首次净价范围内!");
                             errorList.add(validMessage);
                        }
                    }
                }
                
            }
        }else{
            //买断式回购多组合委托添加 add by zhanggenglin 20200103 start
            for(int i=0;i<dtoListtotle.size();i++){
                if(StringUtil.isNotEmpty(dtoListtotle.get(i).getEntrustId())){
                    EtrInvestInfoDto  etrInvestInfoDto = tradeCommonService.findEtrInfo(dtoListtotle.get(i).getEntrustId());
                    //价格模式为限价模式
                    //市价模式不校验
                    if(StringUtil.equals(DictCodeListExt2.价格模式_限价指令.getValue(), etrInvestInfoDto.getEtrEntrust().getPriceModel())){
                        // 2) 交易方向为逆回购时，修改后的回购利率不能低于委托的回购利率；
                        if (inputDto != null && StringUtil.equals(inputDto.getPs(), DictCodeList.回购拆借方向_逆回购.getValue())) {
                            if (inputDto.getRepoRate() != null && inputDto.getEntrustInfo().getEntrustRepoRate() != null
                                && inputDto.getRepoRate().compareTo(inputDto.getEntrustInfo().getEntrustRepoRate()) < 0) {
                                ValidMessage validMessage = new ValidMessage("repoRate",
                                    getMessage("TRD_W_0007", getMessage("TrdOutRightRepoTrade002.amtInfo.repoRate"),
                                        getMessage("TrdOutRightRepoTrade002.amtInfo.repoRate"), getMessage("TrdRepoTrade002.lessthan")));
                                errorList.add(validMessage);
                                break;
                            }
                        }
                        // 3) 交易方向为正回购时，修改后的回购利率不能高于委托的回购利率；
                        else if (inputDto != null && StringUtil.equals(inputDto.getPs(), DictCodeList.回购拆借方向_正回购.getValue())) {
                            if (inputDto.getRepoRate() != null && inputDto.getEntrustInfo().getEntrustRepoRate() != null
                                && inputDto.getRepoRate().compareTo(inputDto.getEntrustInfo().getEntrustRepoRate()) > 0) {
                                ValidMessage validMessage = new ValidMessage("repoRate",
                                    getMessage("TRD_W_0007", getMessage("TrdOutRightRepoTrade002.amtInfo.repoRate"),
                                        getMessage("TrdOutRightRepoTrade002.amtInfo.repoRate"), getMessage("TrdRepoTrade002.morethan")));
                                errorList.add(validMessage);
                                break;
                            }
                        }
                        
                        //首次净价区间控制，回购交易时，首次净价要在委托的首次净价范围内；
                        if(StringUtil.isNotEmpty(inputDto.getfCprice())&&StringUtil.isNotEmpty(etrInvestInfoDto)
                                &&StringUtil.isNotEmpty(etrInvestInfoDto.getEtrEntrustAdd())
                                &&StringUtil.isNotEmpty(etrInvestInfoDto.getEtrEntrustAdd().getFirstUnitCprice())
                                &&StringUtil.isNotEmpty(etrInvestInfoDto.getEtrEntrustAdd().getFirstUnitCprice2())
                                && inputDto.getfCprice().compareTo(etrInvestInfoDto.getEtrEntrustAdd().getFirstUnitCprice()) < 0
                                && inputDto.getfCprice().compareTo(etrInvestInfoDto.getEtrEntrustAdd().getFirstUnitCprice2()) > 0){
                            ValidMessage validMessage = new ValidMessage("fCprice",
                                    "价格模式为限价模式,首次净价要在委托的首次净价范围内!");
                                errorList.add(validMessage);
                                break;
                        }
                    }
                    else if(StringUtil.equals(DictCodeListExt2.价格模式_指定价格.getValue(), etrInvestInfoDto.getEtrEntrust().getPriceModel())){
                        if (inputDto != null ) {
                            //  当价格模式为指定价格时，交易时均按照委托的回购利率进行交易；
                            if (inputDto.getRepoRate() != null && inputDto.getEntrustInfo().getEntrustRepoRate() != null
                                    && inputDto.getRepoRate().compareTo(inputDto.getEntrustInfo().getEntrustRepoRate()) != 0) {
                                ValidMessage validMessage = new ValidMessage("repoRate",
                                        "价格模式为指定价格，交易时均按照委托的回购利率进行交易!");
                                 errorList.add(validMessage);
                                 break;
                            }
                            //首次净价区间控制，回购交易时，首次净价要在委托的首次净价范围内；
                            if(StringUtil.isNotEmpty(inputDto.getfCprice())&&StringUtil.isNotEmpty(etrInvestInfoDto)
                                    &&StringUtil.isNotEmpty(etrInvestInfoDto.getEtrEntrustAdd())
                                    &&StringUtil.isNotEmpty(etrInvestInfoDto.getEtrEntrustAdd().getFirstUnitCprice())
                                    &&StringUtil.isNotEmpty(etrInvestInfoDto.getEtrEntrustAdd().getFirstUnitCprice2())
                                    && (inputDto.getfCprice().compareTo(etrInvestInfoDto.getEtrEntrustAdd().getFirstUnitCprice()) < 0
                                            || inputDto.getfCprice().compareTo(etrInvestInfoDto.getEtrEntrustAdd().getFirstUnitCprice2()) > 0)){
                                ValidMessage validMessage = new ValidMessage("repoRate",
                                        "价格模式为指定价格，首次净价应在委托的首次净价范围内!");
                                 errorList.add(validMessage);
                                 break;
                            }
                        }
                    }
                    
                }
            }
            //买断式回购多组合委托添加 add by zhanggenglin 20200103 end
        }
        
        
        
        // 进行验证：交易中回购期限应等于委托中回购期限，或在委托期限范围内，
        // 若不满足，最终无法提交，给出相应提示：“请输入合理的起息日和到期日”
       /* if (inputDto != null && inputDto.getRepoterm() != null) {
            if ((inputDto.getEntrustInfo().getEntrustTermStart() != null
                 && inputDto.getRepoterm() > inputDto.getEntrustInfo().getEntrustTermStart())) {
                ValidMessage validMessage = new ValidMessage("repoterm", getMessage("TRD_E_0006"));
                errorList.add(validMessage);
            }
        }*/

        if (DictCodeList.回购拆借方向_正回购.getValue().equals(inputDto.getPs()) && StringUtil.isNotEmpty(inputDto.getSecTotalPrin())) {
            if (inputDto.getBussTrdOutRightRepoTrade() != null
                && inputDto.getBussTrdOutRightRepoTrade().getAssetPosition() != null) {
                if (inputDto.getSecTotalPrin().compareTo(inputDto.getBussTrdOutRightRepoTrade().getAssetPosition()) > 0) {
                    ValidMessage validMessage = new ValidMessage("secTotalPrin",
                        getMessage("BUSS_COM_E_0003", getMessage("TrdOutRightRepoTrade002.amtInfo.bondTotalAmt"),
                            getMessage("TrdBondInvestTrade002.avaliblePosition")));
                    errorList.add(validMessage);
                }
            }
            if (inputDto.getTrdOutRightRepoTradePtl()!=null) {
                List<TrdOutRightRepoTradePtl> dtoList = inputDto.getTrdOutRightRepoTradePtl();
                for(int i=0;i<dtoList.size();i++){
                    if (dtoList.get(i).getAssetsavailablepositions()!=null&&dtoList.get(i).getTradeAmt().compareTo(dtoList.get(i).getAssetsavailablepositions()) > 0) {
                        ValidMessage validMessage = new ValidMessage("",
                            getMessage("BUSS_COM_E_0003", getMessage("TrdOutRightRepoTrade002.amtInfo.bondTotalAmt"),
                                getMessage("TrdBondInvestTrade002.avaliblePosition")));
                        errorList.add(validMessage);
                        break;
                    }
                }
            }
        }

        // 4)5) 本金金额高于副总裁/总裁助理相应资产的授权额度，则交易无法提交。
        Map<String, Object> params = new HashMap<>();
        params.put("ROLE_POST_01", DictCodeList.角色岗位_领导.getValue());
        params.put("PINAN_ASSET_TYPE_F07", DictCodeList.平安资产类型_质押式回购.getValue());
        params.put("assetSecType", DictCodeList.回购类型_买断式回购.getValue());
        params.put("tradeMarket", inputDto.getTradeMarket());
        params.put("tradeDirection", inputDto.getPs());
        params.put("loginUserName", LoginUserUtil.getUserName());
        BigDecimal vpOrPAuthAmount = this.trdRepoTrade002Service.getVPOrPAuthAmount(params);
        if (vpOrPAuthAmount != null && inputDto.getfSettleAmt() != null
            && inputDto.getfSettleAmt().compareTo(vpOrPAuthAmount) > 0) {
            ValidMessage validMessage = new ValidMessage("fSettleAmt",
                getMessage("TRD_W_0008", getMessage("TrdOutRightRepoTrade002.amtInfo.firstSettleAmt")));
            errorList.add(validMessage);
        }

        // 若正回购的委托信息中有到期日，需要检查所选任意一只质押券到期日是否都大于到期日；
        // 若不满足，给出提示：“所选质押券到期日不可小于等于回购到期日！”；若满足，则检查通过；（此处需求上为，不可小于回购到期日，经与金强确认，质押券到期日必须为回购到期日之后，回购交易的到期日当天也不行）
        if (DictCodeList.回购拆借方向_正回购.getValue().equals(inputDto.getPs()) && StringUtil.isNotEmpty(inputDto.getFinprodId())) {
            FinProduct finProduct = bussCommonMapperDelegateService.selectBySinglePrimaryKey(FinProductMapper.class,
                inputDto.getFinprodId(), FinProduct.class);
            if (finProduct != null) {
                if (inputDto.getMdate() != null
                    && (finProduct.getMdate() == null || finProduct.getMdate().compareTo(inputDto.getMdate()) <= 0)) {
                    errorList.add(new ValidMessage("mdate", getMessage("TRD_E_0009")));
                }
            }
        }
        //     附件上传改为非必填 by zhuyongkang 20191010 start
        // 1、  交易场所：银行间，必须上传成交单
//        if (DictCodeListExt1.交易场所_银行间.getValue().equals(inputDto.getTradeMarket())
//            && StringUtil.isEmptyList(inputDto.getUploadFileList())) {
//            ValidMessage validMessage = new ValidMessage(getMessage("FW_E_0006", getMessage("TrdRepoTrade002.fileCommon")));
//            errorList.add(validMessage);
//        }

        if (StringUtil.isNotEmpty(inputDto.getFinprodId()) && StringUtil.isNotEmpty(inputDto.getVdate())
            && StringUtil.isNotEmpty(inputDto.getMdate())) {
            if (trdOutRightRepoTrade002Service.checkDateInPayPlan(inputDto.getFinprodId(), inputDto.getVdate(),
                inputDto.getMdate()) == 0) {
                ValidMessage validMessage = new ValidMessage("finprodId", getMessage("TRD_E_0018"));
                errorList.add(validMessage);
            }
        }

        if (bussTrdTransferOrderService != null) {
            if (StringUtil.equals(inputDto.getIsGuarantee(), DictCodeList.是否_否.getValue())) {
                List<ValidMessage> list = bussTrdTransferOrderService.check(inputDto.getBussTrdTransferOrder());
                errorList.addAll(list);
            }
        }

        //add by msk TISHKBS-1038 新增一次性划款通道交易前是否划款判断 start
        if(!"".equals(inputDto.getChlId())&&inputDto.getChlId()!=null){
            Map<String,Object> sMap= new HashMap<String ,Object>();
            sMap.put("chlId",inputDto.getChlId());
            sMap.put("finprodId",inputDto.getPortfolioId());
            Integer count = trdOutRightRepoTrade002Service.selectchlTrade(sMap);
            if(count <= 0){
                ValidMessage validMessage = new ValidMessage(getMessage("通过一次性通道投资请先进行划款！",getMessage("invest.channelId")));
                errorList.add(validMessage);
            }
        }
        //add by msk TISHKBS-1038 新增一次性划款通道交易前是否划款判断 end


        if (StringUtil.isNotEmpty(inputDto.getBussTrdTransferOrder())){
            Map<String, List<BussTrdTransferOrderDto>> map = inputDto.getBussTrdTransferOrder().stream().collect(JoyinCollectors.groupingBy(item -> item.getPortfolioId()));
            if (map!=null && map.keySet().size()<inputDto.getBussTrdTransferOrder().size()){
                errorList.add(new ValidMessage("组合分配不能重复分配给同一组合。"));
            }
        }

        return errorList;
    }
}
