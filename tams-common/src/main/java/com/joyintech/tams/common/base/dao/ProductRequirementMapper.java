package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ProductRequirement;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRequirementMapper {
    int deleteByPrimaryKey(String requirementId);

    int insert(ProductRequirement record);

    int insertSelective(ProductRequirement record);

    int insertBasicByBasicInfo(ProductRequirement record);

    ProductRequirement selectByPrimaryKey(String requirementId);

    int updateByPrimaryKeySelective(ProductRequirement record);

    int updateByPrimaryKey(ProductRequirement record);
}