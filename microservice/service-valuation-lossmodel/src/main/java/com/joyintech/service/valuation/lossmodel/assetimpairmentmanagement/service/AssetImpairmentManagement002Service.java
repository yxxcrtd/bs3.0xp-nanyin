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
import com.joyintech.service.valuation.lossmodel.assetimpairmentmanagement.dto.AssetImpairmentManagement002Dto;
import com.joyintech.tams.common.base.dao.ModEclResultMapper;
import com.joyintech.tams.common.base.entity.ModEclResult;
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
    private ModEclResultMapper modEclResultMapper;

    public int findDuplicateData(AssetImpairmentManagement002Dto dto) {
        return assetImpairmentManagement001Mapper.selectDuplicateData(dto);
    }

    @Override
    public void doComplete(AssetImpairmentManagement002Dto dto, OperationType operationType) {
        ModEclResult modEclResult = new ModEclResult();
        if(OperationType.NEW.equals(operationType)){
            BeanUtils.copyProperties(dto, modEclResult);
            modEclResult.setSeqno(dto.getSeqNo());
            modEclResultMapper.insertSelective(modEclResult);
        }else if(OperationType.MODIFY.equals(operationType)){
            BeanUtils.copyProperties(dto, modEclResult);
            modEclResult.setSeqno(dto.getSeqNo());
            modEclResultMapper.updateByPrimaryKeySelective(modEclResult);
        }else if(OperationType.DELETE.equals(operationType)){
            Map<String, Object> params = new HashMap<>();
            params.put("seqNo", dto.getSeqNo());
            AssetImpairmentManagement002Dto entity = assetImpairmentManagement001Mapper.findByPrimaryKey(params);
            modEclResultMapper.deleteByPrimaryKey(entity.getSeqNo());
        }
    }

    /**
     * 获取流程初始操作类型
     *
     * @param
     * @return 产品代码：下拉框
     * @throws Exception
     * @see
     * @since 1.0
     */
    public String getFirstActionType(String workflowId){
        return assetImpairmentManagement001Mapper.getFirstActionType(workflowId);
    }
}
