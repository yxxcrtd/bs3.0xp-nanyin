
package com.joyintech.tams.invest.trade.trdstockconnect.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.trade.common.dto.EtrInvestInfoDto;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.common.validator.TradeCommonValidator;
import com.joyintech.tams.invest.trade.trdstockconnect.dto.TrdStockConnectDto;
import com.joyintech.tams.invest.trade.trdstockconnect.service.TrdStockConnect002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 港股通交易保存提交check
 *
 * @author hechuan
 * @version 1.0 2021年4月14日
 * @see TrdStockConnectDtoValidator
 * @since 1.0
 */
@Component
public class TrdStockConnectDtoValidator implements TradeCommonValidator {

    /**
     * BussTrdTransferOrderService
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    /**
     * TrdBondInvestTrade002Service
     */
    @Autowired
    private TrdStockConnect002Service trdStockConnect002Service;
    
    @Autowired
	private TradeCommonService tradeCommonService;
    
//    @Autowired
//    private EtrStockConnect002Service etrStockConnect002Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
    	TrdStockConnectDto inputDto = (TrdStockConnectDto)value;
        List<ValidMessage> errorList = new ArrayList<>();


        // 交易金额 〉 0
        if (inputDto.getTradeAmt() != null && inputDto.getTradeAmt().compareTo(BigDecimal.ZERO) <= 0) {
            ValidMessage validMessage = new ValidMessage("tradeAmt",
                getMessage("BUSS_COM_E_0036", getMessage("TrdStock002.tradeAmt"), "0"));
            errorList.add(validMessage);
        }
        if (inputDto.getCpriceAmt() != null && inputDto.getUnitCprice().compareTo(BigDecimal.ZERO) <= 0) {
            ValidMessage validMessage = new ValidMessage("unitCprice",
                getMessage("BUSS_COM_E_0036", getMessage("TrdStock002.unitCprice"), "0"));
            errorList.add(validMessage);
        }
        if (inputDto.getCpriceAmt() != null && inputDto.getCpriceAmt().compareTo(BigDecimal.ZERO) <= 0) {
            ValidMessage validMessage = new ValidMessage("cpriceAmt",
                getMessage("BUSS_COM_E_0036", getMessage("TrdStock002.cpriceAmt"), "0"));
            errorList.add(validMessage);
        }
        if (inputDto.getFpriceAmt() != null && inputDto.getFpriceAmt().compareTo(BigDecimal.ZERO) <= 0) {
            ValidMessage validMessage = new ValidMessage("fpriceAmt",
                getMessage("BUSS_COM_E_0036", getMessage("TrdStock002.fpriceAmt"), "0"));
            errorList.add(validMessage);
        }

        // 划款指令
        if (bussTrdTransferOrderService != null) {
            List<ValidMessage> list = bussTrdTransferOrderService.check(
                inputDto.getBussTrdTransferOrder());
            errorList.addAll(list);
        }
        //交易对手必填
        if (StringUtil.equals(inputDto.getOperateType(),"save") || StringUtil.equals(inputDto.getOperateType(),"submit")){
        	if (StringUtil.isEmpty(inputDto.getTrdInvestPersonInfo().getCounterId())){
                errorList.add(new ValidMessage("trdInvestPersonInfo.counterId",getMessage("TRD_SPOTEX_006")));
            }
        }

        // 委托场合相关check
       if (StringUtil.isNotBlank(inputDto.getEntrustId())) {
            if (inputDto.getTradeAmt() != null) {
                // 查询的委托额度
            	TrdStockConnectDto entrustInfo = trdStockConnect002Service.getEntrustInfo(inputDto.getEntrustId());
            	if(StringUtil.isNotEmpty(entrustInfo.getTargetType()) && StringUtil.equals(DictCodeListExt3.目标类型_数量.getValue(), entrustInfo.getTargetType())){
            		//委托数量不为空时  校验委托数量
                    if (entrustInfo != null 
                        && entrustInfo.getEntrustNum() != null
                        && inputDto.getShareAmt().compareTo(
                            ArithmeticUtil.doSub(entrustInfo.getEntrustNum(),entrustInfo.getFinishAmt())) > 0) {
                        ValidMessage validMessage = new ValidMessage("shareAmt",
                            getMessage("TRD_W_0009", getMessage("TrdStock002.shareAmt")));
                        errorList.add(validMessage);
                    }
            	}
            	if(StringUtil.isNotEmpty(entrustInfo.getTargetType()) && StringUtil.equals(DictCodeListExt3.目标类型_金额.getValue(), entrustInfo.getTargetType())){
	                //委托金额不为空时 校验委托金额
	                if (entrustInfo != null 
	                    && entrustInfo.getEntrustAmt() != null
	                    && inputDto.getTradeAmt().compareTo(
	                        ArithmeticUtil.doSub(entrustInfo.getEntrustAmt(),entrustInfo.getFinishAmt())) > 0) {
	                    ValidMessage validMessage = new ValidMessage("tradeAmt",
	                        getMessage("TRD_W_0009", getMessage("TrdStock002.tradeAmt")));
	                    errorList.add(validMessage);
	                }
            	}
                //价格模式为指定价格   交易方向为 配售 新股中签 增发 精确委托  交易方向为 买入卖出时  可为限价模式  市价模式
                EtrInvestInfoDto  etrInvestInfoDto = tradeCommonService.findEtrInfo(inputDto.getEntrustId());
            	//价格模式为限价模式
            	//市价模式不校验
            	if(StringUtil.equals(DictCodeListExt2.价格模式_限价指令.getValue(), etrInvestInfoDto.getEtrEntrust().getPriceModel())){
                	// 委托场合时且交易方向为卖出
                    //3)	交易方向为卖出时，委托数量为上限，委托价格为下限；
                    if(StringUtil.equals(inputDto.getPs(), DictCodeList.交易方向_卖出.getValue())){
                    	if(inputDto.getUnitCprice().compareTo(entrustInfo.getPrice())<0){
                    		ValidMessage validMessage = new ValidMessage("unitCprice",
                                    "限价指令时，卖出时委托价格为下限!");
                    		errorList.add(validMessage);
                    	}
                    	
                    }
                    // 委托场合时且交易方向为买入 
                    //2)	交易方向为买入时，委托数量和委托价格为上限；
                    else if (StringUtil.equals(inputDto.getPs(), DictCodeList.交易方向_买入.getValue())){
                    	if(inputDto.getUnitCprice().compareTo(entrustInfo.getPrice())>0){
                    		ValidMessage validMessage = new ValidMessage("unitCprice",
                                    "限价指令时，买入时委托价格为上限!");
                    		errorList.add(validMessage);
                    	}
                    }
            	}
            	else if(StringUtil.equals(DictCodeListExt2.价格模式_指定价格.getValue(), etrInvestInfoDto.getEtrEntrust().getPriceModel())){
            		if(inputDto.getUnitCprice().compareTo(entrustInfo.getPrice())!=0){
                		ValidMessage validMessage = new ValidMessage("unitCprice",
                                "价格模式为指定价格时，交易时需要按照委托价格进行交易");
                		errorList.add(validMessage);
                	}
            	}
                
            }

         // 验证流程中是否存在该委托 
            if(StringUtil.isNotEmpty(inputDto.getEntrustId()) && WorkFlowUtil.isNotFromToDoList()){
            	String str = tradeCommonService.check(inputDto.getEntrustId(), "TrdStock002");
            	if(StringUtil.isNotEmpty(str)){ 
            		ValidMessage validMessage = new ValidMessage(str);
            		errorList.add(validMessage);
            	}
            }
        }
        //只有当交易方向为配股/增发（配售 ）时显示此字段，且必填 配售代码
        if((StringUtil.equals(inputDto.getPs(), DictCodeListExt2.交易方向_配股.getValue())||StringUtil.equals(inputDto.getPs(), DictCodeListExt2.交易方向_增发.getValue()))
        		&&StringUtil.isEmpty(inputDto.getSaleCode())){
        	ValidMessage validMessage = new ValidMessage(getMessage("TRD_STOCK_002"));
 			errorList.add(validMessage);
        }

        //卖出时，check可用持仓
        if(StringUtil.equals(inputDto.getPs(), DictCodeList.交易方向_卖出.getValue()) 
        		|| StringUtil.equals(inputDto.getPs(), DictCodeListExt2.交易方向_卖碎股.getValue())){
        	if (inputDto.getShareAmt() != null && inputDto.getBottomAssetAmount() != null
                    && inputDto.getShareAmt().compareTo(inputDto.getBottomAssetAmount()) > 0) {
                ValidMessage validMessage = new ValidMessage(
                        getMessage("BUSS_COM_E_0003", getMessage("TrdStock002.shareAmt"),
                                getMessage("TrdStock002.bottomAssetAmount")));
                errorList.add(validMessage);
            }
        }
        
        //证券账户是否港股通
/*		List<StlSecAcctInfo> listStl = etrStockConnect002Service.selectStlSecAcctInfo(inputDto.getPortfolioId());
		boolean ifHkseStlFlag = false;
		for(StlSecAcctInfo stlSecAcctInfo : listStl){
			if(StringUtil.equals(stlSecAcctInfo.getyNHkstockc(), DictCodeListExt3.是否开通港股通_是.getValue())){
				ifHkseStlFlag = true;
				break;
			}
		}
		if(ifHkseStlFlag == false){
			errorList.add(new ValidMessage("portfolioId","该组合未开通港股通交易，请确认！"));
		}*/
        return errorList;
    }

}
