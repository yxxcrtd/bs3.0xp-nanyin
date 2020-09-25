/*
 * 文件名：TaxScheme007Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.taxparametermanage.taxscheme.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.dao.TaxScheme007Mapper;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.dto.TaxScheme007Dto;
import com.joyintech.tams.common.base.dao.TaxBokConfigMapper;
import com.joyintech.tams.common.base.dao.TaxSchaBokConfigMapper;
import com.joyintech.tams.common.base.entity.TaxBokConfig;
import com.joyintech.tams.common.base.entity.TaxSchaBokConfig;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 会计场景计税配置
 * @author zhangwei
 * @version 1.0 2018年10月16日
 * @see TaxScheme007Service
 * @since 1.0
 */
@Service(value="serviceTaxScheme007Service")
@Transactional(rollbackFor = Exception.class)
public class TaxScheme007Service extends CommonExportService<TaxScheme007Dto> implements BaseProcessService<TaxScheme007Dto> {

    @Autowired
    private TaxScheme007Mapper taxScheme007Mapper;

    @Autowired
    private TaxSchaBokConfigMapper taxSchaBokConfigMapper;

    /**
     * 流程审核完成时执行 {@inheritDoc}
     */
    @Override
    public void doComplete(TaxScheme007Dto entity, OperationType type) {

        if (OperationType.NEW.equals(type)) {
            this.insertSelective(entity);
        }
        else if (OperationType.MODIFY.equals(type)) {
            this.updateByPrimaryKeySelective(entity);
        }
        else if (OperationType.DELETE.equals(type)) {
            this.deleteByPrimaryKey(entity.getSeqNo());
        }
    }


    /**
     * 新建 会计场景计税配置
     * @param dto
     * @return
     */
    public void insertSelective(TaxScheme007Dto dto) {

        // 计税不启用场景配置,不启用场景挂钩税务配置
        TaxSchaBokConfig taxSchaBokConfig = new TaxSchaBokConfig();
        taxSchaBokConfig.setSeqNo(IDGenerator.generate());
        taxSchaBokConfig.setSchemeId(dto.getSchemeId());
        taxSchaBokConfig.setBokConfigId(dto.getConfigId());
        taxSchaBokConfigMapper.insertSelective(taxSchaBokConfig);
    }

    /**
     * 查询 会计场景计税配置
     * @param configId
     * @return
     */
    public TaxScheme007Dto findByPrimaryKey(String seqNo) {
        return taxScheme007Mapper.selectByPrimaryKey(seqNo);
    }

    /**
     * 查询 会计场景计税配置
     * @param configId
     * @return
     */
    public TaxScheme007Dto getConfigInfoByKey(String configId) {
        return taxScheme007Mapper.getConfigInfoByKey(configId);
    }

    /**
     * 修改 会计场景计税配置
     * @param dto
     * @return
     */
    public void updateByPrimaryKeySelective(TaxScheme007Dto dto) {

            // 计税不启用场景配置,不启用场景挂钩税务配置
            TaxSchaBokConfig taxSchaBokConfig = new TaxSchaBokConfig();
            taxSchaBokConfig.setSeqNo(dto.getSeqNo());
            taxSchaBokConfig.setSchemeId(dto.getSchemeId());
            taxSchaBokConfig.setBokConfigId(dto.getConfigId());
            taxSchaBokConfigMapper.updateByPrimaryKeySelective(taxSchaBokConfig);
    }

    /**
     * 删除 会计场景计税配置
     * @return
     */
    public void deleteByPrimaryKey(String seqNo) {
        TaxScheme007Dto entity = taxScheme007Mapper.selectByPrimaryKey(seqNo);

        // 删除 计税不启用场景配置,不启用场景挂钩税务配置
        taxSchaBokConfigMapper.deleteByPrimaryKey(entity.getSeqNo());
    }

    /**
     * 下拉框 - 基础科目性质
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getBaseSubjectNature(int page, int length, Map<String, Object> pageParam) {
        return super.queryForPageForSelectReturn(page, length, ()->taxScheme007Mapper.getBaseSubjectNature(pageParam));
    }

    /**
     * 下拉框 - 会计场景计税配置
     * @param page
     * @param length
     * @param param
     * @return
     */
    public Page<SelectOptionDto> getConfigInfo(int page, int length, Map<String, Object> param) {
        return super.queryForPageForSelectReturn(page, length, ()->taxScheme007Mapper.getConfigInfo(param));
    }

    /**
     * 下拉框 - 基础科目性质
     * @param page
     * @param length
     * @param param
     * @return
     */
    public List<SelectOptionDto> getBaseSubjectNature(Map<String, Object> param) {
        return taxScheme007Mapper.getBaseSubjectNature(param);
    }

}
