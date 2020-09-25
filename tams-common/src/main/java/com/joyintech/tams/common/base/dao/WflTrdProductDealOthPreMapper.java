package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflTrdProductDealOthPre;
import com.joyintech.tams.common.base.entity.WflTrdProductDealOthPreKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflTrdProductDealOthPreMapper {
    int deleteByPrimaryKey(WflTrdProductDealOthPreKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflTrdProductDealOthPre record);

    int updateByWorkflowId(WflTrdProductDealOthPre record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflTrdProductDealOthPre record);

    int insertSelective(WflTrdProductDealOthPre record);

    int insertBasicByBasicInfo(WflTrdProductDealOthPre record);

    WflTrdProductDealOthPre selectByPrimaryKey(WflTrdProductDealOthPreKey key);

    int updateByPrimaryKeySelective(WflTrdProductDealOthPre record);

    int updateByPrimaryKey(WflTrdProductDealOthPre record);
}