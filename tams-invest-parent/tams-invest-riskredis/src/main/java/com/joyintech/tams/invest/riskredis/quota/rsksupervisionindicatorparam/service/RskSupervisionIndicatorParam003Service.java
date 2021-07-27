package com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.redisinit.dto.ImplCond;
import com.joyintech.tams.invest.common.redisinit.service.RiskRedisInitService;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.dto.RskSupervisionIndicatorParam003Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
public class RskSupervisionIndicatorParam003Service extends CommonExportService<RskSupervisionIndicatorParam003Dto> implements BaseProcessService<RskSupervisionIndicatorParam003Dto> {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(RskSupervisionIndicatorParam003Service.class);

    /**
     * redis数据初始化
     */
    @Autowired
    private RiskRedisInitService<ImplCond> redisRriter;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public void doComplete(RskSupervisionIndicatorParam003Dto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.NEW.equals(type)) {
            action = "insertFixRecode";
        }
        if (OperationType.MODIFY.equals(type)) {
            action = "updateFixRecode";
        }
        restTemplate.postForObject(
            ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionIndicatorParam/" + action,
            entity, Integer.class);

        String pageRedisFlag = CacheUtil.getSysParam("pageRedisFlag");
        if (CommonUtil.isType(pageRedisFlag, BussinessDictCodeListRds.是否_是)) {
            threadPoolTaskExecutor.execute(()->{
                try {
                    logger.info("===========指标SQL参数进入redis开始=============");
                    Thread.sleep(6000);
                    // 更新Redis
                    ImplCond cond = new ImplCond();
                    cond.setParamCodeArray(entity.getParaCode());
                    cond.setIsUpdParVal(false);
                    cond.setPlateDate(DateUtil.parse(DateUtil.getSysDate()));
                    redisRriter.dealRiskIndParam(cond);
                    logger.info("===========指标SQL参数进入redis结束=============");
                } catch (InterruptedException e) {
                    logger.error("===========指标SQL参数进入redis异常=============");
                }

            });
        }
    }

    public List<RskSupervisionIndicatorParam003Dto> selectFixRecode(String paraCode) {
        return restTemplate.getForList(ServiceApplication.RISK_RDS_SERVICE_NAME
                                             + "/RskSupervisionIndicatorParam/selectFixRecode?paraCode="
                                             + paraCode,
            RskSupervisionIndicatorParam003Dto.class);
    }

}
