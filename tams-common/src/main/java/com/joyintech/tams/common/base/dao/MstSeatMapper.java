package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstSeat;
import org.springframework.stereotype.Repository;

@Repository
public interface MstSeatMapper {
    int deleteByPrimaryKey(String seatId);

    int insert(MstSeat record);

    int insertSelective(MstSeat record);

    int insertBasicByBasicInfo(MstSeat record);

    MstSeat selectByPrimaryKey(String seatId);

    int updateByPrimaryKeySelective(MstSeat record);

    int updateByPrimaryKey(MstSeat record);
}