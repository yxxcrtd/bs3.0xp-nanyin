package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstDates;
import java.util.Date;
import org.springframework.stereotype.Repository;

@Repository
public interface MstDatesMapper {
    int deleteByPrimaryKey(Date dates);

    int insert(MstDates record);

    int insertSelective(MstDates record);

    int insertBasicByBasicInfo(MstDates record);

    MstDates selectByPrimaryKey(Date dates);

    int updateByPrimaryKeySelective(MstDates record);

    int updateByPrimaryKey(MstDates record);
}