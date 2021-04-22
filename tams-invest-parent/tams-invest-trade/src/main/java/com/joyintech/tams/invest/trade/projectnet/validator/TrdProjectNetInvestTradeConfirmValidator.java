package com.joyintech.tams.invest.trade.projectnet.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.WflTrdProductDealMapper;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.tams.common.base.entity.WflTrdProductDealKey;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.trade.plannet.dto.TrdPlanNetInvestTradeConfirmDto;
import com.joyintech.tams.invest.trade.projectnet.dto.TrdProjectNetInvestTradeConfirmDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class TrdProjectNetInvestTradeConfirmValidator implements BeanValidator {
    /**
     * BussTrdTransferOrderService
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    @Autowired(required = false)
    private WflTrdProductDealMapper wflTrdProductDealMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
        TrdProjectNetInvestTradeConfirmDto dto = (TrdProjectNetInvestTradeConfirmDto)value;

        if(StringUtil.equals(dto.getOperateType(), "revoke") && dto.getSelectedItem() != null && !dto.getSelectedItem().isEmpty()){
            TrdPlanNetInvestTradeConfirmDto dto1 = dto.getSelectedItem().get(0);
            WflTrdProductDealKey key = new WflTrdProductDealKey();
            key.setTradeId(dto1.getTradeId() + "R");
            key.setWorkflowStatus("03");
            WflTrdProductDeal wflTrdProductDeal = wflTrdProductDealMapper.selectByPrimaryKey(key);
            if (wflTrdProductDeal != null) {
                errorList.add(new ValidMessage("该数据已在撤销流程中。"));
            }
        }
        else if(StringUtil.equals(dto.getOperateType(), "save")){
            if(StringUtil.isNotEmpty(dto.getPs())){
                BigDecimal tradeAmt = dto.getPrinAmt();
                BigDecimal shareAmt = dto.getShareAmt();
                BigDecimal assetAvailablePosition = dto.getAssetAvailablePosition();
                BigDecimal zero = new BigDecimal(0);
                if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购确认.getValue())){
                    if(tradeAmt != null && tradeAmt.compareTo(zero)==-1){
                        errorList.add(new ValidMessage("tradeAmt",this.getMessage("BUSS_COM_E_0065", this.getMessage("TrdFundInvestTrade002.tradeAmt"),"0")));
                    }
                }
                if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue())){
                    if(shareAmt != null && shareAmt.compareTo(zero)==-1){
                        errorList.add(new ValidMessage("shareAmt",this.getMessage("BUSS_COM_E_0065", this.getMessage("TrdFundInvestTrade002.tradeSha"),"0")));
                    }
//                    if(assetAvailablePosition != null && shareAmt != null && shareAmt.compareTo(assetAvailablePosition)==1){
//                        errorList.add(new ValidMessage("shareAmt",this.getMessage("BUSS_COM_E_0003", this.getMessage("TrdFundInvestTrade002.tradeSha"),this.getMessage("TrdFundInvestTrade002.assetAvailablePosition"))));
//                    }
                }
                if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue()) && StringUtil.isEmptyList(dto.getBussTrdTransferOrder())){
                    errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0038", getMessage("TrdFundInvestTrade002.transOrder"))));
                }
            }
//           去掉交易到账确认时附件上传check
//            if(dto.getFileArray() == null || dto.getFileArray().size()<=0){
//                errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0038", getMessage("TrdFundInvestTrade002.file"))));
//            }
        }else if(StringUtil.equals(dto.getOperateType(),"submit")){
            if(StringUtil.isNotEmpty(dto.getPs())){
                BigDecimal tradeAmt = dto.getPrinAmt();
                BigDecimal shareAmt = dto.getShareAmt();
                BigDecimal assetAvailablePosition = dto.getAssetAvailablePosition();
                BigDecimal zero = new BigDecimal(0);
                if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购确认.getValue())){
                    if(tradeAmt != null && tradeAmt.compareTo(zero)==-1){
                        errorList.add(new ValidMessage("tradeAmt",this.getMessage("BUSS_COM_E_0065", this.getMessage("TrdFundInvestTrade002.tradeAmt"),"0")));
                    }
                }
                if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue())){
                    if(shareAmt != null && shareAmt.compareTo(zero)==-1){
                        errorList.add(new ValidMessage("shareAmt",this.getMessage("BUSS_COM_E_0065", this.getMessage("TrdFundInvestTrade002.tradeSha"),"0")));
                    }
//                    if(assetAvailablePosition != null && shareAmt != null && shareAmt.compareTo(assetAvailablePosition)==1){
//                        errorList.add(new ValidMessage("shareAmt",this.getMessage("BUSS_COM_E_0003", this.getMessage("TrdFundInvestTrade002.tradeSha"),this.getMessage("TrdFundInvestTrade002.assetAvailablePosition"))));
//                    }
                }
                if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue()) && StringUtil.isEmptyList(dto.getBussTrdTransferOrder())){
                    // 划款指令
                    if (bussTrdTransferOrderService != null) {
                        List<ValidMessage> list = bussTrdTransferOrderService.check(
                                dto.getBussTrdTransferOrder());
                        errorList.addAll(list);
                    }
                }
            }
//           去掉交易到账确认时附件上传check
//            if(dto.getFileArray() == null || dto.getFileArray().size()<=0){
//                errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0038", getMessage("TrdFundInvestTrade002.file"))));
//            }
        }
        return errorList;
    }
}
