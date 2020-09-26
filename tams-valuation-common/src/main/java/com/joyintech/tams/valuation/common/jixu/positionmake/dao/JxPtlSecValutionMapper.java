package com.joyintech.tams.valuation.common.jixu.positionmake.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.PtlSecValution;

/**
 * 头寸生成批处理 - 数据获取
 * @author Administrator
 *
 */
@Repository
public interface JxPtlSecValutionMapper {
	/**
	 * 删除【组合资产估值】数据
	 * @param ptlSecValutionList
	 * @return
	 */
	int deletePtlSecValution(@Param("list")List<PtlSecValution> ptlSecValutionList,@Param("cdate")Date cdate,@Param("genType")String genType);
	/**
	 * 插入【组合资产估值】数据
	 * @param ptlSecValutionList
	 * @return
	 */
	int insertPtlSecValution(@Param("list")List<PtlSecValution> ptlSecValutionList);

	/**
	 * 根据账套代码查询组合代码
	 * @param booksetId
	 * @return
	 */
	String queryPortFolioId(@Param("booksetId")String booksetId);

}

