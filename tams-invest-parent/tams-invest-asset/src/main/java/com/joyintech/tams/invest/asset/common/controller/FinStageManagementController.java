/*
 * 文件名：FinStageManagementController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年3月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.common.controller;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.util.*;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.asset.common.dto.FinStageManagementDto;
import com.joyintech.tams.invest.asset.common.service.FinStageManagementService;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 期次管里
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年3月11日
 * @see FinStageManagementController
 * @since 1.0
 */
@Controller
@PageId("FinStageManagement")
@RequestMapping("/invest/asset/common/FinStageManagement")
public class FinStageManagementController extends BaseController{

    @Autowired
    private FinStageManagementService finStageManagementService;
    @Autowired
    private CommonBusinessService commonBusinessService;
    @Override
    public String init(Model model){
        model.addAttribute("finprodId", this.getParam("finprodId"));
        model.addAttribute("finprodName", this.getParam("finprodName"));
        model.addAttribute("finprodMarketId", this.getParam("finprodMarketId"));
        model.addAttribute("calendarId", this.getParam("calendarId"));
        model.addAttribute("workflowId", this.getParam("workflowId"));
        model.addAttribute("ccy", this.getParam("ccy"));
        setEntity(model);
        return super.init(model); 
    }
    
   private void setEntity(Model model){
       Map<String,Object> paramMap = new HashMap<String,Object>();
       paramMap.put("finprodId", this.getParam("finprodId"));
       String workflowId = this.getParam("workflowId");
       //待办
       if(StringUtil.isNotEmpty(workflowId)){
           FinStageManagementDto finStageManagementDto = finStageManagementService.getBussinessEntityByWorkflowId(workflowId);
           model.addAttribute("finprodId", finStageManagementDto.getFinprodId());
           model.addAttribute("finprodName",finStageManagementDto.getFinprodName());
           model.addAttribute("finprodMarketId", finStageManagementDto.getFinprodMarketId());
           model.addAttribute("calendarId", finStageManagementDto.getCalendarId());
           model.addAttribute("ccy", finStageManagementDto.getCcy());
           paramMap.put("finprodId", finStageManagementDto.getFinprodId());
       }
       Map<String,Object> map = finStageManagementService.getRates(paramMap);
       if(null!=map){
           model.addAttribute("buyoutRate", map.get("BUYOUT_RATE"));
           model.addAttribute("chlRate", map.get("CHL_RATE"));//通道费率
       }
   }
   
   /**
    * 初始化
    * <功能详细描述>
    * @param dto
    * @return
    * @throws ParseException
    * @see
    * @since 1.0
    */
   @RequestMapping(value = "/initStageManagementInfo")
   @ResponseBody
   public List<FinStageManagementDto> initInfo(@RequestBody FinStageManagementDto dto) throws ParseException {
       String finprodId = dto.getFinprodId();
       String workflowId = dto.getWorkflowId();
       List<FinStageManagementDto> finStageManagementList = new ArrayList<FinStageManagementDto>();
           //来自代办
           if(StringUtil.isNotEmpty(workflowId)){
               //根据工作流与留痕处理提供的共通方法获取业务实体
               FinStageManagementDto finStageManagementDto = finStageManagementService.getBussinessEntityByWorkflowId(workflowId);
               finStageManagementList = finStageManagementDto.getSelectedItem();
           }else{
               if(StringUtil.isNotEmpty(finprodId)){
                   Map<String,Object> paramMap = new HashMap<String,Object>();
                   paramMap.put("finprodId", finprodId);
                   paramMap.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
                   paramMap.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
                   paramMap.put("cashType2", DictCodeListExt1.现金流二级类型_利息.getValue());
                   finStageManagementList = finStageManagementService.getfinStageManagementByProductId(paramMap);
               }
           }
       return finStageManagementList;
   }
   
   /**
    * check
    * <功能详细描述>
    * @param dto
    * @param result
    * @return
    * @see
    * @since 1.0
    */
   @ResponseBody
   @RequestMapping("check")
   public AjaxResult check(@RequestBody @Validated({
       ValidateGroupEdit.class}) FinStageManagementDto dto, BindingResult result) {
       return commonCheck(result, dto.getOperateType());
   }

    /**
     * check
     * <功能详细描述>
     * @param dto
     * @param result
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("isCheckMature")
    public AjaxResult isCheckMature(@RequestBody FinStageManagementDto dto) {
        AjaxResult result = new AjaxResult();
        result.setData(finStageManagementService.isCheckMature(dto));
        return result;
    }
    
    /**
     * isCheckTarde
     * 期次子计划是否存在交易  yzg 20190909
     * @param dto
     * @param result
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("isCheckTarde")
    public Boolean isCheckTarde(@RequestBody FinStageManagementDto dto) {
    	if(commonBusinessService.isTransInAsset(dto.getFinprodId()) > 0) {
    		return true;
    	}
    	return false;
    }

    /**
     * 期次子计划是否存在流程中数据
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping("isWorkFlowCheck")
    public Boolean isWorkFlowCheck(@RequestBody FinStageManagementDto dto){
        List<ProcessMark> data = finStageManagementService.isWorkFlowCheck(dto);
        if(data!=null&&data.size()>0){
            return true;
        }
        return false;
    }
   /**
    * 保存
    * @param dto
    * @param br
    * @return
    * @throws Exception
    * @see
    * @since 1.0
    */
   @ResponseBody
   @RequestMapping(PAGE_SAVE)
   public AjaxResult save(@RequestBody @Validated({
       ValidateGroupEdit.class}) FinStageManagementDto dto, BindingResult br)
       throws Exception {
       if (br.hasErrors()) {
           return new AjaxResult(br);
       }
       WorkFlowParameter<FinStageManagementDto> params = new WorkFlowParameter<FinStageManagementDto>();
       params.setPageId(this.getPageId());
       params.setElementId(dto.getElementId());
       params.setBusinessCode(dto.getFinprodMarketId());
       params.setBusinessType(DictCodeList.共通业务类型_期次管理.getValue());
       // 设置流程业务说明
       params.setComment(entity -> {
           return WorkFlowUtil.concatComment(getMessage("FinStageManagement.finprodId")+
               GlobalConstant.OPEN_PARENTHESIS+entity.getFinprodMarketId()+GlobalConstant.CLOSE_PARENTHESIS,
               getMessage("FinStageManagement.finprodName")+GlobalConstant.OPEN_PARENTHESIS+
               entity.getFinprodName()+GlobalConstant.CLOSE_PARENTHESIS);
       });
       // 给参数变成ProcessParameter而非WorkFlowParameter
       ProcessParameter<FinStageManagementDto> parameter = new ProcessParameter<FinStageManagementDto>();
       parameter.setWorkFlowParameter(params);
       // 系统页面表IS_NEED_WORKFLOW 是否走流程，01-是，02-否
       String msg = finStageManagementService.start(parameter, dto);
       return new AjaxResult(msg);

   }


   //add by mashukai 20201119 	TISHKBS-1376 计划类利率型资产期次没办法导出，没有一个界面可以导出期次信息的起息日，到期日，及期次的利率 start
    /**
     * 导出
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
            throws Exception {
        String datas = this.getParam("datas");
        List<FinStageManagementDto> dtoList =new ArrayList<FinStageManagementDto>();
        if(StringUtil.isNotEmpty(datas)){
            dtoList = JsonUtils.json2List(datas,FinStageManagementDto.class);
            for(FinStageManagementDto pDto:dtoList){
                String vstr = DateUtil.format(pDto.getVdate(),"yyyy-MM-dd");
                String mstr = DateUtil.format(pDto.getMdate(),"yyyy-MM-dd");
                pDto.setMdateStr(mstr);
                pDto.setVdateStr(vstr);
            }
        }else{
            dtoList.add(new FinStageManagementDto());
        }
        File outFile = finStageManagementService.makeFile("/com/joyintech/tams/invest/asset/common/conf/export_conf.xml",dtoList);
        return ExportUtil.export(this.getMessage(this.getPageId()) + SystemGlobalConstant.POINT
                + FileNameUtil.getFileExtName(outFile),outFile,true);
    }
    //add by mashukai 20201119 	TISHKBS-1376 计划类利率型资产期次没办法导出，没有一个界面可以导出期次信息的起息日，到期日，及期次的利率 end
}
