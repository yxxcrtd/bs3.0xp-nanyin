/*
 * 文件名：Interf448103Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i448103.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.i448103.dao.Interf448103Mapper;
import com.joyintech.service.interf.i448103.entity.Interf448103InEntity;
import com.joyintech.service.interf.i448103.entity.Interf448103OutEntity;
import com.joyintech.service.interf.ifeerate.entity.FeeRateEntity;
import com.joyintech.service.interf.ifeerate.service.FeeRateService;
import com.joyintech.service.interf.prodtrade.dto.InterfFinProdDto;
import com.joyintech.service.interf.yield.YieldComponent;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 448103上传万分收益和单位净值 处理详细
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月13日
 * @see Interf448103Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_448103_01)
public class Interf448103Service extends SendEsbService<Interf448103InEntity, Interf448103OutEntity> {

    /**
     * 448103查询mapper-查询货币产品/净值型产品的,万份收益/单位净值
     */
    @Autowired
    private Interf448103Mapper mapper;

    /**
     * 查询费率
     */
    @Autowired
    private FeeRateService feeService;

    /**
     * 计算年化收益率和涨跌幅计算
     */
    @Autowired
    private YieldComponent yComponent;

    /**
     * {@inheritDoc}
     */
    @Override
    public Interf448103InEntity preparedLocalData(Object param)
        throws Exception {
        InterfFinProdDto dto = (InterfFinProdDto)param;
        String finProdId = dto.getFinProdId();
        Map<String, Object> map = new HashMap<>();
        // 金融产品代码， 平台时间
        FeeRateEntity feeRateEntity = new FeeRateEntity();
        feeRateEntity.setFinprodId(finProdId);
        feeRateEntity.setCdate(getSysDate());
        // 金融产品类型F16-理财产品，分红方案_按单位分红-01-净值
        map.put("prodType", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        map.put("bonusPlan", DictCodeList.分红方案_按单位分红.getValue());
        map.put("sysDate", getSysDate());
        map.put("prodId", finProdId);
        Interf448103InEntity inEntity = mapper.selectPrdValInfo(map);
        if (!StringUtil.isNotEmpty(inEntity)) {
            getLogger().error("金融产品代码为：{}的金融产品净值信息查询失败", finProdId);
            return inEntity;
        }
        // 现金流一级类型03-费用，现金流二级类型C06-销售费
        // 金融产品类型F16-理财产品，现金流一级类型03-费用，现金流二级类型C06-销售费
        feeRateEntity.setCashType1(DictCodeListExt1.现金流一级类型_费用.getValue());
        feeRateEntity.setCashType2(DictCodeListExt1.现金流二级类型_销售费.getValue());
        String saleFeeRate = feeService.selectFeeRateStr(feeRateEntity);
        inEntity.setSaleRate(saleFeeRate);
        // 如果产品收益类型是净值型，则调用公共方法计算年化收益率和涨跌幅
        if (inEntity.getProfitType().equals(DictCodeList.收益类型_净值型.getValue())) {
            // 设置涨跌幅
            setYield(dto.getFinProdId(), inEntity);
        }

        return inEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(Interf448103OutEntity entity)
        throws Exception {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(Interf448103OutEntity entity)
        throws Exception {
        return 0;
    }

    /**
     * 设置涨跌收益率
     * 
     * @param finprodId 金融产品代码
     * @param inEntity 对象产品
     * @throws ParseException 解析异常
     * @see
     * @since 1.0
     */
    private void setYield(String finprodId, Interf448103InEntity inEntity)
        throws ParseException {
        BigDecimal totalNetValue = new BigDecimal(inEntity.getTotalNetValue());
        String navDate = inEntity.getNavDate();
        // 日涨幅
        inEntity.setDailyIncrease(yComponent.getYield(finprodId, totalNetValue, navDate,
            InterfDictCodeList.涨幅_日涨幅.getValue(), InterfDictCodeList.周期值_日.getValue()));
        // 年化日涨幅
        inEntity.setDailyYearIncrease(yComponent.getYield(finprodId, totalNetValue, navDate,
            InterfDictCodeList.涨幅_年涨幅.getValue(), InterfDictCodeList.周期值_日.getValue()));
        // 周涨幅
        inEntity.setWeeklyIncrease(yComponent.getYield(finprodId, totalNetValue, navDate,
            InterfDictCodeList.涨幅_日涨幅.getValue(), InterfDictCodeList.周期值_周.getValue()));
        // 年化周涨幅
        inEntity.setWeeklyYearIncrease(yComponent.getYield(finprodId, totalNetValue, navDate,
            InterfDictCodeList.涨幅_年涨幅.getValue(), InterfDictCodeList.周期值_周.getValue()));
        // 月涨幅
        inEntity.setMonthlyIncrease(yComponent.getYield(finprodId, totalNetValue, navDate,
            InterfDictCodeList.涨幅_日涨幅.getValue(), InterfDictCodeList.周期值_月.getValue()));
        // 年化月涨幅
        inEntity.setMonthlyYearIncrease(yComponent.getYield(finprodId, totalNetValue, navDate,
            InterfDictCodeList.涨幅_年涨幅.getValue(), InterfDictCodeList.周期值_月.getValue()));
        // 季涨幅
        inEntity.setQuarterIncrease(yComponent.getYield(finprodId, totalNetValue, navDate,
            InterfDictCodeList.涨幅_日涨幅.getValue(), InterfDictCodeList.周期值_季.getValue()));
        // 年化季涨幅
        inEntity.setQuarterYearIncrease(yComponent.getYield(finprodId, totalNetValue, navDate,
            InterfDictCodeList.涨幅_年涨幅.getValue(), InterfDictCodeList.周期值_季.getValue()));
        // 半年涨幅
        inEntity.setSemiAnnualIncrease(yComponent.getYield(finprodId, totalNetValue, navDate,
            InterfDictCodeList.涨幅_日涨幅.getValue(), InterfDictCodeList.周期值_半年.getValue()));
        // 年化半年涨幅
        inEntity.setSemiAnnualYearIncrease(yComponent.getYield(finprodId, totalNetValue, navDate,
            InterfDictCodeList.涨幅_年涨幅.getValue(), InterfDictCodeList.周期值_半年.getValue()));
        // 年涨幅
        inEntity.setAnnualIncrease(yComponent.getYield(finprodId, totalNetValue, navDate,
            InterfDictCodeList.涨幅_日涨幅.getValue(), InterfDictCodeList.周期值_年.getValue()));
        // 年化年涨幅
        inEntity.setAnnualYearIncrease(yComponent.getYield(finprodId, totalNetValue, navDate,
            InterfDictCodeList.涨幅_年涨幅.getValue(), InterfDictCodeList.周期值_年.getValue()));
        // 成立以来涨跌幅 （T日净值-1）*100%
        String sinceEstablishIncrease = yComponent.sinceEstablishIncrease(totalNetValue);
        inEntity.setSinceEstablishIncrease(sinceEstablishIncrease);
        // HisRate,--历史收益率，这里统一传0，后续公共方法中会自己判断查询
        yComponent.getYield(finprodId, totalNetValue, navDate,
            InterfDictCodeList.涨幅_历史涨幅.getValue(), "0");

    }
}
