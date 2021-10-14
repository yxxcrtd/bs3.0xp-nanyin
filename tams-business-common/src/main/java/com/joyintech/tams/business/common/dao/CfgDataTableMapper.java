package com.joyintech.tams.business.common.dao;

import com.joyintech.tams.business.common.entity.CfgDataTableShow;
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
    public Map<String,List<CfgDataTableShow>> getByPageCode(String pageCode);

}
