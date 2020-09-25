/*
 * 文件名：ValProductInfoQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.disclosure.information.manage.workmonitor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.service.disclosure.information.manage.workmonitor.dto.ReportWorkMonitor002Dto;
import com.joyintech.service.disclosure.information.manage.workmonitor.service.ReportWorkMonitor001Service;
import com.joyintech.service.disclosure.information.manage.workmonitor.service.ReportWorkMonitor002Service;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftppath.service.FtpPathService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service.GetPrdInfoSelectService;

/**
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author guozhongbao
 * @version 1.0 2018年12月5日
 * @see ReportWorkMonitor002Contoller
 * @since 1.0
 */
@RestController(value="serviceReportWorkMonitor002Contoller")
@RequestMapping("/ReportWorkMonitor002")
public class ReportWorkMonitor002Contoller extends BaseController {
    
	@Autowired
	private ReportWorkMonitor002Service reportWorkMonitor002Service;
	
	@Autowired
    private ReportWorkMonitor001Service reportWorkMonitor001Service;
	
	@Autowired
    private FtpPathService ftpPathService;
	
	@Autowired
	private GetPrdInfoSelectService getPrdInfoSelectService;
	
  
    
    /**
	 * 分页查询产品下拉框
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getPrdInfoSelect")
	@ResponseBody
    public Page<SelectOptionDto> getPrdInfoSelect()
        throws Exception {
		Page<SelectOptionDto> prdInfoSelect = getPrdInfoSelectService.getPrdInfoSelect(RequestUtil.getPageParam());
        return prdInfoSelect;
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
    public Page<ReportWorkMonitor002Dto> findPage(@RequestBody Map<String, Object> param,@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        Page<ReportWorkMonitor002Dto> pageList = reportWorkMonitor002Service.findPage(page, length, param);
//        pageList.getRoot().forEach(dto -> {
//        	dto.setExecStatusName(systemToolService.getNameFromDataDict(DisclosureDictCode.执行结果, dto.getExecStatus()));
//        	dto.setExecResult(systemToolService.getNameFromDataDict(DisclosureDictCode.执行结果, dto.getExecResult()));
//        });
        return pageList;
    }
    
    @RequestMapping("/getJobidList")
    @ResponseBody
    public List<SelectOptionDto> getJobidList() {
         Map<String,Object> map=new HashMap<String,Object>();
         return reportWorkMonitor001Service.getJobidList(map);
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
//    @RequestMapping(ACTION_EXPORT)
//    public ResponseEntity<byte[]> export() throws Exception {
//    	 String datas = this.getParam("datas");
//         List<ReportWorkMonitor002Dto> dtoList;
//         if (datas == null) {
//        	 dtoList = this.findPage().getRoot();
//         }
//         else {
//        	 dtoList = JsonUtils.json2List(datas, ReportWorkMonitor002Dto.class);
//         }
//        File outFile = reportWorkMonitor002Service.makeFile("/com/joyintech/tams/disclosure/information/manage/workmonitor/conf/export_reportWorkMonitor002.xml",dtoList);
//        return ExportUtil.export(
//                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
//                        + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
//                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
//                outFile, true);
//    }
}
