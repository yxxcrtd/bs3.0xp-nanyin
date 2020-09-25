package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflPaTrdFinDealTotal;
import com.joyintech.tams.common.base.entity.WflPaTrdFinDealTotalKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflPaTrdFinDealTotalMapper {
    int deleteByPrimaryKey(WflPaTrdFinDealTotalKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflPaTrdFinDealTotal record);

    int updateByWorkflowId(WflPaTrdFinDealTotal record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflPaTrdFinDealTotal record);

    int insertSelective(WflPaTrdFinDealTotal record);

    int insertBasicByBasicInfo(WflPaTrdFinDealTotal record);

    WflPaTrdFinDealTotal selectByPrimaryKey(WflPaTrdFinDealTotalKey key);

    int updateByPrimaryKeySelective(WflPaTrdFinDealTotal record);

    int updateByPrimaryKey(WflPaTrdFinDealTotal record);
}