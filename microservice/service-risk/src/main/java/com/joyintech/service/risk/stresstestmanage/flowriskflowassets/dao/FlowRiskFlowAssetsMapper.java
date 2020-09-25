/*
 * 文件名：FlowRiskSceneManageMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.flowriskflowassets.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.risk.stresstestmanage.flowriskflowassets.dto.FlowRiskFlowAssetsDto;



/**
 * 
 * @author wangkang
 * @version 1.0 2019年8月21日
 * @see FlowRiskFlowAssetsMapper
 * @since 1.0
 */
@Repository
public interface FlowRiskFlowAssetsMapper {
    /**
     * 根据参数代码和参数值得到明细的资产类型（一级二级等）
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<FlowRiskFlowAssetsDto> getFloAssTypDetByCodVal(Map<String, Object> map);
    /**
     * 得到一级或二级流动性资产
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<FlowRiskFlowAssetsDto> getFloAssOneOrTwo(Map<String,Object> map);
    /**
     * 得到二级和三级资产树图数据
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<FlowRiskFlowAssetsDto> getFloAssTreeData(Map<String, Object> param);
    /**
     * 根据字典查找流动性映射
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    FlowRiskFlowAssetsDto selectFlowAssetByCode(Map<String,Object> param);
    /**
     * 根据映射代码删除明细
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    int deletSttMapDetByMapId(Map<String,Object> param);
}
