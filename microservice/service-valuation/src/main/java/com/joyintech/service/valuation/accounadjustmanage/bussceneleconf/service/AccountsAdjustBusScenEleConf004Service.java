/*
 * 文件名：AccountsAdjustBusScenEleConf004Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhngming
 * 修改时间：2018年5月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
package com.joyintech.service.valuation.accounadjustmanage.bussceneleconf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.service.valuation.accounadjustmanage.bussceneleconf.dao.AccountsAdjustBusScenEleConf004Mapper;
import com.joyintech.service.valuation.accounadjustmanage.bussceneleconf.dto.AccountsAdjustBusScenEleConf002Dto;
import com.joyintech.tams.common.base.dao.BokTabledictMapper;
import com.joyintech.tams.common.base.entity.BokTabledict;

/**
 * 业务要素配置  service 
 * 
 * @author ruanhongming
 * @version 1.0 2016年5月25日
 * @see AccountsAdjustBusScenEleConf004Service
 * @since 1.0
 */
@Service(value="serviceAccountsAdjustBusScenEleConf004Service")
@Transactional(rollbackFor = Exception.class)
public class AccountsAdjustBusScenEleConf004Service extends BaseService<AccountsAdjustBusScenEleConf002Dto> {

    /**
     * 业务要素新建 mapper
     */
    @Autowired
    private AccountsAdjustBusScenEleConf004Mapper accountsAdjustBusScenEleConf004Mapper;
    
    @Autowired
    private BokTabledictMapper bokTabledictMapper;
    
    /**
     * 修改和查看模式页面初始化，查询出相应的业务要素信息
     * <功能详细描述>
     * @param dictId 业务要素代码
     * @return 业务要素信息
     * @see
     * @since 1.0
     */
    public AccountsAdjustBusScenEleConf002Dto selectBokTableDictInfo(String dictId) {
        return accountsAdjustBusScenEleConf004Mapper.selectBokTableDictInfo(dictId);
    }
    
    /**
     * 查看数据项代码是否已经存在
     * <功能详细描述>
     * @param dto 页面参数dto
     * @return true：已存在；false：不存在
     * @see
     * @since 1.0
     */
    public boolean checkDataRepeat4DictCode(AccountsAdjustBusScenEleConf002Dto dto) {
        return accountsAdjustBusScenEleConf004Mapper.checkDataRepeat4DictCode(dto) > 0;
    }
    
    /**
     * 查看数据项名称是否已经存在
     * <功能详细描述>
     * @param dto 页面参数dto
     * @return true：已存在；false：不出在
     * @see
     * @since 1.0
     */
    public boolean checkDataRepeat4DictName(AccountsAdjustBusScenEleConf002Dto dto) {
        return accountsAdjustBusScenEleConf004Mapper.checkDataRepeat4DictName(dto) > 0;
    }
    
    /**
     * 已被使用过的会计元素不可修改数据项代码、数据类型以及数据来源！
     * 查询是否这几项是否
     * <功能详细描述>
     * @param dto 页面参数dto
     * @return true：已存在；false：不出在
     * @see
     * @since 1.0
     */
    public boolean checkDataUsed(AccountsAdjustBusScenEleConf002Dto dto) {
        return accountsAdjustBusScenEleConf004Mapper.checkDataUsed(dto) > 0;
    }
    
    /**
     * 保存
     * <功能详细描述>
     * @param dto 页面参数dto
     * @return 是否保存成功
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public boolean saveData(AccountsAdjustBusScenEleConf002Dto dto) throws Exception {
        if(GlobalConstant.DETAIL_PAGE_MODEL_NEW.equals(dto.getDealType())) {
            BokTabledict dict = new BokTabledict();
            BeanUtil.copyPropertiesIgnoreNull(dict, dto);
            dict.setDictId(1000+Math.random()*10000+"");
            bokTabledictMapper.insert(dict);
        }
        else {
            BokTabledict dict = new BokTabledict();
            dict.setDictCode(dto.getDictCode());
            dict.setDictId(dto.getDictId());
            dict.setDictName(dto.getDictName());
            dict.setDictSource(dto.getDictSource());
            dict.setDictType(dto.getDictType());
            dict.setTableId(dto.getTableId());
            bokTabledictMapper.updateByPrimaryKeySelective(dict);
        }
        return true;
    }
    
}
