/*
 * 文件名：TaxBalanceQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：leipeng
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.paythevatquery.controller;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.product.paythevatquery.dto.PayTheVatQuery001Dto;
import com.joyintech.tams.invest.product.paythevatquery.dto.PayTheVatQuery002Dto;
import com.joyintech.tams.invest.product.paythevatquery.service.PayTheVatQuery001Service;
import com.joyintech.tams.invest.product.paythevatquery.service.PayTheVatQuery002Service;


/**
 * 纳税增值税查询
 * 
 * @author chufengjie
 * @version 1.0 2019年02月23日
 * @see PayTheVatQuery001Dto
 * @since 1.0
 */
@Controller("finproductPayTheVatQuery001Controller")
@PageId("FinproductPayTheVatQuery001")
@RequestMapping("/invest/product/paythevatquery/PayTheVatQuery001")
public class PayTheVatQuery001Controller extends BaseController {

	@Autowired
	private PayTheVatQuery001Service payTheVatQuery001Service;
	
	@Autowired
	private PayTheVatQuery002Service payTheVatQuery002Service;
//	@Autowired
//	private ValAssetBook001Service valAssetBook001Service;

	/**
	 * TA交易-汇总查询 页面初始化 {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		this.setSelectOptionToModel(model, DictCode.交易方向,DictCode.币种,BussinessDictCode.有效状态,DictCode.增值税品种);
		
		return super.init(model);
	}

	@RequestMapping("selectForPage")
	@ResponseBody
	public Page<PayTheVatQuery001Dto> selectForPage() throws Exception {
	    Map<String , Object> params = RequestUtil.getPageParam();
		String queryDateBegin = (String)params.get("vatDateBegin");
		String queryDateEnd = (String)params.get("vatDateEnd");
	    Page<PayTheVatQuery001Dto> page = payTheVatQuery001Service.selectForPage(params);
        List<String> listProdId = new ArrayList<String>();
        if(!StringUtil.isEmptyList(page.getRoot())){
            for(PayTheVatQuery001Dto dto:page.getRoot()) {
                listProdId.add(dto.getPortfolioId());
            }
            /**调用估值接口获取是否显示水利基金附加税 add by chendijun 2019/10/14 start**/
			//OPEX-650【产品缴纳】缴纳查询界面，选择纳税日期查询条件进行查询，报数据接口异常 guyunfei 20191227 start
			List<String> addTaxTypeList= payTheVatQuery002Service.getAddTaxType(listProdId,
					BussinessDictCodeList.金融产品类型_投资组合.getValue(),null, queryDateBegin,queryDateEnd);
			//OPEX-650【产品缴纳】缴纳查询界面，选择纳税日期查询条件进行查询，报数据接口异常 guyunfei 20191227 end
            for(PayTheVatQuery001Dto dto:page.getRoot()) {
                dto.setShowWaterTrafficTax(!StringUtil.isEmptyList(addTaxTypeList)
                    && addTaxTypeList.contains(BussinessDictCodeList.税类型_水利基金税.getValue())); 
            }
            /**调用估值接口获取是否显示水利基金附加税  add by chendijun 2019/10/14 end**/  
        }
        

		return page;
	}
	
	 /**
     * 产品代码下拉框
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception Exception
     */
    @RequestMapping("queryProdIdInfo")
    @ResponseBody
    public List<SelectOptionDto> queryProdIdInfo(@RequestParam Map<String, Object> param)
            throws Exception {
        return payTheVatQuery001Service.queryProdIdInfo(param);
    }
    
    /**
    * 产品代码下拉框
    * @param param 过滤参数
    * @return 下拉框列表
    * @throws Exception Exception
    */
   @RequestMapping("queryChannelIdInfo")
   @ResponseBody
   public Page<SelectOptionDto> queryChannelIdInfo(@RequestParam Map<String, Object> param)
           throws Exception {
       return payTheVatQuery001Service.queryChannelIdInfo(param);
   }
    
	/**
	 * 检索账套代码
	 * 
	 * @return 账套代码联想框内容
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("/getBooksetId")
	public List<SuggestDto> getBooksetId() throws Exception {
		String param = super.getRequest().getParameter("param");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("param",param);
		return payTheVatQuery001Service.getBooksetId(params);
	}

	  /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    /*@ResponseBody
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return payTheVatQuery001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }*/
	/*
	 * 导出信息excel
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	@RequestMapping(ACTION_EXPORT)
	public ResponseEntity<byte[]> export() throws Exception {
		String datas = this.getParam("datas");
		List<PayTheVatQuery001Dto> listdto;
		if (datas == null) {
			listdto = payTheVatQuery001Service.findByList(RequestUtil.getPageParam());
		} else {
			listdto = JsonUtils.json2List(datas, PayTheVatQuery001Dto.class);
		}
		File outFile = payTheVatQuery001Service.makeFile(
				"/com/joyintech/tams/invest/product/paythevatquery/conf/export_paythevatquery001.xml", listdto);
		return ExportUtil.export(this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
				+ DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
				+ FileNameUtil.getFileExtName(outFile), outFile, true);
	}

    /**
     * 获取账号
     * @return 账号下拉框集合
     * @see
     * @since 1.0
     */
    @RequestMapping("/selectPortfolioId")
    @ResponseBody
    public Page<SelectOptionDto> selectPortfolioId(){
        return payTheVatQuery001Service.selectPortfolioId(RequestUtil.getPageParam());
    }
}
