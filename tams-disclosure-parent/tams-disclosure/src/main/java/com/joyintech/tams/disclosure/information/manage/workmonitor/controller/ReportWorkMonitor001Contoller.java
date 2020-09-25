/*
 * 文件名：ValProductInfoQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.manage.workmonitor.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCode;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.information.manage.workmonitor.dto.ReportWorkMonitor001Dto;
import com.joyintech.tams.disclosure.information.manage.workmonitor.dto.ReportWorkMonitorNew001Dto;
import com.joyintech.tams.disclosure.information.manage.workmonitor.service.ReportWorkMonitor001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import com.joyintech.framework.util.CacheUtil;

/**
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author ZhangYangFei
 * @version 1.0 2018年11月12日
 * @see ReportWorkMonitor001Contoller
 * @since 1.0
 */
@Controller
@PageId("ReportWorkMonitor001")
@RequestMapping("/disclosure/information/manage/workmonitor/ReportWorkMonitor001")
public class ReportWorkMonitor001Contoller extends BaseController {
    
    @Autowired
    private ReportWorkMonitor001Service reportWorkMonitor001Service;
    
//    @Autowired
//    private BaseCommonService baseCommonService;
//    
//    @Autowired
//    private RepDetailsShow001Service repDetailsShow001Service;
    /**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        
//        model.addAttribute("PRODID_LIST",JsonUtils.objectArray2Json(this.getFinprodId().toArray()));
        model.addAttribute("JOBID_LIST",JsonUtils.objectArray2Json(this.getJobidList().toArray()));
        // 入账日期默认为系统平台日
//        model.addAttribute("sysDate",DateUtil.format(baseCommonService.getSysDate(), "yyyy-MM-dd"));
//        model.addAttribute("prodInfoList", JsonUtils.objectArray2Json(repDetailsShow001Service.getProdInfo().toArray()));
        this.setSelectOptionToModel(model,DictCode.执行状态,DictCode.执行结果);
        
        return super.init(model);
    }
    
    /**
     * 
     * 分页数据查询
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ReportWorkMonitorNew001Dto> findPage() throws Exception{
    	Page<ReportWorkMonitorNew001Dto> pageList = reportWorkMonitor001Service.findPage(RequestUtil.getPageParam());
    	pageList.getRoot().forEach(dto -> {
    		if(StringUtil.isNotEmpty(dto.getEndExecDate()) && StringUtil.isNotEmpty(dto.getStartExecDate())) {
    			long interval = (dto.getEndExecDate().getTime() - DateUtil.parse(dto.getStartExecDate()).getTime())/1000;
    			dto.setSpendTime(interval + "");
    		}
    		dto.setExecStatusName(SysDataUtil.getNameFromDataDict(DictCode.执行状态, dto.getExecStatus()));
        	dto.setExecResult(SysDataUtil.getNameFromDataDict(DictCode.执行结果, dto.getExecResult()));
        });
    	return pageList;
    }
    
    @RequestMapping("/getJobidList")
    @ResponseBody
    public List<SelectOptionDto> getJobidList() {
         Map<String,Object> map=new HashMap<String,Object>();
         return reportWorkMonitor001Service.getJobidList(map);
    }
    
    /**
     * 产品下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getFinprodId")
    @ResponseBody
    public List<SelectOptionDto> getFinprodId() {

        List<SelectOptionDto> listSelectDto = new ArrayList<SelectOptionDto>();
        SelectOptionDto dto;
        dto = new SelectOptionDto();dto.setId("001");dto.setText("产品001");listSelectDto.add(dto);
        dto = new SelectOptionDto();dto.setId("002");dto.setText("产品002");listSelectDto.add(dto);
        dto = new SelectOptionDto();dto.setId("003");dto.setText("产品003");listSelectDto.add(dto);
        dto = new SelectOptionDto();dto.setId("004");dto.setText("产品004");listSelectDto.add(dto);
        dto = new SelectOptionDto();dto.setId("005");dto.setText("产品005");listSelectDto.add(dto);
        dto = new SelectOptionDto();dto.setId("006");dto.setText("产品006");listSelectDto.add(dto);
        dto = new SelectOptionDto();dto.setId("007");dto.setText("产品007");listSelectDto.add(dto);
        dto = new SelectOptionDto();dto.setId("008");dto.setText("产品008");listSelectDto.add(dto);
        dto = new SelectOptionDto();dto.setId("009");dto.setText("产品009");listSelectDto.add(dto);
        dto = new SelectOptionDto();dto.setId("0010");dto.setText("产品010");listSelectDto.add(dto);
        dto = new SelectOptionDto();dto.setId("0011");dto.setText("产品011");listSelectDto.add(dto);
        dto = new SelectOptionDto();dto.setId("0012");dto.setText("产品012");listSelectDto.add(dto);
        return listSelectDto;

        // Map<String,Object> map=new HashMap<String,Object>();
        // map.put("finprodType",DictCodeListExt1.金融产品类型_理财产品.getValue());
        // return reportWorkMonitor001Service.getFinprodId(map);
    }
    /**
     * 净值下发确认 -- 【确认】和【撤销确认】更新
     *
     * @return 返回的数据
     * @throws IOException 
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "updateConfirmStatusAndNoticeStatus", method=RequestMethod.POST)
    public AjaxResult updateConfirmStatusAndNoticeStatus(@RequestBody ReportWorkMonitor001Dto paDto,HttpServletRequest request) throws IOException  {

        //当前操作为确认还是下发【确认："QR",下发："XF"】
//        String confirmOrNotice = paDto.getUpdateTypeFlag();
        
        // 获取勾选的数据
        String data=paDto.getData();
        List<ReportWorkMonitor001Dto> listdto = JsonUtils.json2List(data, ReportWorkMonitor001Dto.class);
        
        //默认返回值，发起成功
        String msg = getMessage("COMMON_I_0001");
        
        for(ReportWorkMonitor001Dto dto : listdto){
            
            //实体类中，带入当前操作状态
//            dto.setConfirmOrNotice(confirmOrNotice);
            
            ProcessParameter<ReportWorkMonitor001Dto> proParam = new ProcessParameter<>();
            WorkFlowParameter<ReportWorkMonitor001Dto> params = new WorkFlowParameter<>();
            
            
            // 业务编号
	        params.setBusinessCode(IDGenerator.generate());
	        // 业务类型
	        params.setBusinessType(DictCodeListExt2.共通业务类型_报表确认管理.getValue());
            // 页面id
            params.setPageId(this.getPageId());
            // 元素id
            params.setElementId(paDto.getElementId());
            
            //设置流程业务说明, entity即传入的dto
            // 确认操作
             params.setComment(entity -> WorkFlowUtil.concatComment("报表确认管理",entity.getData(),entity.getCreateUser())
                 );
            // 塞入流程参数
            proParam.setWorkFlowParameter(params);
            
            try {
                msg = reportWorkMonitor001Service.start(proParam, dto);
            }
            catch (Exception e) {
                getLogger().error("启动流程失败：", e);
                return new AjaxResult(e.getMessage());
            }
        }
        
      // reportWorkMonitor001Service.updateConfirmStatus(listdto);
        
        return new AjaxResult(msg);
    }
    
    /**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @param operateType 操作类型
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid ReportWorkMonitor001Dto dto, BindingResult result, String operateType) {
      //  return commonCheck(result, operateType);
    	 return  new AjaxResult();
    }

    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
    	 String datas = this.getParam("datas");
//         Map<String, Object> paraMap = RequestUtil.getPageParam();
         List<ReportWorkMonitorNew001Dto> dtoList;
         if (datas == null) {
        	 //dtoList = reportWorkMonitor001Service.queryPageList(paraMap);
        	 dtoList = this.findPage().getRoot();
         }
         else {
        	 dtoList = JsonUtils.json2List(datas, ReportWorkMonitorNew001Dto.class);
         }
        File outFile = reportWorkMonitor001Service.makeFile("/com/joyintech/tams/disclosure/information/manage/workmonitor/conf/export_reportWorkMonitor001.xml",dtoList);
        String exportFileName = this.getPageId();
		com.joyintech.framework.base.intf.Page page = CacheUtil.getPage(this.getPageId());
        if(null != page) {
            exportFileName = page.getName()
                             + SystemGlobalConstant.OPEN_PARENTHESIS + DateUtil.getCurrentDate()
                             + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
                             + FileNameUtil.getFileExtName(outFile);
        }
		return ExportUtil.export(exportFileName, outFile, true);
    }
    /**
     * 重跑全部
     * @param param 输入条件
     * @return 01 无满足条件的规则   02 无满足条件的产品  03 操作成功 04 失败
     */
    @ResponseBody
    @RequestMapping("runAllJob")
    public AjaxResult runAgainJob(@RequestBody Map<String,Object> param){
    	boolean exeStatus =reportWorkMonitor001Service.runAgain(param);
    	if(exeStatus){
    		return new AjaxResult("执行成功");
    	}
    	else{
    		AjaxResult wb = new AjaxResult();
    		wb.setMessage("重新执行失败");
    		wb.setStatuCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    		wb.setMessageType(MessageType.错误消息);
    		return wb;
    	}
//    	if(StringUtil.equals("01", exeStatus)){
//    		return new AjaxResult("无满足条件的规则");
//    	}
//    	else if(StringUtil.equals("02", exeStatus)){
//    		return new AjaxResult("无满足条件的产品 ");
//    	}
//    	else if(StringUtil.equals("03", exeStatus)){
//    		return new AjaxResult("执行成功");
//    	}
//    	else{
//    		AjaxResult wb = new AjaxResult();
//    		wb.setMessage("重新执行失败");
//    		wb.setStatuCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//    		wb.setMessageType(MessageType.错误消息);
//    		return wb;
//    	}
        
    }
    /**
     * 重跑失败
     * @param param 输入条件
     * @return
     */
    @ResponseBody
    @RequestMapping("runErrorJob")
    public AjaxResult runErrorJob(@RequestBody Map<String,Object> param){
    	String  exeStatus =reportWorkMonitor001Service.runErrorAgain(param);
    	if(StringUtil.equals("01", exeStatus)){
    		return new AjaxResult("无满足条件的规则");
    	}
    	else if(StringUtil.equals("02", exeStatus)){
    		return new AjaxResult("无满足条件的产品 ");
    	}
    	else if(StringUtil.equals("03", exeStatus)){
    		return new AjaxResult("执行成功");
    	}
    	else{
    		AjaxResult wb = new AjaxResult();
    		wb.setMessage("重新执行失败");
    		wb.setStatuCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    		wb.setMessageType(MessageType.错误消息);
    		return wb;
    	}
        
    }
}
