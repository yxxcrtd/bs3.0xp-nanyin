/*
 * 文件名：MoneyProdValuationDataService.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：估值核算系统V1.0 修改人：qiangchengxi
 * 修改时间：2018年11月20日 修改内容：新建 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.netvaluationdata.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.vatflow.dto.ValAddTaxFlowprocessorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokStatBokPosition;
import com.joyintech.tams.common.base.entity.FinFloatAccrualSche;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.netvaluationdata.dao.NetProdValuationDataMapper;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.netvaluationdata.dto.NetBokStatBokPositionDto;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.netvaluationdata.dto.NetProdValuationDataDto;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.netvaluationdata.dto.NetProdValuationDataResult;
import com.joyintech.tams.valuation.interf.valuation.send.common.entity.SendReaderEntity;

/**
 * 净值型产品估值数据Service
 * 
 * @author chendong
 * @version 1.0 2018年11月20日
 * @see NetValuationDataService
 * @since 1.0s
 */
@Service
@Scope("prototype")
public class NetProdValuationDataService {

    /**
     * 净值型产品估值数据Mapper
     */
    @Autowired
    private NetProdValuationDataMapper netValuationDataMapper;

    /**
     * 帐套资产负债方法Service
     */
    @Autowired
    private NetProdAccountingCommonService netAccountingCommonService;

    public ValAddTaxFlowprocessorResult execute(SendReaderEntity item){
        // 获取数据
        ValAddTaxFlowprocessorResult netValuationDataResult = new ValAddTaxFlowprocessorResult();

        List<NetProdValuationDataDto> netValuationDataList = new ArrayList<NetProdValuationDataDto>();
        // 传入的参数账套信息List
        /*
         * List<BokBookset> bokBooksetList = new ArrayList<BokBookset>(); List<String>idList = new ArrayList<String>(); for(BokBookset bokBookset :
         * bokBooksetList){ idList.add(bokBookset.getBooksetId()); }
         */
        netValuationDataList = netValuationDataMapper.queryNetValuationData(item);

        // 循环返回的list，取得累计分红净值
        for (NetProdValuationDataDto netValuationDataDto : netValuationDataList) {
            if (netValuationDataDto.getTotalValue() == null) {
                netValuationDataDto.setTotalValue(BigDecimal.ZERO);
            }
            NetBokStatBokPositionDto position = new NetBokStatBokPositionDto();
            position.setSdateStart(item.getExeDate());
            position.setSdateEnd(item.getExeDate());
            position.setBookDate(item.getExeDate());
            position.setBooksetId(netValuationDataDto.getBookSetId());
            // 查询累计分红净值
            List<BokStatBokPosition> bokStatBokPosition = netAccountingCommonService.getValStatBokPosition(position);
            // 设置累计分红净值
            if (!StringUtil.isEmptyList(bokStatBokPosition)) {
                netValuationDataDto.setTotalBondValue(bokStatBokPosition.get(0).getTotBounsNet());
            }
            else {
                netValuationDataDto.setTotalBondValue(BigDecimal.ZERO);
            }
            // 查询当前年化收益率相关信息
            List<FinFloatAccrualSche> netProdNowRateList = netValuationDataMapper.queryNowRate(netValuationDataDto.getProdId(),
                item.getExeDate(), DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
            // 上次计提日 = 开放计划.区间开始日
            Date lastDay = new Date();
            if (netProdNowRateList != null && netProdNowRateList.size() != 0) {
                lastDay = netProdNowRateList.get(0).getAccrStrDate();
            }
            else {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(item.getExeDate());
                calendar.add(Calendar.DATE, -1);
                lastDay = calendar.getTime();
            }
            // 区间天数 = 参数.日期 - 上次计提日
            long dayNum = DateUtil.getBetweenDayNumber(lastDay, item.getExeDate());
            // 计息基础
            BigDecimal basic = new BigDecimal(365);
            // 设置参数求上次计提日帐套资产负债信息
            NetBokStatBokPositionDto position1 = new NetBokStatBokPositionDto();
            position1.setSdateStart(lastDay);
            position1.setSdateEnd(lastDay);
            position1.setBookDate(lastDay);
            position1.setBooksetId(netValuationDataDto.getBookSetId());
            position1.setLayeringId(netValuationDataDto.getLayerId());
            // 上次计提日帐套资产负债信息
            List<BokStatBokPosition> bokStatBokPosition1 = netAccountingCommonService.getValStatBokPosition(position1);
            // 上次计提日帐套资产负债信息的累计分红净值为空取值为1
            if (bokStatBokPosition1 != null && bokStatBokPosition1.size() > 0
                && StringUtil.isNotEmpty(bokStatBokPosition1.get(0).getTotBounsNet())) {
                bokStatBokPosition1.get(0).setTotBounsNet(BigDecimal.ZERO);
            }
            // 计提开始日累计净值
            BigDecimal nowDayTotalValue = new BigDecimal(1);
            // 上次计提日帐套资产负债信息的累计分红净值为bokStatBokPosition1.get(0).getTotBounsNet()
            if (bokStatBokPosition1 != null && bokStatBokPosition1.size() > 0
                && StringUtil.isNotEmpty(bokStatBokPosition1.get(0).getNetUnitValue())) {
                nowDayTotalValue = bokStatBokPosition1.get(0).getNetUnitValue().add(bokStatBokPosition1.get(0).getTotBounsNet());
            }
            // 年化收益率
            BigDecimal yearRate = new BigDecimal(0);
            if (netValuationDataDto.getUnitValue() != null && !StringUtil.isEmptyList(bokStatBokPosition)) {
                // 期末累计净值
                BigDecimal endTotalValue = bokStatBokPosition.get(0).getTotBounsNet().add(
                    bokStatBokPosition.get(0).getNetUnitValue());
                // 期末累计净值-期初累计净值
                yearRate = endTotalValue.subtract(nowDayTotalValue);
                if (nowDayTotalValue.compareTo(BigDecimal.ZERO) != 0) {
                    // (期末累计净值-期初累计净值) / 期初累计净值
                    yearRate = yearRate.divide(nowDayTotalValue, 14, RoundingMode.HALF_UP);
                    // (期末累计净值-期初累计净值) / 期初累计净值 * 计息基础
                    yearRate = yearRate.multiply(basic);
                    // (期末累计净值-期初累计净值) / 期初累计净值 * 计息基础 /区间天数
                    if (dayNum == 0) {
                        yearRate = yearRate.multiply(BigDecimal.ZERO);
                    }
                    else {
                        yearRate = yearRate.divide(new BigDecimal(dayNum), 6, RoundingMode.HALF_UP);
                    }
                }
            }
            // 设置当前年化收益
            netValuationDataDto.setNowRate(yearRate);
        }

        netValuationDataResult.setDatas(netValuationDataList);
        netValuationDataResult.setTmpEName("NProdDealDataBack");
        return netValuationDataResult;
    }
}
