package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflRskAccessListLog;
import com.joyintech.tams.common.base.entity.WflRskAccessListLogKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflRskAccessListLogMapper {
    int deleteByPrimaryKey(WflRskAccessListLogKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflRskAccessListLog record);

    int updateByWorkflowId(WflRskAccessListLog record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflRskAccessListLog record);

    int insertSelective(WflRskAccessListLog record);

    int insertBasicByBasicInfo(WflRskAccessListLog record);

    WflRskAccessListLog selectByPrimaryKey(WflRskAccessListLogKey key);

    int updateByPrimaryKeySelective(WflRskAccessListLog record);

    int updateByPrimaryKey(WflRskAccessListLog record);
}