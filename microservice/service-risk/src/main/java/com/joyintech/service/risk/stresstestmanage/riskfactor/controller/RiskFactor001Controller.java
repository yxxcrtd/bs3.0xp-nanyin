

package com.joyintech.service.risk.stresstestmanage.riskfactor.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.riskfactor.dto.RiskFactor001Dto;

import com.joyintech.service.risk.stresstestmanage.riskfactor.service.RiskFactor001Service;

/**
 * chenyunlong
 *
 * @version 1.0 2019年8月23日
 * @since 1.0
 */
@RestController("RiskFactor001Ctl_dtx")
@RequestMapping("/RiskFactor001")
public class RiskFactor001Controller {

    @Autowired
    private RiskFactor001Service RiskFactor001Service;

    /**
     * chenyunlong
     *  查询因子数据
     * @version 1.0 2019年8月23日
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<RiskFactor001Dto> selectAll(@RequestBody Map<String, Object> param,
                                            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return RiskFactor001Service.selectForPage(page, length, param);
    }
    /**
     * chenyunlong
     *  因子查询中因子名称查询下拉框
     * @version 1.0 2019年8月23日
     * @since 1.0
     */
    @PostMapping("selectRiskFactorMap")
    public List<SelectOptionDto> selectRiskFactorMap(@RequestBody Map<String, Object> param) throws Exception {

        return RiskFactor001Service.selectRiskFactorMap(param);
    }
    /**
     * chenyunlong
     *  因子查询中因子名称查询下拉框历史数据
     * @version 1.0 2019年8月23日
     * @since 1.0
     */
    @PostMapping("selectRiskFactorMap2")
    public List<SelectOptionDto> selectRiskFactorMap2(@RequestBody Map<String, Object> param) throws Exception {

        return RiskFactor001Service.selectRiskFactorMap2(param);
    }
    /**
     * chenyunlong
     *  因子查询页面因子分类下拉框
     * @version 1.0 2019年8月23日
     * @since 1.0
     */

    @PostMapping("selectRiskFactorMapType1")
    public List<SelectOptionDto> selectRiskFactorMapType1(@RequestBody Map<String, Object> param) throws Exception {

        return RiskFactor001Service.selectRiskFactorMapType1(param);
    }
    /**
     * chenyunlong
     *  因子查询页面因子风险分类下拉框-
     * @version 1.0 2019年8月23日
     * @since 1.0
     */
    @PostMapping("selectRiskFactorMapType2")
    public List<SelectOptionDto> selectRiskFactorMapType2(@RequestBody Map<String, Object> param) throws Exception {

        return RiskFactor001Service.selectRiskFactorMapType2(param);
    }
    /**
     * chenyunlong
     *  修改因子启停状态
     * @version 1.0 2019年8月23日
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @DataSource(DataSourceType.STRESSTEST)
    @PostMapping("update")
    public void update(@RequestBody RiskFactor001Dto dto) throws Exception {

        RiskFactor001Service.update(dto);

    }
}
