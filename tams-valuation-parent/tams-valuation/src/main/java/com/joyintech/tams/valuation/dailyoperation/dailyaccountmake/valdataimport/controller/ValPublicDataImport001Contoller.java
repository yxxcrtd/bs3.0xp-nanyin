/*
 * 文件名：ValPublicDataImport001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
*/
package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valdataimport.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.service.ValAccountHandle001Service;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valdataimport.dto.ValPublicDataImport001LogDto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valdataimport.service.ValPublicDataImport001Service;
import com.joyintech.tams.valuation.interf.inertfStartUp.service.ValInertfStartUp001Service;
import com.joyintech.tams.valuation.common.service.BaseCommonService;

/**
 * 模板示例<br>
 * 账套管理
 * @author 何阳阳
 * @version 1.0 2018年3月13日
 * @see ValPublicDataImport001Contoller
 * @since 1.0
 */

@Controller
@PageId("ValPublicDataImport001")
@RequestMapping("/valuation/dailyoperation/dailyaccountmake/valdataimport/ValPublicDataImport001")
public class ValPublicDataImport001Contoller extends BaseController {
	
	//代表公共数据
	private static String InterfFlag = "InterfData";
	@Autowired
	private ValPublicDataImport001Service valPublicDataImport001Service;
	@Autowired
	private ValInertfStartUp001Service valInertfStartUp001Service;
	@Autowired
	private ValAccountHandle001Service valAccountHandle001Service;
    @Autowired
    private BaseCommonService baseCommonService;
    
    @Autowired
    private InterfCommonService interfCommonService;
	
	/**
     * 页面初始化，显示画面<br>
     * 
     * @param model Model
     * @return 初始化路径
     * @see
     * @since 1.0
     */
    public String init(Model model) {
        String sysDate=baseCommonService.getSysDateString();
        if(sysDate==null||"".equals(sysDate)){
            sysDate=DateUtil.format(new Date());
        }
        model.addAttribute("sysDate", sysDate);
        return super.init(model);
    }
    
    @RequestMapping("checkExecute")
    @ResponseBody
    public int checkExecute(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return valAccountHandle001Service.checkExecute(map);
    }
    
    
    @RequestMapping("/getInterGroup")
    @ResponseBody
    public List<TreeNode> getInterGroup(){
    	Map<String,Object> map = RequestUtil.getPageParam();
    	map.put("batchName", getParam("TmpCName"));
    	map.put("taskGrpType", InterfFlag);
    	List<TreeNode> treeList = valAccountHandle001Service.getTreeNodeList(map);
    	return treeList;
    }
    
    @RequestMapping("/getInterGroupCode")
    @ResponseBody
    public List<SelectOptionDto> getInterGroupCode(){
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("TmpCName", getParam("TmpCName"));
    	List<SelectOptionDto>  list = valPublicDataImport001Service.getInterGroupCode(map);
    	return list;
    }
    
    
    @RequestMapping("/findLogPage")
    @ResponseBody
    public List<ValPublicDataImport001LogDto> findLogPage(){
    	List<ValPublicDataImport001LogDto>  list = new ArrayList<>();
    	Map<String,Object> map = RequestUtil.getPageParam();
    	//map.put("createDate", DateUtil.parse("20180815"));
    	map.put("status", this.getParam("status"));
    	map.put("execTaskId", this.getParam("execTaskId"));
    	if(StringUtil.isNotEmpty(map.get("exDate"))){
        	map.put("createDate", new Date(Long.valueOf(map.get("exDate").toString())));
    		list = valPublicDataImport001Service.getInterActionLog(map);
    	}
    	return list;
    }
    
    
    @RequestMapping("/stopInterf")
    @ResponseBody
    public int stopInterf(){
    	Map<String, Object> map = new HashMap<>();
    	return valPublicDataImport001Service.stopInterLog(map);
    }
    
    
    /**
     * 接口启动
     * 
     * @throws IOException
     * @see
     * @since 1.0
     */
    @RequestMapping("runAs")
    @ResponseBody
    public String runAs()
        throws IOException {
        JsonObject json = new JsonObject();
        int check = 0;
		Date date = new Date();
		Map<String,Object> map = new HashMap<String,Object>();
		String execTaskId = interfCommonService.getUUid();
		//检查当前是否有正在运行中的批处理
    	if(valPublicDataImport001Service.checkInterLog(map)==0){
            String datas = this.getParam("datas");
            String startDate = this.getParam("startDate");
            String endDate = this.getParam("endDate");
            String url = valInertfStartUp001Service.getUrl(null);
            Map<String, String> urlParams = valPublicDataImport001Service.getParm(datas, DateUtil.formatDate(startDate),
                  DateUtil.formatDate(endDate),null,InterfCommonConstant.接口模式_全账套);//modify by dongjun on 20180104
            urlParams.put(InterfCommonConstant.接口_任务执行ID, execTaskId);
            valInertfStartUp001Service.runInterf(url, urlParams);
            check=1;
    	}
    	
        json.addProperty("isCheck", check);
        json.addProperty("createDate", date.getTime());
        json.addProperty("execTaskId", execTaskId);
        
        return json.toString();
    }
  
}