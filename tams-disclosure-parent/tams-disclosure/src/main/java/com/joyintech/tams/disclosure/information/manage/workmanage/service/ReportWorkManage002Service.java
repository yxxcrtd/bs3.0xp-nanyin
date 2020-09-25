/*
 * 文件名：ValFinprodTradeQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.manage.workmanage.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.RepJobExcludeProMapper;
import com.joyintech.tams.common.base.dao.RepJobMapper;
import com.joyintech.tams.common.base.dao.RepJobRangeMapper;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.manage.workmanage.dao.ReportWorkManage002Mapper;
import com.joyintech.tams.disclosure.information.manage.workmanage.dto.ReportWorkManage002Dto;
import com.joyintech.tams.disclosure.information.manage.workmanage.dto.ReportWorkManage003Dto;
import com.joyintech.tams.disclosure.information.manage.workmanage.dto.ReportWorkManage004Dto;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 
 * 作业任务管理-新建Service
 * 
 * @author wanghb
 * @version 1.0 2018年12月6日
 * @see ReportWorkManage002Service
 * @since 1.0
 */
@Service
public class ReportWorkManage002Service extends CommonExportService<ReportWorkManage002Dto> {

	/**
	 */
	@Autowired
	private RepJobMapper repJobMapper;
	/**
	 */
	@Autowired
	private RepJobExcludeProMapper repJobExcludeProMapper;
	/**
	 */
	@Autowired
	private RepJobRangeMapper repJobRangeMapper;
	/**
	 * 新建功能service
	 */
	@Autowired
	private ReportWorkManage002Mapper reportWorkManage002Mapper;
	/**
	 * 日志
	 */
	private static Logger log = LoggerFactory.getLogger(ReportWorkManage002Service.class);
	/**
	 * 查询是否已经存在作业编码和名称
	 * @param dto
	 * @return
	 */
	public int selectJobByIdAndName(ReportWorkManage002Dto dto){
//		return reportWorkManage002Mapper.selectJobByIdAndName(dto);
		
		return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportWorkManage002/selectJobByIdAndName",dto,
    			Integer.class);
		
		
	}
	/**
	 * 
	 * 获取z作业信息
	 * @param jobId
	 * @return
	 * @see
	 * @since 1.0
	 */
	public ReportWorkManage002Dto getReportWorkManage002Dto(String JobId) {
		
		
		ReportWorkManage002Dto dto = restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportWorkManage002/getReportWorkManage002Dto?JobId="+JobId,
    			ReportWorkManage002Dto.class);
		
		
//		ReportWorkManage002Dto dto = reportWorkManage002Mapper.getReportWorkManage002Dto(JobId);
		/*if (StringUtil.isNotEmpty(JobId)) {
			RepJob job = repJobMapper.selectByPrimaryKey(JobId);
			if (null != job) {
				dto = JsonUtils.json2Object(JSONObject.toJSONString(job), ReportWorkManage002Dto.class);
			}
		}*/
		return dto;
	}

	/**
	 * 插入插入报表作业屏蔽产品，作业范围数据
	 * 
	 * @param dto
	 */
	public void doinsertOrUpdate(ReportWorkManage002Dto dto) {
		// 根据作业编号删除报表作业屏蔽产品
		reportWorkManage002Mapper.deleteExcludeProInfo(dto.getJobId());
		// 插入报表作业屏蔽产品REP_JOB_EXCLUDE_PRO
		List<ReportWorkManage003Dto> prodList = dto.getChooseProdTable();
		if (!StringUtil.isEmptyList(prodList)) {
			for (ReportWorkManage003Dto manage3Dto : prodList) {
				manage3Dto.setJobId(dto.getJobId());
				manage3Dto.setId(CommonUtil.getSequenceIndexData(DataIndexSequenceType.基础数据));
				repJobExcludeProMapper.insertSelective(manage3Dto);
			}
		}
		// 根据作业编号删除作业范围数据
		reportWorkManage002Mapper.deleteRangeInfo(dto.getJobId());
		// 插入作业范围数据
		List<ReportWorkManage004Dto> settedRuleList = dto.getSettedRule();
		if (!StringUtil.isEmptyList(settedRuleList)) {
			for (ReportWorkManage004Dto manage4Dto : settedRuleList) {
				manage4Dto.setJobId(dto.getJobId());
				manage4Dto.setFlag(DictCodeListExt2.规则类型_作业表.getValue());
				manage4Dto.setId(CommonUtil.getSequenceIndexData(DataIndexSequenceType.基础数据));
				repJobRangeMapper.insertSelective(manage4Dto);
			}
		}
	}
    /**
     * 保存check
     * @param dto
     * @return
     */
	public AjaxResult check(ReportWorkManage002Dto dto){
		// 验证消息
        Set<String> message = new HashSet<String>();
		if(StringUtil.isEmpty(dto.getJobId())){
            message.add(this.getMessage("REPORT_COMMON_E_0001",this.getMessage("ReportWorkManage002.jobId")));
        }
		if(StringUtil.isEmpty(dto.getJobName())){
            message.add(this.getMessage("REPORT_COMMON_E_0001",this.getMessage("ReportWorkManage002.jobName")));
        }
		if(StringUtil.isEmpty(dto.getRuleId())){
			message.add(this.getMessage("REPORT_COMMON_E_0001",this.getMessage("ReportWorkManage002.ruleId")));
		}
		if(StringUtil.isEmpty(dto.getStartDate())){
			message.add(this.getMessage("REPORT_COMMON_E_0001",this.getMessage("ReportWorkManage002.startDate")));
		}
		if(!StringUtil.isEmpty(dto.getStartDate())&&!StringUtil.isEmpty(dto.getEndDate())&&DateUtil.compareDate(dto.getStartDate(), dto.getEndDate())>0){
			message.add(this.getMessage("REPORT_COMMON_E_0023",this.getMessage("ReportWorkManage002.startDate"),this.getMessage("ReportWorkManage002.endDate")));
		}
		if(StringUtil.equals(dto.getYearType(), ".")&&StringUtil.isEmptyList(dto.getYearAppointList())){
			message.add("请填写指定年");
		}
		if(StringUtil.equals(dto.getMonthType(), ".")&&StringUtil.isEmptyList(dto.getMonthAppointList())){
			message.add("请填写指定月");
		}
		if(StringUtil.equals(dto.getDayType(), ".")&&StringUtil.isEmptyList(dto.getDayAppointList())){
			message.add("请填写指定日");
		}
		if(StringUtil.equals(dto.getWeekType(), ".")&&StringUtil.isEmptyList(dto.getWeekAppointList())){
			message.add("请填写指定周");
		}
		if(StringUtil.equals(dto.getHourType(), ".")&&StringUtil.isEmptyList(dto.getHourAppointList())){
			message.add("请填写指定时");
		}
		if(StringUtil.equals(dto.getMinuteType(), ".")&&StringUtil.isEmptyList(dto.getMinuteAppointList())){
			message.add("请填写指定分");
		}
		if(StringUtil.isEmpty(dto.getIsprodrelated())){
			message.add("请填写是否产品相关");
		}
		AjaxResult result = new AjaxResult();
        if(CollectionUtils.isNotEmpty(message)){
            List<ValidMessage> errorList = new ArrayList<>();
            message.forEach(action->{
                errorList.add(new ValidMessage(action));
                });
            result.setErrorList(errorList);
        }
/*        if(StringUtil.isEmptyList(dto.getSettedRule())){
        	result = new AjaxResult(this.getMessage("REPORT_COMMON_E_0001"), MessageType.确认消息);
        }
*/        return result;
	}
	/**
	 * 
	 * 新增
	 * 
	 * @param dto
	 * @return
	 * @see
	 * @since 1.0
	 */
	public int insert(ReportWorkManage002Dto dto) {
//		if (null != repJobMapper.selectByPrimaryKey(dto.getJobId())) {
//			return 0;
//		}
//		dto.setLoopDateValue(dto.toQuartzConfig());
//		dto.setLoopDateDetail(dto.toQuartzConfigDesc());
//		// 插入插入报表作业屏蔽产品，作业范围数据
//		doinsertOrUpdate(dto);
//		return repJobMapper.insert(dto);
		return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportWorkManage002/insert",dto,
    			Integer.class);
	}

	/**
	 * 
	 * 修改
	 * 
	 * @param dto
	 * @return
	 * @see
	 * @since 1.0
	 */
	public int update(ReportWorkManage002Dto dto) {
//		if (null == repJobMapper.selectByPrimaryKey(dto.getJobId())) {
//			return 0;
//		}
//		// 插入插入报表作业屏蔽产品，作业范围数据
//		doinsertOrUpdate(dto);
//		dto.setLoopDateValue(dto.toQuartzConfig());
//		dto.setLoopDateDetail(dto.toQuartzConfigDesc());
//		return repJobMapper.updateByPrimaryKey(dto);
		return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportWorkManage002/update",dto,
    			Integer.class);
	}

	/**
	 * 
	 * 删除
	 * 
	 * @param jobId
	 * @return
	 * @see
	 * @since 1.0
	 */
	public int delete(String jobId) {
//		if (StringUtil.isNotEmpty(jobId)) {
//			// 根据作业编号删除报表作业屏蔽产品
//			reportWorkManage002Mapper.deleteExcludeProInfo(jobId);
//			// 根据作业编号删除作业范围数据
//			reportWorkManage002Mapper.deleteRangeInfo(jobId);
//			return repJobMapper.deleteByPrimaryKey(jobId);
//		}
//		return 0;
		return restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportWorkManage002/delete?jobId="+jobId,
    			Integer.class);
	}

	/**
	 * 根据作业编号查询报表作业范围
	 * 
	 * @param jobId
	 * @return
	 */
	public List<ReportWorkManage004Dto> findRulePage(String jobId) {
//		return reportWorkManage002Mapper.findRulePage(jobId);
		List<ReportWorkManage004Dto> dto = restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportWorkManage002/findRulePage?jobId="+jobId,
				ReportWorkManage004Dto.class);
		return dto;
	}

	/**
	 * 根据作业编号查询报表作业屏蔽产品
	 * 
	 * @param jobId
	 * @return
	 */
	public List<ReportWorkManage003Dto> findProdPage(String jobId) {
//		return reportWorkManage002Mapper.findProdPage(jobId);
		return restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportWorkManage002/findProdPage?jobId="+jobId,
    			ReportWorkManage003Dto.class);
		
	}

	/**
	 * 获取作业规则表数据
	 * 
	 * @return 产品信息
	 */
	public List<SelectOptionDto> getRepRule() {
//		return reportWorkManage002Mapper.getRepRule();
		
		
		return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportWorkManage002/getRepRule",
    			null,SelectOptionDto.class);
	}
}
