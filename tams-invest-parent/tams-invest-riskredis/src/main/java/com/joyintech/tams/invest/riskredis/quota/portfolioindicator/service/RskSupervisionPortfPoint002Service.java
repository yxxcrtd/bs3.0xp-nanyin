package com.joyintech.tams.invest.riskredis.quota.portfolioindicator.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.redisinit.dto.ImplCond;
import com.joyintech.tams.invest.common.redisinit.service.RiskRedisInitService;
import com.joyintech.tams.invest.riskredis.quota.portfolioindicator.dto.RskPortfolioIndicator002Dto;
import com.joyintech.tams.invest.riskredis.quota.superviseindicator.dto.RskSupervisionIndicator002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 组合关联监督指标service 〈功能详细描述〉
 * 
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see RskSupervisionPortfPoint002Service
 * @since 1.0
 */
@Service
public class RskSupervisionPortfPoint002Service extends CommonExportService<RskPortfolioIndicator002Dto> implements BaseProcessService<RskPortfolioIndicator002Dto> {


    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(RskSupervisionPortfPoint002Service.class);

    /**
     * redis数据初始化
     */
    @Autowired
    private RiskRedisInitService<ImplCond> redisRriter;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public void doComplete(RskPortfolioIndicator002Dto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
        	//如果生效日小于审批通过日，则审批通过日为实际生效日
        	if(DateUtil.compareDate(entity.getStartDate(), DateUtil.parse(DateUtil.format(DateUtil.platDateFromCache())))<0){
        		entity.setStartDate(DateUtil.parse(DateUtil.format(DateUtil.platDateFromCache())));
        	}
            action = "insert";
        }
        else {
            action = "update";
        }
        restTemplate.postForObject(
            ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionPortf002Point/" + action,
            entity, Integer.class);
        String pageRedisFlag = CacheUtil.getSysParam("pageRedisFlag");
        if (CommonUtil.isType(pageRedisFlag, BussinessDictCodeListRds.是否_是)) {
            threadPoolTaskExecutor.execute(()->{
                try {
                    logger.info("===========组合关联指标进入redis开始=============");
                    Thread.sleep(6000);
                    // 更新Redis
                    ImplCond cond = new ImplCond();
                    List<RskPortfolioIndicator002Dto> dtoList = entity.getSelectedItem();
                    // modify zhuzhen 20191203 增加修改组合包下指标信息时更新redis start
                    if(StringUtil.equals(BussinessDictCodeListRds.组合层次_组合包.getValue(), entity.getPortfolioLevel())) {
                        if (StringUtil.isEmptyList(dtoList)) {
                            cond.setPortfBagIdArray(entity.getPortfolioId());
                        }
                        else {
                            String[] prodIdList = dtoList.stream().map(item -> item.getPortfolioId()).collect(
                                    Collectors.toList()).toArray(new String[dtoList.size()]);
                            cond.setPortfBagIdArray(prodIdList);
                        }
                    }
                    // modify zhuzhen 20191203 增加修改组合包下指标信息时更新redis end
                    else {
                        if (StringUtil.isEmptyList(dtoList)) {
                            cond.setPortfIdArray(entity.getPortfolioId());
                        }
                        else {
                            String[] prodIdList = dtoList.stream().map(item -> item.getPortfolioId()).collect(
                                Collectors.toList()).toArray(new String[dtoList.size()]);
                            cond.setPortfIdArray(prodIdList);
                        }
                    }
                    redisRriter.dealRiskPtlIndicator(cond);
                    logger.info("===========组合关联指标进入redis结束=============");
                } catch (InterruptedException e) {
                    logger.error("===========组合关联指标进入redis异常=============");
                }
            });
        }
    }
    /**
     * 画面查询 <功能详细描述>
     * 
     * @param parameters
     * @return RskSupervisionIndicatorDto
     * @see
     * @since 1.0
     */
    public Page<RskSupervisionIndicator002Dto> queryRelatedIndList(Map<String, Object> parameters) {
        return restTemplate.postForPage(ServiceApplication.RISK_RDS_SERVICE_NAME
                                              + "/RskSupervisionPortf002Point/queryRelatedIndList",
            parameters, RskSupervisionIndicator002Dto.class);
    }

    /**
     * 查询组合成分
     * 
     * @param portfolioCode
     * @return String
     * @since 1.0
     */
    public String getPortfolioConst(String portfolioCode) {
        return restTemplate.getForObject(ServiceApplication.RISK_RDS_SERVICE_NAME
                                               + "/RskSupervisionPortf002Point/getPortfolioConst?portfolioCode="
                                               + portfolioCode,
            String.class);
    }

    /**
     * 根据组合代码与指标代码查询关联指标信息
     * 
     * @param portfId 组合代码
     * @param indId 指标代码
     * @return 关联指标信息
     * @since 1.0
     */
    public RskPortfolioIndicator002Dto getBussinessEntityByPortfId(String portfId, String indId) {
        return restTemplate.getForObject(ServiceApplication.RISK_RDS_SERVICE_NAME
                                               + "/RskSupervisionPortf002Point/queryPortfIndByPortfId?portfId="
                                               + portfId + "&indId=" + indId,
            RskPortfolioIndicator002Dto.class);
    }

    /**
     * 新建场合时判断是否已经关联了
     * 
     * @param
     */
    public int searchRelatedIndByPortf(String indId, String portfolioId) {
        return restTemplate.getForObject(ServiceApplication.RISK_RDS_SERVICE_NAME
                                               + "/RskSupervisionPortf002Point/searchRelatedIndByPortf?indId="
                                               + indId + "&portfolioId=" + portfolioId,
            Integer.class);
    }

    public int searchRelatedIndByPortfBagSingle(String indId, String portfolioId) {
        return restTemplate.getForObject(ServiceApplication.RISK_RDS_SERVICE_NAME
                                               + "/RskSupervisionPortf002Point/searchRelatedIndByPortfBagSingle?indId="
                                               + indId + "&portfolioId=" + portfolioId,
            Integer.class);
    }

    public int searchRelatedIndByPortfBag(String indId, String portfolioId) {
        return restTemplate.getForObject(ServiceApplication.RISK_RDS_SERVICE_NAME
                                               + "/RskSupervisionPortf002Point/searchRelatedIndByPortfBag?indId="
                                               + indId + "&portfolioId=" + portfolioId,
            Integer.class);
    }
}
