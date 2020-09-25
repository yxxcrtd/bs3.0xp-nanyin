/*
 * 文件名：ValProfitQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.disclosure.busicommon.financialstatements.valprofitquery.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.commons.lang3.StringUtils;
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
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.dao.RepProdInfoMapper;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.disclosure.busicommon.common.constants.DictCode;
import com.joyintech.tams.disclosure.busicommon.common.constants.DictCodeList;
import com.joyintech.tams.disclosure.busicommon.common.constants.GlobalConstant;
import com.joyintech.tams.disclosure.busicommon.common.dao.BokBookset001Mapper;
import com.joyintech.tams.disclosure.busicommon.common.dao.DisValAssetBook001Mapper;
import com.joyintech.tams.disclosure.busicommon.common.dao.FinProduct001Mapper;
import com.joyintech.tams.disclosure.busicommon.common.dto.BokBalance;
import com.joyintech.tams.disclosure.busicommon.common.dto.BokBookset;
import com.joyintech.tams.disclosure.busicommon.common.dto.ExportFileDto;
import com.joyintech.tams.disclosure.busicommon.common.dto.FinProduct;
import com.joyintech.tams.disclosure.busicommon.common.dto.FinProductKey;
import com.joyintech.tams.disclosure.busicommon.financialstatements.valprofitquery.dao.DisValProfitQuery001Mapper;
import com.joyintech.tams.disclosure.busicommon.financialstatements.valprofitquery.dto.DisProfitCalculationDto;
import com.joyintech.tams.disclosure.busicommon.financialstatements.valprofitquery.dto.DisValProfitQuery001Dto;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tools.util.ExcelCellValueFunction;
import com.joyintech.tools.util.ExcelTools;

/**
 * 利润表service〉
 * 
 * @author wanghb
 * @version 1.0 2018年9月18日
 * @see ValProfitQuery001Service
 * @since 1.0
 */
@Service
public class DisValProfitQuery001Service extends CommonExportService<DisValProfitQuery001Dto> {
    
    /**
    * 针对weblogic部署无法获取模版问题
    */
   private static final String TMP_DIR = "/tpl/";

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(DisValProfitQuery001Service.class);
    
    /**
     * 利润表查询mapper
     */
    @Autowired
    private DisValProfitQuery001Mapper valProfitQuery001Mapper;

    /**
     * 账套查询mapper
     */
    @Autowired
    private BokBookset001Mapper bokBooksetMapper;

    /**
     * 标的类金融产品查询Mapper
     */
    @Autowired
    private FinProduct001Mapper finProductMapper;
    
    @Autowired
    private RepProdInfoMapper prodInfoMapper;
    
    @Autowired
    private  DisValAssetBook001Mapper disValAssetBook001Mapper;
    /**
     *  html 样式
     */
    private static final String HTML_STYLE = "HTML_STYLE";
    
    /**
     * excel 样式
     */
    private static final String EXCEL_STYLE = "EXCEL_STYLE";
    
    /**
     * 通用联动查询
     * 
     * @param param : dict_code_parent,dict_code_child,item_code_parent
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    public List<SelectOptionDto> getLinkSelectOption(Map<String, Object> param) {
        return valProfitQuery001Mapper.getLinkSelectOption(param);
    }

    /**
     * 分析余额日期
     * 
     * @param pageParam 查询条件
     * @param balanceType 期末、年初
     * @return
     * @throws ParseException
     */
    public Date analyseDate(Map<String, Object> pageParam)
        throws ParseException {

        String reportTermType = (String)pageParam.get("reportTermType");
        String reportDate = (String)pageParam.get("reportDate");
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
     * 设置页面传回参数
     * 
     * @param pageParam
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public void changeMapData(Map<String, Object> pageParam)
        throws ParseException {
        String reportTermType = (String)pageParam.get("reportTermType");
        String valDate = DateUtil.format(analyseDate(pageParam), DateUtil.DEFAULT_DATE_FORMAT_EN);
        // 上期维度
        String lastDimen = (String)pageParam.get("lastDimen");
        int thisYear = DateUtil.getYear(valDate);
        int lastYear = thisYear - 1;
        String reportTerm = (String)pageParam.get("reportTerm"); // 期限明细
        Date now = DateUtil.parse(valDate);
        if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_日报.getValue())) {
            pageParam.put("thisTermVdate", valDate);
            pageParam.put("thisTermMdate", valDate);
            if (DictCodeListExt1.上期维度_同比.getValue().equals(lastDimen)) {
                pageParam.put("lastTermVdate", DateUtil.format(DateUtil.addYear(now, -1)));
                pageParam.put("lastTermMdate", DateUtil.format(DateUtil.addYear(now, -1)));
            }
            else if (DictCodeListExt1.上期维度_环比.getValue().equals(lastDimen)) {
                pageParam.put("lastTermVdate", DateUtil.addDay(valDate, -1));
                pageParam.put("lastTermMdate", DateUtil.addDay(valDate, -1));
            }

        }
        else if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_月报.getValue())) {
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
            }
            else if (DictCodeListExt1.上期维度_环比.getValue().equals(lastDimen)) {
                // 查询上期开始日 = 画面.日期-1月的月初
                pageParam.put("lastTermVdate",
                    DateUtil.format(DateUtil.getFirstDay(DateUtil.addMonthDate(now, -1))));
                // 查询上期结束日 = 画面.日期-1月的月末
                pageParam.put("lastTermMdate",
                    DateUtil.format(DateUtil.getLastDay(DateUtil.addMonthDate(now, -1))));
            }
        }
        else if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_季报.getValue())) {
            if (StringUtil.equals(reportTerm, DictCodeListExt1.季度_第一季度.getValue())) {
                pageParam.put("thisTermVdate", thisYear + "-01-01");
                pageParam.put("thisTermMdate", thisYear + "-03-31");
                if (DictCodeListExt1.上期维度_同比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", lastYear + "-01-01");
                    pageParam.put("lastTermMdate", lastYear + "-03-31");
                }
                else if (DictCodeListExt1.上期维度_环比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", lastYear + "-10-01");
                    pageParam.put("lastTermMdate", lastYear + "-12-31");
                }

            }
            else if (StringUtil.equals(reportTerm, DictCodeListExt1.季度_第二季度.getValue())) {
                pageParam.put("thisTermVdate", thisYear + "-04-01");
                pageParam.put("thisTermMdate", thisYear + "-06-30");
                if (DictCodeListExt1.上期维度_同比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", lastYear + "-04-01");
                    pageParam.put("lastTermMdate", lastYear + "-06-30");
                }
                else if (DictCodeListExt1.上期维度_环比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", thisYear + "-01-31");
                    pageParam.put("lastTermMdate", thisYear + "-03-31");
                }
            }
            else if (StringUtil.equals(reportTerm, DictCodeListExt1.季度_第三季度.getValue())) {
                pageParam.put("thisTermVdate", thisYear + "-07-01");
                pageParam.put("thisTermMdate", thisYear + "-09-30");
                if (DictCodeListExt1.上期维度_同比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", lastYear + "-07-01");
                    pageParam.put("lastTermMdate", lastYear + "-09-30");
                }
                else if (DictCodeListExt1.上期维度_环比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", thisYear + "-04-01");
                    pageParam.put("lastTermMdate", thisYear + "-06-30");
                }

            }
            else {
                pageParam.put("thisTermVdate", thisYear + "-10-01");
                pageParam.put("thisTermMdate", thisYear + "-12-31");
                if (DictCodeListExt1.上期维度_同比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", lastYear + "-10-01");
                    pageParam.put("lastTermMdate", lastYear + "-12-31");
                }
                else if (DictCodeListExt1.上期维度_环比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", thisYear + "-07-01");
                    pageParam.put("lastTermMdate", thisYear + "-09-30");
                }

            }
        }
        else if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_半年报.getValue())) {
            if (StringUtil.equals(reportTerm, DictCodeListExt1.半年_上半年.getValue())) {
                pageParam.put("thisTermVdate", thisYear + "-01-01");
                pageParam.put("thisTermMdate", thisYear + "-06-30");
                if (DictCodeListExt1.上期维度_同比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", lastYear + "-01-01");
                    pageParam.put("lastTermMdate", lastYear + "-06-30");
                }
                else if (DictCodeListExt1.上期维度_环比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", lastYear + "-07-01");
                    pageParam.put("lastTermMdate", lastYear + "-12-31");
                }

            }
            else if (StringUtil.equals(reportTerm, DictCodeListExt1.半年_下半年.getValue())) {
                pageParam.put("thisTermVdate", thisYear + "-07-01");
                pageParam.put("thisTermMdate", thisYear + "-12-31");
                if (DictCodeListExt1.上期维度_同比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", lastYear + "-07-01");
                    pageParam.put("lastTermMdate", lastYear + "-12-31");
                }
                else if (DictCodeListExt1.上期维度_环比.getValue().equals(lastDimen)) {
                    pageParam.put("lastTermVdate", thisYear + "-01-01");
                    pageParam.put("lastTermMdate", thisYear + "-06-30");
                }
            }
        }
        else if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_年报.getValue())) {
            pageParam.put("thisTermVdate", thisYear + "-01-01");
            pageParam.put("thisTermMdate", thisYear + "-12-31");
            pageParam.put("lastTermVdate", lastYear + "-01-01");
            pageParam.put("lastTermMdate", lastYear + "-12-31");
        }
    }

    /**
     * 通过账套获取对应资产名称和资产代码
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public FinProduct getFinProductByBookset(DisValProfitQuery001Dto dto) {
        BokBookset bokBookset = bokBooksetMapper.selectByPrimaryKey(dto.getBooksetId());
        FinProductKey key = new FinProductKey();
        // 账套的核算主体代码关联金融产品标的表的金融产品代码
        key.setFinprodId(bokBookset.getAcctSubjectId());
        // 金融产品类型=理财产品
        key.setFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
        return finProductMapper.selectByPrimaryKey(key);
    }
    
    public RepProdInfo selectByPrimaryKey(String prodId){
    	return prodInfoMapper.selectByPrimaryKey(prodId);
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
    public List<DisValProfitQuery001Dto> selectList(Map<String, Object> pageParam)
        throws ParseException {
    	// 查询表样
    	List<DisProfitCalculationDto> profitCalculationDtoList = valProfitQuery001Mapper.selectProfitCalculationDtoList();
        Map<String, DisProfitCalculationDto> profitCalculationMap = profitCalculationDtoList.stream().collect(Collectors.toMap(DisProfitCalculationDto::getProjCode, Function.identity()));
        
        //changeMapData(pageParam);
        Map<String, DisValProfitQuery001Dto> dtoMap = initReportElement(profitCalculationMap);;
//        initData(dtoMap);
        pageParam.put("vdate", pageParam.get("thisTermVdate"));
        
        RepProdInfo prodinfo = null;
        
        if(null!=pageParam.get("booksetId")){
        	prodinfo = prodInfoMapper.selectByPrimaryKey(pageParam.get("booksetId").toString());
        }
        
        if(null!=prodinfo&&prodinfo.getProdType().equals(DisclosureDictCodeList.产品类型_货币型产品.getValue())){
        	pageParam.put("sdate", pageParam.get("thisTermVdate"));
        }else{
        	pageParam.put("sdate", pageParam.get("thisTermMdate"));
        }
        pageParam.put("mdate", pageParam.get("thisTermMdate"));
        List<BokBalance> thisTermData = valProfitQuery001Mapper.selectAllBalancebAmt(pageParam);
       
        //计算上期
        pageParam.put("vdate", pageParam.get("lastTermVdate"));
        if(null!=prodinfo&&prodinfo.getProdType().equals(DisclosureDictCodeList.产品类型_货币型产品.getValue())){
        	pageParam.put("sdate", pageParam.get("lastTermVdate"));
        }else{
        	pageParam.put("sdate", pageParam.get("lastTermMdate"));
        }
        pageParam.put("mdate", pageParam.get("lastTermMdate"));
        List<BokBalance> lastTermData = valProfitQuery001Mapper.selectAllBalanceXY(pageParam);
//        formatData(lastTermData, false, dtoMap);
        
        // 计算本期
        calValue(thisTermData, profitCalculationMap, true);
        // 计算上期
        calValue(lastTermData, profitCalculationMap, false);
        // 画面赋值
        setValue(profitCalculationMap, dtoMap);
        
        List<DisValProfitQuery001Dto> list = new ArrayList<DisValProfitQuery001Dto>();
        list.addAll(dtoMap.values());	
        list.sort(new Comparator<DisValProfitQuery001Dto>() {

            @Override
            public int compare(DisValProfitQuery001Dto arg0, DisValProfitQuery001Dto arg1) {
                if (arg0.getSortNum() < arg1.getSortNum()) {
                    return -1;
                }
                return 1;
            }
        });
        
        return list;
    }

    
    /**add  by  dongxiang  20200211  start  **/
    public List<DisValProfitQuery001Dto> selectListYth(Map<String, Object> pageParam)
        throws ParseException {
    	// 查询表样
    	List<DisProfitCalculationDto> profitCalculationDtoList = valProfitQuery001Mapper.selectProfitCalculationDtoListYth();
        Map<String, DisProfitCalculationDto> profitCalculationMap = profitCalculationDtoList.stream().collect(Collectors.toMap(DisProfitCalculationDto::getProjCode, Function.identity()));
        
        //changeMapData(pageParam);
        Map<String, DisValProfitQuery001Dto> dtoMap = initReportElement(profitCalculationMap);;
//        initData(dtoMap);
        pageParam.put("vdate", pageParam.get("thisTermVdate"));
        
        RepProdInfo prodinfo = null;
        
        if(null!=pageParam.get("booksetId")){
        	prodinfo = prodInfoMapper.selectByPrimaryKey(pageParam.get("booksetId").toString());
        }
        
        if(null!=prodinfo&&prodinfo.getProdType().equals(DisclosureDictCodeList.产品类型_货币型产品.getValue())){
        	pageParam.put("sdate", pageParam.get("thisTermVdate"));
        }else{
        	pageParam.put("sdate", pageParam.get("thisTermMdate"));
        }
        pageParam.put("mdate", pageParam.get("thisTermMdate"));
        List<BokBalance> thisTermData = valProfitQuery001Mapper.selectAllBalancebAmtYth(pageParam);
       
        //计算上期
        pageParam.put("vdate", pageParam.get("lastTermVdate"));
        if(null!=prodinfo&&prodinfo.getProdType().equals(DisclosureDictCodeList.产品类型_货币型产品.getValue())){
        	pageParam.put("sdate", pageParam.get("lastTermVdate"));
        }else{
        	pageParam.put("sdate", pageParam.get("lastTermMdate"));
        }
        pageParam.put("mdate", pageParam.get("lastTermMdate"));
        List<BokBalance> lastTermData = valProfitQuery001Mapper.selectAllBalancebAmtYth(pageParam);
//        formatData(lastTermData, false, dtoMap);
        
        // 计算本期
        calValue(thisTermData, profitCalculationMap, true);
        // 计算上期
        calValue(lastTermData, profitCalculationMap, false);
        // 画面赋值
        setValue(profitCalculationMap, dtoMap);
        
        List<DisValProfitQuery001Dto> list = new ArrayList<DisValProfitQuery001Dto>();
        list.addAll(dtoMap.values());	
        list.sort(new Comparator<DisValProfitQuery001Dto>() {

            @Override
            public int compare(DisValProfitQuery001Dto arg0, DisValProfitQuery001Dto arg1) {
                if (arg0.getSortNum() < arg1.getSortNum()) {
                    return -1;
                }
                return 1;
            }
        });
        
        return list;
    }
    /**add  by  dongxiang  20200211  end  **/
    
    
    /**
     * 分页查询 并 添加样式
     * @param parameters
     * @return
     * @throws ParseException 
     * @see
     * @since 1.0
     */
    public List<DisValProfitQuery001Dto> findPage(Map<String, Object> parameters) throws ParseException {
        List<DisValProfitQuery001Dto> dtoList = this.selectList(parameters);
        this.dealStyle(dtoList, HTML_STYLE, String.valueOf(parameters.get("reportDimen"))); // 添加样式
        return dtoList;
    }
    
    /**
     * 处理样式
     * @param list
     * @param style
     * @param reportDimen 报表维度
     * @see
     * @since 1.0
     */
    public void dealStyle(List<DisValProfitQuery001Dto> list,  String style, String reportDimen) {
        String prefix1 = "[一二三].*";  // 一级项目前缀 
        String prefix2 = "[\\d].*";   // 二级项目前缀
        if(HTML_STYLE.equals(style)) {// html 样式
            for (DisValProfitQuery001Dto dto : list) {
                String project = dto.getProject();
                if(project.matches(prefix1)) {
                    dto.setProject("<strong>" + project + "</strong>");
                }else if(!project.matches(prefix2)) {
                    dto.setProject("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + project);
                }
                
                if ("02".equals(reportDimen)) {
                	dto.setProject("<label style='color:blue;'>" + dto.getProject() + "</label>");
                }
            }
        }else if(EXCEL_STYLE.equals(style)){ // excel 样式
            for (DisValProfitQuery001Dto dto : list) {
                String project = dto.getProject();
                if( !project.matches(prefix1) && !project.matches(prefix2)) {
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
        List<DisValProfitQuery001Dto> dtoList = new ArrayList<DisValProfitQuery001Dto>();
        String prefix;
        if ("01".equals(params.get("reportDimen"))) {
        	// 单账套
        	dtoList = this.selectList(params);
        	prefix = params.get("booksetId").toString();
        } else {
        	// 全账套
        	dtoList = this.selectListAll(params);
        	prefix = "全账套";
        }
        
        this.dealStyle(dtoList, EXCEL_STYLE, String.valueOf(params.get("reportDimen"))); // 添加样式
        
        String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
        String fileName = prefix + "_" + reportDate + "_利润表" + ".xls";
        String tempName = "ValProfitQuery.xls";
        String tplFlodPath = "/accounadjustmanage/valprofitquery/ValProfitQuery.xls";
        String tplPath = FileNameUtil.getTplFile(tplFlodPath);
        String outPath = exportFilePath + File.separator + fileName;

        ExportFileDto exportFileDto = new ExportFileDto();
        InputStream stream = null;
        Map<String, Object> map = new HashMap<>();
        //map.put("org",CacheUtil.getSysParam(GlobalConstant.财务报表_编制单位));
        //多级法人改造start add by hxy 20200619
        if(MultilevelDataAuthorityUtil.isEnabled()) {
        	 //联社模式,查询登录人的机构
        /*	String org =   MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
            String orgName=CacheUtil.getAppCacheInstitutionCodeName(org).toString();
        	map.put("org", orgName);*/
        	 //联社模式
	       	 //根据选择的账套组或者账套，查询出它们的所属机构
	       	String booksetOrg;
	       	if ("02".equals((String)params.get("reportTerm"))) {
	            	booksetOrg=(String)params.get("booksetGroup");
		         String orgName=disValAssetBook001Mapper.selectAssociationModeOrgName(booksetOrg);
		         map.put("org", orgName);
		         
	       	}else{
	       		booksetOrg=(String)params.get("booksetId");
	       		String orgName=disValAssetBook001Mapper.selectAssociationModeOrgName(booksetOrg);
	           	map.put("org", orgName);
	       	}
        	
        }else{
        	//银行模式
        	String orgName=disValAssetBook001Mapper.selectBankModeOrgName();
        	map.put("org", orgName);
        	
        }
        //多级法人改造end add by hxy 20200619
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
                        && String.valueOf(value).matches("[一二三].*")) {
                        font.setBold(true);
                        newStyle.setFont(font);
                        cell.setCellStyle(newStyle);
                    }
                }
            });
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    logger.error("",e);
                }
            }
        }
        return exportFileDto;
    }
    
    /**
     * 把科目余额数据转换为查询数据
     * 
     * @param dataList 科目余额信息
     * @param flag true-本期数据 false-上期数据
     * @param dtoMap 转换的dto数据
     * @see
     * @since 1.0
     */
    public void formatData(List<BokBalance> dataList, boolean flag,
                           Map<String, DisValProfitQuery001Dto> dtoMap) {
        Map<String, BigDecimal> dataMap = new HashMap<String, BigDecimal>();
        for (BokBalance dto : dataList) {
            // 计算当期收入金额
            // SUM（原币当日发生额贷方-原币当日发生额借方+原币结转当日发生额借方 - 原币结转当日发生额贷方）
            BigDecimal amt = ArithmeticUtil.doAdd(
                ArithmeticUtil.subtract(
                    dto.getTdyOCAmt() == null ? BigDecimal.ZERO : dto.getTdyOCAmt(),
                    dto.getTdyODAmt() == null ? BigDecimal.ZERO : dto.getTdyODAmt()),
                ArithmeticUtil.subtract(
                    dto.getTdyOCoDAmt() == null ? BigDecimal.ZERO : dto.getTdyOCoDAmt(),
                    dto.getTdyOCoCAmt() == null ? BigDecimal.ZERO : dto.getTdyOCoCAmt()));
            // 计算当期支出金额
            // SUM（原币当日发生额借方-原币当日发生额贷方+原币结转当日发生额贷方 - 原币结转当日发生额借方）
            BigDecimal payAmt = ArithmeticUtil.doAdd(
                ArithmeticUtil.subtract(
                    dto.getTdyODAmt() == null ? BigDecimal.ZERO : dto.getTdyODAmt(),
                    dto.getTdyOCAmt() == null ? BigDecimal.ZERO : dto.getTdyOCAmt()),
                ArithmeticUtil.subtract(
                    dto.getTdyOCoCAmt() == null ? BigDecimal.ZERO : dto.getTdyOCoCAmt(),
                    dto.getTdyOCoDAmt() == null ? BigDecimal.ZERO : dto.getTdyOCoDAmt()));
            if (StringUtil.equals("6011", dto.getSubjectNo())) {
                // dataMap.put("1.利息收入",dataMap.get("1.利息收入")==null?amtpayAmtithmeticUtil.doAdd(dataMap.get("1.利息收入"),amt));
                dataMap.put("一、收入",
                    dataMap.get("一、收入") == null ? amt : ArithmeticUtil.doAdd(dataMap.get("一、收入"),
                        amt));

                if (flag) {
                    dtoMap.get("1.利息收入").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("1.利息收入").getThisTermAmt(), amt));
                    dtoMap.get("一、收入").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("一、收入").getThisTermAmt(), amt));
                }
                else {
                    dtoMap.get("1.利息收入").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("1.利息收入").getLastTermAmt(), amt));
                    dtoMap.get("一、收入").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("一、收入").getLastTermAmt(), amt));
                }
            }
            else if (StringUtil.equals("601102", dto.getSubjectNo())
                     || StringUtil.equals("601103", dto.getSubjectNo())
                     || StringUtil.equals("601104", dto.getSubjectNo())) {
                // dataMap.put("债券利息收入",dataMap.get("债券利息收入")==null?amt:ArithmeticUtil.doAdd(dataMap.get("债券利息收入"),amt));
                if (flag) {
                    dtoMap.get("债券利息收入").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("债券利息收入").getThisTermAmt(), amt));
                }
                else {
                    dtoMap.get("债券利息收入").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("债券利息收入").getLastTermAmt(), amt));
                }
            }
            else if (StringUtil.equals("601111", dto.getSubjectNo())) {
                // dataMap.put("存放款收入",dataMap.get("存放款收入")==null?amt:ArithmeticUtil.doAdd(dataMap.get("存放款收入"),amt));
                if (flag) {
                    dtoMap.get("存放款收入").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("存放款收入").getThisTermAmt(), amt));
                }
                else {
                    dtoMap.get("存放款收入").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("存放款收入").getLastTermAmt(), amt));
                }
            }
            else if (StringUtil.equals("601121", dto.getSubjectNo())) {
                // dataMap.put("拆放同业利息收入",dataMap.get("拆放同业利息收入")==null?amt:ArithmeticUtil.doAdd(dataMap.get("拆放同业利息收入"),amt));
                if (flag) {
                    dtoMap.get("拆放同业利息收入").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("拆放同业利息收入").getThisTermAmt(), amt));
                }
                else {
                    dtoMap.get("拆放同业利息收入").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("拆放同业利息收入").getLastTermAmt(), amt));
                }
            }
            else if (StringUtil.equals("601131", dto.getSubjectNo())) {
                // dataMap.put("买入返售利息收入",dataMap.get("买入返售利息收入")==null?amt:ArithmeticUtil.doAdd(dataMap.get("买入返售利息收入"),amt));
                if (flag) {
                    dtoMap.get("买入返售利息收入").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("买入返售利息收入").getThisTermAmt(), amt));
                }
                else {
                    dtoMap.get("买入返售利息收入").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("买入返售利息收入").getLastTermAmt(), amt));
                }
            }
            else if (StringUtil.equals("601108", dto.getSubjectNo())) {
                // dataMap.put("优先股投资股息收入",dataMap.get("优先股投资股息收入")==null?amt:ArithmeticUtil.doAdd(dataMap.get("优先股投资股息收入"),amt));
                if (flag) {
                    dtoMap.get("优先股投资股息收入").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("优先股投资股息收入").getThisTermAmt(), amt));
                }
                else {
                    dtoMap.get("优先股投资股息收入").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("优先股投资股息收入").getLastTermAmt(), amt));
                }
            }
            else if (StringUtil.equals("601106", dto.getSubjectNo())) {
                // dataMap.put("计划类资产利息收入",dataMap.get("计划类资产利息收入")==null?amt:ArithmeticUtil.doAdd(dataMap.get("计划类资产利息收入"),amt));
                if (flag) {
                    dtoMap.get("计划类资产利息收入").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("计划类资产利息收入").getThisTermAmt(), amt));
                }
                else {
                    dtoMap.get("计划类资产利息收入").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("计划类资产利息收入").getLastTermAmt(), amt));
                }
            }
            else if (StringUtil.equals("601101", dto.getSubjectNo())) {
                // dataMap.put("活期存款利息收入",dataMap.get("活期存款利息收入")==null?amt:ArithmeticUtil.doAdd(dataMap.get("活期存款利息收入"),amt));
                if (flag) {
                    dtoMap.get("活期存款利息收入").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("活期存款利息收入").getThisTermAmt(), amt));
                }
                else {
                    dtoMap.get("活期存款利息收入").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("活期存款利息收入").getLastTermAmt(), amt));
                }
            }
            else if (StringUtil.equals("601109", dto.getSubjectNo())) {
                // dataMap.put("债券借贷借出费收入",dataMap.get("债券借贷借出费收入")==null?amt:ArithmeticUtil.doAdd(dataMap.get("债券借贷借出费收入"),amt));
                if (flag) {
                    dtoMap.get("债券借贷借出费收入").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("债券借贷借出费收入").getThisTermAmt(), amt));
                }
                else {
                    dtoMap.get("债券借贷借出费收入").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("债券借贷借出费收入").getLastTermAmt(), amt));
                }
            }
            else if (StringUtil.equals("601199", dto.getSubjectNo())
                     || StringUtil.equals("601198", dto.getSubjectNo())) {
                // dataMap.put("利息收入增值税备抵",dataMap.get("利息收入增值税备抵")==null?amt:ArithmeticUtil.doAdd(dataMap.get("利息收入增值税备抵"),amt));
                if (flag) {
                    dtoMap.get("利息收入增值税备抵").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("利息收入增值税备抵").getThisTermAmt(), amt));
                }
                else {
                    dtoMap.get("利息收入增值税备抵").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("利息收入增值税备抵").getLastTermAmt(), amt));
                }
            }
            else if (StringUtil.equals("6111", dto.getSubjectNo())) {
                // dataMap.put("2.投资收益",dataMap.get("2.投资收益")==null?amt:ArithmeticUtil.doAdd(dataMap.get("2.投资收益"),amt));
                dataMap.put("一、收入",
                    dataMap.get("一、收入") == null ? amt : ArithmeticUtil.doAdd(dataMap.get("一、收入"),
                        amt));
                if (flag) {
                    dtoMap.get("2.投资收益").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("2.投资收益").getThisTermAmt(), amt));
                    dtoMap.get("一、收入").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("一、收入").getThisTermAmt(), amt));
                }
                else {
                    dtoMap.get("2.投资收益").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("2.投资收益").getLastTermAmt(), amt));
                    dtoMap.get("一、收入").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("一、收入").getLastTermAmt(), amt));
                }
            }
            else if (StringUtil.equals("611108", dto.getSubjectNo())) {
                // dataMap.put("股票投资收益",dataMap.get("股票投资收益")==null?amt:ArithmeticUtil.doAdd(dataMap.get("股票投资收益"),amt));
                if (flag) {
                    dtoMap.get("股票投资收益").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("股票投资收益").getThisTermAmt(), amt));
                }
                else {
                    dtoMap.get("股票投资收益").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("股票投资收益").getLastTermAmt(), amt));
                }
            }
            else if (StringUtil.equals("611102", dto.getSubjectNo())
                     || StringUtil.equals("611103", dto.getSubjectNo())
                     || StringUtil.equals("611104", dto.getSubjectNo())) {
                // dataMap.put("债券投资收益",dataMap.get("债券投资收益")==null?amt:ArithmeticUtil.doAdd(dataMap.get("债券投资收益"),amt));
                if (flag) {
                    dtoMap.get("债券投资收益").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("债券投资收益").getThisTermAmt(), amt));
                }
                else {
                    dtoMap.get("债券投资收益").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("债券投资收益").getLastTermAmt(), amt));
                }
            }
            else if (StringUtil.equals("611105", dto.getSubjectNo())) {
                // dataMap.put("基金投资收益",dataMap.get("基金投资收益")==null?amt:ArithmeticUtil.doAdd(dataMap.get("基金投资收益"),amt));
                if (flag) {
                    dtoMap.get("基金投资收益").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("基金投资收益").getThisTermAmt(), amt));
                }
                else {
                    dtoMap.get("基金投资收益").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("基金投资收益").getLastTermAmt(), amt));
                }
            }
            else if (StringUtil.equals("601105", dto.getSubjectNo())) {
                // dataMap.put("基金红利收入",dataMap.get("基金红利收入")==null?amt:ArithmeticUtil.doAdd(dataMap.get("基金红利收入"),amt));
                if (flag) {
                    dtoMap.get("基金红利收入").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("基金红利收入").getThisTermAmt(), amt));
                }
                else {
                    dtoMap.get("基金红利收入").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("基金红利收入").getLastTermAmt(), amt));
                }
            }
            else if (StringUtil.equals("611106", dto.getSubjectNo())
                     || StringUtil.equals("611107", dto.getSubjectNo())) {
                // dataMap.put("计划类投资收益",dataMap.get("计划类投资收益")==null?amt:ArithmeticUtil.doAdd(dataMap.get("计划类投资收益"),amt));
                if (flag) {
                    dtoMap.get("计划类投资收益").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("计划类投资收益").getThisTermAmt(), amt));
                }
                else {
                    dtoMap.get("计划类投资收益").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("计划类投资收益").getLastTermAmt(), amt));
                }
            }
            else if (StringUtil.equals("601107", dto.getSubjectNo())) {
                // dataMap.put("基金红利收入",dataMap.get("基金红利收入")==null?amt:ArithmeticUtil.doAdd(dataMap.get("基金红利收入"),amt));
                if (flag) {
                    dtoMap.get("计划类投资红利收入").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("计划类投资红利收入").getThisTermAmt(), amt));
                }
                else {
                    dtoMap.get("计划类投资红利收入").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("计划类投资红利收入").getLastTermAmt(), amt));
                }
            }
            else if (StringUtil.equals("611109", dto.getSubjectNo())) {
                // dataMap.put("以公允价值计量且其变动计入当期损益的金融负债价差收益",dataMap.get("以公允价值计量且其变动计入当期损益的金融负债价差收益")==null?amt:ArithmeticUtil.doAdd(dataMap.get("以公允价值计量且其变动计入当期损益的金融负债价差收益"),amt));
                if (flag) {
                    dtoMap.get("以公允价值计量且其变动计入当期损益的金融负债价差收益").setThisTermAmt(ArithmeticUtil.doAdd(
                        dtoMap.get("以公允价值计量且其变动计入当期损益的金融负债价差收益").getThisTermAmt(), amt));
                }
                else {
                    dtoMap.get("以公允价值计量且其变动计入当期损益的金融负债价差收益").setLastTermAmt(ArithmeticUtil.doAdd(
                        dtoMap.get("以公允价值计量且其变动计入当期损益的金融负债价差收益").getLastTermAmt(), amt));
                }
            }
            else if (StringUtil.equals("611199", dto.getSubjectNo())
                     || StringUtil.equals("611198", dto.getSubjectNo())) {
                // dataMap.put("投资收益增值税备抵",dataMap.get("投资收益增值税备抵")==null?amt:ArithmeticUtil.doAdd(dataMap.get("投资收益增值税备抵"),amt));
                if (flag) {
                    dtoMap.get("投资收益增值税备抵").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("投资收益增值税备抵").getThisTermAmt(), amt));
                }
                else {
                    dtoMap.get("投资收益增值税备抵").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("投资收益增值税备抵").getLastTermAmt(), amt));
                }
            }
            else if (StringUtil.equals("611195", dto.getSubjectNo())) {
                // dataMap.put("红利收入增值税备抵",dataMap.get("红利收入增值税备抵")==null?amt:ArithmeticUtil.doAdd(dataMap.get("红利收入增值税备抵"),amt));
                if (flag) {
                    dtoMap.get("红利收入增值税备抵").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("红利收入增值税备抵").getThisTermAmt(), amt));
                }
                else {
                    dtoMap.get("红利收入增值税备抵").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("红利收入增值税备抵").getLastTermAmt(), amt));
                }
            }
            else if (StringUtil.equals("611190", dto.getSubjectNo())) {
                // dataMap.put("衍生工具收益",dataMap.get("衍生工具收益")==null?amt:ArithmeticUtil.doAdd(dataMap.get("衍生工具收益"),amt));
                if (flag) {
                    dtoMap.get("衍生工具收益").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("衍生工具收益").getThisTermAmt(), amt));
                }
                else {
                    dtoMap.get("衍生工具收益").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("衍生工具收益").getLastTermAmt(), amt));
                }
            }
            else if (StringUtil.equals("6101", dto.getSubjectNo())) {
                // dataMap.put("3.公允价值变动损益",dataMap.get("3.公允价值变动损益")==null?amt:ArithmeticUtil.doAdd(dataMap.get("3.公允价值变动损益"),amt));
                dataMap.put("一、收入",
                    dataMap.get("一、收入") == null ? amt : ArithmeticUtil.doAdd(dataMap.get("一、收入"),
                        amt));
                if (flag) {
                    dtoMap.get("3.公允价值变动损益").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("3.公允价值变动损益").getThisTermAmt(), amt));
                    dtoMap.get("一、收入").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("一、收入").getThisTermAmt(), amt));
                }
                else {
                    dtoMap.get("3.公允价值变动损益").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("3.公允价值变动损益").getLastTermAmt(), amt));
                    dtoMap.get("一、收入").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("一、收入").getLastTermAmt(), amt));
                }
            }
            else if (StringUtil.equals("6112", dto.getSubjectNo())) {
                // dataMap.put("4.汇兑损益",dataMap.get("4.汇兑损益")==null?amt:ArithmeticUtil.doAdd(dataMap.get("4.汇兑损益"),amt));
                dataMap.put("一、收入",
                    dataMap.get("一、收入") == null ? amt : ArithmeticUtil.doAdd(dataMap.get("一、收入"),
                        amt));
                if (flag) {
                    dtoMap.get("4.汇兑损益").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("4.汇兑损益").getThisTermAmt(), amt));
                    dtoMap.get("一、收入").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("一、收入").getThisTermAmt(), amt));
                }
                else {
                    dtoMap.get("4.汇兑损益").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("4.汇兑损益").getLastTermAmt(), amt));
                    dtoMap.get("一、收入").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("一、收入").getLastTermAmt(), amt));
                }
            }
            else if (StringUtil.equals("6302", dto.getSubjectNo())) {
                // dataMap.put("5.其他收入",dataMap.get("5.其他收入")==null?amt:ArithmeticUtil.doAdd(dataMap.get("5.其他收入"),amt));
                dataMap.put("一、收入",
                    dataMap.get("一、收入") == null ? amt : ArithmeticUtil.doAdd(dataMap.get("一、收入"),
                        amt));
                if (flag) {
                    dtoMap.get("5.其他收入").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("5.其他收入").getThisTermAmt(), amt));
                    dtoMap.get("一、收入").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("一、收入").getThisTermAmt(), amt));
                }
                else {
                    dtoMap.get("5.其他收入").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("5.其他收入").getLastTermAmt(), amt));
                    dtoMap.get("一、收入").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("一、收入").getLastTermAmt(), amt));
                }
            }
            else if (StringUtil.equals("6411", dto.getSubjectNo())) {
                // dataMap.put("1.利息支出",dataMap.get("1.利息支出")==null?amt:ArithmeticUtil.doAdd(dataMap.get("1.利息支出"),amt));
                dataMap.put("二、支出",
                    dataMap.get("二、支出") == null ? payAmt : ArithmeticUtil.doAdd(dataMap.get("二、支出"),
                        payAmt));
                if (flag) {
                    dtoMap.get("1.利息支出").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("1.利息支出").getThisTermAmt(), payAmt));
                    dtoMap.get("二、支出").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("二、支出").getThisTermAmt(), payAmt));
                }
                else {
                    dtoMap.get("1.利息支出").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("1.利息支出").getLastTermAmt(), payAmt));
                    dtoMap.get("二、支出").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("二、支出").getLastTermAmt(), payAmt));
                }
            }
            else if (StringUtil.equals("6406", dto.getSubjectNo())) {
                // dataMap.put("销售服务费",dataMap.get("销售服务费")==null?amt:ArithmeticUtil.doAdd(dataMap.get("销售服务费"),amt));
                dataMap.put("2.费用",
                    dataMap.get("2.费用") == null ? payAmt : ArithmeticUtil.doAdd(dataMap.get("2.费用"),
                        payAmt));
                dataMap.put("二、支出",
                    dataMap.get("二、支出") == null ? payAmt : ArithmeticUtil.doAdd(dataMap.get("二、支出"),
                        payAmt));
                if (flag) {
                    dtoMap.get("销售服务费").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("销售服务费").getThisTermAmt(), payAmt));
                    dtoMap.get("2.费用").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("2.费用").getThisTermAmt(), payAmt));
                    dtoMap.get("二、支出").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("二、支出").getThisTermAmt(), payAmt));
                }
                else {
                    dtoMap.get("销售服务费").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("销售服务费").getLastTermAmt(), payAmt));
                    dtoMap.get("2.费用").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("2.费用").getLastTermAmt(), payAmt));
                    dtoMap.get("二、支出").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("二、支出").getLastTermAmt(), payAmt));
                }
            }
            else if (StringUtil.equals("6407", dto.getSubjectNo())) {
                // dataMap.put("交易费用",dataMap.get("交易费用")==null?payAmt:ArithmeticUtil.doAdd(dataMap.get("交易费用"),payAmt));
                dataMap.put("2.费用", dataMap.get("2.费用") == null ? payAmt : ArithmeticUtil.doAdd(
                    dataMap.get("2.费用"), payAmt));
                dataMap.put("二、支出", dataMap.get("二、支出") == null ? payAmt : ArithmeticUtil.doAdd(
                    dataMap.get("二、支出"), payAmt));
                if (flag) {
                    dtoMap.get("交易费用").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("交易费用").getThisTermAmt(), payAmt));
                    dtoMap.get("2.费用").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("2.费用").getThisTermAmt(), payAmt));
                    dtoMap.get("二、支出").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("二、支出").getThisTermAmt(), payAmt));
                }
                else {
                    dtoMap.get("交易费用").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("交易费用").getLastTermAmt(), payAmt));
                    dtoMap.get("2.费用").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("2.费用").getLastTermAmt(), payAmt));
                    dtoMap.get("二、支出").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("二、支出").getLastTermAmt(), payAmt));
                }
            }
            else if (StringUtil.equals("6403", dto.getSubjectNo())) {
                // dataMap.put("管理人报酬",dataMap.get("管理人报酬")==null?payAmt:ArithmeticUtil.doAdd(dataMap.get("管理人报酬"),payAmt));
                dataMap.put("2.费用", dataMap.get("2.费用") == null ? payAmt : ArithmeticUtil.doAdd(
                    dataMap.get("2.费用"), payAmt));
                dataMap.put("二、支出", dataMap.get("二、支出") == null ? payAmt : ArithmeticUtil.doAdd(
                    dataMap.get("二、支出"), payAmt));
                if (flag) {
                    dtoMap.get("管理人报酬").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("管理人报酬").getThisTermAmt(), payAmt));
                    dtoMap.get("2.费用").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("2.费用").getThisTermAmt(), payAmt));
                    dtoMap.get("二、支出").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("二、支出").getThisTermAmt(), payAmt));
                }
                else {
                    dtoMap.get("管理人报酬").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("管理人报酬").getLastTermAmt(), payAmt));
                    dtoMap.get("2.费用").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("2.费用").getLastTermAmt(), payAmt));
                    dtoMap.get("二、支出").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("二、支出").getLastTermAmt(), payAmt));
                }
            }
            else if (StringUtil.equals("6605", dto.getSubjectNo())) {
                // dataMap.put("其他费用",dataMap.get("其他费用")==null?payAmt:ArithmeticUtil.doAdd(dataMap.get("其他费用"),payAmt));
                dataMap.put("2.费用", dataMap.get("2.费用") == null ? payAmt : ArithmeticUtil.doAdd(
                    dataMap.get("2.费用"), payAmt));
                dataMap.put("二、支出", dataMap.get("二、支出") == null ? payAmt : ArithmeticUtil.doAdd(
                    dataMap.get("二、支出"), payAmt));
                if (flag) {
                    dtoMap.get("其他费用").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("其他费用").getThisTermAmt(), payAmt));
                    dtoMap.get("2.费用").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("2.费用").getThisTermAmt(), payAmt));
                    dtoMap.get("二、支出").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("二、支出").getThisTermAmt(), payAmt));
                }
                else {
                    dtoMap.get("其他费用").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("其他费用").getLastTermAmt(), payAmt));
                    dtoMap.get("2.费用").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("2.费用").getLastTermAmt(), payAmt));
                    dtoMap.get("二、支出").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("二、支出").getLastTermAmt(), payAmt));
                }
            }
            else if (StringUtil.equals("6404", dto.getSubjectNo())) {
                // dataMap.put("托管费",dataMap.get("托管费")==null?payAmt:ArithmeticUtil.doAdd(dataMap.get("托管费"),payAmt));
                dataMap.put("2.费用", dataMap.get("2.费用") == null ? payAmt : ArithmeticUtil.doAdd(
                    dataMap.get("2.费用"), payAmt));
                dataMap.put("二、支出", dataMap.get("二、支出") == null ? payAmt : ArithmeticUtil.doAdd(
                    dataMap.get("二、支出"), payAmt));
                if (flag) {
                    dtoMap.get("托管费").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("托管费").getThisTermAmt(), payAmt));
                    dtoMap.get("2.费用").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("2.费用").getThisTermAmt(), payAmt));
                    dtoMap.get("二、支出").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("二、支出").getThisTermAmt(), payAmt));
                }
                else {
                    dtoMap.get("托管费").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("托管费").getLastTermAmt(), payAmt));
                    dtoMap.get("2.费用").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("2.费用").getLastTermAmt(), payAmt));
                    dtoMap.get("二、支出").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("二、支出").getLastTermAmt(), payAmt));
                }
            }
            else if (StringUtil.equals("6409", dto.getSubjectNo())) {
                dataMap.put("代销费",
                    dataMap.get("代销费") == null ? payAmt : ArithmeticUtil.doAdd(dataMap.get("代销费"),
                        payAmt));
                dataMap.put("2.费用", dataMap.get("2.费用") == null ? payAmt : ArithmeticUtil.doAdd(
                    dataMap.get("2.费用"), payAmt));
                dataMap.put("二、支出", dataMap.get("二、支出") == null ? payAmt : ArithmeticUtil.doAdd(
                    dataMap.get("二、支出"), payAmt));
                if (flag) {
                    dtoMap.get("代销费").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("代销费").getThisTermAmt(), payAmt));
                    dtoMap.get("2.费用").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("2.费用").getThisTermAmt(), payAmt));
                    dtoMap.get("二、支出").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("二、支出").getThisTermAmt(), payAmt));
                }
                else {
                    dtoMap.get("代销费").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("代销费").getLastTermAmt(), payAmt));
                    dtoMap.get("2.费用").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("2.费用").getLastTermAmt(), payAmt));
                    dtoMap.get("二、支出").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("二、支出").getLastTermAmt(), payAmt));
                }
            }
            else if (StringUtil.equals("6410", dto.getSubjectNo())) {
                // dataMap.put("外包服务费",dataMap.get("外包服务费")==null?payAmt:ArithmeticUtil.doAdd(dataMap.get("外包服务费"),payAmt));
                dataMap.put("2.费用", dataMap.get("2.费用") == null ? payAmt : ArithmeticUtil.doAdd(
                    dataMap.get("2.费用"), payAmt));
                dataMap.put("二、支出", dataMap.get("二、支出") == null ? payAmt : ArithmeticUtil.doAdd(
                    dataMap.get("二、支出"), payAmt));
                if (flag) {
                    dtoMap.get("外包服务费").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("外包服务费").getThisTermAmt(), payAmt));
                    dtoMap.get("2.费用").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("2.费用").getThisTermAmt(), payAmt));
                    dtoMap.get("二、支出").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("二、支出").getThisTermAmt(), payAmt));
                }
                else {
                    dtoMap.get("外包服务费").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("外包服务费").getLastTermAmt(), payAmt));
                    dtoMap.get("2.费用").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("2.费用").getLastTermAmt(), payAmt));
                    dtoMap.get("二、支出").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("二、支出").getLastTermAmt(), payAmt));
                }
            }
            else if (StringUtil.equals("6413", dto.getSubjectNo())) {
                // dataMap.put("外包服务费",dataMap.get("外包服务费")==null?payAmt:ArithmeticUtil.doAdd(dataMap.get("外包服务费"),payAmt));
                dataMap.put("2.费用", dataMap.get("2.费用") == null ? payAmt : ArithmeticUtil.doAdd(
                    dataMap.get("2.费用"), payAmt));
                dataMap.put("二、支出", dataMap.get("二、支出") == null ? payAmt : ArithmeticUtil.doAdd(
                    dataMap.get("二、支出"), payAmt));
                if (flag) {
                    dtoMap.get("推介费").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("推介费").getThisTermAmt(), payAmt));
                    dtoMap.get("2.费用").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("2.费用").getThisTermAmt(), payAmt));
                    dtoMap.get("二、支出").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("二、支出").getThisTermAmt(), payAmt));
                }
                else {
                    dtoMap.get("推介费").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("推介费").getLastTermAmt(), payAmt));
                    dtoMap.get("2.费用").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("2.费用").getLastTermAmt(), payAmt));
                    dtoMap.get("二、支出").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("二、支出").getLastTermAmt(), payAmt));
                }
            }
            else if (dto.getSubjectNo().equals("6702")) {
                // dataMap.put("3.其他支出",dataMap.get("3.其他支出")==null?payAmt:ArithmeticUtil.doAdd(dataMap.get("3.其他支出"),payAmt));
                dataMap.put("二、支出", dataMap.get("二、支出") == null ? payAmt : ArithmeticUtil.doAdd(
                    dataMap.get("二、支出"), payAmt));
                if (flag) {
                    dtoMap.get("3.其他支出").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("3.其他支出").getThisTermAmt(), payAmt));
                    dtoMap.get("二、支出").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("二、支出").getThisTermAmt(), payAmt));
                }
                else {
                    dtoMap.get("3.其他支出").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("3.其他支出").getLastTermAmt(), payAmt));
                    dtoMap.get("二、支出").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("二、支出").getLastTermAmt(), payAmt));
                }
            }
            else if (dto.getSubjectNo().equals("6408")) {
                // dataMap.put("4.增值税及附加",dataMap.get("4.增值税及附加")==null?payAmt:ArithmeticUtil.doAdd(dataMap.get("4.增值税及附加"),payAmt));
                dataMap.put("二、支出", dataMap.get("二、支出") == null ? payAmt : ArithmeticUtil.doAdd(
                    dataMap.get("二、支出"), payAmt));
                if (flag) {
                    dtoMap.get("4.增值税及附加").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("4.增值税及附加").getThisTermAmt(), payAmt));
                    dtoMap.get("二、支出").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("二、支出").getThisTermAmt(), payAmt));
                }
                else {
                    dtoMap.get("4.增值税及附加").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("4.增值税及附加").getLastTermAmt(), payAmt));
                    dtoMap.get("二、支出").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("二、支出").getLastTermAmt(), payAmt));
                }
            }
            else if (dto.getSubjectNo().equals("6412")) {
                // dataMap.put("5.资产减值损失",dataMap.get("5.资产减值损失")==null?payAmt:ArithmeticUtil.doAdd(dataMap.get("5.资产减值损失"),payAmt));
                dataMap.put("二、支出", dataMap.get("二、支出") == null ? payAmt : ArithmeticUtil.doAdd(
                    dataMap.get("二、支出"), payAmt));
                if (flag) {
                    dtoMap.get("5.资产减值损失").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("5.资产减值损失").getThisTermAmt(), payAmt));
                    dtoMap.get("二、支出").setThisTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("二、支出").getThisTermAmt(), payAmt));
                }
                else {
                    dtoMap.get("5.资产减值损失").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("5.资产减值损失").getLastTermAmt(), payAmt));
                    dtoMap.get("二、支出").setLastTermAmt(
                        ArithmeticUtil.doAdd(dtoMap.get("二、支出").getLastTermAmt(), payAmt));
                }
            }
        }
        dataMap.put("三、利润总额",
            ArithmeticUtil.subtract(
                dataMap.get("一、收入") == null ? BigDecimal.ZERO : dataMap.get("一、收入"),
                dataMap.get("二、支出") == null ? BigDecimal.ZERO : dataMap.get("二、支出")));
        // 设置当期金额数据
        if (flag) {
            dtoMap.get("三、利润总额").setThisTermAmt(ArithmeticUtil.subtract(
                dtoMap.get("一、收入").getThisTermAmt(), dtoMap.get("二、支出").getThisTermAmt()));
        }
        // 设置上期金额数据
        else {
            dtoMap.get("三、利润总额").setLastTermAmt(ArithmeticUtil.subtract(
                dtoMap.get("一、收入").getLastTermAmt(), dtoMap.get("二、支出").getLastTermAmt()));
        }
    }

    /**
     * 通过账套获取对应资产名称和资产代码
     * 
     * @param String
     * @return
     * @see
     * @since 1.0
     */
    public FinProduct getFinProductByBookset(String booksetId) {
        BokBookset bokBookset = bokBooksetMapper.selectByPrimaryKey(booksetId);
        FinProductKey key = new FinProductKey();
        // 账套的核算主体代码关联金融产品标的表的金融产品代码
        key.setFinprodId(bokBookset.getAcctSubjectId());
        // 金融产品类型=理财产品
        key.setFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
        return finProductMapper.selectByPrimaryKey(key);
    }

    /**
     * 初始化数据
     * 
     * @param dtoMap
     * @return
     * @see
     * @since 1.0
     */
    public void initData(Map<String, DisValProfitQuery001Dto> dtoMap) {
        DisValProfitQuery001Dto newDto = new DisValProfitQuery001Dto();
        int sortNum = 1;
        newDto.setSortNum(sortNum);
        newDto.setProject("一、收入");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("一、收入", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("1.利息收入");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("1.利息收入", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("债券利息收入");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("债券利息收入", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("存放款收入");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("存放款收入", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("拆放同业利息收入");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("拆放同业利息收入", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("买入返售利息收入");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("买入返售利息收入", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("优先股投资股息收入");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("优先股投资股息收入", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("计划类资产利息收入");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("计划类资产利息收入", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("活期存款利息收入");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("活期存款利息收入", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("债券借贷借出费收入");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("债券借贷借出费收入", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("利息收入增值税备抵");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("利息收入增值税备抵", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("2.投资收益");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("2.投资收益", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("股票投资收益");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("股票投资收益", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("债券投资收益");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("债券投资收益", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("基金投资收益");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("基金投资收益", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("基金红利收入");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("基金红利收入", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("计划类投资收益");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("计划类投资收益", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("计划类投资红利收入");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("计划类投资红利收入", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("以公允价值计量且其变动计入当期损益的金融负债价差收益");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("以公允价值计量且其变动计入当期损益的金融负债价差收益", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("投资收益增值税备抵");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("投资收益增值税备抵", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("红利收入增值税备抵");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("红利收入增值税备抵", newDto);

        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("衍生工具收益");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("衍生工具收益", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("3.公允价值变动损益");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("3.公允价值变动损益", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("4.汇兑损益");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("4.汇兑损益", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("5.其他收入");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("5.其他收入", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("二、支出");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("二、支出", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("1.利息支出");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("1.利息支出", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("2.费用");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("2.费用", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("销售服务费");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("销售服务费", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("交易费用");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("交易费用", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("管理人报酬");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("管理人报酬", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("其他费用");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("其他费用", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("托管费");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("托管费", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("代销费");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("代销费", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("外包服务费");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("外包服务费", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("推介费");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("推介费", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("3.其他支出");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("3.其他支出", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("4.增值税及附加");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("4.增值税及附加", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("5.资产减值损失");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("5.资产减值损失", newDto);
        sortNum++ ;
        newDto = new DisValProfitQuery001Dto();
        newDto.setSortNum(sortNum);
        newDto.setProject("三、利润总额");
        newDto.setThisTermAmt(BigDecimal.ZERO);
        newDto.setLastTermAmt(BigDecimal.ZERO);
        dtoMap.put("三、利润总额", newDto);
    }

    /**
     * 分页查询 并 添加样式
     * @param parameters
     * @return
     * @throws ParseException 
     * @see
     * @since 1.0
     */
    public List<DisValProfitQuery001Dto> findPageAll(Map<String, Object> parameters) throws ParseException {
        List<DisValProfitQuery001Dto> dtoList = this.selectListAll(parameters);
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
    public List<DisValProfitQuery001Dto> selectListAll(Map<String, Object> pageParam)
        throws ParseException {
    	// 查询表样
    	List<DisProfitCalculationDto> profitCalculationDtoList = valProfitQuery001Mapper.selectProfitCalculationDtoList();
        Map<String, DisProfitCalculationDto> profitCalculationMap = profitCalculationDtoList.stream().collect(Collectors.toMap(DisProfitCalculationDto::getProjCode, Function.identity()));
        
        changeMapData(pageParam);
        Map<String, DisValProfitQuery001Dto> dtoMap = initReportElement(profitCalculationMap);
        // initData(dtoMap);
        pageParam.put("vdate", pageParam.get("thisTermVdate"));
        pageParam.put("mdate", pageParam.get("thisTermMdate"));
        List<BokBalance> thisTermData = valProfitQuery001Mapper.selectAllBalanceAll(pageParam);
//        formatData(thisTermData, true, dtoMap);
        pageParam.put("vdate", pageParam.get("lastTermVdate"));
        pageParam.put("mdate", pageParam.get("lastTermMdate"));
        List<BokBalance> lastTermData = valProfitQuery001Mapper.selectAllBalanceAll(pageParam);
//        formatData(lastTermData, false, dtoMap);
        
        // 计算本期
        calValue(thisTermData, profitCalculationMap, true);
        // 计算上期
        calValue(lastTermData, profitCalculationMap, false);
        // 画面赋值
        setValue(profitCalculationMap, dtoMap);
        
        List<DisValProfitQuery001Dto> list = new ArrayList<DisValProfitQuery001Dto>();
        list.addAll(dtoMap.values());
        list.sort(new Comparator<DisValProfitQuery001Dto>() {

            @Override
            public int compare(DisValProfitQuery001Dto arg0, DisValProfitQuery001Dto arg1) {
                if (arg0.getSortNum() < arg1.getSortNum()) {
                    return -1;
                }
                return 1;
            }
        });
        
        return list;
    }

    /**
     * 初始化报表项
     * 
     * @param profitCalculationMap
     * @return
     */
    public Map<String, DisValProfitQuery001Dto> initReportElement(Map<String, DisProfitCalculationDto> profitCalculationMap) {
    	Map<String, DisValProfitQuery001Dto> retMap = new HashMap<String, DisValProfitQuery001Dto>();
    	DisValProfitQuery001Dto DisValProfitQuery001Dto = null; 
		for (String key : profitCalculationMap.keySet()) {
			DisProfitCalculationDto configDto = profitCalculationMap.get(key);
			// 是报表展示项
			if (DictCodeList.是否_是.getValue().equals(configDto.getShowFlg())) {
				if (!retMap.containsKey(configDto.getProjName())) {
					DisValProfitQuery001Dto = new DisValProfitQuery001Dto();
					DisValProfitQuery001Dto.setSortNum(configDto.getShowOrder());
					DisValProfitQuery001Dto.setProject(configDto.getProjName());
					DisValProfitQuery001Dto.setThisTermAmt(BigDecimal.ZERO);
					DisValProfitQuery001Dto.setLastTermAmt(BigDecimal.ZERO);
					retMap.put(configDto.getProjName(), DisValProfitQuery001Dto);
				}
			}
		}
		return retMap;
	}

	/**
     * 将计算结果复制到返回画面结果集
     * 
     * @param profitCalculationMap
     * @param dtoMap
     */
    public void setValue(Map<String, DisProfitCalculationDto> profitCalculationMap,
			Map<String, DisValProfitQuery001Dto> dtoMap) {
		for (String key : profitCalculationMap.keySet()) {
			if (dtoMap.containsKey(profitCalculationMap.get(key).getProjName())) {
				dtoMap.get(profitCalculationMap.get(key).getProjName()).setThisTermAmt(profitCalculationMap.get(key).getThisTermAmt());
				dtoMap.get(profitCalculationMap.get(key).getProjName()).setLastTermAmt(profitCalculationMap.get(key).getLastTermAmt());
			}
		}
	}

	/**
     * 计算利润表各项目值
     * 
     * @param termData
     * @param profitCalculationMap
     * @param thisTermFlag
     */
	public void calValue(List<BokBalance> termData, Map<String, DisProfitCalculationDto> profitCalculationMap, boolean thisTermFlag) {
		// 汇总项key集合
		List<String> keyList = new ArrayList<String>();
		// 计算明细项目
		for (String key : profitCalculationMap.keySet()) {
			DisProfitCalculationDto dto = profitCalculationMap.get(key);
			if (1== dto.getProjLevel()) {
				// 汇总项，暂不计算。将key保存到汇总项key集合待用
				keyList.add(dto.getProjCode());
				continue;
			}
			// 计算当期值
			BigDecimal termAmt = calcTermAmt(termData, dto);
			if (thisTermFlag) {
				profitCalculationMap.get(key).setThisTermAmt(profitCalculationMap.get(key).getThisTermAmt().add(termAmt));
			} else {
				profitCalculationMap.get(key).setLastTermAmt(profitCalculationMap.get(key).getLastTermAmt().add(termAmt));
			}
		}
		// 计算汇总项目
		Collections.sort(keyList, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o2.length() - o1.length();
			}
			
		});
		for (String key : keyList) {
			// 计算汇总项数值
			calcTotalTermAmt(profitCalculationMap, key);
		}
		
	}

	/**
	 * 根据报表项目配置信息，计算汇总项目值，目前计算公式只支持加减，且计算公式不能带左右括号
	 * @param termData
	 * @param dto
	 * @return
	 */
	private void calcTotalTermAmt(Map<String, DisProfitCalculationDto> profitCalculationMap, String parentKey) {
		DisProfitCalculationDto dto = profitCalculationMap.get(parentKey);
		BigDecimal thisTermAmt = BigDecimal.ZERO;
		BigDecimal lastTermAmt = BigDecimal.ZERO;
		try {
			// 汇总项计算公式
			String formula = dto.getFormula();
			// 参与运算的子项key
			StringBuilder childKeyBuilder = new StringBuilder();
			// 参与计算的运算符
			StringBuilder punctBuilder = new StringBuilder();
			char[] arr = formula.toCharArray();
			for (int i = 0; i < arr.length; i++) {
				if ('+' == arr[i] || '-' == arr[i]) {
					childKeyBuilder.append('@');
					punctBuilder.append(arr[i]);
				} else {
					childKeyBuilder.append(arr[i]);
				}
			}
			
	        char[] punctArr = punctBuilder.toString().toCharArray();
	        String[] childKeyArr = childKeyBuilder.toString().split("@");
	        thisTermAmt = profitCalculationMap.get(childKeyArr[0]).getThisTermAmt();
	        lastTermAmt = profitCalculationMap.get(childKeyArr[0]).getLastTermAmt();
	        for (int i = 0; i < punctArr.length; i ++) {
	        	if ('+' == punctArr[i]) {
	        		thisTermAmt = thisTermAmt.add(profitCalculationMap.get(childKeyArr[i + 1]).getThisTermAmt());
	        		lastTermAmt = lastTermAmt.add(profitCalculationMap.get(childKeyArr[i + 1]).getLastTermAmt());
	        	}
	        	if ('-' == punctArr[i]) {
	        		thisTermAmt = thisTermAmt.subtract(profitCalculationMap.get(childKeyArr[i + 1]).getThisTermAmt());
	        		lastTermAmt = lastTermAmt.subtract(profitCalculationMap.get(childKeyArr[i + 1]).getLastTermAmt());
	        	}
	        }
			profitCalculationMap.get(parentKey).setThisTermAmt(thisTermAmt);
			profitCalculationMap.get(parentKey).setLastTermAmt(lastTermAmt);
		} catch (Exception e) {
		}
	}

	/**
	 * 根据报表项目配置信息，计算项目值，目前计算公式只支持加减，且计算公式不能带左右括号
	 * @param termData
	 * @param dto
	 * @return
	 */
	private BigDecimal calcTermAmt(List<BokBalance> termData, DisProfitCalculationDto dto) {
		// 计算必要信息缺失，直接返回0
		if (termData == null || dto == null || termData.isEmpty() || 
				StringUtils.isEmpty(dto.getSubjectNo()) || StringUtils.isEmpty(dto.getFormula())) {
			return BigDecimal.ZERO;
		}
		BigDecimal termAmt = BigDecimal.ZERO;
		// 标点符号
		String regex = "\\p{Punct}";
		// 计算公式
		String formula = dto.getFormula();
		// 参与计算的字段
		String[] fieldArr = formula.split(regex);
		// 参与计算的运算符
		String punct = "";
		Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(formula);
        while(m.find()){  
        	punct = punct + m.group(0);
        }
        char[] punctArr = punct.toCharArray();
        try {
			for (BokBalance balance : termData) {
				if (balance.getSubjectNo().equals(dto.getSubjectNo())) {
					Method method = balance.getClass().getMethod("get" + fieldArr[0]);
			        Object obj = method.invoke(balance);
			        termAmt = termAmt.add((BigDecimal)(obj == null ? BigDecimal.ZERO : obj));
			        for (int i = 0; i < punctArr.length; i ++) {
			        	Method method1 = balance.getClass().getMethod("get" + fieldArr[i + 1]);
			            Object obj1 = method1.invoke(balance);
			            if ('+' == punctArr[i]) {
			            	termAmt = termAmt.add((BigDecimal)(obj1 == null ? BigDecimal.ZERO : obj1));
			            }
			            if ('-' == punctArr[i]) {
			            	termAmt = termAmt.subtract((BigDecimal)(obj1 == null ? BigDecimal.ZERO : obj1));
			            }
			        }
				}
			}
        } catch (Exception e) {
		}
		return termAmt;
	}
	
}
