/*
 * 文件名：ValBondValuationInfo003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ZhangYangFei
 * 修改时间：2018年12月6日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.basedata.marketdata.bondvaluationinfo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.valuation.basedata.marketdata.bondvaluationinfo.dao.ValBondValuationInfo003Mapper;
import com.joyintech.service.valuation.basedata.marketdata.bondvaluationinfo.dto.ValBondValuationInfo002Dto;
import com.joyintech.service.valuation.basedata.marketdata.bondvaluationinfo.dto.ValBondValuationInfo003Dto;
import com.joyintech.tams.common.base.dao.MstBondValuationInfoMapper;
import com.joyintech.tams.common.base.entity.MstBondValuationInfo;
import com.joyintech.tams.common.base.entity.MstBondValuationInfoKey;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 债券估值行情-新建、删除、修改 Service
 * 
 * @author ZhangYangFei
 * @version 1.0 2018年12月6日
 * @see ValBondValuationInfo003Service
 * @since 1.0
 */
@Service(value="serviceValBondValuationInfo003Service")
public class ValBondValuationInfo003Service  extends CommonExportService<ValBondValuationInfo003Dto>{

    /**
     * 债券估值行情mapper
     */
    @Autowired
    private ValBondValuationInfo003Mapper valBondValuationInfo003Mapper;
    
    /**
     * 债券估值行情mapper
     */
    @Autowired
    private MstBondValuationInfoMapper mstBondValuationInfoMapper;

    /**
     * 根据债券代码查询债券估值行情
     * 
     * @param secId 债券代码
     * @return ValBondValuationInfo002Dto
     * @see
     * @since 1.0
     */
    public List<ValBondValuationInfo002Dto> searchList(String secId) {
        return null;
    }

    /**
     * 流程审核完成{@inheritDoc}
     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
    public int doComplete(ValBondValuationInfo003Dto entity, OperationType type) {
        MstBondValuationInfo record = trans2MstBondValuationInfo(entity);
        // 新建操作
        if (OperationType.NEW.equals(type)) {
            return mstBondValuationInfoMapper.insert(record);
        }
        else if (OperationType.MODIFY.equals(type)) {
            valBondValuationInfo003Mapper.updateByBfUpdPrimaryKeySelective(entity);
            return 1;
        }
        else if (OperationType.DELETE.equals(type)) {
            MstBondValuationInfoKey key = new MstBondValuationInfoKey();
            key.setSecId(entity.getSecId());
            key.setValueDate(DateUtil.parse(entity.getValueDate()));
            key.setValueSource(entity.getValueSource());
            return mstBondValuationInfoMapper.deleteByPrimaryKey(key);
        }
        return 0;
    }


    /**
     * 根据主键获取对应的债券估值行情
     * 
     * @param searchDto
     * @return
     * @see
     * @since 1.0
     */
    public ValBondValuationInfo003Dto getMstBondValuationInfoByKey(ValBondValuationInfo003Dto searchDto) {
        MstBondValuationInfo info = mstBondValuationInfoMapper.selectByPrimaryKey(trans2MstBondValuationInfo(searchDto));
        if(info ==null){
            return null;
        }
        ValBondValuationInfo003Dto valBondValuationInfo003Dto = trans2ValBondValuationInfo003Dto(info);
        // 查询债券简称
        ValBondValuationInfo003Dto finprodDto= valBondValuationInfo003Mapper.selectFinprodAbbrByFinprodId(info.getSecId());
        valBondValuationInfo003Dto.setFinprodAbbr(finprodDto.getFinprodAbbr());
        return valBondValuationInfo003Dto;
    }
    
    /**
     * ValBondValuationInfo003Dto转换为MstBondValuationInfo
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public MstBondValuationInfo trans2MstBondValuationInfo(ValBondValuationInfo003Dto dto){
        if(dto == null){
            return null;
        }
        MstBondValuationInfo record = new MstBondValuationInfo();
        record.setSecId(dto.getSecId());
        record.setValueSource(dto.getValueSource());
        record.setValueDate(DateUtil.parse(dto.getValueDate()));
        record.setLastPeriod(dto.getLastPeriod());
        record.setPrice(dto.getPrice());
        record.setNetprice(dto.getNetprice());
        record.setmDuration(dto.getmDuration());
        record.setmConvexity(dto.getmConvexity());
        record.setBpvalue(dto.getBpvalue());
        record.setSduration(dto.getSduration());
        record.setScnvxty(dto.getScnvxty());
        record.setInterestDuration(dto.getInterestDuration());
        record.setInterestCnvxty(dto.getInterestCnvxty());
        record.setBpyield(dto.getBpyield());
        return record;
    }
    
    /**
     * MstBondValuationInfo转换为 ValBondValuationInfo003Dto
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public ValBondValuationInfo003Dto trans2ValBondValuationInfo003Dto(MstBondValuationInfo dto){
        if(dto == null){
            return null;
        }
        ValBondValuationInfo003Dto record = new ValBondValuationInfo003Dto();
        record.setSecId(dto.getSecId());
        record.setValueSource(dto.getValueSource());
        record.setValueDate(DateUtil.format(dto.getValueDate()));
        record.setLastPeriod(dto.getLastPeriod());
        record.setPrice(dto.getPrice());
        record.setNetprice(dto.getNetprice());
        record.setmDuration(dto.getmDuration());
        record.setmConvexity(dto.getmConvexity());
        record.setBpvalue(dto.getBpvalue());
        record.setSduration(dto.getSduration());
        record.setScnvxty(dto.getScnvxty());
        record.setInterestDuration(dto.getInterestDuration());
        record.setInterestCnvxty(dto.getInterestCnvxty());
        record.setBpyield(dto.getBpyield());
        return record;
    }
    
    /**
     * 获取资产代码list
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodIdList(Map<String, Object> map) {
        List<ValBondValuationInfo003Dto> list =  valBondValuationInfo003Mapper.selectFinprodIdList(map);
        List<SelectOptionDto> options = new ArrayList<SelectOptionDto>();
        if(list != null && list.size() > 0){
            SelectOptionDto optionDto;
            for (ValBondValuationInfo003Dto valBondValuationInfo003Dto : list) {
                optionDto = new SelectOptionDto();
                optionDto.setId(valBondValuationInfo003Dto.getSecId());
                optionDto.setText(valBondValuationInfo003Dto.getSecId() + "(" + valBondValuationInfo003Dto.getFinprodAbbr() + ")");
                options.add(optionDto);
            }
        }
        return options;
    }

    /**
     * 判断表中是否已存在相同数据
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public ValBondValuationInfo003Dto checkExistMstBondValuationInfoByKey(ValBondValuationInfo003Dto dto) {
        return valBondValuationInfo003Mapper.checkExistMstBondValuationInfoByKey(dto);
    }
}
