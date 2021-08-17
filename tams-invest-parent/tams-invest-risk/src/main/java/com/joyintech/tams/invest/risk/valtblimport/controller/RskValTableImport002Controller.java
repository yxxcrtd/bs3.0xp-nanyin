/*
 * 文件名：RskValTableImport002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年12月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.valtblimport.controller;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalrelateimport.dto.RskValRelateImport001Dto;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalrelateimport.service.RskValRelateImport002Service;
import com.joyintech.tams.invest.risk.valtblimport.service.RskValTableImportCommonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.risk.valtblimport.dto.RskValTableImportParamDto;
import com.joyintech.tams.invest.risk.valtblimport.dto.RskValtblAssetSubDto;
import com.joyintech.tams.invest.risk.valtblimport.service.RskValTableImportService;

/**
 * 估值表导入
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年12月29日
 * @see RskValTableImport002Controller
 * @since 1.0
 */
@Controller
@PageId("RskValTableImport002")
@RequestMapping("/invest/risk/valtblimport/RskValTableImport002")
public class RskValTableImport002Controller extends BaseController{

    @Autowired
    private RskValTableImportService rskValTableImportService;
    @Autowired
    private RskValRelateImport002Service rskValRelateImport002Service;
    @Autowired
    private RskValTableImportCommonService rskValTableImportCommonService;
    @Override
    public String init(Model model) {
        model.addAttribute("ptlId", this.getParam("ptlId"));
        model.addAttribute("valTableFileName", this.getParam("valTableFileName"));
        model.addAttribute("valDate", this.getParam("valDate"));
        model.addAttribute("structMapId", this.getParam("structMapId"));
        model.addAttribute("sumsubMapId", this.getParam("sumsubMapId"));
        model.addAttribute("assetSubMapId", this.getParam("assetSubMapId"));
        model.addAttribute("subjectCodeCol", this.getParam("subjectCodeCol"));
        model.addAttribute("startRow", this.getParam("startRow"));
        model.addAttribute("trimChars", this.getParam("trimChars"));
        model.addAttribute("filterId", this.getParam("filterId"));
        model.addAttribute("ptlName", this.getParam("ptlName"));
        return super.init(model);
    }
    
    
    /**
     * 预览初始化
     * <功能详细描述>
     * @param dto
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    
    @RequestMapping(value = "/initValTableInfo")
    @ResponseBody
    public List<RskValtblAssetSubDto> initValTableInfo(@RequestBody RskValTableImportParamDto dto) throws ClassNotFoundException, FileNotFoundException, InstantiationException, IllegalAccessException {
        List<RskValtblAssetSubDto> RskValtblAssetSubList = rskValTableImportService.viewValTable(dto);
        return RskValtblAssetSubList;
    }
    
    /**
     * 保存check
     * 组合估值日是否已经存在
     * @param dto
     * @param result
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public Map<String,Object> check(@RequestBody RskValTableImportParamDto dto, BindingResult result) {
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("ptlId", dto.getPtlId());
        paramMap.put("valDate", DateUtil.format(dto.getValDate()));
        int valCount = rskValTableImportService.selectCount(paramMap);
        paramMap.put("valCount", valCount);
        return paramMap;
    }
    
    /**
     * 保存
     * <功能详细描述>
     * @param dto
     * @return
     * @throws ParseException 
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody RskValtblAssetSubDto dto) throws ParseException {
        dto.setImpMode(DictCodeListExt1.导入方式_手工.getValue());
        List<String> errorList=rskValTableImportService.insert(dto);
        //处理成功的文件入库估值表导入日期记录表
        if(null!=dto.getSelectedItem()&&!dto.getSelectedItem().isEmpty()){
            String deptCode=(LoginUserUtil.getInstCode()!= null && LoginUserUtil.getInstCode().size()>0) ? LoginUserUtil.getInstCode().get(0):null;
            Date startDate = DateUtil.now();
            StringBuilder sb = new StringBuilder(StringUtils.EMPTY);
            for (String item : errorList) {
                sb.append("[");
                sb.append(item);
                sb.append("]");
                sb.append(GlobalConstant.HTML_NEWLINE);
            }
            //根据组合信息，查询是否有对应的映射信息
            RskValRelateImport001Dto import001Dto = rskValRelateImport002Service.selectInfoById(dto.getPtlId());
            //保存委托估值导入记录表
            String chbValSplitStr = CacheUtil.getSysParam(BussinessConstant.CHB_VAL_SPLIT_STR);
            String fileName=import001Dto.getName()+chbValSplitStr+DateUtil.format(dto.getValDate(),"yyyyMMdd");
            rskValTableImportCommonService.saveRskOutValtblImportHis(fileName,fileName, dto.getPtlId(), import001Dto.getName(), dto.getValDate(),
                    DictCodeListExt1.估值表自动解析处理状态_正常结束.getValue(),StringUtil.EMPTY,startDate,DateUtil.parse(DateUtil.getSysDate()),deptCode,sb.toString());
        }
        String eMsg="";
        if(!StringUtil.isEmptyList(errorList)){
        	for(String errorInfo:errorList){
            	eMsg+=StringUtil.isNotEmpty(errorInfo)?("["+errorInfo+"]"):"";
            }
        	return new AjaxResult(getMessage("BUSS_COM_E_0033")+GlobalConstant.HTML_NEWLINE+"以下资产未通过中债要素校验，自动加券失败，需在对应页面新建资产信息："+eMsg);
        }  
        return new AjaxResult(getMessage("BUSS_COM_E_0033"));
    }
    /**
     * 获取警告信息
     *
     * @param dto
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping(value = "/getTableInfo")
    @ResponseBody
    public AjaxResult getTableInfo(@RequestBody RskValTableImportParamDto dto) throws Exception {
        List<RskValtblAssetSubDto> RskValtblAssetSubList = rskValTableImportService.viewValTable(dto);
        if(dto.getWarnMap().get(dto.getAssetName()) > 0) {
            return new AjaxResult(dto.getAssetName() + "存在" + dto.getWarnMap().get(dto.getAssetName()) + "条科目未配置映射" , MessageType.警告消息);
        }
        else {
            return new AjaxResult();
        }
    }
}
