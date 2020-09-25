/*
 * 文件名：PtlFutureCashPosiService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlfuturecashposi.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.ptlfuturecashposi.dto.PtlFutureCashPosiDto;

/**
 * 组合未来头寸分析头寸
 * 
 * @author renping
 * @version 1.0 2019年11月9日
 * @see PtlFutureCashPosiService
 * @since 1.0
 */
@Service
public class PtlFutureCashPosiService extends CommonExportService<PtlFutureCashPosiDto> {

    /**
     * 节假日计算用和日期关联的Service
     */
    @Autowired
    private DateCalService dateCalService;

    /**
     * 页面查询
     * 
     * @param pageParam 查询方法
     * @return Page类
     * @throws Exception 异常信息
     */
    public List<PtlFutureCashPosiDto> selectData(Map<String, Object> pageParam) {
        String portfolioId = (String)pageParam.get("portfolioId");
        if (StringUtil.isNotEmpty(portfolioId)) {
            List<String> portfolioIdList = StringUtil.splitToList(",", portfolioId);
            pageParam.put("portfolioIdList", portfolioIdList);
        }
        String prodManager = (String)pageParam.get("prodManager");
        if (StringUtil.isNotEmpty(prodManager)) {
            List<String> prodManagerList = StringUtil.splitToList(",", prodManager);
            pageParam.put("prodManagerList", prodManagerList);
        }
        else {
            pageParam.put("prodManager", null);
        }
        setDateArrayList(pageParam);
        return restTemplate.postForList(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlFutureCashPosi/selectData", pageParam,
            PtlFutureCashPosiDto.class);
    }

    /**
     * 日期参数
     * 
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    private void setDateArrayList(Map<String, Object> pageParam) {
        List<String> dateArrayList = new ArrayList<String>();
        if (StringUtil.equals(pageParam.get("showDimension"),
            DictCodeListExt3.展示维度_短期.getValue())) {
            Date sysDate = DateUtil.platDateFromCache();
            String morenDate = this.getNextWorkDay(sysDate);
            pageParam.put("cdate", DateUtil.format(sysDate));
            dateArrayList.add(DateUtil.addDay(morenDate, -1));
            dateArrayList.add(morenDate);
            dateArrayList.add(DateUtil.addDay(morenDate, 6));
            dateArrayList.add(DateUtil.addDay(morenDate, 29));
            pageParam.put("startDate", morenDate);
            pageParam.put("endDate", DateUtil.addDay(morenDate, 29));
        }
        else {
            String cdate = (String)pageParam.get("cdate");
            Date sysDate = DateUtil.platDateFromCache();
            if (StringUtil.equals(pageParam.get("showDimension"),
                DictCodeListExt3.展示维度_日.getValue())) {
                dateArrayList.add(DateUtil.format(sysDate));
                String morenDate = this.getNextWorkDay(sysDate);
                while (morenDate.compareTo(cdate) <= 0) {
                    dateArrayList.add(morenDate);
                    morenDate = this.getNextWorkDay(DateUtil.parse(morenDate));
                }
                pageParam.put("cdate", DateUtil.format(sysDate));
                pageParam.put("startDate", DateUtil.format(DateUtil.addDay(sysDate, 1)));
                pageParam.put("endDate", dateArrayList.get(dateArrayList.size() - 1));
            }
            else if (StringUtil.equals(pageParam.get("showDimension"),
                DictCodeListExt3.展示维度_周.getValue())) {
                dateArrayList.add(DateUtil.format(sysDate));
                String morenDate = this.getNextSunDay(sysDate);
                while (morenDate.compareTo(cdate) <= 0) {
                    dateArrayList.add(morenDate);
                    morenDate = this.getNextSunDay(DateUtil.parse(morenDate));
                }
                if (dateArrayList.get(dateArrayList.size() - 1).compareTo(cdate) != 0) {
                    dateArrayList.add(cdate);
                }
                pageParam.put("cdate", DateUtil.format(sysDate));
                pageParam.put("startDate", DateUtil.format(DateUtil.addDay(sysDate, 1)));
                pageParam.put("endDate", dateArrayList.get(dateArrayList.size() - 1));
            }
            else if (StringUtil.equals(pageParam.get("showDimension"),
                DictCodeListExt3.展示维度_月.getValue())) {
                dateArrayList.add(DateUtil.format(sysDate));
                String morenDate = this.getLastDayOfMonth(sysDate);
                while (morenDate.compareTo(cdate) <= 0) {
                    dateArrayList.add(morenDate);
                    morenDate = this.getLastDayOfMonth(DateUtil.parse(morenDate));
                }
                if (dateArrayList.get(dateArrayList.size() - 1).compareTo(cdate) != 0) {
                    dateArrayList.add(cdate);
                }
                pageParam.put("cdate", DateUtil.format(sysDate));
                pageParam.put("startDate", DateUtil.format(DateUtil.addDay(sysDate, 1)));
                pageParam.put("endDate", dateArrayList.get(dateArrayList.size() - 1));
            }
        }
        pageParam.put("dateArrayList", dateArrayList);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/portfolio/ptlfuturecashposi/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<PtlFutureCashPosiDto> selectPageData(Map<String, Object> selectParam) {
        Page<PtlFutureCashPosiDto> page = new Page<>();
        page.setRoot(selectData(selectParam));
        return page;
    }
    /**
     * 设置导出单元格
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if (StringUtil.equals(propName, "settleDate")) {
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }

    /**
     * 获取下一个工作日
     *
     * @param parameters 画面参数
     * @return 下一个工作日
     * @see
     * @since 1.0
     */
    public String getNextWorkDay(Date platDate) {
        List<String> holiday = dateCalService.getDealCalendarHoliday(platDate,
            DateUtil.addDays(platDate, 15), DictCodeListExt3.交易日历_中国法定节假日.getValue());
        Date tradingDay = dateCalService.calculationTradingDay(platDate, 1, holiday, "1");
        return DateUtil.format(tradingDay);
    }

    /**
     * 获取下一个周日
     *
     * @param parameters 画面参数
     * @return 周日
     * @see
     * @since 1.0
     */
    public String getNextSunDay(Date platDate) {
        Date date = platDate;
        do {
            date = DateUtil.addDay(date, 1);
        }
        while (DateUtil.getWeekDay(date) != 7);
        return DateUtil.format(date);
    }

    /**
     * 获取下一个月末
     *
     * @param parameters 画面参数
     * @return 下一个工作日
     * @see
     * @since 1.0
     */
    public String getLastDayOfMonth(Date platDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.addDay(platDate, 1));
        int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return DateUtil.format(cal.getTime());
    }

}
