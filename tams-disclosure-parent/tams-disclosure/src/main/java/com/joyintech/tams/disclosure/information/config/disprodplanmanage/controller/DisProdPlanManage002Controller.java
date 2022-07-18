/*
 * 文件名：DisProdPlanManage002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.disprodplanmanage.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service.GetPrdInfoSelectService;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage002Dto;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.service.DisProdPlanManage002Service;
import com.joyintech.tams.disclosure.information.config.reportcontentenum.dto.ReportContentEnum001Dto;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.interf.interf.common.util.FileUtil;
/**
 * 报表模板管理新建
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author longhao
 * @version 1.0 2018年11月9日
 * @see DisProdPlanManage002Controller
 * @since 1.0
 */
@Controller
@PageId("DisProdPlanManage002")
@RequestMapping("/disclosure/information/config/disprodplanmanage/DisProdPlanManage002")
public class DisProdPlanManage002Controller extends BaseController {

	@Autowired
	DisProdPlanManage002Service disProdPlanManage002Service;

	@Autowired
	private GetPrdInfoSelectService getPrdInfoSelectService;

	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(DisProdPlanManage002Controller.class);
	@Override
	public String init(Model model) {

		this.setSelectOptionToModel(model,DictCode.信披_数据状态);
		
		String id = this.getParam("prodPlanId");
		if (StringUtil.isNotEmpty(id)) {
			model.addAttribute("entity", disProdPlanManage002Service.getDisProdPlanManage002Dto(id));
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
	public Page<SelectOptionDto> getPrdInfoSelect() {
		Map<String, Object> param= RequestUtil.getPageParam();
		// param.put("sonFlag","1");
		Page<SelectOptionDto> prdInfoSelect = getPrdInfoSelectService.getAllPrdInfoSelect(param);
		return prdInfoSelect;
	}


	/**
	 *
	 * 分页查询产品下拉框
	 * @param
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("/getPrdInfo")
	public RepProdInfo getPrdInfo(String finprodId){

		return  disProdPlanManage002Service.getPrdInfo(finprodId);
	}


	/**
	 * 根据主键id查询模板参数
	 * @return
	 */
	@RequestMapping("/findParamPage")
	@ResponseBody
	public List<DisProdPlanManage002Dto> findParamPage() {
		if (StringUtil.isNotEmpty(this.getParam("repId"))) {
			return disProdPlanManage002Service.findParamPage(this.getParam("repId"));
		}
		
		return new ArrayList<DisProdPlanManage002Dto>();
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
	  public AjaxResult checkSave(@RequestBody DisProdPlanManage002Dto dto){
	      AjaxResult result = disProdPlanManage002Service.check(dto);
	      if(result.getErrorList().isEmpty()){
	          result.setData(this.getMessage("DIS_COMMON_C_0001", this.getMessage("SysCommon.save")));
	      }
	      return  result;
	  }

	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Valid DisProdPlanManage002Dto dto, BindingResult bindingResult) {
		String operateType = dto.getOperateType();
		return commonCheck(bindingResult, operateType);
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
			DisProdPlanManage002Dto dto = JsonUtils.json2Object(datas, DisProdPlanManage002Dto.class);
			try {
				if (this.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
					dto.setDataStatus(DictCodeListExt3.信披_数据状态_有效.getValue());
					dto.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());
					disProdPlanManage002Service.insert(dto);
				}
				if(this.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
					disProdPlanManage002Service.update(dto);
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
				 String msg = "请输入上传文件夹,并重新上传文件";
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
			 else{
				 String TEMPLATE_DELETE_TYPE  = CacheUtil.getSysParam(ReportGlobalConstant.TEMPLATE_DELETE_TYPE); 
				 if(null!=TEMPLATE_DELETE_TYPE&&TEMPLATE_DELETE_TYPE.equals(DisclosureDictCodeList.是否_是.getValue())){
					 FileUtil.deleteDir(filepath2);
					 filepath2.mkdirs();
				 }
				 
			 }
			 
			 
			 if(filepath.exists()&&filepath.isDirectory()){
				 
			//调用解压工具,并解压至指定目录
			 boolean deczip =  FileUtil.decompressZip(new SafelyFile(this.getParam("fileName")), filepath2.getPath());
			 
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
		 * @return
		 * @throws Exception
		 */
		@ResponseBody
		@RequestMapping("downloadReport")
		public ResponseEntity<byte[]> downloadReport() throws IOException {
			
			
			return disProdPlanManage002Service.downLoadReportInfo(this.getParam("folder"));
		}
		
}
