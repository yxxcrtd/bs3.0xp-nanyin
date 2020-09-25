/*
 * 文件名：MstSecuritiesAccountService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：lizhi
 * 修改时间：2020年1月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.fundsecuritiesaccount.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.fundsecuritiesaccount.dao.FundSecuritiesAccountMapper;
import com.joyintech.service.basicdata.fundsecuritiesaccount.dto.FundSecuritiesAccountDto;
import com.joyintech.tams.common.base.dao.StlSecAcctInfoMapper;
import com.joyintech.tams.common.base.entity.StlSecAcctInfo;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lizhi
 * @version 1.0 2020年1月14日
 * @see FundSecuritiesAccountService
 * @since 1.0
 */
@Service
public class FundSecuritiesAccountService extends BaseService<FundSecuritiesAccountDto>{

    /**
     * 手写证券账号管理Mapper
     */
    @Autowired
    private FundSecuritiesAccountMapper fundSecuritiesAccountMapper;
    /**
     * 自动生成Mapper
     */
    @Autowired
    private StlSecAcctInfoMapper  stlSecAcctInfoMapper;
    
    /**
     * 查询
     * @param pageParam Map
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<FundSecuritiesAccountDto> selectForPage(int page, int length,Map<String, Object> param)
        throws Exception {
        // 只查询基金账户数据
        return super.queryForPage(page, length, () -> fundSecuritiesAccountMapper.select(param));
    }
    
    /**
     * 联想文本框service层实现
     *
     * @param param String
     * @return SuggestDto
     */
    public List<SuggestDto> getSecAcctId(String param) {
        return  fundSecuritiesAccountMapper.getSecAcctId(param);
    }
    /**
     * 基金管理人文本框service层实现
     *
     * @param param String
     * @return SuggestDto
     */
    public List<SuggestDto> getSecManager(String param) {
        return  fundSecuritiesAccountMapper.getSecManager(param);
    }
    /**
     * 基金管理人下拉框service层实现
     *
     * @param param String
     * @return SuggestDto
     */
    public List<SelectOptionDto> getSecManagerSelect(String param) {
        return  fundSecuritiesAccountMapper.getSecManagerSelect(param);
    }
    
    /**
     * 联想文本框service层实现
     *
     * @param param String
     * @return SuggestDto
     */
    public FundSecuritiesAccountDto selectBySecAcctId(String secAcctId) {
        return  fundSecuritiesAccountMapper.selectBySecAcctId(secAcctId);
    }
    /**
     * 删除操作
     *
     * @param param String
     * @return SuggestDto
     */
    public int delete(StlSecAcctInfo entity) {
        return  stlSecAcctInfoMapper.deleteByPrimaryKey(entity.getSecAcctId());
    }
    /**
     * 插入操作
     *
     * @param param String
     * @return SuggestDto
     */
    public int insert(FundSecuritiesAccountDto entity) {
        return  stlSecAcctInfoMapper.insert(entity);
    }
    /**
     * 修改操作
     *
     * @param param String
     * @return SuggestDto
     */
    public int update(FundSecuritiesAccountDto entity) {
        return  stlSecAcctInfoMapper.updateByPrimaryKey(entity);
    }
    /**
     *检查证券账户账号是否用过
     * <功能详细描述>
     * @param secAcctId
     * @return
     * @see
     * @since 1.0
     */
    public int checkBySecAcctId(FundSecuritiesAccountDto dto) {
        return fundSecuritiesAccountMapper.checkBySecAcctId(dto);
    }
    /**
     * 检查证券账户名称是否用过
     * <功能详细描述>
     * @param secAcctName
     * @return
     * @see
     * @since 1.0
     */
    public int checkBySecAcctName(FundSecuritiesAccountDto dto) {
        return fundSecuritiesAccountMapper.checkBySecAcctName(dto);
    }
    /**
     * 检查资金结算账户是否存在
     * <功能详细描述>
     * @param trdAcctId
     * @return
     * @see
     * @since 1.0
     */
    public int checkByTrdAcctId(FundSecuritiesAccountDto dto) {
        return fundSecuritiesAccountMapper.checkByTrdAcctId(dto);
    }
    /**
     * 删除时检查数据是否被使用过
     * <功能详细描述>
     * @param secAcctId
     * @return
     * @see
     * @since 1.0
     */
    public int checkCodeIsUse(FundSecuritiesAccountDto dto) {
        return fundSecuritiesAccountMapper.checkCodeIsUse(dto);
    }
    /**
     * 批量删除时检查数据是否被使用过
     * <功能详细描述>
     * @param item
     * @return
     * @see
     * @since 1.0
     */
    public List<FundSecuritiesAccountDto> checkAllCodeIsUse(List<FundSecuritiesAccountDto> item) {
        return fundSecuritiesAccountMapper.checkAllCodeIsUse(item);
    }

}
