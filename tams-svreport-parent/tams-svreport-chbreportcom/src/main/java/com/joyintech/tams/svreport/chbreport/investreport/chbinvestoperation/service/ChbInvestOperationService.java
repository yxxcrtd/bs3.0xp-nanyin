package com.joyintech.tams.svreport.chbreport.investreport.chbinvestoperation.service;

import com.joyintech.background.component.common.pack.common.util.ArithmeticUtil;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.svreport.chbreport.investreport.chbinvestoperation.dao.ChbInvestOperationMapper;
import com.joyintech.tams.svreport.chbreport.investreport.chbinvestoperation.dto.ChbInvestPrdInfo;
import com.joyintech.tams.svreport.chbreport.investreport.chbinvestoperation.dto.ChbInvsetAssetInfo;
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
 * @ClassName ChbInvestOperationService
 * @Description: 非标投资运行报告查询service
 * @Author yanya
 * @Date 2021-04-25
 * @Version V1.0
 **/
@Service
public class ChbInvestOperationService extends CommonExportService<ChbInvestPrdInfo> {

    @Autowired
    private ChbInvestOperationMapper chbInvestOperationMapper;

    /**
     * 针对weblogic部署无法获取模版问题
     */
    private static final String TMP_DIR = "/tpl/";

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(ChbInvestOperationService.class);

//    /**
//     * 查询产品下拉框
//     * @param param
//     * @return
//     * @see
//     * @since 1.0
//     */
//    public Page<SelectOptionDto> queryPrdInfoPage(Map<String, Object> param) {
//        return restTemplate.postForPage(
//                SvrServiceApplication.CHBREPORT_SERVICE_NAME + "/ChbInvestOperation/queryPrdInfoPage", param,
//                SelectOptionDto.class);
//    }

//    /**
//     * 分页查询非标资产投资报告
//     * @param param
//     * @return
//     * @throws Exception
//     */
//    public Page<ChbInvestPrdInfo> findPage(Map<String, Object> param) throws Exception {
//        Page<ChbInvestPrdInfo> page =  restTemplate.postForPage(
//                SvrServiceApplication.CHBREPORT_SERVICE_NAME + "/ChbInvestOperation/findPage", param,
//                ChbInvestPrdInfo.class);
//        this.dealDictOrSpecialData(page.getRoot());
//        return page;
//    }

    /**
     * 查询投资运行报告中产品相关信息
     * @param params
     * @return
     * @throws Exception
     */
    public ChbInvestPrdInfo qryChbInvestPrdInfo(Map<String, Object> params){
        List<ChbInvestPrdInfo> list = chbInvestOperationMapper.qryChbInvestPrdInfo(params);
        ChbInvestPrdInfo chbInvestPrdInfo = new ChbInvestPrdInfo();
        if(!StringUtil.isEmptyList(list)) {
            chbInvestPrdInfo =  list.get(0);
        }
        this.dealDictOrSpecialObj(chbInvestPrdInfo);
        return chbInvestPrdInfo;
    }

    /**
     * 查询投资运行报告中产品相关信息
     * @param param
     * @return
     * @throws Exception
     */
    public List<ChbInvsetAssetInfo> qryChbInvsetAssetInfo(Map<String, Object> param) {

        if (StringUtil.isNotEmpty(param.get("endDate"))) {
            param.put("dealdate", DateUtil.parse((String)param.get("endDate")));
        }
        else {
            param.put("dealdate", null);
        }
        List<ChbInvsetAssetInfo> list =  chbInvestOperationMapper.qryChbInvsetAssetInfo(param);
        this.dealDictOrSpecialAssetData(list, (String) param.get("endDate"));
        return list;
    }

    /**
     * 处理返回的数据中需要处理数据字典及特殊字段的
     * @param list
     */
    private void dealDictOrSpecialData(List<ChbInvestPrdInfo> list) {
        if(!StringUtil.isEmptyList(list)) {
            list.stream().forEach(item -> {
                this.dealDictOrSpecialObj(item);
            });
        }
    }

    /**
     * 处理返回的数据中需要处理数据字典及特殊字段的
     * @param list
     */
    private void dealDictOrSpecialAssetData(List<ChbInvsetAssetInfo> list, String dealdateFmt) {
        if(!StringUtil.isEmptyList(list)) {
            int index = 1;
            for(ChbInvsetAssetInfo item : list){
                item.setFbShowName(ChbInvestReportConstant.非标准化债权类资产明细 + ChbInvestReportConstant.空格 + index);
                index++;
                //兑付本金币种显示名称
                String showName = ChbInvestReportUtil.getDictCodeAndName(DictCode.资产_负债二级类别, item.getAssetType2());
                if (StringUtil.isNotEmpty(showName)){
                    item.setAssetType2Fmt(showName);
                }
                else {
                    item.setAssetType2Fmt(ChbInvestReportConstant.空字符);
                }
                //项目收益率(利率)%
                if(item.getAssetYield() == null || BigDecimal.ZERO.compareTo(item.getAssetYield()) == 0) {
                    item.setAssetYieldFmt(ChbInvestReportConstant.无);
                }
                else {
                    item.setAssetYieldFmt(String.valueOf(item.getAssetYield()));
                }
                //剩余融资期限(年)
                if(item.getMdate() != null) {
                    if(DateUtil.compareDate(DateUtil.format(item.getMdate(), DateUtil.DEFAULT_DATE_FORMAT_EN), dealdateFmt) <0) {
                        item.setSurplusTerm(ChbInvestReportConstant.无);
                    }
                    else {
                        item.setSurplusTerm(String.valueOf(ArithmeticUtil.divide(
                                ChbInvestReportUtil.getBetweenDayNumber(DateUtil.parse(dealdateFmt),
                                        DateUtil.parse(DateUtil.format(item.getMdate(), DateUtil.DEFAULT_DATE_FORMAT_EN))),
                                new BigDecimal(365.0000),2)));
                    }
                }
                else {
                    item.setSurplusTerm(ChbInvestReportConstant.无);
                }

            }
        }
    }

    /**
     * 处理返回的数据中需要处理数据字典及特殊字段的
     * @param chbInvestPrdInfo
     */
    private void dealDictOrSpecialObj(ChbInvestPrdInfo chbInvestPrdInfo) {
        //理财信息系统登记编码
        if (StringUtil.isEmpty(chbInvestPrdInfo.getRegCode())){
            chbInvestPrdInfo.setRegCode(ChbInvestReportConstant.空字符);
        }
        //兑付本金币种显示名称
        String showName = ChbInvestReportUtil.getDictCodeAndName(DictCode.币种_中债, chbInvestPrdInfo.getCashPrinCcy());
        if (StringUtil.isNotEmpty(showName)){
            chbInvestPrdInfo.setCashPrinCcyFmt(showName);
        }
        else {
            chbInvestPrdInfo.setCashPrinCcyFmt(ChbInvestReportConstant.空字符);
        }
        //投资性质显示名称
        showName = ChbInvestReportUtil.getDictCodeAndName(DictCode.投资性质, chbInvestPrdInfo.getCashPrinCcy());
        if (StringUtil.isNotEmpty(showName)){
            chbInvestPrdInfo.setIvstorientationFmt(showName);
        }
        else {
            chbInvestPrdInfo.setIvstorientationFmt(ChbInvestReportConstant.空字符);
        }
        //产品风险等级显示名称
        showName = ChbInvestReportUtil.getDictCodeAndName(DictCode.产品风险等级报告显示, chbInvestPrdInfo.getRiskLevel());
        if (StringUtil.isNotEmpty(showName)){
            chbInvestPrdInfo.setRiskLevelFmt(showName);
        }
        else {
            chbInvestPrdInfo.setRiskLevelFmt(ChbInvestReportConstant.空字符);
        }
        //起息日显示名称
        chbInvestPrdInfo.setVdateFmt(DateUtil.format(chbInvestPrdInfo.getVdate(), DateUtil.DEFAULT_DATE_FORMAT_EN));
        //境内外显示名称
        List<String> nameList = new ArrayList<>();
        if (StringUtil.isEmpty(chbInvestPrdInfo.getDepName())){
            chbInvestPrdInfo.setDepName(ChbInvestReportConstant.空字符);
        }
        //交易日期显示名称
        chbInvestPrdInfo.setDealdateFmt(DateUtil.format(chbInvestPrdInfo.getDealdate(), DateUtil.DEFAULT_DATE_FORMAT_EN));
    }

    /**
     * 组装数据
     * @param reportName
     * @param param
     * @param mapList
     * @param titleList
     */
    public void createData(String reportName, Map<String, Object> param ,List<Map<String, Object>> mapList, List<String> titleList) {
        if(StringUtil.isNotEmpty(param.get("endDate")) && StringUtil.isNotEmpty(param.get("prodId"))) {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            ChbInvestPrdInfo chbInvestPrdInfo = this.qryChbInvestPrdInfo(param);
            param.put("curAssetType", DictCodeListExt2.资金流动类型_资产买入.getValue());//买入
            List<ChbInvsetAssetInfo> mr =  this.qryChbInvsetAssetInfo(param);
            param.put("curAssetType", DictCodeListExt2.资金流动类型_资产卖出或到期.getValue());//买入
            List<ChbInvsetAssetInfo> mc =  this.qryChbInvsetAssetInfo(param);
            dataMap.put("dealdateFmt", DateUtil.format(DateUtil.parse(param.get("endDate").toString()),DateUtil.DEFAULT_DATE_FORMAT_CN));//变动日
            dataMap.put("prdInfo", chbInvestPrdInfo);//产品信息
            dataMap.put("assetBuyList", mr);//非标买入
            if(StringUtil.isEmptyList(mr)) {
                dataMap.put("list1", Arrays.asList("新增空"));//
            }
            else {
                dataMap.put("list1", new ArrayList<>());//
            }
            dataMap.put("assetSelllist", mc);//非标到期
            if(StringUtil.isEmptyList(mc)) {
                dataMap.put("list2", Arrays.asList("到期空"));//
            }
            else {
                dataMap.put("list2", new ArrayList<>());//
            }
            mapList.add(dataMap);
            titleList.add(ChbInvestReportUtil.generateFileNameForReport(DictCodeListExt3.中债披露报告_理财产品非标投资运作报告.getValue(),
                    chbInvestPrdInfo.getProdName(), chbInvestPrdInfo.getProdId(), chbInvestPrdInfo.getCustomerType(),
                    param.get("endDate").toString()));
        }
    }

    /**
     * 导出公告
     * @param map
     * @param reportName
     * @return
     * @throws Exception
     */
    public ResponseEntity<byte[]> exportDoc(Map<String, Object> map, String reportName) throws Exception {
        String srcFilePath = ChbInvestReportUtil.getFtlPath();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        List<String> titleList = new ArrayList<String>();
        this.createData(reportName, map, mapList, titleList);
        if(mapList.size() == 1) {
            return WordBatchUtil.exportMillCertificateWord(mapList.get(0), srcFilePath,ChbInvestReportConstant.理财产品非标投资运作报告模板文件, titleList.get(0));
        }
        else {
            String zipname = reportName + ChbInvestReportConstant.下划线 + DateUtil.format(new Date(), ChbInvestReportConstant.日期格式化yyyyMMddHHmmss);
            return WordBatchUtil.exportWordBatch(mapList, titleList, ChbInvestReportConstant.理财产品非标投资运作报告模板文件, srcFilePath, zipname);
        }
    }

    /**
     * 创建非标投资运作报告
     * @param map
     * @param reportName
     * @return
     * @throws Exception
     */
    public File createDoc(Map<String, Object> map, String reportName) throws Exception {
        String srcFilePath = ChbInvestReportUtil.getFtlPath();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        List<String> titleList = new ArrayList<String>();
        this.createData(reportName, map, mapList, titleList);
        return WordBatchUtil.createMillCertificateWord(mapList.get(0), srcFilePath,ChbInvestReportConstant.理财产品非标投资运作报告模板文件, titleList.get(0));
    }

}
