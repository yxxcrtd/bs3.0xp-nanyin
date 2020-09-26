package com.joyintech.tams.valuation.interf.valuation.sztinterf.dao;

import com.joyintech.tams.valuation.interf.valuation.sztinterf.dto.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SztInterfDealMapper {

	/**
	 * 插入汇总信息
	 * @param sztIntfLogModel
	 */
	void insertInterfLog(SztIntfLogModel sztIntfLogModel);

	/**
	 * 删除数据防止表中重复
	 * @param prodId
	 * @param interType
	 * @param cdate
	 */
	void deleteInterfLog(@Param("prodId") String prodId, @Param("interType") String interType, @Param("cdate") String cdate);

	/**
	 * 插入返回对账明细信息
	 * @param model
	 */
	void insertResultDetail(SztResultDetailXmlModel model);

	/**
	 * 查询发送报文存的数量
	 * @param fundId
	 * @param cdate
	 * @param interType
	 * @param refno
	 * @return
	 */
	int getDetailCount(@Param("prodId") String prodId, @Param("serialNo") String serialNo);

	/**
	 * 更新汇总信息对账结果
	 * @param model
	 */
	void updateInterfLog(SztIntfLogModel model);

}
