/*
 * 文件名：FlowForecastReport001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2019年08月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.flowforecastreport.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.product.flowforecastreport.dto.DateAreaDto;
import com.joyintech.tams.invest.product.flowforecastreport.dto.FlowCommonDto;
import com.joyintech.tams.invest.product.flowforecastreport.service.FlowForecastReport001Service;

/**
 * 流动性预测表
 * 
 * @author weipeng
 * @version 1.0 2019年08月16日
 * @see FlowForecastReport001Controller
 * @since 1.0
 */
@Controller
@PageId("FlowForecastReport001")
@RequestMapping("/invest/product/flowforecastreport/FlowForecastReport001")
public class FlowForecastReport001Controller extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private FlowForecastReport001Service flowForecastReport001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        model.addAttribute("queryDate", DateUtil.getSysDate());
        return super.init(model);
    }

    /**
     * 查询流动性备付情况信息
     * 
     * @return 流动性备付情况信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("selectReserve")
    public Page<FlowCommonDto> selectReserve() {
        return flowForecastReport001Service.selectReserve(RequestUtil.getPageParam());
    }

    /**
     * 查询流动性预测信息
     * 
     * @return 流动性预测信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("selectForcast")
    public Page<FlowCommonDto> selectForcast() {
        return flowForecastReport001Service.selectForcast(RequestUtil.getPageParam());
    }
    
    /**
     * 根据查询日期获取日期区间
     * 
     * @return 日期区间
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getDateAreas")
    public List<DateAreaDto> getDateAreas() {
        Map<String,Object> params = RequestUtil.getPageParam();
        Date queryDate = DateUtil.parse(this.getParam("queryDate"));
        List<DateAreaDto> dateAreas = new ArrayList<>();
        // 区间1开始日 = 画面.查询日
        DateAreaDto dateAreaDto1 = new DateAreaDto();
        dateAreaDto1.setBeginDate(queryDate);
        // 区间1结束日 = 区间1开始日+6
        dateAreaDto1.setEndDate(DateUtil.addDay(dateAreaDto1.getBeginDate(), 6));
        dateAreas.add(dateAreaDto1);

        // 区间2开始日 = 区间1结束日+1
        DateAreaDto dateAreaDto2 = new DateAreaDto();
        dateAreaDto2.setBeginDate(DateUtil.addDay(dateAreaDto1.getEndDate(), 1));
        // 区间2结束日 = 区间2开始日+6
        dateAreaDto2.setEndDate(DateUtil.addDay(dateAreaDto2.getBeginDate(), 6));
        dateAreas.add(dateAreaDto2);

        // 区间3开始日 = 区间2结束日+1
        DateAreaDto dateAreaDto3 = new DateAreaDto();
        dateAreaDto3.setBeginDate(DateUtil.addDay(dateAreaDto2.getEndDate(), 1));
        // 区间3结束日 = 区间3开始日+6
        dateAreaDto3.setEndDate(DateUtil.addDay(dateAreaDto3.getBeginDate(), 6));
        dateAreas.add(dateAreaDto3);

        // 区间4开始日 = 区间3结束日+1
        DateAreaDto dateAreaDto4 = new DateAreaDto();
        dateAreaDto4.setBeginDate(DateUtil.addDay(dateAreaDto3.getEndDate(), 1));
        // IF(区间3结束日当月月末 - 区间3结束日) >=5
        if (DateUtil.compareDate(DateUtil.getLastDay(dateAreaDto3.getEndDate()),
            DateUtil.addDay(dateAreaDto3.getEndDate(), 5)) >= 0) {
            // 区间4结束日 = 区间4开始日当月月末
            dateAreaDto4.setEndDate(DateUtil.getLastDay(dateAreaDto4.getBeginDate()));
        }
        else {
            // 区间4结束日 = 区间4开始日次月月末
            Calendar calendar = Calendar.getInstance();
            // 把平台日期赋值给Calendar
            calendar.setTime(dateAreaDto4.getBeginDate());
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
            // 月份+1
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
            int endday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            // 取月末
            calendar.set(Calendar.DAY_OF_MONTH, endday);
            dateAreaDto4.setEndDate(calendar.getTime());
        }
        dateAreas.add(dateAreaDto4);

        // 区间5开始日 = 区间4结束日+1
        DateAreaDto dateAreaDto5 = new DateAreaDto();
        dateAreaDto5.setBeginDate(DateUtil.addDay(dateAreaDto4.getEndDate(), 1));
        // 区间5结束日 = 区间5开始日当月月末
        dateAreaDto5.setEndDate(DateUtil.getLastDay(dateAreaDto5.getBeginDate()));
        dateAreas.add(dateAreaDto5);

        // 区间6开始日 = 区间5结束日+1
        DateAreaDto dateAreaDto6 = new DateAreaDto();
        dateAreaDto6.setBeginDate(DateUtil.addDay(dateAreaDto5.getEndDate(), 1));
        // 区间6结束日 = 区间6开始日当月月末
        dateAreaDto6.setEndDate(DateUtil.getLastDay(dateAreaDto6.getBeginDate()));
        dateAreas.add(dateAreaDto6);

        // 区间x(1~6) = 区间x开始日(m.d) + "-" + 区间x结束日(m.d)
        for (DateAreaDto dto : dateAreas) {
            dto.setDateArea(DateUtil.format(dto.getBeginDate(), "M.dd") + "-"
                            + DateUtil.format(dto.getEndDate(), "M.dd"));
        }

        // 区间1-3合计 = 区间1开始日-区间3结束日
        DateAreaDto dateAreaDto7 = new DateAreaDto();
        dateAreaDto7.setBeginDate(dateAreaDto1.getBeginDate());
        dateAreaDto7.setEndDate(dateAreaDto3.getEndDate());
        dateAreaDto7.setDateArea(DateUtil.format(dateAreaDto7.getBeginDate(), "M.dd") + "-"
            + DateUtil.format(dateAreaDto7.getEndDate(), "M.dd")+"合计");
        dateAreas.add(dateAreaDto7);
        
        // 区间1-4合计 = 区间1开始日-区间4结束日
        DateAreaDto dateAreaDto8 = new DateAreaDto();
        dateAreaDto8.setBeginDate(dateAreaDto1.getBeginDate());
        dateAreaDto8.setEndDate(dateAreaDto4.getEndDate());
        dateAreaDto8.setDateArea(DateUtil.format(dateAreaDto8.getBeginDate(), "M.dd") + "-"
            + DateUtil.format(dateAreaDto8.getEndDate(), "M.dd")+"合计");
        dateAreas.add(dateAreaDto8);
        
        // 区间1-5合计 = 区间1开始日-区间5结束日
        DateAreaDto dateAreaDto9 = new DateAreaDto();
        dateAreaDto9.setBeginDate(dateAreaDto1.getBeginDate());
        dateAreaDto9.setEndDate(dateAreaDto5.getEndDate());
        dateAreaDto9.setDateArea(DateUtil.format(dateAreaDto9.getBeginDate(), "M.dd") + "-"
            + DateUtil.format(dateAreaDto9.getEndDate(), "M.dd")+"合计");
        dateAreas.add(dateAreaDto9);
        

        // 区间1-6合计 = 区间1开始日-区间6结束日
        DateAreaDto dateAreaDto10 = new DateAreaDto();
        dateAreaDto10.setBeginDate(dateAreaDto1.getBeginDate());
        dateAreaDto10.setEndDate(dateAreaDto6.getEndDate());
        dateAreaDto10.setDateArea(DateUtil.format(dateAreaDto1.getBeginDate(), "M.dd") + "-"
            + DateUtil.format(dateAreaDto10.getEndDate(), "M.dd")+"合计");
        dateAreas.add(dateAreaDto10);
        
        // 返回区间
        return dateAreas;
    }
}
