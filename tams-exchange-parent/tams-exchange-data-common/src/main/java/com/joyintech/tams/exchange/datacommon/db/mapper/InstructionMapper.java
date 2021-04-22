package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.db.domain.Instruction;
import com.joyintech.tams.exchange.common.db.domain.InstructionExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructionMapper extends IMybatisMapper<Instruction, String, InstructionExample> {
    int countByExample(InstructionExample example);

    int deleteByExample(InstructionExample example);

    int deleteByPrimaryKey(String entrustId);

    int deleteBatchByPrimaryKey(List<String> ids);

    int insert(Instruction record);

    int insertSelective(Instruction record);

    int updateOrInsertSelective(Instruction record);

    List<Instruction> selectByExample(InstructionExample example);

    Instruction selectByPrimaryKey(String entrustId);

    int updateByExampleSelective(@Param("record") Instruction record, @Param("example") InstructionExample example);

    int updateByExample(@Param("record") Instruction record, @Param("example") InstructionExample example);

    int updateByPrimaryKeySelective(Instruction record);

    int updateByPrimaryKey(Instruction record);
}