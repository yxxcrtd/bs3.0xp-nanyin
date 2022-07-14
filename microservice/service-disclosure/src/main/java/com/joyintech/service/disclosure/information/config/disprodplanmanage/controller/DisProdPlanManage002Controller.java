/*
 * 文件名：disProdPlanManage002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.disclosure.information.config.disprodplanmanage.controller;

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

import com.joyintech.framework.file.SafelyFile;
import com.joyintech.service.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage002Dto;
import com.joyintech.service.disclosure.information.config.disprodplanmanage.service.DisProdPlanManage002Service;
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
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
/**
 * 报表模板管理新建
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author longhao
 * @version 1.0 2018年11月9日
 * @see DisProdPlanManage002Controller
 * @since 1.0
 */
@RestController(value="serviceDisProdPlanManage002Controller")
@RequestMapping("/DisProdPlanManage002")
public class DisProdPlanManage002Controller extends BaseController {

	@Autowired
	DisProdPlanManage002Service disProdPlanManage002Service;
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(DisProdPlanManage002Controller.class);
	
	 @PostMapping("getDataSource")
	    public String getDataSource() {
	    	return disProdPlanManage002Service.getDataSource();
	    }
	 
	 @PostMapping("getCreateWay")
	    public String getCreateWay() {
	    	return disProdPlanManage002Service.getCreateWay();
	    }
	
	
	 @GetMapping("getDisProdPlanManage002Dto")
	    public DisProdPlanManage002Dto getDisProdPlanManage002Dto(String code) {
	    	return disProdPlanManage002Service.getDisProdPlanManage002Dto(code);
	    }
	
	
	
	
	
	/**
	 * 根据主键id查询模板参数
	 * @return
	 */
/*	 @GetMapping("/findParamPage")
	public List<DisProdPlanManage002Dto> findParamPage(String repId) {
		if (StringUtil.isNotEmpty(repId)) {
			return disProdPlanManage002Service.findParamPage(repId);
		}
		
		return new ArrayList<DisProdPlanManage002Dto>();
	}*/
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
//	  public AjaxResult checkSave(@RequestBody DisProdPlanManage002Dto dto){
//	      AjaxResult result = DisProdPlanManage002Service.check(dto);
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
//			DisProdPlanManage002Dto dto = JsonUtils.json2Object(datas, DisProdPlanManage002Dto.class);
//			List<DisProdPlanManage002Dto> addParamList = JsonUtils.json2List(addParam, DisProdPlanManage002Dto.class);
//			if(null!=addParamList&&addParamList.size()!=0){
//				//讲模板参数拼接成指定格式
//				String reportParams ="{";
//				for(DisProdPlanManage002Dto dto2:addParamList){
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
//						int j=DisProdPlanManage002Service.selectRepIdAndUri(dto);
//						if(j>0){
//							result = new AjaxResult("模板文件已存在",
//									MessageType.错误消息);
//						}
//						else{
//							DisProdPlanManage002Service.insert(dto);
//						}
//					}
//				}
//				if(this.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
//					if(StringUtil.isNotEmpty(dto.getFileName())) {
//						String fileName=dto.getFileName();
//						fileName=fileName.substring(0,fileName.lastIndexOf("."));
//						dto.setFileName(fileName);
//					}
//					 DisProdPlanManage002Service.update(dto);
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
	    public int selectRepIdAndUri(@RequestBody DisProdPlanManage002Dto dto) {
		 return disProdPlanManage002Service.selectRepIdAndUri(dto);
	    }
	 
	 @PostMapping("insert")
	    public void insert(@RequestBody DisProdPlanManage002Dto dto) {
		 disProdPlanManage002Service.insert(dto);
	    }
	 
	 @PostMapping("update")
	    public void update(@RequestBody DisProdPlanManage002Dto dto) {
		 disProdPlanManage002Service.update(dto);
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
		public AjaxResult uploadFile(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
			 AjaxResult res = new AjaxResult();
//			 if(request.getParameter("size")>1048576){}
			 if(StringUtil.isEmpty(this.getParam("folder"))){
				//进度条消除
				 disProdPlanManage002Service.removePercent(request);
				 String sourcePath=this.getParam("fileName");
				 File sourceFile=new SafelyFile(sourcePath);
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
				 disProdPlanManage002Service.removePercent(request);
				 String msg = "文件大小不能超过10M";
					res =  new AjaxResult(msg, MessageType.错误消息);
					return res;
			 }else{
			 try {
				 disProdPlanManage002Service.readFile(request);
				} catch (Exception e) {
					String msg = "上传失败：" + e.getMessage();
					res =  new AjaxResult(msg, MessageType.错误消息);
					return res;
				}}
			 //判断目标目镜是否存在
			 String uploadFilePath  = CacheUtil.getSysParam(ReportGlobalConstant.TEMPLATE_FILE_PATH); 
			 File filepath = new SafelyFile(uploadFilePath);///home/tamsuser/jasper
		     File filepath2=new SafelyFile(uploadFilePath+this.getParam("folder"));///NB/GLJY
			 if(!filepath.exists()){
				 filepath.mkdirs();
			 }
			 if(!filepath2.exists()){
				 filepath2.mkdirs();
			 }
			 if(filepath.exists()&&filepath.isDirectory()){
				 //获取已经下载的文件，并且转化为文件流
				 File uploadfile = new SafelyFile(this.getParam("fileName"));///home/tamsuser/upload/.txt
				 FileInputStream fis = null;
				 //要存放的文件名称，并且用输出流降读取的文件流内容输出

				 FileOutputStream fos = null;
				 BufferedInputStream bis = null;
				 BufferedOutputStream bos = null;
			  try {
				  fis = new FileInputStream(uploadfile);
				  File downFile = new SafelyFile(uploadFilePath+this.getParam("folder")+"/"+this.getParam("uploadFileName"));
				   fos= new FileOutputStream(downFile);
				   bis=new BufferedInputStream(fis);
				   bos=new BufferedOutputStream(fos);
				  int b;
				  while((b=bis.read())!=-1){
					  bos.write(b);
				  }
			  }catch (Exception e)
			  {
				  logger.error(e.getMessage(),e);
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
				if (fis != null){
					fis.close();
				}
			  }
		 }
				return res;
		}
		
}
