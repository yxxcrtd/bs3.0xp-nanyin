package com.joyintech.tams.disclosure.information.manage.estimatereveal.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftppath.service.FtpPathService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service.GetPrdInfoSelectService;
import com.joyintech.tams.disclosure.information.manage.estimatereveal.dto.EstimateReveal001Dto;
import com.joyintech.tams.disclosure.information.manage.estimatereveal.service.EstimateReveal001Service;
import com.joyintech.tams.disclosure.information.manage.workmonitor.service.ReportWorkMonitor001Service;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.framework.util.CacheUtil;

@Controller
@PageId("EstimateReveal001")
@RequestMapping("/disclosure/information/manage/estimatereveal/EstimateReveal001")
public class EstimateReveal001Controller extends BaseController {
	
	@Autowired
    private ReportWorkMonitor001Service reportWorkMonitor001Service;
	
	@Autowired
    private EstimateReveal001Service estimateReveal001Service;
	
	@Autowired
    private FtpPathService ftpPathService;
	
	@Autowired
	private GetPrdInfoSelectService getPrdInfoSelectService;
	
	@Override
    public String init(Model model) {
		model.addAttribute("JOBID_LIST",JsonUtils.objectArray2Json(this.getJobidList().toArray()));
		model.addAttribute("REPORT_STYLE",JsonUtils.objectArray2Json(ftpPathService.getReportCategory().toArray()));
		
		// 首页跳转使用
        String confromPage = this.getParam("confromPage");
        if(StringUtil.equals("01", confromPage)){
        	model.addAttribute("confromPage", confromPage);
        	String searchDate = this.getParam("firstCol");
        	String formatDate = null;
        	if(StringUtil.isNotEmpty(searchDate)){
        		formatDate = DateUtil.format(DateUtil.parse(searchDate));
        	}
        	model.addAttribute("searchDate", formatDate);
        	if(!StringUtil.equals("firstCol", this.getParam("repId"))) {
        		model.addAttribute("repType", this.getParam("repId"));
        	}
        }
		
        return super.init(model);
    }
	
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
     * 分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<EstimateReveal001Dto> findPage() throws Exception{
    	Page<EstimateReveal001Dto> pageList = estimateReveal001Service.findPage(RequestUtil.getPageParam());
        return pageList;
    }
	
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
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
		String datas = this.getParam("datas");
		List<EstimateReveal001Dto> dtoList;
		if (datas == null) {
			dtoList = this.findPage().getRoot();
		}
		else {
			dtoList = JsonUtils.json2List(datas, EstimateReveal001Dto.class);
		}
        File outFile = estimateReveal001Service.makeFile("/com/joyintech/tams/disclosure/information/manage/estimatereveal/conf/export_estimateReveal001.xml",dtoList);
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
}
