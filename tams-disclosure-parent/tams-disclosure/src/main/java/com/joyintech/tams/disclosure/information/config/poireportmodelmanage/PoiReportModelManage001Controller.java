/*
 * 文件名：ReportTemplate001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.poireportmodelmanage;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.common.base.entity.DisPoiTab;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.information.config.poireportmodelmanage.dto.PoiReportModelManage002Dto;
import com.joyintech.tams.disclosure.information.config.poireportmodelmanage.service.PoiReportModelManage001Service;
import com.joyintech.tams.disclosure.information.config.poireportmodelmanage.service.PoiReportModelManage002Service;
import com.joyintech.tams.disclosure.information.manage.workrule.dto.ReportRule001Dto;

/**
 * 报表模板管理 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author lishicheng
 * @version 1.0 2018年11月9日
 * @see PoiReportModelManage001Controller
 * @since 1.0
 */
@Controller
@PageId("PoiReportModelManage001")
@RequestMapping("/disclosure/information/config/poireportmodelmanage/PoiReportModelManage001")
public class PoiReportModelManage001Controller extends BaseController {

	@SuppressWarnings("rawtypes")
	@Autowired
	private CommonExportService commonExportService;

	@Autowired
	private PoiReportModelManage001Service reportModelManage001Service;
	@Autowired
	private PoiReportModelManage002Service reportModelManage002Service;
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(PoiReportModelManage001Controller.class);

	@Override
	public String init(Model model) {
		String isModelSelect = this.getParam("isModelSelect");

		model.addAttribute(ReportGlobalConstant.REPORT_SERVER_URL,
				CacheUtil.getSysParam(ReportGlobalConstant.REPORT_SERVER_URL));
		model.addAttribute(ReportGlobalConstant.REPORT_SERVER_PATH,
				CacheUtil.getSysParam(ReportGlobalConstant.REPORT_SERVER_PATH));
		model.addAttribute(ReportGlobalConstant.REPORT_USERNAME,
				CacheUtil.getSysParam(ReportGlobalConstant.REPORT_USERNAME));
		model.addAttribute(ReportGlobalConstant.REPORT_PASSWORD,
				CacheUtil.getSysParam(ReportGlobalConstant.REPORT_PASSWORD));

		model.addAttribute("isModelSelect", isModelSelect);
        this.setSelectOptionToModel(model,DictCode.维护数据状态);
		return super.init(model);
	}



	/**
	 * 左侧栏查询 <一句话功能简述> <功能详细描述>
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("findPage")
	public Page<DisPoiTab> findIndexId() throws Exception {
		Page<DisPoiTab> page = reportModelManage001Service.findByPage(RequestUtil.getPageParam());
		return page;
	}

	/**
	 * 页面DB删除
	 * @param dto
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public AjaxResult delete(@RequestBody PoiReportModelManage001Dto dto) {
	AjaxResult result = new AjaxResult("删除成功", MessageType.提示消息);
	String  operateType=dto.getOperateType();
	String  tabNum=dto.getTabNum();
      if(operateType.equals("delete")){
    	  //单行删除
			reportModelManage001Service.delete(tabNum);
      }else if(operateType.equals("deleteall")){
    	  //多行删除
    	  List<PoiReportModelManage001Dto>  listDto = dto.getSelectedItem();
    		try {
    			for(PoiReportModelManage001Dto lidto:listDto){
    				//删除
    				reportModelManage001Service.delete(lidto.getTabNum());
    			}
    		} catch (Exception e) {
    			logger.error("error:",e);
    			result = new AjaxResult("删除失败", MessageType.错误消息);
    		}
      }
	return result;
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
    	String tempPath = RequestUtil.getParam("tempPath");
    	String tempName = RequestUtil.getParam("tempName");
    	
        // 目标文件
        File  targetFile=new  File(tempPath+"/"+tempName);
        InputStream in = null;
        try {
			if (!targetFile.exists()) {
				throw new BusinessException("文件[" +targetFile.getPath() + "]不存在！");
			}
			else {
				in = new FileInputStream(targetFile);
			}
			return ExportUtil.export(targetFile.getName(), in);
		}finally {
        	if(in != null){
        		in.close();
			}
		}
    }
}
