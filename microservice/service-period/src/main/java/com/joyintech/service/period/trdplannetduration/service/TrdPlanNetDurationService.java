/*
 * 文件名：TrdFundDurationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.period.trdplannetduration.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.autoperiod.common.dao.PeriodCommonMapper;
import com.joyintech.tams.invest.common.autoperiod.common.service.InvestPeriodCommonService;
import com.joyintech.service.period.trdplannetduration.dao.TrdPlanNetDurationMapper;
import com.joyintech.service.period.trdplannetduration.dto.TrdPlanNetDurationDto;
import com.joyintech.tams.common.base.dao.RepTradeMapper;
import com.joyintech.tams.common.base.entity.RepTrade;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.common.BaseTradeInfoDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2018年11月19日
 * @see TrdPlanNetDurationService
 * @since 1.0
 */
@Service
public class TrdPlanNetDurationService extends BaseService<TrdPlanNetDurationDto>{
    @Autowired
    private InvestPeriodCommonService investPeriodCommonService;
    @Autowired
    private RepTradeMapper repTradeMapper;
    @Autowired
    private PeriodCommonMapper periodCommonMapper;
    /**
     *
     */
    @Autowired
    private TrdPlanNetDurationMapper trdPlanNetDurationMapper;
    @Autowired
    private IPlatformDate platformDateImpl;
    /**
     * 查询
     * <功能详细描述>
     * @param page 分页参数
     * @param length 分页参数
     * @param param 画面参数
     * @return 返回值
     * @see
     * @since 1.0
     */
    public Page<TrdPlanNetDurationDto> findPage(int page, int length, Map<String, Object> param) {
        setParams(param);
        return queryForPageForSelectReturn(page, length, () -> trdPlanNetDurationMapper.findPage(param));
    }
    private void setParams(Map<String, Object> param) {
        String portfolioId = (String)param.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)){
            List<String> portfolioIdList = StringUtil.splitToList(GlobalConstant.COMMA,portfolioId);
            param.put("portfolioIdList",portfolioIdList);
        }
        String registDateStart = (String)param.get("registDateStart");
        if(StringUtil.isNotEmpty(registDateStart)){
            param.put("registDateStart",DateUtil.parse(registDateStart));
        }
        else{
            param.remove("registDateStart");
        }
        String registDateEnd = (String)param.get("registDateEnd");
        if(StringUtil.isNotEmpty(registDateEnd)){
            param.put("registDateEnd",DateUtil.parse(registDateEnd));
        }
        else{
            param.remove("registDateEnd");
        }
        String exRightsDateStart = (String)param.get("exRightsDateStart");
        if(StringUtil.isNotEmpty(exRightsDateStart)){
            param.put("exRightsDateStart",DateUtil.parse(exRightsDateStart));
        }
        else{
            param.remove("exRightsDateStart");
        }
        String exRightsDateEnd = (String)param.get("exRightsDateEnd");
        if(StringUtil.isNotEmpty(exRightsDateEnd)){
            param.put("exRightsDateEnd",DateUtil.parse(exRightsDateEnd));
        }
        else{
            param.remove("exRightsDateEnd");
        }
        param.put("PAGE_DATE_STATUS01", DictCodeListExt1.画面数据状态_待处理.getValue());
        param.put("PAGE_DATE_STATUS03", DictCodeListExt1.画面数据状态_流程中.getValue());
        param.put("PAGE_DATE_STATUS05", DictCodeListExt1.画面数据状态_有效.getValue());
        param.put("PAGE_DATE_STATUS06", DictCodeListExt1.画面数据状态_撤销.getValue());
        // 数据字典缓存.交易状态.初始
        param.put("TRADE_STATUS01", DictCodeListExt1.交易状态_初始.getValue());
        param.put("TRADE_STATUS03", DictCodeListExt1.交易状态_撤销.getValue());
        param.put("YESNO_N", DictCodeList.是否_否.getValue());
        param.put("YESNO_Y", DictCodeList.是否_是.getValue());
        // 数据字典缓存.持仓类型.实际持仓
        param.put("POSITION_TYPE03", DictCodeList.持仓类型_实际.getValue());
        // 数据字典缓存.金融产品类型_投管用_计划类资产
        param.put("FINPROD_TYPE_TG_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        param.put("finprodTypeTg", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        param.put("PS_T11", DictCodeList.交易方向_现金分红.getValue());
        param.put("PS_T12", DictCodeList.交易方向_红利再投.getValue());
        param.put("PS_T13", DictCodeList.交易方向_除权登记.getValue());
        param.put("STATUS_A", DictCodeList.数据状态_新建中.getValue());
        param.put("BONUS_MODE_01", DictCodeList.分红方式_现金分红.getValue());
        param.put("BONUS_MODE_02", DictCodeList.分红方式_红利再投.getValue());
        param.put("PROFIT_TYPE_JZ", DictCodeList.收益类型_净值型.getValue());
        param.put("BUSI_TABLE","TRD_PRODUCT_DEAL");
        param.put("ACCT_TYPE_A01", DictCodeListExt1.资金账户类型_产品托管户.getValue());
    }
    /**
     *
     * 市场代码联动
     *
     * @param map 参数
     * @return 联动的详情数据
     * @see
     * @since 1.0
     */
    public Map<String, Object> finprodMarketIdLink(Map<String, Object> map) {
        return trdPlanNetDurationMapper.finprodMarketIdLink(map);
    }

    /**
     *
     * 组合代码联动
     *
     * @param map 参数
     * @return 联动的详情数据
     * @see
     * @since 1.0
     */
    public Map<String, Object> portfolioIdLink(Map<String, Object> map) {
        return trdPlanNetDurationMapper.portfolioIdLink(map);
    }

    /**
     * 计算登记份额
     * @param map 参数
     * @return 检索结果
     */
    public Map<String, Object> calRegistShare(Map<String, Object> map) {
        map.put("HOLDING_TYPE_03", DictCodeList.持仓类型_实际.getValue());
        return trdPlanNetDurationMapper.calRegistShare(map);
    }
    /**
     * 删除流程中数据
     * @param tradeId 交易代码
     */
    private void deleteWflByTradeId(String tradeId) {
        Map<String,Object> map = new HashMap<>();
        map.put("tradeId",tradeId);
        String []tableArr = {investPeriodCommonService.金融产品交易扩展业务流程表, investPeriodCommonService.金融产品交易附属业务流程表, investPeriodCommonService.划款指令业务流程表,
                investPeriodCommonService.金融产品交易业务流程表};
        for(String tableId:tableArr){
            map.put("tableId",tableId);
            periodCommonMapper.deleteWflByTradeId(map);
        }
    }
    /**
     * 工作流结束操作
     * @param durationDto 数据entity
     * @return 处理结果
     */
    public String doComplete(TrdPlanNetDurationDto durationDto) {
        String workflowId = durationDto.getWorkflowId();
        String updateUser = durationDto.getUpdateUser();
        Date updateTime = new Date();
        if (BussinessConstant.REVOKE.equalsIgnoreCase(durationDto.getOperateType())) {
            TrdFinProductDealTotal trdFinProductDealTotal = investPeriodCommonService.revoke汇总和交易相关表(durationDto.getpTradeId(),
                    updateUser, durationDto.getRevokeReason(), null, null);
            // 删除其他表
            investPeriodCommonService.deleteByWorkflowId(durationDto.getWorkflowId());
            if ((StringUtil.equals(durationDto.getType(), "02") || StringUtil.equals(durationDto.getType(), "05"))
                    && StringUtil.equals(durationDto.getBonusMode(), DictCodeList.分红方式_现金分红.getValue())) {
                investPeriodCommonService.revoke后续交易相关表(durationDto.getpTradeId(), updateUser, durationDto.getRevokeReason(), null, null);
            } else if (StringUtil.equals(durationDto.getType(), "03") || StringUtil.equals(durationDto.getType(), "06")) {
                investPeriodCommonService.revoke汇总和交易相关表(durationDto.getbTradeId(), updateUser, durationDto.getRevokeReason(),
                        null, null);
            }
            return Boolean.TRUE.toString();
        }
        investPeriodCommonService.doInsertAndDelete(durationDto.getBaseTotalTradeInfoDto(), updateUser, updateTime,
                durationDto.getWorkflowId());

        List<BaseTradeInfoDto> baseTradeInfoDtos = durationDto.getBaseTotalTradeInfoDto().getBaseTradeInfoDtoList();
        if (baseTradeInfoDtos != null && !baseTradeInfoDtos.isEmpty()) {
            baseTradeInfoDtos.forEach(baseTradeInfoDto -> {
                String portfolioId = baseTradeInfoDto.getTrdProductDealAdd() != null
                        ? baseTradeInfoDto.getTrdProductDealAdd().getPortfolioId()
                        : baseTradeInfoDto.getBaseTrade().getTrdProductDealAdd().getPortfolioId();
                String 产品登记编码 = periodCommonMapper.selectProdRegistCode(portfolioId,
                        DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
                if (StringUtil.isNotEmpty(产品登记编码)) {
                    if (baseTradeInfoDto.getTrdProductDeal() != null) {
                        insertRep(durationDto, updateUser, updateTime, baseTradeInfoDto.getTrdProductDeal(), 产品登记编码);
                    }
                    if (baseTradeInfoDto.getBaseTrade() != null
                            && baseTradeInfoDto.getBaseTrade().getTrdProductDeal() != null) {
                        insertRep(durationDto, updateUser, updateTime,
                                baseTradeInfoDto.getBaseTrade().getTrdProductDeal(), 产品登记编码);
                    }
                }
                //                一体化调用 etliu add start
                AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                inputDto.setProdid(portfolioId);
                inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
                inputDto.setTrdProductDeal(baseTradeInfoDto.getTrdProductDeal());
                inputDto.setTrdProductDealAdd(baseTradeInfoDto.getTrdProductDealAdd());
                inputDto.setPtlTradeList(baseTradeInfoDto.getPtlTrade());
                inputDto.setStlPayOrders(baseTradeInfoDto.getStlPayOrders());
                AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
                //              一体化调用 etliu add end

                baseTradeInfoDto.getBaseTradeInfoDtoList().forEach(baseTradeInfoDto1 -> {
                    AllTradeCommonInputDto inputDto1 = new AllTradeCommonInputDto();
                    inputDto1.setProdid(baseTradeInfoDto1.getTrdProductDealAdd().getPortfolioId());
                    inputDto1.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
                    inputDto1.setTrdProductDeal(baseTradeInfoDto1.getTrdProductDeal());
                    inputDto1.setTrdProductDealAdd(baseTradeInfoDto1.getTrdProductDealAdd());
                    inputDto1.setPtlTradeList(baseTradeInfoDto1.getPtlTrade());
                    inputDto1.setStlPayOrders(baseTradeInfoDto1.getStlPayOrders());
                    AllTradeCommonUtil.handleAfterTradeComplete(inputDto1);
                });
            });
        }
        return Boolean.TRUE.toString();

    }
    private void insertRep(TrdPlanNetDurationDto durationDto, String updateUser, Date updateTime, TrdProductDeal deal,
	            String 产品登记编码) {
	   List<RepTrade> repTrades = gen交易报送信息(durationDto, deal, 产品登记编码);
	   repTrades.forEach(repTrade -> {
	            repTrade.setCreateUser(durationDto.getCreateUser());
	            repTrade.setCreateTime(durationDto.getCreateTime());
	            repTrade.setCreateDept(durationDto.getCreateDept());
	            repTrade.setUpdateUser(updateUser);
	            repTrade.setUpdateTime(updateTime);
	            repTradeMapper.insertSelective(repTrade);
	   });
	}
    /**
     * 处理交易报送的entity
     *
     * @param entity             数据entity
     * @param deal 交易entity
     * @param 产品登记编码             产品登记编码
     * @return 报送数据
     */
    private List<RepTrade> gen交易报送信息(TrdPlanNetDurationDto entity, TrdProductDeal deal, String 产品登记编码) {
             // 折算人民币金额（元）
             BigDecimal 最低收盘价 = BigDecimal.ONE;
             if (!DictCodeList.币种_人民币_CNY.getValue().equals(entity.getCcy())) {
                      最低收盘价 = investPeriodCommonService.find最低收盘价(entity.getCcy(), DateUtil.format(entity.getSettleDate()));
             }
             BigDecimal 最低收盘价F = 最低收盘价;
             List<RepTrade> repTrades = new ArrayList<>();
             RepTrade repTrade = new RepTrade();
             repTrade.setRepFinprodRegId(产品登记编码);
//   发行机构代码
             repTrade.setRepIssuePartyId(entity.getRepIssuePartyId());
//   行内交易编码
//   repTrade.setRepBankTradeCode(entity.getTradeId());
             repTrade.setRepBankTradeCode(deal.getTradeId());
//   行内资产/负债编码
             repTrade.setRepBankAssetCode(entity.getFinprodMarketId());
//   币种
             repTrade.setRepCcy(entity.getCcy());
// 对手方 隐藏发行人
             repTrade.setRepTradeComponent(entity.getIssuer());
// 合同号
             repTrade.setRepDelCode(deal.getTradeId());
//   备注
             repTrade.setRepRemark(entity.getRemark());
//   报送状态
             repTrade.setRepSendStatus(DictCodeList.是否_是.getValue());
//   数据日期
             repTrade.setRepStatusDate(platformDateImpl.getSysDate());
//   所有场景生成第一种报送数据||现金分红后续交易不需要生成
             if (!StringUtil.equals(entity.getType(), "04")&&!StringUtil.equals(entity.getType(),"03")) {
//   第一笔
//   数量
                      repTrade.setRepCount(deal.getPrinAmt());
//   交易日
                      repTrade.setRepTradeDate(deal.getTradeDate());
//   折算人民币
                      repTrade.setRepMoneyCny(ArithmeticUtil.multiply(deal.getIntAmt(), 最低收盘价F));
//   发生金额
                      repTrade.setRepOccurMoney(deal.getIntAmt());
//   资金流动类型
                      repTrade.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_投资资产所得收益.getValue());
                      repTrades.add(repTrade);
             }
// 货币基金红利再投及非货币红利再投时，生成第二种报送数据
             if (StringUtil.equals(entity.getType(), "01") || StringUtil.equals(entity.getType(), "04")) {
                      RepTrade repTrade2 = new RepTrade();
                      BeanUtil.copyPropertiesIgnoreNull(repTrade2, repTrade);
//   第二笔
//   数量
                      repTrade2.setRepCount(deal.getShareAmt());
//   交易日
                      repTrade2.setRepTradeDate(deal.getSettleDate());
//   折算人民币
                      repTrade2.setRepMoneyCny(ArithmeticUtil.multiply(deal.getIntAmt(), 最低收盘价F));
//   发生金额
                      repTrade2.setRepOccurMoney(deal.getIntAmt());
                      repTrade2.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_资产买入.getValue());
                      repTrades.add(repTrade2);
             }
             return repTrades;
    }

    /**
     * 查询有效及撤销数据
     * @param map 查询参数
     * @return 查询结果
     */
    public TrdPlanNetDurationDto findEffectiveData(Map<String, Object> map) {
        setParams(map);
        return trdPlanNetDurationMapper.findEffectiveData(map);
    }
    /**
     * 查询现金分红流程中
     * @param map 查询参数
     * @return 查询结果
     */
    public TrdPlanNetDurationDto findCashDividend(Map<String, Object> map){
        setParams(map);
        return trdPlanNetDurationMapper.findCashDividend(map);
    }
    /**
     * 统计是否存在后续交易
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    public int countFollowDeal(Map<String, Object> map) {
        return trdPlanNetDurationMapper.countFollowDeal(map);
    }
    /**
     * 查询利息明细信息 params 查询参数
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    public List<FinAssetIntInfo> findAssetIntInfo(Map<String, Object> params) {
             setParams(params);
        //        权益登记日
            String registDate = (String) params.get("registDate");
            if (StringUtil.isNotEmpty(registDate)) {
                params.put("registDate", DateUtil.parse(registDate));
            }
             return trdPlanNetDurationMapper.findAssetIntInfo(params);
    }
}
