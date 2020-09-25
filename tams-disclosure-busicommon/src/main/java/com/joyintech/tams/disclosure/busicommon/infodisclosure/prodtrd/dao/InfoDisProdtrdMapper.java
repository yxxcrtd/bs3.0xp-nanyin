package com.joyintech.tams.disclosure.busicommon.infodisclosure.prodtrd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.disclosure.busicommon.infodisclosure.prodtrd.entity.InfoDisProdtrdEntity;

public interface InfoDisProdtrdMapper {
    int insert(InfoDisProdtrdEntity record);

    int insertSelective(InfoDisProdtrdEntity record);

    int insertBasicByBasicInfo(InfoDisProdtrdEntity record);
    
    /**
     * 根据更新时间，查询产品交易信息并汇总
     * @param map
     * @return
     */
    List<InfoDisProdtrdEntity> selectChbPrdDealInfoByUpdateTime(Map<String,String> map);
    
    /**
     * 通过产品代码和确认日查询申赎信息
     * @param entity
     * @return
     */
    InfoDisProdtrdEntity selectRepProdTrdByConfirmDateAndProdId(InfoDisProdtrdEntity entity);

    /**
     * 查询产品在估值的日实收资本
     * @param entity
     * @return
     */
    InfoDisProdtrdEntity selectProdIndexByProdIdAndValueDate(InfoDisProdtrdEntity entity);

    /**
     * 批量更新
     * @param confirmDateEntityList
     * @return
     */
	int mergeProdtrdList(List<InfoDisProdtrdEntity> entityList);

}