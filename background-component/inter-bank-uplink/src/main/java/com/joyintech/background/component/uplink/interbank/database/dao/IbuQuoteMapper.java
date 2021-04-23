/*
 * 文件名：IbuQuoteMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020年5月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.uplink.interbank.database.dao;

import com.joyintech.background.component.uplink.interbank.business.dto.*;
import com.joyintech.background.component.uplink.interbank.business.dto.local.BondQuoteHisSearchDto;
import com.joyintech.background.component.uplink.interbank.database.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 报价服务MyBatis接口
 * @author Jiaoxujin
 * @version 1.0 2020年5月9日
 * @see IbuQuoteMapper
 * @since 1.0
 */
@Repository
public interface IbuQuoteMapper {
    /**
     * 根据报价编号查询现券银行间报价信息
     * @param quoteId String
     * @return IbuQuoteBondDto
     */
    IbuQuoteBondDto selectQuoteBondByKey(@Param("quoteId")String quoteId);

    /**
     * 根据报价编号列表查询现券银行间报价信息列表
     * @param quoteIds List<String>
     * @return List<IbuQuoteBondDto>
     */
    List<IbuQuoteBondDto> selectQuoteBondByKeys(List<String> quoteIds);

    /**
     * 根据报价编号查询现券银行间报价明细信息
     * @param quoteId String
     * @return IbuQuoteBondDetDto
     */
    IbuQuoteBondDetDto selectByQuoteId(@Param("quoteId")String quoteId);
    
    /**
     * 根据报价编号查询现券银行间报价明细信息
     * @param quoteId String
     * @return IbuQuoteBondDetDto
     */
    List<IbuQuoteBondDetDto> selectByQuoteListId(String quoteId);
    
    /**
     * 根据报价编号查询查询质押式回购对话报价信息
     * @param quoteId String
     * @return IbuQuoteCrepDto
     */
    IbuQuoteCrepDto selectQuoteCrepByKey(String quoteId);

    /**
     * 根据报价编号列表查询质押式回购报价信息列表
     * @param quoteIds List<String>
     * @return List<IbuQuoteCrepDto>
     */
    List<IbuQuoteCrepDto> selectQuoteCrepByKeys(List<String> quoteIds);

    /**
     * 根据报价编号查询查询质押式回购报价质押品信息
     * @param quoteId String
     * @return IbuQuoteCrepPledgeDto
     */
    List<IbuQuoteCrepPledgeDto> selectQuoteCrepPledgeByKey(String quoteId);
    
    /**
     * 
     * 根据外部请求编号查询请求报价明细信息
     * @param reqId String
     * @return IbuQuoteBondDetDto
     * @see
     * @since 1.0
     */
    IbuQuoteBondDetDto selectQuoteDetByReqId(String reqId);
    
    /**
     * 根据报价明细编号删除现券银行间报价明细数据
     * @param detId String
     * @return int
     */
    int deleteQuoteDetByDetId(String detId);

    /**
     * 根据报价明细编号列表删除现券银行间报价明细数据列表
     * @param detIdList List<String>
     * @return int
     */
    int deleteQuoteDetByDetIds(List<String> detIdList);

    /**
     * 根据报价编号删除现券银行间报价明细数据列表
     * @param quoteId String
     * @return int
     */
    int deleteQuoteDetByQuoteId(String quoteId);
    
    /**
     * 根据报价编号删除现券银行间报价明细历史数据列表
     * @param quoteId String
     * @return int
     */
    int deleteQuoteDetHisByQuoteId(String quoteId);

    /**
     * 根据报价明细编号删除现券银行间报价明细历史数据
     * @param detId String
     * @return int
     */
    int deleteQuoteDetHisByDetId(String detId);

    /**
     * 根据报价明细编号列表删除现券银行间报价明细历史数据列表
     * @param detIdList List<String>
     * @return int
     */
    int deleteQuoteDetHisByDetIds(List<String> detIdList);

    /**
     * 根据报价编号删除质押式回购报价质押品历史数据列表
     * @param quoteId String
     * @return int
     */
    int deleteCrepPledgeHisByQuoteId(String quoteId);

    /**
     * 历史对话报价查询-指定条件
     * @param inputBean BondQuoteLocalSearchDto
     * @return List<BondDialogueQuoteDto>
     */
    List<BondDialogueQuoteDto> selectBondDialogueQuoteHisList(BondQuoteHisSearchDto inputBean);
    
    /**
     * 历史请求报价查询-指定条件
     * @param inputBean BondQuoteHisSearchDto
     * @return List<BondRequestQuoteDto>
     */
    List<BondRequestQuoteDto> selectBondRequestQuoteHisList(BondQuoteHisSearchDto inputBean);
    
    /**
     * 历史质押式回购报价查询-指定条件
     * @param inputBean BondQuoteLocalSearchDto
     * @return List<RepoDialogueQuoteDto>
     */
    List<RepoDialogueQuoteDto> selectRepoDialogueQuoteHisList(BondQuoteHisSearchDto inputBean);

    /**
     * 批量对话报价新增
     * @param inputList List<BondDialogueQuoteDto>
     * @return int
     */
    int batchInsertBondDialogueQuote(@Param("inputList") List<BondDialogueQuoteDto> inputList, @Param("tableSuffix") String tableSuffix);

    /**
     * 根据对话报价编号修改报价明细表的版本号
     * @param quoteBondDet IbuQuoteBondDet
     * @return int
     */
    int updateDialogueDetVersionByQuoteId(IbuQuoteBondDet quoteBondDet);

    /**
     * 批量请求报价新增
     * @param inputList List<BondRequestQuoteDto>
     * @return int
     */
    int batchInsertBondRequestQuote(@Param("inputList") List<BondRequestQuoteDto> inputList, @Param("tableSuffix") String tableSuffix);
    
    /**
     * 批量质押式回购报价新增
     * @param inputList List<RepoDialogueQuoteDto>
     * @return int
     */
    int batchInsertRepoDialogueQuote(@Param("inputList") List<RepoDialogueQuoteDto> inputList, @Param("tableSuffix") String tableSuffix);
    
    /**
     * 批量请求报价明细新增
     * @param inputList List<IbuQuoteBondDetDto>
     * @return int
     */
    int batchInsetIbuQuoteBondDetDto(@Param("inputList") List<IbuQuoteBondDetDto> inputList);
    
    /**
     * 批量请求报价明细历史新增
     * @param inputList List<IbuQuoteBondDetDto>
     * @return int
     */
    int batchInsetIbuQuoteBondDetHis(@Param("inputList") List<IbuQuoteBondDetHis> inputList);

    /**
     * 查询明细表中是否存在至少一条（已回复）？true: 更新主表交易状态为已回复， false:更新主表交易状态为待回复
     * @param dto IbuQuoteBond
     * @return int
     */
    int updateBondQuoteTradeStatus(IbuQuoteBond dto);

    /**
     * 批量回购报价质押品历史新增
     * @param inputList List<IbuQuoteCrepPledgeHis>
     * @return int
     */
    int batchInsetIbuQuoteCrepPledgeHis(@Param("inputList") List<IbuQuoteCrepPledgeHis> inputList);
    
    /**
     * 根据报价编号查询质押品明细数据
     * @param quoteId String
     * @return IbuQuoteBondDet
     */
    List<IbuQuoteCrepPledge> selectPledgeByQuoteId(String quoteId);
    
    /**
     * 
     * 根据报价编号查询现券银行间请求报价列表
     * @param quoteId
     * @return IbuQuoteBondDet
     * @see
     * @since 1.0
     */
    List<IbuQuoteBondDet> selectIbuQuoteBondDet(String quoteId);
    
    /**
     * 
     * 批量插入质押品信息
     * <功能详细描述>
     * @param pledgeList
     * @return
     * @see
     * @since 1.0
     */
    int insertPledgeList(@Param("pledgeList")List<IbuQuoteCrepPledgeDto> pledgeList);
    
    /**
     * 根据报价编号删除质押品数据列表
     * @param quoteId String
     * @return int
     */
    int deletePledgeByQuoteId(String quoteId);
    
    /**
     * 根据报价编号删除质押式回购报价历史数据列表
     * @param quoteId String
     * @return int
     */
    int deleteCrepHisByQuoteId(String quoteId);
    
    /**
     * 根据报价编号修改质押品表的版本号
     * @param quoteId String
     * @return int
     */
    int updateCrepVersionByQuoteId(IbuQuoteCrepPledge ibuQuoteCrepPledge);
    
    /**
     * 查询除了回复的detId +quoteID 存不存在 已回复数据
     * @param quoteId String
     * @return int
     */
    int selectReplyExceptDetId(@Param("quoteId")String quoteId,@Param("detId")String detId);

    /**
     * 现券银行间报价根据外部报价编号查询报价编号
     * @param quoteId String
     * @return String
     */
    String getQuoteIdByPquoteId(@Param("pquoteId")String pquoteId);
    
    /**
     * 质押式回购根据外部报价编号查询报价编号
     * @param quoteId String
     * @return String
     */
    String getRepoQuoteIdByPquoteId(@Param("pquoteId")String pquoteId);

    /**
     * 批量删除押品
     * @param keyList List<IbuQuoteCrepPledgeKey>
     * @return int
     */
    int deleteQuoteCrepPledges(List<IbuQuoteCrepPledgeKey> keyList);

    /**
     * 批量更新现劵报价明细列表
     * @param detList List<IbuQuoteBondDetDto>
     * @return int
     */
    int updateQuoteBondDetList(List<IbuQuoteBondDetDto> detList);

    /**
     * 根据detId列表查询现卷报价明细列表
     * @param detIdList List<String>
     * @return List<IbuQuoteBondDet>
     */
    List<IbuQuoteBondDet> selectQuoteBondDetList(List<String> detIdList);
    
    /**
     * 根据报价编号删除现券银行间报价历史数据列表
     * @param quoteId String
     * @return int
     */
    int deleteQuoteHisByQuoteId(String quoteId);
    
    /**
     * 
     * 批量插入质押品信息
     * <功能详细描述>
     * @param pledgeList
     * @return
     * @see
     * @since 1.0
     */
    int insertWflPledgeList(@Param("pledgeList")List<WflIbuQuoteCrepPledgeDto> pledgeList);
    
    /**
     * 根据报价编号删除质押品信息
     * @param quoteId
     * @return
     */
    int deleteWflPledgeListByKey(String quoteId);


    /***
     *  多组合  start
     * @param quoteId
     * @return
     */
    int deleteQuoteEtrReByQuoteId(String quoteId);

    int deleteQuoteEntPledgeByQuoteId(String quoteId);

    int deleteQuoteEtrReHisByQuoteId(String quoteId);

    int deleteQuoteEntPledgeHisByQuoteId(String quoteId);

    int deleteWflIbuQuoteEtrReByQuoteId(String quoteId);

    int deleteWflIbuQuoteEntCrepPledgeByQuoteId(String quoteId);

    int batchInsetIbuQuoteEtrReHis(@Param("inputList") List<IbuQuoteEtrReHis> inputList);
    int batchInsetIbuQuoteEntCrepPledgeHis(@Param("inputList") List<IbuQuoteEntCrepPledgeHis> inputList);
    int batchInsetWflIbuQuoteEtrRe(@Param("inputList") List<WflIbuQuoteEtrReDto> inputList);
    int batchInsetWflIbuQuoteEntCrepPledge(@Param("inputList") List<WflIbuQuoteEntCrepPledgeDto> inputList);
    int batchInsetIbuQuoteEtrRe(@Param("inputList") List<IbuQuoteEtrReDto> inputList);
    int batchInsetIbuQuoteEntCrepPledge(@Param("inputList") List<IbuQuoteEntCrepPledgeDto> inputList);

    // add by etliu 20210309 银行间上下行多组合 start
    /**
     * 根据报价编号，查询银行间报价指令关系业务表数据
     * @param quoteId 报价编号
     * @return 银行间报价指令关系业务表数据
     */
    List<IbuQuoteEtrReDto> selectQuoteEtrReDataByQuoteId(@Param("quoteId")String quoteId);
    
    /**
     * 根据报价编号，查询银行间报价指令关系业务表数据(业务流程表数据)
     * @param quoteId 报价编号
     * @return 银行间报价指令关系业务表数据(业务流程表数据)
     */
    List<IbuQuoteEtrReDto> selectWflQuoteEtrReDataByQuoteId(@Param("quoteId")String quoteId);
    // add by etliu 20210309 银行间上下行多组合 end

    List<IbuQuoteEntCrepPledgeDto> selectQuoteEntCrepPledgeDataByQuoteId(@Param("quoteId")String quoteId);
}
