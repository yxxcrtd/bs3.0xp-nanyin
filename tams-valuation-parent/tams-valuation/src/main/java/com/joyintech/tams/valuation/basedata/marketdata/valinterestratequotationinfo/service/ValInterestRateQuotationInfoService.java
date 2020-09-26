package com.joyintech.tams.valuation.basedata.marketdata.valinterestratequotationinfo.service;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.basedata.marketdata.valinterestratequotationinfo.dto.ValInterestRateQuotationInfoDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;
/**
 * 利率行情信息service
 * @author fanshuanghu
 * @version 1.0 2018年8月20日
 * @see ValInterestRateQuotationInfoService
 * @since 1.0
 */
@Service
@Transactional
public class ValInterestRateQuotationInfoService extends CommonExportService<ValInterestRateQuotationInfoDto> 
    implements BaseProcessService<ValInterestRateQuotationInfoDto> {

    /**
     * 分页查询
     * 
     * @param pageParam 传入的查询条件
     * @return Page<ValInterestRateQuotationInfoDto> page对象
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<ValInterestRateQuotationInfoDto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValInterestRateQuotationInfo/selectForPage",
            pageParam, ValInterestRateQuotationInfoDto.class);
    };

    /**
     * 根据主键查询
     * @param map 查询条件
     * @return ValInterestRateQuotationInfoDto 实体类
     */
    public ValInterestRateQuotationInfoDto selectByPrimaryKey(Map<String, Object> map) {
        return restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValInterestRateQuotationInfo/selectByPrimaryKey",
            map, ValInterestRateQuotationInfoDto.class);
    }

    /**
     * 导出 {@inheritDoc}
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/valuation/basedata/marketdata/valinterestratequotationinfo/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
    }
    /**
     * 导出
     */
    @Override
    public Page<ValInterestRateQuotationInfoDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    /**
     * 设置日期格式
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(("relaeseDate".equals(propName) || "effDate".equals(propName)) && value != null){
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }
    /**
     * 流程实现方法 {@inheritDoc}
     */
    @Override
    public void doComplete(ValInterestRateQuotationInfoDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        entity.setIntRate(entity.getIntRate().divide(new BigDecimal("100")));
        restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValInterestRateQuotationInfo/doComplete",
            entity, Integer.class);
    }

}
