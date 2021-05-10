/*
 * 文件名：ValIncomeStatisticsQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年10月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.changesubjectno.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ExcelUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.common.auxiliary.dto.ChangeSubjectNoDto;
import com.joyintech.tams.valuation.common.auxiliary.dto.ChangeSubjectNoResult;
import com.joyintech.tams.valuation.common.auxiliary.dto.ResultCode;
import com.joyintech.tams.valuation.common.auxiliary.service.OperationAuxiliaryService;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.changesubjectno.dto.ChangeSubjectNo001Dto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.changesubjectno.dto.CheckResultDto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.changesubjectno.dto.ImportCheckResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 辅助工具类-科目号变更
 * 
 * @author yanya
 * @version 1.0 2020-12-29
 * @see ValChangeSubjectNo001Service
 * @since 1.0
 */
@Service
public class ValChangeSubjectNo001Service extends CommonExportService<ChangeSubjectNo001Dto> {

    @Autowired
    OperationAuxiliaryService operationAuxiliaryService;

    /**
     * 上传控件进度条Service
     */
    @Autowired(required = false)
    @Qualifier("commonProgressBarService")
    private ProgressBarService progressBarService;

    /**
     * 根据文件名称解析结果
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    public List<List<String>> analysisFile(String fileName) throws FileNotFoundException {
        ExcelUtil excelUtil = ExcelUtil.build(FileNameUtil.getUploadFilePath(fileName), null);
        List<List<String>> dataList = excelUtil.readToList(0, 1, 0);
        return dataList;
    }

    /**
     * 根据excel数据转换为科目变更信息
     * @param dataList
     * @return
     */
    public List<ChangeSubjectNoDto> turnListByAnalysis(List<List<String>> dataList) {
        List<ChangeSubjectNoDto> list = new ArrayList<>();
        if(!StringUtil.isEmptyList(dataList)) {
            dataList.stream().forEach(item -> {
                ChangeSubjectNoDto dto = new ChangeSubjectNoDto();
                dto.setRowIndexNo(Integer.parseInt(StringUtil.trim(item.get(0))));//行号
                dto.setBooksetId(StringUtil.trim(item.get(1)));//账套代码
                dto.setOriSubjectNo(StringUtil.trim(item.get(2)));//原科目号
                dto.setOriSubjectName(StringUtil.trim(item.get(3)));//原科目名称
                dto.setChangeSubjectNo(StringUtil.trim(item.get(4)));//变更后的科目号
                dto.setChangeSubjectName(StringUtil.trim(item.get(5)));//变更后的科目名称
                dto.setRowPri(Integer.parseInt(StringUtil.trim(item.get(7))));//优先级
                dto.setCondition(StringUtil.trim(item.get(6)));// 替换条件
                list.add(dto);
            });
        }
        return list;
    }

    /**
     * 校验
     * @param dto
     * @return
     */
    public CheckResultDto check(ChangeSubjectNo001Dto dto) {
        CheckResultDto result = new CheckResultDto();
        //检验结果标识
        String resultCode = ResultCode.SUCCESS.getCode();
        //检验结果描述
        String resultDesc = "";
        //文件检验结果
        List<ImportCheckResultDto> list = new ArrayList<>();
        if(StringUtil.equals(DictCodeListExt3.科目号变更处理方式_批量.getValue(), dto.getChangeSubjectType())) {
            if(StringUtil.isEmpty(dto.getFileName())) {
                //validMessageList.add(new ValidMessage("文件不能为空！"));
                resultCode = ResultCode.FAILURE.getCode();
                resultDesc = this.getMessage("VAL_SUBJECT_CHANGE_E_001");
            }
            else {
                if (!dto.getFileName().endsWith("xls") && !dto.getFileName().endsWith("xlsx")){
                    resultCode = ResultCode.FAILURE.getCode();
                    resultDesc = this.getMessage("VAL_SUBJECT_CHANGE_E_003");
                }
                else {
                    try {
                        List<List<String>> dataList = this.analysisFile(dto.getFileName());
                        //Map<Integer, Integer> priMap = new HashMap<>();//优先级
                        if (StringUtil.isEmptyList(dataList)) {
                            //validMessageList.add(new ValidMessage("上传的数据文件无数据！"));
                            resultCode = ResultCode.FAILURE.getCode();
                            resultDesc = this.getMessage("VAL_SUBJECT_CHANGE_E_002");
                        } else {
                            result.setDataList(dataList);//解析后的结果数据
                            //excel行号
                            int rowindex = 1;
                            String checkCode = ResultCode.SUCCESS.getCode();
                            String checkDesc = "";
                            //科目信息map
                            Map<String, BokSubject> subjectMap = this.getSubjectList(dataList);
                            for (List<String> item : dataList) {
                                //String 提示 = "EXCEL中第" + rowindex + "行，";
                                ImportCheckResultDto checkDto = new ImportCheckResultDto();
                                checkCode = ResultCode.SUCCESS.getCode();//标识
                                checkDesc = "";//描述
                                if (StringUtil.isEmpty(item.get(0))) {//行号为空
                                    //validMessageList.add(new ValidMessage(提示 + "【行号为空】"));
                                    checkCode = ResultCode.FAILURE.getCode();
                                    checkDesc = this.getMessage("VAL_SUBJECT_CHANGE_E_004", rowindex + 1);
                                }
                                else if(!judgeNumber(item.get(0))) {//行号为非数字
                                    checkCode = ResultCode.FAILURE.getCode();
                                    checkDesc = this.getMessage("VAL_SUBJECT_CHANGE_E_017", rowindex + 1);
                                }
                                else {
                                    checkDto.setRowIndexNo(Integer.parseInt(item.get(0)));
                                }
                                if (StringUtil.isEmpty(item.get(2))) {//原科目号为空
                                    //validMessageList.add(new ValidMessage(提示 + "【原科目号为空】"));
                                    checkCode = ResultCode.FAILURE.getCode();
                                    checkDesc = checkDesc + this.getMessage("VAL_SUBJECT_CHANGE_E_005");
                                }
                                if (StringUtil.isEmpty(item.get(4))) {//替换科目号为空
                                    checkCode = ResultCode.FAILURE.getCode();
                                    checkDesc = checkDesc + this.getMessage("VAL_SUBJECT_CHANGE_E_006");
                                }
                                else if(!subjectMap.containsKey(StringUtil.trim(item.get(4)))) {//替换科目号在科目表不存在
                                    checkCode = ResultCode.FAILURE.getCode();
                                    checkDesc = checkDesc + this.getMessage("VAL_SUBJECT_CHANGE_E_013");
                                }
                                if (StringUtil.isEmpty(item.get(5))) {//替换科目名称为空
                                    checkCode = ResultCode.FAILURE.getCode();
                                    checkDesc = checkDesc + this.getMessage("VAL_SUBJECT_CHANGE_E_011");
                                }
                                if (StringUtil.isEmpty(item.get(7))) {//优先级为空
                                    checkCode = ResultCode.FAILURE.getCode();
                                    checkDesc = checkDesc + this.getMessage("VAL_SUBJECT_CHANGE_E_007");
                                }
                                else if(!judgeNumber(item.get(7))) {//优先级为非数字
                                    checkCode = ResultCode.FAILURE.getCode();
                                    checkDesc = checkDesc + this.getMessage("VAL_SUBJECT_CHANGE_E_016");
                                }
                                else {
                                    String priCheckStr = this.judgeRepeatPri(dataList, StringUtil.trim(item.get(7)), rowindex);
                                    if(StringUtil.isNotEmpty(priCheckStr)) {
                                        checkCode = ResultCode.FAILURE.getCode();
                                        checkDesc = checkDesc + this.getMessage("VAL_SUBJECT_CHANGE_E_015", priCheckStr);
                                    }
                                }
//                                else if(priMap.containsKey(Integer.valueOf(StringUtil.trim(item.get(6))))) {//优先级有重复
//                                    checkCode = ResultCode.FAILURE.getCode();
//                                    checkDesc = checkDesc + this.getMessage("VAL_SUBJECT_CHANGE_E_015");
//                                }
//                                else {
//                                    priMap.put(Integer.valueOf(StringUtil.trim(item.get(6))),Integer.valueOf(StringUtil.trim(item.get(6))));
//                                }
                                if(StringUtil.equals(checkCode, ResultCode.FAILURE.getCode())) {//检验失败
                                    checkDto.setResultCode(checkCode);
                                    checkDto.setResultDesc(checkDesc);
                                    list.add(checkDto);
                                    resultCode = ResultCode.FAILURE.getCode();
                                    resultDesc = this.getMessage("VAL_SUBJECT_CHANGE_E_012");
                                }
                                else {
                                    resultDesc = "";
                                }
                                rowindex++;
                            }
                        }
                    } catch (Exception e) {//出现异常情况，如表格为非模板
                        //validMessageList.add(new ValidMessage("读取文件出现错误：" + e));
                        resultCode = ResultCode.FAILURE.getCode();
                        resultDesc = this.getMessage("VAL_SUBJECT_CHANGE_E_008", e);
                    }
                }
            }
        }
        else {
            int errornum = 1;
            String 错误分割符 = "、";
            String 换行符 = "<br />";
            //原科目号不可为空
            if(StringUtil.isEmpty(dto.getOriSubjectNo())) {//原科目号为空
                //validMessageList.add(new ValidMessage("oriSubjectNo", "原科目号不可为空！"));
                resultCode = ResultCode.FAILURE.getCode();
                resultDesc = resultDesc + errornum + 错误分割符 +  this.getMessage("VAL_SUBJECT_CHANGE_E_009") + 换行符;
                errornum++;

            }
            //替换科目号不可为空
            if(StringUtil.isEmpty(dto.getChangeSubjectNo())) {//替换科目号为空
                //validMessageList.add(new ValidMessage("changeSubjectNo", "替换科目号不可为空！"));
                resultCode = ResultCode.FAILURE.getCode();
                resultDesc = resultDesc + errornum + 错误分割符 + this.getMessage("VAL_SUBJECT_CHANGE_E_010") + 换行符;
                errornum++;
            }
        }
        result.setResultCode(resultCode);
        result.setResultDesc(resultDesc);
        result.setImportCheckList(list);
        return result;
    }

    /**
     * 判定优先级是否有重复
     * @param dataList
     * @param priStr
     * @return
     */
    public String judgeRepeatPri(List<List<String>> dataList, String priStr, int rowindex) {
        String resultStr = "";
        String 分割 = "、";
        List<String> item = null;
        for(int i=0;i<dataList.size();i++) {
            item = dataList.get(i);
            //排除自己的行，且有与其他的更新顺序有重复的数据
            if (    i != (rowindex - 1)
                    && StringUtil.isNotEmpty(item.get(6))
                    && StringUtil.isNotEmpty(StringUtil.trim(item.get(6)))
                    && StringUtil.equals(priStr, StringUtil.trim(item.get(6)))) {//优先级为空
                if (StringUtil.isEmpty(item.get(0))) {//行号为空
                    resultStr += "EXCEL中第" + String.valueOf(i + 2) + "行" + 分割;
                } else {
                    resultStr += "第" + StringUtil.trim(item.get(0)) + "行" + 分割;
                }
            }
        }
        if(StringUtil.isNotEmpty(resultStr)) {
            return resultStr.substring(0, resultStr.length() - 1);
        }
        return resultStr;
    }

    /**
     * 判断是否为数字
     * @param str
     * @return
     */
    public boolean judgeNumber(String str) {
        if (StringUtil.isNotEmpty(str) && StringUtil.isNotEmpty(StringUtil.trim(str))) {
            str = StringUtil.trim(str);
            return str.matches("^[0-9]*$");
        }
        else {
            return false;
        }
    }

    /**
     * 获取科目信息list
     * @param dataList
     * @return
     */
    public Map<String, BokSubject> getSubjectList(List<List<String>> dataList) {
        List<String> list = new ArrayList<>();
        for (List<String> item : dataList) {
            if (StringUtil.isNotEmpty(item.get(4))) {
                list.add(StringUtil.trim(item.get(4)));
            }
        }
        List<BokSubject> subjectList = restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValChangeSubjectNo/getSubjectList",
                    list, BokSubject.class);
        if(StringUtil.isEmptyList(subjectList)) {
            return new HashMap<String, BokSubject>();
        }
        return subjectList.stream().collect(Collectors.toMap(BokSubject::getSubjectNo, subject -> subject));
    }

    /**
     * 获取所有账套
     * @return
     */
    public List<String> getAlltBooksetIds() {
        Map<String, Object> param = new HashMap<>();
        param.put("booksetType", DictCodeList.账套类别_理财账套.getValue());
        return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValChangeSubjectNo/getBooksetIds",
                param, String.class);
    }

    /**
     * 脚本生成
     * @param dto
     * @return
     * @author libingyan
     * @date 20210114
     */
    public AjaxResult scriptGeneration(ChangeSubjectNo001Dto dto) {
        String pageId = "ValChangeSubjectNo001";
        String barId = ProgressBarService.FILE_UPLOAD_ID;
        CheckResultDto result = this.check(dto);
        double currentPross = 0.65D;//记录进度
        progressBarService.updatePercent(pageId, barId, currentPross, false);//更新百分百
        double optAllProcess = 0.97D;

        //定义 变量.待生成脚本科目信息List
        List<ChangeSubjectNoDto> 待生成脚本科目信息List = new ArrayList<>();
        if(StringUtil.equals(result.getResultCode(), ResultCode.SUCCESS.getCode())) {
            if (StringUtil.equals(DictCodeListExt3.科目号变更处理方式_批量.getValue(), dto.getChangeSubjectType())) {
                //转换数据
                List<ChangeSubjectNoDto> list = this.turnListByAnalysis(result.getDataList());
                //根据优先级进行排序
                list.sort(new Comparator<ChangeSubjectNoDto>() {
                    @Override
                    public int compare(ChangeSubjectNoDto arg0, ChangeSubjectNoDto arg1) {
                        if (arg0.getRowPri() < arg1.getRowPri()) {
                            return -1;
                        }
                        return 1;
                    }
                });
                int totalSuccess = 0;
                int currentindex = 1;//当前处理条数
                List<ImportCheckResultDto> checkList = new ArrayList<>();
                int allSize = list.size();

                double optProcess = optAllProcess - currentPross;
                for(ChangeSubjectNoDto tmp : list) {
                    ChangeSubjectNoDto changeSubjectNoDto = new ChangeSubjectNoDto();
                    changeSubjectNoDto.setBooksetId(tmp.getBooksetId());//账套代码
                    changeSubjectNoDto.setRowIndexNo(tmp.getRowIndexNo());//行号
                    changeSubjectNoDto.setOriSubjectNo(tmp.getOriSubjectNo());//原科目号
                    changeSubjectNoDto.setChangeSubjectNo(tmp.getChangeSubjectNo());//变更后的 科目号
                    changeSubjectNoDto.setChangeSubjectName(tmp.getChangeSubjectName());//变更后科目名称
                    changeSubjectNoDto.setCondition(tmp.getCondition());// 替换条件
                }
                待生成脚本科目信息List.addAll(list);
                ChangeSubjectNoResult changeSubjectNoResult = operationAuxiliaryService.accountInformationChangeScriptGeneration(待生成脚本科目信息List);
                result.setResultCode(changeSubjectNoResult.getResultCode());
                result.setResultDesc(changeSubjectNoResult.getResultDesc());
                //todo execSql 将执行SQL生成文件
                String execSql = changeSubjectNoResult.getExecSql();
                result.setExecSql(execSql);
                progressBarService.updatePercent(pageId, barId, 1D, false);//更新进度条
            }
            else { //默认
                ChangeSubjectNoDto changeSubjectNoDto = new ChangeSubjectNoDto();
                //全部账套
                if(StringUtil.isNotEmpty(dto.getBooksetIds())) {
                    List<String> booksetIds = Arrays.asList(dto.getBooksetIds().split(","));
                    changeSubjectNoDto.setBooksetIdList(booksetIds);
                }
                //勾选的账套
                else {
                    List<String> booksetIds = this.getAlltBooksetIds();
                    changeSubjectNoDto.setBooksetIdList(booksetIds);
                }
                changeSubjectNoDto.setOriSubjectNo(dto.getOriSubjectNo());//原科目号
                changeSubjectNoDto.setChangeSubjectNo(dto.getChangeSubjectNo());//变更后的 科目号
                changeSubjectNoDto.setChangeSubjectName(dto.getChangeSubjectName());//变更后科目名称
                progressBarService.updatePercent(pageId, barId, 0.7D, false);//更新进度条

                // add by zwq 2021-01-05 start
                String condition="";
                if (StringUtil.isNotEmpty(dto.getFinprodType())){
                    condition += "[FINPROD_TYPE,"+dto.getFinprodType()+"]|";
                }
                if (StringUtil.isNotEmpty(dto.getType2())){
                    condition += "[TYPE_2,"+dto.getType2()+"]|";
                }
                if (StringUtil.isNotEmpty(dto.getTradeMarket())){
                    condition += "[TRADE_MARKET,"+dto.getTradeMarket()+"]|";
                }
                if (StringUtil.isNotEmpty(dto.getIsQtzq())){
                    condition += "[IS_QTZQ,"+dto.getIsQtzq()+"]|";
                }
                if (StringUtil.isNotEmpty(condition) && condition.length() > 0){
                    condition = condition.substring(0,condition.length() - 1);
                }
                changeSubjectNoDto.setCondition(condition);
                // add by zwq 2021-01-05 end
                /**
                 * IF 画面.处理方式  = 默认
                 * 	    变量.待生成脚本科目信息List.add(变量.科目变更DTO)
                 */
                待生成脚本科目信息List.add(changeSubjectNoDto);
                ChangeSubjectNoResult changeSubjectNoResult = operationAuxiliaryService.accountInformationChangeScriptGeneration(待生成脚本科目信息List);
                result.setResultCode(changeSubjectNoResult.getResultCode());
                result.setResultDesc(changeSubjectNoResult.getResultDesc());
                //todo execSql 将执行SQL生成文件
                String execSql = changeSubjectNoResult.getExecSql();
                result.setExecSql(execSql);
                progressBarService.updatePercent(pageId, barId, 1D, false);//更新进度条
            }
        }
        progressBarService.updatePercent(pageId, barId, 1D, false);//更新99
        AjaxResult axjx = null;
        //批量提交不能给错误信息，否则表格结果方法框架中不加载
        if (StringUtil.equals(DictCodeListExt3.科目号变更处理方式_批量.getValue(), dto.getChangeSubjectType())) {
            if(StringUtil.equals(result.getResultCode(), ResultCode.SUCCESS.getCode())) {
                axjx = new AjaxResult(result.getResultDesc(), MessageType.提示消息);
            }
            else {
                axjx = new AjaxResult(result.getResultDesc(), MessageType.警告消息);
            }
        }
        else {
            if(StringUtil.equals(result.getResultCode(), ResultCode.SUCCESS.getCode())) {
                axjx = new AjaxResult(result.getResultDesc(), MessageType.提示消息);
            }
            else if(StringUtil.equals(result.getResultCode(), ResultCode.REMIND.getCode())) {
                axjx = new AjaxResult(result.getResultDesc(), MessageType.警告消息);
            }
            else {
                axjx = new AjaxResult(result.getResultDesc(), MessageType.错误消息);
            }
        }
        axjx.setData(result);
        progressBarService.updatePercent(pageId, barId, 1D, false);//更新百分百
        return axjx;
    }
    /**
     * 提交方法
     * @param dto
     * @return
     */
    public AjaxResult change(ChangeSubjectNo001Dto dto) {
        String pageId = "ValChangeSubjectNo001";
        String barId = ProgressBarService.FILE_UPLOAD_ID;
        CheckResultDto result = this.check(dto);
        double currentPross = 0.65D;//记录进度
        progressBarService.updatePercent(pageId, barId, currentPross, false);//更新百分百
        double optAllProcess = 0.97D;
        if(StringUtil.equals(result.getResultCode(), ResultCode.SUCCESS.getCode())) {
            if (StringUtil.equals(DictCodeListExt3.科目号变更处理方式_批量.getValue(), dto.getChangeSubjectType())) {
                //转换数据
                List<ChangeSubjectNoDto> list = this.turnListByAnalysis(result.getDataList());
                //根据优先级进行排序
                list.sort(new Comparator<ChangeSubjectNoDto>() {
                    @Override
                    public int compare(ChangeSubjectNoDto arg0, ChangeSubjectNoDto arg1) {
                        if (arg0.getRowPri() < arg1.getRowPri()) {
                            return -1;
                        }
                        return 1;
                    }
                });
                int totalSuccess = 0;
                int currentindex = 1;//当前处理条数
                List<ImportCheckResultDto> checkList = new ArrayList<>();
                int allSize = list.size();

                double optProcess = optAllProcess - currentPross;
                for(ChangeSubjectNoDto tmp : list) {
                    ChangeSubjectNoDto changeSubjectNoDto = new ChangeSubjectNoDto();
                    changeSubjectNoDto.setBooksetId(tmp.getBooksetId());//账套代码
                    changeSubjectNoDto.setRowIndexNo(tmp.getRowIndexNo());//行号
                    changeSubjectNoDto.setOriSubjectNo(tmp.getOriSubjectNo());//原科目号
                    changeSubjectNoDto.setChangeSubjectNo(tmp.getChangeSubjectNo());//变更后的 科目号
                    changeSubjectNoDto.setChangeSubjectName(tmp.getChangeSubjectName());//变更后科目名称
                    changeSubjectNoDto.setCondition(tmp.getCondition());// 替换条件
                    ChangeSubjectNoResult changeSubjectNoResult = operationAuxiliaryService.changeSubjectNo(changeSubjectNoDto);
                    if(StringUtil.equals(changeSubjectNoResult.getResultCode(), ResultCode.SUCCESS.getCode())) {
                        totalSuccess++;
                    }
                    double addProcess = 1.0000* optProcess * currentindex / allSize;
                    if(optProcess + addProcess > optAllProcess) {
                        progressBarService.updatePercent(pageId, barId, optAllProcess, false);//更新进度条
                    }
                    else {
                        progressBarService.updatePercent(pageId, barId, optProcess + addProcess, false);//更新进度条
                    }
                    ImportCheckResultDto checkDto = new ImportCheckResultDto();
                    checkDto.setRowIndexNo(changeSubjectNoResult.getRowIndexNo());//行号
                    checkDto.setResultCode(changeSubjectNoResult.getResultCode());//结果代码
                    checkDto.setResultDesc(changeSubjectNoResult.getResultDesc());//结果描述
                    checkList.add(checkDto);
                    currentindex++;
                }
                result.setResultDesc(this.getMessage("VAL_SUBJECT_CHANGE_E_014", allSize, totalSuccess));//结果描述
                result.setImportCheckList(checkList);
                progressBarService.updatePercent(pageId, barId, optAllProcess, false);//更新进度条
            }
            else {
                ChangeSubjectNoDto changeSubjectNoDto = new ChangeSubjectNoDto();
                //全部账套
                if(StringUtil.isNotEmpty(dto.getBooksetIds())) {
                    List<String> booksetIds = Arrays.asList(dto.getBooksetIds().split(","));
                    changeSubjectNoDto.setBooksetIdList(booksetIds);
                }
                //勾选的账套
                else {
                    List<String> booksetIds = this.getAlltBooksetIds();
                    changeSubjectNoDto.setBooksetIdList(booksetIds);
                }
                changeSubjectNoDto.setOriSubjectNo(dto.getOriSubjectNo());//原科目号
                changeSubjectNoDto.setChangeSubjectNo(dto.getChangeSubjectNo());//变更后的 科目号
                changeSubjectNoDto.setChangeSubjectName(dto.getChangeSubjectName());//变更后科目名称
                progressBarService.updatePercent(pageId, barId, 0.7D, false);//更新进度条

                // add by zwq 2021-01-05 start
                String condition="";
                if (StringUtil.isNotEmpty(dto.getFinprodType())){
                    condition += "[FINPROD_TYPE,"+dto.getFinprodType()+"]|";
                }
                if (StringUtil.isNotEmpty(dto.getType2())){
                    condition += "[TYPE_2,"+dto.getType2()+"]|";
                }
                if (StringUtil.isNotEmpty(dto.getTradeMarket())){
                    condition += "[TRADE_MARKET,"+dto.getTradeMarket()+"]|";
                }
                if (StringUtil.isNotEmpty(dto.getIsQtzq())){
                    condition += "[IS_QTZQ,"+dto.getIsQtzq()+"]|";
                }
                if (StringUtil.isNotEmpty(condition) && condition.length() > 0){
                    condition = condition.substring(0,condition.length() - 1);
                }
                changeSubjectNoDto.setCondition(condition);
                // add by zwq 2021-01-05 end

                //调用共通 科目号变更
                ChangeSubjectNoResult changeSubjectNoResult = operationAuxiliaryService.changeSubjectNo(changeSubjectNoDto);
                result.setResultCode(changeSubjectNoResult.getResultCode());//结果代码
                result.setResultDesc(changeSubjectNoResult.getResultDesc());//结果描述
                progressBarService.updatePercent(pageId, barId, optAllProcess, false);//更新进度条
            }
        }
        progressBarService.updatePercent(pageId, barId, 0.99D, false);//更新99
        AjaxResult axjx = null;
        //批量提交不能给错误信息，否则表格结果方法框架中不加载
        if (StringUtil.equals(DictCodeListExt3.科目号变更处理方式_批量.getValue(), dto.getChangeSubjectType())) {
            if(StringUtil.equals(result.getResultCode(), ResultCode.SUCCESS.getCode())) {
                axjx = new AjaxResult(result.getResultDesc(), MessageType.提示消息);
            }
            else {
                axjx = new AjaxResult(result.getResultDesc(), MessageType.警告消息);
            }
        }
        else {
            if(StringUtil.equals(result.getResultCode(), ResultCode.SUCCESS.getCode())) {
                axjx = new AjaxResult(result.getResultDesc(), MessageType.提示消息);
            }
            else if(StringUtil.equals(result.getResultCode(), ResultCode.REMIND.getCode())) {
                axjx = new AjaxResult(result.getResultDesc(), MessageType.警告消息);
            }
            else {
                axjx = new AjaxResult(result.getResultDesc(), MessageType.错误消息);
            }
        }
        axjx.setData(result);
        progressBarService.updatePercent(pageId, barId, 1D, false);//更新百分百
        return axjx;
    }

    /**
     * 上传文件
     * @param fileName
     * @param schemeType
     * @return
     */
    public AjaxResult uploadFile(String fileName) {
        String pageId = "ValChangeSubjectNo001";
        String barId = ProgressBarService.FILE_UPLOAD_ID;
        AjaxResult res = new AjaxResult();
        progressBarService.updatePercent(pageId, barId, 1D, false);//更新百分百
        return new AjaxResult();
    }

    /**
     * 获取分页下拉框-账套
     * @param param
     * @return
     */
    public Page<SelectOptionDto> findBooksetId(Map<String, Object> param) {
        param.put("booksetType", DictCodeList.账套类别_理财账套.getValue());
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValChangeSubjectNo/findBooksetIdByPage",
                param, SelectOptionDto.class);
    }

    /**
     * 获取分页下拉框-科目
     * @param param
     * @return
     */
    public Page<SelectOptionDto> getSubjectId(Map<String, Object> param) {
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValChangeSubjectNo/findSubjectIdByPage",
                param, SelectOptionDto.class);
    }

    /**
     * 查询核算分类
     * @return
     */
    public Page<SelectOptionDto> selectDataDictType(Map<String, Object> param){
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValChangeSubjectNo/selectDataDictType",
                param, SelectOptionDto.class);
    }
}
