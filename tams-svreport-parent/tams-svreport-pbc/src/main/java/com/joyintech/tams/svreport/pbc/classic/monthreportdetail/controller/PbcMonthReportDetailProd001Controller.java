package com.joyintech.tams.svreport.pbc.classic.monthreportdetail.controller;


import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.pbc.reportdetail.dto.PbcMonthReportDetailProd;
import com.joyintech.tams.svreport.pbc.reportdetail.service.PbcMonthReportDetailProd001Service;
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
import java.util.List;
import java.util.Map;


/**
 * 中债G06报表报送登记Controller
 * 〈功能详细描述〉
 * @author zhangzhongliang
 * @version 1.0 2019年05月08日
 * @see PbcMonthReportDetailProd001Controller
 * @since 1.0
 */
@Controller
@PageId("PbcMonthReportDetailProd001")
@RequestMapping("/pbc/classic/monthreportdetail/PbcMonthReportDetailProd001")
public class PbcMonthReportDetailProd001Controller extends BaseController{

    @Autowired
    private PbcMonthReportDetailProd001Service pbcMonthReportDetailProd001Service;
    
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
		super.setSelectOptionToModel(model, DictCode.报送状态, DictCode.报送数据状态, DictCode.人行月报详情数据类型);
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
	public Page<PbcMonthReportDetailProd> searchPage() {
		Page<PbcMonthReportDetailProd> page = pbcMonthReportDetailProd001Service
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
		Map<String, Object> pageParam = RequestUtil.getPageParam();
		if(pageParam.containsKey("keyList")){
			List<PbcMonthReportDetailProd> dtoList = pbcMonthReportDetailProd001Service.selectForExport(RequestUtil.getParameters(getRequest()));
			File outFile = pbcMonthReportDetailProd001Service.makeFile(
					"/com/joyintech/tams/svreport/pbc/classic/monthreportdetail/controller/conf/prodExport_conf.xml", dtoList);
			return ExportUtil.export(
					this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
							+ DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
							+ GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
					outFile, true);
		}
		else{
			return pbcMonthReportDetailProd001Service.export(this.getPageId(), RequestUtil.getPageParam());
		}
	}

}
