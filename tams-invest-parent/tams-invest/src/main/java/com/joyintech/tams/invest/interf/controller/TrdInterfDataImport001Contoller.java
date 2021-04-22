/*
 * 文件名：ValPublicDataImport001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
*/
package com.joyintech.tams.invest.interf.controller;

import com.google.gson.JsonObject;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.*;
import com.joyintech.tams.invest.interf.dto.TrdInterfDataImport001LogDto;
import com.joyintech.tams.invest.interf.service.TrdInterfDataImport001Service;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.service.BaseCommonService;
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
 * @see TrdInterfDataImport001Contoller
 * @since 1.0
 */

@Controller
@PageId("TrdInterfDataImport001")
@RequestMapping("/invest/interf/TrdInterfDataImport001")
public class TrdInterfDataImport001Contoller extends BaseController {

    /**
     * 接口平台组号
     */
	private static String INTERF_PLACE_GPCODE = "gpcode";
    /**
     * 接口平台触发器编号
     */
    private static String INTERF_PLACE_TGCODE = "tgcode";

    private static String sysDate ;

    private static String gpcode ;

    private static String trigger ;

	@Autowired
	private TrdInterfDataImport001Service trdInterfDataImport001Service;
    @Autowired
    private BaseCommonService baseCommonService;


	/**
     * 页面初始化，显示画面<br>
     *
     * @param model Model
     * @return 初始化路径
     * @see
     * @since 1.0
     */
    @Override
    public String init(Model model) {
        gpcode = CacheUtil.getSysParam(INTERF_PLACE_GPCODE);
        trigger = CacheUtil.getSysParam(INTERF_PLACE_TGCODE);
        sysDate=baseCommonService.getSysDateString();
        if(sysDate==null||"".equals(sysDate)){
            sysDate=DateUtil.format(new Date());
        }
        model.addAttribute("sysDate", sysDate);
        return super.init(model);
    }


    @RequestMapping("/getInterGroup")
    @ResponseBody
    @SuppressWarnings("rawtypes")
    public List<TreeNode> getInterGroup(){

    	Map<String,Object> map = RequestUtil.getPageParam();
    	map.put("gpcode",gpcode);
        map.put("trigger",trigger);
    	return trdInterfDataImport001Service.getTreeNodeList(map);
    }

    @RequestMapping("/getFinprodId")
    @ResponseBody
    public Page<SelectOptionDto> getFinprodId() {
        return trdInterfDataImport001Service.getFinprodId(RequestUtil.getPageParam());
    }



    @RequestMapping("/findLogPage")
    @ResponseBody
    public List<TrdInterfDataImport001LogDto> findLogPage(){
    	List<TrdInterfDataImport001LogDto>  list = new ArrayList<>();
    	Map<String,Object> map = RequestUtil.getPageParam();
        map.put("gpcode",gpcode);
        map.put("trigger",trigger);
        map.put("predate",sysDate);
    	return trdInterfDataImport001Service.getInterActionLog(map);
    }


    @RequestMapping("/stopInterf")
    @ResponseBody
    public int stopInterf(){
    	Map<String, Object> map = new HashMap<>();
    	return trdInterfDataImport001Service.stopInterLog(map);
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
    public AjaxResult runAs() throws IOException {

        Map<String,Object> map = RequestUtil.getPageParam();
        Map<String,Object> params = new HashMap<>();
        Map<String,Object> parameter = new HashMap<>();
        Object prodIdObj = map.get("prodList");
        if(StringUtil.isNotEmpty(prodIdObj)) {
            parameter.put("prodList",((String)prodIdObj).split(GlobalConstant.COMMA));
        }else{
            parameter.put("prodList",new String []{});
        }
        Object dataObj = map.get("datas");
        if(StringUtil.isNotEmpty(dataObj)) {
            params.put("routers",((String)dataObj).split(GlobalConstant.COMMA));
        }else{
            params.put("routers",new String []{});
        }
        parameter.put("queryDate",map.get("queryDate"));
        params.put("parameter",parameter);
        params.put("async",true);
        params.put("predate",sysDate);
        params.put("preseqno", map.get("preseqno"));
        params.put("gpcode",gpcode);
        params.put("trigger",trigger);
        return trdInterfDataImport001Service.runAs(params);
    }

}
