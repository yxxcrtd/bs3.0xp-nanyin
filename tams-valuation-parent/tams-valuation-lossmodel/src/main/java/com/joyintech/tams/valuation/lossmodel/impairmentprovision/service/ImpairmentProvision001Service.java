package com.joyintech.tams.valuation.lossmodel.impairmentprovision.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.lossmodel.impairmentprovision.dto.ImpairmentProvision001Dto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 减值情况计提表
 * @author xutong
 * @createTime 2021-07-05
 */
@Service
public class ImpairmentProvision001Service extends CommonExportService<ImpairmentProvision001Dto> {

    public List<ImpairmentProvision001Dto> selectForPage(Map<String, Object> params){
        return restTemplate.postForList(ServiceApplication.LOSS_MODEL_SERVICE_NAME+"/ImpairmentProvision001/findPage", params, ImpairmentProvision001Dto.class);
    }

    /**
     * 产品代码下拉框
     * @param params
     * @return
     * @throws Exception
     */
    public List<SelectOptionDto> getProdId(Map<String, Object> params){
        return restTemplate.postForList(ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/AssetImpairmentManagement001/getProdIdForImpairment", params, SelectOptionDto.class);
    }
}
