/*
 * 文件名：TrdFundInvestTradeConfirmValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年11月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.fund.validator;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.WflTrdProductDealMapper;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.tams.common.base.entity.WflTrdProductDealKey;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.trade.fund.dto.TrdFundInvestTradeConfirmDto;
import com.joyintech.tams.invest.trade.fund.service.TrdFundInvestTrade001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年11月29日
 * @see TrdFundInvestTradeConfirmValidator
 * @since 1.0
 */
@Component
public class TrdFundInvestTradeConfirmValidator implements BeanValidator {

    @Autowired
    private TrdFundInvestTrade001Service trdFundInvestTrade001Service;

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
        TrdFundInvestTradeConfirmDto dto = (TrdFundInvestTradeConfirmDto)value;
        if(StringUtil.equals(dto.getOperateType(), "revoke") && dto.getSelectedItem() != null && !dto.getSelectedItem().isEmpty()){
            TrdFundInvestTradeConfirmDto dto1 = dto.getSelectedItem().get(0);
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
                if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购确认.getValue())
                    || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_认购确认.getValue())){
                    if(tradeAmt != null && tradeAmt.compareTo(zero)==-1){
                        errorList.add(new ValidMessage("tradeAmt",this.getMessage("BUSS_COM_E_0004", this.getMessage("TrdFundInvestTrade002.tradeAmt"),"0")));
                    }
                }
                if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue())){
                    if(shareAmt != null && shareAmt.compareTo(zero)==-1){
                        errorList.add(new ValidMessage("shareAmt",this.getMessage("BUSS_COM_E_0004", this.getMessage("TrdFundInvestTrade002.tradeSha"),"0")));
                    }
                    if(assetAvailablePosition != null && shareAmt != null && shareAmt.compareTo(assetAvailablePosition)==1){
                        errorList.add(new ValidMessage("shareAmt",this.getMessage("BUSS_COM_E_0003", this.getMessage("TrdFundInvestTrade002.tradeSha"),this.getMessage("TrdFundInvestTrade002.assetAvailablePosition"))));
                    }
                }
                if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue()) && StringUtil.isEmptyList(dto.getBussTrdTransferOrder())){
                    errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0038", getMessage("TrdFundInvestTrade002.transOrder"))));
                }
            }
//            if(dto.getFileArray() == null || dto.getFileArray().size()<=0){
//                errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0038", getMessage("TrdFundInvestTrade002.file"))));
//            }
            if (StringUtil.equals(dto.getPs(), DictCodeListExt2.交易方向_转换确认.getValue()) && (dto.getChangeInShareAmt() == null || dto.getChangeInShareAmt().compareTo(BigDecimal.ZERO) <= 0)) {
                errorList.add(new ValidMessage(getMessage("转换时，转入份额必填")));
            }
        }else if(StringUtil.equals(dto.getOperateType(),"submit")){
            if(StringUtil.isNotEmpty(dto.getPs())){
            	//认购确认，申购确认，赎回确认时，提交时，不能做未来交易
            	if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购确认.getValue())
                        || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_认购确认.getValue())
                         ||StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue())){
            		Date sysDate = DateUtil.platDateFromCache();
                	if(null!=dto.getTradeDate() && dto.getTradeDate().compareTo(sysDate)>0){
                		  errorList.add(new ValidMessage(getMessage("不允许做未来交易。")));
                	}
            	}
            	 BigDecimal tradeAmt = dto.getPrinAmt();
                BigDecimal shareAmt = dto.getShareAmt();
                BigDecimal assetAvailablePosition = dto.getAssetAvailablePosition();
                BigDecimal zero = new BigDecimal(0);
                if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购确认.getValue())
                    || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_认购确认.getValue())){
                    if(tradeAmt != null && tradeAmt.compareTo(zero)==-1){
                        errorList.add(new ValidMessage("tradeAmt",this.getMessage("BUSS_COM_E_0004", this.getMessage("TrdFundInvestTrade002.tradeAmt"),"0")));
                    }
                }
                if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue())){
                    if(shareAmt != null && shareAmt.compareTo(zero)==-1){
                        errorList.add(new ValidMessage("shareAmt",this.getMessage("BUSS_COM_E_0004", this.getMessage("TrdFundInvestTrade002.tradeSha"),"0")));
                    }
                //if(assetAvailablePosition != null && shareAmt != null && shareAmt.compareTo(assetAvailablePosition)==1){
                //  errorList.add(new ValidMessage("shareAmt",this.getMessage("BUSS_COM_E_0003", this.getMessage("TrdFundInvestTrade002.tradeSha"),this.getMessage("TrdFundInvestTrade002.assetAvailablePosition"))));
                //}
                }
                //if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue()) && !StringUtil.isEmptyList(dto.getBussTrdTransferOrder())){
                if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue())){
                    // 划款指令
                    if (bussTrdTransferOrderService != null) {
                        List<ValidMessage> list = bussTrdTransferOrderService.check(
                            dto.getBussTrdTransferOrder());
                        errorList.addAll(list);
                    }
                }
            }
            //     附件上传改为非必填 by zhuyongkang 20191010 start
//            if(dto.getFileArray() == null || dto.getFileArray().size()<=0){
//                errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0038", getMessage("TrdFundInvestTrade002.file"))));
//            }
            if (StringUtil.equals(dto.getPs(), DictCodeListExt2.交易方向_转换确认.getValue()) && (dto.getChangeInShareAmt() == null || dto.getChangeInShareAmt().compareTo(BigDecimal.ZERO) <= 0)) {
                errorList.add(new ValidMessage(getMessage("转换时，转入份额必填")));
            }
        }
        return errorList;
    }

}
