package com.joyintech.tams.svreport.chb.botastpositionregist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.svreport.chb.botastpositionregist.dto.ChbBotAstPosSubHis;
import com.joyintech.tams.svreport.chb.botastpositionregist.dto.ChbBotAstPositionRegist001Dto;

public interface ChbBotAstPosSubHisMapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(ChbBotAstPosSubHis record);

    int insertSelective(ChbBotAstPosSubHis record);

    ChbBotAstPosSubHis selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(ChbBotAstPosSubHis record);

    int updateByPrimaryKey(ChbBotAstPosSubHis record);

	void deleteBySeqno(@Param("seqnolist")List<String> list);

	List<String> selectByprodId(@Param("regList")List<ChbBotAstPositionRegist001Dto> list);

	void insertSubHisBatch(@Param("regList")List<String> list);
}