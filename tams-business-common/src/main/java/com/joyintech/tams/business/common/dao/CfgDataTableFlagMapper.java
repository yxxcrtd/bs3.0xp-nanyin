package com.joyintech.tams.business.common.dao;

import com.joyintech.tams.business.common.entity.CfgDataTableShowFlag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CfgDataTableFlagMapper {

    CfgDataTableShowFlag getByPageCodeAndDtCode(@Param("pageCode") String pageCode, @Param("dtCode") String dtCode, @Param("rangeId") String rangeId);

}
