package com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubnew.dao;

import com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dto.PbcPrdAccountSub;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubnew.dto.PbcPrdBaseinfoSubNew;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface PbcPrdBaseinfoSubNewMapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(PbcPrdBaseinfoSubNew record);

    int insertSelective(PbcPrdBaseinfoSubNew record);

    PbcPrdBaseinfoSubNew selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(PbcPrdBaseinfoSubNew record);

    int updateByPrimaryKey(PbcPrdBaseinfoSubNew record);
    
    List<PbcPrdBaseinfoSubNew> selectNeedRegisteData(PbcPrdBaseinfoSubNew qmodel);
    
    List<PbcPrdBaseinfoSubNew> selectAlReadyRegisteData(@Param("reglist") List<PbcPrdBaseinfoSubNew> reglist);
    
    List<PbcPrdBaseinfoSubNew> selectAllRegisteData(@Param("reglist") List<PbcPrdBaseinfoSubNew> reglist);
    
    void deleteByProdid(@Param("deleteList") List<PbcPrdBaseinfoSubNew> subList);
    
    List<PbcPrdBaseinfoSubNew> searchPage(Map<String, Object> searchParam);

	int cancelManul(List<PbcPrdBaseinfoSubNew> updateList);
	
	int doManul(@Param("list") List<PbcPrdBaseinfoSubNew> updateList, @Param("submitadd") String submitadd);
	
	int insetBatchSubFromHisByUpdateTime(List<PbcPrdBaseinfoSubNew> insertList);
	
	int deleteBatchSubFromHisByUpdateTime(List<PbcPrdBaseinfoSubNew> insertList);
	
	int insertHisBatchBySendStatus(List<PbcPrdBaseinfoSubNew> insertList);
	
	void deleteBatchBySendStatus(List<PbcPrdBaseinfoSubNew> deleteList);
	
	/**
	 * 查询所有不是删除状态的人行产品信息
	 * @param param 
	 * @return 所有不是删除状态的人行产品信息
	 */
	List<PbcPrdBaseinfoSubNew> queryAllPbcPrdBaseInfo(Map<String, Object> param);
	
	List<PbcPrdAccountSub> selectAllRegisteAccountData(@Param("reglist") List<PbcPrdBaseinfoSubNew> reglist);
	
	List<String> selectRegistSeqnoList(@Param("list") List<PbcPrdBaseinfoSubNew> registList, @Param("flag") String flag);
}