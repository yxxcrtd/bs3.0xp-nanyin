package com.joyintech.tams.invest.riskredis.quota.rsksupervisionlog.service;

import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionlog.dto.RskSupervisionLogDto;

/**
 * @description: RskSupervisionLogService
 * @author: 裴家俊
 * @create: 2018-11-01 14:30
 **/
@Service
public class RskSupervisionLogService extends CommonExportService<RskSupervisionLogDto> {
    
    /**
     * 查询界面询事件
     *
     * @param map 页面参数
     * @return 调用微服务
     * @see
     * @since 1.0
     */
    public Page<RskSupervisionLogDto> selectForPage(Map<String, Object> map){
        Page<RskSupervisionLogDto> page = restTemplate.postForPage(
                ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionLog/findPage",
                map, RskSupervisionLogDto.class);
        return page;
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
    	  exportParam.config(
                  "/com/joyintech/tams/invest/riskredis/quota/rsksupervisionlog/conf/export_conf.xml").dataSource(
                  DataSourceType.MASERT);
    }
    /**
     * 设置日期格式
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
    	 if(("logTime".equals(propName)) && value != null){
             cell.setCellValue(DateUtil.format((Date)value));
         }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RskSupervisionLogDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

}
