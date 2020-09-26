/*
 * 文件名：ReconcileSchemeInfo001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.valuationparametermanage.reconcilescheme.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ExcelUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeAssetConfigDto;
import com.joyintech.tams.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeInfo001Dto;
import com.joyintech.tams.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeNatureConfigDto;
import com.joyintech.tams.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeStructConfigDto;
import com.joyintech.tams.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeSubjectConfigDto;
import com.joyintech.tams.valuation.valuationparametermanage.reconcilescheme.service.ValReconcileSchemeInfo001Service;
import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 对账方案管理Controller
 * 
 * @author weipeng
 * @version 1.0 2018年11月13日
 * @see ValReconcileSchemeInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("ValReconcileSchemeInfo001")
@RequestMapping("/valuation/valuationparametermanage/reconcilescheme/ValReconcileSchemeInfo001")
public class ValReconcileSchemeInfo001Controller extends BaseController {

    /** 对账规则方案配置Service */
    @Autowired
    private ValReconcileSchemeInfo001Service reconcileSchemeInfo001Service;

    /**
     * 上传空间进度条Service
     */
    @Autowired
    private ProgressBarService progressBarService;

    /**
     * 对账方案管理 - 页面初始化 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // 获取方案名称--只查询估值表和余额表对账方案
//        List<String> schemeTypeList = new ArrayList<>();
//        schemeTypeList.add(DictCodeListExt1.配置方案类型_估值表对账方案.getValue());
//        schemeTypeList.add(DictCodeListExt1.配置方案类型_余额表对账方案.getValue());
//        Map<String, Object> params = new HashMap<>();
//        params.put("schemeTypeList", schemeTypeList);
//        // 只查询账套模板对应的方案
//        params.put("booksetType", DictCodeList.账套类别_账套模板.getValue());
//        List<SelectOptionDto> options = reconcileSchemeInfo001Service.getSchemeInfoList(params);
//        model.addAttribute("SCHEME_NAME", JsonUtils.objectArray2Json(options.toArray()));
        return super.init(model);
    }
    
    /**
     * 检索方案名称左侧列表
     * 
     * @return 检索方案名称左侧列表
     * @throws Exception 
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findSchemeList")
    public Page<ValReconcileSchemeInfo001Dto> findSchemeList() throws Exception {
        List<String> schemeTypeList = new ArrayList<>();
        schemeTypeList.add(DictCodeListExt1.配置方案类型_估值表对账方案.getValue());
        schemeTypeList.add(DictCodeListExt1.配置方案类型_余额表对账方案.getValue());
        Map<String, Object> params = new HashMap<>();
        params.put("schemeTypeList", schemeTypeList);
        // 只查询账套模板对应的方案
        params.put("booksetType", DictCodeList.账套类别_账套模板.getValue());
        params.put("searchKey", this.getParam("searchKey"));
        return reconcileSchemeInfo001Service.findSchemeList(params);
    }

    /**
     * 初始化方案名称下拉框
     * 
     * @return 方案名称List
     * @throws Exception 错误
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getSchemeInfo")
    public AjaxResult getSchemeInfo() {
        AjaxResult ajaxResult = new AjaxResult();
        String booksetSchemeId = this.getParam("booksetSchemeId");
        if (StringUtil.isNotEmpty(booksetSchemeId)) {
            ValReconcileSchemeInfo001Dto dto = reconcileSchemeInfo001Service.getSchemeInfo(
                booksetSchemeId);
            ajaxResult.setData(dto);
        }
        return ajaxResult;
    }

    // /**
    // * 初始化结构映射
    // *
    // * @return 结构映射明细
    // * @since 1.0
    // */
    // @ResponseBody
    // @RequestMapping("getSchemeInfoList")
    // public List<SelectOptionDto> getSchemeInfoList() {
    // return reconcileSchemeInfo001Service.getSchemeInfoList();
    // }

    /**
     * 初始化结构映射
     * 
     * @return 结构映射明细
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("initStructGrid")
    public List<ValReconcileSchemeStructConfigDto> initStructGrid() {
        return reconcileSchemeInfo001Service.initStructGrid(RequestUtil.getPageParam());
    }

    /**
     * 初始化科目映射
     * 
     * @return 科目映射明细
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("initSubjectGrid")
    public List<ValReconcileSchemeSubjectConfigDto> initSubjectGrid() {
        return reconcileSchemeInfo001Service.initSubjectGrid(RequestUtil.getPageParam());
    }

    /**
     * 初始化科目性质映射
     * 
     * @return 科目映射明细
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("initNatureGrid")
    public List<ValReconcileSchemeNatureConfigDto> initNatureGrid() {
        return reconcileSchemeInfo001Service.initNatureGrid(RequestUtil.getPageParam());
    }

    /**
     * 初始化资产映射
     * 
     * @return 资产映射明细
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("initAssetGrid")
    public List<ValReconcileSchemeAssetConfigDto> initAssetGrid() {
        return reconcileSchemeInfo001Service.initAssetGrid(RequestUtil.getPageParam());
    }

    /**
     * 删除方案
     * 
     * @return 删除结果
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("deleteScheme")
    public AjaxResult deleteScheme() {
        String schId = this.getParam("schId");
        String booksetId = this.getParam("booksetId");
        String msg = getMessage("COMMON_I_0001");
        try {
            reconcileSchemeInfo001Service.deleteBySchemeId(schId, booksetId);
        }
        catch (Exception e) {
            getLogger().error("数据删除失败!", e);
            return new AjaxResult(e.getMessage());
        }

        return new AjaxResult(msg);
    }

    /**
     * 画面check
     * 
     * @param dto ReconcileSchemeInfo002DtoValidator
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid @RequestBody ValReconcileSchemeInfo001Dto dto,
                            BindingResult result) {
        return commonCheck(result, OPERATE_TYPE_SAVE);
    }

    /**
     * 保存
     * 
     * @param schemeDto 方案DTO
     * @param result 保存结果
     * @return 保存结果
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
    public AjaxResult save(@RequestBody ValReconcileSchemeInfo001Dto schemeDto,
                           BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxResult(result);
        }
        AjaxResult ajaxResult = new AjaxResult();
        try {
            // String oldDefaultschId = schemeDto.getOldDefaultSchemeId();
            // if (StringUtil.isNotEmpty(oldDefaultschId)) {
            // reconcileSchemeInfo001Service.updateDefaultScheme(oldDefaultschId,
            // DictCodeList.是否_否.getValue());
            // }

            // 去重处理
            schemeDto.setStructConfigList(
                schemeDto.getStructConfigList().stream().distinct().collect(Collectors.toList()));
            schemeDto.setSubjectConfigList(
                schemeDto.getSubjectConfigList().stream().distinct().collect(Collectors.toList()));
            schemeDto.setNatureConfigList(
                schemeDto.getNatureConfigList().stream().distinct().collect(Collectors.toList()));
            schemeDto.setAssetConfigList(
                schemeDto.getAssetConfigList().stream().distinct().collect(Collectors.toList()));
            // 保存数据
            reconcileSchemeInfo001Service.doSaveOrUpdate(schemeDto);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(this.getMessage("VAL_COMMON_I_0008") + e.getMessage(),
                MessageType.错误消息);
        }
        ajaxResult.setMessage(this.getMessage("VAL_COMMON_I_0006"), MessageType.提示消息);
        return ajaxResult;
    }

    /**
     * 上传文件
     * 
     * @param request request
     * @param response response
     * @param model model
     * @return 返回结果
     * @throws Exception
     * @throws IOException
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("uploadFile")
    public AjaxResult uploadFile(HttpServletRequest request, HttpServletResponse response,
                                 ModelMap model)
        throws Exception {
        AjaxResult res = new AjaxResult();
        ValReconcileSchemeInfo001Dto infoDto = new ValReconcileSchemeInfo001Dto();

        // 文件名
        String fileName = request.getParameter("fileName");
        String schemeType = request.getParameter("schemeType");
        String booksetId = request.getParameter("booksetId");
        if (!StringUtil.isNotEmpty(schemeType)) {
            String msg = "方案类型不能为空";
            res = new AjaxResult(msg, MessageType.错误消息);
            progressBarService.updatePercent("ValReconcileSchemeInfo001",
                ProgressBarService.FILE_UPLOAD_ID, 1D, false);
            return res;
        }
        if (!StringUtil.isNotEmpty(fileName)) {
            String msg = "文件上传失败";
            res = new AjaxResult(msg, MessageType.错误消息);
            progressBarService.updatePercent("ValReconcileSchemeInfo001",
                ProgressBarService.FILE_UPLOAD_ID, 1D, false);
            return res;
        }
        infoDto.setFileName(fileName);
        // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
        try {
            List<ValReconcileSchemeStructConfigDto> structList = getStructList(schemeType,
                fileName);

            List<ValReconcileSchemeSubjectConfigDto> subjectList = getSubjectList(fileName,
                booksetId);

            List<ValReconcileSchemeNatureConfigDto> natureList = getNatureList(fileName,
                booksetId);

            List<ValReconcileSchemeAssetConfigDto> assetList = getAssetList(fileName);
            infoDto.setStructConfigList(structList);
            infoDto.setSubjectConfigList(subjectList);
            infoDto.setNatureConfigList(natureList);
            infoDto.setAssetConfigList(assetList);
            infoDto.setFileName(fileName);
            res.setData(infoDto);
            res.setMessage(getMessage("VAL_EXCELIMP_E_0022"), MessageType.提示消息);
            progressBarService.updatePercent("ValReconcileSchemeInfo001",
                ProgressBarService.FILE_UPLOAD_ID, 1D, false);
        }
        catch (Exception e) {
            e.printStackTrace();
            String msg = "请上传正确的映射关系表";
            res = new AjaxResult(msg, MessageType.错误消息);
            progressBarService.updatePercent("ValReconcileSchemeInfo001",
                ProgressBarService.FILE_UPLOAD_ID, 1D, false);
            return res;
        }

        return res;
    }

    /**
     * 获取结构映射List
     * 
     * @param schemeType 方案类型
     * @param myGZBCloDictVCMap 本系统估值表列指标map
     * @param myYEBCloDictVCMap 本系统余额表列指标map
     * @param blockDictVCMap 区块类型map
     * @param file 上传文件
     * @return 结构映射List
     * @throws Exception 错误
     * @throws IOException IO错误
     * @since 1.0
     */
    private List<ValReconcileSchemeStructConfigDto> getStructList(String schemeType,
                                                                  String fileName)
        throws Exception, IOException {

        // 导入文件数据列表,
        ExcelUtil excelUtil = ExcelUtil.build(FileNameUtil.getUploadFilePath(fileName), null);
        // 获取第一个sheet数据
        List<List<String>> structFileList = excelUtil.readToList(0, 1, 0);
        List<ValReconcileSchemeStructConfigDto> structList = new ArrayList<ValReconcileSchemeStructConfigDto>();
        if (null != structFileList) {
            for (List<String> list : structFileList) {
                if (StringUtil.isEmptyList(list)) {
                    break;
                }
                ValReconcileSchemeStructConfigDto dto = new ValReconcileSchemeStructConfigDto();
                String blockTypeName = list.get(0);
                dto.setBlockType(getValueFromDict(DictCode.估值对账配置_区块类型, blockTypeName));
                String myColRemark = list.get(1);
                String myColId = null;
                if (DictCodeListExt1.配置方案类型_估值表对账方案.getValue().equals(schemeType)) {
                    myColId = getValueFromDict(DictCode.本系统估值表列指标, myColRemark);
                }
                else if (DictCodeListExt1.配置方案类型_余额表对账方案.getValue().equals(schemeType)) {
                    myColId = getValueFromDict(DictCode.本系统余额表列指标, myColRemark);
                }

                String othersideColId = list.get(2);
                if (StringUtil.isEmpty(myColId) || StringUtil.isEmpty(othersideColId)) {
                    continue;
                }
                dto.setMyColId(myColId);
                dto.setMyColRemark(myColRemark);
                dto.setOthersideColId(othersideColId);
                structList.add(dto);
            }
        }
        return structList;
    }

    /**
     * 获取科目映射List
     * 
     * @param blockDictVCMap 区块类型Map
     * @param yesNoDictVCMap 是否CODE-VALUE
     * @param yesNoDictCVMap 是否VALUE-CODE
     * @param file 上传文件
     * @return 科目映射List
     * @throws Exception 错误
     * @throws IOException IO错误
     * @since 1.0
     */
    private List<ValReconcileSchemeSubjectConfigDto> getSubjectList(String fileName,
                                                                    String booksetId)
        throws Exception, IOException {
        Map<String, BokSubject> bokSubjectMap = reconcileSchemeInfo001Service.getAllBokSubjectMap(booksetId);

        // 导入文件数据列表,第二个sheet
        ExcelUtil excelUtil = ExcelUtil.build(FileNameUtil.getUploadFilePath(fileName), null);
        List<List<String>> subjectFileList = excelUtil.readToList(1, 1, 0);
        List<ValReconcileSchemeSubjectConfigDto> subjectList = new ArrayList<ValReconcileSchemeSubjectConfigDto>();
        if (null != subjectFileList) {
            for (List<String> list : subjectFileList) {
                if (StringUtil.isEmptyList(list)) {
                    break;
                }
                ValReconcileSchemeSubjectConfigDto dto = new ValReconcileSchemeSubjectConfigDto();
                String mySubjectId = list.get(0);
                String othSubjectId = list.get(1);
                if (StringUtil.isEmpty(mySubjectId) || StringUtil.isEmpty(othSubjectId)) {
                    continue;
                }
                else {
                    BokSubject bokSubject = bokSubjectMap.get(mySubjectId);
                    if (bokSubject != null) {
                        dto.setMySubjectName(bokSubject.getSubjectName());
                        dto.setMyIsGenSubs(bokSubject.getGenDetsubFlag());
                    }
                }
                dto.setMySubjectId(mySubjectId);
                dto.setOthersideSubjectId(othSubjectId);
                dto.setOthersideSubjectName(list.get(4));
                String isGenSubsView = list.get(5);
                dto.setIsGenSubs(getValueFromDict(DictCode.是否, isGenSubsView));
                String blockTypeName = list.get(6);
                dto.setBlockType(getValueFromDict(DictCode.估值对账配置_区块类型, blockTypeName));
                subjectList.add(dto);
            }
        }
        return subjectList;
    }

    /**
     * 获取科目性质映射List
     * 
     * @param fileName 文件名
     * @param booksetId 账套代码
     * @return 科目性质映射List
     * @throws Exception 错误
     * @throws IOException IO错误
     * @since 1.0
     */
    private List<ValReconcileSchemeNatureConfigDto> getNatureList(String fileName,
                                                                  String booksetId)
        throws Exception, IOException {
//        Map<String, BokSubject> bokSubjectMap = reconcileSchemeInfo001Service.getAllBokSubjectMap(
//            booksetId);
        // 导入文件数据列表,第二个sheet
        ExcelUtil excelUtil = ExcelUtil.build(FileNameUtil.getUploadFilePath(fileName), null);
        List<List<String>> subjectFileList = excelUtil.readToList(2, 1, 0);
        List<ValReconcileSchemeNatureConfigDto> natureList = new ArrayList<ValReconcileSchemeNatureConfigDto>();
        if (null != subjectFileList) {
            for (List<String> list : subjectFileList) {
                if (StringUtil.isEmptyList(list)) {
                    break;
                }
                ValReconcileSchemeNatureConfigDto dto = new ValReconcileSchemeNatureConfigDto();
                String othSubjectId = list.get(0);
                if (StringUtil.isEmpty(othSubjectId)) {
                    continue;
                }
                dto.setOthersideSubjectId(othSubjectId);
                dto.setOthersideSubjectName(list.get(1));
                dto.setMyNatureName(list.get(2));
                dto.setMyNatureId(list.get(3));
//                String isGenSubsView = list.get(4);
//                dto.setIsGenSubs(getValueFromDict(DictCode.是否, isGenSubsView));
                // 全部默认为是，不用客户填写了
                dto.setIsGenSubs(DictCodeList.是否_是.getValue());
                String blockTypeName = list.get(4);
                dto.setBlockType(getValueFromDict(DictCode.估值对账配置_区块类型, blockTypeName));
                natureList.add(dto);
            }
        }
        return natureList;
    }

    /**
     * 获取资产映射List
     * 
     * @param file 上传文件
     * @return 资产映射List
     * @throws Exception 错误
     * @throws IOException IO错误
     * @since 1.0
     */
    private List<ValReconcileSchemeAssetConfigDto> getAssetList(String fileName)
        throws Exception, IOException {

        // 导入文件数据列表,第三个sheet
        ExcelUtil excelUtil = ExcelUtil.build(FileNameUtil.getUploadFilePath(fileName), null);
        List<List<String>> subjectFileList = excelUtil.readToList(3, 1, 0);
        List<ValReconcileSchemeAssetConfigDto> assetList = new ArrayList<ValReconcileSchemeAssetConfigDto>();
        if (null != subjectFileList) {
            for (List<String> list : subjectFileList) {
                if (StringUtil.isEmptyList(list)) {
                    break;
                }
                String myAssetId = list.get(0);
                String myOthAssetId = list.get(1);
                if (StringUtil.isEmpty(myAssetId) || StringUtil.isEmpty(myOthAssetId)) {
                    continue;
                }
                ValReconcileSchemeAssetConfigDto dto = new ValReconcileSchemeAssetConfigDto();
                dto.setMyAssetId(list.get(0));
                dto.setOthersideAssetId(list.get(1));
                assetList.add(dto);
            }
        }
        return assetList;
    }

    /**
     * 从数据字典中根据对应的名称获取code值
     * 
     * @param dictCode
     * @param dictName
     * @return
     * @see
     * @since 1.0
     */
    private String getValueFromDict(DictCode dictCode, String dictName) {
        if (StringUtils.isEmpty(dictName)) {
            return "";
        }
        SelectDto selectDto = this.getSelectDataFromDataDic(dictCode, null);
        if (selectDto != null) {
            for (SelectOptionDto dto : selectDto.getOptions()) {
                if (StringUtil.equals(dictName, dto.getText())) {
                    return dto.getId();
                }
            }
        }
        return dictName;
    }
}
