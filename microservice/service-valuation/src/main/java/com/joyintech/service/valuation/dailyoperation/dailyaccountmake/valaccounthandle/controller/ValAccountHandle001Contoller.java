/*
 * 文件名：ValPublicDataImport001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
*/
package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valaccounthandle.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valaccounthandle.dto.ValAccountHandle001Dto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valaccounthandle.dto.ValAccountHandle002Dto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valaccounthandle.service.ValAccountHandle001Service;
import com.joyintech.system.batch.batchprocess.dto.BatchTaskDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.dto.ClassForStringLIst;

/**
 * 模板示例<br>
 * 账套管理
 * @author 何阳阳
 * @version 1.0 2018年3月13日
 * @see ValAccountHandle001Contoller
 * @since 1.0
 */

@RestController(value="serviceValAccountHandle001Contoller")
@RequestMapping("/ValAccountHandle001")
public class ValAccountHandle001Contoller extends BaseController {
	
	@Autowired
	private ValAccountHandle001Service valAccountHandle001Service;
    /**
     * 页面初始化，显示画面<br>
     * 
     * @param model Model
     * @return 初始化路径
     * @see
     * @since 1.0
     */
//    public String init(Model model) {
//        String sysDate=baseCommonService.getSysDateString();
//        if(sysDate==null||"".equals(sysDate)){
//            sysDate=DateUtil.format(new Date());
//        }
//        model.addAttribute("sysDate", sysDate);
//        model.addAttribute("batchTaskNums", this.getBatchTaskid().size());
//        this.setSelectOptionToModel(model, DictCode.批处理账套类型);
//        return super.init(model);
//    }
    /**
     * 套账下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @PostMapping("/getBatchTaskid")
    public List<BatchTaskDto> getBatchTaskid(@RequestBody Map<String, Object> map) {
//        Map<String,Object> map=new HashMap<String,Object>();
        return valAccountHandle001Service.getBatchTaskid(map);
    }

    
    /**
     * 套账下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @PostMapping("/getBookSetId2/{page}/{length}")
    public Page<SelectOptionDto> getBookSetId(@RequestBody Map<String, Object> params,
    		@PathVariable("page") int page, @PathVariable("length") int length) {    	
    		return valAccountHandle001Service.getBookSetId2(page,length,params);
    }
    @PostMapping("/getBooksetIdByGrp/{page}/{length}")
    public Page<SelectOptionDto> getBooksetIdByGrp(@RequestBody Map<String, Object> map,
    		@PathVariable("page") int page, @PathVariable("length") int length) {
    	return valAccountHandle001Service.getBooksetIdByGrp(page,length,map);
    }
    
    @PostMapping("/findAllTask")
    @SuppressWarnings("rawtypes")
    public List<TreeNode> findAllTask(@RequestBody Map<String,Object> params){
//    	Map<String,Object> map = RequestUtil.getPageParam();
//    	map.put("taskGrpType", "task");
    	return valAccountHandle001Service.getTreeNodeList(params);
//    	return treeList;
    }
    
    
    @PostMapping("/checkBokkSetDate")
    public String checkBokkSetDate(@RequestBody Map<String,Object> map){
//        Map<String,Object> map = RequestUtil.getPageParam();
//        String prodGroupType = String.valueOf(map.get("prodGroupType"));
//        String booksetId = (String)map.get("booksetId");
//        String[] booksetIdArr = null;
//        if(StringUtil.isNotEmpty(booksetId)) {
//        	if (GlobalConstant.BATCH_EXECUTE_TYPE_GROUP.equals(prodGroupType)) {
//        		// 账套组
//        		String[] prodGroupSeqNos = StringUtil.split(booksetId, ",");
//        		booksetIdArr = valAccountHandle001Service.findAllProdbyGroup(prodGroupSeqNos).toArray(new String[0]);
//        	} else {
//        		// 账套
//        		booksetIdArr = booksetId.split(",");
//        	}
//        }
//        map.put("booksetIdArr", booksetIdArr);
    	return valAccountHandle001Service.checkBokkSetDate(map);
    }
    
    @PostMapping("/findAllProdbyGroup")
    public ClassForStringLIst findAllProdbyGroup(@RequestBody String[] prodGroupSeqNos) {
    	ClassForStringLIst dto = new ClassForStringLIst();
    	dto.setPara(valAccountHandle001Service.findAllProdbyGroup(prodGroupSeqNos));
    	return dto;
    }
    
    
    @PostMapping("/checkExecute")
    public int checkExecute(@RequestBody Map<String,Object> map){
//        Map<String,Object> map = RequestUtil.getPageParam();
        return valAccountHandle001Service.checkExecute(map);
    }

    
    @PostMapping("/findLogPage")
    public List<ValAccountHandle001Dto> findLogPage(@RequestBody Map<String,Object> map) throws Exception{
//    	Map<String,Object> map =RequestUtil.getPageParam();

        return valAccountHandle001Service.findLogPage(map);
    }
    
    
//    @RequestMapping(value = "executeBatch")
//    @ResponseBody
//    public AjaxResult executeBatch(String selectedBatchGroup, String selectedProductGroup,
//                                   String startDate, String endDate, String batchGroupType, String prodGroupType) {
//        sysBatchStart001Service.executeBatch();
//        return new AjaxResult("批处理启动成功，可通过监控页面查看");
//    }
    
 
    @PostMapping("/checkBokPosition")
    public List<ValAccountHandle002Dto> checkBokPosition(@RequestBody Map<String,Object> map) throws Exception{
        	return valAccountHandle001Service.checkBokPosition(map);
//        	list = valAccountHandle001Service.selectCheckLog(map);
    }
    
    @PostMapping("/selectCheckLog")
    public List<ValAccountHandle002Dto> selectCheckLog(@RequestBody Map<String, Object> map) {
    	return valAccountHandle001Service.selectCheckLog(map);
    }
    
    
    @PostMapping("/bokCheckBefore")
    public int bokCheckBefore(@RequestBody Map<String,Object> map){
//    	Map<String,Object> map =RequestUtil.getPageParam();
        return valAccountHandle001Service.bokCheckBefore(map);
    }
    
    
    @RequestMapping("geyCheckSysValue")
    @ResponseBody
    public int geyCheckSysValue(){
    	int res=0;
    	//获取系统参数
    	String checkValue = CacheUtil.getSysParam("PRE_BATCH_CHECK");
    	if("OFF".equals(checkValue)){
    		res=0;
    	}else if("ON".equals(checkValue)){
    		res=1;
    	}
        return res;
    }
    
    /**
     * 根据账套代码获取子账套代码
     * @param bookSetIds
     * @return
     */
    @PostMapping("/getBooksetIdSon")
    public ClassForStringLIst getBooksetIdSon(@RequestBody List<String> bookSetIds){
    	ClassForStringLIst dto = new ClassForStringLIst();
    	dto.setPara(valAccountHandle001Service.getBooksetIdSon(bookSetIds));
    	return dto;
    }
    
    
    /**
     * 获取账套到期日
     * @return
     */
    @RequestMapping("getMdateForBookSet")
    @ResponseBody
    public List<String> getMdateForBookSet(@RequestBody Map<String,Object> map){
        return valAccountHandle001Service.getMdateForBookSet(map);
    }
}