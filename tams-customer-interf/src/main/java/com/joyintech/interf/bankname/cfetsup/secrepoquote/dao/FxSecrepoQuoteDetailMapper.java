package com.joyintech.interf.bankname.cfetsup.secrepoquote.dao;

import com.joyintech.interf.bankname.cfetsup.secrepoquote.dto.FxSecrepoQuoteDetail001Dto;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.entity.FxSecrepoQuoteDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface FxSecrepoQuoteDetailMapper {

    List<FxSecrepoQuoteDetail001Dto> selectFinprodInfoByFinprodId(@Param("finprodId") String finprodId, @Param("tradeDate") Date tradeDate);

    List<FxSecrepoQuoteDetail001Dto> selectFinprodDetail(Map param);

    List<FxSecrepoQuoteDetail001Dto> countQuoteAmtBySecCode(@Param("trustid") String trustid,@Param("seccode") String seccode);

    int deleteByPrimaryKey(String seqno);

    int deleteByPseqNo(@Param("pseqno") String pseqno);

    int insert(FxSecrepoQuoteDetail record);

    int insertSelective(FxSecrepoQuoteDetail record);

    int insertSelectiveHist(FxSecrepoQuoteDetail record);

    FxSecrepoQuoteDetail selectByPrimaryKey(String seqno);

    List<FxSecrepoQuoteDetail> selectByPseqNo(@Param("pseqno") String pseqno);

    int updateByPrimaryKeySelective(FxSecrepoQuoteDetail record);

    int updateByPrimaryKey(FxSecrepoQuoteDetail record);

    List<FxSecrepoQuoteDetail> selectHistByPseqNo(String seqno);
}