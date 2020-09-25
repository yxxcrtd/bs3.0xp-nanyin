package com.joyintech.tams.invest.entrust.etrforwex.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.entrust.common.validator.EtrCommonValidator;
import com.joyintech.tams.invest.entrust.etrforwex.dto.EtrForwexDto;
import com.joyintech.tams.invest.entrust.etrforwex.service.EtrForwexService;

@Component
public class EtrForwexValidator implements EtrCommonValidator{

	@Autowired
    private EtrForwexService etrForwexService;
	 
	@Override
	public List<ValidMessage> bussValid(Object value) {
		// TODO Auto-generated method stub

        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        EtrForwexDto etrForwexDto = (EtrForwexDto)value;

        if (StringUtil.equals(etrForwexDto.getOperateType(), "delete")) {//删除
            List<EtrForwexDto> selectItem = etrForwexDto.getSelectedItem();
            for(EtrForwexDto temp:selectItem){
               temp.setEntrustOperationType(DictCodeListExt1.委托操作类型_删除.getValue());
               errorList.addAll(etrForwexService.check(temp));
            }
        }
        else if (StringUtil.equals(etrForwexDto.getOperateType(), "recall")) {//撤销
            List<EtrForwexDto> selectItem = etrForwexDto.getSelectedItem();
            for(EtrForwexDto temp:selectItem){
                temp.setEntrustOperationType(DictCodeListExt1.委托操作类型_撤回.getValue());
                errorList.addAll(etrForwexService.check(temp));
             }
        }
        else if (StringUtil.equals(etrForwexDto.getOperateType(), "close")) {//关闭
            List<EtrForwexDto> selectItem = etrForwexDto.getSelectedItem();
            for(EtrForwexDto temp:selectItem){
                temp.setEntrustOperationType(DictCodeListExt1.委托操作类型_关闭.getValue());
                temp.setCloseWay(DictCodeListExt1.委托关闭方式_人工.getValue());
                errorList.addAll(etrForwexService.check(temp));
             }

        }

        else if (StringUtil.equals(etrForwexDto.getOperateType(), "edit")) {
            if (!(DictCodeList.委托状态_已撤回.getValue().equals(etrForwexDto.getEntrustStatus())
                  || DictCodeList.委托状态_已退回.getValue().equals(etrForwexDto.getEntrustStatus()))) {
                errorList.add(new ValidMessage(
                    // “新建”、“已撤回”、“已退回”
                    this.getMessage("ETR_E_0009", this.getMessage("ETR_LEND_001"))));
            }
        }
        else {
//        //新建或者编辑
//        if(StringUtil.equals(etrForwexDto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)
//        		||StringUtil.equals(etrForwexDto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){

        	//期限，交割日 选填一个
            if(!StringUtil.isNotEmpty(etrForwexDto.getTermType())&&!StringUtil.isNotEmpty(etrForwexDto.getSettleDate())){
                errorList.add(new ValidMessage(StringUtil.isNotEmpty(etrForwexDto.getTermType())?"settleDate":"termType", getMessage("ETR_FORWEX_001")));
            }
            
            //委托开始日不能大于截止日
            if(StringUtil.isNotEmpty(etrForwexDto.getVdate()) && StringUtil.isNotEmpty(etrForwexDto.getMdate())){
                if(etrForwexDto.getVdate().compareTo(etrForwexDto.getMdate()) == 1){
                    errorList.add(new ValidMessage("vdate", getMessage("ETR_X_005")));
                }
            }
            
            //交易对手非空校验
            if(!StringUtil.isNotEmpty(etrForwexDto.getBussEtrInvestPersonInfoDto().getCounterId())){
                errorList.add(new ValidMessage("bussEtrInvestPersonInfoDto.counterId", "请输入交易对手！"));
            }
            
            //委托金额 大于0校验
            if(StringUtil.isNotEmpty(etrForwexDto.getEntrustAmt())&&BigDecimal.ZERO.compareTo(etrForwexDto.getEntrustAmt())>0){
                errorList.add(new ValidMessage("entrustNum", getMessage("ETR_X_008", getMessage("EtrForwex001.entrustAmt"))));
            }
            
            //当是否差额清算选择为是时，货币对只可选择USD/CNY、EUR/CNY、JPY/CNY、GBP/CNY，期限只可选择1W、1M、3M、6M 和1Y
            if(DictCodeList.是否_是.getValue().equals(etrForwexDto.getIsDifSqu())) {
            	//货币对校验
            	if(!DictCodeList.货币对_USD_CNY.getValue().equals(etrForwexDto.getCurPair())&&
            			!DictCodeList.货币对_EUR_CNY.getValue().equals(etrForwexDto.getCurPair())&&
            			!DictCodeList.货币对_JPY_CNY.getValue().equals(etrForwexDto.getCurPair())&&
            			!DictCodeList.货币对_GBP_CNY.getValue().equals(etrForwexDto.getCurPair())) {
                    errorList.add(new ValidMessage("curPair", getMessage("ETR_FORWEX_002")));
            	}
            	//外汇期限校验
            	if(!DictCodeListExt2.外汇期限_1W.getValue().equals(etrForwexDto.getTermType())&&
            			!DictCodeListExt2.外汇期限_1M.getValue().equals(etrForwexDto.getTermType())&&
            			!DictCodeListExt2.外汇期限_3M.getValue().equals(etrForwexDto.getTermType())&&
            			!DictCodeListExt2.外汇期限_6M.getValue().equals(etrForwexDto.getTermType())&&
            			!DictCodeListExt2.外汇期限_1Y.getValue().equals(etrForwexDto.getTermType())) {
                    errorList.add(new ValidMessage("termType", getMessage("ETR_FORWEX_004")));
            	}
            	
            	//人民币账户非空校验
                if(!StringUtil.isNotEmpty(etrForwexDto.getCnyAcct())){
                    errorList.add(new ValidMessage("cnyAcct", getMessage("ETR_X_012", getMessage("EtrForwex002.cnyAcct"))));
                }
            }
            
            //是否差额清算为否时，
            if(DictCodeList.是否_否.getValue().equals(etrForwexDto.getIsDifSqu())) {
            	//交易货币账户非空校验
                if(!StringUtil.isNotEmpty(etrForwexDto.getDealCurAcct())){
                    errorList.add(new ValidMessage("dealCurAcct", getMessage("ETR_X_012", getMessage("EtrForwex002.dealCurAcct"))));
                }
                //对应货币账户非空校验
                if(!StringUtil.isNotEmpty(etrForwexDto.getCorCurAcct())){
                    errorList.add(new ValidMessage("corCurAcct",getMessage("ETR_X_012", getMessage("EtrForwex002.corCurAcct"))));
                }
            }
            
//            if(StringUtil.isNotEmpty(etrForwexDto.getCurPair())){
//                //根据交易方向，货币对，交易币种，对应币种  校验可用持仓
//                String[] curArr = etrForwexDto.getCurPair().split("/");
//                String cur1 = "";
//                //交易方向为买/卖时
//                if(DictCodeList.交易方向_买入.getValue().equals(etrForwexDto.getPs())) {
//                	//非基准货币（货币对后一个币种）金额不大于可用持仓 
//                    cur1 = curArr[1];
//                }
//                //交易方向为卖/买时
//                if(DictCodeList.交易方向_卖出.getValue().equals(etrForwexDto.getPs())) {
//                	//基准货币（货币对前一个币种）金额不大于可用持仓
//                	cur1 = curArr[0];
//                }
//                //交易币种  需要判断交易
//                if(cur1.equals(etrForwexDto.getCcy())) {
//                	//可用持仓 小于金额
//                	if(etrForwexDto.getAccAvailableCash()!=null&&etrForwexDto.getEntrustAmt()!=null
//                			&&etrForwexDto.getAccAvailableCash().compareTo(etrForwexDto.getEntrustAmt())<0) {
//                        errorList.add(new ValidMessage("entrustAmt", getMessage("ETR_X_011", getMessage("EtrForwex001.entrustAmt"))));
//                	};
//                }
//                
//                //对应币种	
//                if(cur1.equals(etrForwexDto.getCorCcy())) {
//                	//可用持仓 小于金额
//                	if(etrForwexDto.getCorCurPos()!=null&&etrForwexDto.getExpCorAmt()!=null
//                			&&etrForwexDto.getCorCurPos().compareTo(etrForwexDto.getExpCorAmt())<0) {
//                        errorList.add(new ValidMessage("expCorAmt", getMessage("ETR_X_011", getMessage("EtrForwex002.corCurPos"))));
//                	};
//                }
//            }
        }
        
		return errorList;
	}

}
