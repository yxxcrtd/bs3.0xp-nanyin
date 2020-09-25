package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbOrgRegistBankCode;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbOrgRegistBankCodeMapper {
    int deleteByPrimaryKey(String orgCode);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbOrgRegistBankCode record);

    int insertSelective(ChbOrgRegistBankCode record);

    int insertBasicByBasicInfo(ChbOrgRegistBankCode record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbOrgRegistBankCode selectByPrimaryKey(String orgCode);

    int updateByPrimaryKeySelective(ChbOrgRegistBankCode record);

    int updateByWorkflowIdSelective(ChbOrgRegistBankCode record);

    int updateByWorkflowId(ChbOrgRegistBankCode record);

    int updateByPrimaryKey(ChbOrgRegistBankCode record);
}