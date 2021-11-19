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
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.business.common.pojo.BaseMap;
import com.joyintech.tams.cfets.etrib.dto.*;
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
import com.joyintech.tams.invest.common.CommonResult;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.cstp.service.CstpCommonService;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.InvestInterfCommDto;
import com.joyintech.tams.invest.common.dto.etr.EtrCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.etr.EtrPositionHoldDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.trade.entrust.EtrFinishStatusDto;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.common.service.InvestInterfCommService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.common.trade.dto.PreTrdCommonDto;
import com.joyintech.tams.invest.common.trade.service.PreTrdCommonBaseService;
import com.joyintech.tams.invest.common.trade.service.impl.etrcash.EtrCommonCashPositionDealService;
import com.joyintech.tams.invest.trade.bond.dto.TrdBondInvestTradeDto;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.workflow.engine.constant.OperationType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import static com.joyintech.tams.invest.common.trade.AllTradeCommonUtil.copyFileInfoByFileNo;
import static com.joyintech.tams.invest.trade.bond.service.TrdBondInvestTrade002Service.getBussinessCodeParameter;


/**
 * 银行间交易成交确认
 * @author
 * @version 1.0 2021年10月26日
 * @see CfetsTrdConfirmService
 * @since 1.0
 */
@Service("CfetsTrdConfirmService")
public class CfetsTrdConfirmService extends BaseService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(CfetsTrdConfirmService.class);

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
    private IPlatformDate iPlatformDate;
    // 交易份额小数保留位数
    private int decimalPlaces_4 = 4;
    /**
     * 交易登记列表分页查询
     *
     * @param page int
     * @param length int
     * @param pageParam int
     * @return
     * @see
     * @since 1.0
     */
    public Page<CfetsTrdConfirmRegInfoListResponse> findAllRegInfoList(CfetsTrdConfirmRegInfoListRequest dto) {
        Map<String, Object> pageParam = BeanUtil.object2Map(dto);
        pageParam.put("sort",dto.getPageMode().getSort());
        pageParam.put("direction",dto.getPageMode().getDirection());
        String userId = SecurityUtil.getLoginUser().getUserName();
        if (StringUtils.isNotEmpty(userId)) {
            pageParam.put("userId", SecurityUtil.getLoginUser().getUserName());
            return super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(), () -> cfetsTrdConfirmMapper.findAllRegInfoList(pageParam));
        }else{
            return null;
        }
    }
    /**
     * 交易执行-成交确认
     */
    public void trdConfirmSaveMethod(CfetsTrdConfirmRegInfoListResponse dto){
        //对dto进行封装
        CfetsTrdBondInvestTradeDto entity=prepareEntity(dto);
        //插入交易表
        doCompleteMethod(entity,OperationType.NEW);

        //日志表


        //发送头寸


    }
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
            trdFinProductDealTotal.setSettleSpeed(Long.parseLong(entity.getSettleSpeedStr()));
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

            // 反向交易生成汇总表数据
            /*String autoGenerateNegateDealFlag = restTemplate.getForObject(
                    ServiceApplication.TRADE_SERVICE_NAME + "/common/trdcommonbase/selectParam/" + "COM" + "/"
                            + BussinessConstant.IS_AUTO_GENERATE_NEGATE_DEAL,
                    String.class);
            String 反向交易编号 = null;
            if (DictCodeList.是否_是.getValue().equals(autoGenerateNegateDealFlag)
                    && (DictCodeList.交易方向_买入.getValue().equals(entity.getPs())
                    || DictCodeList.交易方向_卖出.getValue().equals(entity.getPs()))) {
                PtlPortfolio mcp = ptlPortfolioMapper.selectByPrimaryKey(entity.getCounterId());
                if (mcp != null) {
                    BussinessCodeParameter codeParameter = getBussinessCodeParameter(entity.getPs());
                    反向交易编号 = baseGetBusinessId.buildBussinessCode(codeParameter);
                }
            }*/

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


                if(StringUtil.isNotBlank(entity.getfTradeId())){
                    baseTradeProductDealDto.setfTradeId(entity.getfTradeId());
                }
                baseTradeProductDealDto.setTrdProductDeal(trdProductDeal);
                entity.getTrdProductDealAdds().forEach(trdProductDealAdd -> {
                    if (trdProductDeal.getTradeId() != null
                            && trdProductDeal.getTradeId().equals(trdProductDealAdd.getTradeId())) {
                        baseTradeProductDealDto.setTrdProductDealAdd(trdProductDealAdd);
                    }
                });

                //查询是否存在预交易
         /*       if (StringUtils.isNotEmpty(trdProductDeal.getfTradeId())) {
                    Map<String, Object> paramPre = new HashMap<String, Object>();
                    paramPre.put("CSTP_TRADE_ID", trdProductDeal.getfTradeId());
                    // 获预交易编号
                    List<TrdMatchInfo> preTradeIdList = restTemplate.postForList(
                            ServiceApplication.TRADE_SERVICE_NAME + "/TrdRepoTrade/selectPreTradeId", paramPre,
                            TrdMatchInfo.class);
                    if (preTradeIdList != null && preTradeIdList.size() > 0) {
                        for (TrdMatchInfo trdMatchInfo : preTradeIdList) {
                            TrdProductDealPreKey key = new TrdProductDealPreKey();
                            key.setTradeId(trdMatchInfo.getPreTradeId());
                            key.setWorkflowStatus(DictCodeListExt1.交易流程状态_有效.getValue());
                            TrdProductDealPre trdProductDealPre = trdProductDealPreMapper.selectByPrimaryKey(key);
                            if (trdProductDealPre != null) {
                                // 处理组合及指令头寸
                                PreTrdCommonDto preTrdCommonDto = new PreTrdCommonDto();
                                List<TrdProductDealPre> listTrdProductDealPre = new ArrayList<TrdProductDealPre>();
                                listTrdProductDealPre.add(trdProductDealPre);
                                List<TrdProductDealAddPre> listTrdProductDealAddPre = new ArrayList<TrdProductDealAddPre>();
                                TrdProductDealAddPre trdProductDealAddPre = trdProductDealAddPreMapper
                                        .selectByPrimaryKey(trdMatchInfo.getPreTradeId());
                                listTrdProductDealAddPre.add(trdProductDealAddPre);
                                preTrdCommonDto.setTrdProductDealPreList(listTrdProductDealPre);
                                preTrdCommonDto.setTrdProductDealAddPreList(listTrdProductDealAddPre);
                                preTrdCommonBaseService.excutePosition(preTrdCommonDto, true);
                            }
                        }
                    }
                }*/

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
                //处理cstp下行数据和头寸 TODO hxy 需要同时处理指令状态，分发状态，指令完成度，指令交易完成度吗？

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

            // 反向交易生成汇总表数据
           /* if (反向交易编号 != null) {
                String 交易对手_子组合 = entity.getCounterId();// 子组合交易对手
                //子组合的反向交易不存母组合，存子组合
                //String 交易对手的母组合 = AllTradeCommonUtil.exchangePortfolio(entity.getCounterId());// 交易对手的母组合，设置成反向交易的组合代码


                // 组合代码与交易对手对调
                // 交易方向为买入；
                // 其他字段与原始交易相同；
                // String 原交易母组合代码 = trdProductDealAdd.getPortfolioId();
                String 原交易子组合代码 = entity.getActPortfolioId();

                TrdFinProductDealTotal trdFinProductDealTotal1 = new TrdFinProductDealTotal();
                BeanUtil.copyPropertiesIgnoreNull(trdFinProductDealTotal1, entity);
                trdFinProductDealTotal1.setTotleTradeId(反向交易编号);
                trdFinProductDealTotal1.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
                trdFinProductDealTotal1.setIsCancel(DictCodeList.是否_否.getValue());
                trdFinProductDealTotal1.setCounterId(原交易子组合代码);
                *//*处理反向交易方向*//*
                if(DictCodeList.交易方向_买入.getValue().equals(trdFinProductDealTotal1.getPs())){
                    trdFinProductDealTotal1.setPs(DictCodeList.交易方向_卖出.getValue());
                }else if(DictCodeList.交易方向_卖出.getValue().equals(trdFinProductDealTotal1.getPs())){
                    trdFinProductDealTotal1.setPs(DictCodeList.交易方向_买入.getValue());
                }
                //add by yujiaping 2020-12-07 BS-1501  内部转仓转转仓，清算速度不做特殊处理，卖出是什么买入就是什么start
                trdFinProductDealTotal1.setSettleSpeed(Long.valueOf(entity.getSettleSpeedStr()));
                //add by yujiaping 2020-12-07 BS-1501  内部转仓转转仓，清算速度不做特殊处理，卖出是什么买入就是什么end

                // 上面copyPropertiesIgnoreNull时 错误，重新赋值
                trdFinProductDealTotal1.setInvAim(entity.getTurnInvAim());

                trdFinProductDealTotal1.setPortfolioId(交易对手_子组合);
                trdFinProductDealTotalMapper.insertSelective(trdFinProductDealTotal1);

                PaTrdFinProductDealTotal paTrdFinDealTotal1 = entity.getPaTrdFinProductDealTotal();
                if(DictCodeListExt1.平安业务类型_债券类资产买入.getValue().equals(paTrdFinDealTotal1.getPaBussType())){
                    paTrdFinDealTotal1.setPaBussType(DictCodeListExt1.平安业务类型_债券类资产卖出.getValue());
                }else if(DictCodeListExt1.平安业务类型_债券类资产卖出.getValue().equals(paTrdFinDealTotal1.getPaBussType())){
                    paTrdFinDealTotal1.setPaBussType(DictCodeListExt1.平安业务类型_债券类资产买入.getValue());
                }
                paTrdFinDealTotal1.setTotleTradeId(反向交易编号);
                paTrdFinProductDealTotalMapper.insertSelective(paTrdFinDealTotal1);

                TrdDealTotalAdd trdDealTotalAdd1 = entity.getTrdDealTotalAdd();
                trdDealTotalAdd1.setTotleTradeId(反向交易编号);
                trdDealTotalAddMapper.insertSelective(trdDealTotalAdd1);

                // 存入交易费用信息
                if (!StringUtil.isEmptyList(entity.getTrdTradeFeeTotals())) {
                    entity.getTrdTradeFeeTotals().forEach(feeTotal -> {
                        feeTotal.setTotleTradeId(trdFinProductDealTotal1.getTotleTradeId());
                        trdTradeFeeTotalMapper.insertSelective(feeTotal);
                    });
                }
            }*/
        }
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

    /**
     * 保存提交时，后台数据转换
     * @param cfetsDto
     * @return
     */
/*    public CfetsTrdBondInvestTradeDto packageEquity(CfetsTrdConfirmRegInfoListResponse dto) {
        CfetsTrdBondInvestTradeDto entity=prepareEntity(dto);


        return entity;


    }*/
    /**
     * 由画面输入的参数dto，构筑整个需要反映到数据库的实体
     *
     * @param dto 画面输入的参数dto
     * @see
     * @since 1.0
     */
    public CfetsTrdBondInvestTradeDto prepareEntity(CfetsTrdConfirmRegInfoListResponse dto) {
        //根据汇总交易编号查询登记数据
        CfetsTrdConfirmRegInfoListResponse dtoReg=cfetsTrdConfirmMapper.selectRegInfoByKey(dto.getTotleTradeId());
        CfetsTrdBondInvestTradeDto entity=new CfetsTrdBondInvestTradeDto();
        BeanUtil.copyPropertiesIgnoreNull(entity,dtoReg);
        if (baseGetBusinessId != null) {
            BussinessCodeParameter codeParameter = getBussinessCodeParameter(dto.getPs());
            entity.setTotleTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
        }
        else {
            entity.setTotleTradeId(IDGenerator.generate());
        }
        // 业务类型
        entity.setBusiType(DictCodeList.共通业务类型_债券类资产交易.getValue());
        // 金融产品类型（估值核算）
        // 分支序号
        String branch = CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE);
        Long branchLong = Long.parseLong(branch);
        entity.setBranch(branchLong);
        entity.setFinprodType(BussinessDictCodeList.金融产品类型_投管用_债券.getValue());

       /* entity.setPs(dtoReg.getPs());
        entity.setFinprodId(dtoReg.getPs());
        entity.setFinprodType2(dtoReg.getFinprodType2());
        entity.setTradeDate(dtoReg.getTradeDate());
        entity.setSettleDate(dtoReg.getSettleDate());
        entity.setCcy(dtoReg.getCcy());
        entity.setUnitCprice(dtoReg.getUnitCprice());
        entity.setUnitInt(dtoReg.getUnitInt());
        entity.setUnitFprice(dtoReg.getUnitFprice());
        entity.setParValue(dtoReg.getParValue());
        entity.setCpriceAmt(dtoReg.getCpriceAmt());
        entity.setIntAmt(dtoReg.getIntAmt());
        entity.setFpriceAmt(dtoReg.getFpriceAmt());
        entity.setYield(dtoReg.getYield());
        entity.setExerYield(dtoReg.getExerYield());
        entity.setInvAim(dtoReg.getInvAim());
        entity.setpTradeId();*/

        // 单位面值
        entity.setParValue(this.getUnitPrice(entity.getFinprodId(), dtoReg.getSettleDate()));
        // 交易本金 = 画面.净价总额
        entity.setPrinAmt(dtoReg.getCpriceAmt());

        // 券面总额
        entity.setTotalFaceValue(dto.getFaceValue());
        if (entity.getFinprodId().indexOf("F01_") != 0) {
            entity.setFinprodId("F01_" + dto.getFinprodId());
        }
        // 金融产品交易Entity 构造开始----------------------------------------------------------




        // 交易份额 = 画面.券面总额/100
        entity.setShareAmt(ArithmeticUtil.divide(dtoReg.getFaceValue(), new BigDecimal(100), decimalPlaces_4, RoundingMode.HALF_UP));
        // 交易金额 = 画面.交易金额
        entity.setTradeAmt(dtoReg.getTradeAmt());
        // 到期收益率 = 画面.到期收益率/100
        if (dtoReg.getYield() != null) {
            entity.setYield(ArithmeticUtil.divide(dtoReg.getYield(), new BigDecimal(100), 14));
        }
        // 行权收益率 = 画面.行权收益率/100
        if (dtoReg.getExerYield() != null) {
            entity.setExerYield(ArithmeticUtil.divide(dtoReg.getExerYield(), new BigDecimal(100), 14));
        }
        // 是否结清 = 数据字典缓存.是否.是
        entity.setIsClean(DictCodeList.是否_否.getValue());
        entity.setIsCancel(DictCodeList.是否_否.getValue());

        // 是否外部交易
        entity.setIsOutTrade(DictCodeList.是否_是.getValue());

        // 本方交易员
        entity.setTrader(dtoReg.getOuTraderId());
        // 交易对手
        entity.setCounterId(dtoReg.getCounterId());
        // 对手方交易员
        entity.setCounterTrader(dtoReg.getCounterTrader());

        entity.setTgBusiType(getPaBussType(dtoReg.getPs()));
        if(StringUtil.equals(dtoReg.getTradeMarket(),DictCodeListExt1.交易场所_银行间.getValue())){
            entity.setTradePlat(DictCodeList.交易平台_外汇交易中心.getValue());
        }else{
            entity.setTradePlat(DictCodeList.交易平台_其它.getValue());
        }

        // 汇总扩展表
        entity.setPaTrdFinProductDealTotal(生成汇总扩展表(entity));

        // 汇总附属表
        entity.setTrdDealTotalAdd(生成汇总附属表(entity,dtoReg));

        // 设置费用信息
        TrdTradeFeeTotal trdTradeFeeDetail = null;
        //根据汇总交易编号查询费用信息

        List<TrdTradeFeeTotal> ttfdList = getTrdTradeFeeDetails(dtoReg.getTotleTradeId());

        entity.setFeeAmt(get费用金额(ttfdList, null, null));
        entity.setFeeAmtPay(get费用金额(ttfdList, DictCodeList.是否_是.getValue(), null));
        entity.setFeeAmtUnpay(get费用金额(ttfdList, DictCodeList.是否_否.getValue(), null));
        entity.setTrdTradeFeeTotals(ttfdList);
        // =====================人员信息=====================
        TrdInvestPersonInfo002Dto trdInvestPersonInfo = new TrdInvestPersonInfo002Dto();
        // 本方交易员
        trdInvestPersonInfo.setTrader(dtoReg.getTrader());
        // 交易对手
        trdInvestPersonInfo.setCounterId(dtoReg.getCounterId());
        trdInvestPersonInfo.setIsOutTrade(BussinessDictCodeList.是否_是.getValue());
        // 对手方交易员
        trdInvestPersonInfo.setCounterTrader(dtoReg.getCounterTrader());
        entity.setTrdInvestPersonInfo(trdInvestPersonInfo);

        // =====================人员信息=====================


        List<TrdProductDealAdd> trdProductDealAdds = new ArrayList<>();
        entity.setTrdProductDealAdds(trdProductDealAdds);
        List<PaTrdProductDeal> paTrdProductDeals = new ArrayList<>();
        List<PtlTrade> ptlTrades = new ArrayList<>();
        // 设置交易费用明细
        List<TrdTradeFeeDetail> trdTradeFeeDetails = new ArrayList<>();
        entity.setTrdTradeFeeDetails(trdTradeFeeDetails);


        // 设置交易表
        List<TrdProductDeal> deals = new ArrayList<>();
        entity.setTrdProductDeals(deals);



        //根据交易编号查询所有登记明细
        List<CfetsRegFinProductDealDto>  regDealList=cfetsTrdConfirmMapper.findAllRegDealList(dtoReg.getTotleTradeId());
        for(CfetsRegFinProductDealDto regDeal:regDealList){
            TrdProductDeal deal = new TrdProductDeal();
            BeanUtil.copyPropertiesIgnoreNull(deal, regDeal);
            deal.setTradeId(baseGetBusinessId.buildBussinessCode(getBussinessCodeParameter(deal.getPs())));
            // 交易本金 = 画面.净价总额
            deal.setPrinAmt(regDeal.getCpriceAmt());
            deal.setFeeAmt(get费用金额(ttfdList, null, null));
            deal.setFeeAmtPay(get费用金额(ttfdList, DictCodeList.是否_是.getValue(), null));
            deal.setFeeAmtUnpay(get费用金额(ttfdList, DictCodeList.是否_否.getValue(), null));
            deal.setTotleTradeId(entity.getTotleTradeId());
            deals.add(deal);

            // 金融产品交易附属表Entity 构造开始----------------------------------------------------------
            TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
            trdProductDealAdd.setTradeId(deal.getTradeId()); // 交易编号
            trdProductDealAdd.setPortfolioId(regDeal.getPortfolioId()); // 投资组合代码
            trdProductDealAdd.setEntrustId(regDeal.getEntrustId()); // 委托代码
            trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue()); // 生成方式
            trdProductDealAdd.setOurShareAmt(regDeal.getOurShareAmt());
            trdProductDealAdd.setLendinShareAmt(regDeal.getLendinShareAmt());
            trdProductDealAdd.setOurCpriceAmt(regDeal.getOurCpriceAmt());
            trdProductDealAdd.setLendinCpriceAmt(regDeal.getLendinCpriceAmt());
            trdProductDealAdd.setOurFpriceAmt(regDeal.getOurFpriceAmt());
            trdProductDealAdd.setLendinFpriceAmt(regDeal.getLendinFpriceAmt());
            trdProductDealAdd.setOurIntAmt(regDeal.getOurIntAmt());
            trdProductDealAdd.setLendinIntAmt(regDeal.getLendinIntAmt());

            trdProductDealAdds.add(trdProductDealAdd);

            // 组合交易表Entity 构造开始----------------------------------------------------------
            生成组合交易(regDeal, deal.getTradeId(), ptlTrades);


            // 金融产品交易扩展Entity 构造开始----------------------------------------------------------
            生成交易扩展表(regDeal, deal.getTradeId(), paTrdProductDeals);

            trdTradeFeeDetails.addAll(生成交易费用明细数据(ttfdList,regDeal.getTradeId(),deal.getTradeId()));

        }

        entity.setPtlTrade(ptlTrades);
        // 组合交易表Entity 构造结束----------------------------------------------------------




        // =====================划款指令信息=====================
        List<BussTrdTransferOrderDto> bussTrdTransferOrder = new ArrayList<>();
        BussSettleOrderParameterDto bussSettleOrderParameterDto = new BussSettleOrderParameterDto();
        bussSettleOrderParameterDto.setIsTotalTrade(BussinessDictCodeList.是否_是.getValue());
        bussSettleOrderParameterDto.setBankLendingType(null);
       // bussSettleOrderParameterDto.setPortfolioId(trdBondInvestTradeDto.getPortfolioId());
        TrdProductDeal deal = new TrdProductDeal();
        deal.setFinprodType2(BussinessDictCodeList.金融产品类型_投管用_债券.getValue());
        deal.setSettleDate(dtoReg.getSettleDate());
        deal.setTradeDate(dtoReg.getTradeDate());
        deal.setPs(dtoReg.getPs());
        deal.setCcy(dtoReg.getCcy());
        deal.setTradeAmt(dtoReg.getTradeAmt());
        deal.setCounterId(dtoReg.getCounterId());
        deal.setTradeMarket(dtoReg.getTradeMarket());
        deal.setTradePlat(entity.getTradePlat());
        deal.setSecManageAcctId(dtoReg.getSecManageAcctId());
        deal.setTradeMarketMode(dtoReg.getTradeMarketMode());
        bussSettleOrderParameterDto.setDeal(deal);
        BussTrdTransferOrderDto bussTrdTransferOrderDto = bussTrdTransferOrderService.generateOrder(bussSettleOrderParameterDto);
        bussTrdTransferOrderDto.setPayAcctNoList(null);
        bussTrdTransferOrderDto.setRecAcctNoList(null);
        bussTrdTransferOrderDto.setIsTotalTrade(BussinessDictCodeList.是否_是.getValue());
        if (StringUtil.isNotEmpty(dto.getSecManageAcctId())) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("secAcctId", dto.getSecManageAcctId());
            String secAcctSource = cfetsTrdConfirmMapper.selectSecAcctSource(param);
            if (StringUtil.equals(BussinessDictCodeList.账户来源_自营.getValue(), secAcctSource)
                    && StringUtil.equals(dtoReg.getPs(), BussinessDictCodeList.交易方向_买入.getValue())) {
                bussTrdTransferOrderDto.setIsNeedSend(BussinessDictCodeList.是否_否.getValue());
            }
        }
        bussTrdTransferOrderDto.setTradeId(entity.getTotleTradeId());
        bussTrdTransferOrder.add(bussTrdTransferOrderDto);
        entity.setBussTrdTransferOrder(bussTrdTransferOrderService.save(bussTrdTransferOrder));
        // =====================划款指令信息=====================

        entity.getBussTrdTransferOrder().get(0).setTradeId(dto.getTotleTradeId());
        // 划款指令表Entity 构造结束----------------------------------------------------------

        entity.setPaTrdProductDeals(paTrdProductDeals);
        // 金融产品交易扩展Entity 构造结束----------------------------------------------------------
        return entity;
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
        trdDealTotalAdd.setOurShareAmt(ArithmeticUtil.divide(dtoReg.getOurShareAmt(), 100, 4));
        trdDealTotalAdd.setLendinShareAmt(ArithmeticUtil.divide(dtoReg.getLendinShareAmt(), 100, 4));
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
    private List<TrdTradeFeeTotal> getTrdTradeFeeDetails(String tradeId) {
        //根据汇总交易编号查询费用信息
        List<CfetsTrdConfirmRegFeeInfoDto> regFeeList=cfetsTrdConfirmMapper.findAllRegFeeInfoList(tradeId);
        List<TrdTradeFeeTotal> ttfdList = new ArrayList<>();
        for(CfetsTrdConfirmRegFeeInfoDto regFeeInfo:regFeeList){
            TrdTradeFeeTotal ttfd = new TrdTradeFeeTotal();
            ttfd.setTotleTradeId(tradeId);
            ttfd.setFeeType(regFeeInfo.getFeeType());
            ttfd.setIsPayWithTrade(regFeeInfo.getIsPayWithTrade());
            ttfd.setFeeAmt(regFeeInfo.getFeeAmt());
            ttfdList.add(ttfd);
        }
        return ttfdList;
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
        if (DictCodeListExt1.交易流程状态_有效.getValue().equals(workflowStatus)) {
             deal有效(finProductTradeDto);
        }
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
        if(!finProductTradeDto.isNeedHandleFinishEntrust()){
            baseTradeProductDealCommServiceImpl.finishEntrust(finProductTradeDto.getBaseTradeProductDealDtos(), false);
        }
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

}
