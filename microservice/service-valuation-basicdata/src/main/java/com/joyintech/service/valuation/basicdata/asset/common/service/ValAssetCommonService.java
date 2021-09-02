/*
 * 文件名：AssetCommonService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.basicdata.asset.common.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.basicdata.asset.common.dao.ValAssetCommonMapper;
import com.joyintech.tams.common.base.entity.FinProductClass;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * 资产端共通
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月25日
 * @see AssetCommonService
 * @since 1.0
 */
@Service(value="serviceValAssetCommonService")
public class ValAssetCommonService extends BaseService{


    @Autowired
    private ValAssetCommonMapper assetCommonMapper;


    /**
     * 资产模糊下拉框
     * <功能详细描述>
     * @param search
     * @param finprodType2
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getAssetId(Map<String, Object> param){
        param.put("FINPROD_TYPE_ZQ", DictCodeListExt1.金融产品类型_债券.getValue());
        //增加母资管计划筛选条件 TODO
        if (StringUtil.isNotEmpty(param.get("finprodType"))) {
            if(StringUtil.equals(param.get("finprodType"), DictCodeListExt1.金融产品类型_计划类资产.getValue())){
                param.put("finprodType", StringUtil.splitToList(null, param.get("finprodType").toString()+","+ DictCodeListExt1.金融产品类型_投管用_母资管计划.getValue()));
            }else{
                param.put("finprodType", StringUtil.splitToList(null, param.get("finprodType").toString()));
            }
        }
        return assetCommonMapper.getAssetId(param);
    }

    /**
     * 获取资产联想下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @Transactional(readOnly = true)
    public Page<SelectOptionDto> getAssetIdOption(int page,int length,@RequestBody Map<String, Object> param) {
        return this.queryForPageForSelectReturn(page, length,() ->assetCommonMapper.getAssetIdOption(param));
    }

    @SuppressWarnings("unchecked")
    public Page<SelectOptionDto> getCustomer(int page, int length, Map<String, Object> param) {
        return this.queryForPageForSelectReturn(page, length,
                () -> assetCommonMapper.getCustomer(param));
    }

    public Page<SelectOptionDto> selectCalendarIdForPge(int page, int length,
                                                          Map<String, Object> param) {
       return this.queryForPageForSelectReturn(page, length,
                () -> assetCommonMapper.selectCalendarId(param));
    }

    /**
     * 查询核算分类
     *
     * @return 核算分类
     */
    public String getFinanceProductClass(FinProductClass finProductClass) {
        String valclas = assetCommonMapper.getFinanceProductClass(finProductClass);
        return valclas;
    }

    /**
     * 查询税务分类
     *
     * @return 税务分类
     */
    public String getFinanceProductTaxClass(FinProductClass finProductClass) {
        String valclas = assetCommonMapper.getFinanceProductTaxClass(finProductClass);
        return valclas;
    }

    /**
     * 获取市场机构
     * @param page
     * @param length
     * @param param
     * @return
     */
    public Page<SelectOptionDto> getCustomerName(int page, int length, Map<String, Object> param) {
        return this.queryForPageForSelectReturn(page, length,
                () -> assetCommonMapper.getCustomer(param));
    }
}
