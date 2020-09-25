/*
 * 文件名：ReportModelManage002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.poireportmodelmanage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
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

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.DisPoiTab;
import com.joyintech.tams.common.base.entity.DisPraamsSub;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.information.config.poireportmodelmanage.dto.PoiReportModelManage002Dto;
import com.joyintech.tams.disclosure.information.config.poireportmodelmanage.service.PoiReportModelManage002Service;
import com.joyintech.tams.disclosure.information.config.reportmodelmanage.service.ReportModelManage002Service;
import com.joyintech.tams.disclosure.interf.interf.common.util.FileUtil;
/**
 * 报表模板管理新建
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author longhao
 * @version 1.0 2018年11月9日
 * @see PoiReportModelManage002Controller
 * @since 1.0
 */
@Controller
@PageId("PoiReportModelManage002")
@RequestMapping("/disclosure/information/config/poireportmodelmanage/PoiReportModelManage002")
public class PoiReportModelManage002Controller extends BaseController {


	@Autowired
	ReportModelManage002Service reportModelManage002Service;
	
	@Autowired
	PoiReportModelManage002Service poireportModelManage002Service;
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(PoiReportModelManage002Controller.class);
	@Override
	public String init(Model model) {
//		model.addAttribute("dataSource", reportModelManage002Service.getDataSource());
//		model.addAttribute("createWay", reportModelManage002Service.getCreateWay());
		model.addAttribute("reportWarehouse", CacheUtil.getSysParam( ReportGlobalConstant.TEMPLATE_FILE_PATH));
//		model.addAttribute("userName", "");
//		model.addAttribute("passWord", "");
//		this.setSelectOptionToModel(model,DictCode.报表数据格式类型);
		
		String id = this.getParam("tabNum");
		if (StringUtil.isNotEmpty(id)) {
			model.addAttribute("entity", poireportModelManage002Service.getDisPoiTabDto(id));
		}
		return super.init(model);
	}
	
	/**
	 * 根据主键id查询模板参数
	 * @return
	 */
	@RequestMapping("/findParamPage")
	@ResponseBody
	public List<DisPraamsSub> findParamPage() {
		if (StringUtil.isNotEmpty(this.getParam("tabNum"))) {
			PoiReportModelManage002Dto dto= poireportModelManage002Service.getDisPoiTabDto(this.getParam("tabNum"));
		      List<DisPraamsSub>   lisub=dto.getAddParam();
		      List<DisPraamsSub>   lisub2=new ArrayList<DisPraamsSub>();

		      for(DisPraamsSub sub:lisub){
		    	  DisPraamsSub dis=new DisPraamsSub();
		    	  dis.setDisId(sub.getDisId());
		    	  dis.setDisOrder(sub.getDisOrder());
		    	  lisub2.add(dis);
		      }
		      return lisub2;
		}
		
		return new ArrayList<DisPraamsSub>();
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
	  public AjaxResult checkSave(@RequestBody PoiReportModelManage002Dto dto){
	      AjaxResult result = poireportModelManage002Service.check(dto);
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
			List<PoiReportModelManage002Dto> addParamList=new  ArrayList<PoiReportModelManage002Dto>();
			String pageModel = this.getParam("pageModel");
			String datas = this.getParam("object");
			PoiReportModelManage002Dto dto = JsonUtils.json2Object(datas, PoiReportModelManage002Dto.class);
			DisPoiTab  disDto=new  DisPoiTab();
			List<DisPraamsSub>  subList=new ArrayList<DisPraamsSub>();
			if(pageModel.equals("0")){
				//新增
				String addParam = this.getParam("addParam");
				addParamList = JsonUtils.json2List(addParam, PoiReportModelManage002Dto.class);
				//获取中间目录下的文件
				String tempFilePath  = CacheUtil.getSysParam(ReportGlobalConstant.TEMPLATE_TMEP_PATH); 
				File   tempFile=new File(tempFilePath+"/"+dto.getFileName());
				//获取目标目录文件
				File   finalFile=new  File(dto.getReportWarehouse()+"/"+dto.getFileName());
				if(!finalFile.exists()){
					tempFile.renameTo(finalFile);
					tempFile.delete();
				}
			}else if(pageModel.equals("1")){
				//编辑
				String liparams = this.getParam("liparams");
				addParamList = JsonUtils.json2List(liparams, PoiReportModelManage002Dto.class);
				//获取编辑前dto
				PoiReportModelManage002Dto  ddto=poireportModelManage002Service.getDisPoiTabDto(dto.getTabNum());
			    //获取编辑前模板名称
				String  tempName=ddto.getTempName();
				//获取编辑前模板路径
				String  tempPath=ddto.getTempPath();
				File  beFile=new  File(tempPath);
				//获取编辑前的文件名称
				dto.setFileName(beFile.getName());
				if(!tempName.equals(dto.getTempName())){
					//模板名称变动
					File file=new  File(dto.getReportWarehouse()+"/"+dto.getTempName());
					File dfile=new  File(dto.getReportWarehouse()+"/"+ddto.getTempName());
					dfile.renameTo(file);
				}
			
			}
			//报表编号
			disDto.setTabNum(dto.getTabNum());
			//报表名称
			disDto.setTabName(dto.getTabName());
			//模板名称
			disDto.setTempName(dto.getTempName());
			//模板路径
			disDto.setTempPath(dto.getReportWarehouse());
			//参数条件
			disDto.setTempPrams(dto.getDisId());
			//状态
			disDto.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
			//创建人
			disDto.setCreateUser("system");
			disDto.setUpdateUser("system");
			disDto.setCreateTime(new Date());
			disDto.setUpdateTime(new Date());
			
			for(PoiReportModelManage002Dto addto: addParamList){
				DisPraamsSub  dissubdto=new  DisPraamsSub();
				//关联主表编号
				dissubdto.setTabNum(dto.getTabNum());
				//关联查询表主键
				dissubdto.setDisId(addto.getDisId());
				//序号
				dissubdto.setDisOrder(addto.getDisOrder());
				dissubdto.setCreateUser("system");
				dissubdto.setUpdateUser("system");
				dissubdto.setCreateTime(new Date());
				dissubdto.setUpdateTime(new Date());
				subList.add(dissubdto);
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
						//int j=reportModelManage002Service.selectRepIdAndUri(null);
					    int  j=-2;
						if(j>0){
							result = new AjaxResult("模板文件已存在",
									MessageType.错误消息);
						}
						else{
							//插入查询条件字表
							poireportModelManage002Service.insertDisPraamsSub(subList);
							//插入基础表
							poireportModelManage002Service.insertDisPoiTab(disDto);
						}
					}
				}
				if(this.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
					if(StringUtil.isNotEmpty(dto.getFileName())) {
						String fileName=dto.getFileName();
						fileName=fileName.substring(0,fileName.lastIndexOf("."));
						dto.setFileName(fileName);
					}
					//插入查询条件字表
					poireportModelManage002Service.insertDisPraamsSub(subList);
					//插入基础表
					poireportModelManage002Service.insertDisPoiTab(disDto);
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
				 poireportModelManage002Service.removePercent(request);
				 String sourcePath=this.getParam("fileName");
				 File sourceFile=new File(sourcePath);
				 if(sourceFile.exists()){
					 sourceFile.delete();
				 }
				 String msg = "请输入模板名称,并重新上传文件";
					res =  new AjaxResult(msg, MessageType.错误消息);
					return res;
			 }
			 //判断目标目镜是否存在
			 String uploadFilePath  = CacheUtil.getSysParam(ReportGlobalConstant.TEMPLATE_FILE_PATH); 
			 File filepath = new File(uploadFilePath);///home/tamsuser/poi
		     //File filepath2=new File(uploadFilePath+"/"+this.getParam("folder"));///NB/GLJY
			 if(!filepath.exists()){
				 filepath.mkdirs();
			 }
//			 if(!filepath2.exists()){
//				filepath2.mkdirs();
//
//			 }
			 else{
//				 String TEMPLATE_DELETE_TYPE  = CacheUtil.getSysParam(ReportGlobalConstant.TEMPLATE_DELETE_TYPE); 
//				 if(null!=TEMPLATE_DELETE_TYPE&&TEMPLATE_DELETE_TYPE.equals(DisclosureDictCodeList.是否_是.getValue())){
//					 FileUtil.deleteDir(filepath2);
//					 filepath2.mkdirs();
//				 }
				 
			 }
			   if(filepath.exists()&&filepath.isDirectory()){
					String filenamePath=this.getParam("fileName");
					String fileName1=this.getParam("fileName1");
					File filenamePathFile=new File(filenamePath);
					//File filepath3=new File(filepath2.getPath()+"/"+fileName1);///NB/GLJY
					//存入第三方临时目录
					
					String tempFilePath  = CacheUtil.getSysParam(ReportGlobalConstant.TEMPLATE_TMEP_PATH); 
					File  tempPath=new  File(tempFilePath);
					if(!tempPath.exists()){
						//中间目录不存在创建
						tempPath.mkdirs();
					}
					//把临时目录下文件删除
					File[]  files=tempPath.listFiles();
					if(files!=null&&files.length>0){
						for(File  f:files){
							f.delete();
						}
					}
					File  tempPathFileName=new  File(tempPath.getPath()+"/"+fileName1);
					if(!tempPathFileName.exists()){
						filenamePathFile.renameTo(tempPathFileName);
						poireportModelManage002Service.removePercent(request);
						String msg = "文件上传成功";
						res =  new AjaxResult(msg, MessageType.提示消息);
						return res; 
					}else{
						//如果已经存在，先进行删除
						tempPathFileName.delete();
						filenamePathFile.renameTo(tempPathFileName);
						poireportModelManage002Service.removePercent(request);
						String msg = "文件上传成功";
						res =  new AjaxResult(msg, MessageType.提示消息);
						return res; 
					}
				 }
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
		
		/**
		 * 分页查询产品下拉框
		 * 
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("getInfoSelect")
		@ResponseBody
	    public List<SelectOptionDto> getPrdInfoSelect()
	        throws Exception {
			List<SelectOptionDto> prdInfoSelect =poireportModelManage002Service.getInfoSelect();
	        return prdInfoSelect;
	    }
		
}
