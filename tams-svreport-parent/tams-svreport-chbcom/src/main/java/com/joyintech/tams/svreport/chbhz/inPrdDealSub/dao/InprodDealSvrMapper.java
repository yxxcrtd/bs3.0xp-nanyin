package com.joyintech.tams.svreport.chbhz.inPrdDealSub.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbPrdDealSub;
import com.joyintech.tams.common.base.entity.ChbPrdDealSubTmp;

@Repository
public interface InprodDealSvrMapper {
	
	/**
	 * 查询申报接口临时表list
	 * @param batchdate
	 * @param prodIdList
	 * @return
	 */
	List<ChbPrdDealSubTmp>  genChbPrdDealSubTmpList(@Param("batchdate")String  batchdate,@Param("prodIdList") List<String> prodIdList);
    /**
     * 根据接口信息查询申报信息表
     * @return
     */
    List<ChbPrdDealSub>   genChbPrdDealSubList(@Param("keylist")List<String> keylist);
    
    /**
     * 产品运作模式
     */
    String    getOpertModeByPordId(@Param("prodId") String  prodId);
}