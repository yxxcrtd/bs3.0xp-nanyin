/*
 * 文件名：OwnerEquityChange001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.ownerequitychangenew.service;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.tams.valuation.accounadjustmanage.ownerequitychangenew.dto.OwnerEquityChangeNew001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.valuationtablegeneration.dto.ExportFileDto;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tools.util.ExcelTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lby
 * @version 1.0 2020年11月24日
 * @see OwnerEquityChangeNew001Service
 * @since 1.0
 */
@Service
public class OwnerEquityChangeNew001Service extends CommonExportService<OwnerEquityChangeNew001Dto> {

    /**
    * 针对weblogic部署无法获取模版问题
    */
   private static final String TMP_DIR = "/tpl/";

    @Autowired
    private ValAssetBook001Service valAssetBook001Service;
    /**
     * 分页查询
     * 
     * @param pageParam
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public List<OwnerEquityChangeNew001Dto> selectList(Map<String, Object> pageParam)
        throws Exception {
        changeMapData(pageParam);
        // add by zwq 2020-12-21 start
        // 将账套组id和账套id统一转化为List
        List<String> booksetIds = new ArrayList<>();
        if (StringUtil.isNotEmpty(pageParam.get("booksetGroup")) && !StringUtil.isNotEmpty(pageParam.get("booksetId"))) {
            List<SysBatchGrpPrdlink> list1 = valAssetBook001Service.findAllProduct(pageParam);
            String bookSetIdGroup = list1.stream().map(SysBatchGrpPrdlink::getProductId).distinct().collect(Collectors.joining(","));
            booksetIds = Arrays.asList(bookSetIdGroup.split(","));
            pageParam.put("booksetIds", booksetIds);
        } else {
            booksetIds.add((String) pageParam.get("booksetId"));
            pageParam.put("booksetIds", booksetIds);
        }
        // add by zwq 2020-12-21 end

        /** modify by yanya on 20210118 修复账套组查询问题 start**/
        List<OwnerEquityChangeNew001Dto> allTmpList = new ArrayList<>();
        if (!StringUtil.isEmptyList(booksetIds)) {
            for(String str : booksetIds) {
                OwnerEquityChangeNew001Dto thisBegin = new OwnerEquityChangeNew001Dto();//本期的期初净值
                BigDecimal sszb = BigDecimal.ZERO;//期末实收资本的特殊处理
                BigDecimal wfplr = BigDecimal.ZERO;//期末未分配利润的特殊处理
                BigDecimal syzqy = BigDecimal.ZERO;//期末所有者权益合计的特殊处理
                BigDecimal qtzhsy = BigDecimal.ZERO; //其他综合收益
                pageParam.put("booksetIds", str);//单个账套
                // 本期金额start -------------------------------------------------------------------------------
                pageParam.put("vdate", pageParam.get("thisTermVdate"));
                pageParam.put("mdate", pageParam.get("thisTermMdate"));
                //获取本期的统计周期中的最早一次数据，则该天的期初净值为该周期的期初净值
                List<OwnerEquityChangeNew001Dto> thisbegins = this.selectBegin(pageParam);
                if (null != thisbegins && thisbegins.size() > 0) {
                    //因按日期进行正序叙排列，则第一条数据的期初净值为本周期的期初净值
                    thisBegin = thisbegins.get(0);
                    sszb = ArithmeticUtil.doAdd(sszb, thisBegin.gettIncomeFund());
                    wfplr = ArithmeticUtil.doAdd(wfplr, thisBegin.gettUnQuarterProfit());
                    syzqy = ArithmeticUtil.doAdd(syzqy, thisBegin.gettOwnerEquityTot());

                    // add by zwq 2020-12-17 start
                    qtzhsy = ArithmeticUtil.doAdd(qtzhsy, thisBegin.gettOtherCompIncome());
                    // add by zwq 2020-12-17 end
                }

                //本期统计周期内，所有的类目
                List<OwnerEquityChangeNew001Dto> thisTermData = this.selectAll(pageParam);
                if (null != thisTermData && thisTermData.size() > 0) {
                    for (OwnerEquityChangeNew001Dto dto : thisTermData) {
                        if (dto.getSortNum().equals("1")) {//期初净值数值替换
                            dto.settIncomeFund(thisBegin.gettIncomeFund());
                            dto.settUnQuarterProfit(thisBegin.gettUnQuarterProfit());
                            dto.settOwnerEquityTot(thisBegin.gettOwnerEquityTot());
                            dto.settOtherCompIncome(thisBegin.gettOtherCompIncome());
                        }
                        //if (dto.getSortNum().equals("7")) {//期末净值数值替换
                        if (dto.getSortNum().equals("8")) {//期末净值数值替换
                            dto.settIncomeFund(sszb);
                            dto.settUnQuarterProfit(wfplr);
                            dto.settOwnerEquityTot(syzqy);
                            dto.settOtherCompIncome(qtzhsy);
                            break;
                        }
                        if (!dto.getSortNum().equals("3") && !dto.getSortNum().equals("1")) {
                            sszb = ArithmeticUtil.doAdd(sszb, dto.gettIncomeFund());
                            wfplr = ArithmeticUtil.doAdd(wfplr, dto.gettUnQuarterProfit());
                            syzqy = ArithmeticUtil.doAdd(syzqy, dto.gettOwnerEquityTot());
                            qtzhsy = ArithmeticUtil.doAdd(qtzhsy, dto.gettOtherCompIncome());
                        }
                    }
                }


                // 上期金额start-----------------------------------------------------------------------------------------
                pageParam.put("vdate", pageParam.get("lastTermVdate"));
                pageParam.put("mdate", pageParam.get("lastTermMdate"));
                OwnerEquityChangeNew001Dto lastBegin = new OwnerEquityChangeNew001Dto();//本期的期初净值
                sszb = BigDecimal.ZERO;//上期期末实收资本的特殊处理(重置为0)
                wfplr = BigDecimal.ZERO;//上期期末未分配利润的特殊处理(重置为0)
                syzqy = BigDecimal.ZERO;//上期期末所有者权益合计的特殊处理(重置为0)
                //获取上期的统计周期中的最早一次数据，则该天的期初净值为该周期的期初净值
                qtzhsy = BigDecimal.ZERO; //其他综合收益 置为0
                List<OwnerEquityChangeNew001Dto> lastbegins = this.selectBegin(pageParam);
                if (null != lastbegins && lastbegins.size() > 0) {
                    //因按日期进行正序叙排列，则第一条数据的期初净值为本周期的期初净值
                    lastBegin = lastbegins.get(0);
                    sszb = ArithmeticUtil.doAdd(sszb, lastBegin.gettIncomeFund());
                    wfplr = ArithmeticUtil.doAdd(wfplr, lastBegin.gettUnQuarterProfit());
                    syzqy = ArithmeticUtil.doAdd(syzqy, lastBegin.gettOwnerEquityTot());
                    qtzhsy = ArithmeticUtil.doAdd(qtzhsy, lastBegin.gettOtherCompIncome());
                }

                //上期统计周期内，所有的类目
                List<OwnerEquityChangeNew001Dto> lastTermData = this.selectAll(pageParam);
                if (null != lastTermData && lastTermData.size() > 0) {
                    for (OwnerEquityChangeNew001Dto dto : lastTermData) {
                        if (dto.getSortNum().equals("1")) {//期初净值数值替换
                            dto.settIncomeFund(lastBegin.gettIncomeFund());
                            dto.settUnQuarterProfit(lastBegin.gettUnQuarterProfit());
                            dto.settOwnerEquityTot(lastBegin.gettOwnerEquityTot());
                            dto.settOtherCompIncome(lastBegin.gettOtherCompIncome());
                        }
                        //if (dto.getSortNum().equals("7")) {//期末净值数值替换
                        if (dto.getSortNum().equals("8")) {//期末净值数值替换
                            dto.setyIncomeFund(sszb);
                            dto.setyUnQuarterProfit(wfplr);
                            dto.setyOwnerEquityTot(syzqy);
                            dto.setyOtherCompIncome(qtzhsy);
                            break;
                        }
                        if (!dto.getSortNum().equals("3") && !dto.getSortNum().equals("1")) {
                            sszb = ArithmeticUtil.doAdd(sszb, dto.gettIncomeFund());
                            wfplr = ArithmeticUtil.doAdd(wfplr, dto.gettUnQuarterProfit());
                            syzqy = ArithmeticUtil.doAdd(syzqy, dto.gettOwnerEquityTot());
                            qtzhsy = ArithmeticUtil.doAdd(qtzhsy, dto.gettOtherCompIncome());
                        }

                    }
                }
                //数据整合
                //List<OwnerEquityChangeNew001Dto> temList = doMakeDto(thisTermData,lastTermData);
                allTmpList.addAll(doMakeDto(thisTermData,lastTermData));
            }
        }
        //还原参数，后续下载需要用到
        pageParam.put("booksetIds", booksetIds);
        /** modify by yanya on 20210118 修复账套组查询问题 end**/

        // add by zwq 2020-12-21 start
        // 如果相应的数据为null就给它设置默认值0
        //for (OwnerEquityChangeNew001Dto dto:temList){
        for (OwnerEquityChangeNew001Dto dto : allTmpList) {
            dto.settIncomeFund(dto.gettIncomeFund() == null ? new BigDecimal(0) : dto.gettIncomeFund());
            dto.settOtherCompIncome(dto.gettOtherCompIncome() == null ? new BigDecimal(0): dto.gettOtherCompIncome());
            dto.settUnQuarterProfit(dto.gettUnQuarterProfit() == null ? new BigDecimal(0) : dto.gettUnQuarterProfit());
            dto.settOwnerEquityTot(dto.gettOwnerEquityTot() == null ? new BigDecimal(0) : dto.gettOwnerEquityTot());

            dto.setyIncomeFund(dto.getyIncomeFund() == null ? new BigDecimal(0) : dto.getyIncomeFund());
            dto.setyOtherCompIncome(dto.getyOtherCompIncome() == null ? new BigDecimal(0) : dto.getyOtherCompIncome());
            dto.setyUnQuarterProfit(dto.getyUnQuarterProfit() == null ? new BigDecimal(0) : dto.getyUnQuarterProfit());
            dto.setyOwnerEquityTot(dto.getyOwnerEquityTot() == null ? new BigDecimal(0) : dto.getyOwnerEquityTot());
        }
        /** 是否明细查询 modify by yanya on 20210118 start**/
        boolean flag = false;
        if(StringUtil.isNotEmpty(pageParam.get("project"))) {
            flag = true;
        }
        /** 是否明细查询 modify by yanya on 20210118 end**/
        List<OwnerEquityChangeNew001Dto> list = doMergeDto(allTmpList, flag);
        // add by zwq 2020-12-21 end

        // add by zwq 2020-12-17 start
        list.sort(new Comparator<OwnerEquityChangeNew001Dto>() {
            @Override
            public int compare(OwnerEquityChangeNew001Dto o1, OwnerEquityChangeNew001Dto o2) {
                if (Integer.valueOf(o1.getSortNum()) < Integer.valueOf(o2.getSortNum())){
                    return -1;
                }else if (Integer.valueOf(o1.getSortNum()) > Integer.valueOf(o2.getSortNum())){
                    return 1;
                }
                return 0;
            }
        });
        // add by zwq 2020-12-17 end
        //modify by yanya on 20210118 过滤
        if(flag) {
            return list.stream().filter(item -> StringUtil.equals(item.getProject(), pageParam.get("project").toString()))
                    .collect(Collectors.toList());
        }
        return list;
    }


    /**
     * <组装所有者权益变动表>
     * <通过本期和上期数据来组装所有者权益变动表>
     * @param thisTermData 本期金额
     * @param lastTermData 上期金额
     * @return
     * @see
     * @since 1.0
     */
    private List<OwnerEquityChangeNew001Dto> doMakeDto(List<OwnerEquityChangeNew001Dto> thisTermData,
                                                       List<OwnerEquityChangeNew001Dto> lastTermData) {
        
        List<OwnerEquityChangeNew001Dto> list =  new ArrayList<OwnerEquityChangeNew001Dto>();
        if(null != thisTermData && thisTermData.size() > 0){
            for(OwnerEquityChangeNew001Dto dto : thisTermData){
                if(null != lastTermData && lastTermData.size() > 0){
                    for(OwnerEquityChangeNew001Dto dto2 : lastTermData){
                        if((dto.getSubjectName().equals(dto2.getSubjectName()))&&(dto.getBooksetId().equals(dto2.getBooksetId()))){
                            dto.setyIncomeFund(dto2.gettIncomeFund());//实收基金
                            dto.setyUnQuarterProfit(dto2.gettUnQuarterProfit());//所有者权益合计
                            dto.setyOwnerEquityTot(dto2.gettOwnerEquityTot());//所有者权益合计
                            dto.setyOtherCompIncome(dto2.gettOtherCompIncome()); // 其他综合收益
                            continue;
                        }
                    }
                }
                
            }
            list.addAll(thisTermData);
        }else{
            return list;
        }
        
        return list;
    }

    /**
     * 查找相同科目的数据
     * @param list
     * @param dto
     * @param flag 是否明细查询
     * @return
     */
    private OwnerEquityChangeNew001Dto findSameDto(List<OwnerEquityChangeNew001Dto> list, OwnerEquityChangeNew001Dto dto, boolean flag) {
        if(StringUtil.isEmptyList(list)) {
            return null;
        }
        else {
            for(OwnerEquityChangeNew001Dto tmp : list) {
                if(StringUtil.equals(tmp.getProject(), dto.getProject())) {
                    if(!flag) {
                        return tmp;
                    }
                    else {
                        if(StringUtil.equals(tmp.getBooksetId(), dto.getBooksetId())) {
                            return tmp;
                        }
                    }

                }
            }
            return null;
        }
    }

    /**
     * 合并
     * @param sourceList
     * @param flag
     * @return
     */
    private List<OwnerEquityChangeNew001Dto> doMergeDto(List<OwnerEquityChangeNew001Dto> sourceList, boolean flag) {
        List<OwnerEquityChangeNew001Dto> list = new ArrayList<>();
        for(OwnerEquityChangeNew001Dto dto : sourceList) {
            OwnerEquityChangeNew001Dto ori = findSameDto(list, dto, flag);
            if(ori == null) {
                list.add(dto);
            }
            else {
                ori.settIncomeFund(ArithmeticUtil.doAdd(dto.gettIncomeFund(), ori.gettIncomeFund()));
                ori.settOtherCompIncome(ArithmeticUtil.doAdd(dto.gettOtherCompIncome(), ori.gettOtherCompIncome()));
                ori.settUnQuarterProfit(ArithmeticUtil.doAdd(dto.gettUnQuarterProfit(), ori.gettUnQuarterProfit()));
                ori.settOwnerEquityTot(ArithmeticUtil.doAdd(dto.gettOwnerEquityTot(), ori.gettOwnerEquityTot()));
                ori.setyIncomeFund(ArithmeticUtil.doAdd(dto.getyIncomeFund(), ori.getyIncomeFund()));
                ori.setyOtherCompIncome(ArithmeticUtil.doAdd(dto.getyOtherCompIncome(), ori.getyOtherCompIncome()));
                ori.setyUnQuarterProfit(ArithmeticUtil.doAdd(dto.getyUnQuarterProfit(), ori.getyUnQuarterProfit()));
                ori.setyOwnerEquityTot(ArithmeticUtil.doAdd(dto.getyOwnerEquityTot(), ori.getyOwnerEquityTot()));
            }
        }
        return list;
    }

    /**
     * 设置页面传回参数
     * 
     * @param pageParam
     * @throws Exception
     * @see
     * @since 1.0
     */
    public void changeMapData(Map<String, Object> pageParam)
        throws Exception {
        String reportTermType = (String)pageParam.get("reportTermType");
        String valDate = DateUtil.format(analyseDate(pageParam), DateUtil.DEFAULT_DATE_FORMAT_EN);
        int thisYear = DateUtil.getYear(valDate);
        int lastYear = thisYear - 1;
        int yearBeforeLast = thisYear - 2;
        String reportTerm = (String)pageParam.get("reportTerm");
        String lastDimen = (String)pageParam.get("lastDimen");
        if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_日报.getValue())) {
        	pageParam.put("earlysta", "01");
            pageParam.put("thisTermMdate", valDate); // 本期
            pageParam.put("thisTermVdate", DateUtil.addDay(valDate, -1));// 期初
            if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                pageParam.put("lastTermMdate", DateUtil.addDay(valDate, -1)); // 上期本期
                pageParam.put("lastTermVdate", DateUtil.addDay(valDate, -2)); // 上期期初
            }
            else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                pageParam.put("lastTermMdate",
                    DateUtil.format(DateUtil.addYear(DateUtil.parse(valDate), -1),
                        DateUtil.DEFAULT_DATE_FORMAT_EN)); // 上期本期
                pageParam.put("lastTermVdate",
                    DateUtil.format(DateUtil.addDate(DateUtil.parse(valDate), -1, 0, -1),
                        DateUtil.DEFAULT_DATE_FORMAT_EN)); // 上期期初
            }
        }
        else if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_月报.getValue())) {
        	pageParam.put("earlysta", "02");
            pageParam.put("thisTermMdate", DateUtil.format(
                DateUtil.getLastDay(DateUtil.parse(valDate)), DateUtil.DEFAULT_DATE_FORMAT_EN)); // 本期
            pageParam.put("thisTermVdate",
                DateUtil.format(
                    DateUtil.getLastDay(DateUtil.addMonthDate(DateUtil.parse(valDate), -1)),
                    DateUtil.DEFAULT_DATE_FORMAT_EN)); // 期初
            if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                pageParam.put("lastTermMdate",
                    DateUtil.format(
                        DateUtil.getLastDay(DateUtil.addMonthDate(DateUtil.parse(valDate), -1)),
                        DateUtil.DEFAULT_DATE_FORMAT_EN)); // 上期本期
                pageParam.put("lastTermVdate",
                    DateUtil.format(
                        DateUtil.getLastDay(DateUtil.addMonthDate(DateUtil.parse(valDate), -2)),
                        DateUtil.DEFAULT_DATE_FORMAT_EN)); // 上期期初
            }
            else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                pageParam.put("lastTermMdate",
                    DateUtil.format(
                        DateUtil.getLastDay(DateUtil.addYear(DateUtil.parse(valDate), -1)),
                        DateUtil.DEFAULT_DATE_FORMAT_EN)); // 上期本期
                pageParam.put("lastTermVdate",
                    DateUtil.format(
                        DateUtil.getLastDay(DateUtil.addDate(DateUtil.parse(valDate), -1, -1, 0)),
                        DateUtil.DEFAULT_DATE_FORMAT_EN)); // 上期期初
            }
        }
        else if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_季报.getValue())) {
        	pageParam.put("earlysta", "02");
            if (StringUtil.equals(reportTerm, DictCodeListExt1.季度_第一季度.getValue())) {
                pageParam.put("thisTermVdate", lastYear + "-12-31"); // 期初
                pageParam.put("thisTermMdate", thisYear + "-03-31"); // 本期
                if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-09-30"); // 期初
                    pageParam.put("lastTermMdate", lastYear + "-12-31"); // 本期
                }
                else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                    pageParam.put("lastTermVdate", yearBeforeLast + "-12-31");  // 期初
                    pageParam.put("lastTermMdate", lastYear + "-03-31"); 		// 本期
                }
            }
            else if (StringUtil.equals(reportTerm, DictCodeListExt1.季度_第二季度.getValue())) {
                pageParam.put("thisTermVdate", thisYear + "-03-31"); // 期初
                pageParam.put("thisTermMdate", thisYear + "-06-30"); // 本期
                if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-12-31"); // 期初
                    pageParam.put("lastTermMdate", thisYear + "-03-31"); // 本期
                }
                else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-03-31"); // 期初
                    pageParam.put("lastTermMdate", lastYear + "-06-30"); // 本期
                }
            }
            else if (StringUtil.equals(reportTerm, DictCodeListExt1.季度_第三季度.getValue())) {
                pageParam.put("thisTermVdate", thisYear + "-06-30");
                pageParam.put("thisTermMdate", thisYear + "-09-30");
                if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                    pageParam.put("lastTermVdate", thisYear + "-03-31");
                    pageParam.put("lastTermMdate", thisYear + "-06-30");
                }
                else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-06-30");
                    pageParam.put("lastTermMdate", lastYear + "-09-30");
                }
            }
            else {
                pageParam.put("thisTermVdate", thisYear + "-09-30");
                pageParam.put("thisTermMdate", thisYear + "-12-31");
                if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                    pageParam.put("lastTermVdate", thisYear + "-06-30");
                    pageParam.put("lastTermMdate", thisYear + "-09-30");
                }
                else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-09-30");
                    pageParam.put("lastTermMdate", lastYear + "-12-31");
                }
            }
        }
        else if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_半年报.getValue())) {
        	pageParam.put("earlysta", "02");
            if (StringUtil.equals(reportTerm, DictCodeListExt1.半年_上半年.getValue())) {
                pageParam.put("thisTermVdate", lastYear + "-12-31");
                pageParam.put("thisTermMdate", thisYear + "-06-30");
                if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-06-30");
                    pageParam.put("lastTermMdate", lastYear + "-12-31");
                }
                else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                    pageParam.put("lastTermVdate", yearBeforeLast + "-12-31");
                    pageParam.put("lastTermMdate", lastYear + "-06-30");
                }
            }
            else {
                pageParam.put("thisTermVdate", thisYear + "-06-30");
                pageParam.put("thisTermMdate", thisYear + "-12-31");
                if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-12-31");
                    pageParam.put("lastTermMdate", thisYear + "-06-30");
                }
                else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-06-30");
                    pageParam.put("lastTermMdate", lastYear + "-12-31");
                }
            }
        }
        else {
        	pageParam.put("earlysta", "02");
            pageParam.put("thisTermVdate", lastYear + "-12-31");
            pageParam.put("thisTermMdate", thisYear + "-12-31");
            pageParam.put("lastTermVdate", yearBeforeLast + "-12-31");
            pageParam.put("lastTermMdate", lastYear + "-12-31");
        }
    }

    /**
     * 分析余额日期
     * @param pageParam 期末、年初
     * @return
     * @throws ParseException
     */
    public Date analyseDate(Map<String, Object> pageParam)
        throws ParseException {

        String reportTermType = (String)pageParam.get("reportTermType");
        String reportDate = (String)pageParam.get("valDate");
        String reportTerm = (String)pageParam.get("reportTerm");

        int year = 0;
        int month = -1;
        int day = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);// 时
        calendar.set(Calendar.MINUTE, 0);// 分
        calendar.set(Calendar.SECOND, 0);// 秒
        calendar.set(Calendar.MILLISECOND, 0);// 毫秒

        String[] strArr = reportDate.split(",")[0].split("-");
        if (DictCodeListExt1.报表期限类型_年报.getValue().equals(reportTermType)) {// 年底
            year = Integer.valueOf(strArr[0]);
            month += 12;
            day = 31;
        }
        if (DictCodeListExt1.报表期限类型_半年报.getValue().equals(reportTermType)) {
            year = Integer.valueOf(strArr[0]);
            if (DictCodeListExt1.半年_上半年.getValue().equals(reportTerm)) {
                month += 6;
                day = 30;
            }
            else {
                month += 12;
                day = 31;
            }
        }
        if (DictCodeListExt1.报表期限类型_季报.getValue().equals(reportTermType)) {
            year = Integer.valueOf(strArr[0]);
            if (DictCodeListExt1.季度_第一季度.getValue().equals(reportTerm)) {
                month += 3;
                day = 31;
            }
            else if (DictCodeListExt1.季度_第二季度.getValue().equals(reportTerm)) {
                month += 6;
                day = 30;
            }
            else if (DictCodeListExt1.季度_第三季度.getValue().equals(reportTerm)) {
                month += 9;
                day = 30;
            }
            else {
                month += 12;
                day = 31;
            }
        }
        if (DictCodeListExt1.报表期限类型_月报.getValue().equals(reportTermType)) {// 月底：当前月的下个月的第0天
            year = Integer.valueOf(strArr[0]);
            month += Integer.valueOf(strArr[1]) + 1;
            day = 0;
        }
        if (DictCodeListExt1.报表期限类型_日报.getValue().equals(reportTermType)) {
            year = Integer.valueOf(strArr[0]);
            month += Integer.valueOf(strArr[1]);
            day = Integer.valueOf(strArr[2]);
        }

        calendar.set(year, month, day);
        return calendar.getTime();

    }


	/**
	 * <导出功能>
	 * <功能详细描述>
	 * @param params
	 * @return
	 * @throws IOException 
	 * @see
	 * @since 1.0
	 */
	public ResponseEntity<byte[]> exportExcel(Map<String, Object> params) throws Exception {
		ExportFileDto exportFileDto = createExcel(params);
        File file = new File(exportFileDto.getFilePath());
        return ExportUtil.export(exportFileDto.getFileName(), file, true);
	}

	/**
	 * <导出报表>
	 * <功能详细描述>
	 * @param params
	 * @return
	 * @throws Exception 
	 * @see
	 * @since 1.0
	 */
	private ExportFileDto createExcel(Map<String, Object> params) throws Exception {
		// 报表日期
        String reportDate = DateUtil.format(analyseDate(params), DateUtil.DEFAULT_DATE_FORMAT_EN);
        // 导出数据集
        List<OwnerEquityChangeNew001Dto> dtoList = selectList(params);
        // add by zwq 2020-12-17 start
        dtoList = doDealStyle(dtoList,"EXCEL");
        // add by zwq 2020-12-17 end
        String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);

        // add by zwq 2020-12-21 start
        List<String> booksetIds = (List)params.get("booksetIds");
        String tempFileName = "";
        // add by zwq 2020-12-21 end
        SelectOptionDto optionDto = new SelectOptionDto();
        if (DictCodeListExt2.批处理账套类型_账套.getValue().equals(String.valueOf(params.get("reportDimen")))) {
            for (String id:booksetIds){
                tempFileName += id;
            }
        }else {
            Map<String, Object> pageParam = new HashMap<>();
            pageParam.put("booksetGroup",params.get("booksetGroup").toString());
            Page<SelectOptionDto> booksetGroup = valAssetBook001Service.findBooksetGroup(pageParam);
            optionDto = new SelectOptionDto();
            if (StringUtil.isNotEmpty(booksetGroup) && !StringUtil.isEmptyList(booksetGroup.getRoot())){
                optionDto = booksetGroup.getRoot().get(0);
            }
            tempFileName = optionDto.getText();
        }
        /** 修正下载文件名**/
        String fileName = tempFileName + "_" + reportDate + "_ 理财产品净值变动表" + ".xls";
        String tempName = "OwnerEquityChangeNew001.xls";
        String tplFlodPath = "accounadjustmanage/ownerequitychangenew/OwnerEquityChangeNew001.xls";
        String tplPath = FileNameUtil.getTplFile(tplFlodPath);
        String outPath = exportFilePath + File.separator + fileName;

        ExportFileDto exportFileDto = new ExportFileDto();
        InputStream stream = null;
        Map<String, Object> map = new HashMap<String, Object>();
        //所有者权益变动表(新)-导出增加产品代码(账套代码)字段
        String prefix = "";
        if(StringUtil.isNotEmpty(params.get("booksetId"))) {   // 账套
        	prefix = params.get("booksetId").toString();
        }else if (StringUtil.isNotEmpty(params.get("booksetGroup"))){ // 账套组
            prefix = optionDto.getText();
        }
        //System.out.println("prefix----1>"+prefix);
        map.put("prefix",prefix);
        //所有者权益变动表(新)-导出增加产品代码(账套代码)字段
        map.put("org",CacheUtil.getSysParam(GlobalConstant.财务报表_编制单位));
        map.put("dtoList", dtoList);
        map.put("reportDate", reportDate);
        map.put("reportTermType", SysDataUtil.getNameFromDataDict(DictCode.报表期限类型, params.get("reportTermType").toString()));
        exportFileDto.setFileName(fileName);
        exportFileDto.setFilePath(outPath);
        try {
            
            //针对weblogic环境部署，FileNameUtil.getTplFile无法获取tpl文件夹下模版。
            File templeteFile = new File(tplPath);
            //文件存在情况,直接该路径获取文件流
            if (templeteFile.exists()) {
                stream = new FileInputStream(templeteFile);
            }
            //文件不存在情况，即weblogic部署情况，需要换方式获取文件流。
            else {
                stream = this.getClass().getResourceAsStream(TMP_DIR + tplFlodPath);
            }
            
            ExcelTools.writeExcel(stream, tempName, map, outPath);
        } catch (IOException e) {
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                }
            }
        }
        return exportFileDto;
	}

    
    /**
     * 获取默认日期信息
     * 
     * @param pageParam
     * @return OwnerEquityChangeNew001Dto
     */
    public OwnerEquityChangeNew001Dto getDateInfo(Map<String, Object> pageParam) {
//        String booksetId = String.valueOf(pageParam.get("booksetId"));
        String reportTermType = String.valueOf(pageParam.get("reportTermType"));
        OwnerEquityChangeNew001Dto datedto = new OwnerEquityChangeNew001Dto();
        datedto.setValDate(DateUtil.getSysDate());
//        if(booksetId != "null" && booksetId != ""){
//            datedto = ownerEquityChange001Mapper.getDateInfo1(pageParam);
//        }else{
//            List<OwnerEquityChangeNew001Dto> dateList = ownerEquityChange001Mapper.getDateInfo2(pageParam);
//            for(OwnerEquityChangeNew001Dto item:dateList){
//                if(datedto.getValDate() == null || datedto.getValDate() == ""){
//                    datedto.setValDate(item.getValDate());
//                }else{
//                    if(datedto.getValDate().compareTo(item.getValDate()) > 0){
//                        datedto.setValDate(item.getValDate());
//                    }
//                }
//            }
//        }
        if(DictCodeListExt1.报表期限类型_日报.getValue().equals(reportTermType)){
//            datedto.setValDate(DateUtil.format(DateUtil.addDate(DateUtil.parse(datedto.getValDate()), 0, 0, -1)));
        }else if(DictCodeListExt1.报表期限类型_月报.getValue().equals(reportTermType)){
            datedto.setValDate(DateUtil.format(DateUtil.addDate(DateUtil.parse(datedto.getValDate()), 0, -1, 0), "yyyy-MM"));
        }else if(DictCodeListExt1.报表期限类型_季报.getValue().equals(reportTermType)){
            int season = DateUtil.getSeason(DateUtil.parse(datedto.getValDate()));
            if(season == 1){
                datedto.setReportTerm(DictCodeListExt1.季度_第四季度.getValue());
                datedto.setValDate(DateUtil.format(DateUtil.addDate(DateUtil.parse(datedto.getValDate()), -1, 0, 0), "yyyy"));
            }else if(season == 2){
                datedto.setReportTerm(DictCodeListExt1.季度_第一季度.getValue());
                datedto.setValDate(DateUtil.format(DateUtil.parse(datedto.getValDate()), "yyyy"));
            }else if(season == 3){
                datedto.setReportTerm(DictCodeListExt1.季度_第二季度.getValue());
                datedto.setValDate(DateUtil.format(DateUtil.parse(datedto.getValDate()), "yyyy"));
            }else if(season == 4){
                datedto.setReportTerm(DictCodeListExt1.季度_第三季度.getValue());
                datedto.setValDate(DateUtil.format(DateUtil.parse(datedto.getValDate()), "yyyy"));
            }
        }else if(DictCodeListExt1.报表期限类型_半年报.getValue().equals(reportTermType)){
            int month = DateUtil.getMonth(datedto.getValDate());
            if(month<=6){
                datedto.setReportTerm(DictCodeListExt1.半年_下半年.getValue());
                datedto.setValDate(DateUtil.format(DateUtil.addDate(DateUtil.parse(datedto.getValDate()), -1, 0, 0), "yyyy"));
            }else{
                datedto.setReportTerm(DictCodeListExt1.半年_上半年.getValue());
                datedto.setValDate(DateUtil.format(DateUtil.parse(datedto.getValDate()), "yyyy"));
            }
        }else if(DictCodeListExt1.报表期限类型_年报.getValue().equals(reportTermType)){
            datedto.setValDate(DateUtil.format(DateUtil.addDate(DateUtil.parse(datedto.getValDate()), -1, 0, 0), "yyyy"));
        }
        return datedto;
    }

    /**
     * <批量导出功能>
     * <功能详细描述>
     * @param paraMap
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public List<String> exportExcelBatch(Map<String, Object> paraMap) throws Exception {
        List<String> paths = new ArrayList<>();
        @SuppressWarnings("unchecked")
        List<SuggestDto> suggestDtos = (List<SuggestDto>)paraMap.get("accounts");
        for(SuggestDto dto :  suggestDtos){//因需要按单账套为单纯导出，所以分解为单账套
            paraMap.remove("booksetId");
            paraMap.put("booksetId", dto.getCode());
            ExportFileDto exportFileDto = createExcel(paraMap);
            paths.add(exportFileDto.getFilePath());
        }
        
        return paths;
    }

    private List<OwnerEquityChangeNew001Dto> selectBegin(Map<String, Object> param) {
        List<OwnerEquityChangeNew001Dto> list = restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                        + "/OwnerEquityChangeNew001/selectBegin"
                , param,
                OwnerEquityChangeNew001Dto.class);
        return list;
    }
    private List<OwnerEquityChangeNew001Dto> selectAll(Map<String, Object> param) {
        List<OwnerEquityChangeNew001Dto> list = restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                        + "/OwnerEquityChangeNew001/selectAll"
                , param,
                OwnerEquityChangeNew001Dto.class);
        return list;
    }


    // add by zwq 2020-12-17 start
    // 处理样式
//    private List doDealStyle1(List<OwnerEquityChangeNew001Dto> list,String type){
//        for (OwnerEquityChangeNew001Dto dto : list){
//            if (dto.getSubjectName().indexOf("理财产品赎回款") >= 0){
//                if ("EXCEL".equals(type)){
//                    dto.setSubjectName("     "+dto.getSubjectName());
//                }else if ("HTML".equals(type)){
//                    dto.setSubjectName("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+dto.getSubjectName());
//                }
//            }
//        }
//        return  list;
//    }
    // add by zwq 2020-12-17 end


    public List doDealStyle(List<OwnerEquityChangeNew001Dto> list, String style) {
        String prefix1 = "[一二三四五六].*";  // 一级项目前缀
        String prefix2 = "[\\d].*";   // 二级项目前缀
        String prefix3 = "其中";      // 三级项目前缀
        if ("HTML".equals(style)) {// html 样式
            for (OwnerEquityChangeNew001Dto dto : list) {
                String project = dto.getProject();
                if (project.matches(prefix1)) {
                    dto.setProject("<strong>" + project + "</strong>");
                } else if (!project.matches(prefix1) && project.indexOf(prefix3) < 0) {
                    dto.setProject("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + project);
                }
            }
        } else if ("EXCEL".equals(style)) { // excel 样式
            for (OwnerEquityChangeNew001Dto dto : list) {
                String project = dto.getProject();
                if (!project.matches(prefix1) && project.matches(prefix2) && project.indexOf(prefix3) < 0) {
                    // \t 在 windows 平台 不起作用。
                    dto.setProject("      " + project);
                }
            }
        }
        return list;
    }
}
