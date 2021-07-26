package com.joyintech.tams.invest.riskredis.quota.redismgr.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.riskredis.quota.redismgr.dto.RskRedisParamConfigDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * redis参数查询
 * 
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see RskRedisParamConfigService
 * @since 1.0
 */
@Service
public class RskRedisParamConfigService extends CommonExportService<RskRedisParamConfigDto> implements BaseProcessService<RskRedisParamConfigDto> {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(RskRedisParamConfigService.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(RskRedisParamConfigDto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        else {
            action = "update";
        }
        simpleRestTemplate.postForObject(
            ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskRedisParamConfig/" + action, entity,
            Integer.class);
    }

    /**
     * 画面查询 <功能详细描述>
     *
     * @param parameters
     * @return RskSupervisionIndicatorDto
     * @see
     * @since 1.0
     */
    public Page<RskRedisParamConfigDto> selectForPage(Map<String, Object> parameters) {
        if(StringUtil.isNotEmpty(parameters.get("commonBusinessType"))){
            parameters.put("commonBusinessType",StringUtil.splitToList(",",parameters.get("commonBusinessType").toString()));
        }
        else{
            parameters.remove("commonBusinessType");
        }
        return simpleRestTemplate.postForPage(ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskRedisParamConfig/findPage",
                parameters, RskRedisParamConfigDto.class);
    }

    public RskRedisParamConfigDto getBasicInfo(String commonBusinessType,String paraCode){
        return simpleRestTemplate.getForObject(ServiceApplication.RISK_RDS_SERVICE_NAME
                        + "/RskRedisParamConfig/getBasicInfo?commonBusinessType="
                        + commonBusinessType+"&paraCode="+ paraCode,
                RskRedisParamConfigDto.class);
    }

}
