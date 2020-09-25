/*
 * 文件名：FlowRiskSceneManage002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.flowriskflowassets.dto.FlowRiskFlowAssetsDto;
import com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskSceneManage002Dto;
import com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.service.FlowRiskSceneManage002Service;
import com.joyintech.tams.common.base.entity.SttFactorTips;
import com.joyintech.tams.common.base.entity.SttSceneFtrtips;

/**
 * 流动性风险-情景新建
 * @author wangkang
 * @version 1.0 2019年8月28日
 * @see FlowRiskSceneManage002Controller
 * @since 1.0
 */
@RestController("FlowRiskSceneManage002Ctl_dtx")
@RequestMapping("/FlowRiskSceneManage002")
public class FlowRiskSceneManage002Controller {

    /**
     * FlowRiskSceneManage002Service
     */
    @Autowired
    private FlowRiskSceneManage002Service flowRiskSceneManage002Service;
    
    /**
     * 根据指标类型获取指标
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/queryIndexByType")
    public List<FlowRiskSceneManage002Dto> queryIndexByType(@RequestBody Map<String, Object> param) throws Exception {
        return flowRiskSceneManage002Service.queryIndexByType(param);
    }
    /**
     * 参照适用产品
     * @param param
     * @return
     */
    @PostMapping("/queryScentType2AndMode")
    public FlowRiskSceneManage002Dto queryScentType2AndMode(@RequestBody Map<String, Object> param){
    	return flowRiskSceneManage002Service.queryScentType2AndMode(param);
    }
    /**
     * 根据情景名称查找数据
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/queryScentNameCount")
    public List<FlowRiskSceneManage002Dto> queryScentNameCount(@RequestBody Map<String, Object> param) throws Exception {
        return flowRiskSceneManage002Service.queryScentNameCount(param);
    }
    /**
     * 根据情景代码得到情景信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/querySceneById")
    public FlowRiskSceneManage002Dto querySceneById(@RequestBody Map<String, Object> param) throws Exception {
        return flowRiskSceneManage002Service.querySceneById(param);
    }
    /**
     * 根据情景代码查找关联因子
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/queryFtrBySceId")
    public List<FlowRiskSceneManage002Dto> queryFtrBySceId(@RequestBody Map<String, Object> param) throws Exception {
        return flowRiskSceneManage002Service.queryFtrBySceId(param);
    }
    
    /**
     * 查询流动性风险的假设情景下的因子
     * <功能详细描述>
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/queryFlowSttFactor")
    public List<FlowRiskSceneManage002Dto> queryFlowSttFactor(@RequestBody Map<String, Object> param) throws Exception {
        return flowRiskSceneManage002Service.queryFlowSttFactor(param);
    }
    /**
     * 查询资产变现能力因子
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/queryFlowSttFactor2")
    public List<FlowRiskSceneManage002Dto> queryFlowSttFactor2(@RequestBody Map<String, Object> param) throws Exception {
        return flowRiskSceneManage002Service.queryFlowSttFactor2(param);
    }
    /**
     * 流动性情景模板
     * <功能详细描述>
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/selectSceneTmpl")
    public FlowRiskSceneManage002Dto selectSceneTmpl(@RequestBody Map<String, Object> param) throws Exception {
        return flowRiskSceneManage002Service.selectSceneTmpl(param);
    }
    /**
     * 查找模板关联指标
     * <功能详细描述>
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/selectTmplIndByType")
    public List<FlowRiskSceneManage002Dto> selectTmplIndByType(@RequestBody Map<String, Object> param) throws Exception {
        return flowRiskSceneManage002Service.selectTmplIndByType(param);
    }
    /**
     * 根据因子代码和字典代码查找标签值
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/queryNetOutflow")
    public SttSceneFtrtips queryNetOutflow(@RequestBody Map<String, Object> param) throws Exception {
        return flowRiskSceneManage002Service.queryNetOutflow(param);
    }
    
    /**
     * 情景新建
     * <功能详细描述>
     * @param dto
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @DataSource(DataSourceType.STRESSTEST)
    @PostMapping("insert")
    public void insert(@RequestBody FlowRiskSceneManage002Dto dto) throws Exception {
        flowRiskSceneManage002Service.insert(dto);
    }
    
    /**
     * 情景更新
     * <功能详细描述>
     * @param dto
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @DataSource(DataSourceType.STRESSTEST)
    @PostMapping("update")
    public void update(@RequestBody FlowRiskSceneManage002Dto dto) throws Exception {
        flowRiskSceneManage002Service.update(dto);
    }
    
    
}
