/*
 * 文件名：TrdStockDtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年7月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.stockdeal.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.component.business.trade.dto.BussTrdTransfenpeiDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.trade.bond.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.trade.bond.dto.TrdBondInvestTradeDto;
import com.joyintech.tams.invest.trade.bond.service.TrdBondInvestTrade002Service;
import com.joyintech.tams.invest.trade.common.dto.EtrInvestInfoDto;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.common.validator.TradeCommonValidator;
import com.joyintech.tams.invest.trade.stockdeal.dto.TrdStockDto;
import com.joyintech.tams.invest.trade.stockdeal.service.TrdStock002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 股票交易保存提交check
 *
 * @author wangpeng
 * @version 1.0 2019年7月29日
 * @see TrdStockDtoValidator
 * @since 1.0
 */
@Component
public class TrdStockDtoValidator implements TradeCommonValidator {

    /**
     * BussTrdTransferOrderService
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    /**
     * TrdBondInvestTrade002Service
     */
    @Autowired
    private TrdStock002Service trdStock002Service;
    
    @Autowired
	private TradeCommonService tradeCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
    	TrdStockDto inputDto = (TrdStockDto)value;
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


        // 银行间成交编号 条件必填
//        if (StringUtil.equals(DictCodeListExt1.交易场所_银行间.getValue(),
//            inputDto.getTradeMarket())) {
//            if (StringUtil.isBlank(inputDto.getfTradeId())) {
//                ValidMessage validMessage = new ValidMessage("fTradeId",
//                    getMessage("FW_E_0006", getMessage("TrdBondInvestTrade002.fTradeId")));
//                errorList.add(validMessage);
//            }
//        }

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

        // 画面.交割日 > 标的类金融产品.到期日 时，提交报错
//        if (inputDto.getSettleDate() != null && inputDto.getMdate() != null) {
//            if (DateUtil.compareDate(inputDto.getSettleDate(), inputDto.getMdate()) > 0) {
//                ValidMessage validMessage = new ValidMessage("settleDate",
//                    getMessage("BUSS_COM_E_0035", getMessage("TrdStock002.settleDate"),
//                        getMessage("TrdStock002.mdate")));
//                errorList.add(validMessage);
//            }
//        }

        // 12、 交易场所为银行间时,附件需上传成交单
       /* if (StringUtil.equals(DictCodeListExt1.交易场所_银行间.getValue(),
            inputDto.getTradeMarket())) {
            if (StringUtil.isEmptyList(inputDto.getUploadFileList())) {
                ValidMessage validMessage = new ValidMessage(
                    getMessage("FW_E_0006", getMessage("FileCommon")));
                errorList.add(validMessage);
            }
        }*/


        // 委托场合相关check
       if (StringUtil.isNotBlank(inputDto.getEntrustId())) {
            if (inputDto.getTradeAmt() != null) {
                // 查询的委托额度
                TrdStockDto entrustInfo = trdStock002Service.getEntrustInfo(inputDto.getEntrustId());
                //委托数量不为空时  校验委托数量
                if (entrustInfo != null 
                    && entrustInfo.getEntrustNum() != null
                    && inputDto.getShareAmt().compareTo(
                        ArithmeticUtil.doSub(entrustInfo.getEntrustNum(),entrustInfo.getFinishAmt())) > 0) {
                    ValidMessage validMessage = new ValidMessage("shareAmt",
                        getMessage("TRD_W_0009", getMessage("TrdStock002.shareAmt")));
                    errorList.add(validMessage);
                }
                //委托金额不为空时 校验委托金额
                if (entrustInfo != null 
                    && entrustInfo.getEntrustAmt() != null
                    && inputDto.getTradeAmt().compareTo(
                        ArithmeticUtil.doSub(entrustInfo.getEntrustAmt(),entrustInfo.getFinishAmt())) > 0) {
                    ValidMessage validMessage = new ValidMessage("tradeAmt",
                        getMessage("TRD_W_0009", getMessage("TrdStock002.tradeAmt")));
                    errorList.add(validMessage);
                }
                //委托数量不为空时校验
//                if(entrustInfo.getEntrustNum() != null){
//                	// 委托场合时且交易方向为卖出
//                    //3)	交易方向为卖出时，委托数量为上限，委托价格为下限；
//                    if(StringUtil.equals(inputDto.getPs(), DictCodeList.交易方向_卖出.getValue())){
//                    	if(inputDto.getUnitCprice().compareTo(entrustInfo.getPrice())<0){
//                    		ValidMessage validMessage = new ValidMessage("shareAmt",
//                                    getMessage("TRD_STOCK_007", getMessage("TrdStock002.shareAmt")));
//                    		errorList.add(validMessage);
//                    	}
//                    	
//                    }
//                    // 委托场合时且交易方向为买入 
//                    //2)	交易方向为买入时，委托数量和委托价格为上限；
//                    else if (StringUtil.equals(inputDto.getPs(), DictCodeList.交易方向_买入.getValue())){
//                    	if(inputDto.getUnitCprice().compareTo(entrustInfo.getPrice())>0){
//                    		ValidMessage validMessage = new ValidMessage("shareAmt",
//                                    getMessage("TRD_STOCK_008", getMessage("TrdStock002.shareAmt")));
//                    		errorList.add(validMessage);
//                    	}
//                    }else{
//                    	// 委托场合时且交易方向为 配售 新股中签 增发
//                    	//4)	交易方向为配股/增发（配售）、新股中签时，委托数量为精确数量，委托价格为精确价格；
//                    	if(inputDto.getUnitCprice().compareTo(entrustInfo.getPrice())!=0
//                    		||inputDto.getShareAmt().compareTo(entrustInfo.getEntrustNum())!=0){
//                    		ValidMessage validMessage = new ValidMessage("shareAmt",
//                                    getMessage("TRD_STOCK_009", getMessage("TrdStock002.shareAmt")));
//                    		errorList.add(validMessage);
//                    	}
//                    	
//                    }
//                }
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

        /*//证券托管户 与通道二选一
        if(("".equals(inputDto.getChlId())||inputDto.getChlId()==null)&&("".equals(inputDto.getSecManageAcctId())||inputDto.getSecManageAcctId()==null)){
        	ValidMessage validMessage = new ValidMessage(getMessage("TRD_STOCK_001",getMessage("invest.channelId")));
        	errorList.add(validMessage);
        }*/
        //只有上市板为科创板的才可选择盘后定价交易
        if(StringUtil.equals(inputDto.getDealMode(), DictCodeListExt2.外汇交易模式_盘后定价交易.getValue())
        		&&!StringUtil.equals(inputDto.getBoard(), DictCodeList.上市板_科创板.getValue())){
        	ValidMessage validMessage = new ValidMessage(getMessage("TRD_STOCK_003"));
        	errorList.add(validMessage);
        }
        //交易方式为大宗交易专场时可选，其他默认为交易日不可改；
        if(!StringUtil.equals(inputDto.getTradeDateStr(), inputDto.getSettleDateStr())
        		&&!StringUtil.equals(inputDto.getDealMode(), DictCodeListExt2.外汇交易模式_大宗交易专场.getValue())){
        	ValidMessage validMessage = new ValidMessage(getMessage("TRD_STOCK_004"));
        	errorList.add(validMessage);
        }
        //是/否，交易方向为新股中签，或交易方式为大宗交易/大宗交易专场时可选择，其他情况默认为否不可改；
        if((StringUtil.equals(inputDto.getWithLockPeriod(), DictCodeListExt2.是否有锁定期_是.getValue())) 
        		&&(StringUtil.equals(inputDto.getPs(), DictCodeListExt2.交易方向_新股中签.getValue())
        				||StringUtil.equals(inputDto.getDealMode(), DictCodeListExt2.外汇交易模式_大宗交易.getValue())
        				||StringUtil.equals(inputDto.getDealMode(), DictCodeListExt2.外汇交易模式_大宗交易专场.getValue()) )){
        	ValidMessage validMessage = new ValidMessage(getMessage("TRD_STOCK_006"));
        	errorList.add(validMessage);
        }
        
        //是否锁定为是时必填；是否锁定为否时为空不可填；
        if(StringUtil.equals(inputDto.getWithLockPeriod(), DictCodeListExt2.是否有锁定期_是.getValue())
        		&&StringUtil.isEmpty(inputDto.getLockMdateStr())){
        	ValidMessage validMessage = new ValidMessage(getMessage("TRD_STOCK_005"));
        	errorList.add(validMessage);
        }

        /*// add by 裴家俊 2019-05-23--start----
        if(StringUtil.equals(inputDto.getOperateType(), "save") || StringUtil.equals(inputDto.getOperateType(), "submit")){
            if(inputDto.getTrdInvestPersonInfo() != null && StringUtil.isEmpty(inputDto.getTrdInvestPersonInfo().getCounterId())){
                ValidMessage validMessage = new ValidMessage("trdInvestPersonInfo.counterId",getMessage("FW_E_0006", getMessage("TrdDepositInvestTrade002.cno")));
                errorList.add(validMessage);
            }
        }
        // add by 裴家俊 2019-05-23--end----
*/
        //卖出时，check可用持仓
        if(StringUtil.equals(inputDto.getPs(), DictCodeList.交易方向_卖出.getValue())){
        	if (inputDto.getShareAmt() != null && inputDto.getBottomAssetAmount() != null
                    && inputDto.getShareAmt().compareTo(inputDto.getBottomAssetAmount()) > 0) {
                ValidMessage validMessage = new ValidMessage(
                        getMessage("BUSS_COM_E_0003", getMessage("TrdStock002.shareAmt"),
                                getMessage("TrdStock002.bottomAssetAmount")));
                errorList.add(validMessage);
            }
        }
        return errorList;
    }

}
