package com.joyintech.service.chbreport.finassetliabilitystatement.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.chbreport.finassetliabilitystatement.service.FinAssetLiabilityStatementQuery001Service;
import com.joyintech.tams.common.base.entity.PrdBalance;
import com.joyintech.tams.svreport.chbreport.finassetliabilitystatement.dto.FinAssetLiabilityStatementQuery001Dto;
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
@RestController("FinAssetLiabilityStatementQueryCtr_dtx")
@RequestMapping("/FinAssetLiabilityStatementQuery")
public class FinAssetLiabilityStatementQueryController {

    /**
     * service
     */
    @Autowired
    private FinAssetLiabilityStatementQuery001Service finAssetLiabilityStatementQuery001Service;

    /**
     * 产品代码下拉框取得值
     *
     * @param map
     * @return 下拉框值
     * @see
     * @since 1.0
     */
    @PostMapping("getPrdBaseinfo")
    public List<SelectOptionDto> getPrdBaseinfo(@RequestBody Map<String, Object> map) {
        return finAssetLiabilityStatementQuery001Service.getPrdBaseinfo(map);
    }

    /**
     * 业务画面查询事件
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<FinAssetLiabilityStatementQuery001Dto> selectForPage(@RequestBody Map<String, Object> param) throws Exception {
        return finAssetLiabilityStatementQuery001Service.selectForPage(param);
    }

    /**
     * 查询产品资产负债表导出数据
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectFinAssetLiabilityList")
    public List<PrdBalance> selectFinAssetLiabilityList(@RequestBody Map<String, Object> param) throws Exception {
        return finAssetLiabilityStatementQuery001Service.selectFinAssetLiabilityList(param);
    }
}
