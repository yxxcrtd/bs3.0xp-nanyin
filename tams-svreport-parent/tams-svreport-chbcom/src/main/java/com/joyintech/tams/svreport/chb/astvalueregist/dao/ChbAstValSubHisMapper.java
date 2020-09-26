package com.joyintech.tams.svreport.chb.astvalueregist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.svreport.chb.astvalueregist.dto.ChbAstValSubHis;
import com.joyintech.tams.svreport.chb.astvalueregist.dto.ChbAstValueRegist001Dto;

public interface ChbAstValSubHisMapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(ChbAstValSubHis record);

    int insertSelective(ChbAstValSubHis record);

    ChbAstValSubHis selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(ChbAstValSubHis record);

    int updateByPrimaryKey(ChbAstValSubHis record);

	void insertSubHisBatch(@Param("regList")List<String> list);

	List<String> selectByprodId(@Param("regList")List<ChbAstValueRegist001Dto> list);

	void deleteBySeqno(@Param("seqnolist")List<String> list);
}