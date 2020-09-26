package com.joyintech.tams.svreport.chb.prodpersonissue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.chb.prodpersonissue.dto.ChbPrdIssueSubHis;
import com.joyintech.tams.svreport.chb.prodpersonissue.dto.ProdPersonIssue001Dto;

@Repository
public interface ChbPrdIssueSubHisMapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(ChbPrdIssueSubHis record);

    int insertSelective(ChbPrdIssueSubHis record);

    ChbPrdIssueSubHis selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(ChbPrdIssueSubHis record);

    int updateByPrimaryKey(ChbPrdIssueSubHis record);

	void insertSubHisBatch(@Param("regList")List<String> list);

	List<String> selectByprodId(@Param("regList")List<ProdPersonIssue001Dto> list);

	void deleteBySeqno(@Param("seqnolist")List<String> list);
}