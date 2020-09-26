/*
 * 文件名：ProductTradeDealQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年8月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.trademanage.producttradedeal.controller;

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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.trademanage.producttradedeal.dto.ProductTradeDealQuery002Dto;
import com.joyintech.tams.valuation.trademanage.producttradedeal.service.ProductTradeDealQuery002Service;

/**
 * TA交易-明细查询
 * 
 * @author zhangwei
 * @version 1.0 2018年8月6日
 * @see ProductTradeDealQuery002Controller
 * @since 1.0
 */
@Controller
@PageId("ProductTradeDealQuery002")
@RequestMapping("/valuation/trademanage/producttradedeal/ProductTradeDealQuery002")
public class ProductTradeDealQuery002Controller extends BaseController {

    @Autowired
    private ProductTradeDealQuery002Service productTradeDealQuery002Service;
    
    /**
     * TA交易-明细查询 页面初始化 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	
    	model.addAllAttributes(RequestUtil.getPageParam());	
        return super.init(model);
    }

   
    /**
     * 根据条件分页查询
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ProductTradeDealQuery002Dto> findPage() throws Exception{
        return productTradeDealQuery002Service.findPageByCondition(RequestUtil.getPageParam());
    }
    
    /**
	 * 导出信息excel
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(ACTION_EXPORT)
	public ResponseEntity<byte[]> export() throws Exception {
		List<ProductTradeDealQuery002Dto> dtoList = productTradeDealQuery002Service.findAllByCondition(RequestUtil.getPageParam());
		File outFile = productTradeDealQuery002Service.makeFile(
				"/com/joyintech/tams/valuation/trademanage/producttradedeal/conf/export_producttradedealquery002.xml",
				dtoList);
		return ExportUtil.export(this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
				+ DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
				+ FileNameUtil.getFileExtName(outFile), outFile, true);
	}

}










