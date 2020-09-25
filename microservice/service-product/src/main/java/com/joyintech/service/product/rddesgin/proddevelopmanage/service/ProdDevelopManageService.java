/*
 * 文件名：ProdDevelopManageService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.rddesgin.proddevelopmanage.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.rddesgin.proddevelopmanage.dao.ProdDevelopManageMapper;
import com.joyintech.service.product.rddesgin.proddevelopmanage.dto.ProdDevelopManageDto;
import com.joyintech.tams.common.base.dao.PrdResearchSchemeMapper;
import com.joyintech.tams.common.base.dao.ProductBaseInfoMapper;
import com.joyintech.tams.common.base.entity.PrdResearchSchemeWithBLOBs;
import com.joyintech.tams.common.base.entity.ProductBaseInfo;

/**
 * 微服务：产品研发方案管理Service
 * 
 * @author zhangJin
 * @version 1.0 2019年10月23日
 * @see ProdDevelopManageService
 * @since 1.0
 */
@Service
public class ProdDevelopManageService extends BaseService<ProdDevelopManageDto> {

    /**
     * ProdDevelopManageMapper
     */
    @Autowired
    private ProdDevelopManageMapper prodDevelopManageMapper;

    /**
     * 产品研发方案管理基础Mapper
     */
    @Autowired
    private PrdResearchSchemeMapper prdResearchSchemeMapper;

    /**
     * 产品要素设计信息基础Mapper
     */
    @Autowired
    private ProductBaseInfoMapper productBaseInfoMapper;

    /**
     * 画面查询方法
     * 
     * @param param 请求参数
     * @param page 页面
     * @param length 长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    public Page<ProdDevelopManageDto> selectForPage(int page, int length,
                                                    Map<String, Object> pageParam) {

        return super.queryForPage(page, length,
            () -> prodDevelopManageMapper.selectForPage(pageParam));
    }

    /**
     * 获取系列编号下拉框
     * 
     * @param param 模糊查询
     * @param page 页数
     * @param length 条数
     * @return 下拉框
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getProdDesignSeries(int page, int length,
                                                     Map<String, Object> pageParam) {
        return super.queryForPageForSelectReturn(page, length,
            () -> prodDevelopManageMapper.getProdDesignSeries(pageParam));

    }

    /**
     * 根据系列编号获取结果信息
     * 
     * @param pageParam 系列编号
     * @return 结果信息
     * @see
     * @since 1.0
     */
    public ProdDevelopManageDto getProdResultInfos(Map<String, Object> pageParam) {
        ProdDevelopManageDto dto = prodDevelopManageMapper.getProdResultInfos(pageParam);
        if (StringUtil.isNotEmpty(dto)) {
            // 产品要素设计信息
            dto.setPrdDesignResult(dto.getPrdDesignResultStr());
            // 模拟试算结果
            dto.setPrdMockResult(dto.getPrdMockResultStr());
        }
        return dto;
    }

    // /**
    // * <一句话功能简述> <功能详细描述>
    // *
    // * @param dto
    // * @return
    // * @see
    // * @since 1.0
    // */
    // public int delete(ProdDevelopManageDto dto)
    // throws Exception {
    // // TODO Auto-generated method stub
    // return 0;
    // }
    //
    // /**
    // * 插入
    // *
    // * @param dto
    // * @return
    // * @see
    // * @since 1.0
    // */
    // public int insert(ProdDevelopManageDto dto)
    // throws Exception {
    // PrdResearchSchemeWithBLOBs record = new PrdResearchSchemeWithBLOBs();
    // BeanUtil.copyPropertiesIgnoreNull(record, dto);
    // return prdResearchSchemeMapper.insertSelective(record);
    // }

    /**
     * 工作流完成操作
     * 
     * @param entity ProdDevelopManageDto
     * @param type 类型
     * @see
     * @since 1.0
     */
    public void doComplete(ProdDevelopManageDto entity, String type) {
        if (StringUtil.equals(type, "MODIFY")) {
            PrdResearchSchemeWithBLOBs record = new PrdResearchSchemeWithBLOBs();
            BeanUtil.copyPropertiesIgnoreNull(record, entity);
            prdResearchSchemeMapper.updateByPrimaryKeySelective(record);
        }
        else if (StringUtil.equals(type, "NEW")) {
            PrdResearchSchemeWithBLOBs record = new PrdResearchSchemeWithBLOBs();
            BeanUtil.copyPropertiesIgnoreNull(record, entity);
            prdResearchSchemeMapper.insertSelective(record);
        }
        else if (StringUtil.equals(type, "DELETE")) {
            prdResearchSchemeMapper.deleteByPrimaryKey(entity.getSeqNo());
        }

    }

    /**
     * 根据流水号查询研发方案详情
     * 
     * @param seqNo 流水号
     * @return 研发方案详情
     * @see
     * @since 1.0
     */
    public ProdDevelopManageDto getEntityBySeqNo(String seqNo) {
        ProdDevelopManageDto entity = new ProdDevelopManageDto();
        PrdResearchSchemeWithBLOBs orig = prdResearchSchemeMapper.selectByPrimaryKey(seqNo);
        BeanUtil.copyPropertiesIgnoreNull(entity, orig);
        ProductBaseInfo productBaseInfo = productBaseInfoMapper.selectByPrimaryKey(
            entity.getPrdDesignId());
        if (productBaseInfo!=null){
        entity.setPrdDesignName(productBaseInfo.getPrdDesignSerialName());}
        return entity;
    }

}
