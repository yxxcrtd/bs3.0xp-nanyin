/*
 * 文件名：BaseMstFinCashInfoServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年8月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.FinCashInfoMapper;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.base.core.BaseFinProductCashInfoService;
import com.joyintech.tams.component.base.core.dto.BaseFinProductCashInfoDto;
import com.joyintech.tams.component.base.core.impl.dao.BaseFinProductCashInfoMapper;



/**
 * 金融产品现金流信息组件
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年8月28日
 * @see BaseFinProductCashInfoServiceImpl
 * @since 1.0
 */
@Service
public class BaseFinProductCashInfoServiceImpl implements BaseFinProductCashInfoService{
    /**
     * 金融产品现金流信息组件mapper
     */
    @Autowired
    private BaseFinProductCashInfoMapper baseFinProductCashInfoMapper;
    /**
     * 自动生成现金流mapper
     */
    @Autowired
    private FinCashInfoMapper finCashInfoMapper;

    /**
     * 删除金融产品现金流信息
     */
    @Override
    public boolean deleteByPrimaryKey(FinCashInfo finCashInfo) {
        return baseFinProductCashInfoMapper.deleteByPrimaryKey(finCashInfo)==1?true:false;
    }
    /**
     * 新增金融产品现金流信息
     */
    @Override
    public boolean insertList(List<FinCashInfo> list) {
        if(list!=null&&list.size()>0){
            for(FinCashInfo finCashInfo:list){
                finCashInfoMapper.insert(finCashInfo);
            }
            return true;
        }
        return false;
    }

    /**
     * 修改金融产品现金流信息
     */
    @Override
    public boolean update(BaseFinProductCashInfoDto dto){
        String cashId =dto.getCashId();
        List<FinCashInfo> list=dto.getFinCashInfoList();
        if(StringUtil.isNotEmpty(cashId)&&StringUtil.isNotEmpty(list)){
            FinCashInfo entity = new FinCashInfo();
            entity.setCashId(cashId);
            entity.setCashType1(dto.getCashType1());
            entity.setCashType2(dto.getCashType2());
            entity.setFinprodId(dto.getFinprodId());
            entity.setBranch(dto.getBranch());
            if(this.deleteByPrimaryKey(entity)){
                this.insertList(list);
                return true;
            }
        }
       return false;
    }

    /**
     * 查询
     */
    @Override
    public List<FinCashInfo> select(FinCashInfo finCashInfo) {
        return baseFinProductCashInfoMapper.selectDetail(finCashInfo);
    }

}
