package com.joyintech.tams.invest.period.trdprojduration.service;

import java.util.ArrayList;
import java.util.Map;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
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
import com.joyintech.tams.invest.period.trdprojduration.dto.TrdProjectDurationProcessDto;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.service
 * @Description: 债券存续期管理-查询
 * @date Date : 2018年09月21日 14:32
 */
@Service
public class TrdProjectDuration001Service extends CommonExportService<TrdProjectDurationProcessDto> {

    public Page<SelectOptionDto> getFinprodMarketId() {
        Map<String, Object> params = RequestUtil.getPageParam();
        params.put("finprodType2",
            DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        return restTemplate.postForPage(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectDuration/getFinprodMarketId",
                params, SelectOptionDto.class);
    }

    public Page<SelectOptionDto> getBusiCode() {
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("FINPROD_TYPE_TGF01", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        param.put("TRADE_STATUS03", DictCodeListExt1.交易状态_撤销.getValue());
        return restTemplate.postForPage(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectDuration/getBusiCode",
                param, SelectOptionDto.class);
    }

    public Page<TrdProjectDurationProcessDto> findPage(Map<String, Object> params) {
        if (params.get("statusValid") != null) {
            params.put("statusValid",
                    StringUtil.splitToList(",", (String)params.get("statusValid")));
        }
        else {
            params.put("statusValid", new ArrayList<>());
        }
        //多选下拉框处理（合作资产）
        Object chlFinprodIdObj = params.get("chlFinprodId");
        if (chlFinprodIdObj != null){
            params.put("chlFinprodIdList",
                    StringUtil.splitToList(GlobalConstant.COMMA,(String) chlFinprodIdObj));
        }else {
            params.put("chlFinprodIdList",new ArrayList<>());
        }
        params.put("userName", LoginUserUtil.getUserName());
        return restTemplate.postForPage(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectDuration/findPage", params,
                TrdProjectDurationProcessDto.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/period/trdprojduration/config/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrdProjectDurationProcessDto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }

}
