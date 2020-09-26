package com.joyintech.tams.valuation.basedata.marketdata.externaloptions.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.basedata.marketdata.externaloptions.dto.ExternalOptions002Dto;
import com.joyintech.tams.valuation.basedata.marketdata.valfxvaluztioninfo.dto.ValFxValuztionInfo002Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author 姚轶文
 * @create 2019- 12-31 11:35
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ExternalOptions002Service extends CommonExportService<ExternalOptions002Dto> implements BaseProcessService<ExternalOptions002Dto> {

    public List<SuggestDto> getFinprodId(Map<String, Object> params) {

        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ExternalOptions/findFinRateInfoByBenchmarkId",
                params, SuggestDto.class);
    }


    public ExternalOptions002Dto findByPrimaryKey(Map<String, Object> map)
    {
        String str = DictCodeListExt3.基准类型_2_合约标的.getValue(); //07
        //System.out.println(str);
        map.put("BENCHMARK_TYPE2",str);
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                        + "/ExternalOptions/findByPrimaryKey",
                map, ExternalOptions002Dto.class);
    }

    @Override
    public void doComplete(ExternalOptions002Dto entity, OperationType type) {
        restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                        + "/ExternalOptions/doComplete/" + type.name(),
                entity, AjaxResult.class);
    }


    public String findByBenchmarkId(String finprodId2)
    {
        String str = new String();
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                        + "/ExternalOptions/findByBenchmarkId?finprodId2="+finprodId2,str,
                 String.class);
    }
    
    public ExternalOptions002Dto dateCheck(Map<String, Object> params){
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                        + "/ExternalOptions/dateCheck", params, ExternalOptions002Dto.class);
    }

}
