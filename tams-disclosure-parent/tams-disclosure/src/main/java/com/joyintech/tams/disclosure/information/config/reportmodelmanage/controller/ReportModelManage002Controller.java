/*
 * 文件名：ReportModelManage002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.reportmodelmanage.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.page.PageParams;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCode;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.information.config.reportmodelmanage.dto.ReportModelManage002Dto;
import com.joyintech.tams.disclosure.information.config.reportmodelmanage.service.ReportModelManage002Service;
import com.joyintech.tams.disclosure.interf.framework.util.FtpUtil;
import com.joyintech.tams.disclosure.interf.interf.common.util.FileUtil;
/**
 * 报表模板管理新建
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author longhao
 * @version 1.0 2018年11月9日
 * @see ReportModelManage002Controller
 * @since 1.0
 */
@Controller
@PageId("ReportModelManage002")
@RequestMapping("/disclosure/information/config/reportmodelmanage/ReportModelManage002")
public class ReportModelManage002Controller extends BaseController {

	@Autowired
	ReportModelManage002Service reportModelManage002Service;
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ReportModelManage002Controller.class);
	@Override
	public String init(Model model) {
		model.addAttribute("dataSource", reportModelManage002Service.getDataSource());
		model.addAttribute("createWay", reportModelManage002Service.getCreateWay());
		model.addAttribute("reportWarehouse", CacheUtil.getSysParam( ReportGlobalConstant.TEMPLATE_FILE_PATH));
		model.addAttribute("userName", "");
		model.addAttribute("passWord", "");
		this.setSelectOptionToModel(model,DictCode.报表数据格式类型);
		
		String id = this.getParam("keyId");
		if (StringUtil.isNotEmpty(id)) {
			model.addAttribute("entity", reportModelManage002Service.getReportModelManage002Dto(id));
		}
		return super.init(model);
	}
	/**
	 * 根据主键id查询模板参数
	 * @return
	 */
	@RequestMapping("/findParamPage")
	@ResponseBody
	public List<ReportModelManage002Dto> findParamPage() {
		if (StringUtil.isNotEmpty(this.getParam("repId"))) {
			return reportModelManage002Service.findParamPage(this.getParam("repId"));
		}
		
		return new ArrayList<ReportModelManage002Dto>();
	}
	/**
	    * 
	    * check
	    * @param dto 待校验数据
	    * @return 结果
	    * @see
	    * @since 1.0
	    */
	  @ResponseBody
	  @RequestMapping("/checkSave")
	  public AjaxResult checkSave(@RequestBody ReportModelManage002Dto dto){
	      AjaxResult result = reportModelManage002Service.check(dto);
	      if(result.getErrorList().isEmpty()){
	          result.setData(this.getMessage("DIS_COMMON_C_0001", this.getMessage("SysCommon.save")));
	      }
	      return  result;
	  }
	  
	  /**
		 * 保存 <一句话功能简述> <功能详细描述>
		 * 
		 * @return
		 * @throws IOException
		 */
		@RequestMapping("insert")
		@ResponseBody
		public AjaxResult insert() throws IOException {
			AjaxResult result = new AjaxResult();
			List<ValidMessage> checkList = new ArrayList<ValidMessage>();
			String datas = this.getParam("object");
			String addParam = this.getParam("addParam");
			ReportModelManage002Dto dto = JsonUtils.json2Object(datas, ReportModelManage002Dto.class);
			List<ReportModelManage002Dto> addParamList = JsonUtils.json2List(addParam, ReportModelManage002Dto.class);
			if(null!=addParamList&&addParamList.size()!=0){
				//讲模板参数拼接成指定格式
				String reportParams ="{";
				for(ReportModelManage002Dto dto2:addParamList){
					reportParams=reportParams+dto2.getParamName()+":'"+dto2.getParamType()+"',";		
				}
				reportParams=reportParams.substring(0, reportParams.length()-1)+"}";
				dto.setReportParams(reportParams);
			}
			try {
				if (this.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
					if(StringUtil.isEmpty(dto.getFileName())) {
						checkList.add(new ValidMessage("上传文件不能为空"));
					}
					else{
						String fileName=dto.getFileName();
						fileName=fileName.substring(0,fileName.lastIndexOf("."));
						dto.setFileName(fileName);
						//查询是否存在相同模板
						int j=reportModelManage002Service.selectRepIdAndUri(dto);
						if(j>0){
							result = new AjaxResult("模板文件已存在",
									MessageType.错误消息);
						}
						else{
							reportModelManage002Service.insert(dto);
						}
					}
				}
				if(this.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
					if(StringUtil.isNotEmpty(dto.getFileName())) {
						String fileName=dto.getFileName();
						fileName=fileName.substring(0,fileName.lastIndexOf("."));
						dto.setFileName(fileName);
					}
					 reportModelManage002Service.update(dto);
				}
			}catch (Exception e) {
				logger.error("error:",e);
				result = new AjaxResult("保存失败", MessageType.错误消息);
				return result;
			}
			if(null!=checkList&&checkList.size()!=0) {
				result.setErrorList(checkList);
	        }
			else{
				result= new AjaxResult("保存成功", MessageType.提示消息);
			}
			return result;
		}
		
		/**
		 * 文件上传
		 *
		 * @param request请求
		 * @param response响应
		 * @param model ModelAndView
		 * @return 保存文件信息
		 * @throws Exception
		 *             异常
		 * @see
		 * @since 1.0
		 */
		@RequestMapping(value = "/uploadFile")
		@ResponseBody
		public AjaxResult uploadFile(HttpServletRequest request, HttpServletResponse response, ModelMap model)
				throws Exception {
			 AjaxResult res = new AjaxResult();
//			 if(request.getParameter("size")>1048576){}
			 if(StringUtil.isEmpty(this.getParam("folder"))){
				//进度条消除
				 reportModelManage002Service.removePercent(request);
				 String sourcePath=this.getParam("fileName");
				 File sourceFile=new File(sourcePath);
				 if(sourceFile.exists()){
					 sourceFile.delete();
				 }
				 String msg = "请输入上传文件夹,并重新上传文件";
					res =  new AjaxResult(msg, MessageType.错误消息);
					return res;
			 }
			 String size = this.getParam("size");
			 //文件大小判断 10M
			 if(Integer.parseInt(size)>10485760){
				 //进度条消除
				 reportModelManage002Service.removePercent(request);
				 String msg = "文件大小不能超过10M";
					res =  new AjaxResult(msg, MessageType.错误消息);
					return res;
			 }else{
			 try {
				 reportModelManage002Service.readFile(request);
				} catch (Exception e) {
					String msg = "上传失败：" + e.getMessage();
					res =  new AjaxResult(msg, MessageType.错误消息);
					return res;
				}}
			 //判断目标目镜是否存在
			 String uploadFilePath  = CacheUtil.getSysParam(ReportGlobalConstant.TEMPLATE_FILE_PATH); 
			 File filepath = new File(uploadFilePath);///home/tamsuser/jasper
		     File filepath2=new File(uploadFilePath+this.getParam("folder"));///NB/GLJY
			 if(!filepath.exists()){
				 filepath.mkdirs();
			 }
			 if(!filepath2.exists()){
				filepath2.mkdirs();

			 }
			 else{
				 String TEMPLATE_DELETE_TYPE  = CacheUtil.getSysParam(ReportGlobalConstant.TEMPLATE_DELETE_TYPE); 
				 if(null!=TEMPLATE_DELETE_TYPE&&TEMPLATE_DELETE_TYPE.equals(DisclosureDictCodeList.是否_是.getValue())){
					 FileUtil.deleteDir(filepath2);
					 filepath2.mkdirs();
				 }
				 
			 }
			 
			 
			 if(filepath.exists()&&filepath.isDirectory()){
				 
			//调用解压工具,并解压至指定目录
			 boolean deczip =  FileUtil.decompressZip(new File(this.getParam("fileName")), filepath2.getPath());
			 
			 if(!deczip){
				 String msg = "文件上传并解压失败,请检查文件是否完好!";
				res =  new AjaxResult(msg, MessageType.错误消息);
				return res;
			 }else{
			 	String msg = "文件上传成功";
				res =  new AjaxResult(msg, MessageType.提示消息);
				return res; 
			 }
				 
		 }
//			 
			
			
			 
				return res;
		}
		
		
		
		/**
		 * 下载所有报表文件
		 * <一句话功能简述>
		 * <功能详细描述>
		 * @param dto
		 * @return
		 * @throws Exception
		 */
		@ResponseBody
		@RequestMapping("downloadReport")
		public ResponseEntity<byte[]> downloadReport() throws Exception {
			
			
			return reportModelManage002Service.downLoadReportInfo(this.getParam("folder"));
		}
		
}
