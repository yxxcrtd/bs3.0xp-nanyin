package com.joyintech.tams.svreport.interf.chb.prodinfo.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.common.base.entity.PrdMdateAdjust;
@Repository
public interface ProdInfoMapper {
	/**
	 * 从bs接产品要素信息
	 * @param batchTime
	 */
	void selectProdInfo(@Param("batchTime")Date batchTime);

	String selectBasisByprodid(String prodId);

	List<Date> isUpdated(Date batchTime);

	/**
	 * 功能:
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年3月11日
	 * @param prodinfo
	 * @return 
	 * List<ChbPrdBaseinfo>
	 */
	List<ChbPrdBaseinfo> selectByModel(ChbPrdBaseinfo prodinfo);
	/**
	 * 根据产品代码查询到期日调整
	 * @param prodid
	 * @return
	 */
	List<PrdMdateAdjust>selectMdateAdjustByProdid(String prodid);
}
