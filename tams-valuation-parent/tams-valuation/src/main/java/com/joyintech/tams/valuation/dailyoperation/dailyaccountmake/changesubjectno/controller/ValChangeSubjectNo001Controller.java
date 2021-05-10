/*
 * 文件名：ValIncomeStatisticsQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年10月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.changesubjectno.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.controller.FileUploadController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.bokdetailidupdate.service.BokDetailIdUpdate002Service;
import com.joyintech.tams.valuation.common.auxiliary.service.OperationAuxiliaryService;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.changesubjectno.dto.ChangeSubjectNo001Dto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.changesubjectno.dto.CheckResultDto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.changesubjectno.service.ValChangeSubjectNo001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * 辅助工具类-科目号变更
 * 
 * @author yanya
 * @version 1.0 2020-12-29
 * @see ValChangeSubjectNo001Controller
 * @since 1.0
 */
@Controller
@PageId("ValChangeSubjectNo001")
@RequestMapping("/valuation/dailyoperation/dailyaccountmake/changesubjectno/ValChangeSubjectNo001")
public class ValChangeSubjectNo001Controller extends BaseController {


    @Autowired
    ValChangeSubjectNo001Service valChangeSubjectNo001Service;
    @Autowired
    BokDetailIdUpdate002Service bokDetailIdUpdate002Service;


    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.科目号变更处理方式);
        // add by zwq 2021-01-05 start
        this.setSelectOptionToModel(model,DictCode.金融产品类型);
        this.setSelectOptionToModel(model,DictCode.交易场所);
        this.setSelectOptionToModel(model,DictCode.是否);
        // add by zwq 2021-01-05 end
        model.addAttribute("CHANGE_SUBJECT_TYPE_01", DictCodeListExt3.科目号变更处理方式_默认.getValue());
        model.addAttribute("CHANGE_SUBJECT_TYPE_02", DictCodeListExt3.科目号变更处理方式_批量.getValue());
        return super.init(model);
    }

    /**
     * 所属账套下拉框获取
     * @return 返回的数据
     * @exception Exception
     * @since 1.0
     */
    @RequestMapping("/findBooksetId")
    @ResponseBody
    public Page<SelectOptionDto> findBooksetId(){
        Map<String, Object> param = RequestUtil.getPageParam();
        //param.put("noBooksetType", DictCodeList.账套类别_账套模板.getValue());
        return valChangeSubjectNo001Service.findBooksetId(param);
    }

    /**
     * 检索科目代码 支持联动
     * @return 账套科目代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getSubjectId")
    public Page<SelectOptionDto> getSubjectId() throws Exception {
        return valChangeSubjectNo001Service.getSubjectId(RequestUtil.getPageParam());
    }
    /**
     * 科目号变更前校验
     * @param dto
     * @param result
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid ChangeSubjectNo001Dto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return new AjaxResult();
    }


    /**
     * 科目号变更
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("change")
    public AjaxResult change(@RequestBody ChangeSubjectNo001Dto dto) {
       return valChangeSubjectNo001Service.change(dto);
    }

    /**
     * 科目信息变更脚本生成
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("scriptGeneration")
    public ResponseEntity<byte[]> scriptGeneration() throws IOException {
        ChangeSubjectNo001Dto dto = new ChangeSubjectNo001Dto();
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        String sqlName = "科目信息变更脚本生成脚本";
        sqlName = sqlName + DateUtil.getSysDate();
        dto.setChangeSubjectType(paraMap.get("changeSubjectType") == null ? "" : paraMap.get("changeSubjectType").toString());
        dto.setBooksetIds(paraMap.get("booksetIds") == null ? "" : paraMap.get("booksetIds").toString());
        dto.setOriSubjectNo(paraMap.get("oriSubjectNo") == null ? "" : paraMap.get("oriSubjectNo").toString());
        dto.setChangeSubjectNo(paraMap.get("changeSubjectNo") == null ? "" : paraMap.get("changeSubjectNo").toString());
        dto.setChangeSubjectName(paraMap.get("changeSubjectName") == null ? "" : paraMap.get("changeSubjectName").toString());
        dto.setFileName(paraMap.get("fileName") == null ? "" : paraMap.get("fileName").toString());
        dto.setFinprodType(paraMap.get("finprodType") == null ? "" : paraMap.get("finprodType").toString());
        dto.setType2(paraMap.get("type2") == null ? "" : paraMap.get("type2").toString());
        dto.setTradeMarket(paraMap.get("tradeMarket") == null ? "" : paraMap.get("tradeMarket").toString());
        dto.setIsQtzq(paraMap.get("isQtzq") == null ? "" : paraMap.get("isQtzq").toString());
        AjaxResult ajaxResult = valChangeSubjectNo001Service.scriptGeneration(dto);
        CheckResultDto data = (CheckResultDto) ajaxResult.getData();
        return bokDetailIdUpdate002Service.downloadSqlFile(data.getExecSql(),sqlName);
    }



    /**
     * 科目信息变更脚本生成批量【导入】
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("scriptGenerationUpload")
    public ResponseEntity<byte[]> scriptGenerationUpload() throws IOException {
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        // 文件名
        String fileName = paraMap.get("fileName") == null ? "" : paraMap.get("fileName").toString();
        //处理方式
        String changeSubjectType = paraMap.get("changeSubjectType") == null ? "" : paraMap.get("changeSubjectType").toString();
        ChangeSubjectNo001Dto dto = new ChangeSubjectNo001Dto();
        String sqlName = "科目信息变更执行脚本";
        sqlName = sqlName + DateUtil.getSysDate();
        dto.setFileName(fileName);
        dto.setChangeSubjectType(changeSubjectType);
        AjaxResult ajaxResult = valChangeSubjectNo001Service.scriptGeneration(dto);
        CheckResultDto data = (CheckResultDto) ajaxResult.getData();
        return bokDetailIdUpdate002Service.downloadSqlFile(data.getExecSql(),sqlName);
    }

    /**
     * 模板文件下载
     *
     * @return 将系统所有模板文件进行打包下载
     * @throws IOException 异常
     */
    @RequestMapping(value = "downTemplate")
    public ResponseEntity<byte[]> downTemplate() throws IOException {
        String tplName = "ValChangeSubjectNo001.xls";
        String downName = "科目号变更导入模板.xls";
        File tplFile = new File(FileNameUtil.getTplFile(tplName));
        InputStream in = null;
        if (!tplFile.exists()) {
            in = FileUploadController.class.getResourceAsStream("/tpl/" + tplFile.getName());
        } else {
            in = new FileInputStream(tplFile);
        }
        return ExportUtil.export(downName, in);

    }

    /**
     * 上传
     * 不作任何处理
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("uploadFile")
    public AjaxResult uploadFile(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        // 文件名
        String fileName = request.getParameter("fileName");
        //处理方式
        String changeSubjectType = request.getParameter("changeSubjectType");
        // valChangeSubjectNo001Service.uploadFile(fileName);
        ChangeSubjectNo001Dto dto = new ChangeSubjectNo001Dto();
        dto.setFileName(fileName);
        dto.setChangeSubjectType(changeSubjectType);
        return valChangeSubjectNo001Service.change(dto);
    }

    @ResponseBody
    @RequestMapping("uploadFileTest")
    public AjaxResult uploadFileTest(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        // 文件名
        String fileName = request.getParameter("fileName");
        //处理方式
        String changeSubjectType = request.getParameter("changeSubjectType");
        return valChangeSubjectNo001Service.uploadFile(fileName);
    }

    /**
     * 查询核算分类
     * @return
     */
    @ResponseBody
    @RequestMapping("selectDataDictType")
    public Page<SelectOptionDto> selectDataDictType(){
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("itemCode",param.get("finprodType"));
        Page<SelectOptionDto> dataDictType = valChangeSubjectNo001Service.selectDataDictType(param);
        return dataDictType;
    }

}
