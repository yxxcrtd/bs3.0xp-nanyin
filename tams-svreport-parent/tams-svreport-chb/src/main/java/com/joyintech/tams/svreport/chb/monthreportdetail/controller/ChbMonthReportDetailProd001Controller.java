package com.joyintech.tams.svreport.chb.monthreportdetail.controller;


import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.svreport.chb.investtradereg.dto.InvestTradeRegist001Dto;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReport;
import com.joyintech.tams.svreport.chb.monthreportdetail.dto.ChbMonthReportDetailProd;
import com.joyintech.tams.svreport.chb.monthreportdetail.service.ChbMonthReportDetailProd001Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 中债G06报表报送登记Controller
 * 〈功能详细描述〉
 * @author zhangzhongliang
 * @version 1.0 2019年05月08日
 * @see ChbMonthReportDetailProd001Controller
 * @since 1.0
 */
@Controller
@PageId("ChbMonthReportDetailProd001")
@RequestMapping("/chb/monthreportdetail/ChbMonthReportDetailProd001")
public class ChbMonthReportDetailProd001Controller extends BaseController{

    @Autowired
    private ChbMonthReportDetailProd001Service chbMonthReportDetailProd001Service;
    
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
	 * {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		model.addAttribute("defDate",DateUtil.format(DateUtil.addMonthDate(DateUtil.platDateFromCache(),-1),"yyyyMM"));
		return super.init(model);
	}

	/**
	 *
	 * 业务画面查询事件
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("findPage")
	public Page<ChbMonthReportDetailProd> searchPage() {
		Page<ChbMonthReportDetailProd> page = chbMonthReportDetailProd001Service
				.searchPage(RequestUtil.getPageParam());
		return page;
	}


	/**
	 * 业务画面导出事件
	 * @return
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("export")
	public ResponseEntity<byte[]> export(){
		Map<String, Object> searchParm = new HashMap<>();
		Map<String, Object> pageParam = RequestUtil.getPageParam();
		if(pageParam.containsKey("keyList")){
			searchParm.put("keyList",pageParam.get("keyList"));
		}else{
			searchParm.putAll(pageParam);
		}
		List<ChbMonthReportDetailProd> dtoList = chbMonthReportDetailProd001Service.selectForExport(searchParm);
		Map<String,Object> date = new HashMap<>();
		date.put("keyList",dtoList);
		String fileName = "G06产品端明细台账" + GlobalConstant.OPEN_PARENTHESIS
				+ pageParam.get("enddate") + GlobalConstant.CLOSE_PARENTHESIS
				+ GlobalConstant.POINT + "xls";

		File depositfile = TisUtil.createFileDetail(date, "ChbMonthReportDetailProd001", fileName);
		return ExportUtil.export(fileName, depositfile, true);
	}

}
