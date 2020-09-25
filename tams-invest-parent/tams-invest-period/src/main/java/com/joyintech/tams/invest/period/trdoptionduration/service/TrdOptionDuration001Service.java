package com.joyintech.tams.invest.period.trdoptionduration.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.period.trdoptionduration.dto.OptionDurationDetailDto;
import com.joyintech.tams.invest.period.trdoptionduration.dto.TrdOptionDurationDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public  class TrdOptionDuration001Service extends CommonExportService<TrdOptionDurationDto>  implements BaseProcessService<TrdOptionDurationDto>{

    /**
     * 列表的name属性
     */
    public static final String TABLE_NAME = "TrdOptionDuration001";

    /**
     * 期权存续期列表查询
     * @author panbin
     * @date 2019年12月26日
     * @param params
     * @return
     */
    public Page<TrdOptionDurationDto> findPage(Map<String, Object> params) {
        if (params.get("statusValid") != null) {
            params.put("statusValid",
                    StringUtil.splitToList(",", (String)params.get("statusValid")));
        }
        else {
            params.put("statusValid", null);
        }

        if (params.get("portfolioId") != null) {
            params.put("portfolioId",
                    StringUtil.splitToList(",", (String)params.get("portfolioId")));
        }
        else {
            params.put("portfolioId", null);
        }
        params.put("ps", params.get("ps"));
        params.put("targetId", params.get("targetId"));
        params.put("hylx", params.get("hylx"));
        params.put("exerciseType", params.get("exerciseType"));
        params.put("cDateStart", params.get("cDateStart"));
        params.put("cDateEnd", params.get("cDateEnd"));
        params.put("userName", LoginUserUtil.getUserName());
        params.put("sysDate", DateUtil.getSysDate());
        params.put("xqzt", params.get("xqzt"));
        return restTemplate.postForPage(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdOptionDuration/findPage", params,
                TrdOptionDurationDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/period/trdoptionduration/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrdOptionDurationDto> selectPageData(Map<String, Object> selectParam) {
        selectParam.put("userName", LoginUserUtil.getUserName());
        return findPage(selectParam);
    }
    /**
     * 根据交易编号查询流程中数据是否存在
     *
     * @param tradeId 交易编号
     * @return 流程中数据是否存在
     * @see
     * @since 1.0
     */
    public Boolean isInFlowDataExisted(String tradeId) {
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME
                        + "/TrdOptionTrade/isInFlowDataExisted/" + tradeId,
                null, Boolean.class);
    }

	@Override
	public void doComplete(TrdOptionDurationDto entity, OperationType type) {
		// TODO Auto-generated method stub
		
	}
    
	/**
     * 根据交易编号查询流程中数据是否存在
     *
     * @param tradeId 交易编号
     * @return 流程中数据是否存在
     * @see
     * @since 1.0
     */
    public Integer revokeCheck(Map<String, Object> params) {
        return restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME
                        + "/TrdOptionDuration/revokeCheck/" ,
                        params, Integer.class);
    }
}
