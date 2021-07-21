package com.joyintech.tams.invest.riskredis.quota.portfolioindicator.service;

import java.util.Map;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.redisinit.dto.ImplCond;
import com.joyintech.tams.invest.common.redisinit.service.RiskRedisInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.riskredis.quota.portfolioindicator.dto.RskPortfolioPackage003Dto;
import com.joyintech.tams.invest.riskredis.quota.portfolioindicator.dto.RskPortfolioPoolConfigDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 新增组合包信息
 * 
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see RskSupervisionPortfPoint003Service
 * @since 1.0
 */
@Service
public class RskSupervisionPortfPoint003Service extends CommonExportService<RskPortfolioPackage003Dto>
implements BaseProcessService<RskPortfolioPackage003Dto> {

	/**
	 * redis数据初始化
	 */
	@Autowired
	private RiskRedisInitService<ImplCond> redisRriter;

	/**
	 * 画面查询 <功能详细描述>
	 * @param parameters
	 * @return RskSupervisionIndicatorDto
	 * @see
	 * @since 1.0
	 */
	public Page<RskPortfolioPoolConfigDto> selectForPage(Map<String, Object> parameters) {
		return restTemplate.postForPage(ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionPortf003Point/selectForPage",
				parameters, RskPortfolioPoolConfigDto.class);
	}
    
	/**
	 * 查询组合包基本信息(编辑用)
	 * @param parameters
	 * @return RskPortfolioPackage003Dto
	 * @since 1.0
	 */
	public RskPortfolioPackage003Dto queryPortfBagInfoByBagId(String bagId) {
		return restTemplate.getForObject(ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionPortf003Point/queryPortfBagInfoByBagId?bagId="+bagId,
				 RskPortfolioPackage003Dto.class);
	}
	
	/**
     * 新建场合时查询监督指标名称是否重复
     * @param name
     */
    public int searchRepeatPortfNameByPortName(String portfBagName){
    	 return restTemplate.getForObject(
    	            ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionPortf003Point/searchRepeatPortfNameByPortName?portfBagName="+portfBagName,
    	             Integer.class);
    }
    
	/**
	 * 查询组合包是否有关联的指标
	 * @param parameters
	 * @return RskSupervisionIndicatorDto
	 * @since 1.0
	 */
	public int selectReleteIndByBagId(String bagId) {
		return restTemplate.getForObject(ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionPortf003Point/selectReleteIndByBagId?bagId="+bagId,
				 Integer.class);
	}
	/**
     * 插入操作
     * @param dto 保存参数
     * @return 结果
     * @since 1.0
     */
    public void save(RskPortfolioPackage003Dto dto) {
         restTemplate.postForObject(
                ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionPortf003Point/save",
                dto, Integer.class);
    }
    
    /**
     * 更新操作
     * @param dto 保存参数
     * @return 结果
     * @since 1.0
     */
    public void update(RskPortfolioPackage003Dto dto) {
         restTemplate.postForObject(
                ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionPortf003Point/update",
                dto, Integer.class);
    }
    
    /**
     * 删除组合包
     * @param dto 保存参数
     * @return 结果
     * @since 1.0
     */
    public void deleteBag(String bagId){
    	 restTemplate.postForObject(
    	            ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionPortf003Point/deleteBag", bagId,
    	            Integer.class);
    }

	@Override
	public void doComplete(RskPortfolioPackage003Dto entity, OperationType type) {
		 String action = StringUtil.EMPTY;
	        if (OperationType.DELETE.equals(type)) {
	        	restTemplate.postForObject(
	    	            ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionPortf003Point/deleteBag", entity.getPtlBagId(),
	    	            Integer.class);
	        }else{
	        	if (OperationType.NEW.equals(type)) {
		            action = "save";
		        }
		        else {
		            action = "update";
		        }
	        	restTemplate.postForObject(
	    	            ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionPortf003Point/" + action, entity,
	    	            Integer.class);
	        }

		String pageRedisFlag = CacheUtil.getSysParam("pageRedisFlag");
		if (CommonUtil.isType(pageRedisFlag, BussinessDictCodeListRds.是否_是)) {
			// 更新Redis
			ImplCond cond = new ImplCond();
			cond.setPortfBagIdArray(entity.getPtlBagId());
			redisRriter.dealRiskPtlIndicator(cond);
		}
	        
	}
}
