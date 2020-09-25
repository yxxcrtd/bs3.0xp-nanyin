package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSubCheckItemConvert;
import com.joyintech.tams.common.base.entity.ChbSubCheckItemConvertKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSubCheckItemConvertMapper {
    int deleteByPrimaryKey(ChbSubCheckItemConvertKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbSubCheckItemConvert record);

    int insertSelective(ChbSubCheckItemConvert record);

    int insertBasicByBasicInfo(ChbSubCheckItemConvert record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbSubCheckItemConvert selectByPrimaryKey(ChbSubCheckItemConvertKey key);

    int updateByPrimaryKeySelective(ChbSubCheckItemConvert record);

    int updateByWorkflowIdSelective(ChbSubCheckItemConvert record);

    int updateByWorkflowId(ChbSubCheckItemConvert record);

    int updateByPrimaryKey(ChbSubCheckItemConvert record);
}