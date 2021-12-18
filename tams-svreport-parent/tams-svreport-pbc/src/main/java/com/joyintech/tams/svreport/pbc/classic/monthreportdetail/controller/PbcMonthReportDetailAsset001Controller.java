package com.joyintech.tams.svreport.pbc.classic.monthreportdetail.controller;


import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.pbc.reportdetail.dto.PbcMonthReportDetailAsset;
import com.joyintech.tams.svreport.pbc.reportdetail.service.PbcMonthReportDetailAsset001Service;
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
 * 中债银行月度报表报送登记Controller
 * 〈功能详细描述〉
 * @author yuluan
 * @version 1.0 2017年11月24日
 * @see PbcMonthReportDetailAsset001Controller
 * @since 1.0
 */
@Controller
@PageId("PbcMonthReportDetailAsset001")
@RequestMapping("/pbc/classic/monthreportdetail/PbcMonthReportDetailAsset001")
public class PbcMonthReportDetailAsset001Controller extends BaseController{

    
    @Autowired
    private PbcMonthReportDetailAsset001Service pbcMonthReportDetailAsset001Service;
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
	public Page<PbcMonthReportDetailAsset> searchPage() {
		Page<PbcMonthReportDetailAsset> page = pbcMonthReportDetailAsset001Service
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
		List<SelectOptionDto> list = pbcMonthReportDetailAsset001Service.getAssetCode(param);
		return list;
	}


	@ResponseBody
	@RequestMapping("export")
	public ResponseEntity<byte[]> export(){
		Map<String, Object> pageParam = RequestUtil.getPageParam();
		List<PbcMonthReportDetailAsset> dtoList = pbcMonthReportDetailAsset001Service.selectForExport(RequestUtil.getParameters(getRequest()));
		File outFile = pbcMonthReportDetailAsset001Service.makeFile(
				"/com/joyintech/tams/svreport/pbc/classic/monthreportdetail/controller/conf/assetExport_conf.xml", dtoList);
		return ExportUtil.export(
				this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
						+ DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
						+ GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
				outFile, true);
	}
    

}
