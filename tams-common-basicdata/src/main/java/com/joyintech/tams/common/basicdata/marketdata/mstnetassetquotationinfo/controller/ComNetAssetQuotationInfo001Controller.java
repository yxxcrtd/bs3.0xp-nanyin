/*
 * 文件名：ComNetAssetQuotationInfo001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年7月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.common.basicdata.marketdata.mstnetassetquotationinfo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.basicdata.marketdata.mstnetassetquotationinfo.dto.ComNetAssetQuotationInfo001Dto;
import com.joyintech.tams.common.basicdata.marketdata.mstnetassetquotationinfo.service.ComNetAssetQuotationInfo001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 净值型资产行情信息
 * 提供净值型资产行情信息的管理
 * @author liuwei
 * @version 1.0 2018年8月13日
 * @see ComNetAssetQuotationInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("ComNetAssetQuotationInfo001")
@RequestMapping("/common/basicdata/marketdata/mstnetassetquotationinfo/ComNetAssetQuotationInfo001")
public class ComNetAssetQuotationInfo001Controller extends BaseController{
    
    /**
     * 查询和修改controller
     */
    @Autowired
    private ComNetAssetQuotationInfo001Service mstnetassetquotationinfo001service;
    
    /**
     * 查询界面的初始化，日期默认显示平台日期
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("valDate",DateUtil.format(sysDate));
        // 画面元素是否显示
     	PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
     	pageElementConfigDto.setPageId(this.getPageId());
     	Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto); 
     	model.addAttribute("isEleShow", isEleShow);
        
        return super.init(model);
    }
    
    /**
     * 查询操作
     *
     * @return 查询结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ComNetAssetQuotationInfo001Dto> selectAllAsset()
        throws Exception {       
        return mstnetassetquotationinfo001service.selectForPage(RequestUtil.getPageParam());
    }
    
    /**
     * 资产名称的联想文本框事件
     * 
     * @return 查询的资产代码和资产名称列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getAssetName")
    public List<SuggestDto> getAssetName()
        throws Exception { 
        Map<String,Object> map = new HashMap<>();
        String param = super.getRequest().getParameter("param");   
        map.put("finProdName", param);
        map.put("PROFITTYPE_VALUE_01", DictCodeList.收益类型_净值型.getValue());
        map.put("PROFITTYPE_VALUE_03", DictCodeList.收益类型_货币型.getValue());
        map.put("TYPE_TOUGUAN_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        List<SuggestDto> temp = mstnetassetquotationinfo001service.getAssetName(map);
        return temp;
    }
    
    /**
     * 资产代码的联想文本框事件
     * 
     * @return 查询的资产代码和资产名称列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getAssetCode")
    public List<SuggestDto> getAssetCode()
        throws Exception {
        Map<String,Object> map = new HashMap<>();
        String param = super.getRequest().getParameter("param");   
        map.put("finProdMarketId", param);
        map.put("PROFITTYPE_VALUE_01", DictCodeList.收益类型_净值型.getValue());
        map.put("PROFITTYPE_VALUE_03", DictCodeList.收益类型_货币型.getValue());
        map.put("TYPE_TOUGUAN_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        List<SuggestDto> temp = mstnetassetquotationinfo001service.getAssetCode(map);
        return temp;
    }
    
    /**
     * 删除按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) ComNetAssetQuotationInfo001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 删除操作
     *
     * @param dto dto
     * @param br BindingResult
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({ValidateGroupDelete.class}) ComNetAssetQuotationInfo001Dto dto, BindingResult br)
        throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<ComNetAssetQuotationInfo001Dto> processParameter = new ProcessParameter<ComNetAssetQuotationInfo001Dto>();
        WorkFlowParameter<ComNetAssetQuotationInfo001Dto> params = new WorkFlowParameter<ComNetAssetQuotationInfo001Dto>();
        params.setPageId("ComNetAssetQuotationInfo002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item->item.getFinprodId());
        params.setBusinessType(DictCodeList.共通业务类型_计划类资产_净值型_信息.getValue());
        // 设置流程业务说明: "资产代码" + "(" + 资产代码 + ")" + "-"  + "估值日期" + "(" + 估值日期 + ")" 
        params.setComment(
            entity -> this.getMessage("ComNetAssetQuotationInfo001.assetCode") + "("+entity.getFinprodId() + ")"
            + this.getMessage("ComNetAssetQuotationInfo001.valuationDate") + "(" + DateUtil.format(entity.getValDate()) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = mstnetassetquotationinfo001service.start(
            processParameter, dto.getSelectedItem().toArray(new ComNetAssetQuotationInfo001Dto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }

    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() 
        throws Exception {
        return mstnetassetquotationinfo001service.export(this.getPageId(),RequestUtil.getParameters(getRequest()));
    }
    
    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "checkConfirmMsgForImportData", method = RequestMethod.POST)
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        return mstnetassetquotationinfo001service.checkConfirmMsgForImportData(fileName);
    }
    
    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importData(String fileName) {
        return this.mstnetassetquotationinfo001service.importData(fileName);
    }
}
