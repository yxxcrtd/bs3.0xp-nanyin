/*
 * 文件名：RskValRelateImportService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2018年12月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.penetrationmanagement.rskvalrelateimport.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.RskValtblPrtImpMapMapper;
import com.joyintech.tams.common.base.dao.RskValtblSumsubMapMapper;
import com.joyintech.tams.common.base.entity.RskValtblPrtImpMap;
import com.joyintech.tams.common.base.entity.RskValtblSumsubMap;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.penetrationmanagement.rskvalrelateimport.dao.RskValRelateImportMapper;
import com.joyintech.service.risk.penetrationmanagement.rskvalrelateimport.dto.RskValRelateImportDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongzhiling
 * @version 1.0 2018年12月20日
 * @see RskValRelateImportService
 * @since 1.0
 */
@Service
public class RskValRelateImportService extends BaseService<RskValRelateImportDto> {
    
    /**
     * RskValRelateImportMapper
     */
    @Autowired
    private RskValRelateImportMapper rskValRelateImportMapper;
    /**
     * 
     */
    @Autowired
    private RskValtblPrtImpMapMapper rskValtblPrtImpMapMapper;
    
    @Autowired
    private RskValtblSumsubMapMapper rskValtblSumsubMapMapper;
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param page
     * @param length
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<RskValRelateImportDto> selectForPage(int page, int length,
                                                     Map<String, Object> param) throws Exception{
        return super.queryForPage(page, length, () ->rskValRelateImportMapper.selectForPage(param));
    }
    
    /**
     * <一句话功能简述> 指标结构映射联想下拉框
     * 
     * @return 指标结构映射信息
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryStructMapId() {
        return rskValRelateImportMapper.queryStructMapId();
    }

    /**
     * <一句话功能简述> 汇总科目映射联想下拉框
     * 
     * @return 汇总科目映射信息
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> querySumsubMapId() {
        return rskValRelateImportMapper.querySumsubMapId();
    }

    /**
     * <一句话功能简述> 资产科目映射联想下拉框
     * 
     * @return 资产科目映射信息
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryAssetSubMapId() {
        return rskValRelateImportMapper.queryAssetSubMapId();
    }

    /**
     * <一句话功能简述> 新建插入操作
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int insert(RskValRelateImportDto dto) {
        RskValtblPrtImpMap record = new RskValtblPrtImpMap();
        record.setPtlId(dto.getPtlId());
        record.setStartRow(dto.getStartRow());
        record.setSubjectCodeCol(dto.getSubjectCodeCol());
        record.setStructMapId(dto.getStructMapId());
        record.setSumsubMapId(dto.getSumsubMapId());
        record.setAssetSubMapId(dto.getAssetSubMapId());
        //---add by 裴家俊 20190514---添加过滤器---start------
        record.setFilterId(dto.getFilterId());
        //---add by 裴家俊 20190514------end------
        return rskValtblPrtImpMapMapper.insert(record);
    }

    /**
     * <一句话功能简述> 更新操作
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int update(RskValRelateImportDto dto) {
        RskValtblPrtImpMap record = new RskValtblPrtImpMap();
        record.setPtlId(dto.getPtlId());
        record.setStartRow(dto.getStartRow());
        record.setSubjectCodeCol(dto.getSubjectCodeCol());
        record.setStructMapId(dto.getStructMapId());
        record.setSumsubMapId(dto.getSumsubMapId());
        record.setAssetSubMapId(dto.getAssetSubMapId());
      //---add by 裴家俊 20190514---添加过滤器---start------
        record.setFilterId(dto.getFilterId());
      //---add by 裴家俊 20190514------end------
        return rskValtblPrtImpMapMapper.updateByPrimaryKey(record);
    }

    /**
     * <一句话功能简述> 删除操作
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int delete(RskValRelateImportDto dto) {
        return rskValtblPrtImpMapMapper.deleteByPrimaryKey(dto.getPtlId());
    }

    /**
     * <一句话功能简述> 编辑按钮按下，查询相关信息
     * 
     * @param ptlId
     * @return
     * @see
     * @since 1.0
     */
    public RskValRelateImportDto selectInfoById(String ptlId) {
        RskValRelateImportDto dto = new RskValRelateImportDto();
        try {
            RskValtblPrtImpMap rskValtblPrtImpMap =  rskValtblPrtImpMapMapper.selectByPrimaryKey(ptlId);
            if(null!=rskValtblPrtImpMap){
                BeanUtils.copyProperties(dto, rskValtblPrtImpMap);
                RskValtblSumsubMap rskValtblSumsubMap= rskValtblSumsubMapMapper.selectByPrimaryKey(rskValtblPrtImpMap.getSumsubMapId());
                if(null!=rskValtblSumsubMap){
                    dto.setTrimChars(rskValtblSumsubMap.getTrimChars());
                }
            }
        }
        catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //查询组合名称
        dto.setName(rskValRelateImportMapper.selectGroupName(ptlId));
        return dto;
    }

    /**
     * <一句话功能简述>
     * 批量删除
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int deleteList(RskValRelateImportDto dto) {
        List<RskValRelateImportDto> selectedItem = dto.getSelectedItem();
        List<String> deleteList = new ArrayList<>();
        for (RskValRelateImportDto selected : selectedItem) {
            deleteList.add(selected.getPtlId());
        }
        return rskValRelateImportMapper.deleteList(deleteList);
    }

    /**
     * <一句话功能简述>
     * 组合名称重复校验
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int checkGroupName(RskValRelateImportDto dto) {
        return rskValRelateImportMapper.checkGroupName(dto);
    }

}
