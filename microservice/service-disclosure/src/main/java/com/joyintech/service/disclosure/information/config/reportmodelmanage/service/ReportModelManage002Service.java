/*
 * 文件名：ReportModelManage002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.disclosure.information.config.reportmodelmanage.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.service.disclosure.information.config.reportmodelmanage.dto.ReportParamDto;
import org.apache.commons.collections4.CollectionUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.disclosure.information.config.reportmodelmanage.dao.ReportModelManage002Mapper;
import com.joyintech.service.disclosure.information.config.reportmodelmanage.dto.ReportModelManage002Dto;
import com.joyintech.tams.common.base.entity.RepInfo;


/**
 * 报表模板管理新建service
 * 
 * @author longhao
 * @version 2.0 2018年5月3日
 * @see ReportModelManage002Service
 * @since 2.0
 */
@Service(value="serviceReportModelManage002Service")
@Transactional(rollbackFor = Exception.class)
public class ReportModelManage002Service extends CommonExportService<RepInfo> {
    
    
    
    @Autowired
    private ReportModelManage002Mapper reportModelManage002Mapper;
    
    @Autowired
	private ProgressBarService progressBarService;
        /**
         * 日志
         */
    private Logger logger = LoggerFactory.getLogger(ReportModelManage002Service.class);
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
	public List<ReportParamDto> findParamPage(String repId) throws IOException {
		List<String> paramList= reportModelManage002Mapper.findParamPage(repId);
		List<ReportParamDto> list=new ArrayList<>();
		for(String param:paramList){
			List<ReportParamDto>reportParamList=JsonUtils.json2List(param,ReportParamDto.class);
			if(CollectionUtils.isNotEmpty(reportParamList)){
				list.addAll(reportParamList);
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
	public ReportModelManage002Dto getReportModelManage002Dto(String code) {
		ReportModelManage002Dto dto = reportModelManage002Mapper.getReportModelManage002Dto(code);
		return dto;
	}
	 /**
	 * 移除进度条
	 * @param request
	 * @return
	 */
	public void removePercent(HttpServletRequest request)
	{
		progressBarService.removePercent("ReportModelManage002", ProgressBarService.FILE_UPLOAD_ID);
	}
	/**
	 * 读取文件
	 * @param request
	 * @return
	 */
	public void readFile(HttpServletRequest request)
	{
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
	public void insert(ReportModelManage002Dto dto) {
		dto.setKeyId(reportModelManage002Mapper.setId());
		reportModelManage002Mapper.insert(dto);
		
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
}
