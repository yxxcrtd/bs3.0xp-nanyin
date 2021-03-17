/*
 * 文件名：TrdRepoTrade003DtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：张庚林
 * 修改时间：2019年7月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.trdrepotrade.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.component.business.trade.dto.BussTrdTransfenpeiDto;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.tams.invest.trade.common.validator.TradeCommonValidator;
import com.joyintech.tams.invest.trade.trdrepotrade.dto.TrdRepoTrade003Dto;
import com.joyintech.tams.invest.trade.trdrepotrade.service.TrdRepoTrade002Service;

/**
 * 回购资产交易新建画面：TrdRepoTrade003DtoValidator
 *
 * @author 裴家俊
 * @version 1.0 2018年11月1日
 * @see TrdRepoTrade003DtoValidator
 * @since 1.0
 */
@Component
public class TrdRepoTrade003DtoValidator implements TradeCommonValidator {

    /**
     * TrdRepoTrade002Service
     */
    @Autowired(required = false)
    private TrdRepoTrade002Service trdRepoTrade002Service;

    /**
     * BussTrdTransferOrderService
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        TrdRepoTrade003Dto inputDto = (TrdRepoTrade003Dto) value;
        List<ValidMessage> errorList = new ArrayList<>();

        // 交易场所为银行间时，银行间成交编号必填
//        if (DictCodeListExt1.交易场所_银行间.getValue().equals(inputDto.getTradeMarket())) {
//            if (!StringUtil.isNotEmpty(inputDto.getfTradeId())) {
//                ValidMessage validMessage = new ValidMessage("fTradeId",
//                        getMessage("BUSS_COM_E_0038",
//                                getMessage("TrdRepoTrade002.fTradeId")));
//                errorList.add(validMessage);
//            }
//        }
        // 划款指令
        if (bussTrdTransferOrderService != null) {
            List<ValidMessage> list = bussTrdTransferOrderService.check(
                    inputDto.getBussTrdTransferOrder());
            errorList.addAll(list);
        }
        //划款指令和多组合数目不一致
        /*if(bussTrdTransferOrderService != null){
            if(CollectionUtils.isNotEmpty(inputDto.getBussTrdTransferOrder())&&
                CollectionUtils.isNotEmpty(inputDto.getBussRepoSecPtl().getPledgeBondList()) &&
                    inputDto.getBussTrdTransferOrder().size()!=inputDto.getBussRepoSecPtl().getPledgeBondList().size()){
                ValidMessage validMessage  = new ValidMessage(BussTrdTransferOrderImplService.TABLE_NAME,
                        getMessage("FW_E_0006", getMessage("BussTrdTransferOrder")));
                errorList.add(validMessage);
            }
        }*/

        // 起息日不能大于到期日
        if (inputDto != null
                && inputDto.getVdate() != null
                && inputDto.getMdate() != null) {
            if (DateUtil.compareDate(inputDto.getVdate(),
                    inputDto.getMdate()) > 0) {
                ValidMessage validMessage = new ValidMessage("vdate",
                        getMessage("FW_E_0009", getMessage("TrdLendInvestTrade002.vdate"),
                                getMessage("TrdLendInvestTrade002.mdate")));
                errorList.add(validMessage);
            }
        }

        // 画面.本金金额 〉 未完成委托额度 时，提示
        if (inputDto.getPrinAmt() != null && inputDto.getEntrustAmt() != null
                && inputDto.getPrinAmt().compareTo(inputDto.getEntrustAmt()) > 0) {
            ValidMessage validMessage = new ValidMessage("prinAmt",
                    getMessage("TRD_W_0009", getMessage("TrdRepoTrade002.prinAmt")));
            errorList.add(validMessage);
        }

        // 2) 交易方向为逆回购时，修改后的回购利率不能低于委托的回购利率；
        if (inputDto != null
                && StringUtil.equals(inputDto.getPs(),
                DictCodeList.回购拆借方向_逆回购.getValue())) {
            if (inputDto.getRebackPrice() != null && inputDto.getEntrustPrice() != null
                    && inputDto.getRebackPrice().compareTo(inputDto.getEntrustPrice()) < 0) {
                ValidMessage validMessage = new ValidMessage("rebackPrice",
                        getMessage("TRD_W_0007", getMessage("TrdRepoTrade002.entrustRebackPrice"),
                                getMessage("TrdRepoTrade002.entrustRebackPrice"),
                                getMessage("TrdRepoTrade002.lessthan")));
                errorList.add(validMessage);
            }
        }
        // 3) 交易方向为正回购时，修改后的回购利率不能高于委托的回购利率；
        else if (inputDto != null
                && StringUtil.equals(inputDto.getPs(),
                DictCodeList.回购拆借方向_正回购.getValue())) {
            if (inputDto.getRebackPrice() != null && inputDto.getEntrustPrice() != null
                    && inputDto.getRebackPrice().compareTo(inputDto.getEntrustPrice()) > 0) {
                ValidMessage validMessage = new ValidMessage("rebackPrice",
                        getMessage("TRD_W_0007", getMessage("TrdRepoTrade002.entrustRebackPrice"),
                                getMessage("TrdRepoTrade002.entrustRebackPrice"),
                                getMessage("TrdRepoTrade002.morethan")));
                errorList.add(validMessage);
            }
        }
        // 进行验证：交易中回购期限应等于委托中回购期限，或在委托期限范围内，
        // 若不满足，最终无法提交，给出相应提示：“请输入合理的起息日和到期日”
        if (inputDto != null && inputDto.getTerm() != null) {
            if ((inputDto.getEntrustTermFrom() != null && inputDto.getTerm() > inputDto.getEntrustTermFrom())) {
                ValidMessage validMessage = new ValidMessage("term",
                        getMessage("TRD_E_0006"));
                errorList.add(validMessage);
            }
        }

        // 3) 当回购类型为质押式竞价回购，交易方向为正回购时，
        // 本次回购交易金额<=可使用标准券额度，严格限制，若不满足，则交易无法提交；
        if (inputDto != null && DictCodeList.回购类型_质押式竞价回购.getValue().equals(inputDto.getRepoType())
                && DictCodeList.回购拆借方向_正回购.getValue().equals(inputDto.getPs())) {
            List<PledgeBondInfoDto> pledgeBondList = inputDto.getBussRepoSecPtl().getPledgeBondList();
            List<PledgeBondInfoDto> avaPledgeBondDetailList = inputDto.getBussRepoSecPtl().getAvaPledgeBondDetailList();
            for (PledgeBondInfoDto pledgeBondInfoDto:pledgeBondList) {
                if(pledgeBondInfoDto.getAvaStandardStockPosition()==null||
                        (pledgeBondInfoDto.getPrinAmt()!=null&&pledgeBondInfoDto.getAvaStandardStockPosition()!=null
                            && pledgeBondInfoDto.getPrinAmt().compareTo(pledgeBondInfoDto.getAvaStandardStockPosition()) > 0)){
                    ValidMessage validMessage = new ValidMessage("prinAmt",
                            getMessage("TRD_E_0005"));
                    errorList.add(validMessage);
                }
            }
//            if (inputDto.getAvaStandardStockPosition() == null
//                    || (inputDto.getPrinAmt() != null && inputDto.getAvaStandardStockPosition() != null
//                    && inputDto.getPrinAmt().compareTo(inputDto.getAvaStandardStockPosition()) > 0)) {
//                ValidMessage validMessage = new ValidMessage("prinAmt",
//                        getMessage("TRD_E_0005"));
//                errorList.add(validMessage);
//            }
        }
        // 保存质押券时提示质押券登记托管机构不同，不允许保存；（子画面已作校验，此处不再处理）

        // 3) 正回购本金金额不能大于质押券信息中汇总券面总额，若大于，则提示：“正回购委托金额不能大于质押券汇总券面总额”（除11的其他正回购）
        List<PledgeBondInfoDto> PledgeBondList=inputDto.getBussRepoSecPtl().getPledgeBondList();
        List<PledgeBondInfoDto> PledgeBondListDetailed=inputDto.getBussRepoSecPtl().getPledgeBondListDetailed();
        for(int i=0;i<PledgeBondList.size();i++){
            int k=0;
            for(int j=0;j<PledgeBondListDetailed.size();j++){
                if(PledgeBondList.get(i).getPortfolioId().equals(PledgeBondListDetailed.get(j).getPortfolioId())){
                    k=k+1;
                    if (DictCodeList.回购拆借方向_正回购.getValue().equals(inputDto.getPs())
                        && DictCodeList.回购类型_质押式协议回购.getValue().equals(inputDto.getRepoType())
                        && inputDto.getPrinAmt() != null
                        && (inputDto.getBussRepoSecPtl() == null
                        || inputDto.getBussRepoSecPtl().getPledgeBondListDetailed().get(j) == null)) {
                    ValidMessage validMessage = new ValidMessage("prinAmt",
                            getMessage("TRD_E_00121"));
                        errorList.add(validMessage);
                        break;
                    }
                }
            }
//            if(k==0){//标准券的时候判断
//                ValidMessage validMessage = new ValidMessage("prinAmt",
//                    getMessage("TRD_E_00121"));
//                errorList.add(validMessage);
//                break;
//            }
        }

        // 4)5) 本金金额高于副总裁/总裁助理相应资产的授权额度，则交易无法提交。
//        Map<String, Object> params = new HashMap<>();
//        params.put("ROLE_POST_01", DictCodeList.角色岗位_领导.getValue());
//        params.put("PINAN_ASSET_TYPE_F07", DictCodeList.平安资产类型_质押式回购.getValue());
//        params.put("assetSecType", inputDto.getRepoType());
//        params.put("tradeMarket", inputDto.getTradeMarket());
//        params.put("tradeDirection", inputDto.getPs());
//        params.put("loginUserName", LoginUserUtil.getUserName());
        
        String counterId = inputDto.getCounterId();
        BigDecimal prinAmt = inputDto.getPrinAmt();
        String 平安资产类型 = DictCodeList.平安资产类型_质押式回购.getValue();
        String tradeMarket = inputDto.getTradeMarket();
        String ps = inputDto.getPs();
        String finprodId = null;
        //多组合时用最大的券面总额
        if (null != inputDto.getMaxTradeamt()){
            prinAmt = inputDto.getMaxTradeamt();
        }
        boolean isAuthorizationSoftcontrol = Boolean.parseBoolean(CacheUtil.getSysParam("IS_AUTHORIZATION_SOFTCONTROL"));
        // 授权额度检查（当本金金额高于副总裁/总裁助理相应资产的授权额度时，提示消息）
        if (!isAuthorizationSoftcontrol && prinAmt != null) {
            CommonBusinessService commonBusinessService = SpringContextUtil.getBean(
                CommonBusinessService.class);
            Map<String, Object> params = CommonBusinessService.setParams(counterId, prinAmt, LoginUserUtil.getUserName(),
                    平安资产类型, null, tradeMarket, ps,finprodId, null);
            if (commonBusinessService != null && commonBusinessService.是否超过副总额额度(params)) {
                ValidMessage validMessage = new ValidMessage(getMessage("TRD_E_0016", "副总裁/总裁助理"));
                errorList.add(validMessage);
            }
        }
        
//        BigDecimal vpOrPAuthAmount = this.trdRepoTrade002Service.getVPOrPAuthAmount(params);
//        if (vpOrPAuthAmount != null && inputDto.getPrinAmt() != null
//                && inputDto.getPrinAmt().compareTo(vpOrPAuthAmount) > 0) {
//            ValidMessage validMessage = new ValidMessage("prinAmt",
//                    getMessage("TRD_W_0008", getMessage("TrdRepoTrade002.prinAmt")));
//            errorList.add(validMessage);
//        }

        // 若正回购的委托信息中有到期日，需要检查所选任意一只质押券到期日是否都大于到期日；
        // 若不满足，给出提示：“所选质押券到期日不可小于等于回购到期日！”；若满足，则检查通过；（此处需求上为，不可小于回购到期日，经与金强确认，质押券到期日必须为回购到期日之后，回购交易的到期日当天也不行）
        if (DictCodeList.回购拆借方向_正回购.getValue().equals(inputDto.getPs())
                && inputDto.getBussRepoSecPtl() != null) {
            if (!StringUtil.isEmptyList(inputDto.getBussRepoSecPtl().getPledgeBondListDetailed())) {
                boolean isHaveExpiredBond = false;
                for (PledgeBondInfoDto pbi : inputDto.getBussRepoSecPtl().getPledgeBondListDetailed()) {
                	// Add 20200407 fix TISHKBS-86 start
                	Map<String, Object> paramSec = new HashMap<>();
                	paramSec.put("FINPROD_MARKET_ID", pbi.getBondCode());
                    paramSec.put("FINPROD_TYPE2", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
                	FinProduct finProduct = trdRepoTrade002Service.querySecinfo(paramSec);
                	if (finProduct != null) {
                		pbi.setMdate(finProduct.getMdate());
                	}
                	// Add 20200407 fix TISHKBS-86 end
                    if (inputDto.getMdate() != null
                            && (pbi.getMdate() == null
                            || pbi.getMdate().compareTo(inputDto.getMdate()) <= 0)) {
                        isHaveExpiredBond = true;
                        break;
                    }
                }
                if (isHaveExpiredBond) {
                    ValidMessage validMessage = new ValidMessage("mdate",
                            getMessage("TRD_E_0009"));
                    errorList.add(validMessage);
                }
            }
        }
        if(StringUtil.isEmpty(inputDto.getChlId())){
        	if(StringUtil.isEmptyList(inputDto.getBussRepoSecPtl().getPledgeBondList())){
        		errorList.add(new ValidMessage("chlId","通道代码和证券账户不能同时为空！"));
        	}
        }
        //if (StringUtil.isEmpty(inputDto.getChlId()) && StringUtil.isEmpty(inputDto.getBussRepoSecPtl().getPledgeBondList().get(0).getSecAcctId())) {
            //errorList.add(new ValidMessage("chlId","通道代码和证券账户不能同时为空！"));
        //}
//     附件上传改为非必填 by zhuyongkang 20191010 start
        // 1、  交易场所：银行间，必须上传成交单
//        if (DictCodeListExt1.交易场所_银行间.getValue().equals(inputDto.getTradeMarket())
//                && StringUtil.isEmptyList(inputDto.getUploadFileList())) {
//            ValidMessage validMessage = new ValidMessage(getMessage("FW_E_0006", getMessage("TrdRepoTrade002.fileCommon")));
//            errorList.add(validMessage);
//        }
        if (bussTrdTransferOrderService != null) {
            if (StringUtil.equals(inputDto.getIsGuarantee(), DictCodeList.是否_否.getValue())) {
                List<ValidMessage> list = bussTrdTransferOrderService.check(
                        inputDto.getBussTrdTransferOrder());
                errorList.addAll(list);
            }
        }
        // add by 裴家俊 2019-05-23--start----
        if(StringUtil.equals(inputDto.getOperateType(), "save") || StringUtil.equals(inputDto.getOperateType(), "submit")){
            if(inputDto.getTrdInvestPersonInfo() != null && StringUtil.isEmpty(inputDto.getTrdInvestPersonInfo().getCounterId())){
                ValidMessage validMessage = new ValidMessage("trdInvestPersonInfo.counterId",getMessage("FW_E_0006", getMessage("TrdDepositInvestTrade002.cno")));
                errorList.add(validMessage);
            }
            if(inputDto.getCalendarId() == null && StringUtil.isEmpty(inputDto.getCalendarId())){
                ValidMessage validMessage = new ValidMessage("TrdRepoTrade002.calendarId",getMessage("BUSS_COM_E_0038", getMessage("TrdRepoTrade002.calendarId")));
                errorList.add(validMessage);
            }
        }
        // add by 裴家俊 2019-05-23--end----

        //add by msk TISHKBS-1038 新增一次性划款通道交易前是否划款判断 start
        if(!"".equals(inputDto.getChlId())&&inputDto.getChlId()!=null){
            Map<String,Object> sMap= new HashMap<String ,Object>();
            sMap.put("chlId",inputDto.getChlId());
            sMap.put("finprodId",inputDto.getPortfolioId());
            Integer count = trdRepoTrade002Service.selectchlTrade(sMap);
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
