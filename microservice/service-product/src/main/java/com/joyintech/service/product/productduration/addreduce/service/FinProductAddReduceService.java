/*
 * 文件名：FinProductSchedulingService.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：zhangliang 修改时间：2018年9月13日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.productduration.addreduce.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.common.base.dao.PaTrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.TrdFinProductDealTotalMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.PaTrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.TrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.productduration.addreduce.dao.FinProductAddReduceMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.product.addreduce.*;

/**
 * 产品存续期-强增强减
 *
 * @author 强成西
 * @version 1.0 2018年9月13日
 * @see FinProductAddReduceService
 * @since 1.0
 */
@Service("backFinProductAddReduceService")
public class FinProductAddReduceService extends BaseService<FinProductAddReduceDto> {

    @Autowired
    private FinProductAddReduceMapper finProductAddReduceMapper;

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     *
     * @param pageParam pageParam
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    @Transactional(readOnly = true)
    public Page<FinProductAddReduceDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
//        pageParam.put("N", DictCodeList.是否_否.getValue());
        pageParam.put("发行成功", DictCodeListExt1.发行状态_发行成功.getValue());
        pageParam.put("FINPROD15", DictCodeList.共通业务类型_收益型产品提前赎回管理.getValue());

        if (com.joyintech.framework.util.StringUtil.isNotEmpty(pageParam.get("prodId"))) {
            pageParam.put("prodId", com.joyintech.framework.util.StringUtil.splitToList(",", (String)pageParam.get("prodId")));
        }
        if (com.joyintech.framework.util.StringUtil.isNotEmpty(pageParam.get("dealType"))) {
            pageParam.put("dealType", com.joyintech.framework.util.StringUtil.splitToList(",", (String)pageParam.get("dealType")));
        }
        return super.queryForPage(page, length, () -> finProductAddReduceMapper.selectForPage(pageParam));
    }

    @Transactional(readOnly = true)
    public Page<SelectOptionDto> getProdId(int page, int length, Map<String, Object> pageParam) {
        pageParam.put("ISSUE_STATUS01", DictCodeListExt1.发行状态_发行成功.getValue());
        pageParam.put("dealType11", DictCodeList.处理模式_分档.getValue());
        pageParam.put("dealType12", DictCodeList.处理模式_分档周期型.getValue());
        pageParam.put("dealType10", DictCodeList.处理模式_周期型.getValue());
        pageParam.put("dealType01", DictCodeList.处理模式_普通.getValue());
        //add by zhujun @2019-05-16 兼容净值型产品 强增强减
        /*pageParam.put("profitType", DictCodeList.收益类型_收益型.getValue());*/
        return queryForPageForSelectReturn(page, length, () -> finProductAddReduceMapper.getProdId(pageParam));
    }

    @Transactional(readOnly = true)
    @DataSource(DataSourceType.OTHER)
    public String getSaleDepartment(Map<String, Object> pageParam) {
        return finProductAddReduceMapper.getSaleDepartment(pageParam);
    }

    @Transactional(readOnly = true)
    public BigDecimal getValueByDate(Map<String, Object> param) {
        return finProductAddReduceMapper.getValueByDate(param);
    }
    @Transactional(readOnly = true)
    public BigDecimal getProductAmount(Map<String, Object> param) {
        return finProductAddReduceMapper.getProductAmount(param);
    }

    @Transactional(readOnly = true)
    public ProductAddReduceBaseDto selectByTotalTradeId(Map<String, Object> pageParam) {
//        pageParam.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
//        pageParam.put("NO", DictCodeList.是否_否.getValue());
        pageParam.put("C17", DictCodeList.交易方向_产品提前赎回.getValue());
        pageParam.put("C18", DictCodeList.交易方向_产品违约赎回.getValue());
        pageParam.put("C15", DictCodeList.交易方向_赎回收益确认.getValue());
        pageParam.put("C11", DictCodeList.交易方向_产品付费.getValue());
        pageParam.put("B18", DictCodeListExt1.一次性费用类型_违约金.getValue());
        return finProductAddReduceMapper.selectByTotalTradeId(pageParam);
    }

    public List<ReduceInfoDto> findReduceInfoData(Map<String, Object> param) {
        setParams(param);
        
        param.put("C4", DictCodeList.交易方向_产品赎回.getValue());
        param.put("C5", DictCodeList.交易方向_产品到期.getValue());
        //支持分层产品付息 mod by menghongwei 20190610 start
        //List<ReduceInfoDto> result = finProductAddReduceMapper.findReduceInfoData(param);
        List<ReduceInfoDto> result = finProductAddReduceMapper.findReduceInfoDataNew(param);
        if(result!=null && result.size()>0){
            //部分赎回：判断赎回/到期日=付息日 且 赎回/到期日！=到期日 时应付客户收益=0
            Map<String, Object> tempparams = new HashMap<>();
            tempparams.put("finprodId", param.get("finprodId"));
            tempparams.put("tradeDate", param.get("tradeDate"));
            int count = finProductAddReduceMapper.getRePayAndIntpFlag(tempparams);
            if(count>0){
                result.get(0).setRePayAndIntpFlag(true);
            }else{
                result.get(0).setRePayAndIntpFlag(false);
            }
        }
        //支持分层产品付息 mod by menghongwei 20190610 end
        result.forEach(reduceInfoDto -> {
            reduceInfoDto.setActInterest(reduceInfoDto.getInterest());
        }); 
        return result;
    }

    private void setParams(Map<String, Object> param) {
        param.put("CT_02", DictCodeListExt1.现金流一级类型_利息.getValue());
        param.put("C02", DictCodeListExt1.现金流二级类型_利息.getValue());
        param.put("YES", DictCodeList.是否_是.getValue());
        param.put("NO", DictCodeList.是否_否.getValue());
        param.put("A_365", DictCodeList.计息基础_A_365.getValue());
        param.put("A_360", DictCodeList.计息基础_A_360.getValue());
        param.put("FINPROD19", DictCodeList.共通业务类型_产品费用支付管理.getValue());
        param.put("C11", DictCodeList.交易方向_产品付费.getValue());
        param.put("C15", DictCodeList.交易方向_赎回收益确认.getValue());
        param.put("C7", DictCodeList.交易方向_中间付息.getValue());
        param.put("CT_03", DictCodeListExt1.现金流一级类型_费用.getValue());
        param.put("C04", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        param.put("HT03", DictCodeList.持仓类型_实际.getValue());
        param.put("ISSUE_MODE_02", DictCodeList.开放类型_封闭式.getValue());
        param.put("ISSUE_MODE_01", DictCodeList.开放类型_开放式.getValue());
    }

    public List<ProductFeeInfo> findProdFeeData(Map<String, Object> param) {
        setParams(param);
        List<ProductFeeInfo> result = finProductAddReduceMapper.findProdFeeData(param);
        result.forEach(reduceInfoDto -> {
            reduceInfoDto.setActFeeAmt(reduceInfoDto.getFeeAmt());
        });
        return result;
    }

    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;
    
    // 一体化对应etliu start
    @Autowired
    private TrdProductDealMapper trdProductDealMapper;
    // 一体化对应etliu end

    @Autowired
    private PaTrdFinProductDealTotalMapper paTrdFinProductDealTotalMapper;

    public ProductTradeInfoDto selectProductTradeInfo(String totalTradeId) {
        ProductTradeInfoDto baseDto = new ProductTradeInfoDto();
        baseDto.setTrdFinProductDealTotal( trdFinProductDealTotalMapper.selectByPrimaryKey(totalTradeId));
        baseDto.setPaTrdFinProductDealTotal(paTrdFinProductDealTotalMapper.selectByPrimaryKey(totalTradeId));
        baseDto.setTrdProductDeals(finProductAddReduceMapper.selectAllProductDeal(totalTradeId));
        
        // 一体化对应etliu start
        if (StringUtil.isEmptyList(baseDto.getTrdProductDeals())) {
            TrdProductDeal trdProductDeal = trdProductDealMapper.selectByPrimaryKey(totalTradeId);
            if (trdProductDeal != null) {
                baseDto.setTrdProductDeals(Arrays.asList(trdProductDeal));
            }
        }
        // 一体化对应etliu end
        
        baseDto.setTrdProductDealAdds(finProductAddReduceMapper.selectAllDealAdds(baseDto.getTrdProductDeals()));
        baseDto.setPaTrdProductDeals(finProductAddReduceMapper.selectAllPaDeals(baseDto.getTrdProductDeals()));
        baseDto.setStlPayOrders(finProductAddReduceMapper.selectAllOrders(totalTradeId));
        return baseDto;
    }
    
    @Transactional(readOnly = true)
    public Page<SelectOptionDto> getProdId01(int page, int length, Map<String, Object> pageParam) {
        pageParam.put("ISSUE_STATUS01", DictCodeListExt1.发行状态_发行成功.getValue());
        pageParam.put("dealType11", DictCodeList.处理模式_分档.getValue());
        pageParam.put("dealType12", DictCodeList.处理模式_分档周期型.getValue());
        pageParam.put("dealType10", DictCodeList.处理模式_周期型.getValue());
        pageParam.put("dealType01", DictCodeList.处理模式_普通.getValue());
        pageParam.put("PROFIT_TYPE", DictCodeList.收益类型_收益型.getValue());
        pageParam.put("FINPROD_TYPE", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        return queryForPageForSelectReturn(page, length, () -> finProductAddReduceMapper.getProdId01(pageParam));
    }
}
