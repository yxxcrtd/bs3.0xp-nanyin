package com.joyintech.tams.svreport.interf.chb.assetinfo.dao;

import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SvrMstBondCreditRatingMapper {

    int mergeSecRating(@Param("list") List<MstBondCreditRating> list);

    void deleteTodayData();

    void deleteAllData();
}