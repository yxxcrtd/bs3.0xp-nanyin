package com.joyintech.tams.svreport.pbc.classic.prdaccountendsub.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.svreport.pbc.classic.prdaccountendsub.dto.PbcPrdAccountEndSub;

public interface PbcPrdAccountEndSubMapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(PbcPrdAccountEndSub record);

    int insertSelective(PbcPrdAccountEndSub record);

    PbcPrdAccountEndSub selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(PbcPrdAccountEndSub record);

    int updateByPrimaryKey(PbcPrdAccountEndSub record);
    
    List<PbcPrdAccountEndSub> searchPage(Map<String, Object> searchParam);
    
    int cancelManul(List<PbcPrdAccountEndSub> updateList);
	
	int doManul(@Param("list")List<PbcPrdAccountEndSub> updateList,@Param("submitadd")String submitadd);
	
	List<PbcPrdAccountEndSub> selectAccountEndSub(@Param("batchDate") String batchDate,@Param("list")List<String> prodidlist);

	void deleteBatch(@Param("list")List<PbcPrdAccountEndSub> subList);
	
	int insetBatchSubFromHisByUpdateTime(List<PbcPrdAccountEndSub> insertList);
	
	int deleteBatchSubFromHisByUpdateTime(List<PbcPrdAccountEndSub> insertList);	
	
	List<String> selectRegistSeqnoList(@Param("list")List<PbcPrdAccountEndSub> registList,@Param("flag")String flag);
	
	int insertHisBatchBySendStatus(List<PbcPrdAccountEndSub> insertList);
	
	void deleteBatchBySendStatus(List<PbcPrdAccountEndSub> deleteList);

	List<PbcPrdAccountEndSub> selectRegList(@Param("list")List<PbcPrdAccountEndSub> sublist);

	int selectIsPosition(@Param("dto")PbcPrdAccountEndSub dto);
	
	BigDecimal queryBookBalanceLevel1(@Param("cdate")Date cdate, @Param("prodid")String prodid);
    
	BigDecimal queryBookBalanceLevel1SubSystem(@Param("cdate")Date cdate, @Param("prodid")String prodid);
}