/*
 * 文件名：AssetImpairmentManagement002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xutong
 * 修改时间：2021-06-24
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.lossmodel.assetimpairmentkpmgmanagement.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.service.valuation.lossmodel.assetimpairmentkpmgmanagement.dao.AssetImpairmentKpmgManagement001Mapper;
import com.joyintech.service.valuation.lossmodel.assetimpairmentkpmgmanagement.dao.AssetLossResultKpmgMapper;
import com.joyintech.service.valuation.lossmodel.assetimpairmentkpmgmanagement.dto.AssetImpairmentKpmgManagement002Dto;
import com.joyintech.service.valuation.lossmodel.assetimpairmentkpmgmanagement.dto.AssetLossResultKpmg;
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
 * @see AssetImpairmentKpmgManagement002Service
 * @since 1.0
 */
@Service(value="serviceAssetImpairmentKpmgManagement002Service")
@Transactional(rollbackFor = Exception.class)
public class AssetImpairmentKpmgManagement002Service extends CommonExportService<AssetImpairmentKpmgManagement002Dto> implements BaseProcessService<AssetImpairmentKpmgManagement002Dto> {

    @Autowired
    private AssetImpairmentKpmgManagement001Mapper assetImpairmentKpmgManagement001Mapper;

    @Autowired
    private AssetLossResultKpmgMapper assetLossResultKpmgMapper;

    public int findDuplicateData(AssetImpairmentKpmgManagement002Dto dto) {
        return assetImpairmentKpmgManagement001Mapper.selectDuplicateData(dto);
    }

    @Override
    public void doComplete(AssetImpairmentKpmgManagement002Dto dto, OperationType operationType) {
        if(OperationType.NEW.equals(operationType)){
            AssetLossResultKpmg assetLossResult = new AssetLossResultKpmg();
            BeanUtils.copyProperties(dto, assetLossResult);
            assetLossResult.setSeqno(dto.getSeqNo());
            assetLossResultKpmgMapper.insertSelective(assetLossResult);
        }else if(OperationType.MODIFY.equals(operationType)){
            AssetLossResultKpmg assetLossResult = new AssetLossResultKpmg();
            BeanUtils.copyProperties(dto, assetLossResult);
            assetLossResult.setSeqno(dto.getSeqNo());
            assetLossResultKpmgMapper.updateByPrimaryKeySelective(assetLossResult);
        }else if(OperationType.DELETE.equals(operationType)){
            Map<String, Object> params = new HashMap<>();
            params.put("seqNo", dto.getSeqNo());
            AssetImpairmentKpmgManagement002Dto entity = assetImpairmentKpmgManagement001Mapper.findByPrimaryKey(params);
            assetLossResultKpmgMapper.deleteByPrimaryKey(entity.getSeqNo());
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
        return assetLossResultKpmgMapper.getFirstActionType(workflowId);
    }
}
