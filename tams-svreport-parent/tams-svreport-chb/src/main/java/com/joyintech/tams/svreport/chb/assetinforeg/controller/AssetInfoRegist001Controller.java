/*
 * 文件名：InvestorsHoldInfoRegist001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年5月8日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.svreport.chb.assetinforeg.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.component.svrreport.chb.dto.ChbAssetInfoDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.svreport.chbsd.assetinfosub.service.SdAssetiInfoRegBatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.svreport.chb.assetinforeg.dto.AssetInfoRegist001Dto;
import com.joyintech.tams.svreport.chb.assetinforeg.service.AssetInfoRegist001Service;
import com.joyintech.tams.svreport.chb.assetinforeg.service.AssetInfoRegistMService;
import com.joyintech.tams.svreport.chb.proddurationreg.dto.ProdDurationReg001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;
/**
 * 资产要素登记
 *
 * @author weiyuqiang
 * @version 1.6.0 2018年5月8日
 * @see AssetInfoRegist001Controller
 * @since 1.0
 */
@Controller
@PageId("SvrAssetInfoRegist001")
@RequestMapping("/chb/assetinforegist/AssetInfoRegist001")
public class AssetInfoRegist001Controller extends BaseController {

	/**
	 * 资产要素service
	 */
	@Autowired
	private AssetInfoRegist001Service assetInfoRegist001Service;
    @Autowired
    private AssetInfoRegistMService assetInfoRegistMService;
    
    @SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(getClass());


	@Autowired
	private SdAssetiInfoRegBatchService sdAssetiInfoRegBatchService;
    
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
		super.setSelectOptionToModel(model, DictCode.币种_中债,DictCode.资产_负债类别
				,DictCode.交易流通场所_中债, DictCode.资产_负债二级类别
				,DictCode.报送数据状态, DictCode.报送状态, DictCode.报送反馈状态);
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
    @RequestMapping(SVReportGlobalConstant.SEARCH_PAGE)
	public Page<AssetInfoRegist001Dto> search() {
		Map<String, Object> param = RequestUtil.getPageParam();
		return assetInfoRegistMService.searchForPage(param);
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
        return assetInfoRegist001Service.export(this.getPageId(), pageParam);
    }
    
    /**
     * 手工处理功能
     * @return 提示信息
     * @param map 条件map
     */
    @ResponseBody
    @RequestMapping(value = "/manual" ,method = RequestMethod.POST)
    public AjaxResult doUpdate(@RequestBody Map<String,Object> map) {
    	assetInfoRegistMService.updateSumStaus(map);
        return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
    }
    
    /**
     * @Description 取消手工处理
     * @param map
     * @return
     * @return AjaxResult
     * @version 3.0 2019年1月24日
     */
    @ResponseBody
 	@RequestMapping(value = "/cancelMannul", method = RequestMethod.POST)
 	public AjaxResult cancelMannul(@RequestBody Map<String, Object> map) {
    	//取消手工处理 列表报送状态未更新 leipeng 20200710
    	if(CommonUtil.isBAmodel()){
    		map.put("orgCode", CommonUtil.getTreeInstCode());
    		map.put("belongOrgCode", CommonUtil.getInstitutionCodeByLoginUser_ms());
		}
    	boolean isDuplicate = assetInfoRegistMService.cancelMannul(map);
    	// 是否有重复数据(同时有已报送和未报送数据)
    	if (isDuplicate){
            AjaxResult ajaxResult = new AjaxResult(this.getMessage("REPORT_COMMON_I_0030"),MessageType.错误消息);
            // 防止提示信息后卡死
            ajaxResult.setStatuCode(HttpStatus.OK.value());
            return ajaxResult;
    	} else {
    	    return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
    	}
 	}
     
    
    /**
     * 资产代码下拉框
     * @return 产品名称列表
     */
    @RequestMapping("/getAssetCode")
    @ResponseBody
    public List<SelectOptionDto> getRegCode() {
        return assetInfoRegistMService.queryAssetCodeList(RequestUtil.getPageParam());
    }

	/**start  中债一二期直连代码  add  by  wangzehui  20201012*/

	/***
	 * 报送按钮的检查
	 * @return
	 */
	@ResponseBody
	@RequestMapping("submitBeforeCheck")
	public Map<String,Object> submitBeforeCheck() {
		Map<String, Object> param = RequestUtil.getPageParam();
		return  assetInfoRegistMService.submitBeforeCheck(param);
	}

	/**
	 * 中债直连报送
	 *
	 * @param map
	 *            传入需要报送的主键
	 * @return 反馈信息
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping(value = "/submitChb", method = RequestMethod.POST)
	public AjaxResult doSubmit(@RequestBody Map<String, Object> param) throws Exception {

		List<AssetInfoRegist001Dto> list=new ArrayList<>();
		// 选择行数据时
		if (param.containsKey("keyList")) {
			String keyList = param.get("keyList").toString();
			try {
				list = JsonUtils.json2List(keyList,
						AssetInfoRegist001Dto.class);
			}
			catch (IOException e) {
				// e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}else{
			Page<AssetInfoRegist001Dto> page = assetInfoRegistMService.searchForPage(RequestUtil.getPageParam());
			if(page!=null&&page.getRoot()!=null&&page.getRoot().size()>0){
				list = page.getRoot();
			}
			//查询数据
		}

		//报文拼接：2、进行数据结构转化，把[1]中取得的List，转化为报文拼接组件中调用方法的数据结构类
		List<ChbAssetInfoDto> dataList = new ArrayList<ChbAssetInfoDto>();
		for(AssetInfoRegist001Dto sdDto:list){
			ChbAssetInfoDto target = new ChbAssetInfoDto();
			BeanUtil.copyPropertiesIgnoreNull(target, sdDto);
			dataList.add(target);
		}

		sdAssetiInfoRegBatchService.sendBatchCommon(dataList);

		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0018"));
	}

	/**end  中债一二期直连代码  add  by  wangzehui  2020100910*/
}
