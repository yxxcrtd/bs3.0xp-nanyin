package com.joyintech.tams.svreport.chb.prodterminationregist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.svreport.chb.prodterminationregist.dto.ChbPrdEndSubHis;
import com.joyintech.tams.svreport.chb.prodterminationregist.dto.ProdTerminationRegist001Dto;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbPrdEndSubHisMapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(ChbPrdEndSubHis record);

    int insertSelective(ChbPrdEndSubHis record);

    ChbPrdEndSubHis selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(ChbPrdEndSubHis record);

    int updateByPrimaryKey(ChbPrdEndSubHis record);

	void insertSubHisBatch(@Param("regList")List<String> list);

	void deleteBySeqno(@Param("seqnolist")List<String> list);

	List<String> selectByprodId(@Param("regList")List<ProdTerminationRegist001Dto> prodTerminationList);
}