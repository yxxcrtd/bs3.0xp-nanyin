package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflRskAccessList;
import com.joyintech.tams.common.base.entity.WflRskAccessListKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflRskAccessListMapper {
    int deleteByPrimaryKey(WflRskAccessListKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflRskAccessList record);

    int updateByWorkflowId(WflRskAccessList record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflRskAccessList record);

    int insertSelective(WflRskAccessList record);

    int insertBasicByBasicInfo(WflRskAccessList record);

    WflRskAccessList selectByPrimaryKey(WflRskAccessListKey key);

    int updateByPrimaryKeySelective(WflRskAccessList record);

    int updateByPrimaryKey(WflRskAccessList record);
}