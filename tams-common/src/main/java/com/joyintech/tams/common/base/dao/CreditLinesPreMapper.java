package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.CreditLinesPre;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditLinesPreMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(CreditLinesPre record);

    int insertSelective(CreditLinesPre record);

    int insertBasicByBasicInfo(CreditLinesPre record);

    CreditLinesPre selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(CreditLinesPre record);

    int updateByPrimaryKey(CreditLinesPre record);
}