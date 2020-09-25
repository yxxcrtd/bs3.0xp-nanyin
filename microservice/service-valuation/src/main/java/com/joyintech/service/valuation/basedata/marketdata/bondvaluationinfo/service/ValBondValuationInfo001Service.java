/*
 * 文件名：ValBondValuationInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.basedata.marketdata.bondvaluationinfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.valuation.basedata.marketdata.bondvaluationinfo.dao.ValBondValuationInfo001Mapper;
import com.joyintech.service.valuation.basedata.marketdata.bondvaluationinfo.dto.ValBondValuationInfo001Dto;
import com.joyintech.tams.common.base.dao.MstBondValuationInfoMapper;
import com.joyintech.tams.common.base.entity.MstBondValuationInfoKey;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 债券估值行情
 * @author niuqianyu
 * @version 1.0 2018年7月18日
 * @see ValBondValuationInfo001Service
 * @since 1.0
 */
@Service(value="serviceValBondValuationInfo001Service")
public class ValBondValuationInfo001Service extends CommonExportService<ValBondValuationInfo001Dto>{
    /**
     * 债券估值行情mapper
     */
    @Autowired
    private ValBondValuationInfo001Mapper valBondValuationInfo001Mapper;
    
    /**
     * 债券估值行情mapper
     */
    @Autowired
    private MstBondValuationInfoMapper mstBondValuationInfoMapper;
    
    /**
     * 
     *债券估值行情查询
     * @param dto ValBondValuationInfo001SearchDto
     * @return ValBondValuationInfo001Dto
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<ValBondValuationInfo001Dto> queryPageList(int page,int length,Map<String, Object> params) throws Exception{
        
        return super.queryForPageForSelectReturn(page,length,()->valBondValuationInfo001Mapper.queryPageList(params));
    }
    
    /**
     * 
     *债券估值行情查询
     * @param dto ValBondValuationInfo001SearchDto
     * @return ValBondValuationInfo001Dto
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public List<ValBondValuationInfo001Dto> queryAll(Map<String, Object> params) throws Exception{
        
        return valBondValuationInfo001Mapper.queryPageList(params);
    }

    /**
     * 删除记录 {@inheritDoc}
     */
    public int doComplete(ValBondValuationInfo001Dto entity) {
        if (null != entity && null != entity.getSecId() && null != entity.getValueDate()
            && null != entity.getValueSource()) {
            MstBondValuationInfoKey key = new MstBondValuationInfoKey();
            key.setSecId(entity.getSecId());
            key.setValueDate(DateUtil.parse(entity.getValueDate()));
            key.setValueSource(entity.getValueSource());
            return mstBondValuationInfoMapper.deleteByPrimaryKey(key);
        }
        return 0;
    }

    /**
     * 校验是否有帐套使用了指定行情数据
     * 
     * @param dto1
     * @return
     * @see
     * @since 1.0
     */
    public boolean isInfoUsed(ValBondValuationInfo001Dto dto) {
        //获取购买了指定债券的账套的最大账套日期
        ValBondValuationInfo001Dto info = valBondValuationInfo001Mapper.queryBooksetDate(dto);
        //if  参数.估值日 < 查询出的最大日期
        if(null != info && null != info.getBooksetDate() 
            && DateUtil.compareTo(DateUtil.parse(dto.getValueDate()), info.getBooksetDate()) < 0){
            return true;
        }
        return false;
    }
}
