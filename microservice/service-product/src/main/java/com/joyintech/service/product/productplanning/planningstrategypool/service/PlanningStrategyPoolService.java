/*
 * 文件名：PlanningStrategyPoolService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年12月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.productplanning.planningstrategypool.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.productplanning.planningstrategypool.dao.PlanningStrategyPoolMapper;
import com.joyintech.service.product.productplanning.planningstrategypool.dto.PlanningStrategyPoolDetailDto;
import com.joyintech.service.product.productplanning.planningstrategypool.dto.PlanningStrategyPoolDto;
import com.joyintech.tams.common.base.dao.PtlInvStrategyDetailClassMapper;
import com.joyintech.tams.common.base.dao.PtlInvStrategyDetailMapper;
import com.joyintech.tams.common.base.dao.PtlInvStrategyMapper;
import com.joyintech.tams.common.base.entity.PtlInvStrategy;
import com.joyintech.tams.common.base.entity.PtlInvStrategyDetail;
import com.joyintech.tams.common.base.entity.PtlInvStrategyDetailClass;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;

/**
 * 规划策略池维护
 * 
 * @author niuqianyu
 * @version 1.0 2019年12月16日
 * @see PlanningStrategyPoolService
 * @since 1.0
 */
@Service
public class PlanningStrategyPoolService extends CommonExportService<PlanningStrategyPoolDto> {

	@Autowired
	private PlanningStrategyPoolMapper planningStrategyPoolMapper;

	@Autowired
	private PtlInvStrategyMapper ptlInvStrategyMapper;

	@Autowired
	private PtlInvStrategyDetailMapper ptlInvStrategyDetailMapper;

	@Autowired
	private PtlInvStrategyDetailClassMapper ptlInvStrategyDetailClassMapper;

	/**
	 * 业务编号生成类
	 */
	@Autowired(required = false)
	public BaseGetBussinessId baseGetBussinessId;

	/**
	 * 策略代码下拉框
	 * 
	 * @param pageParam
	 * @param page
	 * @param length
	 * @return
	 */
	public Page<SelectOptionDto> getStrategyId(Map<String, Object> pageParam, int page, int length) {
		pageParam.put("strategyClass", DictCodeListExt3.策略分类_规划策略.getValue());
		return super.queryForPageForSelectReturn(page, length,
				() -> planningStrategyPoolMapper.getStrategyId(pageParam));
	}

	/**
	 * 画面查询方法
	 * 
	 * @param page
	 * @param length
	 * @param pageParam
	 * @return
	 */
	public Page<PlanningStrategyPoolDto> selectForPage(int page, int length, Map<String, Object> pageParam) {
		String parentId = (String) pageParam.get("parentId");
		// 树展开子节点处理
		if (!StringUtil.equals("root", parentId) && StringUtil.isNotEmpty(parentId)) {
			List<String> treeId = StringUtil.splitToList(null, parentId);
			pageParam.put("treeSearchId", treeId.get(0));
			pageParam.put("treeSearchLevel", treeId.get(1));
			if (!StringUtil.equals("1", treeId.get(1))) {
				pageParam.put("typeId", treeId.get(2));
			}
			if (StringUtil.equals("2", treeId.get(1))) {
				pageParam.put("batchId", treeId.get(2));
			}
		}
		pageParam.put("strategyClass", DictCodeListExt3.策略分类_规划策略.getValue());
		Page<PlanningStrategyPoolDto> pages = super.queryForTree(
				() -> planningStrategyPoolMapper.selectForPage(pageParam), PlanningStrategyPoolDto::getAssetTypeId,
				PlanningStrategyPoolDto::getpAssetTypeId, PlanningStrategyPoolDto::getStrategyName,
				getFilter(pageParam), Integer.MAX_VALUE, parentId.equals("root") ? null : parentId);
		return pages;
	}

	private Predicate<PlanningStrategyPoolDto> getFilter(Map<String, Object> pageParam) {
		return (item -> {
			if (StringUtil.equals(pageParam.get("parentId"), "root")) {
				return true;
			}
			return true;
		});
	}

	/**
	 * Excel导出方法
	 * 
	 * @param page
	 * @param length
	 * @param pageParam
	 * @return
	 */
	public List<PlanningStrategyPoolDto> selectForExport(Map<String, Object> pageParam) {
		pageParam.put("strategyClass", DictCodeListExt3.策略分类_规划策略.getValue());
		return planningStrategyPoolMapper.selectForExport(pageParam);
	}

	/**
	 * 新建
	 * 
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public int insert(PlanningStrategyPoolDto entity) {
		int count = 0;
		// 投资策略
		PtlInvStrategy ptlInvStrategy = new PtlInvStrategy();
		ptlInvStrategy.setStrategyClass(DictCodeListExt3.策略分类_规划策略.getValue());
		BeanUtil.copyPropertiesIgnoreNull(ptlInvStrategy, entity);
		ptlInvStrategyMapper.insert(ptlInvStrategy);

		BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
		bussinessCodeParameter.setCommonBusinessType(DictCodeList.共通业务类型_规划策略池维护);

		List<PlanningStrategyPoolDetailDto> deteilList = entity.getPlanningStrategyPoolDetailList();

		int index = 0;
		// 策略明细列表处理
		for (PlanningStrategyPoolDetailDto deteilDto : deteilList) {
			index++;
			String batchId = "";
			// 批次号自动生成
			if (index >= 10) {
				batchId = entity.getStrategyId() + "0" + index;
			} else {
				batchId = entity.getStrategyId() + "00" + index;
			}
			// 投资策略明细
			PtlInvStrategyDetail ptlInvStrategyDetail = new PtlInvStrategyDetail();
			ptlInvStrategyDetail.setBatchId(batchId);
			ptlInvStrategyDetail.setStrategyId(ptlInvStrategy.getStrategyId());
			ptlInvStrategyDetail.setRatio(ArithmeticUtil.divide(deteilDto.getRatio(), new BigDecimal(100), 8));
			ptlInvStrategyDetailMapper.insert(ptlInvStrategyDetail);

			// 投资策略明细分类
			PtlInvStrategyDetailClass ptlInvStrategyDetailClass = new PtlInvStrategyDetailClass();
			ptlInvStrategyDetailClass.setStrategyDetId(baseGetBussinessId != null
					? baseGetBussinessId.buildBussinessCode(bussinessCodeParameter) : IDGenerator.generate());
			ptlInvStrategyDetailClass.setBatchId(batchId);
			ptlInvStrategyDetailClass.setType2(deteilDto.getType2());
			ptlInvStrategyDetailClassMapper.insert(ptlInvStrategyDetailClass);

			count++;
		}
		return count;
	}

	/**
	 * 修改
	 * 
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public int modify(PlanningStrategyPoolDto entity) {
		this.delete(entity);
		return this.insert(entity);
	}

	/**
	 * 删除
	 * 
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public int delete(PlanningStrategyPoolDto entity) {

		List<String> batchIdList = planningStrategyPoolMapper.selectBatchIdList(entity.getStrategyId());
		// 删除投资策略明细分类
		planningStrategyPoolMapper.deletePtlInvStrategyDetailClass(batchIdList);

		// 删除投资策略明细
		planningStrategyPoolMapper.deletePtlInvStrategyDetail(entity.getStrategyId());

		// 删除投资策略
		return ptlInvStrategyMapper.deleteByPrimaryKey(entity.getStrategyId());
	}

	/**
	 * 编辑查看
	 * 
	 * @param strategyId
	 * @return
	 */
	public PlanningStrategyPoolDto getPlanningStrategyPool(String strategyId) {
		PtlInvStrategy ptlInvStrategy = ptlInvStrategyMapper.selectByPrimaryKey(strategyId);
		PlanningStrategyPoolDto planningStrategyPoolDto = new PlanningStrategyPoolDto();
		BeanUtil.copyPropertiesIgnoreNull(planningStrategyPoolDto, ptlInvStrategy);
		return planningStrategyPoolDto;
	}

	/**
	 * 根据策略代码查询策略明细
	 * 
	 * @param strategyId
	 * @return
	 */
	public List<PlanningStrategyPoolDetailDto> getPlanningStrategyPoolDetail(String strategyId) {
		List<PlanningStrategyPoolDetailDto> list = planningStrategyPoolMapper.getPlanningStrategyPoolDetail(strategyId);
		return list;
	}

}
