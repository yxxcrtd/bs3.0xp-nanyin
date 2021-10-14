package com.joyintech.tams.business.common.dao;

import com.joyintech.tams.business.common.entity.CfgDataTableShow;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CfgDataTableMapper {

    void insertCfgDataTableBatch(List<CfgDataTableShow> list);

    /**
     * 查询数据表格式设置
     * @param cfgDataTableShow
     * @return
     */
    List<CfgDataTableShow> getCfgDataTableList(CfgDataTableShow cfgDataTableShow);

    /**
     * 查询整个页面数据表格式设置
     *
     * @param pageCode
     * @return
     */
    @Select("select * from CFG_DATATABLE_SHOW where PAGECODE = #{pageCode} and status = 1")
    List<CfgDataTableShow> listByPageCode(String pageCode);

    List<CfgDataTableShow> getByPageCode1(@Param("pageCode") String pageCode, @Param("dtCode") String dtCode,
                                          @Param("effeRange") Integer effeRange,@Param("rangeId") String rangeId);

    List<String> getDtCodeByPageCode(@Param("pageCode") String pageCode);

}
