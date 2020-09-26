package com.joyintech.tams.svreport.interf.dis.tradeinfo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.interf.dis.tradeinfo.dto.DisRepTreadInfo;

@Repository
public interface DisRepTreadInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(DisRepTreadInfo record);

    int insertSelective(DisRepTreadInfo record);

    int insertBasicByBasicInfo(DisRepTreadInfo record);

    DisRepTreadInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DisRepTreadInfo record);

    int updateByPrimaryKey(DisRepTreadInfo record);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dtoList
     * @see
     * @since 1.0
     */
    int mergeRepTradeInfo(List<DisRepTreadInfo> dtoList);
}