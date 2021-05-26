package com.joyintech.tams.svreport.chbreport.investreport.chbperiodic.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.ChbPrdBaseinfoDetailMapper;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfoDetailWithBLOBs;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.report.system.chbprd.dao.ChbPrdBaseinfo001Mapper;
import com.joyintech.tams.svreport.chb.monthreport.dao.ChbMonthAssetPositionMapper;
import com.joyintech.tams.svreport.chb.monthreport.dto.*;
import com.joyintech.tams.svreport.chb.monthreport.service.MonthReportServiceUtil;
import com.joyintech.tams.svreport.chbreport.investreport.chbperiodic.dao.ChbPeriodicMapper;
import com.joyintech.tams.svreport.chbreport.investreport.chbperiodic.dto.*;
import com.joyintech.tams.svreport.chbreport.investreport.common.service.ChbInvestReportCommonService;
import com.joyintech.tams.svreport.chbreport.investreport.utils.ChbInvestReportConstant;
import com.joyintech.tams.svreport.chbreport.investreport.utils.ChbInvestReportUtil;
import com.joyintech.tams.svreport.chbreport.investreport.utils.WordBatchUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 定期报告
 *
 * @author swwan
 * @className ChbPeriodicReportService
 * @date 2021/5/7 14:33
 */
@Service
public class ChbPeriodicService {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(ChbPeriodicMapper.class);
    @Autowired
    private ChbPeriodicMapper chbPeriodicMapper;
    @Autowired
    private ChbInvestReportCommonService chbInvestReportCommonService;
    @Autowired
    private ChbPrdBaseinfo001Mapper chbPrdBaseinfo001Mapper;
    @Autowired
    private ChbPrdBaseinfoDetailMapper chbPrdBaseinfoDetailMapper;
    @Autowired
    private ChbMonthAssetPositionMapper chbMonthAssetPositionMapper;
    @Autowired
    private MonthReportServiceUtil monthReportServiceUtil;

    /**
     * 导出公告
     *
     * @param map
     * @param reportName
     * @return
     * @throws Exception
     */
    public ResponseEntity<byte[]> exportDoc(Map<String, Object> map, String reportName) throws Exception {
        String srcFilePath = ChbInvestReportUtil.getFtlPath();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        List<String> titleList = new ArrayList<String>();
        String prodId = (String) map.get("prodId");
        List<ChbPrdBaseinfo> chbPrdBaseinfos = chbPrdBaseinfo001Mapper.selectOneDate(map);
        if (StringUtil.isEmptyList(chbPrdBaseinfos)) {
            return null;
        }
        ChbPrdBaseinfo chbPrdBaseinfo = chbPrdBaseinfos.get(0);
        map.put("chbPrdBaseinfo", chbPrdBaseinfo);
        String fprodId = (String) map.get("prodId");
        List<ChbPrdBaseinfoDetailWithBLOBs> chbPrdBaseinfoDetailList = chbPrdBaseinfoDetailMapper.selectByFprodid(fprodId);
        map.put("chbPrdBaseinfoDetailList", chbPrdBaseinfoDetailList);

        this.createData(reportName, map, mapList, titleList);

        String fProdId = null;
        String prodType = null;
        if (chbPrdBaseinfo != null) {
            fProdId = chbPrdBaseinfo.getFprodId();
            prodType = chbPrdBaseinfo.getProdtype();
        }
        if (mapList.size() == 1) {
            if (StringUtil.isEmptyList(chbPrdBaseinfoDetailList)) {
                // 单份额类净值型产品
                if (StringUtil.equals(DictCodeListExt2.产品类型_中债_净值型产品.getValue(), prodType)) {
                    return WordBatchUtil.exportMillCertificateWord(mapList.get(0), srcFilePath, ChbInvestReportConstant.产品定期公告_单份额类净值型产品模板文件, titleList.get(0));
                }
                // 单份额类货币型产品
                else if (StringUtil.equals(DictCodeListExt2.产品类型_中债_货币型产品.getValue(), prodType)) {
                    return WordBatchUtil.exportMillCertificateWord(mapList.get(0), srcFilePath, ChbInvestReportConstant.产品定期公告_单份额类货币型产品模板文件, titleList.get(0));
                }
            } else {
                // 多份额类净值型产品
                if (StringUtil.equals(DictCodeListExt2.产品类型_中债_净值型产品.getValue(), prodType)) {
                    return WordBatchUtil.exportMillCertificateWord(mapList.get(0), srcFilePath, ChbInvestReportConstant.产品定期公告_多份额类净值型产品模板文件, titleList.get(0));
                }
                // 多份额类货币型产品
                else if (StringUtil.equals(DictCodeListExt2.产品类型_中债_货币型产品.getValue(), prodType)) {
                    return WordBatchUtil.exportMillCertificateWord(mapList.get(0), srcFilePath, ChbInvestReportConstant.产品定期公告_多份额类货币型产品模板文件, titleList.get(0));
                }
            }
        } else {
            String zipname = reportName + ChbInvestReportConstant.下划线 + DateUtil.format(new Date(), ChbInvestReportConstant.日期格式化yyyyMMddHHmmss);
            if (StringUtil.isEmptyList(chbPrdBaseinfoDetailList)) {
                // 单份额类净值型产品
                if (StringUtil.isEmpty(fProdId) && StringUtil.equals(DictCodeListExt2.产品类型_中债_净值型产品.getValue(), prodType)) {
                    return WordBatchUtil.exportWordBatch(mapList, titleList, ChbInvestReportConstant.产品定期公告_单份额类净值型产品模板文件, srcFilePath, zipname);
                }
                // 单份额类货币型产品
                else if (StringUtil.isEmpty(fProdId) && StringUtil.equals(DictCodeListExt2.产品类型_中债_货币型产品.getValue(), prodType)) {
                    return WordBatchUtil.exportWordBatch(mapList, titleList, ChbInvestReportConstant.产品定期公告_单份额类净值型产品模板文件, srcFilePath, zipname);
                }
            } else {
                // 多份额类净值型产品
                if (StringUtil.isNotEmpty(fProdId) && StringUtil.equals(DictCodeListExt2.产品类型_中债_净值型产品.getValue(), prodType)) {
                    return WordBatchUtil.exportWordBatch(mapList, titleList, ChbInvestReportConstant.产品定期公告_多份额类净值型产品模板文件, srcFilePath, zipname);
                }
                // 多份额类货币型产品
                else if (StringUtil.isNotEmpty(fProdId) && StringUtil.equals(DictCodeListExt2.产品类型_中债_货币型产品.getValue(), prodType)) {
                    return WordBatchUtil.exportWordBatch(mapList, titleList, ChbInvestReportConstant.产品定期公告_多份额类货币型产品模板文件, srcFilePath, zipname);
                }
            }
        }
        return null;
    }

    private void formatBigDecimal() {
        ChbInvestReportConstant.df1.setRoundingMode(RoundingMode.HALF_UP);
        ChbInvestReportConstant.df2.setRoundingMode(RoundingMode.HALF_UP);
        ChbInvestReportConstant.df3.setRoundingMode(RoundingMode.HALF_UP);
        ChbInvestReportConstant.df4.setRoundingMode(RoundingMode.HALF_UP);
    }

    /**
     * 创建定期报告
     *
     * @param map
     * @param reportName
     * @return
     * @throws Exception
     */
    public File createDoc(Map<String, Object> map, String reportName) throws Exception {
        formatBigDecimal();
        String srcFilePath = ChbInvestReportUtil.getFtlPath();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        List<String> titleList = new ArrayList<String>();
        String prodType = null;
        List<ChbPrdBaseinfo> chbPrdBaseinfos = chbPrdBaseinfo001Mapper.selectOneDate(map);
        if (StringUtil.isEmptyList(chbPrdBaseinfos)) {
            return null;
        }
        ChbPrdBaseinfo chbPrdBaseinfo = chbPrdBaseinfos.get(0);
        map.put("chbPrdBaseinfo", chbPrdBaseinfo);
        String fprodId = (String) map.get("prodId");
        List<ChbPrdBaseinfoDetailWithBLOBs> chbPrdBaseinfoDetailList = chbPrdBaseinfoDetailMapper.selectByFprodid(fprodId);
        map.put("chbPrdBaseinfoDetailList", chbPrdBaseinfoDetailList);
        if (chbPrdBaseinfo != null) {
            prodType = chbPrdBaseinfo.getProdtype();
        }
        this.createData(reportName, map, mapList, titleList);
        if (StringUtil.isEmptyList(chbPrdBaseinfoDetailList)) {
            // 单份额类净值型产品
            if (StringUtil.equals(DictCodeListExt2.产品类型_中债_净值型产品.getValue(), prodType)) {
                return WordBatchUtil.createMillCertificateWord(mapList.get(0), srcFilePath, ChbInvestReportConstant.产品定期公告_单份额类净值型产品模板文件, titleList.get(0));
            }
            // 单份额类货币型产品
            else if (StringUtil.equals(DictCodeListExt2.产品类型_中债_货币型产品.getValue(), prodType)) {
                return WordBatchUtil.createMillCertificateWord(mapList.get(0), srcFilePath, ChbInvestReportConstant.产品定期公告_单份额类货币型产品模板文件, titleList.get(0));
            }
        } else {
            // 多份额类净值型产品
            if (StringUtil.equals(DictCodeListExt2.产品类型_中债_净值型产品.getValue(), prodType)) {
                return WordBatchUtil.createMillCertificateWord(mapList.get(0), srcFilePath, ChbInvestReportConstant.产品定期公告_多份额类净值型产品模板文件, titleList.get(0));
            }
            // 多份额类货币型产品
            else if (StringUtil.equals(DictCodeListExt2.产品类型_中债_货币型产品.getValue(), prodType)) {
                return WordBatchUtil.createMillCertificateWord(mapList.get(0), srcFilePath, ChbInvestReportConstant.产品定期公告_多份额类货币型产品模板文件, titleList.get(0));
            }
        }
        return null;
    }

    /**
     * 组装数据
     *
     * @param reportName
     * @param param
     * @param mapList
     * @param titleList
     */
    public void createData(String reportName, Map<String, Object> param, List<Map<String, Object>> mapList, List<String> titleList) {
        ChbPrdBaseinfo chbPrdBaseinfo = (ChbPrdBaseinfo) param.get("chbPrdBaseinfo");
        List<ChbPrdBaseinfoDetailWithBLOBs> chbPrdBaseinfoDetailList = (List<ChbPrdBaseinfoDetailWithBLOBs>) param.get("chbPrdBaseinfoDetailList");
        if (StringUtil.isNotEmpty(param.get("endDate")) && StringUtil.isNotEmpty(param.get("prodId"))) {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            ChbPeriodicPrdInfo chbPeriodicPrdInfo = this.qryChbPeriodicPrdInfo(param);
            if (!StringUtil.isEmptyList(chbPrdBaseinfoDetailList)) {
                List<String> sprodIdList = chbPrdBaseinfoDetailList.stream().map(ChbPrdBaseinfoDetailWithBLOBs::getProdId).collect(Collectors.toList());
                List<String> sprodNameList = chbPrdBaseinfoDetailList.stream().map(ChbPrdBaseinfoDetailWithBLOBs::getProdName).collect(Collectors.toList());
                chbPeriodicPrdInfo.setSprodIdList(sprodIdList);
                chbPeriodicPrdInfo.setSprodNameList(sprodNameList);
                dataMap.put("sprodIdList", sprodIdList);
                dataMap.put("sprodNameList", sprodNameList);
            }

            // 获取报告起始日期和结束日期
            String reportType = (String) param.get("reportType");
            String startDate = (String) param.get("startDate");
            String year = StringUtil.substring(startDate, 0, 4);
            String dqStartDate = chbInvestReportCommonService.getDqStartDate(reportType, StringUtil.substring(startDate, 0, 4));
            String dqEndDate = chbInvestReportCommonService.getDqEndDate(reportType, StringUtil.substring(startDate, 0, 4));
            String finalDqStartDate = dqStartDate;
            // 若产品起息日>本次报告开始日，则取产品起息日
            if (DateUtil.compareDate(chbPeriodicPrdInfo.getVdate(), DateUtil.parse(dqStartDate)) == 1) {
                finalDqStartDate = DateUtil.format(chbPeriodicPrdInfo.getVdate());
            }
            dataMap.put("startReportDateFmt", DateUtil.format(DateUtil.parse(finalDqStartDate), DateUtil.DEFAULT_DATE_FORMAT_CN));
            dataMap.put("endReportDateFmt", DateUtil.format(DateUtil.parse(dqEndDate), DateUtil.DEFAULT_DATE_FORMAT_CN));
            dataMap.put("startReportDateStr", finalDqStartDate);
            dataMap.put("endReportDateStr", dqEndDate);
            dataMap.put("startReportDate", DateUtil.parse(finalDqStartDate));
            dataMap.put("endReportDate", DateUtil.parse(dqEndDate));
            dataMap.put("vdate", chbPeriodicPrdInfo.getVdate());
            dataMap.put("dqStartDate", DateUtil.parse(dqStartDate));
            dataMap.put("reportTypeFmt", DictCodeListExt3.from(DictCode.中债定期报告类型, reportType).getCodeName());
            dataMap.put(DictCode.产品类型_中债.getValue(), chbPrdBaseinfo.getProdtype());
            dataMap.put("year", StringUtil.substring(dqEndDate, 0, 4));
            dataMap.putAll(param);

            ChbPeriodicValue chbPeriodicValue = this.qryChbPeriodicValue(dataMap);
            if (!StringUtil.isEmptyList(chbPrdBaseinfoDetailList)) {
                List<ChbPeriodicValue> chbPeriodicValues = this.qryChbPeriodicValueDetail(dataMap);
                dataMap.put("valueList", chbPeriodicValues);
            }
            Map<String, ChbPeriodicAssetPosition> chbPeriodicAssetPositionMap = genMonthAssetData(dataMap);
//            List<ChbPeriodicPosition> chbPeriodicPositionList = this.qryChbPeriodicPosition(dataMap);
            List<ChbPeriodicPositionTop10> chbPeriodicPositionTop10List = this.qryChbPeriodicPositionTop10(dataMap);
            List<ChbPeriodicAssetInfo> chbPeriodicAssetInfoList = this.qryChbPeriodicAssetInfo(dataMap);
            List<ChbPeriodicBonus> chbPeriodicBonusList = this.qryChbPeriodicBonus(chbPrdBaseinfo, dataMap);

            dataMap.put("prdInfo", chbPeriodicPrdInfo);//产品信息
            dataMap.put("value", chbPeriodicValue);
//            dataMap.put("positionList", chbPeriodicPositionList);
            dataMap.putAll(chbPeriodicAssetPositionMap);
            dataMap.put("positionTop10List", chbPeriodicPositionTop10List);
            dataMap.put("assetInfoList", chbPeriodicAssetInfoList);
            dataMap.put("bonusList", chbPeriodicBonusList);

            // 适用半年报和年报，季度报告无此章节。
            if (StringUtil.equals(reportType, DictCodeListExt3.中债定期报告类型_上半年报.getValue())
                    || StringUtil.equals(reportType, DictCodeListExt3.中债定期报告类型_下半年报.getValue())
                    || StringUtil.equals(reportType, DictCodeListExt3.中债定期报告类型_年报.getValue())) {
                dataMap.put("depositorReport", "第八章 托管人报告");
            } else {
                dataMap.put("depositorReport", ChbInvestReportConstant.空字符);
            }

            mapList.add(dataMap);

            List<String> prodNameList = new ArrayList<>();
            if (StringUtil.isEmptyList(chbPrdBaseinfoDetailList)) {
                prodNameList.add(chbPeriodicPrdInfo.getProdName());
            } else {
                prodNameList.addAll((List) dataMap.get("sprodNameList"));
            }
            titleList.add(ChbInvestReportUtil.generateFileNameForDQ(prodNameList, reportType, year));
        }
    }

    /**
     * 查询定期报告中资产相关信息
     *
     * @param param
     * @return
     * @throws Exception
     */
    public List<ChbPeriodicPosition> qryChbPeriodicPosition(Map<String, Object> param) {
        if (!Objects.isNull(param.get("endReportDate"))) {
            param.put("endReportDate", param.get("endReportDate"));
        } else {
            param.put("endReportDate", null);
        }
        List<ChbPeriodicPosition> list = chbPeriodicMapper.qryChbPeriodicPosition(param);
        this.dealPositionData(list, (String) param.get("endDate"));
        return list;
    }

    /**
     * 处理返回的数据中需要处理数据字典及特殊字段的
     *
     * @param list
     */
    private void dealPositionData(List<ChbPeriodicPosition> list, String dealdateFmt) {
        if (!StringUtil.isEmptyList(list)) {
            int index = 0;
            for (ChbPeriodicPosition item : list) {
                item.setSeq(Integer.toString(++index));
                // 资产/负债二级类别
                String showName = ChbInvestReportUtil.getDictCodeAndName(DictCode.资产_负债二级类别, item.getAssettype2());
                if (StringUtil.isNotEmpty(showName)) {
                    item.setAssettype2Fmt(showName);
                } else {
                    item.setAssettype2Fmt(ChbInvestReportConstant.横线);
                }
                // 穿透前占总资产比例
                if (item.getPercentAssetvalueA() == null || BigDecimal.ZERO.compareTo(item.getPercentAssetvalueA()) == 0) {
                    item.setPercentAssetvalueAFmt(ChbInvestReportConstant.横线);
                } else {
                    item.setPercentAssetvalueAFmt(String.valueOf(item.getPercentAssetvalueA()));
                }
                //穿透后占总资产比例
                if (item.getPercentAssetvalueB() == null || BigDecimal.ZERO.compareTo(item.getPercentAssetvalueB()) == 0) {
                    item.setPercentAssetvalueBFmt(ChbInvestReportConstant.横线);
                } else {
                    item.setPercentAssetvalueBFmt(String.valueOf(item.getPercentAssetvalueB()));
                }
            }
        } else {
            ChbPeriodicPosition chbPeriodicPosition = new ChbPeriodicPosition();
            chbPeriodicPosition.setSeq(ChbInvestReportConstant.横线);
            chbPeriodicPosition.setAssettype2Fmt(ChbInvestReportConstant.横线);
            chbPeriodicPosition.setPercentAssetvalueAFmt(ChbInvestReportConstant.横线);
            chbPeriodicPosition.setPercentAssetvalueBFmt(ChbInvestReportConstant.横线);
            list.add(chbPeriodicPosition);
        }
    }

    /**
     * 查询定期报告中资产相关信息
     *
     * @param param
     * @return
     * @throws Exception
     */
    public List<ChbPeriodicPositionTop10> qryChbPeriodicPositionTop10(Map<String, Object> param) {
        if (!Objects.isNull(param.get("endReportDate"))) {
            param.put("endReportDate", param.get("endReportDate"));
        } else {
            param.put("endReportDate", null);
        }
        List<ChbPeriodicPositionTop10> list = chbPeriodicMapper.qryChbPeriodicPositionTop10(param);
        this.dealPositionTop10Data(list);
        return list;
    }

    /**
     * 处理返回的数据中需要处理数据字典及特殊字段的
     *
     * @param list
     */
    private void dealPositionTop10Data(List<ChbPeriodicPositionTop10> list) {
        if (!StringUtil.isEmptyList(list)) {
            int index = 0;
            for (ChbPeriodicPositionTop10 item : list) {
                item.setSeq(Integer.toString(++index));
                // 资产名称
                if (StringUtil.isEmpty(item.getAssetName())) {
                    item.setAssetName(ChbInvestReportConstant.横线);
                }
                // 资产/负债二级类别
                String showName = ChbInvestReportUtil.getDictCodeAndName(DictCode.资产_负债二级类别, item.getAssettype2());
                if (StringUtil.isNotEmpty(showName)) {
                    item.setAssettype2Fmt(showName);
                } else {
                    item.setAssettype2Fmt(ChbInvestReportConstant.横线);
                }
                // 持有金额
                if (item.getAssetvalueB() == null || BigDecimal.ZERO.compareTo(item.getAssetvalueB()) == 0) {
                    item.setAssetvalueBFmt(ChbInvestReportConstant.空字符);
                } else {
                    String ccy = ChbInvestReportUtil.getDictCodeAndName(DictCode.币种_中债, item.getCcy());
                    item.setAssetvalueBFmt(ChbInvestReportConstant.df2.format(item.getAssetvalueB()) + ccy);
                }
                // 占总资产比例
                if (item.getPercentAssetvalueB() == null || BigDecimal.ZERO.compareTo(item.getPercentAssetvalueB()) == 0) {
                    item.setPercentAssetvalueBFmt(ChbInvestReportConstant.空字符);
                } else {
                    item.setPercentAssetvalueBFmt(String.valueOf(item.getPercentAssetvalueB()));
                }
            }
        } else {
            ChbPeriodicPositionTop10 chbPeriodicPositionTop10 = new ChbPeriodicPositionTop10();
            chbPeriodicPositionTop10.setSeq(ChbInvestReportConstant.横线);
            chbPeriodicPositionTop10.setAssetName(ChbInvestReportConstant.横线);
            chbPeriodicPositionTop10.setAssettype2Fmt(ChbInvestReportConstant.横线);
            chbPeriodicPositionTop10.setAssetvalueBFmt(ChbInvestReportConstant.横线);
            chbPeriodicPositionTop10.setPercentAssetvalueBFmt(ChbInvestReportConstant.横线);
            list.add(chbPeriodicPositionTop10);
        }
    }

    /**
     * 查询定期报告中资产相关信息
     *
     * @param param
     * @return
     * @throws Exception
     */
    public List<ChbPeriodicAssetInfo> qryChbPeriodicAssetInfo(Map<String, Object> param) {

        if (!Objects.isNull(param.get("endReportDate"))) {
            param.put("endReportDate", param.get("endReportDate"));
        } else {
            param.put("endReportDate", null);
        }
        List<ChbPeriodicAssetInfo> list = chbPeriodicMapper.qryChbPeriodicAssetInfo(param);
        this.dealDictOrSpecialAssetData(list, (String) param.get("endDate"));
        return list;
    }

    /**
     * 处理返回的数据中需要处理数据字典及特殊字段的
     *
     * @param list
     */
    private void dealDictOrSpecialAssetData(List<ChbPeriodicAssetInfo> list, String dealdateFmt) {
        if (!StringUtil.isEmptyList(list)) {
            int index = 0;
            for (ChbPeriodicAssetInfo item : list) {
                item.setSeq(Integer.toString(++index));
                // 资产/负债二级类别
                String showName = ChbInvestReportUtil.getDictCodeAndName(DictCode.资产_负债二级类别, item.getAssetType2());
                if (StringUtil.isNotEmpty(showName)) {
                    item.setAssetType2Fmt(showName);
                } else {
                    item.setAssetType2Fmt(ChbInvestReportConstant.空字符);
                }
                // 项目收益率(利率)%
                if (item.getAssetYield() == null || BigDecimal.ZERO.compareTo(item.getAssetYield()) == 0) {
                    item.setAssetYieldFmt(ChbInvestReportConstant.无);
                } else {
                    item.setAssetYieldFmt(String.valueOf(item.getAssetYield()));
                }
                //剩余融资期限(天)
                if (item.getMdate() != null) {
                    if (DateUtil.compareDate(DateUtil.format(item.getMdate(), DateUtil.DEFAULT_DATE_FORMAT_EN), dealdateFmt) < 0) {
                        item.setSurplusTerm(ChbInvestReportConstant.无);
                    } else {
                        item.setSurplusTerm(String.valueOf(
                                ChbInvestReportUtil.getBetweenDayNumber(DateUtil.parse(dealdateFmt),
                                        DateUtil.parse(DateUtil.format(item.getMdate(), DateUtil.DEFAULT_DATE_FORMAT_EN)))));
                    }
                } else {
                    item.setSurplusTerm(ChbInvestReportConstant.无);
                }
            }
        } else {
            ChbPeriodicAssetInfo chbPeriodicAssetInfo = new ChbPeriodicAssetInfo();
            chbPeriodicAssetInfo.setSeq(ChbInvestReportConstant.横线);
            chbPeriodicAssetInfo.setFinancie(ChbInvestReportConstant.横线);
            chbPeriodicAssetInfo.setAssetType2Fmt(ChbInvestReportConstant.横线);
            chbPeriodicAssetInfo.setAssetName(ChbInvestReportConstant.横线);
            chbPeriodicAssetInfo.setAssetYieldFmt(ChbInvestReportConstant.横线);
            chbPeriodicAssetInfo.setSurplusTerm(ChbInvestReportConstant.横线);
            list.add(chbPeriodicAssetInfo);
        }
    }

    /**
     * 查询定期报告中收益分配相关情况
     *
     * @param params
     * @return
     * @throws Exception
     */
    private List<ChbPeriodicBonus> qryChbPeriodicBonus(ChbPrdBaseinfo chbPrdBaseinfo, Map<String, Object> params) {
        List<ChbPeriodicBonusDeal> list = qryChbPeriodicBonusDeal(params);
        return this.dealBonusValue(chbPrdBaseinfo, list, params);
    }

    private List<ChbPeriodicBonus> dealBonusValue(ChbPrdBaseinfo chbPrdBaseinfo, List<ChbPeriodicBonusDeal> list, Map<String, Object> params) {
        List<ChbPeriodicBonus> bonusList = new ArrayList<>();
        if (Objects.isNull(list) || list.isEmpty()) {
            ChbPeriodicBonus chbPeriodicBonus = new ChbPeriodicBonus();
            chbPeriodicBonus.setRegDate(ChbInvestReportConstant.横线);
            chbPeriodicBonus.setTradeBonus(ChbInvestReportConstant.横线);
            chbPeriodicBonus.setIntBonus(ChbInvestReportConstant.横线);
            bonusList.add(chbPeriodicBonus);
            return bonusList;
        }
        logger.debug("dealBonusValue: List<ChbPeriodicBonusDeal> list=" + JSONUtil.toJsonStr(list));
        ChbPeriodicBonus chbPeriodicBonus = null;
        if (StringUtil.equals(DictCodeListExt2.产品类型_中债_净值型产品.getValue(), chbPrdBaseinfo.getProdtype())) {
            for (ChbPeriodicBonusDeal deal : list) {
                chbPeriodicBonus = new ChbPeriodicBonus();
                if (Objects.isNull(deal.getRegDate())) {
                    chbPeriodicBonus.setRegDate(ChbInvestReportConstant.横线);
                } else {
                    chbPeriodicBonus.setRegDate(DateUtil.format(deal.getRegDate()));
                }
                if (isNullOrZero(deal.getTradeAmt())) {
                    chbPeriodicBonus.setTradeBonus(ChbInvestReportConstant.横线);
                } else {
                    chbPeriodicBonus.setTradeBonus(ChbInvestReportConstant.df1.format(
                            deal.getTradeAmt().divide(deal.getRegTradeQty(), 20, BigDecimal.ROUND_FLOOR).multiply(new BigDecimal(10000))));
                }
                if (isNullOrZero(deal.getIntAmt())) {
                    chbPeriodicBonus.setIntBonus(ChbInvestReportConstant.横线);
                } else {
                    chbPeriodicBonus.setIntBonus(ChbInvestReportConstant.df1.format(
                            deal.getIntAmt().divide(deal.getRegTradeQty(), 20, BigDecimal.ROUND_FLOOR).multiply(new BigDecimal(10000))));
                }
                bonusList.add(chbPeriodicBonus);
            }
        } else if (StringUtil.equals(DictCodeListExt2.产品类型_中债_货币型产品.getValue(), chbPrdBaseinfo.getProdtype())) {
            BigDecimal totalTradeAmt = new BigDecimal("0");
            BigDecimal totalIntAmt = new BigDecimal("0");
            for (ChbPeriodicBonusDeal deal : list) {
                if (!isNullOrZero(deal.getRegTradeQty())) {
                    totalTradeAmt = totalTradeAmt.add(deal.getTradeAmt().divide(deal.getRegTradeQty(), 20, BigDecimal.ROUND_FLOOR).multiply(new BigDecimal(10000)));
                    totalIntAmt = totalIntAmt.add(deal.getIntAmt().divide(deal.getRegTradeQty(), 20, BigDecimal.ROUND_FLOOR).multiply(new BigDecimal(10000)));
                }
            }
            chbPeriodicBonus = new ChbPeriodicBonus();
            if (Objects.isNull(list.get(0).getRegDate())) {
                chbPeriodicBonus.setRegDate(ChbInvestReportConstant.横线);
            } else {
                chbPeriodicBonus.setRegDate(params.get("startReportDateFmt") + ChbInvestReportConstant.横线 + params.get("endReportDateFmt"));
            }
            if (BigDecimal.ZERO.compareTo(totalTradeAmt) == 0) {
                chbPeriodicBonus.setTradeBonus(ChbInvestReportConstant.横线);
            } else {
                chbPeriodicBonus.setTradeBonus(ChbInvestReportConstant.df1.format(totalTradeAmt));
            }
            if (BigDecimal.ZERO.compareTo(totalIntAmt) == 0) {
                chbPeriodicBonus.setIntBonus(ChbInvestReportConstant.横线);
            } else {
                chbPeriodicBonus.setIntBonus(ChbInvestReportConstant.df1.format(totalIntAmt));
            }
            bonusList.add(chbPeriodicBonus);
        }
        return bonusList;
    }

    private boolean isNullOrZero(BigDecimal decimal) {
        return (Objects.isNull(decimal) || BigDecimal.ZERO.compareTo(decimal) == 0);
    }

    /**
     * 查询产品分红明细表
     *
     * @param params
     * @return
     */
    private List<ChbPeriodicBonusDeal> qryChbPeriodicBonusDeal(Map<String, Object> params) {
        Map<String, Object> map = new HashMap();
        map.put("prodId", params.get("prodId"));
        map.put("startReportDate", params.get("startReportDate"));
        map.put("endReportDate", params.get("endReportDate"));
        return chbPeriodicMapper.qryChbPeriodicBonusDeal(params);
    }

    /**
     * 查询定期报告中净值、存续规模及收益相关情况
     *
     * @param params
     * @return
     * @throws Exception
     */
    private ChbPeriodicValue qryChbPeriodicValue(Map<String, Object> params) {
        ChbPeriodicIndex qcChbPeriodicIndex = qryQcPeriodicIndex(params);
        ChbPeriodicIndex qmChbPeriodicIndex = qryQmPeriodicIndex(params);
        return this.dealValue(qcChbPeriodicIndex, qmChbPeriodicIndex, params);
    }

    /**
     * 查询定期报告中净值、存续规模及收益相关情况——子产品
     *
     * @param params
     * @return
     * @throws Exception
     */
    private List<ChbPeriodicValue> qryChbPeriodicValueDetail(Map<String, Object> params) {
        List<ChbPeriodicValue> list = new ArrayList<>();
        List<ChbPeriodicIndex> qcChbPeriodicIndexList = qryQcPeriodicDetailIndex(params);
        List<ChbPeriodicIndex> qmChbPeriodicIndexList = qryQmPeriodicDetailIndex(params);
        for (int i = 0; i < qcChbPeriodicIndexList.size(); i++) {
            ChbPeriodicIndex qcChbPeriodicIndex = qcChbPeriodicIndexList.get(i);
            ChbPeriodicIndex qmChbPeriodicIndex = qmChbPeriodicIndexList.get(i);
            Map<String, ChbPeriodicValue> map = new HashMap<>(4);
            ChbPeriodicValue chbPeriodicValue = this.dealValue(qcChbPeriodicIndex, qmChbPeriodicIndex, params);
            list.add(chbPeriodicValue);
        }
        return list;
    }

    /**
     * 查询期初产品指标表
     *
     * @param params
     * @return
     */
    private ChbPeriodicIndex qryQcPeriodicIndex(Map<String, Object> params) {
        Map<String, Object> map = new HashMap();
        // 若产品起息日>=本次报告开始日，则取产品起息日
        if (DateUtil.compareDate((Date) params.get("vdate"), (Date) params.get("dqStartDate")) == 1
                || DateUtil.compareDate((Date) params.get("vdate"), (Date) params.get("dqStartDate")) == 0) {
            map.put("qryDate", params.get("vdate"));
        } else {
            map.put("qryDate", DateUtil.addDate((Date) params.get("dqStartDate"), 0, 0, -1));
        }
        map.put("startReportDate", map.get("qryDate"));
        map.put("endReportDate", params.get("endReportDate"));
        map.put("prodId", params.get("prodId"));
        return qryPeriodicIndexHandler(map);
    }

    /**
     * 查询期初产品指标表——子产品
     *
     * @param params
     * @return
     */
    private List<ChbPeriodicIndex> qryQcPeriodicDetailIndex(Map<String, Object> params) {
        Map<String, Object> map = new HashMap();
        // 若产品起息日>=本次报告开始日，则取产品起息日
        if (DateUtil.compareDate((Date) params.get("vdate"), (Date) params.get("dqStartDate")) == 1
                || DateUtil.compareDate((Date) params.get("vdate"), (Date) params.get("dqStartDate")) == 0) {
            map.put("qryDate", params.get("vdate"));
        } else {
            map.put("qryDate", DateUtil.addDate((Date) params.get("dqStartDate"), 0, 0, -1));
        }
        map.put("startReportDate", map.get("qryDate"));
        map.put("endReportDate", params.get("endReportDate"));
        map.put("prodId", params.get("prodId"));
        return qryPeriodicIndexDetailHandler(map);
    }

    /**
     * 查询期末产品指标表
     *
     * @param params
     * @return
     */
    private ChbPeriodicIndex qryQmPeriodicIndex(Map<String, Object> params) {
        Map<String, Object> map = new HashMap();
        // 本期净值收益率计算 R1为期初每万份收益
        if (DateUtil.compareDate((Date) params.get("vdate"), (Date) params.get("dqStartDate")) == 1
                || DateUtil.compareDate((Date) params.get("vdate"), (Date) params.get("dqStartDate")) == 0) {
            map.put("startReportDate", params.get("vdate"));
        } else {
            map.put("startReportDate", DateUtil.addDate((Date) params.get("dqStartDate"), 0, 0, -1));
        }
        map.put("qryDate", params.get("endReportDate"));
        map.put("endReportDate", params.get("endReportDate"));
        map.put("prodId", params.get("prodId"));
        return qryPeriodicIndexHandler(map);
    }

    /**
     * 查询期末产品指标表——子产品
     *
     * @param params
     * @return
     */
    private List<ChbPeriodicIndex> qryQmPeriodicDetailIndex(Map<String, Object> params) {
        Map<String, Object> map = new HashMap();
        // 本期净值收益率计算 R1为期初每万份收益
        if (DateUtil.compareDate((Date) params.get("vdate"), (Date) params.get("dqStartDate")) == 1
                || DateUtil.compareDate((Date) params.get("vdate"), (Date) params.get("dqStartDate")) == 0) {
            map.put("startReportDate", params.get("vdate"));
        } else {
            map.put("startReportDate", DateUtil.addDate((Date) params.get("dqStartDate"), 0, 0, -1));
        }
        map.put("qryDate", params.get("endReportDate"));
        map.put("endReportDate", params.get("endReportDate"));
        map.put("prodId", params.get("prodId"));
        return qryPeriodicIndexDetailHandler(map);
    }

    /**
     * 查询产品指标表
     *
     * @param params
     * @return
     */
    private ChbPeriodicIndex qryPeriodicIndexHandler(Map<String, Object> params) {
        List<ChbPeriodicIndex> list = chbPeriodicMapper.qryChbPeriodicIndex(params);
        ChbPeriodicIndex chbPeriodicIndex = new ChbPeriodicIndex();
        if (!StringUtil.isEmptyList(list)) {
            chbPeriodicIndex = list.get(0);
        }
        return chbPeriodicIndex;
    }

    /**
     * 查询产品指标表（子产品）
     *
     * @param params
     * @return
     */
    private List<ChbPeriodicIndex> qryPeriodicIndexDetailHandler(Map<String, Object> params) {
        List<ChbPeriodicIndex> list = chbPeriodicMapper.qryChbPeriodicIndexDetail(params);
        return list;
    }

    /**
     * 查询定期报告中产品相关信息
     *
     * @param params
     * @return
     * @throws Exception
     */
    public ChbPeriodicPrdInfo qryChbPeriodicPrdInfo(Map<String, Object> params) {
        List<ChbPeriodicPrdInfo> list = chbPeriodicMapper.qryChbPeriodicPrdInfo(params);
        ChbPeriodicPrdInfo chbPeriodicPrdInfo = new ChbPeriodicPrdInfo();
        if (!StringUtil.isEmptyList(list)) {
            chbPeriodicPrdInfo = list.get(0);
        }
        this.dealDictOrSpecialObj(chbPeriodicPrdInfo);
        return chbPeriodicPrdInfo;
    }

    /**
     * 处理返回的数据中需要处理数据字典及特殊字段的
     *
     * @param chbPeriodicPrdInfo
     */
    private void dealDictOrSpecialObj(ChbPeriodicPrdInfo chbPeriodicPrdInfo) {
        //理财信息系统登记编码
        if (StringUtil.isEmpty(chbPeriodicPrdInfo.getRegCode())) {
            chbPeriodicPrdInfo.setRegCode(ChbInvestReportConstant.空字符);
        }
        //兑付本金币种显示名称
        String showName = ChbInvestReportUtil.getDictCodeAndName(DictCode.币种_中债, chbPeriodicPrdInfo.getCashPrinCcy());
        if (StringUtil.isNotEmpty(showName)) {
            if (showName.contains(GlobalConstant.COMMA)) {
                chbPeriodicPrdInfo.setCashPrinCcyFmt("人民币/美元");
            } else {
                chbPeriodicPrdInfo.setCashPrinCcyFmt(showName);
            }
        } else {
            chbPeriodicPrdInfo.setCashPrinCcyFmt(ChbInvestReportConstant.空字符);
        }
        //产品运作模式显示名称
        if (StringUtil.isNotEmpty(chbPeriodicPrdInfo.getOperateMode())) {
            if (StringUtil.equals(chbPeriodicPrdInfo.getOperateMode(), DictCodeListExt2.产品运作模式_封闭式净值型.getValue())
                    || StringUtil.equals(chbPeriodicPrdInfo.getOperateMode(), DictCodeListExt2.产品运作模式_封闭式非净值型.getValue())) {
                chbPeriodicPrdInfo.setOperateModeFmt(DictCodeList.开放类型_封闭式.getCodeName());
            } else if (StringUtil.equals(chbPeriodicPrdInfo.getOperateMode(), DictCodeListExt2.产品运作模式_开放式净值型.getValue())
                    || StringUtil.equals(chbPeriodicPrdInfo.getOperateMode(), DictCodeListExt2.产品运作模式_开放式非净值型.getValue())) {
                chbPeriodicPrdInfo.setOperateModeFmt(DictCodeList.开放类型_开放式.getCodeName());
            }
        } else {
            chbPeriodicPrdInfo.setOperateModeFmt(ChbInvestReportConstant.空字符);
        }
        //投资性质显示名称
        showName = ChbInvestReportUtil.getDictCodeAndName(DictCode.投资性质, chbPeriodicPrdInfo.getIvstorientation());
        if (StringUtil.isNotEmpty(showName)) {
            chbPeriodicPrdInfo.setIvstorientationFmt(showName);
        } else {
            chbPeriodicPrdInfo.setIvstorientationFmt(ChbInvestReportConstant.空字符);
        }
        //募集方式显示名称
        showName = ChbInvestReportUtil.getDictCodeAndName(DictCode.募集方式_中债, chbPeriodicPrdInfo.getRaiseType());
        if (StringUtil.isNotEmpty(showName)) {
            chbPeriodicPrdInfo.setRaiseTypeFmt(showName);
        } else {
            chbPeriodicPrdInfo.setRaiseTypeFmt(ChbInvestReportConstant.空字符);
        }
        //产品风险评级显示名称
        if (StringUtil.isNotEmpty(chbPeriodicPrdInfo.getRiskLevel())) {
            if (StringUtil.equals(chbPeriodicPrdInfo.getRiskLevel(), DictCodeListExt2.产品风险等级_中债_一级_低.getValue())) {
                chbPeriodicPrdInfo.setRiskLevelFmt("PR1级（谨慎型）");
            } else if (StringUtil.equals(chbPeriodicPrdInfo.getRiskLevel(), DictCodeListExt2.产品风险等级_中债_二级_中低.getValue())) {
                chbPeriodicPrdInfo.setRiskLevelFmt("PR2级（稳健型）");
            } else if (StringUtil.equals(chbPeriodicPrdInfo.getRiskLevel(), DictCodeListExt2.产品风险等级_中债_三级_中.getValue())) {
                chbPeriodicPrdInfo.setRiskLevelFmt("PR3级（平衡型）");
            } else if (StringUtil.equals(chbPeriodicPrdInfo.getRiskLevel(), DictCodeListExt2.产品风险等级_中债_四级_中高.getValue())) {
                chbPeriodicPrdInfo.setRiskLevelFmt("PR4级（进取型）");
            } else if (StringUtil.equals(chbPeriodicPrdInfo.getRiskLevel(), DictCodeListExt2.产品风险等级_中债_五级_高.getValue())) {
                chbPeriodicPrdInfo.setRiskLevelFmt("PR5级（激进型）");
            }
        } else {
            chbPeriodicPrdInfo.setRiskLevelFmt((ChbInvestReportConstant.空字符));
        }
        //杠杆水平上限显示名称
        if (StringUtil.isNotEmpty(chbPeriodicPrdInfo.getRiskLevel()) && StringUtil.isNotEmpty(chbPeriodicPrdInfo.getOperateMode())) {
            if ((StringUtil.equals(chbPeriodicPrdInfo.getOperateMode(), DictCodeListExt2.产品运作模式_封闭式净值型.getValue())
                    || StringUtil.equals(chbPeriodicPrdInfo.getOperateMode(), DictCodeListExt2.产品运作模式_封闭式非净值型.getValue()))
                    && (StringUtil.equals(chbPeriodicPrdInfo.getRaiseType(), DictCodeListExt2.募集方式_中债_公募.getValue())
                    || StringUtil.equals(chbPeriodicPrdInfo.getRaiseType(), DictCodeListExt2.募集方式_中债_私募.getValue()))) {
                chbPeriodicPrdInfo.setLeverUpperLimitFmt("200%");
            } else if ((StringUtil.equals(chbPeriodicPrdInfo.getOperateMode(), DictCodeListExt2.产品运作模式_开放式净值型.getValue())
                    || StringUtil.equals(chbPeriodicPrdInfo.getOperateMode(), DictCodeListExt2.产品运作模式_开放式非净值型.getValue()))
                    && StringUtil.equals(chbPeriodicPrdInfo.getRaiseType(), DictCodeListExt2.募集方式_中债_公募.getValue())) {
                chbPeriodicPrdInfo.setLeverUpperLimitFmt("140%");
            }
        } else {
            chbPeriodicPrdInfo.setLeverUpperLimitFmt(ChbInvestReportConstant.空字符);
        }
        //起息日显示名称
        if (Objects.isNull(chbPeriodicPrdInfo.getVdate())) {
            chbPeriodicPrdInfo.setVdateFmt(ChbInvestReportConstant.空字符);
        } else {
            chbPeriodicPrdInfo.setVdateFmt(DateUtil.format(chbPeriodicPrdInfo.getVdate(), DateUtil.DEFAULT_DATE_FORMAT_EN));
        }
        //到期日显示名称
        if (Objects.isNull(chbPeriodicPrdInfo.getMdate())) {
            chbPeriodicPrdInfo.setMdateFmt(ChbInvestReportConstant.空字符);
        } else if (DateUtil.compareDate(ChbInvestReportConstant.无特定存续期限日期, DateUtil.format(chbPeriodicPrdInfo.getMdate())) != 1) {
            chbPeriodicPrdInfo.setMdateFmt(ChbInvestReportConstant.无特定存续期限);
        } else {
            chbPeriodicPrdInfo.setMdateFmt(DateUtil.format(chbPeriodicPrdInfo.getMdate(), DateUtil.DEFAULT_DATE_FORMAT_EN));
        }

        //境内外显示名称
        List<String> nameList = new ArrayList<>();
        if (StringUtil.isEmpty(chbPeriodicPrdInfo.getDepName())) {
            chbPeriodicPrdInfo.setDepName(ChbInvestReportConstant.空字符);
        }
        //交易日期显示名称
        chbPeriodicPrdInfo.setDealdateFmt(DateUtil.format(chbPeriodicPrdInfo.getDealdate(), DateUtil.DEFAULT_DATE_FORMAT_EN));
    }

    /**
     * 处理返回的数据中需要处理数据值
     *
     * @param qcDto  期初dto
     * @param qmDto  期末dto
     * @param params
     * @return
     */
    private ChbPeriodicValue dealValue(ChbPeriodicIndex qcDto, ChbPeriodicIndex qmDto, Map<String, Object> params) {
        ChbPeriodicValue value = new ChbPeriodicValue();
        // 期初估值日期
        if (Objects.isNull(qcDto.getValueDate())) {
            value.setValueDateQC(ChbInvestReportConstant.横线);
        } else {
            value.setValueDateQC(DateUtil.format(qcDto.getValueDate()));
        }
        // 若产品起息日>本次报告开始日
        if (DateUtil.compareDate((Date) params.get("vdate"), (Date) params.get("dqStartDate")) == 1) {
            // 期初份额净值
            if (StringUtil.equals(DictCodeListExt2.产品类型_中债_净值型产品.getValue(), params.get(DictCode.产品类型_中债.getValue()))) {
                value.setUnitValueQC(ChbInvestReportConstant.df1.format(1));
            } else if (StringUtil.equals(DictCodeListExt2.产品类型_中债_货币型产品.getValue(), params.get(DictCode.产品类型_中债.getValue()))) {
                value.setUnitValueQC(ChbInvestReportConstant.横线);
            }
            // 期初份额总数
            value.setTotalValueQC(ChbInvestReportConstant.横线);
            // 期初资产净值
            value.setAssetUnitValueQC(ChbInvestReportConstant.横线);
            // 期初累计净值
            value.setAssetTotalValueQC(ChbInvestReportConstant.横线);
        } else {
            if (StringUtil.equals(DictCodeListExt2.产品类型_中债_净值型产品.getValue(), params.get(DictCode.产品类型_中债.getValue()))) {
                // 期初份额净值
                if (Objects.isNull(qcDto.getUnitValue()) || BigDecimal.ZERO.compareTo(qcDto.getUnitValue()) == 0) {
                    value.setUnitValueQC(ChbInvestReportConstant.横线);
                } else {
                    value.setUnitValueQC(ChbInvestReportConstant.df1.format(qcDto.getUnitValue()));
                }
                // 期初累计净值
                if (Objects.isNull(qcDto.getUnitValueT()) || BigDecimal.ZERO.compareTo(qcDto.getUnitValueT()) == 0) {
                    value.setAssetTotalValueQC(ChbInvestReportConstant.横线);
                } else {
                    value.setAssetTotalValueQC(ChbInvestReportConstant.df1.format(qcDto.getUnitValueT()));
                }
            } else if (StringUtil.equals(DictCodeListExt2.产品类型_中债_货币型产品.getValue(), params.get(DictCode.产品类型_中债.getValue()))) {
                // 期初份额净值
                if (Objects.isNull(qcDto.getwYield()) || BigDecimal.ZERO.compareTo(qcDto.getwYield()) == 0
                        || DateUtil.compareDate((Date) params.get("vdate"), (Date) params.get("dqStartDate")) == 1) {
                    value.setUnitValueQC(ChbInvestReportConstant.横线);
                } else {
                    value.setUnitValueQC(ChbInvestReportConstant.df1.format(qcDto.getwYield()));
                }
                // 期初累计净值
                if (Objects.isNull(qcDto.getYield7()) || BigDecimal.ZERO.compareTo(qcDto.getYield7()) == 0
                        || DateUtil.compareDate((Date) params.get("vdate"), (Date) params.get("dqStartDate")) == 1) {
                    value.setAssetTotalValueQC(ChbInvestReportConstant.横线);
                } else {
                    value.setAssetTotalValueQC(ChbInvestReportConstant.df4.format(qcDto.getYield7()));
                }
            }
            // 期初份额总数
            if (Objects.isNull(qcDto.getCapital()) || BigDecimal.ZERO.compareTo(qcDto.getCapital()) == 0) {
                value.setTotalValueQC(ChbInvestReportConstant.横线);
            } else {
                value.setTotalValueQC(ChbInvestReportConstant.df2.format(qcDto.getCapital()));
            }
            // 期初资产净值
            if (Objects.isNull(qcDto.getAssetValueMarket()) || BigDecimal.ZERO.compareTo(qcDto.getAssetValueMarket()) == 0
                    || DateUtil.compareDate((Date) params.get("vdate"), (Date) params.get("dqStartDate")) == 1) {
                value.setAssetUnitValueQC(ChbInvestReportConstant.横线);
            } else {
                value.setAssetUnitValueQC(ChbInvestReportConstant.df2.format(qcDto.getAssetValueMarket()));
            }
        }

        // 期末估值日期
        if (Objects.isNull(qmDto.getValueDate())) {
            value.setValueDate(ChbInvestReportConstant.横线);
        } else {
            value.setValueDate(DateUtil.format(qmDto.getValueDate()));
        }
        if (StringUtil.equals(DictCodeListExt2.产品类型_中债_净值型产品.getValue(), params.get(DictCode.产品类型_中债.getValue()))) {
            // 期末份额净值
            if (Objects.isNull(qmDto.getUnitValue()) || BigDecimal.ZERO.compareTo(qmDto.getUnitValue()) == 0) {
                value.setUnitValue(ChbInvestReportConstant.df1.format(BigDecimal.ZERO));
            } else {
                value.setUnitValue(ChbInvestReportConstant.df1.format(qmDto.getUnitValue()));
            }
            // 期末累计净值
            if (Objects.isNull(qmDto.getUnitValueT()) || BigDecimal.ZERO.compareTo(qmDto.getUnitValueT()) == 0) {
                value.setAssetTotalValue(ChbInvestReportConstant.df1.format(BigDecimal.ZERO));
            } else {
                value.setAssetTotalValue(ChbInvestReportConstant.df1.format(qmDto.getUnitValueT()));
            }
            // 期间累计净值增长率
            // 期初累计净值 空 或者 起息日>报告开始日 默认取1
            BigDecimal qcUnitValueT = null;
            if (isNullOrZero(qcDto.getUnitValueT())
                    || DateUtil.compareDate((Date) params.get("vdate"), (Date) params.get("dqStartDate")) == 1) {
                qcUnitValueT = new BigDecimal("1");
            } else {
                qcUnitValueT = qcDto.getUnitValueT();
            }
            if (!Objects.isNull(qmDto.getUnitValueT())) {
                BigDecimal increaseRateOfCumulativeValue = (qmDto.getUnitValueT().subtract(qcUnitValueT))
                        .divide(qcUnitValueT, 20, BigDecimal.ROUND_FLOOR)
                        .divide(new BigDecimal(DateUtil.getBetweenDayNumber(qcDto.getValueDate(), qmDto.getValueDate())), 20, BigDecimal.ROUND_FLOOR)
                        .multiply(new BigDecimal(365));
                value.setIncreaseRateOfCumulativeValue(ChbInvestReportConstant.df3.format(increaseRateOfCumulativeValue.abs()));
            } else {
                value.setIncreaseRateOfCumulativeValue(ChbInvestReportConstant.df3.format(BigDecimal.ZERO));
            }
        } else if (StringUtil.equals(DictCodeListExt2.产品类型_中债_货币型产品.getValue(), params.get(DictCode.产品类型_中债.getValue()))) {
            // 期末份额净值
            if (Objects.isNull(qmDto.getwYield()) || BigDecimal.ZERO.compareTo(qmDto.getwYield()) == 0) {
                value.setUnitValue(ChbInvestReportConstant.df1.format(BigDecimal.ZERO));
            } else {
                value.setUnitValue(ChbInvestReportConstant.df1.format(qmDto.getwYield()));
            }
            // 期末累计净值
            if (Objects.isNull(qmDto.getYield7()) || BigDecimal.ZERO.compareTo(qmDto.getYield7()) == 0) {
                value.setAssetTotalValue(ChbInvestReportConstant.df4.format(BigDecimal.ZERO));
            } else {
                value.setAssetTotalValue(ChbInvestReportConstant.df4.format(qmDto.getYield7()));
            }
            // 本期净值收益率
            if (Objects.isNull(qmDto.getValuePercent()) || BigDecimal.ZERO.compareTo(qmDto.getValuePercent()) == 0) {
                value.setIncreaseRateOfCurrentValue(ChbInvestReportConstant.df3.format(BigDecimal.ZERO));
            } else {
                value.setIncreaseRateOfCurrentValue(ChbInvestReportConstant.df3.format(qmDto.getValuePercent().abs()));
            }
        }
        // 期末份额总数
        if (Objects.isNull(qmDto.getCapital()) || BigDecimal.ZERO.compareTo(qmDto.getCapital()) == 0) {
            value.setTotalValue(ChbInvestReportConstant.df2.format(BigDecimal.ZERO));
        } else {
            value.setTotalValue(ChbInvestReportConstant.df2.format(qmDto.getCapital()));
        }
        // 期末资产净值
        if (Objects.isNull(qmDto.getAssetValueMarket()) || BigDecimal.ZERO.compareTo(qmDto.getAssetValueMarket()) == 0) {
            value.setAssetUnitValue(ChbInvestReportConstant.df2.format(BigDecimal.ZERO));
        } else {
            value.setAssetUnitValue(ChbInvestReportConstant.df2.format(qmDto.getAssetValueMarket()));
        }

        return value;
    }


    private Map<String, ChbPeriodicAssetPosition> genMonthAssetData(Map<String, Object> params) {
        String prodId = ((String) params.get("prodId"));
        List prodIdList = new ArrayList();
        prodIdList.add(prodId);

        Date lastday = (Date) params.get("endReportDate");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastday);
        // 获取第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstday = calendar.getTime();

        logger.info("查询期末月报资产头寸开始：");
        List<ChbMonthAssetPosition> assetMonthPositionList = chbMonthAssetPositionMapper.selectByMonthDateAndProdId(DateUtil.format(firstday),
                DateUtil.format(lastday), prodId);
        logger.info("查询期末月报资产头寸结束：");
        // 用于统计穿透前期末余额 穿透后期末余额,key=propertyCode
        Map<String, ChbMonthReportDetail> assetMonthReportDetailMap = new HashMap<String, ChbMonthReportDetail>();
        Map<String, ChbPeriodicAssetPosition> periodicAssetPositionMap = new HashMap<>();

        if (!CollectionUtils.isEmpty(assetMonthPositionList)) {
            // 根据配置属性自动汇总相应属性值
            logger.info("根据配置属性自动汇总相应属性值开始：");
            ChbMonthReport report = new ChbMonthReport();
            report.setProdids(prodIdList);
            report.setStartdate(firstday);
            report.setEnddate(lastday);
            assetMonthReportDetailMap = countMonthReportAmtInvest(assetMonthPositionList, firstday, report);
            logger.info(JSONUtil.toJsonStr(assetMonthReportDetailMap));
            logger.info("根据配置属性自动汇总相应属性值结束：");

            ChbPeriodicAssetPosition position = null;
            BigDecimal totalBalance = assetMonthReportDetailMap.get("AMT1_15合计").getBalance();
            BigDecimal totalBottomBalance = assetMonthReportDetailMap.get("AMT1_15合计").getBottombalance();
            for (Map.Entry<String, ChbMonthReportDetail> detailEntry : assetMonthReportDetailMap.entrySet()) {
                if (detailEntry.getKey().contains("合计")) {
                    position = new ChbPeriodicAssetPosition();
                    BeanUtil.copyProperties(detailEntry.getValue(), position);
                    if (isNullOrZero(position.getBalance())) {
                        position.setPercentAssetvalueAFmt(ChbInvestReportConstant.横线);
                    } else {
                        position.setPercentAssetvalueA(position.getBalance().divide(totalBalance, 20, RoundingMode.FLOOR));
                        position.setPercentAssetvalueAFmt(ChbInvestReportConstant.df3.format(position.getPercentAssetvalueA()));
                    }
                    if (isNullOrZero(position.getBottombalance())) {
                        position.setPercentAssetvalueBFmt(ChbInvestReportConstant.横线);
                    } else {
                        position.setPercentAssetvalueB(position.getBottombalance().divide(totalBottomBalance, 20, RoundingMode.FLOOR));
                        position.setPercentAssetvalueBFmt(ChbInvestReportConstant.df3.format(position.getPercentAssetvalueB()));
                    }
                    periodicAssetPositionMap.put(detailEntry.getKey(), position);
                }
            }
        }
        return periodicAssetPositionMap;
    }

    /**
     * 功能:统计期末资金理财投向情况
     *
     * @param assetMonthPositionList
     * @param reportStartDate        月报开始日期 如：201801
     *                               void
     * @version 1.6.0
     * @author：weiyuqiang 2019年1月31日
     * @since 1.6.0
     */
    private Map<String, ChbMonthReportDetail> countMonthReportAmtInvest(List<ChbMonthAssetPosition> assetMonthPositionList
            , Date reportStartDate, ChbMonthReport report) {
        // 规则项检查
        if (!checkRuleIsRight(true)) {
            return null;
        }
        //信用债
        String xyz = "1112,1113,1114,1115,1116,1117";
        // 用于统计穿透前期末余额 穿透后期末余额,key=propertyCode
        Map<String, ChbMonthReportDetail> assetMonthReportDetailMap = new HashMap<String, ChbMonthReportDetail>();

        // 分支机构汇总类
        Map<String, Map<String, ChbMonthReportDetail>> deptMap = new HashMap();

        // 根据配置规则汇总相应数据
        for (ChbMonthAssetPosition position : assetMonthPositionList) {
            // 数据是否包含机构并且当前是否是联社模式
            if (null != position.getOrgId() && !deptMap.containsKey(position.getOrgId()) && CommonUtil.isBAmodel()) {
                //各机构信息新建并保存
                //ADD BY LEIPENG 20200708 如果产品未建主表.则资产新建
                ChbMonthReport prdReport = monthReportServiceUtil.queryMonthReport(report.getTermid(), position.getOrgId(), "01");

                if (null == prdReport) {
                    report.setOrgCode(position.getOrgId());
                }
                deptMap.put(position.getOrgId(), new HashMap<String, ChbMonthReportDetail>());
            }

            for (ChbMonthAssetRule singleRule : ChbMonthAssetRule.values()) {
                // 合计值单独处理
                if (singleRule.getPropertyName().contains("合计")) {
                    // 判断key值是否已存在，已存在则跳过
                    if (null != deptMap.get(position.getOrgId()) && deptMap.get(position.getOrgId()).containsKey(singleRule.getPropertyCode() + "合计")) {
                        continue;
                    } else {
                        //汇总表记录总数
                        if (!assetMonthReportDetailMap.containsKey(singleRule.getPropertyCode() + "合计")) {
                            assetMonthReportDetailMap.put(singleRule.getPropertyCode() + "合计",
                                    buildDetail(reportStartDate, singleRule.getPropertyCode(), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));
                        }
                        //保存分支的合计数据
                        if (null != deptMap.get(position.getOrgId())) {
                            //拷贝一份相同数据并传入当前机构分支数据中
                            deptMap.get(position.getOrgId()).put(singleRule.getPropertyCode() + "合计",
                                    buildDetail(reportStartDate, singleRule.getPropertyCode(), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

                        }
                    }
                }
                // 根据配置规则代码匹配资产头寸中相应属性
                if (!StringUtil.isEmptyList(singleRule.getRuleCodeList())
                        && !StringUtil.isEmptyList(singleRule.getRuleValueList())) {
                    // 该笔头寸记录是否匹配配置规则
                    boolean isPattern = true;
                    // 判断是否多条件交集处理
                    for (int i = 0; i < singleRule.getRuleCodeList().size(); i++) {
                        // 重新初始化是否匹配配置规则标记
                        isPattern = true;
                        // 债券信用评级
                        if (singleRule.getRuleCodeList().get(i).equalsIgnoreCase("RATINGAA+")) {
                            if (!xyz.contains(position.getAssettype2()) || position.getRating() == null || compareRating(position.getRating(), singleRule.getRuleValueList().get(i)) < 0
                            ) {
                                isPattern = false;
                                break;
                            }
                        } else if (singleRule.getRuleCodeList().get(i).equalsIgnoreCase("RATINGAA-")) {
                            if (!xyz.contains(position.getAssettype2()) || position.getRating() == null || compareRating(position.getRating(), singleRule.getRuleValueList().get(i)) >= 0
                            ) {
                                isPattern = false;
                                break;
                            }
                        } else if (singleRule.getRuleCodeList().get(i).equalsIgnoreCase("RATINGNULL")) {
                            if (!xyz.contains(position.getAssettype2()) || position.getRating() != null) {
                                isPattern = false;
                                break;
                            }
                        } else { // 非债券信用评级
                            // 判断是否多条件任取其一，即rulecode和value一一对应以“|”隔开，格式为code1|code2,value1|value2
                            if (singleRule.getRuleCodeList().get(i).contains(GlobalConstant.VERTICAL_LINE)
                                    || singleRule.getRuleValueList().get(i).contains(GlobalConstant.VERTICAL_LINE)) {

                                List<String> ruleCodeList = StringUtil.splitToList(GlobalConstant.VERTICAL_LINE,
                                        singleRule.getRuleCodeList().get(i));
                                List<String> valueList = StringUtil.splitToList(GlobalConstant.VERTICAL_LINE,
                                        singleRule.getRuleValueList().get(i));
                                isPattern = false;
                                String ruleCode = ruleCodeList.get(0);
                                for (int k = 0; k < valueList.size(); k++) {
                                    // 如果ruleCode相同但是多条件，则支持只输入一个code，如：code1,value1|value2|value3
                                    // 否则需一一对应以“|”隔开，格式为code1|code2,value1|value2
                                    if (ruleCodeList.size() > 1) {
                                        ruleCode = ruleCodeList.get(k);
                                    }
                                    // 支持反向判断！value1
                                    if (valueList.get(k).startsWith("!")) {
                                        // 只要有一条满足则该条头寸不计入
                                        if (!valueList.get(k).substring(1)
                                                .equals(getGetMethod(position, ruleCode))) {
                                            isPattern = true;
                                            break;
                                        }
                                    } else {
                                        // 只有多个条件都不满足时该头寸才不计入
                                        if (valueList.get(k).equals(getGetMethod(position, ruleCode))) {
                                            isPattern = true;
                                            break;
                                        }
                                    }
                                }
                                if (!isPattern) {
                                    break;
                                }
                            } else {// 多条件同时满足才计入
                                // 支持反向判断！value1
                                if (singleRule.getRuleValueList().get(i).startsWith("!")) {
                                    // 只要有一条满足则该条头寸不计入
                                    if (singleRule.getRuleValueList().get(i).substring(1
                                    )
                                            .equals(getGetMethod(position,
                                                    singleRule.getRuleCodeList().get(i)))) {
                                        isPattern = false;
                                        break;
                                    }
                                } else {
                                    // 只要有一条不满足则该条头寸不计入
                                    if (!singleRule.getRuleValueList().get(i)
                                            .equals(getGetMethod(position, singleRule.getRuleCodeList()
                                                    .get(i)))) {
                                        isPattern = false;
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    if (singleRule.getPropertyCode().contains("AMT1_4_10") ||
                            singleRule.getPropertyCode().contains("AMT1_4_c")) {
                        logger.debug("检查数据");
                    }

                    // 值域匹配则累计，值域不匹配则计0
                    // 判断key值是否已存在，已存在则累计，未存在则新建
                    //	汇总值数据组装
                    if (assetMonthReportDetailMap.containsKey(singleRule.getPropertyCode())
                            || assetMonthReportDetailMap.containsKey(singleRule.getPropertyCode() + "合计")) {
                        // 非合计值
                        if (assetMonthReportDetailMap.containsKey(singleRule.getPropertyCode())) {
                            ChbMonthReportDetail detail = assetMonthReportDetailMap.get(singleRule.getPropertyCode());
                            // 已存在计入本次头寸值，否则不计入本次头寸值
                            if (isPattern) {
                                // 穿透前科目余额 = 穿透前科目余额 + 新增值
                                detail.setBalance(detail.getBalance().add(position.getAssetvalueA() == null ? BigDecimal.ZERO : position.getAssetvalueA()));
                                // 穿透后科目余额 = 穿透后科目余额 + 新增值
                                detail.setBottombalance(detail.getBottombalance().add(position.getAssetvalueB() == null ? BigDecimal.ZERO : position.getAssetvalueB()));
                                /**G06资产端改造  add by  dongxiangyang  start*/

                                //合规穿透前科目余额 = 合规穿透前科目余额 + （【是否合规产品】 = 是 【新增穿透前值】 else 【0】）
                                if (StringUtil.equals(DictCodeListExt1.新老产品标记_新产品.getValue(), position.getIsFhxgcp())) {
                                    detail.setXgbalance(detail.getXgbalance().add(position.getAssetvalueA() == null ? BigDecimal.ZERO : position.getAssetvalueA()));
                                    detail.setXgbottombalance(detail.getXgbottombalance().add(position.getAssetvalueB() == null ? BigDecimal.ZERO : position.getAssetvalueB()));
                                } else {
                                    //合规穿透后科目余额 = 合规穿透后科目余额 + 【是否合规产品】 = 是 【新增穿透后值】 else 【0】

                                    detail.setXgbalance(detail.getXgbalance().add(BigDecimal.ZERO));
                                    detail.setXgbottombalance(detail.getXgbottombalance().add(BigDecimal.ZERO));
                                }
                                /**G06资产端改造  add by  dongxiangyang  end*/

                                // 场外(内)股票质押融资（穿透后）计行内一级分类IN_ASSET_TYPE1=01场内股票质押回购的资产价值ASSETVALUE_B+ASSETVALUE_A（除一级分类=资管，委外，私募）
//    	    					if (singleRule.equals(ChbMonthAssetRule.场内股票质押回购)
//    	    							|| singleRule.equals(ChbMonthAssetRule.场外股票质押融资)){
//    	    						if (!position.getAssettype().equals(SVReportDictCodeList.资产负债类别_委外投资协议方式)
//    	    								&&!position.getAssettype().equals(SVReportDictCodeList.资产负债类别_私募基金)
//    	    								&&!position.getAssettype().equals(SVReportDictCodeList.资产负债类别_资产管理产品)){
//    	    							detail.setBottombalance(detail.getBottombalance().add(position.getAssetvalueA() == null?BigDecimal.ZERO:position.getAssetvalueA()));
//    	    						}
//    	    					}
                            }
                        } else if (assetMonthReportDetailMap.containsKey(singleRule.getPropertyCode() + "合计")) {
                            ChbMonthReportDetail detail = assetMonthReportDetailMap.get(singleRule.getPropertyCode() + "合计");
                            // 已存在计入本次头寸值，否则不计入本次头寸值
                            if (isPattern) {
                                // 穿透前科目余额 = 穿透前科目余额 + 新增值
                                detail.setBalance(detail.getBalance().add(position.getAssetvalueA() == null ? BigDecimal.ZERO : position.getAssetvalueA()));
                                // 穿透后科目余额 = 穿透后科目余额 + 新增值
                                detail.setBottombalance(detail.getBottombalance().add(position.getAssetvalueB() == null ? BigDecimal.ZERO : position.getAssetvalueB()));
                                /**G06资产端改造  add by  dongxiangyang  start*/

                                //合规穿透前科目余额 = 合规穿透前科目余额 + （【是否合规产品】 = 是 【新增穿透前值】 else 【0】）
                                if (StringUtil.equals(DictCodeListExt1.新老产品标记_新产品.getValue(), position.getIsFhxgcp())) {
                                    detail.setXgbalance(detail.getXgbalance().add(position.getAssetvalueA() == null ? BigDecimal.ZERO : position.getAssetvalueA()));
                                    detail.setXgbottombalance(detail.getXgbottombalance().add(position.getAssetvalueB() == null ? BigDecimal.ZERO : position.getAssetvalueB()));
                                } else {
                                    //合规穿透后科目余额 = 合规穿透后科目余额 + 【是否合规产品】 = 是 【新增穿透后值】 else 【0】
                                    detail.setXgbalance(detail.getXgbalance().add(BigDecimal.ZERO));
                                    detail.setXgbottombalance(detail.getXgbottombalance().add(BigDecimal.ZERO));
                                }
                                /**G06资产端改造  add by  dongxiangyang  end*/
                            }
                        }
                    } else {

                        BigDecimal balance = BigDecimal.ZERO;

                        BigDecimal bottombalance = BigDecimal.ZERO;

                        BigDecimal xgbalance = BigDecimal.ZERO;

                        BigDecimal xgbottombalance = BigDecimal.ZERO;

//    					ChbMonthReportDetail detail = new ChbMonthReportDetail();
//    					// uuid
//    					detail.setDetailuuid(IDGenerator.generate(36));
//    					// 期数
//    					detail.setTermid(DateUtil.format(reportStartDate, "yyyyMM"));
//    					// 报表类型：BALANCE-资产负债表
//    					detail.setReporttype(ChbMonthReportPrdConstants.REPORT_TYPE_ASSET);
//    					// 属性代码
//    					detail.setPropertycode(singleRule.getPropertyCode());
                        // 计入本次头寸值
                        if (isPattern) {
                            balance = position.getAssetvalueA() == null ? BigDecimal.ZERO : position.getAssetvalueA();

                            bottombalance = position.getAssetvalueB() == null ? BigDecimal.ZERO : position.getAssetvalueB();

                            if (StringUtil.equals(DictCodeListExt1.新老产品标记_新产品.getValue(), position.getIsFhxgcp())) {
                                xgbalance = position.getAssetvalueA() == null ? BigDecimal.ZERO : position.getAssetvalueA();

                                xgbottombalance = position.getAssetvalueB() == null ? BigDecimal.ZERO : position.getAssetvalueB();
                            }
                            // 期末余额穿透前 = 新增值
//	    					detail.setBalance(position.getAssetvalueA() == null?BigDecimal.ZERO:position.getAssetvalueA());
//	    					// 期末余额穿透后 = 新增值
//	    					detail.setBottombalance(position.getAssetvalueB() == null?BigDecimal.ZERO:position.getAssetvalueB());
                            // 场外(内)股票质押融资（穿透后）计行内一级分类IN_ASSET_TYPE1=01场内股票质押回购的资产价值ASSETVALUE_B+ASSETVALUE_A（除一级分类=资管，委外，私募）
//	    					if (singleRule.equals(ChbMonthAssetRule.场内股票质押回购)
//	    							|| singleRule.equals(ChbMonthAssetRule.场外股票质押融资)){
//	    						if (!position.getAssettype().equals(SVReportDictCodeList.资产负债类别_委外投资协议方式)
//	    								&&!position.getAssettype().equals(SVReportDictCodeList.资产负债类别_私募基金)
//	    								&&!position.getAssettype().equals(SVReportDictCodeList.资产负债类别_资产管理产品)){
//	    							detail.setBottombalance(detail.getBottombalance().add(position.getAssetvalueA() == null?BigDecimal.ZERO:position.getAssetvalueA()));
//	    						}
//	    					}
                        }

                        //如果汇总项无此合计项则填入
                        assetMonthReportDetailMap.put(singleRule.getPropertyCode(),
                                buildDetail(reportStartDate, singleRule.getPropertyCode(), balance
                                        , bottombalance, xgbalance, xgbottombalance));


//    					assetMonthReportDetailMap.put(singleRule.getPropertyCode(), detail);
                    }

                    //update by leipeng 银行模式下报错修改
                    if (null != deptMap.get(position.getOrgId())) {
                        //独立机构部分数据组装
                        if (null != deptMap.get(position.getOrgId()) &&
                                (deptMap.get(position.getOrgId()).containsKey(singleRule.getPropertyCode()) ||
                                        deptMap.get(position.getOrgId()).containsKey(singleRule.getPropertyCode() + "合计"))) {
                            // 非合计值
                            if (deptMap.get(position.getOrgId()).containsKey(singleRule.getPropertyCode())) {
                                ChbMonthReportDetail detail = deptMap.get(position.getOrgId()).get(singleRule.getPropertyCode());
                                // 已存在计入本次头寸值，否则不计入本次头寸值
                                if (isPattern) {
                                    // 穿透前科目余额 = 穿透前科目余额 + 新增值
                                    detail.setBalance(detail.getBalance().add(position.getAssetvalueA() == null ? BigDecimal.ZERO : position.getAssetvalueA()));
                                    // 穿透后科目余额 = 穿透后科目余额 + 新增值
                                    detail.setBottombalance(detail.getBottombalance().add(position.getAssetvalueB() == null ? BigDecimal.ZERO : position.getAssetvalueB()));

                                    /**G06资产端改造  add by  dongxiangyang  start*/

                                    //合规穿透前科目余额 = 合规穿透前科目余额 + （【是否合规产品】 = 是 【新增穿透前值】 else 【0】）
                                    if (StringUtil.equals(DictCodeListExt1.新老产品标记_新产品.getValue(), position.getIsFhxgcp())) {
                                        detail.setXgbalance(detail.getXgbalance().add(position.getAssetvalueA() == null ? BigDecimal.ZERO : position.getAssetvalueA()));
                                        detail.setXgbottombalance(detail.getXgbottombalance().add(position.getAssetvalueB() == null ? BigDecimal.ZERO : position.getAssetvalueB()));
                                    } else {
                                        //合规穿透后科目余额 = 合规穿透后科目余额 + 【是否合规产品】 = 是 【新增穿透后值】 else 【0】
                                        detail.setXgbalance(detail.getXgbalance().add(BigDecimal.ZERO));
                                        detail.setXgbottombalance(detail.getXgbottombalance().add(BigDecimal.ZERO));
                                    }
                                    /**G06资产端改造  add by  dongxiangyang  end*/
                                }
                            } else if (deptMap.get(position.getOrgId()).containsKey(singleRule.getPropertyCode() + "合计")) {
                                ChbMonthReportDetail detail = deptMap.get(position.getOrgId()).get(singleRule.getPropertyCode() + "合计");
                                // 已存在计入本次头寸值，否则不计入本次头寸值
                                if (isPattern) {
                                    // 穿透前科目余额 = 穿透前科目余额 + 新增值
                                    detail.setBalance(detail.getBalance().add(position.getAssetvalueA() == null ? BigDecimal.ZERO : position.getAssetvalueA()));
                                    // 穿透后科目余额 = 穿透后科目余额 + 新增值
                                    detail.setBottombalance(detail.getBottombalance().add(position.getAssetvalueB() == null ? BigDecimal.ZERO : position.getAssetvalueB()));

                                    /**G06资产端改造  add by  dongxiangyang  start*/

                                    //合规穿透前科目余额 = 合规穿透前科目余额 + （【是否合规产品】 = 是 【新增穿透前值】 else 【0】）
                                    if (StringUtil.equals(DictCodeListExt1.新老产品标记_新产品.getValue(), position.getIsFhxgcp())) {
                                        detail.setXgbalance(detail.getXgbalance().add(position.getAssetvalueA() == null ? BigDecimal.ZERO : position.getAssetvalueA()));
                                        detail.setXgbottombalance(detail.getXgbottombalance().add(position.getAssetvalueB() == null ? BigDecimal.ZERO : position.getAssetvalueB()));
                                    } else {
                                        //合规穿透后科目余额 = 合规穿透后科目余额 + 【是否合规产品】 = 是 【新增穿透后值】 else 【0】
                                        detail.setXgbalance(detail.getXgbalance().add(BigDecimal.ZERO));
                                        detail.setXgbottombalance(detail.getXgbottombalance().add(BigDecimal.ZERO));
                                    }
                                    /**G06资产端改造  add by  dongxiangyang  end*/
                                }
                            }
                        } else {
                            //新增一个数据集合
                            BigDecimal balance = BigDecimal.ZERO;

                            BigDecimal bottombalance = BigDecimal.ZERO;

                            BigDecimal xgbalance = BigDecimal.ZERO;

                            BigDecimal xgbottombalance = BigDecimal.ZERO;

                            // 计入本次头寸值
                            if (isPattern) {

                                balance = position.getAssetvalueA() == null ? BigDecimal.ZERO : position.getAssetvalueA();


                                bottombalance = position.getAssetvalueB() == null ? BigDecimal.ZERO : position.getAssetvalueB();

                                if (StringUtil.equals(DictCodeListExt1.新老产品标记_新产品.getValue(), position.getIsFhxgcp())) {
                                    xgbalance = position.getAssetvalueA() == null ? BigDecimal.ZERO : position.getAssetvalueA();

                                    xgbottombalance = position.getAssetvalueB() == null ? BigDecimal.ZERO : position.getAssetvalueB();
                                }
                            }
                            deptMap.get(position.getOrgId()).put(singleRule.getPropertyCode(),
                                    buildDetail(reportStartDate, singleRule.getPropertyCode(), balance
                                            , bottombalance, xgbalance, xgbottombalance));
                        }
                    }
                }
            }
        }

        //多级法人改造。不能出汇总数据，会导致分支机构数据删除错误
        if (!CommonUtil.isBAmodel()) {
            //汇总数据合计
            sumDetail(assetMonthReportDetailMap);
        } else {
            //机构数据合计以及入库
            for (Map.Entry<String, Map<String, ChbMonthReportDetail>> details : deptMap.entrySet()) {
                sumDetail(details.getValue());
            }
        }
        return assetMonthReportDetailMap;
    }

    public ChbMonthReportDetail buildDetail(Date reportStartDate, String properCode, BigDecimal balance, BigDecimal bottombalance,
                                            BigDecimal xgbalance, BigDecimal xgbottombalance) {
        ChbMonthReportDetail detail = new ChbMonthReportDetail();
        // uuid
        detail.setDetailuuid(IDGenerator.generate(36));
        // 期数
        detail.setTermid(DateUtil.format(reportStartDate, "yyyyMM"));
        // 报表类型：BALANCE-资产负债表
        detail.setReporttype(ChbMonthReportPrdConstants.REPORT_TYPE_ASSET);
        // 属性代码
        detail.setPropertycode(properCode);
        // 期末余额穿透前 = 新增值
        detail.setBalance(balance);
        // 期末余额穿透后 = 新增值
        detail.setBottombalance(bottombalance);
        /**新增合规穿透代码字段 start  by dongxiangyang */
        //dto.合规穿透前金额 = 入参.合规穿透前金额
        detail.setXgbalance(xgbalance);
        //dto.合规穿透后金额 = 入参.合规穿透后金额
        detail.setXgbottombalance(xgbottombalance);
        /**新增合规穿透代码字段 start  by dongxiangyang */
        return detail;
    }

    /**
     * 数据合计方法
     *
     * @param assetMonthReportDetailMap
     */
    public void sumDetail(Map<String, ChbMonthReportDetail> assetMonthReportDetailMap) {
        //总合计1.17单独计算
        BigDecimal balanceA = BigDecimal.ZERO;
        BigDecimal balanceB = BigDecimal.ZERO;
        BigDecimal balanceC = BigDecimal.ZERO;
        BigDecimal balanceD = BigDecimal.ZERO;

        // 处理合计值
        for (Map.Entry<String, ChbMonthReportDetail> detail : assetMonthReportDetailMap.entrySet()) {
            if (detail.getKey().contains("合计")) {
                // 获取与合计值匹配的properTycode:AMT1_7_11_
                String properTycodeSumRule = detail.getKey().substring(0, detail.getKey().lastIndexOf("_") + 1);
                // 期末余额穿透前
                BigDecimal balance = BigDecimal.ZERO;
                // 期末余额穿透后
                BigDecimal bottombalance = BigDecimal.ZERO;

                BigDecimal xgbalance = BigDecimal.ZERO;
                BigDecimal xgbottombalance = BigDecimal.ZERO;

                // AMT2_3特殊处理，此处不会计算AMT2_1_3故另外计算
                if (detail.getKey().startsWith("AMT2_3")) {
                    if (assetMonthReportDetailMap.containsKey("AMT2_1_3合计")) {
                        ChbMonthReportDetail detailAmt213 = assetMonthReportDetailMap.get("AMT2_1_3合计");
                        balance = balance.add(detailAmt213.getBalance());
                        bottombalance = bottombalance.add(detailAmt213.getBottombalance());
                        xgbalance = xgbalance.add(detailAmt213.getXgbalance());
                        xgbottombalance = xgbottombalance.add(detailAmt213.getXgbottombalance());

                    } else if (assetMonthReportDetailMap.containsKey("AMT2_1_3")) {
                        ChbMonthReportDetail detailAmt213 = assetMonthReportDetailMap.get("AMT2_1_3");
                        balance = balance.add(detailAmt213.getBalance());
                        bottombalance = bottombalance.add(detailAmt213.getBottombalance());
                        xgbalance = xgbalance.add(detailAmt213.getXgbalance());
                        xgbottombalance = xgbottombalance.add(detailAmt213.getXgbottombalance());
                    }
                }
                for (Map.Entry<String, ChbMonthReportDetail> detail2 : assetMonthReportDetailMap.entrySet()) {
                    // 计算合计值，匹配规则如下：
                    // 1.AMT1_1_1合计 则统计以AMT1_1_开头的
                    // 2.且统计值的最后一个"_"的索引不能大于所需计算的合计值的最后一个"_"
                    // 3.不以_a或_b结尾
                    // 以合计为开头且长度小于等于合计(去除不同纬度)
                    if (detail2.getKey().startsWith(properTycodeSumRule)
                            && detail2.getKey().lastIndexOf("_") <= detail.getKey().lastIndexOf("_")
                            && !detail2.getKey().endsWith("_a")
                            && !detail2.getKey().endsWith("_b")
                            && !detail2.getKey().endsWith("_c")
                            && !detail2.getKey().equals(detail.getKey())) {
                        balance = balance.add(detail2.getValue().getBalance());
                        bottombalance = bottombalance.add(detail2.getValue().getBottombalance());
                        xgbalance = xgbalance.add(detail2.getValue().getXgbalance());
                        xgbottombalance = xgbottombalance.add(detail2.getValue().getXgbottombalance());
                    }
                }
                ChbMonthReportDetail chbSumDetail = detail.getValue();
                chbSumDetail.setBalance(balance);
                chbSumDetail.setBottombalance(bottombalance);
                chbSumDetail.setXgbalance(xgbalance);
                chbSumDetail.setXgbottombalance(xgbottombalance);

                if (detail.getKey().startsWith("AMT1_")) {
                    // 计算其他合计累计值
                    balanceA = balanceA.add(balance);
                    balanceC = balanceC.add(xgbalance);

                    // 去除隐藏列的b列值
                    if (!detail.getKey().startsWith("AMT1_12_9")
                            && !detail.getKey().startsWith("AMT1_13_7")
                            && !detail.getKey().startsWith("AMT1_14_8")) {
                        balanceB = balanceB.add(bottombalance);
                        balanceD = balanceD.add(xgbottombalance);
                    }
                }
                detail.setValue(chbSumDetail);
            }
        }
        // 计算合计值ATM1_17
        ChbMonthReportDetail atm117 = assetMonthReportDetailMap.get("AMT1_15合计");
        atm117.setBalance(balanceA);
        atm117.setBottombalance(balanceB);
        atm117.setXgbalance(balanceC);
        atm117.setXgbottombalance(balanceD);
    }


    /**
     * 根据属性，获取get方法(不区分大小写)
     *
     * @param ob   对象
     * @param name 属性名
     * @return
     */
    private Object getGetMethod(Object ob, String name) {
        Method[] m = ob.getClass().getMethods();
        for (int i = 0; i < m.length; i++) {
            if (("get" + name).equalsIgnoreCase(m[i].getName())) {
                try {
                    return m[i].invoke(ob);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    logger.error(e.getMessage(), e);
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * 功能:检查匹配规则是否完善
     *
     * @return boolean
     * @version 1.6.0
     * @author：weiyuqiang 2019年1月31日
     * @since 1.6.0
     */
    private boolean checkRuleIsRight(boolean isIncludeNull) {
        // 检查配置规则项
        for (ChbMonthAssetRule singleRule : ChbMonthAssetRule.values()) {
            // 检查属性代码是否为空
            if (StringUtil.isEmpty(singleRule.getPropertyCode())) {
                logger.error("请检查属性代码是否为空！");
                return false;
            }
            if (!isIncludeNull) {
                // 检查配置规则代码域与配置规则值域是否为空,支持为空时请关闭此项
                if (StringUtil.isEmptyList(singleRule.getRuleCodeList())
                        || StringUtil.isEmptyList(singleRule.getRuleValueList())) {
                    logger.error("请检查置规则代码域与配置规则值域是否为空！");
                    return false;
                }
            }
            // 判断配置规则代码域与配置规则值域是否大小一致，即一一对应
            if (singleRule.getRuleCodeList().size() != singleRule.getRuleValueList().size()) {
                logger.error("请检查属性代码:" + singleRule.getPropertyCode() + "对应规则值域与对应规则代码域大小是否对应一致！");
                return false;
            }
        }
        return true;
    }

    /**
     * 功能:判断评级信息大小
     *
     * @param s1 待比较评级结果
     * @param s2 评级比较标准    S2固定只能为AA+或AA-
     * @return >0 s1>s2 =0 s1 = s2 <0 s1<s2
     * @version 1.6.0
     * @author：weiyuqiang 2019年2月11日
     * @since 1.6.0
     */
    private int compareRating(String s1, String s2) {
        if (s1.equals(s2)) {
            return 0;
        }
        if (s2.equals("AA+")) {
            if (s1.length() < s2.length()) {
                return -1;
            } else {
                if (s1.substring(0, 3).equals(s2) || s1.startsWith("AAA")) {
                    return 1;
                } else {
                    return -1;
                }
            }
        } else {
            if (s1.length() < s2.length()) {
                return -1;
            } else {
                if (s1.substring(0, 3).equals(s2) || s1.startsWith("AAA") || s1.startsWith("AA+")) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
    }
}
