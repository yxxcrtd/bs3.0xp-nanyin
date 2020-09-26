/*
 * 文件名：OneKeyMakeAccount001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2019年2月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.makeaccount.controller;

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
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.batch.batchprocess.dto.BatchTaskDto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.dto.ValAccountHandle001Dto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.service.ValAccountHandle001Service;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valdataexport.service.ValPublicDataexport001Service;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valdataimport.dto.ValPublicDataImport001LogDto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valdataimport.service.ValPublicDataImport001Service;
import com.joyintech.tams.valuation.makeaccount.dto.OneKeyMakeAccount001Dto;
import com.joyintech.tams.valuation.makeaccount.services.OneKeyMakeAccount001Services;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.service.BaseCommonService;

/**
 * 一键做账
 * @author HuangJian
 * @version 1.0 2019年2月26日
 * @see OneKeyMakeAccount001Controller
 * @since 1.0
 */
@Controller
@PageId("OneKeyMakeAccount001")
@RequestMapping("/valuation/makeaccount/OneKeyMakeAccount001")
public class OneKeyMakeAccount001Controller extends BaseController{

    @Autowired
    private OneKeyMakeAccount001Services oneKeyMakeAccount001Services;
    
    @Autowired
    private BaseCommonService baseCommonService;
    
    @Autowired
    private ValPublicDataImport001Service valPublicDataImport001Service;
    
//    @Autowired
//    private InterfCommonService interfCommonService;
    
    @Autowired
	private ValAccountHandle001Service valAccountHandle001Service;
    
    @Autowired
    private ValPublicDataexport001Service valPublicDataexport001Service;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // TODO Auto-generated method stub
        this.setSelectOptionToModel(model, DictCode.批处理账套类型);
        String YJZZ = CacheUtil.getSysParam("UNITFYCACCOUNTANT_MODEL_CODE");
        String[] yjzuArr = null;
        if (StringUtil.isNotEmpty(YJZZ)) {
            yjzuArr = YJZZ.split(",");
        }
        model.addAttribute("YJZZ", yjzuArr);
        String sysDate=baseCommonService.getSysDateString();
        if(sysDate==null||"".equals(sysDate)){
            sysDate=DateUtil.format(new Date());
        }
        model.addAttribute("sysDate", sysDate);
        model.addAttribute("batchTaskNums", this.getBatchTaskid().size());
        this.setSelectOptionToModel(model, DictCode.批处理账套类型);
        return super.init(model);
    }
    
    /**
     * 
     * 左侧的树形任务菜单
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("/findAllTask")
    @ResponseBody
    @SuppressWarnings("rawtypes")
    public List<TreeNode> findAllTask(){
        Map<String,Object> map = RequestUtil.getPageParam();
        map.put("taskGrpType", "task");
        List<TreeNode> treeList = oneKeyMakeAccount001Services.getTreeNodeList(map);
        return treeList;
    }
    
    
    
    @RequestMapping("/findloglogtotal")
    @ResponseBody
    public List<OneKeyMakeAccount001Dto> findloglogtotal(){
        Map<String,Object> map =RequestUtil.getPageParam();
        return oneKeyMakeAccount001Services.findloglogtotal(map);
    }
    
    
    @RequestMapping("/findLogPage")
    @ResponseBody
    public List<ValAccountHandle001Dto> findLogPage () throws Exception{
        Map<String,Object> map =RequestUtil.getPageParam();
        return oneKeyMakeAccount001Services.findLogPage(map);
    }
    
    @ResponseBody
    @RequestMapping("/check")
    public AjaxResult check(){
       if(oneKeyMakeAccount001Services.check()){
           return new AjaxResult();
       }else{
           return new AjaxResult("存在一键做账任务未结束！", MessageType.错误消息);
       }
    }
    
    @ResponseBody
    @RequestMapping("/docomplete")
    public String docomplete(String selectedBatchGroup, String selectedProductGroup,
                           String startDate, String endDate, String batchGroupType, String prodGroupType,String detailName){
      //检查当前是否有正在运行中的批处理
        int check = 0;
        Map<String,Object> map = new HashMap<String,Object>();
        if(valPublicDataImport001Service.checkInterLog(map)==0){
            return  oneKeyMakeAccount001Services.doComplete(selectedBatchGroup, selectedProductGroup, startDate, endDate, batchGroupType, prodGroupType,detailName);
              
        }else{
            
            JsonObject json = new JsonObject();
            json.addProperty("check", check);
            return json.toString();
        }
        
        
        
        
        
    }
    /**
     * 套账下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getBatchTaskid")
    @ResponseBody
    public List<BatchTaskDto> getBatchTaskid() {
        Map<String,Object> map=new HashMap<String,Object>();
        return valAccountHandle001Service.getBatchTaskid(map);
    }

    @RequestMapping("/checkAccountLog")
    @ResponseBody
    public  List<OneKeyMakeAccount001Dto>  checkAccountLog(){
        Map<String,Object> map =RequestUtil.getPageParam();
       return   oneKeyMakeAccount001Services.checkAccountLog(map);
        
        
        
    }

    @RequestMapping("/findDataBackLog")
    @ResponseBody
    public List<ValPublicDataImport001LogDto> findDataBackLog(){
        List<ValPublicDataImport001LogDto>  list = new ArrayList<>();
        Map<String,Object> map = RequestUtil.getPageParam();
        //map.put("createDate", DateUtil.parse("20180815"));
        map.put("status", this.getParam("status"));
        map.put("execTaskId", this.getParam("execTaskId"));
            list = valPublicDataexport001Service.getInterActionLog(map);
        return list;
    }
}
