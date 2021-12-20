/*
 * 文件名：CfetsTrdConfirmService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：sjj
 * 修改时间：2021年11月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.cfets.trdconfirm.service;

import com.google.common.collect.Lists;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.*;
import com.joyintech.service.bankuplink.common.dto.TrdBondInvestTradeDto;
import com.joyintech.tams.business.common.service.PositionHttpRequest;
import com.joyintech.tams.cfets.etrib.bond.dao.CfetsEtrIbBondinfoMapper;
import com.joyintech.tams.cfets.etrib.bond.dto.CfetsEtrIbBondinfoDto;
import com.joyintech.tams.cfets.etrib.bond.dto.CfetsEtrIbPosPortfolioRequest;
import com.joyintech.tams.cfets.etrib.bond.dto.CfetsEtrIbPositionRequest;
import com.joyintech.tams.cfets.etrib.bond.service.CfetsEtribBondInfo003Service;
import com.joyintech.tams.cfets.trdconfirm.dao.CfetsTrdConfirmMapper;
import com.joyintech.tams.cfets.trdconfirm.dto.*;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.settle.dto.BussSettleOrderParameterDto;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.component.business.trade.impl.BaseTradeProductDealCommServiceImpl;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.cstp.service.CstpCommonService;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.PtlAllocationDto;
import com.joyintech.tams.invest.common.dto.cstp.EtrEntrustExt;
import com.joyintech.tams.invest.common.dto.cstp.InterfTradSecdealDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.service.InvestInterfCommService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.common.trade.service.PreTrdCommonBaseService;
import com.joyintech.tams.invest.trade.cstptrdmatchinfo.dto.TrdMatchInfoDto;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 银行间交易成交确认
 * @author
 * @version 1.0 2021年10月26日
 * @see CfetsTrdConfirm001Service
 * @since 1.0
 */
@Transactional
@Service("CfetsTrdConfirm001Service")
public class CfetsTrdConfirm001Service extends BaseService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(CfetsTrdConfirm001Service.class);

    @Autowired
    private CfetsTrdConfirmMapper cfetsTrdConfirmMapper;
    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;
    @Autowired
    private PaTrdFinProductDealTotalMapper paTrdFinProductDealTotalMapper;
    @Autowired
    private TrdDealTotalAddMapper trdDealTotalAddMapper;
    @Autowired
    private TrdProductDealMapper trdProductDealMapper;
    @Autowired
    private TrdProductDealAddMapper trdProductDealAddMapper;
    @Autowired
    private PaTrdProductDealMapper paTrdProductDealMapper;
    @Autowired
    private StlPayOrdersMapper stlPayOrdersMapper;
    @Autowired
    private TrdTradeFeeDetailMapper trdTradeFeeDetailMapper;
    @Autowired
    private TrdTradeFeeTotalMapper trdTradeFeeTotalMapper;
    @Autowired
    private PtlTradeMapper ptlTradeMapper;
    @Autowired
    private TrdProductDealPreMapper trdProductDealPreMapper;
    @Autowired
    private TrdProductDealAddPreMapper trdProductDealAddPreMapper;
    @Autowired(required = false)
    private FinProductMapper finProductMapper;
    @Autowired(required = false)
    private StlSecAcctInfoMapper stlSecAcctInfoMapper;
    @Autowired
    private PtlPortfolioMapper ptlPortfolioMapper;
    @Autowired(required = false)
    private BaseTradeProductDealCommService baseTradeProductDealCommService;
    @Autowired(required = false)
    private BaseTradeProductDealCommServiceImpl baseTradeProductDealCommServiceImpl;
    @Autowired
    private CstpCommonService cstpCommonService;
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;
    @Autowired
    private InvestInterfCommService investInterfCommService;
    @Autowired
    private PreTrdCommonBaseService preTrdCommonBaseService;
    @Autowired
    private CommonBusinessService commonBusinessService;
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;
    @Autowired(required = false)
    private CfetsEtribBondInfo003Service cfetsEtribBondInfo003Service;
    @Autowired(required = false)
    private CfetsEtrIbBondinfoMapper cfetsEtrIbBondinfoMapper;
    @Autowired(required = false)
    private IPlatformDate iPlatformDate;
    // 交易份额小数保留位数
    private int decimalPlaces_4 = 4;


    @Transactional(rollbackFor = Exception.class)
    @DTXTransaction
    public void doCompleteMethod(CfetsTrdBondInvestTradeDto entity, OperationType operationType) {
        String tradeId = entity.getTotleTradeId();
        //买入含权债时，自动创建交易对手 add by wangwei5 2020-03-23 Start
        if(operationType.equals(OperationType.NEW)){
            //含权债标记
            String str = entity.getFinprodId();
            if (!StringUtil.equals(str.substring(0, 3), "F01")) {
                entity.setFinprodId("F01_" + str);
            }
            DebtBaseInfo debtbaseinfo=trdFinProductDealTotalMapper.selectIsRight(entity.getFinprodId());
            if(StringUtil.isNotEmpty(debtbaseinfo.getIsRight()) && StringUtil.isNotEmpty(entity.getPs())){
                if("Y".equals(debtbaseinfo.getIsRight()) && "T01".equals(entity.getPs())){
                    BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
                    bussinessCodeParameter.setCommonBusinessType(DictCodeList.共通业务类型_交易对手管理);
                    debtbaseinfo.setCounterId(baseGetBusinessId.buildBussinessCode(bussinessCodeParameter));
                    debtbaseinfo.setCreateUser(LoginUserUtil.getUserName());
                    debtbaseinfo.setCreateDept(LoginUserUtil.getDeptCode());
                    debtbaseinfo.setUpdateUser(LoginUserUtil.getUserName());
                    //判断交易对手表中是否存在
                    int flag=trdFinProductDealTotalMapper.selectCounterName(debtbaseinfo.getCounterName());
                    if(flag == 0){
                        trdFinProductDealTotalMapper.insertDebtBaseInfo(debtbaseinfo);
                    }
                }
            }
        }
        //买入含权债时，自动创建交易对手 add by wangwei5 2020-03-23 end
        if (!operationType.equals(OperationType.REVOKE) && !operationType.equals(OperationType.DELETE)) {
            entity.setActPortfolioId(entity.getPortfolioId());
            // entity.setPortfolioId(AllTradeCommonUtil.exchangePortfolio(entity.getPortfolioId()));
            entity.setPortfolioId(entity.getPortfolioId());
            TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
            BeanUtil.copyPropertiesIgnoreNull(trdFinProductDealTotal, entity);
            trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
            trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
            //     trdFinProductDealTotal.setPortfolioId(entity.getActPortfolioId());
            trdFinProductDealTotal.setSettleSpeed(entity.getSettleSpeed());
            trdFinProductDealTotalMapper.insertSelective(trdFinProductDealTotal);

            PaTrdFinProductDealTotal paTrdFinDealTotal = entity.getPaTrdFinProductDealTotal();
            // 新增 返款金额 、返款利率 by caozhenming 20210803 start
            paTrdFinDealTotal.setRefundAmount(entity.getRefundAmount());
            BigDecimal refundRate = entity.getRefundRate();
            if (refundRate != null){
                paTrdFinDealTotal.setRefundRate(refundRate.divide(new BigDecimal(100)));
            }
            // 新增 返款金额 、返款利率 by caozhenming 20210803 end
            paTrdFinProductDealTotalMapper.insertSelective(paTrdFinDealTotal);

            TrdDealTotalAdd trdDealTotalAdd = entity.getTrdDealTotalAdd();
            trdDealTotalAddMapper.insertSelective(trdDealTotalAdd);

            entity.getTrdProductDealAdds().forEach(trdProductDealAdd -> {
                // trdProductDealAdd.setPortfolioId(entity.getPortfolioId());
                // 推荐方 by zhuyongkang 20191023 start
                // trdProductDealAdd.setAssetRecommandOrg(entity.getAssetRecommandOrg());
                // 推荐方 by zhuyongkang 20191023 start
                trdProductDealAddMapper.insertSelective(trdProductDealAdd);
            });

            // 金融产品交易扩展表Entity -> 金融产品交易扩展业务流程表
            entity.getPaTrdProductDeals().forEach(paTrdProductDeal -> {
                paTrdProductDealMapper.insertSelective(paTrdProductDeal);
            });

            StlPayOrders stlPayOrders = new StlPayOrders();
            for (BussTrdTransferOrderDto dto : entity.getBussTrdTransferOrder()) {
                BeanUtil.copyPropertiesIgnoreNull(stlPayOrders, dto);
                stlPayOrders.setOrderStatus(DictCodeListExt1.指令状态_新建.getValue());
                stlPayOrders.setTradeId(entity.getTotleTradeId());
                stlPayOrders.setWorkflowId(entity.getWorkflowId());
                stlPayOrdersMapper.insertSelective(stlPayOrders);
            }

            entity.getTrdTradeFeeDetails().forEach(
                    trdTradeFeeDetail -> trdTradeFeeDetailMapper.insertSelective(trdTradeFeeDetail));

            entity.getPtlTrade().forEach(ptlTrade -> ptlTradeMapper.insertSelective(ptlTrade));

            //改造保存结算费经手费
            for(TrdTradeFeeTotal trdTradeFeeDetail:entity.getTrdTradeFeeTotals()){
                if(!BigDecimal.ZERO.equals(trdTradeFeeDetail.getFeeAmt())){
                    trdTradeFeeTotalMapper.insertSelective(trdTradeFeeDetail);
                }
            }

            // 7.3写入交易信息进交易报送信息表，为中债报送用
            this.dealInfoCbReportLogic_7_3(entity);

            // 7.4更新资产负债报送状态
            this.assetDebtCbReportLogic_7_4(entity);

            //basRiskMathCommService.insert(entity.getRiskTrialRetDto(), entity.getTotleTradeId());

            //无下行时，插入下行成交匹配关系.有下行时，手工匹配或自动匹配插入下行成交匹配表
            String support_cstp_flag_bond=CacheUtil.getSysParam("IS_SUPPORT_CSTP_FLAG_BOND");

            if(null!=entity.getCfetsTrdConfirmMatchDto()){
                if(StringUtil.equals(support_cstp_flag_bond,DictCodeList.是否_否.getValue())){
                    cfetsTrdConfirmMapper.insertTrdQutoMatchInfo(entity.getCfetsTrdConfirmMatchDto());
                }else{
                    //根据登记编号查询下行成交匹配表
                    boolean flag=cfetsTrdConfirmMapper.isExistMatchInfo(entity.getCfetsTrdConfirmMatchDto().getPreTradeId());
                    if(flag){
                        //若存在对应的成交匹配数据，则更新下行成交匹配表
                        cfetsTrdConfirmMapper.updateTrdQutoMatchInfo(entity.getCfetsTrdConfirmMatchDto());
                    }else{
                        //不存在对应的成交匹配数据，插入下行成交匹配表
                        entity.getCfetsTrdConfirmMatchDto().setSeqno(IDGenerator.generate());
                        entity.getCfetsTrdConfirmMatchDto().setCreateUser(SecurityUtil.getLoginUser().getUserName());
                        entity.getCfetsTrdConfirmMatchDto().setCreateDept(CommonUtil.getDeptCode());
                        cfetsTrdConfirmMapper.insertTrdQutoMatchInfo(entity.getCfetsTrdConfirmMatchDto());
                    }
                }
            }

            // 金融产品交易信息DTO.金融产品交易Entity -> 金融产品交易业务流程表
            for (TrdProductDeal trdProductDeal : entity.getTrdProductDeals()) {

                //处理业务模式与投资目的对应关系
              /*  for(TrdProductDeal temp : entity.getTrdProductDeals()){
                    if(StringUtil.equals(entity.getBusinessModel(),"Y1")){
                        temp.setInvAim("AC");
                        temp.setBusinessModel(entity.getBusinessModel());
                    }else if(StringUtil.equals(entity.getBusinessModel(),"Y2")){
                        temp.setInvAim("FVTPL");
                        temp.setBusinessModel(entity.getBusinessModel());
                    }
                }*/
                trdProductDeal.setBusinessModel(trdProductDeal.getInvAim());

                trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
                trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
                trdProductDealMapper.insertSelective(trdProductDeal);

                AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();

                // 调用业务组件.【金融产品交易共通.交易产品接口】,写入[可用]的组合持仓流水及现金流水
                FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
                finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_有效.getValue());
                finProductTradeDto.setTotalFaceValue(entity.getTotalFaceValue());// 劵面总额
                finProductTradeDto.setNeedSum(true);
                BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
                baseTradeProductDealDto.setBondFirstSign(entity.getBondFirstSign());
                //设置预录入的成交编号
               /* if(StringUtil.isNotBlank(entity.getPreTradeId())){
                    baseTradeProductDealDto.setPreTradeId(entity.getPreTradeId());
                }*/
                //设置登记时的成交编号


                if(StringUtil.isNotBlank(trdProductDeal.getfTradeId())){
                    baseTradeProductDealDto.setfTradeId(trdProductDeal.getfTradeId());
                }
                baseTradeProductDealDto.setTrdProductDeal(trdProductDeal);
                entity.getTrdProductDealAdds().forEach(trdProductDealAdd -> {
                    if (trdProductDeal.getTradeId() != null
                            && trdProductDeal.getTradeId().equals(trdProductDealAdd.getTradeId())) {
                        baseTradeProductDealDto.setTrdProductDealAdd(trdProductDealAdd);
                    }
                });

                if (!StringUtil.isEmptyList(entity.getBussTrdTransferOrder())
                        && !DictCodeListExt1.投资目的_卖空.getValue().equals(trdProductDeal.getInvAim())) {
                    baseTradeProductDealDto.setStlPayOrdersList(Arrays.asList(entity.getBussTrdTransferOrder().get(0)));
                }

                // 委托代码不为空设置委托编号
                if (StringUtil.isNotEmpty(entity.getEntrustId())
                        && !DictCodeListExt1.投资目的_卖空.getValue().equals(trdProductDeal.getInvAim())) {
                    baseTradeProductDealDto.setEntrustId(entity.getEntrustId());
                }

                finProductTradeDto.setBaseTradeProductDealDtos(Arrays.asList(baseTradeProductDealDto));
                //处理cstp下行数据和头寸

                baseTradeCommExecute(finProductTradeDto);

               /* if(StringUtil.equals(entity.getIsOfferFlag(), "01")){
                    //存在上行报价时，生效时，反冲可用流水
                    String tradeId2 = entity.getTradeId();
                    if(StringUtil.isEmpty(tradeId2)){
                        tradeId2 = entity.getTotleTradeId();
                    }
                    cstpCommonService.excuteQuotePositionData(entity.getEntrustId(), entity.getfTradeId(), false, tradeId2);
                }*/
                //  trdProductDeal.setTradeId(tradeId1);

                trdProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
                trdProductDeal.setBusiType(DictCodeList.共通业务类型_债券类资产交易.getValue());
                trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());// 交易状态
                trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
                // 发送Murex接口处理
                trdProductDeal.setTotleTradeId(null);
                // this.sendMurex(entity, false);
                // 一体化对应etliu start
                // 所有交易生效后调用的共通方法
                inputDto.setPageId("TrdBondInvestTrade002");
                inputDto.setProdid(entity.getPortfolioId());
                inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
                inputDto.setTrdProductDeal(trdProductDeal);

                TrdProductDealAdd trdProductDealAdd = entity.getTrdProductDealAdds().stream().filter(
                        trdProductDealAdd1 -> trdProductDeal.getTradeId().equals(trdProductDealAdd1.getTradeId())).findFirst().get();
                List<TrdTradeFeeDetail> trdTradeFeeDetails = new ArrayList<>();
                TrdTradeFeeDetail TrdTradeFeeDetailChlid = null;
                for(TrdTradeFeeDetail trdTradeFeeDetail :entity.getTrdTradeFeeDetails()){
                    if(trdProductDeal.getTradeId().equals(trdTradeFeeDetail.getTradeId())){
                        if(StringUtil.equals(DictCodeListExt1.一次性费用类型_通道费.getValue(), trdTradeFeeDetail.getFeeType())){
                            TrdTradeFeeDetailChlid= trdTradeFeeDetail;
                        }else{
                            trdTradeFeeDetails.add(trdTradeFeeDetail);
                        }
                    }
                }
                // 通道交易 新增 强成西 2019/03/26 start
                inputDto.setTrdProductDealAdd(trdProductDealAdd);
                inputDto.setListTrdTradeFeeDetail(trdTradeFeeDetails);
                inputDto.setTrdTradeFeeDetail(TrdTradeFeeDetailChlid);
                // 通道交易 新增 强成西 2019/03/26 end
                PaTrdProductDeal paTrdProductDeal = entity.getPaTrdProductDeals().stream().filter(
                        trdProductDealAdd1 -> trdProductDeal.getTradeId().equals(trdProductDealAdd1.getTradeId())).findFirst().get();

                List<PtlTrade> ptlTrades = entity.getPtlTrade().stream().filter(
                        trdProductDealAdd1 -> trdProductDeal.getTradeId().equals(trdProductDealAdd1.getBusiNo())).collect(
                        Collectors.toList());
                // 反向交易增加 start by qiangchengxi 2019年3月12日
                inputDto.setTurnInvAim(entity.getTurnInvAim());

                if (entity.getBussTrdTransferOrder() != null && !entity.getBussTrdTransferOrder().isEmpty()) {
                    stlPayOrders = new StlPayOrders();
                    BeanUtil.copyPropertiesIgnoreNull(stlPayOrders, entity.getBussTrdTransferOrder().get(0));
                    inputDto.setStlPayOrders(stlPayOrders);
                }

                // MODIFY BY WJ 20200424 ----债券 缺少PTL_CASH_POSITION_FLOW
                if(stlPayOrders!=null){
                    stlPayOrders.setTradeId(null);
                    stlPayOrders.setPortfolioId(entity.getTrdInvestPersonInfo().getCounterId());
                }

                AllTradeCommonUtil.setDeals(inputDto, trdProductDeal, trdProductDealAdd, paTrdProductDeal, Arrays.asList(stlPayOrders), ptlTrades,
                        entity.getActPortfolioId(), trdTradeFeeDetails);
                // 反向交易增加 end by qiangchengxi 2019年3月12日

                if (entity.getActPortfolioId() != null && !entity.getActPortfolioId().equals(entity.getPortfolioId())) {
                    inputDto.setActPortfolioId(entity.getActPortfolioId());
                }
                /** ADD BY WUJIE ON 20191121 START REMARK:子组合改造 **/
                inputDto.setPtlTradeList(ptlTrades);
                /** ADD BY WUJIE ON 20191121 END **/

                //AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
                // 一体化对应etliu end
                // 调用投管接口 add by renping 20190205 start
               /* InvestInterfCommDto investInterfCommDto = new InvestInterfCommDto();
                investInterfCommDto.setPageId("TrdBondInvestTrade002");
                investInterfCommDto.setTradeStatus(DictCodeListExt1.画面数据状态_新建.getValue());
                investInterfCommDto.setPortfolioId(entity.getActPortfolioId());
                investInterfCommDto.setTrdProductDeal(trdProductDeal);
                investInterfCommDto.setTrdProductDealAdd(trdProductDealAdd);
                investInterfCommDto.setStlPayOrders(inputDto.getStlPayOrders());
                investInterfCommDto.setTradeFeeDetaillist(trdTradeFeeDetails);
                investInterfCommService.doInvestInterf(investInterfCommDto);*/
                // 调用投管接口 add by renping 20190205 end
            }

        }
        //更新操作记录日志
        /*CfetsOprHisInfoRequest oprHisReq=new CfetsOprHisInfoRequest();
        oprHisReq.setEntrustId(dto.getEntrustId());
        oprHisReq.setOperType(DictCodeListExt3.操作类型_撤回.getValue());
        oprHisReq.setBusiDes(dto.getRemark());
        joyinbusinessCommonService.updateCfetsOprHis(oprHisReq);*/

        //发送头寸
        sendPositionMethod(entity);
    }
    /**
     * 7.3写入交易信息进交易报送信息表，为中债报送用
     *
     * @param dto 画面参数dto
     * @see
     * @since 1.0
     */
    private void dealInfoCbReportLogic_7_3(CfetsTrdBondInvestTradeDto dto) {
        // 7.7写入交易信息进交易报送信息表，为中债报送用
        // 1)查询产品登记编码
        String prodRegistCode = restTemplate.postForObject(
                ServiceApplication.TRADE_SERVICE_NAME + "/common/trdcommonbase/selectProdRegistCode/" + dto.getPortfolioId(), null,
                String.class);

        if (StringUtil.isBlank(prodRegistCode)) {
            return;
        }

        // 2）写入交易报送信息
        // 交易报送信息Entity
        RepTrade repTrade = new RepTrade();
        repTrade.setRepFinprodRegId(prodRegistCode); // 产品登记编码
        repTrade.setRepBankTradeCode(dto.getTotleTradeId()); // 行内交易编码
        // 行内资产/负债编码
        FinProduct finProduct = finProductMapper.selectByPrimaryKey(dto.getFinprodId());
        if (finProduct != null) {
            repTrade.setRepBankAssetCode(finProduct.getFinprodMarketId());
        }
        repTrade.setRepCcy(dto.getCcy()); // 币种
        repTrade.setRepOccurMoney(dto.getFpriceAmt()); // 发生金额

        // 折算人民币金额（元） = 画面.全价总额*收盘价(利用画面.币种与交易日查询汇率行情表中最新的收盘价)
        BigDecimal 收盘价 = commonBusinessService.selectClosePrice(dto.getCcy(), DateUtil.format(dto.getTradeDate()));
        BigDecimal 折算人民币金额 = ArithmeticUtil.multiply(dto.getFpriceAmt(), 收盘价);
        repTrade.setRepMoneyCny(折算人民币金额);
        // 数量 = 画面.券面总额
        repTrade.setRepCount(dto.getTotalFaceValue());

        // 资金流动类型: 数据字典缓存.资金流动类型 ※根据交易方向判断，若买入，即资产买入，若卖出，即为资产卖出或到期
        String 资金流动类型 = DictCodeListExt2.资金流动类型_资产卖出或到期.getValue();
        if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())) {
            资金流动类型 = DictCodeListExt2.资金流动类型_资产买入.getValue();
        }
        repTrade.setRepMoneyFlowType(资金流动类型);

        repTrade.setRepTradeDate(dto.getTradeDate()); // 交易日
        repTrade.setRepTradeComponent(dto.getCounterId()); // 交易对手方
        repTrade.setRepUnitPriceFull(dto.getUnitFprice()); // 单位成交价格（全价）
        repTrade.setRepUnitPriceNet(dto.getUnitCprice()); // 单位成交价格（净价）
        repTrade.setRepExpireYield(dto.getYield().multiply(new BigDecimal(100))); // 到期收益率%
        if (StringUtil.isNotEmpty(dto.getfTradeId())) {
            repTrade.setRepDelCode(dto.getfTradeId()); // 成交编号/合同号
        }
        else {
            repTrade.setRepDelCode(dto.getTotleTradeId()); // 成交编号/合同号
        }
        repTrade.setRepSendStatus(DictCodeList.是否_是.getValue()); // 报送状态
        repTrade.setRepStatusDate(iPlatformDate.getSysDate()); // 数据日期
        repTrade.setRepRemark(dto.getRemark()); // 备注

        // 调用基础组件.【交易报送组件】将交易报送信息Entity插入交易报送信息表
        restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/common/trdcommonbase/insertRepTrade",
                Arrays.asList(repTrade), Boolean.class);
    }
    /**
     * 7.4更新资产负债报送状态 利用金融产品代码作为条件把资产负债报送信息表中的报送状态更改为是
     *
     * @param dto 画面参数dto
     * @see
     * @since 1.0
     */
    private void assetDebtCbReportLogic_7_4(CfetsTrdBondInvestTradeDto dto) {
        // 利用金融产品代码作为条件把资产负债报送信息表中的报送状态更改为是
        restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/common/trdcommonbase/updateRepAssetDebt",
                dto.getFinprodId(), Boolean.class);
    }






    private String getPaBussType(String 交易方向) {
        // 平安业务类型(数据字典缓存.平安业务类型（按交易方向取对应值）)
        if (StringUtil.equals(DictCodeList.交易方向_买入.getValue(), 交易方向)) {
            return DictCodeListExt1.平安业务类型_债券类资产买入.getValue();
        }
        else if (StringUtil.equals(DictCodeList.交易方向_卖出.getValue(), 交易方向)) {
            return DictCodeListExt1.平安业务类型_债券类资产卖出.getValue();
        }
        // 一体化对应etliu start
        // else if (StringUtil.equals(DictCodeList.交易方向_行权.getValue(), 交易方向)) {
        else if (StringUtil.equals(DictCodeList.交易方向_行权_回售.getValue(), 交易方向)
                || StringUtil.equals(DictCodeList.交易方向_行权_赎回.getValue(), 交易方向)) {
            // 一体化对应etliu end
            return DictCodeListExt1.平安业务类型_债券类资产行权.getValue();
        }
        return null;
    }
    private PaTrdFinProductDealTotal 生成汇总扩展表(CfetsTrdBondInvestTradeDto entity) {
        PaTrdFinProductDealTotal productDealTotal = new PaTrdFinProductDealTotal();
        productDealTotal.setTotleTradeId(entity.getTotleTradeId());
        productDealTotal.setPaBussType(getPaBussType(entity.getPs()));
        return productDealTotal;
    }
    private TrdDealTotalAdd 生成汇总附属表(CfetsTrdBondInvestTradeDto entity,CfetsTrdConfirmRegInfoListResponse dtoReg) {
        TrdDealTotalAdd trdDealTotalAdd = new TrdDealTotalAdd();
        trdDealTotalAdd.setTotleTradeId(entity.getTotleTradeId());
        trdDealTotalAdd.setOurShareAmt(dtoReg.getOurShareAmt());
        trdDealTotalAdd.setLendinShareAmt(dtoReg.getLendinShareAmt());
        trdDealTotalAdd.setOurFpriceAmt(dtoReg.getOurFpriceAmt());
        trdDealTotalAdd.setLendinFpriceAmt(dtoReg.getLendinFpriceAmt()== null ? BigDecimal.ZERO : dtoReg.getLendinFpriceAmt());
        trdDealTotalAdd.setOurCpriceAmt(ArithmeticUtil.subtract(dtoReg.getCpriceAmt(), dtoReg.getLendinCpriceAmt()));
        trdDealTotalAdd.setLendinCpriceAmt(dtoReg.getLendinCpriceAmt() == null ? BigDecimal.ZERO : dtoReg.getLendinCpriceAmt());
        trdDealTotalAdd.setOurFpriceAmt2(dtoReg.getOwnIntAmt());
        trdDealTotalAdd.setLendinFpriceAmt2(dtoReg.getLendinIntAmt() == null ? BigDecimal.ZERO : dtoReg.getLendinIntAmt());
        // trdDealTotalAdd.setAssetRecommandOrg(dtoReg.getAssetRecommandOrg());
        //trdDealTotalAdd.setEntrustId(dtoReg.getEntrustId());
        return trdDealTotalAdd;
    }
    private void 生成组合交易(CfetsRegFinProductDealDto dto, String 交易编号, List<PtlTrade> ptlTrades) {
        PtlTrade ptlTrade = new PtlTrade();
        ptlTrade.setPortfolioId(dto.getPortfolioId()); // 组合代码
        ptlTrade.setBusiNo(交易编号); // 业务编号
        ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue()); // 业务表
        ptlTrade.setBusiId(dto.getFinprodId()); // 业务明细代码
        ptlTrade.setBusiType(DictCodeList.共通业务类型_债券类资产交易.getValue()); // 业务类型
        ptlTrade.setInvAim(dto.getInvAim()); // 投资目的
        ptlTrade.setSecManageAcctId(dto.getSecManageAcctId()); // 证券托管户代码
        ptlTrade.setSettleDate(dto.getSettleDate()); // 实际发生日
        ptlTrade.setWorkflowId(null); // 工作流代码
        ptlTrades.add(ptlTrade);
    }
    private void 生成交易扩展表(CfetsRegFinProductDealDto dto, String 交易编号,
                         List<PaTrdProductDeal> paTrdProductDeals) {
        PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
        paTrdProductDeal.setTradeId(交易编号); // 交易编号
        paTrdProductDeal.setPaBussType(getPaBussType(dto.getPs()));
        paTrdProductDeal.setDeptCode(null); // 交易所属部门
        paTrdProductDeal.setWorkflowId(null); // 工作流代码
        paTrdProductDeals.add(paTrdProductDeal);
    }

    private List<TrdTradeFeeDetail> 生成交易费用明细数据(List<TrdTradeFeeTotal> 汇总交易费用信息,  String  regTradeId,String tradeId) {
        if (汇总交易费用信息 == null || 汇总交易费用信息.isEmpty()) {
            return Lists.newArrayList();
        }
        List<TrdTradeFeeDetail> result = new ArrayList<>();
        //根据登记编号查询所有的费用信息
        List<CfetsTrdConfirmRegFeeInfoDto> regFeeList=cfetsTrdConfirmMapper.findAllRegFeeDetailInfoList(regTradeId);
        for(CfetsTrdConfirmRegFeeInfoDto regFeeInfo:regFeeList){
            TrdTradeFeeDetail trdTradeFeeDetail = new TrdTradeFeeDetail();
            trdTradeFeeDetail.setTradeId(tradeId);
            trdTradeFeeDetail.setFeeType(regFeeInfo.getFeeType());
            trdTradeFeeDetail.setIsPayWithTrade(regFeeInfo.getIsPayWithTrade());
            trdTradeFeeDetail.setFeeAmt(regFeeInfo.getFeeAmt());
            result.add(trdTradeFeeDetail);
        }
        return result;
    }
    private List<TrdTradeFeeTotal> getTrdTradeFeeTotles(String tradeId,String totleTradeId) {
        //根据汇总交易编号查询费用信息
        List<CfetsTrdConfirmRegFeeInfoDto> regFeeList=cfetsTrdConfirmMapper.findAllRegFeeInfoList(tradeId);
        List<TrdTradeFeeTotal> ttfdList = new ArrayList<>();
        for(CfetsTrdConfirmRegFeeInfoDto regFeeInfo:regFeeList){
            TrdTradeFeeTotal ttfd = new TrdTradeFeeTotal();
            ttfd.setTotleTradeId(totleTradeId);
            ttfd.setFeeType(regFeeInfo.getFeeType());
            ttfd.setIsPayWithTrade(regFeeInfo.getIsPayWithTrade());
            ttfd.setFeeAmt(regFeeInfo.getFeeAmt());
            ttfdList.add(ttfd);
        }
        return ttfdList;
    }
    private List<TrdTradeFeeTotal> getTrdTradeIbuFeeTotles(CfetsTrdConfirmRegInfoListResponse dto,String totleTradeId) {
        //根据汇总交易编号查询费用信息
        List<TrdTradeFeeTotal> ttfdList = new ArrayList<>();
        if(null!=dto && StringUtil.isNotEmpty(dto.getTrdFeeAmt())){
            TrdTradeFeeTotal ttfd = new TrdTradeFeeTotal();
            ttfd.setTotleTradeId(totleTradeId);
            ttfd.setFeeType(BussinessDictCodeList.费用类型_交易手续费.getValue());
            ttfd.setIsPayWithTrade(DictCodeList.是否_否.getValue());
            ttfd.setFeeAmt(dto.getTrdFeeAmt());
            ttfdList.add(ttfd);
        }
        if(null!=dto && StringUtil.isNotEmpty(dto.getSettleFeeAmt())){
            TrdTradeFeeTotal ttfd = new TrdTradeFeeTotal();
            ttfd.setTotleTradeId(totleTradeId);
            ttfd.setFeeType(BussinessDictCodeList.费用类型_结算手续费.getValue());
            ttfd.setIsPayWithTrade(DictCodeList.是否_否.getValue());
            ttfd.setFeeAmt(dto.getSettleFeeAmt());
            ttfdList.add(ttfd);
        }
        return ttfdList;
    }
    private List<TrdTradeFeeDetail> 生成交易Ibu费用明细数据(CfetsRegFinProductDealDto dto,String tradeId) {
        if(!StringUtil.isNotEmpty(dto.getTrdFeeAmt()) && !StringUtil.isNotEmpty(dto.getSettleFeeAmt())){
            return Lists.newArrayList();
        }
        List<TrdTradeFeeDetail> result = new ArrayList<>();
        //根据登记编号查询所有的费用信息
        if(StringUtil.isNotEmpty(dto.getTrdFeeAmt())){
            TrdTradeFeeDetail trdTradeFeeDetail = new TrdTradeFeeDetail();
            trdTradeFeeDetail.setTradeId(tradeId);
            trdTradeFeeDetail.setFeeType(BussinessDictCodeList.费用类型_交易手续费.getValue());
            trdTradeFeeDetail.setIsPayWithTrade(DictCodeList.是否_否.getValue());
            trdTradeFeeDetail.setFeeAmt(dto.getTrdFeeAmt());
            result.add(trdTradeFeeDetail);
        }
        if(StringUtil.isNotEmpty(dto.getSettleFeeAmt())){
            TrdTradeFeeDetail trdTradeFeeDetail = new TrdTradeFeeDetail();
            trdTradeFeeDetail.setTradeId(tradeId);
            trdTradeFeeDetail.setFeeType(BussinessDictCodeList.费用类型_结算手续费.getValue());
            trdTradeFeeDetail.setIsPayWithTrade(DictCodeList.是否_否.getValue());
            trdTradeFeeDetail.setFeeAmt(dto.getSettleFeeAmt());
            result.add(trdTradeFeeDetail);
        }
        return result;
    }

    private BigDecimal getDeatil费用金额(List<CfetsTrdConfirmRegFeeInfoDto> ttfdList, String 是否随交易支付) {
        BigDecimal result = BigDecimal.ZERO;
        for (CfetsTrdConfirmRegFeeInfoDto trdTradeFeeDetail : ttfdList) {
            if (是否随交易支付 == null || 是否随交易支付.equals(trdTradeFeeDetail.getIsPayWithTrade())) {
                result = ArithmeticUtil.doAdd(result, trdTradeFeeDetail.getFeeAmt());
            }
        }
        return result;
    }

    private BigDecimal get费用金额(List<TrdTradeFeeTotal> ttfdList, String 是否随交易支付, BigDecimal 占比) {
        BigDecimal result = BigDecimal.ZERO;
        for (TrdTradeFeeTotal trdTradeFeeDetail : ttfdList) {
            if (是否随交易支付 == null || 是否随交易支付.equals(trdTradeFeeDetail.getIsPayWithTrade())) {
                result = ArithmeticUtil.doAdd(result, trdTradeFeeDetail.getFeeAmt());
            }
        }
        if (占比 != null) {
            result = ArithmeticUtil.round(ArithmeticUtil.multiply(result, ArithmeticUtil.round(占比, 12, RoundingMode.HALF_UP)), 2,
                    RoundingMode.HALF_UP);
        }
        return result;
    }
    public static BussinessCodeParameter getBussinessCodeParameter(String ps) {
        BussinessCodeParameter codeParameter = new BussinessCodeParameter();
        if (StringUtil.equals(DictCodeList.交易方向_买入.getValue(), ps)) {
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券买入);
        }
        else {
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券卖出);
        }
        return codeParameter;
    }

    /**
     * 获取投资目的
     * @return 投资目的
     */
    public String getInvAim(Map<String,Object> map){
        String invAim = "";
        String portfolioId = (String)map.get("portfolioId");
        PtlPortfolio ptlPortfolio = null;
        if(StringUtil.isNotEmpty(portfolioId)){
            ptlPortfolio = ptlPortfolioMapper.selectByPrimaryKey(portfolioId);
        }
        invAim = BussinessDictCodeList.投资目的_公允价值变动计入当期损益.getValue();
        if(null!=ptlPortfolio){
            if(StringUtil.equals(ptlPortfolio.getProfitType(),DictCodeList.收益类型_货币型.getValue())
                    ||StringUtil.equals(ptlPortfolio.getProfitType(),DictCodeList.收益类型_收益型.getValue())){
                invAim = BussinessDictCodeList.投资目的_摊余成本类.getValue();
            }
        }
        return invAim;
    }
    public void baseTradeCommExecute(FinProductTradeDto finProductTradeDto) {
        if (finProductTradeDto == null) {
            return ;
        }
        //处理CSTP下行数据
        if(finProductTradeDto.getBaseTradeProductDealDtos() != null && !finProductTradeDto.getBaseTradeProductDealDtos().isEmpty()
                && finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal() != null &&
                StringUtil.isNotBlank(finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal().getfTradeId())){
            if(StringUtil.equals(finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal().getBusiType(),DictCodeList.共通业务类型_债券类资产交易.getValue())){
                baseTradeProductDealCommServiceImpl.updateInterfForZhaiJuan(finProductTradeDto);
            }else if(StringUtil.equals(finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal().getBusiType(),DictCodeList.共通业务类型_质押式回购交易.getValue())){
                baseTradeProductDealCommServiceImpl.updateInterfForZhiYaShi(finProductTradeDto);
            }
        }
        String workflowStatus = finProductTradeDto.getWorkflowStatus();
       /* if (DictCodeListExt1.交易流程状态_流程中.getValue().equals(workflowStatus)) {
            return deal流程中(finProductTradeDto);
        }
        else*/
        /* if (DictCodeListExt1.交易流程状态_有效.getValue().equals(workflowStatus)) {*/
        deal有效(finProductTradeDto);
        /* }*/
       /* else if (DictCodeListExt1.交易流程状态_撤销.getValue().equals(workflowStatus)) {
            return deal撤销(finProductTradeDto);
        }*/
        return ;
    }
    private void deal有效(FinProductTradeDto finProductTradeDto) {
        if (finProductTradeDto.getBaseTradeProductDealDtos() == null
                || finProductTradeDto.getBaseTradeProductDealDtos().isEmpty()) {
            return ;
        }
        // .调用基础组件.【投资委托组件.完成委托】，写入委托完成状况信息(投资委托场合才执行此步)
        /*     if(!finProductTradeDto.isNeedHandleFinishEntrust()){*/
        baseTradeProductDealCommServiceImpl.finishEntrust(finProductTradeDto.getBaseTradeProductDealDtos(), false);
        /*}*/
        return ;
    }



    /**
     * 1.8计算单位面值
     *
     * @param finprodId 金融产品代码
     * @param settleDate 交割日
     * @return 单位面值
     * @see
     * @since 1.0
     */
    public BigDecimal getUnitPrice(String finprodId, Date settleDate) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("finprodId", finprodId);
        parameters.put("settleDate", DateUtil.format(settleDate));
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondInvestTrade/getUnitPrice", parameters,
                BigDecimal.class);
    }
    private  void sendPositionMethod(CfetsTrdBondInvestTradeDto entity){
        List<CfetsEtrIbPosPortfolioRequest> ptlList=new ArrayList<>();
        for (TrdProductDeal dto : entity.getTrdProductDeals()) {
            //调用头寸
            CfetsEtrIbPosPortfolioRequest posport=new CfetsEtrIbPosPortfolioRequest();
            posport.setPortfolioId(dto.getPortfolioId());
            posport.setTradeId(dto.getTradeId());
            if(StringUtil.isNotEmpty(dto.getPortfolioId())){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("portfolioId", dto.getPortfolioId());
                map.put("acctType", BussinessDictCodeList.资金账户类型_托管账户.getValue());
                String cashAcctId = cfetsEtribBondInfo003Service.qryPortfolioCashAcctId(map);
                if (StringUtil.isNotEmpty(cashAcctId)) {
                    posport.setCashAcctId(cashAcctId); // 托管户
                }
            }
            posport.setSecAcctId(entity.getSecManageAcctId());
            posport.setSecAmount(ArithmeticUtil.multiply(dto.getShareAmt()==null?BigDecimal.ZERO:dto.getShareAmt(),new BigDecimal(100)));
            posport.setCashAmount(dto.getTradeAmt()==null?BigDecimal.ZERO:dto.getTradeAmt());
            entity.getTrdProductDealAdds().forEach(trdProductDealAdd -> {
                if (dto.getTradeId() != null
                        && dto.getTradeId().equals(trdProductDealAdd.getTradeId())) {
                    posport.setEtrId(trdProductDealAdd.getEntrustId());
                    posport.setIntegrateSecAmount(ArithmeticUtil.multiply(trdProductDealAdd.getLendinShareAmt()==null?BigDecimal.ZERO:trdProductDealAdd.getLendinShareAmt(),new BigDecimal(100)));
                    posport.setOwnSecAmount(ArithmeticUtil.multiply(trdProductDealAdd.getOurShareAmt()==null?BigDecimal.ZERO:trdProductDealAdd.getOurShareAmt(),new BigDecimal(100)));
                }
            });
            //查询指令的券面总额
            if(StringUtil.isNotEmpty(posport.getEtrId())){
                CfetsEtrIbBondinfoDto etrDto=cfetsTrdConfirmMapper.findEtrinfoByPrimaryKey(posport.getEtrId());
                if(null!=etrDto){
                    posport.setEtrSecAmount(ArithmeticUtil.multiply(etrDto.getShareNum(),new BigDecimal(100)));
                }
            }
            ptlList.add(posport);
        }
        //调用头寸
        CfetsEtrIbPositionRequest req=new CfetsEtrIbPositionRequest();
        req.setInterfaceType("Bond001");
        req.setUserId(null!=SecurityUtil.getLoginUser()?SecurityUtil.getLoginUser().getUserName():"SYSTEM");
        req.setTotalTradeId(entity.getTotleTradeId());
        req.setFlowStatus(DictCodeListExt3.流程节点_流程提交.getValue());
        req.setModelType(DictCodeListExt1.子系统代码_交易.getValue());
        req.setBusiType(DictCodeListExt3.头寸业务类型_现券交易.getValue());
        if(StringUtil.isNotEmpty(entity.getPs())
                && StringUtil.equals(entity.getPs(),BussinessDictCodeList.交易方向_行权回售.getValue())){
            req.setBusiType(DictCodeListExt3.头寸业务类型_债券行权交易.getValue()); // 业务类型：01债券一级投标，02现券交易，03债券行权交易，04债券存续期交易
        }
        req.setTradePlace(BussinessDictCodeList.交易场所_银行间.getValue());
        if(entity.getSettleSpeed().compareTo(new Long(0))==0){
            req.setSettleSpeed("01");
        }else if(entity.getSettleSpeed().compareTo(new Long(1))==0){
            req.setSettleSpeed("02");
        }else if(entity.getSettleSpeed().compareTo(new Long(2))==0){
            req.setSettleSpeed("03");
        }
        req.setBalanceCheck("N");
        req.setBusiAct(DictCodeListExt3.业务动作_成交确认.getValue());
        req.setPs(entity.getPs());
        req.setSystemAct(DictCodeListExt3.系统动作_新建.getValue());
        req.setCdate(entity.getTradeDate());
        req.setCcy(entity.getCcy());
        req.setFinprodId(entity.getFinprodId());
        req.setPtlList(ptlList);
        req.setbTotalTradeId(entity.getRegIbuId());
        PositionHttpRequest.send(req);
    }

}
