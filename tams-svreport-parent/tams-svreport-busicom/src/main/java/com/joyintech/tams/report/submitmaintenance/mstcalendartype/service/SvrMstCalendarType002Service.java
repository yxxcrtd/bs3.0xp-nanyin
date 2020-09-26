package com.joyintech.tams.report.submitmaintenance.mstcalendartype.service;

import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.report.submitmaintenance.mstcalendartype.dao.SvrMstCalendarTypeMapper;
import com.joyintech.tams.report.submitmaintenance.mstcalendartype.dto.SvrMstCalendarType002Dto;

/**
 * 日历类型管理Service
 *
 * @author liuwei
 * @version 1.0 2018年8月15日
 * @see SvrMstCalendarType002Service
 * @since 1.0
 */

@Service
public class SvrMstCalendarType002Service extends CommonExportService<SvrMstCalendarType002Dto> {

	/**
     * 日历类型管理Mapper
     */
    @Autowired
    private SvrMstCalendarTypeMapper mstCalendarTypeMapper;
    /**
     * 交易日历名称的明细查询
     *
     * @param map 查询参数
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<SvrMstCalendarType002Dto> selectCanlendarDetail(Map<String, Object> map) {
//        return simpleRestTemplate.postForPage(
//                ServiceApplication.BASICDATA_SERVICE_NAME + "/MstCalendarType/selectCanlendarDetail",
//                map, MstCalendarType002Dto.class);
        int pagenum = Integer.parseInt(map.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(map.get(GlobalConstant.PARAM_LENGTH).toString());
        Page<SvrMstCalendarType002Dto> page = queryForPage(pagenum,legth,()->mstCalendarTypeMapper.selectCanlendarDetail(map));
    	return page;
    }
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/report/submitmaintenance/mstcalendartype/conf/export_conf1.xml").dataSource(DataSourceType.MASERT);
    }
    /**
     * 设置日期格式
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(("cDate".equals(propName)) && value != null){
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }
    @Override
    public Page<SvrMstCalendarType002Dto> selectPageData(Map<String, Object> selectParam) {
        return selectCanlendarDetail(selectParam);
    }
}
