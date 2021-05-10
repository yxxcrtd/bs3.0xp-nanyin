package com.joyintech.tams.valuation.trdassetlossmanage.trdassetlossbalance.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trdassetlossmanage.trdassetlossbalance.dto.TrdAssetLossBalance001Dto;
import com.joyintech.tams.valuation.trdassetlossmanage.trdassetlossbalance.dto.TrdAssetLossBalance002Dto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TrdAssetLossBalance001Service  extends CommonExportService<TrdAssetLossBalance001Dto> {

    /**
     * 查询画面
     * @param params
     * @return
     * @throws Exception
     */
    public Page<TrdAssetLossBalance001Dto> queryPageList(Map<String, Object> params) throws Exception {
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdAssetLossBalance001/findPage",
                params, TrdAssetLossBalance001Dto.class);
    }

    /**
     * 明细查询
     * @param params
     * @return
     * @throws Exception
     */
    public Page<TrdAssetLossBalance002Dto> queryDetailList(Map<String, Object> params) throws Exception {
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdAssetLossBalance002/findPage",
                params, TrdAssetLossBalance002Dto.class);
    }

    /**
     * 查询资产代码
     */
    public Page<SelectOptionDto> getFinprodId(Map<String, Object> params){
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdAssetLossBalance001/getFinprodId",
                params, SelectOptionDto.class);
    }

    /**
     * 查询账套代码
     */
    public Page<SelectOptionDto> getBooksetId(Map<String, Object> params){
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdAssetLossBalance001/getBooksetId",
                params, SelectOptionDto.class);
    }

    /**
     * 导出数据查询
     */
    public List<TrdAssetLossBalance001Dto> getAllList(Map<String, Object> params){
        return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdAssetLossBalance001/getAllList",
                params, TrdAssetLossBalance001Dto.class);
    }

}
