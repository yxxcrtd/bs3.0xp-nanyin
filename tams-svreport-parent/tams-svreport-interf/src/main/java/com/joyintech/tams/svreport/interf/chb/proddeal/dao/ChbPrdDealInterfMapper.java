package com.joyintech.tams.svreport.interf.chb.proddeal.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.ChbPrdCustNumIfo;
import com.joyintech.tams.svreport.interf.chb.proddeal.dto.CheckReport001Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbPrdDeal;
import com.joyintech.tams.common.base.entity.ChbPrdFeeDeal;
@Repository
public interface ChbPrdDealInterfMapper {
	
    void selectPrdDealByDate(@Param("batchTime")Date batchTime,@Param("prodIdList")List<String> prodIdList);
	
	int deletePrdDealBatchList(@Param("listChbPrdDeal")List<ChbPrdDeal> listChbPrdDeal);
	
	int insertPrdDealBatchList(@Param("listChbPrdDeal")List<ChbPrdDeal> listChbPrdDeal);
	
	int updatePrdBaseinfo(@Param("listChbPrdDeal")List<ChbPrdDeal> listChbPrdDeal);
    
	void selectPrdFeeDealByDate(@Param("batchTime")Date batchTime,@Param("prodIdList")List<String> prodIdList);
	
	int deletePrdFeeDealBatchList(@Param("listChbPrdFeeDeal")List<ChbPrdFeeDeal> listChbPrdFeeDeal);
	
	int insertPrdFeeDealBatchList(@Param("listChbPrdFeeDeal")List<ChbPrdFeeDeal> listChbPrdFeeDeal);
	
	int deletePrdFeeDealByDate(Date paydate);
	
	int deletePrdDealByDate(Date settdateDate);
	
	void insertPrdDeal(ChbPrdDeal dl);

	void insertPrdFeeDeal(ChbPrdFeeDeal dl);
    
    /**
     * 识别标识在投资者身份信息表中是否已存在
     * @return 结果集
     * @see
     * @since 1.0
     */
    List<Map<String,String>> getAllRegistBankCodeSet();
    
    
    List<ChbPrdDeal> selectDealList(@Param("batchTime")Date batchTime);
    
    List<ChbPrdDeal> getAllPrdIssuePartyFromBaseInfo();
    /**
     * 批量插入登记表数据
     * <功能详细描述>
     * @param subList 数据集合
     * @see
     * @since 1.0
     */
    void insertChbPrdFeeDealData(@Param("newDataList")List<ChbPrdFeeDeal> newDataList);
    
    int insertDataMerge(@Param("newDataList")List<ChbPrdDeal> insertList);

	/**
	 * 查询三期的产品代码和客户数
	 * @return
	 */
	List<ChbPrdCustNumIfo>   getraiseCustNumThree(@Param("prodIdList")List<String> prodIdList, @Param("checkDate")Date checkDate);

	/**
	 * 批量插入报送前检查结果
	 *
	 * @param resultList 数据集
	 * @see
	 * @since 1.0
	 */
	void batchInsert(List<CheckReport001Dto> resultList);
}