package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflTrdProductDealPre;
import com.joyintech.tams.common.base.entity.WflTrdProductDealPreKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflTrdProductDealPreMapper {
    int deleteByPrimaryKey(WflTrdProductDealPreKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflTrdProductDealPre record);

    int updateByWorkflowId(WflTrdProductDealPre record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflTrdProductDealPre record);

    int insertSelective(WflTrdProductDealPre record);

    int insertBasicByBasicInfo(WflTrdProductDealPre record);

    WflTrdProductDealPre selectByPrimaryKey(WflTrdProductDealPreKey key);

    int updateByPrimaryKeySelective(WflTrdProductDealPre record);

    int updateByPrimaryKey(WflTrdProductDealPre record);
}