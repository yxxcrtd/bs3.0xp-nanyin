package com.joyintech.invest.interf.common.core.service;

import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.invest.interf.common.core.cache.InvestInterfCacheUtil;
import com.joyintech.invest.interf.common.core.dao.InvestInterfDataDictMapper;
import com.joyintech.invest.interf.common.core.entity.InterfDataDictInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @program: tams-base
 * @description:
 * @author: 王波
 * @create: 2020-02-25 16:27
 */
@Service
public class InvestInterfDataDictService {
    /**
     * 接口数据字典查询Mapper
     */
    @Autowired
    private InvestInterfDataDictMapper dictMapper;

    /**
     * 查询数据字典关系
     *
     * @param info 查询条件
     * @return 数据字典关系
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public InterfDataDictInfo selectDataDictInfo(InterfDataDictInfo info) {
        return dictMapper.selectDataDictInfo(info);
    }

    /**
     * 查询数据字典关系
     *
     * @param isOthToTz 查询类型，true：根据外围数据字典查询投组数据字典；false：反之
     * @param dictCode 数据字典代码
     * @param itemValue 数据字典项目值
     * @return 数据字典关系
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String selectDataDictInfo(boolean isOthToTz, String dictCode, String itemValue) {
        InterfDataDictInfo info = new InterfDataDictInfo();
        if (isOthToTz) {
            info.setDctCode(dictCode);
            info.setItemVal(itemValue);
        }
        else {
            info.setLnkDict(dictCode);
            info.setLnkdVal(itemValue);
        }
        return selectDataDictValue(isOthToTz, info);
    }

    /**
     * 查询数据字典关系
     *
     * @param isOthToTz 查询类型，true：根据外围数据字典查询投组数据字典；false：反之
     * @param info 查询条件
     * @return 数据字典关系
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String selectDataDictValue(boolean isOthToTz, InterfDataDictInfo info) {
        InterfDataDictInfo result = selectDataDictInfo(info);
        if (isOthToTz) {
            return result == null ? info.getItmCode() : result.getLnkItem();
        }
        return result == null ? info.getLnkItem() : result.getItmCode();
    }

    /**
     * 查询所有数据字典关系
     *
     * @return 所有数据字典关系
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<InterfDataDictInfo> selectAllInfDictList() {
        return dictMapper.selectInfDataDictList();
    }

    /**
     * 从缓存中查询所有数据字典关系
     *
     * @return 所有数据字典关系
     */
    public List<InterfDataDictInfo> getAllDictCode()
    {
        return InvestInterfCacheUtil.getAllDictCode();
    }
}
