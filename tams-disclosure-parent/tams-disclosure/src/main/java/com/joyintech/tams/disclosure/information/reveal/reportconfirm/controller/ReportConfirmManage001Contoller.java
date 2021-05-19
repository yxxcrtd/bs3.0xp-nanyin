/*
 * 文件名：ValProductInfoQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.reveal.reportconfirm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftppath.service.FtpPathService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service.GetPrdInfoSelectService;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCode;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.common.common.service.BaseCommonService;
import com.joyintech.tams.disclosure.information.manage.workmanage.dto.ReportWorkManage001Dto;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManage001Dto;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManageParamDto;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.service.ReportConfirmManage001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import com.joyintech.framework.util.CacheUtil;


/**
 * 
 * 金融产品交易查询
 * @author weipengconfirmCancle
 * @version 1.0 2018年5月16日
 * @see ValNetValueConfirm001Contoller
 * @since 1.0
 */
@Controller
@PageId("ReportConfirmManage001")
@RequestMapping("/disclosure/information/reveal/reportconfirm/ReportConfirmManage001")
public class ReportConfirmManage001Contoller extends BaseController {
    
    /**
     * ValProductInfoQuery001Service
     */
    @Autowired
    private ReportConfirmManage001Service reportConfirmManage001Service;
    
    @Autowired
    private BaseCommonService baseCommonService;
    
    @Autowired
    private FtpPathService ftpPathService;
    
    @Autowired
	private GetPrdInfoSelectService getPrdInfoSelectService;
    /**
     * 附件处理接口
     */
    @Autowired(required = false)
    private FileAttachmentProcess fileAttachmentProcess;
    /**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	this.setSelectOptionToModel(model,DictCode.披露渠道);
        this.setSelectOptionToModel(model,DictCode.确认状态,DictCode.确认类型);
        model.addAttribute("confirmStatusYes", DictCodeList.确认状态_已确认.getValue());
        // 入账日期默认为系统平台日
        model.addAttribute("sysDate",DateUtil.format(baseCommonService.getSysDate(), "yyyy-MM-dd"));
        
        // 工作流ID
         String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);       
         if(StringUtil.isNotEmpty(workflowId)){
         	 model.addAttribute("workflowId",workflowId);
         }
         model.addAttribute("reportCategoryList", JsonUtils.objectArray2Json(ftpPathService.getReportCategory().toArray()));
         
        // 首页跳转使用
        String confromPage = this.getParam("confromPage");
        if(StringUtil.equals("01", confromPage)){
        	model.addAttribute("confromPage", confromPage);
//        	model.addAttribute("searchDate", this.getParam("searchDate"));
        	model.addAttribute("confirmStatus", DictCodeList.确认状态_未确认.getValue());
        	if(!StringUtil.equals("firstCol", this.getParam("repId"))) {
        		model.addAttribute("repType", this.getParam("repId"));
        	}
        	model.addAttribute("searchDateStart", this.getParam("searchDateStart"));
        	model.addAttribute("searchDateEnd", this.getParam("searchDateEnd"));
        }
         return super.init(model);
        
    }
    
    
    /**
	 * 分页查询产品下拉框
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getPrdInfoSelect")
	@ResponseBody
    public Page<SelectOptionDto> getPrdInfoSelect()
        throws Exception {
		Page<SelectOptionDto> prdInfoSelect = getPrdInfoSelectService.getPrdInfoSelect(RequestUtil.getPageParam());
        return prdInfoSelect;
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
    public Page<ReportConfirmManage001Dto> findPage() throws Exception{
    	String workflowId =this.getParam("workflowId");
    	
    	Page<ReportConfirmManage001Dto> page;
    	
    	if(StringUtil.isNotEmpty(workflowId)){
    		page = new Page<>();
    		List<ReportConfirmManage001Dto> list = new ArrayList<>();
    		ReportConfirmManage001Dto wflList = reportConfirmManage001Service.getBussinessEntityByWorkflowId(workflowId);
    		// 业务主键为空，则从data的json字符串（选中的row）中取  zhangyf 20190111 start
    		if(!StringUtil.isNotEmpty(wflList.getId())){
    	        List<ReportConfirmManage001Dto> dtoList = JsonUtils.json2List(wflList.getData(), ReportConfirmManage001Dto.class);
    	        if(!StringUtil.isEmptyList(dtoList) && dtoList.get(0) != null  && StringUtil.isNotEmpty(dtoList.get(0).getSeqno())){
    	            wflList.setId(dtoList.get(0).getSeqno());
    	        }
    		}
    		// 业务主键为空，则从data的json字符串（选中的row）中取  zhangyf 20190111 end
    		ReportConfirmManage001Dto newData = reportConfirmManage001Service.findNewData(wflList);
    		list.add(newData);
    		page.setRoot(list);
    	}else{
    		page = reportConfirmManage001Service.findPage(RequestUtil.getPageParam());
    	}
    	page.getRoot().forEach(dto -> {
    		if(StringUtil.isNotEmpty(dto) && StringUtil.isNotEmpty(dto.getRevealChannel())) {
				List<String> revealChannelList = StringUtil.splitToList(",", dto.getRevealChannel());
				List<String> revealChannelNameList = new ArrayList<String>();
				for (String string : revealChannelList) {
					revealChannelNameList.add(SysDataUtil.getNameFromDataDict(DictCode.披露渠道, string));
				}
				dto.setRevealChannelName(StringUtil.joinString(revealChannelNameList, ","));
    		}
		});
    	return page;
    }
    
    /**
     *报表确认管理【确认走流程】
     *
     * @return 返回的数据
     * @throws IOException 
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "updateConfirmStatusAndNoticeStatus", method=RequestMethod.POST)
    public AjaxResult updateConfirmStatusAndNoticeStatus(@RequestBody ReportConfirmManage001Dto paDto,HttpServletRequest request) throws IOException  {

        //当前操作为确认还是下发【确认："QR",下发："XF"】
        String confirmOrNotice = paDto.getUpdateTypeFlag();
        
        // 获取勾选的数据
        String data=paDto.getData();
        List<ReportConfirmManage001Dto> listdto = JsonUtils.json2List(data, ReportConfirmManage001Dto.class);
        
        //默认返回值，发起成功
        String msg = getMessage("COMMON_I_0001");
        
        for(ReportConfirmManage001Dto dto : listdto) {
        	if((StringUtil.isEmpty(dto.getSysFilepath()) || dto.getSysFilepath() == "")
        			&& (StringUtil.isEmpty(dto.getFilepath()) || dto.getFilepath() == "")) {
        		AjaxResult result = new AjaxResult();
        		result.setMessage("该报表文件路径不存在: " + dto.getReportName(), MessageType.错误消息);
        		return result;
        	}
          
            ProcessParameter<ReportConfirmManage001Dto> proParam = new ProcessParameter<>();
            WorkFlowParameter<ReportConfirmManage001Dto> params = new WorkFlowParameter<>();
            
            //确认操作用户
            dto.setConfirmUser(LoginUserUtil.getUserName());
            dto.setUpdateUser(dto.getConfirmUser());
            // 业务编号
//	        params.setBusinessCode(IDGenerator.generate());
            params.setBusinessCode(dto.getProdid() + "_" + dto.getReportCategoryName());
	        // 业务类型
	        params.setBusinessType(DictCodeListExt2.共通业务类型_报表确认管理.getValue());
            // 页面id
            params.setPageId(this.getPageId());
            // 元素id
            params.setElementId(paDto.getElementId());
            
            //设置流程业务说明, entity即传入的dto
            // 确认操作
			if ("QR".equals(confirmOrNotice)) {
				params.setComment(entity -> WorkFlowUtil.concatComment("报表确认管理(确认)",
						entity.getReportDate()==null?"":"报告期(" +entity.getReportDate() + ")",
						LoginUserUtil.getUserName()));
			}                
            // 塞入流程参数
            proParam.setWorkFlowParameter(params);
            
            dto.setConfirmDate(new Date());
            
            try {
                msg = reportConfirmManage001Service.start(proParam, dto);
            }
            catch (Exception e) {
                getLogger().error("启动流程失败：", e);
                return new AjaxResult(e.getMessage());
            }
        }
        
      // reportConfirmManage001Service.updateConfirmStatus(listdto);
        
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
    public AjaxResult check(@Valid ReportConfirmManageParamDto dto, BindingResult result, String operateType) {
        return commonCheck(result, operateType);
//    	 return  new AjaxResult();
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
         List<ReportConfirmManage001Dto> dtoList;
         if (datas == null) {
        	 dtoList = reportConfirmManage001Service.queryPageList(paraMap);
        	 for (ReportConfirmManage001Dto dto : dtoList) {
        		List<String> revealChannelList = StringUtil.splitToList(",", dto.getRevealChannel());
      			List<String> revealChannelNameList = new ArrayList<String>();
      			for (String string : revealChannelList) {
     				revealChannelNameList.add(SysDataUtil.getNameFromDataDict(DictCode.披露渠道, string));
 				}
     			dto.setRevealChannelName(StringUtil.joinString(revealChannelNameList, ","));
			}
         }
         else {
        	 dtoList = JsonUtils.json2List(datas, ReportConfirmManage001Dto.class);
         }
    	
        File outFile = reportConfirmManage001Service.makeFile("/com/joyintech/tams/disclosure/information/reveal/reportconfirm/conf/export_valnetvalue.xml",dtoList);
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
	 * 删除检验
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("deleteCheck")
	public AjaxResult deletecheck(@RequestBody ReportWorkManage001Dto dto, BindingResult result) throws Exception {
		
		return new AjaxResult();
	}
	
    /**
     *  删除
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "deleteConfirm", method = RequestMethod.POST)
	public AjaxResult deleteConfirm(@RequestBody ReportConfirmManage001Dto dto) {
		String msg = StringUtils.EMPTY;

		int row = reportConfirmManage001Service.deleteComfirmByPrimaryKey(dto);
		if (row > 0) {
			msg = "删除成功";
			return new AjaxResult(msg);
		}
		msg = "删除失败";
		return new AjaxResult(msg, MessageType.错误消息);
	}
	
	/**
	 * 下载报表
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("downloadReport")
	public ResponseEntity<byte[]> downloadReport() throws Exception {
		String id = RequestUtil.getParam("id");
		List<String> idList = new ArrayList<String>();
		if(StringUtil.isNotEmpty(id)){
			idList = JsonUtils.json2List(id, String.class);
		}
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("idList", idList);
		return reportConfirmManage001Service.downLoadReportInfo(paraMap);
	}
	
	/**
	 * 取消确认
	 * @param dto
	 * @return
	 * @throws IOException 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("confirmCancle")
	public AjaxResult confirmCancle(@RequestBody ReportConfirmManage001Dto dto) throws IOException{
		AjaxResult res = new AjaxResult();
//		String id = RequestUtil.getParam("id");
		String id =dto.getId();
		List<String> idList = new ArrayList<String>();
		if(StringUtil.isNotEmpty(id)){
			idList = JsonUtils.json2List(id, String.class);
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("idList", idList);
			paraMap.put("updateUser", LoginUserUtil.getUserName());
			paraMap.put("confimStatus", DictCodeListExt1.披露确认状态_未确认.getValue());
			reportConfirmManage001Service.confirmCancle(paraMap);
			res = new AjaxResult("已取消确认");
		}
		return res;
	}
	
	/**
	 * 删除文件
	 * 
	 * @return
	 */
	@ResponseBody
    @RequestMapping("deleteFile")
    public AjaxResult deleteFile(@RequestBody Map<String,Object> param) {
		  String option = (String) param.get("option");
	        Map<String, Object> params = new HashMap<String, Object>();
	        params.put("updateUser", LoginUserUtil.getUserName());
	        params.put("id", (String) param.get("id"));
	        if(StringUtil.equals("1", option)) {
	            // 删除手工上传的文件
	            params.put("option", "1");
	        }
	        // 清空REP_REVEAL表中对应的字段
	        int flag = reportConfirmManage001Service.deleteFile(params);
	        AjaxResult result = new AjaxResult();
	        if(flag == 1) {
	            result.setMessage("删除文件成功", MessageType.提示消息);
	            getLogger().info("删除文件成功");
	        }else {
	            getLogger().info("删除文件失败");
	            result.setMessage("删除文件失败", MessageType.提示消息);
	        }
	        return result;
//    	AjaxResult result = new AjaxResult();
//    	result = reportConfirmManage001Service.deleteFile(param);
//    	return result;
    }
	
	 /**
     * 模板文件下载
     * 
     * @return 将系统所有模板文件进行打包下载
     * @throws Exception 异常
     */
	@ResponseBody
    @RequestMapping("downLoad")
    public ResponseEntity<byte[]> download()
        throws Exception {
    	String sysFilepath = RequestUtil.getParam("sysFilepath");
        if(null == fileAttachmentProcess) {
            throw new BusinessException("FileAttachmentProcess接口未实现，系统无法获取附件 " + sysFilepath);
        }
        // 获取附件
        UploadFile attachment = fileAttachmentProcess.fetch(sysFilepath);
        String targetFileName=new String();
        String formate=reportConfirmManage001Service.getFormate(sysFilepath);
        formate=formate.substring(formate.lastIndexOf(".") );
        // 文件名
//    	targetFileName=reportConfirmManage001Service.getFileName(sysFilepath)+"_"+DateUtil.format(new Date(), DateUtil.DEFAULT_OTHER_FORMAT)+formate;
    	
    	String prodId = RequestUtil.getParam("prodId");
        String reportDate = RequestUtil.getParam("reportDate");
        String reportName = RequestUtil.getParam("reportName");

		// modify by swwan 20210518 start
		targetFileName = RequestUtil.getParam("sysFileName");
    	/*targetFileName=reportName+formate;
    	if (StringUtil.isNotEmpty(reportDate)&&!StringUtil.equals(reportDate, "undefined")){
    	    if(reportDate.lastIndexOf("--")!=-1){
    	        targetFileName=reportDate.substring(reportDate.lastIndexOf("--")+2)+GlobalConstant.UNDER_LINE+targetFileName;
    	    }
    	    else{
    	        targetFileName=reportDate+GlobalConstant.UNDER_LINE+targetFileName;
    	    }
        }
    	if (StringUtil.isNotEmpty(prodId)&&!StringUtil.equals(prodId, "undefined")) {
    	    targetFileName=prodId+GlobalConstant.UNDER_LINE+targetFileName;
        }
        */
		// modify by swwan 20210518 end
    	if(StringUtil.isEmpty(targetFileName)){
        	targetFileName=attachment.getFileName();
        }
        // 目标文件
        File targetFile = attachment.getTargetFile();
        
        InputStream in = null;
        if (!targetFile.exists()) {
            throw new BusinessException("文件[" +targetFile.getPath() + "]不存在！");
        }
        else {
            in = new FileInputStream(targetFile);
        }
        return ExportUtil.export(targetFileName, in);
    }
}
