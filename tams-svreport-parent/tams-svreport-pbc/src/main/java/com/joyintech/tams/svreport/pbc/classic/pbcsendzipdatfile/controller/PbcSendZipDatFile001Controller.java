package com.joyintech.tams.svreport.pbc.classic.pbcsendzipdatfile.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.pbc.classic.pbcsendzipdatfile.service.PbcSendZipDatFileMService;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


/**
 * 人行报送文件管理界面Controller
 * 〈功能详细描述〉
 * @author meifaxin
 * @version 1.0 2021年7月27日
 * @see PbcSendZipDatFile001Controller
 * @since 1.0
 */
@Controller
@PageId("PbcSendZipDatFile")
@RequestMapping("/pbc/pbcsendzipdatfile/PbcSendZipDatFile")
public class PbcSendZipDatFile001Controller extends BaseController{


    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * service
     */
    @Autowired
    private PbcSendZipDatFileMService pbcSendZipDatFileMService;

	
    /**
	 * {@inheritDoc}
	 */
    @RequestMapping(PAGE_INIT)
	@Override
	public String init(Model model) {
		super.setSelectOptionToModel(model, DictCode.人行报送类型, DictCode.报送状态, DictCode.人行报送管理反馈状态);
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
	public Page<PbcSendZipDatFile> searchPage() {
		Map<String,Object> searchParam = RequestUtil.getPageParam();
		Page<PbcSendZipDatFile> page = pbcSendZipDatFileMService
				.searchPage(searchParam);
		return page;
	}





	/**
	 *
	 * 业务画面导入检查事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("checkBaseInfoSubDat")
	public AjaxResult checkBaseInfoSubDat(String fileName) {
		return pbcSendZipDatFileMService.checkBaseInfoSubDat(fileName);
	}

	/**
	 *
	 * 业务画面导入事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("importBaseInfoSubDat")
	public List<ExcelValidDto> importBaseInfoSubDat(String fileName) {
		return pbcSendZipDatFileMService.importBaseInfoSubDat(fileName);
	}



}
