package com.joyintech.tams.valuation.accounadjustmanage.assistaccout.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.accounadjustmanage.assistaccout.dto.ValAssistAccounting002Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.sec.SecVestAssetDealTemplateRecService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValAssistAccounting002Service extends CommonExportService<ValAssistAccounting002Dto> implements BaseProcessService<ValAssistAccounting002Dto> {

    @Autowired
    private SecVestAssetDealTemplateRecService secVestAssetDealTemplateRecService;

    @Autowired
    private ValAssistAccountingCommonServer trdBondTradeCommonServer;




    @Override
    public void doComplete(ValAssistAccounting002Dto valAssistAccounting002Dto, OperationType operationType) {
        String action = StringUtil.EMPTY;
         if (OperationType.NEW.equals(operationType)) {
            //insert(secDealInfoProcessorResult);
             action = "insert";
        }
        else {
            action = "update";
        }
        restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssistAccounting002/" + action, valAssistAccounting002Dto,
                Integer.class);

    }





//    //封装实体类
//    public TrdBondTradeResult generateEntity(ValAssistAccounting002Dto ValAssistAccounting002Dto){
//        //将页面输入值赋值给接口实体类
//        List<ValSecVestAssetDeal> listValSecVestAssetDeal = new ArrayList<ValSecVestAssetDeal>();
//        ValSecVestAssetDeal valSecVestAssetDeal = new ValSecVestAssetDeal();
//        BeanUtil.copyPropertiesIgnoreNull(valSecVestAssetDeal,ValAssistAccounting002Dto);
//        valSecVestAssetDeal.setDealDate(DateUtil.parse(ValAssistAccounting002Dto.getTradeDate()));
//        valSecVestAssetDeal.setSettdateDate(DateUtil.parse(ValAssistAccounting002Dto.getSettdateDate()));
//        valSecVestAssetDeal.setFundSettleDate(DateUtil.parse(ValAssistAccounting002Dto.getFundSettleDate()));
//        //比较界面和接口字段差异，并手动给接口字段赋值
//        valSecVestAssetDeal.setAssetId("F01_"+ValAssistAccounting002Dto.getSecId());//资产代码
//        valSecVestAssetDeal.setDataActionSign("01");
//        valSecVestAssetDeal.setStlForm("03");
//        valSecVestAssetDeal.setParValue(new BigDecimal(100));
//        valSecVestAssetDeal.setFeeAmtUnpay(new BigDecimal(0));
//        valSecVestAssetDeal.setYield(new BigDecimal(1));
//        valSecVestAssetDeal.setCounterId("FYDLCA365001");
//        valSecVestAssetDeal.setCounterType("01");
//        valSecVestAssetDeal.setTrader("wujiankang");
//        valSecVestAssetDeal.setFprice(new BigDecimal(101.4383));
//        valSecVestAssetDeal.setUnitInt(new BigDecimal(2.264547945205));
//        valSecVestAssetDeal.setInvestAim(ValAssistAccounting002Dto.getInvAim());
//        valSecVestAssetDeal.setIsStampDuty("Y");
//        valSecVestAssetDeal.setIsBrokerage("Y");
//        valSecVestAssetDeal.setIsCertfFee("Y");
//        valSecVestAssetDeal.setIsStlAmt("Y");
//        valSecVestAssetDeal.setIsCommission("Y");
//        valSecVestAssetDeal.setIsDealFeeAmt("Y");
//        valSecVestAssetDeal.setIsStlFeeAmt("Y");
//        valSecVestAssetDeal.setIsChlFeeAmt("Y");
//        valSecVestAssetDeal.setRegistAgency("01");
//        valSecVestAssetDeal.setIsClean("N");
//        valSecVestAssetDeal.setExeYield(new BigDecimal(0.0395000820535));
//        valSecVestAssetDeal.setSharesTradeMarket("02");
//
//        listValSecVestAssetDeal.add(valSecVestAssetDeal);
//
//        return trdBondTradeCommonServer.generateEntity(ValAssistAccounting002Dto,listValSecVestAssetDeal);
//
//    }

}
