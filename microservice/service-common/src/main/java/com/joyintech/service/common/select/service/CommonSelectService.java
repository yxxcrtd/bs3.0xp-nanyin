/*
 * 文件名：CommonSelectService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年09月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.common.select.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.common.select.dao.CommonSelectMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CommonSelectService extends BaseService {

    @Autowired(required = false)
    private SqlSessionFactory sessionFactory;

    @Autowired
    private CommonSelectMapper commonSelectMapper;

    public List<SuggestDto> getAssetIdCommon(Map<String, Object> param) {
        return this.queryForSuggest(() -> commonSelectMapper.getAssetIdCommon(param), null);
    }
    
    /**
     * 
     * 资产简称(债券)
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getFinprodAbbr(Map<String, Object> param) {
        return this.queryForSuggest(() -> commonSelectMapper.getFinprodAbbr(param), null);
    }

    public List<SuggestDto> getBrachBankId(Map<String, Object> param) {
        return this.queryForSuggest(() -> commonSelectMapper.getBrachBankId(param), null);
    }

    @SuppressWarnings("unchecked")
    public List<SuggestDto> getCustomerId(Map<String, Object> param) {
        return this.queryForSuggest(() -> commonSelectMapper.getCustomerId(param), null);
    }

    public List<SuggestDto> getEntrustId(Map<String, Object> param) {
        return this.queryForSuggest(() -> commonSelectMapper.getEntrustId(param), null);
    }

    public List<SuggestDto> getMarketValuationId(Map<String, Object> param) {
        return this.queryForSuggest(() -> commonSelectMapper.getMarketValuationId(param), null);
    }

    public List<SuggestDto> getCommonSelect(String selectTarget, Map<String, Object> param) {
        SqlSession session = SqlSessionUtils.getSqlSession(sessionFactory);
        List<SuggestDto> list = session.selectList(
            CommonSelectMapper.class.getName() + ".get" + selectTarget, param);
        SqlSessionUtils.closeSqlSession(session, sessionFactory);
        return list;
    }

    public List<SelectOptionDto> selectCommonSelect(String selectTarget,
                                                    Map<String, Object> param) {
        SqlSession session = SqlSessionUtils.getSqlSession(sessionFactory);
        List<SelectOptionDto> list = session.selectList(
            CommonSelectMapper.class.getName() + ".select" + selectTarget, param);
        SqlSessionUtils.closeSqlSession(session, sessionFactory);
        return list;
    }

    public Page<SelectOptionDto> selectCommonSelectForPge(String selectTarget, int page, int length,
                                                    Map<String, Object> param) {
        switch (selectTarget) {
            case "CounterId" :
                return this.queryForPageForSelectReturn(page, length,
                        () -> commonSelectMapper.selectCounterId(param));
            case "CalendarId" :    return this.queryForPageForSelectReturn(page, length,
                    () -> commonSelectMapper.selectCalendarId(param));
            case "CashOutNo" :    return this.queryForPageForSelectReturn(page, length,
                    () -> commonSelectMapper.selectCashOutNo(param));
            case "PortfolioId" :    return this.queryForPageForSelectReturn(page, length,
                    () -> commonSelectMapper.selectPortfolioId(param));
            case "WindBondAsset" :    return this.queryForPageForSelectReturn(page, length,
                () -> commonSelectMapper.selectWindBondAsset(param));
            case "WindFundAsset" :    return this.queryForPageForSelectReturn(page, length,
                () -> commonSelectMapper.selectWindFundAsset(param));
        }
        return new Page<>();
    }

    @SuppressWarnings("unchecked")
    public Page<SelectOptionDto> getCustomer(int page, int length, Map<String, Object> param) {
        return this.queryForPageForSelectReturn(page, length,
            () -> commonSelectMapper.getCustomer(param));
    }

    @SuppressWarnings("unchecked")
    public Page<SelectOptionDto> getGroupIdInfo(int page, int length, Map<String, Object> param) {
        return this.queryForPageForSelectReturn(page, length,
            () -> commonSelectMapper.getGroupIdInfo(param));
    }

    @SuppressWarnings("unchecked")
    public Page<SelectOptionDto> getAcctNo(int page, int length, Map<String, Object> param) {
        return this.queryForPageForSelectReturn(page, length,
            () -> commonSelectMapper.getAcctNo(param));
    }

    @SuppressWarnings("unchecked")
    public List<SelectOptionDto> selectPtlCapAcctNo(Map<String, Object> param) {
        // TODO Auto-generated method stub
        return this.queryForSuggest(() -> commonSelectMapper.selectPtlCapAcctNo(param), null);
    }
    
    // 分页查询 add by renping 20191030 start
    @SuppressWarnings("unchecked")
    public Page<SelectOptionDto> qryPtlCapAcctNoByRequestParam(int page, int length,
                                                    Map<String, Object> param) {
        return this.queryForPageForSelectReturn(page, length,
            () -> commonSelectMapper.selectPtlCapAcctNo(param));
    }

    @SuppressWarnings("unchecked")
    public Page<SelectOptionDto> qryMstCapAcctNoByRequestParam(int page, int length,
                                                               Map<String, Object> param) {
        return this.queryForPageForSelectReturn(page, length,
            () -> commonSelectMapper.selectMstCapAcctNo(param));
    }
    // 分页查询 end by renping 20191030 end
    
    @SuppressWarnings("unchecked")
    public List<SelectOptionDto> selectMstCapAcctNo(Map<String, Object> param) {
        // TODO Auto-generated method stub
        return this.queryForSuggest(() -> commonSelectMapper.selectMstCapAcctNo(param), null);
    }

    public List<SelectOptionDto> getChannelIdForAsset(Map<String, Object> params) {
        params.put("Y", DictCodeList.是否_是.getValue());
        params.put("F28", DictCodeListExt1.金融产品类型_投管用_通道.getValue());
        if (Boolean.valueOf((String) params.get("assetChl"))
                && (StringUtil.isNotEmpty((String) params.get("finprodMarketId")) || StringUtil.isNotEmpty((String) params.get("finprodId")))) {
            return commonSelectMapper.getChannelIdForAsset(params);
        }
        return new ArrayList<>();
    }
    
    public List<SelectOptionDto> getChannelIdAndCollectTypeForAsset(Map<String, Object> params) {
        params.put("Y", DictCodeList.是否_是.getValue());
        params.put("F28", DictCodeListExt1.金融产品类型_投管用_通道.getValue());
        if ((StringUtil.isNotEmpty((String) params.get("finprodMarketId")) || StringUtil.isNotEmpty((String) params.get("finprodId")))) {
            return commonSelectMapper.getChannelIdAndCollectTypeForAsset(params);
        }
        return new ArrayList<>();
    }

    /**
     * 资产代码查询（可投金额试算使用）
     * Add by nixiukuan 20190927
     *债券、基金、股票
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    public List<SuggestDto> getFinprodByType(Map<String, Object> param) {
        return this.queryForSuggest(() -> commonSelectMapper.getFinprodByType(param), null);
    }

    public Page<SelectOptionDto> getSecManageAcctId(int page, int length, Map<String, Object> param) {
        return queryForPageForSelectReturn(page, length, () -> commonSelectMapper.getSecManageAcctId(param));
    }

    public Page<SelectOptionDto> selectFinprodMarketId(int page,int length, Map<String, Object> param) {
        return queryForPageForSelectReturn(page, length, () -> commonSelectMapper.selectFinprodMarketId(param));

    }
}
