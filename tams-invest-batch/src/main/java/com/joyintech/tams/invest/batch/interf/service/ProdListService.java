/*
 * 文件名：ProdListService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年12月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.interf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.web.PlatformDate;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.batch.interf.dao.ProdStartToEndMapper;

/**
 * 查询产品数据Service
 * 
 * @author zhangrui1
 * @version 1.0 2018年12月27日
 * @see ProdListService
 * @since 1.0
 */
@Service
public class ProdListService {

    @Autowired
    private ProdStartToEndMapper readerMapper;

    /**
     * 平台日期组件对象
     */
    @Autowired
    private PlatformDate platformDtImpl;

    /**
     * 产品模块类型
     *
     * @author zhangrui1
     * @version 1.0 2018年11月20日
     * @see DoType
     * @since 1.0
     */
    public enum DoType {
        /**
         * 产品成立定时任务
         */
        PROD_FOUND,
        /**
         * 产品存续定时任务
         */
        PROD_ALIVE,
        /**
         * 产品到期定时任务
         */
        PROD_END,
        /**
         * 单位净值万分收益定时任务
         */
        PROD_VNET;
    }

    /**
     * 查询产品列表
     * 
     * @param doType 产品模块类型
     * @param dealMode 处理模式列表
     * @return 产品列表
     * @see
     * @since 1.0
     */
    public List<String> createList(DoType doType, List<String> dealMode) {
        String prodType = DictCodeListExt1.金融产品类型_投管用_理财产品.getValue();
        String sysDate = DateUtil.format(platformDtImpl.getSysDate(),
            DateUtil.DEFAULT_OTHER_FORMAT);
        return readerMapper.selectProdInfo(doType.name(), prodType, sysDate, dealMode,
            DictCodeList.销售部门_零售产品管理部.getValue());
    }
}
