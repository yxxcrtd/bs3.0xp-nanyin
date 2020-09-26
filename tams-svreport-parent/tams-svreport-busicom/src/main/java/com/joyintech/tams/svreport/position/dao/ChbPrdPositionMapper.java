package com.joyintech.tams.svreport.position.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.common.base.entity.ChbPrdDeal;
import com.joyintech.tams.svreport.position.dto.ChbPrdPosition;

public interface ChbPrdPositionMapper {
	
    int insertBatch(@Param("insertDataList")List<ChbPrdPosition> insertDataList);

    /**
     * 删除跑批日之后头寸
     * @param cdate
     * @return
     */
    int deletePosition(@Param("cdate")Date cdate,@Param("prodIdList")List<String> prodIdList);

    /**
     * 查询跑批日交易
     * @param 
     * @param 
     * @return
     */
    void queryDealList(@Param("bookDate")Date bookDate,
            					   @Param("prodIdList")List<String> prodIdList,
								   @Param("indentifyMark")String indentifyMark,
								   @Param("ccy")String ccy,
								   @Param("issuePartyId")String issuePartyId,
			                       @Param("dealtypes")List<String> dealtype);
	
	/**
	 * 查询某天的头寸信息
	 * @param cdate
	 * @param prodIdList
	 * @return
	 */
	List<ChbPrdPosition> selectByDate(@Param("cdate")Date cdate,@Param("prodIdList")List<String> prodIdList);

	/**
	 * 查询倒起息交易
	 * @param bookDate
	 * @param prodIdList
	 * @return
	 */
	Date queryMinSettdate(@Param("batchDate")Date batchDate,@Param("prodIdList")List<String> prodIdList);

	void insertPos(ChbPrdPosition pos);
	
	List<ChbPrdDeal> selectPrdDeal(@Param("batchDate")Date batchDate,@Param("prodIdList")List<String> prodIdList);
}