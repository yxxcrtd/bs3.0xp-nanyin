/*
 * 文件名：TaxBalanceQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：leipeng
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.trademanage.taxbalancequery.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

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
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.service.ValAccountHandle001Service;
import com.joyintech.tams.valuation.trademanage.taxbalancequery.dto.TaxBalanceQuery001Dto;
import com.joyintech.tams.valuation.trademanage.taxbalancequery.service.TaxBalanceQuery001Service;

/**
 * 简易计税余额查询
 * 
 * @author leipeng
 * @version 1.0 2018年10月9日
 * @see TaxBalanceQuery001Controller
 * @since 1.0
 */
@Controller
@PageId("TaxBalanceQuery001")
@RequestMapping("/valuation/trademanage/taxbalancequery/TaxBalanceQuery001")
public class TaxBalanceQuery001Controller extends BaseController {

	@Autowired
	private TaxBalanceQuery001Service taxBalanceQuery001Service;
	@Autowired
	private ValAccountHandle001Service valAccountHandle001Service;

	/**
	 * TA交易-汇总查询 页面初始化 {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		super.setSelectOptionToModel(model, DictCode.增值税类型);
		super.setSelectOptionToModel(model, DictCode.增值税_确认类型);

		return super.init(model);
	}

	@RequestMapping("findPage")
	@ResponseBody
	public Page<TaxBalanceQuery001Dto> findPage() throws Exception {
		return taxBalanceQuery001Service.findByPage(RequestUtil.getPageParam());
	}
	
	/**
     * 套账下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getBookSetId")
    @ResponseBody
    public Page<SelectOptionDto> getBookSetId() {
        return valAccountHandle001Service.getBookSetId2(RequestUtil.getPageParam());
    }

    /**
     * 资产下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getAssetId")
    @ResponseBody
    public Page<SelectOptionDto> getAssetId() {
        return taxBalanceQuery001Service.getAssetId(RequestUtil.getPageParam());
    }

	/*
	 * 导出信息excel
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(ACTION_EXPORT)
	public ResponseEntity<byte[]> export() throws Exception {
		 String datas = this.getParam("datas");
         List<TaxBalanceQuery001Dto> listdto;
         if (datas == null) {
        	 listdto = taxBalanceQuery001Service.findByList(RequestUtil.getPageParam());
         }
         else {
        	 listdto = JsonUtils.json2List(datas, TaxBalanceQuery001Dto.class);
         }
         File outFile = taxBalanceQuery001Service.makeFile(
  				"/com/joyintech/tams/valuation/trademanage/taxbalancequery/conf/export_taxbalancequery001.xml",
 				listdto);
         return ExportUtil.export(
             this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                  + DateUtil.format(new Date())
                                  + SystemGlobalConstant.CLOSE_PARENTHESIS
                                  + SystemGlobalConstant.POINT
                                  + FileNameUtil.getFileExtName(outFile),
             outFile, true);
	}

}
