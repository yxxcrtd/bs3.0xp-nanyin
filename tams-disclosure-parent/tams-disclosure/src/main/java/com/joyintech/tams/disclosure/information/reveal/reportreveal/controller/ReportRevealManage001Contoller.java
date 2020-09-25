/*
 * 文件名：ValProductInfoQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.reveal.reportreveal.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.tams.disclosure.information.config.reportpage.controller.ReportPageManage002Controller;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManage001Dto;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManageParamDto;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.service.ReportConfirmManage001Service;
import com.joyintech.tams.disclosure.information.reveal.reportreveal.service.ReportRevealManage001Service;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftppath.service.FtpPathService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service.GetPrdInfoSelectService;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCode;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.common.common.service.BaseCommonService;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import com.joyintech.framework.util.CacheUtil;
/**
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年11月11日
 * @see ReportRevealManage001Contoller
 * @since 1.0
 */
@Controller
@PageId("ReportRevealManage001")
@RequestMapping("/disclosure/information/reveal/reportreveal/ReportRevealManage001")
public class ReportRevealManage001Contoller extends BaseController {
    
    @Autowired
    private ReportRevealManage001Service reportRevealManage001Service;
    
    @Autowired
    private ReportConfirmManage001Service reportConfirmManage001Service;
    
    @Autowired
    private BaseCommonService baseCommonService;
    
    @Autowired
    private FtpPathService ftpPathService;
    
    @Autowired
	private GetPrdInfoSelectService getPrdInfoSelectService;
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ReportRevealManage001Contoller.class);
    
    /**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        
        model.addAttribute("revealStatusYes", DictCodeListExt2.披露状态_已披露.getValue());
        model.addAttribute("FINPRODID_LIST",JsonUtils.objectArray2Json(this.getFinprodId().toArray()));
        //获取系统平台日
        model.addAttribute("sysDate",
        DateUtil.format(baseCommonService.getSysDate(), "yyyy-MM-dd"));
        //流程id
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);       
        if(StringUtil.isNotEmpty(workflowId)){
        	 model.addAttribute("workflowId",workflowId);
        }
        model.addAttribute("REPORT_STYLE",JsonUtils.objectArray2Json(ftpPathService.getReportCategory().toArray()));
        this.setSelectOptionToModel(model,DictCode.披露状态);
        this.setSelectOptionToModel(model,DictCode.披露渠道);
        // 首页跳转使用
        String confromPage = this.getParam("confromPage");
        if(StringUtil.equals("01", confromPage)){
        	model.addAttribute("confromPage", confromPage);
        	model.addAttribute("searchDate", this.getParam("searchDate"));
        	model.addAttribute("searchDateStart", this.getParam("searchDateStart"));
        	model.addAttribute("searchDateEnd", this.getParam("searchDateEnd"));
        	if(StringUtil.equals("待披露", this.getParam("firstCol"))) {
        		model.addAttribute("revealStatus", DictCodeListExt2.披露状态_未披露.getValue());
        		model.addAttribute("pageFrom","01");
        	}else {
        		model.addAttribute("revealStatus", DictCodeListExt2.披露状态_已披露.getValue());
        		model.addAttribute("pageFrom","02");
        	}
        	if(!StringUtil.equals("firstCol", this.getParam("repId"))) {
        		model.addAttribute("repType", this.getParam("repId"));
        	}
        }
        Map<String, Object> map = RequestUtil.getPageParam();
        if(StringUtil.isNotEmpty(map.get("remType"))) {
            model.addAttribute("finprodId", (String)map.get("finprodId"));
            model.addAttribute("remType", map.get("remType"));
            String mdate = (String)map.get("mdate");
            if(StringUtil.isNotEmpty(mdate)){
            	model.addAttribute("quaryDate", DateUtil.addDay(mdate,0)+" - "+DateUtil.addDay(mdate,0));
            }
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
    		list.add(reportRevealManage001Service.getBussinessEntityByWorkflowId(workflowId));
    		page.setRoot(list);

    	}else{
    		page = reportRevealManage001Service.findPage(RequestUtil.getPageParam());
    		page.getRoot().forEach(dto -> {
    			if(dto.getFilename() != null) {
    				dto.setSysFilename(dto.getFilename());
    				dto.setSysFilepath(dto.getFilepath());
    			}
    			List<String> revealChannelList = StringUtil.splitToList(",", dto.getRevealChannel());
    			List<String> revealChannelNameList = new ArrayList<String>();
    			for (String string : revealChannelList) {
    				revealChannelNameList.add(SysDataUtil.getNameFromDataDict(DictCode.披露渠道, string));
				}
    			dto.setRevealChannelName(StringUtil.joinString(revealChannelNameList, ","));
            });
    	}
    	return page;
    }
    
	// add by guozhongbao 报表披露管理 20181204 start
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
	// add by guozhongbao 报表披露管理 20181204 end

	
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
    @RequestMapping(value = "updateRevealStatusAndNoticeStatus", method=RequestMethod.POST)
    public AjaxResult updateRevealStatusAndNoticeStatus(@RequestBody ReportConfirmManage001Dto paDto,HttpServletRequest request) throws IOException  {

        //当前操作为确认还是下发【确认："QR",下发："XF"】
        String confirmOrNotice = paDto.getUpdateTypeFlag();
        
        // 获取勾选的数据
        String data=paDto.getData();
        List<ReportConfirmManage001Dto> listdto = JsonUtils.json2List(data, ReportConfirmManage001Dto.class);
        
        //默认返回值，发起成功
        String msg = getMessage("COMMON_I_0001");
        
        for(ReportConfirmManage001Dto dto : listdto){
        	
            //实体类中，带入当前操作状态
//            dto.setConfirmOrNotice(confirmOrNotice);
            
            ProcessParameter<ReportConfirmManage001Dto> proParam = new ProcessParameter<>();
            WorkFlowParameter<ReportConfirmManage001Dto> params = new WorkFlowParameter<>();
            
            //披露操作用户
            dto.setRevealUser(LoginUserUtil.getUserName());
            dto.setUpdateUser(dto.getRevealUser());
            // 业务编号
	        //params.setBusinessCode(IDGenerator.generate());
	        params.setBusinessCode(dto.getProdid() + "_" + dto.getReportCategoryName());
	        // 业务类型
	        params.setBusinessType(DictCodeListExt2.共通业务类型_报表确认管理.getValue());
            // 页面id
            params.setPageId(this.getPageId());
            // 元素id
            params.setElementId(paDto.getElementId());
            
            //设置流程业务说明, entity即传入的dto
            // 确认操作
            if("QR".equals(confirmOrNotice)){
             params.setComment(entity -> WorkFlowUtil.concatComment("报表披露管理(披露)",entity.getCreateUser())
                );
            }
            // 下发操作
            else{
//                params.setComment(entity -> WorkFlowUtil.concatComment("净值下发确认（下发）",entity.getFinprodAbbr(),entity.getValDate(),entity.getValBalFlag(),
//                    entity.getNoticeStatus()));
            }
             
            // 塞入流程参数
            proParam.setWorkFlowParameter(params);
            
            try {
                msg = reportRevealManage001Service.start(proParam, dto);
            }
            catch (Exception e) {
                getLogger().error("启动流程失败：", e);
                return new AjaxResult(e.getMessage());
            }
        }
        
      // reportRevealManage001Service.updateConfirmStatus(listdto);
        
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
    public AjaxResult check(@Valid ReportConfirmManageParamDto dto, BindingResult result, String operateType) throws Exception{
        return commonCheck(result, operateType);
//    	 return  new AjaxResult();
    }
    /**
     * 撤销披露
     * @param dto
     * @param result
     * @param operateType
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("revoke")
    public AjaxResult revoke(@Valid ReportConfirmManageParamDto dto, BindingResult result, String operateType) {
        try {
            @SuppressWarnings("unused")
			boolean result1 =  reportRevealManage001Service.updateRevealStatus(dto);
        }
        catch (IOException e) {
//            e.printStackTrace();
        	logger.error("error:",e);
            return new AjaxResult(e.getMessage());
        }
        return  new AjaxResult("撤销披露成功。");
    }

    @ResponseBody
    @RequestMapping("goBack")
    public AjaxResult goBack(@Valid ReportConfirmManageParamDto dto, BindingResult result, String operateType) {
        try {
            @SuppressWarnings("unused")
			boolean result1 =  reportRevealManage001Service.updateConfirmStatusWithGoBack(dto);
        }
        catch (IOException e) {
//            e.printStackTrace();
        	logger.error("error:",e);
            return new AjaxResult(e.getMessage());
        }
        return  new AjaxResult("退回成功。");
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
        	 dtoList = reportRevealManage001Service.queryPageList(paraMap);
        	 for (ReportConfirmManage001Dto single : dtoList) {
        		 List<String> revealChannelList = StringUtil.splitToList(",", single.getRevealChannel());
        		 List<String> revealChannelNameList = new ArrayList<String>();
     			 for (String string : revealChannelList) {
     				 revealChannelNameList.add(SysDataUtil.getNameFromDataDict(DictCode.披露渠道, string));
 				 }
     			 single.setRevealChannelName(StringUtil.joinString(revealChannelNameList, ","));
			 }
//        	 dtoList = this.findPage().getRoot();
        	 for (ReportConfirmManage001Dto dto : dtoList) {
				if(StringUtil.equals(DictCodeListExt2.披露状态_未披露.getValue(), dto.getRevealStatus())) {
					dto.setRevealDate(null);
				}
			 }
         }
         else {
        	 dtoList = JsonUtils.json2List(datas, ReportConfirmManage001Dto.class);
        	 for (ReportConfirmManage001Dto dto : dtoList) {
 				if(StringUtil.equals(DictCodeListExt2.披露状态_未披露.getValue(), dto.getRevealStatus())) {
 					dto.setRevealDate(null);
 				}
 			 }
         }
        File outFile = reportRevealManage001Service.makeFile("/com/joyintech/tams/disclosure/information/reveal/reportreveal/config/export_reportRevealManage001.xml",dtoList);
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
}
