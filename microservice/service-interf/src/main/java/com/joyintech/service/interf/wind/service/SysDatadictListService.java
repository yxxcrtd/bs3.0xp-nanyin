/*
 * 文件名：SysDatadictListService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2019年1月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.wind.service;

import java.util.List;

import com.joyintech.tams.systemtools.dto.SysDataDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.constant.InterfDictCode;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.service.interf.wind.dao.SysDatadictListMapper;

/**
 * 数据字典项Service
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月8日
 * @see SysDatadictListService
 * @since 1.0
 */
@Service
public class SysDatadictListService {

    /**
     * 数据字典项查询Mapper
     */
    @Autowired
    private SysDatadictListMapper selectMapper;

    /**
     * 行政区划_省
     */
    public static final String PROVINCE = "ADMINISTRATIVE_TYPE1";

    /**
     * 行政区划_市
     */
    public static final String CITY = "ADMINISTRATIVE_TYPE2";

    /**
     * 行政区划_县
     */
    public static final String COUNTY = "ADMINISTRATIVE_TYPE3";

    /**
     * 企业性质
     */
    public static final String COMP_PROP = "ENTERPRISE_PROPERTY";

    /**
     * 评级类型
     */
    public static final String GRADE_TYPE = "MAIN_GRADE_TYPE";

    /**
     * 长期短期
     */
    public static final String LONG_SHORT_TERM = "LONG_SHORT_TERM";

    /**
     * 数据字典项
     */
    private List<SysDataDict> sysDataDictList;

    /**
     * 取得数据字典项
     * 
     * @param dictCodeList 字典代码
     * @return 数据字典项
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<SysDataDict> getSysDatadictList(List<String> dictCodeList) {
        return selectMapper.selectSysDatadictList(dictCodeList);
    }

    /**
     * 获取 数据字典项
     * 
     * @return 数据字典项.
     */
    public List<SysDataDict> getSysDataDictList() {
        if (StringUtil.isEmptyList(sysDataDictList)) {
            selectList();
        }
        return sysDataDictList;
    }

    /**
     * 取得数据代码
     * 
     * @param dictCode 字典代码
     * @param itemValue 参数值
     * @return 数据代码
     * @see
     * @since 1.0
     */
    public String getItemCode(String dictCode, String itemValue) {
        if (StringUtil.isEmptyList(sysDataDictList)) {
            selectList();
        }
        for (SysDataDict dict : sysDataDictList) {
            if (StringUtil.equals(dictCode, dict.getDictCode())
                && (StringUtil.contains(dict.getItemValue(), itemValue)
                    || StringUtil.contains(itemValue, dict.getItemValue()))) {
                return dict.getItemCode();
            }
        }
        return itemValue;
    }

    /**
     * 数据字典转换
     * 
     * @param dict 数据字典
     * @param busiDict 数据字典
     * @param ratingType 参数值
     * @return 数据字典code名称
     * @see
     * @since 1.0
     */
    public String changeRateType(InterfDictCode dict, String busiDict, String ratingType) {
        InterfDictCodeList code = InterfDictCodeList.from(dict, ratingType);
        return getItemCode(busiDict, code == null ? ratingType : code.getCodeName());
    }

    /**
     * 取得数据字典项
     * 
     * @return 数据字典项
     * @see
     * @since 1.0
     */
    @Autowired
    private void selectList() {
        sysDataDictList = getSysDatadictList(null);
    }
}
