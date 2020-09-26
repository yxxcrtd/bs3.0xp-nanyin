/*
 * 文件名：AccountingMethodMapper.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2017年1月3日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.batch.common.accountingmethod.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.joyintech.tams.common.base.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.AccountEntry;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.SceneConfigEntry;


/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author qiangchengxi
 * @version 1.0 2017年1月3日
 * @see AccountingMethodMapper
 * @since 1.0
 */
@Repository
public interface AccountingMethodMapper {

    /**
     * 根据账套代码获取节点序号
     * 
     * @param booksetId 账套代码
     * @return 节点序号
     * @see
     * @since 1.0
     */
    String findNodeNoByBooksetId(String booksetId);

    /**
     * 根据会计场景代码获取某账套下的场景分支配置信息
     * 
     * @param accountSceneCodes 本批次批处理任务记账时需要使用的全部会计场景代码
     * @param booksetId 账套代码
     * @param accountevents ACCOUNTEVENTS
     * @return 会计场景配置信息
     * @see
     * @since 1.0
     */
    List<AccountEntry> getSceneSubjectConfigByNodeId(@Param(
        value = "booksetId") String booksetId, @Param(
            value = "accountSceneCodes") List<String> accountSceneCodes, @Param(
                value = "ACCOUNTEVENTS") String accountevents);

    /**
     * 根据会计场景代码 批量查询会计场景 配置信息
     * 
     * @param accountSceneCodes 本批次批处理任务记账时需要使用的全部会计场景代码
     * @param booksetId 账套代码
     * @param accountevents ACCOUNTEVENTS
     * @return 会计场景配置信息
     * @see
     * @since 1.0
     */
    List<SceneConfigEntry> getSceneConfigByNodeId(@Param(
        value = "booksetId") String booksetId, @Param(
            value = "accountSceneCodes") List<String> accountSceneCodes, @Param(
                value = "ACCOUNTEVENTS") String accountevents);

    /**
     * 
     * 获取匹配信息
     * @param matchType 匹配类型
     * @param source 源
     * @param acctStandard 会计准则
     * @return 匹配信息
     * @see
     * @since 1.0
     */
    List<BokMatchRlat> selectValMatchRlatBySource(@Param(value = "matchTypeList") List<String> matchTypeList,
                                                  @Param(value = "source") String source,
                                                  @Param(value = "acctStandard") String acctStandard);
    
    /**
     * 获取跑批范围信息
     * @param param 参数
     * @return 跑批范围信息
     * @see
     * @since 1.0
     */
    List<BokBatchTaskTodo> getTaskTodoScopeInfo(Map<String, Object> param);
    
    /**
     * 获取交易方向优先级
     * @param param 参数
     * @return 跑批范围信息
     * @see
     * @since 1.0
     */
    List<BokTradeOrder> selectValTradeOrder(Map<String, Object> param);
    /**
     * 获取帐套信息
     * @param bookSetIds 参数
     * @return 帐套信息
     * @see
     * @since 1.0
     */
    List<BokBookset> selectBookSetIdInfo(@Param(value = "bookSetIds") Set<String> bookSetIds);
    
	/**
     * 
     * 获取账务明细代码
     * @param booksetId 账套代码
     * @param busiId 业务明细代码
     * @param busiDetailType 业务明细类型
     * @return 匹配信息
     * @see
     * @since 1.0
     */
    BokDetailId selectBokDetailId(@Param("booksetId") String booksetId,
    		                             @Param("busiId") String busiId, 
    		                             @Param("busiDetailType") String busiDetailType);
										 
    /**
     * 获取帐套信息
     * @param param 参数
     * @return 帐套信息
     * @see
     * @since 1.0
     */
    List<BokBookset> getBooksetInfoByParam(Map<String, Object> param);

    /**
     * 根据条件删除【统计分析明细表（账务明细）】数据，条件为不包含【处理序列】的主键。
     *
     * @param key
     * @return
     */
    int deleteBokStatDetPosiiton(BokStatDetPosiitonKey key);
    
    /**
     * 查询批处理待执行任务中的账套代码List
     * @param params 参数
     * @return 账套代码List
     * @see
     * @since 1.0
     */
    List<String> getBatchExcuteProdList(Map<String, Object> params);
}
