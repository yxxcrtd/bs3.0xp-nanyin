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

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.service.AssetInfoDetailMService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		super.setSelectOptionToModel(model, DictCode.金融产品类型_投管用,DictCode.资产_负债类别
				,DictCode.WIND一级分类_含债券中债, DictCode.资产_负债二级类别,DictCode.WIND二级分类_含债券二级分类等, DictCode.处理状态);
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
        Map<String, Object> pageParam = RequestUtil.getPageParam();
//      Map<String, String> cacheValue = CacheUtil.getAppCache(GlobalConstant.SYS_PARA_KEY, Map.class);
//      cacheValue.put(GlobalConstant.EXPORT_EXCEL_TYPE, DictCodeList.是否_中债_是.getValue());
//      CacheUtil.putAppCache(GlobalConstant.SYS_PARA_KEY, cacheValue);
//      if(pageParam.containsKey("keyList")){
//	 		List<AssetInfoRegist001Dto>  dataList=assetInfoRegistMService.searchForExport(pageParam);
//	 		  File outFile = assetInfoRegist001Service.makeFile(
//	 	             "/com/joyintech/tams/svreport/chb/assetinforeg/conf/export_conf.xml", dataList);
//	 	         return ExportUtil.export(
//	 	             this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
//	 	                                  + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
//	 	                                  + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
//	 	             outFile, true);
//	 	}
        if (pageParam.containsKey("expKeys")||pageParam.get("assetTypes")!=null){
        	pageParam.put("export_method_flg", "exportSelect");
        } else {
        	pageParam.put("export_method_flg", "export");
        }
//        return assetInfoDetailMService.export(this.getPageId(), pageParam);
		return null;
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
