package com.joyintech.tams.invest.trade.transfer.fund.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.settle.dto.BussSettleOrderParameterDto;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.threadservice.JasperUtil;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.trade.transfer.fund.dto.FundAssetTransfer001Dto;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 基金类资产转仓service
 *
 * @author liupengfei
 */
@Service
public class FundAssetTransfer001Service extends BaseService<FundAssetTransfer001Dto> implements BaseProcessService<FundAssetTransfer001Dto> {
    /**
     * baseGetBusinessId
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;
    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderService;
    @Autowired
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;
    @Autowired
    private BaseTradeProductDealCommService baseTradeProductDealCommService;
    @Autowired
    private WflTrdProductDealMapper wflTrdProductDealMapper;
    @Autowired
    private WflTrdProductDealAddMapper wflTrdProductDealAddMapper;
    @Autowired
    private WflPaTrdProductDealMapper wflPaTrdProductDealMapper;
    @Autowired
    private WflStlPayOrdersMapper wflStlPayOrdersMapper;
    @Autowired
    private PtlTradeMapper ptlTradeMapper;
    @Autowired
    private PaTrdProductDealMapper paTrdProductDealMapper;
    @Autowired
    private RepTradeMapper repTradeMapper;
    @Autowired
    private JasperUtil jasperUtil;
    @Autowired
    private IPlatformDate iPlatformDate;

    @Override
    public void doComplete(FundAssetTransfer001Dto dto, OperationType operationType) {
        String workflowId = "";
        for (FundAssetTransfer001Dto input : dto.getFunAssetTransferDtoList()) {
            if (!operationType.equals(OperationType.REVOKE) && !operationType.equals(OperationType.DELETE)) {
                input.setWorkflowId(dto.getWorkflowId());
                input.setInvTransPortf(dto.getInvTransPortf());
                FundAssetTransfer001Dto output = new FundAssetTransfer001Dto();
                BeanUtil.copyPropertiesIgnoreNull(output, input);
                // 构造Entity
                this.prepareEntity(input, dto);

                // 7.1登录各业务表，包括金融产品交易、金融产品附属、划款指令及附件信息
                // 金融产品交易信息DTO.金融产品交易Entity -> 金融产品交易表
                input.getTrdProductDeal().setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());

                // 金融产品交易扩展表Entity -> 金融产品交易扩展表
                PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
                BeanUtil.copyPropertiesIgnoreNull(paTrdProductDeal, input.getWflPaTrdProductDeal());

                if (input.getPtlTrade().size() > 0) {
                    input.getPtlTrade().forEach(ptlTrade -> ptlTradeMapper.insertSelective(ptlTrade));
                }
                paTrdProductDealMapper.insertSelective(paTrdProductDeal);

                // 金融产品交易信息DTO.划款指令表Entity -> 划款指令表
                StlPayOrdersDto stlPayOrder = null;
                List<StlPayOrdersDto> stlPayOrders = new ArrayList<>();
                if (!StringUtil.isEmptyList(input.getBussTrdTransferOrder())) {
                    for (BussTrdTransferOrderDto bttod : input.getBussTrdTransferOrder()) {
                        stlPayOrder = new StlPayOrdersDto();
                        BeanUtil.copyPropertiesIgnoreNull(stlPayOrder, bttod);
                        stlPayOrders.add(stlPayOrder);
                    }
                }

                // OPCIS表Entity TODO

                FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
                finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_有效.getValue());

                BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
                // 金融产品交易
                baseTradeProductDealDto.setTrdProductDeal(input.getTrdProductDeal());
                // 金融产品附属
                baseTradeProductDealDto.setTrdProductDealAdd(input.getTrdProductDealAdd());
                // 划款指令
                if (stlPayOrders != null) {
                    baseTradeProductDealDto.setStlPayOrdersList(stlPayOrders);
                }
                finProductTradeDto.setBaseTradeProductDealDtos(Arrays.asList(baseTradeProductDealDto));

                // 调用业务组件.【金融产品交易共通.交易产品接口】, insert
                // 调用业务组件.【金融产品交易共通.交易产品接口】,写入[实际]的组合持仓流水及现金流水
                baseTradeProductDealCommService.execute(finProductTradeDto);

                if (StringUtil.isNotEmpty(input.getActPortfolioId())
                        && !input.getActPortfolioId().equals(input.getPortfolioId())) {
                    finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                            baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(
                                    input.getActPortfolioId()));
                    baseTradeProductDealCommService.insertPosition(finProductTradeDto);
                    finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                            baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(
                                    input.getPortfolioId()));
                }
                // 插入金融产品交易扩展表
                Map<String, Object> map = new HashMap<>();
                map.put("product", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
                map.put("portfolioId", input.getPortfolioId());
                // 插入交易报送表
                String prodCode = simpleRestTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/trade/transfer/fund/FundAssetTransfer/selProdCode", map, String.class);
                if (StringUtil.isNotEmpty(prodCode)) {
                    RepTrade repentity = new RepTrade();
                    repentity.setRepFinprodRegId(prodCode);
                    repentity.setRepBankTradeCode(input.getTradeId());
                    repentity.setRepBankAssetCode(input.getFinprodId());
                    repentity.setRepCcy(input.getCcy());
                    repentity.setRepOccurMoney(input.getTradeAmt());
                    repentity.setRepIssuePartyId(input.getIssuePartyId());
                    repentity.setRepTradeComponent(input.getCounterId());
                    BigDecimal cloPrice = new BigDecimal(1);
                    if (!StringUtil.equals(input.getCcy(), DictCodeList.币种_人民币_CNY.getValue())) {
                        map.put("ccy", input.getCcy());
                        map.put("cny", DictCodeList.币种_人民币_CNY.getValue());
                        map.put("tradDate", input.getTradeDate());
                        BigDecimal num = simpleRestTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/trade/transfer/fund/FundAssetTransfer/selCloPrice", map, BigDecimal.class);
                        if (num != null) {
                            cloPrice = num;
                        }
                    }
                    repentity.setRepMoneyCny(input.getTradeAmt().multiply(cloPrice));
                    repentity.setRepCount(input.getTradePrin());
                    repentity.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_资产卖出或到期.getValue());
                    repentity.setRepTradeDate(input.getTradeDate());
                    repentity.setRepTradeComponent(input.getCounterTrader());
                    repentity.setRepSendStatus(DictCodeList.是否_是.getValue());
                    if (iPlatformDate != null) {
                        Date sysdate = iPlatformDate.getSysDate();
                        repentity.setRepStatusDate(sysdate);
                    }
                    repentity.setRepRemark(input.getRemark());
                    // 调交易报送组件
                    repTradeMapper.insert(repentity);
                }
                map.put("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
                map.put("finprodId", input.getFinprodId());
                simpleRestTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/trade/transfer/fund/FundAssetTransfer/updateRep", map, Integer.class);

                // 一体化对应etliu start
                // 所有交易生效后调用的共通方法
                AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                inputDto.setProdid(input.getInvTransPortf());
                inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
                inputDto.setTrdProductDeal(input.getTrdProductDeal());
                // 通道交易 新增 强成西 2019/03/26 start
                inputDto.setTrdProductDealAdd(input.getTrdProductDealAdd());
                // 通道交易 新增 强成西 2019/03/26 end
                AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
                // 一体化对应etliu end

                jasperExecute(input, true);

                //转入产品组合登陆------------------------------------------------------------------------------------------------------------
                // 构造Entity
                this.prepareEntity1(output, dto);

                // 7.1登录各业务表，包括金融产品交易、金融产品附属、划款指令及附件信息
                // 金融产品交易信息DTO.金融产品交易Entity -> 金融产品交易表
                output.getTrdProductDeal().setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());

                // 金融产品交易扩展表Entity -> 金融产品交易扩展表
                PaTrdProductDeal paTrdProductDeal1 = new PaTrdProductDeal();
                BeanUtil.copyPropertiesIgnoreNull(paTrdProductDeal1, output.getWflPaTrdProductDeal());

                if (output.getPtlTrade().size() > 0) {
                    output.getPtlTrade().forEach(ptlTrade -> ptlTradeMapper.insertSelective(ptlTrade));
                }
                paTrdProductDealMapper.insertSelective(paTrdProductDeal1);
                // 金融产品交易信息DTO.划款指令表Entity -> 划款指令表
                StlPayOrdersDto stlPayOrder1 = null;
                List<StlPayOrdersDto> stlPayOrders1 = new ArrayList<>();
                if (!StringUtil.isEmptyList(output.getBussTrdTransferOrder())) {
                    for (BussTrdTransferOrderDto bttod : output.getBussTrdTransferOrder()) {
                        stlPayOrder1 = new StlPayOrdersDto();
                        BeanUtil.copyPropertiesIgnoreNull(stlPayOrder1, bttod);
                        stlPayOrders1.add(stlPayOrder1);
                    }
                }

                // OPCIS表Entity TODO

                FinProductTradeDto finProductTradeDto1 = new FinProductTradeDto();
                finProductTradeDto1.setWorkflowStatus(DictCodeListExt1.交易流程状态_有效.getValue());

                BaseTradeProductDealDto baseTradeProductDealDto1 = new BaseTradeProductDealDto();
                // 金融产品交易
                baseTradeProductDealDto1.setTrdProductDeal(output.getTrdProductDeal());
                // 金融产品附属
                baseTradeProductDealDto1.setTrdProductDealAdd(output.getTrdProductDealAdd());
                // 划款指令
                if (stlPayOrders1 != null) {
                    baseTradeProductDealDto1.setStlPayOrdersList(stlPayOrders1);
                }
                finProductTradeDto1.setBaseTradeProductDealDtos(Arrays.asList(baseTradeProductDealDto1));

                // 调用业务组件.【金融产品交易共通.交易产品接口】, insert
                // 调用业务组件.【金融产品交易共通.交易产品接口】,写入[实际]的组合持仓流水及现金流水
                baseTradeProductDealCommService.execute(finProductTradeDto1);

                if (StringUtil.isNotEmpty(output.getActPortfolioId())
                        && !output.getActPortfolioId().equals(output.getPortfolioId())) {
                    finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                            baseTradeProductDealDto2 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(
                                    output.getActPortfolioId()));
                    baseTradeProductDealCommService.insertPosition(finProductTradeDto);
                    finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                            baseTradeProductDealDto2 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(
                                    output.getPortfolioId()));
                }
                // 插入金融产品交易扩展表
                Map<String, Object> map1 = new HashMap<>();
                map1.put("product", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
                map1.put("portfolioId", output.getPortfolioId());
                // 插入交易报送表
                String prodCode1 = simpleRestTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/trade/transfer/fund/FundAssetTransfer/selProdCode", map1, String.class);
                if (StringUtil.isNotEmpty(prodCode1)) {
                    RepTrade repentity = new RepTrade();
                    repentity.setRepFinprodRegId(prodCode);
                    repentity.setRepBankTradeCode(output.getTradeId());
                    repentity.setRepBankAssetCode(output.getFinprodId());
                    repentity.setRepCcy(output.getCcy());
                    repentity.setRepOccurMoney(output.getTradeAmt());
                    repentity.setRepIssuePartyId(output.getIssuePartyId());
                    repentity.setRepTradeComponent(output.getCounterId());
                    BigDecimal cloPrice = new BigDecimal(1);
                    if (!StringUtil.equals(output.getCcy(), DictCodeList.币种_人民币_CNY.getValue())) {
                        map1.put("ccy", output.getCcy());
                        map1.put("cny", DictCodeList.币种_人民币_CNY.getValue());
                        map1.put("tradDate", output.getTradeDate());
                        BigDecimal num = simpleRestTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/trade/transfer/fund/FundAssetTransfer/selCloPrice", map, BigDecimal.class);
                        if (num != null) {
                            cloPrice = num;
                        }
                    }
                    repentity.setRepMoneyCny(output.getTradeAmt().multiply(cloPrice));
                    repentity.setRepCount(output.getTradePrin());
                    repentity.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_资产卖出或到期.getValue());
                    repentity.setRepTradeDate(output.getTradeDate());
                    repentity.setRepTradeComponent(output.getCounterTrader());
                    repentity.setRepSendStatus(DictCodeList.是否_是.getValue());
                    if (iPlatformDate != null) {
                        Date sysdate = iPlatformDate.getSysDate();
                        repentity.setRepStatusDate(sysdate);
                    }
                    repentity.setRepRemark(output.getRemark());
                    // 调交易报送组件
                    repTradeMapper.insert(repentity);
                }
                map1.put("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
                map1.put("finprodId", output.getFinprodId());
                simpleRestTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/trade/transfer/fund/FundAssetTransfer/updateRep", map1, Integer.class);


                // 一体化对应etliu start
                // 所有交易生效后调用的共通方法
                AllTradeCommonInputDto outputDto = new AllTradeCommonInputDto();
                outputDto.setProdid(output.getPortfolioId());
                outputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
                outputDto.setTrdProductDeal(output.getTrdProductDeal());
                // 通道交易 新增 强成西 2019/03/26 start
                outputDto.setTrdProductDealAdd(output.getTrdProductDealAdd());
                // 通道交易 新增 强成西 2019/03/26 end
                AllTradeCommonUtil.handleAfterTradeComplete(outputDto);

                jasperExecute(input, false);
            }

            // 7.2删除原业务流程表中的交易记录
            workflowId = input.getWorkflowId();
            if (StringUtil.isBlank(workflowId)) {
                WflTrdProductDealKey wflTrdProductDealKey = new WflTrdProductDealKey();
                wflTrdProductDealKey.setTradeId(input.getTradeId());
                wflTrdProductDealKey.setWorkflowStatus(
                        DictCodeListExt1.交易流程状态_流程中.getValue());
                WflTrdProductDeal wflTrdProductDeal = bussCommonMapperDelegateService.selectByMultiPrimaryKey(
                        WflTrdProductDealMapper.class, wflTrdProductDealKey, WflTrdProductDeal.class);

                if (wflTrdProductDeal != null) {
                    workflowId = wflTrdProductDeal.getWorkflowId();
                }
            }
        }
        this.doDelete(workflowId);
    }

    public void jasperExecute(FundAssetTransfer001Dto entity, Boolean isMC) {
        if ("001".equals(CacheUtil.getSysParam("CUSTOMER_BANK_CODE"))) {
            // 生成划款指令附件:调用自定义接口项目生成Jasper报表 add by zhangJin 2019-07-29 start
            TrdProductDeal trdProductDeal = new TrdProductDeal();
            trdProductDeal.setTradeId(entity.getTradeId());
            if (isMC) {
                trdProductDeal.setPs(DictCodeList.交易方向_卖出.getValue());
            } else {
                trdProductDeal.setPs(DictCodeList.交易方向_买入.getValue());
            }
            trdProductDeal.setPs(entity.getPs());
            trdProductDeal.setFinprodId(entity.getFinprodId());

            jasperUtil.jasperExecute(Arrays.asList(trdProductDeal),simpleRestTemplate);
            // 生成划款指令附件:调用自定义接口项目生成Jasper报表 add by zhangJin 2019-07-29 end
        }
    }

    /**
     * 从带转仓列表带出参数
     *
     * @return entity
     */
    public FundAssetTransfer001Dto setData(Map<String, Object> param) {
        FundAssetTransfer001Dto dto = new FundAssetTransfer001Dto();
        //交易日期
        dto.setTradeDate(iPlatformDate.getSysDate());
        //资产代码
        dto.setFinprodMarketId((String) param.get("finprodMarketId"));
        //资产名称
        dto.setFinprodAbbr((String) param.get("finprodName"));
        //币种
        dto.setCcy((String) param.get("ccy"));
        //通道代码
        dto.setChlId((String) param.get("chlId"));
        //持仓份额
        BigDecimal tradePrinOrg = BigDecimal.ZERO;
        if (StringUtil.isNotEmpty((String) param.get("posAmt"))) {
            tradePrinOrg = new BigDecimal((String) param.get("posAmt"));
        }
        dto.setTradePrinOrg(tradePrinOrg);
        //剩余可转仓份额
        dto.setRollablePrin(tradePrinOrg);
        //金融产品代码
        dto.setFinprodIdOrg((String) param.get("finprodId"));
        //原组合代码
        dto.setInvTransPortf((String) param.get("portfolioId"));
        //交易日历
        dto.setCalendarId((String) param.get("calendarId"));
        //资产类型
        dto.setAssetType((String) param.get("assetType"));

        FinProductTypeKey key = new FinProductTypeKey();
        key.setFinprodId((String) param.get("finprodId"));
        key.setBranch(Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
        FinProductType type = bussCommonMapperDelegateService.selectByMultiPrimaryKey(FinProductTypeMapper.class,key,FinProductType.class);
        if(StringUtil.isNotEmpty(type)){
            dto.setFundType(type.getType6());
        }
        return dto;
    }

    /**
     * 查询组合信息
     * * @return list
     */
    public List<SelectOptionDto> selectPortfolioId(Map<String, Object> param) {
        return simpleRestTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME + "/trade/transfer/fund/FundAssetTransfer/selectPortfolioId",
                param, SelectOptionDto.class);
    }

    /**
     * 非货币基金单位净值初始化值
     *
     * @param param 参数
     * @return 非货币基金单位净值初始化值
     */
    public BigDecimal findInitCarryOverQty(Map<String, Object> param) {
        return simpleRestTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/trade/transfer/fund/FundAssetTransfer/findInitCarryOverQty",
                param, BigDecimal.class);
    }

    /**
     * 设置列表信息
     *
     * @param fundDto dto
     * @return 列表信息
     */
    public FundAssetTransfer001Dto setProjectListInfoDto(FundAssetTransfer001Dto fundDto) {
        FundAssetTransfer001Dto dto = new FundAssetTransfer001Dto();
        if (StringUtil.isNotEmpty(fundDto)) {
            dto.setPortfolioId(fundDto.getPortfolioId());
            dto.setPortfolioName(fundDto.getPortfolioName());
            dto.setFinprodMarketId(fundDto.getFinprodMarketId());
            dto.setTradeDate(fundDto.getTradeDate());
            dto.setTradePrin(fundDto.getTradePrin());
            dto.setUnitCprice(fundDto.getUnitCprice());
            dto.setCarryOverQty(fundDto.getCarryOverQty());
            dto.setTradeAmt(fundDto.getTradeAmt());
            dto.setFinprodId(fundDto.getFinprodIdOrg());
            dto.setTradeId(generateTradeId(true));
        }
        return dto;
    }

    /**
     * 生成交易编号 转出=赎回 转入=申购
     *
     * @param isZC 是转出
     * @return 交易编号
     */
    private String generateTradeId(Boolean isZC) {
        String tradeId = "";
        if (baseGetBusinessId != null) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            if (isZC) {
                codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_货币基金赎回);
            } else {
                codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_货币基金申购);
            }
            tradeId = baseGetBusinessId.buildBussinessCode(codeParameter);
        } else {
            tradeId = IDGenerator.generate();
        }
        return tradeId;
    }

    //查询单位净价
    public Map<String, BigDecimal> findUnitCprice(FundAssetTransfer001Dto dto) {
        Map<String, Object> param = new HashMap<>();
        Map<String, BigDecimal> unitCpriceMap = new HashMap<>();
        param.put("finprodId", dto.getFinprodIdOrg());
        param.put("tardeStatus01", DictCodeListExt1.交易状态_初始.getValue());
        param.put("yesorno_no", DictCodeListExt1.是否_否.getValue());
        param.put("tradeDate", DateUtil.format(dto.getTradeDate()));
        List<FundAssetTransfer001Dto> list = simpleRestTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME + "/trade/transfer/fund/FundAssetTransfer/findUnitCprice", param, FundAssetTransfer001Dto.class);
        for (FundAssetTransfer001Dto fundDto : list) {
            if (!StringUtil.isNotEmpty(unitCpriceMap.get(fundDto.getPortfolioId()))) {
                unitCpriceMap.put(fundDto.getPortfolioId(), fundDto.getUnitCprice());
            }
        }
        return unitCpriceMap;
    }

    /**
     * 工作流doInsert
     *
     * @param entity        entity
     * @param operationType operationType
     */
    @Override
    public void doInsert(FundAssetTransfer001Dto entity, OperationType operationType) {
        // 新建交易
        if (!operationType.equals(OperationType.REVOKE) && !operationType.equals(OperationType.DELETE)) {
            this.handleBussFlowData(entity);
        }
    }

    /**
     * 提交时登录业务流程表
     *
     * @param entity 画面参数dto
     * @see
     * @since 1.0
     */
    private void handleBussFlowData(FundAssetTransfer001Dto entity) {
        if (!StringUtil.equals("submit", entity.getOperateType())) {
            return;
        }
        if (StringUtil.isNotEmpty(entity.getFunAssetTransferDtoList())) {
            for (FundAssetTransfer001Dto input : entity.getFunAssetTransferDtoList()) {
                input.setWorkflowId(entity.getWorkflowId());
                input.setSplitTradeId(entity.getTradeId());
                input.setInvTransPortf(entity.getInvTransPortf());

                FundAssetTransfer001Dto output = new FundAssetTransfer001Dto();
                BeanUtil.copyPropertiesIgnoreNull(output, input);
                //转出产品组合登录
                // 构造Entity
                this.prepareEntity(input, entity);

                // 调用授信共同 sjj start
                TrdProductDeal TrdProductDeal = new TrdProductDeal();
                BeanUtil.copyPropertiesIgnoreNull(TrdProductDeal, input.getTrdProductDeal());
                AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                inputDto.setTrdProductDeal(TrdProductDeal);
                AllTradeCommonUtil.handleBeforeTradeComplete(inputDto, false);
                // 调用授信共同 sjj end

                // 金融产品交易业务流程信息
                input.getWflTrdProductDeal().setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                // 金融产品交易附属业务流程表
                input.getWflTrdProductDealAdd().setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                // 金融产品交易扩展业务流程表
                input.getWflPaTrdProductDeal().setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());

                // 批量插入
                wflTrdProductDealMapper.insertSelective(input.getWflTrdProductDeal());
                wflTrdProductDealAddMapper.insertSelective(input.getWflTrdProductDealAdd());
                wflPaTrdProductDealMapper.insertSelective(input.getWflPaTrdProductDeal());

                // 金融产品交易信息DTO.划款指令表Entity -> 划款指令业务流程表
                if (!StringUtil.isEmptyList(input.getBussTrdTransferOrder())) {
                    WflStlPayOrders wflStlPayOrders = new WflStlPayOrders();
                    BeanUtil.copyPropertiesIgnoreNull(wflStlPayOrders,
                            input.getBussTrdTransferOrder().get(0));
                    wflStlPayOrders.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                    wflStlPayOrdersMapper.insertSelective(wflStlPayOrders);
                }

                // 调用业务组件.【金融产品交易共通.交易产品接口】,写入[可用]的组合持仓流水及现金流水
                FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
                finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
                baseTradeProductDealDto.setTrdProductDeal(input.getTrdProductDeal());
                baseTradeProductDealDto.setTrdProductDealAdd(input.getTrdProductDealAdd());

                //金融产品交易信息DTO.划款指令表Entity -> 划款指令表
                StlPayOrdersDto stlPayOrder = null;
                List<StlPayOrdersDto> stlPayOrders = new ArrayList<>();
                if (!StringUtil.isEmptyList(input.getBussTrdTransferOrder())) {
                    for (BussTrdTransferOrderDto bttod : input.getBussTrdTransferOrder()) {
                        stlPayOrder = new StlPayOrdersDto();
                        BeanUtil.copyPropertiesIgnoreNull(stlPayOrder, bttod);
                        stlPayOrders.add(stlPayOrder);
                    }
                }
                if (!StringUtil.isEmptyList(stlPayOrders)) {
                    baseTradeProductDealDto.setStlPayOrdersList(stlPayOrders);
                }
                finProductTradeDto.setBaseTradeProductDealDtos(Arrays.asList(baseTradeProductDealDto));

                baseTradeProductDealCommService.execute(finProductTradeDto);
                if (StringUtil.isNotEmpty(input.getActPortfolioId()) && !input.getActPortfolioId().equals(input.getPortfolioId())) {
                    finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                            baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(input.getActPortfolioId()));
                    baseTradeProductDealCommService.insertPosition(finProductTradeDto);
                    finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                            baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(input.getPortfolioId()));
                }

                //转入产品组合登录
                // 构造Entity
                this.prepareEntity1(output, entity);

                // 调用授信共同 sjj start
                TrdProductDeal TrdProductDeal1 = new TrdProductDeal();
                BeanUtil.copyPropertiesIgnoreNull(TrdProductDeal1, output.getTrdProductDeal());
                AllTradeCommonInputDto outputDto = new AllTradeCommonInputDto();
                outputDto.setTrdProductDeal(TrdProductDeal1);
                AllTradeCommonUtil.handleBeforeTradeComplete(outputDto, false);
                // 调用授信共同 sjj end

                // 金融产品交易业务流程信息
                output.getWflTrdProductDeal().setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                // 金融产品交易附属业务流程表
                output.getWflTrdProductDealAdd().setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                // 金融产品交易扩展业务流程表
                output.getWflPaTrdProductDeal().setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());

                // 批量插入
                wflTrdProductDealMapper.insertSelective(output.getWflTrdProductDeal());
                wflTrdProductDealAddMapper.insertSelective(output.getWflTrdProductDealAdd());
                wflPaTrdProductDealMapper.insertSelective(output.getWflPaTrdProductDeal());

                // 金融产品交易信息DTO.划款指令表Entity -> 划款指令业务流程表
                if (!StringUtil.isEmptyList(output.getBussTrdTransferOrder())) {
                    WflStlPayOrders wflStlPayOrders = new WflStlPayOrders();
                    BeanUtil.copyPropertiesIgnoreNull(wflStlPayOrders,
                            output.getBussTrdTransferOrder().get(0));
                    wflStlPayOrders.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                    wflStlPayOrdersMapper.insertSelective(wflStlPayOrders);
                }

                // 调用业务组件.【金融产品交易共通.交易产品接口】,写入[可用]的组合持仓流水及现金流水
                FinProductTradeDto finProductTradeDto1 = new FinProductTradeDto();
                finProductTradeDto1.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                BaseTradeProductDealDto baseTradeProductDealDto1 = new BaseTradeProductDealDto();
                baseTradeProductDealDto1.setTrdProductDeal(output.getTrdProductDeal());
                baseTradeProductDealDto1.setTrdProductDealAdd(output.getTrdProductDealAdd());

                //金融产品交易信息DTO.划款指令表Entity -> 划款指令表
                StlPayOrdersDto stlPayOrder1 = null;
                List<StlPayOrdersDto> stlPayOrders1 = new ArrayList<>();
                if (!StringUtil.isEmptyList(output.getBussTrdTransferOrder())) {
                    for (BussTrdTransferOrderDto bttod : output.getBussTrdTransferOrder()) {
                        stlPayOrder1 = new StlPayOrdersDto();
                        BeanUtil.copyPropertiesIgnoreNull(stlPayOrder1, bttod);
                        stlPayOrders1.add(stlPayOrder1);
                    }
                }
                if (!StringUtil.isEmptyList(stlPayOrders1)) {
                    baseTradeProductDealDto1.setStlPayOrdersList(stlPayOrders1);
                }
                finProductTradeDto1.setBaseTradeProductDealDtos(Arrays.asList(baseTradeProductDealDto1));

                baseTradeProductDealCommService.execute(finProductTradeDto1);
                if (StringUtil.isNotEmpty(output.getActPortfolioId()) && !output.getActPortfolioId().equals(output.getPortfolioId())) {
                    finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                            baseTradeProductDealDto2 -> baseTradeProductDealDto2.getTrdProductDealAdd().setPortfolioId(output.getActPortfolioId()));
                    baseTradeProductDealCommService.insertPosition(finProductTradeDto);
                    finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                            baseTradeProductDealDto2 -> baseTradeProductDealDto2.getTrdProductDealAdd().setPortfolioId(output.getPortfolioId()));
                }
            }
        }
    }

    private void prepareEntity(FundAssetTransfer001Dto input, FundAssetTransfer001Dto entity) {
        input.setActPortfolioId(input.getPortfolioId());
        input.setPortfolioId(AllTradeCommonUtil.exchangePortfolio(input.getPortfolioId()));
        input.setIssuePartyId(CacheUtil.getSysParam(BussinessConstant.ISSUE_PARTY_ID));
        //根据原始交易编号查询拆分前投资过度组合信息

        // 金融产品交易Entity 构造开始-----------------------------------------------------------------------
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        //新增：系统生成的交易编号
        trdProductDeal.setTradeId(input.getTradeId());
        //数据字典.基金投资交易
        trdProductDeal.setBusiType(DictCodeList.共通业务类型_基金类资产交易.getValue());
        // 分支序号 = 系统参数.分支序号固定值（和估值核算保持一致，0）
        trdProductDeal.setBranch(Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
        //金融产品代码 = 列表.金融产品代码
        trdProductDeal.setFinprodId(input.getFinprodId());
        // 金融产品类型（估值核算）数据字典.基金
        trdProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
        // 金融产品类型（投管分类）数据字典.基金
        trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        // 交易日 = 画面.交易日
        trdProductDeal.setTradeDate(entity.getTradeDate());
        // 交割日 = 画面.交易日
        trdProductDeal.setSettleDate(entity.getTradeDate());
        // 交割日 = 画面.币种
        trdProductDeal.setCcy(entity.getCcy());
        //单位净价 = 转仓列表.单位净价
        trdProductDeal.setUnitCprice(input.getUnitCprice());
        // 交易份额 = 画面.本金金额
        trdProductDeal.setShareAmt(input.getTradePrin());
        // 交易本金 = 画面.本金金额
        trdProductDeal.setPrinAmt(input.getTradeAmt());
        //利息总额 = 拆分列表.待结转份额
        trdProductDeal.setIntAmt(input.getCarryOverQty());
        // 交易金额 = 画面.本金金额
        trdProductDeal.setTradeAmt(input.getTradeAmt());
        // 交易对手 = 转入产品组合.组合代码
        trdProductDeal.setCounterId(input.getPortfolioId());
        //交易方向 = 卖出
        trdProductDeal.setPs(DictCodeList.交易方向_卖出.getValue());
        // 交易状态 = 交易状态_初始
        trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        // 工作流代码
        trdProductDeal.setWorkflowId(entity.getWorkflowId());
        //备注
        trdProductDeal.setRemark("基金类资产交易转出");
        //会否外部交易
        trdProductDeal.setIsOutTrade(DictCodeList.是否_否.getValue());
        //清算速度
        trdProductDeal.setSettleSpeed(0l);
        //是否撤销 默认否
        trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
        //是否结清 默认是
        trdProductDeal.setIsClean(DictCodeList.是否_是.getValue());
        //交易场所
        trdProductDeal.setTradeMarket(entity.getTradeMarket());
        //交易员
        trdProductDeal.setTrader(entity.getTrader());
        //通道代码
        trdProductDeal.setChlId(entity.getChlId());
        //备注
        input.setRemark("基金类资产交易转出");

        input.setTrdProductDeal(trdProductDeal);

        // 金融产品交易信息DTO.金融产品交易Entity -> 金融产品交易业务流程表
        WflTrdProductDeal wflTrdProductDeal = new WflTrdProductDeal();
        BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDeal, input.getTrdProductDeal());
        input.setWflTrdProductDeal(wflTrdProductDeal);
        // 金融产品交易Entity 构造结束-----------------------------------------------------------------------

        // 金融产品交易附属表Entity 构造开始-----------------------------------------------------------------------
        // 金融产品交易信息DTO.金融产品交易附属表Entity -> 金融产品交易附属业务流程表
        TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
        //交易编号 = 画面.交易编号
        trdProductDealAdd.setTradeId(input.getTradeId());
        //投资组合代码 = 待转出产品组合.组合代码
        trdProductDealAdd.setPortfolioId(entity.getInvTransPortf());
        //是否有结算指令	= 数据字典.是否    默认为否
        // TODO
        // 生成方式 = 数据字典缓存.生成方式.画面录入
        trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
        // 工作流代码
        trdProductDealAdd.setWorkflowId(entity.getWorkflowId());

        input.setTrdProductDealAdd(trdProductDealAdd);

        // 金融产品交易信息DTO.金融产品交易Entity -> 金融产品交易业务流程表
        WflTrdProductDealAdd wflTrdProductDealAdd = new WflTrdProductDealAdd();
        BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDealAdd, input.getTrdProductDealAdd());
        input.setWflTrdProductDealAdd(wflTrdProductDealAdd);
        // 金融产品交易附属表Entity 构造结束-----------------------------------------------------------------------

        // 组合交易表Entity 构造开始-----------------------------------------------------------------------
        List<PtlTrade> ptlTrades = new ArrayList<>();
        PtlTrade ptlTrade = new PtlTrade();
        // 组合代码 = 转出产品组合.组合代码
        ptlTrade.setPortfolioId(entity.getInvTransPortf());
        // 业务编号 = 业务编号 ※2生成的交易编号
        ptlTrade.setBusiNo(input.getTradeId());
        //业务表 = 画面.业务表  默认为金融产品交易表
        ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        // 业务明细代码 = 画面.金融产品代码
        ptlTrade.setBusiId(input.getFinprodId());
        // 业务类型 = 数据字典缓存.共通业务类型.回购投资交易
        ptlTrade.setBusiType(DictCodeList.共通业务类型_基金类资产交易.getValue());
        // 证券托管户代码 = 画面.证券托管账户
        ptlTrade.setSecManageAcctId(entity.getChlId());
        // 实际发生日 = 画面.交易日
        ptlTrade.setSettleDate(entity.getTradeDate());
        // 工作流代码
        ptlTrade.setWorkflowId(input.getWorkflowId());
        ptlTrades.add(ptlTrade);
        if (!input.getPortfolioId().equals(input.getActPortfolioId())) {
            PtlTrade ptlTrade1 = new PtlTrade();
            BeanUtil.copyPropertiesIgnoreNull(ptlTrade1, ptlTrade);
            // 组合代码 = 画面.组合代码
            ptlTrade1.setPortfolioId(input.getActPortfolioId());
            ptlTrades.add(ptlTrade1);
        }
        input.setPtlTrade(ptlTrades);
        // 组合交易表Entity 构造结束-----------------------------------------------------------------------

        // 划款指令表Entity 构造开始----------------------------------------------------------
        if (bussTrdTransferOrderService != null) {
            if (StringUtil.isEmptyList(input.getBussTrdTransferOrder())) {
                // 后台自动生成划款指令
                BussSettleOrderParameterDto orderParameterDto = new BussSettleOrderParameterDto();
                // 是否汇总交易 = 是否_否
                orderParameterDto.setIsTotalTrade(DictCodeList.是否_否.getValue());

                // 金融产品交易Entity = 上述构造的金融产品交易Entity
                TrdProductDeal orderParameterDeal = new TrdProductDeal();
                // 交易日期 = 画面.交易日
                orderParameterDeal.setTradeDate(entity.getTradeDate());
                // 交易方向 = 交易方向_买入
                orderParameterDeal.setPs(trdProductDeal.getPs());
                // 币种 = 画面.币种
                orderParameterDeal.setCcy(entity.getCcy());
                // 交易金额 = 画面.本金金额
                orderParameterDeal.setTradeAmt(input.getTradeAmt());
                // 交易对手 = 画面.交易对手
                orderParameterDeal.setCounterId(trdProductDeal.getCounterId());
                orderParameterDto.setDeal(orderParameterDeal);

                // 拆借类型 = null
                orderParameterDto.setBankLendingType(null);
                // 组合代码 = 画面.组合代码
                orderParameterDto.setPortfolioId(trdProductDealAdd.getPortfolioId());

                BussTrdTransferOrderDto resultOrderDto = bussTrdTransferOrderService.generateOrder(orderParameterDto);
                if (resultOrderDto != null) {
                    input.setBussTrdTransferOrder(Arrays.asList(resultOrderDto));
                }
            }
            if (!StringUtil.isEmptyList(input.getBussTrdTransferOrder())) {
                BussTrdTransferOrderDto bttod = input.getBussTrdTransferOrder().get(0);
                // 交易编号 = 业务编号 ※2生成的交易编号
                bttod.setTradeId(input.getTradeId());
                // 指令状态 = 指令状态_新建
                bttod.setOrderStatus(DictCodeListExt1.指令状态_新建.getValue());
                // 工作流代码
                bttod.setWorkflowId(input.getWorkflowId());
                input.setBussTrdTransferOrder(bussTrdTransferOrderService.save(input.getBussTrdTransferOrder()));
            }
        }
        // 划款指令表Entity 构造结束----------------------------------------------------------

        // 金融产品交易扩展Entity 构造开始----------------------------------------------------------
        PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
        //交易编号
        paTrdProductDeal.setTradeId(input.getTradeId());
        //平安业务类型	= 货币基金/非货币基金赎回确认
        if (StringUtil.equals(DictCodeList.基金类资产类型_货币基金.getValue(), entity.getAssetType())) {
            paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_货币基金赎回确认.getValue());
        } else {
            paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_非货币基金赎回确认.getValue());
        }
        paTrdProductDeal.setDeptCode(entity.getDeptCode());

        paTrdProductDeal.setWorkflowId(entity.getWorkflowId());

        input.setPaTrdProductDeal(paTrdProductDeal);

        WflPaTrdProductDeal wflPaTrdProductDeal = new WflPaTrdProductDeal();

        BeanUtil.copyPropertiesIgnoreNull(wflPaTrdProductDeal, paTrdProductDeal);

        input.setWflPaTrdProductDeal(wflPaTrdProductDeal);
        // 金融产品交易扩展Entity 构造结束----------------------------------------------------------
    }

    private void prepareEntity1(FundAssetTransfer001Dto output, FundAssetTransfer001Dto entity) {
        //生成交易编号
        output.setTradeId(generateTradeId(false));
        output.setActPortfolioId(output.getPortfolioId());
        output.setPortfolioId(AllTradeCommonUtil.exchangePortfolio(output.getPortfolioId()));
        output.setIssuePartyId(CacheUtil.getSysParam(BussinessConstant.ISSUE_PARTY_ID));
        //根据原始交易编号查询拆分前投资过度组合信息

        // 金融产品交易Entity 构造开始-----------------------------------------------------------------------
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        //新增：系统生成的交易编号
        trdProductDeal.setTradeId(output.getTradeId());
        //数据字典.基金投资交易
        trdProductDeal.setBusiType(DictCodeList.共通业务类型_基金类资产交易.getValue());
        // 分支序号 = 系统参数.分支序号固定值（和估值核算保持一致，0）
        trdProductDeal.setBranch(Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
        //金融产品代码 = 列表.金融产品代码
        trdProductDeal.setFinprodId(output.getFinprodId());
        // 金融产品类型（估值核算）数据字典.基金
        trdProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
        // 金融产品类型（投管分类）数据字典.基金
        trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        // 交易日 = 画面.交易日
        trdProductDeal.setTradeDate(entity.getTradeDate());
        // 交割日 = 画面.交易日
        trdProductDeal.setSettleDate(entity.getTradeDate());
        // 交割日 = 画面.币种
        trdProductDeal.setCcy(entity.getCcy());
        //单位净价 = 转仓列表.单位净价
        trdProductDeal.setUnitCprice(output.getUnitCprice());
        // 交易份额 = 画面.本金金额
        trdProductDeal.setShareAmt(output.getTradePrin());
        // 交易本金 = 画面.本金金额
        trdProductDeal.setPrinAmt(output.getTradeAmt());
        //利息总额 = 拆分列表.待结转份额
        trdProductDeal.setIntAmt(output.getCarryOverQty());
        // 交易金额 = 画面.本金金额
        trdProductDeal.setTradeAmt(output.getTradeAmt());
        // 交易对手 = 转入产品组合.组合代码
        trdProductDeal.setCounterId(entity.getInvTransPortf());
        //交易方向 = 买入
        trdProductDeal.setPs(DictCodeList.交易方向_买入.getValue());
        // 交易状态 = 交易状态_初始
        trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        // 工作流代码
        trdProductDeal.setWorkflowId(entity.getWorkflowId());
        //备注
        trdProductDeal.setRemark("基金类资产交易转入");
        //会否外部交易
        trdProductDeal.setIsOutTrade(DictCodeList.是否_否.getValue());
        //清算速度
        trdProductDeal.setSettleSpeed(0l);
        //是否撤销 默认否
        trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
        //是否结清 默认是
        trdProductDeal.setIsClean(DictCodeList.是否_是.getValue());
        //交易场所
        trdProductDeal.setTradeMarket(entity.getTradeMarket());
        //交易员
        trdProductDeal.setTrader(entity.getTrader());
        //通道代码
        trdProductDeal.setChlId(entity.getChlId());
        //备注
        output.setRemark("基金类资产交易转入");

        output.setTrdProductDeal(trdProductDeal);

        // 金融产品交易信息DTO.金融产品交易Entity -> 金融产品交易业务流程表
        WflTrdProductDeal wflTrdProductDeal = new WflTrdProductDeal();
        BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDeal, output.getTrdProductDeal());
        output.setWflTrdProductDeal(wflTrdProductDeal);
        // 金融产品交易Entity 构造结束-----------------------------------------------------------------------

        // 金融产品交易附属表Entity 构造开始-----------------------------------------------------------------------
        // 金融产品交易信息DTO.金融产品交易附属表Entity -> 金融产品交易附属业务流程表
        TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
        //交易编号 = 画面.交易编号
        trdProductDealAdd.setTradeId(output.getTradeId());
        //投资组合代码 = 待转入产品组合.组合代码
        trdProductDealAdd.setPortfolioId(output.getPortfolioId());
        //是否有结算指令	= 数据字典.是否    默认为否
        // TODO
        // 生成方式 = 数据字典缓存.生成方式.画面录入
        trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
        // 工作流代码
        trdProductDealAdd.setWorkflowId(entity.getWorkflowId());

        output.setTrdProductDealAdd(trdProductDealAdd);

        // 金融产品交易信息DTO.金融产品交易Entity -> 金融产品交易业务流程表
        WflTrdProductDealAdd wflTrdProductDealAdd = new WflTrdProductDealAdd();
        BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDealAdd, output.getTrdProductDealAdd());
        output.setWflTrdProductDealAdd(wflTrdProductDealAdd);
        // 金融产品交易附属表Entity 构造结束-----------------------------------------------------------------------

        // 组合交易表Entity 构造开始-----------------------------------------------------------------------
        List<PtlTrade> ptlTrades = new ArrayList<>();
        PtlTrade ptlTrade = new PtlTrade();
        // 组合代码 = 转入产品组合.组合代码
        ptlTrade.setPortfolioId(output.getPortfolioId());
        // 业务编号 = 业务编号 ※2生成的交易编号
        ptlTrade.setBusiNo(output.getTradeId());
        //业务表 = 画面.业务表  默认为金融产品交易表
        ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        // 业务明细代码 = 画面.金融产品代码
        ptlTrade.setBusiId(output.getFinprodId());
        // 业务类型 = 数据字典缓存.共通业务类型.回购投资交易
        ptlTrade.setBusiType(DictCodeList.共通业务类型_基金类资产交易.getValue());
        // 证券托管户代码 = 画面.证券托管账户
        ptlTrade.setSecManageAcctId(entity.getChlId());
        // 实际发生日 = 画面.交易日
        ptlTrade.setSettleDate(entity.getTradeDate());
        // 工作流代码
        ptlTrade.setWorkflowId(output.getWorkflowId());
        ptlTrades.add(ptlTrade);
        if (!output.getPortfolioId().equals(output.getActPortfolioId())) {
            PtlTrade ptlTrade1 = new PtlTrade();
            BeanUtil.copyPropertiesIgnoreNull(ptlTrade1, ptlTrade);
            // 组合代码 = 画面.组合代码
            ptlTrade1.setPortfolioId(output.getActPortfolioId());
            ptlTrades.add(ptlTrade1);
        }
        output.setPtlTrade(ptlTrades);
        // 组合交易表Entity 构造结束-----------------------------------------------------------------------

        // 划款指令表Entity 构造开始----------------------------------------------------------
        if (bussTrdTransferOrderService != null) {
            if (StringUtil.isEmptyList(output.getBussTrdTransferOrder())) {
                // 后台自动生成划款指令
                BussSettleOrderParameterDto orderParameterDto = new BussSettleOrderParameterDto();
                // 是否汇总交易 = 是否_否
                orderParameterDto.setIsTotalTrade(DictCodeList.是否_否.getValue());

                // 金融产品交易Entity = 上述构造的金融产品交易Entity
                TrdProductDeal orderParameterDeal = new TrdProductDeal();
                // 交易日期 = 画面.交易日
                orderParameterDeal.setTradeDate(entity.getTradeDate());
                // 交易方向 = 交易方向_买入
                orderParameterDeal.setPs(trdProductDeal.getPs());
                // 币种 = 画面.币种
                orderParameterDeal.setCcy(entity.getCcy());
                // 交易金额 = 画面.本金金额
                orderParameterDeal.setTradeAmt(output.getTradePrin());
                // 交易对手 = 画面.交易对手
                orderParameterDeal.setCounterId(trdProductDeal.getCounterId());
                orderParameterDto.setDeal(orderParameterDeal);

                // 拆借类型 = null
                orderParameterDto.setBankLendingType(null);
                // 组合代码 = 画面.组合代码
                orderParameterDto.setPortfolioId(trdProductDealAdd.getPortfolioId());

                BussTrdTransferOrderDto resultOrderDto = bussTrdTransferOrderService.generateOrder(orderParameterDto);
                if (resultOrderDto != null) {
                    output.setBussTrdTransferOrder(Arrays.asList(resultOrderDto));
                }
            }
            if (!StringUtil.isEmptyList(output.getBussTrdTransferOrder())) {
                BussTrdTransferOrderDto bttod = output.getBussTrdTransferOrder().get(0);
                // 交易编号 = 业务编号 ※2生成的交易编号
                bttod.setTradeId(output.getTradeId());
                // 指令状态 = 指令状态_新建
                bttod.setOrderStatus(DictCodeListExt1.指令状态_新建.getValue());
                // 工作流代码
                bttod.setWorkflowId(output.getWorkflowId());
                output.setBussTrdTransferOrder(bussTrdTransferOrderService.save(output.getBussTrdTransferOrder()));
            }
        }
        // 划款指令表Entity 构造结束----------------------------------------------------------

        // 金融产品交易扩展Entity 构造开始----------------------------------------------------------
        PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
        //交易编号
        paTrdProductDeal.setTradeId(output.getTradeId());
        //平安业务类型	= 货币基金/非货币基金赎回确认
        if (StringUtil.equals(DictCodeList.基金类资产类型_货币基金.getValue(), entity.getAssetType())) {
            paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_货币基金赎回确认.getValue());
        } else {
            paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_非货币基金赎回确认.getValue());
        }
        paTrdProductDeal.setDeptCode(entity.getDeptCode());

        paTrdProductDeal.setWorkflowId(entity.getWorkflowId());

        output.setPaTrdProductDeal(paTrdProductDeal);

        WflPaTrdProductDeal wflPaTrdProductDeal = new WflPaTrdProductDeal();

        BeanUtil.copyPropertiesIgnoreNull(wflPaTrdProductDeal, paTrdProductDeal);

        output.setWflPaTrdProductDeal(wflPaTrdProductDeal);
        // 金融产品交易扩展Entity 构造结束----------------------------------------------------------
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doUpdate(FundAssetTransfer001Dto dto) {
        if (!StringUtil.equals("revoke", dto.getOperateType())
                && !StringUtil.equals("delete", dto.getOperateType())) {
            // 先删除流程中数据
            this.doDelete(dto.getWorkflowId());
            ProcessMark newEntity = null;
            try {
                newEntity = WorkFlowUtil.getBusinessObjectFromRequest(FundAssetTransfer001Dto.class, true);
                // 工作流ID
                newEntity.setWorkflowId(dto.getWorkflowId());
                newEntity.setStatus(dto.getStatus());
                newEntity.setBusinessData(JsonUtils.object2Json(dto));
                // 更新业务表数据
                // 操作留痕表
                WorkflowContext.getProcessMarkService().insertIntoProcessMark(newEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 重新insert
            this.handleBussFlowData(dto);
        }
    }

    /**
     * 删除业务流程数据
     *
     * @param workflowId 工作流代码
     * @see
     * @since 1.0
     */
    @Override
    public void doDelete(String workflowId) {
        baseTradeProductDealCommService.deleteWflData(workflowId);
    }

    @Override
    public void doBack(FundAssetTransfer001Dto entity) {
        List<WflTrdProductDeal> trdProductDealList = null;
        if (StringUtil.isNotEmpty(entity.getWorkflowId())) {
            trdProductDealList = simpleRestTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME+ "/trade/transfer/common/TransferCommon/selectDealByworkflowId",
                    entity.getWorkflowId(), WflTrdProductDeal.class);
        }
        if (!StringUtil.isEmptyList(trdProductDealList)) {
            for (WflTrdProductDeal deal : trdProductDealList) {
                AllTradeCommonUtil.deletePosition("",deal.getTradeId());
            }
        }
    }
}
