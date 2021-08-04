package com.joyintech.tams.invest.basicdata.futuresdepositpercent.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.invest.basicdata.fundsecuritiesaccount.dto.FundSecuritiesAccountDto;
import com.joyintech.tams.invest.basicdata.futuresdepositpercent.dto.FuturesDepositPercentDto;
import com.joyintech.tams.invest.basicdata.mstcalendartype.dto.MstCalendarType001Dto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Classname FuturesDepositPercentService
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/11/12 14:31 
 * @version 1.0 
 */
@Service
public class FuturesDepositPercentService  extends CommonExportService<FuturesDepositPercentDto> implements BaseProcessService<FuturesDepositPercentDto> {

    @Override
    public void doComplete(FuturesDepositPercentDto futuresDepositPercentDto, OperationType operationType) {
        restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME+"/FuturesDepositPercent/doComplete/" + operationType, futuresDepositPercentDto, Integer.class);
    }


    /**
     *
     * @Description //分页查询
     * @Param [map]
     * @return com.joyintech.framework.grid.Page<com.joyintech.tams.invest.basicdata.futuresdepositpercent.dto.FuturesDepositPercentDto>
     * @version 1.0
     * @see
    **/
    public Page<FuturesDepositPercentDto> selectForPage(Map<String, Object> map) {
        return restTemplate.postForPage(
                ServiceApplication.BASICDATA_SERVICE_NAME + "/FuturesDepositPercent/selectForPage",
                map, FuturesDepositPercentDto.class);
    }


    /**
     *
     * @Description //根据主键查询Dto
     * @Param [map]
     * @return com.joyintech.tams.invest.basicdata.futuresdepositpercent.dto.FuturesDepositPercentDto
     * @version 1.0
     * @see
    **/
    public FuturesDepositPercentDto selectByPrimaryKey(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME + "/FuturesDepositPercent/selectByPrimaryKey",
                map, FuturesDepositPercentDto.class);
    }


    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/invest/basicdata/futuresdepositpercent/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FuturesDepositPercentDto> selectPageData(Map<String, Object> selectParam) {
        try {
            return selectForPage(selectParam);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
