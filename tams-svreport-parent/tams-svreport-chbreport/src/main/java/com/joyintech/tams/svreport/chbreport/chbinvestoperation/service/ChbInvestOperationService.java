package com.joyintech.tams.svreport.chbreport.chbinvestoperation.service;

import com.joyintech.background.component.common.pack.common.util.ArithmeticUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.svreport.chbreport.chbinvestoperation.dto.ChbInvestPrdInfo;
import com.joyintech.tams.svreport.chbreport.chbinvestoperation.dto.ChbInvsetAssetInfo;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ChbInvestOperationService
 * @Description: 非标投资运行报告查询service
 * @Author yanya
 * @Date 2021-04-25
 * @Version V1.0
 **/
@Service
public class ChbInvestOperationService extends CommonExportService<ChbInvestPrdInfo> {

    /**
     * 针对weblogic部署无法获取模版问题
     */
    private static final String TMP_DIR = "/tpl/";

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(ChbInvestOperationService.class);

    /**
     * 查询产品下拉框
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryPrdInfoPage(Map<String, Object> param) {
        return restTemplate.postForPage(
                SvrServiceApplication.CHBREPORT_SERVICE_NAME + "/ChbInvestOperation/queryPrdInfoPage", param,
                SelectOptionDto.class);
    }

    /**
     * 分页查询非标资产投资报告
     * @param param
     * @return
     * @throws Exception
     */
    public Page<ChbInvestPrdInfo> findPage(Map<String, Object> param) throws Exception {
        Page<ChbInvestPrdInfo> page =  restTemplate.postForPage(
                SvrServiceApplication.CHBREPORT_SERVICE_NAME + "/ChbInvestOperation/findPage", param,
                ChbInvestPrdInfo.class);
        this.dealDictOrSpecialData(page.getRoot());
        return page;
    }

    /**
     * 查询投资运行报告中产品相关信息
     * @param param
     * @return
     * @throws Exception
     */
    public ChbInvestPrdInfo qryChbInvestPrdInfo(Map<String, Object> param){
        ChbInvestPrdInfo chbInvestPrdInfo =  restTemplate.postForObject(
                SvrServiceApplication.CHBREPORT_SERVICE_NAME + "/ChbInvestOperation/qryChbInvestPrdInfo", param,
                ChbInvestPrdInfo.class);
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
        List<ChbInvsetAssetInfo> list =  restTemplate.postForList(
                SvrServiceApplication.CHBREPORT_SERVICE_NAME + "/ChbInvestOperation/qryChbInvsetAssetInfo", param,
                ChbInvsetAssetInfo.class);
        this.dealDictOrSpecialAssetData(list, (String) param.get("dealdateFmt"));
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
                item.setFbShowName("非标准化债权类资产明细 " + index);
                index++;
                //兑付本金币种显示名称
                String showName = this.getDictCodeAndName(DictCode.资产_负债二级类别, item.getAssetType2());
                if (StringUtil.isNotEmpty(showName)){
                    item.setAssetType2Fmt(showName);
                }
                else {
                    item.setAssetType2Fmt(空字符);
                }
                //项目收益率(利率)%
                if(item.getAssetYield() == null || BigDecimal.ZERO.compareTo(item.getAssetYield()) == 0) {
                    item.setAssetYieldFmt(无);
                }
                else {
                    item.setAssetYieldFmt(String.valueOf(item.getAssetYield()));
                }
                //剩余融资期限(年)
                if(item.getMdate() != null) {
                    if(DateUtil.compareDate(DateUtil.format(item.getMdate(), DateUtil.DEFAULT_DATE_FORMAT_EN), dealdateFmt) <0) {
                        item.setSurplusTerm(无);
                    }
                    else {
                        item.setSurplusTerm(String.valueOf(ArithmeticUtil.divide(
                                getBetweenDayNumber(DateUtil.parse(DateUtil.format(item.getMdate(), DateUtil.DEFAULT_DATE_FORMAT_EN)),
                                        DateUtil.parse(dealdateFmt)),
                                new BigDecimal(365.0000),2)));
                    }
                }
                else {
                    item.setSurplusTerm(无);
                }

            }
        }
    }

    /**
     * 根据两个日期，取得相隔的天数
     *
     * @param d1 开始日期
     * @param d2 结束日期
     * @return 天数
     */
    private BigDecimal getBetweenDayNumber(Date d1, Date d2) {
        LocalDate start = new LocalDate(d1);
        LocalDate end = new LocalDate(d2);
        int days = Days.daysBetween(start, end).getDays();
        return new BigDecimal(days);
    }

    public static final String 空字符 = "";
    public static final String 无 = "无";
    public static final String 顿号 = "、";
    /**
     * 处理返回的数据中需要处理数据字典及特殊字段的
     * @param chbInvestPrdInfo
     */
    private void dealDictOrSpecialObj(ChbInvestPrdInfo chbInvestPrdInfo) {
        //理财信息系统登记编码
        if (StringUtil.isEmpty(chbInvestPrdInfo.getRegCode())){
            chbInvestPrdInfo.setRegCode(空字符);
        }
        //兑付本金币种显示名称
        String showName = this.getDictCodeAndName(DictCode.币种_中债, chbInvestPrdInfo.getCashPrinCcy());
        if (StringUtil.isNotEmpty(showName)){
            chbInvestPrdInfo.setCashPrinCcyFmt(showName);
        }
        else {
            chbInvestPrdInfo.setCashPrinCcyFmt(空字符);
        }
        //投资性质显示名称
        showName = this.getDictCodeAndName(DictCode.投资性质, chbInvestPrdInfo.getCashPrinCcy());
        if (StringUtil.isNotEmpty(showName)){
            chbInvestPrdInfo.setIvstorientationFmt(showName);
        }
        else {
            chbInvestPrdInfo.setIvstorientationFmt(空字符);
        }
        //产品风险等级显示名称
        showName = this.getDictCodeAndName(DictCode.产品风险等级报告显示, chbInvestPrdInfo.getRiskLevel());
        if (StringUtil.isNotEmpty(showName)){
            chbInvestPrdInfo.setRiskLevelFmt(showName);
        }
        else {
            chbInvestPrdInfo.setRiskLevelFmt(空字符);
        }
        //起息日显示名称
        chbInvestPrdInfo.setVdateFmt(DateUtil.format(chbInvestPrdInfo.getVdate(), DateUtil.DEFAULT_DATE_FORMAT_EN));
        //境内外显示名称
        List<String> nameList = new ArrayList<>();
        if (StringUtil.isEmpty(chbInvestPrdInfo.getDepName())){
            chbInvestPrdInfo.setDepName(空字符);
        }
        //交易日期显示名称
        chbInvestPrdInfo.setDealdateFmt(DateUtil.format(chbInvestPrdInfo.getDealdate(), DateUtil.DEFAULT_DATE_FORMAT_EN));
    }


    /**
     * 获取中债格式化后的数据字典（数据字典名称） 如果传过来的是用“,”分割的字符串，则得到用“,”分割的格式化后的数据字典
     *
     * @param dictCode 数据字段
     * @param code 数据字典明细代码
     * @return 格式化后的数据
     * @see
     * @since 1.0
     */
    public String getDictCodeAndName(DictCode dictCode, final String code) {
        if (StringUtil.isEmpty(code)) {
            return "";
        }
        List<String> codeList = StringUtil.splitToList(GlobalConstant.COMMA, code);
        List<String> nameList = new ArrayList<>();
        for (String string : codeList) {
            String name = SysDataUtil.getNameFromDataDict(dictCode, string);
            if (StringUtil.isNotEmpty(name)){
                nameList.add(name);
            }
        }
        return StringUtil.joinString(nameList, GlobalConstant.COMMA);
    }


}
