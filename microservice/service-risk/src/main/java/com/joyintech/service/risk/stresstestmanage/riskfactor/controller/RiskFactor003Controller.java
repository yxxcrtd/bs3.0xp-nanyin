

package com.joyintech.service.risk.stresstestmanage.riskfactor.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.stresstestmanage.riskfactor.dao.RiskFactor003Mapper;
import com.joyintech.service.risk.stresstestmanage.riskfactor.dto.RiskFactor001Dto;
import com.joyintech.service.risk.stresstestmanage.riskfactor.dto.RiskFactor003Dto;
import com.joyintech.service.risk.stresstestmanage.riskfactor.dto.RiskFactor003selectDto;
import com.joyintech.service.risk.stresstestmanage.riskfactor.service.RiskFactor001Service;
import com.joyintech.service.risk.stresstestmanage.riskfactor.service.RiskFactor003Service;

/**
 * chenyunlong
 *
 * @version 1.0 2019年8月23日
 * @since 1.0
 */
@RestController
@RequestMapping("/RiskFactor003")
public class RiskFactor003Controller {

    @Autowired
    private RiskFactor003Service RiskFactor001Service;

    /**
     * 查询因子历史数据
     * 〈功能详细描述〉
     *
     * @author chenyunlong
     * @version 1.0 2019年8月23日
     * @see RiskFactor003Controller
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<RiskFactor003Dto> selectAll(@RequestBody Map<String, Object> param,
                                            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return RiskFactor001Service.selectForPage(page, length, param);
    }
    /**
     * 查询因子历史数据时间范围
     * 〈功能详细描述〉
     *
     * @author chenyunlong
     * @version 1.0 2019年8月23日
     * @see RiskFactor003Controller
     * @since 1.0
     */
    @PostMapping("selectdate")
    public RiskFactor003Dto selectdate(@RequestBody Map<String, Object> param) throws Exception {
        return RiskFactor001Service.selectdto(param);
    }

}
