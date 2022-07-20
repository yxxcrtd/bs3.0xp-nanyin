package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.DisProdPartner;
import org.springframework.stereotype.Repository;

@Repository
public interface DisProdPartnerMapper {
    int deleteByPrimaryKey(String prodPartenerId);

    int insert(DisProdPartner record);

    int insertSelective(DisProdPartner record);

    int insertBasicByBasicInfo(DisProdPartner record);

    DisProdPartner selectByPrimaryKey(String prodPartenerId);

    int updateByPrimaryKeySelective(DisProdPartner record);

    int updateByPrimaryKey(DisProdPartner record);
}