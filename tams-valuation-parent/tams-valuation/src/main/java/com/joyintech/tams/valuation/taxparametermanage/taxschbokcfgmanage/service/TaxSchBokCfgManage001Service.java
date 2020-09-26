package com.joyintech.tams.valuation.taxparametermanage.taxschbokcfgmanage.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.taxparametermanage.taxschbokcfgmanage.dto.TaxSchBokCfgManage001Dto;

/**
 * @author zhangwei
 * @createTime 2019-07-13 13:57
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TaxSchBokCfgManage001Service extends CommonExportService<TaxSchBokCfgManage001Dto> {


    /**
     * 获取会计场景计税配置 - 分页
     * @param pageParam
     * @return
     */
    public Page<TaxSchBokCfgManage001Dto> findTaxSchaBokConfigByPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxSchBokCfgManage001/findTaxSchaBokConfig",
            pageParam, TaxSchBokCfgManage001Dto.class);
    }

    /**
     * 查询参数设置 - 不分页
     * @param pageParam
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public List<TaxSchBokCfgManage001Dto> findTaxSchaBokConfig(Map<String, Object> pageParam) throws Exception {
        return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxSchBokCfgManage001/findTaxSchaBokConfig",
            pageParam, TaxSchBokCfgManage001Dto.class);
    }

    /**
     * 统计配置使用数
     * @param configId
     * @return
     */
    public int countUsed(String configId) {
        return restTemplate.getForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/TaxSchBokCfgManage001/countUsed?configId=" + configId,
            Integer.class);
    }
}
