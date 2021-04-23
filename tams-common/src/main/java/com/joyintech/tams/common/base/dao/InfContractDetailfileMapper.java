package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InfContractDetailfile;
import org.springframework.stereotype.Repository;

@Repository
public interface InfContractDetailfileMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(InfContractDetailfile record);

    int insertSelective(InfContractDetailfile record);

    int insertBasicByBasicInfo(InfContractDetailfile record);

    InfContractDetailfile selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(InfContractDetailfile record);

    int updateByPrimaryKey(InfContractDetailfile record);
}