/**
 * 
 */
package com.joyintech.service.product.feepay.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.product.common.dao.ProductCommonMapper;
import com.joyintech.service.product.feepay.dao.FinProductFeePayMapper;
import com.joyintech.service.product.feepay.dto.FinProductFeePayDto;
import com.joyintech.service.product.feepay.dto.FinProductFeePayFeeDetailDto;
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
 * @author zhangliang
 */
@Service("backFinProductFeePayService")
public class FinProductFeePayService extends BaseService<FinProductFeePayDto> {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductFeePayMapper finProductFeePayMapper;

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
    public Page<FinProductFeePayDto> selectForPage(int page, int length, Map<String, Object> map)
        throws Exception {
        map.put("pendingStatus", DictCodeListExt1.有效状态_待处理.getValue());
        map.put("effectStatus", DictCodeListExt1.有效状态_有效.getValue());
        map.put("issueStatus", DictCodeListExt1.发行状态_发行成功.getValue());
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("chargeType02", DictCodeList.收费类型_不规则费用.getValue());
        map.put("cashType2fix", DictCodeListExt1.现金流二级类型_固定管理费.getValue());
        map.put("cashType2trust", DictCodeListExt1.现金流二级类型_托管费.getValue());
        map.put("cashType2invest", DictCodeListExt2.现金流二级类型_投资顾问费.getValue());
        map.put("cashType2sale", DictCodeListExt1.现金流二级类型_销售费.getValue());
        map.put("cashType2float", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        map.put("cashType2wf", DictCodeListExt1.现金流二级类型_外包服务费.getValue());
        map.put("cashType2dx", DictCodeListExt1.现金流二级类型_代销费.getValue());
        map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        map.put("chargeType", DictCodeList.收费类型_计提式费用.getValue());
        map.put("profitType", DictCodeList.收益类型_收益型.getValue());
        map.put("platDate", iPlatformDate.getSysDate());
        map.put("noFlag", DictCodeList.是否_否.getValue());
        // map.put("feeTypeTax", DictCodeList.费用品种类型_增值附加税费.getValue());
        map.put("feeType04", DictCodeList.费用品种类型_浮动差额中收.getValue());
        map.put("feeType_settle", DictCodeList.费用品种类型_结算服务费.getValue());
        map.put("feeType_account", DictCodeList.费用品种类型_账户维护费.getValue());
        map.put("feeType_float", DictCodeList.费用品种类型_浮动管理费.getValue());
        map.put("feeType_trade", DictCodeList.费用品种类型_交易手续费.getValue());
        map.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
        map.put("ps", DictCodeList.交易方向_产品付费.getValue());
        map.put("acctType_trust", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        map.put("acctType1_fix", DictCodeListExt1.资金账户类型_产品管理费账户.getValue());
        map.put("acctType1_trust", DictCodeListExt1.资金账户类型_产品托管费账户.getValue());
        map.put("acctType1_sale", DictCodeListExt1.资金账户类型_产品销售费账户.getValue());
        map.put("payCycle", DictCodeList.频率_不规则.getValue());
        map.put("isClean", DictCodeList.是否_否.getValue());
        map.put("businessType", DictCodeList.共通业务类型_产品费用支付管理.getValue());
        return super.queryForPage(page, length, () -> finProductFeePayMapper.selectForPage(map));
    }

    /**
     * 查询组合下产品关联的费用（不包含已有付费计划）
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
        return finProductFeePayMapper.queryFeeInfoList(map);
    }

    /**
     * 查询组合下产品关联的费用
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
        return finProductFeePayMapper.queryFeeInfoListEx(map);
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
    // return finProductFeePayMapper.queryFeeInfoList(map);
    // }

    /**
     * 查询组合的产品及其募集币种
     * 
     * @param ptlId 组合代码
     * @return 募集币种
     * @see
     * @since 1.0
     */
    public FinProductFeePayDto queryByPtlId(String ptlId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ptlId", ptlId);
        return finProductFeePayMapper.queryByPtlId(map);
    }

    /**
     * 查询费用待支付费用
     * 
     * @param ptlId 组合代码
     * @param feeType 费用类型
     * @param cdate 计提结束日
     * @return 待支付费用
     */
    public BigDecimal queryUnpayPosition(String ptlId, String feeType, String cdate) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ptlId", ptlId);
        map.put("feeType", feeType);
        map.put("cdate", cdate);
        return finProductFeePayMapper.queryUnpayPosition(map);
    }

    /**
     * 查询增值税待支付费用
     * 
     * @param ptlId 组合代码
     * @param cdate 查询日
     * @return 增值税待支付费用
     */
    public List<PtlFeePosition> queryTaxUnpayPosition(String ptlId, String cdate) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ptlId", ptlId);
        map.put("cdate", cdate);

        // 一体化对应etliu start
        // map.put("feeType_ALT0102", DictCodeListExt2.费用类型_城建税.getValue());
        // map.put("feeType_ALT0202", DictCodeListExt2.费用类型_教育费.getValue());
        // map.put("feeType_ALT0302", DictCodeListExt2.费用类型_地方教育费.getValue());
        // map.put("feeType_TAX0102", DictCodeListExt2.费用类型_利息收入.getValue());
        // map.put("feeType_TAX0202", DictCodeListExt2.费用类型_红利收入.getValue());
        // map.put("feeType_TAX0302", DictCodeListExt2.费用类型_价差收入.getValue());
        map.put("feeType_ALT0102", DictCodeList.税类型_城建税.getValue());
        map.put("feeType_ALT0202", DictCodeList.税类型_教育费.getValue());
        map.put("feeType_ALT0302", DictCodeList.税类型_地方教育费.getValue());
        map.put("feeType_TAX0102", DictCodeList.税类型_利息收入.getValue());
        map.put("feeType_TAX0202", DictCodeList.税类型_红利收入.getValue());
        map.put("feeType_TAX0302", DictCodeList.税类型_价差收入.getValue());
        // 一体化对应etliu end

        return finProductFeePayMapper.queryTaxUnpayPosition(map);
    }

    public BigDecimal queryPayTradeAmt(String ptlId, String org, String cdate, String feeType) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ptlId", ptlId);
        map.put("org", org);
        map.put("cdate", cdate);
        map.put("feeType", feeType);
        //add by yzg 已付费用增加未撤销查询条件 start
        map.put("isCancel_NO",DictCodeList.是否_否.getValue());
        //add by yzg 已付费用增加未撤销查询条件 start
        if (feeType != null && "07".equals(feeType)) {
            map.put("feeType1", "B08");
        }
        else if (feeType != null && "08".equals(feeType)) {
            map.put("feeType1", "B13");
        }
        // add by wm 增加交易手续费查询 2019-09-05 start
        else if (feeType != null && feeType.equals(DictCodeList.费用品种类型_交易手续费.getValue())) {
            map.put("feeType1", DictCodeListExt1.一次性费用类型_交易手续费.getValue());
        }
        // add by wm 增加交易手续费查询 2019-09-05 end
        // modify by wm 改变获取方式，从头寸中获取应付费用 start 2019-08-31
        // BigDecimal pay = finProductFeePayMapper.queryPayTradeAmt(map);//new BigDecimal(0);
        BigDecimal pay = finProductFeePayMapper.queryPayFeeAmtByPosition(map);
        BigDecimal payed = finProductFeePayMapper.queryPayed(map); // new BigDecimal(0);
        // BigDecimal pledgePay = finProductFeePayMapper.querypledgePay(map);
        // modify by wm 改变获取方式，从头寸中获取应付费用 end 2019-08-31
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
        return finProductFeePayMapper.checkUndoPlanExists(cashId, cashNum);
    }

    /**
     * 根据现金流代码和到期日查询具体付费计划
     * 
     * @param map 参数
     * @return 付费计划
     */
    public FinProductFeePayDto queryPendingFeeInfoByCashId(Map<String, Object> map) {
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
        map.put("businessType", DictCodeList.共通业务类型_产品费用支付管理.getValue());

        return finProductFeePayMapper.queryPendingFeeInfoByCashId(map);
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
        return finProductFeePayMapper.queryFeeAmtByFeeType(feeType, cdate, finprodId, branch);
    }

    /**
     * 计提明细查看
     * 
     * @param feeType 费用类型
     * @param startDate 计提开始日
     * @param enddate 计提结束日
     * @param finprodId 金融产品代码
     * @return 计提明细
     */
    public List<BokPositionPrdfee> queryFeePositionDetail(String feeType, String startDate, String enddate, String finprodId,
                                                          String branch) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("feeType", feeType);
        map.put("startDate", startDate);
        map.put("enddate", enddate);
        map.put("finprodId", finprodId);
        map.put("profitType", DictCodeList.收益类型_收益型.getValue());
        map.put("branch", branch);
        map.put("feeTypeFloat", DictCodeList.费用品种类型_浮动管理费.getValue());
        return finProductFeePayMapper.queryFeePositionDetail(map);
    }

    /**
     * 根据交易编号查询增值税信息
     * 
     * @param tradeId 交易编号
     * @return 增值税信息
     */
    public List<TrdTradeFeeDetail> queryTaxDetailByTradeId(String tradeId) {
        return finProductFeePayMapper.queryTaxDetailByTradeId(tradeId);
    }

    /**
     * 根据交易编号查询费用明细信息
     * 
     * @param tradeId 交易编号
     * @return 费用明细信息
     */
    public List<FinProductFeePayFeeDetailDto> queryFeeDetailByTradeId(String tradeId) {
        List<FinProductFeePayFeeDetailDto> feeDetailList=finProductFeePayMapper.queryFeeDetailByTradeId(tradeId);
        for(FinProductFeePayFeeDetailDto feeDto:feeDetailList){
            BigDecimal feeAmt=queryUnCleanFee(feeDto.getFinprodMarketId(),feeDto.getFeeId(),DateUtil.format(feeDto.getPayDate()),
                    feeDto.getsFinprodId());
            feeDto.setUnclearAmt(feeAmt);
        }
        return feeDetailList;
    }

    /**
     * 根据交易编号查询组合交易
     * 
     * @param tradeId 交易编号
     * @return 组合交易
     */
    public PtlTrade queryPtlTradeByTradeId(String tradeId) {
        return finProductFeePayMapper.queryPtlTradeByTradeId(tradeId);
    }

    /**
     * 根据交易编号查询费用明细信息
     * 
     * @param tradeId 交易编号
     * @return 费用明细信息
     */
    public List<TrdTradeFeeDetail> queryTrdTradeFeeDetailByTradeId(String tradeId) {
        return finProductFeePayMapper.queryTrdTradeFeeDetailByTradeId(tradeId);
    }

    /**
     * 根据交易编号查询费用明细信息
     * 
     * @param map 交易编号
     * @return 费用明细信息
     */
    public List<FinProductFeePayFeeDetailDto> queryFeePositionByPtlId(Map<String, Object> map) {
        // map.put("profitType", DictCodeList.收益类型_收益型.getValue());
        map.put("cashType2_C04", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        return finProductFeePayMapper.queryFeePositionByPtlId(map);
    }

    /**
     * 根据交易编号查询费用明细信息(保护焊分层)
     * 
     * @param map 交易编号
     * @return 费用明细信息
     */
    public List<FinProductFeePayFeeDetailDto> queryFeePositionByPtlLayeredId(Map<String, Object> map) {
        map.put("profitType", DictCodeList.收益类型_收益型.getValue());
        map.put("cashType2_C04", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        map.put("ps", DictCodeList.交易方向_产品付费.getValue());
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("isCancel", DictCodeList.是否_否.getValue());
        map.put("chargeType", DictCodeList.收费类型_计提式费用.getValue());
        map.put("payCycle", DictCodeList.频率_不规则.getValue());
        //增加交易状态为初始的查询条件
        map.put("tradeStatus_01", DictCodeListExt1.交易状态_初始.getValue());
        List<FinProductFeePayFeeDetailDto> list = finProductFeePayMapper.queryFeePositionByPtlLayeredId(map);

        return list;
    }

    /**
     * 获取产品的规则类型
     * 
     * @param map 交易编号
     * @return 费用明细信息
     */
    public String queryPayCycleByCashId(Map<String, Object> map) {
        String payCycle = finProductFeePayMapper.queryPayCycleByCashId(map);

        return payCycle;
    }

    /**
     * 根据交易编号查询费用明细信息
     * 
     * @param map 交易编号
     * @return 费用明细信息
     */
    public List<FinProductFeePayFeeDetailDto> queryFeePositionByPtlId1(Map<String, Object> map) {
        // map.put("profitType", DictCodeList.收益类型_收益型.getValue());
        map.put("cashType2_C04", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        map.put("payCycle", DictCodeList.频率_不规则.getValue());
        // TODO
        map.put("ps", DictCodeList.交易方向_产品付费.getValue());
        map.put("chargeType", DictCodeList.收费类型_计提式费用.getValue());
        map.put("isCancel", DictCodeList.是否_否.getValue());
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        return finProductFeePayMapper.queryFeePositionByPtlId(map);
    }

    /**
     * 查询费用上次计提结束日
     * 
     * @param ptlId 组合代码
     * @param feeId 费用Id
     * @return 上次计提结束日
     */
    public String queryLastPayDate(String ptlId, String feeId, String payCycle,String org) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isCancel", DictCodeList.是否_否.getValue());
        map.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
        map.put("ptlId", ptlId);
        map.put("feeId", feeId);
        map.put("payCycle", DictCodeList.频率_不规则.getValue());
        map.put("flag", payCycle);
        map.put("org", org);
        String lastPayDate = "";
        if (StringUtil.isNotEmpty(org) && !"null".equals(org)) {
            lastPayDate = finProductFeePayMapper.queryLastPayDate1(map);
        }else {
            lastPayDate = finProductFeePayMapper.queryLastPayDate(map);
        } 
        // 无上次付费计提结束日则为组合起息日
        if (StringUtil.isEmpty(lastPayDate)) {
            PtlPortfolio ptlPortfolio = ptlPortfolioMapper.selectByPrimaryKey(ptlId);
            if (ptlPortfolio != null) {
                lastPayDate = DateUtil.format(ptlPortfolio.getVdate());
            }
        }
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
        return finProductFeePayMapper.queryUnCleanFee(map);
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
        return finProductFeePayMapper.queryAccrStrDate(map);
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
        return finProductFeePayMapper.calResiPrincipal(map);
    }

    /**
     * 根据产品代码费用Id查现金流二级类型
     * 
     * @param finprodId 现金流代码
     * @param feeId 现金流序号
     * @return 待处理的计划个数
     */
    public String selectCashType2(String finprodId, String feeId) {
        return finProductFeePayMapper.selectCashType2(finprodId, feeId);
    }

    /**
     * 根据产品代码费用Id查现金流二级类型(分层)
     * 
     * @param finprodId 现金流代码
     * @param feeId 现金流序号
     * @return 待处理的计划个数
     */
    public String selectLayyerdCashType2(String finprodId, String feeId) {
        return finProductFeePayMapper.selectLayyerdCashType2(finprodId, feeId);
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
        return finProductFeePayMapper.checkpayAmt(map);
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
    //  map.put("chargeType02", DictCodeList.收费类型_不规则费用.getValue());
    // map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("cashType", DictCodeList.现金流类型_费用支付.getValue());
        map.put("payCycle", DictCodeList.频率_不规则.getValue());
        map.put("feeType04", DictCodeList.费用品种类型_浮动差额中收.getValue());
        map.put("profitType02", DictCodeList.收益类型_收益型.getValue());
        map.put("feeType_03", DictCodeList.费用品种类型_固定管理费.getValue());
    //  map.put("feeType_04", DictCodeList.费用品种类型_浮动差额中收.getValue());
        map.put("feeType_12", DictCodeList.费用品种类型_浮动管理费.getValue());
        return finProductFeePayMapper.checkpayAmtrule(map);
    }

    public FinProductFeePayDto selectFeeByTradeId(Map<String, Object> map) {
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        return finProductFeePayMapper.selectFeeByTradeId(map);
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
        return finProductFeePayMapper.selectCashId(map);
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
        return finProductFeePayMapper.queryPrdLayerListByPid(finprodId);
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

    @Autowired
    private ProductCommonMapper productCommonMapper;

    public String doCompleteRevoke(FinProductFeePayDto entity) {
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
        trdFinProductDealTotalMapper.insert(trdFinProductDealTotal);

        PaTrdFinProductDealTotal patrdFinProductDealTotal = paTrdFinProductDealTotalMapper.selectByPrimaryKey(entity.getTradeId());

        // 原始汇总交易的状态改为已撤销
        if (patrdFinProductDealTotal != null) {
            patrdFinProductDealTotal.setTotleTradeId(
                patrdFinProductDealTotal.getTotleTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
            paTrdFinProductDealTotalMapper.insert(patrdFinProductDealTotal);
        }

        // 根据汇总交易编号查询金融产品交易
        List<TrdProductDeal> trdProductDealList = productCommonMapper.queryTrdProductDealByTotleTradeId(entity.getTradeId());

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
        List<StlPayOrders> stlPayOrdersList = productCommonMapper.queryStlPayOrdersByTradeId(entity.getTradeId());

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
        PtlTrade ptlTrade = this.queryPtlTradeByTradeId(entity.getTradeId());
        if (ptlTrade != null) {
            ptlTrade.setBusiNo(ptlTrade.getBusiNo() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
            ptlTrade.setCreateUser(entity.getCreateUser());
            ptlTrade.setCreateTime(new Date());
            ptlTrade.setUpdateUser(entity.getCreateUser());
            ptlTrade.setUpdateTime(new Date());
            ptlTradeMapper.insert(ptlTrade);
        }

//        mapperDelegateService.insertAutoClassifyBatch(stlPayOrdersList, ptlTrade);
        return "Success";
    }
    
    /**
     * 根据产品Id和费用Id查询费用信息 yzg 20191211
     * @param finprodId
     * @param feeId
     * @return
     */
    public MstFeeinfo selectMstFeeInfo(String finprodId, String feeId,String enddate) {
    	return finProductFeePayMapper.selectMstFeeInfo(finprodId, feeId,enddate);
    }
    /**
     * 	查询组合起息日
     * 
     * @param ptlId 组合代码
     * @param feeId 费用Id
     * @return 上次计提结束日
     */
    public String queryVdate(String ptlId) {
    	 String lastPayDate = "";
           PtlPortfolio ptlPortfolio = ptlPortfolioMapper.selectByPrimaryKey(ptlId);
            if (ptlPortfolio != null) {
              lastPayDate = DateUtil.format(ptlPortfolio.getVdate());
            }
        return lastPayDate;
    }
}
