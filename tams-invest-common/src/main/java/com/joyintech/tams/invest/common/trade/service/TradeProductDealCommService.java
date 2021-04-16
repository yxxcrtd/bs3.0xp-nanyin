package com.joyintech.tams.invest.common.trade.service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.EtrFinishStatus;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.WflTrdProductDealAdd;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.CommonResult;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlSecPositionInfoService;
import com.joyintech.tams.invest.common.component.base.trade.BaseTradeProductDealService;
import com.joyintech.tams.invest.common.component.business.common.BaseAssetCashPositionCommService;
import com.joyintech.tams.invest.common.component.business.common.dto.PortfolioCashPositionsFlowInputDto;
import com.joyintech.tams.invest.common.component.business.common.dto.PortfolioPositionsFlowInputDto;
import com.joyintech.tams.invest.common.dto.*;
import com.joyintech.tams.invest.common.dto.trade.entrust.EtrFinishStatusDto;
import com.joyintech.tams.invest.common.service.MapperDelegateService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dao.BaseAssetCashPositionCommMapper;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.BussTrdPositionDto;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.component.business.period.service.impl
 * @Description: 金融产品交易共通
 * @date Date : 2018年09月27日 15:31
 */
@Service
public class TradeProductDealCommService {

    /**
     * 业务共通组件.资产头寸现金持仓共通组件
     */
    @Autowired
    private BaseAssetCashPositionCommService assetPositionsCashCommServiceImpl;

    @Autowired
    private BaseAssetCashPositionCommMapper baseAssetCashPositionCommMapper;

    /**
     * BussCommonMapperDelegateService
     */
    @Autowired
    private MapperDelegateService mapperDelegateService;

    @Autowired
    private TrdCommonBaseService trdCommonBaseService;

    @Autowired(required = false)
    private BasePtlSecPositionInfoService basePtlSecPositionInfoService;

    @Autowired(required = false)
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;

    @Autowired(required = false)
    private BaseTradeProductDealService baseTradeProductDealService;

    @Autowired(required = false)
    private BaseEtrInvestInfoService baseEtrInvestInfoService;

    @Autowired(required = false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;

    /**
     *
     */
    @Autowired
    private WflTrdFinProductDealTotalMapper wflTrdFinProductDealTotalMapper;

    /**
     *
     */
    @Autowired
    private WflPaTrdFinDealTotalMapper wflPaTrdFinDealTotalMapper;

    /**
     *
     */
    @Autowired
    private WflTrdProductDealMapper wflTrdProductDealMapper;
    /**
     *
     */
    @Autowired
    private WflTrdProductDealAddMapper wflTrdProductDealAddMapper;

    @Autowired
    private WflTrdDealTotalAddMapper wflTrdDealTotalAddMapper;
    /**
     *
     */
    @Autowired
    private WflPaTrdProductDealMapper wflPaTrdProductDealMapper;

    /**
     *
     */
    @Autowired
    private WflTrdProductDealOthMapper wflTrdProductDealOthMapper;

    /**
     *
     */
    @Autowired
    private WflStlPayOrdersMapper wflStlPayOrdersMapper;

    private static final List<String> 申购确认_赎回确认_除权登记_红利再投 = Arrays.asList(DictCodeList.交易方向_申购确认.getValue(),
        DictCodeList.交易方向_赎回确认.getValue(), DictCodeList.交易方向_除权登记.getValue(), DictCodeList.交易方向_红利再投.getValue());

    /**
     *
     */
    public CommonResult execute(FinProductTradeDto finProductTradeDto) {
        if (finProductTradeDto == null) {
            return new CommonResult(CommonResult.FAILD, "没有数据需要处理");
        }

        String workflowStatus = finProductTradeDto.getWorkflowStatus();
        if (DictCodeListExt1.交易流程状态_流程中.getValue().equals(workflowStatus)) {
            return deal流程中(finProductTradeDto);
        }
        else if (DictCodeListExt1.交易流程状态_有效.getValue().equals(workflowStatus)) {
            return deal有效(finProductTradeDto);
        }
        else if (DictCodeListExt1.交易流程状态_撤销.getValue().equals(workflowStatus)) {
            return deal撤销(finProductTradeDto);
        }
        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }

    private CommonResult deal撤销(FinProductTradeDto finProductTradeDto) {
        if (finProductTradeDto.getBaseTradeProductDealDtos() == null
            || finProductTradeDto.getBaseTradeProductDealDtos().isEmpty()) {
            return new CommonResult(CommonResult.FAILD, "没有数据需要处理");
        }
        if (!finProductTradeDto.isNeedSum()) {
            // 2.调用基础组件.【金融产品交易组件.撤销交易金融产品交易信息】，撤销金融产品交易信息
            List<BaseTradeProductDealRevokeDto> baseTradeProductDealRevokeDtos = new ArrayList<>();
            for (BaseTradeProductDealDto baseTradeProductDealDto : finProductTradeDto.getBaseTradeProductDealDtos()) {
                BaseTradeProductDealRevokeDto dto = new BaseTradeProductDealRevokeDto();
                dto.setTrdProductDeal(baseTradeProductDealDto.getTrdProductDeal());
                dto.setTrdProductDealAdd(baseTradeProductDealDto.getTrdProductDealAdd());
                dto.setStlPayOrdersList(baseTradeProductDealDto.getStlPayOrdersList());
                // dto.setStlOrdersList(baseTradeProductDealDto.getStlOrdersList());
                dto.setTrdFinProductBarterList(baseTradeProductDealDto.getTrdFinProductBarterList());
                dto.setTrdTradeFeeDetailList(baseTradeProductDealDto.getTrdTradeFeeDetailList());
                dto.setMstFileInputParameter(baseTradeProductDealDto.getMstFileInputParameter());
                dto.setMstFileOutputParameter(baseTradeProductDealDto.getMstFileOutputParameter());
                dto.setCancelRemark(baseTradeProductDealDto.getTrdProductDeal().getCancelRemark());
                dto.setpTradeId(baseTradeProductDealDto.getTrdProductDeal().getTradeId());
                baseTradeProductDealRevokeDtos.add(dto);
            }
            // Boolean result = restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/common/trdcommonbase/revokeTradeInfo",
            // baseTradeProductDealRevokeDtos, Boolean.class);
            boolean result = baseTradeProductDealService.revoke(baseTradeProductDealRevokeDtos);
            if (!result) {
                return new CommonResult(CommonResult.FAILD, "处理失败");
            }
        }

        /*----------------------zhangliang----add------2019/02/23-------------start--------------------*/
        BaseTradeProductDealDto baseTradeDto = finProductTradeDto.getBaseTradeProductDealDtos().get(0);
        if (baseTradeDto.getTrdProductDeal() != null
            && StringUtil.equals(baseTradeDto.getTrdProductDeal().getBusiType(), DictCodeList.共通业务类型_买断式回购交易.getValue())) {
            if (baseTradeDto.getSecAmount() != null) {
                baseTradeDto.getTrdProductDeal().setShareAmt(baseTradeDto.getSecAmount());
            }
        }
        /*----------------------zhangliang----add------2019/02/23-------------end--------------------*/

        // 3.调用【组合可用持仓与实际持仓查询更新组件】，写入类型为[实际]的组合持仓流水
        if (!insertPosition(finProductTradeDto)) {
            return new CommonResult(CommonResult.FAILD, "处理失败");
        }
        // 调用基础组件.【投资委托组件.取消完成委托】，撤销委托完成状况信息(投资委托场合才执行此步)
        finishEntrust(finProductTradeDto.getBaseTradeProductDealDtos(), true);
        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }

    private CommonResult deal有效(FinProductTradeDto finProductTradeDto) {
        if (finProductTradeDto.getBaseTradeProductDealDtos() == null
            || finProductTradeDto.getBaseTradeProductDealDtos().isEmpty()) {
            return new CommonResult(CommonResult.FAILD, "没有数据需要处理");
        }

        /*----------------------裴家俊-----add------2018/11/23-------------start--------------*/
        BaseTradeProductDealDto baseTradeDto = finProductTradeDto.getBaseTradeProductDealDtos().get(0);
        Map<String, Object> vDateNato = baseAssetCashPositionCommMapper.selectVDateNato(
            baseTradeDto.getTrdProductDeal().getFinprodId());
        String profitType = vDateNato == null ? null : (String)vDateNato.get("PROFIT_TYPE");// 获取收益类型
        List<StlPayOrdersDto> globalStlOrderList = new ArrayList<StlPayOrdersDto>();
        if (申购确认_赎回确认_除权登记_红利再投.contains(baseTradeDto.getTrdProductDeal().getPs())
            && DictCodeListExt1.金融产品类型_投管用_基金.getValue().equals(baseTradeDto.getTrdProductDeal().getFinprodType2())
            || 申购确认_赎回确认_除权登记_红利再投.contains(baseTradeDto.getTrdProductDeal().getPs())
               && DictCodeList.收益类型_净值型.getValue().equals(profitType)
               && DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue().equals(baseTradeDto.getTrdProductDeal().getFinprodType2())
            || 申购确认_赎回确认_除权登记_红利再投.contains(baseTradeDto.getTrdProductDeal().getPs())
               && DictCodeList.收益类型_货币型.getValue().equals(profitType)
               && DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue().equals(baseTradeDto.getTrdProductDeal().getFinprodType2())) {
            globalStlOrderList = baseTradeDto.getStlPayOrdersList();
            baseTradeDto.setStlPayOrdersList(new ArrayList<StlPayOrdersDto>());
        }
        /*-------------------裴家俊------add------2018/11/23-------------end--------------------*/
        if (!finProductTradeDto.isNeedSum()) {
            // 2.调用基础组件.【金融产品交易组件.新建金融产品交易信息】,写入金融产品交易信息
            baseTradeProductDealService.insert(finProductTradeDto.getBaseTradeProductDealDtos());
        }
        /*----------------------裴家俊----add------2018/11/23-------------start--------------------*/
        if (申购确认_赎回确认_除权登记_红利再投.contains(baseTradeDto.getTrdProductDeal().getPs())
            && DictCodeListExt1.金融产品类型_投管用_基金.getValue().equals(baseTradeDto.getTrdProductDeal().getFinprodType2())
            || 申购确认_赎回确认_除权登记_红利再投.contains(baseTradeDto.getTrdProductDeal().getPs())
               && DictCodeList.收益类型_净值型.getValue().equals(profitType)
               && DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue().equals(baseTradeDto.getTrdProductDeal().getFinprodType2())
            || 申购确认_赎回确认_除权登记_红利再投.contains(baseTradeDto.getTrdProductDeal().getPs())
               && DictCodeList.收益类型_货币型.getValue().equals(profitType)
               && DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue().equals(baseTradeDto.getTrdProductDeal().getFinprodType2())) {
            baseTradeDto.setStlPayOrdersList(globalStlOrderList);
        }
        /*----------------------裴家俊----add------2018/11/23-------------end--------------------*/

        // 3.写入类型为[实际]的组合持仓流水与组合现金流水
        insertPosition(finProductTradeDto);

        /*----------------------zhangliang----add------2019/02/23-------------start--------------------*/
        BigDecimal shareAmt = baseTradeDto.getTrdProductDeal().getShareAmt();
        if (baseTradeDto.getTrdProductDeal() != null
            && StringUtil.equals(baseTradeDto.getTrdProductDeal().getBusiType(), DictCodeList.共通业务类型_买断式回购交易.getValue())) {
            if (baseTradeDto.getSecAmount() != null) {
                baseTradeDto.getTrdProductDeal().setShareAmt(baseTradeDto.getSecAmount());
            }
        }
        /*----------------------zhangliang----add------2019/02/23-------------end--------------------*/

        // 3.调用基础组件.【投资委托组件.完成委托】，写入委托完成状况信息(投资委托场合才执行此步)
        finishEntrust(finProductTradeDto.getBaseTradeProductDealDtos(), false);

        /*----------------------zhangliang----add------2019/02/23-------------start--------------------*/
        baseTradeDto.getTrdProductDeal().setShareAmt(shareAmt);
        /*----------------------zhangliang----add------2019/02/23-------------end--------------------*/
        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }

    private Boolean finishEntrust(List<BaseTradeProductDealDto> BaseTradeProductDealDtoList, boolean isCancel) {
        List<EtrFinishStatusDto> baseEtrInvestInfoDtos = new ArrayList<>();
        for (BaseTradeProductDealDto baseTradeProductDealDto : BaseTradeProductDealDtoList) {
            if (StringUtil.isEmpty(baseTradeProductDealDto.getTrdProductDealAdd().getEntrustId())) {
                continue;
            }
            EtrFinishStatusDto status = new EtrFinishStatusDto();
            status.setEntrustId(baseTradeProductDealDto.getTrdProductDealAdd().getEntrustId());
            status.setTradeId(baseTradeProductDealDto.getTrdProductDealAdd().getTradeId());
            status.setFinishDate(baseTradeProductDealDto.getTrdProductDeal().getTradeDate());
            if (StringUtil.equals(baseTradeProductDealDto.getTrdProductDeal().getFinprodType2(),
                DictCodeListExt1.金融产品类型_投管用_债券.getValue())
                || StringUtil.equals(baseTradeProductDealDto.getTrdProductDeal().getFinprodType2(),
                    DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue())
                || StringUtil.equals(baseTradeProductDealDto.getTrdProductDeal().getFinprodType2(),
                    DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())) {
                // status.setFinishAmt(new BigDecimal(baseTradeProductDealDto.getTrdProductDeal().getShareAmt().intValue() * 100));
                status.setFinishAmt(baseTradeProductDealDto.getTrdProductDeal().getShareAmt());
            }
            //add by wangpeng 20190923 股票增加委托金额（ 当委托时是委托金额，则更新交易金额，委托时是委托数量，则更新委托数量）
            else if(StringUtil.equals(baseTradeProductDealDto.getTrdProductDeal().getFinprodType2(),
                    DictCodeListExt1.金融产品类型_投管用_股票.getValue())){
            	BaseEtrInvestInfoDto baseEtrInvestInfoDto = baseEtrInvestInfoService.queryActiveMandate(baseTradeProductDealDto.getTrdProductDealAdd().getEntrustId());
            	if(baseEtrInvestInfoDto!=null&&baseEtrInvestInfoDto.getEtrEntrust()!=null){
            		//委托时委托金额不为空 ，则更新交易金额，反之 更新交易数量
            		if(StringUtil.isNotEmpty(baseEtrInvestInfoDto.getEtrEntrust().getEntrustAmt())){
            			status.setFinishAmt(baseTradeProductDealDto.getTrdProductDeal().getTradeAmt());
            		}else{
            			status.setFinishAmt(baseTradeProductDealDto.getTrdProductDeal().getShareAmt());
            		}
            	}

            }
            //add by wangpeng end
            else {
                status.setFinishAmt(StringUtil.isNotEmpty(
                    baseTradeProductDealDto.getTrdProductDeal().getPrinAmt()) ? baseTradeProductDealDto.getTrdProductDeal().getPrinAmt() : StringUtil.isNotEmpty(
                        baseTradeProductDealDto.getTrdProductDeal().getShareAmt()) ? baseTradeProductDealDto.getTrdProductDeal().getShareAmt() : baseTradeProductDealDto.getTrdProductDeal().getTradeAmt());
            }
            status.setTrader(baseTradeProductDealDto.getTrdProductDeal().getTrader());
            baseEtrInvestInfoDtos.add(status);
        }
        if (!baseEtrInvestInfoDtos.isEmpty() && null != baseEtrInvestInfoService) {
            boolean result = true;
            if (isCancel) {
                for (EtrFinishStatus status : baseEtrInvestInfoDtos) {
                    result = result
                             && baseEtrInvestInfoService.cancelCompleteDelegate(status.getEntrustId(), status.getTradeId());
                }
                // return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/common/entrustcommonbase/cancelEntrust", baseEtrInvestInfoDtos,
                // Boolean.class);
            }
            else {
                result = baseEtrInvestInfoService.completeDelegate(baseEtrInvestInfoDtos);
                // return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/common/entrustcommonbase/finishEntrust", baseEtrInvestInfoDtos,
                // Boolean.class);
            }
            return result;
        }
        return Boolean.TRUE;
    }

    /**
     * 处理流程中状态
     *
     * @return CommonResult
     * @param finProductTradeDto
     */
    private CommonResult deal流程中(FinProductTradeDto finProductTradeDto) {
        if (finProductTradeDto.getBaseTradeProductDealDtos() == null
            || finProductTradeDto.getBaseTradeProductDealDtos().isEmpty()) {
            return new CommonResult(CommonResult.FAILD, "没有数据需要处理");
        }
        // 1.调用【组合可用持仓与实际持仓查询更新组件】与【组合可用现金与实际现金查询更新组件】执行持仓与现金的插入操作

        /*----------------------zhangliang----add------2019/02/23-------------start--------------------*/
        BaseTradeProductDealDto baseTradeDto = finProductTradeDto.getBaseTradeProductDealDtos().get(0);
        if (baseTradeDto.getTrdProductDeal() != null
            && StringUtil.equals(baseTradeDto.getTrdProductDeal().getBusiType(), DictCodeList.共通业务类型_买断式回购交易.getValue())) {
            if (baseTradeDto.getSecAmount() != null) {
                baseTradeDto.getTrdProductDeal().setShareAmt(baseTradeDto.getSecAmount());
            }
        }
        /*----------------------zhangliang----add------2019/02/23-------------end--------------------*/

        if (!insertPosition(finProductTradeDto)) {
            return new CommonResult(CommonResult.FAILD, "处理失败");
        }
        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }

    /**
     * @description: 2.写入类型为[实际]的组合持仓流水与组合现金流水
     * @param: [BaseTradeProductDealDtoList, 持仓类型]
     * @return: void
     * @date: 2018/9/29
     */
    public Boolean insertPosition(FinProductTradeDto finProductTradeDto) {
        DictCodeList 持仓类型 = null;
        boolean needNegative = false;
        if (DictCodeListExt1.交易流程状态_撤销.getValue().equals(finProductTradeDto.getWorkflowStatus())) {
            持仓类型 = DictCodeList.持仓类型_实际;
            needNegative = true;
        }
        else if (DictCodeListExt1.交易流程状态_流程中.getValue().equals(finProductTradeDto.getWorkflowStatus())) {
            持仓类型 = DictCodeList.持仓类型_可用;
            needNegative = false;
        }
        else if (DictCodeListExt1.交易流程状态_有效.getValue().equals(finProductTradeDto.getWorkflowStatus())) {
            持仓类型 = DictCodeList.持仓类型_实际;
            needNegative = false;
        }
        if (持仓类型 == null) {
            return false;
        }

        List<PtlSecPositionFlowDto> ptlSecPositionFlows = new ArrayList<>();
        List<PtlCashPositionFlowDto> ptlCashPositionFlows = new ArrayList<>();
        for (BaseTradeProductDealDto baseTradeProductDealDto : finProductTradeDto.getBaseTradeProductDealDtos()) {
            PortfolioPositionsFlowInputDto inputDto = new PortfolioPositionsFlowInputDto();
            inputDto.setPositionType(持仓类型.getValue());
            inputDto.setTrdProductDeal(baseTradeProductDealDto.getTrdProductDeal());
            inputDto.setPortfolioId(baseTradeProductDealDto.getTrdProductDealAdd().getPortfolioId());
            inputDto.setBonusMode(baseTradeProductDealDto.getTrdProductDealAdd().getPayType());
            inputDto.setAssetType2(finProductTradeDto.getAssetType2());
            ptlSecPositionFlows.addAll(assetPositionsCashCommServiceImpl.positionDeal(inputDto));

            if (baseTradeProductDealDto.getStlPayOrdersList() != null
                && !baseTradeProductDealDto.getStlPayOrdersList().isEmpty()) {
                PortfolioCashPositionsFlowInputDto cashInputDto = new PortfolioCashPositionsFlowInputDto();
                cashInputDto.setPositionType(持仓类型.getValue());
                cashInputDto.setTrdProductDeal(baseTradeProductDealDto.getTrdProductDeal());
                cashInputDto.setPortfolioId(baseTradeProductDealDto.getTrdProductDealAdd().getPortfolioId());
                cashInputDto.setBonusMode(baseTradeProductDealDto.getTrdProductDealAdd().getPayType());
                cashInputDto.setAssetType2(finProductTradeDto.getAssetType2());
                cashInputDto.setStlPayOrders(baseTradeProductDealDto.getStlPayOrdersList());
                cashInputDto.setIeFlag(finProductTradeDto.getIeFlag());
                List<PtlCashPositionFlowDto> resultList = assetPositionsCashCommServiceImpl.cashDeal(cashInputDto);
                ptlCashPositionFlows.addAll(resultList);
            }
        }
        if (needNegative) {
            if (ptlCashPositionFlows != null && !ptlCashPositionFlows.isEmpty()) {
                for (PtlCashPositionFlowDto ptlCashPosition : ptlCashPositionFlows) {
                    if (ptlCashPosition.getAmount() != null) {
                        ptlCashPosition.setAmount(ptlCashPosition.getAmount().negate());
                    }
                    if (ptlCashPosition.getOrderId().lastIndexOf('R') != ptlCashPosition.getOrderId().length() - 1) {
                        ptlCashPosition.setOrderId(ptlCashPosition.getOrderId() + "R");
                    }
                }
            }
            if (ptlSecPositionFlows != null && !ptlSecPositionFlows.isEmpty()) {
                for (PtlSecPositionFlowDto ptlSecPosition : ptlSecPositionFlows) {
                    if (ptlSecPosition.getAmount() != null) {
                        ptlSecPosition.setAmount(ptlSecPosition.getAmount().negate());
                    }
                    if (ptlSecPosition.getTradeId().lastIndexOf('R') != ptlSecPosition.getTradeId().length() - 1) {
                        ptlSecPosition.setTradeId(ptlSecPosition.getTradeId() + "R");
                    }
                }
            }
        }
        PtlPositionHoldDto ptlPositionHoldDto = new PtlPositionHoldDto();
        ptlPositionHoldDto.setPtlSecPositionFlows(ptlSecPositionFlows);
        ptlPositionHoldDto.setPtlCashPositionFlows(ptlCashPositionFlows);
        if (ptlPositionHoldDto.getPtlSecPositionFlows() != null && !ptlPositionHoldDto.getPtlSecPositionFlows().isEmpty()) {
            for (PtlSecPositionFlowDto ptlSecPositionFlow : ptlPositionHoldDto.getPtlSecPositionFlows()) {
                basePtlSecPositionInfoService.inertSecPositionFlow(ptlSecPositionFlow);
            }
        }
        if (ptlPositionHoldDto.getPtlCashPositionFlows() != null && !ptlPositionHoldDto.getPtlCashPositionFlows().isEmpty()) {
            for (PtlCashPositionFlowDto ptlCashPositionFlow : ptlPositionHoldDto.getPtlCashPositionFlows()) {
                basePtlCashPositionInfoService.insertCashPositionFlow(ptlCashPositionFlow);
            }
        }
        return Boolean.TRUE;
    }

    public void deleteDatas(String synchroedTradeId) {
        // 1、删除登陆业务表
        mapperDelegateService.deleteBySpecificKey("TRD_FIN_PRODUCT_DEAL_TOTAL", "TOTLE_TRADE_ID", synchroedTradeId);
        mapperDelegateService.deleteBySpecificKey("PA_TRD_FIN_PRODUCT_DEAL_TOTAL", "TOTLE_TRADE_ID", synchroedTradeId);
        mapperDelegateService.deleteBySpecificKey("TRD_PRODUCT_DEAL", "TRADE_ID", synchroedTradeId);
        mapperDelegateService.deleteBySpecificKey("TRD_PRODUCT_DEAL_ADD", "TRADE_ID", synchroedTradeId);
        mapperDelegateService.deleteBySpecificKey("PA_TRD_PRODUCT_DEAL", "TRADE_ID", synchroedTradeId);
        mapperDelegateService.deleteBySpecificKey("PTL_TRADE", "BUSI_NO", synchroedTradeId);
        mapperDelegateService.deleteBySpecificKey("PA_BRANCH_SALE_DETAIL", "TRADE_ID", synchroedTradeId);
        mapperDelegateService.deleteBySpecificKey("TRD_TRADE_FEE_DETAIL", "TRADE_ID", synchroedTradeId);
        mapperDelegateService.deleteBySpecificKey("STL_PAY_ORDERS", "TRADE_ID", synchroedTradeId);
    }

    public CommonResult delete(String workflowId) {
        // 根据工作流代码查询交易编号、组合代码
        WflTrdProductDealAdd wflTrdProductDealAdd = trdCommonBaseService.selectWflTrdProductDealAdd(workflowId);
        // 调用授信共同 sjj start
        // 根据工作流代码查询交易相关信息
        AllTradeCommonInputDto inputDto = trdCommonBaseService.selectWflTrdProductDeal(workflowId);
        // 所有交易生效后调用的共通方法

        deleteWflTableData(workflowId);

        if (inputDto != null && wflTrdProductDealAdd != null) {
            AllTradeCommonUtil.handleBeforeTradeComplete(inputDto, true);
        }

        if (wflTrdProductDealAdd == null) {
            return new CommonResult(CommonResult.FAILD, "没有数据需要处理");
        }
        else {
            // 费用明细表
            mapperDelegateService.deleteBySpecificKey("TRD_TRADE_FEE_DETAIL", "TRADE_ID", wflTrdProductDealAdd.getTradeId());
            mapperDelegateService.deleteBySpecificKey("FIN_TRADE_PRODUCT", "FINPROD_ID", wflTrdProductDealAdd.getTradeId());
        }

        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }

    public CommonResult deleteWflData(String workflowId) {
        deleteWflTableData(workflowId);
        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }

    private void deleteWflTableData(String workflowId) {
        // 1.删除各业务流程信息
        // 汇总表
//        mapperDelegateService.deleteBySpecificKey("WFL_TRD_FIN_PRODUCT_DEAL_TOTAL", "WORKFLOW_ID", workflowId);
        wflTrdFinProductDealTotalMapper.deleteByWorkflowId(workflowId);
        // 汇总扩展表
//        mapperDelegateService.deleteBySpecificKey("WFL_PA_TRD_FIN_DEAL_TOTAL", "WORKFLOW_ID", workflowId);
        wflPaTrdFinDealTotalMapper.deleteByWorkflowId(workflowId);

        wflTrdDealTotalAddMapper.deleteByWorkflowId(workflowId);

        // 金融产品交易信息DTO.金融产品交易Entity -> 金融产品交易业务流程表
//        mapperDelegateService.deleteBySpecificKey("WFL_TRD_PRODUCT_DEAL", "WORKFLOW_ID", workflowId);
        wflTrdProductDealMapper.deleteByWorkflowId(workflowId);

        // 金融产品交易信息DTO.金融产品交易附属表Entity -> 金融产品交易附属业务流程表
//        mapperDelegateService.deleteBySpecificKey("WFL_TRD_PRODUCT_DEAL_ADD", "WORKFLOW_ID", workflowId);
        wflTrdProductDealAddMapper.deleteByWorkflowId(workflowId);
        // 金融产品交易扩展表Entity -> 金融产品交易扩展业务流程表
//        mapperDelegateService.deleteBySpecificKey("WFL_PA_TRD_PRODUCT_DEAL", "WORKFLOW_ID", workflowId);
        wflPaTrdProductDealMapper.deleteByWorkflowId(workflowId);
        // 金融产品交易业务流程其他信息Entity -> 金融产品交易业务流程其他信息表
//        mapperDelegateService.deleteBySpecificKey("WFL_TRD_PRODUCT_DEAL_OTH", "WORKFLOW_ID", workflowId);
        wflTrdProductDealOthMapper.deleteByWorkflowId(workflowId);
        // 金融产品交易信息DTO.划款指令表Entity -> 划款指令业务流程表
//        mapperDelegateService.deleteBySpecificKey("WFL_STL_PAY_ORDERS", "WORKFLOW_ID", workflowId);
        wflStlPayOrdersMapper.deleteByWorkflowId(workflowId);
    }

    /**
     * 产品端金融产品交易组合现金和组合持仓插入共通
     */
    public CommonResult trdProductInsertPosition(BussTrdPositionDto bussTrdPositionDto) {
        if (bussTrdPositionDto == null || StringUtil.isEmptyList(bussTrdPositionDto.getTrdProductDealList())) {
            return new CommonResult(CommonResult.FAILD, "没有数据需要处理");
        }

        String positionType = null;
        boolean needNegative = false;
        boolean resultFlag = true;
        if (DictCodeListExt1.交易流程状态_流程中.getValue().equals(bussTrdPositionDto.getWorkflowStatus())) {
            positionType = DictCodeList.持仓类型_可用.getValue();
        }
        else if (DictCodeListExt1.交易流程状态_有效.getValue().equals(bussTrdPositionDto.getWorkflowStatus())) {
            positionType = DictCodeList.持仓类型_实际.getValue();
        }
        else if (DictCodeListExt1.交易流程状态_撤销.getValue().equals(bussTrdPositionDto.getWorkflowStatus())) {
            positionType = DictCodeList.持仓类型_实际.getValue();
            needNegative = true;
        }
        List<PtlSecPositionFlowDto> ptlSecPositionFlows = new ArrayList<>();
        List<PtlCashPositionFlowDto> ptlCashPositionFlows = new ArrayList<>();
        for (TrdProductDeal trdProductDeal : bussTrdPositionDto.getTrdProductDealList()) {
            PortfolioPositionsFlowInputDto inputDto = new PortfolioPositionsFlowInputDto();
            inputDto.setPositionType(positionType);
            inputDto.setTrdProductDeal(trdProductDeal);
            inputDto.setPortfolioId(bussTrdPositionDto.getPortfolioId());
            inputDto.setBonusMode(bussTrdPositionDto.getBonusMode());
            inputDto.setAssetType2(null);
            ptlSecPositionFlows.addAll(assetPositionsCashCommServiceImpl.positionDeal(inputDto));
        }
        if (bussTrdPositionDto.getStlPayOrders() != null && !bussTrdPositionDto.getStlPayOrders().isEmpty()) {
            PortfolioCashPositionsFlowInputDto cashInputDto = new PortfolioCashPositionsFlowInputDto();
            cashInputDto.setPositionType(positionType);
            TrdProductDeal deal = bussTrdPositionDto.getTrdProductDealList().get(0);
            if (DictCodeList.交易方向_赎回收益确认.getValue().equals(deal.getPs())) { // 赎回撤销时，取得的第一条记录可能是赎回收益确认，需要转成共通方法里用到的交易方向
                deal.setPs(DictCodeList.交易方向_产品赎回.getValue());
            }
            cashInputDto.setTrdProductDeal(bussTrdPositionDto.getTrdProductDealList().get(0));
            cashInputDto.setPortfolioId(bussTrdPositionDto.getPortfolioId());
            cashInputDto.setBonusMode(bussTrdPositionDto.getBonusMode());
            cashInputDto.setAssetType2(null);
            cashInputDto.setStlPayOrders(bussTrdPositionDto.getStlPayOrders());
            cashInputDto.setNeedNegative(needNegative);
            ptlCashPositionFlows.addAll(assetPositionsCashCommServiceImpl.cashDeal(cashInputDto));
            if (baseEtrCashPositionCommService != null) {
            	if( null!=positionType &&
            			((positionType.equals(DictCodeList.持仓类型_实际.getValue())
    					&& DictCodeListExt1.交易流程状态_撤销.getValue().equals(bussTrdPositionDto.getWorkflowStatus()))
    					|| DictCodeListExt1.交易流程状态_流程中.getValue().equals(bussTrdPositionDto.getWorkflowStatus()))) {//fixed BSTOZ-857BUG add by zhouxiuping at 20191029
                baseEtrCashPositionCommService.productEtrCashPosition(cashInputDto);
            	}
            }
        }
        if (needNegative) {
            if (ptlCashPositionFlows != null && !ptlCashPositionFlows.isEmpty()) {
                for (PtlCashPositionFlowDto ptlCashPosition : ptlCashPositionFlows) {
                    if (ptlCashPosition.getAmount() != null) {
                        ptlCashPosition.setAmount(ptlCashPosition.getAmount().negate());
                    }
                }
            }
            if (ptlSecPositionFlows != null && !ptlSecPositionFlows.isEmpty()) {
                for (PtlSecPositionFlowDto ptlSecPosition : ptlSecPositionFlows) {
                    if (ptlSecPosition.getAmount() != null) {
                        ptlSecPosition.setAmount(ptlSecPosition.getAmount().negate());
                    }
                }
            }
        }
        PtlPositionHoldDto ptlPositionHoldDto = new PtlPositionHoldDto();
        ptlPositionHoldDto.setPtlSecPositionFlows(ptlSecPositionFlows);
        ptlPositionHoldDto.setPtlCashPositionFlows(ptlCashPositionFlows);
        if (!ptlSecPositionFlows.isEmpty() || !ptlCashPositionFlows.isEmpty()) {
            if (ptlPositionHoldDto.getPtlSecPositionFlows() != null && !ptlPositionHoldDto.getPtlSecPositionFlows().isEmpty()) {
                for (PtlSecPositionFlowDto ptlSecPositionFlow : ptlPositionHoldDto.getPtlSecPositionFlows()) {
                    basePtlSecPositionInfoService.inertSecPositionFlow(ptlSecPositionFlow);
                }
            }
            if (ptlPositionHoldDto.getPtlCashPositionFlows() != null && !ptlPositionHoldDto.getPtlCashPositionFlows().isEmpty()) {
                for (PtlCashPositionFlowDto ptlCashPositionFlow : ptlPositionHoldDto.getPtlCashPositionFlows()) {
                    basePtlCashPositionInfoService.insertCashPositionFlow(ptlCashPositionFlow);
                }
            }
        }
        if (!resultFlag) {
            return new CommonResult(CommonResult.FAILD, "处理失败");
        }
        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }
}
