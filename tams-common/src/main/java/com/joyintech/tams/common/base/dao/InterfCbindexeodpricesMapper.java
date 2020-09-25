package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfCbindexeodprices;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfCbindexeodpricesMapper {
    int deleteByPrimaryKey(String objectId);

    int insert(InterfCbindexeodprices record);

    int insertSelective(InterfCbindexeodprices record);

    int insertBasicByBasicInfo(InterfCbindexeodprices record);

    InterfCbindexeodprices selectByPrimaryKey(String objectId);

    int updateByPrimaryKeySelective(InterfCbindexeodprices record);

    int updateByPrimaryKey(InterfCbindexeodprices record);
}