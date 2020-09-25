package com.joyintech.tams.invest.period.trdsecovertransfer.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.period.trdsecoverdueduration.dto.TrdSecOverdueDuration003Dto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class TrdSecOverTransfer001Service extends CommonExportService<TrdSecOverdueDuration003Dto> {
    public Page<TrdSecOverdueDuration003Dto> findPage(Map<String, Object> params) {
        setParams(params);
        return restTemplate.postForPage(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdSecOverTransfer/findPage", params,
                TrdSecOverdueDuration003Dto.class);    }

    private void setParams(Map<String, Object> params) {
    }
    /**
     * 导出参数
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/invest/period/trdsecovertransfer/config/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(("transferDate".equals(propName)) && value != null){
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrdSecOverdueDuration003Dto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }
}
