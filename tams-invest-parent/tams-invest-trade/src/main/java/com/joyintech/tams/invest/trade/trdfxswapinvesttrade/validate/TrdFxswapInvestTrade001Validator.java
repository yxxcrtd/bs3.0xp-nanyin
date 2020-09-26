/*
 * 文件名：TrdFxswapInvestTrade001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年6月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.trdfxswapinvesttrade.validate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.EtrFinishStatus;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.trade.common.dto.EtrInvestInfoDto;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.common.validator.TradeCommonValidator;
import com.joyintech.tams.invest.trade.lend.service.TrdLendInvestTrade001Service;
import com.joyintech.tams.invest.trade.spotex.dto.TrdSpotexInvestTradeDto;
import com.joyintech.tams.invest.trade.stockdeal.dto.TrdStockDto;
import com.joyintech.tams.invest.trade.trdfxswapinvesttrade.dto.TrdFxswapInvestTrade001Dto;
import com.joyintech.tams.invest.trade.trdfxswapinvesttrade.service.TrdFxswapInvestTrade002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 外汇掉期交易验证
 * 
 * @author renping
 * @version 1.0 2019年6月25日
 * @see TrdFxswapInvestTrade001Validator
 * @since 1.0
 */
@Transactional
@Component
public class TrdFxswapInvestTrade001Validator implements TradeCommonValidator {

    /**
     * 外汇掉期
     */
    @Autowired
    private TrdFxswapInvestTrade002Service trdFxswapInvestTrade002Service;

    /**
     * 指令信息service
     */
    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    
    /**
     * TrdLendInvestTrade001Service
     */
    @Autowired
    private TrdLendInvestTrade001Service trdLendInvestTrade001Service;
    
    @Autowired
	private TradeCommonService tradeCommonService;
    
    @Override
    public List<ValidMessage> bussValid(Object value) {
        ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
        TrdFxswapInvestTrade001Dto dto = (TrdFxswapInvestTrade001Dto)value;
        
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            if (dto.getTrdInvestPersonInfo002Dto() == null
                || StringUtil.isEmpty(dto.getTrdInvestPersonInfo002Dto().getCounterId())) {
                errorList.add(new ValidMessage("trdInvestPersonInfo002Dto.counterId",
                    getMessage("FW_E_0006", getMessage("TrdFxswapInvestTrade002.counterId"))));
            }
        }
        if (dto.getOperateType().equals("submit") || dto.getOperateType().equals("save")) {
        	//中债新增 20200407 start
            //持有目的
            if(StringUtil.isEmpty(dto.getHoldPurpose())){
                ValidMessage validMessage = new ValidMessage("holdPurpose",
                        getMessage("FW_E_0006", getMessage("TrdBondLendInvestTrade002.holdPurpose")));
                errorList.add(validMessage);
            }else if(StringUtil.equals(DictCodeListExt1.持有目的_其他.getValue(), dto.getHoldPurpose())){
            	if (StringUtil.isEmpty(dto.getRemark())) {
            		ValidMessage validMessage = new ValidMessage("remark",
            				getMessage("FW_E_0006", getMessage("TrdBondLendInvestTrade002.remark")));
            		errorList.add(validMessage);
    			}
    		}
            //中债新增 20200407 end
            if (StringUtil.isNotEmpty(dto.getPortfolioId())) {
                // String isGuar =
                // trdFxswapInvestTrade002Service.getIsGuarantee(dto.getPortfolioId());//查询是否保本
                // if(StringUtil.isNotEmpty(isGuar) && StringUtil.equals(isGuar,
                // DictCodeList.是否_是.getValue())
                // && StringUtil.isEmptyList(dto.getInterfSendOpicsList()) ){
                // errorList.add(new
                // ValidMessage("interfSendOpicsList",getMessage("FW_E_0006",getMessage("TrdFxswapInvestTrade002.InterfSendOpicsList"))));
                // }else if(StringUtil.isNotEmpty(isGuar) && StringUtil.equals(isGuar,
                // DictCodeList.是否_否.getValue())
                // && StringUtil.isEmptyList(dto.getBussTrdTransferOrder()) ){
                // errorList.add(new
                // ValidMessage("bussTrdTransferOrder",getMessage("FW_E_0006",getMessage("TrdFxswapInvestTrade002.bussTrdTransferOrder"))));
                // }
            }
//            if (StringUtil.isEmptyList(dto.getFileArray())) {
//                errorList.add(new ValidMessage("fileArray",
//                    getMessage("FW_E_0006", getMessage("TrdFxswapInvestTrade002.fileCommon"))));
//            }
        }
        else if (dto.getOperateType().equals("delete")) {
        	
        	TrdFxswapInvestTrade001Dto selDto = dto.getSelectedItem().get(0);
            // 存在流程中数据
            if (WorkFlowUtil.isNotFromToDoList()) {
                if (trdLendInvestTrade001Service.isInFlowDataExisted(selDto.getTradeId())) {
                    ValidMessage validMessage = new ValidMessage(
                        getMessage("COMMON_E_0012", selDto.getTradeId()));
                    errorList.add(validMessage);
                }
            }

            // 删除状态不允许撤回
            if (trdLendInvestTrade001Service.isRevokeDataExisted(selDto.getTradeId())) {
                ValidMessage validMessage = new ValidMessage(getMessage("TRD_W_0001",
                    getMessage("pingan.common.revoke"), getMessage("pingan.common.revoke")));
                errorList.add(validMessage);
            }
            
            // 不允许撤销交易日在平台日之前的交易
            Date sysDate = DateUtil.platDateFromCache();
            if(DateUtil.compareDate(selDto.getTradeDate(), sysDate)<0) {
            	ValidMessage validMessage = new ValidMessage(
                        getMessage("TRD_REVOKE_COMMON_001"));
                    errorList.add(validMessage);
            }
            
        }
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            errorList.addAll(bussTrdTransferOrderService.check(dto.getBussTrdTransferOrder()));
            // 远端期限应大于近端期限，意思是除非标准期限外，远端期限的枚举值应在对应近端期限之后
            if (StringUtil.isNotEmpty(dto.getnTermTypeExc())
                && StringUtil.isNotEmpty(dto.getfTermTypeExc())
                && !StringUtil.equals(dto.getnTermTypeExc(),
                    DictCodeListExt2.外汇期限_非标准期限.getValue())
                && !StringUtil.equals(dto.getfTermTypeExc(),
                    DictCodeListExt2.外汇期限_非标准期限.getValue())
                && StringUtil.equals(dto.getExcFxsTermType(),
                    DictCodeListExt2.外汇期限类型_远期对远期掉期.getValue())) {
                DataDictItem nDictItem = SysDataUtil.getDataDictItem(DictCode.外汇期限.getValue(),
                    dto.getnTermTypeExc());
                DataDictItem fDictItem = SysDataUtil.getDataDictItem(DictCode.外汇期限.getValue(),
                    dto.getfTermTypeExc());
                if (Integer.valueOf(nDictItem.getOrderNo()) >= Integer.valueOf(
                    fDictItem.getOrderNo())) {
                    // 远端期限应大于近端期限
                    errorList.add(new ValidMessage("fTermTypeExc",
                        getMessage("BUSS_COM_E_0036",
                            getMessage("TrdFxswapInvestTrade002.fTermTypeExc"),
                            getMessage("TrdFxswapInvestTrade002.nTermTypeExc"))));
                }
            }
            if (dto.getMdate() != null && dto.getVdate() != null
                && !(DateUtil.compareDate(dto.getMdate(), dto.getVdate()) > 0)) {
                errorList.add(new ValidMessage("mdate",
                    getMessage("BUSS_COM_E_0036", getMessage("TrdFxswapInvestTrade002.mdate"),
                        getMessage("TrdFxswapInvestTrade002.vdate"))));
            }
            if (dto.getVdate() != null && dto.getTradeDate() != null
                && DateUtil.compareDate(dto.getVdate(), dto.getTradeDate()) < 0) {
                errorList.add(new ValidMessage("vdate",
                    getMessage("BUSS_COM_E_0004", getMessage("TrdFxswapInvestTrade002.vdate"),
                        getMessage("TrdFxswapInvestTrade002.tradeDate"))));
            }
            if (StringUtil.isNotEmpty(dto.getEntrustId())) {
                TrdFxswapInvestTrade001Dto entrustInfo = trdFxswapInvestTrade002Service.getDealInfoByEntrustId(
                    dto.getEntrustId());
                if (entrustInfo != null && StringUtil.isNotEmpty(entrustInfo.getUnitCprice())) {
                    BigDecimal diffPoint = ArithmeticUtil.doSub(dto.getfPoint(), dto.getnPoint());
                    String param1 = "（" + getMessage("TrdFxswapInvestTrade002.fPoint") + " - "
                                    + getMessage("TrdFxswapInvestTrade002.nPoint") + "）";
                    String param2 = entrustInfo.getUnitCprice().toString()
                                    + getMessage("TrdFxswapInvestTrade002.point");
                    // 交易方向为卖/买时，掉期点为上限
                    if (StringUtil.equals(dto.getPs(), DictCodeListExt2.交易方向_卖买.getValue())) {
                        if (entrustInfo.getUnitCprice().compareTo(diffPoint) < 0) {
                            errorList.add(new ValidMessage("fPoint",
                                getMessage("BUSS_COM_E_0003", param1, param2)));
                        }
                    }
                    // 交易方向为买/卖时，掉期点为下限
                    else {
                        if (entrustInfo.getUnitCprice().compareTo(diffPoint) > 0) {
                            errorList.add(new ValidMessage("fPoint",
                                getMessage("BUSS_COM_E_0004", param1, param2)));
                        }
                    }
                }
                
             // 验证流程中是否存在该委托 
                if(StringUtil.isNotEmpty(dto.getEntrustId()) && WorkFlowUtil.isNotFromToDoList()){
                	String str = tradeCommonService.check(dto.getEntrustId(), "TrdFxswapInvestTrade002");
                	if(StringUtil.isNotEmpty(str)){ 
                		ValidMessage validMessage = new ValidMessage(str);
                		errorList.add(validMessage);
                	}
                	EtrInvestInfoDto etrInvestInfoDto = tradeCommonService.findEtrInfo(dto.getEntrustId());
                	BigDecimal finishAmt = BigDecimal.ZERO;
                	if(etrInvestInfoDto.getEtrFinishStatusList()!=null){
                		for(EtrFinishStatus fin:etrInvestInfoDto.getEtrFinishStatusList()){
                			finishAmt=ArithmeticUtil.doAdd(finishAmt,fin.getFinishAmt());
                		}
                	}
                	 if (dto.getnDealCurAmt() != null 
                			 && dto.getnDealCurAmt().compareTo(ArithmeticUtil.subtract(etrInvestInfoDto.getEtrEntrust().getEntrustAmt(), finishAmt)) > 0) {
                         errorList.add(new ValidMessage("nDealCurAmt",getMessage("交易货币金额不能大于剩余委托金额")));
                     }
                }
            }
            // //买入的时候可用现金必须大于交易本金
            // if(DictCodeList.交易方向_买入.getValue().equals(dto.getPs())){
            // if(dto.getAccAvailableCashT() != null && dto.getPrinAmt() != null
            // && dto.getAccAvailableCashT().compareTo(dto.getPrinAmt()) < 0){
            // errorList.add(new
            // ValidMessage("prinAmt",getMessage("BUSS_COM_E_0036",getMessage("TrdFxswapInvestTrade002.accAvailableCashT"),getMessage("TrdFxswapInvestTrade001.tradeAmt"))));
            // }
            // }
            // 卖出时持仓必须大于交易金额
            // if(DictCodeList.交易方向_卖出.getValue().equals(dto.getPs())){
            // if(dto.getBottomAssetAmount() != null && dto.getPrinAmt() != null
            // && dto.getBottomAssetAmount().compareTo(dto.getPrinAmt()) < 0){
            // errorList.add(new
            // ValidMessage("prinAmt",getMessage("BUSS_COM_E_0036",getMessage("TrdFxswapInvestTrade002.bottomAssetAmount"),getMessage("TrdFxswapInvestTrade001.tradeAmt"))));
            // }
            // }
        }
        return errorList;
    }
}
