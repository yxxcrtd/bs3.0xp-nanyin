package com.joyintech.tams.svreport.pbc.classic.prdinfoendsubnew.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dto.PbcPrdBaseinfoEndSubDat;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubnew.dto.PbcPrdBaseinfoEndSubNew;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.joyintech.tams.svreport.position.dto.ChbPrdFeePosition;
import com.joyintech.tams.svreport.position.dto.ChbPrdPosition;

@Repository
public interface PbcPrdBaseinfoEndSubNewMapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(PbcPrdBaseinfoEndSubNew record);

    int insertSelective(PbcPrdBaseinfoEndSubNew record);

    PbcPrdBaseinfoEndSubNew selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(PbcPrdBaseinfoEndSubNew record);

    int updateByPrimaryKey(PbcPrdBaseinfoEndSubNew record);
    
    List<PbcPrdBaseinfoEndSubNew> searchPage(Map<String, Object> map);

	List<PbcPrdBaseinfoEndSubDat> searchDat(Map<String, Object> map);


	int doManul(@Param("list") List<PbcPrdBaseinfoEndSubNew> updateList, @Param("submitadd") String submitadd);

	int cancelManul(List<PbcPrdBaseinfoEndSubNew> updateList);

	List<PbcPrdBaseinfoEndSubNew> getProdInfoByEndSub(@Param("list") List<PbcPrdBaseinfoEndSubNew> list);

	List<ChbPrdFeePosition> getPrdFeePositionList(@Param("list") List<PbcPrdBaseinfoEndSubNew> list);

	List<ChbPrdPosition> getPrdDealPositionList(@Param("list") List<PbcPrdBaseinfoEndSubNew> list);
	
	List<String> selectRegistSeqnoList(@Param("list") List<PbcPrdBaseinfoEndSubNew> registList, @Param("flag") String flag);

	void deleteBatch(@Param("list") List<PbcPrdBaseinfoEndSubNew> subList);

	List<PbcPrdBaseinfoEndSubNew> getProdIdsByEndSub(@Param("batchDate") String format, @Param("tradType") String value, @Param("prodidlist") List<String> prodIdList);

	int selectCountReg(@Param("dto") PbcPrdBaseinfoEndSubNew res);
	
	int insetBatchSubFromHisByUpdateTime(List<PbcPrdBaseinfoEndSubNew> insertList);
	
	int deleteBatchSubFromHisByUpdateTime(List<PbcPrdBaseinfoEndSubNew> insertList);
	
	int insertHisBatchBySendStatus(List<PbcPrdBaseinfoEndSubNew> insertList);
	
	void deleteBatchBySendStatus(List<PbcPrdBaseinfoEndSubNew> deleteList);
	
	List<PbcPrdBaseinfoEndSubNew> selectByDimension(Map<String, Object> map);

	List<PbcPrdBaseinfoEndSubNew> selectRegList(@Param("dto") PbcPrdBaseinfoEndSubNew list);
	
	BigDecimal queryBookBalanceLevel1(@Param("cdate") Date cdate, @Param("prodid") String prodid);
           
	BigDecimal queryBookBalanceLevel1SubSystem(@Param("cdate") Date cdate, @Param("prodid") String prodid);

	int updateByProdIds(@Param("list")List<String> list);

}