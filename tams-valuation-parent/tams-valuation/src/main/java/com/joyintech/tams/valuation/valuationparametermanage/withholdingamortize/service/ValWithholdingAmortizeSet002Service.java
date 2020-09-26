package com.joyintech.tams.valuation.valuationparametermanage.withholdingamortize.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.valuationparametermanage.withholdingamortize.dto.ValWithholdingAmortizeSet001Dto;
import com.joyintech.tams.valuation.valuationparametermanage.withholdingamortize.dto.ValWithholdingAmortizeSet002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 待摊设置
 * 
 * @author tanxuan
 * @version 1.0 2019年11月25日
 * @see ValWithholdingAmortizeSet002Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValWithholdingAmortizeSet002Service extends CommonExportService<ValWithholdingAmortizeSet002Dto> implements BaseProcessService<ValWithholdingAmortizeSet002Dto> {

    /**
     * 流程审核完成时执行 {@inheritDoc}
     */
    @Override
    public void doComplete(ValWithholdingAmortizeSet002Dto entity, OperationType type) {
        restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                                   + "/ValWithholdingAmortizeSet002/doComplete/" + type.name(),
            entity, AjaxResult.class);
    }

    /**
     * 查询
     * 
     * @param configId
     * @return valWithholdingAmortizeSet002Dto
     */
    public ValWithholdingAmortizeSet002Dto findByPrimaryKey(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                                          + "/ValWithholdingAmortizeSet002/findByPrimaryKey",
            map, ValWithholdingAmortizeSet002Dto.class);
    }

    /**
     * 账套代码 - 业务编号获取
     *
     * @return 返回的数据
     */
    public List<SelectOptionDto> getFinprodId(Map<String, Object> params) {

        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                                        + "/ValWithholdingAmortizeSet002/getFinprodId",
            params, SelectOptionDto.class);
    }

    /**
     * 通过业务编号，获取信息
     * 
     * @param pageParam
     * @return valWithholdingAmortizeSet001Dto
     */
    public ValWithholdingAmortizeSet001Dto getFinprodInfo(Map<String, Object> pageParam) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                                          + "/ValWithholdingAmortizeSet002/getFinprodInfo",
            pageParam, ValWithholdingAmortizeSet001Dto.class);
    }
    
    /**
     * 获取交易日期
     * 
     * @param pageParam
     * @return valWithholdingAmortizeSet001Dto
     */
    public ValWithholdingAmortizeSet001Dto getTradeDate(Map<String, Object> pageParam) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                                          + "/ValWithholdingAmortizeSet002/getTradeDate",
            pageParam, ValWithholdingAmortizeSet001Dto.class);
    }

}
