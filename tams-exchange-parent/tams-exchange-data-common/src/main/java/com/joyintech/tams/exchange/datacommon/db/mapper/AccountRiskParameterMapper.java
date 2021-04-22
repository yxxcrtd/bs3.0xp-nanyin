package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.db.domain.AccountRiskParameter;
import com.joyintech.tams.exchange.common.db.domain.AccountRiskParameterExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRiskParameterMapper extends IMybatisMapper<AccountRiskParameter, String, AccountRiskParameterExample> {
    int countByExample(AccountRiskParameterExample example);

    int deleteByExample(AccountRiskParameterExample example);

    int deleteByPrimaryKey(String accountId);

    int insert(AccountRiskParameter record);

    int insertSelective(AccountRiskParameter record);

    int updateOrInsertSelective(AccountRiskParameter record);

    int deleteBatchByPrimaryKey(List<String> ids);

    List<AccountRiskParameter> selectByExample(AccountRiskParameterExample example);

    AccountRiskParameter selectByPrimaryKey(String accountId);

    int updateByExampleSelective(@Param("record") AccountRiskParameter record, @Param("example") AccountRiskParameterExample example);

    int updateByExample(@Param("record") AccountRiskParameter record, @Param("example") AccountRiskParameterExample example);

    int updateByPrimaryKeySelective(AccountRiskParameter record);

    int updateByPrimaryKey(AccountRiskParameter record);
}