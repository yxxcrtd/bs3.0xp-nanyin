/*
 * 文件名：BondAutoDurDealGenerateProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.cfetsbond.service;

import java.util.*;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.batch.autodurdealgenerate.cfetsbond.dao.CfetsBondAutoDurDealGenerateMapper;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.invest.batch.common.dto.BatchBussSettleOrderParameterDto;
import com.joyintech.tams.invest.batch.common.service.PinganBatchCommonService;
import com.joyintech.tams.invest.common.autoperiod.bond.AutoPeriodBondCommonService;
import com.joyintech.tams.invest.common.autoperiod.common.AutoPeriodCommonService;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.trdbondduration.TrdBondDurDbInertDto;
import com.joyintech.tams.invest.common.dto.period.trdbondduration.TrdBondDurationDto;

/**
 * processor相关业务处理
 *
 * @author etliu
 * @version 1.0 2018年12月11日
 * @see CfetsBondAutoDurDealGenerateProcessorService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
@Scope("prototype")
public class CfetsBondAutoDurDealGenerateProcessorService extends BaseService {

    /**
     * PinganBatchCommonService
     */
    @Autowired
    private PinganBatchCommonService pinganBatchCommonService;

    /**
     * AutoPeriodBondCommonService
     */
    @Autowired
    private AutoPeriodBondCommonService autoPeriodBondCommonService;

    /**
     * AutoPeriodCommonService
     */
    @Autowired
    private AutoPeriodCommonService autoPeriodCommonService;

    @Autowired
    private CfetsBondAutoDurDealGenerateMapper cfetsBondAutoDurDealGenerateMapper;

    /**
     * AutoPeriodCommonService
     */
    @Autowired(required = false)
    private BaseGetBussinessId baseGetBussinessId;

    /**
     * process 数据处理
     *
     * @param batchDate 传递参数
     * @param productIdList 传递参数
     * @return BookGenerateResult
     * @see
     * @since 1.0
     */
    public void execute(Date batchDate, List<String> productIdList) {

        //删除当日数据
        autoPeriodCommonService.deleteActData(DictCodeList.共通业务类型_债券类资产存续期.getValue(),
                DictCodeListExt1.金融产品类型_投管用_债券.getValue(),batchDate);

        // 待处理交易数据参数
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("statusValid", Arrays.asList(DictCodeListExt1.画面数据状态_待处理.getValue()));
        paramMap.put("userName", LoginUserUtil.getUserName());
        paramMap.put("PLATEFORM_DATE", batchDate);
        // 查询待处理交易详细数据
        this.setParams(paramMap);
        List<TrdBondDurationDto> tradeDataList = cfetsBondAutoDurDealGenerateMapper.findAllData(paramMap);

        //查询行权赎回待处理数据
        paramMap.put("psT39", DictCodeList.交易方向_行权_赎回.getValue());
        List<RegFinProductDealTotal> regFinProductDealTotalList = cfetsBondAutoDurDealGenerateMapper.findShData(paramMap);

        List<TrdBondDurationDto> trdBondDurationDtoInsertList = new ArrayList<TrdBondDurationDto>();
        for(TrdBondDurationDto trdBondDurationDto: tradeDataList){
            //重新查询单条记录数据
            paramMap.put("finprodId", trdBondDurationDto.getFinprodId());
            paramMap.put("finprodMarketId",trdBondDurationDto.getFinprodMarketId());
            //调整后的计算结束日
            paramMap.put("mDate", trdBondDurationDto.getMDate());
            paramMap.put("vDate", trdBondDurationDto.getVDate());
            paramMap.put("secAcctId", trdBondDurationDto.getSecManageAcctId());
            paramMap.put("adjustMDate", DateUtil.parse(trdBondDurationDto.getMDate()));

            TrdBondDurationDto pendingTradeData = cfetsBondAutoDurDealGenerateMapper.findEffectiveData(paramMap);

            //paramMap.put("busiCode", trdBondDurationDto.getBusiCode());
            paramMap.put("yield", pendingTradeData.getYield());
            List<FinAssetIntInfo> finAssetIntInfos = cfetsBondAutoDurDealGenerateMapper.findAssetIntInfo(paramMap);
            // 实收本息总额: 设置为 全价总额 理论本息总额
            pendingTradeData.setTradeAmt(pendingTradeData.getFpriceAmt());
            BussinessCodeParameter parameter = new BussinessCodeParameter();
            parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
            pendingTradeData.setBusiCode(autoPeriodCommonService.buildBussinessCode(parameter));

            pendingTradeData.setFinAssetIntInfoList(finAssetIntInfos);

             // 构造将要登录的业务流程表数据
             genDataForInsertDto(pendingTradeData);

             trdBondDurationDtoInsertList.add(pendingTradeData);
        }

        for(RegFinProductDealTotal regFinProductDealTotal : regFinProductDealTotalList){
            // 构造将要登录的业务流程表数据
            TrdBondDurationDto TrdBondDurationDto = genDataForShInsertDto(regFinProductDealTotal,batchDate);

            trdBondDurationDtoInsertList.add(TrdBondDurationDto);
        }
        //插入数据
        for(TrdBondDurationDto trdBondDurationDto: trdBondDurationDtoInsertList){
            autoPeriodCommonService.insertCfetsDealData(trdBondDurationDto.getTrdBondDurDbInertDto());
        }
    }

    private TrdBondDurationDto genDataForShInsertDto(RegFinProductDealTotal regFinProductDealTotal,Date batchDate) {
        TrdBondDurDbInertDto trdBondDurDbInertDto = new TrdBondDurDbInertDto();
        TrdBondDurationDto trdBondDurationDto =new  TrdBondDurationDto();
        //查询登记明细相关表
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("totalTradeId", regFinProductDealTotal.getTotleTradeId());
        List<RegFinProductDeal> regFinProductDealList = cfetsBondAutoDurDealGenerateMapper.queryRegDeallnfo(map);

        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        List<TrdProductDeal> trdProductDealList = new ArrayList<TrdProductDeal>();
        List<StlPayOrders> stlPayOrdersList =new ArrayList<StlPayOrders>();
        List<PtlTrade> ptlTradeList =new ArrayList<PtlTrade>();

        BeanUtil.copyPropertiesIgnoreNull(trdFinProductDealTotal, regFinProductDealTotal);
        trdFinProductDealTotal.setTradeDate(DateUtil.parse(DateUtil.format(batchDate)));
        trdFinProductDealTotal.setSettleDate(DateUtil.parse(DateUtil.format(batchDate)));

        for(RegFinProductDeal regFinProductDeal : regFinProductDealList){
            TrdProductDeal trdProductDeal = new TrdProductDeal();
            BeanUtil.copyPropertiesIgnoreNull(trdProductDeal,regFinProductDeal);
            trdProductDeal.setTradeDate(DateUtil.parse(DateUtil.format(batchDate)));
            trdProductDeal.setSettleDate(DateUtil.parse(DateUtil.format(batchDate)));
            trdProductDealList.add(trdProductDeal);

            //组装划款指令表
            StlPayOrders stlPayOrders = new StlPayOrders();
            BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
            bussinessCodeParameter.setSeqNo("9998");
            String orderId;
            if(baseGetBussinessId != null){
                orderId = baseGetBussinessId.buildBussinessCode(bussinessCodeParameter);
            }else{
                orderId = IDGenerator.generate();
            }
            stlPayOrders.setOrderId(orderId);
            stlPayOrders.setPayType(DictCodeListExt1.平安划款类型_托管划款.getValue());
            String acctNo = autoPeriodCommonService.selectDealCapacctInfo(regFinProductDealTotal.getSecManageAcctId(), trdFinProductDealTotal.getCcy());
            stlPayOrders.setMyAcctNo(acctNo);
            stlPayOrders.setSettleDate(DateUtil.parse(DateUtil.format(batchDate)));
            stlPayOrders.setPs(DictCodeListExt1.收支方向_收入.getValue());
            stlPayOrders.setSettleAmt(regFinProductDealTotal.getTradeAmt());
            stlPayOrders.setCcy(regFinProductDealTotal.getCcy());
            stlPayOrders.setSettleStatus(DictCodeList.结算状态_已结算.getValue());
            stlPayOrders.setIsNeedSend(DictCodeListExt1.是否_是.getValue());
            stlPayOrders.setIsTotalTrade(DictCodeListExt1.是否_是.getValue());
            stlPayOrders.setTradeId(regFinProductDealTotal.getTotleTradeId());
            stlPayOrders.setOrderStatus(DictCodeListExt1.指令状态_新建.getValue());

            stlPayOrdersList.add(stlPayOrders);

            //组装组合交易表
            PtlTrade ptlTrade2 = new PtlTrade();
            ptlTrade2.setPortfolioId(regFinProductDeal.getPortfolioId());
            ptlTrade2.setBusiNo(regFinProductDeal.getTradeId());
            ptlTrade2.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
            ptlTrade2.setInvAim(regFinProductDeal.getInvAim());
            ptlTrade2.setBusiId(regFinProductDeal.getFinprodId());
            ptlTrade2.setBusiType(DictCodeList.共通业务类型_债券类资产存续期.getValue());
            ptlTrade2.setSecManageAcctId(regFinProductDeal.getSecManageAcctId());
            ptlTrade2.setSettleDate(DateUtil.parse(DateUtil.format(batchDate)));

            ptlTradeList.add(ptlTrade2);
        }

        trdBondDurDbInertDto.setTrdFinProductDealTotal(trdFinProductDealTotal);
        trdBondDurDbInertDto.setTrdProductDealList(trdProductDealList);
        trdBondDurDbInertDto.setStlPayOrdersList(stlPayOrdersList);
        trdBondDurDbInertDto.setPtlTrades(ptlTradeList);

        trdBondDurationDto.setTrdBondDurDbInertDto(trdBondDurDbInertDto);

        return trdBondDurationDto;
    }

    /**
     * 构造将要登录的业务流程表数据
     *
     * @param entity 参数dto
     * @see
     * @since 1.0
     */
    private void genDataForInsertDto(TrdBondDurationDto entity) {
        autoPeriodBondCommonService.genBondDataForInsertDtoNew(entity);

        TrdBondDurDbInertDto trdBondDurDbInertDto = entity.getTrdBondDurDbInertDto();

        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        PaTrdFinProductDealTotal paTrdFinProductDealTotal =new PaTrdFinProductDealTotal();
        List<TrdProductDeal> trdProductDealList = new ArrayList<TrdProductDeal>();
        List<TrdProductDealAdd> trdProductDealAddList =new ArrayList<TrdProductDealAdd>();
        List<StlPayOrders> stlPayOrdersList =new ArrayList<StlPayOrders>();

        BeanUtil.copyPropertiesIgnoreNull(trdFinProductDealTotal, trdBondDurDbInertDto.getWflTrdFinProductDealTotal());
        BeanUtil.copyPropertiesIgnoreNull(paTrdFinProductDealTotal, trdBondDurDbInertDto.getWflPaTrdFinDealTotal());
        for(WflTrdProductDeal wflTrdProductDeal : trdBondDurDbInertDto.getWflTrdProductDealList()){
            TrdProductDeal trdProductDeal = new TrdProductDeal();
            BeanUtil.copyPropertiesIgnoreNull(trdProductDeal,wflTrdProductDeal);
            trdProductDealList.add(trdProductDeal);
        }
        for(WflTrdProductDealAdd wflTrdProductDealAdd : trdBondDurDbInertDto.getWflTrdProductDealAdds()){
            TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
            BeanUtil.copyPropertiesIgnoreNull(trdProductDealAdd,wflTrdProductDealAdd);
            trdProductDealAddList.add(trdProductDealAdd);
        }
        for(WflStlPayOrders wflStlPayOrders : trdBondDurDbInertDto.getWflStlPayOrdersList()){
            StlPayOrders stlPayOrders = new StlPayOrders();
            BeanUtil.copyPropertiesIgnoreNull(stlPayOrders,wflStlPayOrders);
            stlPayOrdersList.add(stlPayOrders);
        }

        trdBondDurDbInertDto.setTrdFinProductDealTotal(trdFinProductDealTotal);
        trdBondDurDbInertDto.setPaTrdFinProductDealTotal(paTrdFinProductDealTotal);
        trdBondDurDbInertDto.setTrdProductDealList(trdProductDealList);
        trdBondDurDbInertDto.setTrdProductDealAdds(trdProductDealAddList);
        trdBondDurDbInertDto.setStlPayOrdersList(stlPayOrdersList);

        entity.setTrdBondDurDbInertDto(trdBondDurDbInertDto);
    }

    /**
     * 生成划款指令
     *
     * @param entity 交易数据
     * @return 划款指令
     * @see
     * @since 1.0
     */
    private List<WflStlPayOrders> gen划款指令流程(TrdBondDurationDto entity) {
        // 自动生成划款指令用deal
        TrdProductDeal trdProductDeal = AutoPeriodBondCommonService.getTrdProductDeal(entity);
        BatchBussSettleOrderParameterDto parameterDto = new BatchBussSettleOrderParameterDto();
        parameterDto.setIsTotalTrade(DictCodeList.是否_是.getValue());
        parameterDto.setBankLendingType(null);
        parameterDto.setPortfolioId(entity.getPortfolioId());
        parameterDto.setDeal(trdProductDeal);

        return pinganBatchCommonService.generateSettleOrder(parameterDto);
    }

    private void setParams(Map<String, Object> param) {
        param.put("CASH_TYPE_1_02", DictCodeListExt1.现金流一级类型_利息.getValue());
        param.put("CASH_TYPE_2_C02", DictCodeListExt1.现金流二级类型_利息.getValue());
        param.put("PAGE_DATE_STATUS01", DictCodeListExt1.画面数据状态_待处理.getValue());
        param.put("PAGE_DATE_STATUS03", DictCodeListExt1.画面数据状态_流程中.getValue());
        param.put("PAGE_DATE_STATUS05", DictCodeListExt1.画面数据状态_有效.getValue());
        param.put("PAGE_DATE_STATUS06", DictCodeListExt1.画面数据状态_撤销.getValue());
        param.put("CASH_CLASS_TYPE01", DictCodeList.现金流类型_中间收息.getValue());
        param.put("CASH_CLASS_TYPE02", DictCodeList.现金流类型_中间还本.getValue());
        param.put("CASH_CLASS_TYPE03", DictCodeList.现金流类型_到期还本.getValue());
        param.put("CASH_CLASS_TYPE04", DictCodeList.现金流类型_到期收息.getValue());
        // 数据字典缓存.交易状态.初始
        param.put("TRADE_STATUS01", DictCodeListExt1.交易状态_初始.getValue());
        param.put("TRADE_STATUS03", DictCodeListExt1.交易状态_撤销.getValue());
        param.put("YESNO_N", DictCodeList.是否_否.getValue());
        param.put("YESNO_Y", DictCodeList.是否_是.getValue());
        // 数据字典缓存.持仓类型.实际持仓
        param.put("POSITION_TYPE03", DictCodeList.持仓类型_实际.getValue());
        // 数据字典缓存.金融产品类型 投管分类.债券
        param.put("FINPROD_TYPE_TGF01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        param.put("TRD10", DictCodeList.共通业务类型_债券类资产存续期.getValue());
        param.put("STATUS_N", DictCodeList.数据状态_新建.getValue());
        //String plateDate = platformDateImpl.getSysDateStr();
        //param.put("PLATEFORM_DATE", DateUtil.parse(plateDate));
    }
}
