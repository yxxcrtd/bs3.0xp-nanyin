/*
 * 文件名：ValPublicDataImport001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
*/
package com.joyintech.tams.disclosure.information.reveal.genreport.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.busicommon.genreport.dto.BatchGenResultDto;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.entity.ProdInfoListEntity;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.information.reveal.detailshow.dto.RepDetailsShow001Dto;
import com.joyintech.tams.disclosure.information.reveal.genreport.service.BatchGenReport001Service;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;

/**
 * 
 * 手工批量生成报表
 * @author chenxc
 * @version 1.0 2022-10-13
 * @see BatchGenReport001Contoller
 * @since 1.0
 */

@Controller
@PageId("BatchGenReport001")
@RequestMapping("/disclosure/information/reveal/genreport/BatchGenReport001")
public class BatchGenReport001Contoller extends BaseController {
	
    @Autowired
    private BatchGenReport001Service batchGenReport001Service;
    
    @Autowired
    private CommonPlatformDateService commonPlatformDateService;
    
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(BatchGenReport001Contoller.class);
	/**
     * 页面初始化，显示画面<br>
     * 
     * @param model Model
     * @return 初始化路径
     * @see
     * @since 1.0
     */
    public String init(Model model) {
        model.addAttribute(ReportGlobalConstant.REPORT_SERVER_URL, CacheUtil.getSysParam(ReportGlobalConstant.REPORT_SERVER_URL));
        model.addAttribute(ReportGlobalConstant.REPORT_SERVER_PATH, CacheUtil.getSysParam(ReportGlobalConstant.REPORT_SERVER_PATH));
        model.addAttribute(ReportGlobalConstant.REPORT_USERNAME, CacheUtil.getSysParam(ReportGlobalConstant.REPORT_USERNAME));
        model.addAttribute(ReportGlobalConstant.REPORT_PASSWORD, CacheUtil.getSysParam(ReportGlobalConstant.REPORT_PASSWORD));

        model.addAttribute("cureDate", DateUtil.getCurrentDate());
        
        //model.addAttribute("prodInfoList", JsonUtils.object2Json(repDetailsShow001Service.getProdInfo()));
    	return super.init(model);
    }

	@RequestMapping("queryProdList")
	@ResponseBody
	public List<ProdInfoListEntity> queryProdList(){
		Map<String, Object> param=RequestUtil.getPageParam();
		String repId = param.get("repId").toString();
		Date execDate = (Date)param.get("execDate");
		return batchGenReport001Service.queryProdList(repId, execDate);
	}
    
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody Map<String, Object> param) {
		return new AjaxResult();
	}
	
    /**
	 * 批量执行生成报表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/batchExec")
	@ResponseBody
    public  AjaxResult batchExec(HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> params = RequestUtil.getPageParam();
		
		//前台传入的参数处理
		//产品列表转换
		String listProd = params.get("listProd").toString();
		if ( listProd!=null && listProd.length()> 0 ) {
			JSONArray jsonProds = JSON.parseArray(listProd);
			List<RepProdInfo> listProds = new ArrayList<>();
			for ( Object s:jsonProds  ) {
				JSONObject jo = (JSONObject)JSON.parse(s.toString());
				RepProdInfo prod = new RepProdInfo();
				prod.setProdId(jo.get("id").toString());
				prod.setProdName(jo.get("name").toString());
				listProds.add(prod);
			}
			params.put("listProd", listProds);
		}

		//增加平台时间
		params.put("platformDate", commonPlatformDateService.getSysDate());
		
		//传入参数结束
		List<BatchGenResultDto> listResult = null;
		AjaxResult ret = new AjaxResult();
		try {
			listResult =  batchGenReport001Service.batchExec(params);
			StringBuffer sbRet = new StringBuffer();
			for ( BatchGenResultDto dto: listResult) {
				sbRet.append(dto);
			}
			if (sbRet.length()>0 ) {
				ret.setMessage(sbRet.toString(), MessageType.提示消息);
			}
		} catch ( Exception e ) {
			listResult = new ArrayList<>();
			ret.setMessage("批量报表异常:"+e.getMessage(), MessageType.提示消息);
		}
		return ret;
    }

    /**
     * 
     * 获取查询条件
     * <功能详细描述>
     * @param repId
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("/getRepsearch")
    @ResponseBody
    public RepDetailsShow001Dto getRepsearch(String repId){
        RepDetailsShow001Dto dto = batchGenReport001Service.getRepScreenList(repId);
        return dto;
    }
    
	/**
	 *
	 * 获取季度枚举值
	 * <功能详细描述>
	 * @param
	 * @return
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("getQuarter")
	@ResponseBody
	public List<SelectOptionDto> getQuarter(){
		SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.季度, null);
		List<SelectOptionDto> list = selectDto.getOptions();
		return list;
	}
}
