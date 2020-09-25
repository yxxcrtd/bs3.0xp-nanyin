package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflTrdProductDealAddPre;
import com.joyintech.tams.common.base.entity.WflTrdProductDealAddPreKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflTrdProductDealAddPreMapper {
    int deleteByPrimaryKey(WflTrdProductDealAddPreKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflTrdProductDealAddPre record);

    int updateByWorkflowId(WflTrdProductDealAddPre record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflTrdProductDealAddPre record);

    int insertSelective(WflTrdProductDealAddPre record);

    int insertBasicByBasicInfo(WflTrdProductDealAddPre record);

    WflTrdProductDealAddPre selectByPrimaryKey(WflTrdProductDealAddPreKey key);

    int updateByPrimaryKeySelective(WflTrdProductDealAddPre record);

    int updateByPrimaryKey(WflTrdProductDealAddPre record);
}