package com.joyintech.service.chbreport.hxprofit.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.chbreport.hxprofit.service.HXProfitQuery001Service;
import com.joyintech.tams.common.base.entity.HxProfit;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportPrdInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 理财产品资产负债表查询服务
 *
 * @author WanShiwei
 * @version 2.1.6 2021年04月20日
 * @see
 * @since 2.1.6
 */
@RestController("HXProfitQueryCtr_dtx")
@RequestMapping("/HXProfitQuery")
public class HXProfitQueryController {

    /**
     * service
     */
    @Autowired
    private HXProfitQuery001Service hxProfitQuery001Service;

    /**
     * 业务画面查询事件
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ChbreportPrdInfoDto> selectForPage(@RequestBody Map<String, Object> param) throws Exception {
        return hxProfitQuery001Service.selectForPage(param);
    }

    /**
     * 查询导出华夏利润表
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectHXProfitList")
    public List<HxProfit> selectHXProfitList(@RequestBody Map<String, Object> param) throws Exception {
        return hxProfitQuery001Service.selectHXProfitList(param);
    }

}
