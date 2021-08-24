package com.joyintech.tams.valuation.lossmodel.assetimpairmentmanagement.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.lossmodel.assetimpairmentmanagement.dto.AssetImpairmentManagement001Dto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 资产减值管理
 * @author xutong
 * @createTime 2021-06-24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AssetImpairmentManagement001Service extends CommonExportService<AssetImpairmentManagement001Dto> {

    public Page<AssetImpairmentManagement001Dto> selectForPage(Map<String, Object> params)
            throws Exception {
        return restTemplate.postForPage(ServiceApplication.LOSS_MODEL_SERVICE_NAME+"/AssetImpairmentManagement001/findPage", params, AssetImpairmentManagement001Dto.class);
    }

    public Page<SelectOptionDto> getBusiNum(Map<String, Object> params)
            throws Exception {
        return restTemplate.postForPage(ServiceApplication.LOSS_MODEL_SERVICE_NAME+"/AssetImpairmentManagement001/getBusiNum", params, SelectOptionDto.class);
    }

    public List<AssetImpairmentManagement001Dto> findPageout(Map<String, Object> params)
            throws Exception {
        return restTemplate.postForList(ServiceApplication.LOSS_MODEL_SERVICE_NAME+"/AssetImpairmentManagement001/findPageout", params, AssetImpairmentManagement001Dto.class);
    }

    /**
     * 资产代码下拉框
     * @param params
     * @return
     * @throws Exception
     */
    public List<SelectOptionDto> getFinprodIdForImpairment(Map<String, Object> params){
        return restTemplate.postForList(ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/AssetImpairmentManagement001/getFinprodIdForImpairment", params, SelectOptionDto.class);
    }

    /**
     * 产品代码下拉框
     * @param params
     * @return
     * @throws Exception
     */
    public List<SelectOptionDto> getProdIdForImpairment(Map<String, Object> params){
        return restTemplate.postForList(ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/AssetImpairmentManagement001/getProdIdForImpairment", params, SelectOptionDto.class);
    }

    /**
     * 资产代码查询-下拉框-减值结果管理
     * @param params
     * @return
     * @throws Exception
     */
    public List<SelectOptionDto> getFinprodIdByLossResult(Map<String, Object> params){
        return restTemplate.postForList(ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/AssetImpairmentManagement001/getFinprodIdByLossResult", params, SelectOptionDto.class);
    }

    /**
     * 产品代码查询-减值结果管理
     * @param params
     * @return
     * @throws Exception
     */
    public List<SelectOptionDto> getProdIdByLossResult(Map<String, Object> params){
        return restTemplate.postForList(ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/AssetImpairmentManagement001/getProdIdByLossResult", params, SelectOptionDto.class);
    }

    /**
     * 有效业务编号查询-减值结果管理
     * @param params
     * @return
     * @throws Exception
     */
    public List<SelectOptionDto> getBusiNumByLossResult(Map<String, Object> params){
        return restTemplate.postForList(ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/AssetImpairmentManagement001/getBusiNumByLossResult", params, SelectOptionDto.class);
    }




}
