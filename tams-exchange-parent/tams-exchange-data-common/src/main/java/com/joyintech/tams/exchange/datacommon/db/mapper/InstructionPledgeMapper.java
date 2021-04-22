package com.joyintech.tams.exchange.datacommon.db.mapper;


import com.joyintech.tams.exchange.common.db.domain.InstructionPledge;
import com.joyintech.tams.exchange.common.db.domain.InstructionPledgeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructionPledgeMapper extends IMybatisMapper<InstructionPledge, String, InstructionPledgeExample> {
    int countByExample(InstructionPledgeExample example);

    int deleteByExample(InstructionPledgeExample example);

    int deleteByPrimaryKey(String entrustId);

    int deleteBatchByPrimaryKey(List<String> ids);

    int insert(InstructionPledge record);

    int insertSelective(InstructionPledge record);

    int updateOrInsertSelective(InstructionPledge record);

    List<InstructionPledge> selectByExample(InstructionPledgeExample example);

    InstructionPledge selectByPrimaryKey(String entrustId);

    int updateByExampleSelective(@Param("record") InstructionPledge record, @Param("example") InstructionPledgeExample example);

    int updateByExample(@Param("record") InstructionPledge record, @Param("example") InstructionPledgeExample example);

    int updateByPrimaryKeySelective(InstructionPledge record);

    int updateByPrimaryKey(InstructionPledge record);
}