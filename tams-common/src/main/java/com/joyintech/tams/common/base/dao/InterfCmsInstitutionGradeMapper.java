package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfCmsInstitutionGrade;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfCmsInstitutionGradeMapper {
    int insert(InterfCmsInstitutionGrade record);

    int insertSelective(InterfCmsInstitutionGrade record);

    int insertBasicByBasicInfo(InterfCmsInstitutionGrade record);
}