package com.joyintech.interf.bankname.cfetsup.secrepoquote.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dto.FxSecrepoQuote001Dto;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dto.FxSecrepoQuote003Dto;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dto.FxSecrepoQuote004Dto;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.entity.FxSecrepoQuote;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface FxSecrepoQuoteMapper {

    List<FxSecrepoQuote001Dto> selectPageData(Map para);

    List<SelectOptionDto> getPortfolioId(Map para);

    List<SelectOptionDto> getPortfolioName(Map para);

    List<SelectOptionDto> getMenInfo(Map para);

    List<SuggestDto> getSecrepoQuoteInfo(Map para);

    List<SelectOptionDto> getFinprodId(Map para);

    List<FxSecrepoQuote003Dto> selectByFinprodInfo(Map para);

    List<FxSecrepoQuote004Dto> selectEntrustInfo(Map param);

    String getSequence();
    /**
     *
     * 获取平台日
     * <功能详细描述>
     * @return 平台日
     * @see
     * @since 1.0
     */
    Date getSysDate();

    int deleteByPrimaryKey(@Param("seqno") String seqno);

    int insert(FxSecrepoQuote record);

    int insertSelective(FxSecrepoQuote record);

    int insertSelectiveHist(FxSecrepoQuote record);

    FxSecrepoQuote selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(FxSecrepoQuote record);

    int updateByPrimaryKeySelectiveHist(FxSecrepoQuote record);

    int updateByPrimaryKey(FxSecrepoQuote record);

    FxSecrepoQuote  getObjectBySeqno(@Param("seqno") String seqno);

    void deleteByPrimaryKeyHist(String pseqno);

    List<FxSecrepoQuote> selectByPrimaryNbquoteid(String nbquoteid);

}