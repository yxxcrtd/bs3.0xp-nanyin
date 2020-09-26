/*
 * 文件名：ValFinExercisePara001Service.java
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.finexercisepara.dao.ValFinExercisePara001Mapper;
import com.joyintech.tams.valuation.finexercisepara.dto.ValFinExercisePara001Dto;
import com.joyintech.tams.valuation.finexercisepara.dto.ValFinExercisePara002Dto;
import com.joyintech.tams.common.base.dao.BokAmoConfigMapper;
import com.joyintech.tams.common.base.dao.BokBooksetDateMapper;
import com.joyintech.tams.common.base.entity.BokAmoConfig;
import com.joyintech.tams.common.base.entity.BokAmoConfigKey;
import com.joyintech.tams.common.base.entity.BokBooksetDate;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * @author zhangwei
 * @version 1.0 2019年3月2日
 * @see ValFinExercisePara001Service
 * @since 1.0
 */
@Service
public class ValFinExercisePara001Service extends CommonExportService<ValFinExercisePara001Dto> {

    @Autowired
    private ValFinExercisePara001Mapper valFinExercisePara001Mapper;
    
    @Autowired
    private ValFinExercisePara002Service valFinExercisePara002Service;

    public Page<ValFinExercisePara001Dto> findPage(Map<String, Object> map)
        throws Exception {
        return super.queryForPage(() -> valFinExercisePara001Mapper.selectAll(map));
    }

    public List<ValFinExercisePara001Dto> findAll(Map<String, Object> map)
        throws Exception {
        return valFinExercisePara001Mapper.selectAll(map);
    }

    public void deleteByPrimaryKey(ValFinExercisePara002Dto dto, OperationType type) {
        valFinExercisePara001Mapper.deleteByPrimaryKey(dto);
        
        // 调用共通插入【摊销重算配置】表
        valFinExercisePara002Service.insertBokAmoConfig(dto, type);
    }

}
