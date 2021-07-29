package com.joyintech.tams.common.base.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.tams.common.base.entity.StlCapacctInfoKey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StlCapacctInfoMapper {
    int deleteByPrimaryKey(StlCapacctInfoKey key);

    int insert(StlCapacctInfo record);

    int insertSelective(StlCapacctInfo record);

    int insertBasicByBasicInfo(StlCapacctInfo record);

    StlCapacctInfo selectByPrimaryKey(StlCapacctInfoKey key);

    int updateByPrimaryKeySelective(StlCapacctInfo record);

    int updateByPrimaryKey(StlCapacctInfo record);

    List<SelectOptionDto> getAcctInfo(Map<String,Object> param);
}