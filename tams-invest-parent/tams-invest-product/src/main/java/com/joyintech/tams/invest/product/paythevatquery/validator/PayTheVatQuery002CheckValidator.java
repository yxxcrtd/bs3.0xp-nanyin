/*
 * 文件名：PayTheVatQuery002CheckValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2019年2月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.invest.product.paythevatquery.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.trade.BussTrdAvailableCashQueryService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.product.paythevatquery.dto.PayTheVatQuery002Dto;
import com.joyintech.tams.invest.product.paythevatquery.dto.PayTheVatQuery002TableDto;
import com.joyintech.tams.invest.product.paythevatquery.service.PayTheVatQuery002Service;

/**
 * 增值税缴纳校验
 * @author wangqidong
 * @version 1.0 2019年2月28日
 * @see PayTheVatQuery002CheckValidator
 * @since 1.0
 */
@Component("FinproductPayTheVatQuery002CheckValidator")
public class PayTheVatQuery002CheckValidator implements BeanValidator{
	
	@Autowired
	private PayTheVatQuery002Service payTheVatQuery002Service;
	
	@Autowired(required = false)
    private BussTrdAvailableCashQueryService bussTrdAvailableCashQueryService;
	
	@Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        // TODO Auto-generated method stub
        List<ValidMessage> validList = new ArrayList<ValidMessage>();
        PayTheVatQuery002Dto dto = (PayTheVatQuery002Dto)value;
//        //获取平台日期（string型），缴费日期不能小于平台日
		String pltDate = DateUtil.format(DateUtil.platDateFromCache(), DateUtil.DEFAULT_DATE_FORMAT_EN);
        if(DateUtil.compareDate(dto.getPayTaxVDate(), pltDate)<0) {
        	validList.add(new ValidMessage("payTaxVDate",this.getMessage("BUSS_COM_E_0004","纳税日期","平台日")));
        }
        

        Map<String, Object> params = new HashMap<String, Object>();
        List<String> portfolioIdList = new ArrayList<>();
        List<String> finprodIdList = new ArrayList<>();
        //产品托管户剩余现金不能小于待缴税额
    	Map<String, Object> params2 = new HashMap<>();
    	params2.put("ccy", DictCodeList.币种_人民币_CNY.getValue());
    	params2.put("tradeDate", dto.getPayTaxVDate());
    	params2.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
    	BigDecimal secAcctBalance = BigDecimal.ZERO;
        for(PayTheVatQuery002TableDto payTheVatQuery002TableDto:dto.getListpayTheVatQuery002TableDto()) {
        	params2.put("portfolioId", payTheVatQuery002TableDto.getPortfolioId());
        	
        	if (bussTrdAvailableCashQueryService != null && params2.get("tradeDate") != null) {
        		secAcctBalance = bussTrdAvailableCashQueryService.getSecManageAvailableCash(params2);
            }

        	if(payTheVatQuery002TableDto.getTaxTotal() !=null && payTheVatQuery002TableDto.getTaxTotal().compareTo(secAcctBalance)>0){
        		validList.add(new ValidMessage("",getMessage("FIN_PROD_E_0127","产品托管户剩余现金","待缴税额,组合代码："+payTheVatQuery002TableDto.getPortfolioId()+"的托管户剩余现金:"+secAcctBalance+"")));
        	}
        	
        	portfolioIdList.add(payTheVatQuery002TableDto.getPortfolioId());
        	if(StringUtil.equals(BussinessDictCodeList.收益类型_预期收益.getValue(), payTheVatQuery002TableDto.getProfitType())) {
        		finprodIdList.add(payTheVatQuery002TableDto.getPortfolioId());
        	} else {
        		finprodIdList.add(payTheVatQuery002TableDto.getFinprodId());
        	}
        }
        //本次缴费日期不能小于上次缴费日期
        params.put("portfolioIdList",portfolioIdList);
        params.put("taxidenType", dto.getTaxidenType());
        if(StringUtil.equals(dto.getTaxClass(), DictCodeListExt3.增值税品种_通道增值税.getValue())) {
            params.put("channelId", dto.getChannelId());  
        } 
        List<PayTheVatQuery002Dto> lastPayTheVatInfoList = payTheVatQuery002Service.getLastPayTheVatInfoList(params);
        if(lastPayTheVatInfoList != null && lastPayTheVatInfoList.size()>0) {
        	for(PayTheVatQuery002Dto lastDto:lastPayTheVatInfoList) {
        		if(DateUtil.compareDate(dto.getPayTaxVDate(), lastDto.getPayTaxVDate())<0) {
        			validList.add(new ValidMessage("payTaxVDate",this.getMessage("BUSS_COM_E_0004","组合代码："+lastDto.getPortfolioId()+"的纳税日期","最后一次交税日期("+lastDto.getPayTaxVDate()+")")));
        		}
        	}
        }
        
        //缴费日期不能小于产品的已跑批日期+1自然日
        Map<String, Object> params1 = new HashMap<String, Object>();
        params1.put("portfolioIdList",portfolioIdList);
        params1.put("finprodIdList",finprodIdList);
        List<PayTheVatQuery002Dto> LastcdateList = payTheVatQuery002Service.getLastcdate(params1);
        //modify by jyl start 20200113 预期收益型产品去掉“纳税日期不能小于产品跑批自然日+1”限制
        for(PayTheVatQuery002Dto payTheVatQuery002Dto: LastcdateList) {
        	/*if(DateUtil.compareDate(dto.getPayTaxVDate(), payTheVatQuery002Dto.getPayTaxVDate())<0
                    && !StringUtil.equals(BussinessDictCodeList.收益类型_预期收益.getValue(), payTheVatQuery002Dto.getProfitType())) {
        		validList.add(new ValidMessage("payTaxVDate",this.getMessage("BUSS_COM_E_0004","组合代码："+payTheVatQuery002Dto.getPortfolioId()+"的纳税日期","产品跑批自然日+1("+payTheVatQuery002Dto.getPayTaxVDate()+")")));
        	}
        	modify by hxy start 20200320 预期收益型产品纳税日期不能大于等于产品跑批自然日+1
        	if(DateUtil.compareDate(dto.getPayTaxVDate(), payTheVatQuery002Dto.getPayTaxVDate())>=0
                    && StringUtil.equals(BussinessDictCodeList.收益类型_预期收益.getValue(), payTheVatQuery002Dto.getProfitType())) {
        		validList.add(new ValidMessage("payTaxVDate",this.getMessage("BUSS_COM_E_0045","组合代码："+payTheVatQuery002Dto.getPortfolioId()+"的纳税日期","产品跑批自然日+1("+payTheVatQuery002Dto.getPayTaxVDate()+")")));
        	}
        	modify by hxy end 20200320 预期收益型产品纳税日期不能大于等于产品跑批自然日*/
        	//modify by hxy start 20200428 缴纳结束日必须小于等于跑批自然日
             if(DateUtil.compareDate(dto.getPayTaxMDate(),payTheVatQuery002Dto.getPayTaxVDate())>0){
            		validList.add(new ValidMessage("payTaxMDate",this.getMessage("BUSS_COM_E_0003","组合代码："+payTheVatQuery002Dto.getPortfolioId()+"的纳税结束日","产品跑批自然日("+payTheVatQuery002Dto.getPayTaxVDate()+")")));
             }
        }
        //modify by jyl end 20200113 预期收益型产品去掉“纳税日期不能小于产品跑批自然日+1”限制
        //产品增值税和逐笔划款的通道增值税需要校验划款指令
        if(StringUtil.equals(dto.getTaxClass(),DictCodeListExt3.增值税品种_产品增值税.getValue()) 
            || (StringUtil.equals(dto.getTaxClass(),DictCodeListExt3.增值税品种_通道增值税.getValue()) 
                && StringUtil.equals(dto.getPayType(),DictCodeListExt2.划款方式_逐笔划款.getValue()))) {
            /** 增加付款指令中对付款账号和划款账号的校验 **/
            List<ValidMessage> list = bussTrdTransferOrderService.check(dto.getBussTrdTransferOrder());
            validList.addAll(list);
             
        }

        
        return validList;
    }

}
