package com.joyintech.tams.valuation.basicdata.mstcalendartype.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.basicdata.mstcalendartype.dto.MstCalendarType002Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * 日历类型管理Service
 *
 * @author liuwei
 * @version 1.0 2018年8月15日
 * @see MstCalendarType002Service
 * @since 1.0
 */

@Service
public class MstCalendarType002Service extends CommonExportService<MstCalendarType002Dto> {
    /**
     * 交易日历名称的明细查询
     *
     * @param map 查询参数
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<MstCalendarType002Dto> selectCanlendarDetail(Map<String, Object> map) {
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/MstCalendarType/selectCanlendarDetail",
                map, MstCalendarType002Dto.class);
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
    public Page<MstCalendarType002Dto> selectPageData(Map<String, Object> selectParam) {
        return selectCanlendarDetail(selectParam);
    }
}
