package com.joyintech.tams.svreport.pbc.classic.prdinitraisesub.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.pbc.classic.prdinitraisesub.dto.PbcInitProdRaisingSub;
import com.joyintech.tams.svreport.pbc.classic.prdinitraisesub.dto.PbcInitProdRaisingSubVo;
import com.joyintech.tams.svreport.position.dto.ChbPrdPosition;

@Repository
public interface PbcInitProdRaisingSubMapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(PbcInitProdRaisingSub record);

    int insertSelective(PbcInitProdRaisingSub record);

    PbcInitProdRaisingSub selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(PbcInitProdRaisingSub record);

    int updateByPrimaryKey(PbcInitProdRaisingSub record);

	void deleteBatch(@Param("list")List<PbcInitProdRaisingSub> deletelist);

	void insertBatch(List<PbcInitProdRaisingSub> insertlist);

	List<PbcInitProdRaisingSub> selectPrdRegistInfo(@Param("vdate")String executeDate, @Param("prodIdList")List<String> prodIdList);
	
	List<PbcInitProdRaisingSub> selectPsbcPrdRegistInfo(@Param("vdate")String executeDate, @Param("prodIdList")List<String> prodIdList);
	
	List<ChbPrdPosition> selectPrdposition(@Param("sublist")List<PbcInitProdRaisingSub> raisingSubList);
	
	List<ChbPrdPosition> selectPsbcPrdposition(@Param("sublist")List<PbcInitProdRaisingSub> raisingSubList);

	List<PbcInitProdRaisingSub> selectPIB2All(Map<String,Object> searchParam);

	List<PbcInitProdRaisingSub> searchPage(Map<String, Object> searchParam);

	int cancelManul(List<PbcInitProdRaisingSub> updateList);
	
	int doManul(@Param("list")List<PbcInitProdRaisingSub> updateList,@Param("submitadd")String submitadd);
	
	int insertHisBatchBySendStatus(List<PbcInitProdRaisingSub> insertList);
	
	void deleteBatchBySendStatus(List<PbcInitProdRaisingSub> deleteList);
	
	int selectCountReg(@Param("dto")PbcInitProdRaisingSub dto);
	
	int insetBatchSubFromHisByUpdateTime(List<PbcInitProdRaisingSub> insertList);
	
	int deleteBatchSubFromHisByUpdateTime(List<PbcInitProdRaisingSub> insertList);
	
	List<PbcInitProdRaisingSub> selectByDimension(Map<String, Object> searchParam);
	
	List<PbcInitProdRaisingSub> selectRegList(@Param("dto")PbcInitProdRaisingSub dto);
	
	List<String> selectRegistSeqnoList(@Param("list")List<PbcInitProdRaisingSub> registList,@Param("flag")String flag);
	
	//产品基本信息页面导出人行Excel的第二个sheet页 起始募集信息的明细数据
	List<PbcInitProdRaisingSubVo> searchListDetail(Map<String, Object> searchParam);
	
	List<PbcInitProdRaisingSubVo> searchListDetailCNY(Map<String, Object> searchParam);
	
	List<PbcInitProdRaisingSubVo> searchListDetailOth(Map<String, Object> searchParam);
	
	
	
}