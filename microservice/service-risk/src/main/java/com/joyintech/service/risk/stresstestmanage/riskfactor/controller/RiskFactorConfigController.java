

package com.joyintech.service.risk.stresstestmanage.riskfactor.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.risk.stresstestmanage.riskfactor.dto.RiskFactor001Dto;
import com.joyintech.service.risk.stresstestmanage.riskfactor.dto.RiskFactor003Dto;
import com.joyintech.service.risk.stresstestmanage.riskfactor.service.RiskFactorConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * chenyunlong
 *
 * @version 1.0 2019年8月23日
 * @since 1.0
 */
@RestController("backRiskFactorConfigController")
@RequestMapping("/RiskFactorConfig")
public class RiskFactorConfigController {

    @Autowired
    private RiskFactorConfigService RiskFactorConfigService;

    /**
     * 查询因子历史数据
     * 〈功能详细描述〉
     *
     * @author chenyunlong
     * @version 1.0 2019年8月23日
     * @see RiskFactorConfigController
     * @since 1.0
     */
    @PostMapping("findPage")
    public List<RiskFactor001Dto> selectAll(@RequestBody Map<String, Object> param) throws Exception {
        return RiskFactorConfigService.selectForPage(param);
    }

}
