package com.joyintech.tams.invest.entrust.etrbondtostock.validator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.trade.CeLinesUpdate;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.CreditLinesDealCommon;
import com.joyintech.tams.invest.entrust.common.validator.EtrCommonValidator;
import com.joyintech.tams.invest.entrust.etrbondtostock.dto.EtrBondToStockDto;
import com.joyintech.tams.invest.entrust.etrbondtostock.service.EtrBondToStockService;
import com.joyintech.tams.invest.entrust.etrconbond.dto.EtrConBondDto;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
/**
 * 可转债转股委托校验
 * 〈功能详细描述〉
 * @author gaopeng
 * @version 1.0 2019年10月24日
 * @see EtrBondToStockValidator
 * @since 1.0
 */
@Component
public class EtrBondToStockValidator implements EtrCommonValidator{
	
	 	@Autowired
	    private EtrBondToStockService etrbondtostockservice;

	    /**
	     * {@inheritDoc}
	     */
	    @Override
	    public List<ValidMessage> bussValid(Object value) {

	        List<ValidMessage> errorList = new ArrayList<>();
	        EtrBondToStockDto dto = (EtrBondToStockDto)value;
	        if (StringUtil.equals(dto.getOperateType(), "delete")) {
	            List<EtrBondToStockDto> listDto = dto.getSelectedItem();
	            for (EtrBondToStockDto entity : listDto) {
	                entity.setOperateType(BussinessDictCodeList.委托操作类型_删除.getValue());
	                errorList.addAll(etrbondtostockservice.check(entity));
	            }
	        } else if (StringUtil.equals(dto.getOperateType(), "close")) {
	            List<EtrBondToStockDto> listDto = dto.getSelectedItem();
	            for (EtrBondToStockDto entity : listDto) {
	                entity.setOperateType(BussinessDictCodeList.委托操作类型_关闭.getValue());
	                errorList.addAll(etrbondtostockservice.check(entity));
	            }
	        } else if (StringUtil.equals(dto.getOperateType(), "recall")) {
	            List<EtrBondToStockDto> listDto = dto.getSelectedItem();
	            for (EtrBondToStockDto entity : listDto) {
	                entity.setOperateType(BussinessDictCodeList.委托操作类型_撤回.getValue());
	                errorList.addAll(etrbondtostockservice.check(entity));
	            }
	        } else if (StringUtil.equals(dto.getOperateType(), "edit")) {
	            dto.setOperateType(DictCodeListExt1.委托操作类型_修改.getValue());
	            if (!(DictCodeList.委托状态_已撤回.getValue().equals(dto.getEntrustStatus())
	                  || DictCodeList.委托状态_已退回.getValue().equals(dto.getEntrustStatus()))) {
	                errorList.add(new ValidMessage(
	                    // “新建”、“已撤回”、“已退回”
	                    this.getMessage("ETR_E_0009", this.getMessage("ETR_LEND_001"))));
	            }
	        }  else {
	            errorList.addAll(etrbondtostockservice.check(dto));
	            //验证证券账户和通道
	            if (StringUtil.isEmpty(dto.getChlId()) && StringUtil.isEmpty(dto.getSecAcctId())){
	                errorList.add(new ValidMessage( this.getMessage("ETR_CB_001",getMessage("invest.channelId"))));
	            }
	            //验证转股起始日和委托终止日
	            if(DateUtil.compareDate(dto.getConVdate(),dto.getMdate())==1) {
	            	errorList.add(new ValidMessage( this.getMessage("ETR_BTS_003")));
	            }
	            //验证转股券面总额是否大于可转换券面总额
	            if(dto.getStocketrPriceTotal()!=null) {
	            	 if(dto.getStocketrPriceTotal().compareTo(dto.getConetrPriceTotal())==1) {
	 	            	errorList.add(new ValidMessage( this.getMessage("ETR_BTS_004")));
	 	            }
	            }
	        }
	        return errorList;
	    }

	    public AjaxResult checkConfirmInfo(EtrConBondDto dto) {
	        String confirmMsg = "";
	        //如果头寸不足时
	        if (dto.getProAvailableCash().compareTo(dto.getPurchBondTotal()) < 0){
	            confirmMsg = "组合可用现金不足,当前最多可用金额为:<br/>" +
	                    dto.getProAvailableCash() + "，请确认是否继续？";
	        }
	        if (StringUtil.isNotBlank(confirmMsg)) {
	            AjaxResult wr = new AjaxResult();
	            wr.setData(confirmMsg);
	            return wr;
	        }
	        return null;
	    }
}
