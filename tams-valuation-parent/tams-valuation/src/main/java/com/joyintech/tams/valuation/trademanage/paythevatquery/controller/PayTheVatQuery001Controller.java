/*
 * 文件名：TaxBalanceQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：leipeng
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.trademanage.paythevatquery.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
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
import com.joyintech.tams.valuation.trademanage.paythevatquery.dto.PayTheVatQuery001Dto;
import com.joyintech.tams.valuation.trademanage.paythevatquery.service.PayTheVatQuery001Service;
import com.joyintech.tams.constant.DictCode;

/**
 * 纳税增值税查询
 * 
 * @author chufengjie
 * @version 1.0 2019年02月23日
 * @see PayTheVatQuery001Dto
 * @since 1.0
 */
@Controller
@PageId("PayTheVatQuery001")
@RequestMapping("/valuation/trademanage/paythevatquery/PayTheVatQuery001")
public class PayTheVatQuery001Controller extends BaseController {

	@Autowired
	private PayTheVatQuery001Service payTheVatQuery001Service;

	/**
	 * TA交易-汇总查询 页面初始化 {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		this.setSelectOptionToModel(model, DictCode.交易方向);

		return super.init(model);
	}

	@RequestMapping("findPage")
	@ResponseBody
	public Page<PayTheVatQuery001Dto> findPage() throws Exception {
		return payTheVatQuery001Service.findByPage(RequestUtil.getPageParam());
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
	public List<SelectOptionDto> getBooksetId() throws Exception {
		String param = super.getRequest().getParameter("search");
		return payTheVatQuery001Service.getBooksetId(param);
	}
	
	/**
     * 查询前校验
     * 
     * @param dto 参数
     * @param result 校验结果
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("doCheck")
    public AjaxResult doCheck(@Valid @RequestBody PayTheVatQuery001Dto dto,
            BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return new AjaxResult();
    }

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
				"/com/joyintech/tams/valuation/trademanage/paythevatquery/conf/export_paythevatquery001.xml", listdto);
		return ExportUtil.export(this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
				+ DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
				+ FileNameUtil.getFileExtName(outFile), outFile, true);
	}

}
