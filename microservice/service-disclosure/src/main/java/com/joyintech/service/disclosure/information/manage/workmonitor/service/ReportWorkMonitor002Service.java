/*
 * 文件名：ValFinprodTradeQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.disclosure.information.manage.workmonitor.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.disclosure.information.manage.workmonitor.dao.ReportWorkMonitor002Mapper;
import com.joyintech.service.disclosure.information.manage.workmonitor.dto.ReportWorkMonitor002Dto;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;

/**
 * 
 * 报表作业监控查看详细Service
 * @author 郭忠宝
 * @version 1.0 2018年12月5日
 * @see ReportWorkMonitor002Service
 * @since 1.0
 */
@Service(value="serviceReportWorkMonitor002Service")
public class ReportWorkMonitor002Service extends CommonExportService<ReportWorkMonitor002Dto> {
	
    /**
     */
    @Autowired
    private ReportWorkMonitor002Mapper reportWorkMonitor002Mapper;
    
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ReportWorkMonitor002Dto> findPage(int page, int length, Map<String, Object> params) throws Exception {
        
    	String jobId = (String)params.get("jobId");
        String[] jobIdArr = null;
        if(StringUtil.isNotEmpty(jobId)){
            jobIdArr = jobId.split(",");
        }
        params.put("jobIdArr", jobIdArr);
        
        String execStatus = (String)params.get("execStatus");
        String[] execStatusArr = null;
        if(StringUtil.isNotEmpty(execStatus)){
        	execStatusArr = execStatus.split(",");
        }
        params.put("execStatusArr", execStatusArr);
        
        String execResult = (String)params.get("execResult");
        String[] execResultArr = null;
        if(StringUtil.isNotEmpty(execResult)){
        	execResultArr = execResult.split(",");
        }
        params.put("execResultArr", execResultArr);
        
        String prodId = (String)params.get("prodId");
        String[] prodIdArr = null;
        if(StringUtil.isNotEmpty(prodId)){
            prodIdArr = prodId.split(",");
        }
        params.put("prodIdArr", prodIdArr);
        
        String repType = (String)params.get("repType");
        String[] repTypeArr = null;
        if(StringUtil.isNotEmpty(repType)){
        	repTypeArr = repType.split(",");
        }
        params.put("repTypeArr", repTypeArr);
        
        params.put("status", DisclosureDictCodeList.数据状态_有效.getValue());
        return super.queryForPageForSelectReturn(page, length, ()-> reportWorkMonitor002Mapper.findPage(params));
    }
}
