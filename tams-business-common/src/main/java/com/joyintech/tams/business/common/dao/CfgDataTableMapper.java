package com.joyintech.tams.business.common.dao;

import com.joyintech.tams.business.common.entity.CfgDataTableShow;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
    @MapKey("DTCODE")
    public Map<String,List<CfgDataTableShow>> getByPageCode(String pageCode);

}
