package com.joyintech.tams.invest.settle.netting.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.settle.netting.TransferMessageDto;
import com.joyintech.tams.invest.settle.netting.dto.NettingManagement001Dto;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 姚轶文
 * @create 2019- 11-20 11:23
 */
@Service
public class TransferMessageService  extends CommonExportService<TransferMessageDto> {


    @Override
    protected void buildExportParam(ExportParam exportParam) {

        /*
        if (exportParam.getSelectParam().get("isDvp") != null && Boolean.parseBoolean((String) exportParam.getSelectParam().get("isDvp"))) {
            exportParam.config("/com/joyintech/tams/invest/settle/netting/config/export_conf.xml").dataSource(DataSourceType.MASERT);
        }
        else {
            exportParam.config("/com/joyintech/tams/invest/settle/netting/config/export.xml").dataSource(DataSourceType.MASERT);
        }
*/
        //exportParam.config("/com/joyintech/tams/invest/settle/netting/config/export_conf.xml").dataSource(DataSourceType.MASERT);
        exportParam.config("/com/joyintech/tams/invest/settle/netting/config/export.xml").dataSource(DataSourceType.MASERT);

    }

    @Override
    public Page<TransferMessageDto> selectPageData(Map<String, Object> selectParam) {
            return findTransferTableData(selectParam);
    }


    public Page<TransferMessageDto> findTransferTableData(Map<String, Object> params) {

        Page<TransferMessageDto> page = restTemplate.postForPage(ServiceApplication.SETTLE_SERVICE_NAME + "/NettingManagement/findTransferMessage",
                params, TransferMessageDto.class);
        return page;
    }


}
