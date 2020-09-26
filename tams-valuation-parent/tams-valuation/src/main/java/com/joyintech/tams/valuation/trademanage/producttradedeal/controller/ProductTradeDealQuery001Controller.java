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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
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
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.service.ValAccountHandle001Service;
import com.joyintech.tams.valuation.trademanage.producttradedeal.dto.ProductTradeDealQuery001Dto;
import com.joyintech.tams.valuation.trademanage.producttradedeal.service.ProductTradeDealQuery001Service;

/**
 * TA交易-汇总查询
 * 
 * @author zhangwei
 * @version 1.0 2018年8月6日
 * @see ProductTradeDealQuery001Controller
 * @since 1.0
 */
@Controller
@PageId("ProductTradeDealQuery001")
@RequestMapping("/valuation/trademanage/producttradedeal/ProductTradeDealQuery001")
public class ProductTradeDealQuery001Controller extends BaseController {

	@Autowired
	private ProductTradeDealQuery001Service productTradeDealQuery001Service;
    @Autowired
    private ValAccountHandle001Service valAccountHandle001Service;

	/**
	 * TA交易-汇总查询 页面初始化 {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		super.setSelectOptionToModel(model, DictCode.发行方式);
		super.setSelectOptionToModel(model, DictCode.币种);
		super.setSelectOptionToModel(model, DictCode.收益类型);// 产品类型
        model.addAttribute("BOOK_SETID",JsonUtils.objectArray2Json(this.getBookSetId().toArray()));
		return super.init(model);
	}

	@RequestMapping("findPage")
	@ResponseBody
	public Page<ProductTradeDealQuery001Dto> findPage() throws Exception {
		return productTradeDealQuery001Service.findByPage(RequestUtil.getPageParam());
	}

	/**
	 * 下拉框获取：资产
	 * 
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("getAssets")
	@ResponseBody
	public List<SelectOptionDto> getAssets() throws Exception {
		return productTradeDealQuery001Service.getAssets(RequestUtil.getPageParam());
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
    public List<SelectOptionDto> getBookSetId() {
        Map<String,Object> map=new HashMap<String,Object>();
        return valAccountHandle001Service.getBookSetId(map);
    }
    
    /**
     * 套账关联文本框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getFinprod")
    @ResponseBody
    public List<SuggestDto> getFinprod() {
        Map<String,Object> map = RequestUtil.getPageParam();
        map.put("finprodType", DictCodeListExt1.金融产品类型_理财产品.getValue());
        return productTradeDealQuery001Service.getFinprod(map);
    }


	/**
	 * 套账关联文本框数据选择（通道和理财产品）
	 *
	 * @return 返回的数据
	 * @exception Exception
	 *                异常
	 * @since 1.0
	 */
	@RequestMapping("/getFinprodWithTd")
	@ResponseBody
	public List<SuggestDto> getFinprodWithTd() {
		Map<String,Object> map = RequestUtil.getPageParam();

		return productTradeDealQuery001Service.getFinprodWithTd(map);
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
         List<ProductTradeDealQuery001Dto> listdto;
         if (datas == null) {
        	 listdto = productTradeDealQuery001Service.findAll(RequestUtil.getPageParam());
         }
         else {
        	 listdto = JsonUtils.json2List(datas, ProductTradeDealQuery001Dto.class);
         }
         File outFile = productTradeDealQuery001Service.makeFile(
 				"/com/joyintech/tams/valuation/trademanage/producttradedeal/conf/export_producttradedealquery001.xml",
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
