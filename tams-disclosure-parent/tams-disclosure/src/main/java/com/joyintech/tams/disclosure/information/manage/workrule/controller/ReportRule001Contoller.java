/*
 * 文件名：ValProductInfoQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.manage.workrule.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCode;
import com.joyintech.tams.disclosure.common.common.service.BaseCommonService;
import com.joyintech.tams.disclosure.information.manage.workrule.dto.ReportRule001Dto;
import com.joyintech.tams.disclosure.information.manage.workrule.service.ReportRule001Service;
import com.joyintech.tams.disclosure.information.manage.workrule.service.ReportRule002Service;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.framework.util.CacheUtil;
/**
 * 
 * 作业规则查询
 * @author 
 * @version 1.0 2018年5月16日
 * @see ValNetValueConfirm001Contoller
 * @since 1.0
 */
@Controller
@PageId("ReportRule001")
@RequestMapping("/disclosure/information/manage/workrule/ReportRule001")
public class ReportRule001Contoller extends BaseController {
	@SuppressWarnings("rawtypes")
	@Autowired
	private CommonExportService commonExportService;
    @Autowired
    private ReportRule001Service reportRule001Service;
    @Autowired
    private ReportRule002Service reportRule002Service;
    @Autowired
    private BaseCommonService baseCommonService;
    /**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	model.addAttribute("REPORT_NAME",JsonUtils.objectArray2Json(this.getReportNameList().toArray()));
        this.setSelectOptionToModel(model,DictCode.披露渠道);
        // 入账日期默认为系统平台日
        model.addAttribute("sysDate",DateUtil.format(baseCommonService.getSysDate(), "yyyy-MM-dd"));
        return super.init(model);
    }
    
    /**
     * 获取报表名称
     * @return
     */
    @RequestMapping("/getReportNameList")
    @ResponseBody
    private List<SelectOptionDto> getReportNameList() {
    	return reportRule002Service.getReportInfo();
		
	}

	/**
     * 
     * 分页数据查询
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ReportRule001Dto> findPage() throws Exception{
        Page<ReportRule001Dto>  pageList = reportRule001Service.findPage(RequestUtil.getPageParam());
        return pageList;
    }

    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
    	 String datas = this.getParam("datas");
 		 String datasAll = this.getParam("datasAll");
         List<ReportRule001Dto> dtoList;
         if (datas == null) {		
        	 dtoList = reportRule001Service.queryPageList(RequestUtil.getPageParam());
 		} else {
 			dtoList = JsonUtils.json2List(datas, ReportRule001Dto.class);
 		}
        File outFile = commonExportService.makeFile("/com/joyintech/tams/disclosure/information/manage/workrule/config/export_reportrule001.xml",dtoList);
        String exportFileName = this.getPageId();
		com.joyintech.framework.base.intf.Page page = CacheUtil.getPage(this.getPageId());
        if(null != page) {
            exportFileName = page.getName()
                             + SystemGlobalConstant.OPEN_PARENTHESIS + DateUtil.getCurrentDate()
                             + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
                             + FileNameUtil.getFileExtName(outFile);
        }
		return ExportUtil.export(exportFileName, outFile, true);
    }
	
	/**
	 * 删除检验
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/check")
	public AjaxResult check(@RequestBody ReportRule001Dto dto, BindingResult result) throws Exception {
		return new AjaxResult();
	}
}
