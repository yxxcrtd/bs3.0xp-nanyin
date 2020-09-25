/*
 * 文件名：ReportModelManage002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.disclosure.information.config.reportmodelmanage.controller;

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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.disclosure.information.config.reportmodelmanage.dto.ReportModelManage002Dto;
import com.joyintech.service.disclosure.information.config.reportmodelmanage.service.ReportModelManage002Service;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
/**
 * 报表模板管理新建
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author longhao
 * @version 1.0 2018年11月9日
 * @see ReportModelManage002Controller
 * @since 1.0
 */
@RestController(value="serviceReportModelManage002Controller")
@RequestMapping("/ReportModelManage002")
public class ReportModelManage002Controller extends BaseController {

	@Autowired
	ReportModelManage002Service reportModelManage002Service;
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ReportModelManage002Controller.class);
	
	 @PostMapping("getDataSource")
	    public String getDataSource() {
	    	return reportModelManage002Service.getDataSource();
	    }
	 
	 @PostMapping("getCreateWay")
	    public String getCreateWay() {
	    	return reportModelManage002Service.getCreateWay();
	    }
	
	
	 @GetMapping("getReportModelManage002Dto")
	    public ReportModelManage002Dto getReportModelManage002Dto(String code) {
	    	return reportModelManage002Service.getReportModelManage002Dto(code);
	    }
	
	
	
	
	
	/**
	 * 根据主键id查询模板参数
	 * @return
	 */
	 @GetMapping("/findParamPage")
	public List<ReportModelManage002Dto> findParamPage(String repId) {
		if (StringUtil.isNotEmpty(repId)) {
			return reportModelManage002Service.findParamPage(repId);
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
//	  @ResponseBody
//	  @RequestMapping("/checkSave")
//	  public AjaxResult checkSave(@RequestBody ReportModelManage002Dto dto){
//	      AjaxResult result = reportModelManage002Service.check(dto);
//	      if(result.getErrorList().isEmpty()){
//	          result.setData(this.getMessage("REPORT_COMMON_C_0001", this.getMessage("SysCommon.save")));
//	      }
//	      return  result;
//	  }
	  
	  /**
		 * 保存 <一句话功能简述> <功能详细描述>
		 * 
		 * @return
		 * @throws IOException
		 */
//		@RequestMapping("insert")
//		@ResponseBody
//		public AjaxResult insert() throws IOException {
//			AjaxResult result = new AjaxResult();
//			List<ValidMessage> checkList = new ArrayList<ValidMessage>();
//			String datas = this.getParam("object");
//			String addParam = this.getParam("addParam");
//			ReportModelManage002Dto dto = JsonUtils.json2Object(datas, ReportModelManage002Dto.class);
//			List<ReportModelManage002Dto> addParamList = JsonUtils.json2List(addParam, ReportModelManage002Dto.class);
//			if(null!=addParamList&&addParamList.size()!=0){
//				//讲模板参数拼接成指定格式
//				String reportParams ="{";
//				for(ReportModelManage002Dto dto2:addParamList){
//					reportParams=reportParams+dto2.getParamName()+":'"+dto2.getParamType()+"',";		
//				}
//				reportParams=reportParams.substring(0, reportParams.length()-1)+"}";
//				dto.setReportParams(reportParams);
//			}
//			try {
//				if (this.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
//					if(StringUtil.isEmpty(dto.getFileName())) {
//						checkList.add(new ValidMessage("上传文件不能为空"));
//					}
//					else{
//						String fileName=dto.getFileName();
//						fileName=fileName.substring(0,fileName.lastIndexOf("."));
//						dto.setFileName(fileName);
//						//查询是否存在相同模板
//						int j=reportModelManage002Service.selectRepIdAndUri(dto);
//						if(j>0){
//							result = new AjaxResult("模板文件已存在",
//									MessageType.错误消息);
//						}
//						else{
//							reportModelManage002Service.insert(dto);
//						}
//					}
//				}
//				if(this.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
//					if(StringUtil.isNotEmpty(dto.getFileName())) {
//						String fileName=dto.getFileName();
//						fileName=fileName.substring(0,fileName.lastIndexOf("."));
//						dto.setFileName(fileName);
//					}
//					 reportModelManage002Service.update(dto);
//				}
//			}catch (Exception e) {
//				logger.error("error:",e);
//				result = new AjaxResult("保存失败", MessageType.错误消息);
//				return result;
//			}
//			if(null!=checkList&&checkList.size()!=0) {
//				result.setErrorList(checkList);
//	        }
//			else{
//				result= new AjaxResult("保存成功", MessageType.提示消息);
//			}
//			return result;
//		}
	 
	 
	 
	 
	 @PostMapping("selectRepIdAndUri")
	    public int selectRepIdAndUri(@RequestBody ReportModelManage002Dto dto) throws Exception {
		 return reportModelManage002Service.selectRepIdAndUri(dto);
	    }
	 
	 @PostMapping("insert")
	    public void insert(@RequestBody ReportModelManage002Dto dto) throws Exception {
		 reportModelManage002Service.insert(dto);
	    }
	 
	 @PostMapping("update")
	    public void update(@RequestBody ReportModelManage002Dto dto) throws Exception {
		 reportModelManage002Service.update(dto);
	    }
		
		/**
		 * 文件上传
		 *
		 * @param
		 * @param
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
				 String msg = "请输入上传文件夹";
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
			 if(filepath.exists()&&filepath.isDirectory()){
				 //获取已经下载的文件，并且转化为文件流
				 File uploadfile = new File(this.getParam("fileName"));///home/tamsuser/upload/.txt
				 FileInputStream fis = new FileInputStream(uploadfile);
				 //要存放的文件名称，并且用输出流降读取的文件流内容输出

				 FileOutputStream fos = null;
				 BufferedInputStream bis = null;
				 BufferedOutputStream bos = null;
			  try {
				  File downFile = new File(uploadFilePath+this.getParam("folder")+"/"+this.getParam("uploadFileName"));
				   fos= new FileOutputStream(downFile);
				   bis=new BufferedInputStream(fis);
				   bos=new BufferedOutputStream(fos);
				  int b;
				  while((b=bis.read())!=-1){
					  bos.write(b);
				  }
			  }catch (Exception e)
			  {
			  	e.printStackTrace();
			  }
			  finally {
			  	if(fos!=null)
				{
					fos.close();
				}

				if(bis!=null)
				{
					bis.close();
				}

				if(bos!=null)
				{
					bos.close();
				}
			  }

				 
		 }
//			 
			
			
			 
				return res;
		}
		
}
