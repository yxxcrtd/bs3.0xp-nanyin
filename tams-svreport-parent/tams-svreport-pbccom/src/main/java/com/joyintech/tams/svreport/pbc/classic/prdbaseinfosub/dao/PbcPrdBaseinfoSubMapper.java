package com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dto.PbcPrdAccountSub;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dto.PbcPrdBaseinfoSub;


public interface PbcPrdBaseinfoSubMapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(PbcPrdBaseinfoSub record);

    int insertSelective(PbcPrdBaseinfoSub record);

    PbcPrdBaseinfoSub selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(PbcPrdBaseinfoSub record);

    int updateByPrimaryKey(PbcPrdBaseinfoSub record);
    
    List<PbcPrdBaseinfoSub> selectNeedRegisteData(PbcPrdBaseinfoSub qmodel);
    
    List<PbcPrdBaseinfoSub> selectAlReadyRegisteData(@Param("reglist")List<PbcPrdBaseinfoSub> reglist);
    
    List<PbcPrdBaseinfoSub> selectAllRegisteData(@Param("reglist")List<PbcPrdBaseinfoSub> reglist);
    
    void deleteByProdid(@Param("deleteList")List<PbcPrdBaseinfoSub> subList);
    
    List<PbcPrdBaseinfoSub> searchPage(Map<String, Object> searchParam);

	int cancelManul(List<PbcPrdBaseinfoSub> updateList);
	
	int doManul(@Param("list")List<PbcPrdBaseinfoSub> updateList,@Param("submitadd")String submitadd);
	
	int insetBatchSubFromHisByUpdateTime(List<PbcPrdBaseinfoSub> insertList);
	
	int deleteBatchSubFromHisByUpdateTime(List<PbcPrdBaseinfoSub> insertList);	
	
	int insertHisBatchBySendStatus(List<PbcPrdBaseinfoSub> insertList);
	
	void deleteBatchBySendStatus(List<PbcPrdBaseinfoSub> deleteList);
	
	/**
	 * 查询所有不是删除状态的人行产品信息
	 * @param param 
	 * @return 所有不是删除状态的人行产品信息
	 */
	List<PbcPrdBaseinfoSub> queryAllPbcPrdBaseInfo(Map<String, Object> param);
	
	List<PbcPrdAccountSub> selectAllRegisteAccountData(@Param("reglist")List<PbcPrdBaseinfoSub> reglist);
	
	List<String> selectRegistSeqnoList(@Param("list")List<PbcPrdBaseinfoSub> registList,@Param("flag")String flag);
}