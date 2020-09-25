package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysOrganization;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysOrganizationMapper {
    int deleteByPrimaryKey(String seqno);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbSysOrganization record);

    int insertSelective(ChbSysOrganization record);

    int insertBasicByBasicInfo(ChbSysOrganization record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbSysOrganization selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(ChbSysOrganization record);

    int updateByWorkflowIdSelective(ChbSysOrganization record);

    int updateByWorkflowId(ChbSysOrganization record);

    int updateByPrimaryKey(ChbSysOrganization record);
}