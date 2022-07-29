/*
 * 文件名：ValPublicDataImport001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
*/
package com.joyintech.tams.disclosure.information.reveal.detailshow.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.common.util.JacksonUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service.GetPrdInfoSelectService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.repcheck.service.RepCheckInfoService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.reportDeal.service.ReportDealService;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.ReportManager;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.information.config.reporttree.service.ReportTreeMenuConfiguration001Service;
import com.joyintech.tams.disclosure.information.reveal.detailshow.dto.RepDetailsShow001Dto;
import com.joyintech.tams.disclosure.information.reveal.detailshow.service.RepDetailsShow001Service;
import com.joyintech.tams.disclosure.information.reveal.detailshow.service.WebOfficeObject;
import com.joyintech.tams.disclosure.information.reveal.detailshow.service.iMsgServer2015;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 模板示例<br>
 * 账套管理
 * @author 何阳阳
 * @version 1.0 2018年3月13日
 * @see RepDetailsShow001Contoller
 * @since 1.0
 */

@Controller
@PageId("RepDetailsShow001")
@RequestMapping("/disclosure/information/reveal/detailshow/RepDetailsShow001")
public class RepDetailsShow001Contoller extends BaseController {
	
    @Autowired
    private RepDetailsShow001Service repDetailsShow001Service;
    
    @Autowired
    private ReportTreeMenuConfiguration001Service reportTreeMenuConfiguration001Service;
    
    @Autowired
    ReportDealService reportDealService;
    
    @Autowired
	private GetPrdInfoSelectService getPrdInfoSelectService;
    
    @Autowired
	private ReportManager reportManager;
    @Autowired
    private RepCheckInfoService repCheckInfoService;
    
    @Autowired
	@Qualifier("masterDataSource") DataSource master;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(RepDetailsShow001Contoller.class);
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
    
    /**
	 * 分页查询产品下拉框
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getPProdIdList")
	@ResponseBody
    public Page<SelectOptionDto> getPProdIdList(){
	    Map<String, Object> param=RequestUtil.getPageParam();
	    if(param.get("repId")!=null){
	        repDetailsShow001Service.getRandgeRule(param);
	        //param.put("checkFor", "01");
	        //CheckConfirmMessage msg=repCheckInfoService.check(param);
	    }
		Page<SelectOptionDto> prdInfoSelect = getPrdInfoSelectService.getPProdIdList(param);
        return prdInfoSelect;
    }
	/**
	 * 分页查询客户下拉框
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getCusInfoSelect")
	@ResponseBody
	public Page<SelectOptionDto> getCusInfoSelect() {
		Map<String, Object> param=RequestUtil.getPageParam();
		Page<SelectOptionDto> cusInfoSelect = getPrdInfoSelectService.getCusInfoSelect(param);
		return cusInfoSelect;
	}
    /**
     * 
     * 获取系统枚举参数
     * <功能详细描述>
     * @param DictCode
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getDictSelect")
    @ResponseBody
    public String getDictSelect(String DictCode){
        Map<String,Object> map = repDetailsShow001Service.getCatchDetail(DictCode);
        String json = JacksonUtil.toJSONString(map);        
        return json;
    }
    
    /**
     * 
     * 获取树表
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping("/getRepGroup")
    @ResponseBody
    public List<TreeNode> getRepGroup(String reportCode){
        List<TreeNode> list = reportTreeMenuConfiguration001Service.getTreeNodeListByTreeName(reportCode);
        return list;
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
        RepDetailsShow001Dto dto = repDetailsShow001Service.getRepScreenList(repId);
        return dto;
    }

    /**
     * 
     * 获取查询条数
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unused")
	@ResponseBody
    @RequestMapping("/querySameCount")
    public AjaxResult querySameCount() {
    	String msg = StringUtil.EMPTY;
    	Map<String, Object>params = RequestUtil.getPageParam();
    	int count = repDetailsShow001Service.querySameCount(params);
    	if(count > 0){
    		return new AjaxResult("存在相同的报表，是否继续生成？",MessageType.确认消息);
    	}
    	else {
    		return null;
    	}
    }
//    @SuppressWarnings("finally")
//   	@RequestMapping("/showDetailHtml")
//       @ResponseBody
//       public AjaxResult showDetailHtml(){
//           AjaxResult value = new AjaxResult();
//           try {
//           	Map<String,Object> params = RequestUtil.getPageParam();
////           	String operateType = params.get("operateType").toString();
//           	RepReveal repReveal = new RepReveal();
//           	if(StringUtil.isNotEmpty(params.get("portnum"))){
//               	repReveal.setProdid(params.get("portnum").toString());
//           	}
//           	if(params.get("startDate") != null) {
//           		repReveal.setStartDate(DateUtil.parse(params.get("startDate").toString()));
//           	}
//           	if(params.get("endDate") != null) {
//           		repReveal.setEndDate(DateUtil.parse(params.get("endDate").toString()));
//           	}
//           	String uri = params.get("uri").toString();
//           	String result = repDetailsShow001Service.showDetailHtml(uri, repReveal);
//           	value.setData(result);
//           	if(StringUtil.isEmpty(result)){
//           		value.setMessage("生成失败", MessageType.错误消息);
//           	}
//
////           	boolean flag = false;
////           	boolean confirmFlag = true;
////
////               if(flag){
////                   value.setMessage("生成成功", MessageType.提示消息);
////               }else if(confirmFlag) {
////                   value.setMessage("生成失败", MessageType.错误消息);
////               }
//           }catch (Exception e) {
//               value.setMessage("生成失败:"+e.getMessage(), MessageType.错误消息);
//               logger.error("error:",e);
//           }
//           finally{
//              // return value;
//           }
//		return value;
//	}
//
//    @RequestMapping(value="/showDetailHtmlNew", method = RequestMethod.POST)
//	public void showDetailHtmlNew(HttpServletRequest request, HttpServletResponse response) {
//    	Map<String,Object> params = RequestUtil.getPageParam();
//    	if(null!=params.get("endDate")){
//    		Date endDate = DateUtil.parse(params.get("endDate").toString());
//    		params.put("endDate", new java.sql.Date(endDate.getTime()));
//    	}
//    	if(null!=params.get("startDate")){
//    		Date endDate = DateUtil.parse(params.get("startDate").toString());
//    		params.put("startDate", new java.sql.Date(endDate.getTime()));
//    	}
//    	ReportInfo r1 = reportManager.getReportByURI(params.get("uri").toString());
//
//		reportManager.setDataSource(master);
//
////		HashMap<String, Object> parameters1 = new HashMap<String, Object>();
////		parameters1.put("portnum", "A170A3624");
//
//		reportManager.genReport(r1, params, request, response);
//
//	}

    /**
	 * 查询产品系列下拉框
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getProdSeriesList")
	@ResponseBody
    public List<SelectOptionDto> getProdSeriesList(){
	    Map<String, Object> param=RequestUtil.getPageParam();
	    if(param.get("repId")!=null){
	        repDetailsShow001Service.getRandgeRule(param);
	        //param.put("checkFor", "01");
	        //CheckConfirmMessage msg=repCheckInfoService.check(param);
	    }
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.产品系列, null);
	    List<SelectOptionDto> prodSeriesList = selectDto.getOptions();
        return prodSeriesList;
    }

	/**
	 * 实现了两个功能：1.左边树单击，浏览报表的模板（转成html格式）；
	 * 2.点击预览按钮，生成指定的docx报表数据流，（1）生成服务器文件，（2）转成html传到前端实现预览功能
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/previewReport", method = RequestMethod.POST)
	public void previewReport(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> params = RequestUtil.getPageParam();
        params.put("reportId", params.get("repid"));
        params.put("reportDate", params.get("enddate"));
		repDetailsShow001Service.previewReport(params,response);
	}

	/**
	 * 生成报表：（1）生成服务器文件，如果有就覆盖，（2）创建生成报表的工作流
	 * @return
	 */
	@RequestMapping("/genReport")
    @ResponseBody
    public AjaxResult genReport(){
        AjaxResult value = new AjaxResult();
        try {
        	//对参数进行修正，防止前台传入的参数错误或者变量大小写错误
        	Map<String,Object> params = RequestUtil.getPageParam();
        	params.put("modelId", params.get("modelid"));
        	params.put("prodId", params.get("prodid"));
        	params.put("pprodId", params.get("pprodid"));
        	params.put("prodName", params.get("prodname"));
        	params.put("repId", params.get("repid"));
        	params.put("reportId", params.get("repid"));
        	params.put("endDate", params.get("enddate"));
        	params.put("repName",params.get("repname"));
        	if ( params.get("uri")==null )
        		params.put("uri",params.get("modelid"));
        	if (params.get("prodId")==null )
        		params.put("prodId",params.get("pprodId"));
        	
        	boolean flag = false;
        	boolean confirmFlag = true;
        	RepReveal repReveal = new RepReveal();
        	String prodId = params.get("pprodid").toString();
        	if(params.get("repid") != null) {
        		repReveal.setRepId(params.get("repid").toString());
        	}
			repReveal.setProdid(prodId);
			confirmFlag = reportDealService.selectConfirmStatus(repReveal);  
    		if(confirmFlag == true) {  //检查通过：不存在相同的已确认的报告
    			reportDealService.updateDataStatus(repReveal);
    			repDetailsShow001Service.genReport(params);
   				value.setMessage("生成成功", MessageType.提示消息);
    		}else {
    			value.setMessage("存在相同且已确认的报表", MessageType.错误消息);
    		}
        }catch (Exception e) {
            value.setMessage("生成失败:"+e.getMessage(), MessageType.错误消息);
            logger.error("error:",e);
        }
		return value;
	}

    /**
     * 导出合并文档
     *
     * @return excel
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
    @RequestMapping("mergeExport")
    public ResponseEntity<byte[]> mergeExport() throws IOException {
    	Map<String,Object> params = RequestUtil.getPageParam();
    	params.put("modelId", params.get("modelid"));
    	params.put("prodId", params.get("prodid"));
    	params.put("pprodId", params.get("pprodid"));
    	params.put("prodName", params.get("prodname"));
    	params.put("repId", params.get("repid"));
    	params.put("reportId", params.get("repid"));
    	params.put("endDate", params.get("enddate"));
    	params.put("repName",params.get("repname"));
    	if ( params.get("uri")==null )
    		params.put("uri",params.get("modelid"));
    	if (params.get("prodId")==null )
    		params.put("prodId",params.get("pprodId"));
        return repDetailsShow001Service.exportReport(params);
    }

//	/**
//	 * 导出合并文档
//	 *
//	 * @return excel
//	 * @throws Exception
//	 *             异常
//	 * @see
//	 * @since 1.0
//	 */
//	@RequestMapping("mergeExport")
//	public ResponseEntity<byte[]> mergeExport() throws IOException {
//		Map<String, Object> param = RequestUtil.getPageParam();
//		ReportModelManage001Dto reportModelManage001Dto = null;
//		try {
//			reportModelManage001Dto = (ReportModelManage001Dto) JsonUtils.map2entity(param,
//					ReportModelManage001Dto.class);
//		} catch (Exception e) {
//			logger.error(e.getMessage(),e);
//			throw new IllegalArgumentException("转换异常");
//		}
//		reportModelManage001Dto.setParam(param);
//		return reportModelManage001Service.mergeExportSingle(reportModelManage001Dto);
//	}

	/**
	 *
	 * 获取系统枚举参数
	 * <功能详细描述>
	 * @param
	 * @return
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("getHalfYear")
	@ResponseBody
	public List<SelectOptionDto> getHalfYear(){
		SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.半年, null);
		List<SelectOptionDto> list = selectDto.getOptions();
		return list;
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

	/**
	 *
	 * 获取子产品代码下拉框
	 * <功能详细描述>
	 * @param
	 * @return
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("getProdIdList")
	@ResponseBody
	public Page<SelectOptionDto> getProdIdList(){
		Map<String, Object> param=RequestUtil.getPageParam();
		Page<SelectOptionDto> prdInfoSelect = getPrdInfoSelectService.getProdIdList(param);
		return prdInfoSelect;
	}

	/**
	 *
	 * 获取费率名称下拉框
	 * <功能详细描述>
	 * @param
	 * @return
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("getFeeNameList")
	@ResponseBody
	public Page<SelectOptionDto> getFeeNameList(){
		Map<String, Object> param=RequestUtil.getPageParam();
		Page<SelectOptionDto> feeNameList = getPrdInfoSelectService.getFeeNameList(param);
		return feeNameList;
	}

	/**
	 *
	 * 获取要素名称下拉框
	 * <功能详细描述>
	 * @param
	 * @return
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("getElementNameList")
	@ResponseBody
	public Page<SelectOptionDto> getElementNameList(){
		Map<String, Object> param=RequestUtil.getPageParam();
		Page<SelectOptionDto> elementNameList = getPrdInfoSelectService.getElementNameList(param);
		return elementNameList;
	}

}
