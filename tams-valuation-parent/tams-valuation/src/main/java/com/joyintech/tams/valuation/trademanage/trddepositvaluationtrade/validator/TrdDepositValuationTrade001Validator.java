/*
 * 文件名：TrdDepositInvestTrade001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年11月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.trademanage.trddepositvaluationtrade.validator;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;

import com.joyintech.tams.valuation.trademanage.trddepositvaluationtrade.service.TrdDepositValuationTrade002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 存款类资产验证
 * @author fanshuanghu
 * @version 1.0 2018年11月8日
 * @see TrdDepositValuationTrade001Validator
 * @since 1.0
 */
@Transactional
@Component
public class TrdDepositValuationTrade001Validator implements BeanValidator {
    
    @Autowired
    private TrdDepositValuationTrade002Service trdDepositValuationTrade002Service;
    
//    @Autowired
//	private TradeCommonService tradeCommonService;

    @Override
    public List<ValidMessage> valid(Object o) {
        return null;
    }

//    @Override
//    public List<ValidMessage> bussValid(Object value){
//        ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
//        TrdDepositInvestTrade001Dto dto = (TrdDepositInvestTrade001Dto)value;
//        if(dto.getOperateType().equals("submit")){
//            if(StringUtil.isNotEmpty(dto.getPortfolioId())){
//                String isGuar = trdDepositInvestTrade002Service.getIsGuarantee(dto.getPortfolioId());//查询是否保本
//                if(StringUtil.isNotEmpty(isGuar) && StringUtil.equals(isGuar, DictCodeList.是否_否.getValue())
//                    && StringUtil.isEmptyList(dto.getBussTrdTransferOrder()) ){
//                    errorList.add(new ValidMessage("bussTrdTransferOrder",getMessage("FW_E_0006",getMessage("TrdDepositInvestTrade002.bussTrdTransferOrder"))));
//                }
//            }
//            //     附件上传改为非必填 by zhuyongkang 20191010 start
////            if(StringUtil.isEmptyList(dto.getFileArray())){
////                errorList.add(new ValidMessage("fileArray",getMessage("FW_E_0006",getMessage("TrdDepositInvestTrade002.fileCommon"))));
////            }
//        }
//        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
////            if(dto.getBussTrdTransferOrder() != null && dto.getBussTrdTransferOrder().size()>0){
////                if((dto.getBussTrdTransferOrder().get(0).getPayAcctNo() == null || dto.getBussTrdTransferOrder().get(0).getPayAcctNo().isEmpty()) && dto.getBussTrdTransferOrder().get(0).getOrderId() != null
////                        && (dto.getBussTrdTransferOrder().get(0).getRecAcctNo() == null || dto.getBussTrdTransferOrder().get(0).getRecAcctNo().isEmpty()) && dto.getBussTrdTransferOrder().get(0).getOrderId() != null){
////                    errorList.add(new ValidMessage("bussTrdTransferOrder",getMessage("FW_E_0006",getMessage("TrdDepositInvestTrade002.payAcctNo","或",getMessage("TrdDepositInvestTrade002.recAcctNo")))));
////                }
////            }
//            if(DateUtil.compareDate(dto.getTradeDate(), dto.getMdate()) > 0 ){
//                errorList.add(new ValidMessage("mdate",getMessage("BUSS_COM_E_0003",getMessage("TrdDepositInvestTrade002.tradeDate"),getMessage("TrdDepositInvestTrade002.mDate"))));
//            }
//         // 验证流程中是否存在该委托
//            if(StringUtil.isNotEmpty(dto.getEntrustId()) && WorkFlowUtil.isNotFromToDoList()){
//            	String str = tradeCommonService.check(dto.getEntrustId(), "TrdDepositInvestTrade002");
//            	if(StringUtil.isNotEmpty(str)){
//            		ValidMessage validMessage = new ValidMessage(str);
//            		errorList.add(validMessage);
//            	}
//            }
////            if(DateUtil.compareDate(dto.getTradeDate(), dto.getVdate()) < 0 ){
////                errorList.add(new ValidMessage("vdate",getMessage("BUSS_COM_E_0003",getMessage("TrdDepositInvestTrade002.tradeDate"),getMessage("TrdDepositInvestTrade002.vDate"))));
////            }
////            //买入的时候可用现金必须大于交易本金
////            if(DictCodeList.交易方向_买入.getValue().equals(dto.getPs())){
////                if(dto.getAccAvailableCashT() != null && dto.getPrinAmt() != null
////                    && dto.getAccAvailableCashT().compareTo(dto.getPrinAmt()) < 0){
////                    errorList.add(new ValidMessage("prinAmt",getMessage("BUSS_COM_E_0036",getMessage("TrdDepositInvestTrade002.accAvailableCashT"),getMessage("TrdDepositInvestTrade001.tradeAmt"))));
////                }
////            }
//            //卖出时持仓必须大于交易金额
//            if(DictCodeList.交易方向_卖出.getValue().equals(dto.getPs())){
//                if(dto.getBottomAssetAmount() != null && dto.getPrinAmt() != null
//                    && dto.getBottomAssetAmount().compareTo(dto.getPrinAmt()) < 0){
//                    errorList.add(new ValidMessage("prinAmt",getMessage("BUSS_COM_E_0036",getMessage("TrdDepositInvestTrade002.bottomAssetAmount"),getMessage("TrdDepositInvestTrade001.tradeAmt"))));
//                }
//                if (!dto.getBussTrdTransferOrder().isEmpty()) {
//                    BussTrdTransferOrderDto order = dto.getBussTrdTransferOrder().get(0);
//                    if (StringUtil.isEmpty(order.getRecAcctNo())) {
//                        errorList.add(new ValidMessage("",getMessage("FW_E_0006",getMessage("TrdDepositInvestTrade002.recAcctNo"))));
//                    }
//                }
//            }
//            else if (DictCodeList.交易方向_买入.getValue().equals(dto.getPs())) {
//                if (!dto.getBussTrdTransferOrder().isEmpty()) {
//                    BussTrdTransferOrderDto order = dto.getBussTrdTransferOrder().get(0);
//                    if (StringUtil.isEmpty(order.getPayAcctNo())) {
//                        errorList.add(new ValidMessage("",getMessage("FW_E_0006",getMessage("TrdDepositInvestTrade002.payAcctNo"))));
//                    }
//                }
//            }
//        }
//      return errorList;
//    }


}
