/*
 * 文件名：ValSubDevelopment001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：zwq
 * 修改时间：2020年12月30日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */
package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valbasicsubadjust.controller;

import com.google.common.collect.Lists;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.systemtools.service.SystemToolService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valbasicsubadjust.dto.ValBasicSubAdjust001Dto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valbasicsubadjust.service.ValBasicSubAdjust001Service;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valsubdevelopment.dto.ValSubDevelopment001Dto;
import com.joyintech.tools.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.*;

@Controller
@PageId("ValBasicSubAdjust001")
@RequestMapping("/valuation/dailyoperation/dailyaccountmake/valbasicsubadjust/ValBasicSubAdjust001")
public class ValBasicSubAdjust001Controller extends BaseController {
    @Autowired
    private ValBasicSubAdjust001Service valBasicSubAdjust001Service;

    @Autowired
    private SystemToolService systemToolService;

    /**
     * 重写init方法，加载页面
     */
    @Override
    public String init(Model model) {
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody ValBasicSubAdjust001Dto dto){
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        if (!StringUtil.isNotEmpty(dto.getSubjectNo())){
            errorList.add(new ValidMessage("科目号为必填项！"));
        }
        if (!StringUtil.isNotEmpty(dto.getBaseSubjectNature())){
            errorList.add(new ValidMessage("调整基础科目性质为必填项！"));
        }
        ajaxResult.setErrorList(errorList);
        return ajaxResult;
    }

    /**
     * 查询账套代码
     */
    @ResponseBody
    @RequestMapping("selectBooksetIds")
    public Page<SelectOptionDto> selectBooksetIds(){
        Map<String, Object> param = RequestUtil.getPageParam();
        Page<SelectOptionDto> booksetIds = valBasicSubAdjust001Service.selectBooksetIds(param);
        return booksetIds;
    }

    /**
     * 查询科目号
     */
    @ResponseBody
    @RequestMapping("selectSubjectNo")
    public Page<SelectOptionDto> selectSubjectNo(){
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("booksetType", DictCodeList.账套类别_账套模板.getValue());
        Page<SelectOptionDto> subjectNo = valBasicSubAdjust001Service.selectSubjectNo(param);
        return subjectNo;
    }

    /**
     * 查询基础科目性质
     */
    @ResponseBody
    @RequestMapping("selectBaseSubjectNature")
    public Page<SelectOptionDto> selectBaseSubjectNature(){
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("acctStandardId", DictCodeListExt2.会计准则_企业准则.getValue());
        Page<SelectOptionDto> baseSubjectNature = valBasicSubAdjust001Service.selectBaseSubjectNature(param);
        return baseSubjectNature;
    }

    @ResponseBody
    @RequestMapping("submit")
    public AjaxResult submit(@RequestBody ValBasicSubAdjust001Dto dto){
        AjaxResult ajaxResult = new AjaxResult();

        dto.setUpdateUser(LoginUserUtil.getUserName());
        dto.setUpdateTime(DateUtil.now());
        // 账务历史表数据更新
        valBasicSubAdjust001Service.updateBokBookdetailAll(dto);
        // 账务当前表数据更新
        valBasicSubAdjust001Service.updateBokBookdetailCurrent(dto);
        // 明细科目数据更新
        valBasicSubAdjust001Service.updateBokSubjectDetail(dto);
        ajaxResult.setMessage("调整成功！");
        return ajaxResult;
    }


    /**
     * add by zwq 2021-1-14
     * 脚本生成
     */
    @RequestMapping("generateSql")
    public ResponseEntity<byte[]> generateSql() throws IOException {
        AjaxResult ajaxResult = new AjaxResult();
        Map<String, Object> param = RequestUtil.getPageParam();
        String baseSubjectNature = (String) param.get("baseSubjectNature");       // 基础科目性质
        String subjectNo = (String)param.get("subjectNo");       // 科目号
        String str = (String)param.get("booksetIds");
        List<String> booksetIds = null;
        if (StringUtil.isNotEmpty(str)){
            booksetIds = Arrays.asList(str.split(","));          // 账套代码
        }

        // 执行sql
        // 1.账务明细历史表：基础科目性质字段更新
        String resultSQl = "/**1.账务明细历史表：基础科目性质字段更新**/\n";
        resultSQl += "UPDATE BOK_BOOKDETAIL_ALL \n" +
                "\tSET BASE_SUBJECT_NATURE = '"+baseSubjectNature+"',\n" +
                "\t\tUPDATE_USER = 'sjzl',\n" +
                "\t\tUPDATE_TIME = SYSDATE\n" +
                "\tWHERE SUBJECT_NO = '"+subjectNo+"'";
        // 处理Oracle中的in语句超过1000的情况
        if (!StringUtil.isEmptyList(booksetIds)){
            List<List<String>> partitions = Lists.partition(booksetIds, 1000);
            resultSQl += "\n\tAND (";
            for (int j = 0; j < partitions.size(); j++) {
                List<String> partition = partitions.get(j);
                resultSQl += "BOOKSET_ID IN (";
                for (int i = 0; i < partition.size(); i++) {
                    String booksetIdTem = partition.get(i);
                    if (i == booksetIds.size() - 1) {
                        resultSQl += "'" + booksetIdTem + "'";
                    } else {
                        resultSQl += "'" + booksetIdTem + "',";
                    }
                }
                if (partition.size()>0&&partition.size()<=1000){
                    resultSQl += ")";
                }else {
                    resultSQl += ") OR ";
                }
            }
            resultSQl += ");\n";
        }else {
            resultSQl += ";";
        }

        // 2.账务明细当前表：基础科目性质字段更新
        resultSQl += "/**2.账务明细当前表：基础科目性质字段更新**/\n";
        resultSQl += "UPDATE BOK_BOOKDETAIL_CURRENT\n" +
                "\tSET BASE_SUBJECT_NATURE = '"+baseSubjectNature+"',\n" +
                "\t\tUPDATE_USER = 'sjzl',\n" +
                "\t\tUPDATE_TIME = SYSDATE\n" +
                "\tWHERE SUBJECT_NO = '"+subjectNo+"'";
        // 处理Oracle中的in语句超过1000的情况
        if (!StringUtil.isEmptyList(booksetIds)){
            List<List<String>> partitions = Lists.partition(booksetIds, 1000);
            resultSQl += " \n\tAND (";
            for (int j = 0; j < partitions.size(); j++) {
                List<String> partition = partitions.get(j);
                resultSQl += "BOOKSET_ID IN (";
                for (int i = 0; i < partition.size(); i++) {
                    String booksetIdTem = partition.get(i);
                    if (i == booksetIds.size() - 1) {
                        resultSQl += "'" + booksetIdTem + "'";
                    } else {
                        resultSQl += "'" + booksetIdTem + "',";
                    }
                }
                if (partition.size()>0&&partition.size()<=1000){
                    resultSQl += ")";
                }else {
                    resultSQl += ") OR ";
                }
            }
            resultSQl += ");\n";
        }else {
            resultSQl += ";";
        }

        // 3.明细科目数据表：基础科目性质字段更新
        resultSQl += "/**3.明细科目数据表：基础科目性质字段更新**/\n";
        resultSQl += "UPDATE BOK_SUBJECT_DETAIL\n" +
                "\tSET BASE_SUBJECT_NATURE = '"+baseSubjectNature+"',\n" +
                "\t\tUPDATE_USER = 'sjzl',\n" +
                "\t\tUPDATE_TIME = SYSDATE\n" +
                "\tWHERE SUBJECT_NO = '"+subjectNo+"'";
        // 处理Oracle中的in语句超过1000的情况
        if (!StringUtil.isEmptyList(booksetIds)){
            List<List<String>> partitions = Lists.partition(booksetIds, 1000);
            resultSQl += " \n\tAND (";
            for (int j = 0; j < partitions.size(); j++) {
                List<String> partition = partitions.get(j);
                resultSQl += "BOOKSET_ID IN (";
                for (int i = 0; i < partition.size(); i++) {
                    String booksetIdTem = partition.get(i);
                    if (i == booksetIds.size() - 1) {
                        resultSQl += "'" + booksetIdTem + "'";
                    } else {
                        resultSQl += "'" + booksetIdTem + "',";
                    }
                }
                if (partition.size()>0&&partition.size()<=1000){
                    resultSQl += ")";
                }else {
                    resultSQl += ") OR ";
                }
            }
            resultSQl += ");\n";
        }else {
            resultSQl += ";";
        }

        resultSQl += "\nCOMMIT;";
        return downloadSqlFile(resultSQl);
    }

    private ResponseEntity<byte[]> downloadSqlFile(String sql) throws IOException {
        // 新建文件
        String fileName = UUID.randomUUID().toString() + ".sql";
        String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
        String outPath = exportFilePath + "/" + fileName;
        File tempFile = new File(outPath);
        if (!tempFile.getParentFile().exists()) {
            tempFile.getParentFile().mkdirs();
        }
        File file = new File(outPath);
        file.createNewFile();

        // 往文件中写入数据
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            byte[] bytes = sql.getBytes();
            outputStream.write(bytes);
        }catch (Exception e){

        }finally {
            outputStream.close();
        }

        // 导出日期
        String sqlExportDate = DateUtil.format(new Date(), DateUtil.DEFAULT_DATE_FORMAT_EN);
        // 导出文件
        return ExportUtil.export("基础科目性质调整（慎用）执行脚本_"+sqlExportDate+".sql",file,true);
    }
}
