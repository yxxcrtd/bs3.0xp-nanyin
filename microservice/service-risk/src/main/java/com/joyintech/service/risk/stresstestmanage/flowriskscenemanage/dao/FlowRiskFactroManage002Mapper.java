/*
 * 文件名：FlowRiskFactroManage002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskSceneManage002Dto;
import com.joyintech.tams.common.base.entity.SttFactorTips;
import com.joyintech.tams.common.base.entity.SttSceneFtrtips;


/**
 * 
 * @author wangkang
 * @version 1.0 2019年8月21日
 * @see FlowRiskFactroManage002Mapper
 * @since 1.0
 */
@Repository
public interface FlowRiskFactroManage002Mapper {
    /**
     * 得到因子
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<FlowRiskSceneManage002Dto> queryIndexByType(Map<String, Object> pageParam);
    /**
     * 根据情景名称查找数据
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<FlowRiskSceneManage002Dto> queryScentNameCount(Map<String, Object> pageParam);
    /**
     * 查询流动性风险的假设情景下的因子
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<FlowRiskSceneManage002Dto> queryFlowSttFactor(Map<String, Object> pageParam);
    /**
     * 根据情景代码得到情景信息
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    FlowRiskSceneManage002Dto querySceneById(Map<String, Object> pageParam);
    /**
     *  根据情景代码查找关联因子
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<FlowRiskSceneManage002Dto> queryFtrBySceId(Map<String, Object> param);
    
    /**
     * 流动性情景模板
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    FlowRiskSceneManage002Dto selectSceneTmpl(Map<String, Object> pageParam);
    
    /**
     * 查找模板关联指标
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<FlowRiskSceneManage002Dto> selectTmplIndByType(Map<String, Object> pageParam);
    
    /**
     * 根据因子代码和子店代码查找标签值
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    SttSceneFtrtips queryNetOutflow(Map<String, Object> pageParam);
    /**
     * 查找情景二级和适用产品
     * @param pageParam
     * @return
     */
    FlowRiskSceneManage002Dto queryScentType2AndMode(Map<String, Object> pageParam);
}
