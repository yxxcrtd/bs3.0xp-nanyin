package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflRegTradeFee;
import com.joyintech.tams.common.base.entity.WflRegTradeFeeKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflRegTradeFeeMapper {
    int deleteByPrimaryKey(WflRegTradeFeeKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflRegTradeFee record);

    int updateByWorkflowId(WflRegTradeFee record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflRegTradeFee record);

    int insertSelective(WflRegTradeFee record);

    int insertBasicByBasicInfo(WflRegTradeFee record);

    WflRegTradeFee selectByPrimaryKey(WflRegTradeFeeKey key);

    int updateByPrimaryKeySelective(WflRegTradeFee record);

    int updateByPrimaryKey(WflRegTradeFee record);
}