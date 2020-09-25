/*
 * 文件名：MstBondFirstMarketCalendar001Controller.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 创建人：赵东东
 * 创建时间：2019年11月16日
 * 创建内容：查询
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstbondfirstmarketcalendar.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.basicdata.mstbondfirstmarketcalendar.dto.MstBondFirstMarketCalendar001Dto;
import com.joyintech.tams.invest.basicdata.mstbondfirstmarketcalendar.service.MstBondFirstMarketCalendar001Service;
import com.joyintech.tams.invest.basicdata.mstbondquotationinfo.controller.MstBondQuotationInfo001Controller;


/**
 * 一级新债日历查询
 * 
 * @author 赵东东
 * @version 1.0 2019年11月16日
 * @see MstBondFirstMarketCalendar001Controller
 * @since 1.0
 */
@Controller
@PageId("MstBondFirstMarketCalendar001")
@RequestMapping("/invest/basicdata/mstbondfirstmarketcalendar/MstBondFirstMarketCalendar001")
public class MstBondFirstMarketCalendar001Controller extends BaseController {
	
	/**
	 * 查询界面入口
	 */
	@Override
    @RequestMapping(PAGE_INIT)
	 public String init(Model model){
		DateUtil.platDateFromCache();
		String sysDate = DateUtil.format(DateUtil.platDateFromCache());
		model.addAttribute("sysDate",sysDate);
		return super.init(model);
	}
	/**
	 * 定义service对象
	 */
	@Autowired
	private MstBondFirstMarketCalendar001Service mstBondFirstMarketCalendar001Service;
	/**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
	@ResponseBody
    @RequestMapping("findPage")
    public Page<MstBondFirstMarketCalendar001Dto> selectAll()
        throws Exception {
        Map<String,Object> map = RequestUtil.getPageParam();
        Page<MstBondFirstMarketCalendar001Dto> page = mstBondFirstMarketCalendar001Service.selectForPage(map);
        return page;
    }
	
	/**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return mstBondFirstMarketCalendar001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
    /**
     * 根据债券代码查询finproduct表中是否存在finprodId
     */
    @ResponseBody
    @RequestMapping("getFinprodId")
    public AjaxResult getFinprodId() {
    	String finprodId = RequestUtil.getParam("secId");
    	finprodId = mstBondFirstMarketCalendar001Service.getFinprodId(finprodId);
    	AjaxResult wr = new AjaxResult();
    	wr.setData(finprodId);
    	return wr;
    }
    

}
