package com.joyintech.tams.svreport.chb.prodpersonissue.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.chb.proddurationreg.dto.ProdDurationReg001Dto;
import com.joyintech.tams.svreport.chb.prodpersonissue.dto.ProdInfo;
import com.joyintech.tams.svreport.chb.prodpersonissue.dto.ProdPersonIssue001Dto;


@Repository
public interface ProdPersonIssue001Mapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(ProdPersonIssue001Dto record);

    int insertSelective(ProdPersonIssue001Dto record);

    ProdPersonIssue001Dto selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(ProdPersonIssue001Dto record);

    int updateByPrimaryKey(ProdPersonIssue001Dto record);
    /**
     * 个人登记报表分页列表查询
     * <p>根据指定条件查询列表数据</p>
     * @param param 查询参数
     * @return 查询结果列表
     */
    List<ProdPersonIssue001Dto> queryForProdPersonIssuePage(Map<String, Object> param);
    /**
    * 按选择查询导出数据
    * @param map 查询条件
    * @return 结果列表
    * @see
    * @since 1.0
    */
    List<ProdPersonIssue001Dto> queryExportListByKeys(@Param("registList")List<String> keys);
    
    void updateInterfSubmitAndSeqBatch(@Param("sendStatus")String sendStatus,@Param("submitFeeBackStatus")String submitFeeBackStatus, @Param("registList")List<ProdPersonIssue001Dto> registList);
    
    Integer cancelMannultRegist(List<ProdPersonIssue001Dto> list);
	
	List<ProdPersonIssue001Dto> queryGenerateList(@Param("generateDate")String generateDate
			,@Param("prodInfos")List<String> prodIds,@Param("model_type")String model_type);
	
	/**
	 * 功能:根据产品代码删除产品发行登记
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年3月23日
	 * @param subList 
	 * void
	 */
    void deleteByProdid(@Param("deleteList")List<ProdPersonIssue001Dto> subList);
    
    /**
     * 功能:插入产品发行登记
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月23日
     * @param prodOrgIssue001Dto
     * @return 
     * int
     */
    int insertSub(ProdPersonIssue001Dto prodOrgIssue001Dto);
    
    List<ProdPersonIssue001Dto> queryRegIssueList(@Param("reglist")List<ProdPersonIssue001Dto> reglist);

	List<String> selectRegistSeqnoList(@Param("list")List<ProdPersonIssue001Dto> registList,@Param("flag")String flag);

	void deleteRegistSeqnoList(@Param("regList")List<String> list);

	void insertSeletForSub(@Param("regList")List<String> list);
	
    List<ProdPersonIssue001Dto>   selectAllDataByList(@Param("keyList")List<ProdPersonIssue001Dto>  dataList,@Param("model_type")String model_type);
}