package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.db.domain.Entrust;
import com.joyintech.tams.exchange.common.db.domain.EntrustExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrustMapper extends IMybatisMapper<Entrust, String, EntrustExample> {
    int countByExample(EntrustExample example);

    int deleteByExample(EntrustExample example);

    int deleteByPrimaryKey(String id);

    int deleteBatchByPrimaryKey(List<String> ids);

    int insert(Entrust record);

    int insertSelective(Entrust record);

    int updateOrInsertSelective(Entrust record);

    List<Entrust> selectByExample(EntrustExample example);

    Entrust selectByPrimaryKey(String id);


    int updateByExampleSelective(Entrust record, EntrustExample example);

    int updateByExample(Entrust record, EntrustExample example);

    int updateByPrimaryKeySelective(Entrust record);

    int updateByPrimaryKey(Entrust record);
}