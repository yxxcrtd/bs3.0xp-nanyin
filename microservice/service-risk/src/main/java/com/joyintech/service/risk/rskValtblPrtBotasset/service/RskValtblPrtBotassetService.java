/*
 * 文件名：RskValtblPrtBotassetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.rskValtblPrtBotasset.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.RskValtblPrtBotassetMapMapper;
import com.joyintech.tams.common.base.entity.RskValtblPrtBotassetMap;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.rskValtblPrtBotasset.dao.RskValtblPrtBotassetMapper;
import com.joyintech.service.risk.rskValtblPrtBotasset.dto.RskValtblPrtBotassetMapDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 估值表底层资产映射
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月3日
 * @see RskValtblPrtBotassetService
 * @since 1.0
 */
@Service
public class RskValtblPrtBotassetService extends BaseService<RskValtblPrtBotassetMapDto>{

    @Autowired
    private RskValtblPrtBotassetMapper rskValtblPrtBotassetMapper;
    
    /**
     * 自动生成
     */
    @Autowired
    private RskValtblPrtBotassetMapMapper rskValtblPrtBotassetMapMapper;
    
    public Page<RskValtblPrtBotassetMapDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page, length, () -> rskValtblPrtBotassetMapper.queryForPage(pageParam));
    }
    
    /**
     * by组合代码查找底层资产映射
     * <一句话功能简述>
     * <功能详细描述>
     * @param ptlId
     * @return
     * @see
     * @since 1.0
     */
    public List<RskValtblPrtBotassetMapDto> selectBotAssetMap(String ptlId){
        return rskValtblPrtBotassetMapper.selectBotAssetMap(ptlId);
    }
    
    /**
     * 更新(取消)映射关系
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void update(RskValtblPrtBotassetMapDto dto){
        RskValtblPrtBotassetMap record = new RskValtblPrtBotassetMap();
        record.setPtlId(dto.getPtlId());
        record.setSubCode(dto.getSubCode());
        record.setFinprodId(dto.getFinprodId());
        record.setAssetSource(dto.getAssetSource());
        record.setSubClass(dto.getSubClass());
        record.setSubName(dto.getSubName());
        record.setTblMarketId(dto.getTblMarketId());
        rskValtblPrtBotassetMapMapper.updateByPrimaryKey(record);
        //取消映射  置空估值表FINPROD_ID
        //add by zhangnian 20191012  start
        if(StringUtil.equals(dto.getOperateType(),"cancle")){
            rskValtblPrtBotassetMapper.cancleValmapped(dto.getPtlId(),dto.getSubCode());
        }
        //add by zhangnian 20191012  end
    }
    /**
     * 保存
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void writebotAssetMap(RskValtblPrtBotassetMapDto dto){
        if(null!=dto.getSelectedItem()&&!dto.getSelectedItem().isEmpty()){
            Map<String, Object> map = new HashMap<String, Object>();
            /*for(RskValtblPrtBotassetMapDto entity:dto.getSelectedItem()){
                map.put("ptlId", entity.getPtlId());
                rskValtblPrtBotassetMapper.deletePrtBotassetMap(map);
            }*/
            for(RskValtblPrtBotassetMapDto entity:dto.getSelectedItem()){
                RskValtblPrtBotassetMap record = new RskValtblPrtBotassetMap();
                record.setPtlId(entity.getPtlId());
                record.setSubCode(entity.getSubCode());
                record.setSubName(entity.getSubName());
                record.setAssetSource(entity.getAssetSource());
                record.setSubClass(entity.getSubClass());
                record.setFinprodId(entity.getFinprodId());
                record.setTblMarketId(entity.getTblMarketId());
                if(StringUtil.equals(entity.getIsAdd(), DictCodeList.是否_是.getValue())){
                    rskValtblPrtBotassetMapMapper.insert(record);
                }else{
                    if(StringUtil.isNotEmpty(entity.getFinprodId())){
                        rskValtblPrtBotassetMapMapper.updateByPrimaryKey(record);
                    }
                }
            }
        }
    }
    
    public RskValtblPrtBotassetMapDto selectAssetById(String finprodId){
        return rskValtblPrtBotassetMapper.selectAssetById(finprodId);
    }
    
    public RskValtblPrtBotassetMapDto selectWindBondAssetById(String finprodId){
        return rskValtblPrtBotassetMapper.selectWindBondAssetById(finprodId);
    }
    
    /**
     * 获取未映射个数
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    public Integer getCountForValUnmapped(Map<String,Object> paramMap){
        return rskValtblPrtBotassetMapper.getCountForValUnmapped(paramMap);
    }
    
    /**
     * 获取不同映射个数
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    public Integer getCountForValDiffmapped(Map<String,Object> paramMap){
        return rskValtblPrtBotassetMapper.getCountForValDiffmapped(paramMap);
    }
    
    /**
     * 应用未映射
     * <功能详细描述>
     * @param paramMap
     * @see
     * @since 1.0
     */
    public void applyMapForValUnmapped(Map<String,Object> paramMap){
         rskValtblPrtBotassetMapper.applyMapForValUnmapped(paramMap);
    }
    
    /**
     * 应用不同映射
     * <功能详细描述>
     * @param paramMap
     * @see
     * @since 1.0
     */
    public void applyMapForValDiffmapped(Map<String,Object> paramMap){
        rskValtblPrtBotassetMapper.applyMapForValDiffmapped(paramMap);
   }
}
