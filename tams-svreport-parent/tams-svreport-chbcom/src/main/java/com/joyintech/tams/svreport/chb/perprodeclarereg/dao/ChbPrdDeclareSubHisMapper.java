package com.joyintech.tams.svreport.chb.perprodeclarereg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.svreport.chb.perprodeclarereg.dto.ChbPrdDeclareSubHis;
import com.joyintech.tams.svreport.chb.perprodeclarereg.dto.PerProDeclareRegDto;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbPrdDeclareSubHisMapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(ChbPrdDeclareSubHis record);

    int insertSelective(ChbPrdDeclareSubHis record);

    ChbPrdDeclareSubHis selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(ChbPrdDeclareSubHis record);

    int updateByPrimaryKey(ChbPrdDeclareSubHis record);
    
    void insertSubHisBatch(@Param("regList")List<String> regList);

	List<String> selectByprodId(@Param("regList")List<PerProDeclareRegDto> proDeclareList);

	void deleteBySeqno(@Param("seqnolist")List<String> seqnolist);
}