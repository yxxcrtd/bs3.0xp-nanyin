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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.disclosure.information.manage.workmonitor.dao.ReportWorkMonitor001Mapper;
import com.joyintech.service.disclosure.information.manage.workmonitor.dto.ReportWorkMonitor001Dto;
import com.joyintech.service.disclosure.information.manage.workmonitor.dto.ReportWorkMonitorNew001Dto;
import com.joyintech.tams.common.base.dao.RepJobMapper;
import com.joyintech.tams.common.base.entity.RepJob;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.service.InfoDisclosureReadAndGenDetailService;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 
 * 金融产品交易查询Service
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValFinprodTradeQuery001Service
 * @since 1.0
 */
@Service(value="serviceReportWorkMonitor001Service")
public class ReportWorkMonitor001Service extends CommonExportService<ReportWorkMonitorNew001Dto> implements BaseProcessService<ReportWorkMonitor001Dto> {
	
    /**
     */
    @Autowired
    private ReportWorkMonitor001Mapper reportWorkMonitor001Mapper;
    
    @Autowired
    private RepJobMapper repJobMapper;
    @Autowired
    private InfoDisclosureReadAndGenDetailService infoDisclosureReadAndGenDetailService;
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ReportWorkMonitor001Service.class);
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ReportWorkMonitorNew001Dto> findPage(int page, int length, Map<String, Object> params) throws Exception {
        
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
        
//        String prodId = (String)params.get("prodId");
//        String[] prodIdArr = null;
//        if(StringUtil.isNotEmpty(prodId)){
//            prodIdArr = prodId.split(",");
//        }
//        params.put("prodIdArr", prodIdArr);

        //用于流程-我的待办-点击查看
        params.put("finprodIdForFlow", (String)params.get("finprodIdForFlow"));
        params.put("valDateForFlow", (String)params.get("valDateForFlow"));
        params.put("valBalFlagCodeForFlow", (String)params.get("valBalFlagCodeForFlow"));
        params.put("status", DisclosureDictCodeList.数据状态_有效.getValue());
        return super.queryForPageForSelectReturn(page, length, ()-> reportWorkMonitor001Mapper.queryPageListNew(params));
    }



    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<ReportWorkMonitor001Dto> queryPageList(Map<String, Object> params) {
        return reportWorkMonitor001Mapper.queryPageList(params);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @see
     * @since 1.0
     */
    public int updateConfirmStatus(List<ReportWorkMonitor001Dto> listdto) {
        int counts=0;
        for(ReportWorkMonitor001Dto dto:listdto){
            counts=counts+reportWorkMonitor001Mapper.updateConfirmStatusById(dto);
        }
        return counts;
    }

    /**
     * 估值表余额表状态表 - 确认状态和下发状态更新
     * {@inheritDoc}
     */
    @Override
    public void doComplete(ReportWorkMonitor001Dto entity, OperationType type){
        if(entity!=null){
        	
//        	reportWorkMonitor001Mapper.updateConfirmStatusById(entity);
        }
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getJobidList(Map<String, Object> map) {
        List<ReportWorkMonitor001Dto> reportIdList = reportWorkMonitor001Mapper.selectJobidList(map);
        List<SelectOptionDto> optionList= new ArrayList<SelectOptionDto>();
        reportIdList.forEach(item ->{
            SelectOptionDto selectOptionDto =  new SelectOptionDto();
            selectOptionDto.setId(item.getJobId());
            selectOptionDto.setText(item.getJobName());
            optionList.add(selectOptionDto);
        });
        return optionList;
    }
    /**
     * 重新跑全部
     * @param map
     * @return 01 无满足条件的规则   02 无满足条件的产品  03 操作成功 04 失败
     */
    public boolean runAgain(Map<String, Object> map){
    	//通过jobid获取job的内容
    	RepJob repJob = repJobMapper.selectByPrimaryKey(map.get("jobId").toString());
    	//生成披露规则用
//    	Map<String,Date> dateMap = new HashMap<String,Date>();
    	boolean flag = false;
    	try {
//    		flag = infoDisclosureReadAndGenDetailService.readAndGenDetailAgain(repJob, dateMap, 
//    				DateUtil.parse(map.get("execDate").toString()),map.get("jobSeq").toString());
    		flag = infoDisclosureReadAndGenDetailService.readAndGenMain(repJob, DictCodeListExt2.执行标志_重新执行.getValue(),
    				map.get("jobSeq").toString(), DateUtil.parse(map.get("execDate").toString()));
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("error:",e);
		}
    	return flag;
    }
    /**
     * 重新跑失败
     * @param map
     * @return
     */
    public String runErrorAgain(Map<String, Object> map){
    	//通过jobid获取job的内容
    	RepJob repJob = repJobMapper.selectByPrimaryKey(map.get("jobId").toString());
    	//生成披露规则用
    	Map<String,Date> dateMap = new HashMap<String,Date>();
    	String flag = "04";
    	try {
    		flag = infoDisclosureReadAndGenDetailService.readAndGenErrorAgain(repJob, dateMap, 
    				DateUtil.parse(map.get("execDate").toString()),map.get("jobSeq").toString());
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("error:",e);
		}
    	return flag;
    }
}
