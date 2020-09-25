package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflRskAccessListDetail;
import com.joyintech.tams.common.base.entity.WflRskAccessListDetailKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflRskAccessListDetailMapper {
    int deleteByPrimaryKey(WflRskAccessListDetailKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflRskAccessListDetail record);

    int updateByWorkflowId(WflRskAccessListDetail record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflRskAccessListDetail record);

    int insertSelective(WflRskAccessListDetail record);

    int insertBasicByBasicInfo(WflRskAccessListDetail record);

    WflRskAccessListDetail selectByPrimaryKey(WflRskAccessListDetailKey key);

    int updateByPrimaryKeySelective(WflRskAccessListDetail record);

    int updateByPrimaryKey(WflRskAccessListDetail record);
}