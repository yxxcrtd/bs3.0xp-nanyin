package com.joyintech.tams.svreport.chbreport.investreport.chbperiodic.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.ChbPrdBaseinfoMapper;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.svreport.chbreport.investreport.chbperiodic.dao.ChbPeriodicMapper;
import com.joyintech.tams.svreport.chbreport.investreport.chbperiodic.dto.*;
import com.joyintech.tams.svreport.chbreport.investreport.common.service.ChbInvestReportCommonService;
import com.joyintech.tams.svreport.chbreport.investreport.utils.ChbInvestReportConstant;
import com.joyintech.tams.svreport.chbreport.investreport.utils.ChbInvestReportUtil;
import com.joyintech.tams.svreport.chbreport.investreport.utils.WordBatchUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

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
    private ChbPrdBaseinfoMapper chbPrdBaseinfoMapper;

    /**
     * 导出公告
     *
     * @param map
     * @param reportName
     * @return
     * @throws Exception
     */
    public ResponseEntity<byte[]> exportDoc(Map<String, Object> map, String reportName) throws Exception {
        String srcFilePath = FileNameUtil.getTplFile("chbreport");
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        List<String> titleList = new ArrayList<String>();
        String prodId = (String) map.get("prodId");
        ChbPrdBaseinfo chbPrdBaseinfo = chbPrdBaseinfoMapper.selectByProdid(prodId);
        this.createData(reportName, chbPrdBaseinfo, map, mapList, titleList);

        String prodName = null;
        String fProdId = null;
        String prodType = null;
        if (chbPrdBaseinfo != null) {
            prodName = chbPrdBaseinfo.getProdName();
            fProdId = chbPrdBaseinfo.getFprodId();
            prodType = chbPrdBaseinfo.getProdtype();
        }
        if (mapList.size() == 1) {
            // 单份额类净值型产品
            if (StringUtil.isEmpty(fProdId) && StringUtil.equals(DictCodeListExt2.产品类型_中债_净值型产品.getValue(), prodType)) {
                return WordBatchUtil.exportMillCertificateWord(mapList.get(0), srcFilePath, ChbInvestReportConstant.产品定期公告_单份额类净值型产品模板文件, titleList.get(0));
            }
            // 单份额类货币型产品
            else if (StringUtil.isEmpty(fProdId) && StringUtil.equals(DictCodeListExt2.产品类型_中债_货币型产品.getValue(), prodType)) {
                return WordBatchUtil.exportMillCertificateWord(mapList.get(0), srcFilePath, ChbInvestReportConstant.产品定期公告_单份额类货币型产品模板文件, titleList.get(0));
            }
            // 多份额类净值型产品
            else if (StringUtil.isNotEmpty(fProdId) && StringUtil.equals(DictCodeListExt2.产品类型_中债_净值型产品.getValue(), prodType)) {
                return WordBatchUtil.exportMillCertificateWord(mapList.get(0), srcFilePath, ChbInvestReportConstant.产品定期公告_多份额类净值型产品模板文件, titleList.get(0));
            }
            // 多份额类货币型产品
            else if (StringUtil.isNotEmpty(fProdId) && StringUtil.equals(DictCodeListExt2.产品类型_中债_货币型产品.getValue(), prodType)) {
                return WordBatchUtil.exportMillCertificateWord(mapList.get(0), srcFilePath, ChbInvestReportConstant.产品定期公告_多份额类货币型产品模板文件, titleList.get(0));
            }
        } else {
            String zipname = reportName + ChbInvestReportConstant.下划线 + DateUtil.format(new Date(), ChbInvestReportConstant.日期格式化yyyyMMddHHmmss);
            // 单份额类净值型产品
            if (StringUtil.isEmpty(fProdId) && StringUtil.equals(DictCodeListExt2.产品类型_中债_净值型产品.getValue(), prodType)) {
                return WordBatchUtil.exportWordBatch(mapList, titleList, ChbInvestReportConstant.产品定期公告_单份额类净值型产品模板文件, srcFilePath, zipname);
            }
            // 单份额类货币型产品
            else if (StringUtil.isEmpty(fProdId) && StringUtil.equals(DictCodeListExt2.产品类型_中债_货币型产品.getValue(), prodType)) {
                return WordBatchUtil.exportWordBatch(mapList, titleList, ChbInvestReportConstant.产品定期公告_单份额类净值型产品模板文件, srcFilePath, zipname);
            }
            // 多份额类净值型产品
            else if (StringUtil.isNotEmpty(fProdId) && StringUtil.equals(DictCodeListExt2.产品类型_中债_净值型产品.getValue(), prodType)) {
                return WordBatchUtil.exportWordBatch(mapList, titleList, ChbInvestReportConstant.产品定期公告_多份额类净值型产品模板文件, srcFilePath, zipname);
            }
            // 多份额类货币型产品
            else if (StringUtil.isNotEmpty(fProdId) && StringUtil.equals(DictCodeListExt2.产品类型_中债_货币型产品.getValue(), prodType)) {
                return WordBatchUtil.exportWordBatch(mapList, titleList, ChbInvestReportConstant.产品定期公告_多份额类货币型产品模板文件, srcFilePath, zipname);
            }
        }
        return null;
    }

    /**
     * 创建定期报告
     *
     * @param map
     * @param reportName
     * @return
     * @throws Exception
     */
    public File createDoc(Map<String, Object> map, String reportName, ChbPrdBaseinfo chbPrdBaseinfo) throws Exception {
        String srcFilePath = FileNameUtil.getTplFile("chbreport");
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        List<String> titleList = new ArrayList<String>();
        String prodName = null;
        String fProdId = null;
        String prodType = null;
        if (chbPrdBaseinfo != null) {
            prodName = chbPrdBaseinfo.getProdName();
            fProdId = chbPrdBaseinfo.getFprodId();
            prodType = chbPrdBaseinfo.getProdtype();
        }
        this.createData(reportName, chbPrdBaseinfo, map, mapList, titleList);
        // 单份额类净值型产品
        if (StringUtil.isEmpty(fProdId) && StringUtil.equals(DictCodeListExt2.产品类型_中债_净值型产品.getValue(), prodType)) {
            return WordBatchUtil.createMillCertificateWord(mapList.get(0), srcFilePath, ChbInvestReportConstant.产品定期公告_单份额类净值型产品模板文件, titleList.get(0));
        }
        // 单份额类货币型产品
        else if (StringUtil.isEmpty(fProdId) && StringUtil.equals(DictCodeListExt2.产品类型_中债_货币型产品.getValue(), prodType)) {
            return WordBatchUtil.createMillCertificateWord(mapList.get(0), srcFilePath, ChbInvestReportConstant.产品定期公告_单份额类货币型产品模板文件, titleList.get(0));
        }
        // 多份额类净值型产品
        else if (StringUtil.isNotEmpty(fProdId) && StringUtil.equals(DictCodeListExt2.产品类型_中债_净值型产品.getValue(), prodType)) {
            return WordBatchUtil.createMillCertificateWord(mapList.get(0), srcFilePath, ChbInvestReportConstant.产品定期公告_多份额类净值型产品模板文件, titleList.get(0));
        }
        // 多份额类货币型产品
        else if (StringUtil.isNotEmpty(fProdId) && StringUtil.equals(DictCodeListExt2.产品类型_中债_货币型产品.getValue(), prodType)) {
            return WordBatchUtil.createMillCertificateWord(mapList.get(0), srcFilePath, ChbInvestReportConstant.产品定期公告_多份额类货币型产品模板文件, titleList.get(0));
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
    public void createData(String reportName, ChbPrdBaseinfo chbPrdBaseinfo, Map<String, Object> param, List<Map<String, Object>> mapList, List<String> titleList) {
        if (StringUtil.isNotEmpty(param.get("endDate")) && StringUtil.isNotEmpty(param.get("prodId"))) {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            ChbPeriodicPrdInfo chbPeriodicPrdInfo = this.qryChbPeriodicPrdInfo(param);

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

            Map<String, ChbPeriodicValue> chbPeriodicValueMap = this.qryChbPeriodicValue(param);
            List<ChbPeriodicPosition> chbPeriodicPositionList = this.qryChbPeriodicPosition(param);
            List<ChbPeriodicPositionTop10> chbPeriodicPositionTop10List = this.qryChbPeriodicPositionTop10(param);
            List<ChbPeriodicAssetInfo> chbPeriodicAssetInfoList = this.qryChbPeriodicAssetInfo(param);
            List<ChbPeriodicBonus> chbPeriodicBonusList = this.qryChbPeriodicBonus(chbPrdBaseinfo, param);

            dataMap.put("prdInfo", chbPeriodicPrdInfo);//产品信息
            dataMap.put("qcValue", chbPeriodicValueMap.get(ChbInvestReportConstant.期初数据));
            dataMap.put("qmValue", chbPeriodicValueMap.get(ChbInvestReportConstant.期末数据));
            dataMap.put("positionList", chbPeriodicPositionList);
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
            prodNameList.add(chbPeriodicPrdInfo.getProdName());
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
            chbPeriodicPosition.setPercentAssetvalueBFmt(ChbInvestReportConstant.横线);
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
                                ChbInvestReportUtil.getBetweenDayNumber(DateUtil.parse(DateUtil.format(item.getMdate(), DateUtil.DEFAULT_DATE_FORMAT_EN)),
                                        DateUtil.parse(dealdateFmt))));
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
        return this.dealBonusValue(chbPrdBaseinfo, list);
    }

    private List<ChbPeriodicBonus> dealBonusValue(ChbPrdBaseinfo chbPrdBaseinfo, List<ChbPeriodicBonusDeal> list) {
        List<ChbPeriodicBonus> bonusList = new ArrayList<>();
        if (Objects.isNull(list) || list.isEmpty()) {
            ChbPeriodicBonus chbPeriodicBonus = new ChbPeriodicBonus();
            chbPeriodicBonus.setRegDate(ChbInvestReportConstant.横线);
            chbPeriodicBonus.setTradeBonus(ChbInvestReportConstant.横线);
            chbPeriodicBonus.setIntBonus(ChbInvestReportConstant.横线);
            bonusList.add(chbPeriodicBonus);
            return bonusList;
        }
        ChbPeriodicBonus chbPeriodicBonus = new ChbPeriodicBonus();
        if (StringUtil.equals(DictCodeListExt2.产品类型_中债_净值型产品.getValue(), chbPrdBaseinfo.getProdtype())) {
            for (ChbPeriodicBonusDeal deal : list) {
                if (Objects.isNull(deal.getRegDate())) {
                    chbPeriodicBonus.setRegDate(ChbInvestReportConstant.横线);
                } else {
                    chbPeriodicBonus.setRegDate(DateUtil.format(deal.getRegDate()));
                }
                if (Objects.isNull(deal.getTradeAmt()) || BigDecimal.ZERO.compareTo(deal.getTradeAmt()) == 0) {
                    chbPeriodicBonus.setTradeBonus(ChbInvestReportConstant.横线);
                } else {
                    chbPeriodicBonus.setTradeBonus(ChbInvestReportConstant.df1.format(deal.getTradeAmt()));
                }
                if (Objects.isNull(deal.getIntAmt()) || BigDecimal.ZERO.compareTo(deal.getIntAmt()) == 0) {
                    chbPeriodicBonus.setIntBonus(ChbInvestReportConstant.横线);
                } else {
                    chbPeriodicBonus.setIntBonus(ChbInvestReportConstant.df1.format(deal.getIntAmt()));
                }
                bonusList.add(chbPeriodicBonus);
            }
        } else if (StringUtil.equals(DictCodeListExt2.产品类型_中债_货币型产品.getValue(), chbPrdBaseinfo.getProdtype())) {
            BigDecimal totalTradeAmt = new BigDecimal("0");
            BigDecimal totalIntAmt = new BigDecimal("0");
            for (ChbPeriodicBonusDeal deal : list) {
                totalTradeAmt.add(deal.getTradeAmt());
                totalIntAmt.add(deal.getIntAmt());
            }
            if (Objects.isNull(list.get(0).getRegDate())) {
                chbPeriodicBonus.setRegDate(ChbInvestReportConstant.横线);
            } else {
                chbPeriodicBonus.setRegDate(DateUtil.format(list.get(0).getRegDate()));
            }
            if (Objects.isNull(list.get(0).getRegTradeQty()) || BigDecimal.ZERO.compareTo(list.get(0).getRegTradeQty()) == 0
                    || BigDecimal.ZERO.compareTo(totalTradeAmt) == 0) {
                chbPeriodicBonus.setTradeBonus(ChbInvestReportConstant.横线);
            } else {
                chbPeriodicBonus.setTradeBonus(ChbInvestReportConstant.df1.format(totalTradeAmt.divide(list.get(0).getRegTradeQty(), 20, BigDecimal.ROUND_FLOOR).multiply(new BigDecimal(1000))));
            }
            if (Objects.isNull(list.get(0).getRegTradeQty()) || BigDecimal.ZERO.compareTo(list.get(0).getRegTradeQty()) == 0
                    || BigDecimal.ZERO.compareTo(totalIntAmt) == 0) {
                chbPeriodicBonus.setIntBonus(ChbInvestReportConstant.横线);
            } else {
                chbPeriodicBonus.setIntBonus(ChbInvestReportConstant.df1.format(totalIntAmt.divide(list.get(0).getRegTradeQty(), 20, BigDecimal.ROUND_FLOOR).multiply(new BigDecimal(1000))));
            }
            bonusList.add(chbPeriodicBonus);
        }
        return bonusList;
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
    private Map<String, ChbPeriodicValue> qryChbPeriodicValue(Map<String, Object> params) {
        Map<String, ChbPeriodicValue> map = new HashMap<>(4);
        ChbPeriodicIndex qcChbPeriodicIndex = qryQcPeriodicIndex(params);
        ChbPeriodicIndex qmChbPeriodicIndex = qryQmPeriodicIndex(params);
        this.dealValue(qcChbPeriodicIndex, qmChbPeriodicIndex, params, map);
        return map;
    }

    /**
     * 查询期初产品指标表
     *
     * @param params
     * @return
     */
    private ChbPeriodicIndex qryQcPeriodicIndex(Map<String, Object> params) {
        Map<String, Object> map = new HashMap();
        map.put("prodId", params.get("prodId"));
        // 若产品起息日>=本次报告开始日，则取产品起息日
        if (DateUtil.compareDate((Date) params.get("vdate"), (Date) params.get("dqStartDate")) == 1
                || DateUtil.compareDate((Date) params.get("vdate"), (Date) params.get("dqStartDate")) == 0) {
            map.put("qryDate", params.get("vdate"));
        } else {
            map.put("qryDate", DateUtil.addDate((Date) params.get("dqStartDate"), 0, 0, -1));
        }
        return qryPeriodicIndexHandler(map);
    }

    /**
     * 查询期末产品指标表
     *
     * @param params
     * @return
     */
    private ChbPeriodicIndex qryQmPeriodicIndex(Map<String, Object> params) {
        Map<String, Object> map = new HashMap();
        map.put("prodId", params.get("prodId"));
        map.put("qryDate", params.get("endReportDate"));
        return qryPeriodicIndexHandler(map);
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
        showName = ChbInvestReportUtil.getDictCodeAndName(DictCode.投资性质, chbPeriodicPrdInfo.getCashPrinCcy());
        if (StringUtil.isNotEmpty(showName)) {
            chbPeriodicPrdInfo.setIvstorientationFmt(showName);
        } else {
            chbPeriodicPrdInfo.setIvstorientationFmt(ChbInvestReportConstant.空字符);
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
                    && (StringUtil.equals(chbPeriodicPrdInfo.getOperateMode(), DictCodeListExt2.募集方式_中债_公募.getValue())
                    || StringUtil.equals(chbPeriodicPrdInfo.getOperateMode(), DictCodeListExt2.募集方式_中债_私募.getValue()))) {
                chbPeriodicPrdInfo.setLeverUpperLimitFmt("200%");
            } else if ((StringUtil.equals(chbPeriodicPrdInfo.getOperateMode(), DictCodeListExt2.产品运作模式_开放式净值型.getValue())
                    || StringUtil.equals(chbPeriodicPrdInfo.getOperateMode(), DictCodeListExt2.产品运作模式_开放式非净值型.getValue()))
                    && StringUtil.equals(chbPeriodicPrdInfo.getOperateMode(), DictCodeListExt2.募集方式_中债_公募.getValue())) {
                chbPeriodicPrdInfo.setLeverUpperLimitFmt("140%");
            }
        } else {
            chbPeriodicPrdInfo.setLeverUpperLimitFmt(ChbInvestReportConstant.空字符);
        }
        //起息日显示名称
        chbPeriodicPrdInfo.setVdateFmt(DateUtil.format(chbPeriodicPrdInfo.getVdate(), DateUtil.DEFAULT_DATE_FORMAT_EN));
        //到期日显示名称
        chbPeriodicPrdInfo.setMdateFmt(DateUtil.format(chbPeriodicPrdInfo.getMdate(), DateUtil.DEFAULT_DATE_FORMAT_EN));

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
     * @param map
     * @return
     */
    private void dealValue(ChbPeriodicIndex qcDto, ChbPeriodicIndex qmDto, Map<String, Object> params, Map<String, ChbPeriodicValue> map) {
        ChbPeriodicValue qcValue = new ChbPeriodicValue();
        // 期初估值日期
        if (Objects.isNull(qcDto.getValueDate())) {
            qcValue.setValueDate(ChbInvestReportConstant.横线);
        } else {
            qcValue.setValueDate(DateUtil.format(qcDto.getValueDate()));
        }
        // 若产品起息日>本次报告开始日
        if (DateUtil.compareDate((Date) params.get("vdate"), (Date) params.get("dqStartDate")) == 1) {
            // 期初份额净值
            if (StringUtil.equals(DictCodeListExt2.产品类型_中债_净值型产品.getValue(), params.get(DictCode.产品类型_中债.getValue()))) {
                qcValue.setUnitValue(ChbInvestReportConstant.df1.format(1));
            } else if (StringUtil.equals(DictCodeListExt2.产品类型_中债_货币型产品.getValue(), params.get(DictCode.产品类型_中债.getValue()))) {
                qcValue.setUnitValue(ChbInvestReportConstant.横线);
            }
            // 期初份额总数
            qcValue.setTotalValue(ChbInvestReportConstant.横线);
            // 期初累计净值
            qcValue.setAssetUnitValue(ChbInvestReportConstant.横线);
            // 期初资产净值
            qcValue.setAssetTotalValue(ChbInvestReportConstant.横线);
        } else {
            if (StringUtil.equals(DictCodeListExt2.产品类型_中债_净值型产品.getValue(), params.get(DictCode.产品类型_中债.getValue()))) {
                // 期初份额净值
                if (Objects.isNull(qcDto.getUnitValue()) || BigDecimal.ZERO.compareTo(qcDto.getUnitValue()) == 0) {
                    qcValue.setUnitValue(ChbInvestReportConstant.横线);
                } else {
                    qcValue.setUnitValue(ChbInvestReportConstant.df1.format(qcDto.getUnitValue()));
                }
                // 期初累计净值
                if (Objects.isNull(qcDto.getUnitValueT()) || BigDecimal.ZERO.compareTo(qcDto.getUnitValueT()) == 0) {
                    qcValue.setAssetUnitValue(ChbInvestReportConstant.横线);
                } else {
                    qcValue.setAssetUnitValue(ChbInvestReportConstant.df1.format(qcDto.getUnitValueT()));
                }
            } else if (StringUtil.equals(DictCodeListExt2.产品类型_中债_货币型产品.getValue(), params.get(DictCode.产品类型_中债.getValue()))) {
                // 期初份额净值
                if (Objects.isNull(qcDto.getwYield()) || BigDecimal.ZERO.compareTo(qcDto.getwYield()) == 0
                        || DateUtil.compareDate((Date) params.get("vdate"), (Date) params.get("dqStartDate")) == 1) {
                    qcValue.setUnitValue(ChbInvestReportConstant.横线);
                } else {
                    qcValue.setUnitValue(ChbInvestReportConstant.df1.format(qcDto.getwYield()));
                }
                // 期初累计净值
                if (Objects.isNull(qcDto.getYield7()) || BigDecimal.ZERO.compareTo(qcDto.getYield7()) == 0
                        || DateUtil.compareDate((Date) params.get("vdate"), (Date) params.get("dqStartDate")) == 1) {
                    qcValue.setAssetUnitValue(ChbInvestReportConstant.横线);
                } else {
                    qcValue.setAssetUnitValue(ChbInvestReportConstant.df1.format(qcDto.getYield7()));
                }

            }
            // 期初份额总数
            if (Objects.isNull(qcDto.getCapital()) || BigDecimal.ZERO.compareTo(qcDto.getCapital()) == 0) {
                qcValue.setTotalValue(ChbInvestReportConstant.横线);
            } else {
                qcValue.setTotalValue(ChbInvestReportConstant.df2.format(qcDto.getCapital()));
            }
            // 期初资产净值
            if (Objects.isNull(qcDto.getAssetValueMarket()) || BigDecimal.ZERO.compareTo(qcDto.getAssetValueMarket()) == 0
                    || DateUtil.compareDate((Date) params.get("vdate"), (Date) params.get("dqStartDate")) == 1) {
                qcValue.setAssetTotalValue(ChbInvestReportConstant.横线);
            } else {
                qcValue.setAssetTotalValue(ChbInvestReportConstant.df2.format(qcDto.getAssetValueMarket()));
            }
        }

        ChbPeriodicValue qmValue = new ChbPeriodicValue();
        // 期末估值日期
        if (Objects.isNull(qmDto.getValueDate())) {
            qmValue.setValueDate(ChbInvestReportConstant.横线);
        } else {
            qmValue.setValueDate(DateUtil.format(qmDto.getValueDate()));
        }
        if (StringUtil.equals(DictCodeListExt2.产品类型_中债_净值型产品.getValue(), params.get(DictCode.产品类型_中债.getValue()))) {
            // 期末份额净值
            if (Objects.isNull(qmDto.getUnitValue()) || BigDecimal.ZERO.compareTo(qmDto.getUnitValue()) == 0) {
                qmValue.setUnitValue(ChbInvestReportConstant.横线);
            } else {
                qmValue.setUnitValue(ChbInvestReportConstant.df1.format(qmDto.getUnitValue()));
            }
            // 期末累计净值
            if (Objects.isNull(qmDto.getUnitValueT()) || BigDecimal.ZERO.compareTo(qmDto.getUnitValueT()) == 0) {
                qmValue.setAssetUnitValue(ChbInvestReportConstant.横线);
            } else {
                qmValue.setAssetUnitValue(ChbInvestReportConstant.df1.format(qmDto.getUnitValueT()));
            }
            // 期间累计净值增长率
            if (!Objects.isNull(qmDto.getAssetValueMarket()) && !Objects.isNull(qcDto.getAssetValueMarket())
                    && BigDecimal.ZERO.compareTo(qcDto.getAssetValueMarket()) == 0) {
                BigDecimal increaseRateOfCumulativeValue = ((new BigDecimal(qmValue.getAssetTotalValue()))
                        .subtract(new BigDecimal(qcValue.getAssetTotalValue())))
                        .divide(new BigDecimal(qcValue.getAssetTotalValue()), 20, BigDecimal.ROUND_FLOOR)
                        .divide(new BigDecimal(DateUtil.getBetweenDayNumber(qcValue.getValueDate(), qmValue.getValueDate())), 20, BigDecimal.ROUND_FLOOR)
                        .multiply(new BigDecimal(36500));
                qmValue.setIncreaseRateOfCumulativeValue(ChbInvestReportConstant.df3.format(increaseRateOfCumulativeValue));
            } else {
                qmValue.setIncreaseRateOfCumulativeValue(ChbInvestReportConstant.横线);
            }
        } else if (StringUtil.equals(DictCodeListExt2.产品类型_中债_货币型产品.getValue(), params.get(DictCode.产品类型_中债.getValue()))) {
            // 期末份额净值
            if (Objects.isNull(qmDto.getUnitValue()) || BigDecimal.ZERO.compareTo(qmDto.getUnitValue()) == 0) {
                qmValue.setUnitValue(ChbInvestReportConstant.横线);
            } else {
                qmValue.setUnitValue(ChbInvestReportConstant.df1.format(qmDto.getUnitValue()));
            }
            // 期末累计净值
            if (Objects.isNull(qmDto.getUnitValueT()) || BigDecimal.ZERO.compareTo(qmDto.getUnitValueT()) == 0) {
                qmValue.setAssetUnitValue(ChbInvestReportConstant.横线);
            } else {
                qmValue.setAssetUnitValue(ChbInvestReportConstant.df1.format(qmDto.getUnitValueT()));
            }
            // 本期净值收益率
            BigDecimal chbPeriodicIndexValuePercent = chbPeriodicMapper.qryChbPeriodicIndexValuePercent(params);
            if (BigDecimal.ZERO.compareTo(chbPeriodicIndexValuePercent) == 0) {
                qmValue.setAssetUnitValue(ChbInvestReportConstant.横线);
            } else {
                qmValue.setAssetUnitValue(ChbInvestReportConstant.df3.format(chbPeriodicIndexValuePercent));
            }
        }
        // 期末份额总数
        if (Objects.isNull(qmDto.getCapital()) || BigDecimal.ZERO.compareTo(qmDto.getCapital()) == 0) {
            qmValue.setTotalValue(ChbInvestReportConstant.横线);
        } else {
            qmValue.setTotalValue(ChbInvestReportConstant.df2.format(qmDto.getCapital()));
        }
        // 期末资产净值
        if (Objects.isNull(qmDto.getAssetValueMarket()) || BigDecimal.ZERO.compareTo(qmDto.getAssetValueMarket()) == 0) {
            qmValue.setAssetTotalValue(ChbInvestReportConstant.横线);
        } else {
            qmValue.setAssetTotalValue(ChbInvestReportConstant.df2.format(qmDto.getAssetValueMarket()));
        }


        map.put(ChbInvestReportConstant.期末数据, qmValue);
        map.put(ChbInvestReportConstant.期初数据, qcValue);
    }
}
