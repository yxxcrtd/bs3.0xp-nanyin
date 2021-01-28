/*
 * 文件名：TrdDepositInvestTrade001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年11月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.trddepositinvesttrade.validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.tams.component.business.trade.dto.BussTrdTransfenpeiDto;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.common.validator.TradeCommonValidator;
import com.joyintech.tams.invest.trade.trddepositinvesttrade.dto.TrdDepositInvestTrade001Dto;
import com.joyintech.tams.invest.trade.trddepositinvesttrade.service.TrdDepositInvestTrade002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 存款类资产验证
 * @author fanshuanghu
 * @version 1.0 2018年11月8日
 * @see TrdDepositInvestTrade001Validator
 * @since 1.0
 */
@Transactional
@Component
public class TrdDepositInvestTrade001Validator implements TradeCommonValidator{
    
    @Autowired
    private TrdDepositInvestTrade002Service trdDepositInvestTrade002Service;
    
    @Autowired
	private TradeCommonService tradeCommonService;

    /**
     * 指令信息service
     */
    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderService;
    
    @Value("${flag:false}")
    private boolean flag;
    
    @Override
    public List<ValidMessage> bussValid(Object value){
        ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
        if(flag) {
        	return errorList;
        }
        TrdDepositInvestTrade001Dto dto = (TrdDepositInvestTrade001Dto)value;
        if(dto.getOperateType().equals("submit")){
            // 划款指令
            if (bussTrdTransferOrderService != null) {
                List<ValidMessage> list = bussTrdTransferOrderService.check(
                		dto.getBussTrdTransferOrder());
                errorList.addAll(list);
            }
            if(StringUtil.isNotEmpty(dto.getPortfolioId())){
                String isGuar = trdDepositInvestTrade002Service.getIsGuarantee(dto.getPortfolioId());//查询是否保本
                if(StringUtil.isNotEmpty(isGuar) && StringUtil.equals(isGuar, DictCodeList.是否_否.getValue())
                    && StringUtil.isEmptyList(dto.getBussTrdTransferOrder()) ){
                    errorList.add(new ValidMessage("bussTrdTransferOrder",getMessage("FW_E_0006",getMessage("TrdDepositInvestTrade002.bussTrdTransferOrder"))));
                }
            }
            //     附件上传改为非必填 by zhuyongkang 20191010 start
//            if(StringUtil.isEmptyList(dto.getFileArray())){
//                errorList.add(new ValidMessage("fileArray",getMessage("FW_E_0006",getMessage("TrdDepositInvestTrade002.fileCommon"))));
//            }
        }
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)
        		|| StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
//            if(dto.getBussTrdTransferOrder() != null && dto.getBussTrdTransferOrder().size()>0){
//                if((dto.getBussTrdTransferOrder().get(0).getPayAcctNo() == null || dto.getBussTrdTransferOrder().get(0).getPayAcctNo().isEmpty()) && dto.getBussTrdTransferOrder().get(0).getOrderId() != null
//                        && (dto.getBussTrdTransferOrder().get(0).getRecAcctNo() == null || dto.getBussTrdTransferOrder().get(0).getRecAcctNo().isEmpty()) && dto.getBussTrdTransferOrder().get(0).getOrderId() != null){
//                    errorList.add(new ValidMessage("bussTrdTransferOrder",getMessage("FW_E_0006",getMessage("TrdDepositInvestTrade002.payAcctNo","或",getMessage("TrdDepositInvestTrade002.recAcctNo")))));
//                }
//            }
            if(DateUtil.compareDate(dto.getTradeDate(), dto.getMdate()) > 0 ){
                errorList.add(new ValidMessage("mdate",getMessage("BUSS_COM_E_0003",getMessage("TrdDepositInvestTrade002.tradeDate"),getMessage("TrdDepositInvestTrade002.mDate"))));
            }

            if (!StringUtil.equals(dto.getFinprodType(), DictCodeListExt3.存款类资产类型_活期存放.getValue()) && StringUtil.isEmpty(dto.getTermDays())) {
                errorList.add(new ValidMessage("termDays",getMessage("BUSS_COM_E_0038",getMessage("TrdDepositInvestTrade002.termDays"))));
            }
            //同一标的只能一个组合交易
            if (StringUtil.equals(dto.getFinprodType(), DictCodeListExt3.存款类资产类型_活期存放.getValue())
                    && StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())
                    && trdDepositInvestTrade002Service.checkTradeById(dto)>0) {
                errorList.add(new ValidMessage("",getMessage("TRD_DEPOSIT_001")));
            }
            //验证标的是否做过终止协议交易
            if (StringUtil.equals(dto.getFinprodType(), DictCodeListExt3.存款类资产类型_活期存放.getValue())
                    && StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())
                    && trdDepositInvestTrade002Service.checkIsCleanById(dto)>0) {
                errorList.add(new ValidMessage("",getMessage("TRD_DEPOSIT_002")));
            }
         // 验证流程中是否存在该委托
            if(StringUtil.isNotEmpty(dto.getEntrustId()) && WorkFlowUtil.isNotFromToDoList()){
            	String str = tradeCommonService.check(dto.getEntrustId(), "TrdDepositInvestTrade002");
            	if(StringUtil.isNotEmpty(str)){ 
            		ValidMessage validMessage = new ValidMessage(str);
            		errorList.add(validMessage);
            	}
            }
//            if(DateUtil.compareDate(dto.getTradeDate(), dto.getVdate()) < 0 ){
//                errorList.add(new ValidMessage("vdate",getMessage("BUSS_COM_E_0003",getMessage("TrdDepositInvestTrade002.tradeDate"),getMessage("TrdDepositInvestTrade002.vDate"))));
//            }
//            //买入的时候可用现金必须大于交易本金
//            if(DictCodeList.交易方向_买入.getValue().equals(dto.getPs())){
//                if(dto.getAccAvailableCashT() != null && dto.getPrinAmt() != null 
//                    && dto.getAccAvailableCashT().compareTo(dto.getPrinAmt()) < 0){
//                    errorList.add(new ValidMessage("prinAmt",getMessage("BUSS_COM_E_0036",getMessage("TrdDepositInvestTrade002.accAvailableCashT"),getMessage("TrdDepositInvestTrade001.tradeAmt"))));
//                }
//            }
            //卖出时持仓必须大于交易金额
            if(DictCodeList.交易方向_卖出.getValue().equals(dto.getPs())){
                if(dto.getBottomAssetAmount() != null && dto.getPrinAmt() != null 
                    && dto.getBottomAssetAmount().compareTo(dto.getPrinAmt()) < 0){
                    errorList.add(new ValidMessage("prinAmt",getMessage("BUSS_COM_E_0036",getMessage("TrdDepositInvestTrade002.bottomAssetAmount"),getMessage("TrdDepositInvestTrade001.tradeAmt"))));
                }
                if (!dto.getBussTrdTransferOrder().isEmpty()) {
                    BussTrdTransferOrderDto order = dto.getBussTrdTransferOrder().get(0);
                    if (StringUtil.isEmpty(order.getRecAcctNo())) {
                        errorList.add(new ValidMessage("",getMessage("FW_E_0006",getMessage("TrdDepositInvestTrade002.recAcctNo"))));
                    }
                }
            }
            else if (DictCodeList.交易方向_买入.getValue().equals(dto.getPs())) {
                if (!dto.getBussTrdTransferOrder().isEmpty()) {
                    BussTrdTransferOrderDto order = dto.getBussTrdTransferOrder().get(0);
                    if (StringUtil.isEmpty(order.getPayAcctNo())) {
                        errorList.add(new ValidMessage("",getMessage("FW_E_0006",getMessage("TrdDepositInvestTrade002.payAcctNo"))));
                    }
                }
            }
            //add by yujiaping 2020-12-8 BS-1598 '活期存放'交易方向选择'卖出'时，将'应计利息总额'去掉必填并隐藏start
            if(!(StringUtil.equals(dto.getFinprodType(), DictCodeListExt3.存款类资产类型_活期存放.getValue())
               && StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue()))){
            	 if (!StringUtil.isNotEmpty(dto.getIntAmt())) {
                     errorList.add(new ValidMessage("请输入应计利息总额。"));
                 } 
               }
            //add by yujiaping 2020-12-8 BS-1598 '活期存放'交易方向选择'卖出'时，将'应计利息总额'去掉必填并隐藏end
            if (StringUtil.isNotEmpty(dto.getBussTrdTransfenpeiDto())){
                Map<String, List<BussTrdTransfenpeiDto>> map = dto.getBussTrdTransfenpeiDto().stream().collect(JoyinCollectors.groupingBy(item->item.getPortfolioId()));
                if (map.keySet().size()<dto.getBussTrdTransfenpeiDto().size()){
                    errorList.add(new ValidMessage("组合分配不能重复分配给同一组合。"));
                }
            }
        }
      return errorList;
    }
}
