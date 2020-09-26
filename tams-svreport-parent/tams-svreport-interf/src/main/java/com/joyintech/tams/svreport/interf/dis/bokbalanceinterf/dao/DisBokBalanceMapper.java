package com.joyintech.tams.svreport.interf.dis.bokbalanceinterf.dao;

import com.joyintech.tams.svreport.interf.dis.bokbalanceinterf.dto.BokBalance;
import com.joyintech.tams.svreport.interf.dis.bokbalanceinterf.dto.BokBalanceKey;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface DisBokBalanceMapper {
    int deleteByPrimaryKey(BokBalanceKey key);

    int insert(List<BokBalance> list);

    int insertSelective(BokBalance record);

    int insertBasicByBasicInfo(BokBalance record);

    BokBalance selectByPrimaryKey(BokBalanceKey key);

    int updateByPrimaryKeySelective(BokBalance record);

    int updateByPrimaryKey(BokBalance record);
    
    void  delete(Date balanceDate);
}