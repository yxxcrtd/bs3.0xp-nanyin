package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflTrdProductDealOth;
import com.joyintech.tams.common.base.entity.WflTrdProductDealOthKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflTrdProductDealOthMapper {
    int deleteByPrimaryKey(WflTrdProductDealOthKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflTrdProductDealOth record);

    int updateByWorkflowId(WflTrdProductDealOth record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflTrdProductDealOth record);

    int insertSelective(WflTrdProductDealOth record);

    int insertBasicByBasicInfo(WflTrdProductDealOth record);

    WflTrdProductDealOth selectByPrimaryKey(WflTrdProductDealOthKey key);

    int updateByPrimaryKeySelective(WflTrdProductDealOth record);

    int updateByPrimaryKey(WflTrdProductDealOth record);
}