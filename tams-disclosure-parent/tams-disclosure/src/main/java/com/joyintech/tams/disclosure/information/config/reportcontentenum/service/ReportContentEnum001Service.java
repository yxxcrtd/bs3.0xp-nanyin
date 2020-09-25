/*
	 * 文件名：ReportContentEnum001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.reportcontentenum.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.config.reportcontentenum.dto.ReportContentEnum001Dto;

/**
 * 自定义指标报表service 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author lishicheng
 * @version 1.0 2018年11月6日
 * @see ReportContentEnum001Service
 * @since 1.0
 */
@Service
public class ReportContentEnum001Service extends CommonExportService<ReportContentEnum001Dto> {

//	@Autowired
//	private ReportContentEnum001Mapper reportContentEnum001Mapper;
//	/**
//	 */
//	@Autowired
//	private RepJobRangeMapper repJobRangeMapper;
//	/**
//	 */
//	@Autowired
//	private RepIndexPrimaryMapper repIndexPrimaryMapper;
//	@Autowired
//	private RepIndexSubMapper repIndexSubMapper;

	/**
	 * 指标库查询service <一句话功能简述> <功能详细描述>
	 * 
	 * @param pageParam
	 * @return
	 */
	public Page<ReportContentEnum001Dto> findIndexByPage(Map<String, Object> pageParam) throws Exception {
		
		
		return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportContentEnum001/findIndexId",
                pageParam, ReportContentEnum001Dto.class);
		
//		return super.queryForPage(() -> reportContentEnum001Mapper.selectIndex(pageParam));
	}

	/**
	 * 通过id查询 <一句话功能简述> <功能详细描述>
	 * 
	 * @param params
	 * @return
	 */
	public List<ReportContentEnum001Dto> findbyIndexId(String id) {
//		return reportContentEnum001Mapper.selectByIndexId(id);
		
		return restTemplate.getForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportContentEnum002/findbyIndexId?id="+id,
                 ReportContentEnum001Dto.class);
//		
	}

	/**
	 * 插入插入报表作业屏蔽产品，作业范围数据
	 * 
	 * @param dto
	 */
//	public void doinsertOrUpdate(ReportContentEnum001Dto dto) {
//		// 根据作业编号删除作业范围数据
//		reportContentEnum001Mapper.deleteRangeInfo(dto.getId());
//		// 插入作业范围数据
//		List<ReportContentEnum003Dto> settedRuleList = dto.getSettedRule();
//		if (!StringUtil.isEmptyList(settedRuleList)) {
//			for (ReportContentEnum003Dto manage3Dto : settedRuleList) {
//				manage3Dto.setJobId(dto.getId());
//				manage3Dto.setFlag(DisclosureDictCodeList.规则类型_指标值.getValue());
//				manage3Dto.setId(CommonUtil.getSequenceIndexData(DataIndexSequenceType.基础数据));
//				repJobRangeMapper.insertSelective(manage3Dto);
//			}
//		}
//	}

	/**
	 * 新建 <一句话功能简述> <功能详细描述>
	 * 
	 * @param dto
	 */
	public void insert(ReportContentEnum001Dto dto) {
//		this.doinsertOrUpdate(dto);
//		repIndexPrimaryMapper.insertSelective(dto);
		restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportContentEnum002/insert",
				dto,Integer.class);
	}

	/**
	 * 更新 <一句话功能简述> <功能详细描述>
	 * 
	 * @param dto
	 */
	public void updateByPrimaryKeySelective(ReportContentEnum001Dto dto) {
//		this.doinsertOrUpdate(dto);
//		repIndexPrimaryMapper.updateByPrimaryKeySelective(dto);
		restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportContentEnum002/updateByPrimaryKeySelective",
				dto,Integer.class);
	}

	/**
	 * 左侧通过id获取修改时的值 <一句话功能简述> <功能详细描述>
	 * 
	 * @param id
	 * @return
	 */
	public ReportContentEnum001Dto findById(String id) {
		// TODO Auto-generated method stub
//		return reportContentEnum001Mapper.selectById(id);
		return restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportContentEnum002/findById?id="+id,
				ReportContentEnum001Dto.class);
	}

	/**
	 * 右侧删除 <一句话功能简述> <功能详细描述>
	 * 
	 * @param id
	 * @return
	 */
	public int deleteByPrimaryKey(String id) {
		if (StringUtil.isNotEmpty(id)) {
//			// 根据作业编号从REP_JOB_RANGE删除作业范围数据
//			List<String> idList=reportContentEnum001Mapper.getId(id);
//			for(String jobId:idList){
//				reportContentEnum001Mapper.deleteRangeInfo(jobId);
//			}
//			//根据指标id从REP_INDEX_SUB删除数据
//			reportContentEnum001Mapper.deleteByPriId(id);
//			//从REP_INDEX_PRIMARY删除数据
//			return repIndexPrimaryMapper.deleteByPrimaryKey(id);
			return restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportContentEnum001/deleteIndex?id="+id, Integer.class);
		}
		return 0;
	}
	
	public List<ReportContentEnum001Dto> findbyIndexName(String name) {
//		return reportContentEnum001Mapper.findbyIndexName(name);
		
		return restTemplate.getForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportContentEnum002/findbyIndexName?name="+name,
                 ReportContentEnum001Dto.class);
	}

}
