package com.joyintech.tams.svreport.position.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.common.base.entity.ChbPrdFeeDeal;
import com.joyintech.tams.svreport.position.dto.ChbPrdFeePosition;

public interface ChbPrdFeePositionMapper {
	
    int insertBatch(@Param("insertDataList")List<ChbPrdFeePosition> insertDataList);

    /**
     * 删除跑批日之后头寸
     * @param cdate
     * @return
     */
    int deletePosition(@Param("cdate")Date cdate,@Param("prodIdList")List<String> prodIdList);

	/**
	 * 查询某天的头寸信息
	 * @param cdate
	 * @param prodIdList
	 * @return
	 */
	List<ChbPrdFeePosition> selectByDate(@Param("cdate")Date cdate,@Param("prodIdList")List<String> prodIdList);

	/**
	 * 查询倒起息交易
	 * @param bookDate
	 * @param prodIdList
	 * @return
	 */
	Date queryMinSettdate(@Param("batchDate")Date batchDate,@Param("prodIdList")List<String> prodIdList);

	void insertPos(ChbPrdFeePosition pos);
	
	List<ChbPrdFeeDeal> selectPrdFeeDeal(@Param("batchDate")Date batchDate,@Param("prodIdList")List<String> prodIdList);
}