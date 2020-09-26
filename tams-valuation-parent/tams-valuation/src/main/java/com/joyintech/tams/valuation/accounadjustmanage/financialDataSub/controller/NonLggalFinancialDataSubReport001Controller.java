/*
 * 文件名：NonLggalFinancialDataSubReport001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanwgei1
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.financialDataSub.controller;

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
import com.joyintech.tams.valuation.accounadjustmanage.financialDataSub.dto.NonLggalFinancialDataSubReport001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.financialDataSub.service.NonLggalFinancialDataSubReport001Service;
import com.joyintech.tams.valuation.common.service.BaseCommonService;

/**
 * 非法人财务数据报送 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wanwgei1
 * @version 1.0 2018年11月20日
 * @see NonLggalFinancialDataSubReport001Controller
 * @since 1.0
 */
@Controller
@PageId("NonLggalFinancialDataSubReport001")
@RequestMapping("/valuation/accounadjustmanage/financialDataSub/NonLggalFinancialDataSubReport001")
public class NonLggalFinancialDataSubReport001Controller extends BaseController {

	@Autowired
	private NonLggalFinancialDataSubReport001Service NonLggalFinancialDataSubReport001Service;
	
	@Autowired
    private ValAssetBook001Service valAssetBook001Service;

	@Autowired
	private BaseCommonService baseCommonService;

	@Override
	public String init(Model model) {
		Map<String, Object> map = RequestUtil.getPageParam();
		model.addAttribute("booksetId", map.get("booksetId"));
		// 日期默认为系统平台日
        model.addAttribute("sysDate",
            DateUtil.format(baseCommonService.getSysDate(), "yyyy-MM-dd"));
		return super.init(model);
	}

	@ResponseBody
	@RequestMapping("findPage")
	public Page<NonLggalFinancialDataSubReport001Dto> findPage(NonLggalFinancialDataSubReport001Dto dto) throws Exception {
//		  Map<String, Object> params= this.getRequestMap();
//	      dto = (NonLggalFinancialDataSubReport001Dto)JsonUtils.map2entity(params, NonLggalFinancialDataSubReport001Dto.class);	     
		return  NonLggalFinancialDataSubReport001Service.selectForPage(RequestUtil.getPageParam());
		
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
		NonLggalFinancialDataSubReport001Dto dto = (NonLggalFinancialDataSubReport001Dto)JsonUtils.map2entity(paraMap, NonLggalFinancialDataSubReport001Dto.class);
		List<NonLggalFinancialDataSubReport001Dto> listdto;
		if (datas == null) {
			listdto = (List<NonLggalFinancialDataSubReport001Dto>) NonLggalFinancialDataSubReport001Service.findPageout(dto);
		} else {
			listdto = JsonUtils.json2List(datas, NonLggalFinancialDataSubReport001Dto.class);
		}
		File outFile = NonLggalFinancialDataSubReport001Service.makeFile(
				"/com/joyintech/tams/valuation/accounadjustmanage/financialDataSub/conf/export_NonLggalFinancialDataSubReport001.xml",
				listdto);
		return ExportUtil.export("非法人财务数据报送"  + SystemGlobalConstant.OPEN_PARENTHESIS
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
