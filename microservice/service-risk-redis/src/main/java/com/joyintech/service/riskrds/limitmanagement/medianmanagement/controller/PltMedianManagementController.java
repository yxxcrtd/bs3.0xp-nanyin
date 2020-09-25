package com.joyintech.service.riskrds.limitmanagement.medianmanagement.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.riskrds.limitmanagement.medianmanagement.dto.PltMedianManagementDto;
import com.joyintech.service.riskrds.limitmanagement.medianmanagement.service.PltMedianManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
/**
 * 指标参数中间值管理
 * @author zhengjunping
 * @version 1.0 2019年11月19日
 * @see PltMedianManagementController
 * @since 1.0
 */
@RestController("backPltMedianManagementController")
@RequestMapping("/PltMedianManagement")
public class PltMedianManagementController {
    /**注入service
     * 
     */
    @Autowired
    private PltMedianManagementService pltMedianManagementService;

    
    /**
     * 画面查询方法
     * @param param 请求参数
     * @param page 页面
     * @param length 长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<PltMedianManagementDto> selectAll(@RequestBody Map<String, Object> param,
                                                  @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return pltMedianManagementService.selectForPage(page,length,param);
    }
    
    /**
     * 画面查询方法
     *
     * @param param 画面参数
     * @param page 页码数
     * @param length 页面长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("selectMedianManagement/{page}/{length}")
    public Page<SelectOptionDto> selectMedianManagement(@RequestBody Map<String, Object> param,
                                                        @PathVariable("page") int page,
                                                        @PathVariable("length") int length)
        throws Exception {
        return pltMedianManagementService.selectMedianManagement(page, length, param);
    }

}
