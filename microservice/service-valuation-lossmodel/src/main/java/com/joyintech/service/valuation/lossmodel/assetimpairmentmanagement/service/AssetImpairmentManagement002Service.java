/*
 * 文件名：AssetImpairmentManagement002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xutong
 * 修改时间：2021-06-24
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.lossmodel.assetimpairmentmanagement.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.service.valuation.lossmodel.assetimpairmentmanagement.dao.AssetImpairmentManagement001Mapper;
import com.joyintech.service.valuation.lossmodel.assetimpairmentmanagement.dao.AssetLossResultMapper;
import com.joyintech.service.valuation.lossmodel.assetimpairmentmanagement.dto.AssetImpairmentManagement002Dto;
import com.joyintech.service.valuation.lossmodel.assetimpairmentmanagement.dto.AssetLossResult;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 资产减值管理
 * @author xutong
 * @createTime 2021-06-24
 * @see AssetImpairmentManagement002Service
 * @since 1.0
 */
@Service(value="serviceAssetImpairmentManagement002Service")
@Transactional(rollbackFor = Exception.class)
public class AssetImpairmentManagement002Service extends CommonExportService<AssetImpairmentManagement002Dto> implements BaseProcessService<AssetImpairmentManagement002Dto> {

    @Autowired
    private AssetImpairmentManagement001Mapper assetImpairmentManagement001Mapper;

    @Autowired
    private AssetLossResultMapper assetLossResultMapper;

    public int findDuplicateData(AssetImpairmentManagement002Dto dto) {
        return assetImpairmentManagement001Mapper.selectDuplicateData(dto);
    }

    @Override
    public void doComplete(AssetImpairmentManagement002Dto dto, OperationType operationType) {
        if(OperationType.NEW.equals(operationType)){
            AssetLossResult assetLossResult = new AssetLossResult();
            BeanUtils.copyProperties(dto, assetLossResult);
            assetLossResult.setSeqno(dto.getSeqNo());
            assetLossResultMapper.insertSelective(assetLossResult);
        }else if(OperationType.MODIFY.equals(operationType)){
            AssetLossResult assetLossResult = new AssetLossResult();
            BeanUtils.copyProperties(dto, assetLossResult);
            assetLossResult.setSeqno(dto.getSeqNo());
            assetLossResultMapper.updateByPrimaryKeySelective(assetLossResult);
        }else if(OperationType.DELETE.equals(operationType)){
            Map<String, Object> params = new HashMap<>();
            params.put("seqNo", dto.getSeqNo());
            AssetImpairmentManagement002Dto entity = assetImpairmentManagement001Mapper.findByPrimaryKey(params);
            assetLossResultMapper.deleteByPrimaryKey(entity.getSeqNo());
        }
    }
}
