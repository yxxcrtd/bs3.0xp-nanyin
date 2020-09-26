///*
// * 文件名：ValExcelImpHandle001Service.java
// * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
// * 描述：理财资管管理系统V2.0
// * 修改人：weipeng
// * 修改时间：2018年5月9日
// * 修改内容：新建
// * 系统名称：理财资管管理系统V2.0
// */
//
//package com.joyintech.tams.valuation.system.excelimphandle.service;
//
//import java.io.UnsupportedEncodingException;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.math.BigDecimal;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.dbcp.BasicDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.joyintech.framework.base.BaseEntity;
//import com.joyintech.tams.constant.DictCodeList;
//import com.joyintech.framework.export.CommonExportService;
//import com.joyintech.framework.security.SecurityUtil;
//import com.joyintech.framework.security.User;
//import com.joyintech.framework.util.DateUtil;
//import com.joyintech.framework.util.ExcelUtil;
//import com.joyintech.framework.util.FileNameUtil;
//import com.joyintech.framework.util.SpringContextUtil;
//import com.joyintech.framework.util.StringUtil;
//import com.joyintech.tams.valuation.common.service.BaseCommonService;
//import com.joyintech.tams.valuation.common.constants.DataIndexSequenceType;
//import com.joyintech.tams.valuation.common.constants.GlobalConstant;
//import com.joyintech.tams.valuation.system.excelimphandle.dao.ValExcelImpHandle001Mapper;
//import com.joyintech.tams.valuation.system.excelimphandle.dto.ColumnNullableEnum;
//import com.joyintech.tams.valuation.system.excelimphandle.dto.ColumnTypeNameEnum;
//import com.joyintech.tams.valuation.system.excelimphandle.dto.ImportFlgEnum;
//import com.joyintech.tams.valuation.system.excelimphandle.dto.ValExcelImpFileInfoDto;
//import com.joyintech.tams.valuation.system.excelimphandle.dto.ValExcelImpFileSheetInfoDto;
//import com.joyintech.tams.valuation.system.excelimphandle.dto.ValExcelImpHandle001Dto;
//import com.joyintech.tams.valuation.system.excelimphandle.dto.ValExcelImpResultDto;
//import com.joyintech.tams.valuation.system.excelimphandle.dto.ValExcelRuleCustomSqlDto;
//import com.joyintech.tams.valuation.system.excelimphandle.dto.ValExcelRuleDetailDto;
//import com.joyintech.tams.valuation.system.excelimphandle.dto.ValExcelRuleDictcodeDetailDto;
//import com.joyintech.tams.valuation.system.excelimphandle.dto.ValExcelRuleDictcodeDto;
//import com.joyintech.tams.valuation.system.excelimphandle.dto.ValExcelRuleDto;
//import com.joyintech.tams.valuation.system.excelimphandle.dto.ValExcelRuleMasterDataDto;
//import com.joyintech.tams.valuation.system.excelimphandle.dto.ValTableColumnDefineInfoDto;
//import com.joyintech.tools.collectors.JoyinCollectors;
//
///**
// * 导入管理Service
// * 
// * @author weipeng
// * @version 1.0 2018年5月9日
// * @see ValExcelImpHandle001Service
// * @since 1.0
// */
//@Service
//public class ValExcelImpHandle001Service extends CommonExportService<ValExcelImpHandle001Dto> {
//
//    /** 自定义SQL参数前缀 */
//    private static final String CUSSQL_PARA_PREFIX = "#\\{";
//
//    /** 自定义SQL参数后缀 */
//    private static final String CUSSQL_PARA_SUFFIX = "\\}";
//
//    /** 自定义SQL参数替换校验正则表达式 */
//    private static final String CUSSQL_PARA_REG = "#\\{[A-Z]+\\}";
//
//    /**
//     * ValExcelImpHandle001Mapper
//     */
//    @Autowired
//    private ValExcelImpHandle001Mapper valExcelImpHandle001Mapper;
//
//    /**
//     * BaseCommonService
//     */
//    @Autowired
//    private BaseCommonService baseCommonService;
//
//    /**
//     * 数据库jdbcDiver
//     */
//    @Value("${spring.datasource.driverClassName}")
//    private String jdbcDiver;
//
//    /**
//     * 数据库jdbcUrl
//     */
//    @Value("${spring.datasource.url}")
//    private String jdbcUrl;
//
//    /**
//     * 数据库连接 jdbcUser
//     */
//    @Value("${spring.datasource.username}")
//    private String jdbcUser;
//
//    /**
//     * 数据库连接 jdbcPassword
//     */
//    @Value("${spring.datasource.password}")
//    private String jdbcPassword;
//
//    /** 撤销标志 */
//    private boolean revokeFlag = false;
//
//    /**
//     * 文件读取
//     *
//     * @param request request
//     * @return 导入文件信息
//     * @throws Exception 异常
//     * @see
//     * @since 1.0
//     */
//    @SuppressWarnings("unchecked")
//    public ValExcelImpFileInfoDto readFile(HttpServletRequest request)
//        throws Exception {
//
//        ValExcelImpFileInfoDto fileInfoDto = new ValExcelImpFileInfoDto();
//
//        // 文件名
//        String fileName = request.getParameter("fileName");
//        fileInfoDto.setImpFileName(fileName);
//
//        // 导入文件时间戳
//        String nowTime = new SimpleDateFormat(DateUtil.DATETIME_FORMAT_WITH_SSS_YEAR1).format(
//            Calendar.getInstance().getTime());
//        fileInfoDto.setImpFileTime(nowTime);
//        ExcelUtil excelUtil = ExcelUtil.build(FileNameUtil.getUploadFilePath(fileName), null);
//        List<String> sheetNames = excelUtil.getSheetNameList();
//        if (sheetNames != null) {
//            List<ValExcelImpFileSheetInfoDto> sheetInfoList = new ArrayList<ValExcelImpFileSheetInfoDto>();
//            for (int i = 0; i < sheetNames.size(); i++ ) {
//                ValExcelImpFileSheetInfoDto sheetInfoDto = new ValExcelImpFileSheetInfoDto();
//
//                // 导入文件sheet序号
//                sheetInfoDto.setImpFileSheetNo(i);
//
//                // sheet名
//                String fileSheetName = sheetNames.get(i);
//                sheetInfoDto.setImpFileSheetName(fileSheetName);
//
//                try {
//                    // sheet数据
//                    List<List<String>> sheetDataList = excelUtil.readToList(i, 1, 0);
//                    sheetInfoDto.setSheetDataList(sheetDataList);
//                }
//                catch (Exception e) {
//                }
//
//                sheetInfoList.add(sheetInfoDto);
//            }
//
//            // excel导入文件中所有sheet信息
//            fileInfoDto.setSheetInfoList(sheetInfoList);
//        }
//
//        // 将该文件信息保存到session
//        List<ValExcelImpFileInfoDto> fileInfoList = new ArrayList<ValExcelImpFileInfoDto>();
//        Object fileInfoListFromSession = request.getSession().getAttribute(
//            GlobalConstant.EXCEL_IMP_HANDLE_FILE_INFO);
//        if (fileInfoListFromSession != null) {
//            fileInfoList = (List<ValExcelImpFileInfoDto>)fileInfoListFromSession;
//        }
//        fileInfoList.add(fileInfoDto);
//        request.getSession().setAttribute(GlobalConstant.EXCEL_IMP_HANDLE_FILE_INFO, fileInfoList);
//
//        return fileInfoDto;
//    }
//
//    /**
//     * excel导入具体处理
//     *
//     * @param request request
//     * @param impFileInfoFromPage 导入文件信息(画面保存)
//     * @return 处理结果
//     * @throws Exception 异常
//     * @see
//     * @since 1.0
//     */
//    @SuppressWarnings("unchecked")
//    public ValExcelImpResultDto impHandle(HttpServletRequest request,
//                                          ValExcelImpFileInfoDto impFileInfoFromPage)
//        throws Exception {
//
//        ValExcelImpResultDto totalResultDto = new ValExcelImpResultDto();
//
//        // 从session中获取保存的上传文件信息
//        Object fileInfoListFromSession = request.getSession().getAttribute(
//            GlobalConstant.EXCEL_IMP_HANDLE_FILE_INFO);
//        if (fileInfoListFromSession == null
//            || StringUtil.isEmptyList(impFileInfoFromPage.getImpFileInfoList())) {
//            totalResultDto.setImpResultMsg(this.getMessage("VAL_EXCELIMP_E_0001"));
//            return totalResultDto;
//        }
//
//        // 画面保存的文件时间戳map
//        Map<String, String> fileTimeMap = impFileInfoFromPage.getImpFileInfoList().stream().collect(
//            JoyinCollectors.toMap(x -> x.getImpFileTime(), x -> x.getImpFileTime()));
//
//        List<ValExcelImpFileInfoDto> fileInfoList = (List<ValExcelImpFileInfoDto>)fileInfoListFromSession;
//        List<ValExcelImpResultDto> totalResultList = new ArrayList<ValExcelImpResultDto>();
//        for (ValExcelImpFileInfoDto excelImpFileInfoDto : fileInfoList) {
//            ValExcelImpResultDto resultDto = this.handleOneFileImp(excelImpFileInfoDto,
//                fileTimeMap);
//
//            if (resultDto != null) {
//                totalResultList.add(resultDto);
//            }
//        }
//        totalResultDto.setResultList(totalResultList);
//        // 清除session中保留的上传文件信息
//        request.getSession().removeAttribute(GlobalConstant.EXCEL_IMP_HANDLE_FILE_INFO);
//        return totalResultDto;
//    }
//
//    /**
//     * 处理单个文件的导入处理
//     *
//     * @param fileInfoDto 单个文件信息
//     * @param fileTimeMap 画面保存的文件时间戳map
//     * @return 处理结果
//     * @throws Exception 异常
//     * @see
//     * @since 1.0
//     */
//    private ValExcelImpResultDto handleOneFileImp(ValExcelImpFileInfoDto fileInfoDto,
//                                                  Map<String, String> fileTimeMap)
//        throws Exception {
//        if (!fileTimeMap.containsKey(fileInfoDto.getImpFileTime())) {
//            return null;
//        }
//
//        ValExcelImpResultDto resultDto = new ValExcelImpResultDto();
//        resultDto.setImpFileName(fileInfoDto.getImpFileName());
//
//        // 根据文件名及sheet名获取excel映射规则
//        /* List<String> tempMsgList = */this.getExcelRule(fileInfoDto);
//        // if (StringUtil.isNotEmptyList(tempMsgList)) {
//        // resultDto.setImportFlag(ImportFlgEnum.导入失败.getName());
//        // resultDto.setImpResultMsg(
//        // StringUtil.joinString(tempMsgList, GlobalConstant.HTML_NEWLINE));
//        // return resultDto;
//        // }
//
//        // 设置导入数据表
//        List<String> impTargetTableNameList = new ArrayList<String>();
//        for (ValExcelImpFileSheetInfoDto sheetInfoDto : fileInfoDto.getSheetInfoList()) {
//            if (sheetInfoDto.getValExcelRuleDto() != null && !impTargetTableNameList.contains(
//                sheetInfoDto.getValExcelRuleDto().getTargetTableName())) {
//                impTargetTableNameList.add(sheetInfoDto.getValExcelRuleDto().getTargetTableName());
//            }
//        }
//        resultDto.setImpTargetTableName(
//            StringUtil.joinString(impTargetTableNameList, GlobalConstant.HTML_NEWLINE));
//
//        // 导入处理
//        int successCount = 0;
//        for (ValExcelImpFileSheetInfoDto sheetInfoDto : fileInfoDto.getSheetInfoList()) {
//            ValExcelImpResultDto sheetResultDto = this.handleOneSheetImp(sheetInfoDto);
//
//            if (StringUtil.equals(ImportFlgEnum.导入成功.getName(), sheetResultDto.getImportFlag())) {
//                successCount++ ;
//            }
//
//            resultDto.addeErorMsgList(sheetInfoDto.getImpFileSheetName(), Arrays.asList(
//                sheetResultDto.getImpResultMsg().split(GlobalConstant.HTML_NEWLINE)));
//        }
//
//        // 导入结果
//        if (successCount == 0) {
//            resultDto.setImportFlag(ImportFlgEnum.导入失败.getName());
//        }
//        else if (successCount == fileInfoDto.getSheetInfoList().size()) {
//            resultDto.setImportFlag(ImportFlgEnum.导入成功.getName());
//        }
//        else {
//            resultDto.setImportFlag(ImportFlgEnum.部分成功.getName());
//        }
//        resultDto.setImpResultMsg(
//            StringUtil.joinString(resultDto.getErrorMsgList(), GlobalConstant.HTML_NEWLINE));
//
//        return resultDto;
//    }
//
////    /**
////     * 处理单文件单sheet中的数据
////     *
////     * @param sheetInfoDto sheet中的数据
////     * @return 处理结果
////     * @throws Exception 异常
////     * @see
////     * @since 1.0
////     */
////    private ValExcelImpResultDto handleOneSheetImp(ValExcelImpFileSheetInfoDto sheetInfoDto)
////        throws Exception {
////        ValExcelImpResultDto resultDto = new ValExcelImpResultDto();
////
////        ValExcelRuleDto valExcelRule = sheetInfoDto.getValExcelRuleDto();
////
////        if (valExcelRule == null) {
////            resultDto.setImportFlag(ImportFlgEnum.导入失败.getName());
////            resultDto.setImpResultMsg(this.getMessage("VAL_EXCELIMP_E_0002"));
////            return resultDto;
////        }
////
////        resultDto.setImpTargetTableName(valExcelRule.getTargetTableName());
////
////        // 不可return过多，加一个checkFlag作为标志
////        boolean checkFlag = true;
////        // 获取excel映射规则明细
////        Map<Long, List<ValExcelRuleDetailDto>> excelRuleDetailMap = this.getExcelRuleDetailData(
////            valExcelRule.getRuleId());
////        if (excelRuleDetailMap == null || excelRuleDetailMap.size() <= 0) {
////            resultDto.setImportFlag(ImportFlgEnum.导入失败.getName());
////            resultDto.setImpResultMsg(this.getMessage("VAL_EXCELIMP_E_0002"));
////            // return resultDto;
////            checkFlag = false;
////        }
////        else {
////            if (StringUtil.isEmptyList(sheetInfoDto.getSheetDataList())) {
////                resultDto.setImportFlag(ImportFlgEnum.导入失败.getName());
////                resultDto.setImpResultMsg(this.getMessage("VAL_EXCELIMP_E_0003"));
////                // return resultDto;
////                checkFlag = false;
////            }
////            else {
////                // 获取目标表主键
////                List<String> tableKeyList = valExcelImpHandle001Mapper.selectTableKey(
////                    valExcelRule.getTargetTable());
////                valExcelRule.setTableKeyList(tableKeyList);
////
////                // 获取目标表的字段定义信息
////                List<ValTableColumnDefineInfoDto> columnDefineInfoList = valExcelImpHandle001Mapper.selectTableColumnDefineInfo(
////                    valExcelRule.getTargetTable());
////                if (StringUtil.isEmptyList(columnDefineInfoList)) {
////                    resultDto.setImportFlag(ImportFlgEnum.导入失败.getName());
////                    resultDto.setImpResultMsg(this.getMessage("VAL_EXCELIMP_E_0005"));
////                    // return resultDto;
////                    checkFlag = false;
////                }
////                else {
////                    valExcelRule.setColumnDefineInfoList(columnDefineInfoList);
////                }
////            }
////
////        }
////        // 查看校验结果标志，若为false，直接return
////        if (!checkFlag) {
////            return resultDto;
////        }
////        // 各业务数据具体实现类
////        ValExcelImpBaseService impBaseService = null;
////        if (StringUtil.isNotBlank(valExcelRule.getImpHandleServiceName())) {
////            try {
////                impBaseService = (ValExcelImpBaseService)SpringContextUtil.getBean(
////                    valExcelRule.getImpHandleServiceName());
////            }
////            catch (Exception e) {
////                e.printStackTrace();
////            }
////        }
////
////        // 根据映射规则解析excel数据
////        List<Map<String, Object>> fileDataList = new ArrayList<Map<String, Object>>();
////        int rowIndex = 0;
////        // 数据检查结果
////        List<String> errorMsgList = new ArrayList<String>();
////        // 需要跳过检查和设值的共通字段
////        Map<String, String> skipColumnMap = this.getSkipColumnMap();
////
////        // 检索数据转换规则
////        // 数据字典规则
////        Map<String, Map<String, String>> ruleDictCodeMap = this.getRuleDictCodeMap(
////            valExcelRule.getRuleId());
////
////        // master规则
////        Map<String, ValExcelRuleMasterDataDto> ruleMasterMap = this.getRuleMasterMap(
////            valExcelRule.getRuleId());
////
////        // 自定义SQL规则
////        Map<String, ValExcelRuleCustomSqlDto> ruleSqlMap = this.getRuleSqlMap(
////            valExcelRule.getRuleId());
////
////        // 表字段：表字段名map
////        Map<String, String> valExcelRuleMap = valExcelRule.getColumnDefineInfoList().stream().collect(
////            JoyinCollectors.toMap(x -> x.getColName(), x -> x.getRemarks()));
////
////        for (List<String> rowData : sheetInfoDto.getSheetDataList()) {
////            rowIndex++ ;
////
////            List<String> rowErrorMsgList = new ArrayList<String>();
////
////            // 单行数据处理
////            Map<String, Object> rowDataMap = new HashMap<String, Object>();
////
////            // 自定义SQL用Map
////            Map<String, String> cusSqlParamMap = new HashMap<String, String>();
////            for (int i = 0; i < rowData.size(); i++ ) {
////                // 自定义规则不为空时，将模板所有字段按列名为key作成map : #{A}='xxx', #{B}='yyy'
////                try {
////                    cusSqlParamMap.put(
////                        CUSSQL_PARA_PREFIX + ExcelUtil.indexToColumnNo(i) + CUSSQL_PARA_SUFFIX,
////                        rowData.get(i));
////                }
////                catch (Exception e) {
////                    e.printStackTrace();
////                }
////            }
////
////            int dataIndex = 0;
////            for (String itemData : rowData) {
////
////                // 取得源列对应的映射明细，一对一或一对多
////                List<ValExcelRuleDetailDto> ruleDetailList = excelRuleDetailMap.get(
////                    Long.valueOf(dataIndex));
////                dataIndex++ ;
////                if (!StringUtil.isEmptyList(ruleDetailList)) {
////                    for (ValExcelRuleDetailDto ruleDetail : ruleDetailList) {
////
////                        // rowDataMap.put(ruleDetail.getTargetColumn(),
////                        // itemData);
////
////                        String targetColumn = ruleDetail.getTargetColumn();
////                        // 数据字典项目处理
////                        String targetValue = null;
////                        try {
////                            targetValue = getConvertDictCode(ruleDictCodeMap, targetColumn,
////                                itemData);
////                        }
////                        catch (Exception e) {
////                            e.printStackTrace();
////                            rowErrorMsgList.add(this.getMessage("VAL_EXCELIMP_E_0014", rowIndex + 1,
////                                ruleDetail.getColumnNo(),
////                                targetColumn + GlobalConstant.OPEN_PARENTHESIS + valExcelRuleMap.get(
////                                    targetColumn) + GlobalConstant.CLOSE_PARENTHESIS));
////                        }
////
////                        // master数据项目处理
////                        try {
////                            targetValue = getMasterValue(ruleMasterMap, targetColumn, targetValue);
////                        }
////                        catch (Exception e) {
////                            e.printStackTrace();
////                            rowErrorMsgList.add(this.getMessage("VAL_EXCELIMP_E_0015", rowIndex + 1,
////                                ruleDetail.getColumnNo(),
////                                targetColumn + GlobalConstant.OPEN_PARENTHESIS + valExcelRuleMap.get(
////                                    targetColumn) + GlobalConstant.CLOSE_PARENTHESIS));
////                        }
////
////                        // 自定义sql项目处理
////                        try {
////                            targetValue = getValueByCusSQL(ruleSqlMap, cusSqlParamMap,
////                                targetColumn, targetValue);
////                        }
////                        catch (Exception e) {
////                            e.printStackTrace();
////                            rowErrorMsgList.add(this.getMessage("VAL_EXCELIMP_E_0016", rowIndex + 1,
////                                ruleDetail.getColumnNo(),
////                                targetColumn + GlobalConstant.OPEN_PARENTHESIS + valExcelRuleMap.get(
////                                    targetColumn) + GlobalConstant.CLOSE_PARENTHESIS));
////                        }
////
////                        // 空字符串转成null,为之后往dto塞值时，空字符串转成BigDecimal或Date等类型时，不会出错
////                        rowDataMap.put(ruleDetail.getTargetColumn(),
////                            StringUtil.stripToNull(targetValue));
////                    }
////                }
////
////            }
////
////            // 共通转换，数据字典，master，自定义SQL转换出错时，无法找到规则对应的值
////            if (!StringUtil.isEmptyList(rowErrorMsgList)) {
////                errorMsgList.addAll(rowErrorMsgList);
////                continue;
////            }
////            // 针对字段做特殊format 自定义func
////            if (impBaseService != null) {
////                impBaseService.formatRowData(rowDataMap);
////            }
////
////            // 对行数据进行数据检查(共通检查)
////            errorMsgList.addAll(
////                this.checkRowData(rowIndex, valExcelRule, rowDataMap, skipColumnMap));
////
////            fileDataList.add(rowDataMap);
////        }
////
////        // 数据检查不过的情况
////        if (!StringUtil.isEmptyList(errorMsgList)) {
////            resultDto.setImportFlag(ImportFlgEnum.导入失败.getName());
////            resultDto.setImpResultMsg(
////                StringUtil.joinString(errorMsgList, GlobalConstant.HTML_NEWLINE));
////        }
////        else {
////            // 获取系统平台日期
////            Date systemDate = baseCommonService.getSysDate();
////
////            List<Object> insertList = new ArrayList<Object>();
////            List<Object> updateList = new ArrayList<Object>();
////            for (Map<String, Object> rowDataMap : fileDataList) {
////
////                boolean insertFlg = true;
////
////                // db录入
////                try {
////                    insertFlg = this.handleRowDataToDb(valExcelRule, rowDataMap, insertList,
////                        updateList, skipColumnMap, systemDate, impBaseService);
////                }
////                catch (Exception e) {
////                    e.printStackTrace();
////                    resultDto.setImportFlag(ImportFlgEnum.导入失败.getName());
////                    resultDto.setImpResultMsg(
////                        this.getMessage("VAL_EXCELIMP_E_0004",
////                            StringUtil.getOrElse(e.getMessage(),
////                                StringUtil.EMPTY) + StringUtil.getOrElse(e.getClass(),
////                                    StringUtil.EMPTY)));
////                    return resultDto;
////                }
////
////                // 主表数据录入后的特殊处理逻辑
////                if (impBaseService != null) {
////                    Object dataObj = null;
////                    if (insertFlg) {
////                        dataObj = insertList.get(insertList.size() - 1);
////                    }
////                    else {
////                        dataObj = updateList.get(updateList.size() - 1);
////                    }
////                    try {
////                        impBaseService.afterHandleRowDataToDb(insertFlg, revokeFlag, dataObj);
////                    }
////                    catch (Exception e) {
////                        e.printStackTrace();
////                        resultDto.setImportFlag(ImportFlgEnum.导入失败.getName());
////                        resultDto.setImpResultMsg(
////                            this.getMessage("VAL_EXCELIMP_E_0018",
////                                StringUtil.getOrElse(e.getMessage(),
////                                    StringUtil.EMPTY) + StringUtil.getOrElse(e.getClass(),
////                                        StringUtil.EMPTY)));
////                        return resultDto;
////                    }
////                }
////            }
////
////            resultDto.setImportFlag(ImportFlgEnum.导入成功.getName());
////            resultDto.setImpResultMsg(this.getMessage("VAL_EXCELIMP_I_0001",
////                sheetInfoDto.getSheetDataList().size(), insertList.size(), updateList.size()));
////        }
////
////        return resultDto;
////    }
//
//    /**
//     * 对行数据进行数据检查(共通检查)
//     *
//     * @param rowIndex 行序号
//     * @param valExcelRule 映射规则
//     * @param rowDataMap 行数据
//     * @param skipColumnMap 设值跳过的共通字段
//     * @return 检查结果
//     * @see
//     * @since 1.0
//     */
//    private List<String> checkRowData(int rowIndex, ValExcelRuleDto valExcelRule,
//                                      Map<String, Object> rowDataMap,
//                                      Map<String, String> skipColumnMap) {
//        // 表的字段定义信息
//        List<ValTableColumnDefineInfoDto> columnDefineInfoList = valExcelRule.getColumnDefineInfoList();
//        // 检查结果
//        List<String> errorMsgList = new ArrayList<String>();
//
//        // 根据字段定义，检查必须、类型、长度等共通检查项目
//        for (ValTableColumnDefineInfoDto columnDefineInfoDto : columnDefineInfoList) {
//            // 跳过共通字段
//            if (skipColumnMap.containsKey(
//                this.underlineToCamel(columnDefineInfoDto.getColName()))) {
//                continue;
//            }
//
//            // 字段值
//            Object columnValue = rowDataMap.get(columnDefineInfoDto.getColName());
//            List<String> columnErrorMsgList = new ArrayList<String>();
//
//            // 必须检查
//            if (StringUtil.equals(ColumnNullableEnum.不可为空.getCode(),
//                columnDefineInfoDto.getNulls())) {
//                if (columnValue == null || StringUtil.isBlank(columnValue.toString())) {
//                    columnErrorMsgList.add(this.getMessage("VAL_EXCELIMP_E_0005", rowIndex + 1,
//                        columnDefineInfoDto.getRemarks()));
//
//                    errorMsgList.addAll(columnErrorMsgList);
//                    continue;
//                }
//            }
//
//            if (columnValue == null || StringUtil.isBlank(columnValue.toString())) {
//                continue;
//            }
//
//            String columnValueStr = columnValue.toString();
//            // 字段值长度
//            int columnValueLength = 0;
//            try {
//                columnValueLength = columnValueStr.getBytes("UTF-8").length;
//            }
//            catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//                columnValueLength = columnValueStr.length();
//            }
//
//            // 类型检查
//            if (StringUtil.equals(ColumnTypeNameEnum.CHARACTER.getCode(),
//                columnDefineInfoDto.getTypeName())) {
//                // 固定位数检查
//                if (columnValueLength != columnDefineInfoDto.getLength()) {
//                    columnErrorMsgList.add(this.getMessage("VAL_EXCELIMP_E_0013", rowIndex + 1,
//                        columnDefineInfoDto.getRemarks(), columnDefineInfoDto.getLength()));
//                }
//            }
//            else if (StringUtil.equals(ColumnTypeNameEnum.VARCHAR.getCode(),
//                columnDefineInfoDto.getTypeName())) {
//                // 最大长度检查
//                if (columnValueLength > columnDefineInfoDto.getLength()) {
//                    columnErrorMsgList.add(this.getMessage("VAL_EXCELIMP_E_0012", rowIndex + 1,
//                        columnDefineInfoDto.getRemarks(), columnDefineInfoDto.getLength()));
//                }
//            }
//            else if (StringUtil.equals(ColumnTypeNameEnum.DATE.getCode(),
//                columnDefineInfoDto.getTypeName())) {
//                // 日期格式检查
//                try {
//                    Date columnValueDate = DateUtil.parse(columnValueStr);
//                    rowDataMap.put(columnDefineInfoDto.getColName(), columnValueDate);
//                }
//                catch (Exception e) {
//                    columnErrorMsgList.add(this.getMessage("VAL_EXCELIMP_E_0006", rowIndex + 1,
//                        columnDefineInfoDto.getRemarks()));
//                }
//            }
//            else if (StringUtil.equals(ColumnTypeNameEnum.TIMESTAMP.getCode(),
//                columnDefineInfoDto.getTypeName())) {
//                // 日期格式检查
//                try {
//                    Date columnValueDate = DateUtil.parse(columnValueStr);
//                    rowDataMap.put(columnDefineInfoDto.getColName(), columnValueDate);
//                }
//                catch (Exception e) {
//                    columnErrorMsgList.add(this.getMessage("VAL_EXCELIMP_E_0007", rowIndex + 1,
//                        columnDefineInfoDto.getRemarks()));
//                }
//            }
//            else if (StringUtil.equals(ColumnTypeNameEnum.DECIMAL.getCode(),
//                columnDefineInfoDto.getTypeName())) {
//                // 数值格式检查
//                BigDecimal columnValueDecimal = null;
//                try {
//                    columnValueStr = StringUtil.remove(columnValueStr, GlobalConstant.COMMA);
//                    columnValueDecimal = new BigDecimal(columnValueStr);
//                }
//                catch (Exception e) {
//                    columnErrorMsgList.add(this.getMessage("VAL_EXCELIMP_E_0008", rowIndex + 1,
//                        columnDefineInfoDto.getRemarks()));
//                }
//
//                // 整数位小数位检查
//                if (columnValueDecimal != null) {
//                    rowDataMap.put(columnDefineInfoDto.getColName(), columnValueDecimal);
//
//                    String tempCheckValueStr = columnValueDecimal.abs().toString();
//
//                    List<String> numStrList = StringUtil.splitToList(GlobalConstant.POINT,
//                        tempCheckValueStr);
//                    if (numStrList.get(0).length() > columnDefineInfoDto.getLength()) {
//                        columnErrorMsgList.add(this.getMessage("VAL_EXCELIMP_E_0009", rowIndex + 1,
//                            columnDefineInfoDto.getRemarks(), columnDefineInfoDto.getLength(),
//                            columnDefineInfoDto.getScale()));
//                    }
//                    else if (numStrList.size() > 1 && columnDefineInfoDto.getScale() > 0) {
//                        if (numStrList.get(1).length() > columnDefineInfoDto.getScale()) {
//                            // 小数位按照DB位数四舍五入截取
//                            rowDataMap.put(columnDefineInfoDto.getColName(),
//                                columnValueDecimal.setScale(2, BigDecimal.ROUND_HALF_UP));
//                        }
//                    }
//
//                }
//            }
//            else if (StringUtil.equals(ColumnTypeNameEnum.INTEGER.getCode(),
//                columnDefineInfoDto.getTypeName())) {
//                // 数值格式检查
//                Integer columnValueInt = null;
//                try {
//                    columnValueStr = StringUtil.remove(columnValueStr, GlobalConstant.COMMA);
//                    columnValueInt = Integer.valueOf(columnValueStr);
//                }
//                catch (Exception e) {
//                    columnErrorMsgList.add(this.getMessage("VAL_EXCELIMP_E_0010", rowIndex + 1,
//                        columnDefineInfoDto.getRemarks()));
//                }
//
//                // 数值长度检查
//                if (columnValueInt != null) {
//                    rowDataMap.put(columnDefineInfoDto.getColName(), columnValueInt);
//
//                    String tempCheckValueStr = String.valueOf(Math.abs(columnValueInt));
//
//                    if (tempCheckValueStr.length() > columnDefineInfoDto.getLength()) {
//                        columnErrorMsgList.add(this.getMessage("VAL_EXCELIMP_E_0011", rowIndex + 1,
//                            columnDefineInfoDto.getRemarks(), columnDefineInfoDto.getLength()));
//                    }
//                }
//            }
//
//            errorMsgList.addAll(columnErrorMsgList);
//        }
//
//        return errorMsgList;
//    }
//
//    /**
//     * 需要跳过检查和设值的共通字段
//     *
//     * @return 共通字段
//     * @see
//     * @since 1.0
//     */
//    private Map<String, String> getSkipColumnMap() {
//        Map<String, String> skipColumnMap;
//        // 需要跳过检查的共通字段
//        Field[] skipFieldArray = BaseEntity.class.getDeclaredFields();
//        List<Field> skipFieldList = Arrays.asList(skipFieldArray);
//        skipColumnMap = skipFieldList.stream().collect(
//            JoyinCollectors.toMap(x -> x.getName(), x -> x.getName()));
//        skipColumnMap.put("workflowId", "workflowId");
//        skipColumnMap.remove("status");
//        return skipColumnMap;
//    }
//
//    /**
//     * 入库
//     *
//     * @param valExcelRule 映射规则
//     * @param rowDataMap 行数据
//     * @param insertList 登录数据列表
//     * @param updateList 更新数据列表
//     * @param skipColumnMap 设值跳过的共通字段
//     * @param systemDate 系统平台日期
//     * @param impBaseService 各业务数据具体实现类
//     * @throws Exception 异常
//     * @return 主表数据是insert还是update
//     * @see
//     * @since 1.0
//     */
//    private boolean handleRowDataToDb(ValExcelRuleDto valExcelRule, Map<String, Object> rowDataMap,
//                                      List<Object> insertList, List<Object> updateList,
//                                      Map<String, String> skipColumnMap, Date systemDate,
//                                      ValExcelImpBaseService impBaseService)
//        throws Exception {
//        // 目标表物理名
//        String tableName = valExcelRule.getTargetTable();
//        // 目标表物理名对应entity名（首字母小写）
//        String tableEntityName = this.underlineToCamel(tableName);
//        // 目标表物理名对应entity名（首字母大写）
//        String tableEntityNameOther = StringUtil.substring(tableEntityName, 0, 1).toUpperCase()
//                                      + StringUtil.substring(tableEntityName, 1);
//        // 目标表entity类名
//        String entityClassName = "com.joyintech.tams.common.base.entity."
//                                 + tableEntityNameOther;
//        Class<?> entityClass = Class.forName(entityClassName);
//        Object entityObj = entityClass.newInstance();
//
//        // 单表mapper
//        String daoMapperClassName = "com.joyintech.tams.common.base.dao."
//                                    + tableEntityNameOther + "Mapper";
//        Object daoObj = SpringContextUtil.getBean(tableEntityName + "Mapper");
//
//        // 根据主键查询数据是否已存在,确定是insert还是update
//        boolean insertFlg = true;
//        List<String> tableKeyList = valExcelRule.getTableKeyList();
//        Object keyObj = null;
//        String entityKeyClassName = null;
//        Class<?> entityKeyClass = null;
//        Object entityKeyObj = null;
//        if (!StringUtil.isEmptyList(tableKeyList)) {
//            if (tableKeyList.size() == 1) {
//                keyObj = rowDataMap.get(tableKeyList.get(0));
//            }
//            else {
//                entityKeyClassName = entityClassName + "Key";
//                entityKeyClass = Class.forName(entityKeyClassName);
//                entityKeyObj = entityKeyClass.newInstance();
//
//                for (String keyColumn : tableKeyList) {
//                    if (rowDataMap.get(keyColumn) != null) {
//                        Field field = this.getClassField(entityKeyClassName,
//                            this.underlineToCamel(keyColumn));
//                        field.setAccessible(true);
//                        field.set(entityKeyObj, rowDataMap.get(keyColumn));
//                    }
//                }
//
//                keyObj = entityKeyObj;
//            }
//
//            Method tempMethod = Class.forName(daoMapperClassName).getDeclaredMethod(
//                "selectByPrimaryKey", keyObj.getClass());
//            Object resultObj = tempMethod.invoke(daoObj, keyObj);
//            if (resultObj != null) {
//                insertFlg = false;
//                entityObj = resultObj;
//            }
//        }
//
//        for (String columnName : rowDataMap.keySet()) {
//            if (skipColumnMap.containsKey(this.underlineToCamel(columnName))) {
//                continue;
//            }
//            Object columnValue = rowDataMap.get(columnName);
//
//            Field field = this.getClassField(entityClassName, this.underlineToCamel(columnName));
//            field.setAccessible(true);
//
//            if (field.getType().toString().contains("java.lang.Integer")
//                && StringUtil.isNotEmpty(columnValue)) {
//                BigDecimal tempColumnDecValue = new BigDecimal(columnValue.toString().trim());
//                Integer tempColumnIntValue = tempColumnDecValue.intValue();
//                field.set(entityObj, tempColumnIntValue);
//            }
//            else if (field.getType().toString().contains("java.math.BigDecimal")
//                     && StringUtil.isNotEmpty(columnValue)) {
//                BigDecimal tempColumnDecValue = new BigDecimal(columnValue.toString().trim());
//                field.set(entityObj, tempColumnDecValue);
//            }
//            else {
//                field.set(entityObj, columnValue);
//            }
//        }
//
//        if (insertFlg) {
//            // 工作流代码设置
//            try {
//                Field field = this.getClassField(entityClassName, "workflowId");
//                field.setAccessible(true);
//                field.set(entityObj,
//                    baseCommonService.getSequenceIndexData(DataIndexSequenceType.工作流));
//            }
//            catch (Exception e) {
//            }
//
//            // 撤销时，先删除db里有效数据
//            try {
//                revokeFlag = false;
//                if (DictCodeList.数据状态_删除.getValue().equals(rowDataMap.get("STATUS"))) {
//                    if (entityKeyObj != null) {
//                        Field field = this.getClassField(entityKeyClassName, "status");
//                        field.setAccessible(true);
//                        field.set(entityKeyObj, DictCodeList.数据状态_有效.getValue());
//
//                        Method selectMethod = Class.forName(daoMapperClassName).getDeclaredMethod(
//                            "selectByPrimaryKey", entityKeyClass);
//
//                        // 取得要删除数据的workflowId
//                        Class<?> entityClass2 = Class.forName(entityClassName);
//                        Object selectData = entityClass2.newInstance();
//                        selectData = selectMethod.invoke(daoObj, entityKeyObj);
//                        Field workflowIdField = this.getClassField(entityClassName, "workflowId");
//                        workflowIdField.setAccessible(true);
//                        Object workflowId = workflowIdField.get(selectData);
//
//                        if (workflowId != null) {
//                            Method deleteMethod = Class.forName(
//                                daoMapperClassName).getDeclaredMethod("deleteByPrimaryKey",
//                                    entityKeyClass);
//                            int deleteCount = (int)deleteMethod.invoke(daoObj, entityKeyObj);
//                            workflowIdField.set(entityObj, workflowId);
//                            revokeFlag = deleteCount > 0;
//                        }
//                        /** 共通处理：撤销日存成生效日 */
//                        // 取得生效日属性
//                        Field fieldEffDate = this.getClassField(entityClassName, "effDate");
//                        // 取得撤销日属性
//                        Field fieldRevDate = this.getClassField(entityClassName, "revDate");
//                        if (fieldEffDate != null && fieldRevDate != null) {
//                            // 获取权限
//                            fieldEffDate.setAccessible(true);
//                            fieldRevDate.setAccessible(true);
//
//                            Object effDate = fieldEffDate.get(entityObj);
//                            // 设置撤销日值
//                            fieldRevDate.set(entityObj, effDate);
//                        }
//                    }
//                }
//            }
//            catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            // 数据录入之前特殊处理
//            if (impBaseService != null) {
//                impBaseService.beforeHandleRowDataToDb(insertFlg, revokeFlag, entityObj);
//            }
//
//            // 数据登录
//            Method insertMethod = Class.forName(daoMapperClassName).getDeclaredMethod("insert",
//                entityClass);
//            insertMethod.invoke(daoObj, entityObj);
//
//            insertList.add(entityObj);
//        }
//        else {
//            // 数据录入之前特殊处理
//            if (impBaseService != null) {
//                impBaseService.beforeHandleRowDataToDb(insertFlg, false, entityObj);
//            }
//
//            // 更新需要将更新者和更新时间更改
//            Field field = this.getClassField(entityClassName, "updateUser");
//            User user = SecurityUtil.getLoginUser();
//            field.setAccessible(true);
//            field.set(entityObj, user.getUserName());
//            field = this.getClassField(entityClassName, "updateTime");
//            field.setAccessible(true);
//            field.set(entityObj, new Date());
//
//            // 数据更新
//            Method updateMethod = Class.forName(daoMapperClassName).getDeclaredMethod(
//                "updateByPrimaryKeySelective", entityClass);
//            updateMethod.invoke(daoObj, entityObj);
//
//            updateList.add(entityObj);
//        }
//
//        // 处理交易数据产品的最小跑批日
//        // try {
//        // // 取得生效日属性
//        // Field fieldEffDate = this.getClassField(entityClassName, "effDate");
//        // Object effDate = fieldEffDate.get(entityObj);
//        // // 取得产品代码属性 PROD_ID或者ACCT_ID
//        // Object prodId = null;
//        // try {
//        // Field fieldProdId = this.getClassField(entityClassName, "prodId");
//        // prodId = fieldProdId.get(entityObj);
//        // }
//        // catch (Exception e) {
//        // }
//        // if (prodId == null) {
//        // Field fieldProdId = this.getClassField(entityClassName, "acctId");
//        // prodId = fieldProdId.get(entityObj);
//        // }
//        //
//        // if (effDate != null && prodId != null) {
//        // Date effDateValue = (Date)effDate;
//        // String prodIdValue = (String)prodId;
//        //
//        // baseCommonService.HandleProdExebatchMindate(effDateValue,
//        // prodIdValue);
//        // }
//        // }
//        // catch (Exception e) {
//        // }
//
//        return insertFlg;
//    }
//
//    /**
//     * 根据文件名及sheet名获取Excel导入规则
//     *
//     * @param fileInfoDto 导入文件信息
//     * @return 结果
//     * @see
//     * @since 1.0
//     */
//    private List<String> getExcelRule(ValExcelImpFileInfoDto fileInfoDto) {
//        List<String> msgList = new ArrayList<String>();
//
//        String impFileName = fileInfoDto.getImpFileName();
//        List<ValExcelRuleDto> excelRuleList = valExcelImpHandle001Mapper.selectValExcelRule(
//            impFileName, null);
//        if (!StringUtil.isEmptyList(excelRuleList)) {
//            for (ValExcelImpFileSheetInfoDto sheetInfoDto : fileInfoDto.getSheetInfoList()) {
//                sheetInfoDto.setValExcelRuleDto(excelRuleList.get(0));
//            }
//        }
//        else {
//            for (ValExcelImpFileSheetInfoDto sheetInfoDto : fileInfoDto.getSheetInfoList()) {
//                List<ValExcelRuleDto> excelSheetRuleList = valExcelImpHandle001Mapper.selectValExcelRule(
//                    null, sheetInfoDto.getImpFileSheetName());
//                if (!StringUtil.isEmptyList(excelSheetRuleList)) {
//                    sheetInfoDto.setValExcelRuleDto(excelSheetRuleList.get(0));
//                }
//                else {
//                    String msg = getMessage("VAL_EXCELIMP_E_0019", sheetInfoDto.getImpFileSheetName());
//                    msgList.add(msg);
//                }
//            }
//        }
//
//        return msgList;
//    }
//
//    /**
//     * 根据规则代码获取Excel导入规则明细
//     *
//     * @param ruleId 规则代码
//     * @return Excel导入规则明细
//     * @see
//     * @since 1.0
//     */
//    private Map<Long, List<ValExcelRuleDetailDto>> getExcelRuleDetailData(String ruleId) {
//
//        List<ValExcelRuleDetailDto> excelRuleDetailList = valExcelImpHandle001Mapper.selectExcelRuleDetail(
//            ruleId);
//        if (StringUtil.isEmptyList(excelRuleDetailList)) {
//            return null;
//        }
//
//        Map<Long, List<ValExcelRuleDetailDto>> resultMap = excelRuleDetailList.stream().collect(
//            JoyinCollectors.groupingBy(x -> {
//                try {
//                    return ExcelUtil.columnNoToIndex(x.getColumnNo());
//                }
//                catch (Exception e) {
//                    e.printStackTrace();
//                    return null;
//                }
//            }));
//
//        return resultMap;
//    }
//
//    /**
//     * 获取某个类的field对象
//     *
//     * @param className 类名
//     * @param fieldName field名
//     * @return field对象
//     * @see
//     * @since 1.0
//     */
//    private Field getClassField(String className, String fieldName) {
//        try {
//            Class<?> tempClass = Class.forName(className);
//            Field field;
//            while (tempClass != null) {
//                try {
//                    field = tempClass.getDeclaredField(fieldName);
//                    return field;
//                }
//                catch (Exception e) {
//                    tempClass = tempClass.getSuperclass(); // 得到父类,然后赋给自己
//                }
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 取得字典转换值
//     *
//     * @param ruleDictCodeMap 字典转换规则
//     * @param targetColumn 目标列
//     * @param sourceValue 源值
//     * @return 字典映射值
//     * @throws Exception 目标列有转换规则，源值对应的的转换规则不存在时，抛出异常
//     * @see
//     * @since 1.0
//     */
//    private String getConvertDictCode(Map<String, Map<String, String>> ruleDictCodeMap,
//                                      String targetColumn, String sourceValue)
//        throws Exception {
//        // 参数为空时，返回源值
//        if (isEmptyMap(ruleDictCodeMap) || StringUtil.isEmpty(targetColumn)
//            || StringUtil.isEmpty(sourceValue)) {
//            return sourceValue;
//        }
//        // 获取目标字段对应的数据字典转换规则明细
//        Map<String, String> targetMap = ruleDictCodeMap.get(targetColumn);
//        // 转换规则明细为空时，返回源值
//        if (isEmptyMap(targetMap)) {
//            return sourceValue;
//        }
//        // 根据源值，取得转换的目标值
//        String targetValue = targetMap.get(sourceValue);
//        // 如果源值不为空，找不到映射目标字典值，则抛出异常提示
//        if (targetValue == null) {
//            throw new Exception();
//        }
//        return targetValue;
//    }
//
//    /**
//     * 取得master转换值
//     *
//     * @param ruleMasterMap master转换规则map
//     * @param targetColumn 目标字段
//     * @param sourceValue 源值
//     * @return master转换值
//     * @throws Exception master转换规则存在，查询的master结果为空时，抛出异常提示
//     * @see
//     * @since 1.0
//     */
//    private String getMasterValue(Map<String, ValExcelRuleMasterDataDto> ruleMasterMap,
//                                  String targetColumn, String sourceValue)
//        throws Exception {
//        // 参数为空时，返回源值
//        if (isEmptyMap(ruleMasterMap) || StringUtil.isEmpty(targetColumn)
//            || StringUtil.isEmpty(sourceValue)) {
//            return sourceValue;
//        }
//        // 如果无mater转换规则，则返回源值
//        ValExcelRuleMasterDataDto valExcelRuleMasterData = ruleMasterMap.get(targetColumn);
//        if (valExcelRuleMasterData == null) {
//            return sourceValue;
//        }
//
//        String targetValue = valExcelImpHandle001Mapper.getMasterValue(
//            valExcelRuleMasterData.getMasterDataTable(),
//            valExcelRuleMasterData.getMasterDataIdColumn(),
//            valExcelRuleMasterData.getMasterDataNameColumn(), sourceValue);
//        // 如果目标列master转换规则存在，源值也存在，查询的master数据值为空，则抛出异常
//        if (targetValue == null) {
//            throw new Exception();
//        }
//        return targetValue;
//    }
//
//    /**
//     * 根据自定义SQL转换规则，取得转换值
//     *
//     * @param ruleSqlMap 自定义SQL转换规则
//     * @param cusSqlParamMap 替换参数值
//     * @param targetColumn 目标字段
//     * @param sourceValue 源值
//     * @return 转换值
//     * @throws Exception 错误
//     * @see
//     * @since 1.0
//     */
//    private String getValueByCusSQL(Map<String, ValExcelRuleCustomSqlDto> ruleSqlMap,
//                                    Map<String, String> cusSqlParamMap, String targetColumn,
//                                    String sourceValue)
//        throws Exception {
//        // 参数为空时，返回源值
//        if (isEmptyMap(ruleSqlMap) || StringUtil.isEmpty(targetColumn)
//            || StringUtil.isEmpty(sourceValue)) {
//            return sourceValue;
//        }
//        // 如果无自定义SQL转换规则，则返回源值
//        ValExcelRuleCustomSqlDto valExcelRuleCustomSql = ruleSqlMap.get(targetColumn);
//        if (valExcelRuleCustomSql == null) {
//            return sourceValue;
//        }
//
//        String customSql = valExcelRuleCustomSql.getCustomSql();
//        if (!StringUtil.isEmpty(customSql)) {
//            for (Map.Entry<String, String> entry : cusSqlParamMap.entrySet()) {
//                customSql = customSql.replaceAll(entry.getKey(),
//                    this.strToSqlParam(entry.getValue()));
//            }
//
//            Pattern pattern = Pattern.compile(CUSSQL_PARA_REG);
//            Matcher matcher = pattern.matcher(customSql);
//            if (matcher.find()) {
//                // 参数不足
//                throw new Exception();
//            }
//        }
//        String targetValue = executeCustomSql(customSql);
//        // TODO 如果目标列master转换规则存在，源值也存在，查询的master数据值为空，则抛出异常
//        return targetValue;
//    }
//
//    /**
//     * 获取数据字典转换规则
//     *
//     * @param ruleId 规则代码
//     * @return 数据字典转换规则
//     * @see
//     * @since 1.0
//     */
//    private Map<String, Map<String, String>> getRuleDictCodeMap(String ruleId) {
//        List<ValExcelRuleDictcodeDto> ruleDictcodeDtoList = valExcelImpHandle001Mapper.selectRuleDictcode(
//            ruleId);
//
//        Map<String, List<ValExcelRuleDictcodeDetailDto>> ruleDictCodeDetailMap = null;
//        if (!StringUtil.isEmptyList(ruleDictcodeDtoList)) {
//            ruleDictCodeDetailMap = ruleDictcodeDtoList.stream().collect(
//                JoyinCollectors.toMap(x -> x.getTargetColumn(), x -> x.getDetailDtos()));
//        }
//        Map<String, Map<String, String>> ruleDictCodelMap = new HashMap<String, Map<String, String>>();
//        if (!this.isEmptyMap(ruleDictCodeDetailMap)) {
//            for (Map.Entry<String, List<ValExcelRuleDictcodeDetailDto>> entry : ruleDictCodeDetailMap.entrySet()) {
//                List<ValExcelRuleDictcodeDetailDto> detailDtoList = entry.getValue();
//                if (!StringUtil.isEmptyList(detailDtoList)) {
//                    Map<String, String> tempDetailMap = detailDtoList.stream().collect(
//                        JoyinCollectors.toMap(x -> x.getColumnValue(), x -> x.getTargetValue()));
//                    ruleDictCodelMap.put(entry.getKey(), tempDetailMap);
//                }
//            }
//
//        }
//
//        return ruleDictCodelMap;
//    }
//
//    /**
//     * 获取master转换规则
//     *
//     * @param ruleId 规则代码
//     * @return master转换规则
//     * @see
//     * @since 1.0
//     */
//    private Map<String, ValExcelRuleMasterDataDto> getRuleMasterMap(String ruleId) {
//        List<ValExcelRuleMasterDataDto> ruleMasterDataList = valExcelImpHandle001Mapper.selectRuleMasterData(
//            ruleId);
//
//        Map<String, ValExcelRuleMasterDataDto> ruleMasterMap = null;
//        if (!StringUtil.isEmptyList(ruleMasterDataList)) {
//            ruleMasterMap = ruleMasterDataList.stream().collect(
//                JoyinCollectors.toMap(x -> x.getTargetColumn(), x -> x));
//        }
//        return ruleMasterMap;
//    }
//
//    /**
//     * 获取自定义SQL转换规则
//     *
//     * @param ruleId 规则代码
//     * @return 自定义SQL转换规则
//     * @see
//     * @since 1.0
//     */
//    private Map<String, ValExcelRuleCustomSqlDto> getRuleSqlMap(String ruleId) {
//        List<ValExcelRuleCustomSqlDto> ruleCustomSqlList = valExcelImpHandle001Mapper.selectRuleCustomSql(
//            ruleId);
//        Map<String, ValExcelRuleCustomSqlDto> ruleSqlMap = null;
//        if (!StringUtil.isEmptyList(ruleCustomSqlList)) {
//            ruleSqlMap = ruleCustomSqlList.stream().collect(
//                JoyinCollectors.toMap(x -> x.getTargetColumn(), x -> x));
//        }
//        return ruleSqlMap;
//    }
//
//    /**
//     * 执行自定义SQL
//     *
//     * @param customSql 已拼装好的自定义SQL文
//     * @return 自定义SQL执行结果
//     * @see
//     * @since 1.0
//     */
//    private String executeCustomSql(String customSql) {
//        String resultValue = null;
//        Connection conn = null;
//        BasicDataSource basicDataSource = new BasicDataSource();
//        try {
//            basicDataSource.setDriverClassName(jdbcDiver);
//            basicDataSource.setUrl(jdbcUrl);
//            basicDataSource.setUsername(jdbcUser);
//            basicDataSource.setPassword(jdbcPassword);
//            conn = basicDataSource.getConnection();
//            Statement statement = conn.createStatement();
//            ResultSet rs = statement.executeQuery(customSql);
//            while (rs.next()) {
//                resultValue = rs.getString(1);
//            }
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return resultValue;
//
//    }
//
//    /**
//     * 下划线格式字符串转换为驼峰格式字符串
//     *
//     * @param param 参数字符串
//     * @return 驼峰格式字符串
//     */
//    private String underlineToCamel(String param) {
//        if (param == null || "".equals(param.trim())) {
//            return param;
//        }
//        param = param.toLowerCase();
//        int len = param.length();
//        StringBuilder sb = new StringBuilder(len);
//        for (int i = 0; i < len; i++ ) {
//            char c = param.charAt(i);
//            if (c == '_') {
//                if (++i < len) {
//                    sb.append(Character.toUpperCase(param.charAt(i)));
//                }
//            }
//            else {
//                sb.append(c);
//            }
//        }
//        return sb.toString();
//    }
//
//    /**
//     * 判断map是否为空
//     * 
//     * @param map 参数map
//     * @return boolean true:空，false:不为空
//     * @see
//     * @since 1.0
//     */
//    private boolean isEmptyMap(Map<?, ?> map) {
//        if (map == null || map.isEmpty()) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 将字符串转成带单引号的字符串
//     *
//     * @param value 输入的字符串
//     * @return String 转换成单引号的字符串
//     * @see
//     * @since 1.0
//     */
//    private String strToSqlParam(String value) {
//        if (StringUtil.isEmpty(value)) {
//            return value;
//        }
//        return "'" + value + "'";
//    }
//}
