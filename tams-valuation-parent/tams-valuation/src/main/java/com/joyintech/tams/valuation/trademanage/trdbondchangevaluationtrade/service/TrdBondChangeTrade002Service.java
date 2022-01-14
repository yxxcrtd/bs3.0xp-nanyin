package com.joyintech.tams.valuation.trademanage.trdbondchangevaluationtrade.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.sec.SecVestAssetDealTemplateRecService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFundChangeDeal;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSecVestAssetDeal;
import com.joyintech.tams.valuation.trademanage.trdbondchangevaluationtrade.dto.TrdBondChangeTrade002Dto;
import com.joyintech.tams.valuation.trademanage.trdbondchangevaluationtrade.dto.TrdBondChangeTradeResult;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TrdBondChangeTrade002Service extends CommonExportService<TrdBondChangeTrade002Dto> implements BaseProcessService<TrdBondChangeTrade002Dto> {

    @Autowired
    private SecVestAssetDealTemplateRecService secVestAssetDealTemplateRecService;

    @Autowired
    private TrdBondChangeTradeCommonServer trdBondChangeTradeCommonServer;

    @Autowired
    private TrdBondChangeTradeFundServer trdBondChangeTradeFundServer;


    /**
     * 通过资产代码查询资产简称和交易场所
     *
     * @return 产品代码列表
     * @see
     * @since 1.0
     */
    public FinProduct selectInfoBySecId(Map<String, Object> map) {
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdBondChangeTrade002/selectInfoBySecId", map,
                FinProduct.class);
    }

    /**
     * 通过交易编号查询交易信息（修改）
     *
     * @return 产品代码列表
     * @see
     * @since 1.0
     */
    public TrdBondChangeTrade002Dto queryTradeInfoByTradeId(String tradeId) {
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdBondChangeTrade002/queryTradeInfoByTradeId", tradeId,
                TrdBondChangeTrade002Dto.class);
    }

    /**
     *
     * 交易方向为债转股时，转出证券代码下拉框显示可转债资产
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getSecExgId(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/TrdBondChangeTrade002/getSecExgId" , map,
                SelectOptionDto.class);
    }

    /**
     *
     * 交易方向为基金转换时，转出证券代码下拉框显示基金
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFundId(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/TrdBondChangeTrade002/getFundId" , map,
                SelectOptionDto.class);
    }

    /**
     * 展示股票代码和基金
     *
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getCAssetId(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME
                        + "/TrdBondChangeTrade002/getCAssetId" , map,
                SelectOptionDto.class);
    }

    //自增序列号
    public int querySequences(){
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/TrdBondChangeTrade002/querySequences", null,Integer.class);
    }


    @Override
    public void doComplete(TrdBondChangeTrade002Dto trdBondChangeTrade002Dto, OperationType operationType) {
        String action = StringUtil.EMPTY;
         if (OperationType.NEW.equals(operationType)) {
             action = "insert";
        }
        else {
            action = "update";
        }

        TrdBondChangeTradeResult result = null;

        if(DictCodeListExt1.交易方向_债转股.getValue().equals(trdBondChangeTrade002Dto.getPs())){
            result = generateEntity(trdBondChangeTrade002Dto);
        }else if(DictCodeList.交易方向_上市流通.getValue().equals(trdBondChangeTrade002Dto.getPs())){

        }else if(DictCodeListExt3.交易方向_转换申购确认.getValue().equals(trdBondChangeTrade002Dto.getPs())){
            result = generateEntity1(trdBondChangeTrade002Dto);
        }

        restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdBondChangeTrade002/" + action, result,
                Integer.class);

    }

    //封装实体类(债转股)
    public TrdBondChangeTradeResult generateEntity(TrdBondChangeTrade002Dto dto){
        //将页面输入值赋值给接口实体类
        List<ValSecVestAssetDeal> listValSecVestAssetDeal = new ArrayList<ValSecVestAssetDeal>();
        ValSecVestAssetDeal entity = new ValSecVestAssetDeal();
        //BeanUtil.copyPropertiesIgnoreNull(entity,dto);
        entity.setTradeId(dto.getTradeId());
        entity.setProdId(dto.getProdId());
        entity.setSecId(dto.getoAssetId());
        entity.setSecAbbr(dto.getoAssetAbbr());
        entity.setDealDate(DateUtil.parse(dto.getTradeDate()));
        entity.setSettdateDate(DateUtil.parse(dto.getSettdateDate()));
        entity.setPs(dto.getPs());
        entity.setDealNum(dto.getoTradeNum());
        entity.setCcy(dto.getCcy());
        entity.setInvestAim(dto.getInvAim());
        entity.setTradeAmt(dto.getoTradeAmt());
        entity.setSharesCode(dto.getcAssetId());
        entity.setSharesNum(dto.getcTradeNum());
        entity.setExpConValue(dto.getcTradeAmt());
        //比较界面和接口字段差异，并手动给接口字段赋值
        entity.setAssetId("F01_"+dto.getoAssetId());//资产代码
        entity.setDataActionSign("01");
        entity.setTradeMarket("01");
        entity.setStlForm("03");
        entity.setParValue(new BigDecimal(100));
        entity.setFeeAmtUnpay(new BigDecimal(0));
        entity.setYield(new BigDecimal(1));
        entity.setCounterId("FYDLCA365001");
        entity.setCounterType("01");
        entity.setTrader("wujiankang");
        entity.setSecManageAcctId("111111111");
        entity.setCprice(new BigDecimal(101.4383));
        entity.setFprice(new BigDecimal(101.4383));
        entity.setUnitInt(new BigDecimal(2.264547945205));
        entity.setIntAmt(new BigDecimal(100));
        entity.setCpriceAmt(new BigDecimal(100));
        entity.setFpriceAmt(new BigDecimal(100));
        entity.setIsStampDuty("Y");
        entity.setIsBrokerage("Y");
        entity.setIsCertfFee("Y");
        entity.setIsStlAmt("Y");
        entity.setIsCommission("Y");
        entity.setIsDealFeeAmt("Y");
        entity.setIsStlFeeAmt("Y");
        entity.setIsChlFeeAmt("Y");
        entity.setRegistAgency("01");
        entity.setIsClean("N");
        entity.setExeYield(new BigDecimal(0.0395000820535));
        entity.setSharesTradeMarket("02");

        listValSecVestAssetDeal.add(entity);

        return trdBondChangeTradeCommonServer.generateEntity(dto,listValSecVestAssetDeal);

    }

    //封装实体类(基金转换)
    public TrdBondChangeTradeResult generateEntity1(TrdBondChangeTrade002Dto dto){
        List<ValFundChangeDeal> listValFundChangeDeal = new ArrayList<ValFundChangeDeal>();
        ValFundChangeDeal entity = new ValFundChangeDeal();
       //BeanUtil.copyPropertiesIgnoreNull(valFundChangeDeal,dto);
        entity.setTradeId(dto.getTradeId());
        entity.setProdId(dto.getProdId());
        entity.setAssetId(dto.getoAssetId());
        entity.setAssetName(dto.getoAssetAbbr());
        entity.setCcy(dto.getCcy());
        entity.setPs(dto.getPs());
        entity.setTradeDate(DateUtil.parse(dto.getTradeDate()));
        entity.setShareAmt(dto.getoTradeNum());
        entity.setPrinAmt(dto.getoTradeAmt());
        entity.setTradeAmt(dto.getoTradeAmt());
        entity.setcAssetId(dto.getcAssetId());
        //比较界面和接口字段差异，并手动给接口字段赋值
        entity.setDataActionSign("01");
        entity.setTradeMarket("01");
        entity.setUnitCprice(new BigDecimal(1));
        entity.setIntAmt(new BigDecimal(100));

        listValFundChangeDeal.add(entity);
        return trdBondChangeTradeFundServer.generateEntity(dto,listValFundChangeDeal);
    }

}
