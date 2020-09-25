package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdProductDealPre;
import com.joyintech.tams.common.base.entity.TrdProductDealPreKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdProductDealPreMapper {
    int deleteByPrimaryKey(TrdProductDealPreKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(TrdProductDealPre record);

    int updateByWorkflowId(TrdProductDealPre record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(TrdProductDealPre record);

    int insertSelective(TrdProductDealPre record);

    int insertBasicByBasicInfo(TrdProductDealPre record);

    TrdProductDealPre selectByPrimaryKey(TrdProductDealPreKey key);

    int updateByPrimaryKeySelective(TrdProductDealPre record);

    int updateByPrimaryKey(TrdProductDealPre record);
}