package com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dto.PbcPrdBaseinfoEndSubDat;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.joyintech.tams.svreport.position.dto.ChbPrdFeePosition;
import com.joyintech.tams.svreport.position.dto.ChbPrdPosition;

@Repository
public interface PbcPrdBaseinfoEndSubDatMapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(PbcPrdBaseinfoEndSubDat record);

    int insertSelective(PbcPrdBaseinfoEndSubDat record);

    PbcPrdBaseinfoEndSubDat selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(PbcPrdBaseinfoEndSubDat record);

    int updateByPrimaryKey(PbcPrdBaseinfoEndSubDat record);
    
    List<PbcPrdBaseinfoEndSubDat> searchPage(Map<String, Object> map);

	int doManul(@Param("list") List<PbcPrdBaseinfoEndSubDat> updateList, @Param("submitadd") String submitadd);

	int cancelManul(List<PbcPrdBaseinfoEndSubDat> updateList);

	List<PbcPrdBaseinfoEndSubDat> getProdInfoByEndSub(@Param("list") List<PbcPrdBaseinfoEndSubDat> list);

	List<ChbPrdFeePosition> getPrdFeePositionList(@Param("list") List<PbcPrdBaseinfoEndSubDat> list);

	List<ChbPrdPosition> getPrdDealPositionList(@Param("list") List<PbcPrdBaseinfoEndSubDat> list);
	
	List<String> selectRegistSeqnoList(@Param("list") List<PbcPrdBaseinfoEndSubDat> registList, @Param("flag") String flag);

	void deleteBatch(@Param("list") List<PbcPrdBaseinfoEndSubDat> subList);

	List<PbcPrdBaseinfoEndSubDat> getProdIdsByEndSub(@Param("batchDate") String format, @Param("tradType") String value, @Param("prodidlist") List<String> prodIdList);

	int selectCountReg(@Param("dto") PbcPrdBaseinfoEndSubDat res);
	
	int insetBatchSubFromHisByUpdateTime(List<PbcPrdBaseinfoEndSubDat> insertList);
	
	int deleteBatchSubFromHisByUpdateTime(List<PbcPrdBaseinfoEndSubDat> insertList);
	
	int insertHisBatchBySendStatus(List<PbcPrdBaseinfoEndSubDat> insertList);
	
	void deleteBatchBySendStatus(List<PbcPrdBaseinfoEndSubDat> deleteList);
	
	List<PbcPrdBaseinfoEndSubDat> selectByDimension(Map<String, Object> map);

	List<PbcPrdBaseinfoEndSubDat> selectRegList(@Param("dto") PbcPrdBaseinfoEndSubDat list);
	
	BigDecimal queryBookBalanceLevel1(@Param("cdate") Date cdate, @Param("prodid") String prodid);
           
	BigDecimal queryBookBalanceLevel1SubSystem(@Param("cdate") Date cdate, @Param("prodid") String prodid);
           

}