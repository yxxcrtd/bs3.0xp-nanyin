package com.joyintech.invest.interf.common.core.dao;

import com.joyintech.invest.interf.common.core.entity.InterfDataDictInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: tams-base
 * @description:
 * @author: 王波
 * @create: 2020-02-25 16:28
 */
@Repository
public interface InvestInterfDataDictMapper {
    /**
     * 根据条件查询数据字典（外围系统数据字典-投组系统数据字典）
     *
     * @param info 检索条件
     * @return 数据字典（外围系统数据字典-投组系统数据字典）
     */
    InterfDataDictInfo selectDataDictInfo(InterfDataDictInfo info);

    /**
     * 查询所有数据字典对应关系
     *
     * @return 数据字典对应关系
     */
    List<InterfDataDictInfo> selectInfDataDictList();
}
