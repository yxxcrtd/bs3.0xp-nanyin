/*
 * 文件名：ValProductInfoQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.disclosure.information.manage.workrule.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.disclosure.information.manage.workrule.dto.ReportRule001Dto;
import com.joyintech.service.disclosure.information.manage.workrule.service.ReportRule001Service;
import com.joyintech.service.disclosure.information.manage.workrule.service.ReportRule002Service;
import com.joyintech.tams.disclosure.common.common.service.BaseCommonService;
/**
 * 
 * 作业规则查询
 * @author 
 * @version 1.0 2018年5月16日
 * @see ValNetValueConfirm001Contoller
 * @since 1.0
 */
@RestController(value="serviceReportRule001Contoller")
@RequestMapping("/ReportRule001")
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
     * 获取报表名称
     * @return
     */
    @PostMapping("/getReportNameList")
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
    @PostMapping("findPage/{page}/{length}")
    public Page<ReportRule001Dto> findPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        Page<ReportRule001Dto>  pageList = reportRule001Service.findPage(page, length, param);
        return pageList;
    }

    
    
    
    @PostMapping("/queryPageList")
    public List<ReportRule001Dto> queryPageList(@RequestBody Map<String, Object> param) throws Exception{
        List<ReportRule001Dto>  pageList = reportRule001Service.queryPageList(param);
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
//    @SuppressWarnings("unchecked")
//	@RequestMapping(ACTION_EXPORT)
//    public ResponseEntity<byte[]> export() throws Exception {
//    	 String datas = this.getParam("datas");
// 		 String datasAll = this.getParam("datasAll");
//         List<ReportRule001Dto> dtoList;
//         if (datas == null) {		
//        	 dtoList = reportRule001Service.queryPageList(RequestUtil.getPageParam());
// 		} else {
// 			dtoList = JsonUtils.json2List(datas, ReportRule001Dto.class);
// 		}
//        File outFile = commonExportService.makeFile("/com/joyintech/tams/disclosure/information/manage/workrule/config/export_reportrule001.xml",dtoList);
//        return ExportUtil.export(
//                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
//                        + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
//                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
//                outFile, true);
//    }
	
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
