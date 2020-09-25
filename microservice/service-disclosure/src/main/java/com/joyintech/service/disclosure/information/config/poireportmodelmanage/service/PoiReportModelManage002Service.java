/*
 * 文件名：ReportModelManage002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.disclosure.information.config.poireportmodelmanage.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.disclosure.information.config.poireportmodelmanage.dao.PoiReportModelManage002Mapper;
import com.joyintech.service.disclosure.information.config.poireportmodelmanage.dto.PoiReportModelManage002Dto;
import com.joyintech.service.disclosure.information.config.reportmodelmanage.dao.ReportModelManage002Mapper;
import com.joyintech.service.disclosure.information.config.reportmodelmanage.dto.ReportModelManage002Dto;
import com.joyintech.tams.common.base.entity.DisPoiTab;
import com.joyintech.tams.common.base.entity.DisPraamsSub;
import com.joyintech.tams.common.base.entity.RepInfo;


/**
 * 报表模板管理新建service
 * 
 * @author longhao
 * @version 2.0 2018年5月3日
 * @see PoiReportModelManage002Service
 * @since 2.0
 */
@Service(value="servicePoiReportModelManage002Service")
@Transactional(rollbackFor = Exception.class)
public class PoiReportModelManage002Service extends CommonExportService<RepInfo> {
    
    
    
    @Autowired
    private ReportModelManage002Mapper reportModelManage002Mapper;
    
    @Autowired
    private PoiReportModelManage002Mapper poireportModelManage002Mapper;
    
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
			return reportModelManage002Mapper.getDataSource();
		}
	/**
	 * 获取生成方式
	 * @return
	 */
	public String getCreateWay() {
		return reportModelManage002Mapper.getCreateWay();
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
		List<String> paramList= reportModelManage002Mapper.findParamPage(repId);
		List<ReportModelManage002Dto> list=new ArrayList<>();
			for(String param:paramList){
				if(StringUtil.isNotEmpty(param)){
					//去掉参数字符串的{}
					String params=param.substring(1, param.length()-1);
					//去掉参数字符串的''
					String params2=params.replace("'", "");
					//分割参数字符串
					String[] params3=params2.split(",");
					int i=1;
					for(String para:params3){
						ReportModelManage002Dto dto=new ReportModelManage002Dto();
						//设置参数编号
						dto.setParamNum(i++);
						//设置参数名称
						dto.setParamName(para.split(":")[0]);
						switch (dto.getParamName()){
							case "portnum":dto.setName("产品名称");break;
							case "startDate":dto.setName("开始日期");break;
							case "endDate":dto.setName("结束日期");break;
							default: break;
						}
						//设置参数类型
						dto.setParamType(para.split(":")[1]);
						list.add(dto);
					}
				}
				
				
				
			
		}
	
		return list;
	}
	/**
	 * 保存check
	 * @param dto
	 * @return
	 */
	public AjaxResult check(ReportModelManage002Dto dto) {
		Set<String> message = new HashSet<String>();
		if(StringUtil.isEmpty(dto.getReportDesc())){
            message.add("请输入中文描述");
        }
		if(StringUtil.isEmpty(dto.getFolder())){
            message.add("请输入文件夹");
        }
		if(StringUtil.isEmpty(dto.getReportWarehouse())){
			message.add("请输入报表仓库");
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
		PoiReportModelManage002Dto  poiReportModelManage002Dto=new PoiReportModelManage002Dto();
		//基础表dto
		DisPoiTab dto = poireportModelManage002Mapper.selectByPrimaryKey(code);
		//查询条件表dto
		List<DisPraamsSub>  sublist= poireportModelManage002Mapper.selectById(code);
		if(dto!=null){
			poiReportModelManage002Dto.setTabNum(dto.getTabNum());
			poiReportModelManage002Dto.setTabName(dto.getTabName());
			poiReportModelManage002Dto.setTempName(dto.getTempName());
			poiReportModelManage002Dto.setTempPath(dto.getTempPath());
		}
		if(sublist!=null&&sublist.size()>0){
			poiReportModelManage002Dto.setAddParam(sublist);
		}
		return poiReportModelManage002Dto;
	}
	 /**
	 * 移除进度条
	 * @param request
	 * @return
	 */
	public void removePercent(HttpServletRequest request) throws Exception {
		progressBarService.removePercent("ReportModelManage002", ProgressBarService.FILE_UPLOAD_ID);
	}
	/**
	 * 读取文件
	 * @param request
	 * @return
	 */
	public void readFile(HttpServletRequest request) throws Exception {
		progressBarService.updatePercent("ReportModelManage002", ProgressBarService.FILE_UPLOAD_ID, 1D, false);
	}
	/**
	 * 查询是否存在相同模板
	 * @param dto
	 * @return
	 */
	public int selectRepIdAndUri(ReportModelManage002Dto dto) {
		int i=reportModelManage002Mapper.selectRepIdAndUri(dto);
		return i;
	}
	/**
	 * 插入数据
	 * @param dto
	 */
	public void insertDisPoiTab(DisPoiTab dto) {
		poireportModelManage002Mapper.mergeDisPoiTab(dto);
	}
	
	/**
	 * 插入数据
	 * @param dto
	 */
	public void insertDisPraamsSub(List<DisPraamsSub> list) {
		poireportModelManage002Mapper.mergeDisPraamsSub(list);
	}
	/**
	 * 更新数据
	 * @param dto
	 * @return
	 */
	public void update(ReportModelManage002Dto dto) {
		
		reportModelManage002Mapper.update(dto);
	}
	/**
	 * 删除
	 * @param param
	 */
	public void deleteModle(Map<String, Object> param) {
		reportModelManage002Mapper.deleteModle(param);
		
	}
	
	/**
	 * 获取下拉框值
	 */
	public List<SelectOptionDto>  getInfoSelect(){
		return  poireportModelManage002Mapper.getInfoSelect();
	}
}
