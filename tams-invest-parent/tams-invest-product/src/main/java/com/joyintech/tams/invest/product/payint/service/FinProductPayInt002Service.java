/*
 * 文件名：FinProductPayInt002Service.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：etliu 修改时间：2019年1月6日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.payint.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlPositionHoldDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.product.addreduce.ReduceInfoDto;
import com.joyintech.tams.invest.common.service.MapperDelegateService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.service.TradeProductDealCommService;
import com.joyintech.tams.invest.product.payint.dto.FinProductPayInt001Dto;
import com.joyintech.tams.invest.product.payint.dto.FinProductPayInt002Dto;
import com.joyintech.tams.invest.product.payint.dto.FinProductPayInt002IntDetailDto;
import com.joyintech.tams.invest.product.payint.dto.FinProductPayInt002TradeDto;
import com.joyintech.tams.valuation.common.service.BooksetCommonService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品中间付息确认
 *
 * @author etliu
 * @version 1.0 2019年1月6日
 * @see FinProductPayInt002Service
 * @since 1.0
 */
@Service
public class FinProductPayInt002Service extends BaseService<FinProductPayInt002Dto> implements BaseProcessService<FinProductPayInt002Dto> {

    /**
     * BaseGetBusinessId
     */
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

    /**
     * BussTrdTransferOrderService
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    @Autowired
    private FinCashInfoMapper finCashInfoMapper;
    @Autowired
    private MapperDelegateService bussCommonMapperDelegateService;

    @Autowired
    private FinCashPayRuleMapper finCashPayRuleMapper;

    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;

    @Autowired
    private PaTrdProductDealMapper paTrdProductDealMapper;

    @Autowired
    private TrdProductDealMapper trdProductDealMapper;

    @Autowired
    private TrdProductDealAddMapper trdProductDealAddMapper;

    @Autowired
    private PtlTradeMapper ptlTradeMapper;

    @Autowired
    private StlPayOrdersMapper stlPayOrdersMapper;

    @Autowired
    private TrdTradeFeeDetailMapper trdTradeFeeDetailMapper;

    @Autowired
    private PaTrdFinProductDealTotalMapper paTrdFinProductDealTotalMapper;

    @Autowired
    private PrdSaleParamMapper prdSaleParamMapper;

    @Autowired
    private FinProductAddMapper finProductAddMapper;

    @Autowired
    private WflStlPayOrdersMapper wflStlPayOrdersMapper;

    @Autowired
    private WflTrdFinProductDealTotalMapper wflTrdFinProductDealTotalMapper;

    @Autowired
    private WflPaTrdFinDealTotalMapper wflPaTrdFinDealTotalMapper;

    @Autowired
    private WflPaTrdProductDealMapper wflPaTrdProductDealMapper;

    @Autowired
    private WflTrdProductDealMapper wflTrdProductDealMapper;

    @Autowired
    private WflTrdProductDealAddMapper wflTrdProductDealAddMapper;

    @Autowired
    private RepDurationMapper repDurationMapper;

    @Autowired
    private TradeProductDealCommService tradeProductDealCommService;
    
    @Autowired
    private BooksetCommonService booksetCommonService;

    /**
     * 1.6.1产品基本信息
     *
     * @param paramDto 参数dto
     * @return 产品基本信息
     * @see
     * @since 1.0
     */
    public FinProductPayInt002Dto selectProdBaseInfo(FinProductPayInt001Dto paramDto) {
        paramDto.setBranch(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPayInt/selectConfirmProdBaseInfo",
            paramDto, FinProductPayInt002Dto.class);
    }

    /**
     * 查询利息明细信息
     *
     * @param inputDto 画面参数
     * @return 利息明细信息
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<ReduceInfoDto> findIntDetailInfo(FinProductPayInt002Dto inputDto)
        throws Exception {
        Page<ReduceInfoDto> resultPage = new Page<>();
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotBlank(inputDto.getWorkflowId())) {
            FinProductPayInt002Dto dataDto = this.getBussinessEntityByWorkflowId(inputDto.getWorkflowId());
            resultPage.setRoot(dataDto.getIntDetailDataList());
            return resultPage;
        }

        List<ReduceInfoDto> resultList = restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPayInt/findIntDetailInfo", inputDto, ReduceInfoDto.class);

        // // 测试代码
        // FinProductPayInt002IntDetailDto testDto = new FinProductPayInt002IntDetailDto();
        // testDto.setSonFinprodId("etliu001");
        // testDto.setTheoryYield(BigDecimal.valueOf(5));
        // testDto.setTheoryInt(BigDecimal.valueOf(1000));
        // testDto.setActYield(BigDecimal.valueOf(5));
        // testDto.setActInt(BigDecimal.valueOf(1000));
        // resultList.add(testDto);

        resultPage.setRoot(resultList);
        return resultPage;
    }

    /**
     * 画面联动 2.应付客户收益 = sum（应付利息） 3.实付客户收益 = sum（实付利息）
     *
     * @param inputDto 参数
     * @return 下拉框列表
     * @throws Exception Exception
     */
    public FinProductPayInt002Dto calcCusProfit(FinProductPayInt002Dto inputDto)
        throws Exception {
        FinProductPayInt002Dto resultDto = new FinProductPayInt002Dto();
        if (inputDto == null || StringUtil.isEmptyList(inputDto.getIntDetailDataList())) {
            return resultDto;
        }

        BigDecimal 应付客户收益 = BigDecimal.ZERO;
        BigDecimal 实付客户收益 = BigDecimal.ZERO;
        BigDecimal 产品本金 = BigDecimal.ZERO;
        for (ReduceInfoDto intDetailDto : inputDto.getIntDetailDataList()) {
            应付客户收益 = ArithmeticUtil.doAdd(应付客户收益, intDetailDto.getInterest());
            实付客户收益 = ArithmeticUtil.doAdd(实付客户收益, intDetailDto.getActInterest());
            产品本金 = ArithmeticUtil.doAdd(产品本金, intDetailDto.getRemainPrinAmt());
        }

        resultDto.setTheoryCusProfit(应付客户收益);
        resultDto.setActCusProfit(实付客户收益);
        resultDto.setPrinAmt(产品本金);

        return resultDto;
    }

    /**
     * 画面联动 反算实付收益率
     *
     * @param inputDto 参数
     * @return 下拉框列表
     * @throws Exception Exception
     */
    public FinProductPayInt002IntDetailDto calcActYield(FinProductPayInt002IntDetailDto inputDto)
        throws Exception {
        FinProductPayInt002IntDetailDto resultDto = new FinProductPayInt002IntDetailDto();
        if (inputDto == null || inputDto.getActInt() == null) {
            return resultDto;
        }

        // 实付收益率 = 实付利息*计息基础/计息本金/计息区间
        BigDecimal 实付收益率 = ArithmeticUtil.multiply(inputDto.getActInt(), BigDecimal.valueOf(inputDto.getCalcIntBasic()));
        实付收益率 = ArithmeticUtil.divide(实付收益率,
            ArithmeticUtil.multiply(inputDto.getIntAccrPrinAmt(), BigDecimal.valueOf(inputDto.getCalcIntPeriod())), 4);
        实付收益率 = ArithmeticUtil.multiply(实付收益率, BigDecimal.valueOf(100));

        resultDto.setActYield(实付收益率);

        return resultDto;
    }

    /**
     * 构造调用组件的输入参数DTO
     *
     * @param dto 画面输入参数dto
     * @see
     * @since 1.0
     */
    public void prepareEntity(FinProductPayInt002Dto dto) {
        BussinessCodeParameter codeParameter = new BussinessCodeParameter();
        codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_收益型产品中间付息管理);

        BigDecimal 计息本金汇总 = BigDecimal.ZERO;

        // List<金融产品交易信息DTO> 构造开始-----------------------------------------------------------
        if (!StringUtil.isEmptyList(dto.getIntDetailDataList())) {
            List<FinProductPayInt002TradeDto> tradeDtoList = new ArrayList<FinProductPayInt002TradeDto>();

            for (ReduceInfoDto intDetailDto : dto.getIntDetailDataList()) {
                计息本金汇总 = ArithmeticUtil.doAdd(计息本金汇总, intDetailDto.getRemainPrinAmt());

                FinProductPayInt002TradeDto tradeDto = new FinProductPayInt002TradeDto();

                // 金融产品交易Entity 构造开始----------------------------------------------------------
                TrdProductDeal trdProductDeal = new TrdProductDeal();
                trdProductDeal.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
                trdProductDeal.setBusiType(DictCodeList.共通业务类型_收益型产品中间付息管理.getValue()); // 业务类型
                // 金融产品代码
                // if (StringUtil.isNotBlank(intDetailDto.getLayFinprodId())) {
                // trdProductDeal.setFinprodId(intDetailDto.getLayFinprodId());
                // }
                // else {
                trdProductDeal.setFinprodId(dto.getFinprodId());
                // }
                trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财子产品.getValue());
                // 金融产品类型（估值核算） TODO
                trdProductDeal.setFinprodType(null);
                trdProductDeal.setCcy(dto.getCcy()); // 交易币种
                trdProductDeal.setPs(DictCodeList.交易方向_中间付息.getValue()); // 交易方向
                trdProductDeal.setTradeDate(dto.getPayDate()); // 交易日
                trdProductDeal.setVdate(dto.getPayintStartDate()); // 起息日
                trdProductDeal.setMdate(dto.getPayintEndDate()); // 到期日
                trdProductDeal.setSettleDate(dto.getPayDate()); // 交割日
                trdProductDeal.setIntAmt(intDetailDto.getActInterest()); // 利息总额
                // trdProductDeal.setFpriceAmt(intDetailDto.getActInt()); // 全价总额
                trdProductDeal.setTradeAmt(intDetailDto.getActInterest()); // 交易金额
                trdProductDeal.setTotleTradeId(dto.getBussId()); // 汇总交易编号
                trdProductDeal.setBranch(Long.valueOf(0)); // 分支序号
                trdProductDeal.setIsClean(DictCodeList.是否_是.getValue()); // 是否结清
                trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
                trdProductDeal.setWorkflowId(dto.getWorkflowId()); // 工作流代码
                trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
                tradeDto.setTrdProductDeal(trdProductDeal);
                // 金融产品交易Entity 构造结束----------------------------------------------------------

                // 金融产品交易扩展Entity 构造开始----------------------------------------------------------
                PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
                paTrdProductDeal.setTradeId(trdProductDeal.getTradeId()); // 交易编号
                // paTrdProductDeal.setTheoryRate(intDetailDto.getTheoryYield()); // 应付利率
                // paTrdProductDeal.setTheoryRate(ArithmeticUtil.divide(intDetailDto.getTheoryYield(),
                // BigDecimal.valueOf(100), 2)); // 应付利率
                //// paTrdProductDeal.setActRate(intDetailDto.getActYield()); // 实付利率
                // paTrdProductDeal.setActRate(ArithmeticUtil.divide(intDetailDto.getActYield(),
                // BigDecimal.valueOf(100), 2)); // 实付利率
                paTrdProductDeal.setTheoryInt(intDetailDto.getInterest()); // 应付利息
                paTrdProductDeal.setRemainPrinAmt(intDetailDto.getRemainPrinAmt()); // 剩余本金
                paTrdProductDeal.setWorkflowId(dto.getWorkflowId()); // 工作流代码
                tradeDto.setPaTrdProductDeal(paTrdProductDeal);
                // 金融产品交易扩展Entity 构造结束----------------------------------------------------------

                // 金融产品交易附属表Entity 构造开始----------------------------------------------------------
                TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
                trdProductDealAdd.setTradeId(trdProductDeal.getTradeId()); // 交易编号
                trdProductDealAdd.setPortfolioId(dto.getPortfolioId()); // 投资组合代码
                trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue()); // 生成方式
                trdProductDealAdd.setWorkflowId(dto.getWorkflowId()); // 工作流代码
                tradeDto.setTrdProductDealAdd(trdProductDealAdd);
                // 金融产品交易附属表Entity 构造结束----------------------------------------------------------

                // 组合交易表Entity 构造开始----------------------------------------------------------
                PtlTrade ptlTrade = new PtlTrade();
                ptlTrade.setPortfolioId(dto.getPortfolioId()); // 组合代码
                ptlTrade.setBusiNo(trdProductDeal.getTradeId()); // 业务编号
                ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue()); // 业务表
                ptlTrade.setBusiId(trdProductDeal.getFinprodId()); // 业务明细代码
                ptlTrade.setBusiType(DictCodeList.共通业务类型_收益型产品中间付息管理.getValue()); // 业务类型
                ptlTrade.setSettleDate(dto.getPayDate()); // 实际发生日
                ptlTrade.setWorkflowId(dto.getWorkflowId()); // 工作流代码
                tradeDto.setPtlTrade(ptlTrade);
                // 组合交易表Entity 构造结束----------------------------------------------------------

                tradeDtoList.add(tradeDto);
            }
            // 一体化对应etliu start
            dto.setTradeDtoList(tradeDtoList);
            // 一体化对应etliu end
        }
        // List<金融产品交易信息DTO> 构造结束-----------------------------------------------------------

        // 金融产品交易汇总Entity 构造开始----------------------------------------------------------
        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        trdFinProductDealTotal.setTotleTradeId(dto.getBussId()); // 汇总交易编号
        trdFinProductDealTotal.setBusiType(DictCodeList.共通业务类型_收益型产品中间付息管理.getValue()); // 业务类型
        trdFinProductDealTotal.setFinprodId(dto.getFinprodId()); // 金融产品代码
        trdFinProductDealTotal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        // 金融产品类型（估值核算） TODO
        trdFinProductDealTotal.setFinprodType(null);
        trdFinProductDealTotal.setCcy(dto.getCcy()); // 交易币种
        trdFinProductDealTotal.setPs(DictCodeList.交易方向_中间付息.getValue()); // 交易方向
        trdFinProductDealTotal.setTradeDate(dto.getPayDate()); // 交易日
        trdFinProductDealTotal.setVdate(dto.getPayintStartDate()); // 起息日
        trdFinProductDealTotal.setMdate(dto.getPayintEndDate()); // 到期日
        trdFinProductDealTotal.setSettleDate(dto.getPayDate()); // 交割日
        trdFinProductDealTotal.setShareAmt(计息本金汇总); // 交易份额
        trdFinProductDealTotal.setPrinAmt(计息本金汇总); // 交易本金
        trdFinProductDealTotal.setIntAmt(dto.getActCusProfit()); // 利息总额
        trdFinProductDealTotal.setTradeAmt(dto.getActCusProfit()); // 交易金额
        trdFinProductDealTotal.setWorkflowId(dto.getWorkflowId()); // 工作流代码
        // 一体化对应etliu start
        trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
        trdFinProductDealTotal.setCashId(dto.getCashId());
        trdFinProductDealTotal.setPortfolioId(dto.getPortfolioId());
        // 一体化对应etliu end
        dto.setTrdFinProductDealTotal(trdFinProductDealTotal);
        // 金融产品交易汇总Entity 构造结束----------------------------------------------------------

        // // 组合交易表Entity 构造开始----------------------------------------------------------
        // PtlTrade ptlTrade = new PtlTrade();
        // ptlTrade.setPortfolioId(dto.getPortfolioId()); // 组合代码
        // ptlTrade.setBusiNo(trdFinProductDealTotal.getTotleTradeId()); // 业务编号
        // ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue()); // 业务表
        // ptlTrade.setBusiId(trdFinProductDealTotal.getFinprodId()); // 业务明细代码
        // ptlTrade.setBusiType(DictCodeList.共通业务类型_收益型产品中间付息管理.getValue()); // 业务类型
        // ptlTrade.setSettleDate(dto.getPayDate()); // 实际发生日
        // ptlTrade.setWorkflowId(dto.getWorkflowId()); // 工作流代码
        // dto.setPtlTrade(ptlTrade);
        // // 组合交易表Entity 构造结束----------------------------------------------------------

        // 金融产品交易汇总扩展表Entity 构造开始----------------------------------------------------------
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = new PaTrdFinProductDealTotal();
        paTrdFinProductDealTotal.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId()); // 汇总交易编号
        // 一体化对应etliu start
        // paTrdFinProductDealTotal.setTheoryInt(dto.getTheoryCusProfit()); // 应付利息
        paTrdFinProductDealTotal.setInterest(dto.getTheoryCusProfit()); // 应付利息
        // 一体化对应etliu end
        paTrdFinProductDealTotal.setWorkflowId(dto.getWorkflowId()); // 工作流代码
        dto.setPaTrdFinProductDealTotal(paTrdFinProductDealTotal);
        // 金融产品交易汇总扩展表Entity 构造结束----------------------------------------------------------

        // 划款指令表Entity 构造开始----------------------------------------------------------
        if (!StringUtil.isEmptyList(dto.getBussTrdTransferOrder())) {
            dto.getBussTrdTransferOrder().get(0).setTradeId(trdFinProductDealTotal.getTotleTradeId()); // 交易编号
            dto.getBussTrdTransferOrder().get(0).setOrderStatus(DictCodeListExt1.指令状态_新建.getValue()); // 指令状态
            dto.getBussTrdTransferOrder().get(0).setWorkflowId(dto.getWorkflowId()); // 工作流代码
            dto.setBussTrdTransferOrder(bussTrdTransferOrderService.save(dto.getBussTrdTransferOrder()));
        }
        // 划款指令表Entity 构造结束----------------------------------------------------------

        // 存续期报送信息Entity 构造开始----------------------------------------------------------
        // TODO 等待强成西共通
        RepDuration repDuration = new RepDuration();
        repDuration.setRepFinprodId(trdFinProductDealTotal.getFinprodId()); // 金融产品代码
       // repDuration.setRepIssuePartyName(CacheUtil.getSysParam(BussinessConstant.ISSUE_PARTY_NAME)); // 发行机构名称
        //多级法人改造start add by hxy 20200620
        if(MultilevelDataAuthorityUtil.isEnabled()) {
	        	 //联社模式
	        	 //根据选择的账套组或者账套，查询出它们的所属机构
	        	String booksetOrg=trdFinProductDealTotal.getFinprodId();
	        	Map<String, Object> orgmap=new HashMap<String, Object>();
	        	orgmap.put("booksetOrg", booksetOrg);
	        	orgmap.put("dataType", "FINPROD04");
	        	String orgName=booksetCommonService.selectAssociationModeOrgName(orgmap);
	        	repDuration.setRepIssuePartyName(orgName);
	        	
        	}else{
        	//银行模式
        	String orgName=booksetCommonService.selectBankModeOrgName();
        	repDuration.setRepIssuePartyName(orgName);
        	
           }
        //多级法人改造end add by hxy 20200620
        
        repDuration.setRepIssuePartyId(CacheUtil.getSysParam(BussinessConstant.ISSUE_PARTY_ID)); // 发行机构代码
        repDuration.setRepFinprodRegId(dto.getProdRegistCode()); // 产品登记编码
        repDuration.setRepBankProdCode(dto.getProdId()); // 行内标识码/理财产品代码
        repDuration.setRepBusVdate(dto.getPayDate()); // 业务起始日
        repDuration.setRepBusMdate(dto.getPayDate()); // 业务终止日
        repDuration.setRepCcy(dto.getCcy()); // 币种
        dto.setRepDuration(repDuration);
        // 存续期报送信息Entity 构造结束----------------------------------------------------------

        // // 组合现金流水Entity 构造开始----------------------------------------------------------
        // PtlCashPositionFlow ptlCashPositionFlow = new PtlCashPositionFlow();
        // // 2.1.1 调用业务编号共通组件获取流水号
        // BussinessCodeParameter parameter = new BussinessCodeParameter();
        // parameter.setSeqNo("9999");
        // String seqNo = baseGetBusinessId.buildBussinessCode(parameter);
        // ptlCashPositionFlow.setSeqno(seqNo); // 流水号
        // ptlCashPositionFlow.setPortfolioId(dto.getPortfolioId()); // 组合代码
        // if (!StringUtil.isEmptyList(dto.getBussTrdTransferOrder())) {
        // ptlCashPositionFlow.setAcctId(dto.getBussTrdTransferOrder().get(0).getMyAcctNo()); // 账号
        // ptlCashPositionFlow.setAmount(dto.getBussTrdTransferOrder().get(0).getSettleAmt()); // 数量
        // ptlCashPositionFlow.setOrderId(dto.getBussTrdTransferOrder().get(0).getOrderId()); // 指令编号
        // }
        // ptlCashPositionFlow.setCdate(dto.getPayDate()); // 日期
        // ptlCashPositionFlow.setCcy(dto.getCcy()); // 币种
        // dto.setPtlCashPositionFlow(ptlCashPositionFlow);
        // // 组合现金流水Entity: 构造结束----------------------------------------------------------
        //
        // // 组合持仓流水Entity 构造开始----------------------------------------------------------
        // PtlSecPositionFlow ptlSecPositionFlow = new PtlSecPositionFlow();
        // // 2.1.1 调用业务编号共通组件获取流水号
        // seqNo = baseGetBusinessId.buildBussinessCode(parameter);
        // ptlSecPositionFlow.setSeqno(seqNo); // 流水号
        // ptlSecPositionFlow.setPortfolioId(dto.getPortfolioId()); // 组合代码
        // ptlSecPositionFlow.setFinprodId(dto.getFinprodId()); // 金融产品代码
        // if (!StringUtil.isEmptyList(dto.getBussTrdTransferOrder())) {
        // ptlSecPositionFlow.setAmount(dto.getBussTrdTransferOrder().get(0).getSettleAmt()); // 数量
        // }
        // ptlSecPositionFlow.setCdate(dto.getPayDate()); // 日期
        // ptlSecPositionFlow.setCcy(dto.getCcy()); // 币种
        // dto.setPtlSecPositionFlow(ptlSecPositionFlow);
        // // 组合持仓流水Entity: 构造结束----------------------------------------------------------
    }

    /**
     * 提交时登录业务流程表
     *
     * @param entity 画面参数dto
     * @see
     * @since 1.0
     */
    private void handleBussFlowData(FinProductPayInt002Dto entity) {
        if (!StringUtil.equals("submit", entity.getOperateType())) {
            return;
        }

        this.prepareEntity(entity);

        // 6.1.插入新建记录到金融产品业务流程信息表中
        if (!StringUtil.isEmptyList(entity.getTradeDtoList())) {
            for (FinProductPayInt002TradeDto tradeDto : entity.getTradeDtoList()) {
                // 金融产品交易业务流程表
                WflTrdProductDeal wflTrdProductDeal = new WflTrdProductDeal();
                BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDeal, tradeDto.getTrdProductDeal());
                wflTrdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
                wflTrdProductDeal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());

                // 金融产品交易扩展表业务流程表
                WflPaTrdProductDeal wflPaTrdProductDeal = new WflPaTrdProductDeal();
                BeanUtil.copyPropertiesIgnoreNull(wflPaTrdProductDeal, tradeDto.getPaTrdProductDeal());
                wflPaTrdProductDeal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());

                // 金融产品交易附属表业务流程表
                WflTrdProductDealAdd wflTrdProductDealAdd = new WflTrdProductDealAdd();
                BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDealAdd, tradeDto.getTrdProductDealAdd());
                wflTrdProductDealAdd.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());

                // 批量插入
                wflTrdProductDealMapper.insertSelective(wflTrdProductDeal);
                wflPaTrdProductDealMapper.insertSelective(wflPaTrdProductDeal);
                wflTrdProductDealAddMapper.insertSelective(wflTrdProductDealAdd);
            }
        }

        // 6.2插入新建记录到金融产品交易汇总表、金融产品交易汇总扩展表
        // 金融产品交易汇总业务流程表
        WflTrdFinProductDealTotal wflTrdFinProductDealTotal = new WflTrdFinProductDealTotal();
        BeanUtil.copyPropertiesIgnoreNull(wflTrdFinProductDealTotal, entity.getTrdFinProductDealTotal());
        wflTrdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        wflTrdFinProductDealTotal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());

        // 金融产品交易汇总扩展业务流程表
        WflPaTrdFinDealTotal wflPaTrdFinDealTotal = new WflPaTrdFinDealTotal();
        BeanUtil.copyPropertiesIgnoreNull(wflPaTrdFinDealTotal, entity.getPaTrdFinProductDealTotal());
        wflPaTrdFinDealTotal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());

        wflTrdFinProductDealTotalMapper.insertSelective(wflTrdFinProductDealTotal);
        wflPaTrdFinDealTotalMapper.insertSelective(wflPaTrdFinDealTotal);

        // 金融产品交易信息DTO.划款指令表Entity -> 划款指令业务流程表
        if (!StringUtil.isEmptyList(entity.getBussTrdTransferOrder())) {
            WflStlPayOrders wflStlPayOrders = new WflStlPayOrders();
            BeanUtil.copyPropertiesIgnoreNull(wflStlPayOrders, entity.getBussTrdTransferOrder().get(0));
            wflStlPayOrders.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());

            wflStlPayOrdersMapper.insertSelective(wflStlPayOrders);
        }
    }

    /**
     * 7.3写入[实际]的组合持仓流水及现金流水
     *
     * @param entity entity
     * @see
     * @since 1.0
     */
    private void handleCashSecFlowData(FinProductPayInt002Dto entity) {
        // // 组合持仓流水
        // List<PtlSecPositionFlowDto> ptlSecPositionFlows = new ArrayList<>();
        // PtlSecPositionFlowDto ptlSecPositionFlowDto = new PtlSecPositionFlowDto();
        // ptlSecPositionFlowDto.setPortfolioId(entity.getPortfolioId()); // 组合代码
        // ptlSecPositionFlowDto.setSecAcctId("999999");
        // ptlSecPositionFlowDto.setFinprodId(entity.getFinprodId());
        // ptlSecPositionFlowDto.setBranch(0L);
        // ptlSecPositionFlowDto.setHodingType(DictCodeList.持仓类型_实际.getValue());
        // ptlSecPositionFlowDto.setCcy(entity.getCcy());
        // ptlSecPositionFlowDto.setTradeId(entity.getBussId());
        // ptlSecPositionFlowDto.setCdate(entity.getPayDate()); // 日期
        // if (!StringUtil.isEmptyList(entity.getBussTrdTransferOrder())) {
        // ptlSecPositionFlowDto.setAmount(entity.getBussTrdTransferOrder().get(0).getSettleAmt()); // 数量
        // }
        // ptlSecPositionFlows.add(ptlSecPositionFlowDto);

        // 现金流水
        List<PtlCashPositionFlowDto> ptlCashPositionFlows = new ArrayList<>();
        PtlCashPositionFlowDto ptlCashPositionFlowDto = new PtlCashPositionFlowDto();
        ptlCashPositionFlowDto.setPortfolioId(entity.getPortfolioId()); // 组合代码
        if (!StringUtil.isEmptyList(entity.getBussTrdTransferOrder())) {
            ptlCashPositionFlowDto.setAcctId(entity.getBussTrdTransferOrder().get(0).getMyAcctNo()); // 账号
            ptlCashPositionFlowDto.setAmount(entity.getBussTrdTransferOrder().get(0).getSettleAmt()); // 数量
            ptlCashPositionFlowDto.setOrderId(entity.getBussTrdTransferOrder().get(0).getOrderId()); // 指令编号
        }
        ptlCashPositionFlowDto.setCdate(entity.getPayDate()); // 日期
        ptlCashPositionFlowDto.setCcy(entity.getCcy()); // 币种
        ptlCashPositionFlowDto.setHodingType(DictCodeList.持仓类型_实际.getValue());
        ptlCashPositionFlows.add(ptlCashPositionFlowDto);

        PtlPositionHoldDto ptlPositionHoldDto = new PtlPositionHoldDto();
        // ptlPositionHoldDto.setPtlSecPositionFlows(ptlSecPositionFlows);
        ptlPositionHoldDto.setPtlCashPositionFlows(ptlCashPositionFlows);
        restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/common/ptlposition/insertPosition",
            ptlPositionHoldDto, Boolean.class);
    }

    @Override
    public void doInsert(FinProductPayInt002Dto entity, OperationType operationType) {
        // 新建交易
        if (!operationType.equals(OperationType.REVOKE) && !operationType.equals(OperationType.DELETE)) {
            this.handleBussFlowData(entity);
        }
        // 撤销交易
        else {
            // this.doRevokeInsert(entity);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doUpdate(FinProductPayInt002Dto entity) {
        if (!StringUtil.equals("revoke", entity.getOperateType()) && !StringUtil.equals("delete", entity.getOperateType())) {
            // 先删除流程中数据
            this.doDelete(entity.getWorkflowId());

            // 重新insert
            this.handleBussFlowData(entity);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doDelete(String workflowId) {
        tradeProductDealCommService.deleteWflData(workflowId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinProductPayInt002Dto entity, OperationType operationType) {
        if (!operationType.equals(OperationType.REVOKE) && !operationType.equals(OperationType.DELETE)) {
            this.prepareEntity(entity);

            // 7.1删除原业务流程表数据
            this.doDelete(entity.getWorkflowId());

            // 7.2登陆各业务表
            // 金融产品交易相关表
            if (!StringUtil.isEmptyList(entity.getTradeDtoList())) {
                for (FinProductPayInt002TradeDto tradeDto : entity.getTradeDtoList()) {
                    // 金融产品交易表
                    TrdProductDeal trdProductDeal = new TrdProductDeal();
                    BeanUtil.copyPropertiesIgnoreNull(trdProductDeal, tradeDto.getTrdProductDeal());
                    trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());

                    // 金融产品交易扩展表
                    PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
                    BeanUtil.copyPropertiesIgnoreNull(paTrdProductDeal, tradeDto.getPaTrdProductDeal());

                    // 金融产品交易附属表
                    TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
                    BeanUtil.copyPropertiesIgnoreNull(trdProductDealAdd, tradeDto.getTrdProductDealAdd());

                    // 组合交易表Entity
                    PtlTrade ptlTrade = new PtlTrade();
                    BeanUtil.copyPropertiesIgnoreNull(ptlTrade, tradeDto.getPtlTrade());

                    trdProductDealMapper.insertSelective(trdProductDeal);
                    paTrdProductDealMapper.insertSelective(paTrdProductDeal);
                    trdProductDealAddMapper.insertSelective(trdProductDealAdd);
                    ptlTradeMapper.insertSelective(ptlTrade);
                }
            }

            // 金融产品交易汇总相关表
            // 金融产品交易汇总表
            TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
            BeanUtil.copyPropertiesIgnoreNull(trdFinProductDealTotal, entity.getTrdFinProductDealTotal());
            trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());

            // 金融产品交易汇总扩展表
            PaTrdFinProductDealTotal paTrdFinProductDealTotal = new PaTrdFinProductDealTotal();
            BeanUtil.copyPropertiesIgnoreNull(paTrdFinProductDealTotal, entity.getPaTrdFinProductDealTotal());

            // 批量插入
            trdFinProductDealTotalMapper.insertSelective(trdFinProductDealTotal);
            paTrdFinProductDealTotalMapper.insertSelective(paTrdFinProductDealTotal);

            // 金融产品交易信息DTO.划款指令表Entity -> 划款指令业务流程表
            if (!StringUtil.isEmptyList(entity.getBussTrdTransferOrder())) {
                StlPayOrders stlPayOrders = new StlPayOrders();
                BeanUtil.copyPropertiesIgnoreNull(stlPayOrders, entity.getBussTrdTransferOrder().get(0));
                stlPayOrdersMapper.insertSelective(stlPayOrders);
            }

            // 存续期报送信息 TODO 等待强成西共通

            // 7.3写入[实际]的组合持仓流水及现金流水
            this.handleCashSecFlowData(entity);

            // 一体化对应etliu start
            // 所有交易生效后调用的共通方法
            if (!StringUtil.isEmptyList(entity.getTradeDtoList())) {
                for (FinProductPayInt002TradeDto tradeDto : entity.getTradeDtoList()) {
                    AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                    inputDto.setProdid(entity.getFinprodId());
                    inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
                    inputDto.setProdVdate(tradeDto.getTrdProductDeal().getVdate());
                    inputDto.setTrdProductDeal(tradeDto.getTrdProductDeal());
                    AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
                }
            }
            // 一体化对应etliu end
        }
        else {

        }
    }

}
