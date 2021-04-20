package com.joyintech.background.component.uplink.interbank.database.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.background.component.uplink.interbank.database.entity.WflIbuQuoteBond2;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.background.component.uplink.interbank.business.dto.IbuQuoteBondDetDto;
import com.joyintech.background.component.uplink.interbank.business.dto.IbuQuoteBondDto;

@Repository
public interface WflIbuQuoteBond2Mapper {
    int deleteByPrimaryKey(String quoteId);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflIbuQuoteBond2 record);

    int updateByWorkflowId(WflIbuQuoteBond2 record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflIbuQuoteBond2 record);

    int insertSelective(WflIbuQuoteBond2 record);

    int insertBasicByBasicInfo(WflIbuQuoteBond2 record);

    WflIbuQuoteBond2 selectByPrimaryKey(String quoteId);

    int updateByPrimaryKeySelective(WflIbuQuoteBond2 record);

    int updateByPrimaryKey(WflIbuQuoteBond2 record);
    
    /**
     * 根据报价编号删除现券银行间报价明细数据列表
     * @param quoteId String
     * @return int
     */
    int deleteWflQuoteDetByQuoteId(String quoteId);
    
    /**
     * 根据报价编号查询现券银行间报价信息
     * @param quoteId String
     * @return IbuQuoteBondDto
     */
    IbuQuoteBondDto selectQuoteBondByKey(@Param("quoteId")String quoteId);
    
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
}