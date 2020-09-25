package com.joyintech.tams.invest.riskredis.quota.rskevent.service;


import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RskEvent;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.redisinit.dto.ImplCond;
import com.joyintech.tams.invest.common.redisinit.service.RiskRedisInitService;
import com.joyintech.tams.invest.riskredis.quota.rskevent.dto.RskEvent003Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RskEvent003Service extends CommonExportService<RskEvent003Dto> implements BaseProcessService<RskEvent003Dto> {

	/**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(RskEvent003Service.class);

	/**
     * redis数据初始化
     */
    @Autowired
    private RiskRedisInitService<ImplCond> redisRriter;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;


	@Override
	public void doComplete(RskEvent003Dto entity, OperationType type) {
		String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            action = "deleteRelPara";
        }
        else if (OperationType.NEW.equals(type)) {
            action = "insertRelPara";
        }
        else {
            action = "updateRelPara";
        }
        restTemplate.postForObject(
            ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskEvent/" + action, entity,
            Integer.class);
        String pageRedisFlag = CacheUtil.getSysParam("pageRedisFlag");
        if (CommonUtil.isType(pageRedisFlag, BussinessDictCodeListRds.是否_是)) {
            threadPoolTaskExecutor.execute(()->{
                try {
                    logger.info("===========风控事项关联参数进入redis开始=============");
                    Thread.sleep(6000);
                    // 更新Redis
                    redisRriter.dealRiskEvent();
                    logger.info("===========风控事项关联参数进入redis结束=============");
                } catch (InterruptedException e) {
                    logger.info("===========风控事项关联参数进入redis异常=============",e);
                }
            });
        }
	}
	
	/**
     * 根据风险事件代码查询风险事件
     * @param eventName
     * @return
     */
    public RskEvent selectByEventCode(String eventCode) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("eventCode", eventCode);
    	ResponseEntity<RskEvent> result = restTemplate.postForEntity(ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskEvent/selectByEventCode",
                map, RskEvent.class);
    	RskEvent item = result.getBody();
		return item;
    }
    
    /**
     * 根据风险事件代码查询风险事件关联指标参数
     * @param eventCode
     * @return
     */
    public RskEvent003Dto selectEventRelParaByCode(String eventCode) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("eventCode", eventCode);
    	ResponseEntity<RskEvent003Dto> result = restTemplate.postForEntity(ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskEvent/selectEventRelParaByCode",
                map, RskEvent003Dto.class);
    	RskEvent003Dto item = result.getBody();
				return item;
    }
    /**
     * 指标名称的联想下拉框 <功能详细描述>
     * @param param
     * @return SelectOptionDto
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getIndicatorParamList() {
        return restTemplate.getForList(
                ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionIndicator/getIndicatorParamList?param=''",
                SelectOptionDto.class);
    }

}
