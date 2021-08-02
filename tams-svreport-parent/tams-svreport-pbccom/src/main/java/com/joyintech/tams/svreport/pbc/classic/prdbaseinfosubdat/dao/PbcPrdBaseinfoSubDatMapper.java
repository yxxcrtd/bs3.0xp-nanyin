package com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.dao;

import com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dto.PbcPrdAccountSub;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.dto.PbcPrdBaseinfoSubDat;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcPrdBaseinfoSubDatMapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(PbcPrdBaseinfoSubDat record);

    int insertSelective(PbcPrdBaseinfoSubDat record);

    PbcPrdBaseinfoSubDat selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(PbcPrdBaseinfoSubDat record);

    int updateByPrimaryKey(PbcPrdBaseinfoSubDat record);

    List<PbcPrdBaseinfoSubDat> selectNeedRegisteData(PbcPrdBaseinfoSubDat qmodel);
    
    List<PbcPrdBaseinfoSubDat> selectAlReadyRegisteData(@Param("reglist") List<PbcPrdBaseinfoSubDat> reglist);
    
    List<PbcPrdBaseinfoSubDat> selectAllRegisteData(@Param("reglist") List<PbcPrdBaseinfoSubDat> reglist);
    
    void deleteByProdid(@Param("deleteList") List<PbcPrdBaseinfoSubDat> subList);
    
    List<PbcPrdBaseinfoSubDat> searchPage(Map<String, Object> searchParam);

	int cancelManul(List<PbcPrdBaseinfoSubDat> updateList);
	
	int doManul(@Param("list") List<PbcPrdBaseinfoSubDat> updateList, @Param("submitadd") String submitadd);
	
	int insetBatchSubFromHisByUpdateTime(List<PbcPrdBaseinfoSubDat> insertList);
	
	int deleteBatchSubFromHisByUpdateTime(List<PbcPrdBaseinfoSubDat> insertList);
	
	int insertHisBatchBySendStatus(List<PbcPrdBaseinfoSubDat> insertList);
	
	void deleteBatchBySendStatus(List<PbcPrdBaseinfoSubDat> deleteList);
	
	/**
	 * 查询所有不是删除状态的人行产品信息
	 * @param param 
	 * @return 所有不是删除状态的人行产品信息
	 */
	List<PbcPrdBaseinfoSubDat> queryAllPbcPrdBaseInfo(Map<String, Object> param);

	List<PbcPrdAccountSub> selectAllRegisteAccountData(@Param("reglist") List<PbcPrdBaseinfoSubDat> reglist);
	
	List<String> selectRegistSeqnoList(@Param("list") List<PbcPrdBaseinfoSubDat> registList, @Param("flag") String flag);

	/**
	 * 根据理财产品代码查询
	 * @param prodIdList
	 * @return
	 */
	List<PbcPrdBaseinfoSubDat> queryPbcPrdBaseInfoByprodId(@Param("list") List<String> prodIdList);

	/**
	 * 根据理财产品代码查询
	 * @param prodIdList
	 * @return
	 */
	int insertBatch(@Param("list") List<PbcPrdBaseinfoSubDat> prodIdList);
	/**
	 * 根据主键批量更新
	 * @param record
	 * @return
	 */
	int updateBatchByPrimaryKey(@Param("list") List<PbcPrdBaseinfoSubDat> record);
}