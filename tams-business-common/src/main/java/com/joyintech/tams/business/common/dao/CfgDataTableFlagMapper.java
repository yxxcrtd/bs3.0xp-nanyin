package com.joyintech.tams.business.common.dao;

import com.joyintech.tams.business.common.entity.CfgDataTableShowFlag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CfgDataTableFlagMapper {

    /**
     * 查询数据表格设置记录，查询到数据表示用户已设置，否则用户未设置
     * @param pageCode
     * @param dtCode
     * @param rangeId
     * @return
     */
    CfgDataTableShowFlag getByPageCodeAndDtCode(@Param("pageCode") String pageCode, @Param("dtCode") String dtCode, @Param("rangeId") String rangeId);

    /**
     * 新增数据表格设置记录
     * @param cfgDataTableFlag
     * @return
     */
    int insertCfgDataTableFlag(CfgDataTableShowFlag cfgDataTableFlag);

}
