package com.joyintech.tams.business.common.service;

import com.joyintech.tams.business.common.dao.CfgDataTableMapper;
import com.joyintech.tams.business.common.entity.CfgDataTableShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 数据表格设置service
 */
@Service
public class CfgDataTableService {

    @Autowired
    private CfgDataTableMapper cfgDataTableMapper;

    /**
     * 查询数据表格式设置
     * @param cfgDataTableShow
     * @return
     */
    public List<CfgDataTableShow> getCfgDataTableList(CfgDataTableShow cfgDataTableShow){
        return cfgDataTableMapper.getCfgDataTableList(cfgDataTableShow);
    }

    /**
     * 查询整个页面数据表格式设置
     * @param pageCode
     * @return
     */
    public Map<String,List<CfgDataTableShow>> getByPageCode(String pageCode){
        return cfgDataTableMapper.getByPageCode(pageCode);
    }

}
