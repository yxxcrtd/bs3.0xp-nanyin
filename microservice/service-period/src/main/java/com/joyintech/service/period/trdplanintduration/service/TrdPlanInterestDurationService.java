package com.joyintech.service.period.trdplanintduration.service;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.common.autoperiod.common.dao.PeriodCommonMapper;
import com.joyintech.tams.invest.common.autoperiod.common.service.InvestPeriodCommonService;
import com.joyintech.service.period.trdplanintduration.dao.TrdPlanInterestDurationMapper;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.common.TrdCommonDeleteDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.trdplanintduration.TrdPlanInterestDurationDto;
import com.joyintech.tams.invest.common.risk.service.RiskCreditListCommonValidatorService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.service.TradeProductDealCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: microservice
 * @Package com.joyintech.service.period.trdbondduration.service
 * @Description: 计划类资产利率型 存续期 微服务用Service
 * @date Date : 2018年09月21日 14:40
 */
@Service
public class TrdPlanInterestDurationService extends BaseService {

    @Autowired
    private TrdPlanInterestDurationMapper trdPlanInterestDurationMapper;

    @Autowired
    private InvestPeriodCommonService investPeriodCommonService;

    @Autowired
    private PeriodCommonMapper periodCommonMapper;

    @Autowired
    private IPlatformDate platformDateImpl;

    @Autowired
    private TradeProductDealCommService baseTradeProductDealCommService;

    @Autowired
    private BaseTradeProductDealCommService baseTradeProductDealCommServiceImpl;
    
    @Autowired
    private RiskCreditListCommonValidatorService riskCreditListCommonValidatorService;

    public Page<TrdPlanInterestDurationDto> findPage(int page, int length, Map<String, Object> param) {
        setParams(param);
        setParamsForSearch(param);
        return queryForPageForSelectReturn(page, length, () -> trdPlanInterestDurationMapper.findPage(param));
    }

    public List<TrdPlanInterestDurationDto> selectAllData(Map<String, Object> param) {
        setParams(param);
        setParamsForSearch(param);
        return trdPlanInterestDurationMapper.findPage(param);
    }

    private void setParamsForSearch(Map<String, Object> param) {
        List<String> statusValid = param.get("statusValid") == null ? null : (List)param.get("statusValid");

        String cashType = param.get("cashType") == null ? null : (String)param.get("cashType");
        param.put("paBizType", cashType);
        StringBuilder cashTypeSb = new StringBuilder();
        if (cashType != null) {
            if (cashType.contains(DictCodeListExt1.平安业务类型_计划类资产_利率型_中间付息.getValue())
                || cashType.contains(DictCodeListExt1.平安业务类型_计划类资产_利率型_中间还本.getValue())) {
                cashTypeSb.append(DictCodeList.现金流类型_中间收息.getValue());
                cashTypeSb.append(DictCodeList.现金流类型_中间还本.getValue());
            }
            if (cashType.contains(DictCodeListExt1.平安业务类型_计划类资产_利率型_到期.getValue())) {
                cashTypeSb.append(DictCodeList.现金流类型_到期收息.getValue());
                cashTypeSb.append(DictCodeList.现金流类型_到期还本.getValue());
            }
            if (cashType.contains(DictCodeListExt1.平安业务类型_计划类资产_利率型_中间付息.getValue())) {
                param.put("cashType", DictCodeList.现金流类型_中间收息.getValue());
            }
            else if (cashType.contains(DictCodeListExt1.平安业务类型_计划类资产_利率型_中间还本.getValue())) {
                param.put("cashType", DictCodeList.现金流类型_中间还本.getValue());
            }
            else if (cashType.contains(DictCodeListExt1.平安业务类型_计划类资产_利率型_到期.getValue())) {
                param.put("cashType", DictCodeList.现金流类型_到期还本.getValue());
            }
            param.put("cashTypeSb", cashTypeSb.toString());
        }
    }

    private void setParams(Map<String, Object> param) {
        param.put("PAGE_DATE_STATUS01", DictCodeListExt1.画面数据状态_待处理.getValue());
        param.put("PAGE_DATE_STATUS03", DictCodeListExt1.画面数据状态_流程中.getValue());
        param.put("PAGE_DATE_STATUS05", DictCodeListExt1.画面数据状态_有效.getValue());
        param.put("PAGE_DATE_STATUS06", DictCodeListExt1.画面数据状态_撤销.getValue());
        param.put("CASH_CLASS_TYPE01", DictCodeList.现金流类型_中间收息.getValue());
        param.put("CASH_CLASS_TYPE02", DictCodeList.现金流类型_中间还本.getValue());
        param.put("CASH_CLASS_TYPE03", DictCodeList.现金流类型_到期还本.getValue());
        param.put("CASH_CLASS_TYPE04", DictCodeList.现金流类型_到期收息.getValue());
        param.put("PS_T07", DictCodeList.交易方向_到期.getValue());
        param.put("PS_T09", DictCodeList.交易方向_收息.getValue());
        param.put("CASH_TYPE_1_02", DictCodeListExt1.现金流一级类型_利息.getValue());
        param.put("CASH_TYPE_2_C02", DictCodeListExt1.现金流二级类型_利息.getValue());
        // 数据字典缓存.交易状态.初始
        param.put("TRADE_STATUS01", DictCodeListExt1.交易状态_初始.getValue());
        param.put("TRADE_STATUS03", DictCodeListExt1.交易状态_撤销.getValue());
        param.put("YESNO_N", DictCodeList.是否_否.getValue());
        param.put("YESNO_Y", DictCodeList.是否_是.getValue());
        // 数据字典缓存.持仓类型.实际持仓
        param.put("POSITION_TYPE03", DictCodeList.持仓类型_实际.getValue());
        param.put("FINPROD_TYPE_TGF17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        param.put("TRD15", DictCodeList.共通业务类型_计划类资产_利率型_存续期.getValue());
        param.put("PROFIT_TYPE02", DictCodeList.收益类型_收益型.getValue());

        String plateDate = platformDateImpl.getSysDateStr();
        param.put("PLATEFORM_DATE", DateUtil.parse(plateDate));
        if (param.get(BussinessConstant.PLUS_DAYS) != null) {
            param.put("PLATEFORM_DATE_OTHER", DateUtil.plusDays(plateDate, (Integer)param.get(BussinessConstant.PLUS_DAYS)));
        }
        else {
            param.put("PLATEFORM_DATE_OTHER", DateUtil.plusDays(plateDate, 90));
        }
    }

    public TrdPlanInterestDurationDto findEffectiveData(Map<String, Object> params) {
        setParams(params);
        TrdPlanInterestDurationDto trdPlanInterestDurationDto=trdPlanInterestDurationMapper.findEffectiveData(params);
        //查询资产是不是还本不带息资产
         int count=trdPlanInterestDurationMapper.getisPayInt(trdPlanInterestDurationDto.getFinprodId());
         if(count<1){
             trdPlanInterestDurationDto.setIntAmtUnpayFlag(false);//不是还本不带息的资产,不展示累计还本未付金额
         }
        return trdPlanInterestDurationDto;
    }

    public List<FinAssetIntInfo> findAssetIntInfo(Map<String, Object> params) {
        setParams(params);
        return trdPlanInterestDurationMapper.findAssetIntInfo(params);
    }

    public String doDelete(TrdCommonDeleteDto trdCommonDeleteDto) {
        investPeriodCommonService.deleteByWorkflowId(trdCommonDeleteDto.getWorkflowId());
        return Boolean.TRUE.toString();
    }

    public String doComplete(TrdPlanInterestDurationDto durationDto) {
        String updateUser = durationDto.getUpdateUser();
        Date updateTime = new Date();
        if (BussinessConstant.REVOKE.equals(durationDto.getOperateType())) {
            //查找授信交易TRADE_ID 撤销释放额度用
            List<String> tradeidList = trdPlanInterestDurationMapper.findCeTradeIdByTotleTradeId(durationDto.getBusiCode());
            //删除授信占用
            if (tradeidList != null && !tradeidList.isEmpty()){
                tradeidList.forEach(tradeid -> {
                    riskCreditListCommonValidatorService.deleteCredit(tradeid);
                });
            }
            
            TrdFinProductDealTotal trdFinProductDealTotal = investPeriodCommonService.revoke汇总和交易相关表(durationDto.getBusiCode(),
                updateUser, durationDto.getRevokeReason(), null, null);
            // 删除其他表
            investPeriodCommonService.deleteByWorkflowId(durationDto.getWorkflowId());
            return Boolean.TRUE.toString();
        }
        investPeriodCommonService.doInsertAndDelete(durationDto.getBaseTotalTradeInfoDto(), updateUser, updateTime,
            durationDto.getWorkflowId());

        List<BaseTradeInfoDto> baseTradeInfoDtos = durationDto.getBaseTotalTradeInfoDto().getBaseTradeInfoDtoList();
        if (baseTradeInfoDtos != null && !baseTradeInfoDtos.isEmpty()) {
            baseTradeInfoDtos.forEach(baseTradeInfoDto -> {
                String portfolioId = baseTradeInfoDto.getTrdProductDealAdd() != null ? baseTradeInfoDto.getTrdProductDealAdd().getPortfolioId() : baseTradeInfoDto.getBaseTrade().getTrdProductDealAdd().getPortfolioId();

                String 产品登记编码 = periodCommonMapper.selectProdRegistCode(portfolioId,
                        DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
                if (StringUtil.isNotEmpty(产品登记编码)) {
                    if (baseTradeInfoDto.getTrdProductDeal() != null) {
                        investPeriodCommonService.insertRep(durationDto, updateUser, updateTime, baseTradeInfoDto.getTrdProductDeal(), 产品登记编码);
                    }
                    if (baseTradeInfoDto.getBaseTrade() != null && baseTradeInfoDto.getBaseTrade().getTrdProductDeal() != null) {
                        investPeriodCommonService.insertRep(durationDto, updateUser, updateTime, baseTradeInfoDto.getBaseTrade().getTrdProductDeal(),
                                产品登记编码);
                    }
                }

                
                // 一体化对应etliu start
                // 所有交易生效后调用的共通方法
                if (baseTradeInfoDto.getTrdProductDeal() != null) {
                    AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                    // 存续期增加通道费 add by renping 20191018 start
                    if(!com.joyintech.framework.util.StringUtil.isEmptyList(baseTradeInfoDto.getTrdTradeFeeDetail())) {
                        inputDto.setTrdTradeFeeDetail(baseTradeInfoDto.getTrdTradeFeeDetail().get(0));
                    }
                    inputDto.setTrdProductDealAdd(baseTradeInfoDto.getTrdProductDealAdd());
                    // 存续期增加通道费 add by renping 20191018 end
                    inputDto.setProdid(portfolioId);
                    inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
                    inputDto.setTrdProductDeal(baseTradeInfoDto.getTrdProductDeal());
                    inputDto.setPtlTradeList(baseTradeInfoDto.getPtlTrade());
                    inputDto.setStlPayOrders(baseTradeInfoDto.getStlPayOrders());
                    AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
                }

                baseTradeInfoDto.getBaseTradeInfoDtoList().forEach(baseTradeInfoDto1 -> {
                    if(baseTradeInfoDto1.getTrdProductDeal()!= null && baseTradeInfoDto1.getTrdProductDealAdd() != null ){
                        AllTradeCommonInputDto inputDto1 = new AllTradeCommonInputDto();
                        inputDto1.setProdid(baseTradeInfoDto1.getTrdProductDealAdd().getPortfolioId());
                        inputDto1.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
                        inputDto1.setTrdProductDeal(baseTradeInfoDto1.getTrdProductDeal());
                        inputDto1.setTrdProductDealAdd(baseTradeInfoDto1.getTrdProductDealAdd());
                        inputDto1.setPtlTradeList(baseTradeInfoDto1.getPtlTrade());
                        inputDto1.setStlPayOrders(baseTradeInfoDto1.getStlPayOrders());
                        AllTradeCommonUtil.handleAfterTradeComplete(inputDto1);
                    }

                    if (baseTradeInfoDto1.getBaseTrade() != null && baseTradeInfoDto1.getBaseTrade().getTrdProductDeal() != null) {
                        AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                        inputDto.setTrdProductDealAdd(baseTradeInfoDto1.getBaseTrade().getTrdProductDealAdd());
                        inputDto.setProdid(baseTradeInfoDto1.getBaseTrade().getTrdProductDealAdd().getPortfolioId());
                        inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
                        inputDto.setTrdProductDeal(baseTradeInfoDto1.getBaseTrade().getTrdProductDeal());
                        inputDto.setPtlTradeList(baseTradeInfoDto1.getBaseTrade().getPtlTrade());
                        inputDto.setStlPayOrders(baseTradeInfoDto1.getStlPayOrders());
                        AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
                    }
                });
                
                if (baseTradeInfoDto.getBaseTrade() != null && baseTradeInfoDto.getBaseTrade().getTrdProductDeal() != null) {
                    AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                    // 存续期增加通道费 add by renping 20191018 start
                    if (baseTradeInfoDto.getTrdProductDeal() == null
                        && !com.joyintech.framework.util.StringUtil.isEmptyList(
                            baseTradeInfoDto.getTrdTradeFeeDetail())) {
                        inputDto.setTrdTradeFeeDetail(
                            baseTradeInfoDto.getTrdTradeFeeDetail().get(0));
                    }
                    inputDto.setTrdProductDealAdd(baseTradeInfoDto.getTrdProductDealAdd());
                    // 存续期增加通道费 add by renping 20191018 end
                    inputDto.setProdid(portfolioId);
                    inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
                    inputDto.setTrdProductDeal(baseTradeInfoDto.getBaseTrade().getTrdProductDeal());
                    inputDto.setPtlTradeList(baseTradeInfoDto.getBaseTrade().getPtlTrade());
                    inputDto.setStlPayOrders(baseTradeInfoDto.getStlPayOrders());
                    AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
                }
                // 一体化对应etliu end


                if(baseTradeInfoDto.getRepaymentUnpayInterestBaseTradeInfoDtoList()!=null && baseTradeInfoDto.getRepaymentUnpayInterestBaseTradeInfoDtoList().size()>0 ){
                    baseTradeInfoDto.getRepaymentUnpayInterestBaseTradeInfoDtoList().forEach(receDto->{
                        String portfolioId1 = receDto.getTrdProductDealAdd() != null ?
                                receDto.getTrdProductDealAdd().getPortfolioId() : receDto.getBaseTrade().getTrdProductDealAdd().getPortfolioId();
                        调估值接口(receDto, portfolioId1); // 还本不带息调用估值接口
                    });
                }
                baseTradeInfoDto.getBaseTradeInfoDtoList().forEach(subRece->{
                    if(subRece.getRepaymentUnpayInterestBaseTradeInfoDtoList()!=null){
                        for(BaseTradeInfoDto rece:subRece.getRepaymentUnpayInterestBaseTradeInfoDtoList()){
                            String portfolioId1 = rece.getTrdProductDealAdd() != null ?
                                    rece.getTrdProductDealAdd().getPortfolioId() : rece.getBaseTrade().getTrdProductDealAdd().getPortfolioId();
                            调估值接口(rece, portfolioId1); // 子组合调用估值接口
                        }
                    }
                });

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
        //update zhangweiqi 通道费修改 2019-10-16 begin
        if(!com.joyintech.framework.util.StringUtil.isEmptyList(baseTradeInfoDto.getTrdTradeFeeDetail())) {
            inputDto.setTrdTradeFeeDetail(baseTradeInfoDto.getTrdTradeFeeDetail().get(0));
        }
        //update zhangweiqi 通道费修改 2019-10-16 end
        inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
        if (baseTradeInfoDto.getTrdProductDeal() != null) {
            inputDto.setTrdProductDeal(baseTradeInfoDto.getTrdProductDeal());
            inputDto.setTrdProductDealAdd(baseTradeInfoDto.getTrdProductDealAdd());
            inputDto.setPtlTradeList(baseTradeInfoDto.getPtlTrade());
            inputDto.setStlPayOrders(baseTradeInfoDto.getStlPayOrders());
            AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
            // 存续期增加通道费 add by renping 20191018 start
            inputDto.setTrdTradeFeeDetail(null);
            // 存续期增加通道费 add by renping 20191018 end
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
     * 插入流程表，查询流程中数据用到汇总和汇总扩展，详情页面利用留痕表展示
     *
     * @param baseTotalTradeInfoDto BaseTotalTradeInfoDto
     * @return 是否成功
     */
    public String doInsertBaseTradeInfo(BaseTotalTradeInfoDto baseTotalTradeInfoDto) {
        if(baseTotalTradeInfoDto.isBack()){
            baseTradeProductDealCommServiceImpl.delete(baseTotalTradeInfoDto.getTrdFinProductDealTotal().getWorkflowId());
        }else{
            baseTradeProductDealCommService.deleteWflData(baseTotalTradeInfoDto.getTrdFinProductDealTotal().getWorkflowId());
        }
        return investPeriodCommonService.insertWflData(baseTotalTradeInfoDto);
    }

    /**
     * 插入流程表，查询流程中数据用到汇总和汇总扩展，详情页面利用留痕表展示
     *
     * @param durationDto TrdPlanInterestDurationDto
     * @return 是否成功
     */
    public String doInsert(TrdPlanInterestDurationDto durationDto) {
        if(durationDto.getBaseTotalTradeInfoDto().isBack()){
            baseTradeProductDealCommServiceImpl.delete(durationDto.getBaseTotalTradeInfoDto().getTrdFinProductDealTotal().getWorkflowId());
        }else{
            baseTradeProductDealCommService.deleteWflData(durationDto.getBaseTotalTradeInfoDto().getTrdFinProductDealTotal().getWorkflowId());
        }
        return investPeriodCommonService.doInsert(durationDto);
    }

    public String doRevoke(TrdPlanInterestDurationDto durationDto) {
        Map<String, Object> params = new HashMap<>();
        params.put("bizCode", durationDto.getBusiCode());
        params.put("workflowId", durationDto.getWorkflowId());
        params.put("workflowStatus", DictCodeList.数据状态_删除中.getValue());
        params.put("user", durationDto.getCreateUser());
        params.put("dept", com.joyintech.framework.util.StringUtil.isEmpty(durationDto.getCreateDept()) ? "dept" : durationDto.getCreateDept());
        params.put("time", durationDto.getCreateTime());
        params.put("revokeReason", durationDto.getRevokeReason());
        investPeriodCommonService.insertWflTable(params);
        return Boolean.TRUE.toString();
    }
    /**
     * 取区间的未付还本不带息金额
     *
     * @param params
     * @return 返回实际的未付还本不带息金额
     */
    public BigDecimal getRepaymentUnpayInterest(Map<String, Object> params) {
        return trdPlanInterestDurationMapper.getRepaymentUnpayInterest(params);
    }
    /**
     * 取交易的BRANCH
     *
     * @param params
     * @return 交易的BRANCH
     */
    public String getBranch(Map<String, Object> params) {
        return trdPlanInterestDurationMapper.getBranch(params);
    }

}
