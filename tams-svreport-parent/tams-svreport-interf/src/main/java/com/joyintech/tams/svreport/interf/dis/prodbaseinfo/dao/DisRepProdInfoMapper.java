package com.joyintech.tams.svreport.interf.dis.prodbaseinfo.dao;

import com.joyintech.tams.svreport.interf.dis.prodbaseinfo.dto.DisRepProdInfo;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface DisRepProdInfoMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(DisRepProdInfo record);

    int insertSelective(DisRepProdInfo record);

    int insertBasicByBasicInfo(DisRepProdInfo record);

    DisRepProdInfo selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(DisRepProdInfo record);

    int updateByPrimaryKey(DisRepProdInfo record);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dtoList
     * @see
     * @since 1.0
     */
    int mergeRepProdInfo(List<DisRepProdInfo> dtoList);
}