/*
 * 文件名：EquityBusinessDetails001.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：zyf
 * 修改时间：2017年9月16日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.submitmaintenance.businessdetailed.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.submitmaintenance.businessdetailed.dto.EquiteBusiDetails001Dto;
import com.joyintech.tams.report.submitmaintenance.businessdetailed.service.EquityBusiDetails001Service;
import com.joyintech.tams.report.submitmaintenance.businessdetailed.service.EquityBusinessDetailsMService;
import com.joyintech.tams.report.submitmanagement.equitybusiinforegist.service.EquityBusiInfoRegist001Service;
import com.joyintech.tams.systemtools.util.CommonUtil;
/**
 *  投资者明细信息维护界面Controller
 * 〈功能详细描述〉
 * @author zyf
 * @version 1.0 2017年9月16日
 * @see EquityBusinessDetails001Controller
 * @since 1.0
 */
@Controller
@PageId("SvrEquityBusinessDetails001")
@RequestMapping("/svreport/submitmaintenance/businessdetailed/SvrEquityBusinessDetails001")
public class EquityBusinessDetails001Controller extends BaseController{
    
    /**
     * EquityBaseInfoBusinessDetails001Service
     */
    @Autowired
    private EquityBusiDetails001Service equityBusiDetails001Service;

    @Autowired
    private EquityBusinessDetailsMService equityBusinessDetailsMService;
    @Autowired
    private EquityBusiInfoRegist001Service equityBusiInfoRegist001Service;
    /**
     * 页面初始化
     * {@inheritDoc}
     */
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.维护数据状态,DictCode.业务种类,DictCode.投资者明细渠道);
        if(RequestUtil.getPageParam().containsKey("dataStatus")){
            model.addAttribute("isFromMain","01");
        }
        else{
            model.addAttribute("isFromMain","02");
        }
        Map<String, Object> map = RequestUtil.getPageParam();
        if (RequestUtil.getPageParam().containsKey("interfType")) {
            model.addAttribute("status", DictCodeListExt1.维护数据状态_待处理.getValue());
            model.addAttribute("searchDataStart", map.get("searchDataStart"));
			model.addAttribute("searchDataEnd", map.get("searchDataEnd"));
            model.addAttribute("isFromMain", "01");
        }
        String myOrgCode=CommonUtil.getOrgCode();
        if(StringUtil.isNotEmpty(myOrgCode)){
            model.addAttribute("MY_AGENCY_CODE",myOrgCode);
        }
      //业务确认日-市场节假日表中有值则取前一个工作日，无则取前一天
        String dateStr = null;
        Date ywDate = equityBusiInfoRegist001Service.getLastWeekDay(
        		CacheUtil.getSysParam(SVReportGlobalConstant.SVR_CALENDAR_ID),DateUtil.getCurrentDate());
        if(StringUtil.isNotEmpty(ywDate)){
        	dateStr = DateUtil.format(ywDate);
        }else{
        	dateStr = DateUtil.plusDays(DateUtil.getCurrentDate(), -1);
        }
        String dayselectRange = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY,
        		SVReportGlobalConstant.SVR_EQUITY_CONF_DATE_SELECT_RANGE);
        model.addAttribute("DATE_START",dateStr);
        model.addAttribute("DATE_END",dateStr);
        model.addAttribute("DAY_SELECT_RANGE",dayselectRange);
        return super.init(model);
    }
    
    /**
     * 产品名称下拉框
     * @return 产品名称列表
     */
    @RequestMapping("getPrdName")
    @ResponseBody
    public List<SelectOptionDto> getPrdName() {
        return equityBusinessDetailsMService.queryPrdNames(RequestUtil.getPageParam());
    }
    
    /**
     * 投资者明细信息分页查询列表
     * 
     * @return Page
     */
    @ResponseBody
    @RequestMapping(ReportGlobalConstant.SEARCH_PAGE)
    public Page<EquiteBusiDetails001Dto> search() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return equityBusinessDetailsMService.searchForPage(param);
    }
    
    /**
     * 编辑删除前的check判断
     * @param dto
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) EquiteBusiDetails001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 删除数据
     * <p>
     * 逻辑删除数据，并返回处理结果
     * </p>
     * 
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = ACTION_DELETE, method = RequestMethod.POST)
    public AjaxResult doDelete(@RequestBody EquiteBusiDetails001Dto dto) {
        AjaxResult wb = new AjaxResult(this.getMessage("REPORT_COMMON_I_0007"));
        equityBusinessDetailsMService.doDelete(dto);
        return wb;
    }
    
    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "checkConfirmMsgForImportData", method = RequestMethod.POST)
    public AjaxResult checkConfirmMsgForImportData(String fileName){
        return equityBusinessDetailsMService.checkConfirmMsgForImportData(fileName);
    }
    
    /**
     * 导入数据文件
     * 
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @ResponseBody
    @RequestMapping("/importFile")
    public List<ExcelValidDto> importData(String fileName) {
        return this.equityBusinessDetailsMService.importData(fileName,this.getPageId());
    }
    
    /**
     *导入代销数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "checkConfirmMsgForChbImportData", method = RequestMethod.POST)
    public AjaxResult checkConfirmMsgForChbImportData(String fileName) {
        return equityBusinessDetailsMService.checkConfirmMsgForChbImportData(fileName);
    }
    
    /**
     * 导入代销数据文件
     * @param fileName
     * @return
     */
    @ResponseBody
    @RequestMapping("/chbImportFile")
    public List<ExcelValidDto> importChbData(String fileName){
    	return this.equityBusinessDetailsMService.importChbData(fileName,this.getPageId());
    }
    
    /**
     * 导出excel
     *
     * @return 导出
     */
    @RequestMapping(value = ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception{
//    	if(RequestUtil.getPageParam().containsKey("keyList")){
//        	List<EquiteBusiDetails001Dto> dtoList = this.equityBusinessDetailsMService.queryReportList(RequestUtil.getParameters(getRequest()));
//        	File outFile = null;
//            outFile = equityBusiDetails001Service.makeFile(
//                "/com/joyintech/tams/report/submitmaintenance/businessdetailed/conf/export_conf.xml", dtoList);
//            return ExportUtil.export(
//                this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
//                                     + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
//                                     + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
//                outFile, true);
//    	}else{
//    		return equityBusiDetails001Service.export(this.getPageId(), RequestUtil.getPageParam());
//    	}
    	String datas = this.getParam("datas");
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        List<EquiteBusiDetails001Dto> listdto;
        if (datas == null) {
            listdto = (List<EquiteBusiDetails001Dto>)equityBusinessDetailsMService.queryReportList(paraMap);
        }
        else {
            listdto = JsonUtils.json2List(datas, EquiteBusiDetails001Dto.class);
        }
        File outFile = equityBusiDetails001Service.makeFile(
            "/com/joyintech/tams/report/submitmaintenance/businessdetailed/conf/export_conf.xml",
            listdto);
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date())
                                 + SystemGlobalConstant.CLOSE_PARENTHESIS
                                 + SystemGlobalConstant.POINT
                                 + FileNameUtil.getFileExtName(outFile),
            outFile, true);

    }
    
}