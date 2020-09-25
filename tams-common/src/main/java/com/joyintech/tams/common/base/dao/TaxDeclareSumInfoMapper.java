package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TaxDeclareSumInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxDeclareSumInfoMapper {
    int deleteByPrimaryKey(String seqId);

    int insert(TaxDeclareSumInfo record);

    int insertSelective(TaxDeclareSumInfo record);

    int insertBasicByBasicInfo(TaxDeclareSumInfo record);

    TaxDeclareSumInfo selectByPrimaryKey(String seqId);

    int updateByPrimaryKeySelective(TaxDeclareSumInfo record);

    int updateByPrimaryKey(TaxDeclareSumInfo record);
}