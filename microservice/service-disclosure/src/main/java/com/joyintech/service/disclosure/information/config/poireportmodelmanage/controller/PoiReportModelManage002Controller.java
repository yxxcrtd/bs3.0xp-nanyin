/*
 * 文件名：ReportModelManage002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.disclosure.information.config.poireportmodelmanage.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.disclosure.information.config.poireportmodelmanage.dto.PoiReportModelManage002Dto;
import com.joyintech.service.disclosure.information.config.poireportmodelmanage.service.PoiReportModelManage002Service;
import com.joyintech.service.disclosure.information.config.reportmodelmanage.dto.ReportModelManage002Dto;
import com.joyintech.service.disclosure.information.config.reportmodelmanage.service.ReportModelManage002Service;
import com.joyintech.tams.common.base.entity.DisPoiTab;
import com.joyintech.tams.common.base.entity.DisPraamsSub;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
/**
 * 报表模板管理新建
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author longhao
 * @version 1.0 2018年11月9日
 * @see PoiReportModelManage002Controller
 * @since 1.0
 */
@RestController(value="servicePoiReportModelManage002Controller")
@RequestMapping("/PoiReportModelManage002")
public class PoiReportModelManage002Controller extends BaseController {

	@Autowired
	ReportModelManage002Service reportModelManage002Service;
	
	@Autowired
	PoiReportModelManage002Service poireportModelManage002Service;
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(PoiReportModelManage002Controller.class);
	
	 @PostMapping("getDataSource")
	    public String getDataSource() {
	    	return reportModelManage002Service.getDataSource();
	    }
	 
	 @PostMapping("getCreateWay")
	    public String getCreateWay() {
	    	return reportModelManage002Service.getCreateWay();
	    }
	
	
	 @GetMapping("getgetDisPoiTabDto")
	    public PoiReportModelManage002Dto getgetDisPoiTabDto(String code) {
	    	return poireportModelManage002Service.getDisPoiTabDto(code);
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

	 @PostMapping("selectRepIdAndUri")
	    public int selectRepIdAndUri(@RequestBody ReportModelManage002Dto dto) throws Exception {
		 return reportModelManage002Service.selectRepIdAndUri(dto);
	    }
	 
	 @PostMapping("insertDisPoiTab")
	    public void insert(@RequestBody DisPoiTab dto) throws Exception {
		 poireportModelManage002Service.insertDisPoiTab(dto);
	    }
	 
	 @PostMapping("insertDisPraamsSub")
	 public void insertDisPraamsSub(@RequestBody List<DisPraamsSub> list) throws Exception {
		 poireportModelManage002Service.insertDisPraamsSub(list);
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
			  	fos.close();
			  	bis.close();
			  	bos.close();
			  }

				 
		 }
//			 
			
			
			 
				return res;
		}
		
		/**
		 * 获取下拉框值
		 * @return
		 */
		 @GetMapping("/getInfoSelect")
		public List<SelectOptionDto> getInfoSelect() {
				
			 List<SelectOptionDto> page1	=	poireportModelManage002Service.getInfoSelect();
			 return page1;
		 }
		 
}
