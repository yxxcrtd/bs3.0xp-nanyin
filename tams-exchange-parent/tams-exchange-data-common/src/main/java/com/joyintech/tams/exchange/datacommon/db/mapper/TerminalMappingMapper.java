package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.db.domain.TerminalMapping;
import com.joyintech.tams.exchange.common.db.domain.TerminalMappingExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerminalMappingMapper extends IMybatisMapper<TerminalMapping, String, TerminalMappingExample> {
    int countByExample(TerminalMappingExample example);

    int deleteByExample(TerminalMappingExample example);

    int deleteByPrimaryKey(String id);

    int insert(TerminalMapping record);

    int insertSelective(TerminalMapping record);

    List<TerminalMapping> selectByExample(TerminalMappingExample example);

    TerminalMapping selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TerminalMapping record, @Param("example") TerminalMappingExample example);

    int updateByExample(@Param("record") TerminalMapping record, @Param("example") TerminalMappingExample example);

    int updateByPrimaryKeySelective(TerminalMapping record);

    int updateByPrimaryKey(TerminalMapping record);
}