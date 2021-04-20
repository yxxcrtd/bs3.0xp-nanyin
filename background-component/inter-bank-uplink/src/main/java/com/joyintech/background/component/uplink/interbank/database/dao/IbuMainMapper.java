/*
 * 文件名：IbuMainMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020年5月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.background.component.uplink.interbank.database.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.background.component.uplink.interbank.business.dto.BondQuoteExtDto;
import com.joyintech.background.component.uplink.interbank.business.dto.BondQuoteSearchDto;
import com.joyintech.background.component.uplink.interbank.business.dto.RepoQuoteExtDto;
import com.joyintech.background.component.uplink.interbank.business.dto.RepoQuoteSearchDto;

/**
 * 银行间上行组件主服务专用Mapper接口
 * @author Jiaoxujin
 * @version 1.0 2020年5月12日
 * @see IbuMainMapper
 * @since 1.0
 */
@Repository
public interface IbuMainMapper {
    /**
     * 对话报价查询-指定条件
     * @param inputBean BondQuoteSearchDto
     * @return List<BondQuoteExtDto>
     */
    List<BondQuoteExtDto> selectBondQuoteList(BondQuoteSearchDto inputBean);
    
    /**
     * 质押式回购报价查询-指定条件
     * @param inputBean RepoQuoteSearchDto
     * @return List<RepoQuoteExtDto>
     */
    List<RepoQuoteExtDto> selectRepoQuoteList(RepoQuoteSearchDto inputBean);
    
    /**
     * 现券报价根据成交编号获取委托编号
     * @param execid
     * @return String
     */
    String getBondEntrustIdByExeid(@Param("execid")String execid);
    
    /**
     * 质押式回购根据成交编号获取委托编号
     * @param execid
     * @return String
     */
    String getRepoEntrustIdByExeid(@Param("execid")String execid);

    /**
     * 根据ID列表批量删除现券银行间报价信息
     */
    int deleteQuoteByIds(List<String> idList);
    
    /**
     * 根据ID列表现券银行间报价明细信息
     */
    int deleteQuoteDetByIds(List<String> idList);

    /**
     * 根据ID列表批量删除现券银行间报价历史信息
     */
    int deleteQuoteHisByIds(List<String> idList);
    
    /**
     * 根据ID列表现券银行间报价明细历史信息
     */
    int deleteQuoteDetHisByIds(List<String> idList);
    
    /**
     * 根据ID列表质押品信息
     */
    int deleteQuoteCrepPledgeByIds(List<String> idList);
    
    /**
     * 根据ID列表质押品历史信息
     */
    int deleteQuoteCrepPledgeHisByIds(List<String> idList);
    
    /**
     * 根据ID列表批量删除质押式回购报价信息
     */
    int deleteQuoteCrepByIds(List<String> idList);
    
    /**
     * 根据ID列表批量删除质押式回购报价历史信息
     */
    int deleteQuoteCrepHisByIds(List<String> idList);
    
    /**
     * 获取质押数量
     * @param quoteId String
     * @param quoteDate Date
     * @param pledgeFinprodId String
     * @return BigDecimal
     */
    BigDecimal getPledgeFaceValue(@Param("quoteId")String quoteId,@Param("quoteDate")Date quoteDate,@Param("pledgeFinprodId")String pledgeFinprodId);

    /**
     * 查询已经存在quoteId列表
     */
    List<String> selectQuoteIdsByIdList(List<String> quoteIdList);

    /**
     * 查询已经存在detId列表
     */
    List<String> selectDetIdsByIdList(List<String> detIdList);

    /**
     * 根据报价编号查询现券银行间外部报价编号
     * @param quoteId String
     * @return String
     */
    String selectBondPquoteId(@Param("quoteId")String quoteId);

    /**
     * 根据报价编号查询质押报价外部报价编号
     * @param quoteId String
     * @return String
     */
    String selectCrepPquoteId(@Param("quoteId")String quoteId);
    
    /**
     * 查询现券报价编号
     * @param workflowId
     * @return
     */
	String selectBondQuoteIdByWorkflowId(@Param("workflowId") String workflowId);

	/**
	 * 查询回购报价编号
	 * @param workflowId
	 * @return
	 */
	String selectRepoQuoteIdByWorkflowId(@Param("workflowId") String workflowId);
    
}
