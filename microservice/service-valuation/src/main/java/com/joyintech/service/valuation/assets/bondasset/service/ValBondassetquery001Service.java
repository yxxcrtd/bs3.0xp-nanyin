/*
 * 文件名：ValBondassetquery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年7月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.assets.bondasset.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.assets.bondasset.dao.ValBondassetquery001Mapper;
import com.joyintech.service.valuation.assets.bondasset.dao.ValBondassetquery002Mapper;
import com.joyintech.service.valuation.assets.bondasset.dto.ValBondassetquery001Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;


/**
 * 
 * 债券类资产查询
 * @author zhangzhao
 * @version 1.0 2018年7月20日
 * @see ValBondassetquery001Service
 * @since 1.0
 */
@Service(value="serviceValBondassetquery001Service")
@Transactional(rollbackFor = Exception.class)
public class ValBondassetquery001Service extends CommonExportService<ValBondassetquery001Dto> {
    
    /**
     * ValBondassetquery001Mapper
     */
    @Autowired
    private ValBondassetquery001Mapper mapper;
    
    
    @Autowired
    private ValBondassetquery002Mapper valBondassetquery002Mapper;
    
    /**
     * 债券下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public List<SelectOptionDto> getBondId(Map<String, Object> params) {        
        List<ValBondassetquery001Dto> resultList = mapper.getBondId(params);
        List<SelectOptionDto> selectOptionDtoList = new ArrayList<>();
        for (ValBondassetquery001Dto single : resultList) { // 组装select节点对象
            SelectOptionDto selectOptionDto = new SelectOptionDto();
            selectOptionDto.setId(single.getFinprodId());
            selectOptionDto.setText(single.getFinprodAbbr());
            selectOptionDtoList.add(selectOptionDto);
        }
        return selectOptionDtoList;
    }
    
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ValBondassetquery001Dto> findPage(int page, int length, Map<String, Object> params) throws Exception {
        String bond = (String)params.get("bond");
        String couponType = (String)params.get("couponType");
        String bondType = (String)params.get("bondType");
        
        String[] bondArr = null;
        if(StringUtil.isNotEmpty(bond)){
            bondArr = bond.split(",");
        }
        String[] couponTypeArr = null;
        if(StringUtil.isNotEmpty(couponType)){
            couponTypeArr = couponType.split(",");
        }
        String[] bondTypeArr = null;
        if(StringUtil.isNotEmpty(bondType)){
            bondTypeArr = bondType.split(",");
        }
        
        params.put("bondArr",bondArr);
        params.put("couponTypeArr", couponTypeArr);
        params.put("bondTypeArr", bondTypeArr);
        params.put("finprodType", DictCodeListExt1.金融产品类型_债券.getValue());
        return super.queryForPage(page, length, () -> mapper.queryPageList(params));
    }
 
    /**
     * 导出功能
     * <功能详细描述>
     * @param paraMap
     * @return
     * @see
     * @since 1.0
     */
    public List<ValBondassetquery001Dto> findPageout(Map<String, Object> params) throws Exception {
    	String bond = (String)params.get("bond");
        String couponType = (String)params.get("couponType");
        String bondType = (String)params.get("bondType");
        
        String[] bondArr = null;
        if(StringUtil.isNotEmpty(bond)){
            bondArr = bond.split(",");
        }
        String[] couponTypeArr = null;
        if(StringUtil.isNotEmpty(couponType)){
            couponTypeArr = couponType.split(",");
        }
        String[] bondTypeArr = null;
        if(StringUtil.isNotEmpty(bondType)){
            bondTypeArr = bondType.split(",");
        }
        
        params.put("bondArr",bondArr);
        params.put("couponTypeArr", couponTypeArr);
        params.put("bondTypeArr", bondTypeArr);
        params.put("finprodType", DictCodeListExt1.金融产品类型_债券.getValue());
        return  mapper.queryPageList(params);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param result
     * @param dto
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    public int getDataInWorklow(ProcessMark processMark) throws Exception {
        return mapper.getDataInWorklow(processMark);
    }
    
    /**
     * {@inheritDoc}
     */
    public void doComplete(ValBondassetquery001Dto entity) {
        // 1.删除标的类金融产品表
        valBondassetquery002Mapper.deleteFinProductByFinProdId(entity.getBondid());
        // 2.删除标的类金融产品扩展表
        valBondassetquery002Mapper.deleteFinProductExtByFinProdId(entity.getBondid());
        // 3.删除金融产品分类表
        valBondassetquery002Mapper.deleteFinProductTypeByFinProdId(entity.getBondid());
        // 4.删除行权计划表
        valBondassetquery002Mapper.deleteFinExercisePlanByFinProdId(entity.getBondid());
        // 5.删除现金流计划表
        valBondassetquery002Mapper.deleteFinCashPlanByFinProdId(entity.getBondid());
        // 6.删除现金流信息表
        valBondassetquery002Mapper.deleteFinCashInfoByFinProdId(entity.getBondid());
        // 7.删除现金流计算规则表
        valBondassetquery002Mapper.deleteFinCashCalcRuleByFinProdId(entity.getBondid());
        // 8.删除现金流支付规则表
        valBondassetquery002Mapper.deleteFinCashPayRuleByFinProdId(entity.getBondid());
    }
}

