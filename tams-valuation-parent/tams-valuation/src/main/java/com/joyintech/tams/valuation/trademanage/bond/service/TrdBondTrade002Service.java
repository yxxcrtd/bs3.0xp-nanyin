package com.joyintech.tams.valuation.trademanage.bond.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.interf.base.dto.AssetDealInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.sec.SecVestAssetDealTemplateRecService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSecVestAssetDeal;
import com.joyintech.tams.valuation.trademanage.bond.dto.TrdBondTradeResult;
import com.joyintech.tams.valuation.trademanage.bond.dto.TrdBondTrade002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.h2.mvstore.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class TrdBondTrade002Service extends CommonExportService<TrdBondTrade002Dto> implements BaseProcessService<TrdBondTrade002Dto> {

    @Autowired
    private SecVestAssetDealTemplateRecService secVestAssetDealTemplateRecService;

    @Autowired
    private TrdBondTradeCommonServer trdBondTradeCommonServer;

    /**
     * 通过资产代码查询资产简称和交易场所
     *
     * @return 产品代码列表
     * @see
     * @since 1.0
     */
    public FinProduct selectInfoBySecId(Map<String, Object> map) {
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdBondTrade002/selectInfoBySecId", map,
                FinProduct.class);
    }

    /**
     * 通过交易编号查询交易信息（修改）
     *
     * @return 产品代码列表
     * @see
     * @since 1.0
     */
    public TrdBondTrade002Dto queryTradeInfoByTradeId(String tradeId) {
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdBondTrade002/queryTradeInfoByTradeId", tradeId,
                TrdBondTrade002Dto.class);
    }

    /**
     * 查询证券托管户：联想框文本框
     *
     * @param parameters 画面参数
     * @return 证券托管户：联想框文本框
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getSecManageAcctId(Map<String, Object> parameters) {
         return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdBondTrade002/getSecManageAcctId", parameters, SelectOptionDto.class);
    }

    public Page<SelectOptionDto> getChannelId(Map<String, Object> pageParam) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/TrdBondTrade002/getChannelId",
                pageParam, SelectOptionDto.class);
    }

    //自增序列号
    public int querySequences(){
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/TrdBondTrade002/querySequences", null,Integer.class);
    }


    @Override
    public void doComplete(TrdBondTrade002Dto trdBondTrade002Dto, OperationType operationType) {
        TrdBondTradeResult trdBondTradeResult = generateEntity(trdBondTrade002Dto);
        String action = StringUtil.EMPTY;
         if (OperationType.NEW.equals(operationType)) {
            //insert(secDealInfoProcessorResult);
             action = "insert";
        }
        else {
            action = "update";
        }
        restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdBondTrade002/" + action, trdBondTradeResult,
                Integer.class);

    }

//    public int insert(SecDealInfoProcessorResult1 dto){
//        List<SecDealInfoProcessorResult1> item = new ArrayList<>();
//        item.add(dto);
////        try {
////            secDealInfoRecWriter.write(item);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//        return 1;
//    }
//
//    public int update(SecDealInfoProcessorResult1 dto){
//      return  insert(dto);
//    }



    //封装实体类
    public TrdBondTradeResult generateEntity(TrdBondTrade002Dto trdBondTrade002Dto){
        //将页面输入值赋值给接口实体类
        List<ValSecVestAssetDeal> listValSecVestAssetDeal = new ArrayList<ValSecVestAssetDeal>();
        ValSecVestAssetDeal valSecVestAssetDeal = new ValSecVestAssetDeal();
        BeanUtil.copyPropertiesIgnoreNull(valSecVestAssetDeal,trdBondTrade002Dto);
        valSecVestAssetDeal.setDealDate(DateUtil.parse(trdBondTrade002Dto.getTradeDate()));
        valSecVestAssetDeal.setSettdateDate(DateUtil.parse(trdBondTrade002Dto.getSettdateDate()));
        valSecVestAssetDeal.setFundSettleDate(DateUtil.parse(trdBondTrade002Dto.getFundSettleDate()));
        //比较界面和接口字段差异，并手动给接口字段赋值
        valSecVestAssetDeal.setAssetId("F01_"+trdBondTrade002Dto.getSecId());//资产代码
        valSecVestAssetDeal.setDataActionSign("01");
        valSecVestAssetDeal.setStlForm("03");
        valSecVestAssetDeal.setParValue(new BigDecimal(100));
        valSecVestAssetDeal.setFeeAmtUnpay(new BigDecimal(0));
        valSecVestAssetDeal.setYield(new BigDecimal(1));
        valSecVestAssetDeal.setCounterId("FYDLCA365001");
        valSecVestAssetDeal.setCounterType("01");
        valSecVestAssetDeal.setTrader("wujiankang");
        valSecVestAssetDeal.setFprice(new BigDecimal(101.4383));
        valSecVestAssetDeal.setUnitInt(new BigDecimal(2.264547945205));
        valSecVestAssetDeal.setInvestAim(trdBondTrade002Dto.getInvAim());
        valSecVestAssetDeal.setIsStampDuty("Y");
        valSecVestAssetDeal.setIsBrokerage("Y");
        valSecVestAssetDeal.setIsCertfFee("Y");
        valSecVestAssetDeal.setIsStlAmt("Y");
        valSecVestAssetDeal.setIsCommission("Y");
        valSecVestAssetDeal.setIsDealFeeAmt("Y");
        valSecVestAssetDeal.setIsStlFeeAmt("Y");
        valSecVestAssetDeal.setIsChlFeeAmt("Y");
        valSecVestAssetDeal.setRegistAgency("01");
        valSecVestAssetDeal.setIsClean("N");
        valSecVestAssetDeal.setExeYield(new BigDecimal(0.0395000820535));
        valSecVestAssetDeal.setSharesTradeMarket("02");

        listValSecVestAssetDeal.add(valSecVestAssetDeal);

        return trdBondTradeCommonServer.generateEntity(trdBondTrade002Dto,listValSecVestAssetDeal);

    }

}
