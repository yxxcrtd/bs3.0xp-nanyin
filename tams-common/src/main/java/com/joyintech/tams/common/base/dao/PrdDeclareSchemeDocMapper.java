package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PrdDeclareSchemeDoc;
import org.springframework.stereotype.Repository;

@Repository
public interface PrdDeclareSchemeDocMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(PrdDeclareSchemeDoc record);

    int insertSelective(PrdDeclareSchemeDoc record);

    int insertBasicByBasicInfo(PrdDeclareSchemeDoc record);

    PrdDeclareSchemeDoc selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(PrdDeclareSchemeDoc record);

    int updateByPrimaryKey(PrdDeclareSchemeDoc record);
}