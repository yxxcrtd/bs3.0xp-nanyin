/*
 * 文件名：RskValTableImportService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.valtblimport.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.valtblimport.dao.RskValTableImportMapper;
import com.joyintech.service.risk.valtblimport.dto.RskValtblAssetSubDto;
import com.joyintech.service.risk.valtblimport.dto.ValType;
import com.joyintech.tams.common.base.dao.RskValtblAssetSubMapper;
import com.joyintech.tams.common.base.dao.RskValtblSumAssetSubMapper;
import com.joyintech.tams.common.base.entity.RskValtblAssetSub;
import com.joyintech.tams.common.base.entity.RskValtblSumAssetSub;


@Service("backRskValTableImportService")
public class RskValTableImportService {
	
    @Autowired
    private RskValtblAssetSubMapper rskValtblAssetSubMapper;
    @Autowired
    private RskValtblSumAssetSubMapper rskValtblSumAssetSubMapper;
    @Autowired
    private RskValTableImportMapper rskValTableImportMapper;
    /**
     * 估值表保存
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void saveValTbl(RskValtblAssetSubDto dto){ 
        rskValTableImportMapper.deleteValTable(dto);
        rskValTableImportMapper.deleteSumValTable(dto);
        if(null!=dto.getSelectedItem()&&!dto.getSelectedItem().isEmpty()){
            for(RskValtblAssetSubDto entity:dto.getSelectedItem()){
                if(StringUtil.equals(entity.getValType().getDesc(),ValType.ASSET.getDesc())){
                    RskValtblAssetSub record = new RskValtblAssetSub();
                    BeanUtil.copyPropertiesIgnoreNull(record, entity);
                    record.setPtlId(dto.getPtlId());
                    record.setValDate(dto.getValDate());
                    rskValtblAssetSubMapper.insert(record);
                }else{
                    RskValtblSumAssetSub record = new RskValtblSumAssetSub();
                    BeanUtil.copyPropertiesIgnoreNull(record, entity);
                    record.setPtlId(dto.getPtlId());
                    record.setValDate(dto.getValDate());
                    rskValtblSumAssetSubMapper.insert(record);
                }
            }
        }
    }
    
    /**
     *  估值表科目数量
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    public int selectCount(Map<String,Object> paramMap){
        return rskValTableImportMapper.selectCount(paramMap);
    }
}
