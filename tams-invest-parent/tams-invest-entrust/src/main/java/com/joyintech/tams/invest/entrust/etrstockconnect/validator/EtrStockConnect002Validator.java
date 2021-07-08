
package com.joyintech.tams.invest.entrust.etrstockconnect.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.StlSecAcctInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.entrust.common.validator.EtrCommonValidator;
import com.joyintech.tams.invest.entrust.etrstockconnect.dto.EtrStockConnectDto;
import com.joyintech.tams.invest.entrust.etrstockconnect.service.EtrStockConnect002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 港股通
 * @see EtrStockConnect002Validator
 * @since 1.0
 */
@Component
public class EtrStockConnect002Validator implements EtrCommonValidator {

	@Autowired
	EtrStockConnect002Service etrStockConnect002Service;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ValidMessage> bussValid(Object value) {
		
		List<ValidMessage> errorList = new ArrayList<>();
		// TODO Auto-generated method stub
		EtrStockConnectDto dto = (EtrStockConnectDto) value;

		if (StringUtil.equals(dto.getOperateType(), "recall")) {
			List<EtrStockConnectDto> listDto = dto.getSelectedItem();
			for (EtrStockConnectDto entity : listDto) {
				if(!StringUtil.equals(entity.getEntrustStatus(), DictCodeList.委托状态_待接收.getValue())){
					errorList.add(new ValidMessage("entrustStatus","只允许撤回待接收的港股通指令!"));
				}
			}
		}

		if (StringUtil.equals(dto.getOperateType(), "close")) {
			List<EtrStockConnectDto> listDto = dto.getSelectedItem();
			for (EtrStockConnectDto entity : listDto) {
				if(!StringUtil.equals(entity.getEntrustStatus(), DictCodeList.委托状态_已接收.getValue())
				     && !StringUtil.equals(entity.getEntrustStatus(), DictCodeList.委托状态_已撤回.getValue())
				     && !StringUtil.equals(entity.getEntrustStatus(), DictCodeList.委托状态_已退回.getValue())){
					errorList.add(new ValidMessage("entrustStatus","只能对指令状态为已接收、已退回和已撤回的指令进行主动关闭!"));
				}
			}
		}

		else if (dto.getOperateType().equals("save") || dto.getOperateType().equals("submit")) {

			//价格指令
			if(StringUtil.equals(dto.getPriceModel(), DictCodeListExt2.价格模式_限价指令.getValue())|| StringUtil.equals(dto.getPriceModel(), DictCodeListExt2.价格模式_指定价格.getValue())){
				if(!StringUtil.isNotEmpty(dto.getPrice())){
					errorList.add(new ValidMessage("price","限价指令和指定价格，委托价格非空!"));
				}
			}

			if(StringUtil.equals(dto.getPriceModel(), DictCodeListExt2.价格模式_市价指令.getValue())){

			}

			//证券账户是否港股通
			StlSecAcctInfo stlDto = etrStockConnect002Service.selectStlSecAcctInfo(dto.getSecAcctId());
			if(stlDto!=null && !StringUtil.equals(stlDto.getyNHkstockc(), DictCodeListExt3.是否开通港股通_是.getValue())){
				errorList.add(new ValidMessage("portfolioId","该组合未开通港股通交易，请确认！"));
			}

			//配股和增发时配售代码非空
			if(StringUtil.equals(dto.getPs(), DictCodeListExt2.交易方向_配股.getValue()) || StringUtil.equals(dto.getPs(), DictCodeListExt2.交易方向_增发.getValue())){
                if(StringUtil.isEmpty(dto.getSaleCode())){
					errorList.add(new ValidMessage("saleCode","交易方向为配股和增发时,配售代码非空!"));
				}
			}

			//交易方向为买入、卖出时才查询最小买卖单位
			EtrStockConnectDto dtoMin = new EtrStockConnectDto();
			if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue()) || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())){
				Map<String,Object> map = new HashMap<>();
				map.put("finprodId",dto.getFinprodId());
				dtoMin = etrStockConnect002Service.getFinProdInfo(map);
			}
			if(null!=dtoMin && null != dtoMin.getMinSaleUnit()){
				BigDecimal multiple = dto.getEntrustNum().divide(dtoMin.getMinSaleUnit());
				if(new BigDecimal(multiple.intValue()).compareTo(multiple) != 0){
					errorList.add(new ValidMessage("entrustNum","指令数量应为实际最小买卖单位的整数倍! 该资产最小买卖单位为"+dtoMin.getMinSaleUnit()+"股"));
				}
			}

			//证券账户
			if(StringUtil.isEmpty(dto.getSecAcctId())){
				errorList.add(new ValidMessage("secAcctId","组合未找到相应交易所开通港股通的证券账户!"));
			}
			
			// 可用持仓校验
			if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())&&dto.getAssetAvailablePosition() != null&& StringUtil.isNotEmpty(dto.getEntrustNum())) {
				if(dto.getEntrustNum().compareTo(dto.getAssetAvailablePosition()) > 0) {
					errorList.add(new ValidMessage("entrustNum", "指令数量不能大于可用持仓!"));
				}
			// 可用现金校验
			}else if((StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue()))&& dto.getProAvailableCash() != null && StringUtil.isNotEmpty(dto.getEntrustAmt())) {
//				if(dto.getEntrustAmt().compareTo(dto.getProAvailableCash())>0) {
//					errorList.add(new ValidMessage("entrustAmt", "指令金额不能大于组合T日可用现金!"));
//				}
			}else if((StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue()))&& dto.getProAvailableCash() != null && StringUtil.isNotEmpty(dto.getEntrustNum())&& StringUtil.isNotEmpty(dto.getPrice())&& StringUtil.isNotEmpty(dto.getReferenceRate())) {
				BigDecimal amt = dto.getEntrustNum().multiply(dto.getPrice()).multiply(dto.getReferenceRate());
				if(amt.compareTo(dto.getProAvailableCash())>0) {
					errorList.add(new ValidMessage("entrustAmt", "指令金额不能大于组合T日可用现金!"));
				}
			}

		}
		return errorList;
	}
}
