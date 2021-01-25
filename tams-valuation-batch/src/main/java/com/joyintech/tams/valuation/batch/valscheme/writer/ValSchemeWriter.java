/*
 * 文件名：ValBokBalanceWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V1.0
 * 修改人：ranhongming
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V1.0
 */

package com.joyintech.tams.valuation.batch.valscheme.writer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.BokValbalTableStatus;
import com.joyintech.tams.common.base.entity.BokValbalTableStatusKey;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
//import com.joyintech.tams.common.base.dao.BokFileGenMapper;
import com.joyintech.tams.common.base.dao.BokValTableDataMapper;
import com.joyintech.tams.common.base.dao.BokValbalTableLogMapper;
import com.joyintech.tams.common.base.dao.BokValbalTableStatusMapper;
//import com.joyintech.tams.common.base.entity.BokFileGen;
import com.joyintech.tams.common.base.entity.BokValTableData;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.tams.valuation.batch.common.util.ExcelTools;
import com.joyintech.tams.valuation.batch.valscheme.dto.ProdValuationDto;
import com.joyintech.tams.valuation.batch.valscheme.dto.ValSchemeGenerateResult;
import com.joyintech.tams.valuation.common.constants.DataIndexSequenceType;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.utils.DBUtil;

/**
 * 估值表生成
 * 
 * @author yangzhi
 * @version 1.0 2018年5月28日
 * @see ValSchemeWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValSchemeWriter extends BaseWriter<NeedToWriteEntity> {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
//TODO 一体化问题8：表删除（文件生成信息:BOK_FILE_GEN） 
//    @Autowired
//    private BokFileGenMapper valFileGenMapper;

    @Autowired
    private BokValTableDataMapper bokValTableDataMapper;

    @Autowired
    private DBUtil dBUtil;

    @Autowired
    private BokValbalTableStatusMapper bokValbalTableStatusMapper;

    @Autowired
    private BokValbalTableLogMapper bokValbalTableLogMapper;
    
    @Autowired
    private AccountingCommonService accountingCommonService;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        // 获取process返回组合的list值 - 记账凭证list
        List<ValSchemeGenerateResult> resultList = (List<ValSchemeGenerateResult>)items;
        // 估值模板选择
        String schemeMode = CacheUtil.getSysParam("SCHEME_MODE");
        // 循环科目余额list，对每个记账凭证进行操作
        for (ValSchemeGenerateResult result : resultList) {
            /** modfiy by dongjun on 20181205 start **/
            if(StringUtil.isEmptyList(result.getProdValuationDtoList())){
                continue;
            }
            /** modfiy by dongjun on 20181205 end **/
            // valSchemeMapper.deleteBokValTableData(result.getBookDate(), result.getBooksetId());
            List<ProdValuationDto> list = result.getProdValuationDtoList();
            
            // 科目明细
            if(list != null && list.size()>0){
                list.forEach(item -> {
                    bokValTableDataMapper.insert(item.toBokValTableData(
                        dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                        result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                        DictCodeList.估值表类型_科目明细列表.getValue()));
                });
            }

            // 汇总合计
            List<ProdValuationDto> listSum = new ArrayList<ProdValuationDto>();

            // 实收资本
            if (result.getActRecAmount() != null) {
                bokValTableDataMapper.insert(result.getActRecAmount().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_实收资本.getValue()));
                listSum.add(result.getActRecAmount());
            }

            // 损益平准金未实现
            if (result.getProfLossUnrealAmount() != null) {

                bokValTableDataMapper.insert(result.getProfLossUnrealAmount().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_损益平准金未实现.getValue()));
                listSum.add(result.getProfLossUnrealAmount());
            }
            // 损益平准金已实现
            if (result.getProfLossRealAmount() != null) {
                bokValTableDataMapper.insert(result.getProfLossRealAmount().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_损益平准金已实现.getValue()));
                listSum.add(result.getProfLossRealAmount());
            }
            // 资产类合计
            if (result.getAssetTotal() != null) {
                bokValTableDataMapper.insert(result.getAssetTotal().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_资产类合计.getValue()));
                listSum.add(result.getAssetTotal());
            }
            // 负债类合计
            if (result.getDebtTotal() != null) {
                bokValTableDataMapper.insert(result.getDebtTotal().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_负债类合计.getValue()));
                listSum.add(result.getDebtTotal());
            }



            //update by yangjian 2019/06/25 - 针对【估值核算系统1.5.2版本验收问题记录跟踪表】中提出问题修改：--------------------------being
            //“净值型产品估值表中，估值表汇总指标数据产品单位净值、产品资产净值与产品单位净值-费后、产品资产净值-费后重复，两者是一个概念，只保留费后的两个指标即可。”。
            //
            if (!DictCodeList.收益类型_净值型.getValue().equals(result.getProfitType())) {
                // 产品资产净值
                if (result.getProdAssetVal() != null) {
                    bokValTableDataMapper.insert(result.getProdAssetVal().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                            result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                            DictCodeList.估值表类型_产品资产净值.getValue()));
                    listSum.add(result.getProdAssetVal());
                }
                // 产品单位净值
                if (result.getProdUnitVal() != null) {
                    BokValTableData record = result.getProdUnitVal().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                            result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                            DictCodeList.估值表类型_产品单位净值.getValue());
                    bokValTableDataMapper.insert(record);
                    listSum.add(result.getProdUnitVal());
                }
            }
            //update by yangjian 2019/06/25 - 针对【估值核算系统1.5.2版本验收问题记录跟踪表】中提出问题修改：--------------------------end


            // 证券投资合计
            if (result.getSecurityInvTotal() != null) {
                bokValTableDataMapper.insert(result.getSecurityInvTotal().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_证券投资合计.getValue()));
                listSum.add(result.getSecurityInvTotal());
            }
            // 其中股票投资
            if (result.getShareInvInside() != null) {
                bokValTableDataMapper.insert(result.getShareInvInside().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_其中股票投资.getValue()));
                listSum.add(result.getShareInvInside());
            }
            // 其中债券投资
            if (result.getBondInvInside() != null) {
                bokValTableDataMapper.insert(result.getBondInvInside().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_其中债券投资.getValue()));
                listSum.add(result.getBondInvInside());
            }
            // 其中基金投资
            if (result.getFundInvInside() != null) {
                bokValTableDataMapper.insert(result.getFundInvInside().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_其中基金投资.getValue()));
                listSum.add(result.getFundInvInside());
            }
            // 其中权证投资
            if (result.getWarrantInvInside() != null) {
                bokValTableDataMapper.insert(result.getWarrantInvInside().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_其中权证投资.getValue()));
                listSum.add(result.getWarrantInvInside());
            }
            // 融资业务合计
            if (result.getFinancBusiTotal() != null) {
                bokValTableDataMapper.insert(result.getFinancBusiTotal().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_融资业务合计.getValue()));
                listSum.add(result.getFinancBusiTotal());
            }

            // 融券业务合计
            if (result.getMarginBusiTotal() != null) {
                bokValTableDataMapper.insert(result.getMarginBusiTotal().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_融券业务合计.getValue()));
                listSum.add(result.getMarginBusiTotal());
            }

            // 今日可用头寸
            if (result.getTdyAvailPosition() != null) {
                bokValTableDataMapper.insert(result.getTdyAvailPosition().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_今日可用头寸.getValue()));
                listSum.add(result.getTdyAvailPosition());
            }

            // 产品累计单位净值
            if (result.getProdTotalUnitPrice() != null) {
                bokValTableDataMapper.insert(result.getProdTotalUnitPrice().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_产品累计单位净值.getValue()));
                listSum.add(result.getProdTotalUnitPrice());
            }
            // 昨日单位净值
            if (result.getYstUnitPrice() != null) {
                bokValTableDataMapper.insert(result.getYstUnitPrice().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_昨日单位净值.getValue()));
                listSum.add(result.getYstUnitPrice());
            }
            if (StringUtils.isEmpty(schemeMode)
                || DictCodeList.估值模板_理财产品模板.getValue().equals(schemeMode)) {

                // 成立以来净值增长率(%)
                if (result.getPriceGrowRateSince() != null) {
                    bokValTableDataMapper.insert(result.getPriceGrowRateSince().toBokValTableData(
                        dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                        result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                        DictCodeList.估值表类型_成立以来净值增长率.getValue()));
                    listSum.add(result.getPriceGrowRateSince());
                }

                // 净值年增长率(%)
                if (result.getPriceGrowRateYear() != null) {
                    bokValTableDataMapper.insert(result.getPriceGrowRateYear().toBokValTableData(
                        dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                        result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                        DictCodeList.估值表类型_净值年增长率.getValue()));
                    listSum.add(result.getPriceGrowRateYear());
                }

                // 净值季度增长率(%)
                if (result.getPriceGrowRateQuarter() != null) {
                    bokValTableDataMapper.insert(
                        result.getPriceGrowRateQuarter().toBokValTableData(
                            dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                            result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                            DictCodeList.估值表类型_净值季度增长率.getValue()));
                    listSum.add(result.getPriceGrowRateQuarter());
                }

                // 净值月增长率(%)
                if (result.getPriceGrowRateMonth() != null) {
                    bokValTableDataMapper.insert(result.getPriceGrowRateMonth().toBokValTableData(
                        dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                        result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                        DictCodeList.估值表类型_净值月增长率.getValue()));
                    listSum.add(result.getPriceGrowRateMonth());
                }

                // 净值周增长率(%)
                if (result.getPriceGrowRateWeek() != null) {
                    bokValTableDataMapper.insert(result.getPriceGrowRateWeek().toBokValTableData(
                        dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                        result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                        DictCodeList.估值表类型_净值周增长率.getValue()));
                    listSum.add(result.getPriceGrowRateWeek());
                }

                // 净值日增长率(%)
                if (result.getPriceGrowRateDay() != null) {
                    bokValTableDataMapper.insert(result.getPriceGrowRateDay().toBokValTableData(
                        dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                        result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                        DictCodeList.估值表类型_净值日增长率.getValue()));
                    listSum.add(result.getPriceGrowRateDay());
                }
            }
            // 影子价格单位净值
            if (result.getShandowUnitPrice() != null) {
                bokValTableDataMapper.insert(result.getShandowUnitPrice().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_影子价格单位净值.getValue()));
                listSum.add(result.getShandowUnitPrice());
            }
            
            //add by tanxuan 20200102 start
            //待抵扣投资收益税
            if(result.getDeductInvestIncomeTax() != null){
                bokValTableDataMapper.insert(result.getDeductInvestIncomeTax().toBokValTableData(
                        dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                        result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                        DictCodeListExt3.估值表类型_待抵扣投资收益税.getValue()));
                    listSum.add(result.getDeductInvestIncomeTax());
            }
            //待抵扣暂估投资收益税
            if(result.getExtDeductInvestIncomeTax() != null){
                bokValTableDataMapper.insert(result.getExtDeductInvestIncomeTax().toBokValTableData(
                        dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                        result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                        DictCodeListExt3.估值表类型_待抵扣暂估投资收益税.getValue()));
                    listSum.add(result.getExtDeductInvestIncomeTax());
            }
          //add by tanxuan 20200102 end

            // 偏离金额
            if (result.getDeviationAmount() != null) {
                bokValTableDataMapper.insert(result.getDeviationAmount().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_偏离金额.getValue()));
                listSum.add(result.getDeviationAmount());
            }

            // 偏离度
            if (result.getDeviationDegree() != null) {
                bokValTableDataMapper.insert(result.getDeviationDegree().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_偏离度.getValue()));
                listSum.add(result.getDeviationDegree());
            }

            // 产品本日收益
            if (result.getTdyProdProfit() != null) {
                bokValTableDataMapper.insert(result.getTdyProdProfit().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_产品本日收益_费后.getValue()));
                listSum.add(result.getTdyProdProfit());
            }

            // 万份收益
            if (result.getPYield() != null) {
                bokValTableDataMapper.insert(result.getPYield().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_万份收益_费后.getValue()));
                listSum.add(result.getPYield());
            }

            // 本年累积万份收益
            if (result.getPYieldYear() != null) {
                bokValTableDataMapper.insert(result.getPYieldYear().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_本年累积万份收益.getValue()));
                listSum.add(result.getPYieldYear());
            }

            // 从成立以来累计万份收益
            if (result.getPYieldSince() != null) {
                bokValTableDataMapper.insert(result.getPYieldSince().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_从成立以来累计万份收益.getValue()));
                listSum.add(result.getPYieldSince());
            }

            // 本日年化收益率
            if (result.getTdyYield() != null) {
                bokValTableDataMapper.insert(result.getTdyYield().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_本日年化收益率.getValue()));
                listSum.add(result.getTdyYield());
            }

            // 7日年化收益率
            if (result.getYield7() != null) {
                bokValTableDataMapper.insert(result.getYield7().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_7日年化收益率.getValue()));
                listSum.add(result.getYield7());
            }
            // 投资组合平均到期日
            if (result.getInvGroupAvrMday() != null) {
                bokValTableDataMapper.insert(result.getInvGroupAvrMday().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_投资组合平均到期日.getValue()));
                listSum.add(result.getInvGroupAvrMday());
            }
            // 剩余存续期
            if (result.getRemainDuration() != null) {
                bokValTableDataMapper.insert(result.getRemainDuration().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_剩余存续期.getValue()));
                listSum.add(result.getRemainDuration());
            }
            // 当日客户收益
            if (result.getTdyCustProfit() != null) {
                bokValTableDataMapper.insert(result.getTdyCustProfit().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_当日客户收益.getValue()));
                listSum.add(result.getTdyCustProfit());
            }
            // 累计客户收益
            if (result.getTotalCustProfit() != null) {
                bokValTableDataMapper.insert(result.getTotalCustProfit().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_累计客户收益.getValue()));
                listSum.add(result.getTotalCustProfit());
            }
            // 当日客户收益率
            if (result.getTdyCustProfitRate() != null) {
                bokValTableDataMapper.insert(result.getTdyCustProfitRate().toBokValTableData(
                    dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                    result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                    DictCodeList.估值表类型_当日客户收益率.getValue()));
                listSum.add(result.getTdyCustProfitRate());
            }
            // 分层
            if(result.getLayeredProdValDtoList() != null && result.getLayeredProdValDtoList().size()>0){
                result.getLayeredProdValDtoList().forEach(item -> {
                    bokValTableDataMapper.insert(item.toBokValTableData(
                        dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型),
                        result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                        null));
                    // 处理OPEX-591： 赵 晓丽【估值表】成立以来年化收益率、周期年化收益率，没有按%显示，和其他收益率不统一
                    // 不更改入库逻辑，页面展示以及下载文件处修改
                    String subjectName = item.getSubjectName();
                    String subjectNo = item.getSubjectNo();
                    // 由于框架会查询2次，故屏蔽第二次缓存查询进入，已包含%，则不处理
                    if (DictCodeList.估值表类型_成立以来年化收益率.getValue().equals(item.getDetailDist())) {
                        subjectNo.replace("成立以来年化收益率", "成立以来年化收益率%");
                        // 获取小数位数
                        int length = subjectName.length() - subjectName.indexOf(".") - 1;
                        // 取值* 100，且小数位数与原来保持一致
                        subjectName = ArithmeticUtil.multiply(length, null,
                            new BigDecimal(subjectName), new BigDecimal(100)).toString();
                        // 更新名称以及值
                        item.setSubjectNo(subjectNo);
                        item.setSubjectName(subjectName);
                    }
                    else if (DictCodeList.估值表类型_周期年化收益率.getValue().equals(item.getDetailDist())) {
                        subjectNo.replace("周期年化收益率", "周期年化收益率%");
                        // 获取小数位数
                        int length = subjectName.length() - subjectName.indexOf(".") - 1;
                        // 取值* 100，且小数位数与原来保持一致
                        subjectName = ArithmeticUtil.multiply(length, null,
                            new BigDecimal(subjectName), new BigDecimal(100)).toString();
                        // 更新名称以及值
                        item.setSubjectNo(subjectNo);
                        item.setSubjectName(subjectName);
                    }
                    listSum.add(item);
                });
            }

            /** modify by dongjun on 20190425 估值表添加指标 start**/
            // 产品资产净值-费后
            if(result.getNetAssetValue() != null){
                bokValTableDataMapper.insert(result.getNetAssetValue().toBokValTableData(
                        String.valueOf(dBUtil.getSequenceIndex(DataIndexSequenceType.记账类型)),
                        result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                        DictCodeList.估值表类型_产品资产净值_费后.getValue()));
                listSum.add(result.getNetAssetValue());
            }

            // 产品单位净值-费后
            if(result.getNetUnitValue() != null){
                bokValTableDataMapper.insert(result.getNetUnitValue().toBokValTableData(
                        String.valueOf(dBUtil.getSequenceIndex(DataIndexSequenceType.记账类型)),
                        result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                        DictCodeList.估值表类型_产品单位净值_费后.getValue()));
                listSum.add(result.getNetUnitValue());
            }

            // 产品资产净值-费前
            if(result.getNetAssetValueBef() != null){
                bokValTableDataMapper.insert(result.getNetAssetValueBef().toBokValTableData(
                        String.valueOf(dBUtil.getSequenceIndex(DataIndexSequenceType.记账类型)),
                        result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                        DictCodeList.估值表类型_产品资产净值_费前.getValue()));
                listSum.add(result.getNetAssetValueBef());
            }

            // 产品单位净值-费前
            if(result.getNetUnitValueBef() != null){
                bokValTableDataMapper.insert(result.getNetUnitValueBef().toBokValTableData(
                        String.valueOf(dBUtil.getSequenceIndex(DataIndexSequenceType.记账类型)),
                        result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                        DictCodeList.估值表类型_产品单位净值_费前.getValue()));
                listSum.add(result.getNetUnitValueBef());
            }

            // 周期年化收益率
            if(result.getYieldTerm() != null){
                bokValTableDataMapper.insert(result.getYieldTerm().toBokValTableData(
                        String.valueOf(dBUtil.getSequenceIndex(DataIndexSequenceType.记账类型)),
                        result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                        DictCodeList.估值表类型_周期年化收益率.getValue()));
                // 处理OPEX-591： 赵 晓丽【估值表】成立以来年化收益率、周期年化收益率，没有按%显示，和其他收益率不统一
                // 不更改入库逻辑，页面展示以及下载文件处修改
                ProdValuationDto dto = result.getYieldTerm();
                String subjectName = dto.getSubjectName();
                String subjectNo = dto.getSubjectNo();
                // 获取小数位数
                int length = subjectName.length() - subjectName.indexOf(".") - 1;
                // 取值* 100，且小数位数与原来保持一致
                subjectName = ArithmeticUtil.multiply(length, null,
                    new BigDecimal(subjectName), new BigDecimal(100)).toString();
                subjectNo.replace("周期年化收益率", "周期年化收益率%");
                dto.setSubjectNo(subjectNo);
                dto.setSubjectName(subjectName);
                listSum.add(dto);
            }

            // 产品本日收益-费前
            if(result.getTdyIncomeBef() != null){
                bokValTableDataMapper.insert(result.getTdyIncomeBef().toBokValTableData(
                        String.valueOf(dBUtil.getSequenceIndex(DataIndexSequenceType.记账类型)),
                        result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                        DictCodeList.估值表类型_产品本日收益_费前.getValue()));
                listSum.add(result.getTdyIncomeBef());
            }

            // 万份收益-费前
            if(result.getpYieldBef() != null){
                bokValTableDataMapper.insert(result.getpYieldBef().toBokValTableData(
                        String.valueOf(dBUtil.getSequenceIndex(DataIndexSequenceType.记账类型)),
                        result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                        DictCodeList.估值表类型_万份收益_费前.getValue()));
                listSum.add(result.getpYieldBef());
            }

            // 成立以来年化收益率
            if(result.getToTdyyield() != null){
                bokValTableDataMapper.insert(result.getToTdyyield().toBokValTableData(
                        String.valueOf(dBUtil.getSequenceIndex(DataIndexSequenceType.记账类型)),
                        result.getBooksetId(), result.getBooksetName(), result.getBookDate(),
                        DictCodeList.估值表类型_成立以来年化收益率.getValue()));
                // 处理OPEX-591： 赵 晓丽【估值表】成立以来年化收益率、周期年化收益率，没有按%显示，和其他收益率不统一
                // 不更改入库逻辑，页面展示以及下载文件处修改
                ProdValuationDto dto = result.getToTdyyield();
                String subjectName = dto.getSubjectName();
                String subjectNo = dto.getSubjectNo();
                // 获取小数位数
                int length = subjectName.length() - subjectName.indexOf(".") - 1;
                // 取值* 100，且小数位数与原来保持一致
                subjectName = ArithmeticUtil.multiply(length, null,
                    new BigDecimal(subjectName), new BigDecimal(100)).toString();
                subjectNo.replace("成立以来年化收益率", "成立以来年化收益率%");
                dto.setSubjectNo(subjectNo);
                dto.setSubjectName(subjectName);
                listSum.add(dto);
            }

            /** modify by dongjun on 20190425 估值表添加指标  end**/

            if (StringUtil.equals(CacheUtil.getSysParam("EXPORT_VALBOKBANLANCE_SWITCH"), "ON")) {
                // 定义 变量.估值表模板名称
                String tempName = "";
                // 调用共通获取账套配置表中估值表模板字段
                List<String> paramCodeList = new ArrayList<String>();
                // 估值表模板
                paramCodeList.add(DictCode.估值表模板.getValue());
                Map<String, Object> getValBokBooksetExtMap = accountingCommonService.getValBokBooksetExtForMap(
                    result.getBooksetId(), paramCodeList);
                String valTemplate = (String)getValBokBooksetExtMap.get(DictCode.估值表模板.getValue());
                // 若账套配置表中估值表模板不为空，则根据当前配置区分模板选择
                if (StringUtil.isNotEmpty(valTemplate)) {
                    if (DictCodeListExt1.估值表模板_理财产品模板_系统推荐.getValue().equals(valTemplate)) {
                        // 杭州估值表模板
                        tempName = GlobalConstant.PROD_TMP_NAME;
                    }
                    else{
                        // 平安估值表模板
                        tempName = GlobalConstant.FUND_TMP_NAME;
                    }
                }
                else{
                    if (DictCodeList.估值模板_理财产品模板.getValue().equals(schemeMode)) {
                        // 杭州估值表模板
                        tempName = GlobalConstant.PROD_TMP_NAME;
                    }
                    else{
                        // 平安估值表模板
                        tempName = GlobalConstant.FUND_TMP_NAME;
                    }
                }
                String fileName = result.getBooksetId() + "（" + result.getBooksetFullName() + "）"
                                  + "估值表" + DateUtil.format(result.getBookDate(), "yyyy-MM-dd")
                                  + ".xls";
                String outPath = CacheUtil.getSysParam("EXPORT_VALBOKBANLANCE") + File.separator
                                 + fileName;
                // 默认净值型的excel模板
                // String tplPath = CacheUtil.getSysParam("BATCH_TPL_PATH") + File.separator
                // + "ProdValuation.xls";
                // 2018年10月10日--改为画面模板路径，无需改动模板
                String tplPath = CacheUtil.getSysParam("BATCH_TPL_OUT") + File.separator
                                 + tempName;
                InputStream stream = null;
                Map<String, Object> paraMap = new HashMap<>();
                paraMap.put("prodTitle",
                    String.format("%s（%s）估值表", result.getBooksetFullName(), result.getBooksetId()));
                Calendar cal = Calendar.getInstance();
                cal.setTime(result.getBookDate());
                paraMap.put("dateStr", String.format("%d/%d/%d", cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH)));
                // 单位净值
                if(result.getProdUnitVal() != null){
                    paraMap.put("unitPrice", result.getProdUnitVal().getSubjectName());
                }

                // 科目明细
                paraMap.put("listAll", list);

                // 实收资本
                // paraMap.put("actRecAmount", result.getActRecAmount());
                // listSum.add(result.getActRecAmount());
                // // 资产类合计
                // // paraMap.put("assetTotal", result.getAssetTotal());
                // listSum.add(result.getAssetTotal());
                // // 负债类合计
                // // paraMap.put("debtTotal", result.getDebtTotal());
                // listSum.add(result.getDebtTotal());
                // // 产品资产净值
                // // paraMap.put("prodAssetVal", result.getProdAssetVal());
                // listSum.add(result.getProdAssetVal());
                // // 产品单位净值
                // // paraMap.put("prodUnitVal", result.getProdUnitVal());
                // listSum.add(result.getProdUnitVal());

                // delete by ：
                // 【../10.跟踪监控/02-平安银行/估值核算-UAT问题记录及跟踪表-平安银行.xlsx】中【UAT-180】【7、
                // 删除证券投资。。。。融券业务合计这几行，今日可用头寸，也去掉】---【模版也已去除这几行】
                /*
                 * // 证券投资合计 paraMap.put("securityInvTotal", result.getSecurityInvTotal()); //
                 * 其中股票投资 paraMap.put("shareInvInside", result.getShareInvInside()); // 其中债券投资
                 * paraMap.put("bondInvInside", result.getBondInvInside()); // 其中基金投资
                 * paraMap.put("fundInvInside", result.getFundInvInside()); // 其中权证投资
                 * paraMap.put("warrantInvInside", result.getWarrantInvInside()); // 融资业务合计
                 * paraMap.put("financBusiTotal", result.getFinancBusiTotal()); // 融券业务合计
                 * paraMap.put("marginBusiTotal", result.getMarginBusiTotal()); // 今日可用头寸
                 * paraMap.put("tdyAvailPosition", result.getTdyAvailPosition());
                 */

                // if (DictCodeList.收益类型_净值型.getValue().equals(result.getProfitType())) {
                // // 损益平准金未实现
                // // paraMap.put("profLossUnrealAmount", result.getProfLossUnrealAmount());
                // listSum.add(result.getProfLossUnrealAmount());
                // // 损益平准金已实现
                // // paraMap.put("profLossRealAmount", result.getProfLossRealAmount());
                // listSum.add(result.getProfLossRealAmount());

                // 产品累计单位净值
                // paraMap.put("prodTotalUnitPrice", result.getProdTotalUnitPrice());
                // listSum.add(result.getProdTotalUnitPrice());
                // if (StringUtils.isEmpty(hzSchemeSwitch)
                // || GlobalConstant.SWITCH_OFF.equals(hzSchemeSwitch)) {
                // // 昨日单位净值
                // // paraMap.put("ystUnitPrice", result.getYstUnitPrice());
                // listSum.add(result.getYstUnitPrice());
                // // 成立以来净值增长率(%)
                // // paraMap.put("priceGrowRateSince", result.getPriceGrowRateSince());
                // listSum.add(result.getPriceGrowRateSince());
                // // 净值年增长率(%)
                // // paraMap.put("priceGrowRateYear", result.getPriceGrowRateYear());
                // listSum.add(result.getPriceGrowRateYear());
                // // 净值季度增长率(%)
                // // paraMap.put("priceGrowRateQuarter", result.getPriceGrowRateQuarter());
                // listSum.add(result.getPriceGrowRateQuarter());
                // // 净值月增长率(%)
                // // paraMap.put("priceGrowRateMonth", result.getPriceGrowRateMonth());
                // listSum.add(result.getPriceGrowRateMonth());
                // // 净值周增长率(%)
                // // paraMap.put("priceGrowRateWeek", result.getPriceGrowRateWeek());
                // listSum.add(result.getPriceGrowRateWeek());
                // // 净值日增长率(%)
                // // paraMap.put("priceGrowRateDay", result.getPriceGrowRateDay());
                // listSum.add(result.getPriceGrowRateDay());
                // }
                // }
                // else {
                //
                // // 影子价格单位净值
                // // paraMap.put("shandowUnitPrice", result.getShandowUnitPrice());
                // // listSum.add(result.getShandowUnitPrice());
                //
                // // 偏离金额
                // // paraMap.put("deviationAmount", result.getDeviationAmount());
                // listSum.add(result.getDeviationAmount());
                // // 偏离度
                // // paraMap.put("deviationDegree", result.getDeviationDegree());
                // listSum.add(result.getDeviationDegree());
                // // 产品本日收益
                // // paraMap.put("tdyProdProfit", result.getTdyProdProfit());
                // listSum.add(result.getTdyProdProfit());
                // // 万份收益
                // // paraMap.put("pYield", result.getPYield());
                // listSum.add(result.getPYield());
                // // 本年累积万份收益
                // // paraMap.put("pYieldYear", result.getPYieldYear());
                // listSum.add(result.getPYieldYear());
                // // 从成立以来累计万份收益
                // // paraMap.put("pYieldSince", result.getPYieldSince());
                // listSum.add(result.getPYieldSince());
                // // 本日年化收益率
                // // paraMap.put("tdyYield", result.getTdyYield());
                // listSum.add(result.getTdyYield());
                // // 7日年化收益率
                // // paraMap.put("yield7", result.getYield7());
                // listSum.add(result.getYield7());
                // // 投资组合平均到期日
                // listSum.add(result.getInvGroupAvrMday());
                // // 剩余存续期
                // listSum.add(result.getRemainDuration());
                // }

                // 去除list中数据中的包含的null值。
                listSum.removeAll(Collections.singleton(null));

                paraMap.put("listSum", listSum);
                try {
                    stream = new FileInputStream(tplPath);
                    ExcelTools.writeExcel(stream, tempName, paraMap, outPath);
                    // 上传文件到文件服务器上
//                    SftpTools sftpTools = new SftpTools();
//                    String ip = CacheUtil.getSysParam("FILESERVER_HOST");// 主机IP
//                    Integer port = Integer.parseInt(CacheUtil.getSysParam("FILESERVER_PORT"));// 主机端口
//                    String username = CacheUtil.getSysParam("FILESERVER_USERNAME");// 主机用户名
//                    String password = CacheUtil.getSysParam("FILESERVER_PASSWORD");// 主机密码或秘钥地址
//                    String sftpLoginType = CacheUtil.getSysParam("SFTP_LOGIN_TYPE");// SFTP登录方式
//                    String remotePath = CacheUtil.getSysParam("FILESERVER_REMOTE_PATH");
//                    sftpTools.login(username, password, ip, port, sftpLoginType );
//                    
//                    if (StringUtils.isEmpty(hzSchemeSwitch)
//                        || DictCodeList.估值模板_基金模板.getValue().equals(hzSchemeSwitch)) {
//                        sftpTools.putFile(outPath, remotePath);
//                    }
//                    else {
//                        sftpTools.putFileDate(outPath, remotePath,
//                            DateUtil.format(result.getBookDate(), "yyyyMMdd"));
//                    }
//                    sftpTools.close();
                }
                catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
                finally {
                    if (stream != null) {
                        try {
                            stream.close();
                        }
                        catch (IOException e) {
                            logger.error(e.getMessage(), e);
                        }
                    }
                }
//                BokFileGen valFileGen = new BokFileGen();
//                valFileGen.setBooksetId(result.getBooksetId());
//                valFileGen.setGenDate(result.getBookDate());
//                valFileGen.setFileName(fileName);
//                valFileGen.setFilePath(outPath);
//                valFileGen.setFileType(DictCodeList.文件类型_估值表.getValue());
//                // 上传状态设为 02：未上传
//                valFileGen.setUploadStatus(GlobalConstant.TYPE_02);
//                valFileGenMapper.insert(valFileGen);
            }

            // 估值表余额表状态表 入库
            if (result.getBokValbalTableStatus() != null) {
                // 估值表余额表生成日志表 入库
                if (result.getBokValbalTableLog() != null) {
                    bokValbalTableLogMapper.insert(result.getBokValbalTableLog());
                }
                //增加一个估值参数，通过参数控制是否修改状态 IS_CHANGE_STATUS_VALNETVALUECONFIRM
                //净值确认下发重新跑批时是否修改确认状态、下发状态 Y:是 N 否
                String isChangeStatusValNetValyeConfirm = CacheUtil.getSysParam("IS_CHANGE_STATUS_VALNETVALUECONFIRM");
                if(StringUtil.isNotBlank(isChangeStatusValNetValyeConfirm)
                        && StringUtil.equals(DictCodeList.是否_否.getValue(),isChangeStatusValNetValyeConfirm)){
//                    bokValbalTableStatusMapper.insert(result.getBokValbalTableStatus());
                      //根据主键查询数据是否存在
                    BokValbalTableStatusKey key =new BokValbalTableStatusKey();
                    key.setFinprodId(result.getBokValbalTableStatus().getFinprodId());
                    key.setValBalFlag(result.getBokValbalTableStatus().getValBalFlag());
                    key.setValDate(result.getBokValbalTableStatus().getValDate());
                    BokValbalTableStatus bokValbalTableStatus = bokValbalTableStatusMapper.selectByPrimaryKey(key);
                    if(bokValbalTableStatus !=null){
                        continue;
                    }else{
                        bokValbalTableStatusMapper.insert(result.getBokValbalTableStatus());
                    }
                }else{
                    bokValbalTableStatusMapper.insert(result.getBokValbalTableStatus());
                }
            }
        }
    }

}
