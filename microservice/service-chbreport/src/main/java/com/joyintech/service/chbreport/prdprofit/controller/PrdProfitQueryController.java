package com.joyintech.service.chbreport.prdprofit.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.chbreport.prdprofit.service.PrdProfitQuery001Service;
import com.joyintech.tams.common.base.entity.PrdProfit;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportPrdInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 理财产品利润表查询服务
 *
 * @author WanShiwei
 * @version 2.1.6 2021年04月20日
 * @see
 * @since 2.1.6
 */
@RestController("PrdProfitQueryCtr_dtx")
@RequestMapping("/PrdProfitQuery")
public class PrdProfitQueryController {

    /**
     * service
     */
    @Autowired
    private PrdProfitQuery001Service prdProfitQuery001Service;

    /**
     * 业务画面查询事件
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ChbreportPrdInfoDto> selectForPage(@RequestBody Map<String, Object> param) throws Exception {
        return prdProfitQuery001Service.selectForPage(param);
    }

    /**
     * 查询产品利润表导出数据
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectFinAssetLiabilityList")
    public List<PrdProfit> selectFinAssetLiabilityList(@RequestBody Map<String, Object> param) throws Exception {
        return prdProfitQuery001Service.selectPrdProfitList(param);
    }
}
