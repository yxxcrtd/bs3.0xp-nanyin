/*
 * 文件名：ProdBonusplanManager002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.RepProdInfoMapper;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.dao.ProdBonusplanMapper;
import com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.dto.ProdBonusplanDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 报表模板管理新建service
 *
 * @author longhao
 * @version 2.0 2018年5月3日
 * @see ProdBonusplanManager002Service
 * @since 2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProdBonusplanManager002Service extends CommonExportService<ProdBonusplanDto> implements BaseProcessService<ProdBonusplanDto> {


    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ProdBonusplanManager002Service.class);

    @Autowired
    private RepProdInfoMapper repProdInfoMapper;

    @Autowired
    private ProdBonusplanMapper prodBonusplanMapper;

    /**
     * 保存check
     *
     * @param dto
     * @return
     */
    public AjaxResult check(ProdBonusplanDto dto) {
        Set<String> message = new HashSet<String>();

        AjaxResult result = new AjaxResult();
        if (CollectionUtils.isNotEmpty(message)) {
            List<ValidMessage> errorList = new ArrayList<>();
            message.forEach(action -> {
                errorList.add(new ValidMessage(action));
            });
            result.setErrorList(errorList);
        }
        return result;
    }

    //初始化编辑页面数据
    public ProdBonusplanDto getProdBonusplanDto(String keyId) {
        ProdBonusplanDto dto = prodBonusplanMapper.selectByBonusPlanId(keyId);
        return dto;
    }


    /**
     * 插入数据
     *
     * @param dto
     */
    public void insert(ProdBonusplanDto dto) {
        dto.setBonusPlanId(IDGenerator.generate(36));
        prodBonusplanMapper.insert(dto);

    }

    /**
     * 更新数据
     *
     * @param dto
     * @return
     */
    public void update(ProdBonusplanDto dto) {
        prodBonusplanMapper.updateByPrimaryKeySelective(dto);
    }

    /**
     * 删除
     *
     * @param entity
     */
    public void delete(ProdBonusplanDto entity) {
        String bonusPlanId = entity.getBonusPlanId();
        if (StringUtils.isNotEmpty(bonusPlanId)) {
            ProdBonusplanDto dto = new ProdBonusplanDto();
            dto.setBonusPlanId(bonusPlanId);
            dto.setDataStatus(DictCodeListExt3.信披_数据状态_删除D.getValue());
            this.update(dto);
        }

    }

    public RepProdInfo getPrdInfo(String prodId) {
        RepProdInfo prodInfo = repProdInfoMapper.selectByPrimaryKey(prodId);
        return prodInfo;
    }


    @Override
    public void doComplete(ProdBonusplanDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        entity.setUpdateTime(DateUtil.now());
        if (StringUtil.equals(entity.getOperateType(), "NEW")) {
            entity.setDataStatus(DictCodeListExt3.信披_数据状态_有效E.getValue());
            entity.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());
            this.insert(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "MODIFY")) {
            this.update(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "DELETE")) {
            this.delete(entity);

        }

    }
}
