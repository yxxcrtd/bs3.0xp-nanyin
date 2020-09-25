/**
 * 
 */
package com.joyintech.service.product.flowforecastreport.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.product.flowforecastreport.dto.FlowCommonDto;
import com.joyintech.service.product.flowforecastreport.service.FlowForecastReportService;

/**
 * @author weipeng
 */
@RestController("flowForecastReportController")
@RequestMapping("/FlowForecastReport")
public class FlowForecastReportController {

    /**
     * {@linkplain}
     */
    @Autowired
    private FlowForecastReportService flowForecastReportService;

    /**
     * 查询流动性储备情况表格
     * 
     * @param param 查询条件
     * @param page 第几页
     * @param length 每页展示个数
     * @return 流动性储备情况信息
     * @throws Exception 异常信息
     */
    @PostMapping("selectReserve/{page}/{length}")
    public Page<FlowCommonDto> selectReserve(@RequestBody Map<String, Object> param,
                                             @PathVariable("page") int page,
                                             @PathVariable("length") int length)
        throws Exception {
        return flowForecastReportService.selectReserve(page, length, param);
    }

    /**
     * 查询流动性预测表格
     * 
     * @param param 查询条件
     * @param page 第几页
     * @param length 每页展示个数
     * @return 流动性预测信息
     * @throws Exception 异常信息
     */
    @PostMapping("selectForcast/{page}/{length}")
    public Page<FlowCommonDto> selectForcast(@RequestBody Map<String, Object> param,
                                             @PathVariable("page") int page,
                                             @PathVariable("length") int length)
        throws Exception {
        return flowForecastReportService.selectForcast(page, length, param);
    }

}
