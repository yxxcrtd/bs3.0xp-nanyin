package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ProductRequirementAdd;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRequirementAddMapper {
    int deleteByPrimaryKey(String requirementId);

    int insert(ProductRequirementAdd record);

    int insertSelective(ProductRequirementAdd record);

    int insertBasicByBasicInfo(ProductRequirementAdd record);

    ProductRequirementAdd selectByPrimaryKey(String requirementId);

    int updateByPrimaryKeySelective(ProductRequirementAdd record);

    int updateByPrimaryKey(ProductRequirementAdd record);
}