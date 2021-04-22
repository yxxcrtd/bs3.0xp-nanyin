package com.joyintech.tams.exchange.datacommon.db.mapper;


import java.util.List;

public interface IMybatisMapper<T, ID, S> {

    T selectByPrimaryKey(ID id);

    int insert(T record);

    // 允许默认值
    int insertSelective(T record);

    int updateOrInsertSelective(T record);

    int updateByPrimaryKey(T record);

    //允许默认值
    int updateByPrimaryKeySelective(T record);

    int deleteByPrimaryKey(ID id);

    int deleteBatchByPrimaryKey(List<ID> ids);

    List<T> selectByExample(S example);
}
