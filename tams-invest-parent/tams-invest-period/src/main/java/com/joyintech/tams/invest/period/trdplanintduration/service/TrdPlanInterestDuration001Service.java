package com.joyintech.tams.invest.period.trdplanintduration.service;

import java.util.ArrayList;
import java.util.Map;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.period.trdplanintduration.dto.TrdPlanInterestDurationProcessDto;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.service
 * @Description: 债券存续期管理-查询
 * @date Date : 2018年09月21日 14:32
 */
@Service
public class TrdPlanInterestDuration001Service extends CommonExportService<TrdPlanInterestDurationProcessDto> {

    public Page<SelectOptionDto> getFinprodMarketId() {
        Map<String, Object> params = RequestUtil.getPageParam();
        params.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        params.put("profitType", DictCodeList.收益类型_收益型.getValue());
        return restTemplate.postForPage(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectDuration/getFinprodMarketId", params,
            SelectOptionDto.class);
    }
    
    public Page<SelectOptionDto> getParentMarketId() {
    	Map<String, Object> params = RequestUtil.getPageParam();
    	params.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_母资管计划.getValue());
    	params.put("profitType", DictCodeList.收益类型_收益型.getValue());
    	return restTemplate.postForPage(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdBondDuration/getFinprodMarketId", params,
    			SelectOptionDto.class);
    }

    public Page<TrdPlanInterestDurationProcessDto> findPage(Map<String, Object> params) {
        if (params.get("statusValid") != null) {
            params.put("statusValid", StringUtil.splitToList(",", (String)params.get("statusValid")));
        }
        else {
            params.put("statusValid", new ArrayList<>());
        }
        params.put("userName", LoginUserUtil.getUserName());
        return restTemplate.postForPage(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdPlanInterestDuration/findPage", params,
            TrdPlanInterestDurationProcessDto.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/period/trdplanintduration/config/export_conf.xml").dataSource(DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrdPlanInterestDurationProcessDto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }

    public Page<SelectOptionDto> getBusiCode() {
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("FINPROD_TYPE_TGF01", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        param.put("TRADE_STATUS03", DictCodeListExt1.交易状态_撤销.getValue());
        return restTemplate.postForPage(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectDuration/getBusiCode", param,
            SelectOptionDto.class);
    }
}
