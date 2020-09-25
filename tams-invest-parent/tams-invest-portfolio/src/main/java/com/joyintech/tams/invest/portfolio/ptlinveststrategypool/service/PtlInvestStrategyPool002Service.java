package com.joyintech.tams.invest.portfolio.ptlinveststrategypool.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.ptlinveststrategypool.dto.PtlInvestStrategyPool001Dto;
import com.joyintech.tams.invest.portfolio.ptlinveststrategypool.dto.PtlInvestStrategyPoolDetailDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
public class PtlInvestStrategyPool002Service extends CommonExportService<PtlInvestStrategyPool001Dto> implements BaseProcessService<PtlInvestStrategyPool001Dto> {

    /**
     * 业务编号生成类
     */
    @Autowired(required = false)
    public BaseGetBusinessId baseGetBussinessId;

    @Override
    public void doComplete(PtlInvestStrategyPool001Dto entity, OperationType type) {
        if (OperationType.REVOKE.equals(type)) {

        }
        else {
            // 新建修改操作微服务
            if (OperationType.NEW.equals(type)) {
                entity.setIsNew(DictCodeList.是否_是.getValue());
            }
            else {
                entity.setIsNew(DictCodeList.是否_否.getValue());
            }
            restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME
                                       + "/PtlInvestStrategyPool/editStrategyPoolByEntity",
                entity, Integer.class);
        }

    }

    /**
     * 通过策略代码查询策略详情信息
     * 
     * @param strategyId 策略id
     * @return
     */
    public PtlInvestStrategyPool001Dto getPtlInvestStrategyPool(String strategyId) {

        return restTemplate.getForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME
                                         + "/PtlInvestStrategyPool/getPtlInvestStrategyPool?strategyId="
                                         + strategyId,
            PtlInvestStrategyPool001Dto.class);
    }

    /**
     * 通过策略代码查询策略详情信息列表
     * 
     * @param strategyId 策略id
     * @return
     */
    public List<PtlInvestStrategyPoolDetailDto> getPtlInvestStrategyPoolDetail(String strategyId) {

        return restTemplate.getForList(ServiceApplication.PORTFOLIO_SERVICE_NAME
                                       + "/PtlInvestStrategyPool/getPtlInvestStrategyPoolDetail?strategyId="
                                       + strategyId,
            PtlInvestStrategyPoolDetailDto.class);
    }

    /**
     * 策略代码生成
     * 
     * @see
     * @since 1.0
     */
    public String createStrategyId() {
        return restTemplate.postForObject(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlInvestStrategyPool/createStrategyId",
            null, String.class);

    }

    /**
     * 查询二级分类多选框
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryMulType2(Map<String, Object> map) {
        Page<SelectOptionDto> list = restTemplate.postForPage(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlInvestStrategyPool/queryMulType2",
            map, SelectOptionDto.class);
        return list;
    }

    /**
     * 查询三级分类多选框
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryMulType3(Map<String, Object> map) {
        Page<SelectOptionDto> list = restTemplate.postForPage(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlInvestStrategyPool/queryMulType3",
            map, SelectOptionDto.class);
        return list;
    }

    /**
     * 查询四级分类多选框
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryMulType4(Map<String, Object> map) {
        Page<SelectOptionDto> list = restTemplate.postForPage(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlInvestStrategyPool/queryMulType4",
            map, SelectOptionDto.class);
        return list;
    }

    /**
     * 查询五级分类多选框
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryMulType5(Map<String, Object> map) {
        Page<SelectOptionDto> list = restTemplate.postForPage(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlInvestStrategyPool/queryMulType5",
            map, SelectOptionDto.class);
        return list;
    }
}
