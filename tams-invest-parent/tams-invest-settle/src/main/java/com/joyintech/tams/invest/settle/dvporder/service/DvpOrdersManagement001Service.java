package com.joyintech.tams.invest.settle.dvporder.service;

import java.util.Map;

import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.settle.dvporder.DvpOrdersDto;
import com.joyintech.tams.invest.settle.balance.dto.OutflowFundsSystemFlowDto;
import com.joyintech.tams.invest.settle.dvporder.dto.DvpOrdersManagement001Dto;
import com.joyintech.tams.invest.settle.dvporder.dto.DvpOrdersProcessDto;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;

/**
 * @program: tams-invest-parent
 * @description: DVP指令管理
 * @author: 强成西
 * @create: 2019-01-03 10:54
 **/
@Service
public class DvpOrdersManagement001Service extends CommonExportService<DvpOrdersManagement001Dto> {

    public Page<DvpOrdersManagement001Dto> findTransferTableData(Map<String, Object> params) {
        return restTemplate.postForPage(ServiceApplication.SETTLE_SERVICE_NAME + "/DvpOrdersManagement/findTransferTableData",
            params, DvpOrdersManagement001Dto.class);
    }

    public Page<DvpOrdersManagement001Dto> findDvpTransferTableData(Map<String, Object> params) {
        return restTemplate.postForPage(ServiceApplication.SETTLE_SERVICE_NAME + "/DvpOrdersManagement/findDvpTransferTableData",
                params, DvpOrdersManagement001Dto.class);
    }


    @Override
    protected void buildExportParam(ExportParam exportParam) {
        if (exportParam.getSelectParam().get("isDvp") != null && Boolean.parseBoolean((String) exportParam.getSelectParam().get("isDvp"))) {
            exportParam.config("/com/joyintech/tams/invest/settle/dvporder/config/export_confDvp.xml").dataSource(
                    DataSourceType.MASERT);
        }
        else {
            exportParam.config("/com/joyintech/tams/invest/settle/dvporder/config/export_conf.xml").dataSource(DataSourceType.MASERT);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<DvpOrdersManagement001Dto> selectPageData(Map<String, Object> selectParam) {
        if (selectParam.get("isDvp") != null && Boolean.parseBoolean((String) selectParam.get("isDvp"))) {
            return findDvpTransferTableData(selectParam);
        }
        else {
            return findTransferTableData(selectParam);
        }
    }

    public DvpOrdersProcessDto printPayOrders(Map<String, Object> pageParam) {
        return restTemplate.postForObject(ServiceApplication.SETTLE_SERVICE_NAME + "/DvpOrdersManagement/findDvpOrdersByOrderId",
                pageParam.get("bizCode"), DvpOrdersProcessDto.class);
    }
}
