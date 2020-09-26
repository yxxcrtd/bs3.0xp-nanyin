/*
 * 文件名：InterfCommonMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年7月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokBankSceConfig;
import com.joyintech.tams.common.base.entity.BokBooksetCapacctInfo;
import com.joyintech.tams.common.base.entity.BokBooksetExt;
import com.joyintech.tams.common.base.entity.BokBooksetPlan;
import com.joyintech.tams.common.base.entity.BokSceneconfig;
import com.joyintech.tams.common.base.entity.BokScenesubject;
import com.joyintech.tams.common.base.entity.BokSchemeAssetConfig;
import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.common.base.entity.BokTabledict;
import com.joyintech.tams.common.base.entity.BokTablelist;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinProductClass;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.StlFinProductAcct;
import com.joyintech.tams.common.base.entity.StlOrdersMapping;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.valuation.common.interf.base.dto.ProductDealDto;
import com.joyintech.tams.valuation.common.interf.base.dto.TargetFinProduct;
import com.joyintech.tams.valuation.common.interf.base.dto.ValBooksetInfoDto;

@Repository
public interface InterfCommonMapper {
    /**
     * 通过产品ID查询账套信息
     * 
     * @param prodId 产品ID
     * @return
     */
    public List<ValBooksetInfoDto> getBooksetInfoByProdId(@Param("prodId")String prodId);
    
    /**
     * 查询 账套配置
     * 
     * @param booksetId
     * @return
     */
    public List<BokBooksetExt> selectBooksetExtByBooksetId(@Param("booksetId")String booksetId);

    /**
     * 通过账套代码查询科目明细
     * 
     * @param booksetId 账套代码
     * @return List<ValBokSubject>  科目明细
     * @see
     * @since 1.0
     */
    List<BokSubject> selectValBokSubjectInfoByBooksetId(@Param("booksetId") String booksetId);

    /**
     * 通过账套代码查询会计场景
     * 
     * @param booksetId 账套代码
     * @return List<ValBokTablelist>  会计场景
     * @see
     * @since 1.0
     */
    List<BokTablelist> selectValBokTablelistByBooksetId(@Param("booksetId") String booksetId);


    /**
     * 通过账套代码查询会计元素
     * 
     * @param booksetId 账套代码
     * @return
     */
    List<BokTabledict> selectValBokTabledictByBooksetId(@Param("booksetId") String booksetId);
    
    /**
     * 通过账套代码查询场景科目关系
     * 
     * @param booksetId 账套代码
     * @return
     */
    List<BokScenesubject> selectValBokScenesubjectByBooksetId(@Param("booksetId") String booksetId);
    
    /**
     * 通过账套代码查询场景分支配置
     * 
     * @param booksetId
     * @return
     */
    List<BokSceneconfig> selectValBokSceneconfigByBooksetId(@Param("booksetId") String booksetId);


    /**
     * 通过账套代码查询账套估值方案
     * 
     * @param booksetId
     * @return
     */
    List<BokBooksetPlan> selectValBooksetValPlanByBooksetId(@Param("booksetId") String booksetId);
    /**
     * 通过账套代码和科目号查询账套科目信息
     * 
     * @param booksetId
     * @return
     */
    BokSubject getValBokSubject(BokSubject valBokSubject);

//    delete by wujie on 20181204
//    /**
//     * <一句话功能简述>
//     * <功能详细描述>
//     * @param cashId
//     * @param prodType
//     * @param prodId
//     * @return
//     * @see
//     * @since 1.0
//     */
//    public FinCashCalcRule queryCashCalcRule(@Param("cashId")String cashId, @Param("prodType")String prodType, @Param("prodId")String prodId);
    /**
     * 通过金融产品代码查询标的信息
     * 
     * @param 金融产品代码
     * @return
     */
    TargetFinProduct getTargetFinProduct(String finprodId);
       

    /**
     * 通过金融产品代码查询交易类金融产品
     * 
     * @param 金融产品代码
     * @return
     */
    FinTradeProduct queryFinTradeProduct(String finprodId);

    /**
     *
     * 并集查询：标的类金融产品表和交易类金融产品表
     * 通过金融产品代码查询标的信息
     * @param finprodId
     * @return TargetFinProduct
     * @see
     * @since 1.0
     */
    TargetFinProduct queryTargetFinProduct(String finprodId);

    /**
     * 
     * 金融产品账户查询
     * @param finprodId 金融产品代码
     * @param finprodType 金融产品类型
     * @param acctType 账户类型
     * @return ValFinProductAcct
     * @see
     * @since 1.0
     */
     StlFinProductAcct selectValFinProductAcct(@Param("finprodId") String finprodId,@Param("finprodType") String finprodType,
    		 @Param("acctType") String acctType,@Param("acctNo")String acctNo,@Param("ccy")String ccy);
     /**
      * 查询账套清算款账户
      * 
      * @return
      * @author WUJIE
      * @serialData 20181210
      */
     BokBooksetCapacctInfo queryBokBooksetCapacctInfo(Map<String, Object> map);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param string
     * @see
     * @since 1.0
     */
    public int queryPdSequences();

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param string
     * @see
     * @since 1.0
     */
    public int queryOrderSequences();
    
    /**
     * 查询核算分类
     * <功能详细描述>
     * @param string
     * @see
     * @since 1.0
     */
    public String getFinanceProductClass(FinProductClass finProductClass);
    
    /**
     * 查询税务分类
     * <功能详细描述>
     * @param string
     * @see
     * @since 1.0
     */
    public String getFinanceProductTaxClass(FinProductClass finProductClass);


    /**
     * 当前计划对应分支序号查询 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    Long queryValFinProductDealBranchNow(Map<String, Object> map);
    
    /**
     * 最大分支序号查询
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    Long queryValFinProductDealMaxBranch(Map<String, Object> map);

    /**
     * 现金流信息查询
     * 
     * @param map
     */
    public FinCashInfo queryCashInfo(Map<String, Object> map);
	public List<FinCashInfo> queryCashInfoList(Map<String, Object> map);
    
    /**
     * 根据默认的税务方案初始化帐套的税务方案
     * 
     * @param map
     * @return
     */
    public BokBooksetPlan buildPlan(Map<String,Object> map);
    
    /**
     * 根据产品代码查询账套信息_分层产品
     *
     */
    public List<ValBooksetInfoDto>  getBooksetInfoLayering(@Param("prodId")String prodId);

    /**
     * 根据产品代码查询金融产品类型
     */
    public String queryProductType(@Param("prodId")String prodId);
    
    /**
     * 根据参数和账套代码查询账套配置信息
     */
    public  BokBooksetExt  queryBokBooksetExtbyId(@Param("code")String code,@Param("booksetId")String  booksetId);
    
    /**
     * 获取未执行的序号
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    List<String> getSeqnoUnEXEC(Map<String, Object> params);
    
    /**
     * 查询交易对应指令
     * @param productDealDto
     * @return
     */
    public List<StlOrdersMapping> queryStlOrdersMapping(ProductDealDto productDealDto);
    
    /**
     * 查询对账方案资产映射表
     * @return
     */
    List<BokSchemeAssetConfig> getBokSchemeAssetConfigList();
    
    BokSchemeAssetConfig getBokSchemeAssetConfigDto(Map<String, Object> params);
	/**
     * 现金流计算规则信息
     */
	public List<FinCashCalcRule> queryFinCashCalcRuleList(Map<String, Object> map);
    /**
     *  金融产品账户信息查询
     */
	public List<StlFinProductAcct> queryStlFinProductAcctInfo(Map<String, Object> map);
    
    List<TrdProductDeal> queryDelChlFeeDeal(List<TrdProductDeal> list);
    
    List<BokBankSceConfig> getBokBankSceConfig(Map<String, Object> params);
    
    /**
     * 查询金融产品类型
     * 
     * @return
     */
    String getFinprodType(Map<String, Object> map);

    /**
     * 更加条件查询标的
     * @param finprodId
     * @param finprodType
     * @return
     */
    TargetFinProduct getTargetFinProductByCondition(@Param("finprodId") String finprodId,
                                                    @Param("finprodType") String finprodType);
    
    /**
     * 查询附加税税则中的附加税类型
     * @param params 参数
     * @return 缴纳附加税相关附加税类型
     * @see
     * @since 1.0
     */
    List<String> getAddTaxType(Map<String, Object> params);
}
