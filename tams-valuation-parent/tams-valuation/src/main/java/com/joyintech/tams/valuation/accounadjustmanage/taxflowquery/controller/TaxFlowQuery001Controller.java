/*
 * 文件名：TaxFlowQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.taxflowquery.controller;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.tams.valuation.accounadjustmanage.taxflowquery.dto.TaxFlowQuery001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.taxflowquery.service.TaxFlowQuery001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.common.service.BaseCommonService;

/**
 * 计税流水表查询 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author lishicheng
 * @version 1.0 2018年9月14日
 * @see TaxFlowQuery001Controller
 * @since 1.0
 */
@Controller
@PageId("TaxFlowQuery001")
@RequestMapping("/valuation/accounadjustmanage/taxflowquery/TaxFlowQuery001")
public class TaxFlowQuery001Controller extends BaseController {

	@Autowired
	private TaxFlowQuery001Service taxFlowQuery001Service;
	
	@Autowired
    private ValAssetBook001Service valAssetBook001Service;

	@Autowired
	private BaseCommonService baseCommonService;

	@Override
	public String init(Model model) {
		Map<String, Object> map = RequestUtil.getPageParam();
		model.addAttribute("booksetId", map.get("booksetId"));
		// 记账日期默认为系统平台日
        model.addAttribute("sysDate",
            DateUtil.format(baseCommonService.getSysDate(), "yyyy-MM-dd"));
		super.setSelectOptionToModel(model, DictCode.增值税_确认类型);
		super.setSelectOptionToModel(model, DictCode.增值税类型);
		super.setSelectOptionToModel(model, DictCode.计税方式);
		super.setSelectOptionToModel(model, DictCode.增值税资产分类);
		return super.init(model);
	}

	@ResponseBody
	@RequestMapping("findPage")
	public Page<TaxFlowQuery001Dto> findPage() throws Exception {
		  Map<String, Object> params= this.getRequestMap();
//		  TaxFlowQuery001Dto dto = (TaxFlowQuery001Dto)JsonUtils.map2entity(params, TaxFlowQuery001Dto.class);	     
		return  taxFlowQuery001Service.selectForPage(params);
		
	}

	/**
	 * 所属账套下拉框获取
	 *
	 * @return 返回的数据
	 * @exception Exception
	 *                异常
	 * @since 1.0
	 */
	@RequestMapping("/getBooksetId")
	@ResponseBody
	public Page<SelectOptionDto> getBooksetId() {
		return valAssetBook001Service.findBooksetIdByPage(RequestUtil.getPageParam());
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
		Map<String, Object> paraMap = this.getRequestMap();
		TaxFlowQuery001Dto dto = (TaxFlowQuery001Dto)JsonUtils.map2entity(paraMap, TaxFlowQuery001Dto.class);
		List<TaxFlowQuery001Dto> listdto;
		if (datas == null) {
			listdto = (List<TaxFlowQuery001Dto>) taxFlowQuery001Service.findPageout(dto);
		} else {
			listdto = JsonUtils.json2List(datas, TaxFlowQuery001Dto.class);
		}
		File outFile = taxFlowQuery001Service.makeFile(
				"/com/joyintech/tams/valuation/accounadjustmanage/taxflowquery/conf/export_taxflowquery001.xml",
				listdto);
		return ExportUtil.export(this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
				+ DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
				+ FileNameUtil.getFileExtName(outFile), outFile, true);
	}

	
	   /**
     * 
     * 获取页面参数 页面中有时间参数，获取参数后将时间转换成Date类型
     * 
     * @return paraMap
     * @throws ParseException 
     * @see
     * @since 1.0
     */
    public Map<String, Object> getRequestMap() throws ParseException {
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        // 发生日期区间
        String happenDateBegin = this.getParam("happenDateBegin");
        if(StringUtil.isNotEmpty(happenDateBegin)){
            paraMap.put("happenDateBegin", DateUtil.parseDate(happenDateBegin, "yyyy-MM-dd"));
        }
        String happenDateEnd = this.getParam("happenDateEnd");
        if(StringUtil.isNotEmpty(happenDateEnd)){
            paraMap.put("happenDateEnd", DateUtil.parseDate(happenDateEnd, "yyyy-MM-dd"));
        }
        return paraMap;
    }
}
