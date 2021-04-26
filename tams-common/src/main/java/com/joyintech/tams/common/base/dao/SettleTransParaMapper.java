package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SettleTransPara;
import org.springframework.stereotype.Repository;

@Repository
public interface SettleTransParaMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(SettleTransPara record);

    int insertSelective(SettleTransPara record);

    int insertBasicByBasicInfo(SettleTransPara record);

    SettleTransPara selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(SettleTransPara record);

    int updateByPrimaryKey(SettleTransPara record);
}