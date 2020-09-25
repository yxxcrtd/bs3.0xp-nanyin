/*
 * 文件名：ChannelInterface001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2019年8月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.disclosure.information.reveal.channelinterface.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftpDetail.dto.FtpDetailDto;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftpDetail.service.FtpDetailService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service.GetPrdInfoSelectService;
import com.joyintech.tams.disclosure.information.reveal.channelinterface.dto.ChannelInterfaceNew001Dto;
import com.joyintech.tams.disclosure.information.reveal.channelinterface.service.ChannelInterface001Service;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManage001Dto;

@Controller
@PageId("ChannelInterface001")
@RequestMapping("/disclosure/information/reveal/channelinterface/ChannelInterface001")
public class ChannelInterface001Controller extends BaseController{
	
	/**
	 *  渠道接口信息查询service
	 */
	@Autowired
	private ChannelInterface001Service channelInterface001Service;
	
	@Autowired
	private GetPrdInfoSelectService getPrdInfoSelectService;
	
	@Autowired
	private FtpDetailService ftpDetailService;

	/**
	 * 重写init方法，加载页面
	 */
	@Override
	public String init(Model model) {
		this.setSelectOptionToModel(model, DictCode.披露渠道);
		this.setSelectOptionToModel(model, DictCode.报表类别);
		this.setSelectOptionToModel(model, DictCode.披露发送状态);
		this.setSelectOptionToModel(model, DictCode.披露反馈状态);
		
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
	
	
	
	@RequestMapping("ChannelInterface001Search")
	@ResponseBody
	public Page<ChannelInterfaceNew001Dto> queryChannelInterfacePageList() throws Exception{
		return channelInterface001Service.queryAllInstitutionPageList(RequestUtil.getPageParam());
	}
	
	
	

	private Map<String, Object> getRequestMap() {
		Map<String, Object> paraMap=RequestUtil.getPageParam();
		return paraMap;
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
         List<ChannelInterfaceNew001Dto> dtoList;
         if (datas == null) {
        	 //dtoList = reportWorkMonitor001Service.queryPageList(paraMap);
        	 dtoList = this.queryChannelInterfacePageList().getRoot();
         }
         else {
        	 dtoList = JsonUtils.json2List(datas, ChannelInterfaceNew001Dto.class);
         }
        File outFile = channelInterface001Service.makeFile("/com/joyintech/tams/disclosure/information/reveal/channelinterface/conf/export_channelinterface001.xml",dtoList);
        return ExportUtil.export(							
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }
	
    @ResponseBody
    @RequestMapping(value ="ChannelInterface001Resend",method=RequestMethod.POST)
    public AjaxResult reSend(@RequestBody ChannelInterfaceNew001Dto clDto,HttpServletRequest reques) throws IOException {
    	
    	AjaxResult res = new AjaxResult();
    	//FTP发送类
    	FtpDetailDto ftp=new FtpDetailDto();
    	
    	// 获取勾选的数据存入
    	String data=clDto.getData();
    	List<ChannelInterfaceNew001Dto> listdto = JsonUtils.json2List(data, ChannelInterfaceNew001Dto.class);
    	
    	for(ChannelInterfaceNew001Dto listDto : listdto){
    		ftp.setProdId(listDto.getProdId());
    		ftp.setReportId(listDto.getReportId());
    		ftp.setReportCategory(listDto.getReportCategory());
    		ftp.setOption(DictCodeListExt2.披露方式_手动.getValue());
    		
    		if(StringUtil.isNotEmpty(listDto.getStatrDateStr())) {
    			ftp.setStatrDate(DateUtil.parse(listDto.getStatrDateStr()));
    		}
    		if(StringUtil.isNotEmpty(listDto.getEndDateStr())) {
    			ftp.setEndDate(DateUtil.parse(listDto.getEndDateStr()));
    		}
    		
    		ftp.setRevealId(listDto.getRevealId());
    		
    		Boolean flag=ftpDetailService.doReveal(ftp);
    		
    		if(flag==true){
    			res = new AjaxResult("发送成功!");
    		}else{
    			res = new AjaxResult("发送失败，请确认后重新操作!");
    		}
    	}
    	
    	return res;
    	
    }
    
/*    
		FTP发送类.setProdId(页面.产品编号);
		FTP发送类.setReportId(页面.报表编号);
		FTP发送类.setReportCategory(页面.报表类别); // 获取ftp路径用
		FTP发送类.setOption(DisclosureDictCodeList.披露方式_手动.getValue()); //手工操作
		if(StringUtil.isNotEmpty(页面.开始日)) {
			FTP发送类.setStatrDate(页面.开始日);
		}
		if(StringUtil.isNotEmpty(页面.结束日)) {
			FTP发送类.setEndDate(页面.结束日);
		}
		
		FTP发送类.setRevealId(页面.披露编号);
		FtpDetailService.doReveal(FTP发送类);
		return null;
	*/
	
    @ResponseBody
    @RequestMapping(value = "ChannelInterface001getReceiveInfo", method=RequestMethod.POST)
    public AjaxResult getReceiveInfo(@RequestBody ChannelInterfaceNew001Dto clDto,HttpServletRequest reques) throws IOException {
    	AjaxResult res = new AjaxResult();
    	
    	// 获取勾选的数据
    	String data=clDto.getData();
    	List<ChannelInterfaceNew001Dto> listdto = JsonUtils.json2List(data, ChannelInterfaceNew001Dto.class);
        
        for(ChannelInterfaceNew001Dto listDto : listdto){
        	
        	String ftpDetailId=listDto.getDetailId();
        	String revealId=listDto.getRevealId();
        	
        	 Boolean flag=ftpDetailService.getDetailMsgBack(ftpDetailId,revealId);
             
             if(flag==true){
             	res = new AjaxResult("已发送请求!");
             }else{
         			res = new AjaxResult("获取反馈信息失败!");
             }
           
        }
        
       
        return res;
    	
    }
	
	
}
