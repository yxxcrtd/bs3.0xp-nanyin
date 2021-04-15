package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstSaleDepartment;
import org.springframework.stereotype.Repository;

@Repository
public interface MstSaleDepartmentMapper {
    int deleteByPrimaryKey(String saleDepid);

    int insert(MstSaleDepartment record);

    int insertSelective(MstSaleDepartment record);

    int insertBasicByBasicInfo(MstSaleDepartment record);

    MstSaleDepartment selectByPrimaryKey(String saleDepid);

    int updateByPrimaryKeySelective(MstSaleDepartment record);

    int updateByPrimaryKey(MstSaleDepartment record);
}