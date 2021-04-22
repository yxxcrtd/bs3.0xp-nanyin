package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.bean.EntrustQryBean;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrustExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntegratedEntrustMapper extends IMybatisMapper<IntegratedEntrust, String, IntegratedEntrustExample> {
    int countByExample(IntegratedEntrustExample example);

    int deleteByExample(IntegratedEntrustExample example);

    int deleteByPrimaryKey(String id);

    int deleteBatchByPrimaryKey(List<String> ids);

    int insert(IntegratedEntrust record);

    int insertSelective(IntegratedEntrust record);

    int updateOrInsertSelective(IntegratedEntrust record);

    List<IntegratedEntrust> selectByExample(IntegratedEntrustExample example);

    List<IntegratedEntrust> selectEntrustsPageInfo(EntrustQryBean entrustQryBean);

    IntegratedEntrust selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") IntegratedEntrust record, @Param("example") IntegratedEntrustExample example);

    int updateByExample(@Param("record") IntegratedEntrust record, @Param("example") IntegratedEntrustExample example);

    int updateByPrimaryKeySelective(IntegratedEntrust record);

    int updateByPrimaryKey(IntegratedEntrust record);
}