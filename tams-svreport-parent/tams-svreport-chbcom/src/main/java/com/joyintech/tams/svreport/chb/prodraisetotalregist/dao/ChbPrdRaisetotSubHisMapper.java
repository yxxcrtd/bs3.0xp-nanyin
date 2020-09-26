package com.joyintech.tams.svreport.chb.prodraisetotalregist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.chb.prodraisetotalregist.dto.ChbPrdRaisetotSubHis;
import com.joyintech.tams.svreport.chb.prodraisetotalregist.dto.RaiseTotalRegist001Dto;

@Repository
public interface ChbPrdRaisetotSubHisMapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(ChbPrdRaisetotSubHis record);

    int insertSelective(ChbPrdRaisetotSubHis record);

    ChbPrdRaisetotSubHis selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(ChbPrdRaisetotSubHis record);

    int updateByPrimaryKey(ChbPrdRaisetotSubHis record);

	void insertSubHisBatch(@Param("regList")List<String> list);

	List<String> selectByprodId(@Param("regList")List<RaiseTotalRegist001Dto> prodTerminationList);

	void deleteBySeqno(@Param("seqnolist")List<String> list);
}