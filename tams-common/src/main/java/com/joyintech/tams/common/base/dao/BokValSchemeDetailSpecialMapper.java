package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokValSchemeDetailSpecial;
import org.springframework.stereotype.Repository;

@Repository
public interface BokValSchemeDetailSpecialMapper {
    int deleteByPrimaryKey(String schemeDetailId);

    int insert(BokValSchemeDetailSpecial record);

    int insertSelective(BokValSchemeDetailSpecial record);

    int insertBasicByBasicInfo(BokValSchemeDetailSpecial record);

    BokValSchemeDetailSpecial selectByPrimaryKey(String schemeDetailId);

    int updateByPrimaryKeySelective(BokValSchemeDetailSpecial record);

    int updateByPrimaryKey(BokValSchemeDetailSpecial record);
}