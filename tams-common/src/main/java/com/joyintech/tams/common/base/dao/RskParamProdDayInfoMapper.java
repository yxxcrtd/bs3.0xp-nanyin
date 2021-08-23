package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskParamProdDayInfo;
import com.joyintech.tams.common.base.entity.RskParamProdDayInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RskParamProdDayInfoMapper {
    int deleteByPrimaryKey(RskParamProdDayInfoKey key);

    int insert(RskParamProdDayInfo record);

    int insertSelective(RskParamProdDayInfo record);

    int insertBasicByBasicInfo(RskParamProdDayInfo record);

    RskParamProdDayInfo selectByPrimaryKey(RskParamProdDayInfoKey key);

    int updateByPrimaryKeySelective(RskParamProdDayInfo record);

    int updateByPrimaryKey(RskParamProdDayInfo record);
}