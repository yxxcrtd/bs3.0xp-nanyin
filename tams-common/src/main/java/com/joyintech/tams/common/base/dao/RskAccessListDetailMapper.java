package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskAccessListDetail;
import com.joyintech.tams.common.base.entity.RskAccessListDetailKey;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface RskAccessListDetailMapper {
    int deleteByPrimaryKey(RskAccessListDetailKey key);

    int insert(RskAccessListDetail record);

    int insertSelective(RskAccessListDetail record);

    int insertBasicByBasicInfo(RskAccessListDetail record);

    RskAccessListDetail selectByPrimaryKey(RskAccessListDetailKey key);
    
    int selectByName(Map<String,Object> map);
    
    List<RskAccessListDetail> selectEntityList(Map<String,Object> map);

    int updateByPrimaryKeySelective(RskAccessListDetail record);

    int updateByPrimaryKey(RskAccessListDetail record);
}