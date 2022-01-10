package com.joyintech.tams.svreport.interf.chb.secrating.dao;

import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SecRatingInterfMapper {

    /**
     * 查询截止上次接入时间的关联机构信息
     * @param batchTime
     * @return
     */
    List<MstBondCreditRating> select(@Param("updatetime") Date updatetime);

}