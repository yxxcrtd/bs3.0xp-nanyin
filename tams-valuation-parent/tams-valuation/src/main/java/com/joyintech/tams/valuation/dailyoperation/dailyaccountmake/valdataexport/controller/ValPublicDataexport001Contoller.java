/*
 * 文件名：ValPublicDataImport001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
*/
package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valdataexport.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.service.ValAccountHandle001Service;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valdataexport.service.ValPublicDataexport001Service;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valdataimport.dto.ValPublicDataImport001LogDto;
import com.joyintech.tams.valuation.interf.inertfStartUp.service.ValInertfStartUp001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

/**
 * 模板示例<br>
 * 账套管理
 * @author 何阳阳
 * @version 1.0 2018年3月13日
 * @see ValPublicDataexport001Contoller
 * @since 1.0
 */

@Controller
@PageId("ValPublicDataexport001")
@RequestMapping("/valuation/dailyoperation/dailyaccountmake/valdataexport/ValPublicDataexport001")
public class ValPublicDataexport001Contoller extends BaseController {
	
	//代表公共数据
	private static String InterfFlag = "DataBack";
	@Autowired
	private ValPublicDataexport001Service valPublicDataexport001Service;
	@Autowired
	private ValInertfStartUp001Service valInertfStartUp001Service;
	@Autowired
	private ValAccountHandle001Service valAccountHandle001Service;
    @Autowired
    private InterfCommonService interfCommonService;


    //add  by zhangnian  20210609 start
    /**
     * 接口平台组号
     */
    private static String INTERF_PLACE_GPCODE = "gpcodehc";
    /**
     * 接口平台触发器编号
     */
    private static String INTERF_PLACE_TGCODE = "tgcodehc";

    private static String sysDate ;

    private static String gpcode ;

    private static String trigger ;

    @Autowired
    private IPlatformDate iPlatformDate;

    //add  by zhangnian  20210609 end
	
    
    /**
     * 套账下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getBookSetId")
    @ResponseBody
    public Page<SelectOptionDto> getBookSetId() {
        Map<String,Object> map=RequestUtil.getPageParam();
        return valAccountHandle001Service.getBookSetId2(map);
    }
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
        gpcode = CacheUtil.getSysParam(INTERF_PLACE_GPCODE);
        trigger = CacheUtil.getSysParam(INTERF_PLACE_TGCODE);
        sysDate= iPlatformDate.getSysDateStr();
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
        return valPublicDataexport001Service.checkExecute(map);
    }
    
    
    @RequestMapping("/getInterGroup")
    @ResponseBody
    public List<TreeNode> getInterGroup(){
    	/*Map<String,Object> map = RequestUtil.getPageParam();
    	map.put("batchName", getParam("TmpCName"));
    	map.put("taskGrpType", InterfFlag);
    	List<TreeNode> treeList = valAccountHandle001Service.getTreeNodeList(map);
    	return treeList;*/
        Map<String,Object> map = RequestUtil.getPageParam();
        map.put("gpcode",gpcode);
        map.put("trigger",trigger);
        return valPublicDataexport001Service.getTreeNodeList2(map);
    }
    
    @RequestMapping("/getInterGroupCode")
    @ResponseBody
    public List<SelectOptionDto> getInterGroupCode(){
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("TmpCName", getParam("TmpCName"));
    	List<SelectOptionDto>  list = valPublicDataexport001Service.getInterGroupCode(map);
    	return list;
    }
    
    
    @RequestMapping("/findLogPage")
    @ResponseBody
    public List<ValPublicDataImport001LogDto> findLogPage(){
    	/*List<ValPublicDataImport001LogDto>  list = new ArrayList<>();
    	Map<String,Object> map = RequestUtil.getPageParam();
    	//map.put("createDate", DateUtil.parse("20180815"));
    	map.put("status", this.getParam("status"));
    	map.put("execTaskId", this.getParam("execTaskId"));
    	if(StringUtil.isNotEmpty(map.get("exDate"))){
        	map.put("createDate", new Date(Long.valueOf(map.get("exDate").toString())));
    		list = valPublicDataexport001Service.getInterActionLog(map);
    	}
    	return list;*/
        List<ValPublicDataImport001LogDto>  list = new ArrayList<>();
        Map<String,Object> map = RequestUtil.getPageParam();
        map.put("gpcode",gpcode);
        map.put("trigger",trigger);
        map.put("predate",sysDate);
        return valPublicDataexport001Service.getInterActionLog(map);
    }
    
    
    @RequestMapping("/stopInterf")
    @ResponseBody
    public int stopInterf(){
    	Map<String, Object> map = new HashMap<>();
    	return valPublicDataexport001Service.stopInterLog(map);
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
    public AjaxResult runAs()
        throws IOException {
        /*JsonObject json = new JsonObject();
        int check = 0;
		Date date = new Date();
		Map<String,Object> map = new HashMap<String,Object>();
		String execTaskId = interfCommonService.getUUid();
		//检查当前是否有正在运行中的批处理
    	if(valPublicDataexport001Service.checkInterLog(map)==0){
    	    String booksetId=this.getParam("booksetId");
            String datas = this.getParam("datas");
            String startDate = this.getParam("startDate");
            String endDate = this.getParam("endDate");
            String url = valInertfStartUp001Service.getUrl(null);
            Map<String, String> urlParams = valPublicDataexport001Service.getParm(datas, DateUtil.formatDate(startDate),
                  DateUtil.formatDate(endDate),booksetId);
            urlParams.put(InterfCommonConstant.接口_任务执行ID, execTaskId);
            valInertfStartUp001Service.runInterf(url, urlParams);
            check=1;
    	}
    	
        json.addProperty("isCheck", check);
        json.addProperty("createDate", date.getTime());
        json.addProperty("execTaskId", execTaskId);
        
        return json.toString();*/
        Map<String,Object> map = RequestUtil.getPageParam();
        Map<String,Object> params = new HashMap<>();
        Map<String,Object> parameter = new HashMap<>();
        Object prodIdObj = map.get("booksetId");
        if(StringUtil.isNotEmpty(prodIdObj)) {
            parameter.put("booksetList",((String)prodIdObj).split(GlobalConstant.COMMA));
        }else{
            parameter.put("booksetList",new String []{});
        }
        Object dataObj = map.get("datas");
        if(StringUtil.isNotEmpty(dataObj)) {
            params.put("routers",((String)dataObj).split(GlobalConstant.COMMA));
        }else{
            params.put("routers",new String []{});
        }
        parameter.put("queryDate",map.get("startDate"));
        params.put("parameter",parameter);
        params.put("async",true);
        params.put("predate",sysDate);
        params.put("preseqno", interfCommonService.getUUid());
        params.put("gpcode",gpcode);
        params.put("trigger",trigger);
        return valPublicDataexport001Service.runAs(params);
    }
  
}