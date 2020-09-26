/*
 * 文件名：ValFinExercisePara002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年3月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.finexercisepara.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.dao.BokAmoConfigMapper;
import com.joyintech.tams.common.base.dao.BokBooksetDateMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.BokAmoConfig;
import com.joyintech.tams.common.base.entity.BokAmoConfigKey;
import com.joyintech.tams.common.base.entity.BokBooksetDate;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.finexercisepara.dao.ValFinExercisePara002Mapper;
import com.joyintech.tams.valuation.finexercisepara.dto.ValFinExercisePara002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * @author zhangwei
 * @version 1.0 2019年3月2日
 * @see ValFinExercisePara002Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValFinExercisePara002Service extends BaseService<ValFinExercisePara002Dto> implements BaseProcessService<ValFinExercisePara002Dto> {

    @Autowired
    private ValFinExercisePara002Mapper valFinExercisePara002Mapper;

    @Autowired
    private BokAmoConfigMapper bokAmoConfigMapper;

    @Autowired
    private FinProductMapper finProductMapper;

    @Autowired
    private ValFinExercisePara001Service valFinExercisePara001Service;

    @Autowired
    private BokBooksetDateMapper bokBooksetDateMapper;

    @Override
    public void doComplete(ValFinExercisePara002Dto entity, OperationType type) {
        if (OperationType.NEW.equals(type)) {
            entity.setBranch(0L);
            this.insertSelective(entity, type);
        }
        else if (OperationType.MODIFY.equals(type)) {
            this.updateByPrimaryKeySelective(entity, type);
        }
        else if (OperationType.DELETE.equals(type)) {
            valFinExercisePara001Service.deleteByPrimaryKey(entity, type);
        }

    }

    /**
     * 检索账套代码列表(分页下拉框)
     * 
     * @param param
     * @return
     */
    public Page<SelectOptionDto> findBooksetIdByPage(Map<String, Object> param) {
        param.put("booksetType", DictCodeList.账套类别_理财账套.getValue());
        Page<SelectOptionDto> page = super.queryForPageForSelectReturn(
            () -> valFinExercisePara002Mapper.findBooksetId(param));
        List<SelectOptionDto> root = page.getRoot().stream().map(item -> {
            item.setText(item.getId() + "(" + item.getText() + ")");
            return item;
        }).collect(Collectors.toList());
        page.setRoot(root);
        return page;
    }

    /**
     * 检索资产（债券）
     * 
     * @param param
     * @return
     */
    public Page<SelectOptionDto> findFinprod(Map<String, Object> param) {
        Page<SelectOptionDto> page = super.queryForPageForSelectReturn(
            () -> valFinExercisePara002Mapper.findFinprod(param));
        List<SelectOptionDto> root = page.getRoot().stream().map(item -> {
            item.setText(item.getId() + "(" + item.getText() + ")");
            return item;
        }).collect(Collectors.toList());
        page.setRoot(root);
        return page;
    }

    public ValFinExercisePara002Dto findByPrimaryKey(ValFinExercisePara002Dto dto) {
        return valFinExercisePara002Mapper.selectByPrimaryKey(dto);
    }

    public void insertSelective(ValFinExercisePara002Dto dto, OperationType type) {
        valFinExercisePara002Mapper.insertSelective(dto);

        // 调用共通插入【摊销重算配置】表
        this.insertBokAmoConfig(dto, type);
    }

    public void updateByPrimaryKeySelective(ValFinExercisePara002Dto dto, OperationType type) {
        valFinExercisePara002Mapper.updateByPrimaryKeySelective(dto);

        // 调用共通插入【摊销重算配置】表
        this.insertBokAmoConfig(dto, type);
    }

    /**
     * 共通插入【摊销重算配置】表
     * 
     * @param dto
     * @return
     */
    public void insertBokAmoConfig(ValFinExercisePara002Dto dto, OperationType type) {
        BokAmoConfigKey bokAmoConfigKey = new BokAmoConfigKey();
        bokAmoConfigKey.setBooksetId(dto.getBooksetId());
        bokAmoConfigKey.setBusiId(dto.getFinprodId());
        BokBooksetDate bokBooksetDate = bokBooksetDateMapper.selectByPrimaryKey(dto.getBooksetId());

        if (bokBooksetDate != null) {
            bokAmoConfigKey.setCdate(bokBooksetDate.getBooksetDate());
            if (OperationType.DELETE.equals(type)) {
                bokAmoConfigMapper.deleteByPrimaryKey(bokAmoConfigKey);
            } else {
                BokAmoConfig bokAmoConfig = null;

                bokAmoConfig = bokAmoConfigMapper.selectByPrimaryKey(bokAmoConfigKey);

                if (bokAmoConfig == null) {
                    bokAmoConfig = new BokAmoConfig();
                    bokAmoConfig.setBooksetId(dto.getBooksetId());
                    bokAmoConfig.setBusiId(dto.getFinprodId());
                    bokAmoConfig.setCdate(bokBooksetDate.getBooksetDate());
                    bokAmoConfig.setRecalType(DictCodeList.重算方式_重算.getValue());

                    bokAmoConfig.setCreateUser(dto.getCreateUser());
                    bokAmoConfig.setCreateTime(dto.getCreateTime());
                    bokAmoConfig.setCreateDept(dto.getCreateDept());
                    bokAmoConfig.setUpdateUser(dto.getUpdateUser());
                    bokAmoConfig.setUpdateTime(dto.getUpdateTime());

                    bokAmoConfigMapper.insertSelective(bokAmoConfig);
                }
            }
        }
    }

    public FinProduct getFinprodInfo(Map<String, Object> map) {
//        FinProductKey key = new FinProductKey();
//        key.setFinprodId((String)map.get("finprodId"));
//        key.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
        return finProductMapper.selectByPrimaryKey((String)map.get("finprodId"));
    }

}
