package com.joyintech.tams.invest.riskredis.quota.rskevent.service;


import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RskEvent;
import com.joyintech.tams.common.base.entity.RskEventItemRule;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.redisinit.dto.ImplCond;
import com.joyintech.tams.invest.common.redisinit.service.RiskRedisInitService;
import com.joyintech.tams.invest.riskredis.quota.rskevent.dto.RskEvent002Dto;
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
public class RskEvent002Service extends CommonExportService<RskEvent002Dto> implements BaseProcessService<RskEvent002Dto> {
	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(RskEvent002Service.class);

    /**
     * redis数据初始化
     */
    @Autowired
    private RiskRedisInitService<ImplCond> redisRriter;
    @Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	@Override
	public void doComplete(RskEvent002Dto entity, OperationType type) {
		String action = StringUtil.EMPTY;
		setEntity(entity,type);
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        else {
            action = "update";
        }
        restTemplate.postForObject(
                ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskEvent/" + action, entity,
                Integer.class);
            String pageRedisFlag = CacheUtil.getSysParam("pageRedisFlag");
            if (CommonUtil.isType(pageRedisFlag, BussinessDictCodeListRds.是否_是)) {
    			threadPoolTaskExecutor.execute(()->{
    				try {
    					logger.info("===========风控事项特征值进入redis开始=============");
    					Thread.sleep(6000);
    					redisRriter.dealRiskEvent();
    					logger.info("===========风控事项特征值进入redis结束=============");
    				} catch (InterruptedException e) {
    					logger.error("===========风控事项特征值进入redis异常=============",e);
    				}
    			});
            }
    	}
	
	private void setEntity(RskEvent002Dto entity,OperationType type) {
		if(!StringUtil.isEmptyList(entity.getParamGroup())){
			if(OperationType.NEW.equals(type)||OperationType.MODIFY.equals(type)){
				for(List<RskEventItemRule> list:entity.getParamGroup()){
					String groupId = IDGenerator.generate();
					for(RskEventItemRule e:list){
						e.setGroupId(groupId);
					}
				}
			}
			
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
     * 根据风险事件代码查询风险事件
     * @param eventName
     * @return
     */
    public RskEvent002Dto selectEventDetailInfoByCode(String eventCode) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("eventCode", eventCode);
    	ResponseEntity<RskEvent002Dto> result = restTemplate.postForEntity(ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskEvent/selectEventDetailInfoByCode",
                map, RskEvent002Dto.class);
    	RskEvent002Dto item = result.getBody();
				return item;
    }
	
}
