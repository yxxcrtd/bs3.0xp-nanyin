package com.joyintech.tams.disclosure.information.config.hugeinfopro.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.config.proelementadj.dto.ProElementAdj001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HugeInfoPro001Service extends CommonExportService<ProElementAdj001Dto> implements BaseProcessService<ProElementAdj001Dto> {


    public Page<ProElementAdj001Dto> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ProElementAdj/findPage",
                pageParam, ProElementAdj001Dto.class);
    }

    @Override
    public void doComplete(ProElementAdj001Dto proElementAdj001Dto, OperationType operationType) {
        proElementAdj001Dto.setOperateType(operationType.toString());
        restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ProElementAdj/doComplete",
                proElementAdj001Dto, Integer.class);

    }

    public ProElementAdj001Dto selectByPrimaryKey(String prodRegistNo) {
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ProElementAdj/selectByPrimaryKey", prodRegistNo,
                ProElementAdj001Dto.class);
    }


    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    @Override
    public Page<ProElementAdj001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
}
