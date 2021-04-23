package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.db.domain.Terminal;
import com.joyintech.tams.exchange.common.db.domain.TerminalExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerminalMapper extends IMybatisMapper<Terminal, String, TerminalExample> {
    int countByExample(TerminalExample example);

    int deleteByExample(TerminalExample example);

    int deleteByPrimaryKey(String terminalName);

    int insert(Terminal record);

    int insertSelective(Terminal record);

    List<Terminal> selectByExample(TerminalExample example);

    Terminal selectByPrimaryKey(String terminalName);

    int updateByExampleSelective(@Param("record") Terminal record, @Param("example") TerminalExample example);

    int updateByExample(@Param("record") Terminal record, @Param("example") TerminalExample example);

    int updateByPrimaryKeySelective(Terminal record);

    int updateByPrimaryKey(Terminal record);
}