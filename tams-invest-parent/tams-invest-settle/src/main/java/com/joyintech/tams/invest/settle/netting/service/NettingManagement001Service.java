package com.joyintech.tams.invest.settle.netting.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.settle.netting.NettingManagementDto;
import com.joyintech.tams.invest.common.dto.settle.netting.TransferMessageDto;
import com.joyintech.tams.invest.settle.dvporder.dto.DvpOrdersManagement001Dto;
import com.joyintech.tams.invest.settle.netting.dto.NettingManagement001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 姚轶文
 * @create 2019- 11-06 17:04
 */
@Service
public class NettingManagement001Service extends CommonExportService<NettingManagement001Dto> {

    @Autowired
    TransferMessageService transferMessageService;

    public Page<NettingManagement001Dto> findNettingTableData(Map<String, Object> params) {

        Page<NettingManagement001Dto> page = restTemplate.postForPage(ServiceApplication.SETTLE_SERVICE_NAME + "/NettingManagement/findNettingTableData",
                params, NettingManagement001Dto.class);

        if(page!=null && page.getRoot()!=null && page.getRoot().size()>0)
        {
            List<NettingManagement001Dto> list = page.getRoot();

            for(NettingManagement001Dto dto : list)
            {
                String orderId = restTemplate.postForObject(ServiceApplication.SETTLE_SERVICE_NAME + "/NettingManagement/findOrderIdByTotleTradeId", dto.getTotle(), String.class);
                if(orderId!=null && !orderId.equals(""))
                {
                    dto.setState("已处理");
                }
            }
        }

        return page;
    }

    public Page<TransferMessageDto> findTransferMessage(Map<String, Object> params) {
        return restTemplate.postForPage(ServiceApplication.SETTLE_SERVICE_NAME + "/NettingManagement/findTransferMessage",
                params, TransferMessageDto.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {

        if (exportParam.getSelectParam().get("isDvp") != null && Boolean.parseBoolean((String) exportParam.getSelectParam().get("isDvp"))) {
            exportParam.config("/com/joyintech/tams/invest/settle/netting/config/export_conf.xml").dataSource(DataSourceType.MASERT);
        }
        else {
            exportParam.config("/com/joyintech/tams/invest/settle/netting/config/export.xml").dataSource(DataSourceType.MASERT);
        }

        //exportParam.config("/com/joyintech/tams/invest/settle/netting/config/export_conf.xml").dataSource(DataSourceType.MASERT);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<NettingManagement001Dto> selectPageData(Map<String, Object> selectParam) {
        /*
        if (selectParam.get("isDvp") != null && Boolean.parseBoolean((String) selectParam.get("isDvp"))) {
            return findDvpTransferTableData(selectParam);
        }
        else {
            return findTransferTableData(selectParam);
        }*/
        return findDvpTransferTableData(selectParam);
    }



    public Page<NettingManagement001Dto> findDvpTransferTableData(Map<String, Object> params) {
        Page<NettingManagement001Dto> page = restTemplate.postForPage(ServiceApplication.SETTLE_SERVICE_NAME + "/NettingManagement/findNettingTableData",
                params, NettingManagement001Dto.class);
        return page;
    }



}
