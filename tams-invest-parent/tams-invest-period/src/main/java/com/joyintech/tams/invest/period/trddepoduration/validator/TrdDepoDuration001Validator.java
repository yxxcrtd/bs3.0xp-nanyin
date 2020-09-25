/*
 * 文件名：TrdDepoDuration001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.period.trddepoduration.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.period.trddepoduration.dto.TrdDepoDuration003Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trddepoduration.dto.TrdDepoDuration001Dto;
import com.joyintech.tams.invest.period.trddepoduration.service.TrdDepoDuration001Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年10月11日
 * @see TrdDepoDuration001Validator
 * @since 1.0
 */
@Component
public class TrdDepoDuration001Validator implements BeanValidator{
    @Autowired
    private PeriodCommonService periodCommonService;
    /**
     * 数据service
     */
    @Autowired
    private TrdDepoDuration001Service trdDepoDuration001Service;
    /**
     * 数据service
     */
    @Autowired(required = false)
    private MstWorkflowCommonService mstWorkflowCommonService;
    /**
     * 
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> messageList = new ArrayList<>();
        TrdDepoDuration001Dto dto = (TrdDepoDuration001Dto)value;
        Map<String,Object>map = new HashMap<>();
//        map.put("portfolioId", dto.getPortfolioId());
        map.put("finprodMarketId", dto.getFinprodMarketId());
        if((StringUtil.equals(dto.getOperateType(),"submit")||StringUtil.equals(dto.getOperateType(),"save"))) {
        	
            //活期存放结息方式为结转为本金时 no check
            if(StringUtil.equals(dto.getOpType(),"demand")){
            	if(dto.getRemainPrinAmt().compareTo(BigDecimal.ZERO) == 0){
            		messageList.add(new ValidMessage("资产剩余本金为0，无法做资产结息!"));
            	}
                if(StringUtil.equals(dto.getIntSettType(), DictCodeListExt3.结息方式_现金结息.getValue())){
                    messageList.addAll(bussTrdTransferOrderService.check(periodCommonService.transformOrderDto(dto.getBussTrdTransferOrder())));
                }
                if(StringUtil.isNotEmpty(dto.getFinprodId())&&null!=dto.getTradeDate()){
                    Map<String,Object> paramMap = new HashMap<String,Object>();
                    paramMap.put("finprodId",dto.getFinprodId());
                    paramMap.put("tradeDate",DateUtil.format(dto.getTradeDate()));
                    paramMap.put("ps",DictCodeList.交易方向_卖出.getValue());
                    paramMap.put("yes",DictCodeList.是否_是.getValue());
                    paramMap.put("tradeStatus",DictCodeListExt1.交易状态_初始.getValue());
                    if(trdDepoDuration001Service.countTradeForDemand(paramMap)>0){
                        messageList.add(new ValidMessage("已协议终止的资产不可以重新买入!"));
                    }
                }
            }
            else{
                messageList.addAll(bussTrdTransferOrderService.check(periodCommonService.transformOrderDto(dto.getBussTrdTransferOrder())));
            }
        }
        if(StringUtil.equals(dto.getOpType(),"draw")) {
        	if(!StringUtil.isNotEmpty(dto.getPortfolioId())) {
                messageList.add(new ValidMessage("portfolioId",getMessage("FIN_PROD_E_0001","提前支取金额")) );
            }
            if(!StringUtil.isNotEmpty(dto.getEarlyWithdrawalAmt())) {
                messageList.add(new ValidMessage("earlyWithdrawalAmt",getMessage("FIN_PROD_E_0001","提前支取金额")) );
            }
            if(!StringUtil.isNotEmpty(dto.getEarlyWithdrawalRate())) {
                messageList.add(new ValidMessage("earlyWithdrawalRate",getMessage("FIN_PROD_E_0001","提前支取利率")) );
            }
            if(!StringUtil.isNotEmpty(dto.getRemainPrinAmt())) {
                messageList.add(new ValidMessage("remainPrinAmt",getMessage("FIN_PROD_E_0015","剩余本金","0")) );
            }
            //提前支取金额 > 剩余本金
            if (StringUtil.isNotEmpty(dto.getEarlyWithdrawalAmt()) && StringUtil.isNotEmpty(dto.getRemainPrinAmt())){
                if (new BigDecimal(0).compareTo(dto.getEarlyWithdrawalAmt()) == 0 ){
                    messageList.add(new ValidMessage("earlyWithdrawalAmt",getMessage("FIN_PROD_E_0015","提前支取金额","0")) );
                }
                if (new BigDecimal(0).compareTo(dto.getRemainPrinAmt()) == 0 ){
                    messageList.add(new ValidMessage("remainPrinAmt",getMessage("FIN_PROD_E_0015","剩余本金","0")) );
                }
                if (dto.getEarlyWithdrawalAmt().compareTo(dto.getRemainPrinAmt()) > 0){
                    messageList.add(new ValidMessage("提前支取金额不能大于剩余本金！"));
                }
            }
            // 增加存放同业提前支取资金到账日和计息结束日不能小于平台日的check by 赵东东 20191210 start
            Date sysDate = DateUtil.platDateFromCache();
            if(StringUtil.equals(dto.getAssetType(),DictCodeList.存款类资产类型_存放同业.getValue())) {
            	if(StringUtil.isNotEmpty(dto.getTradeDate())&&DateUtil.compareDate(DateUtil.format(dto.getTradeDate()), DateUtil.format(sysDate))<0) {
            		messageList.add(new ValidMessage(getMessage("TRD_DEPO_100")));
            	}
            	if(StringUtil.isNotEmpty(dto.getMdateProd())&& DateUtil.compareDate(DateUtil.format(dto.getMdateProd()), DateUtil.format(sysDate))<0) {
            		messageList.add(new ValidMessage(getMessage("TRD_DEPO_101")));
            	}
            }
            // 增加存放同业提前支取资金到账日和计息结束日不能小于平台日的check by 赵东东 20191210  end
        }
        else{
            if(!StringUtil.isNotEmpty(dto.getIsClean())&&(StringUtil.equals(dto.getOperateType(),"submit")||StringUtil.equals(dto.getOperateType(),"save"))) {
                messageList.add(new ValidMessage("isClean",getMessage("FIN_PROD_E_0001",getMessage("TrdSecOverdueDuration002.isClean"))) );
            }
        }
        //update by  zhangnian  20191111 start
        if(!StringUtil.equals(dto.getOpType(), "revoke")){
            if((StringUtil.isNotEmpty(dto.getTradeDate())&&StringUtil.isNotEmpty(dto.getMdateProd())
                    &&DateUtil.compareDate(dto.getTradeDate(),dto.getMdateProd()) < 0)){
                messageList.add(new ValidMessage("tradeDate",getMessage("BUSS_COM_E_0004",getMessage("TrdDepoDuration001.tradeDate"),getMessage("TrdDepoDuration001.mdateProd"))) );
            }
            if((StringUtil.isNotEmpty(dto.getTradeDate())&&DateUtil.compareDate(dto.getTradeDate(),DateUtil.platDateFromCache()) < 0)){
                messageList.add(new ValidMessage("tradeDate",getMessage("BUSS_COM_E_0004",getMessage("TrdDepoDuration001.tradeDate"),"系统平台日")) );
            }
        }
        //update by  zhangnian  20191111 end
        if(StringUtil.equals(dto.getOpType(), "edit")) {
            map.put("statusValid", DictCodeListExt1.画面数据状态_待处理.getValue());
        }
        else if(StringUtil.equals(dto.getOpType(), "revoke")){
            map.put("statusValid", DictCodeListExt1.画面数据状态_有效.getValue());
        }
      //所有待处理数据
        List<TrdDepoDuration001Dto> allData = trdDepoDuration001Service.getAllDataForCheck(map);
        allData = allData.stream().sorted((x,y)->{
            return DateUtil.compareDate(y.getMdateProd(), x.getMdateProd());
        }).collect(Collectors.toList());
        //待处理校验
        if(WorkFlowUtil.isNotFromToDoList()&&StringUtil.equals(dto.getOpType(), "edit") &&allData.size()>0) {
//            校验其前置的现金流信息所对应的交易 是否在流程中
            Map<String,Object> tempMap = new HashMap<>();
            tempMap.put("finprodId",dto.getFinprodId());
            tempMap.put("finprodMarketId",dto.getFinprodMarketId());
            tempMap.put("mdate",DateUtil.format(dto.getMdateProd()));
            List<String>tradeList = periodCommonService.getFlowDeal(tempMap);
            if(tradeList!=null && tradeList.size()>0){
                messageList.add(new ValidMessage(getMessage("BUSS_COM_E_0027",GlobalConstant.SPLIT+getMessage("TrdLendDuration001.busiCode"), StringUtil.joinString(tradeList, GlobalConstant.COMMA))));
            }
            if(DateUtil.compareDate(dto.getMdateProd(),allData.get(allData.size()-1).getMdateProd())!=0) {
                messageList.add(new ValidMessage(getMessage("FIN_ASSET_E_0020")));
            }
            if(mstWorkflowCommonService != null) {
                WflValidInputParameter param =new WflValidInputParameter();
                param.setPageId("TrdDepoDuration002");
                param.setBusinessDataPk2(dto.getFinprodMarketId());
                param.setBusinessDataPk3(dto.getPortfolioId());
                param.setBusinessDataPk4(DateUtil.format(dto.getMdateProd()));
                if(mstWorkflowCommonService.isInWflProcess(param)) {
                    messageList.add(new ValidMessage(getMessage("BUSS_COM_E_0023")));
                }
            }
        }
        //撤销校验
        if(WorkFlowUtil.isNotFromToDoList()&&StringUtil.equals(dto.getMode(), "revoke")){
            TrdDepoDuration003Dto dto003 = new TrdDepoDuration003Dto();
            BeanUtil.copyPropertiesIgnoreNull(dto003, dto);
            if (trdDepoDuration001Service.checkBusinessDataInFlow(dto003)) {
                messageList.add(new ValidMessage(this.getMessage("COMMON_E_0012", dto.getBusiCodeTemp())));
                return messageList;
            }
            if(DateUtil.compareDate(dto.getMdateProd(),allData.get(0).getMdateProd())!=0) {
                messageList.add(new ValidMessage(getMessage("FIN_ASSET_E_0021")));
            }
            //存放同业到期，无法进行撤销操作
            if(!StringUtil.isEmptyList(dto.getSelectedItem())&&StringUtil.equals(dto.getAssetType(), DictCodeList.存款类资产类型_存放同业.getValue())){
                //设计这里这么写的
                messageList.add(new ValidMessage("资产类型为“存放同业”，不允许撤销。"));
            }
//                需判断其在现金流对账模块是否完成匹配，如果是已匹配状态，需要先取消匹配才允许撤销
            else if(trdDepoDuration001Service.countCashMatch(dto.getBusiCode())>0){
                messageList.add(new ValidMessage(getMessage("FIN_ASSET_E_0005")));
            }
//          校验活期存款结息其所对应的交易 是否在流程中
            if(mstWorkflowCommonService != null) {
                WflValidInputParameter param =new WflValidInputParameter();
                param.setPageId("TrdDepoDuration004");
                param.setBusinessDataPk1(dto.getBusiCode());
/*                param.setBusinessDataPk2(dto.getFinprodMarketId());
                param.setBusinessDataPk3(dto.getPortfolioId());
                param.setBusinessDataPk4(DateUtil.format(dto.getMdateProd()));*/
                if(mstWorkflowCommonService.isInWflProcess(param)) {
                    messageList.add(new ValidMessage(getMessage("BUSS_COM_E_0023")));
                }

            }
        }
        return messageList;
    }

}
