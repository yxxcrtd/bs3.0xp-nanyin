package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfCmsInstitution;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfCmsInstitutionMapper {
    int insert(InterfCmsInstitution record);

    int insertSelective(InterfCmsInstitution record);

    int insertBasicByBasicInfo(InterfCmsInstitution record);
}