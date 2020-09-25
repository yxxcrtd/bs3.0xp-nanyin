/*
 * 文件名：FinProductLedger001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.ledger.controller;

import java.io.File;
import java.util.ArrayList;
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
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.product.productelement.ledger.dto.FinProductLedger001Dto;
import com.joyintech.tams.invest.product.productelement.ledger.service.FinProductLedger001Service;

/**
 * 产品新建管理
 * 
 * @author zhangliang
 * @version 1.0 2018年9月17日
 * @see FinNetValueProductBuild001Controller
 * @since 1.0
 */
@Controller
@PageId(value = "FinProductLedger001")
@RequestMapping("/invest/product/productelement/ledger/FinProductLedger001")
public class FinProductLedger001Controller extends BaseController {

	/**
	 * {@linkplain}
	 */
	@Autowired
	private FinProductLedger001Service finProductLedger001Service;
	
	@Autowired
    private IPlatformDate iPlatformDate;
	
	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
		setSelectOptionToModel(model,
				new DictCode[] { DictCode.收益类型, DictCode.处理模式, DictCode.目标客户类型, DictCode.投资性质, DictCode.募集方式, 
						DictCode.开放类型, DictCode.产品收益标识, DictCode.产品系列, DictCode.发行状态,
						DictCode.是否, DictCode.目标客户类型,DictCode.产品风险等级});

        model.addAttribute("queryDate", DateUtil.format(iPlatformDate.getSysDate(), "yyyy-MM-dd"));
		List<SelectOptionDto> options2 = new ArrayList<SelectOptionDto>();
		SelectOptionDto option2 = new SelectOptionDto();
        option2.setId("02");
        option2.setText("是");
        options2.add(option2);
        option2 = new SelectOptionDto();
        option2.setId("01");
        option2.setText("否");
        options2.add(option2);       
		model.addAttribute("YESNO", JsonUtils.objectArray2Json(options2.toArray()));
		FinProductLedger001Dto finProductLedger001Dto= new FinProductLedger001Dto();
		model.addAttribute("entity",finProductLedger001Dto);
		return super.init(model);
	}

	/**
	 * 产品代码联想下拉框
	 * 
	 * @return 产品代码列表
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("queryProdIdInfo")
	public Page<SelectOptionDto> queryProdIdInfo() {
		return finProductLedger001Service.queryProdIdInfo(RequestUtil.getPageParam());
	}

	/**
	 * 画面查詢方法
	 * 
	 * @return 所有數據
	 * @throws Exception 異常
	 */
	@ResponseBody
	@RequestMapping("selectForPage")
	public Page<FinProductLedger001Dto> selectForPage() {
		return finProductLedger001Service.selectForPage(RequestUtil.getPageParam());
	}

	/**
	 * 导出信息excel
	 * 
	 * @return excel
	 * @throws Exception 异常
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("export")
	public ResponseEntity<byte[]> export() throws Exception {
		String datas = this.getParam("datas");
        List<FinProductLedger001Dto> listdto;
        if (datas == null) {
        	return finProductLedger001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
        }
        else {
            listdto = JsonUtils.json2List(datas, FinProductLedger001Dto.class);
        }
        File outFile = finProductLedger001Service.makeFile(
            "/com/joyintech/tams/invest/product/productelement/ledger/conf/export_conf.xml", listdto);
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date())
                                 + SystemGlobalConstant.CLOSE_PARENTHESIS
                                 + SystemGlobalConstant.POINT
                                 + FileNameUtil.getFileExtName(outFile),
            outFile, true);
	}

}
