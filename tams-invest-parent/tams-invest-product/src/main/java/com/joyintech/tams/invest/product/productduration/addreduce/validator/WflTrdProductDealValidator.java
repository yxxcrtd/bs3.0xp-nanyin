package com.joyintech.tams.invest.product.productduration.addreduce.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.product.addreduce.ProductFeeInfo;
import com.joyintech.tams.invest.common.dto.product.addreduce.ReduceInfoDto;
import com.joyintech.tams.invest.product.productduration.addreduce.dto.FinProductAddReduce002Dto;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: code
 * @Package com.joyintech.tams.invest.product.productduration.addreduce.validator
 * @date Date : 2018年09月15日 15:30
 */
@Component
public class WflTrdProductDealValidator implements BeanValidator {

    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    @Override
    public List<ValidMessage> valid(Object value) {
        FinProductAddReduce002Dto dto = (FinProductAddReduce002Dto)value;
        List<ValidMessage> errorList = new ArrayList<>();
        if (StringUtil.isNotEmpty(dto.getOperateType()) && !"revoke".equals(dto.getOperateType())) {
            errorList.addAll(bussTrdTransferOrderService.check(dto.getBussTrdTransferOrder()));
            if (DictCodeList.收益类型_收益型.getValue().equals(dto.getProfitType())) {
                if (dto.getReduceInfoDtoList() != null && !dto.getReduceInfoDtoList().isEmpty()) {
                    for (ReduceInfoDto reduceInfoDto : dto.getReduceInfoDtoList()) {
                        if (reduceInfoDto.getReduceAmt() ==null ||
                            BigDecimal.ZERO.compareTo(reduceInfoDto.getReduceAmt()) > 0) {
                            errorList.add(new ValidMessage("reduceInfoDtoList",
                                    this.getMessage("BUSS_COM_E_0004", "提前赎回本金", "0")));
                            break;
                        }

                        if (reduceInfoDto.getActInterest() == null || reduceInfoDto.getActInterest().compareTo(BigDecimal.ZERO) < 0) {
                            errorList.add(new ValidMessage("reduceInfoDtoList",
                                    this.getMessage("BUSS_COM_E_0004", "实付利息", "0")));
                            break;
                        }

                        /*if (reduceInfoDto.getReduceAmt() != null && reduceInfoDto.getReduceAmt().compareTo(reduceInfoDto.getRemainPrinAmt()) > 0) {
                            errorList.add(new ValidMessage("reduceInfoDtoList",
                                    this.getMessage("BUSS_COM_E_0003", "提前赎回本金", "剩余本金")));
                        }*/
                    }
                }
              
                
                if (BigDecimal.ZERO.compareTo(dto.getPrinAmt()) > 0) {
                    errorList.add(new ValidMessage("prinAmt",
                            this.getMessage("BUSS_COM_E_0004", "提前赎回本金", "0")));
                }
            }
            
           if(StringUtil.isEmptyList(dto.getBussTrdTransferOrder())){
              errorList.add(new ValidMessage("prinAmt", this.getMessage("请生成指令!")));
           } 
           else{
                  //校验收付款账户
                  BussTrdTransferOrderDto order = dto.getBussTrdTransferOrder().get(0);
                  if(order!=null){
                      if(!StringUtil.isNotEmpty(order.getRecAcctNo())){
                          errorList.add(new ValidMessage("prinAmt", this.getMessage("收款账户不能为空")));
                      }
                  }
           } 
           
             if (dto.getTradeDate() != null && DateUtil.getSysDate().compareTo(dto.getTradeDate()) > 0) {
                 errorList.add(new ValidMessage("tradeDate", this.getMessage("BUSS_COM_E_0004",
                     this.getMessage("FinProductAddReduce001.cfmDate"), "平台日期")));
             }
             if (dto.getSettleDate() != null && dto.getTradeDate() != null && dto.getTradeDate().compareTo(dto.getSettleDate()) > 0) {
                 errorList.add(new ValidMessage("tradeDate", this.getMessage("BUSS_COM_E_0004",
                     this.getMessage("FinProductAddReduce002.payDate"),this.getMessage("FinProductAddReduce001.cfmDate"))));
             }
             
//            if (dto.getTradeDate() != null && dto.getPrdMdate().compareTo(dto.getTradeDate()) < 0) {
//                errorList.add(new ValidMessage("tradeDate", this.getMessage("BUSS_COM_E_0045",
//                    this.getMessage("FinProductAddReduce001.cfmDate"), this.getMessage("FinProductAddReduce002.mdate"))));
//            }
//
//            if (dto.getTradeDate() != null && dto.getPrdVdate().compareTo(dto.getTradeDate()) > 0) {
//                errorList.add(new ValidMessage("tradeDate", this.getMessage("BUSS_COM_E_0004",
//                    this.getMessage("FinProductAddReduce001.cfmDate"), this.getMessage("FinProductAddReduce002.vdate"))));
//            }

//            if (dto.getTradeAmt() != null && dto.getTradeAmt().compareTo(BigDecimal.ZERO) <= 0) {
//                errorList.add(new ValidMessage("tradeAmt",
//                    this.getMessage("FIN_PROD_E_0015", this.getMessage("FinProductAddReduce002.tradeAmt"), "0")));
//            }
        }
        return errorList;
    }
}
