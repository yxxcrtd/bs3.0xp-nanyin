package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttTestObj;
import com.joyintech.tams.common.base.entity.SttTestObjKey;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SttTestObjMapper {
    int deleteByPrimaryKey(SttTestObjKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(SttTestObj record);

    int insertSelective(SttTestObj record);

    int insertBasicByBasicInfo(SttTestObj record);

    int insertBasicByWorkflowId(Map paraMap);

    SttTestObj selectByPrimaryKey(SttTestObjKey key);

    int updateByPrimaryKeySelective(SttTestObj record);

    int updateByWorkflowIdSelective(SttTestObj record);

    int updateByWorkflowId(SttTestObj record);

    int updateByPrimaryKey(SttTestObj record);
    
    @Select("select OBJECT_ID from STT_TEST_OBJ where TEST_ID = #{testId,jdbcType=VARCHAR}")
    List<String> getObjectIds(String testId);
}