/*
 * 文件名：TrdDepoDurationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.period.trddepoduration.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.tams.common.base.dao.RepTradeMapper;
import com.joyintech.tams.common.base.entity.RepTrade;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.autoperiod.common.dao.PeriodCommonMapper;
import com.joyintech.tams.invest.common.autoperiod.common.service.InvestPeriodCommonService;
import com.joyintech.service.period.trddepoduration.dao.TrdDepoDurationMapper;
import com.joyintech.service.period.trddepoduration.dto.TrdDepoDurationDto;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.common.BaseTradeInfoDto;
import com.joyintech.tams.invest.common.risk.service.RiskCreditListCommonValidatorService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;

/**
 * 存续期微服务 〈功能详细描述〉
 *
 * @author Administrator
 * @version 1.0 2018年10月11日
 * @see TrdDepoDurationService
 * @since 1.0
 */
@Service
public class TrdDepoDurationService extends BaseService<TrdDepoDurationDto> {
    @Autowired
    private TrdDepoDurationMapper trdDepoDurationMapper;
//    @Autowired
//    private TrdRepoDurationMapper trdRepoDurationMapper;
//
//    @Autowired
//    private TrdProductDealMapper trdProductDealMapper;
//
//    @Autowired
//    private PtlTradeMapper ptlTradeMapper;
//
//    @Autowired
//    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;
//
//    @Autowired
//    private WflStlPayOrdersMapper wflStlPayOrdersMapper;
//
//    @Autowired
//    private PaTrdFinProductDealTotalMapper paTrdFinProductDealTotalMapper;

    @Autowired
    private RepTradeMapper repTradeMapper;
//    @Autowired
//    private TrdTradeFeeDetailMapper trdTradeFeeDetailMapper;
//    @Autowired
//    private WflTrdFinProductDealTotalMapper wflTrdFinProductDealTotalMapper;
//
//    @Autowired
//    private StlPayOrdersMapper stlPayOrdersMapper;
//
//    @Autowired
//    private WflPaTrdFinDealTotalMapper wflPaTrdFinDealTotalMapper;
//
//    @Autowired
//    private WflTrdProductDealMapper wflTrdProductDealMapper;

    @Autowired
    private InvestPeriodCommonService investPeriodCommonService;

    @Autowired
    private IPlatformDate platformDateImpl;
    @Autowired
    private PeriodCommonMapper periodCommonMapper;
    
    @Autowired
    private RiskCreditListCommonValidatorService riskCreditListCommonValidatorService;

    public String doComplete(TrdDepoDurationDto durationDto) {
        String updateUser = durationDto.getUpdateUser();
        Date updateTime = new Date();
        if (BussinessConstant.REVOKE.equals(durationDto.getOperateType())) {
            //查找授信交易TRADE_ID 撤销释放额度用
            List<String> tradeidList = trdDepoDurationMapper.findCeTradeIdByTotleTradeId(durationDto.getpTradeId());
            //删除授信占用
            if (tradeidList != null && !tradeidList.isEmpty()){
                tradeidList.forEach(tradeid -> {
                    riskCreditListCommonValidatorService.deleteCredit(tradeid);
                });
            }
            
            TrdFinProductDealTotal trdFinProductDealTotal = investPeriodCommonService
                    .revoke汇总和交易相关表(durationDto.getpTradeId(), updateUser, durationDto.getRevokeReason(), null, null);
            // 删除其他表
            investPeriodCommonService.deleteByWorkflowId(durationDto.getWorkflowId());
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
                        insertRep(durationDto, updateUser, updateTime, baseTradeInfoDto.getTrdProductDeal(), 产品登记编码,portfolioId);
                    }
                    if (baseTradeInfoDto.getBaseTrade() != null
                            && baseTradeInfoDto.getBaseTrade().getTrdProductDeal() != null) {
                        insertRep(durationDto, updateUser, updateTime,
                                baseTradeInfoDto.getBaseTrade().getTrdProductDeal(), 产品登记编码,portfolioId);
                    }
                }
//              // 一体化对应etliu start modiby yanya on 20191127 修改调用估值方法，子组合不生成入账凭证修复
//              // 所有交易生效后调用的共通方法
//                AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
//                // 存续期增加通道费 add by renping 20191018 start
//                if(!StringUtil.isEmptyList(baseTradeInfoDto.getTrdTradeFeeDetail())) {
//                    inputDto.setTrdTradeFeeDetail(baseTradeInfoDto.getTrdTradeFeeDetail().get(0));
//                }
//                // 存续期增加通道费 add by renping 20191018 end
//                inputDto.setProdid(portfolioId);
//                inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
//                if (baseTradeInfoDto.getTrdProductDeal() != null) {
//                	inputDto.setTrdProductDeal(baseTradeInfoDto.getTrdProductDeal());
//                    inputDto.setTrdProductDealAdd(baseTradeInfoDto.getTrdProductDealAdd());
//                    inputDto.setPtlTradeList(baseTradeInfoDto.getPtlTrade());
//                	AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
//                    // 存续期增加通道费 add by renping 20191018 start
//                	inputDto.setTrdTradeFeeDetail(null);
                    // 存续期增加通道费 add by renping 20191018 end
//                }
                //baseTradeInfoDto.getTrdProductDeal().setFinprodType(DictCodeListExt1.金融产品类型_存款.getValue());
                调估值接口(baseTradeInfoDto, portfolioId);
                //子组合处理
                if(baseTradeInfoDto.getBaseTradeInfoDtoList() != null) {
                    baseTradeInfoDto.getBaseTradeInfoDtoList().forEach(baseTradeInfoDto1 -> {
                        String portfolioId1 = baseTradeInfoDto1.getTrdProductDealAdd() != null ?
                                baseTradeInfoDto1.getTrdProductDealAdd().getPortfolioId() : baseTradeInfoDto1.getBaseTrade().getTrdProductDealAdd().getPortfolioId();
                        //baseTradeInfoDto1.getTrdProductDeal().setFinprodType(DictCodeListExt1.金融产品类型_存款.getValue());
                        调估值接口(baseTradeInfoDto1, portfolioId1); // 子组合调用估值接口
                    });
                }
//                if (baseTradeInfoDto.getBaseTrade() != null
//                        && baseTradeInfoDto.getBaseTrade().getTrdProductDeal() != null) {
//                    inputDto.setTrdProductDeal(baseTradeInfoDto.getBaseTrade().getTrdProductDeal());
//                    inputDto.setTrdProductDealAdd(baseTradeInfoDto.getBaseTrade().getTrdProductDealAdd());
//                    inputDto.setPtlTradeList(baseTradeInfoDto.getBaseTrade().getPtlTrade());
//                    AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
//                }
//              // 一体化对应etliu end
            });
        }
        return Boolean.TRUE.toString();
    }

    /**
     * 调用估值接口生成入账凭证（参考债券存续期）
     * @param baseTradeInfoDto
     * @param portfolioId
     */
    private void 调估值接口(BaseTradeInfoDto baseTradeInfoDto, String portfolioId) {
        AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
        inputDto.setProdid(portfolioId);
        if(!com.joyintech.framework.util.StringUtil.isEmptyList(baseTradeInfoDto.getTrdTradeFeeDetail())) {
            inputDto.setTrdTradeFeeDetail(baseTradeInfoDto.getTrdTradeFeeDetail().get(0));
        }
        inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
        if (baseTradeInfoDto.getTrdProductDeal() != null) {
            baseTradeInfoDto.getTrdProductDeal().setFinprodType(DictCodeListExt1.金融产品类型_存款.getValue());
            inputDto.setTrdProductDeal(baseTradeInfoDto.getTrdProductDeal());
            inputDto.setTrdProductDealAdd(baseTradeInfoDto.getTrdProductDealAdd());
            inputDto.setPtlTradeList(baseTradeInfoDto.getPtlTrade());
            inputDto.setStlPayOrders(baseTradeInfoDto.getStlPayOrders());
            AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
            inputDto.setTrdTradeFeeDetail(null);
        }
        if (baseTradeInfoDto.getBaseTrade() != null && baseTradeInfoDto.getBaseTrade().getTrdProductDeal() != null) {
            inputDto.setTrdProductDeal(baseTradeInfoDto.getBaseTrade().getTrdProductDeal());
            inputDto.setTrdProductDealAdd(baseTradeInfoDto.getBaseTrade().getTrdProductDealAdd());
            inputDto.setPtlTradeList(baseTradeInfoDto.getBaseTrade().getPtlTrade());
            inputDto.setStlPayOrders(baseTradeInfoDto.getStlPayOrders());
            AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
        }
    }

    /**
     * 
     * 处理报送数据
     * <功能详细描述>
     * @param durationDto jiaoyidto
     * @param updateUser 更新人
     * @param updateTime 更新时间
     * @param deal 交易
     * @param 产品登记编码 产品登记编码
     * @param portfolioId 组合代码
     * @see
     * @since 1.0
     */
    private void insertRep(TrdDepoDurationDto durationDto, String updateUser, Date updateTime, TrdProductDeal deal,
            String 产品登记编码, String portfolioId) {
        List<RepTrade> repTrades = gen交易报送信息(durationDto, deal, 产品登记编码);
        repTrades.forEach(repTrade -> {
            repTrade.setCreateUser(durationDto.getCreateUser());
            repTrade.setCreateTime(durationDto.getCreateTime());
            repTrade.setCreateDept(durationDto.getCreateDept());
            repTrade.setUpdateUser(updateUser);
            repTrade.setUpdateTime(updateTime);
            repTradeMapper.insertSelective(repTrade);
        });
//        // 7.2 删除原业务流程表中的交易记录
//        deleteWflByWorkflowId(workflowId);
//        //处理交易费
//        TrdTradeFeeDetail trdTradeFeeDetail = get交易费用(entity);
//        trdTradeFeeDetailMapper.insert(trdTradeFeeDetail);
//
//        return Boolean.TRUE.toString();
    }
//    /**
//     * 流程结束时处理
//     *
//     * @param entity 参数
//     * @return
//     */
//    public String doComplete(TrdDepoDurationDto entity) {
//        String workflowId = entity.getWorkflowId();
//        if (BussinessConstant.REVOKE.equalsIgnoreCase(entity.getMode())) {
//            periodCommonService.revoke汇总和交易相关表(entity.getpTradeId(), entity.getUpdateUser(), "");
//         // 7.2 删除原业务流程表中的交易记录
//            deleteWflByWorkflowId(workflowId);
//            return Boolean.TRUE.toString();
//        }
//        // 金融产品交易Entity
//        List<WflTrdProductDeal> wflTrdProductDeals = trdRepoDurationMapper.findDealByWorkflowId(
//                workflowId);
//        // 组合交易
//        List<PtlTrade> ptlTrades = gen组合交易(entity, wflTrdProductDeals);
//        // 金融产品交易汇总
//        WflTrdFinProductDealTotal wflTrdFinProductDealTotal = trdRepoDurationMapper.findTotalByWorkflowId(
//            workflowId);
//        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
//        BeanUtils.copyProperties(wflTrdFinProductDealTotal, trdFinProductDealTotal);
//
//        List<TrdProductDeal> trdProductDeals = new ArrayList<>();
//        wflTrdProductDeals.forEach(wflTrdProductDeal -> {
//            TrdProductDeal deal = new TrdProductDeal();
//            BeanUtils.copyProperties(wflTrdProductDeal, deal);
//            trdProductDeals.add(deal);
//        });
//        trdFinProductDealTotalMapper.insertSelective(trdFinProductDealTotal);
//
//        // 划款指令
//        List<WflStlPayOrders> stlPayOrdersDtos = trdRepoDurationMapper.findOrdersByWorkflowId(
//            workflowId);
//        stlPayOrdersDtos.forEach(order -> {
//            StlPayOrders deal = new StlPayOrders();
//            BeanUtils.copyProperties(order, deal);
//            stlPayOrdersMapper.insertSelective(deal);
//        });
//
//        // 7.4 插入金融产品交易表
//        trdProductDeals.forEach(trdProductDeal -> {
//            trdProductDealMapper.insertSelective(trdProductDeal);
//        });
//
//        // 金融产品交易汇总扩展Entity
//        WflPaTrdFinDealTotal wflPaTrdFinDealTotal = trdRepoDurationMapper.findPaTotalByWorkflowId(
//            workflowId);
//        PaTrdFinProductDealTotal paTrdFinProductDealTotal = new PaTrdFinProductDealTotal();
//        BeanUtils.copyProperties(wflPaTrdFinDealTotal, paTrdFinProductDealTotal);
//        paTrdFinProductDealTotalMapper.insertSelective(paTrdFinProductDealTotal);
//
//        // 7.3 插入组合交易
//        ptlTrades.forEach(ptlTrade -> {
//            PtlTrade deal = new PtlTrade();
//            BeanUtils.copyProperties(ptlTrade, deal);
//            ptlTradeMapper.insertSelective(deal);
//        });
//
//        // 7.7 交易报送信息
//        List<RepTrade> repTrades = gen交易报送信息(entity, wflTrdProductDeals);
//        repTrades.forEach(repTrade -> {
//            RepTrade deal = new RepTrade();
//            BeanUtils.copyProperties(repTrade, deal);
//            repTradeMapper.insertSelective(deal);
//        });
//        // 7.2 删除原业务流程表中的交易记录
//        deleteWflByWorkflowId(workflowId);
//        //处理交易费
//        TrdTradeFeeDetail trdTradeFeeDetail = get交易费用(entity);
//        trdTradeFeeDetailMapper.insert(trdTradeFeeDetail);
//        return Boolean.TRUE.toString();
//    }
//    /**
//     *
//     * 交易费用
//     * <功能详细描述>
//     * @param entity
//     * @return
//     * @see
//     * @since 1.0
//     */
//
//    public TrdTradeFeeDetail get交易费用(TrdDepoDurationDto entity) {
//        TrdTradeFeeDetail feeDto = new TrdTradeFeeDetail();
//        //部门机构创建时间
//        BeanUtil.copyPropertiesIgnoreNull(feeDto, entity);
//        feeDto.setFeeAmt(entity.getFeeAmt());
//        feeDto.setTradeId(entity.getBusiCode());
//        feeDto.setFeeType(DictCodeListExt1.一次性费用类型_投管_资产推荐费.getValue());
//        feeDto.setIsPayWithTrade(DictCodeList.是否_否.getValue());
//        return feeDto;
//    }
//    private String deleteWflByWorkflowId(String workflowId) {
//        wflTrdFinProductDealTotalMapper.deleteByWorkflowId(workflowId);
//        wflStlPayOrdersMapper.deleteByWorkflowId(workflowId);
//        wflPaTrdFinDealTotalMapper.deleteByWorkflowId(workflowId);
//        wflTrdProductDealMapper.deleteByWorkflowId(workflowId);
//        return Boolean.TRUE.toString();
//    }

    /**
     * 查询详情页数据
     *
     * @param params 参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public TrdDepoDurationDto findEffectiveData(Map<String, Object> params) {
        setParams(params);
        if(params.containsKey("isFindEnd")) {
        	// TISHKBS-692 存款类资产 使用产品经理做到期，统计的到期金额错误 合并吉林代码 add by lizhi 20200630 start
        	params.put("userName", "");
        	// TISHKBS-692 存款类资产 使用产品经理做到期，统计的到期金额错误 合并吉林代码 add by lizhi 20200630 end
        	return trdDepoDurationMapper.findEndData(params);
        }
        else {
        	return trdDepoDurationMapper.findEffectiveData(params);
        }
    }

    /**
     * 加载查询列表
     *
     * @param page   分页用变量
     * @param length 分页用变量
     * @param param  查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public Page<TrdDepoDurationDto> findPage(int page, int length, Map<String, Object> param) {
        setParamsForSearch(param);
        if(StringUtil.isNotEmpty((String)param.get("finprodMarketId"))){

            param.put("finprodMarketIds", Arrays.asList(param.get("finprodMarketId").toString().split(",")));
        }else{
            param.put("finprodMarketIds", null);
        }
        param.remove("finprodMarketId");
        return queryForPageForSelectReturn(page, length, () -> trdDepoDurationMapper.findPage(param));
    }

    /**
     * 查询所有数据
     *
     * @param param 界面参数
     * @return 查询结果集
     */
    public List<TrdDepoDurationDto> selectAllData(@RequestBody Map<String, Object> param) {
        setParamsForSearch(param);
        return trdDepoDurationMapper.findPage(param);
    }

    private void setParamsForSearch(Map<String, Object> param) {
        setParams(param);
        Date plateDate = platformDateImpl.getSysDate();
        if (param.get(BussinessConstant.PLUS_DAYS) != null) {
            plateDate = DateUtil.parse(
                    DateUtil.plusDays(DateUtil.format(plateDate), (Integer) param.get(BussinessConstant.PLUS_DAYS)));
        }
        param.put("PLATEFORM_DATE", plateDate);
    }

    private void setParams(Map<String, Object> param) {
        /* 现金流二级类型.资产推荐费 */
        param.put("CASH_TYPE_2_C08", DictCodeListExt1.现金流二级类型_资产推荐费.getValue());
        param.put("CASH_TYPE_1_02", DictCodeListExt1.现金流一级类型_利息.getValue());
        param.put("CASH_TYPE_2_C02", DictCodeListExt1.现金流二级类型_利息.getValue());
        param.put("PAGE_DATE_STATUS01", DictCodeListExt1.画面数据状态_待处理.getValue());
        param.put("PAGE_DATE_STATUS03", DictCodeListExt1.画面数据状态_流程中.getValue());
        param.put("PAGE_DATE_STATUS05", DictCodeListExt1.画面数据状态_有效.getValue());
        param.put("PAGE_DATE_STATUS06", DictCodeListExt1.画面数据状态_撤销.getValue());
        param.put("CASH_CLASS_TYPE01", DictCodeList.现金流类型_中间收息.getValue());
        param.put("CASH_CLASS_TYPE02", DictCodeList.现金流类型_中间还本.getValue());
        param.put("CASH_CLASS_TYPE03", DictCodeList.现金流类型_到期还本.getValue());
        // 一览查询bug对应 add by renping 20191027 start
        param.put("PS_T38", DictCodeList.交易方向_提前支取.getValue());
        // 一览查询bug对应 add by renping 20191027 end
        // 数据字典缓存.交易状态.初始
        param.put("TRADE_STATUS01", DictCodeListExt1.交易状态_初始.getValue());
        param.put("TRADE_STATUS03", DictCodeListExt1.交易状态_撤销.getValue());
        param.put("YESNO_N", DictCodeList.是否_否.getValue());
        param.put("YESNO_Y", DictCodeList.是否_是.getValue());
        if(StringUtil.isNotEmpty((String)param.get("portfolioId"))){
            param.replace("portfolioId", Arrays.asList(param.get("portfolioId").toString().split(",")));
        }else{
            param.replace("portfolioId", null);
        } 
        // 数据字典缓存.持仓类型.实际持仓
        param.put("POSITION_TYPE03", DictCodeList.持仓类型_实际.getValue());
        // 数据字典缓存.金融产品类型_投管用_存款
        param.put("FINPROD_TYPE_TGF07", DictCodeListExt1.金融产品类型_投管用_存款.getValue());
        param.put("TRD10", DictCodeList.共通业务类型_债券类资产存续期.getValue());
        param.put("STATUS_A", DictCodeList.数据状态_新建.getValue());
        param.put("ACCT_TYPE_A01", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        Date plateDate = platformDateImpl.getSysDate();
        param.put("PLATEFORM_DATE", plateDate);
        param.put("PS_C7", DictCodeList.交易方向_中间付息.getValue());
		// 跟上面代码重复导致报错，删除
//        if(StringUtil.isNotEmpty((String)param.get("portfolioId"))){
//            param.replace("portfolioId", Arrays.asList(param.get("portfolioId").toString().split(",")));
//        }else{
//            param.replace("portfolioId", null);
//        }
    }

    /**
     * 判断其在现金流对账模块是否完成匹配 <功能详细描述>
     *
     * @param busiCode 交易编号
     * @return 统计结果
     * @see
     * @since 1.0
     */
    public int countCashMatch(String busiCode) {
        return trdDepoDurationMapper.countCashMatch(busiCode);
    }

    //add by yujiaping 2020-12-5 BS-1562【存款类存续期】同一资产同一计息结束日只能做一次活期存放结息start
    public Map<String, Object> getTradeId(Map<String, Object> map) {
        return trdDepoDurationMapper.getTradeId(map);
    }
    //add by yujiaping 2020-12-5 BS-1562【存款类存续期】同一资产同一计息结束日只能做一次活期存放结息end
    /**
     *
     * 市场代码联动 <功能详细描述>
     *
     * @param map 参数
     * @return 联动的详情数据
     * @see
     * @since 1.0
     */
    public Map<String, Object> finprodMarketIdLink(Map<String, Object> map) {
        map.put("PLATEFORM_DATE", platformDateImpl.getSysDate());
        return trdDepoDurationMapper.finprodMarketIdLink(map);
    }

    /**
     *
     * 组合代码联动 <功能详细描述>
     *
     * @param map 参数
     * @return 联动的详情数据
     * @see
     * @since 1.0
     */
    public Map<String, Object> portfolioIdLink(Map<String, Object> map) {
        return trdDepoDurationMapper.portfolioIdLink(map);
    }

    /**
     *
     * 组合代码联动 add by jiangpeng on 20201215 ZGBS-69
     *
     * @param map 参数
     * @return 联动的详情数据
     * @see
     * @since 1.0
     */
    public Map<String, Object> getPortfolioName(Map<String, Object> map) {
        return trdDepoDurationMapper.getPortfolioName(map);
    }

    /**
     * 剩余本金计算联动 <功能详细描述>
     *
     * @param map 画面参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public Map<String, Object> calResiPrincipal(Map<String, Object> map) {
        //活期存放结息
        if(StringUtil.equals(map.get("opType"),"demand")){
           map.put("mdateProd",DateUtil.format(DateUtil.addDays(DateUtil.parse((String)map.get("mdateProd")),-1)));
           map.put("hodingType",DictCodeList.持仓类型_实际.getValue());
           return trdDepoDurationMapper.calResiPrincipalDemand(map);
        }
        return trdDepoDurationMapper.calResiPrincipal(map);
    }

    public Map<String, Object> calTheoryIntDemand(Map<String, Object> map) {
        map.put("mdateProd",DateUtil.format(DateUtil.addDays(DateUtil.parse((String)map.get("mdateProd")),-1)));
        return trdDepoDurationMapper.calTheoryIntDemand(map);
    }

//    private List<PtlTrade> gen组合交易(TrdDepoDurationDto entity, List<WflTrdProductDeal> wflTrdProductDeals) {
//        List<PtlTrade> result = new ArrayList<>();
//        wflTrdProductDeals.forEach(wflTrdProductDeal -> {
//            PtlTrade ptlTrade2 = new PtlTrade();
//            ptlTrade2.setPortfolioId(entity.getPortfolioId());
//            ptlTrade2.setBusiNo(wflTrdProductDeal.getTradeId());
//            ptlTrade2.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
//            ptlTrade2.setBusiId(entity.getFinprodId());
//            ptlTrade2.setBusiType(DictCodeList.共通业务类型_存款类资产存续期.getValue());
//            ptlTrade2.setSecManageAcctId("999999");
//            ptlTrade2.setSettleDate(entity.getTradeDate());
//            result.add(ptlTrade2);
//        });
//        return result;
//    }
    /**
     * 
     * 处理交易报送 <功能详细描述>
     * 
     * @param entity 业务实体
     * @param deal   交易
     * @return 报送数据
     * @see
     * @since 1.0
     */
    private List<RepTrade> gen交易报送信息(TrdDepoDurationDto entity, TrdProductDeal deal, String 产品登记编码) {
        List<RepTrade> result = new ArrayList<>();
// 产品登记编码
//        Map<String, Object> params = new HashMap<>();
//        params.put("FINPROD_TYPE_TG_F06", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
//        params.put("portfolioId", entity.getPortfolioId());
//        String 产品登记编码 = trdRepoDurationMapper.findPrdRegCode(params);
        if (StringUtil.isEmpty(产品登记编码)) {
            return result;
        }
//            折算人民币金额（元）
        BigDecimal 最低收盘价 = BigDecimal.ONE;
        if (!DictCodeList.币种_人民币_CNY.getValue().equals(entity.getCcy())) {
            最低收盘价 = investPeriodCommonService.find最低收盘价(entity.getCcy(), DateUtil.format(entity.getSettleDate()));
        }
        BigDecimal 最低收盘价F = 最低收盘价;
        RepTrade repTrade = new RepTrade();
        repTrade.setRepIssuePartyId(entity.getRepIssuePartyId());
        repTrade.setRepFinprodRegId(产品登记编码);
        repTrade.setRepBankTradeCode(deal.getTradeId());
        repTrade.setRepBankAssetCode(entity.getFinprodMarketId());
        repTrade.setRepCcy(entity.getCcy());
        repTrade.setRepCount(deal.getShareAmt());
			repTrade.setRepTradeDate(entity.getTradeDate());
        repTrade.setRepTradeComponent(entity.getCounterId());
        repTrade.setRepDelCode(deal.getTradeId());
        repTrade.setRepRemark(entity.getRemark());
        repTrade.setRepSendStatus(DictCodeList.是否_是.getValue());
        repTrade.setRepStatusDate(platformDateImpl.getSysDate());
        if (DictCodeList.交易方向_提前支取.getValue().equals(deal.getPs())) {
            RepTrade repTrade2 = new RepTrade();
            BeanUtil.copyPropertiesIgnoreNull(repTrade2, repTrade);
//                第一笔
            repTrade.setRepMoneyCny(ArithmeticUtil.multiply(deal.getPrinAmt(), 最低收盘价F));
            repTrade.setRepOccurMoney(deal.getPrinAmt());
            repTrade.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_资产卖出或到期.getValue());
            repTrade.setRepMoneyFlowTypeExp("银行存款提前还本本金");
            result.add(repTrade);
//                第二笔
            repTrade2.setRepBankTradeCode(deal.getTradeId());
            repTrade2.setRepMoneyCny(ArithmeticUtil.multiply(deal.getIntAmt(), 最低收盘价F));
            repTrade2.setRepOccurMoney(deal.getIntAmt());
            repTrade2.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_投资资产所得收益.getValue());
            repTrade2.setRepMoneyFlowTypeExp("银行存款提前还本利息收入");
            result.add(repTrade2);
        } else if (DictCodeList.交易方向_到期.getValue().equals(deal.getPs())) {
            repTrade.setRepMoneyCny(ArithmeticUtil.multiply(deal.getPrinAmt(), 最低收盘价F));
            repTrade.setRepOccurMoney(deal.getPrinAmt());
            repTrade.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_资产卖出或到期.getValue());
            repTrade.setRepMoneyFlowTypeExp("银行存款到期本金");
            result.add(repTrade);
        } else if (DictCodeList.交易方向_收息.getValue().equals(deal.getPs())) {
            repTrade.setRepMoneyCny(ArithmeticUtil.multiply(deal.getTradeAmt(), 最低收盘价F));
            repTrade.setRepOccurMoney(deal.getTradeAmt());
            repTrade.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_投资资产所得收益.getValue());
            repTrade.setRepMoneyFlowTypeExp("银行存款利息收入");
            result.add(repTrade);
        }
        return result;
    }

//    private List<RepTrade> gen交易报送信息(TrdDepoDurationDto entity, List<WflTrdProductDeal> wflTrdProductDeals) {
//        List<RepTrade> result = new ArrayList<>();
//// 产品登记编码
//        Map<String, Object> params = new HashMap<>();
//        params.put("FINPROD_TYPE_TG_F06", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
//        params.put("portfolioId", entity.getPortfolioId());
//        String 产品登记编码 = trdRepoDurationMapper.findPrdRegCode(params);
//        if (StringUtil.isEmpty(产品登记编码)) {
//            return result;
//        }
////            折算人民币金额（元）
//        BigDecimal 最低收盘价 = BigDecimal.ONE;
//        if (!DictCodeList.币种_人民币_CNY.getValue().equals(entity.getCcy())) {
//            最低收盘价 = periodCommonService.find最低收盘价(entity.getCcy(), DateUtil.format(entity.getSettleDate()));
//        }
//        BigDecimal 最低收盘价F = 最低收盘价;
//        wflTrdProductDeals.forEach(wflTrdProductDeal -> {
//            RepTrade repTrade = new RepTrade();
//            repTrade.setRepIssuePartyId(entity.getRepIssuePartyId());
//            repTrade.setRepFinprodRegId(产品登记编码);
//            repTrade.setRepBankTradeCode(wflTrdProductDeal.getTradeId());
//            repTrade.setRepBankAssetCode(entity.getFinprodMarketId());
//            repTrade.setRepCcy(entity.getCcy());
//            repTrade.setRepCount(wflTrdProductDeal.getShareAmt());
//            repTrade.setRepTradeDate(entity.getSettleDate());
//            repTrade.setRepTradeComponent(entity.getCounterId());
//            repTrade.setRepDelCode(wflTrdProductDeal.getTradeId());
//            repTrade.setRepRemark(entity.getRemark());
//            repTrade.setRepSendStatus(DictCodeList.是否_是.getValue());
//            repTrade.setRepStatusDate(platformDateImpl.getSysDate());
//            if(StringUtil.equals("draw",entity.getOpType())) {
//                RepTrade repTrade2 = new RepTrade();
//                BeanUtil.copyPropertiesIgnoreNull(repTrade2, repTrade);
////                第一笔
//                repTrade.setRepMoneyCny(ArithmeticUtil.multiply(wflTrdProductDeal.getPrinAmt(), 最低收盘价F));
//                repTrade.setRepOccurMoney(wflTrdProductDeal.getPrinAmt());
//                repTrade.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_资产卖出或到期.getValue());
//                repTrade.setRepMoneyFlowTypeExp("银行存款提前还本本金");
//                result.add(repTrade);
////                第二笔
//                repTrade2.setRepBankTradeCode(wflTrdProductDeal.getTradeId());
//                repTrade2.setRepMoneyCny(ArithmeticUtil.multiply(wflTrdProductDeal.getIntAmt(), 最低收盘价F));
//                repTrade2.setRepOccurMoney(wflTrdProductDeal.getIntAmt());
//                repTrade2.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_投资资产所得收益.getValue());
//                repTrade2.setRepMoneyFlowTypeExp("银行存款提前还本利息收入");
//                result.add(repTrade2);
//            }
//            else if (DictCodeList.交易方向_到期.getValue().equals(wflTrdProductDeal.getPs())) {
//                repTrade.setRepMoneyCny(ArithmeticUtil.multiply(wflTrdProductDeal.getPrinAmt(), 最低收盘价F));
//                repTrade.setRepOccurMoney(wflTrdProductDeal.getPrinAmt());
//                repTrade.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_资产卖出或到期.getValue());
//                repTrade.setRepMoneyFlowTypeExp("银行存款到期本金");
//                result.add(repTrade);
//            }
//            else if (DictCodeList.交易方向_收息.getValue().equals(wflTrdProductDeal.getPs())) {
//                repTrade.setRepMoneyCny(ArithmeticUtil.multiply(wflTrdProductDeal.getTradeAmt(), 最低收盘价F));
//                repTrade.setRepOccurMoney(wflTrdProductDeal.getTradeAmt());
//                repTrade.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_投资资产所得收益.getValue());
//                repTrade.setRepMoneyFlowTypeExp("银行存款利息收入");
//                result.add(repTrade);
//            }
//        });
//        return result;
//    }
    /**
     * 画面查询方法-市场代码
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    public Page<SelectOptionDto> getFinprodMarketId(int page, int length, Map<String, Object> param) {
        return queryForPageForSelectReturn(page, length, () -> trdDepoDurationMapper.getFinprodMarketId(param));
    }

    /**
     * 查询组合代码 <功能详细描述>
     * 
     * @param page
     * @param length
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getPortfolioId(int page, int length, Map<String, Object> param) {
        return queryForPageForSelectReturn(page, length, () -> trdDepoDurationMapper.getPortfolioId(param));
    }

    /**
     * 查询所有的符合数据 <功能详细描述>
     * 
     * @param param 参数
     * @return 查询的符合结果
     * @see
     * @since 1.0
     */
    public List<TrdDepoDurationDto> getAllDataForCheck(Map<String, Object> param) {
        setParams(param);
        Date plateDate = platformDateImpl.getSysDate();
        if (param.get(BussinessConstant.PLUS_DAYS) != null) {
            plateDate = DateUtil.parse(
                    DateUtil.plusDays(DateUtil.format(plateDate), (Integer) param.get(BussinessConstant.PLUS_DAYS)));
        }
        param.put("PLATEFORM_DATE", plateDate);
        return trdDepoDurationMapper.findPage(param);
    }

    /**
     *
     * 根据汇总交易编号查询交易 <功能详细描述>
     * 
     * @param busiCode 汇总交易编号
     * @return
     * @see
     * @since 1.0
     */
    public List<TrdProductDeal> findDealByTotalId(String busiCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("totleTradeId", busiCode);
        return periodCommonMapper.findDealByTotalId(params);
    }

    /**
     * 查询利息明细信息 <功能详细描述>
     * 
     * @param params 参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public List<FinAssetIntInfo> findAssetIntInfo(Map<String, Object> params) {
        setParams(params);
        return trdDepoDurationMapper.findAssetIntInfo(params);
    }


    public String doRevoke(TrdDepoDurationDto durationDto) {
        Map<String, Object> params = new HashMap<>();
        params.put("bizCode", durationDto.getpTradeId());
        params.put("workflowId", durationDto.getWorkflowId());
        params.put("workflowStatus", DictCodeList.数据状态_删除中.getValue());
        params.put("user", durationDto.getCreateUser());
        params.put("dept", StringUtil.isEmpty(durationDto.getCreateDept()) ? "dept" : durationDto.getCreateDept());
        params.put("time", durationDto.getCreateTime());
        params.put("revokeReason", durationDto.getRevokeReason());
        investPeriodCommonService.insertWflTable(params);
        return Boolean.TRUE.toString();
    }
    /**
     * 
     * 获取业务编号下拉框
     * <功能详细描述>
     * @param length 分页参数
     * @param page 分页参数
     * @param param 参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getBusiCode(int page, int length, Map<String, Object> param){
        param.put("TRADE_STATUS_CX", DictCodeListExt1.交易状态_撤销.getValue());
        return queryForPageForSelectReturn(page, length, () -> trdDepoDurationMapper.getBusiCode(param));
    }


    public int countTradeForDemand(Map<String,Object> map) {
        return trdDepoDurationMapper.countTradeForDemand(map);
    }
}
