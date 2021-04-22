package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.db.domain.PositionBean;
import com.joyintech.tams.exchange.common.db.domain.PositionBeanExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionBeanMapper extends IMybatisMapper<PositionBean, String, PositionBeanExample> {
    int countByExample(PositionBeanExample example);

    int deleteByExample(PositionBeanExample example);

    int deleteByPrimaryKey(String id);

    int insert(PositionBean record);

    int insertSelective(PositionBean record);

    int updateOrInsertSelective(PositionBean record);

    int deleteBatchByPrimaryKey(List<String> ids);

    List<PositionBean> selectByExample(PositionBeanExample example);

    PositionBean selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PositionBean record, @Param("example") PositionBeanExample example);

    int updateByExample(@Param("record") PositionBean record, @Param("example") PositionBeanExample example);

    int updateByPrimaryKeySelective(PositionBean record);

    int updateByPrimaryKey(PositionBean record);
}