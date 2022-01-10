package com.joyintech.tams.svreport.chb.monthreportdetail.controller;


import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReport;
import com.joyintech.tams.svreport.chb.monthreportdetail.dto.ChbMonthReportDetailAsset;
import com.joyintech.tams.svreport.chb.monthreportdetail.dto.ChbMonthReportDetailProd;
import com.joyintech.tams.svreport.chb.monthreportdetail.service.ChbMonthReportDetailAsset001Service;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.*;


/**
 * 中债银行月度报表报送登记Controller
 * 〈功能详细描述〉
 * @author yuluan
 * @version 1.0 2017年11月24日
 * @see ChbMonthReportDetailAsset001Controller
 * @since 1.0
 */
@Controller
@PageId("ChbMonthReportDetailAsset001")
@RequestMapping("/chb/monthreportdetail/ChbMonthReportDetailAsset001")
public class ChbMonthReportDetailAsset001Controller extends BaseController{

    
    @Autowired
    private ChbMonthReportDetailAsset001Service chbMonthReportDetailAsset001Service;
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());


	/**
	 * {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		Map<String, Object> map = RequestUtil.getPageParam();
		model.addAttribute("defDate",DateUtil.format(DateUtil.addMonthDate(DateUtil.platDateFromCache(),-1),"yyyyMM"));

		super.setSelectOptionToModel(model, DictCode.报送状态, DictCode.报送数据状态, DictCode.报送反馈状态);
		return super.init(model);
	}

	/**
	 *
	 * 业务画面查询事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("findPage")
	public Page<ChbMonthReportDetailAsset> searchPage() {
		Page<ChbMonthReportDetailAsset> page = chbMonthReportDetailAsset001Service
				.searchPage(RequestUtil.getPageParam());
		return page;
	}

	/**
	 *
	 * 资产代码/科目号
	 *
	 * @return 下拉框值
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("/getAssetCode")
	public List<SelectOptionDto> getAssetCode() {
		Map<String, Object> param = RequestUtil.getPageParam();
		List<SelectOptionDto> list = chbMonthReportDetailAsset001Service.getAssetCode(param);
		return list;
	}


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
		List<ChbMonthReportDetailAsset> dtoList = chbMonthReportDetailAsset001Service.selectForExport(searchParm);


		Map<String,Object> date = new HashMap<>();
		date.put("keyList",dtoList);
		String fileName = "G06资产端明细台账（穿透前）" + GlobalConstant.OPEN_PARENTHESIS
				+ pageParam.get("enddate") + GlobalConstant.CLOSE_PARENTHESIS
				+ GlobalConstant.POINT + "xls";
		File depositfile = TisUtil.createFileDetail(date, "ChbMonthReportDetailAsset001", fileName);
		return ExportUtil.export(fileName, depositfile, true);
	}
    

}
