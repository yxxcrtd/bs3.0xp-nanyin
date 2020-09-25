package com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.redisinit.dto.ImplCond;
import com.joyintech.tams.invest.common.redisinit.service.RiskRedisInitService;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.dto.RskSupervisionIndicatorParam005Dto;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.dto.RskSupervisionIndicatorParamDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
public class RskSupervisionIndicatorParam005Service extends CommonExportService<RskSupervisionIndicatorParam005Dto> implements BaseProcessService<RskSupervisionIndicatorParam005Dto> {
	 /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(RskSupervisionIndicatorParam005Service.class);

    /**
     * redis数据初始化
     */
    @Autowired
    private RiskRedisInitService<ImplCond> redisRriter;
    @Override
    public void doComplete(RskSupervisionIndicatorParam005Dto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.NEW.equals(type)) {
            action = "insertCustomParam";
            restTemplate.postForObject(
                    ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionIndicatorParam/" + action, entity,
                    Integer.class);
        }else if (OperationType.MODIFY.equals(type)) {
            action = "updateCustomParam";
            restTemplate.postForObject(
                    ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionIndicatorParam/" + action, entity,
                    Integer.class);
        }else{
        	RskSupervisionIndicatorParamDto dto = new RskSupervisionIndicatorParamDto();
        	BeanUtil.copyPropertiesIgnoreNull(dto, entity);
        	dto.setParaType(BussinessDictCodeListRds.指标参数实现类型_自定义.getValue());
        	action = "delete";
        	restTemplate.postForObject(
                    ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionIndicatorParam/" + action, dto,
                    Integer.class);
        }

        String pageRedisFlag = CacheUtil.getSysParam("pageRedisFlag");
        if (CommonUtil.isType(pageRedisFlag, BussinessDictCodeListRds.是否_是)) {
            try {
                logger.info("===========指标自定义参数进入redis开始=============");
                Thread.sleep(2000);
                // 更新Redis
                ImplCond cond = new ImplCond();
                cond.setParamCodeArray(entity.getParaCode());
                cond.setDel(StringUtil.equals(action, "delete"));
                cond.setIsUpdParVal(false);
                cond.setPlateDate(DateUtil.parse(DateUtil.getSysDate()));
                redisRriter.dealRiskIndParam(cond);
                if (OperationType.NEW.equals(type)) {
                    // 更新Redis
                    ImplCond cond2 = new ImplCond();
                    cond2.setParamCodeArray(entity.getParaCode());
                    cond2.setDel(StringUtil.equals(action, "delete"));
                    cond2.setIsUpdParVal(true);
                    cond2.setPlateDate(DateUtil.parse(DateUtil.getSysDate()));
                    redisRriter.dealRiskIndParam(cond2);
                }
                logger.info("===========指标自定义参数进入redis结束=============");
            } catch (InterruptedException e) {
                logger.error("===========指标自定义参数进入redis异常=============",e);
            }
        }
    }

    public List<RskSupervisionIndicatorParam005Dto> selectCustomParam(String paraCode) {
        return restTemplate.getForList(ServiceApplication.RISK_RDS_SERVICE_NAME
                                             + "/RskSupervisionIndicatorParam/selectCustomParam?paraCode="
                                             + paraCode,
            RskSupervisionIndicatorParam005Dto.class);
    }

	public String getModelValue(String modelCode) {
		return restTemplate.getForObject(ServiceApplication.RISK_RDS_SERVICE_NAME
                + "/RskSupervisionIndicatorParam/getModelValue?modelCode="
                + modelCode,
                String.class);
	}

	
	
}
