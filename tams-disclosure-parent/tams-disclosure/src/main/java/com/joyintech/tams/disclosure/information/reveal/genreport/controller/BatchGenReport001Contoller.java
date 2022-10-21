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
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.busicommon.genreport.dto.BatchGenResultDto;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.dao.ReportInfoMapper;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.pojo.ReportInfo;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.information.reveal.detailshow.dto.RepDetailsShow001Dto;
import com.joyintech.tams.disclosure.information.reveal.detailshow.service.RepDetailsShow001Service;
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
    
	@Autowired
	private ReportInfoMapper reportInfoMapper;
	
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

	@RequestMapping("/queryProdList")
	@ResponseBody
	public List<TreeNode> queryProdList() {
		Map<String, Object> param=RequestUtil.getPageParam();

		//报表编号
		String repId = "";
		String repProdType = "P";
		if ( param.get("repId")!=null ) { 
			repId = param.get("repId").toString();
			repProdType = getRepProdType(repId);
		}
		//根据报表的类型，确定是针对母产品进行报表还是子产品进行报表
		if ( !"N".equals(repProdType))
			return batchGenReport001Service.buildProdTree(repProdType,repId);
		return null;
	}
    
	private String getRepProdType(String repId) {
		//针对母产品的报表
		String parentProds ="CP_001_001_001"  //发行公告
			+"CP_001_002_015"  //季报
			+"CP_001_002_016"  
			+"CP_001_002_017"  //半年报
			+"CP_001_002_018"
			+"CP_001_002_019"  //年报
			+"CP_001_002_020"
			+"CP_001_002_021"  //调整投资非标准化债权类资产公告
			+"CP_001_004_001"   //增加产品销售机构公告
			+"CP_001_004_002"  //产品不成立公告（公募）
			+"CP_001_004_003"
			+"CP_001_004_004"   //单产品多费率模板1（公募）
			+"CP_001_004_005"
			+"CP_001_004_006"
			+"CP_001_004_007"

			+"CP_001_004_012"  //单产品多要素调整模板
			+"CP_001_004_013"
			+"CP_001_004_014"   //单要素多产品调整模板
			+"CP_001_004_015"
			+"CP_001_004_016"   //巨额赎回公告
			+"CP_001_004_017";
		String noPords = "CP_001_004_008"   //单费率多产品模板1（公募）
				+"CP_001_004_009"
				+"CP_001_004_010"
				+"CP_001_004_011";
		
		if ( parentProds.contains(repId)) {
			return "P";
		} else if ( noPords.contains(repId))  {
			return "N";
		}
		else {
			return "C";
		}
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
		//增加平台时间
		params.put("platformDate", commonPlatformDateService.getSysDate());
		params.put("repid", params.get("repId"));		
		
		//产品列表转换
		List<RepProdInfo> listProds = new ArrayList<>();
		String listProd = params.get("listProd").toString();
		if ( listProd!=null && listProd.length()> 0 ) {
			JSONArray jsonProds = JSON.parseArray(listProd);

			for ( Object s:jsonProds  ) {
				JSONObject jo = (JSONObject)JSON.parse(s.toString());
				RepProdInfo prod = new RepProdInfo();
				prod.setProdId(jo.get("id").toString());
				prod.setProdName(jo.get("name").toString());
				listProds.add(prod);
			}
		}
		
		//输出结果
		AjaxResult ret = new AjaxResult();
		
		StringBuffer sbRet = new StringBuffer();
		int idx = 1;
		String repName =  params.get("repName").toString();
		for (RepProdInfo prod : listProds) {
			BatchGenResultDto dto = null;
			try {
				dto =  batchGenReport001Service.exec(prod,params);
			} catch ( Exception e ) {
				dto = new BatchGenResultDto();
				dto.setRepId(params.get("repId").toString());
				dto.setRepName(params.get("repName").toString());
				dto.setProdId(prod.getProdId());
				dto.setProdName(prod.getProdName());
				dto.setGenDate((Date)params.get("platformDate"));
				dto.setResult("F");
				dto.setRemark("生成报表失败："+e.getMessage());
			}
			sbRet.append("★★"+repName+idx++ +"：");
			sbRet.append(dto);
			sbRet.append("<br>");
		}

		if (sbRet.length()>0 ) {
			ret.setData(sbRet.toString());
		}
		ret.setMessage("报表生成完成，详见明细信息。", MessageType.提示消息);

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
