/*
 * 文件名：ProductTradeDealQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.trademanage.accountcurrentrate.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.valuation.trademanage.accountcurrentrate.dao.AccountCurrentRate001Mapper;
import com.joyintech.service.valuation.trademanage.accountcurrentrate.dto.AccountCurrentRate001Dto;
import com.joyintech.service.valuation.trademanage.accountcurrentrate.dto.AccountCurrentRateDto;
import com.joyintech.tams.common.base.dao.FinCashCalcRuleMapper;
import com.joyintech.tams.common.base.dao.FinRateInfoMapper;
//import com.joyintech.tams.common.base.dao.FinCashIntInfoMapper;
import com.joyintech.tams.common.base.entity.FinCashCalcRuleKey;
import com.joyintech.tams.common.base.entity.FinRateInfoKey;
//import com.joyintech.tams.common.base.entity.FinCashIntInfoKey;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 账户活期利率查询查询
 * 
 * @author huwei
 * @version 1.0 2018年10月11日
 * @see AccountCurrentRate001Service
 * @since 1.0
 */
@Service(value="serviceAccountCurrentRate001Service")
@Transactional(rollbackFor = Exception.class)
public class AccountCurrentRate001Service extends CommonExportService<AccountCurrentRate001Dto> implements BaseProcessService<AccountCurrentRate001Dto> {

    /**
     * accountCurrentRate001Mapper
     */
    @Autowired
    private AccountCurrentRate001Mapper accountCurrentRate001Mapper;

    /**
     * finCashIntInfoMapper
     */
    // @Autowired
    // private FinCashIntInfoMapper finCashIntInfoMapper;

    @Autowired
    private FinRateInfoMapper finRateInfoMapper;

    /**
     * finCashCalcRuleMapper
     */
    @Autowired
    private FinCashCalcRuleMapper finCashCalcRuleMapper;

    /**
     * 根据条件分页查询
     * 
     * @param params Map
     * @return Page<AccountCurrentRate001Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<AccountCurrentRate001Dto> queryAccountCurrentRateList(int page, int length, Map<String, Object> params)
        throws Exception {
        return super.queryForPage(page, length, () -> accountCurrentRate001Mapper.queryAccountCurrentRateList(params));
    }

    /**
     * 根据条件查询所有
     * 
     * @param params Map
     * @return List<AccountCurrentRate001Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public List<AccountCurrentRate001Dto> findAll(Map<String, Object> params)
        throws Exception {
        List<AccountCurrentRate001Dto> list = accountCurrentRate001Mapper.queryAccountCurrentRateList(params);
        return list;
    }

    /**
     * 下拉框获取：账户
     * 
     * @param params Map
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getAccount(Map<String, Object> params) {
        return queryForSuggest(() -> {
            return accountCurrentRate001Mapper.getAccount(params);
        }, null, null);

    }

    /**
     * 删除
     * 
     * @param dto AccountCurrentRate001Dto
     * @return int
     * @see
     * @since 1.0
     */
    public int delete(AccountCurrentRateDto dto) {
        int count = 0;
        // FinCashIntInfoKey key1 = new FinCashIntInfoKey();
        FinRateInfoKey key1 = new FinRateInfoKey();
        key1.setCashId(dto.getCashId());
        key1.setEffDate(DateUtil.parse(dto.getEffDate()));
        FinCashCalcRuleKey key2 = new FinCashCalcRuleKey();
        key2.setCashId(dto.getCashId());
        key2.setEffDate(DateUtil.parse(dto.getEffDate()));
        // count += finCashIntInfoMapper.deleteByPrimaryKey(key1);
        count += finRateInfoMapper.deleteByPrimaryKey(key1);
        count += finCashCalcRuleMapper.deleteByPrimaryKey(key2);

        return count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(AccountCurrentRate001Dto entity, OperationType type) {
        // TODO Auto-generated method stub
        // // 删除操作
        // if(OperationType.DELETE.equals(type)) {
        // this.delete(entity);
        // }
    }

    /**
     * 删除数据整合DTO
     * 
     * @param dto AccountCurrentRate001Dto
     * @return AccountCurrentRateDto
     * @see
     * @since 1.0
     */
    public AccountCurrentRateDto getAccountCurrentRateDto(AccountCurrentRate001Dto dto) {
        AccountCurrentRateDto result = new AccountCurrentRateDto();
        result.setAcctNo(dto.getAcctNo());
        result.setAcctType(dto.getAcctType());
        result.setCashId(dto.getCashId());
        result.setEffDate(dto.getEffDate());
        result.setOperateType(dto.getOperateType());
        return result;

    }

}
