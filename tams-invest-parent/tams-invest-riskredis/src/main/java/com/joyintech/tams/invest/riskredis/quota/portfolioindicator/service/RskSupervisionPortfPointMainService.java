package com.joyintech.tams.invest.riskredis.quota.portfolioindicator.service;

import java.util.List;
import java.util.stream.Collectors;

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
import com.joyintech.tams.invest.riskredis.quota.superviseindicator.dto.RskPortfolioIndicatorDto;
import com.joyintech.tams.invest.riskredis.quota.superviseindicator.dto.RskSupervisionIndicator002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 组合关联监督指标service 〈功能详细描述〉
 * 
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see RskSupervisionPortfPointMainService
 * @since 1.0
 */
@Service
public class RskSupervisionPortfPointMainService extends CommonExportService<RskSupervisionIndicator002Dto> implements BaseProcessService<RskSupervisionIndicator002Dto> {
	/**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(RskSupervisionPortfPointMainService.class);

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * redis数据初始化
     */
    @Autowired
    private RiskRedisInitService<ImplCond> redisRriter;

    @Override
    public void doComplete(RskSupervisionIndicator002Dto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
            // 如果生效日小于审批通过日，则审批通过日为实际生效日
            if (StringUtil.isNotEmpty(entity.getRskPortfolioIndicatorDto())) {
                if (DateUtil.compareDate(entity.getRskPortfolioIndicatorDto().getStartDate(),
                    DateUtil.parse(DateUtil.format(DateUtil.platDateFromCache()))) < 0) {
                    entity.getRskPortfolioIndicatorDto().setStartDate(
                        DateUtil.parse(DateUtil.format(DateUtil.platDateFromCache())));
                }
            }
            action = "insert";
        }
        else {
            action = "update";
        }
        restTemplate.postForObject(
            ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionPortfPoint/" + action, entity,
            Integer.class);

        String pageRedisFlag = CacheUtil.getSysParam("pageRedisFlag");
        if (CommonUtil.isType(pageRedisFlag, BussinessDictCodeListRds.是否_是)) {
            // 更新Redis
            ImplCond cond = new ImplCond();
            List<RskPortfolioIndicatorDto> dtoList = entity.getSelectedPortfIndItem();
            if (!StringUtil.isEmptyList(dtoList)) {
                String[] prodIdList = dtoList.stream().map(item -> item.getPortfolioId()).collect(
                    Collectors.toList()).toArray(new String[dtoList.size()]);
                cond.setPortfIdArray(prodIdList);
                redisRriter.dealRiskPtlIndicator(cond);
            }
        }
    }

}
