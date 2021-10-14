package com.joyintech.tams.business.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.business.common.entity.CfgDataTableShow;

@Repository
public interface CfgDataTableMapper {

    /**
     * 查询数据表格式设置
     * @param cfgDataTableShow
     * @return
     */
    public List<CfgDataTableShow> getCfgDataTableList(CfgDataTableShow cfgDataTableShow);

    /**
     * 查询整个页面数据表格式设置
     * @param pageCode
     * @return
     */
    @Select("select * from CFG_DATATABLE_SHOW where PAGECODE = #{pageCode} and status = 1")
    public List<CfgDataTableShow> listByPageCode(String pageCode);

}
