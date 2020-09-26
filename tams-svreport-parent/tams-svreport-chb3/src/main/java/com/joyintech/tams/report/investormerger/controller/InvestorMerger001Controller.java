package com.joyintech.tams.report.investormerger.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.investormerger.dto.ChbEquHoldCheckDto;
import com.joyintech.tams.report.investormerger.service.InvestorMergerMService;

@Controller
@PageId("SvrInvestorMerger001")
@RequestMapping("/svreport/investormerger/InvestorMerger001")
public class InvestorMerger001Controller extends BaseController{
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private InvestorMergerMService investorMergerMService;
	
	/**
     * 初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
	public String init(Model model) {
    	Map<String, Object> map = RequestUtil.getPageParam();
    	if(map.containsKey("status")){
            model.addAttribute("isMain","01");
            model.addAttribute("statusA",DictCodeListExt2.处理状态_中债_未合并.getValue());
            model.addAttribute("searchDataStart", "1900-01-01");
            model.addAttribute("searchDataEnd", map.get("searchDataEnd"));
        }
		super.setSelectOptionToModel(model, DictCode.处理状态_中债, DictCode.个人证件类别, DictCode.机构证件类别);
		return super.init(model);
	}
    /**
     * @Description 分页查询
     * @return Page<ChbEquHoldCheckDto>
     * @version 1.0 2019年3月18日
     */
    @ResponseBody
    @RequestMapping("findPage")
	public Page<ChbEquHoldCheckDto> selectAllData() {
    	Page<ChbEquHoldCheckDto> page = investorMergerMService.selectForPage(RequestUtil.getPageParam());
    	return page;
	}
    
    /**
     * 业务画面导出事件
     * @return
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("export")
	public ResponseEntity<byte[]> export()  {
		Map<String, Object> pageParam = RequestUtil.getPageParam();
		List<ChbEquHoldCheckDto> dtoList = new ArrayList<ChbEquHoldCheckDto>();
		if (pageParam.containsKey("keyList")) {
			dtoList = investorMergerMService.selectForExport(RequestUtil.getParameters(getRequest()));
		} else {
			Page<ChbEquHoldCheckDto> page = investorMergerMService.selectForPage(pageParam);
			dtoList = page.getRoot();
//			return investorMerger001Service.export(this.getPageId(), RequestUtil.getPageParam());
		}
		File outFile = investorMergerMService
				.makeFile("/com/joyintech/tams/report/investormerger/conf/export_conf.xml", dtoList);
		return ExportUtil.export(this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
				+ DateUtil.formatDateTime(new Date()) + GlobalConstant.CLOSE_PARENTHESIS + GlobalConstant.POINT
				+ FileNameUtil.getFileExtName(outFile), outFile, true);
	}
	
	@ResponseBody
    @RequestMapping(value = "/manual" ,method = RequestMethod.POST)
	public AjaxResult manualMerger(@RequestBody Map<String,Object> map) {
		investorMergerMService.manualMerger(map);
    	return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}
}
