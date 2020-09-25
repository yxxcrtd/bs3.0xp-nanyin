package com.joyintech.tams.invest.product.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.PaTrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.TrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.PaBranchSaleDetail;
import com.joyintech.tams.common.base.entity.PaTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.web.PlatformDate;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.product.productelement.build.dto.FinProductBuild002Dto;
import com.joyintech.tams.invest.product.productelement.module.dto.FinProductModuleInfo002Dto;
import com.joyintech.tams.invest.product.productelement.module.dto.FinProductModuleInfoDto;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: pingan-parent
 * @Package com.joyintech.tams.invest.product.common.service
 * @Description: 产品端共通Service
 * @date Date : 2018年09月06日 15:34
 */
@Service
public class ProductCommonService extends BaseService {

    /**
     * {@linkplain}
     */
    @Autowired
    private PlatformDate platformDate;
    
    /**
     * {@linkplain}
     */
    

    public List<SuggestDto> getProdId() {
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/getProdId",
            RequestUtil.getPageParam(), SuggestDto.class);
    }

    public List<SuggestDto> getGroupId() {
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/getGroupId",
            RequestUtil.getPageParam(), SuggestDto.class);
    }

    /**
     * 根据金融产品代码查询标的信息
     * 
     * @param finprodId 金融产品代码
     * @return 标的信息
     * @see
     * @since 1.0
     */
    public FinProduct queryFinProductByFinprodId(String finprodId) {
        return restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                         + "/FinProductCommon/queryFinProductByFinprodId?finprodId="
                                         + finprodId,
            FinProduct.class);
    }

    /**
     * 查询查询交易对手下拉框
     * 
     * @param map 参数
     * @return 查询交易对手
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryCounterpartyInfo(Map<String, Object> map) {
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/queryCounterpartyInfo",
            map, SelectOptionDto.class);
    }

    /**
     * 交易日历下拉框查询
     * 
     * @param map 参数
     * @return 交易日历
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryCalendarInfoList(Map<String, Object> map) {
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/queryCalendarInfoList",
            map, SelectOptionDto.class);
    }

    /**
     * 根据产品代码查询产品信息
     * 
     * @param finprodMarketId 产品代码
     * @param finprodType 金融产品类型
     * @return 产品信息
     * @see
     * @since 1.0
     */
    public FinProductBuild002Dto queryProdInfoByFinprodMarketId(String finprodMarketId,
                                                                String finprodType) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodMarketId", finprodMarketId);
        map.put("finprodType2", finprodType);
        map.put("branch", CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        return restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductBuild/queryProdInfoByFinprodId",
            map, FinProductBuild002Dto.class);
    }
    
    /**
     * 通过模板ID获取模板信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param templateId
     * @return
     * @see
     * @since 1.0
     */
    public FinProductModuleInfo002Dto selectTemplateById(String templateId) {
        FinProductModuleInfoDto finProductModuleInfoDto = restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
            + "/FinProductModule/selectByTemplateId?templateId="+templateId,
                FinProductModuleInfoDto.class);
        if(StringUtil.isNotEmpty(finProductModuleInfoDto) && StringUtil.isNotEmpty(finProductModuleInfoDto.getTemplateContent())) {
            return JsonUtils.json2Object(finProductModuleInfoDto.getTemplateContent(), FinProductModuleInfo002Dto.class);
        }
        return null;
    }

    /**
     * 根据产品代码模糊查询联系下拉框
     * 
     * @param map 参数map
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryProdIdInfo(Map<String, Object> map) {
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/queryProdIdInfo", map,
            SelectOptionDto.class);
    }

    /**
     * 根据费用账户模糊查询联想下拉框
     * 
     * @param map 参数map
     * @return 费用账户
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryFeeAccountList(Map<String, Object> map) {
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/queryFeeAccountList",
            map, SelectOptionDto.class);
    }
    
    /**
     * 根据产品代码模糊查询联系下拉框(发行成功的母产品)
     * 
     * @param map 参数map
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryProdIdInfoIssueSuc(Map<String, Object> map) {
        map.put("userId", SecurityUtil.getLoginUser().getUserName());
        map.put("platDate", DateUtil.format(platformDate.getSysDate()));
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/queryProdIdInfoIssueSuc",
            map, SelectOptionDto.class);
    }

    /**
     * 费用代码联想下拉框
     * 
     * @param map 参数map
     * @return 费用代码
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryFeeInfoList(Map<String, Object> map) {
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/queryFeeInfoList", map,
            SelectOptionDto.class);
    }

    /**
     * 托管名称联想下拉框
     * 
     * @param map 参数map
     * @return 费用代码
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryTrusterCustomerName(Map<String, Object> map) {
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/queryTrusterCustomerName",
            map, SelectOptionDto.class);
    }

    /**
     * 根据交易编号查询流程中数据是否存在
     *
     * @param tradeId 交易编号
     * @return 流程中数据是否存在
     * @see
     * @since 1.0
     */
    public Boolean isInFlowDataExisted(String tradeId) {
        return restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                         + "/FinProductCommon/isInFlowDataExisted?tradeId="
                                         + tradeId,
            Boolean.class);
    }

    /**
     * 根据交易编号查询该交易是否已被撤销
     *
     * @param tradeId 交易编号
     * @return 该交易是否已被撤销
     * @see
     * @since 1.0
     */
    public Boolean isRevokeDataExisted(String tradeId) {
        return restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                         + "/FinProductCommon/isAlreadyRevoked?tradeId=" + tradeId,
            Boolean.class);
    }

    /**
     * 根据汇总交易编号划款指令信息
     * 
     * @param tradeId 汇总交易编号
     * @return 划款指令信息
     * @see
     * @since 1.0
     */
    public List<StlPayOrdersDto> queryStlPayOrdersByTradeId(String tradeId) {
        return restTemplate.getForList(ServiceApplication.PRODUCT_SERVICE_NAME
                                       + "/FinProductCommon/queryStlPayOrdersByTradeId?tradeId="
                                       + tradeId,
                                       StlPayOrdersDto.class);
    }

    /**
     * 根据汇总交易编号查询交易信息
     * 
     * @param tradeId 汇总交易编号
     * @return 交易信息
     * @see
     * @since 1.0
     */
    public List<TrdProductDeal> queryTrdProductDealByTotleTradeId(String tradeId) {
        return restTemplate.getForList(ServiceApplication.PRODUCT_SERVICE_NAME
                                       + "/FinProductCommon/queryTrdProductDealByTotleTradeId?tradeId="
                                       + tradeId,
            TrdProductDeal.class);
    }

    /**
     * 根据汇总交易编号查询交易附属信息
     * 
     * @param tradeId 汇总交易编号
     * @return 交易附属信息
     * @see
     * @since 1.0
     */
    public List<TrdProductDealAdd> queryTrdProductDealAddByTotleTradeId(String tradeId) {
        return restTemplate.getForList(ServiceApplication.PRODUCT_SERVICE_NAME
                                       + "/FinProductCommon/queryTrdProductDealAddByTotleTradeId?tradeId="
                                       + tradeId,
                                       TrdProductDealAdd.class);
    }

    /**
     * 根据汇总交易编号查询金融产品交易扩展
     * 
     * @param tradeId 汇总交易编号
     * @return 金融产品交易扩展
     * @see
     * @since 1.0
     */
    public List<PaTrdProductDeal> queryPaTrdProductDealByTotleTradeId(String tradeId) {
        return restTemplate.getForList(ServiceApplication.PRODUCT_SERVICE_NAME
                                       + "/FinProductCommon/queryPaTrdProductDealByTotleTradeId?tradeId="
                                       + tradeId,
                                       PaTrdProductDeal.class);
    }

    /**
     * 根据汇总交易编号查询理财分行销售明细
     * 
     * @param tradeId 汇总交易编号
     * @return 理财分行销售明细
     * @see
     * @since 1.0
     */
    public List<PaBranchSaleDetail> queryPaBranchSaleDetailByTotleTradeId(String tradeId) {
        return restTemplate.getForList(ServiceApplication.PRODUCT_SERVICE_NAME
                                       + "/FinProductCommon/queryPaBranchSaleDetailByTotleTradeId?tradeId="
                                       + tradeId,
                                       PaBranchSaleDetail.class);
    }
    
    /**
     * 根据汇总交易编号修改金融产品交易的撤销状态
     * 
     * @param tradeId 汇总交易编号
     * @return DB变更行数
     * @see
     * @since 1.0
     */
    public int updateTrdProductDealByTotleTradeId(String tradeId){
        return restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                       + "/FinProductCommon/updateTrdProductDealByTotleTradeId?tradeId="
                                       + tradeId, Integer.class);
    }
    
    /**
     * 费用类型转成现金流二级类型
     * 
     * @param feeType 费用类型
     * @return 现金流二级类型
     * @see
     * @since 1.0
     */
    public String convertFeeTypeToCashType(String feeType) {
        if (StringUtil.equals(feeType, DictCodeList.费用品种类型_销售手续费.getValue())) {
            return DictCodeListExt1.现金流二级类型_销售费.getValue();
        }
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_固定管理费.getValue())) {
            return DictCodeListExt1.现金流二级类型_固定管理费.getValue();
        }
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_托管费.getValue())) {
            return DictCodeListExt1.现金流二级类型_托管费.getValue();
        }
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_代销费.getValue())) {
            return DictCodeListExt1.现金流二级类型_代销费.getValue();
        }
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_浮动差额中收.getValue())) {
            return DictCodeListExt1.现金流二级类型_浮动管理费.getValue();
        }
     // 一体化对应etliu start
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_浮动管理费.getValue())) {
            return DictCodeListExt1.现金流二级类型_浮动管理费.getValue();
        }
     // 一体化对应etliu end
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_其他费用.getValue())) {
            return DictCodeListExt1.一次性费用类型_其他费用.getValue();
        }
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_交易手续费.getValue())) {
            // 一体化对应etliu start
//            return DictCodeListExt2.费用类型_银行手续费.getValue();
            return DictCodeListExt1.一次性费用类型_交易手续费.getValue();
            // 一体化对应etliu end
        }
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_结算服务费.getValue())) {
         // 一体化对应etliu start
//            return DictCodeListExt2.费用类型_结算服务费.getValue();
            return DictCodeListExt1.一次性费用类型_结算手续费.getValue();
         // 一体化对应etliu end
        }
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_账户维护费.getValue())) {
            return DictCodeListExt1.一次性费用类型_账户维护费.getValue();
        }
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_增值附加税费.getValue())) {
         // 一体化对应etliu start
//            return DictCodeListExt2.费用类型_增值附加税费.getValue();
            return DictCodeListExt1.现金流二级类型_税额.getValue();
         // 一体化对应etliu end
        }
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_外包服务费.getValue())) {
            return DictCodeListExt1.现金流二级类型_外包服务费.getValue();
        }
        //add by wm start 2019-08-15 通道费用转换
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_通道托管费.getValue())) {
            return DictCodeListExt1.一次性费用类型_通道费.getValue();
        }
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_通道管理费.getValue())) {
            return DictCodeListExt1.一次性费用类型_通道费.getValue();
        }
      //add by wm end 2019-08-15 通道费用转换
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_佣金.getValue())) {
            return DictCodeListExt1.一次性费用类型_佣金.getValue();
        }
        /**add by chendijun 2019-10-15 投资顾问费转换 **/
        else if (StringUtil.equals(feeType, DictCodeListExt2.费用品种类型_投资顾问费.getValue())) {
            return DictCodeListExt2.现金流二级类型_投资顾问费.getValue();
        }
        return null;
    }
    /**
     * 费用类型转成现金流二级类型
     * 
     * @param feeType 费用类型
     * @return 现金流二级类型
     * @see
     * @since 1.0
     */
    public String convertFeeTypeToCashTypeForFinType(String feeType) {
        if (StringUtil.equals(feeType, DictCodeList.费用品种类型_销售手续费.getValue())) {
            return DictCodeListExt1.现金流二级类型_销售费.getValue();
        }
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_固定管理费.getValue())) {
            return DictCodeListExt1.现金流二级类型_固定管理费.getValue();
        }
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_托管费.getValue())) {
            return DictCodeListExt1.现金流二级类型_托管费.getValue();
        }
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_代销费.getValue())) {
            return DictCodeListExt1.现金流二级类型_代销费.getValue();
        }
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_浮动差额中收.getValue())) {
            return DictCodeListExt1.现金流二级类型_浮动管理费.getValue();
        }
     // 一体化对应etliu start
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_浮动管理费.getValue())) {
            return DictCodeListExt1.现金流二级类型_浮动管理费.getValue();
        }
     // 一体化对应etliu end
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_其他费用.getValue())) {
            return DictCodeListExt1.金融产品类型_交易费用.getValue();
        }
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_交易手续费.getValue())) {
            // 一体化对应etliu start
//            return DictCodeListExt2.费用类型_银行手续费.getValue();
            return DictCodeListExt1.金融产品类型_交易费用.getValue();
            // 一体化对应etliu end
        }
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_结算服务费.getValue())) {
         // 一体化对应etliu start
//            return DictCodeListExt2.费用类型_结算服务费.getValue();
            return DictCodeListExt1.金融产品类型_交易费用.getValue();
         // 一体化对应etliu end
        }
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_账户维护费.getValue())) {
            return DictCodeListExt1.金融产品类型_交易费用.getValue();
        }
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_增值附加税费.getValue())) {
         // 一体化对应etliu start
//            return DictCodeListExt2.费用类型_增值附加税费.getValue();
            return DictCodeListExt1.现金流二级类型_税额.getValue();
         // 一体化对应etliu end
        }
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_外包服务费.getValue())) {
            return DictCodeListExt1.现金流二级类型_外包服务费.getValue();
        }
        //add by wm start 2019-08-15 通道费用转换
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_通道托管费.getValue())) {
            return DictCodeListExt1.一次性费用类型_通道费.getValue();
        }
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_通道管理费.getValue())) {
            return DictCodeListExt1.一次性费用类型_通道费.getValue();
        }
      //add by wm end 2019-08-15 通道费用转换
        else if (StringUtil.equals(feeType, DictCodeList.费用品种类型_佣金.getValue())) {
            return DictCodeListExt1.金融产品类型_交易费用.getValue();
        } 
        /**add by chendijun 2019-10-15 投资顾问费转换 **/
        else if (StringUtil.equals(feeType, DictCodeListExt2.费用品种类型_投资顾问费.getValue())) {
            return DictCodeListExt2.现金流二级类型_投资顾问费.getValue();
        }

        return null;
    }

    @Autowired
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;
    /**
     * 生成金融产品交易汇总撤销数据
     * 
     * @param tradeId 交易编号
     * @return 金融产品交易汇总撤销数据
     * @see
     * @since 1.0
     */
    public TrdFinProductDealTotal genTrdFinProductDealTotalRevoke(String tradeId) {
        TrdFinProductDealTotal trdFinProductDealTotal = bussCommonMapperDelegateService.selectBySinglePrimaryKey(
            TrdFinProductDealTotalMapper.class, tradeId, TrdFinProductDealTotal.class);
        if (trdFinProductDealTotal != null){
            trdFinProductDealTotal.setpTradeId(trdFinProductDealTotal.getTotleTradeId());
            trdFinProductDealTotal.setTotleTradeId(StringUtil.defaultString(trdFinProductDealTotal.getTotleTradeId()) + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
            trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
        }

        return trdFinProductDealTotal;
    }
    
    /**
     * 生成金融产品交易汇总扩展撤销数据
     * 
     * @param tradeId 交易编号
     * @return 金融产品交易汇总撤销数据
     * @see
     * @since 1.0
     */
    public PaTrdFinProductDealTotal genPaTrdFinProductDealTotalRevoke(String tradeId) {
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = bussCommonMapperDelegateService.selectBySinglePrimaryKey(
            PaTrdFinProductDealTotalMapper.class, tradeId, PaTrdFinProductDealTotal.class);
        if (paTrdFinProductDealTotal != null){
            paTrdFinProductDealTotal.setTotleTradeId(StringUtil.defaultString(paTrdFinProductDealTotal.getTotleTradeId()) + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
        }

        return paTrdFinProductDealTotal;
    }
    
    /**
     * 生成划款指令撤销数据
     * 
     * @param tradeId 交易编号
     * @return 划款指令撤销数据
     * @see
     * @since 1.0
     */
    public List<StlPayOrdersDto> genStlPayOrdersRevoke(String tradeId) {
        List<StlPayOrdersDto> stlPayOrdersList = this.queryStlPayOrdersByTradeId(tradeId);
        if (!StringUtil.isEmptyList(stlPayOrdersList)) {
            for (StlPayOrders stlPayOrders : stlPayOrdersList) {
                stlPayOrders.setpTradeId(stlPayOrders.getOrderId());
                stlPayOrders.setOrderId(StringUtil.defaultString(stlPayOrders.getOrderId()) + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                stlPayOrders.setTradeId(StringUtil.defaultString(stlPayOrders.getTradeId()) + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                stlPayOrders.setOrderStatus(DictCodeListExt1.指令状态_撤销.getValue());
            }
        }

        return stlPayOrdersList;
    }

    /**
     * 生成金融产品交易撤销数据
     * 
     * @param tradeId 交易编号
     * @param revokeReason 撤销原因
     * @return 金融产品交易撤销数据
     * @see
     * @since 1.0
     */
    public List<TrdProductDeal> genTrdProductDealRevoke(String tradeId, String revokeReason) {
        List<TrdProductDeal> trdProductDealList = this.queryTrdProductDealByTotleTradeId(
            tradeId);
        if (!StringUtil.isEmptyList(trdProductDealList)) {
            for (TrdProductDeal trdProductDeal : trdProductDealList) {
                trdProductDeal.setpTradeId(trdProductDeal.getTradeId());
                trdProductDeal.setTradeId(StringUtil.defaultString(trdProductDeal.getTradeId()) + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                trdProductDeal.setTotleTradeId(StringUtil.defaultString(trdProductDeal.getTotleTradeId()) + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
                trdProductDeal.setCancelRemark(revokeReason);
                //add by wm将带R的交易的创建时间与更新时间修改为最新的系统时间 start  2019-09-02
                trdProductDeal.setCreateTime(DateUtil.now());
                trdProductDeal.setUpdateTime(DateUtil.now());
                //add by wm将带R的交易的创建时间与更新时间修改为最新的系统时间 end  2019-09-02

            }
        }

        return trdProductDealList;
    }

    /**
     * 生成金融产品交易附属撤销数据
     * 
     * @param tradeId 交易编号
     * @return 金融产品交易附属撤销数据
     * @see
     * @since 1.0
     */
    public List<TrdProductDealAdd> genTrdProductDealAddRevoke(String tradeId){
        List<TrdProductDealAdd> trdProductDealAddList = this.queryTrdProductDealAddByTotleTradeId(tradeId);
        if (!StringUtil.isEmptyList(trdProductDealAddList)) {
            for (TrdProductDealAdd trdProductDealAdd : trdProductDealAddList) {
                trdProductDealAdd.setTradeId(StringUtil.defaultString(trdProductDealAdd.getTradeId()) + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
            }
        }
        
        return trdProductDealAddList;
    }

    /**
     * 生成金融产品交易附属撤销数据
     * 
     * @param tradeId 交易编号
     * @return 金融产品交易附属撤销数据
     * @see
     * @since 1.0
     */
    public List<PaTrdProductDeal> genPaTrdProductDealRevoke(String tradeId){
        List<PaTrdProductDeal> paTrdProductDealList = this.queryPaTrdProductDealByTotleTradeId(tradeId);
        if (!StringUtil.isEmptyList(paTrdProductDealList)) {
            for (PaTrdProductDeal paTrdProductDeal : paTrdProductDealList) {
                paTrdProductDeal.setTradeId(StringUtil.defaultString(paTrdProductDeal.getTradeId()) + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
            }
        }
        
        return paTrdProductDealList;
    }
    
    /**
     * 生成组合交易撤销数据
     * 
     * @param tradeId 交易编号
     * @return 组合交易撤销数据
     * @see
     * @since 1.0
     */
    public List<PtlTrade> genPtlTradeRevoke(String tradeId) {
        List<PtlTrade> ptlTradeList = restTemplate.getForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/queryPtlTradeByTotleTradeId?tradeId="
                                                              + tradeId, PtlTrade.class);
        if (!StringUtil.isEmptyList(ptlTradeList)) {
            for (PtlTrade ptlTrade : ptlTradeList) {
                ptlTrade.setBusiNo(StringUtil.defaultString(ptlTrade.getBusiNo()) + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
            }
        }

        return ptlTradeList;
    }
    
    /**
     * 生成理财分行销售明细撤销数据
     * 
     * @param tradeId 交易编号
     * @return 理财分行销售明细撤销数据
     * @see
     * @since 1.0
     */
    public List<PaBranchSaleDetail> genPaBranchSaleDetailRevoke(String tradeId){
        List<PaBranchSaleDetail> paBranchSaleDetailList = this.queryPaBranchSaleDetailByTotleTradeId(tradeId);
        if (!StringUtil.isEmptyList(paBranchSaleDetailList)) {
            for (PaBranchSaleDetail paBranchSaleDetail : paBranchSaleDetailList) {
                paBranchSaleDetail.setTradeId(StringUtil.defaultString(paBranchSaleDetail.getTradeId()) + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
            }
        }
        
        return paBranchSaleDetailList;
    }
    
    public Page<SelectOptionDto> getUserName(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/getUserName", map,
        		SelectOptionDto.class);
    }
    
    public Page<SelectOptionDto> getUserName1(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/getUserName1", map,
        		SelectOptionDto.class);
    }
    
    /**
     * 需求发起人下拉框 yzg 20191024
     * @return
     */
    public Page<SelectOptionDto> getProductUserName(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/getProductUserName", map,
        		SelectOptionDto.class);
    }
    
    public Page<SelectOptionDto> getCustomerName(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/getCustomerName", map,
        		SelectOptionDto.class);
    }
    /**
     * 获取行内标识码
     * @param map 传入条件
     * @return
     */
    public List<SelectOptionDto> queryRepFinprodInsId(Map<String, Object> map) {
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/queryRepFinprodInsId", map,
        		SelectOptionDto.class);
    }
    /**
     * 查询机构
     *
     * @param dto dto
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryTrusteeId(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/queryTrusteeId", map,
                SelectOptionDto.class);
    }
}
