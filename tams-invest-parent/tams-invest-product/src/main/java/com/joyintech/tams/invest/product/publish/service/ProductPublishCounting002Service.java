
package com.joyintech.tams.invest.product.publish.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.common.base.dao.PrdIssuStrategyDetailMapper;
import com.joyintech.tams.common.base.dao.PrdIssueplanAssetDatailMapper;
import com.joyintech.tams.common.base.dao.PrdIssueplanInfoMapper;
import com.joyintech.tams.common.base.dao.PrdIssueplanStrategyMapper;
import com.joyintech.tams.common.base.entity.PrdIssuStrategyDetail;
import com.joyintech.tams.common.base.entity.PrdIssueplanAssetDatail;
import com.joyintech.tams.common.base.entity.PrdIssueplanInfo;
import com.joyintech.tams.common.base.entity.PrdIssueplanStrategy;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.publish.dto.ProductPublishCounting001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * @version 1.0 2018年12月25日
 * @see ProductPublishCounting002Service
 * @since 1.0
 */
@Service
public class ProductPublishCounting002Service extends BaseService<ProductPublishCounting001Dto> 
				implements BaseProcessService<ProductPublishCounting001Dto>{
	
	@Autowired
	private	PrdIssueplanInfoMapper prdIssueplanInfoMapper;
	
	@Autowired
	private	PrdIssueplanStrategyMapper prdIssueplanStrategyMapper;
	
	@Autowired
	private	PrdIssuStrategyDetailMapper prdIssueplanStrategyDetailMapper;
	
	@Autowired
	private	PrdIssueplanAssetDatailMapper prdIssueplanAssetDatailMapper;
	
	@Override
	public void doComplete(ProductPublishCounting001Dto entity, OperationType type) {
		if (type == OperationType.NEW) {
			//数据新建
            doCompleteInsert(entity);
        }
		else if(type == OperationType.MODIFY) {
			//数据修改
			doCompleteModify(entity);
		}
		else if(type == OperationType.DELETE) {
			//数据删除
			doCompleteDelete(entity);
		}
	}
	/**
	 * 产品发行规划新建
	 */
	private void doCompleteInsert(ProductPublishCounting001Dto dto) {
		//产品发行规划信息
		PrdIssueplanInfo prdIssueplanInfo = getPrdIssueplanInfo(dto);
		prdIssueplanInfoMapper.insertSelective(prdIssueplanInfo);
		//产品发行规划策略
		List<PrdIssueplanStrategy> prdIssueplanStrategyList = getPrdIssueplanStrategyList(dto);
		if(prdIssueplanStrategyList.size()>0) {
			prdIssueplanStrategyList.forEach(prdIssueplanStrategy -> {
				prdIssueplanStrategyMapper.insertSelective(prdIssueplanStrategy);
			});
		}
		//产品发行规划策略明细
		List<PrdIssuStrategyDetail> prdIssueplanStrategyDetailList = getPrdIssueplanStrategyDetailList(dto);
		if(prdIssueplanStrategyDetailList.size()>0) {
			prdIssueplanStrategyDetailList.forEach(prdIssueplanStrategyDetail -> {
				prdIssueplanStrategyDetailMapper.insertSelective(prdIssueplanStrategyDetail);
			});
		}
		//产品发行规划资产规划信息
		List<PrdIssueplanAssetDatail> prdIssuePlanAssetList = getPrdIssueplanAssetDetailList(dto);
		if(prdIssuePlanAssetList.size()>0) {
			prdIssuePlanAssetList.forEach(prdIssuePlanAsset -> {
				prdIssueplanAssetDatailMapper.insertSelective(prdIssuePlanAsset);
			});
		}
	}
	/**
	 * 产品发行规划修改
	 */
	private void doCompleteDelete(ProductPublishCounting001Dto dto) {
		//产品发行规划信息
		prdIssueplanInfoMapper.deleteByPrimaryKey(dto.getPrdIssueplanCode());
		//产品发行规划策略
		restTemplate.postForObject(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductPublishCounting/deleteIssueplanStrategyByCode",
					dto.getPrdIssueplanCode(),Integer.class
		);
		//产品发行规划策略明细
		restTemplate.postForObject(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductPublishCounting/deleteStrategyDetailByCode",
					dto.getPrdIssueplanCode(),Integer.class
		);
		//产品发行规划资产规划信息
		restTemplate.postForObject(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductPublishCounting/deleteIssueplanAssetByCode",
					dto.getPrdIssueplanCode(),Integer.class
		);
	}
	/**
	 * 产品发行规划修改
	 */
	private void doCompleteModify(ProductPublishCounting001Dto dto) {
		//产品发行规划信息
		PrdIssueplanInfo prdIssueplanInfo = getPrdIssueplanInfo(dto);
		prdIssueplanInfoMapper.updateByPrimaryKey(prdIssueplanInfo);
		//产品发行规划策略
		//删除
		restTemplate.postForObject(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductPublishCounting/deleteIssueplanStrategyByCode",
					dto.getPrdIssueplanCode(),Integer.class
		);
		//新增
		List<PrdIssueplanStrategy> prdIssueplanStrategyList = getPrdIssueplanStrategyList(dto);
		if(prdIssueplanStrategyList.size()>0) {
			prdIssueplanStrategyList.forEach(prdIssueplanStrategy -> {
				prdIssueplanStrategyMapper.insertSelective(prdIssueplanStrategy);
			});
		}
		//产品发行规划策略明细
		//删除
		restTemplate.postForObject(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductPublishCounting/deleteStrategyDetailByCode",
					dto.getPrdIssueplanCode(),Integer.class
		);
		List<PrdIssuStrategyDetail> prdIssueplanStrategyDetailList = getPrdIssueplanStrategyDetailList(dto);
		if(prdIssueplanStrategyDetailList.size()>0) {
			prdIssueplanStrategyDetailList.forEach(prdIssueplanStrategyDetail -> {
				prdIssueplanStrategyDetailMapper.insertSelective(prdIssueplanStrategyDetail);
			});
		}
		//产品发行规划资产规划信息
		//删除
		restTemplate.postForObject(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductPublishCounting/deleteIssueplanAssetByCode",
					dto.getPrdIssueplanCode(),Integer.class
		);
		List<PrdIssueplanAssetDatail> prdIssuePlanAssetList = getPrdIssueplanAssetDetailList(dto);
		if(prdIssuePlanAssetList.size()>0) {
			prdIssuePlanAssetList.forEach(prdIssuePlanAsset -> {
				prdIssueplanAssetDatailMapper.insertSelective(prdIssuePlanAsset);
			});
		}
	}
	/**
	 * 获取产品发行规划信息
	 */
	private PrdIssueplanInfo getPrdIssueplanInfo(ProductPublishCounting001Dto dto) {
		//产品发行规划信息
		PrdIssueplanInfo prdIssueplanInfo = new PrdIssueplanInfo();
		prdIssueplanInfo.setPrdIssueplanCode(dto.getPrdIssueplanCode());
		prdIssueplanInfo.setPrdIssueplanName(dto.getPrdIssueplanName());
		prdIssueplanInfo.setVdate(dto.getVdate());
		prdIssueplanInfo.setEdate(dto.getEdate());
		prdIssueplanInfo.setRemark(dto.getRemark());
		return prdIssueplanInfo;
	}
	/**
	 * 获取产品发行规划策略
	 */
	private List<PrdIssueplanStrategy> getPrdIssueplanStrategyList(ProductPublishCounting001Dto dto) {
		List<PrdIssueplanStrategy> prdIssueplanStrategyList = new ArrayList<>();
		for(ProductPublishCounting001Dto strategyDto : dto.getPrdIssuePlanStrategyList()) {
			PrdIssueplanStrategy prdIssueplanStrategy = new PrdIssueplanStrategy();
			prdIssueplanStrategy.setPrdIssueplanCode(dto.getPrdIssueplanCode());
			prdIssueplanStrategy.setPrdPlanSerisId(strategyDto.getPrdPlanSerisName());
			prdIssueplanStrategy.setStrategyId(strategyDto.getStrategyId());
			prdIssueplanStrategy.setRemainChangeAmt(strategyDto.getPlanChangeAmt()
					.multiply(new BigDecimal(10000)));
			prdIssueplanStrategy.setCofingScale(strategyDto.getConfigScale()
					.multiply(new BigDecimal(10000)));
			prdIssueplanStrategy.setCountOrder(strategyDto.getOrder());
			prdIssueplanStrategyList.add(prdIssueplanStrategy);
		}
		return prdIssueplanStrategyList;
	}
	/**
	 * 获取产品发行规划策略
	 */
	private List<PrdIssuStrategyDetail> getPrdIssueplanStrategyDetailList(ProductPublishCounting001Dto dto) {
		List<PrdIssuStrategyDetail> prdIssueplanStrategyDetailList = new ArrayList<>();
		for(ProductPublishCounting001Dto strategyDto : dto.getPrdIssuePlanStrategyDetailList()) {
			PrdIssuStrategyDetail prdIssueplanStrategyDetail = new PrdIssuStrategyDetail();
			prdIssueplanStrategyDetail.setPrdIssueplanCode(dto.getPrdIssueplanCode());
			prdIssueplanStrategyDetail.setPrdPlanSerisId(strategyDto.getPrdPlanSerisId());
			prdIssueplanStrategyDetail.setStrategyId(strategyDto.getStrategyId());
			prdIssueplanStrategyDetail.setAssetType(strategyDto.getAssetType());
			prdIssueplanStrategyDetail.setCofingScale(strategyDto.getConfigScale()
					.multiply(new BigDecimal(10000)));
			prdIssueplanStrategyDetailList.add(prdIssueplanStrategyDetail);
		}
		return prdIssueplanStrategyDetailList;
	}
	/**
	 * 获取产品发行规划资产规划信息
	 */
	private List<PrdIssueplanAssetDatail> getPrdIssueplanAssetDetailList(ProductPublishCounting001Dto dto) {
		List<PrdIssueplanAssetDatail> prdIssueplanAssetDetailList = new ArrayList<>();
		for(ProductPublishCounting001Dto assetDto : dto.getPrdIssuePlanAssetList()) {
			PrdIssueplanAssetDatail prdIssueplanAssetDatail = new PrdIssueplanAssetDatail();
			prdIssueplanAssetDatail.setPrdIssueplanCode(dto.getPrdIssueplanCode());
			prdIssueplanAssetDatail.setAssetType(assetDto.getAssetType());
			prdIssueplanAssetDatail.setPlanTargetAmt(assetDto.getPlanTargetAmt()
					.multiply(new BigDecimal(10000)));
			prdIssueplanAssetDatail.setRemainChangeAmt(assetDto.getPlanChangeAmt()
					.multiply(new BigDecimal(10000)));
			prdIssueplanAssetDetailList.add(prdIssueplanAssetDatail);
		}
		return prdIssueplanAssetDetailList;
	}
	/**
	 * 查询资产大类列表
	 */
	public List<ProductPublishCounting001Dto> getAssetPlanList(Map<String, Object> map) {
		return restTemplate.postForList(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductPublishCounting/getAssetPlanList",
					map,ProductPublishCounting001Dto.class
		);
	}

	/**
	 * 发行试算
	 */
	public List<ProductPublishCounting001Dto> getPublishCountingList(ProductPublishCounting001Dto dto) {
		List<ProductPublishCounting001Dto> list = dto.getPrdIssuePlanStrategyList();
		//产品发行配置明细list
		List<ProductPublishCounting001Dto> prdIssuePlanStrategyDetailList = new ArrayList<ProductPublishCounting001Dto>();
		//设置配置明细list
		for(ProductPublishCounting001Dto planStrategy : list) {
			//查询配置的策略明细
//			if(StringUtil.isNotEmpty(planStrategy.getStrategyId())){
//
//			}
			List<ProductPublishCounting001Dto> invStrategyDetailList = restTemplate.postForList(
						ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductPublishCounting/getInvStrategyDetailList",
							planStrategy.getStrategyId(),ProductPublishCounting001Dto.class
					);
			for(ProductPublishCounting001Dto planStrategyDetail : invStrategyDetailList) {
				ProductPublishCounting001Dto planStrategyDetailDto = new ProductPublishCounting001Dto();
				planStrategyDetailDto.setPrdPlanSerisId(planStrategy.getPrdPlanSerisName()); 	//产品规划系列编号
				planStrategyDetailDto.setStrategyId(planStrategy.getStrategyId());		//策略
				planStrategyDetailDto.setAssetType(planStrategyDetail.getAssetType());		//资产大类
				planStrategyDetailDto.setRatio(planStrategyDetail.getRatio());		//配置比例
				planStrategyDetailDto.setConfigScale(planStrategy.getConfigScale());
				planStrategyDetailDto.setConfigScale1(planStrategy.getConfigScale().multiply(planStrategyDetail.getRatio()));		//配置规模
				prdIssuePlanStrategyDetailList.add(planStrategyDetailDto);
			}
		}
		return prdIssuePlanStrategyDetailList;
	}
	
	/**
	 * 发行规划信息查询
	 */
	public ProductPublishCounting001Dto getPrdIssuePlanInfoByCode(String code) {
		return restTemplate.postForObject(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductPublishCounting/getPrdIssuePlanInfoByCode",
					code,ProductPublishCounting001Dto.class
		);
	}
	
	/**
	 * 发行规划策略查询
	 */
	public List<ProductPublishCounting001Dto> getPrdPlanStrategyListByCode(String code) {
		return restTemplate.postForList(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductPublishCounting/getPrdPlanStrategyListByCode",
					code,ProductPublishCounting001Dto.class
		);
	}
	
	/**
	 * 发行资产大类查询
	 */
	public List<ProductPublishCounting001Dto> getPrdPlanAssetListByCode(String code) {
		return restTemplate.postForList(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductPublishCounting/getPrdPlanAssetListByCode",
					code,ProductPublishCounting001Dto.class
		);
	}
}
