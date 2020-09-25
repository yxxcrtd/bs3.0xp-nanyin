/*
	 * 文件名：ReportContentEnum001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.thresholdCheck.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.RepIndexPrimaryMapper;
import com.joyintech.tams.common.base.dao.RepJobRangeMapper;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.information.config.reportcontentenum.dto.ReportContentEnum001Dto;
import com.joyintech.tams.disclosure.information.config.reportcontentenum.dto.ReportContentEnum003Dto;
import com.joyintech.tams.disclosure.information.config.thresholdCheck.dao.ThresholdCheck001001Mapper;
import com.joyintech.tams.disclosure.information.config.thresholdCheck.dto.ThresholdCheck001Dto;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 自定义指标报表service 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author lishicheng
 * @version 1.0 2018年11月6日
 * @see ThresholdCheck001Service
 * @since 1.0
 */
@Service
public class ThresholdCheck001Service extends CommonExportService<ThresholdCheck001Dto> {

	@Autowired
	private ThresholdCheck001001Mapper thresholdCheck001001Mapper;
	/**
	 */
	@Autowired
	private RepJobRangeMapper repJobRangeMapper;
	/**
	 */
	@Autowired
	private RepIndexPrimaryMapper repIndexPrimaryMapper;


	/**
	 * 指标库查询service <一句话功能简述> <功能详细描述>
	 * 
	 * @param pageParam
	 * @return
	 */
	public Page<ThresholdCheck001Dto> findIndexByPage(Map<String, Object> pageParam) throws Exception {
		 String finprodId = (String)pageParam.get("finprodId");
		  pageParam.put("finprodId", finprodId);
		 String[] finprodIdArr = null;
	        if(StringUtil.isNotEmpty(finprodId)){
	            finprodIdArr = finprodId.split(",");
	        }
	        pageParam.put("finprodIdArr", finprodIdArr);
		 
		 
		Object  reportName=pageParam.get("reportName");
		Integer   sum=thresholdCheck001001Mapper.selectCount(pageParam);
		ThresholdCheck001Dto  dto=new ThresholdCheck001Dto();
		dto.setReportName("全部数据");
		dto.setCount(sum);
		dto.setProdArray(finprodId);
		if(reportName!=null){
			//报表维度为报表名称时
			if(((String)reportName).equals("01")){
				return super.queryForPage(() -> thresholdCheck001001Mapper.selectIndex(pageParam));
			//报表维度为阈值指标时
			}else if(((String)reportName).equals("02")){
				return super.queryForPage(() -> thresholdCheck001001Mapper.selectIndexByIndex(pageParam).add(dto));
			}
		}
			return super.queryForPage(() -> thresholdCheck001001Mapper.selectIndex(pageParam).add(dto));
		}
	
	

	/**
	 * 通过id查询 <一句话功能简述> <功能详细描述>
	 * 
	 * @param params
	 * @return
	 */
	public List<ReportContentEnum001Dto> findbyIndexId(String id) {
		//return reportContentEnum001Mapper.selectByIndexId(id);
		return null;
	}

	/**
	 * 插入插入报表作业屏蔽产品，作业范围数据
	 * 
	 * @param dto
	 */
	public void doinsertOrUpdate(ReportContentEnum001Dto dto) {
		// 根据作业编号删除作业范围数据
		//reportContentEnum001Mapper.deleteRangeInfo(dto.getId());
		// 插入作业范围数据
		List<ReportContentEnum003Dto> settedRuleList = dto.getSettedRule();
		if (!StringUtil.isEmptyList(settedRuleList)) {
			for (ReportContentEnum003Dto manage3Dto : settedRuleList) {
				manage3Dto.setJobId(dto.getId());
				manage3Dto.setFlag(DisclosureDictCodeList.规则类型_指标值.getValue());
				manage3Dto.setId(CommonUtil.getSequenceIndexData(DataIndexSequenceType.基础数据));
				repJobRangeMapper.insertSelective(manage3Dto);
			}
		}
	}

	/**
	 * 新建 <一句话功能简述> <功能详细描述>
	 * 
	 * @param dto
	 */
	public void insert(ReportContentEnum001Dto dto) {
		this.doinsertOrUpdate(dto);
		repIndexPrimaryMapper.insertSelective(dto);
	}

	/**
	 * 更新 <一句话功能简述> <功能详细描述>
	 * 
	 * @param dto
	 */
	public void updateByPrimaryKeySelective(ReportContentEnum001Dto dto) {
		this.doinsertOrUpdate(dto);
		repIndexPrimaryMapper.updateByPrimaryKeySelective(dto);

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
		return null;
	}

	/**
	 * 右侧删除 <一句话功能简述> <功能详细描述>
	 * 
	 * @param id
	 * @return
	 */
	public int deleteByPrimaryKey(String id) {
//		if (StringUtil.isNotEmpty(id)) {
//			// 根据作业编号从REP_JOB_RANGE删除作业范围数据
//			List<String> idList=reportContentEnum001Mapper.getId(id);
//			for(String jobId:idList){
//				reportContentEnum001Mapper.deleteRangeInfo(jobId);
//			}
//			//根据指标id从REP_INDEX_SUB删除数据
//			reportContentEnum001Mapper.deleteByPriId(id);
//			//从REP_INDEX_PRIMARY删除数据
//			return repIndexPrimaryMapper.deleteByPrimaryKey(id);
//		}
		return 0;
	}
	
	public List<ReportContentEnum001Dto> findbyIndexName(String name) {
//		return reportContentEnum001Mapper.findbyIndexName(name);
		return null;
	}
	
	/**
     * 右侧栏查询
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @throws Exception
     */
	public Page<ThresholdCheck001Dto> findIndexDetailByPage(Map<String, Object> pageParam) throws Exception {
		 String prodArray = (String)pageParam.get("prodArray");
		 String[] finprodIdArr = null;
	        if(StringUtil.isNotEmpty(prodArray)){
	            finprodIdArr = prodArray.split(",");
	        }
	        pageParam.put("finprodIdArr", finprodIdArr);
		//报表维度flag
		Object  flag=pageParam.get("flag");
		if(flag!=null){
			//左侧页面展示报表维度的时候
			if(((String)flag).equals("01")){
				return super.queryForPage(()-> thresholdCheck001001Mapper.selectIndexDetail(pageParam));
			//左侧页面展示阈值维度的时候
			}else if(((String)flag).equals("02")){
				return super.queryForPage(()-> thresholdCheck001001Mapper.selectIndexDetailByIndex(pageParam));
			}
		}
		
		return super.queryForPage(()-> thresholdCheck001001Mapper.selectIndexDetail(pageParam));
	}
	
	/**
     * 导出查询
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @throws Exception
     */
	public List<ThresholdCheck001Dto> findIndexDetail(Map<String, Object> pageParam) throws Exception {
		return  thresholdCheck001001Mapper.selectIndexDetail(pageParam);
	}

}
