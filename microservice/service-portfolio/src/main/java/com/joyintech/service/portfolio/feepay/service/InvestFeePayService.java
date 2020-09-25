/**
 * 
 */
package com.joyintech.service.portfolio.feepay.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.feepay.dao.InvestFeePayMapper;
import com.joyintech.service.portfolio.feepay.dto.InvestFeePayDto;
import com.joyintech.service.portfolio.feepay.dto.InvestFeePayFeeDetailDto;
import com.joyintech.service.portfolio.feepay.dto.InvestFeePayPositionDetailDto;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.TrdCommonParam;
import com.joyintech.tams.invest.common.dto.common.TrdCommonWflParam;
import com.joyintech.tams.invest.common.product.dto.FinProductExpireBaseTrdDto;
import com.joyintech.tams.invest.common.service.MapperDelegateService;

/**
 * @author malinlin
 */
@Service("backInvestFeePayService")
public class InvestFeePayService extends BaseService<InvestFeePayDto> {

    /**
     * {@linkplain}
     */
    @Autowired
    private InvestFeePayMapper investFeePayMapper;

    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;

    /**
     * {@linkplain}
     */
    @Autowired
    private PtlPortfolioMapper ptlPortfolioMapper;

    /**
     * {@linkplain}
     */
    @Autowired
    private MstFeeinfoMapper mstFeeinfoMapper;

    /**
     * {@linkplain}
     */
    @Autowired
    private MapperDelegateService mapperDelegateService;

    /**
     * {@linkplain}
     */
    @Autowired
    private PaTrdProductDealMapper paTrdProductDealMapper;
    /**
     * {@linkplain}
     */
    @Autowired
    private StlPayOrdersMapper stlPayOrdersMapper;
    
    /**
     * {@linkplain}
     */
    @Autowired
    private PtlTradeMapper ptlTradeMapper;
    
    /**
     * {@linkplain}
     */
    @Autowired
    private TrdProductDealMapper trdProductDealMapper;
    
    /**
     * {@linkplain}
     */
    @Autowired
    private TrdProductDealAddMapper trdProductDealAddMapper;


    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * 
     * @param page page
     * @param length length
     * @param map map
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<InvestFeePayDto> selectForPage(int page, int length, Map<String, Object> map)
        throws Exception {
        map.put("pendingStatus", DictCodeListExt1.有效状态_流程中.getValue());
        map.put("effectStatus", DictCodeListExt1.有效状态_有效.getValue());
        map.put("cancleStatus", DictCodeListExt1.有效状态_撤销.getValue());
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("chargeType02", DictCodeList.收费类型_不规则费用.getValue());
        map.put("chargeType", DictCodeList.收费类型_计提式费用.getValue());
        map.put("profitType", DictCodeList.收益类型_收益型.getValue());
        map.put("platDate", iPlatformDate.getSysDate());
        map.put("noFlag", DictCodeList.是否_否.getValue());
        map.put("yesFlag", DictCodeList.是否_是.getValue());
        map.put("ps", DictCodeList.交易方向_产品付费.getValue());
        map.put("payCycle", DictCodeList.频率_不规则.getValue());
        map.put("isClean", DictCodeList.是否_否.getValue());
        map.put("TG_BUSI_TYPE", "142");
        map.put("feeType06", DictCodeList.费用品种类型_交易手续费.getValue());
        map.put("feeType07", DictCodeList.费用品种类型_结算服务费.getValue());
        map.put("feeType14", DictCodeList.费用品种类型_佣金.getValue());
        return super.queryForPage(page, length, () -> investFeePayMapper.selectForPage(map));
    }
    
    /**
     * 证券账户联想文本框service层实现
     *
     * @param param String
     * @return SuggestDto
     */
    public List<SuggestDto> getSecAcctId(String param) {
        return investFeePayMapper.getSecAcctId(param);
    }

    /**
     * 查询证券账户下产品关联的费用（不包含已有付费计划）
     * 
     * @param map 参数
     * @return 费用信息
     */
    public List<SelectOptionDto> queryFeeInfoList(Map<String, Object> map) {
        map.put("relationProd", DictCodeList.关联范围_产品.getValue());
        map.put("relationOther", DictCodeList.关联范围_其他.getValue());
        map.put("feeTypeFloat", DictCodeList.费用品种类型_浮动差额中收.getValue());
        map.put("chargeType_01", DictCodeList.收费类型_计提式费用.getValue());
        map.put("chargeType_02", DictCodeList.收费类型_不规则费用.getValue());
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("cashType", DictCodeList.现金流类型_费用支付.getValue());
        map.put("yes", DictCodeList.是否_是.getValue());
        map.put("feeType06", DictCodeList.费用品种类型_交易手续费.getValue());
        map.put("feeType07", DictCodeList.费用品种类型_结算服务费.getValue());
        map.put("feeType14", DictCodeList.费用品种类型_佣金.getValue());
        return investFeePayMapper.queryFeeInfoList(map);
    }

    /**
     * 查询证券账户下产品关联的费用
     * 
     * @param map 参数
     * @return 费用信息
     */
    public List<SelectOptionDto> queryFeeInfoListEx(Map<String, Object> map) {
        map.put("relationProd", DictCodeList.关联范围_产品.getValue());
        map.put("relationOther", DictCodeList.关联范围_其他.getValue());
        map.put("feeTypeFloat", DictCodeList.费用品种类型_浮动差额中收.getValue());
        map.put("chargeType01", DictCodeList.收费类型_计提式费用.getValue());
        map.put("chargeType02", DictCodeList.收费类型_不规则费用.getValue());
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("cashType", DictCodeList.现金流类型_费用支付.getValue());
        map.put("payCycle", DictCodeList.频率_不规则.getValue());
        map.put("feeType04", DictCodeList.费用品种类型_浮动差额中收.getValue());
        map.put("profitType02", DictCodeList.收益类型_收益型.getValue());
        map.put("yes", DictCodeList.是否_是.getValue());
        map.put("feeType06", DictCodeList.费用品种类型_交易手续费.getValue());
        map.put("feeType07", DictCodeList.费用品种类型_结算服务费.getValue());
        map.put("feeType14", DictCodeList.费用品种类型_佣金.getValue());
        return investFeePayMapper.queryFeeInfoListEx(map);
    }
    // /**
    // * 查询组合下产品关联的费用
    // * @param map 参数
    // * @return 费用信息
    // */
    // public List<SelectOptionDto> queryFeeInfoList(Map<String, Object> map){
    // map.put("cashType1_tax", DictCodeListExt1.现金流一级类型_税额.getValue());
    // map.put("cashType1_fee", DictCodeListExt1.现金流一级类型_费用.getValue());
    // map.put("relationProd", DictCodeList.关联范围_产品.getValue());
    // map.put("chargeType", DictCodeList.收费类型_不规则费用.getValue());
    // return investFeePayMapper.queryFeeInfoList(map);
    // }

    /**
     * 查询费用待支付费用
     * 
     * @param secAcctId 证券账户
     * @param feeType 费用类型
     * @param cdate 计提结束日
     * @param regTrustOrg 登记托管机构
     * @return 待支付费用
     */
    public BigDecimal queryUnpayPosition(String secAcctId, String feeType, String cdate, String regTrustOrg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("secAcctId", secAcctId);
        map.put("feeType", feeType);
        map.put("cdate", cdate);
        map.put("org", regTrustOrg);
        if (feeType != null && feeType.equals(DictCodeList.费用品种类型_结算服务费.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_结算手续费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_交易手续费.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_交易手续费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_佣金.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_佣金.getValue());
        }
        return investFeePayMapper.queryUnpayPosition(map);
    }

    public BigDecimal queryPayTradeAmt(String secAcctId, String org, String cdate, String feeType) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("secAcctId", secAcctId);
        map.put("org", org);
        map.put("cdate", cdate);
        map.put("feeType", feeType);
        map.put("noFlag", DictCodeList.是否_否.getValue());
        if (feeType != null && feeType.equals(DictCodeList.费用品种类型_结算服务费.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_结算手续费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_交易手续费.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_交易手续费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_佣金.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_佣金.getValue());
        }
        BigDecimal pay = investFeePayMapper.queryPayFeeAmtByPosition(map);
        BigDecimal payed = BigDecimal.ZERO;
//            investFeePayMapper.queryPayed(map); 
        if (pay != null && payed != null) {
            return pay.subtract(payed);
        }
        else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 查询是否有小于当前日期的待处理的计划
     * 
     * @param cashId 现金流代码
     * @param cashNum 现金流序号
     * @return 待处理的计划个数
     */
    public int checkUndoPlanExists(String cashId, String cashNum) {
        return investFeePayMapper.checkUndoPlanExists(cashId, cashNum);
    }

    /**
     * 根据现金流代码和到期日查询具体付费计划
     * 
     * @param map 参数
     * @return 付费计划
     */
    public InvestFeePayDto queryPendingFeeInfoByCashId(Map<String, Object> map) {
        map.put("pendingStatus", DictCodeListExt1.有效状态_流程中.getValue());
        map.put("effectStatus", DictCodeListExt1.有效状态_有效.getValue());
        map.put("issueStatus", DictCodeListExt1.发行状态_发行成功.getValue());
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("chargeType02", DictCodeList.收费类型_不规则费用.getValue());
        map.put("cashType2fix", DictCodeListExt1.现金流二级类型_固定管理费.getValue());
        map.put("cashType2trust", DictCodeListExt1.现金流二级类型_托管费.getValue());
        map.put("cashType2sale", DictCodeListExt1.现金流二级类型_销售费.getValue());
        map.put("cashType2float", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        map.put("cashType2wf", DictCodeListExt1.现金流二级类型_外包服务费.getValue());
        map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        map.put("chargeType", DictCodeList.收费类型_计提式费用.getValue());
        map.put("profitType", DictCodeList.收益类型_收益型.getValue());
        map.put("feeType04", DictCodeList.费用品种类型_浮动差额中收.getValue());
        map.put("platDate", iPlatformDate.getSysDate());
        map.put("noFlag", DictCodeList.是否_否.getValue());
        map.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
        map.put("ps", DictCodeList.交易方向_产品付费.getValue());
        map.put("acctType_trust", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        map.put("acctType1_fix", DictCodeListExt1.资金账户类型_产品管理费账户.getValue());
        map.put("acctType1_trust", DictCodeListExt1.资金账户类型_产品托管费账户.getValue());
        map.put("acctType1_sale", DictCodeListExt1.资金账户类型_产品销售费账户.getValue());
        map.put("isClean", DictCodeList.是否_否.getValue());
        map.put("businessType", DictCodeList.共通业务类型_产品费用支付管理.getValue());

        return investFeePayMapper.queryPendingFeeInfoByCashId(map);
    }

    /**
     * 查询待支付费用
     * 
     * @param feeType 费用类型
     * @param cdate 计提结束日
     * @param finprodId 金融产品代码
     * @param branch 分支序号
     * @return 待支付费用
     */
    public BigDecimal queryFeeAmtByFeeType(String feeType, String cdate, String finprodId, String branch) {
        return investFeePayMapper.queryFeeAmtByFeeType(feeType, cdate, finprodId, branch);
    }
    
    /**
     * 组合费用明细列表
     * @param secAcctId
     * @param org
     * @param cdate
     * @param feeType
     * @return
     * @see
     * @since 1.0
     */
    public List<InvestFeePayPositionDetailDto> queryPayFeeInfoList(String secAcctId, String org, String cdate, String feeType) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("secAcctId", secAcctId);
        map.put("org", org);
        map.put("cdate", cdate);
        map.put("feeType", feeType);
        if (feeType != null && feeType.equals(DictCodeList.费用品种类型_结算服务费.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_结算手续费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_交易手续费.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_交易手续费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_佣金.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_佣金.getValue());
        }
        
        List<InvestFeePayPositionDetailDto> feeList = investFeePayMapper.queryPayFeeInfoList(map);
        return feeList;
    }

    /**
     * 费用明细查看
     * @param feeType 费用类型
     * @param startDate 计提开始日
     * @param enddate 计提结束日
     * @param secAcctId 证券账户代码
     * @param ptlId 组合代码
     * @return 费用明细
     */
    public List<InvestFeePayPositionDetailDto> queryFeePositionDetail(String feeType, String startDate, String enddate, 
                                                                      String secAcctId, String ptlId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("feeType", feeType);
        map.put("startDate", startDate);
        map.put("enddate", enddate);
        map.put("secAcctId", secAcctId);
        map.put("ptlId", ptlId);
        map.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        map.put("IS_CANCEL_N", DictCodeListExt1.是否_否.getValue());
        map.put("FINPROD_TYPE2_C11", DictCodeListExt1.金融产品类型_交易费用.getValue());
        if (feeType != null && feeType.equals(DictCodeList.费用品种类型_结算服务费.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_结算手续费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_交易手续费.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_交易手续费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_佣金.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_佣金.getValue());
        }
        return investFeePayMapper.queryFeePositionDetail(map);
    }

    /**
     * 根据交易编号查询费用明细信息
     * 
     * @param tradeId 交易编号
     * @return 费用明细信息
     */
    public List<InvestFeePayFeeDetailDto> queryFeeDetailByTradeId(String tradeId) {
        return investFeePayMapper.queryFeeDetailByTradeId(tradeId);
    }

    /**
     * 根据交易编号查询组合交易
     * 
     * @param tradeId 交易编号
     * @return 组合交易
     */
    public List<PtlTrade> queryPtlTradeByTradeId(String tradeId) {
        return investFeePayMapper.queryPtlTradeByTradeId(tradeId);
    }

    /**
     * 根据交易编号查询费用明细信息
     * 
     * @param tradeId 交易编号
     * @return 费用明细信息
     */
    public List<TrdTradeFeeDetail> queryTrdTradeFeeDetailByTradeId(String tradeId) {
        return investFeePayMapper.queryTrdTradeFeeDetailByTradeId(tradeId);
    }

    /**
     * 根据交易编号查询费用明细信息
     * 
     * @param map 交易编号
     * @return 费用明细信息
     */
    public List<InvestFeePayFeeDetailDto> queryFeePositionByPtlId(Map<String, Object> map) {
        // map.put("profitType", DictCodeList.收益类型_收益型.getValue());
        map.put("cashType2_C04", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        return investFeePayMapper.queryFeePositionByPtlId(map);
    }

    /**
     * 根据交易编号查询费用明细信息(保护焊分层)
     * 
     * @param map 交易编号
     * @return 费用明细信息
     */
    public List<InvestFeePayFeeDetailDto> queryFeePositionByPtlLayeredId(Map<String, Object> map) {
        map.put("profitType", DictCodeList.收益类型_收益型.getValue());
        map.put("cashType2_C04", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        map.put("ps", DictCodeList.交易方向_产品付费.getValue());
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("isCancel", DictCodeList.是否_否.getValue());
        map.put("chargeType", DictCodeList.收费类型_计提式费用.getValue());
        map.put("payCycle", DictCodeList.频率_不规则.getValue());

        List<InvestFeePayFeeDetailDto> list = investFeePayMapper.queryFeePositionByPtlLayeredId(map);

        return list;
    }

    /**
     * 获取产品的规则类型
     * 
     * @param map 交易编号
     * @return 费用明细信息
     */
    public String queryPayCycleByCashId(Map<String, Object> map) {
        String payCycle = investFeePayMapper.queryPayCycleByCashId(map);

        return payCycle;
    }

    /**
     * 根据交易编号查询费用明细信息
     * 
     * @param map 交易编号
     * @return 费用明细信息
     */
    public List<InvestFeePayFeeDetailDto> queryFeePositionByPtlId1(Map<String, Object> map) {
        // map.put("profitType", DictCodeList.收益类型_收益型.getValue());
        map.put("cashType2_C04", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        map.put("payCycle", DictCodeList.频率_不规则.getValue());
        // TODO
        map.put("ps", DictCodeList.交易方向_产品付费.getValue());
        map.put("chargeType", DictCodeList.收费类型_计提式费用.getValue());
        map.put("isCancel", DictCodeList.是否_否.getValue());
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        return investFeePayMapper.queryFeePositionByPtlId(map);
    }

    /**
     * 查询费用上次计提结束日
     * 
     * @param ptlId 组合代码
     * @param feeType 费用类型
     * @return 上次计提结束日
     */
    public String queryLastPayDate(String secAcctId, String feeType) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isCancel", DictCodeList.是否_否.getValue());
        map.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
        map.put("secAcctId", secAcctId);
        map.put("feeType", feeType);
        map.put("payCycle", DictCodeList.频率_不规则.getValue());
        map.put("flag", DictCodeList.是否_否.getValue());
        map.put("cdate", DateUtil.getSysDate());
        if (feeType != null && feeType.equals(DictCodeList.费用品种类型_结算服务费.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_结算手续费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_交易手续费.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_交易手续费.getValue());
        } else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_佣金.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_佣金.getValue());
        }
        String lastPayDate = investFeePayMapper.queryLastPayDate(map);
        return lastPayDate;
    }

    /**
     * 查询累计未结清费用
     * 
     * @param ptlId 组合代码
     * @param feeId 费用代码
     * @param payDate 支付日期
     * @param finprodId 金融产品代码
     * @return 累计未结清费用
     */
    public BigDecimal queryUnCleanFee(String ptlId, String feeId, String payDate, String finprodId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("noFlag", DictCodeList.是否_否.getValue());
        map.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
        map.put("ptlId", ptlId);
        map.put("feeId", feeId);
        map.put("finprodId", finprodId);
        map.put("payDate", payDate);
        map.put("isClean", DictCodeList.是否_否.getValue());
        return investFeePayMapper.queryUnCleanFee(map);
    }

    /**
     * 查询 计提开始日
     * 
     * @param finprodId 金融产品代码
     * @param cdate 计算日
     * @return 累计未结清费用
     */
    public Date queryAccrStrDate(String finprodId, String cdate) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("cdate", cdate);
        map.put("CASH_TYPE_1_03", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("CASH_TYPE_2_C04", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        return investFeePayMapper.queryAccrStrDate(map);
    }

    /**
     * 查询产品剩余现金
     * 
     * @param ptlId 组合代码
     * @param payDate 支付日期
     * @return 剩余现金
     */
    public BigDecimal calResiPrincipal(String ptlId, String payDate) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ptlId", ptlId);
        map.put("payDate", payDate);
        return investFeePayMapper.calResiPrincipal(map);
    }

    /**
     * 根据产品代码费用Id查现金流二级类型
     * 
     * @param finprodId 现金流代码
     * @param feeId 现金流序号
     * @return 待处理的计划个数
     */
    public String selectCashType2(String finprodId, String feeId) {
        return investFeePayMapper.selectCashType2(finprodId, feeId);
    }

    /**
     * 根据产品代码费用Id查现金流二级类型(分层)
     * 
     * @param finprodId 现金流代码
     * @param feeId 现金流序号
     * @return 待处理的计划个数
     */
    public String selectLayyerdCashType2(String finprodId, String feeId) {
        return investFeePayMapper.selectLayyerdCashType2(finprodId, feeId);
    }

    public Integer checkpayAmt(Map<String, Object> map) {
        /*
         * map.put("pendingStatus", DictCodeListExt1.有效状态_待处理.getValue()); map.put("effectStatus", DictCodeListExt1.有效状态_有效.getValue());
         * map.put("issueStatus", DictCodeListExt1.发行状态_发行成功.getValue()); map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
         * map.put("chargeType02", DictCodeList.收费类型_不规则费用.getValue()); map.put("cashType2fix", DictCodeListExt1.现金流二级类型_固定管理费.getValue());
         * map.put("cashType2trust", DictCodeListExt1.现金流二级类型_托管费.getValue()); map.put("cashType2sale", DictCodeListExt1.现金流二级类型_销售费.getValue());
         * map.put("cashType2float", DictCodeListExt1.现金流二级类型_浮动管理费.getValue()); map.put("cashType2wf", DictCodeListExt1.现金流二级类型_外包服务费.getValue());
         * map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue()); map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
         * map.put("chargeType", DictCodeList.收费类型_计提式费用.getValue()); map.put("profitType", DictCodeList.收益类型_收益型.getValue()); map.put("feeType04",
         * DictCodeList.费用品种类型_浮动差额中收.getValue()); map.put("platDate", iPlatformDate.getSysDate()); map.put("noFlag", DictCodeList.是否_否.getValue());
         * map.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue()); map.put("ps", DictCodeList.交易方向_产品付费.getValue()); map.put("acctType_trust",
         * DictCodeListExt1.资金账户类型_产品托管户.getValue()); map.put("acctType1_fix", DictCodeListExt1.资金账户类型_产品管理费账户.getValue()); map.put("acctType1_trust",
         * DictCodeListExt1.资金账户类型_产品托管费账户.getValue()); map.put("acctType1_sale", DictCodeListExt1.资金账户类型_产品销售费账户.getValue()); map.put("isClean",
         * DictCodeList.是否_否.getValue()); map.put("relationProd", DictCodeList.关联范围_产品.getValue()); map.put("relationOther", DictCodeList.关联范围_其他.getValue());
         * map.put("feeTypeFloat", DictCodeList.费用品种类型_浮动差额中收.getValue()); map.put("chargeType01", DictCodeList.收费类型_计提式费用.getValue());
         * map.put("chargeType02", DictCodeList.收费类型_不规则费用.getValue()); map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue()); map.put("cashType",
         * DictCodeList.现金流类型_费用支付.getValue()); map.put("payCycle", DictCodeList.频率_不规则.getValue()); map.put("feeType04",
         * DictCodeList.费用品种类型_浮动差额中收.getValue()); map.put("profitType02", DictCodeList.收益类型_收益型.getValue());
         */
        map.put("chargeType_01", DictCodeList.收费类型_计提式费用.getValue());
        map.put("ps_C11", DictCodeList.交易方向_产品付费.getValue());
        map.put("noFlag", DictCodeList.是否_否.getValue());
        map.put("tradeStatus_01", DictCodeListExt1.交易状态_初始.getValue());
        map.put("feeType_03", DictCodeList.费用品种类型_固定管理费.getValue());
        map.put("feeType_04", DictCodeList.费用品种类型_浮动差额中收.getValue());
        map.put("feeType_12", DictCodeList.费用品种类型_浮动管理费.getValue());
        return investFeePayMapper.checkpayAmt(map);
    }

    public Integer checkpayAmtrule(Map<String, Object> map) {
        map.put("pendingStatus", DictCodeListExt1.有效状态_待处理.getValue());
        map.put("effectStatus", DictCodeListExt1.有效状态_有效.getValue());
        map.put("issueStatus", DictCodeListExt1.发行状态_发行成功.getValue());
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("chargeType02", DictCodeList.收费类型_不规则费用.getValue());
        map.put("cashType2fix", DictCodeListExt1.现金流二级类型_固定管理费.getValue());
        map.put("cashType2trust", DictCodeListExt1.现金流二级类型_托管费.getValue());
        map.put("cashType2sale", DictCodeListExt1.现金流二级类型_销售费.getValue());
        map.put("cashType2float", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        map.put("cashType2wf", DictCodeListExt1.现金流二级类型_外包服务费.getValue());
        map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        map.put("chargeType", DictCodeList.收费类型_计提式费用.getValue());
        map.put("profitType", DictCodeList.收益类型_收益型.getValue());
        map.put("feeType04", DictCodeList.费用品种类型_浮动差额中收.getValue());
        map.put("platDate", iPlatformDate.getSysDate());
        map.put("noFlag", DictCodeList.是否_否.getValue());
        map.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
        map.put("ps", DictCodeList.交易方向_产品付费.getValue());
        map.put("acctType_trust", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        map.put("acctType1_fix", DictCodeListExt1.资金账户类型_产品管理费账户.getValue());
        map.put("acctType1_trust", DictCodeListExt1.资金账户类型_产品托管费账户.getValue());
        map.put("acctType1_sale", DictCodeListExt1.资金账户类型_产品销售费账户.getValue());
        map.put("isClean", DictCodeList.是否_否.getValue());
        map.put("relationProd", DictCodeList.关联范围_产品.getValue());
        map.put("relationOther", DictCodeList.关联范围_其他.getValue());
        map.put("feeTypeFloat", DictCodeList.费用品种类型_浮动差额中收.getValue());
        map.put("chargeType01", DictCodeList.收费类型_计提式费用.getValue());
        map.put("chargeType02", DictCodeList.收费类型_不规则费用.getValue());
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("cashType", DictCodeList.现金流类型_费用支付.getValue());
        map.put("payCycle", DictCodeList.频率_不规则.getValue());
        map.put("feeType04", DictCodeList.费用品种类型_浮动差额中收.getValue());
        map.put("profitType02", DictCodeList.收益类型_收益型.getValue());
        map.put("feeType_03", DictCodeList.费用品种类型_固定管理费.getValue());
        map.put("feeType_04", DictCodeList.费用品种类型_浮动差额中收.getValue());
        map.put("feeType_12", DictCodeList.费用品种类型_浮动管理费.getValue());
        return investFeePayMapper.checkpayAmtrule(map);
    }

    public InvestFeePayDto selectFeeByTradeId(Map<String, Object> map) {
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        return investFeePayMapper.selectFeeByTradeId(map);
    }

    /**
     * 新建产品费用支付时，根据费用id找到fin_cash_info的cash_id <功能详细描述>
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public String selectCashId(Map<String, Object> map) {
        return investFeePayMapper.selectCashId(map);
    }

    /**
     * 查询费用信息 <功能详细描述>
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public MstFeeinfo selectFeeId(Map<String, Object> map) {
        return mstFeeinfoMapper.selectByPrimaryKey((String)map.get("feeId"));
    }

    /**
     * 查询产品分层产品
     * 
     * @param finprodId 母产品id
     * @return 子产品列表
     */
    public List<PrdLayerParam> queryPrdLayerListByPid(String finprodId) {
        return investFeePayMapper.queryPrdLayerListByPid(finprodId);
    }

    public String insertBussFlowData(TrdCommonWflParam trdCommonWflParam) {
        mapperDelegateService.insertAutoClassifyBatch(trdCommonWflParam.getWflStlPayOrdersDtos(),
            trdCommonWflParam.getWflTrdFinProductDealTotal(), trdCommonWflParam.getWflPaTrdFinDealTotal(),
            trdCommonWflParam.getWflTrdProductDeals(), trdCommonWflParam.getWflDealAdds(), trdCommonWflParam.getWflPaDeals());
        return "Success";
    }

    public String doComplete(TrdCommonParam trdCommonParam) {
        mapperDelegateService.insertAutoClassifyBatch(trdCommonParam.getTrdTradeFeeDetails(), trdCommonParam.getPtlTrades(),
            trdCommonParam.getTrdFinProductDealTotal());
        return "Success";
    }

    public String insertBatchData(FinProductExpireBaseTrdDto expireBaseTrdDto) {
        mapperDelegateService.insertAutoClassifyBatch(expireBaseTrdDto.getTrdProductDealList(),
            expireBaseTrdDto.getTrdProductDealAddList(), expireBaseTrdDto.getPaTrdProductDealList(),
            expireBaseTrdDto.getTrdTradeFeeDetailList());
        return "Success";
    }

    public String doCompleteInsert(TrdCommonParam trdCommonParam) {
        mapperDelegateService.insertAutoClassifyBatch(trdCommonParam.getPaTrdFinProductDealTotal(),
            trdCommonParam.getTrdProductDeals(), trdCommonParam.getDealAdds(), trdCommonParam.getPaDeals(),
            trdCommonParam.getTrdTradeFeeDetails(), trdCommonParam.getPtlTrades(), trdCommonParam.getTrdFinProductDealTotal(),
            trdCommonParam.getStlPayOrdersDtos());
        return "Success";
    }

    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;

    @Autowired
    private PaTrdFinProductDealTotalMapper paTrdFinProductDealTotalMapper;

    public String doCompleteRevoke(InvestFeePayDto entity) {
        // 根据汇总交易编号查询金融产品交易汇总
        TrdFinProductDealTotal trdFinProductDealTotal = trdFinProductDealTotalMapper.selectByPrimaryKey(entity.getTradeId());

        // 原始汇总交易的状态改为已撤销
        TrdFinProductDealTotal trdFinProductDealTotalRevoke = new TrdFinProductDealTotal();
        trdFinProductDealTotalRevoke.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
        trdFinProductDealTotalRevoke.setIsCancel(DictCodeList.是否_是.getValue());
        trdFinProductDealTotalMapper.updateByPrimaryKeySelective(trdFinProductDealTotalRevoke);
        // 原始交易的状态改为已撤销

        trdFinProductDealTotal.setpTradeId(trdFinProductDealTotal.getTotleTradeId());
        trdFinProductDealTotal.setTotleTradeId(
            trdFinProductDealTotal.getTotleTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
        trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
        TrdFinProductDealTotal trdFinProductDealTotalR = trdFinProductDealTotalMapper.selectByPrimaryKey(trdFinProductDealTotal.getTotleTradeId());
        if (StringUtil.isNotEmpty(trdFinProductDealTotalR)) {
            trdFinProductDealTotalMapper.updateByPrimaryKey(trdFinProductDealTotal); 
        }else {
            trdFinProductDealTotalMapper.insert(trdFinProductDealTotal);
        }
        

        PaTrdFinProductDealTotal patrdFinProductDealTotal = paTrdFinProductDealTotalMapper.selectByPrimaryKey(entity.getTradeId());

        // 原始汇总交易的状态改为已撤销
        if (patrdFinProductDealTotal != null) {
            patrdFinProductDealTotal.setTotleTradeId(
                patrdFinProductDealTotal.getTotleTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
            paTrdFinProductDealTotalMapper.insert(patrdFinProductDealTotal);
        }

        // 根据汇总交易编号查询金融产品交易
        List<TrdProductDeal> trdProductDealList = investFeePayMapper.queryTrdProductDealByTotleTradeId(entity.getTradeId());

        if (!trdProductDealList.isEmpty()) {
            for (TrdProductDeal trdProductDeal : trdProductDealList) {
                /**金融产品交易表**/
                TrdProductDeal trdProductDealRevoke = new TrdProductDeal();
                trdProductDealRevoke.setTradeId(trdProductDeal.getTradeId());
                trdProductDealRevoke.setIsCancel(DictCodeList.是否_是.getValue());
                trdProductDealMapper.updateByPrimaryKeySelective(trdProductDealRevoke);
                
                trdProductDeal.setpTradeId(trdProductDeal.getTradeId());
                trdProductDeal.setTradeId(trdProductDeal.getTradeId()+BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                trdProductDeal.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
                trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
                trdProductDealMapper.insert(trdProductDeal);

                /**金融产品扩展表**/
                PaTrdProductDeal paTrdProductDeal = paTrdProductDealMapper.selectByPrimaryKey(trdProductDeal.getTradeId());
                if (paTrdProductDeal != null) {
                    paTrdProductDeal.setTradeId(
                        paTrdProductDeal.getTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                    paTrdProductDealMapper.insert(paTrdProductDeal);
                }
                
                /**交易附属表 **/
                TrdProductDealAdd trdProductDealAdd = trdProductDealAddMapper.selectByPrimaryKey(trdProductDeal.getTradeId());
                if (trdProductDealAdd != null) {
                    trdProductDealAdd.setTradeId(trdProductDealAdd.getTradeId()
                                                + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                    trdProductDealAddMapper.insert(trdProductDealAdd);
                }
            }
        }

        // 划款指令表
        List<StlPayOrders> stlPayOrdersList = investFeePayMapper.queryStlPayOrdersByTradeId(entity.getTradeId());

        if (!stlPayOrdersList.isEmpty()) {
            for (StlPayOrders stlPayOrders : stlPayOrdersList) {
                stlPayOrders.setOrderId(stlPayOrders.getOrderId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                stlPayOrders.setpTradeId(stlPayOrders.getOrderId());
                stlPayOrders.setTradeId(entity.getTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                stlPayOrders.setOrderStatus(DictCodeListExt1.指令状态_撤销.getValue());
                
                stlPayOrdersMapper.insert(stlPayOrders);
            }
        }

        // 组合交易表
        List<PtlTrade> ptlTradeList = this.queryPtlTradeByTradeId(entity.getTradeId());
        if (!ptlTradeList.isEmpty()) {
            for (PtlTrade ptlTrade : ptlTradeList) {
                ptlTrade.setBusiNo(ptlTrade.getBusiNo() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                ptlTrade.setCreateUser(entity.getCreateUser());
                ptlTrade.setCreateTime(new Date());
                ptlTrade.setUpdateUser(entity.getCreateUser());
                ptlTrade.setUpdateTime(new Date());
                ptlTradeMapper.insert(ptlTrade);
            }
        }

        return "Success";
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
        return investFeePayMapper.selectInFlowDataCount(tradeId) > 0;
    }

    /**
     * 根据交易编号查询该交易是否已被撤销
     *
     * @param tradeId 交易编号
     * @return 该交易是否已被撤销
     * @see
     * @since 1.0
     */
    public Boolean isAlreadyRevoked(String tradeId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tradeId", tradeId);
        paramMap.put("YES_NO_YES", DictCodeList.是否_是.getValue());
        paramMap.put("TRADE_STATUS_03", DictCodeListExt1.交易流程状态_撤销.getValue());
        return investFeePayMapper.selectRevokedDataCount(paramMap) > 0;
    }
    
    /** 根据汇总交易编号划款指令信息
     * @param tradeId 汇总交易编号
     * @return 划款指令信息
     */
    public List<StlPayOrders> queryStlPayOrdersByTradeId(String tradeId){
        return investFeePayMapper.queryStlPayOrdersByTradeId(tradeId);
    }
    
    /**
     * 查询证券账户登记托管机构
     * @param secAcctId 证券账户
     * @return 登记托管机构
     * @see
     * @since 1.0
     */
    public String querySecAcctOrg(String secAcctId) {
        return investFeePayMapper.querySecAcctOrg(secAcctId);
    }
    
    /**
     * 根据汇总交易编号查询金融产品交易
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    public List<TrdProductDeal> queryTrdProductDealByTotleTradeId(String tradeId) {
        // 根据汇总交易编号查询金融产品交易
        return investFeePayMapper.queryTrdProductDealByTotleTradeId(tradeId);
    }
    
    /**
     * 根据组合id查询产品信息
     * @param ptlId 组合id
     * @return 产品信息
     * @see
     * @since 1.0
     */
    public InvestFeePayDto queryByPtlId(String ptlId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ptlId", ptlId);
        return investFeePayMapper.queryByPtlId(map);
    }
    
}
