/*
 * 文件名：ValProductInfoQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.disclosure.information.manage.workmanage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.service.disclosure.information.manage.workmanage.dto.ReportWorkManage001Dto;
import com.joyintech.service.disclosure.information.manage.workmanage.service.ReportWorkManage001Service;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
/**
 * 
 * 金融产品交易查询
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValNetValueConfirm001Contoller
 * @since 1.0
 */
@RestController(value="serviceReportWorkManage001Contoller")
@RequestMapping("/ReportWorkManage001")
public class ReportWorkManage001Contoller extends BaseController {
    
    @Autowired
    private ReportWorkManage001Service reportWorkManage001Service;
    
//    @Autowired
//    private RepDetailsShow001Service repDetailsShow001Service;
//    
//    @Autowired
//    private BaseCommonService baseCommonService;
    /**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
//    @Override
//    public String init(Model model) {
//        
//        this.setSelectOptionToModel(model,DisclosureDictCode.确认状态);
//        model.addAttribute("confirmStatusYes", DisclosureDictCodeList.确认状态_已确认.getValue());
//        model.addAttribute("REPORTTPLID_LIST",JsonUtils.objectArray2Json(this.getJobInfo().toArray()));
//        // 入账日期默认为系统平台日
//        model.addAttribute("sysDate",DateUtil.format(baseCommonService.getSysDate(), "yyyy-MM-dd"));
//        return super.init(model);
//    }
    
    /**
     * 
     * 分页数据查询
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ReportWorkManage001Dto> findPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        Page<ReportWorkManage001Dto>  pageList = reportWorkManage001Service.findPage(page, length, param);
        return pageList;
    }
    @PostMapping("/getReportIdList")
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
//    @ResponseBody
//    @RequestMapping(value = "updateConfirmStatusAndNoticeStatus", method=RequestMethod.POST)
//    public AjaxResult updateConfirmStatusAndNoticeStatus(@RequestBody ReportWorkManage001Dto paDto,HttpServletRequest request) throws IOException  {
//
//        
//        // 获取勾选的数据
//        String data=paDto.getData();
//        List<ReportWorkManage001Dto> listdto = JsonUtils.json2List(data, ReportWorkManage001Dto.class);
//        
//        //默认返回值，发起成功
//        String msg = getMessage("COMMON_I_0001");
//        
//        for(ReportWorkManage001Dto dto : listdto){
//            
//            
//            ProcessParameter<ReportWorkManage001Dto> proParam = new ProcessParameter<>();
//            WorkFlowParameter<ReportWorkManage001Dto> params = new WorkFlowParameter<>();
//            
//            
//            // 业务编号
//	        params.setBusinessCode(IDGenerator.generate());
//	        // 业务类型
//	        params.setBusinessType(DisclosureDictCodeList.理财资管业务类型_报表确认管理.getValue());
//            // 页面id
//            params.setPageId(this.getPageId());
//            // 元素id
//            params.setElementId(paDto.getElementId());
//            
//            //设置流程业务说明, entity即传入的dto
//            // 确认操作
//             params.setComment(entity -> WorkFlowUtil.concatComment("报表确认管理",entity.getData(),entity.getCreateUser())
//                 );
//            // 塞入流程参数
//            proParam.setWorkFlowParameter(params);
//            
//            try {
//                msg = reportWorkManage001Service.start(proParam, dto);
//            }
//            catch (Exception e) {
//                getLogger().error("启动流程失败：", e);
//                return new AjaxResult(e.getMessage());
//            }
//        }
//        
//      // reportWorkManage001Service.updateConfirmStatus(listdto);
//        
//        return new AjaxResult(msg);
//    }
    

    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
//    @RequestMapping(ACTION_EXPORT)
//    public ResponseEntity<byte[]> export() throws Exception {
//    	 String datas = this.getParam("datas");
//         Map<String, Object> paraMap = RequestUtil.getPageParam();
//         List<ReportWorkManage001Dto> dtoList;
//         if (datas == null) {
//        	 dtoList = reportWorkManage001Service.queryPageList(paraMap);
//         }
//         else {
//        	 dtoList = JsonUtils.json2List(datas, ReportWorkManage001Dto.class);
//         }
//    	
//        File outFile = reportWorkManage001Service.makeFile("/com/joyintech/tams/disclosure/information/manage/workmanage/config/export_reportworkmanage001.xml",dtoList);
//        return ExportUtil.export(
//                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
//                        + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
//                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
//                outFile, true);
//    }
//    
    @PostMapping("/findAll")
    public List<ReportWorkManage001Dto> findAll(@RequestBody Map<String, Object> param){
    	 return reportWorkManage001Service.queryPageList(param);
    }
    /**
     *  删除
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     */
//	@ResponseBody
//	@RequestMapping(value = "deleteWorkManage", method = RequestMethod.POST)
//	public AjaxResult deleteWorkManage(@RequestBody ReportWorkManage001Dto dto) {
//		String msg = StringUtils.EMPTY;
//
//		int row = reportWorkManage001Service.deleteByPrimaryKey(dto.getJobId());
//		if (row > 0) {
//			msg = "删除成功";
//			return new AjaxResult(msg);
//		}
//		msg = "删除失败";
//		return new AjaxResult(msg, MessageType.错误消息);
//	}
    @GetMapping("deleteByPrimaryKey")
    public int deleteByPrimaryKey(String jobId){
    	
    	return reportWorkManage001Service.deleteByPrimaryKey(jobId);
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
    @PostMapping("runJobNow")
    public String runJobNow(@RequestBody Map<String,Object> param){
    	
    	return reportWorkManage001Service.runNow(param);
//    	String  exeStatus =reportWorkManage001Service.runNow(param);
//    	if(StringUtil.equals("01", exeStatus)){
//    		return new AjaxResult("执行成功");
//    	}
//    	else if(StringUtil.equals("02", exeStatus)){
//    		AjaxResult wb = new AjaxResult();
//    		wb.setMessage("任务执行中");
//    		wb.setStatuCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//    		wb.setMessageType(MessageType.错误消息);
//    		return wb;
//    	}
//    	else{
//    		AjaxResult wb = new AjaxResult();
//    		wb.setMessage("执行失败");
//    		wb.setStatuCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//    		wb.setMessageType(MessageType.错误消息);
//    		return wb;
//    	}
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
	@PostMapping("")
	public boolean execBatchRefresh() {
//		String msg = StringUtils.EMPTY;
//		boolean flag = reportWorkManage001Service.execBatchRefresh();
//		if (flag) {
//			msg = "刷新成功";
//			return new AjaxResult(msg);
//		}
//		msg = "刷新失败";
//		return new AjaxResult(msg, MessageType.错误消息);
		return reportWorkManage001Service.execBatchRefresh();
	}
}
