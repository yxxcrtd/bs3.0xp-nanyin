package com.joyintech.tams.model.impairment.batch.common.searchinfo.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.model.impairment.batch.common.calculation.ImpairmentCalculationCommonService;
import com.joyintech.tams.model.impairment.batch.common.searchinfo.dao.ImpairmentViewCommonMapper;
import com.joyintech.tams.model.impairment.batch.common.searchinfo.dto.ViewRatingInfoDto;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep1ReaderDto;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ModStandardGradeMapping;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchLogResult;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 减值模块共通方法
 */
@Service
public class ImpairmentCommonService extends BaseService{
	
	private static final Logger logger = LoggerFactory.getLogger(ImpairmentCommonService.class);
	
	@Autowired
	private ImpairmentViewCommonMapper impairmentViewCommonMapper;

	@Autowired
	private ImpairmentCalculationCommonService impairmentCalculationCommonService;

	// 日志key
	private final String LOG_KEY = "logkey";

	/**
	 * 获取资产交易列表
	 * @param params
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ImpairmentDataLoadStep1ReaderDto> selectAssetTradeList(Map<String, Object> params){
		return impairmentViewCommonMapper.selectAssetTradeList(params);
	}

	/**
	 * 获取评级信息
	 * @param params
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ViewRatingInfoDto> selectViewRatingInfo(Map<String, Object> params){
		return impairmentViewCommonMapper.selectViewRatingInfo(params);
	}

	/**
	 * 查询小于等于查询日最新的评级结果
	 * @param list
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ViewRatingInfoDto> selectViewRatingInfoByList(List<ViewRatingInfoDto> list, Date searchDate){
		return impairmentViewCommonMapper.selectViewRatingInfoByList(list, searchDate);
	}

	/**
	 * 获取资产信息列表
	 * @param params 查询参数
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ImpairmentDataLoadStep1ReaderDto> selectAssetInfo(Map<String, Object> params) {
		return impairmentViewCommonMapper.selectAssetInfo(params);
	}

	/**
	 * 查询当日资产信息
	 * 无历史持仓，当日买入
	 * @param params  查询参数
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ImpairmentDataLoadStep1ReaderDto> selectTdyAssetInfo(Map<String, Object> params){
		return impairmentViewCommonMapper.selectTdyAssetInfo(params);
	}

	/**
	 * 获取资产信息列表
	 * @param params
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Date queryFirstBuyDate(Map<String, Object> params){
		return impairmentViewCommonMapper.queryFirstBuyDate(params);
	}

	/**
	 * 获取评级类型为最新时，对应的评级机构信息Map
	 * @param list
	 * @return
	 */
	public Map<String,ViewRatingInfoDto> getRatingInfoMap(List<ViewRatingInfoDto> list){
		Map<String, ViewRatingInfoDto> mapRating = new HashMap<String, ViewRatingInfoDto>();
		//根据外部评级结果列表，查询标准评级映射，将外部评级映射成模型使用的评级
		if (list != null && list.size() > 0) {
			// 查询所有的评级映射关系
			List<ModStandardGradeMapping> listMapping = impairmentViewCommonMapper.selectViewRatingInfoMapping();
			if(CollectionUtils.isEmpty(listMapping)){
				return mapRating;
			}
			// 定义 变量.映射关系Map
			Map<String, ModStandardGradeMapping> mappingMap = new HashMap<>();
			// 评级映射关系按key分组
			for (ModStandardGradeMapping mappingDto : listMapping) {
				// key = 评级机构+评级类型+外部评级结果
				String key = mappingDto.getGradeInst() + GlobalConstant.UNDER_LINE + mappingDto.getoType()
					+ GlobalConstant.UNDER_LINE + mappingDto.getGradeResult();
					mappingMap.put(key, mappingDto);
			}
			// 循环外部评级最新评级信息
			for (ViewRatingInfoDto dto : list) {
				// key = 评级机构+评级类型+评级结果
				String key = dto.getGradeorgid() + GlobalConstant.UNDER_LINE + dto.getOtype()
							 + GlobalConstant.UNDER_LINE + dto.getMstratingresult01();
				// 循环遍历映射关系map，通过评级机构+评级类型+评级结果，映射到对应内部评级
				if(mappingMap.get(key) != null){
					// 映射对应内部评级，存值
					dto.setMstratingresult01(mappingMap.get(key).getGradeStandardResult());
				}
				// 外部评级机构对应评级信息分组：评级主体_评级类型_评级机构：内部评级结果
				mapRating.put(dto.getOcode() + GlobalConstant.UNDER_LINE + dto.getOtype() + GlobalConstant.UNDER_LINE + dto.getGradeorgid(), dto);
			}
		}
		return mapRating;
	}

	/**
	 * 获取评级类型为最新时，对应的评级机构信息Map
	 * @param list
	 * @return
	 */
	public Map<String,ViewRatingInfoDto> getLatestRatingInfoMap(List<ViewRatingInfoDto> list){
		// 定义 变量.最新评级机构结果Map：每个评级类型对应的最新评级机构的主体评级的较高值
		Map<String, ViewRatingInfoDto> latestMapRating = new HashMap<String, ViewRatingInfoDto>();
		// 外部评级机构对应评级信息分组：评级主体_评级类型_评级机构：内部评级结果
		for (ViewRatingInfoDto dto : list) {
			// 定义 变量.key = 评级主体+评级类型
			String key =dto.getOcode() + GlobalConstant.UNDER_LINE + dto.getOtype();
			//  若为空，则是第一次比较，直接赋值
			if(latestMapRating.get(key) == null){
				latestMapRating.put(key,dto);
			}
			else{
				// 定义 变量.最新评级信息
				ViewRatingInfoDto latestInfo = latestMapRating.get(key);
				// 先比较日期，取最新一条日期的查询日
				// 若日期<评级信息Dto.
				if(DateUtil.compareDate(latestInfo.getSearchDate(),dto.getSearchDate()) <0){
					// 最新评级信息Map覆盖更新
					latestMapRating.put(key,dto);
				}
				else{
					// 比较评级结果，取最大值
					// 若 变量.最新评级机构结果 < 评级信息Dto.评级结果
					if (impairmentCalculationCommonService.getPoorRating(
						latestInfo.getMstratingresult01(), dto.getMstratingresult01()) < 0) {
						// 最新评级信息Map覆盖更新
						latestMapRating.put(key,dto);
					}
				}
			}
		}
		return latestMapRating;
	}

	/**
	 * 批处理错误/警告日志记载
	 * @param stepExecution 异常信息
	 * @param execInfo 批处理执行任务信息
	 * @param finprodId 资产代码
	 * @param msg 消息
	 * @param logType 日志类型
	 */
	// 组装日志上下文信息
	public void editLogContextInfo(StepExecution stepExecution, SysBatchExecInfo execInfo,
								   String finprodId, String msg, String logType) {
		ModValBatchLogResult logrst = new ModValBatchLogResult();
		logrst.setFinprodId(finprodId);
		logrst.setBatchExecSeqno(execInfo.getBatchExedetailSeqNo());
		logrst.setTaskId(execInfo.getTaskId());
		logrst.setMsg("【资产减值阶段判断批处理】：资产无风险五级分类");
		logrst.setType(ModelDictCodeList.批处理执行结果_警告.getValue());
	}

}
