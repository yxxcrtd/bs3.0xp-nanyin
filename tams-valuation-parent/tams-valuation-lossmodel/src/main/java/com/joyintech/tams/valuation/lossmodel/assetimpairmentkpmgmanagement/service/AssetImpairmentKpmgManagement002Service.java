package com.joyintech.tams.valuation.lossmodel.assetimpairmentkpmgmanagement.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.lossmodel.assetimpairmentkpmgmanagement.dto.AssetImpairmentKpmgManagement002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
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
public class AssetImpairmentKpmgManagement002Service extends CommonExportService<AssetImpairmentKpmgManagement002Dto> implements BaseProcessService<AssetImpairmentKpmgManagement002Dto> {

    @Override
    public void doComplete(AssetImpairmentKpmgManagement002Dto assetImpairmentKpmgManagement002Dto, OperationType operationType) {
        restTemplate.postForObject(
                ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/AssetImpairmentKpmgManagement002/doComplete/" + operationType.name(), assetImpairmentKpmgManagement002Dto,
                AjaxResult.class);
    }

    /**
     * 主键查询
     * @param
     * @return
     */
    public AssetImpairmentKpmgManagement002Dto findByPrimaryKey(String seqNo) {
        return restTemplate.getForObject(
                ServiceApplication.LOSS_MODEL_SERVICE_NAME+"/AssetImpairmentKpmgManagement001/findByPrimaryKey?seqNo=" + seqNo,
                AssetImpairmentKpmgManagement002Dto.class);
    }

    /**
     * 业务编号下拉框
     * @param params
     * @return
     * @throws Exception
     */
    public List<SelectOptionDto> getBusiNoForImpairment(Map<String, Object> params){
        return restTemplate.postForList(ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/AssetImpairmentKpmgManagement001/getBusiNoForImpairment", params, SelectOptionDto.class);
    }

    /**
     * 资产代码下拉框
     * @param params
     * @return
     * @throws Exception
     */
    public List<SelectOptionDto> getFinprodIdForImpairment(Map<String, Object> params){
        return restTemplate.postForList(ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/AssetImpairmentKpmgManagement001/getFinprodIdForImpairment", params, SelectOptionDto.class);
    }

    /**
     * 产品代码下拉框
     * @param params
     * @return
     * @throws Exception
     */
    public List<SelectOptionDto> getProdIdForImpairment(Map<String, Object> params){
        return restTemplate.postForList(ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/AssetImpairmentKpmgManagement001/getProdIdForImpairment", params, SelectOptionDto.class);
    }

    /**
     * 获取流程初始操作类型
     * @param params
     * @return
     * @throws Exception
     */
    public String getFirstActionType(Map<String, Object> params){
        return restTemplate.postForObject(ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/interf/ModelCallOnInvest/getFirstActionType", params, String.class);
    }

    /**
     * 是否存在重复数据
     * @param
     * @return
     * @throws Exception
     */
    public int findDuplicateData(AssetImpairmentKpmgManagement002Dto dto){
        return restTemplate.postForObject(ServiceApplication.LOSS_MODEL_SERVICE_NAME
                + "/AssetImpairmentKpmgManagement002/findDuplicateData", dto, Integer.class);
    }

}
