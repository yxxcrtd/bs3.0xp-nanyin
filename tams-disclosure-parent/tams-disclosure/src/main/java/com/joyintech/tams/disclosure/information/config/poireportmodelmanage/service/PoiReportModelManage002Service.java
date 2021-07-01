/*
 * 文件名：ReportModelManage002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.poireportmodelmanage.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.DisPoiTab;
import com.joyintech.tams.common.base.entity.DisPraamsSub;
import com.joyintech.tams.common.base.entity.RepInfo;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.dao.GetPrdInfoSelectMapper;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.config.poireportmodelmanage.dto.PoiReportModelManage002Dto;
import com.joyintech.tams.disclosure.information.config.reportmodelmanage.dao.ReportModelManage002Mapper;
import com.joyintech.tams.disclosure.information.config.reportmodelmanage.dto.ReportModelManage002Dto;


/**
 * 报表模板管理新建service
 * 
 * @author longhao
 * @version 2.0 2018年5月3日
 * @see PoiReportModelManage002Service
 * @since 2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PoiReportModelManage002Service extends CommonExportService<RepInfo> {
    
    
    
    @Autowired
    private ReportModelManage002Mapper reportModelManage002Mapper;
   
    @Autowired
    private  GetPrdInfoSelectMapper  getPrdInfoSelectMapper;
    
    @Autowired
	private ProgressBarService progressBarService;
        /**
         * 日志
         */
    private Logger logger = LoggerFactory.getLogger(PoiReportModelManage002Service.class);
    /**
     * 获取数据源
     * @return
     */
	public String getDataSource() {
//			return reportModelManage002Mapper.getDataSource();
			return restTemplate.postForObject(
	                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportModelManage002/getDataSource",null,
	                 String.class);
			
			
			
		}
	/**
	 * 获取生成方式
	 * @return
	 */
	public String getCreateWay() {
//		return reportModelManage002Mapper.getCreateWay();
		return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportModelManage002/getCreateWay",null,
                 String.class);
		
		
		
		
	}
	public String getReportWarehouse() {
		
		return reportModelManage002Mapper.getReportWarehouse();
	}
	/**
	 * 查询模板参数
	 * @param param keyId
	 * @return
	 */
	public List<ReportModelManage002Dto> findParamPage(String repId) {
		
		
		
		 return restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportModelManage002/findParamPage?repId="+repId,
				 ReportModelManage002Dto.class);
//		List<String> paramList= reportModelManage002Mapper.findParamPage(repId);
//		List<ReportModelManage002Dto> list=new ArrayList<>();
//			for(String param:paramList){
//				if(StringUtil.isNotEmpty(param)){
//					//去掉参数字符串的{}
//					String params=param.substring(1, param.length()-1);
//					//去掉参数字符串的''
//					String params2=params.replace("'", "");
//					//分割参数字符串
//					String[] params3=params2.split(",");
//					int i=1;
//					for(String para:params3){
//						ReportModelManage002Dto dto=new ReportModelManage002Dto();
//						//设置参数编号
//						dto.setParamNum(i++);
//						//设置参数名称
//						dto.setParamName(para.split(":")[0]);
//						switch (dto.getParamName()){
//							case "portnum":dto.setName("产品名称");break;
//							case "startDate":dto.setName("开始日期");break;
//							case "endDate":dto.setName("结束日期");break;
//							default: break;
//						}
//						//设置参数类型
//						dto.setParamType(para.split(":")[1]);
//						list.add(dto);
//					}
//				}
//				
//				
//				
//			
//		}
//	
//		return list;
	}
	/**
	 * 保存check
	 * @param dto
	 * @return
	 */
	public AjaxResult check(PoiReportModelManage002Dto dto) {
		Set<String> message = new HashSet<String>();
		String pageModel = dto.getPageModel();
		String  liparams=dto.getLiparams();
		if(pageModel.equals("0")){
			//新增
			PoiReportModelManage002Dto poiReportModelManage002Dto = restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/PoiReportModelManage002/getgetDisPoiTabDto?code="+dto.getTabNum(),
					PoiReportModelManage002Dto.class);
			if(poiReportModelManage002Dto!=null
					&&poiReportModelManage002Dto.getTabNum()!=null){
				 message.add("报表编号不能重复");
			}
			if(	StringUtil.isEmpty(dto.getDisId())){
	            message.add("请输入查询条件表主键");
	        }
			if(StringUtil.isEmpty(dto.getFileName())){
	        	 message.add("请上传模板");
	        }if(StringUtil.isEmpty(dto.getDisOrder())){
	       	 message.add("请输入序号");
	        }
		}else if(pageModel.equals("1")){
			try {
			List<PoiReportModelManage002Dto>  list1=JsonUtils.json2List(liparams, PoiReportModelManage002Dto.class);
			for(PoiReportModelManage002Dto  dto02:list1){
			   String disId=dto02.getDisId();
			   if(StringUtil.isEmpty(disId)){
		            message.add("请输入查询条件表主键");
		            break;
		        }
			}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//必填項校驗
		if(StringUtil.isEmpty(dto.getTabNum())){
            message.add("请输入报表编号");
        }if(StringUtil.isEmpty(dto.getTabName())){
        	 message.add("请输入报表名称");
        }if(StringUtil.isEmpty(dto.getTempName())){
        	 message.add("请输入模板名称");
        }
		//字段长度check
		if(dto.getTabNum().length()>200){
			message.add("报表编号长度校验失败");
		}
		if(dto.getTabName().length()>200){
			message.add("报表名称长度校验失败");
		}
		if(dto.getTempName().length()>50){
			message.add("模板名称长度校验失败");
		}
		AjaxResult result = new AjaxResult();
		if(CollectionUtils.isNotEmpty(message)){
            List<ValidMessage> errorList = new ArrayList<>();
            message.forEach(action->{
                errorList.add(new ValidMessage(action));
                });
            result.setErrorList(errorList);
        }
		return result;
	}
	
	
	//初始化编辑页面数据
	public PoiReportModelManage002Dto getDisPoiTabDto(String code) {
//		ReportModelManage002Dto dto = reportModelManage002Mapper.getReportModelManage002Dto(code);
		
		PoiReportModelManage002Dto dto = restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/PoiReportModelManage002/getgetDisPoiTabDto?code="+code,
				PoiReportModelManage002Dto.class);
		
		
		return dto;
	}
	 /**
	 * 移除进度条
	 * @param request
	 * @return
	 */
	public void removePercent(HttpServletRequest request) throws Exception {
		progressBarService.removePercent("PoiReportModelManage002", ProgressBarService.FILE_UPLOAD_ID);
	}
	/**
	 * 读取文件
	 * @param request
	 * @return
	 */
	public void readFile(HttpServletRequest request) throws Exception {
		progressBarService.updatePercent("PoiReportModelManage002", ProgressBarService.FILE_UPLOAD_ID, 1D, false);
	}
	/**
	 * 查询是否存在相同模板
	 * @param dto
	 * @return
	 */
	public int selectRepIdAndUri(ReportModelManage002Dto dto) {
//		int i=reportModelManage002Mapper.selectRepIdAndUri(dto);
		
		int i = restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportModelManage002/selectRepIdAndUri",
                dto, Integer.class);
		
		
		
		
		return i;
	}
	/**
	 * 插入poi基础表数据
	 * @param dto
	 */
	public void insertDisPoiTab(DisPoiTab dto) {
		restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/PoiReportModelManage002/insertDisPoiTab",
                dto, Integer.class);
		
	}
	
	/**
	 * 插入poi查询字表
	 * @param dto
	 */
	public void insertDisPraamsSub(List<DisPraamsSub> list) {
		restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/PoiReportModelManage002/insertDisPraamsSub",
                list, Integer.class);
		
	}
	/**
	 * 更新数据
	 * @param dto
	 * @return
	 */
	public void update(ReportModelManage002Dto dto) {
		
//		reportModelManage002Mapper.update(dto);
		
		restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportModelManage002/update",
                dto, Integer.class);
	}
	/**
	 * 删除
	 * @param 
	 */
	public void deleteModle(Map<String, Object> param) {
//		reportModelManage002Mapper.deleteModle(param);
		
		restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportModelManage001/deleteModle",
                param, Integer.class);
		
	}
	
	
	/**
	 * 下载报表模板zip文件
	 * @param filenpath
	 * @return
	 * @throws IOException
	 */
	public ResponseEntity<byte[]> downLoadReportInfo(String filenpath) throws IOException{
		String uploadFilePath  = CacheUtil.getSysParam(ReportGlobalConstant.TEMPLATE_FILE_PATH); 
		File filepath=new File(uploadFilePath+filenpath);///NB/GLJY
		//检查文件夹是否存在
		String zipFileName = FileNameUtil.createFileName("zip");
		File zipFile = new File(uploadFilePath + File.separator + zipFileName);
		ZipOutputStream  zipOut =null;
		try {
			if(filepath.exists()){
				zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
				zipOut.setEncoding("gbk");
				compressFile2(zipOut, filepath,filepath.getName());
			}
		}finally {
			if(zipOut != null){
				zipOut.close();
			}
		}
		return ExportUtil.export(zipFileName, zipFile, true);

	}
	
	
	/**
     * 压缩报表信息
     * @param 容器
     * @param 被压缩文件
     * @param 路径
	 * @throws IOException 
     */
	private void compressFile2(ZipOutputStream out, File sourcefile, String basedir) throws IOException{
        if (sourcefile.isDirectory()) {
        	File[] files = sourcefile.listFiles();
        	
        	if(files==null||files.length==0) {
				out.putNextEntry(new ZipEntry(basedir + File.separator));
			} 
        	else {
				for(int i=0; i<files.length; i++) {
					compressFile2(out, files[i], files[i].getName());
				}
			}
        }
        else {
			FileInputStream fos = null;
			BufferedInputStream bis = null;
			try {
				out.putNextEntry(new ZipEntry(basedir));
				fos = new FileInputStream(sourcefile);
				bis = new BufferedInputStream(fos);
				int len;
				byte[] buf = new byte[1024];
				while((len=bis.read(buf, 0, 1024)) != -1) {
					out.write(buf, 0, len);
				}
			}finally {
				if (fos != null){
					fos.close();
				}
				if (fos != null){
					bis.close();
				}
			}
    	}
	}
	   /**
     * 获取下拉框
     * @return
     */
	public List<SelectOptionDto>  getInfoSelect() {
			
		List<SelectOptionDto> list=	getPrdInfoSelectMapper.getInfoSelect();
	    return list;	
	}
	
}
