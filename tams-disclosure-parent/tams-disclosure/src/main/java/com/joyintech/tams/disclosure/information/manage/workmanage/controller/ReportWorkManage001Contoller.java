/*
 * 文件名：ValProductInfoQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.manage.workmanage.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCode;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.common.common.service.BaseCommonService;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.information.manage.workmanage.dto.ReportWorkManage001Dto;
import com.joyintech.tams.disclosure.information.manage.workmanage.service.ReportWorkManage001Service;
import com.joyintech.tams.disclosure.information.reveal.detailshow.service.RepDetailsShow001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
/**
 * 
 * 金融产品交易查询
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValNetValueConfirm001Contoller
 * @since 1.0
 */
@Controller
@PageId("ReportWorkManage001")
@RequestMapping("/disclosure/information/manage/workmanage/ReportWorkManage001")
public class ReportWorkManage001Contoller extends BaseController {
    
    @Autowired
    private ReportWorkManage001Service reportWorkManage001Service;
    
    @Autowired
    private RepDetailsShow001Service repDetailsShow001Service;
    
    @Autowired
    private BaseCommonService baseCommonService;
    /**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        
        this.setSelectOptionToModel(model,DictCode.确认状态);
        model.addAttribute("confirmStatusYes", DictCodeList.确认状态_已确认.getValue());
        // 入账日期默认为系统平台日
        model.addAttribute("sysDate",DateUtil.format(baseCommonService.getSysDate(), "yyyy-MM-dd"));
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
    public Page<ReportWorkManage001Dto> findPage() throws Exception{
        Page<ReportWorkManage001Dto>  pageList = reportWorkManage001Service.findPage(RequestUtil.getPageParam());
        return pageList;
    }
    @RequestMapping("/getReportIdList")
    @ResponseBody
    public List<SelectOptionDto> getJobInfo() {
         return reportWorkManage001Service.getJobInfo();
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
    public AjaxResult updateConfirmStatusAndNoticeStatus(@RequestBody ReportWorkManage001Dto paDto,HttpServletRequest request) throws IOException  {

        
        // 获取勾选的数据
        String data=paDto.getData();
        List<ReportWorkManage001Dto> listdto = JsonUtils.json2List(data, ReportWorkManage001Dto.class);
        
        //默认返回值，发起成功
        String msg = getMessage("COMMON_I_0001");
        
        for(ReportWorkManage001Dto dto : listdto){
            
            
            ProcessParameter<ReportWorkManage001Dto> proParam = new ProcessParameter<>();
            WorkFlowParameter<ReportWorkManage001Dto> params = new WorkFlowParameter<>();
            
            
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
                msg = reportWorkManage001Service.start(proParam, dto);
            }
            catch (Exception e) {
                getLogger().error("启动流程失败：", e);
                return new AjaxResult(e.getMessage());
            }
        }
        
      // reportWorkManage001Service.updateConfirmStatus(listdto);
        
        return new AjaxResult(msg);
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
         Map<String, Object> paraMap = RequestUtil.getPageParam();
         List<ReportWorkManage001Dto> dtoList;
         if (datas == null) {
        	 dtoList = reportWorkManage001Service.queryPageList(paraMap);
         }
         else {
        	 dtoList = JsonUtils.json2List(datas, ReportWorkManage001Dto.class);
         }
    	
        File outFile = reportWorkManage001Service.makeFile("/com/joyintech/tams/disclosure/information/manage/workmanage/config/export_reportworkmanage001.xml",dtoList);
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
     *  删除
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "deleteWorkManage", method = RequestMethod.POST)
	public AjaxResult deleteWorkManage(@RequestBody ReportWorkManage001Dto dto) {
		String msg = StringUtils.EMPTY;

		int row = reportWorkManage001Service.deleteByPrimaryKey(dto.getJobId());
		if (row > 0) {
			msg = "删除成功";
			return new AjaxResult(msg);
		}
		msg = "删除失败";
		return new AjaxResult(msg, MessageType.错误消息);
	}
	
	/**
	 * 删除检验
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/check")
	public AjaxResult check(@RequestBody ReportWorkManage001Dto dto, BindingResult result) throws Exception {
		return new AjaxResult();
	}
    /**
     * 界面重新执行
     * @param param 输入条件
     * @return 
     */
    @ResponseBody
    @RequestMapping("runJobNow")
    public AjaxResult runJobNow(@RequestBody Map<String,Object> param){
    	String  exeStatus =reportWorkManage001Service.runNow(param);
    	if(StringUtil.equals("01", exeStatus)){
    		return new AjaxResult("执行成功");
    	}
    	else if(StringUtil.equals("02", exeStatus)){
    		AjaxResult wb = new AjaxResult();
    		wb.setMessage("任务执行中");
    		wb.setStatuCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    		wb.setMessageType(MessageType.错误消息);
    		return wb;
    	}
    	else{
    		AjaxResult wb = new AjaxResult();
    		wb.setMessage("执行失败");
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
//    		wb.setMessage("执行失败");
//    		wb.setStatuCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//    		wb.setMessageType(MessageType.错误消息);
//    		return wb;
//    	}
        
    }
	/**
	 * 删除检验
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/refreshCheck")
	public AjaxResult refreshCheck() throws Exception {
		AjaxResult aj = new AjaxResult();
		String times = CacheUtil.getSysParam(ReportGlobalConstant.REP_REFRESH_JOBRULE);
		aj.setData(times);
		return aj;
	}
    /**
     *  刷新作业规则具体操作
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "refreshJobRule", method = RequestMethod.POST)
	public AjaxResult refreshJobRule() {
		String msg = StringUtils.EMPTY;
		boolean flag = reportWorkManage001Service.execBatchRefresh();
		if (flag) {
			msg = "刷新成功";
			return new AjaxResult(msg);
		}
		msg = "刷新失败";
		return new AjaxResult(msg, MessageType.错误消息);
	}
}
