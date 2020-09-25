package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbMstExchangeInfo;
import com.joyintech.tams.common.base.entity.ChbMstExchangeInfoKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbMstExchangeInfoMapper {
    int deleteByPrimaryKey(ChbMstExchangeInfoKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbMstExchangeInfo record);

    int insertSelective(ChbMstExchangeInfo record);

    int insertBasicByBasicInfo(ChbMstExchangeInfo record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbMstExchangeInfo selectByPrimaryKey(ChbMstExchangeInfoKey key);

    int updateByPrimaryKeySelective(ChbMstExchangeInfo record);

    int updateByWorkflowIdSelective(ChbMstExchangeInfo record);

    int updateByWorkflowId(ChbMstExchangeInfo record);

    int updateByPrimaryKey(ChbMstExchangeInfo record);
}