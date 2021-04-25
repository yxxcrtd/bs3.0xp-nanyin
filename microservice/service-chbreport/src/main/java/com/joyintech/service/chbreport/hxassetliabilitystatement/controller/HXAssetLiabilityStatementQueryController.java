package com.joyintech.service.chbreport.hxassetliabilitystatement.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.chbreport.hxassetliabilitystatement.service.HXAssetLiabilityStatementQuery001Service;
import com.joyintech.tams.common.base.entity.HxBalance;
import com.joyintech.tams.svreport.chbreport.hxassetliabilitystatement.dto.HXAssetLiabilityStatementQuery001Dto;
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
@RestController("HXAssetLiabilityStatementQueryCtr_dtx")
@RequestMapping("/HXAssetLiabilityStatementQuery")
public class HXAssetLiabilityStatementQueryController {

    /**
     * service
     */
    @Autowired
    private HXAssetLiabilityStatementQuery001Service hxAssetLiabilityStatementQuery001Service;

    /**
     * 业务画面查询事件
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<HXAssetLiabilityStatementQuery001Dto> selectForPage(@RequestBody Map<String, Object> param) throws Exception {
        return hxAssetLiabilityStatementQuery001Service.selectForPage(param);
    }

    /**
     * 查询导出华夏资产负债表
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectHXAssetLiabilityList")
    public List<HxBalance> selectHXAssetLiabilityList(@RequestBody Map<String, Object> param) throws Exception {
        return hxAssetLiabilityStatementQuery001Service.selectHXAssetLiabilityList(param);
    }

}
