/*
 * 文件名：ValProfitQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.valprofitquerynew.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.valuation.accounadjustmanage.valprofitquerynew.dto.ValProfitNewQuery001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.valuationtablegeneration.dto.ExportFileDto;
import com.joyintech.tools.util.ExcelCellValueFunction;
import com.joyintech.tools.util.ExcelTools;

/***
 *
 * @author huanghao
 *
 */
@Service
public class ValProfitNewQuery001Service extends CommonExportService<ValProfitNewQuery001Dto> {

    /**
     * 针对weblogic部署无法获取模版问题
     */
    private static final String TMP_DIR = "/tpl/";

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ValProfitNewQuery001Service.class);


    /**
     * html 样式
     */
    private static final String HTML_STYLE = "HTML_STYLE";

    /**
     * excel 样式
     */
    private static final String EXCEL_STYLE = "EXCEL_STYLE";
    @Autowired
    private ValAssetBook001Service valAssetBook001Service;
    /**
     * 通用联动查询
     *
     * @param param : dict_code_parent,dict_code_child,item_code_parent
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    //@DataSource(DataSourceType.OTHER)
    public List<SelectOptionDto> getLinkSelectOption(Map<String, Object> param) {
        List<SelectOptionDto> list = restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                        + "/ValProfitNewQuery001/getLinkSelectOption"
                , param,
                SelectOptionDto.class);
        return list;
    }

    /**
     * 分析余额日期
     *
     * @param pageParam   查询条件
     * @param balanceType 期末、年初
     * @return
     * @throws ParseException
     */
    public Date analyseDate(Map<String, Object> pageParam)
            throws ParseException {

        String reportTermType = (String) pageParam.get("reportTermType");
        String reportDate = (String) pageParam.get("reportDate");
        String reportTerm = (String) pageParam.get("reportTerm");

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
//            year = Integer.valueOf(strArr[0]);
//            month += Integer.valueOf(strArr[1]);
//            day = Integer.valueOf(strArr[2]);
        }
        if (DictCodeListExt1.报表期限类型_半年报.getValue().equals(reportTermType)) {
            year = Integer.valueOf(strArr[0]);
            if (DictCodeListExt1.半年_上半年.getValue().equals(reportTerm)) {
                month += 6;
                day = 30;
            } else {
                month += 12;
                day = 31;
            }
        }
        if (DictCodeListExt1.报表期限类型_季报.getValue().equals(reportTermType)) {
            year = Integer.valueOf(strArr[0]);
            if (DictCodeListExt1.季度_第一季度.getValue().equals(reportTerm)) {
                month += 3;
                day = 31;
            } else if (DictCodeListExt1.季度_第二季度.getValue().equals(reportTerm)) {
                month += 6;
                day = 30;
            } else if (DictCodeListExt1.季度_第三季度.getValue().equals(reportTerm)) {
                month += 9;
                day = 30;
            } else {
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
     * 设置页面传回参数
     *
     * @param pageParam
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public void changeMapData(Map<String, Object> pageParam)
            throws ParseException {
        String reportTermType = (String) pageParam.get("reportTermType");
        String valDate = DateUtil.format(analyseDate(pageParam), DateUtil.DEFAULT_DATE_FORMAT_EN);
        // 上期维度
        String lastDimen = (String) pageParam.get("lastDimen");
        int thisYear = DateUtil.getYear(valDate);
        int lastYear = thisYear - 1;
        String reportTerm = (String) pageParam.get("reportTerm"); // 期限明细
        Date now = DateUtil.parse(valDate);
        if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_日报.getValue())) {
            pageParam.put("thisTermVdate", valDate);
            pageParam.put("thisTermMdate", valDate);
            if (DictCodeListExt1.上期维度_同比.getValue().equals(lastDimen)) {
                pageParam.put("lastTermVdate", DateUtil.format(DateUtil.addYear(now, -1)));
                pageParam.put("lastTermMdate", DateUtil.format(DateUtil.addYear(now, -1)));
            } else if (DictCodeListExt1.上期维度_环比.getValue().equals(lastDimen)) {
                pageParam.put("lastTermVdate", DateUtil.addDay(valDate, -1));
                pageParam.put("lastTermMdate", DateUtil.addDay(valDate, -1));
            }

        } else if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_月报.getValue())) {
            pageParam.put("thisTermVdate", DateUtil.format(
                    DateUtil.getFirstDay(DateUtil.parse(valDate)), DateUtil.DEFAULT_DATE_FORMAT_EN));
            pageParam.put("thisTermMdate", DateUtil.format(
                    DateUtil.getLastDay(DateUtil.parse(valDate)), DateUtil.DEFAULT_DATE_FORMAT_EN));
            if (DictCodeListExt1.上期维度_同比.getValue().equals(lastDimen)) {
                // 查询上期开始日 = 画面.日期-1年的月初
                pageParam.put("lastTermVdate",
                        DateUtil.format(DateUtil.getFirstDay(DateUtil.addYear(now, -1))));
                // 查询上期结束日 = 画面.日期-1年的月末
                pageParam.put("lastTermMdate",
                        DateUtil.format(DateUtil.getLastDay(DateUtil.addYear(now, -1))));
            } else if (DictCodeListExt1.上期维度_环比.getValue().equals(lastDimen)) {
                // 查询上期开始日 = 画面.日期-1月的月初
                pageParam.put("lastTermVdate",
                        DateUtil.format(DateUtil.getFirstDay(DateUtil.addMonthDate(now, -1))));
                // 查询上期结束日 = 画面.日期-1月的月末
                pageParam.put("lastTermMdate",
                        DateUtil.format(DateUtil.getLastDay(DateUtil.addMonthDate(now, -1))));
            }
        } else if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_季报.getValue())) {
            if (StringUtil.equals(reportTerm, DictCodeListExt1.季度_第一季度.getValue())) {
                pageParam.put("thisTermVdate", thisYear + "-01-01");
                pageParam.put("thisTermMdate", thisYear + "-03-31");
                if (DictCodeListExt1.上期维度_同比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", lastYear + "-01-01");
                    pageParam.put("lastTermMdate", lastYear + "-03-31");
                } else if (DictCodeListExt1.上期维度_环比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", lastYear + "-10-01");
                    pageParam.put("lastTermMdate", lastYear + "-12-31");
                }

            } else if (StringUtil.equals(reportTerm, DictCodeListExt1.季度_第二季度.getValue())) {
                pageParam.put("thisTermVdate", thisYear + "-04-01");
                pageParam.put("thisTermMdate", thisYear + "-06-30");
                if (DictCodeListExt1.上期维度_同比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", lastYear + "-04-01");
                    pageParam.put("lastTermMdate", lastYear + "-06-30");
                } else if (DictCodeListExt1.上期维度_环比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", thisYear + "-01-01");
                    pageParam.put("lastTermMdate", thisYear + "-03-31");
                }
            } else if (StringUtil.equals(reportTerm, DictCodeListExt1.季度_第三季度.getValue())) {
                pageParam.put("thisTermVdate", thisYear + "-07-01");
                pageParam.put("thisTermMdate", thisYear + "-09-30");
                if (DictCodeListExt1.上期维度_同比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", lastYear + "-07-01");
                    pageParam.put("lastTermMdate", lastYear + "-09-30");
                } else if (DictCodeListExt1.上期维度_环比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", thisYear + "-04-01");
                    pageParam.put("lastTermMdate", thisYear + "-06-30");
                }

            } else {
                pageParam.put("thisTermVdate", thisYear + "-10-01");
                pageParam.put("thisTermMdate", thisYear + "-12-31");
                if (DictCodeListExt1.上期维度_同比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", lastYear + "-10-01");
                    pageParam.put("lastTermMdate", lastYear + "-12-31");
                } else if (DictCodeListExt1.上期维度_环比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", thisYear + "-07-01");
                    pageParam.put("lastTermMdate", thisYear + "-09-30");
                }

            }
        } else if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_半年报.getValue())) {
            if (StringUtil.equals(reportTerm, DictCodeListExt1.半年_上半年.getValue())) {
                pageParam.put("thisTermVdate", thisYear + "-01-01");
                pageParam.put("thisTermMdate", thisYear + "-06-30");
                if (DictCodeListExt1.上期维度_同比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", lastYear + "-01-01");
                    pageParam.put("lastTermMdate", lastYear + "-06-30");
                } else if (DictCodeListExt1.上期维度_环比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", lastYear + "-07-01");
                    pageParam.put("lastTermMdate", lastYear + "-12-31");
                }

            } else if (StringUtil.equals(reportTerm, DictCodeListExt1.半年_下半年.getValue())) {
                pageParam.put("thisTermVdate", thisYear + "-07-01");
                pageParam.put("thisTermMdate", thisYear + "-12-31");
                if (DictCodeListExt1.上期维度_同比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", lastYear + "-07-01");
                    pageParam.put("lastTermMdate", lastYear + "-12-31");
                } else if (DictCodeListExt1.上期维度_环比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", thisYear + "-01-01");
                    pageParam.put("lastTermMdate", thisYear + "-06-30");
                }
            }
        } else if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_年报.getValue())) {
            pageParam.put("thisTermVdate", thisYear + "-01-01");
            //pageParam.put("thisTermMdate", thisYear + "-12-31");
            pageParam.put("lastTermVdate", lastYear + "-01-01");
            //pageParam.put("lastTermMdate", lastYear + "-12-31");
            pageParam.put("thisTermMdate", valDate);
            pageParam.put("lastTermMdate", DateUtil.format(DateUtil.addYear(now, -1)));
        }
    }

    /**
     * 查询利率表数据list
     *
     * @param pageParam
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public List<ValProfitNewQuery001Dto> selectList(Map<String, Object> pageParam)
            throws ParseException {
        changeMapData(pageParam);
        pageParam.put("vdate", pageParam.get("thisTermVdate"));
        pageParam.put("mdate", pageParam.get("thisTermMdate"));
        List<ValProfitNewQuery001Dto> thisTermData = this.selectProfit(pageParam);//本期利润数据
        pageParam.put("vdate", pageParam.get("lastTermVdate"));
        pageParam.put("mdate", pageParam.get("lastTermMdate"));
        List<ValProfitNewQuery001Dto> lastTermData = this.selectProfit(pageParam);//上期利润数据

        List<ValProfitNewQuery001Dto> list = doMakeRofitDto(thisTermData, lastTermData);

        return list;
    }


    /**
     * <组装利润表数据>
     * <功能详细描述>
     *
     * @param thisTermData
     * @param lastTermData
     * @return
     * @see
     * @since 1.0
     */
    private List<ValProfitNewQuery001Dto> doMakeRofitDto(List<ValProfitNewQuery001Dto> thisTermData,
                                                         List<ValProfitNewQuery001Dto> lastTermData) {
        // TODO Auto-generated method stub
        List<ValProfitNewQuery001Dto> list = new ArrayList<ValProfitNewQuery001Dto>();

        if (null != thisTermData && thisTermData.size() > 0) {//如果本期的利润数据不为空
            for (ValProfitNewQuery001Dto dto : thisTermData) {
                if (null != lastTermData && lastTermData.size() > 0) { //如果上期的利润数据不为空
                    for (ValProfitNewQuery001Dto last : lastTermData) {
                        if (dto.getProject().equals(last.getProject())) {
                            dto.setLastTermAmt(last.getThisTermAmt());//将上期金额加入到利润表数据中
                            continue;
                        }
                    }
                }

            }
            list.addAll(thisTermData);

        } else {
            return list;
        }


        return list;
    }

    /**
     * 分页查询 并 添加样式
     *
     * @param parameters
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public List<ValProfitNewQuery001Dto> findPage(Map<String, Object> parameters) throws ParseException {
        List<ValProfitNewQuery001Dto> dtoList = this.selectList(parameters);
        this.dealStyle(dtoList, HTML_STYLE, String.valueOf(parameters.get("reportDimen"))); // 添加样式
        return dtoList;
    }

    /**
     * 处理样式
     *
     * @param list
     * @param style
     * @param reportDimen 报表维度
     * @see
     * @since 1.0
     */
    public void dealStyle(List<ValProfitNewQuery001Dto> list, String style, String reportDimen) {
        String prefix1 = "[一二三四五六].*";  // 一级项目前缀
        String prefix2 = "[\\d].*";   // 二级项目前缀
        String prefix3 = "其中";      // 三级项目前缀       add by zwq 2020-12-22
        if (HTML_STYLE.equals(style)) {// html 样式
            for (ValProfitNewQuery001Dto dto : list) {
                String project = dto.getProject();
                if (project.matches(prefix1)) {
                    dto.setProject("<strong>" + project + "</strong>");
                } else if (!project.matches(prefix2) && project.indexOf(prefix3) < 0) {
                    dto.setProject("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + project);
                }

                if (DictCodeListExt2.批处理账套类型_账套组.getValue().equals(reportDimen)) {
                    dto.setProject("<label style='color:blue;'>" + dto.getProject() + "</label>");
                }
            }
        } else if (EXCEL_STYLE.equals(style)) { // excel 样式
            for (ValProfitNewQuery001Dto dto : list) {
                String project = dto.getProject();
                if (!project.matches(prefix1) && !project.matches(prefix2) && project.indexOf(prefix3) < 0) {
                    // \t 在 windows 平台 不起作用。
                    dto.setProject("    " + project);
                }
            }
        }
    }


    /**
     * 导出 利润表 excel
     *
     * @param dto
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public ResponseEntity<byte[]> exportExcel(Map<String, Object> params)
            throws ParseException, IOException {
        ExportFileDto exportFileDto = createExcel(params);
        File file = new File(exportFileDto.getFilePath());
        return ExportUtil.export(exportFileDto.getFileName(), file, true);
    }

    /**
     * 利润表 导出逻辑
     *
     * @param dto
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public ExportFileDto createExcel(Map<String, Object> params) throws ParseException, IOException {

        // 报表日期
        String reportDate = DateUtil.format(analyseDate(params), DateUtil.DEFAULT_DATE_FORMAT_EN);
        List<ValProfitNewQuery001Dto> dtoList = new ArrayList<ValProfitNewQuery001Dto>();
        String prefix;
        if ("1".equals(params.get("reportDimen"))) {
            // 账套
            dtoList = this.selectList(params);
            prefix = params.get("booksetId").toString();
        } else {
            // 账套组
            Map<String, Object> pageParam = new HashMap<>();
            pageParam.put("booksetGroup",params.get("booksetGroup").toString());
            Page<SelectOptionDto> booksetGroup = valAssetBook001Service.findBooksetGroup(pageParam);
            SelectOptionDto optionDto = new SelectOptionDto();
            if (StringUtil.isNotEmpty(booksetGroup) && !StringUtil.isEmptyList(booksetGroup.getRoot())){
                optionDto = booksetGroup.getRoot().get(0);
            }
            dtoList = this.selectListAll(params);
            String profitType = (String) params.get("profitType");
            if (StringUtil.isNotEmpty(profitType)) {
                profitType = profitType.replaceAll("01", "净值型");
                profitType = profitType.replaceAll("02", "预期收益型");
                profitType = profitType.replaceAll("03", "货币型");
                profitType = profitType.replaceAll(",", "、");
                prefix = "(" + profitType + ")" + optionDto.getText();
            } else {
                prefix = optionDto.getText();
            }
        }

        this.dealStyle(dtoList, EXCEL_STYLE, String.valueOf(params.get("reportDimen"))); // 添加样式

        String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
        String fileName = prefix + "_" + reportDate + "_利润表" + ".xls";
        String tempName = "ValProfitNewQuery.xls";
        String tplFlodPath = "accounadjustmanage/valprofitquerynew/ValProfitNewQuery.xls";
        String tplPath = FileNameUtil.getTplFile(tplFlodPath);
        String outPath = exportFilePath + File.separator + fileName;

        ExportFileDto exportFileDto = new ExportFileDto();
        InputStream stream = null;
        Map<String, Object> map = new HashMap<>();
        //利润表报表(新)-导出增加产品代码(账套代码)字段
        //System.out.println("prefix----2>"+prefix);
        map.put("prefix", prefix);
        //利润表报表(新)-导出增加产品代码(账套代码)字段
        map.put("org", CacheUtil.getSysParam(GlobalConstant.财务报表_编制单位));
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

            ExcelTools.writeExcel(stream, tempName, map, outPath, new ExcelCellValueFunction() {

                @Override
                public void execute(String tplKey, Object value, Cell cell, Row row, Sheet sheet, Workbook workbook) {

                    CellStyle newStyle = workbook.createCellStyle();
                    newStyle.cloneStyleFrom(cell.getCellStyle());
                    Font font = workbook.createFont();
                    // 加粗一级项目
                    if (StringUtil.startsWith(tplKey, "dtoList")
                            && tplKey.contains("project")
                            && String.valueOf(value).matches("[一二三四五六].*")) {
                        font.setBold(true);
                        newStyle.setFont(font);
                        cell.setCellStyle(newStyle);
                    }
                }
            });
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
        }
        return exportFileDto;
    }


    /**
     * 分页查询 并 添加样式
     *
     * @param parameters
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public List<ValProfitNewQuery001Dto> findPageAll(Map<String, Object> parameters) throws ParseException {
        List<ValProfitNewQuery001Dto> dtoList = this.selectListAll(parameters);
        this.dealStyle(dtoList, HTML_STYLE, String.valueOf(parameters.get("reportDimen"))); // 添加样式
        return dtoList;
    }

    /**
     * 查询利率表数据list
     *
     * @param pageParam
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public List<ValProfitNewQuery001Dto> selectListAll(Map<String, Object> pageParam)
            throws ParseException {
        changeMapData(pageParam);
        pageParam.put("vdate", pageParam.get("thisTermVdate"));
        pageParam.put("mdate", pageParam.get("thisTermMdate"));
        List<ValProfitNewQuery001Dto> thisTermData = this.selectProfitAll(pageParam);//本期利润数据
        pageParam.put("vdate", pageParam.get("lastTermVdate"));
        pageParam.put("mdate", pageParam.get("lastTermMdate"));
        List<ValProfitNewQuery001Dto> lastTermData = this.selectProfitAll(pageParam);//上期利润数据

        List<ValProfitNewQuery001Dto> list = doMakeRofitDto(thisTermData, lastTermData);

        return list;
    }


    /**
     * 获取默认日期信息
     *
     * @param pageParam
     * @return ValProfitQuery001Dto
     */
    public ValProfitNewQuery001Dto getDateInfo(Map<String, Object> pageParam) {
//        String booksetId = String.valueOf(pageParam.get("booksetId"));
        String reportTermType = String.valueOf(pageParam.get("reportTermType"));
        ValProfitNewQuery001Dto datedto = new ValProfitNewQuery001Dto();
        datedto.setReportDate(DateUtil.getSysDate());
//        if(booksetId != "null" && booksetId != ""){
//            datedto = valProfitQuery001Mapper.getDateInfo1(pageParam);
//        }else{
//            List<ValProfitQuery001Dto> dateList = valProfitQuery001Mapper.getDateInfo2(pageParam);
//            for(ValProfitQuery001Dto item:dateList){
//                if(datedto.getReportDate() == null || datedto.getReportDate() == ""){
//                    datedto.setReportDate(item.getReportDate());
//                }else{
//                    if(datedto.getReportDate().compareTo(item.getReportDate()) > 0){
//                        datedto.setReportDate(item.getReportDate());
//                    }
//                }
//            }
//        }
        if (DictCodeListExt1.报表期限类型_日报.getValue().equals(reportTermType)) {
//            datedto.setReportDate(DateUtil.format(DateUtil.addDate(DateUtil.parse(datedto.getReportDate()), 0, 0, -1)));
        } else if (DictCodeListExt1.报表期限类型_月报.getValue().equals(reportTermType)) {
            datedto.setReportDate(DateUtil.format(DateUtil.addDate(DateUtil.parse(datedto.getReportDate()), 0, -1, 0), "yyyy-MM"));
        } else if (DictCodeListExt1.报表期限类型_季报.getValue().equals(reportTermType)) {
            int season = DateUtil.getSeason(DateUtil.parse(datedto.getReportDate()));
            if (season == 1) {
                datedto.setReportTerm(DictCodeListExt1.季度_第四季度.getValue());
                datedto.setReportDate(DateUtil.format(DateUtil.addDate(DateUtil.parse(datedto.getReportDate()), -1, 0, 0), "yyyy"));
            } else if (season == 2) {
                datedto.setReportTerm(DictCodeListExt1.季度_第一季度.getValue());
                datedto.setReportDate(DateUtil.format(DateUtil.parse(datedto.getReportDate()), "yyyy"));
            } else if (season == 3) {
                datedto.setReportTerm(DictCodeListExt1.季度_第二季度.getValue());
                datedto.setReportDate(DateUtil.format(DateUtil.parse(datedto.getReportDate()), "yyyy"));
            } else if (season == 4) {
                datedto.setReportTerm(DictCodeListExt1.季度_第三季度.getValue());
                datedto.setReportDate(DateUtil.format(DateUtil.parse(datedto.getReportDate()), "yyyy"));
            }
        } else if (DictCodeListExt1.报表期限类型_半年报.getValue().equals(reportTermType)) {
            int month = DateUtil.getMonth(datedto.getReportDate());
            if (month <= 6) {
                datedto.setReportTerm(DictCodeListExt1.半年_下半年.getValue());
                datedto.setReportDate(DateUtil.format(DateUtil.addDate(DateUtil.parse(datedto.getReportDate()), -1, 0, 0), "yyyy"));
            } else {
                datedto.setReportTerm(DictCodeListExt1.半年_上半年.getValue());
                datedto.setReportDate(DateUtil.format(DateUtil.parse(datedto.getReportDate()), "yyyy"));
            }
        } else if (DictCodeListExt1.报表期限类型_年报.getValue().equals(reportTermType)) {
            datedto.setReportDate(DateUtil.format(DateUtil.parse(datedto.getReportDate()), "yyyy"));
//            int year = DateUtil.getYear(datedto.getReportDate())-1;
//            datedto.setReportDate(year + "-12-31");
        }
        return datedto;
    }

    /**
     * <批量导出功能>
     * <功能详细描述>
     *
     * @param paraMap
     * @return
     * @throws IOException
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public List<String> exportExcelBatch(Map<String, Object> paraMap) throws ParseException, IOException {
        List<String> paths = new ArrayList<>();
        @SuppressWarnings("unchecked")
        List<SuggestDto> suggestDtos = (List<SuggestDto>) paraMap.get("accounts");
        for (SuggestDto dto : suggestDtos) {//因需要按单账套为单位导出，所以分解为单账套
            paraMap.remove("booksetId");
            paraMap.put("booksetId", dto.getCode());
            ExportFileDto exportFileDto = createExcel(paraMap);
            paths.add(exportFileDto.getFilePath());
        }
        return paths;
    }

    /**
     * 检索账套代码列表（只取理财账套）
     *
     * @param param 过滤参数
     * @return 上级科目代码列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getBooksetId(String profitType) {
        String booksetType = DictCodeList.账套类别_理财账套.getValue();
        Map<String, Object> param = new HashMap<>();
        param.put("profitType", profitType);
        param.put("booksetType", booksetType);
        List<SuggestDto> list = restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                        + "/ValProfitNewQuery001/getBooksetId"
                , param,
                SuggestDto.class);
        return list;
    }

    private List<ValProfitNewQuery001Dto> selectProfit(Map<String, Object> param) {
        List<ValProfitNewQuery001Dto> list = restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                        + "/ValProfitNewQuery001/selectProfit"
                , param,
                ValProfitNewQuery001Dto.class);
        return list;
    }

    private List<ValProfitNewQuery001Dto> selectProfitAll(Map<String, Object> param) {
        List<ValProfitNewQuery001Dto> list = restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                        + "/ValProfitNewQuery001/selectProfitAll"
                , param,
                ValProfitNewQuery001Dto.class);
        return list;
    }

}
