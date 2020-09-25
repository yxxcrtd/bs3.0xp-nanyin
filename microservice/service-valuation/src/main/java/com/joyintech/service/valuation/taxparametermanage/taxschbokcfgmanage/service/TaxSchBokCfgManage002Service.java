/*
 * 文件名：TaxScheme007Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.taxparametermanage.taxschbokcfgmanage.service;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.taxparametermanage.taxschbokcfgmanage.dao.TaxSchBokCfgManage002Mapper;
import com.joyintech.service.valuation.taxparametermanage.taxschbokcfgmanage.dto.TaxSchBokCfgManage002Dto;
import com.joyintech.tams.common.base.dao.TaxBokConfigMapper;
import com.joyintech.tams.common.base.entity.TaxBokConfig;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 会计场景计税配置
 * 
 * @author zhangwei
 * @version 1.0 2018年10月16日
 * @see TaxSchBokCfgManage002Service
 * @since 1.0
 */
@Service(value = "serviceTaxSchBokCfgManage002Service")
@Transactional(rollbackFor = Exception.class)
public class TaxSchBokCfgManage002Service extends CommonExportService<TaxSchBokCfgManage002Dto> implements BaseProcessService<TaxSchBokCfgManage002Dto> {

    @Autowired
    private TaxSchBokCfgManage002Mapper taxSchBokCfgManage002Mapper;

    @Autowired
    private TaxBokConfigMapper taxBokConfigMapper;
    

    /**
     * 流程审核完成时执行 {@inheritDoc}
     */
    @Override
    public void doComplete(TaxSchBokCfgManage002Dto entity, OperationType type) {

        if (OperationType.NEW.equals(type)) {
            this.insertSelective(entity);
        }
        else if (OperationType.MODIFY.equals(type)) {
            this.updateByPrimaryKeySelective(entity);
        }
        else if (OperationType.DELETE.equals(type)) {
            this.deleteByPrimaryKey(entity.getConfigId());
        }
    }

    /**
     * 新建 会计场景计税配置
     * 
     * @param dto
     * @return
     */
    public void insertSelective(TaxSchBokCfgManage002Dto dto) {
        // 税额记账分类配置
        TaxBokConfig taxBokConfig = new TaxBokConfig();
        BeanUtils.copyProperties(dto, taxBokConfig);
        // 重新生成符合规则的编号
        taxBokConfig.setConfigId(configIdGenerator(dto.getTableId(), dto.getSubjectNature()));
        taxBokConfigMapper.insertSelective(taxBokConfig);
    }

    /**
     * 查询 会计场景计税配置
     * 
     * @param configId
     * @return
     */
    public TaxSchBokCfgManage002Dto findByPrimaryKey(String configId) {
        return taxSchBokCfgManage002Mapper.selectByPrimaryKey(configId);
    }

    /**
     * 修改 会计场景计税配置
     * 
     * @param dto
     * @return
     */
    public void updateByPrimaryKeySelective(TaxSchBokCfgManage002Dto dto) {
        // 税额记账分类配置
        TaxBokConfig taxBokConfig = new TaxBokConfig();
        BeanUtils.copyProperties(dto, taxBokConfig);
        taxBokConfigMapper.updateByPrimaryKeySelective(taxBokConfig);
    }

    /**
     * 删除 会计场景计税配置
     * 
     * @return
     */
    public void deleteByPrimaryKey(String configId) {
        TaxSchBokCfgManage002Dto entity = taxSchBokCfgManage002Mapper.selectByPrimaryKey(configId);
        // 删除 税额记账分类配置
        taxBokConfigMapper.deleteByPrimaryKey(entity.getConfigId());
    }

    /**
     * 下拉框 - 基础科目性质
     * 
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getBaseSubjectNature(int page, int length,
                                                      Map<String, Object> pageParam) {
        return super.queryForPageForSelectReturn(page, length,
            () -> taxSchBokCfgManage002Mapper.getBaseSubjectNature(pageParam));
    }

    /**
     * 配置代码生成规则：记账场景代码+发生额科目性质 + 3位数字（001 开始，递增， 补0）
     * 
     * @param tableId
     * @param subjectNature
     * @return
     */
    public String configIdGenerator(String tableId, String subjectNature) {
        StringBuilder sb = new StringBuilder();
        sb.append(tableId).append(subjectNature);
        int dbCount = taxSchBokCfgManage002Mapper.countConfigId(sb.toString());
        sb.append(String.format("%03d", dbCount + 1));
        return sb.toString();
    }

}
