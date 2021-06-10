/*
 * 文件名：FinDepositTargetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.basicdata.asset.depo.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.basicdata.asset.depo.dao.FinDepositTargetMapper;
import com.joyintech.service.valuation.basicdata.asset.depo.dto.FinDepositTargetDto;
import com.joyintech.service.valuation.basicdata.base.service.BaseFinProductBaseInfoService;
import com.joyintech.service.valuation.component.asset.service.FinInterestFactorService;
import com.joyintech.tams.common.base.dao.FinCashCalcRuleMapper;
import com.joyintech.tams.common.base.dao.PaFinProductBuyoutRateMapper;
import com.joyintech.tams.common.base.dao.PaFinProductMapper;
import com.joyintech.tams.common.base.entity.FinProductClass;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 存款类标的信息service
 * @author zhangnian
 * @version 1.0 2018年9月14日
 * @see FinDepositTargetService
 * @since 1.0
 */
@Service(value="serviceFinDepositTargetService")
public class FinDepositTargetService extends BaseService<FinDepositTargetDto>{

   
    /**
     * 定义Mapper(mime)
     */
    @Autowired
    private FinDepositTargetMapper finDepositTargetMapper;
    
    @Autowired
    private PaFinProductMapper paFinProductMapper;
    /**
     * 基础--标的类金融产品
     */
    @Autowired(required = false)
    private BaseFinProductBaseInfoService baseFinProductBaseInfoService;
    
    @Autowired(required = false)
    private FinInterestFactorService finInterestFactorService;


    /**
     * 金融产品买断利率(平安,生成)
     */
    @Autowired
    private PaFinProductBuyoutRateMapper paFinProductBuyoutRateMapper;

    /**
     * 现金流计算规则表
     */
    @Autowired
    private FinCashCalcRuleMapper finCashCalcRuleMapper;

    
    /**
     * 分页
     * @param pageParam
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<FinDepositTargetDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page, length, () -> finDepositTargetMapper.queryForPage(pageParam));
    }
    
    /**
     * 插入
     * @param entity
     * @return
     * @see
     * @since 1.0
     */
     public void insert(FinDepositTargetDto entity) {
        //标的金融产品信息
        baseFinProductBaseInfoService.insert(entity.getBaseFinProductBaseInfoList());

    }

    public void update(FinDepositTargetDto entity){
        //标的金融产品信息
        List<String> finprodIdList = new ArrayList<String>();
        finprodIdList.add(entity.getFinprodId());
        baseFinProductBaseInfoService.update(finprodIdList, entity.getBaseFinProductBaseInfoList());

    }

    /**
     * 删除
     * @param entity
     * @see
     * @since 1.0
     */
    public void delete(FinDepositTargetDto entity) {
        List<String> finprodIdList = new ArrayList<String>();
        finprodIdList.add(entity.getFinprodId());
        //标的金融产品信息
        baseFinProductBaseInfoService.delete(finprodIdList);
        //利率表
        finDepositTargetMapper.deleteFinCashCalcRule(entity.getFinprodId());
    }


    /**
     * 获取基本信息--修改/详细
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public FinDepositTargetDto getBasicInfo(String finprodId,String finprodType,String cashType,String finprodMarketId,String assetType){
        String finprodType2="";
        if(DictCodeListExt1.存款类资产类型_GZ_同业借款.getValue().equals(assetType)){
            finprodType2=DictCodeListExt1.金融产品类型_同业借款.getValue();
        }else {
            finprodType2=DictCodeListExt1.金融产品类型_投管用_存款.getValue();
        }
        return finDepositTargetMapper.getBasicInfo(finprodId,finprodType,cashType,finprodMarketId, finprodType2);

    }




    /**
     * 存款机构下拉框
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    /*存借款机构匹配存放行和融资人属性 modified by lishuping 20191018 start*/
    public List<SelectOptionDto> getCustomerName(@RequestBody Map<String, Object> param) {
        if (param == null) {
            param = new HashMap<String, Object>();
        }
        param.put("CUSTOMER_TYPE", DictCodeList.客户类型_法人.getValue());
        param.put("IS_DEPOSIT_BANK", DictCodeList.是否_是.getValue());
        param.put("IS_FINANCIER", DictCodeList.是否_是.getValue());
        return finDepositTargetMapper.getCustomerName(param);
    }

    /*存借款机构匹配存放行和融资人属性 modified by lishuping 20191018 end*/


    /**
     *
     * 根据条件查询全部信息
     * @param pageParam 筛选条件
     * @return 全部信息
     * @see
     * @since 1.0
     */
    public List<FinDepositTargetDto> selectAll(Map<String, Object> pageParam){
        return finDepositTargetMapper.queryForPage(pageParam);
    }

    /**
     * 金融产品代码是否重复
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public int selectCountByfinprodId(String finprodId) {
        return finDepositTargetMapper.selectCountByfinprodId(finprodId);
    }

    /**
     * 资产模糊下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getAssetId(Map<String, Object> param){
        param.put("FINPROD_TYPE_ZQ", DictCodeListExt1.金融产品类型_存款.getValue());
        //增加母资管计划筛选条件 TODO
        if (StringUtil.isNotEmpty(param.get("finprodType"))) {
            if(StringUtil.equals(param.get("finprodType"), DictCodeListExt1.金融产品类型_计划类资产.getValue())){
                param.put("finprodType", StringUtil.splitToList(null, param.get("finprodType").toString()+","+ DictCodeListExt1.金融产品类型_投管用_母资管计划.getValue()));
            }else{
                param.put("finprodType", StringUtil.splitToList(null, param.get("finprodType").toString()));
            }
        }
        return finDepositTargetMapper.getAssetId(param);
    }

    /**
     * 查询核算分类
     *
     * @return 核算分类
     */
    public FinProductClass getFinanceProductClass(FinProductClass finProductClass) {
        return finDepositTargetMapper.getFinanceProductClass(finProductClass);
    }
}
