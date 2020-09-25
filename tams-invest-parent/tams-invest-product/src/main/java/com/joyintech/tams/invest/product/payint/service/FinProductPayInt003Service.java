/*
 * 文件名：FinProductPayInt003Service.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：etliu 修改时间：2019年1月6日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.payint.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.settle.BussSettleOrderCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlSecPositionInfoService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlPositionHoldDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.product.addreduce.ReduceInfoDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.service.TradeProductDealCommService;
import com.joyintech.tams.invest.common.trade.service.impl.BaseEtrCashPositionCommServiceImpl;
import com.joyintech.tams.invest.product.common.service.ProductCommonService;
import com.joyintech.tams.invest.product.payint.dto.*;
import com.joyintech.tams.valuation.common.service.BooksetCommonService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品中间付息新建
 *
 * @author etliu
 * @version 1.0 2019年1月6日
 * @see FinProductPayInt003Service
 * @since 1.0
 */
@Service
public class FinProductPayInt003Service extends BaseService<FinProductPayInt003Dto> implements BaseProcessService<FinProductPayInt003Dto> {

    /**
     * {@linkplain}
     */
    @Autowired
    private ProductCommonService productCommonService;

    /**
     * {@linkplain}
     */
    @Autowired
    private TradeProductDealCommService baseTradeProductDealCommServiceImpl;

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

//    @Autowired
//    private FinCashInfoMapper finCashInfoMapper;
//
//    @Autowired
//    private FinCashPayRuleMapper finCashPayRuleMapper;

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

//    @Autowired
//    private TrdTradeFeeDetailMapper trdTradeFeeDetailMapper;

    @Autowired
    private PaTrdFinProductDealTotalMapper paTrdFinProductDealTotalMapper;

//    @Autowired
//    private PrdSaleParamMapper prdSaleParamMapper;
//
//    @Autowired
//    private FinProductAddMapper finProductAddMapper;

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

//    @Autowired
//    private RepDurationMapper repDurationMapper;
    
    @Autowired
    private TrdAsynHandleStatusMapper trdAsynHandleStatusMapper;
    /**
     * 清算指令共通Service
     */
    @Autowired(required = false)
    private BussSettleOrderCommService bussSettleOrderCommService;
    
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
        return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPayInt/selectConfirmProdBaseInfo",
            paramDto, FinProductPayInt002Dto.class);
    }

    /**
     * 查询利息明细信息
     *
     * @param map 画面参数
     * @return 利息明细信息
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public List<ReduceInfoDto> findIntDetailInfo(Map<String, Object> map)
        throws Exception {
        /*
         * Page<FinProductPayInt002IntDetailDto> resultPage = new Page<>(); if (!WorkFlowUtil.isNotFromToDoList() &&
         * StringUtil.isNotBlank(inputDto.getWorkflowId())) { FinProductPayInt003Dto dataDto = this.getBussinessEntityByWorkflowId( inputDto.getWorkflowId());
         * resultPage.setRoot(dataDto.getIntDetailDataList()); return resultPage; }
         */

        /*
         * List<FinProductPayInt002IntDetailDto> resultList = restTemplate.postForList( ServiceApplication.PRODUCT_SERVICE_NAME +
         * "/FinProductPayInt/findIntDetailInfo", inputDto, FinProductPayInt002IntDetailDto.class);
         *
         * resultPage.setRoot(resultList);
         */
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPayInt/findIntDetailInfo", map,
            ReduceInfoDto.class);
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
        for (ReduceInfoDto intDetailDto : inputDto.getIntDetailDataList()) {
            应付客户收益 = ArithmeticUtil.doAdd(应付客户收益, intDetailDto.getInterest());
            实付客户收益 = ArithmeticUtil.doAdd(实付客户收益, intDetailDto.getActInterest());
        }

        resultDto.setTheoryCusProfit(应付客户收益);
        resultDto.setActCusProfit(实付客户收益);

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
                if (StringUtil.isNotBlank(intDetailDto.getFinprodId())) {
                    trdProductDeal.setFinprodId(intDetailDto.getFinprodId());
                }
                else {
                    trdProductDeal.setFinprodId(dto.getFinprodId());
                }
                trdProductDeal.setFinprodType2(DictCodeList.是否_是.getValue().equals(
                    dto.getIsLay()) ? DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue() : DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
                // trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
                // 金融产品类型（估值核算） TODO
                trdProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
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
                trdProductDeal.setBranch((long)0); // 分支序号
                // 中间付息不结清，isclean为空
                // trdProductDeal.setIsClean(DictCodeList.是否_是.getValue());// 是否结清
                trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
                trdProductDeal.setWorkflowId(dto.getWorkflowId()); // 工作流代码
                // lijun modify 20190322 SHBS-1243 【上海农商行BS】清结算管理-单据打印，交易备注中有值，划拨摘要中没有取到值
                trdProductDeal.setRemark(dto.getTradeRemark());
                // add by yejiejie 2019-4-2 start cashId
                trdProductDeal.setCashId(dto.getCashId());
                // add by yejiejie 2019-4-2 end
                tradeDto.setTrdProductDeal(trdProductDeal);
                // 金融产品交易Entity 构造结束----------------------------------------------------------

                // 金融产品交易扩展Entity 构造开始----------------------------------------------------------
                PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
                paTrdProductDeal.setTradeId(trdProductDeal.getTradeId()); // 交易编号
                // paTrdProductDeal.setTheoryRate(intDetailDto.getTheoryYield()); // 应付利率
                // paTrdProductDeal.setActRate(intDetailDto.getActYield()); // 实付利率
                paTrdProductDeal.setTheoryInt(intDetailDto.getInterest()); // 应付利息
                paTrdProductDeal.setRemainPrinAmt(intDetailDto.getRemainPrinAmt()); // 剩余本金
                paTrdProductDeal.setWorkflowId(dto.getWorkflowId()); // 工作流代码
                paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_产品中间付息.getValue());
                paTrdProductDeal.setOrderBussType(DictCodeListExt1.指令业务类型_产品分红.getValue());
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

                // // 组合交易表Entity 构造开始----------------------------------------------------------
                // PtlTrade ptlTrade = new PtlTrade();
                // ptlTrade.setPortfolioId(dto.getPortfolioId()); // 组合代码
                // ptlTrade.setBusiNo(trdProductDeal.getTradeId()); // 业务编号
                // ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue()); // 业务表
                // ptlTrade.setBusiId(trdProductDeal.getFinprodId()); // 业务明细代码
                // ptlTrade.setBusiType(DictCodeList.共通业务类型_收益型产品中间付息管理.getValue()); // 业务类型
                // ptlTrade.setSettleDate(dto.getPayDate()); // 实际发生日
                // ptlTrade.setWorkflowId(dto.getWorkflowId()); // 工作流代码
                // tradeDto.setPtlTrade(ptlTrade);
                // // 组合交易表Entity 构造结束----------------------------------------------------------

                tradeDtoList.add(tradeDto);
            }
            dto.setTradeDtoList(tradeDtoList);
        }

        // List<金融产品交易信息DTO> 构造结束-----------------------------------------------------------

        // 金融产品交易汇总Entity 构造开始----------------------------------------------------------
        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        trdFinProductDealTotal.setTotleTradeId(dto.getBussId()); // 汇总交易编号
        trdFinProductDealTotal.setPortfolioId(dto.getPortfolioId());
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
        trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
        dto.setTrdFinProductDealTotal(trdFinProductDealTotal);
        // 金融产品交易汇总Entity 构造结束----------------------------------------------------------

        // 组合交易表Entity 构造开始----------------------------------------------------------
        PtlTrade ptlTrade = new PtlTrade();
        ptlTrade.setPortfolioId(dto.getPortfolioId()); // 组合代码
        ptlTrade.setBusiNo(trdFinProductDealTotal.getTotleTradeId()); // 业务编号
        ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue()); // 业务表
        ptlTrade.setBusiId(trdFinProductDealTotal.getFinprodId()); // 业务明细代码
        ptlTrade.setBusiType(DictCodeList.共通业务类型_收益型产品中间付息管理.getValue()); // 业务类型
        ptlTrade.setSettleDate(dto.getPayDate()); // 实际发生日
        ptlTrade.setWorkflowId(dto.getWorkflowId()); // 工作流代码
        dto.setPtlTrade(ptlTrade);
        // 组合交易表Entity 构造结束----------------------------------------------------------

        // 金融产品交易汇总扩展表Entity 构造开始----------------------------------------------------------
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = new PaTrdFinProductDealTotal();
        paTrdFinProductDealTotal.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId()); // 汇总交易编号
        paTrdFinProductDealTotal.setTheoryInt(dto.getTheoryCusProfit()); // 理论应计利息
        paTrdFinProductDealTotal.setInterest(dto.getTheoryCusProfit()); // 应付利息
        paTrdFinProductDealTotal.setWorkflowId(dto.getWorkflowId()); // 工作流代码
        paTrdFinProductDealTotal.setPaBussType(DictCodeListExt1.平安业务类型_产品中间付息.getValue());
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
        //repDuration.setRepIssuePartyName(CacheUtil.getSysParam(BussinessConstant.ISSUE_PARTY_NAME)); // 发行机构名称
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

    }

//    @Autowired
//    private PaBranchSaleDetailMapper paBranchSaleDetailMapper;

    /**
     * 提交时登录业务流程表
     *
     * @param entity 画面参数dto
     * @see
     * @since 1.0
     */
    private void insertBussFlowData(FinProductPayInt003Dto entity) {
        if (!StringUtil.equals("submit", entity.getOperateType())) {
            return;
        }
        entity.setBranch("0");
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
        wflPaTrdFinDealTotal.setPaBussType(DictCodeListExt1.平安业务类型_产品中间付息.getValue());

        // 批量插入
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
     * 组合可用持仓和实际持仓查询更新组件接口
     */
    @Autowired
    private BasePtlSecPositionInfoService basePtlSecPositionInfoServiceImpl;

    /**
     * 组合可用现金和实际现金查询更新组件
     */
    @Autowired
    private BasePtlCashPositionInfoService basePtlCashPositionInfoServiceImpl;

    @Autowired
    private BaseEtrCashPositionCommServiceImpl baseEtrCashPositionCommServiceImpl;
    /**
     * 7.3写入[实际]的组合持仓流水及现金流水
     *
     * @param entity entity
     * @param needNegate 是否撤销 true 是 false 否
     * @see
     * @since 1.0
     */
    private void handleCashSecFlowData(FinProductPayInt003Dto entity, boolean needNegate) {
        // 组合持仓流水
        List<PtlSecPositionFlowDto> ptlSecPositionFlows = new ArrayList<>();
        PtlSecPositionFlowDto ptlSecPositionFlowDto = new PtlSecPositionFlowDto();
        ptlSecPositionFlowDto.setPortfolioId(entity.getPortfolioId()); // 组合代码
        ptlSecPositionFlowDto.setSecAcctId("999999");
        ptlSecPositionFlowDto.setFinprodId(entity.getFinprodId());
        ptlSecPositionFlowDto.setBranch(0L);
        ptlSecPositionFlowDto.setHodingType(DictCodeList.持仓类型_实际.getValue());
        ptlSecPositionFlowDto.setCcy(entity.getCcy());
        ptlSecPositionFlowDto.setTradeId(needNegate ? entity.getBussId() + "R" : entity.getBussId());
        ptlSecPositionFlowDto.setCdate(entity.getPayDate()); // 日期
        if (!StringUtil.isEmptyList(entity.getBussTrdTransferOrder())) {
            ptlSecPositionFlowDto.setAmount(needNegate ? entity.getBussTrdTransferOrder().get(
                0).getSettleAmt().negate() : entity.getBussTrdTransferOrder().get(0).getSettleAmt()); // 数量
        }
        //当撤销并且无指令时，重新查一遍指令信息
        else if(StringUtil.isEmptyList(entity.getBussTrdTransferOrder())&&needNegate&&StringUtil.isNotEmpty(entity.getBussId())){
            List<BussTrdTransferOrderBase> list = bussSettleOrderCommService.selectOrdersByBizCode(entity.getBussId());
            if(!StringUtil.isEmptyList(list)){
            	ptlSecPositionFlowDto.setAmount(needNegate ? list.get(0).getSettleAmt().negate() : list.get(0).getSettleAmt()); // 数量
            }
        }
        ptlSecPositionFlows.add(ptlSecPositionFlowDto);

        // 现金流水
        List<PtlCashPositionFlowDto> ptlCashPositionFlows = new ArrayList<>();
        PtlCashPositionFlowDto ptlCashPositionFlowDto = new PtlCashPositionFlowDto();
        ptlCashPositionFlowDto.setPortfolioId(entity.getPortfolioId()); // 组合代码
        if (!StringUtil.isEmptyList(entity.getBussTrdTransferOrder())) {
            entity.getBussTrdTransferOrder().get(0).setTradeId(needNegate ? entity.getBussId() + "R" : entity.getBussId()); // 交易编号
            entity.getBussTrdTransferOrder().get(0).setOrderStatus(
                needNegate ? DictCodeListExt1.指令状态_撤销.getValue() : DictCodeListExt1.指令状态_新建.getValue()); // 指令状态
            entity.getBussTrdTransferOrder().get(0).setWorkflowId(entity.getWorkflowId()); // 工作流代码
            entity.setBussTrdTransferOrder(bussTrdTransferOrderService.save(entity.getBussTrdTransferOrder()));
            // ptlCashPositionFlowDto.setAcctId(entity.getBussTrdTransferOrder().get(0).getMyAcctNo()); // 账号
            // ptlCashPositionFlowDto.setAmount(entity.getBussTrdTransferOrder().get(0).getSettleAmt()); // 数量
            // ptlCashPositionFlowDto.setOrderId(entity.getBussTrdTransferOrder().get(0).getOrderId()); // 指令编号
            for (StlPayOrdersDto stlPayOrdersDto : entity.getBussTrdTransferOrder()) {
                ptlCashPositionFlowDto.setAcctId(stlPayOrdersDto.getMyAcctNo());
                ptlCashPositionFlowDto.setHodingType(DictCodeList.持仓类型_实际.getValue());
                ptlCashPositionFlowDto.setCdate(entity.getPayDate());
                ptlCashPositionFlowDto.setCcy(entity.getCcy());
                ptlCashPositionFlowDto.setAmount(
                    needNegate ? stlPayOrdersDto.getSettleAmt() : stlPayOrdersDto.getSettleAmt().negate());
                ptlCashPositionFlowDto.setOrderId(needNegate ? stlPayOrdersDto.getOrderId() + "R" : stlPayOrdersDto.getOrderId());
                ptlCashPositionFlows.add(ptlCashPositionFlowDto);
            }
        }
        // ptlCashPositionFlowDto.setCdate(entity.getPayDate()); // 日期
        // ptlCashPositionFlowDto.setCcy(entity.getCcy()); // 币种
        // ptlCashPositionFlowDto.setHodingType(DictCodeList.持仓类型_实际.getValue());
        // ptlCashPositionFlows.add(ptlCashPositionFlowDto);

        PtlPositionHoldDto ptlPositionHoldDto = new PtlPositionHoldDto();
        ptlPositionHoldDto.setPtlSecPositionFlows(ptlSecPositionFlows);
        ptlPositionHoldDto.setPtlCashPositionFlows(ptlCashPositionFlows);
        if (ptlPositionHoldDto.getPtlSecPositionFlows() != null
                && !ptlPositionHoldDto.getPtlSecPositionFlows().isEmpty()) {
            for (PtlSecPositionFlowDto ptlSecPositionFlow : ptlPositionHoldDto.getPtlSecPositionFlows()) {
                basePtlSecPositionInfoServiceImpl.inertSecPositionFlow(ptlSecPositionFlow);
           }
        }
        if (ptlPositionHoldDto.getPtlCashPositionFlows() != null
                && !ptlPositionHoldDto.getPtlCashPositionFlows().isEmpty()) {
            for (PtlCashPositionFlowDto ptlCashPositionFlow : ptlPositionHoldDto.getPtlCashPositionFlows()) {
                basePtlCashPositionInfoServiceImpl.insertCashPositionFlow(ptlCashPositionFlow);
            }
        }
        ptlPositionHoldDto.setPtlSecPositionFlows(null);//委托持仓不变动
        baseEtrCashPositionCommServiceImpl.portfolioEtrCashPosition(ptlPositionHoldDto, false);
    }


    @Override
    public void doInsert(FinProductPayInt003Dto entity, OperationType operationType) {
        // 新建交易
        if (!operationType.equals(OperationType.REVOKE) && !operationType.equals(OperationType.DELETE)) {
            this.insertBussFlowData(entity);
        }
        // 撤销交易
        else {
            this.doInsertRevoke(entity);
        }
        //
    }

    private void doInsertRevoke(FinProductPayInt003Dto entity) {
        // 根据汇总交易编号查询金融产品交易汇总
        TrdFinProductDealTotal trdFinProductDealTotal = productCommonService.genTrdFinProductDealTotalRevoke(entity.getBussId());
        if (trdFinProductDealTotal != null) {
            WflTrdFinProductDealTotal wflTrdFinProductDealTotal = new WflTrdFinProductDealTotal();
            BeanUtil.copyPropertiesIgnoreNull(wflTrdFinProductDealTotal, trdFinProductDealTotal);
            wflTrdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
            wflTrdFinProductDealTotal.setWorkflowStatus(DictCodeListExt1.画面数据状态_流程中.getValue());
            wflTrdFinProductDealTotalMapper.insert(wflTrdFinProductDealTotal);
        }

        // 根据汇总交易编号查询金融产品交易汇总扩展
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = productCommonService.genPaTrdFinProductDealTotalRevoke(
            entity.getBussId());
        if (paTrdFinProductDealTotal != null) {
            WflPaTrdFinDealTotal wflPaTrdFinDealTotal = new WflPaTrdFinDealTotal();
            BeanUtil.copyPropertiesIgnoreNull(wflPaTrdFinDealTotal, paTrdFinProductDealTotal);
            wflPaTrdFinDealTotal.setWorkflowId(entity.getWorkflowId());
            wflPaTrdFinDealTotal.setWorkflowStatus(DictCodeListExt1.画面数据状态_流程中.getValue());
            wflPaTrdFinDealTotalMapper.insert(wflPaTrdFinDealTotal);
        }

        // 根据汇总交易编号查询划款指令
        List<StlPayOrdersDto> stlPayOrdersList = productCommonService.genStlPayOrdersRevoke(entity.getBussId());
        if (!StringUtil.isEmptyList(stlPayOrdersList)) {
            for (StlPayOrders stlPayOrders : stlPayOrdersList) {
                WflStlPayOrders wflStlPayOrders = new WflStlPayOrders();
                BeanUtil.copyPropertiesIgnoreNull(wflStlPayOrders, stlPayOrders);
                wflStlPayOrders.setWorkflowId(entity.getWorkflowId());
                wflStlPayOrders.setWorkflowStatus(DictCodeListExt1.画面数据状态_流程中.getValue());
                wflStlPayOrdersMapper.insertSelective(wflStlPayOrders);
            }
        }

        // 根据汇总交易编号查询交易信息
        List<TrdProductDeal> trdProductDealList = productCommonService.genTrdProductDealRevoke(entity.getBussId(),
            entity.getRevokeReason());
        if (!StringUtil.isEmptyList(trdProductDealList)) {
            for (TrdProductDeal trdProductDeal : trdProductDealList) {
                WflTrdProductDeal wflTrdProductDeal = new WflTrdProductDeal();
                BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDeal, trdProductDeal);
                wflTrdProductDeal.setWorkflowId(entity.getWorkflowId());
                wflTrdProductDeal.setWorkflowStatus(DictCodeListExt1.画面数据状态_流程中.getValue());
                wflTrdProductDealMapper.insertSelective(wflTrdProductDeal);
            }
        }

        // 根据汇总交易编号查询交易附属信息
        List<TrdProductDealAdd> trdProductDealAddList = productCommonService.genTrdProductDealAddRevoke(entity.getBussId());
        if (!StringUtil.isEmptyList(trdProductDealAddList)) {
            for (TrdProductDealAdd trdProductDealAdd : trdProductDealAddList) {
                WflTrdProductDealAdd wflTrdProductDealAdd = new WflTrdProductDealAdd();
                BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDealAdd, trdProductDealAdd);
                wflTrdProductDealAdd.setWorkflowId(entity.getWorkflowId());
                wflTrdProductDealAdd.setWorkflowStatus(DictCodeListExt1.画面数据状态_流程中.getValue());
                wflTrdProductDealAdd.setUpdateUser(entity.getUpdateUser());
                wflTrdProductDealAdd.setCreateUser(entity.getCreateUser());
                wflTrdProductDealAdd.setCreateDept(entity.getCreateDept());
                wflTrdProductDealAdd.setUpdateTime(entity.getUpdateTime());
                wflTrdProductDealAdd.setCreateTime(entity.getCreateTime());
                wflTrdProductDealAddMapper.insertSelective(wflTrdProductDealAdd);
            }
        }

        // 根据汇总交易编号查询金融产品交易扩展
        List<PaTrdProductDeal> paTrdProductDealList = productCommonService.genPaTrdProductDealRevoke(entity.getBussId());
        if (!StringUtil.isEmptyList(paTrdProductDealList)) {
            for (PaTrdProductDeal paTrdProductDeal : paTrdProductDealList) {
                WflPaTrdProductDeal wflPaTrdProductDeal = new WflPaTrdProductDeal();
                BeanUtil.copyPropertiesIgnoreNull(wflPaTrdProductDeal, paTrdProductDeal);
                wflPaTrdProductDeal.setWorkflowId(entity.getWorkflowId());
                wflPaTrdProductDeal.setWorkflowStatus(DictCodeListExt1.画面数据状态_流程中.getValue());
                wflPaTrdProductDeal.setUpdateUser(entity.getUpdateUser());
                wflPaTrdProductDeal.setCreateUser(entity.getCreateUser());
                wflPaTrdProductDeal.setCreateDept(entity.getCreateDept());
                wflPaTrdProductDeal.setUpdateTime(entity.getUpdateTime());
                wflPaTrdProductDeal.setCreateTime(entity.getCreateTime());
                wflPaTrdProductDealMapper.insertSelective(wflPaTrdProductDeal);
            }
        }
    }

    private void doCompleteRevoke(FinProductPayInt003Dto entity) {
        // 根据汇总交易编号查询金融产品交易汇总
        TrdFinProductDealTotal trdFinProductDealTotal = productCommonService.genTrdFinProductDealTotalRevoke(entity.getBussId());
        if (trdFinProductDealTotal != null) {
            trdFinProductDealTotalMapper.insert(trdFinProductDealTotal);
        }

        // 根据汇总交易编号查询金融产品交易汇总扩展
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = productCommonService.genPaTrdFinProductDealTotalRevoke(
            entity.getBussId());
        if (paTrdFinProductDealTotal != null) {
            paTrdFinProductDealTotalMapper.insert(paTrdFinProductDealTotal);
        }

        // 根据汇总交易编号查询划款指令
        List<StlPayOrdersDto> stlPayOrdersList = productCommonService.genStlPayOrdersRevoke(entity.getBussId());
        if (!StringUtil.isEmptyList(stlPayOrdersList)) {
            stlPayOrdersList.forEach(stlPayOrdersDto -> stlPayOrdersMapper.insertSelective(stlPayOrdersDto));
        }

        // 根据汇总交易编号查询交易信息
        List<TrdProductDeal> trdProductDealList = productCommonService.genTrdProductDealRevoke(entity.getBussId(),
            entity.getRevokeReason());
        if (!StringUtil.isEmptyList(trdProductDealList)) {
            trdProductDealList.forEach(stlPayOrdersDto -> trdProductDealMapper.insertSelective(stlPayOrdersDto));
        }

        // 根据汇总交易编号查询交易附属信息
        List<TrdProductDealAdd> trdProductDealAddList = productCommonService.genTrdProductDealAddRevoke(entity.getBussId());
        if (!StringUtil.isEmptyList(trdProductDealAddList)) {
            trdProductDealAddList.forEach(stlPayOrdersDto -> trdProductDealAddMapper.insertSelective(stlPayOrdersDto));
        }

        // 根据汇总交易编号查询金融产品交易扩展
        List<PaTrdProductDeal> paTrdProductDealList = productCommonService.genPaTrdProductDealRevoke(entity.getBussId());
        if (!StringUtil.isEmptyList(paTrdProductDealList)) {
            paTrdProductDealList.forEach(stlPayOrdersDto -> paTrdProductDealMapper.insertSelective(stlPayOrdersDto));
        }

        // 根据汇总交易编号查询组合交易信息
        List<PtlTrade> ptlTradeList = productCommonService.genPtlTradeRevoke(entity.getBussId());
        if (!StringUtil.isEmptyList(ptlTradeList)) {
            ptlTradeList.forEach(stlPayOrdersDto -> ptlTradeMapper.insertSelective(stlPayOrdersDto));
        }

        // 根据汇总交易编号查询理财分行销售明细
        /*
         * List<PaBranchSaleDetail> paBranchSaleDetailList = productCommonService.genPaBranchSaleDetailRevoke(entity.getTradeId()); if
         * (!StringUtil.isEmptyList(paBranchSaleDetailList)) { bussCommonMapperDelegateService.insertAutoClassifyBatch(paBranchSaleDetailList); }
         */

        // 原始汇总交易的状态改为已撤销
        TrdFinProductDealTotal trdFinProductDealTotalRevoke = new TrdFinProductDealTotal();
        trdFinProductDealTotalRevoke.setTotleTradeId(entity.getBussId());
        trdFinProductDealTotalRevoke.setIsCancel(DictCodeList.是否_是.getValue());
        trdFinProductDealTotalMapper.updateByPrimaryKeySelective(trdFinProductDealTotalRevoke);

        // 原始交易的状态改为已撤销
        productCommonService.updateTrdProductDealByTotleTradeId(entity.getBussId());

        // 一体化对应etliu start
        // 所有交易生效后调用的共通方法
        if (!StringUtil.isEmptyList(trdProductDealList)) {
            for (TrdProductDeal tradeDto : trdProductDealList) {
                AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                inputDto.setProdid(entity.getFinprodId());
                inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
                inputDto.setTrdProductDeal(tradeDto);
                AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
            }
        }
        // 一体化对应etliu end
        
        //20191008新增 	
        if (trdFinProductDealTotal != null) {
        	TrdAsynHandleStatus trdAsynHandleStatus = new TrdAsynHandleStatus();
            trdAsynHandleStatus.setTradeId(trdFinProductDealTotal.getTotleTradeId());		//交易汇总id
            trdAsynHandleStatus.setTrdTableType("01");		// 交易表类型(01：金融产品交易汇总表，02：金融产品交易表)
            trdAsynHandleStatus.setHandleStatus("01");		//01
            trdAsynHandleStatusMapper.insertSelective(trdAsynHandleStatus);
        }
    }

    /**
     * 生成组合持仓流水DTO
     * 
     * @param entity 产品成立dto
     * @param tradeId 交易编号
     * @param finprodId 金融产品代码
     * @param prinamt 本金
     * @return 组合持仓流水DTO
     * @see
     * @since 1.0
     */
    private PtlSecPositionFlowDto genPtlSecPositionFlow(FinProductPayInt003Dto entity, String tradeId, String finprodId,
                                                        BigDecimal prinamt) {
        PtlSecPositionFlowDto ptlSecPositionFlowDto = new PtlSecPositionFlowDto();
        ptlSecPositionFlowDto.setTradeId(tradeId);
        ptlSecPositionFlowDto.setPortfolioId(entity.getPortfolioId());
        ptlSecPositionFlowDto.setFinprodId(finprodId != null ? finprodId : entity.getFinprodId());
        // ptlSecPositionFlowDto.setBranch(
        // Long.parseLong(DateUtil.format(entity.getVdate(), DateUtil.DEFAULT_OTHER_FORMAT)));
        ptlSecPositionFlowDto.setBranch((long)0);
        ptlSecPositionFlowDto.setHodingType(DictCodeList.持仓类型_实际.getValue());
        ptlSecPositionFlowDto.setCdate(entity.getVdate());
        ptlSecPositionFlowDto.setCcy(entity.getCcy());
        ptlSecPositionFlowDto.setAmount(prinamt);
        ptlSecPositionFlowDto.setpFinprodId(finprodId != null ? entity.getFinprodId() : null);
        ptlSecPositionFlowDto.setSecAcctId("999999");
        ptlSecPositionFlowDto.setInvAim("999999");
        return ptlSecPositionFlowDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doUpdate(FinProductPayInt003Dto entity) {
        if (!StringUtil.equals("revoke", entity.getOperateType()) && !StringUtil.equals("delete", entity.getOperateType())) {
            // 先删除流程中数据
            this.doDelete(entity.getWorkflowId());

            // 重新insert
            this.insertBussFlowData(entity);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doDelete(String workflowId) {
        baseTradeProductDealCommServiceImpl.delete(workflowId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinProductPayInt003Dto entity, OperationType operationType) {
        if (!operationType.equals(OperationType.REVOKE) && !operationType.equals(OperationType.DELETE)) {
            this.prepareEntity(entity);

            // 7.1删除原业务流程表数据
            // this.doDelete(entity.getWorkflowId());

            // 7.2登陆各业务表
            // 金融产品交易相关表
            if (!StringUtil.isEmptyList(entity.getTradeDtoList())) {
                for (FinProductPayInt002TradeDto tradeDto : entity.getTradeDtoList()) {
                    // // 金融产品交易表
                    TrdProductDeal trdProductDeal = new TrdProductDeal();
                    BeanUtil.copyPropertiesIgnoreNull(trdProductDeal, tradeDto.getTrdProductDeal());
                    trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
                    //
                    // // 金融产品交易扩展表
                    PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
                    BeanUtil.copyPropertiesIgnoreNull(paTrdProductDeal, tradeDto.getPaTrdProductDeal());
                    //
                    // // 金融产品交易附属表
                    TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
                    BeanUtil.copyPropertiesIgnoreNull(trdProductDealAdd, tradeDto.getTrdProductDealAdd());

                    // // 组合交易表Entity
                    // PtlTrade ptlTrade = new PtlTrade();
                    // BeanUtil.copyPropertiesIgnoreNull(ptlTrade, tradeDto.getPtlTrade());

                    // 批量插入
                    trdProductDealMapper.insertSelective(trdProductDeal);
                    paTrdProductDealMapper.insertSelective(paTrdProductDeal);
                    trdProductDealAddMapper.insertSelective(trdProductDealAdd);

                    // 一体化对应etliu start
                    // 所有交易生效后调用的共通方法
                    AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                    inputDto.setProdid(entity.getFinprodId());
                    inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
                    inputDto.setTrdProductDeal(trdProductDeal);
                    inputDto.setProdVdate(entity.getVdate());
                    AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
                    // 一体化对应etliu end
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
            paTrdFinProductDealTotal.setPaBussType(DictCodeListExt1.平安业务类型_产品中间付息.getValue());

            // 组合交易表Entity
            PtlTrade ptlTrade = new PtlTrade();
            BeanUtil.copyPropertiesIgnoreNull(ptlTrade, entity.getPtlTrade());

            // 批量插入
            trdFinProductDealTotalMapper.insertSelective(trdFinProductDealTotal);
            paTrdFinProductDealTotalMapper.insertSelective(paTrdFinProductDealTotal);
            ptlTradeMapper.insertSelective(ptlTrade);

            // 金融产品交易信息DTO.划款指令表Entity -> 划款指令业务流程表
            if (!StringUtil.isEmptyList(entity.getBussTrdTransferOrder())) {
                StlPayOrders stlPayOrders = new StlPayOrders();
                BeanUtil.copyPropertiesIgnoreNull(stlPayOrders, entity.getBussTrdTransferOrder().get(0));

                stlPayOrdersMapper.insertSelective(stlPayOrders);
            }

            // 存续期报送信息 TODO 等待强成西共通

            // 7.3写入[实际]的组合持仓流水及现金流水
            this.handleCashSecFlowData(entity, false);

            if ("001".equals(CacheUtil.getSysParam("CUSTOMER_BANK_CODE"))) {
                // 调用个性化接口- 资金到账标识接口 lijun add by 2019-08-12
                restTemplate.postForObject(
                    ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/FinProductInterf/sendFundToTAccount",
                    trdFinProductDealTotal, String.class);
            }
            
            //20191008新增 	
            TrdAsynHandleStatus trdAsynHandleStatus = new TrdAsynHandleStatus();
            trdAsynHandleStatus.setTradeId(trdFinProductDealTotal.getTotleTradeId());		//交易汇总id
            trdAsynHandleStatus.setTrdTableType("01");		// 交易表类型(01：金融产品交易汇总表，02：金融产品交易表)
            trdAsynHandleStatus.setHandleStatus("01");		//01	待处理
            trdAsynHandleStatusMapper.insertSelective(trdAsynHandleStatus);
        }
        else {
            this.doCompleteRevoke(entity);

            // 7.3写入[实际]的组合持仓流水及现金流水
            this.handleCashSecFlowData(entity, true);
        }
        baseTradeProductDealCommServiceImpl.delete(entity.getWorkflowId());
    }

    public FinProductPayInt003Dto queryProdInfoByFinprodId(String prodId) {
        Map<String, Object> map = new HashMap<String, Object>();
        /*
         * map.put("prodId", prodId); map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue())
         */;
        return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPayInt/queryProdInfoByprodId",
            map, FinProductPayInt003Dto.class);
    }

    /**
     * 根据产品代码模糊查询联系下拉框(发行成功的母产品)
     * 
     * @param map 参数map
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryProdIdInfoIssueSuc(Map<String, Object> map) {
        map.put("userId", SecurityUtil.getLoginUser().getUserName());
        map.put("platDate", DateUtil.getSysDate());
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPayInt/queryProdIdInfoIssueSuc",
            map, SelectOptionDto.class);
    }

    // 双击查看
    public FinProductPayInt003Dto selectProdInfo(String finprodId, String tradeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("tradeId", tradeId);
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        return restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPayInt/selectProdPayInfoByFinprodId", map,
            FinProductPayInt003Dto.class);
    }

    // 双击查看分层明细
    public List<FinProductPayInt002IntDetailDto> selectLayIntDetailDataList(String finprodId, String tradeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("tradeId", tradeId);
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPayInt/selectLayIntDetailDataList",
            map, FinProductPayInt002IntDetailDto.class);
    }

}
