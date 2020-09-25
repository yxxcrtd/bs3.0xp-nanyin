/*
 * 文件名：ValPublicDataImport001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
*/
package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valdataexport.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valdataexport.service.ValPublicDataexport001Service;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valdataimport.dto.ValPublicDataImport001LogDto;

/**
 * 模板示例<br>
 * 账套管理
 * @author 何阳阳
 * @version 1.0 2018年3月13日
 * @see ValPublicDataexport001Contoller
 * @since 1.0
 */

@RestController(value="serviceValPublicDataexport001Contoller")
@RequestMapping("/ValPublicDataexport001")
public class ValPublicDataexport001Contoller extends BaseController {
	
	@Autowired
	private ValPublicDataexport001Service valPublicDataexport001Service;
	/**
     * 套账下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
//    @RequestMapping("/getBookSetId")
//    @ResponseBody
//    public Page<SelectOptionDto> getBookSetId() {
//        Map<String,Object> map=RequestUtil.getPageParam();
//        return valAccountHandle001Service.getBookSetId2(map);
//    }
	/**
     * 页面初始化，显示画面<br>
     * 
     * @param model Model
     * @return 初始化路径
     * @see
     * @since 1.0
     */
    public String init(Model model) {
//        String sysDate=baseCommonService.getSysDateString();
//        if(sysDate==null||"".equals(sysDate)){
//            sysDate=DateUtil.format(new Date());
//        }
//        model.addAttribute("sysDate", sysDate);
        return super.init(model);
    }
    
    @PostMapping("checkExecute")
    public int checkExecute(@RequestBody Map<String, Object> map){
//        Map<String,Object> map = RequestUtil.getPageParam();
        return valPublicDataexport001Service.checkExecute(map);
    }
    
    
//    @RequestMapping("/getInterGroup")
//    @ResponseBody
//    public List<TreeNode> getInterGroup(){
//    	Map<String,Object> map = RequestUtil.getPageParam();
//    	map.put("batchName", getParam("TmpCName"));
//    	map.put("taskGrpType", InterfFlag);
//    	List<TreeNode> treeList = valAccountHandle001Service.getTreeNodeList(map);
//    	return treeList;
//    }
    
    @PostMapping("/getInterGroupCode")
    public List<SelectOptionDto> getInterGroupCode(@RequestBody Map<String,Object> map){
//    	Map<String,Object> map = new HashMap<String,Object>();
//    	map.put("TmpCName", getParam("TmpCName"));
    	return valPublicDataexport001Service.getInterGroupCode(map);
//    	return list;
    }
    
    
    @PostMapping("/findLogPage")
    public List<ValPublicDataImport001LogDto> findLogPage(@RequestBody Map<String, Object> params){
//    	List<ValPublicDataImport001LogDto>  list = new ArrayList<>();
//    	Map<String,Object> map = RequestUtil.getPageParam();
//    	//map.put("createDate", DateUtil.parse("20180815"));
//    	map.put("status", this.getParam("status"));
//    	map.put("execTaskId", this.getParam("execTaskId"));
//    	if(StringUtil.isNotEmpty(map.get("exDate"))){
//        	map.put("createDate", new Date(Long.valueOf(map.get("exDate").toString())));
//    		list = valPublicDataexport001Service.getInterActionLog(map);
//    	}
//    	return list;
    	return valPublicDataexport001Service.getInterActionLog(params);
    }
    
    
    @PostMapping("/stopInterf")
    public int stopInterf(@RequestBody Map<String, Object> map){
//    	Map<String, Object> map = new HashMap<>();
    	return valPublicDataexport001Service.stopInterLog(map);
    }
    
    
    /**
     * 接口启动
     * 
     * @throws IOException
     * @see
     * @since 1.0
     */
    @PostMapping("runAs")
    public int checkInterLog(@RequestBody Map<String, Object> params){
    	
    	return valPublicDataexport001Service.checkInterLog(params);
    	   
    }
  
}