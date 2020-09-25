/*
 * 文件名：Interf150005Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.prodquote.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.prodquote.dao.Interf150005Mapper;
import com.joyintech.service.interf.prodquote.entity.Interf150005InEntity;
import com.joyintech.service.interf.prodquote.entity.Interf150005InLvlEntity;
import com.joyintech.service.interf.prodquote.entity.Interf150005OutEntity;
import com.joyintech.service.interf.prodquote.entity.ParamMapEntity;
import com.joyintech.service.interf.prodtrade.dto.InterfFinProdDto;
import com.joyintech.service.interf.yield.YieldComponent;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 报价接口150005-详细处理
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月9日
 * @see Interf150005Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_150005_00)
public class Interf150005Service extends SendEsbService<Interf150005InEntity, Interf150005OutEntity> {

    /**
     * 报价接口150005-查询
     */
    @Autowired
    private Interf150005Mapper interf150005Mapper;

    /**
     * 报价接口150005-查询
     */
    @Autowired
    private YieldComponent yieldComponent;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * {@inheritDoc}
     */
    @Override
    public Interf150005InEntity preparedLocalData(Object param)
        throws Exception {
        InterfFinProdDto dto = (InterfFinProdDto)param;
        Interf150005InEntity inEntity = null;
        if (dto == null) {
            return inEntity;
        }
        finprodId = dto.getFinProdId();
        ParamMapEntity paramMap = new ParamMapEntity();
        paramMap.setFinProdId(finprodId);
        paramMap.setCdate(dto.getCdate());
        paramMap.setProdType(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        if (BussinessDictCodeList.处理模式_净值型.getValue().equals(dto.getType())) {
            inEntity = interf150005Mapper.selectInfoFromVal(paramMap);
            setYield(inEntity);
            inEntity.setHisYield(
                getYield(inEntity, InterfDictCodeList.涨幅_历史涨幅, InterfDictCodeList.周期值_历史));
        }
        else if (BussinessDictCodeList.处理模式_货币型.getValue().equals(dto.getType())) {
            inEntity = interf150005Mapper.selectInfoFromVal(paramMap);
        }
        else {
            inEntity = interf150005Mapper.selectInfoFromCal(paramMap);
            String isLay = interf150005Mapper.selectIsLay(finprodId);
            List<Interf150005InLvlEntity> lvlList = null;
            paramMap.setCashType(DictCodeListExt1.现金流二级类型_利息.getValue());
            if (DictCodeList.是否_是.getValue().equals(isLay)) {
                paramMap.setProdType(DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue());
                lvlList = interf150005Mapper.selectIsLayInfo(paramMap);
            }
            else {
                lvlList = interf150005Mapper.selectNoLayInfo(paramMap);
            }
            inEntity.setLvlList(lvlList);
        }
        if (inEntity != null) {
            // 设置销售费率
            paramMap.setCashType(DictCodeListExt1.现金流二级类型_销售费.getValue());
            inEntity.setSaleFeeRate(interf150005Mapper.selectFee(paramMap));
        }
        return inEntity;
    }

    /**
     * 取得涨跌收益率
     * 
     * @param inEntity 对象产品
     * @param type Z-涨幅；Y-年化涨幅
     * @param termValue 天数：1-日；7-周；30-月；90-季；180-半年；360-年
     * @return 涨跌收益率
     * @throws ParseException 解析异常
     * @see
     * @since 1.0
     */
    private String getYield(Interf150005InEntity inEntity, InterfDictCodeList type,
                            InterfDictCodeList termValue)
        throws ParseException {
        BigDecimal netVal = new BigDecimal(inEntity.getNetValue());
        String cDate = inEntity.getNetValueDate();
        return yieldComponent.getYield(finprodId, netVal, cDate, type.getValue(),
            termValue.getValue());
    }

    /**
     * 设置涨跌收益率
     * 
     * @param inEntity 对象产品
     * @throws ParseException 解析异常
     * @see
     * @since 1.0
     */
    private void setYield(Interf150005InEntity inEntity)
        throws ParseException {
        inEntity.setDailyIncrease(
            getYield(inEntity, InterfDictCodeList.涨幅_日涨幅, InterfDictCodeList.周期值_日));
        inEntity.setDailyYearIncrease(
            getYield(inEntity, InterfDictCodeList.涨幅_年涨幅, InterfDictCodeList.周期值_日));
        inEntity.setWeeklyIncrease(
            getYield(inEntity, InterfDictCodeList.涨幅_日涨幅, InterfDictCodeList.周期值_周));
        inEntity.setWeeklyYearIncrease(
            getYield(inEntity, InterfDictCodeList.涨幅_年涨幅, InterfDictCodeList.周期值_周));
        inEntity.setMonthIncrease(
            getYield(inEntity, InterfDictCodeList.涨幅_日涨幅, InterfDictCodeList.周期值_月));
        inEntity.setMonthYearIncrease(
            getYield(inEntity, InterfDictCodeList.涨幅_年涨幅, InterfDictCodeList.周期值_月));
        inEntity.setSeasonIncrease(
            getYield(inEntity, InterfDictCodeList.涨幅_日涨幅, InterfDictCodeList.周期值_季));
        inEntity.setSeasonYearIncrease(
            getYield(inEntity, InterfDictCodeList.涨幅_年涨幅, InterfDictCodeList.周期值_季));
        inEntity.setSemiannualIncrease(
            getYield(inEntity, InterfDictCodeList.涨幅_日涨幅, InterfDictCodeList.周期值_半年));
        inEntity.setSemiYearIncrease(
            getYield(inEntity, InterfDictCodeList.涨幅_年涨幅, InterfDictCodeList.周期值_半年));
        inEntity.setAnnaulIncrease(
            getYield(inEntity, InterfDictCodeList.涨幅_日涨幅, InterfDictCodeList.周期值_年));
        inEntity.setAnnaulYearIncrease(
            getYield(inEntity, InterfDictCodeList.涨幅_年涨幅, InterfDictCodeList.周期值_年));
        BigDecimal totalNetValue = new BigDecimal(inEntity.getTotalNetValue());
        inEntity.setEstablishUpDown(yieldComponent.sinceEstablishIncrease(totalNetValue));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(Interf150005OutEntity entity)
        throws Exception {
        // 推送接口无登录操作
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(Interf150005OutEntity entity)
        throws Exception {
        // 推送接口无登录操作
        return 0;
    }

}
