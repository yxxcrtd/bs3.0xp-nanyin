package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ValInterfTemplate;
import com.joyintech.tams.common.base.entity.ValInterfTemplateKey;
import org.springframework.stereotype.Repository;

@Repository
public interface ValInterfTemplateMapper {
    int deleteByPrimaryKey(ValInterfTemplateKey key);

    int insert(ValInterfTemplate record);

    int insertSelective(ValInterfTemplate record);

    int insertBasicByBasicInfo(ValInterfTemplate record);

    ValInterfTemplate selectByPrimaryKey(ValInterfTemplateKey key);

    int updateByPrimaryKeySelective(ValInterfTemplate record);

    int updateByPrimaryKey(ValInterfTemplate record);
}