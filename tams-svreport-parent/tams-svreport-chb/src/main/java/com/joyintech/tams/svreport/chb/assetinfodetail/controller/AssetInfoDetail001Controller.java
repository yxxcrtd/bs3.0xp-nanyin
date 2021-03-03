/*
 * 文件名：InvestorsHoldInfoRegist001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年5月8日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.svreport.chb.assetinfodetail.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.errormessage.dto.ErrorMessage001;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.service.AssetInfoDetailMService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 资产要素登记
 *
 * @author weiyuqiang
 * @version 1.6.0 2018年5月8日
 * @see AssetInfoDetail001Controller
 * @since 1.0
 */
@Controller
@PageId("AssetInfoDetail001")
@RequestMapping("/chb/assetinfodetail/AssetInfoDetail001")
public class AssetInfoDetail001Controller extends BaseController {

	/**
	 * 资产要素service
	 */
	@Autowired
	private AssetInfoDetailMService assetInfoDetailMService;

    
    @SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(getClass());
    
	/**
	 * {@inheritDoc}
	 */
	@Override
    @RequestMapping(PAGE_INIT)
	public String init(Model model) {
//        model.addAttribute("searchDataStart", DateUtil.addDay(DateUtil.getSysDate(),-1));
//        model.addAttribute("searchDataEnd", DateUtil.getSysDate());
//        model.addAttribute("statusT", DictCodeListExt2.报送数据状态_新建.getValue());
//        model.addAttribute("sendStatusT", DictCodeListExt2.报送状态_手工处理.getValue());
//        model.addAttribute("cny", DictCodeListExt1.币种_中债_人民币.getValue());
		Map<String, Object> map = RequestUtil.getPageParam();
		if (StringUtil.isNotEmpty(map.get("submitStatus"))) {
			model.addAttribute("submitStatus", map.get("submitStatus").toString().trim());
			if (StringUtil.isNotEmpty(map.get("status"))) {
				model.addAttribute("status", map.get("status").toString().trim());
			}
			if(StringUtil.isNotEmpty(map.get("submitFeedbackStatus"))){
            	model.addAttribute("submitFeedbackStatus", map.get("submitFeedbackStatus").toString().trim());
            }
			model.addAttribute("searchDataStart", map.get("searchDataStart"));
			model.addAttribute("searchDataEnd", map.get("searchDataEnd"));
			model.addAttribute("isMain", "01");
		}
		super.setSelectOptionToModel(model,DictCode.资产_负债类别, DictCode.资产_负债二级类别
				, DictCode.中债_上游资产一级分类, DictCode.中债_上游资产二级分类,DictCode.中债_上游资产三级分类,DictCode.中债_上游资产四级分类,
				DictCode.中债_上游资产五级分类, DictCode.中债_处理状态);
		return super.init(model);
	}


	/**
	 *
	 * 业务画面查询事件
	 * <功能详细描述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("searchPage")
	public Page<AssetInfoDetailDto> searchPage() {
		Page<AssetInfoDetailDto> assetList = assetInfoDetailMService.searchForPage(RequestUtil.getPageParam());
		return assetList;
	}


	@ResponseBody
	@RequestMapping("delete")
	public AjaxResult doDelete(String assetUuid,String dataStatus) {
		AjaxResult result = new AjaxResult(this.getMessage("REPORT_COMMON_I_0007"));
		AssetInfoDetailDto dto = new AssetInfoDetailDto();
		dto.setAssetUuid(assetUuid);
		dto.setDataStatus(dataStatus);
		try{
				assetInfoDetailMService.doDelete(dto);
		}catch(Exception e){
			logger.info("删除数据错误",e);
		}
		return result;
	}

    /**
     * 导出文件
     *
     * @return 导出的excel
     * @see
     * @since 1.6.0
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = ACTION_EXPORT)
    public ResponseEntity<byte[]> export() {

		List<AssetInfoDetailDto> dtoList = assetInfoDetailMService.setDataList(RequestUtil.getParameters(getRequest()));
		File outFile = assetInfoDetailMService.makeFile(
				"/com/joyintech/tams/svreport/chb/assetinfodetail/conf/export_conf.xml", dtoList);
		return ExportUtil.export(
				"资产基本信息" + GlobalConstant.OPEN_PARENTHESIS
						+ DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
						+ GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
				outFile, true);

    }
    

     
    
    /**
     * 资产代码下拉框
     * @return 产品名称列表
     */
    @RequestMapping("/getAssetCode")
    @ResponseBody
    public List<SelectOptionDto> getRegCode() {
        return assetInfoDetailMService.queryAssetCodeList(RequestUtil.getPageParam());
    }
}
