package com.joyintech.tams.invest.trade.projectnet.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.common.validator.TradeCommonValidator;
import com.joyintech.tams.invest.trade.projectnet.dto.TrdProjectNetInvestTradeDto;
import com.joyintech.tams.invest.trade.projectnet.service.TrdProjectNetInvestTrade001Service;
import com.joyintech.tams.invest.trade.projectnet.service.TrdProjectNetInvestTrade002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TrdProjectNetInvestTradeValidator implements TradeCommonValidator {
    @Autowired
    private TrdProjectNetInvestTrade001Service trdProjectNetInvestTrade001Service;

    @Autowired
    private TrdProjectNetInvestTrade002Service trdProjectNetInvestTrade002Service;

    /**
     * BussTrdTransferOrderService
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    @Autowired
    private TradeCommonService tradeCommonService;

    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
        TrdProjectNetInvestTradeDto dto = (TrdProjectNetInvestTradeDto)value;
        if (StringUtil.equals(dto.getOperateType(), "revoke")){
            TrdProjectNetInvestTradeDto checkDto = dto.getSelectedItem().get(0);
            WflValidInputParameter param = new WflValidInputParameter();
            if (!WorkFlowUtil.isNotFromToDoList()) {
                param.setWorkflowId(checkDto.getWorkflowId());//去除自身这个流程
            }
            param.setBusinessDataPk1(checkDto.getTradeId());
            if(StringUtil.equals(checkDto.getPs(), DictCodeList.交易方向_申购确认.getValue())
            ||StringUtil.equals(checkDto.getPs(), DictCodeList.交易方向_赎回确认.getValue())){
                param.setPageId("TrdProjectNetInvestTrade003");
            }else{
                param.setPageId("TrdProjectNetInvestTrade002");
            }
            if (null != mstWorkflowCommonService &&
                    mstWorkflowCommonService.isInWflProcess(param)) {
                errorList.add(
                        new ValidMessage(this.getMessage("COMMON_E_0012", checkDto.getTradeId())));
            }
        }
        //交易对手必填
        if (!StringUtil.equals(dto.getOperateType(), "revoke") && !StringUtil.equals(dto.getOperateType(), "confirm")
                && (dto.getTrdInvestPersonInfo() == null || StringUtil.isEmpty(dto.getTrdInvestPersonInfo().getCounterId()))) {
            errorList.add(new ValidMessage("trdInvestPersonInfo.counterId", this.getMessage("BUSS_COM_E_0038", this.getMessage("交易对手"))));
            return errorList;
        }
        if (StringUtil.equals(dto.getOperateType(), "save")) {
            if (StringUtil.isNotEmpty(dto.getPs())) {
                if((StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())
                        ||StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回.getValue()))){
                    if(null==dto.getPurCfmDate()){
                        errorList.add(new ValidMessage("purCfmDate",
                                getMessage("COMMON_E_0057", getMessage("TrdFundInvestTrade002.purCfmDate"))));
                    }
                }
                BigDecimal shareAmt = dto.getShareAmt();
                BigDecimal tradeAmt = dto.getTradeAmt();
                String entrustId = dto.getEntrustId();
                String tradeId =
                        dto.getTradeId();
                if (StringUtil.isNotEmpty(entrustId)) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("entrustId", entrustId);
                    param.put("tradeId", tradeId);
                    Map<String, Object> resultMap = trdProjectNetInvestTrade002Service.selectEntrustAmt(param);
                    if (resultMap != null) {
                        BigDecimal entrustAmt = new BigDecimal((String)resultMap.get("ENTRUST_AMT"));
                        BigDecimal entrustNum = new BigDecimal((String) resultMap.get("ENTRUST_NUM"));
                        BigDecimal finishAmt = new BigDecimal((String)resultMap.get("FINISH_AMT"));
                        BigDecimal prinAmt = new BigDecimal((String)resultMap.get("PRIN_AMT"));
                        if (finishAmt != null) {
                            entrustAmt = entrustAmt.subtract(finishAmt);
                        }
                        if (prinAmt != null) {
                            entrustAmt = entrustAmt.subtract(prinAmt);
                        }
                        if (StringUtil.isNotEmpty(tradeAmt)&&tradeAmt.compareTo(entrustAmt) == 1) {
                            errorList.add(new ValidMessage("tradeAmt", this.getMessage("TRD_X_0004") + entrustAmt));
                        }
                        if (StringUtil.isNotEmpty(shareAmt)&&shareAmt.compareTo(entrustNum) == 1) {
                            errorList.add(new ValidMessage("shareAmt", this.getMessage("TRD_X_0010") + entrustNum));
                        }
                    }
                }
                // 验证流程中是否存在该委托
                if(StringUtil.isNotEmpty(entrustId) && WorkFlowUtil.isNotFromToDoList()){
                    String str = tradeCommonService.check(entrustId, "TrdPlanNetInvestTrade002");
                    if(StringUtil.isNotEmpty(str)){
                        ValidMessage validMessage = new ValidMessage(str);
                        errorList.add(validMessage);
                    }
                }
                BigDecimal zero = new BigDecimal(0);

                if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())) {
                    if (tradeAmt == null) {
                        errorList.add(new ValidMessage("tradeAmt", this.getMessage("BUSS_COM_E_0038", this.getMessage("TrdFundInvestTrade002.tradeAmt"))));
                    }
                }
                else if (StringUtil.equals(dto.getPs(),
                        DictCodeList.交易方向_赎回.getValue())) {
                    if (shareAmt == null && tradeAmt == null) {
                        errorList.add(new ValidMessage("shareAmt", this.getMessage("BUSS_COM_E_0038", this.getMessage("TrdPlanNetInvestTrade001.shareAmt"))));
                    }
                }
                if (tradeAmt != null && tradeAmt.compareTo(zero) <=0) {
                    errorList.add(new ValidMessage("tradeAmt", this.getMessage("BUSS_COM_E_0065", this.getMessage("TrdFundInvestTrade002.tradeAmt"),"0")));
                }
                if (shareAmt != null && shareAmt.compareTo(zero) <=0) {
                    errorList.add(new ValidMessage("shareAmt", this.getMessage("BUSS_COM_E_0065", this.getMessage("TrdFundInvestTrade002.tradeSha"),"0")));
                }
                if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回.getValue())) {
                    BigDecimal assetAvailablePosition = dto.getAssetAvailablePosition();
                    if (assetAvailablePosition != null && shareAmt != null
                            && shareAmt.compareTo(assetAvailablePosition) == 1) {
                        errorList.add(new ValidMessage("shareAmt", this.getMessage("BUSS_COM_E_0003", this.getMessage("TrdFundInvestTrade002.tradeSha"),this.getMessage("TrdFundInvestTrade002.assetAvailablePosition"))));
                    }
                }
                /*if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())
                        && StringUtil.isEmptyList(dto.getBussTrdTransferOrder())) {
                    errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0038",
                            getMessage("TrdFundInvestTrade002.transOrder"))));
                }*/
                if ((StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())
                        || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())
                        || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue()))
                        && StringUtil.isEmptyList(dto.getBussTrdTransferOrder())) {
                    // 划款指令
                    if (bussTrdTransferOrderService != null) {
                        List<ValidMessage> list = bussTrdTransferOrderService.check(
                                dto.getBussTrdTransferOrder());
                        errorList.addAll(list);
                    }
                }
            }
            //     附件上传改为非必填 by zhuyongkang 20191010 start
//            if (dto.getFileArray() == null || dto.getFileArray().size() <= 0) {
//                errorList.add(new ValidMessage(
//                    getMessage("BUSS_COM_E_0038", getMessage("TrdFundInvestTrade002.file"))));
//            }

//            if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())){ 内部交易只能卖出
//                PtlSecPositionFlowDto ptlSecPositionFlowDto = new PtlSecPositionFlowDto();
//                ptlSecPositionFlowDto.setCdate(dto.getTradeDate());
//                ptlSecPositionFlowDto.setPortfolioId(dto.getTrdInvestPersonInfo().getCounterId());
//                ptlSecPositionFlowDto.setSecAcctId(dto.getChlId());
//                ptlSecPositionFlowDto.setFinprodId(dto.getFinprodId());
//                BigDecimal assetPosition = trdPlanNetInvestTrade002Service.getPtlSecPosition(ptlSecPositionFlowDto);
//                //资产可用持仓小于交易份额
//                if (StringUtil.isNotEmpty(dto.getShareAmt())) {
//                    if (assetPosition.compareTo(dto.getShareAmt()) == -1) {
//                        errorList.add(new ValidMessage("",
//                                getMessage("BUSS_COM_E_0003",
//                                        ptlSecPositionFlowDto.getPortfolioId() + "  " + getMessage("TrdPlanNetInvestTrade001.shareAmt"),
//                                        getMessage("TrdFundInvestTrade002.assetAvailablePosition"))));
//                    }
//                }
//
//                //同时验证买入方是否有足够的现金
//                Map<String, Object> param = new HashMap<>();
//                param.put("portfolioId", dto.getPortfolioId());
//                param.put("ccy", dto.getCcy());
//                param.put("vdate", DateUtil.format(dto.getTradeDate()));
//                param.put("calId", dto.getCalendarId());
//                param.put("chlId", dto.getChlId());
//                Map<String, Object> para = trdPlanNetInvestTrade002Service.getProAvailableCash(param);
//                BigDecimal accCash = new BigDecimal((String)para.get("cashT2"));
//                if(accCash.compareTo(dto.getTradeAmt())==-1){
//                    errorList.add(new ValidMessage("",
//                        getMessage("BUSS_COM_E_0003",
//                        param.get("portfolioId")+"  "+getMessage("TrdPlanNetInvestTrade002.tradeAmt"),
//                        getMessage("TrdFundInvestTrade002.accAvailableCash"))));
//                }
//            }else
            if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())){
                if (dto.getShareAmt() == null) {
                    errorList.add(new ValidMessage("shareAmt", this.getMessage("BUSS_COM_E_0038", this.getMessage("TrdPlanNetInvestTrade003.tradeSha"))));
                }
                Map<String, Object> param = new HashMap<>();
                param.put("portfolioId", dto.getTrdInvestPersonInfo().getCounterId());
                param.put("ccy", dto.getCcy());
                param.put("vdate", DateUtil.format(dto.getTradeDate()));
                param.put("calId", dto.getCalendarId());
                param.put("chlId", dto.getChlId());
                Map<String, Object> para = trdProjectNetInvestTrade002Service.getProAvailableCash(param);
                BigDecimal accCash = new BigDecimal((String)para.get("cashT2"));
                //交易对手没有足够的现金
                if(accCash.compareTo(dto.getTradeAmt())==-1){
                    errorList.add(new ValidMessage("",
                            getMessage("BUSS_COM_E_0003",
                                    param.get("portfolioId")+"  "+getMessage("TrdPlanNetInvestTrade002.tradeAmt"),
                                    getMessage("TrdFundInvestTrade002.accAvailableCash"))));
                }

                //同时验证卖出方是否有足够的持仓
                PtlSecPositionFlowDto ptlSecPositionFlowDto = new PtlSecPositionFlowDto();
                ptlSecPositionFlowDto.setCdate(dto.getTradeDate());
                ptlSecPositionFlowDto.setPortfolioId(dto.getPortfolioId());
                ptlSecPositionFlowDto.setSecAcctId(dto.getChlId());
                ptlSecPositionFlowDto.setFinprodId(dto.getFinprodId());
                BigDecimal assetPosition = trdProjectNetInvestTrade002Service.getPtlSecPosition(ptlSecPositionFlowDto);
                if (StringUtil.isNotEmpty(dto.getShareAmt())) {
                    if (assetPosition.compareTo(dto.getShareAmt()) == -1) {
                        errorList.add(new ValidMessage("",
                                getMessage("BUSS_COM_E_0003",
                                        ptlSecPositionFlowDto.getPortfolioId() + "  " + getMessage("TrdPlanNetInvestTrade001.shareAmt"),
                                        getMessage("TrdFundInvestTrade002.assetAvailablePosition"))));
                    }
                }
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "submit")) {
            if (StringUtil.isNotEmpty(dto.getPs())) {
                BigDecimal shareAmt = dto.getShareAmt();
                BigDecimal tradeAmt = dto.getTradeAmt();
                String entrustId = dto.getEntrustId();
                String tradeId = dto.getTradeId();
                if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue()) || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回.getValue())) {
                    if (!StringUtil.isNotEmpty(dto.getSettleSpreed())) {
                        errorList.add(new ValidMessage("settleSpreed", this.getMessage("BUSS_COM_E_0038", this.getMessage("TrdFundInvestTrade002.settleSpreed"))));
                    }
                    if(null==dto.getPurCfmDate()){
                        errorList.add(new ValidMessage("purCfmDate",
                                getMessage("COMMON_E_0057", getMessage("TrdFundInvestTrade002.purCfmDate"))));
                    }
                }
                if (StringUtil.isNotEmpty(entrustId)) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("entrustId", entrustId);
                    param.put("tradeId", tradeId);
                    Map<String, Object> resultMap = trdProjectNetInvestTrade002Service.selectEntrustAmt(param);
                    if (resultMap != null) {
                        BigDecimal entrustAmt = new BigDecimal((String) resultMap.get("ENTRUST_AMT"));
                        BigDecimal entrustNum = new BigDecimal((String) resultMap.get("ENTRUST_NUM"));
                        BigDecimal finishAmt = new BigDecimal((String) resultMap.get("FINISH_AMT"));
                        BigDecimal prinAmt = new BigDecimal((String) resultMap.get("PRIN_AMT"));
                        if (finishAmt != null) {
                            entrustAmt = entrustAmt.subtract(finishAmt);
                        }
                        if (prinAmt != null) {
                            entrustAmt = entrustAmt.subtract(prinAmt);
                        }
                        if (!StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())) {
                            if (StringUtil.isNotEmpty(tradeAmt)&&tradeAmt.compareTo(entrustAmt) == 1) {
                                errorList.add(new ValidMessage("tradeAmt", this.getMessage("TRD_X_0004") + entrustAmt));
                            }
                            if (StringUtil.isNotEmpty(shareAmt)&&shareAmt.compareTo(entrustNum) == 1) {
                                errorList.add(new ValidMessage("shareAmt", this.getMessage("TRD_X_0010") + entrustNum));
                            }
                        }else{
                            if (StringUtil.isNotEmpty(tradeAmt)&&entrustAmt.compareTo(BigDecimal.ZERO)>0&&tradeAmt.compareTo(entrustAmt) == 1) {
                                errorList.add(new ValidMessage("tradeAmt", this.getMessage("TRD_X_0004") + entrustAmt));
                            }
                            if (StringUtil.isNotEmpty(shareAmt)&&entrustNum.compareTo(BigDecimal.ZERO)>0&&shareAmt.compareTo(entrustNum) == 1) {
                                errorList.add(new ValidMessage("shareAmt", this.getMessage("TRD_X_0010") + entrustNum));
                            }
                        }

                    }
                }
                // 验证流程中是否存在该委托
                if(StringUtil.isNotEmpty(entrustId) && WorkFlowUtil.isNotFromToDoList()){
                    String str = tradeCommonService.check(entrustId, "TrdPlanNetInvestTrade002");
                    if(StringUtil.isNotEmpty(str)){
                        ValidMessage validMessage = new ValidMessage(str);
                        errorList.add(validMessage);
                    }
                }
                BigDecimal zero = new BigDecimal(0);
                if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())) {
                    if (tradeAmt == null) {
                        errorList.add(new ValidMessage("tradeAmt", this.getMessage("BUSS_COM_E_0038", this.getMessage("TrdFundInvestTrade002.tradeAmt"))));
                    }
                } else if (StringUtil.equals(dto.getPs(),
                        DictCodeList.交易方向_赎回.getValue())) {
                    if (shareAmt == null && tradeAmt == null) {
                        errorList.add(new ValidMessage("shareAmt", this.getMessage("BUSS_COM_E_0038", this.getMessage("TrdPlanNetInvestTrade001.shareAmt"))));
                    }
                }
                if (tradeAmt != null && tradeAmt.compareTo(zero) <=0) {
                    errorList.add(new ValidMessage("tradeAmt", this.getMessage("BUSS_COM_E_0065", this.getMessage("TrdFundInvestTrade002.tradeAmt"), "0")));
                }
                if (shareAmt != null && shareAmt.compareTo(zero) <=0) {
                    errorList.add(new ValidMessage("shareAmt", this.getMessage("BUSS_COM_E_0065", this.getMessage("TrdFundInvestTrade001.shareAmt"), "0")));
                }
                if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回.getValue())) {
                    BigDecimal assetAvailablePosition = dto.getAssetAvailablePosition();
                    if (assetAvailablePosition != null && shareAmt != null
                            && shareAmt.compareTo(assetAvailablePosition) == 1) {
                        errorList.add(new ValidMessage("shareAmt", this.getMessage("BUSS_COM_E_0003", this.getMessage("TrdFundInvestTrade001.shareAmt"), this.getMessage("TrdFundInvestTrade002.assetAvailablePosition"))));
                    }
//                    if (assetAvailablePosition != null && tradeAmt != null && tradeAmt.compareTo(assetAvailablePosition) == 1) {
//                        errorList.add(new ValidMessage("tradeAmt", this.getMessage("BUSS_COM_E_0003", this.getMessage("TrdFundInvestTrade002.tradeAmt"), this.getMessage("TrdFundInvestTrade002.assetAvailablePosition"))));
//                    } // 不用金额与持仓做check
                }
                if ((StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())
                        || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())
                        || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue()))
                        && StringUtil.isEmptyList(dto.getBussTrdTransferOrder())) {
                    // 划款指令
                    if (bussTrdTransferOrderService != null) {
                        List<ValidMessage> list = bussTrdTransferOrderService.check(
                                dto.getBussTrdTransferOrder());
                        errorList.addAll(list);
                    }
                }
                if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue()) && trdProjectNetInvestTrade002Service.checkIssueAmt(dto)){
                    errorList.add(new ValidMessage("tradeAmt", this.getMessage("该净值型计划的持有金额与交易金额之和不能超过该资产标的的发行金额")));
                }
            }
            if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())) {
                if (dto.getShareAmt() == null) {
                    errorList.add(new ValidMessage("shareAmt", this.getMessage("BUSS_COM_E_0038", this.getMessage("TrdPlanNetInvestTrade003.tradeSha"))));
                }
                Map<String, Object> param = new HashMap<>();
                param.put("portfolioId", dto.getTrdInvestPersonInfo().getCounterId());
                param.put("ccy", dto.getCcy());
                param.put("vdate", DateUtil.format(dto.getTradeDate()));
                param.put("calId", dto.getCalendarId());
                param.put("chlId", dto.getChlId());
                Map<String, Object> para = trdProjectNetInvestTrade002Service.getProAvailableCash(param);
                BigDecimal accCash = new BigDecimal((String) para.get("cashT2"));
                //交易对手没有足够的现金
                //上农商可以透支
                /*if (accCash.compareTo(dto.getTradeAmt()) == -1) {
                    errorList.add(new ValidMessage("",
                            getMessage("BUSS_COM_E_0003",
                                    param.get("portfolioId") + "  " + getMessage("TrdPlanNetInvestTrade002.tradeAmt"),
                                    getMessage("TrdFundInvestTrade002.accAvailableCash"))));
                }
*/
                if (dto.getShareAmt() != null) {
                    //同时验证卖出方是否有足够的持仓
                   PtlSecPositionFlowDto ptlSecPositionFlowDto = new PtlSecPositionFlowDto();
                    ptlSecPositionFlowDto.setCdate(dto.getTradeDate());
                    ptlSecPositionFlowDto.setPortfolioId(dto.getPortfolioId());
                    ptlSecPositionFlowDto.setSecAcctId(dto.getChlId());
                    ptlSecPositionFlowDto.setFinprodId(dto.getFinprodId());
                    BigDecimal assetPosition = trdProjectNetInvestTrade002Service.getPtlSecPosition(ptlSecPositionFlowDto);
                    if (StringUtil.isNotEmpty(dto.getShareAmt())) {
                        if (assetPosition.compareTo(dto.getShareAmt()) == -1) {
                            errorList.add(new ValidMessage("",
                                    getMessage("BUSS_COM_E_0003",
                                            ptlSecPositionFlowDto.getPortfolioId() + "  " + getMessage("TrdPlanNetInvestTrade001.shareAmt"),
                                            getMessage("TrdFundInvestTrade002.assetAvailablePosition"))));
                        }
                    } else {
                        errorList.add(new ValidMessage("shareAmt",
                                getMessage("FW_E_0006", "交易份额")));
                    }
                }
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "revoke")) {
            TrdProjectNetInvestTradeDto dtocopy = dto.getSelectedItem().get(0);
            if (StringUtil.equals(dtocopy.getValidStatus(),
                    DictCodeListExt1.交易流程状态_撤销.getValue())
                    || StringUtil.equals(dtocopy.getValidStatus(),
                    DictCodeListExt1.交易流程状态_流程中.getValue())) {
                errorList.add(new ValidMessage(this.getMessage("TRD_X_0005")));
            }
            if (StringUtil.equals(dtocopy.getPs(), DictCodeList.交易方向_赎回确认.getValue())
                    && trdProjectNetInvestTrade001Service.selRedemption(dtocopy.getTradeId()) != 0) {
                errorList.add(new ValidMessage(this.getMessage("TRD_X_0006")));
            }
            String ps = dtocopy.getPs();
            if (StringUtil.isNotEmpty(ps)
                    && (StringUtil.equals(ps, DictCodeList.交易方向_申购.getValue())
                    || StringUtil.equals(ps, DictCodeList.交易方向_赎回.getValue()))) {
                if (!trdProjectNetInvestTrade001Service.revokeCheck(dtocopy)) {
                    errorList.add(new ValidMessage(this.getMessage("TRD_X_0007")));
                }
            }
            if (StringUtil.isNotEmpty(ps)
                    && StringUtil.equals(ps, DictCodeList.交易方向_赎回到账.getValue())) {
                errorList.add(new ValidMessage(this.getMessage("TRD_X_0008")));
            }
        }
        return errorList;
    }

}
