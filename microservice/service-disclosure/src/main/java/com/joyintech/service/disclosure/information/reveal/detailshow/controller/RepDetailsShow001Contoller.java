/*
 * 文件名：ValPublicDataImport001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
*/
package com.joyintech.service.disclosure.information.reveal.detailshow.controller;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.disclosure.information.config.reporttree.service.ReportTreeMenuConfiguration001Service;
import com.joyintech.service.disclosure.information.reveal.detailshow.dto.RepDetailsShow001Dto;
import com.joyintech.service.disclosure.information.reveal.detailshow.service.RepDetailsShow001Service;
import com.joyintech.tams.common.base.entity.RepInfo;
import com.joyintech.tams.common.base.entity.RepJobRange;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.common.base.entity.RepTree;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service.GetPrdInfoSelectService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.repcheck.service.RepCheckInfoService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.reportDeal.service.ReportDealService;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.ReportManager;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.pojo.ReportInfo;
/**
 * 模板示例<br>
 * 账套管理
 * @author 何阳阳
 * @version 1.0 2018年3月13日
 * @see RepDetailsShow001Contoller
 * @since 1.0
 */

@RestController(value="serviceRepDetailsShow001Contoller")
@RequestMapping("/RepDetailsShow001")
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
	 * 分页查询产品下拉框
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/getRandgeRule")
    public List<RepJobRange> getRandgeRule(@RequestBody Map<String,Object> map)
        throws Exception {
//	    Map<String, Object> param=RequestUtil.getPageParam();
//	    if(param.get("repId")!=null){
	        return repDetailsShow001Service.getRandgeRule(map);
	        //param.put("checkFor", "01");
//	        //CheckConfirmMessage msg=repCheckInfoService.check(param);
//	    }
//		Page<SelectOptionDto> prdInfoSelect = getPrdInfoSelectService.getPrdInfoSelect(param);
//        return prdInfoSelect;
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
        String json = JSONObject.toJSONString(map);        
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
	@GetMapping("getTreeNodeListByTreeName")
    public List<TreeNode> getTreeNodeListByTreeName(String tmpCName){
        List<TreeNode> list = reportTreeMenuConfiguration001Service.getTreeNodeListByTreeName(tmpCName);
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
    @GetMapping("getRepsearch")
    public RepDetailsShow001Dto getRepsearch(String repId){
        RepDetailsShow001Dto dto = repDetailsShow001Service.getRepScreenList(repId);
        return dto;
    }
    
    @GetMapping("selectByPrimaryKey")
    public RepInfo selectByPrimaryKey(String repId){
    	RepInfo dto = repDetailsShow001Service.selectByPrimaryKey(repId);
        return dto;
    }
    
    /**
     * 根据主键查询树节点
     * @param repId
     * @return
     */
	@GetMapping("selectByRepTreePrimaryKey")
	public RepTree selectByRepTreePrimaryKey(String repId) {
		return repDetailsShow001Service.selectByRepTreePrimaryKey(repId);
	}
  
    
    @GetMapping("deleteErrorMsg")
    public int deleteErrorMsg(String repId){
    	return  repDetailsShow001Service.deleteErrorMsg(repId);
    }
    
    @PostMapping("/getErrorMessage")
    public List<ValidMessage> getErrorMessage(@RequestBody Map<String,Object> map) throws MalformedURLException{
    	return repDetailsShow001Service.getErrorMessage(map);
    	
    }
    
    
    @PostMapping("downFileAndSubmit")
    public boolean downFileAndSubmit(@RequestBody Map<String,Object> map) throws MalformedURLException{
    	return repDetailsShow001Service.downFileAndSubmit(map);
    }
    
    
    @PostMapping("downFileListAndSubmit")
    public boolean downFileListAndSubmit(@RequestBody Map<String,Object> map) throws MalformedURLException{
    	return repDetailsShow001Service.downFileListAndSubmit(map);
    }
    
    @PostMapping("querySameCount")
    public int querySameCount(@RequestBody Map<String,Object> map){
    	return repDetailsShow001Service.querySameCount(map);
    }
    
//    @GetMapping("selectByPrimaryKey")
//    public RepTree selectByPrimaryKey(String repId){
//    	return  repDetailsShow001Service.selectByPrimaryKey(repId);
//    }
    
    
    
//    @SuppressWarnings("finally")
//	@RequestMapping("/downFileAndSubmit")
//    @ResponseBody
//    public AjaxResult downFileAndSubmit(){
//        AjaxResult value = new AjaxResult();
//        List<ValidMessage> checkList = new ArrayList<ValidMessage>();
//        try {
//        	Map<String,Object> params = RequestUtil.getPageParam();
//        	String operateType = params.get("operateType").toString();
//        	String rep_id = params.get("rep_id").toString();
//        	RepReveal repReveal = new RepReveal();
//        	if(params.get("repId") != null) {
//        		repReveal.setRepId(params.get("repId").toString());
//        	}
//        	if(params.get("portnum") != null) {
//        		repReveal.setProdid(params.get("portnum").toString());
//        	}
//        	if(params.get("startDate") != null) {
//        		repReveal.setStartDate(DateUtil.parse(params.get("startDate").toString()));
//        	}
//        	if(params.get("endDate") != null) {
//        		repReveal.setEndDate(DateUtil.parse(params.get("endDate").toString()));
//        	}
//        	
//        	boolean flag = false;
//        	boolean confirmFlag = true;
//        	if("01".equals(operateType)){
//        		confirmFlag = reportDealService.selectConfirmStatus(repReveal);
//        		if(confirmFlag == true) {
//        			reportDealService.updateDataStatus(repReveal);
//        			checkList = repDetailsShow001Service.getErrorMessage(RequestUtil.getPageParam());
//        			if(checkList.size() == 0){
//        				flag = repDetailsShow001Service.downFileAndSubmit(RequestUtil.getPageParam());
//        			}
///*        			else{
//        				value.setErrorList(checkList);
//        			}*/
//        			
//        		}else {
//        			value.setMessage("存在相同且已确认的报表", MessageType.错误消息);
//        		}
//        	}
//            if(flag){
//                value.setMessage("生成成功", MessageType.提示消息);
//            }else if(confirmFlag) {
// //               value.setMessage("生成失败", MessageType.错误消息);
//                value.setErrorList(checkList);
//                repDetailsShow001Service.deleteErrorMsg(rep_id);
//            }
//        }catch (Exception e) {
//            value.setMessage("生成失败:"+e.getMessage(), MessageType.错误消息);
////            e.printStackTrace();
//            logger.error("error:",e);
//        }
//        finally{
//            return value; 
//        }
//    }
    
    @SuppressWarnings("finally")
	@RequestMapping("/downFileListAndSubmit")
    @ResponseBody
    public AjaxResult downFileListAndSubmit(){
        AjaxResult value = new AjaxResult();
        try {
            boolean flag = repDetailsShow001Service.downFileListAndSubmit(RequestUtil.getPageParam());
            if(flag){
                value.setMessage("生成成功", MessageType.提示消息);
            }else{
                value.setMessage("生成失败", MessageType.错误消息);
            }
        }catch (MalformedURLException e) {
            value.setMessage("生成失败:"+e.getMessage(), MessageType.错误消息);
//            e.printStackTrace();
            logger.error("error:",e);
        }finally{
            //return value;
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
    public ResponseEntity<byte[]> mergeExport() throws Exception {
        return repDetailsShow001Service.mergeExport(RequestUtil.getPageParam());
    }
    
    /*@RequestMapping("/getProdInfo")
    @ResponseBody 
    public String getProdInfo() throws Exception {
        List<ProdInfoDto>prodInfoList = repDetailsShow001Service.getProdInfo();
        Map<String,Object> prodMap = new HashMap<String,Object>();
        prodMap.put("prodInfoList", prodInfoList);
        String json = JSONObject.toJSONString(prodMap);
        return json;
    }*/
    
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
    public AjaxResult querySameCount() throws Exception{
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
    @SuppressWarnings("finally")
   	@RequestMapping("/showDetailHtml")
       @ResponseBody
       public AjaxResult showDetailHtml(){
           AjaxResult value = new AjaxResult();
           try {
           	Map<String,Object> params = RequestUtil.getPageParam();
//           	String operateType = params.get("operateType").toString();
           	RepReveal repReveal = new RepReveal();
           	if(StringUtil.isNotEmpty(params.get("portnum"))){
               	repReveal.setProdid(params.get("portnum").toString());
           	}
           	if(params.get("startDate") != null) {
           		repReveal.setStartDate(DateUtil.parse(params.get("startDate").toString()));
           	}
           	if(params.get("endDate") != null) {
           		repReveal.setEndDate(DateUtil.parse(params.get("endDate").toString()));
           	}
           	String uri = params.get("uri").toString();
           	String result = repDetailsShow001Service.showDetailHtml(uri, repReveal);
           	value.setData(result);
           	if(StringUtil.isEmpty(result)){
           		value.setMessage("生成失败", MessageType.错误消息);
           	}

//           	boolean flag = false;
//           	boolean confirmFlag = true;
//
//               if(flag){
//                   value.setMessage("生成成功", MessageType.提示消息);
//               }else if(confirmFlag) {
//                   value.setMessage("生成失败", MessageType.错误消息);
//               }
           }catch (Exception e) {
               value.setMessage("生成失败:"+e.getMessage(), MessageType.错误消息);
//               e.printStackTrace();
               logger.error("error:",e);
           }
           finally{
               //return value;
           }
        return value;
    }
    
    @RequestMapping(value="/showDetailHtmlNew", method = RequestMethod.POST)
	public void showDetailHtmlNew(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,Object> params = RequestUtil.getPageParam();
    	if(null!=params.get("endDate")){
    		Date endDate = DateUtil.parse(params.get("endDate").toString());
    		params.put("endDate", new java.sql.Date(endDate.getTime()));
    	}
    	if(null!=params.get("startDate")){
    		Date endDate = DateUtil.parse(params.get("startDate").toString());
    		params.put("startDate", new java.sql.Date(endDate.getTime()));
    	}
    	ReportInfo r1 = reportManager.getReportByURI(params.get("uri").toString());
		
		reportManager.setDataSource(master);
		
//		HashMap<String, Object> parameters1 = new HashMap<String, Object>();
//		parameters1.put("portnum", "A170A3624");
		
		reportManager.genReport(r1, params, request, response);
	}
}