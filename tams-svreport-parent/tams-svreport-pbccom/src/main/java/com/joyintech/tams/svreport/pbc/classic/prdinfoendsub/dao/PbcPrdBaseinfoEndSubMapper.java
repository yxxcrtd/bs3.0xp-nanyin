package com.joyintech.tams.svreport.pbc.classic.prdinfoendsub.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsub.dto.PbcPrdBaseinfoEndSub;
import com.joyintech.tams.svreport.pbc.classic.prdinitraisesub.dto.PbcInitProdRaisingSub;
import com.joyintech.tams.svreport.position.dto.ChbPrdFeePosition;
import com.joyintech.tams.svreport.position.dto.ChbPrdPosition;

@Repository
public interface PbcPrdBaseinfoEndSubMapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(PbcPrdBaseinfoEndSub record);

    int insertSelective(PbcPrdBaseinfoEndSub record);

    PbcPrdBaseinfoEndSub selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(PbcPrdBaseinfoEndSub record);

    int updateByPrimaryKey(PbcPrdBaseinfoEndSub record);
    
    List<PbcPrdBaseinfoEndSub> searchPage(Map<String,Object> map);

	int doManul(@Param("list")List<PbcPrdBaseinfoEndSub> updateList,@Param("submitadd")String submitadd);

	int cancelManul(List<PbcPrdBaseinfoEndSub> updateList);

	List<PbcPrdBaseinfoEndSub> getProdInfoByEndSub(@Param("list")List<PbcPrdBaseinfoEndSub> list);

	List<ChbPrdFeePosition> getPrdFeePositionList(@Param("list")List<PbcPrdBaseinfoEndSub> list);

	List<ChbPrdPosition> getPrdDealPositionList(@Param("list")List<PbcPrdBaseinfoEndSub> list);
	
	List<String> selectRegistSeqnoList(@Param("list")List<PbcPrdBaseinfoEndSub> registList,@Param("flag")String flag);

	void deleteBatch(@Param("list")List<PbcPrdBaseinfoEndSub> subList);

	List<PbcPrdBaseinfoEndSub> getProdIdsByEndSub(@Param("batchDate")String format, @Param("tradType")String value, @Param("prodidlist")List<String> prodIdList);

	int selectCountReg(@Param("dto")PbcPrdBaseinfoEndSub res);
	
	int insetBatchSubFromHisByUpdateTime(List<PbcPrdBaseinfoEndSub> insertList);
	
	int deleteBatchSubFromHisByUpdateTime(List<PbcPrdBaseinfoEndSub> insertList);	
	
	int insertHisBatchBySendStatus(List<PbcPrdBaseinfoEndSub> insertList);
	
	void deleteBatchBySendStatus(List<PbcPrdBaseinfoEndSub> deleteList);
	
	List<PbcPrdBaseinfoEndSub> selectByDimension(Map<String,Object> map);

	List<PbcPrdBaseinfoEndSub> selectRegList(@Param("dto")PbcPrdBaseinfoEndSub list);
	
	BigDecimal queryBookBalanceLevel1(@Param("cdate")Date cdate, @Param("prodid")String prodid);
           
	BigDecimal queryBookBalanceLevel1SubSystem(@Param("cdate")Date cdate, @Param("prodid")String prodid);
           

}