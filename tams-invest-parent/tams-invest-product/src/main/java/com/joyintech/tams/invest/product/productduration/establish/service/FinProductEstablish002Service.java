/*
 * 文件名：FinProductEstablish002Service.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：ejupiter
 * 修改时间：2018年12月21日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productduration.establish.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.grid.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseDictCode;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.base.intf.DataDict;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.dao.FinProductAddMapper;
import com.joyintech.tams.common.base.dao.FinTradeProductMapper;
import com.joyintech.tams.common.base.dao.PaBranchSaleDetailMapper;
import com.joyintech.tams.common.base.dao.PaTrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.PaTrdProductDealMapper;
import com.joyintech.tams.common.base.dao.PtlTradeMapper;
import com.joyintech.tams.common.base.dao.StlPayOrdersMapper;
import com.joyintech.tams.common.base.dao.TrdAsynHandleStatusMapper;
import com.joyintech.tams.common.base.dao.TrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealAddMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.dao.WflPaTrdFinDealTotalMapper;
import com.joyintech.tams.common.base.dao.WflPaTrdProductDealMapper;
import com.joyintech.tams.common.base.dao.WflStlPayOrdersMapper;
import com.joyintech.tams.common.base.dao.WflTrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.WflTrdProductDealAddMapper;
import com.joyintech.tams.common.base.dao.WflTrdProductDealMapper;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.PaBranchSaleDetail;
import com.joyintech.tams.common.base.entity.PaTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdAsynHandleStatus;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.WflPaTrdFinDealTotal;
import com.joyintech.tams.common.base.entity.WflPaTrdProductDeal;
import com.joyintech.tams.common.base.entity.WflStlPayOrders;
import com.joyintech.tams.common.base.entity.WflTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.tams.common.base.entity.WflTrdProductDealAdd;
import com.joyintech.tams.component.business.common.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFeeInfoDto;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.BussTrdPositionDto;
import com.joyintech.tams.invest.common.trade.service.TradeProductDealCommService;
import com.joyintech.tams.invest.common.valuationinterf.ValuationInterfService;
import com.joyintech.tams.invest.common.valuationinterf.dto.ProdOtherInfoInitInputDto;
import com.joyintech.tams.invest.product.common.service.ProductCommonService;
import com.joyintech.tams.invest.product.productduration.establish.dto.FinProductEstablish002Dto;
import com.joyintech.tams.invest.product.productduration.establish.dto.FinProductEstablishBranchDto;
import com.joyintech.tams.invest.product.productduration.establish.dto.FinProductEstablishDealTotal;
import com.joyintech.tams.invest.product.productduration.establish.dto.FinProductEstablishLayerDto;
import com.joyintech.tams.invest.product.productelement.build.dto.FinProductBuild002Dto;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 产品成立确认
 *
 * @author zhangliang
 * @version 1.0 2018年12月21日
 * @see FinProductEstablish002Service
 * @since 1.0
 */
@Service
public class FinProductEstablish002Service extends BaseService<FinProductEstablish002Dto> implements BaseProcessService<FinProductEstablish002Dto> {
    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

    /**
     * {@linkplain}
     */

    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    /**
     * {@linkplain}
     */
    @Autowired
    private TradeProductDealCommService baseTradeProductDealCommService;

    /**
     * {@linkplain}
     */
    @Autowired
    private ProductCommonService productCommonService;

    // 一体化对应etliu start
    /**
     * ValuationInterfService
     */
    @Autowired
    private ValuationInterfService valuationInterfService;
    // 一体化对应etliu end

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductAddMapper finProductAddMapper;
    @Autowired
    private FinTradeProductMapper finTradeProductMapper;
    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;
    @Autowired
    private PaTrdFinProductDealTotalMapper paTrdFinProductDealTotalMapper;
    @Autowired
    private PtlTradeMapper ptlTradeMapper;
    @Autowired
    private StlPayOrdersMapper stlPayOrdersMapper;
    @Autowired
    private PaBranchSaleDetailMapper paBranchSaleDetailMapper;
    @Autowired
    private WflStlPayOrdersMapper wflStlPayOrdersMapper;
    @Autowired
    private WflTrdFinProductDealTotalMapper wflTrdFinProductDealTotalMapper;
    @Autowired
    private WflPaTrdFinDealTotalMapper wflPaTrdFinDealTotalMapper;

    @Autowired
    private WflTrdProductDealMapper wflTrdProductDealMapper;

    @Autowired
    private WflTrdProductDealAddMapper wflTrdProductDealAddMapper;

    @Autowired
    private WflPaTrdProductDealMapper wflPaTrdProductDealMapper;
    @Autowired
    private TrdProductDealMapper trdProductDealMapper;
    @Autowired
    private TrdProductDealAddMapper trdProductDealAddMapper;
    @Autowired
    private PaTrdProductDealMapper paTrdProductDealMapper;
    @Autowired
    private TrdAsynHandleStatusMapper trdAsynHandleStatusMapper;
    /**
     * {@inheritDoc}
     */
    @Override
    public void doInsert(FinProductEstablish002Dto entity, OperationType type) {
        if (!type.equals(OperationType.REVOKE) && !type.equals(OperationType.DELETE)) {
            this.insertBussFlowData(entity);
        }
        else {
            this.doInsertRevoke(entity);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doUpdate(FinProductEstablish002Dto entity) {
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
        baseTradeProductDealCommService.delete(workflowId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinProductEstablish002Dto entity, OperationType operationType) {
        // 数据处理
        this.prepareData(entity);

        if (operationType == OperationType.NEW) {

            /** 手动插入PK,解决多次申购后撤销的交易不是选中交易的问题 **/
            ProcessMark newEntity;
            try {
                if (StringUtil.isNotEmpty(entity.getWorkflowId())) {
                    newEntity = WorkflowContext.getProcessMarkService().getBusinessInfo(entity.getWorkflowId());
                }
                else {
                    newEntity = WorkFlowUtil.getBusinessObjectFromRequest(FinProductEstablish002Dto.class, true);
                }
                newEntity.setWorkflowId(entity.getWorkflowId());
                newEntity.setStatus(entity.getStatus());
                newEntity.setBusinessData(JsonUtils.object2Json(entity));
                newEntity.setUpdateUser(newEntity.getCreateUser());

                newEntity.setBusinessDataPk3(entity.getTradeId());
                newEntity.setBusinessDataPk1(entity.getFinprodId());

                // 操作留痕表
                WorkflowContext.getProcessMarkService().insertIntoProcessMark(newEntity);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            this.doCompleteInsert(entity);
        }
        else if (operationType == OperationType.REVOKE || operationType == OperationType.DELETE) {
            this.doCompleteRevoke(entity);
        }
        // 删除工作流表
        baseTradeProductDealCommService.deleteWflData(entity.getWorkflowId());
    }

    /**
     * 根据金融产品代码查询费用列表
     *
     * @param finprodId 金融产品代码
     * @return 费用列表
     * @see
     * @since 1.0
     */
    public List<BussFinProductFeeInfoDto> queryFeeInfoByFinprodId(String finprodId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("branch", Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
        List<BussFinProductFeeInfoDto> feeInfoList = restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductEstablish/queryFeeInfoByFinprodId", map,
            BussFinProductFeeInfoDto.class);
        if (!StringUtil.isEmptyList(feeInfoList)) {
            for (BussFinProductFeeInfoDto feeInfo : feeInfoList) {
                // 一体化对应etliu start
                // feeInfo.setFeeType(
                // SysDataUtil.getNameFromDataDict(DictCode.费用类型, feeInfo.getFeeType()));
                feeInfo.setFeeType(SysDataUtil.getNameFromDataDict(DictCode.费用品种类型, feeInfo.getFeeType()));
                // 一体化对应etliu end
                feeInfo.setBasis(SysDataUtil.getNameFromDataDict(DictCode.计息基础, feeInfo.getBasis()));
            }
        }
        return feeInfoList;
    }

    /**
     * 根据金融产品代码查询分层金额明细
     *
     * @param map 参数
     * @return 分层金额明细
     * @see
     * @since 1.0
     */
    public List<FinProductEstablishLayerDto> queryLayAmtDetailByFinprodId(Map<String, Object> map) {
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductEstablish/queryLayAmtDetailByFinprodId", map,
            FinProductEstablishLayerDto.class);
    }

    /**
     * 根据金融产品代码查询分层金额明细
     *
     * @param bussId 业务编号
     * @return 分层金额明细
     * @see
     * @since 1.0
     */
    public List<FinProductEstablishLayerDto> queryLayAmtDetailByBussId(String bussId) {
        return restTemplate.getForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductEstablish/queryLayAmtDetailByBussId?bussId=" + bussId,
            FinProductEstablishLayerDto.class);
    }

    /**
     * 根据金融产品代码查询募集金额明细
     *
     * @param finprodId 金融产品代码
     * @param vdate 成立日
     * @return 募集金额明细
     * @see
     * @since 1.0
     */
    public FinProductEstablishDealTotal queryRaiseAmtInfoByFinprodId(String finprodId, String vdate) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("vdate", vdate);
        FinProductEstablishDealTotal finProductEstablishDealTotal = restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductEstablish/queryRaiseAmtInfoByFinprodId", map,
            FinProductEstablishDealTotal.class);
        if (finProductEstablishDealTotal == null) {
            finProductEstablishDealTotal = new FinProductEstablishDealTotal();
            finProductEstablishDealTotal.setLastRed(BigDecimal.ZERO);
            finProductEstablishDealTotal.setLastAmtSub(BigDecimal.ZERO);
        }
        return finProductEstablishDealTotal;
    }

    /**
     * 根据金融产品代码查询分层金额明细
     *
     * @param map 参数
     * @return 分层金额明细
     * @see
     * @since 1.0
     */
    public List<FinProductEstablishBranchDto> queryBranchRaiseInfoByFinprodId(Map<String, Object> map) {
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductEstablish/queryBranchRaiseInfoByFinprodId", map,
            FinProductEstablishBranchDto.class);
    }

    /**
     * 根据金融产品代码查询分层金额明细
     *
     * @param bussId 交易编号
     * @return 分层金额明细
     * @see
     * @since 1.0
     */
    public List<FinProductEstablishBranchDto> queryBranchRaiseInfoByBussId(String bussId) {
        return restTemplate.getForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductEstablish/queryBranchRaiseInfoByBussId?bussId=" + bussId,
            FinProductEstablishBranchDto.class);
    }

    /**
     * 查询分行信息
     *
     * @param map 参数
     * @return 分行信息
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryBranchInfoIdList(Map<String, Object> map) {
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductEstablish/queryBranchInfoIdList",
            map, SelectOptionDto.class);
    }

    /**
     * 查询分行信息
     *
     * @param map 参数
     * @return 分行信息
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryBranchInfoNameList(Map<String, Object> map) {
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductEstablish/queryBranchInfoNameList",
            map, SelectOptionDto.class);
    }

    /**
     * 金融产品交易共通业务逻辑处理
     *
     * @param entity 产品成立确认
     * @param totleTradeId 汇总交易编号
     * @param finprodId 金融产品代码
     * @param prinamt 交易金额
     * @return 金融产品交易信息实体
     * @see
     * @since 1.0
     */
    private BaseTradeProductDealDto insertBaseTradeProductDealComm(FinProductEstablish002Dto entity, String totleTradeId,
                                                                   String finprodId, BigDecimal prinamt) {
        // 交易金额为空时,默认为0
        prinamt = prinamt == null ? BigDecimal.ZERO : prinamt;
        // 金融产品交易Entity
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        if (baseGetBusinessId != null) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_收益型产品认申购管理);
            trdProductDeal.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
        }
        else {
            trdProductDeal.setTradeId(IDGenerator.generate());
        }
        trdProductDeal.setBusiType(DictCodeList.共通业务类型_收益型产品认申购管理.getValue());
        trdProductDeal.setFinprodId(finprodId);
        trdProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
        trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        if (this.isIsLay(entity)) {
            trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue());
        }
        // if (this.isTrdFinProduct(entity.getDealMode())) {
        // trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财子产品.getValue());
        // }
        trdProductDeal.setBranch(entity.getBranch());
        trdProductDeal.setCcy(entity.getCcy());
        trdProductDeal.setPs(entity.getPs());
        trdProductDeal.setTradeDate(entity.getVdate());
        trdProductDeal.setVdate(entity.getVdate());
        trdProductDeal.setSettleDate(entity.getVdate());
        trdProductDeal.setMdate(entity.getMdate());
        trdProductDeal.setShareAmt(prinamt);
        trdProductDeal.setPrinAmt(prinamt);
        trdProductDeal.setFpriceAmt(prinamt);
        trdProductDeal.setTradeAmt(prinamt);
        trdProductDeal.setTotleTradeId(totleTradeId);
        trdProductDeal.setWorkflowId(entity.getWorkflowId());
        // 一体化对应etliu start
        trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
        // 一体化对应etliu end

        // add by yejiejie 2019-2-21 start
        /*
         * String remark = null; if(StringUtil.equals(entity.getPs(), DictCodeList.交易方向_产品认购.getValue())){ //{产品代码}{产品名称}起息 {产品简称}产品募集本金，期初客户数{客户数} remark =
         * entity.getProdId() + entity.getFinprodName()+"起息" +entity.getFinprodAbbr() +"产品募集本金:"+entity.getCurrentSub()
         * +",期初客户数："+entity.getCustomersBeginNum()+"。"; }else if(StringUtil.equals(entity.getPs(), DictCodeList.交易方向_产品申购.getValue())){
         * //{产品代码}{产品名称}申购赎回{产品简称}申购{申购金额}元。 remark = entity.getProdId() + entity.getFinprodName()+"申购赎回" +entity.getFinprodAbbr()
         * +"申购:"+entity.getCurrentSub()+"。"; }
         */
        trdProductDeal.setRemark(entity.getRemark());
        // add by yejiejie 2019-2-21 start

        // 金融产品交易附属表Entity
        TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
        trdProductDealAdd.setTradeId(trdProductDeal.getTradeId());
        trdProductDealAdd.setPortfolioId(entity.getPtlId());
        trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
        trdProductDealAdd.setWorkflowId(entity.getWorkflowId());
        BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
        baseTradeProductDealDto.setTrdProductDeal(trdProductDeal); // 金融产品交易
        baseTradeProductDealDto.setTrdProductDealAdd(trdProductDealAdd); // 金融产品交易附属表

        return baseTradeProductDealDto;
    }

    /**
     * 金融产品标的共通处理
     *
     * @param entity 产品确认信息
     * @return 金融产品标的共通
     * @see
     * @since 1.0
     */
    private BaseFinProductBaseInfoDto insertBaseFinProductInfoComm(FinProductEstablish002Dto entity) {
        List<FinCashInfo> finCashInfoList = new ArrayList<FinCashInfo>();
        List<FinCashCalcRule> finCashCalcRuleList = new ArrayList<FinCashCalcRule>();
        // 子产品报价：
        // 现金流信息Entity
        FinCashInfo finCashInfo = new FinCashInfo();
        finCashInfo.setCashId(CommonUtil.generateCashFlowCode(entity.getTradeId(), DictCodeListExt1.现金流业务类型_金融产品.getValue(),
            entity.getBranch(), DictCodeListExt1.现金流二级类型_利息.getValue()));
        finCashInfo.setCashType1(DictCodeListExt1.现金流一级类型_利息.getValue());
        finCashInfo.setCashType2(DictCodeListExt1.现金流二级类型_利息.getValue());
        finCashInfo.setCcy(entity.getCcy());
        finCashInfo.setFinprodId(entity.getFinprodId());
        finCashInfo.setFinprodType(DictCodeListExt1.现金流业务类型_金融产品.getValue());
        finCashInfo.setBranch(entity.getBranch());
        finCashInfo.setIsCalc(DictCodeList.是否_是.getValue());
        finCashInfoList.add(finCashInfo);
        // 现金计算规则Entity
        FinCashCalcRule finCashCalcRule = new FinCashCalcRule();
        finCashCalcRule.setCashId(finCashInfo.getCashId());
        finCashCalcRule.setEffDate(entity.getVdate());
        // 一体化对应etliu
        // finCashCalcRule.setRelaeseDate(entity.getVdate());
        finCashCalcRule.setBasis(entity.getBasis());
        finCashCalcRule.setYield(entity.getYield());
        finCashCalcRule.setIsInitial(DictCodeList.是否_否.getValue());
        finCashCalcRule.setFinprodId(entity.getFinprodId());
        // finCashCalcRule.setBranch(
        // Long.parseLong(DateUtil.format(entity.getVdate(), DateUtil.DEFAULT_OTHER_FORMAT)));
        finCashCalcRule.setBranch((long)0);
        // finCashCalcRule.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财子产品.getValue());
        finCashCalcRule.setFinprodType2(DictCodeListExt1.金融产品类型_理财产品.getValue());
        finCashCalcRuleList.add(finCashCalcRule);
        // 子产品费率报价： 按费用类型循环处理
        if (!StringUtil.isEmptyList(entity.getBussFinProductFeeInfoDtoList())) {
            for (BussFinProductFeeInfoDto bussFinProductFeeInfoDto : entity.getBussFinProductFeeInfoDtoList()) {
                String cashId_fee = "";
                String cashType2 = "";
                // 托管费
                if (StringUtil.equals(DictCodeList.费用品种类型_托管费.getCodeName(), bussFinProductFeeInfoDto.getFeeType())) {
                    cashId_fee = CommonUtil.generateCashFlowCode(entity.getFinprodId(), DictCodeListExt1.现金流业务类型_金融产品.getValue(),
                        Long.parseLong(DateUtil.format(entity.getVdate(), DateUtil.DEFAULT_OTHER_FORMAT)),
                        DictCodeListExt1.现金流二级类型_托管费.getValue());
                    cashType2 = DictCodeListExt1.现金流二级类型_托管费.getValue();
                }
                // 固定管理费
                if (StringUtil.equals(DictCodeList.费用品种类型_固定管理费.getCodeName(), bussFinProductFeeInfoDto.getFeeType())) {
                    cashId_fee = CommonUtil.generateCashFlowCode(entity.getFinprodId(), DictCodeListExt1.现金流业务类型_金融产品.getValue(),
                        Long.parseLong(DateUtil.format(entity.getVdate(), DateUtil.DEFAULT_OTHER_FORMAT)),
                        DictCodeListExt1.现金流二级类型_固定管理费.getValue());
                    cashType2 = DictCodeListExt1.现金流二级类型_固定管理费.getValue();
                }
                // 销售费
                if (StringUtil.equals(DictCodeList.费用品种类型_销售手续费.getCodeName(), bussFinProductFeeInfoDto.getFeeType())) {
                    cashId_fee = CommonUtil.generateCashFlowCode(entity.getFinprodId(), DictCodeListExt1.现金流业务类型_金融产品.getValue(),
                        Long.parseLong(DateUtil.format(entity.getVdate(), DateUtil.DEFAULT_OTHER_FORMAT)),
                        DictCodeListExt1.现金流二级类型_销售费.getValue());
                    cashType2 = DictCodeListExt1.现金流二级类型_销售费.getValue();
                }
                // 一体化对应etliu start
                if (StringUtil.equals(DictCodeList.费用品种类型_外包服务费.getCodeName(), bussFinProductFeeInfoDto.getFeeType())) {
                    cashId_fee = CommonUtil.generateCashFlowCode(entity.getFinprodId(), DictCodeListExt1.现金流业务类型_金融产品.getValue(),
                        Long.parseLong(DateUtil.format(entity.getVdate(), DateUtil.DEFAULT_OTHER_FORMAT)),
                        DictCodeListExt1.现金流二级类型_外包服务费.getValue());
                    cashType2 = DictCodeListExt1.现金流二级类型_外包服务费.getValue();
                }
                // 一体化对应etliu end

                // 现金流信息
                FinCashInfo finCashInfo_fee = new FinCashInfo();
                finCashInfo_fee.setCashId(cashId_fee);
                finCashInfo_fee.setCashType1(DictCodeListExt1.现金流一级类型_费用.getValue());
                finCashInfo_fee.setCashType2(cashType2);
                finCashInfo_fee.setCcy(entity.getCcy());
                finCashInfo_fee.setFinprodId(entity.getFinprodId());
                finCashInfo_fee.setFinprodType(DictCodeListExt1.现金流业务类型_金融产品.getValue());
                // finCashInfo_fee.setBranch(Long.parseLong(
                // DateUtil.format(entity.getVdate(), DateUtil.DEFAULT_OTHER_FORMAT)));
                finCashCalcRule.setBranch((long)0);
                finCashInfo_fee.setIsCalc(DictCodeList.是否_是.getValue());
                finCashInfo_fee.setFeeId(bussFinProductFeeInfoDto.getFeeId());
                finCashInfoList.add(finCashInfo_fee);
                // 现金计算规则Entity
                FinCashCalcRule finCashCalcRule_fee = new FinCashCalcRule();
                finCashCalcRule_fee.setCashId(finCashInfo_fee.getCashId());
                finCashCalcRule_fee.setEffDate(entity.getVdate());
                // 一体化对应etliu
                // finCashCalcRule_fee.setRelaeseDate(entity.getVdate());
                finCashCalcRule_fee.setBasis(bussFinProductFeeInfoDto.getBasis());
                finCashCalcRule_fee.setYield(bussFinProductFeeInfoDto.getFeeRate());
                finCashCalcRule_fee.setIsInitial(DictCodeList.是否_是.getValue());
                finCashCalcRule_fee.setFinprodId(entity.getFinprodId());
                // finCashCalcRule_fee.setBranch(Long.parseLong(
                // DateUtil.format(entity.getVdate(), DateUtil.DEFAULT_OTHER_FORMAT)));
                finCashCalcRule.setBranch((long)0);
                // finCashCalcRule_fee.setFinprodType2(
                // DictCodeListExt1.金融产品类型_投管用_理财子产品.getValue());
                finCashCalcRule_fee.setFinprodType2(DictCodeListExt1.金融产品类型_理财产品.getValue());
                finCashCalcRuleList.add(finCashCalcRule_fee);
            }
        }

        BaseFinProductBaseInfoDto baseFinProductBaseInfoDto = new BaseFinProductBaseInfoDto();
        baseFinProductBaseInfoDto.setFinCashInfoList(finCashInfoList);
        baseFinProductBaseInfoDto.setFinCashCalcRuleList(finCashCalcRuleList);

        return baseFinProductBaseInfoDto;
    }

    /**
     * 插入交易类金融产品
     *
     * @param entity 产品确认信息
     * @param finprodId 金融产品代码
     * @param prinamt 交易金额
     * @see
     * @since 1.0
     */
    private void insertFinTradeProduct(FinProductEstablish002Dto entity, String finprodId, BigDecimal prinamt) {
        if (this.isTrdFinProduct(entity.getDealMode())) {
            FinTradeProduct finTradeProduct = new FinTradeProduct();
            finTradeProduct.setFinprodId(finprodId);
            finTradeProduct.setBranch(entity.getBranch());
            finTradeProduct.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财子产品.getValue());
            finTradeProduct.setCcy(entity.getCcy());
            finTradeProduct.setVdate(entity.getVdate());
            finTradeProduct.setMdate(entity.getMdate());
            finTradeProduct.setmPrinAmt(prinamt);
            // lijun add 周期型产品 交易类金融产品表根据设计 添加期限、利率类型、固定利率、计息基础、交易场所 20190329 start
            finTradeProduct.setTerm(DateUtil.getBetweenDayNumber(entity.getVdate(), entity.getMdate()));// 期限
            FinProduct finproduct = productCommonService.queryFinProductByFinprodId(finprodId);
            finTradeProduct.setBasis(entity.getBasis());// 计息基础
            finTradeProduct.setIntRate(entity.getYield());// 固定利率
            finTradeProduct.setTradeMarket(finproduct.getTradeMarket());// 交易场所
            finTradeProduct.setProfitType(finproduct.getProfitType());// 利率类型
            // lijun add 周期型产品 交易类金融产品表根据设计 添加期限、利率类型、固定利率、计息基础、交易场所 20190329 end

            if (this.isIsCycle(entity)) {
                finTradeProductMapper.insertSelective(finTradeProduct);
            }
            // 一体化对应etliu start
            // 产品成立 周期型产品申购，调用估值接口
            ProdOtherInfoInitInputDto inputDto = new ProdOtherInfoInitInputDto();
            inputDto.setPageId("FinProductEstablish002");
            inputDto.setFinTradeProduct(finTradeProduct);

            valuationInterfService.prodOtherInfoInit(inputDto);
            // 一体化对应etliu end
        }
    }

    /**
     * 插入金融产品交易扩展表Entity
     *
     * @param entity 产品确认信息
     * @param tradeId 交易编号
     * @param yield 收益率
     * @return 交易扩展表Entity
     * @see
     * @since 1.0
     */
    private PaTrdProductDeal genPaTrdProductDealInsert(FinProductEstablish002Dto entity, String tradeId, BigDecimal yield) {
        PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
        paTrdProductDeal.setTradeId(tradeId);
        paTrdProductDeal.setTheoryRate(yield);
        paTrdProductDeal.setBasis(entity.getBasis());
        // paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_产品成立.getValue());
        // 如果是申购则业务类型插成申购 yjm start 20190727
        if (StringUtil.equals(entity.getPs(), DictCodeList.交易方向_产品申购.getValue())) {
            paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_产品申购.getValue());
        }
        else {
            paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_产品成立.getValue());
        }
        // 如果是申购则业务类型插成申购 yjm end 20190727

        // add by yejiejie 2019-2-21 start
        paTrdProductDeal.setCustomersBeginNum(entity.getCustomersBeginNum());
        paTrdProductDeal.setCustomersAddNum(entity.getCustomersAddNum());
        // add by yejiejie 2019-2-21 end
        // lijun add 指令业务类型2019-03-16 start
        if (StringUtil.equals(DictCodeList.交易方向_产品申购.getValue(), entity.getPs())) {
            paTrdProductDeal.setOrderBussType(DictCodeListExt1.指令业务类型_产品申购.getValue());
        }
        else {
            paTrdProductDeal.setOrderBussType(DictCodeListExt1.指令业务类型_产品认购.getValue());
        }
        // lijun add 指令业务类型 2019-03-16 end
        paTrdProductDeal.setWorkflowId(entity.getWorkflowId());
        return paTrdProductDeal;
    }

    /**
     * 组合交易表Entity
     *
     * @param entity 产品确认信息
     * @return 组合交易表Entity
     * @see
     * @since 1.0
     */
    private PtlTrade genPtlTradeInsert(FinProductEstablish002Dto entity) {
        PtlTrade ptlTrade = new PtlTrade();
        ptlTrade.setPortfolioId(entity.getPtlId());
        ptlTrade.setBusiNo(entity.getTradeId());
        ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        ptlTrade.setBusiId(entity.getFinprodId());
        ptlTrade.setBusiType(DictCodeList.共通业务类型_收益型产品认申购管理.getValue());
        ptlTrade.setSettleDate(entity.getVdate());
        ptlTrade.setPortfolioId(entity.getPtlId());
        ptlTrade.setWorkflowId(entity.getWorkflowId());

        return ptlTrade;
    }

    /**
     * 是否分层并且有分层列表
     *
     * @param entity 确认信息
     * @return 是否分层并且有分层列表
     * @see
     * @since 1.0
     */
    private boolean isIsLay(FinProductEstablish002Dto entity) {
        boolean flag = false;
        if (StringUtil.equals(entity.getDealMode(), DictCodeList.处理模式_分档.getValue())
            || StringUtil.equals(entity.getDealMode(), DictCodeList.处理模式_分档周期型.getValue())) {
            if (!StringUtil.isEmptyList(entity.getFinProductEstablishLayerDtoList())) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 根据处理模式判断是否生成交易类金融产品
     *
     * @param dealMode 处理模式
     * @return 是否生成交易类金融产品
     * @see
     * @since 1.0
     */
    private boolean isTrdFinProduct(String dealMode) {
        boolean flag = false;
        if (StringUtil.equals(dealMode, DictCodeList.处理模式_普通.getValue())
            || StringUtil.equals(dealMode, DictCodeList.处理模式_分档.getValue())
            || StringUtil.equals(dealMode, DictCodeList.处理模式_周期型.getValue())
            || StringUtil.equals(dealMode, DictCodeList.处理模式_分档周期型.getValue())) {
            flag = true;
        }
        return flag;
    }

    /**
     * 从数据字典中获取参数值
     *
     * @param dictCode 数据字典
     * @param itemValue 数据代码
     * @return 参数值
     * @see
     * @since 1.0
     */
    private String getCodeFromDataDict(final BaseDictCode dictCode, String itemValue) {
        List<? extends DataDictItem> dataDictItemList = getDataDictItem(dictCode.getValue());
        if(dataDictItemList!= null) {
        return dataDictItemList.stream().filter(item -> {
            if (StringUtil.equals(item.getItemValue(), itemValue)) {
                return true;
            }
            return false;
        }).map(DataDictItem::getItemCode).findFirst().orElse(null);
        } 
        return null;
    }

    /**
     * 获取数据字典明细列表
     *
     * @param dictCode 数据字典代码
     * @return 数据字典明细列表
     */
    private List<? extends DataDictItem> getDataDictItem(String dictCode) {
        DataDict dict = CacheUtil.getDictCode(dictCode);
        if (dict != null) {
            return dict.getItems();
        }
        return null;
    }

    /**
     * 数据处理
     *
     * @param entity 确认信息
     * @see
     * @since 1.0
     */
    private void prepareData(FinProductEstablish002Dto entity) {
        // 分支序号
        // entity.setBranch(
        // Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
        entity.setBranch((long)0);
        if (StringUtil.equals(entity.getOperationType(), DictCodeList.开放类型_开放式.getValue())) {
            String branch = DateUtil.format(entity.getVdate(), DateUtil.DEFAULT_OTHER_FORMAT);
            Integer num = this.selectIsBranch(entity.getFinprodId(), null) + 1;
            // entity.setBranch(Long.parseLong(branch+""+num.toString()));
            // modify by tuqirong 2019-03-21 start 周期型产品的分支序号不为0
            // entity.setBranch((long)0);
            if (this.isIsCycle(entity)) {
                entity.setBranch(Long.parseLong(branch + "" + num.toString()));
            }
            else {
                entity.setBranch((long)0);
            }
            // modify by tuqirong 2019-03-21 end
        }
        // 计息基础
        entity.setBasis(getCodeFromDataDict(DictCode.计息基础, entity.getBasis()));
        // 收益率
        entity.setYield(ArithmeticUtil.divide(entity.getYield(), 100, 4));

        entity.setBussTrdTransferOrder(bussTrdTransferOrderService.save(entity.getBussTrdTransferOrder()));
    }

    /**
     * 生成金融产品交易汇总
     *
     * @param entity 产品成立确认Dto
     * @return 金融产品交易汇总
     * @see
     * @since 1.0
     */
    private TrdFinProductDealTotal genTrdFinProductDealTotal(FinProductEstablish002Dto entity) {
        // 金融产品交易汇总Entity
        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        trdFinProductDealTotal.setTotleTradeId(entity.getTradeId());
        trdFinProductDealTotal.setBusiType(DictCodeList.共通业务类型_收益型产品认申购管理.getValue());
        trdFinProductDealTotal.setFinprodId(entity.getFinprodId());
        trdFinProductDealTotal.setFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
        trdFinProductDealTotal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        trdFinProductDealTotal.setBranch(entity.getBranch());
        trdFinProductDealTotal.setCcy(entity.getCcy());
        trdFinProductDealTotal.setPs(entity.getPs());
        trdFinProductDealTotal.setTradeDate(entity.getVdate());
        trdFinProductDealTotal.setVdate(entity.getVdate());
        trdFinProductDealTotal.setMdate(entity.getMdate());
        trdFinProductDealTotal.setSettleDate(entity.getVdate());
        trdFinProductDealTotal.setShareAmt(entity.getCurrentSub());
        trdFinProductDealTotal.setPrinAmt(entity.getCurrentSub());
        trdFinProductDealTotal.setTradeAmt(entity.getCurrentSub());
        trdFinProductDealTotal.setFpriceAmt(entity.getAccountingAmt());
        trdFinProductDealTotal.setLastAmt(entity.getLastAmt());
        trdFinProductDealTotal.setLastRed(entity.getLastRed());
        trdFinProductDealTotal.setLastAmtSub(entity.getLastAmtSub());
        trdFinProductDealTotal.setCurrentSub(entity.getCurrentSub());
        trdFinProductDealTotal.setAccountingAmt(entity.getAccountingAmt());
        trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
        trdFinProductDealTotal.setPortfolioId(entity.getPtlId());
        trdFinProductDealTotal.setUpdateUser(entity.getUpdateUser());
        trdFinProductDealTotal.setCreateUser(entity.getCreateUser());
        trdFinProductDealTotal.setCreateDept(entity.getCreateDept());
        trdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
        // add by gcl 2019/4/8 start
        trdFinProductDealTotal.setRemark(entity.getRemark());
        // add by gcl 2019/4/8 end
        return trdFinProductDealTotal;
    }

    /**
     * 生成金融产品交易汇总扩展
     *
     * @param entity 产品成立确认Dto
     * @return 金融产品交易汇总扩展
     * @see
     * @since 1.0
     */
    private PaTrdFinProductDealTotal genPaTrdFinProductDealTotal(FinProductEstablish002Dto entity) {

        PaTrdFinProductDealTotal paTrdFinProductDealTotal = new PaTrdFinProductDealTotal();
        paTrdFinProductDealTotal.setTotleTradeId(entity.getTradeId());
        // 如果是申购则业务类型插成申购 yjm start 20190727
        if (StringUtil.equals(entity.getPs(), DictCodeList.交易方向_产品申购.getValue())) {
            paTrdFinProductDealTotal.setPaBussType(DictCodeListExt1.平安业务类型_产品申购.getValue());
        }
        else {
            paTrdFinProductDealTotal.setPaBussType(DictCodeListExt1.平安业务类型_产品成立.getValue());
        }
        // 如果是申购则业务类型插成申购 yjm end 20190727
        paTrdFinProductDealTotal.setUpdateUser(entity.getUpdateUser());
        paTrdFinProductDealTotal.setCreateUser(entity.getCreateUser());
        paTrdFinProductDealTotal.setCreateDept(entity.getCreateDept());
        paTrdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());

        return paTrdFinProductDealTotal;
    }

    /**
     * 撤销doComplete
     *
     * @param entity 参数
     * @see
     * @since 1.0
     */
    private void doCompleteRevoke(FinProductEstablish002Dto entity) {
        // 根据汇总交易编号查询金融产品交易汇总
        TrdFinProductDealTotal trdFinProductDealTotal = productCommonService.genTrdFinProductDealTotalRevoke(entity.getTradeId());
        if (trdFinProductDealTotal != null) {
            trdFinProductDealTotalMapper.insert(trdFinProductDealTotal);
        }

        // 根据汇总交易编号查询金融产品交易汇总扩展
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = productCommonService.genPaTrdFinProductDealTotalRevoke(
            entity.getTradeId());
        if (paTrdFinProductDealTotal != null) {
            paTrdFinProductDealTotalMapper.insert(paTrdFinProductDealTotal);
        }

        // 根据汇总交易编号查询划款指令
        List<StlPayOrdersDto> stlPayOrdersList = productCommonService.genStlPayOrdersRevoke(entity.getTradeId());
        if (!StringUtil.isEmptyList(stlPayOrdersList)) {
            stlPayOrdersList.forEach(stlPayOrdersDto -> stlPayOrdersMapper.insert(stlPayOrdersDto));
        }

        // 根据汇总交易编号查询交易信息
        List<TrdProductDeal> trdProductDealList = productCommonService.genTrdProductDealRevoke(entity.getTradeId(),
            entity.getRevokeReason());
        if (!StringUtil.isEmptyList(trdProductDealList)) {
            trdProductDealList.forEach(stlPayOrdersDto -> trdProductDealMapper.insert(stlPayOrdersDto));
        }

        // 根据汇总交易编号查询交易附属信息
        List<TrdProductDealAdd> trdProductDealAddList = productCommonService.genTrdProductDealAddRevoke(entity.getTradeId());
        if (!StringUtil.isEmptyList(trdProductDealAddList)) {
            trdProductDealAddList.forEach(stlPayOrdersDto -> trdProductDealAddMapper.insert(stlPayOrdersDto));
        }

        // 根据汇总交易编号查询金融产品交易扩展
        List<PaTrdProductDeal> paTrdProductDealList = productCommonService.genPaTrdProductDealRevoke(entity.getTradeId());
        if (!StringUtil.isEmptyList(paTrdProductDealList)) {
            paTrdProductDealList.forEach(stlPayOrdersDto -> paTrdProductDealMapper.insert(stlPayOrdersDto));
        }

        // 根据汇总交易编号查询组合交易信息
        List<PtlTrade> ptlTradeList = productCommonService.genPtlTradeRevoke(entity.getTradeId());
        if (!StringUtil.isEmptyList(ptlTradeList)) {
            ptlTradeList.forEach(stlPayOrdersDto -> ptlTradeMapper.insert(stlPayOrdersDto));
        }

        // 根据汇总交易编号查询理财分行销售明细
        List<PaBranchSaleDetail> paBranchSaleDetailList = productCommonService.genPaBranchSaleDetailRevoke(entity.getTradeId());
        if (!StringUtil.isEmptyList(paBranchSaleDetailList)) {
            paBranchSaleDetailList.forEach(stlPayOrdersDto -> paBranchSaleDetailMapper.insert(stlPayOrdersDto));
        }

        // 交易类金融产品（金融产品代码+成立日分支序号）
        if (!StringUtil.isEmptyList(trdProductDealList)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("branch", DateUtil.format(trdFinProductDealTotal.getSettleDate(), DateUtil.DEFAULT_OTHER_FORMAT));
            for (TrdProductDeal trdProductDeal : trdProductDealList) {
                map.put("finprodId", trdProductDeal.getFinprodId());
                restTemplate.postForObject(
                    ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductEstablish/deleteFinTradeProductByFinprodId", map,
                    Integer.class);
                // 现金流信息、现金计算规则（金融产品代码+成立日分支序号）
                restTemplate.postForObject(
                    ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductEstablish/deleteFinCashInfoByFinprodId", map,
                    Integer.class);
                restTemplate.postForObject(
                    ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductEstablish/deleteFinCashCalcRuleByFinprodId", map,
                    Integer.class);

                // 一体化对应etliu start
                // 所有交易生效后调用的共通方法
                AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                inputDto.setProdid(entity.getFinprodId());
                inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_理财子产品.getValue());
                inputDto.setProdVdate(new Date(trdFinProductDealTotal.getSettleDate().getTime()));
                inputDto.setTrdProductDeal(trdProductDeal);
                AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
                // 一体化对应etliu end
            }
        }

        // 原始汇总交易的状态改为已撤销
        TrdFinProductDealTotal trdFinProductDealTotalRevoke = new TrdFinProductDealTotal();
        trdFinProductDealTotalRevoke.setTotleTradeId(entity.getTradeId());
        trdFinProductDealTotalRevoke.setIsCancel(DictCodeList.是否_是.getValue());
        trdFinProductDealTotalRevoke.setCreateDept(null);
        trdFinProductDealTotalRevoke.setCreateTime(null);
        trdFinProductDealTotalRevoke.setCreateDept(null);
        trdFinProductDealTotalMapper.updateByPrimaryKeySelective(trdFinProductDealTotalRevoke);

        // 原始交易的状态改为已撤销
        productCommonService.updateTrdProductDealByTotleTradeId(entity.getTradeId());

        // 组合现金和持仓处理
        BussTrdPositionDto bussTrdPositionDto = new BussTrdPositionDto();
        bussTrdPositionDto.setPortfolioId(entity.getPtlId());
        bussTrdPositionDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_撤销.getValue());
        bussTrdPositionDto.setTrdProductDealList(trdProductDealList);
        bussTrdPositionDto.setStlPayOrders(stlPayOrdersList);
        baseTradeProductDealCommService.trdProductInsertPosition(bussTrdPositionDto);

        // 组合持仓流水DTO
        // List<PtlSecPositionFlowDto> ptlSecPositionFlowDtoList = new ArrayList<>();
        // if (!StringUtil.isEmptyList(trdProductDealList)) {
        // trdProductDealList.forEach(dto -> {
        // ptlSecPositionFlowDtoList.add(this.genPtlSecPositionFlow(entity, dto.getTradeId(),
        // dto.getFinprodId(), dto.getPrinAmt().negate()));
        // });
        // }
        // // 组合现金流水DTO
        // List<PtlCashPositionFlowDto> ptlCashPositionFlowDtoList = new ArrayList<>();
        // if (!StringUtil.isEmptyList(stlPayOrdersList)) {
        // stlPayOrdersList.forEach(dto -> {
        // PtlCashPositionFlowDto ptlCashPositionFlowDto = new PtlCashPositionFlowDto();
        // ptlCashPositionFlowDto.setPortfolioId(entity.getPtlId());
        // ptlCashPositionFlowDto.setAcctId(dto.getMyAcctNo());
        // ptlCashPositionFlowDto.setHodingType(DictCodeList.持仓类型_实际.getValue());
        // ptlCashPositionFlowDto.setCdate(entity.getVdate());
        // ptlCashPositionFlowDto.setCcy(entity.getCcy());
        // ptlCashPositionFlowDto.setAmount(dto.getSettleAmt().negate());
        // ptlCashPositionFlowDto.setOrderId(dto.getOrderId());
        // ptlCashPositionFlowDtoList.add(ptlCashPositionFlowDto);
        // });
        // }
        //
        // // 调用业务组件.【金融产品交易共通.交易产品接口】,写入[可用]的组合持仓流水及现金流水
        // PtlPositionHoldDto ptlPositionHoldDto = new PtlPositionHoldDto();
        // ptlPositionHoldDto.setPtlSecPositionFlows(ptlSecPositionFlowDtoList);
        // ptlPositionHoldDto.setPtlCashPositionFlows(ptlCashPositionFlowDtoList);
        // restTemplate.postForObject(
        // ServiceApplication.PORTFOLIO_SERVICE_NAME + "/common/ptlposition/insertPosition",
        // ptlPositionHoldDto, Boolean.class);
        // add by zhangJin 邮储项目个性化-TA交易发送 start
        if ("001".equals(CacheUtil.getSysParam("CUSTOMER_BANK_CODE"))) {
            if (!StringUtil.isEmptyList(trdProductDealList)) {
                if (StringUtil.isNotEmpty(entity.getPs())
                    && StringUtil.equals(entity.getPs(), DictCodeList.交易方向_产品申购.getValue())) {
                    BigDecimal tradeAmt = BigDecimal.ZERO;
                    BigDecimal shareAmt = BigDecimal.ZERO;
                    BigDecimal feeAmt = BigDecimal.ZERO;
                    for (TrdProductDeal temp : trdProductDealList) {
                        tradeAmt = ArithmeticUtil.doAdd(tradeAmt, temp.getTradeAmt());
                        shareAmt = ArithmeticUtil.doAdd(shareAmt, temp.getShareAmt());
                        feeAmt = ArithmeticUtil.doAdd(feeAmt, temp.getFeeAmt());
                    }
                    TrdProductDeal deal = new TrdProductDeal();
                    deal.setFinprodId(entity.getFinprodId());
                    deal.setTradeAmt(tradeAmt);
                    deal.setSettleDate(trdProductDealList.get(0).getSettleDate());
                    deal.setPs(entity.getPs());
                    deal.setFeeAmt(feeAmt);
                    deal.setShareAmt(shareAmt);
                    deal.setTradeId(entity.getTradeId());
                    deal.setStatus("3");
                    restTemplate.postForObject(ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/custodian/sendProdTADeal", deal,
                        String.class);
                }
            }
        }
        // add by zhangJin 邮储项目个性化-TA交易发送 end
        
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
     * 成立确认doComplete
     *
     * @param entity 产品成立确认Dto
     * @see
     * @since 1.0
     */
    private void doCompleteInsert(FinProductEstablish002Dto entity) {
        // entity.setBranch((long)0);
        String finprodid = entity.getFinprodId();
        // 生成金融产品交易汇总
        TrdFinProductDealTotal trdFinProductDealTotal = this.genTrdFinProductDealTotal(entity);

        // 金融产品交易汇总扩展表Entity
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = this.genPaTrdFinProductDealTotal(entity);

        // 组合交易表Entity
        PtlTrade ptlTrade = this.genPtlTradeInsert(entity);

        // 批量插入
        trdFinProductDealTotalMapper.insertSelective(trdFinProductDealTotal);
        paTrdFinProductDealTotalMapper.insertSelective(paTrdFinProductDealTotal);
        ptlTradeMapper.insertSelective(ptlTrade);

        // 标的类金融产品共通处理
        BaseFinProductBaseInfoDto baseFinProductBaseInfoDto = null;

        List<TrdProductDeal> trdProductDealList = new ArrayList<>();
        List<TrdProductDealAdd> trdProductDealAddList = new ArrayList<>();
        List<PaTrdProductDeal> paTrdProductDealList = new ArrayList<>();

        // 组合持仓流水Entity
        // List<PtlSecPositionFlowDto> ptlSecPositionFlows = new ArrayList<PtlSecPositionFlowDto>();

        if (this.isIsLay(entity)) {
            // 子产品报价
            List<FinCashInfo> finCashInfoList = new ArrayList<FinCashInfo>();
            List<FinCashCalcRule> finCashCalcRuleList = new ArrayList<FinCashCalcRule>();

            for (FinProductEstablishLayerDto finProductEstablishLayerDto : entity.getFinProductEstablishLayerDtoList()) {
                BaseTradeProductDealDto baseTradeProductDealDto = this.insertBaseTradeProductDealComm(entity,
                    trdFinProductDealTotal.getTotleTradeId(), finProductEstablishLayerDto.getFinprodId(),
                    finProductEstablishLayerDto.getCurrentSub());

                trdProductDealList.add(baseTradeProductDealDto.getTrdProductDeal());
                trdProductDealAddList.add(baseTradeProductDealDto.getTrdProductDealAdd());

                // 金融产品交易扩展表Entity
                // PaTrdProductDeal paTrdProductDeal = this.genPaTrdProductDealInsert(entity,
                // baseTradeProductDealDto.getTrdProductDeal().getTradeId(),
                // finProductEstablishLayerDto.getYield());
                // paTrdProductDealList.add(paTrdProductDeal);
                // 组合交易表Entity
                // PtlTrade ptlTrade = this.genPtlTradeInsert(entity,
                // baseTradeProductDealDto.getTrdProductDeal().getTradeId(),
                // finProductEstablishLayerDto.getFinprodId());
                // ptlTradeList.add(ptlTrade);

                if (this.isIsCycle(entity)) {
                    // 交易类金融产品Entity
                    this.insertFinTradeProduct(entity, finProductEstablishLayerDto.getFinprodId(),
                        finProductEstablishLayerDto.getCurrentSub());
                }
                // 金融产品标的共通处理
                entity.setFinprodId(finProductEstablishLayerDto.getFinprodId());
                // 金融产品交易扩展表Entity
                PaTrdProductDeal paTrdProductDeal = this.genPaTrdProductDealInsert(entity,
                    baseTradeProductDealDto.getTrdProductDeal().getTradeId(), finProductEstablishLayerDto.getYield());
                paTrdProductDealList.add(paTrdProductDeal);

                if (this.isIsCycle(entity)) {
                    BaseFinProductBaseInfoDto baseFinProductBaseInfoDtoLay = this.insertBaseFinProductInfoComm(entity);
                    finCashInfoList.addAll(baseFinProductBaseInfoDtoLay.getFinCashInfoList());
                    finCashCalcRuleList.addAll(baseFinProductBaseInfoDtoLay.getFinCashCalcRuleList());
                }
                // PtlSecPositionFlowDto ptlSecPositionFlowDto = new PtlSecPositionFlowDto();
                // ptlSecPositionFlowDto.setSecAcctId("999999");
                // ptlSecPositionFlowDto.setInvAim("999999");
                // ptlSecPositionFlowDto.setTradeId(
                // baseTradeProductDealDto.getTrdProductDeal().getTradeId());
                // ptlSecPositionFlowDto.setPortfolioId(entity.getPtlId());
                // ptlSecPositionFlowDto.setFinprodId(
                // finProductEstablishLayerDto.getFinprodId() != null ? finProductEstablishLayerDto.getFinprodId() : entity.getFinprodId());
                // ptlSecPositionFlowDto.setBranch(entity.getBranch());
                // ptlSecPositionFlowDto.setHodingType(DictCodeList.持仓类型_实际.getValue());
                // ptlSecPositionFlowDto.setCdate(entity.getVdate());
                // ptlSecPositionFlowDto.setCcy(entity.getCcy());
                // ptlSecPositionFlowDto.setAmount(finProductEstablishLayerDto.getCurrentSub());
                // ptlSecPositionFlowDto.setpFinprodId(
                // finProductEstablishLayerDto.getFinprodId() != null ? entity.getFinprodId() : null);
                // ptlSecPositionFlows.add(ptlSecPositionFlowDto);

                // 一体化对应etliu start
                // 所有交易生效后调用的共通方法
                AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                inputDto.setProdid(finProductEstablishLayerDto.getFinprodId());
                if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_理财子产品.getValue(),
                    baseTradeProductDealDto.getTrdProductDeal().getFinprodType2())) {
                    inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_理财子产品.getValue());
                }
                else {
                    inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
                }
                inputDto.setProdVdate(new Date(entity.getVdate().getTime()));
                inputDto.setTrdProductDeal(baseTradeProductDealDto.getTrdProductDeal());
                AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
                // 一体化对应etliu end
            }

            baseFinProductBaseInfoDto = new BaseFinProductBaseInfoDto();
            baseFinProductBaseInfoDto.setFinCashInfoList(finCashInfoList);
            baseFinProductBaseInfoDto.setFinCashCalcRuleList(finCashCalcRuleList);
        }
        else {
            BaseTradeProductDealDto baseTradeProductDealDto = this.insertBaseTradeProductDealComm(entity,
                trdFinProductDealTotal.getTotleTradeId(), entity.getFinprodId(), entity.getAccountingAmt());

            trdProductDealList.add(baseTradeProductDealDto.getTrdProductDeal());
            trdProductDealAddList.add(baseTradeProductDealDto.getTrdProductDealAdd());

            // 金融产品交易扩展表Entity
            PaTrdProductDeal paTrdProductDeal = this.genPaTrdProductDealInsert(entity,
                baseTradeProductDealDto.getTrdProductDeal().getTradeId(), entity.getYield());
            paTrdProductDealList.add(paTrdProductDeal);

            // 交易类金融产品Entity(处理模式：普通、滚动、普通N天、滚动N天、开放阶梯)
            if (this.isIsCycle(entity)) {
                this.insertFinTradeProduct(entity, entity.getFinprodId(), entity.getAccountingAmt());
                // 金融产品标的共通处理
                baseFinProductBaseInfoDto = this.insertBaseFinProductInfoComm(entity);
            }

            // PtlSecPositionFlowDto ptlSecPositionFlowDto = new PtlSecPositionFlowDto();
            // ptlSecPositionFlowDto.setSecAcctId("999999");
            // ptlSecPositionFlowDto.setInvAim("999999");
            // ptlSecPositionFlowDto.setTradeId(entity.getTradeId());
            // ptlSecPositionFlowDto.setPortfolioId(entity.getPtlId());
            // ptlSecPositionFlowDto.setFinprodId(entity.getFinprodId());
            // ptlSecPositionFlowDto.setBranch(entity.getBranch());
            // ptlSecPositionFlowDto.setHodingType(DictCodeList.持仓类型_实际.getValue());
            // ptlSecPositionFlowDto.setCdate(entity.getVdate());
            // ptlSecPositionFlowDto.setCcy(entity.getCcy());
            // ptlSecPositionFlowDto.setAmount(entity.getCurrentSub());
            // ptlSecPositionFlows.add(ptlSecPositionFlowDto);

            // 一体化对应etliu start
            // 所有交易生效后调用的共通方法
            AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
            inputDto.setProdid(entity.getFinprodId());
            if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_理财子产品.getValue(),
                baseTradeProductDealDto.getTrdProductDeal().getFinprodType2())) {
                inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_理财子产品.getValue());
            }
            else {
                inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
            }
            inputDto.setProdVdate(new Date(entity.getVdate().getTime()));
            inputDto.setTrdProductDeal(baseTradeProductDealDto.getTrdProductDeal());
            AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
            // 一体化对应etliu end
        }

        trdProductDealList.forEach(trdProductDeal -> trdProductDealMapper.insertSelective(trdProductDeal));
        trdProductDealAddList.forEach(trdProductDeal -> trdProductDealAddMapper.insertSelective(trdProductDeal));
        paTrdProductDealList.forEach(trdProductDeal -> paTrdProductDealMapper.insertSelective(trdProductDeal));

        // 划款指令表Entity
        List<StlPayOrdersDto> stlPayOrderList = null;
        if (!StringUtil.isEmptyList(entity.getBussTrdTransferOrder())) {
            StlPayOrdersDto stlPayOrdersDto = new StlPayOrdersDto();
            stlPayOrderList = new ArrayList<>();
            BeanUtil.copyPropertiesIgnoreNull(stlPayOrdersDto, entity.getBussTrdTransferOrder().get(0));
            stlPayOrdersDto.setTradeId(entity.getTradeId());
            stlPayOrdersDto.setWorkflowId(entity.getWorkflowId());
            stlPayOrderList.add(stlPayOrdersDto);
            stlPayOrdersMapper.insertSelective(stlPayOrdersDto);
        }

        if (this.isIsCycle(entity)) {
            // 金融产品标的共通处理
            restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductEstablish/insertFinProductComm",
                baseFinProductBaseInfoDto, Integer.class);
        }
        // 理财分行募集明细Entity
        List<FinProductEstablishBranchDto> finProductEstablishBranchDtoList = entity.getFinProductEstablishBranchDtoList();
        if (!StringUtil.isEmptyList(finProductEstablishBranchDtoList)) {
            for (FinProductEstablishBranchDto finProductEstablishBranchDto : finProductEstablishBranchDtoList) {
                finProductEstablishBranchDto.setTradeId(entity.getTradeId());
                finProductEstablishBranchDto.setCdate(entity.getVdate());
                paBranchSaleDetailMapper.insert(finProductEstablishBranchDto);
            }
        }

        // 组合现金和持仓处理
        BussTrdPositionDto bussTrdPositionDto = new BussTrdPositionDto();
        bussTrdPositionDto.setPortfolioId(entity.getPtlId());
        bussTrdPositionDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_有效.getValue());
        bussTrdPositionDto.setTrdProductDealList(trdProductDealList);
        bussTrdPositionDto.setStlPayOrders(stlPayOrderList);
        baseTradeProductDealCommService.trdProductInsertPosition(bussTrdPositionDto);
        // List<PtlCashPositionFlowDto> ptlCashPositionFlows = new ArrayList<PtlCashPositionFlowDto>();
        // if (!StringUtil.isEmptyList(entity.getBussTrdTransferOrder())) {
        // for (StlPayOrdersDto stlPayOrdersDto : entity.getBussTrdTransferOrder()) {
        // PtlCashPositionFlowDto ptlCashPositionFlowDto = new PtlCashPositionFlowDto();
        // ptlCashPositionFlowDto.setPortfolioId(entity.getPtlId());
        // ptlCashPositionFlowDto.setAcctId(stlPayOrdersDto.getMyAcctNo());
        // ptlCashPositionFlowDto.setHodingType(DictCodeList.持仓类型_实际.getValue());
        // ptlCashPositionFlowDto.setCdate(entity.getVdate());
        // ptlCashPositionFlowDto.setCcy(entity.getCcy());
        // ptlCashPositionFlowDto.setAmount(stlPayOrdersDto.getSettleAmt());
        // ptlCashPositionFlowDto.setOrderId(stlPayOrdersDto.getOrderId());
        // ptlCashPositionFlows.add(ptlCashPositionFlowDto);
        // }
        // }
        //
        // PtlPositionHoldDto ptlPositionHoldDto = new PtlPositionHoldDto();
        // ptlPositionHoldDto.setPtlSecPositionFlows(ptlSecPositionFlows);
        // ptlPositionHoldDto.setPtlCashPositionFlows(ptlCashPositionFlows);
        // restTemplate.postForObject(
        // ServiceApplication.PORTFOLIO_SERVICE_NAME + "/common/ptlposition/insertPosition",
        // ptlPositionHoldDto, Boolean.class);

        // 将母产品的发行状态改为确认发行
        FinProductAdd finProductAdd = new FinProductAdd();
        finProductAdd.setFinprodId(finprodid);
        finProductAdd.setIssueStatus(DictCodeListExt1.发行状态_发行成功.getValue());
        finProductAdd.setIssueStatusRemark(DictCodeList.共通业务类型_收益型产品认申购管理.getCodeName());
        finProductAddMapper.updateByPrimaryKeySelective(finProductAdd);

        // add by zhangJin 邮储项目个性化-TA交易发送 start
        if ("001".equals(CacheUtil.getSysParam("CUSTOMER_BANK_CODE"))) {
            // 发送建账
            if (StringUtil.isNotEmpty(entity.getPs()) && StringUtil.equals(entity.getPs(), DictCodeList.交易方向_产品认购.getValue())) {
                restTemplate.postForObject(ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/custodian/sendProdRelease",
                    entity.getFinprodId(), String.class);
            }
            // 发送TA申购数据
            else {
                BigDecimal tradeAmt = BigDecimal.ZERO;
                BigDecimal shareAmt = BigDecimal.ZERO;
                BigDecimal feeAmt = BigDecimal.ZERO;
                for (TrdProductDeal temp : trdProductDealList) {
                    tradeAmt = ArithmeticUtil.doAdd(tradeAmt, temp.getTradeAmt());
                    shareAmt = ArithmeticUtil.doAdd(shareAmt, temp.getShareAmt());
                    feeAmt = ArithmeticUtil.doAdd(feeAmt, temp.getFeeAmt());
                }
                TrdProductDeal deal = new TrdProductDeal();
                deal.setFinprodId(entity.getFinprodId());
                deal.setTradeAmt(tradeAmt);
                deal.setSettleDate(trdProductDealList.get(0).getSettleDate());
                deal.setPs(entity.getPs());
                deal.setFeeAmt(feeAmt);
                deal.setShareAmt(shareAmt);
                deal.setTradeId(entity.getTradeId());
                deal.setStatus("0");
                restTemplate.postForObject(ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/custodian/sendProdTADeal", deal,
                    String.class);
            }
        }
        // add by zhangJin 邮储项目个性化-TA交易发送 end
        
        //20191008新增 	
        TrdAsynHandleStatus trdAsynHandleStatus = new TrdAsynHandleStatus();
        trdAsynHandleStatus.setTradeId(trdFinProductDealTotal.getTotleTradeId());		//交易汇总id
        trdAsynHandleStatus.setTrdTableType("01");		// 交易表类型(01：金融产品交易汇总表，02：金融产品交易表)
        trdAsynHandleStatus.setHandleStatus("01");		//01
        trdAsynHandleStatusMapper.insertSelective(trdAsynHandleStatus);
    }

    /**
     * 提交时登录业务流程表
     *
     * @param entity 画面参数dto
     * @see
     * @since 1.0
     */
    private void insertBussFlowData(FinProductEstablish002Dto entity) {
        if (!StringUtil.equals("submit", entity.getOperateType())) {
            return;
        }

        // 数据处理
        this.prepareData(entity);

        // 生成划款指令信息
        if (entity.getBussTrdTransferOrder() != null) {
            WflStlPayOrders wflStlPayOrders = new WflStlPayOrders();
            BeanUtil.copyPropertiesIgnoreNull(wflStlPayOrders, entity.getBussTrdTransferOrder().get(0));
            wflStlPayOrders.setWorkflowStatus(DictCodeListExt1.画面数据状态_流程中.getValue());
            wflStlPayOrders.setWorkflowId(entity.getWorkflowId());
            wflStlPayOrders.setTradeId(entity.getTradeId());
            wflStlPayOrdersMapper.insert(wflStlPayOrders);
        }

        // 生成金融产品交易汇总
        TrdFinProductDealTotal trdFinProductDealTotal = this.genTrdFinProductDealTotal(entity);
        WflTrdFinProductDealTotal wflTrdFinProductDealTotal = new WflTrdFinProductDealTotal();
        BeanUtil.copyPropertiesIgnoreNull(wflTrdFinProductDealTotal, trdFinProductDealTotal);
        wflTrdFinProductDealTotal.setWorkflowStatus(DictCodeListExt1.画面数据状态_流程中.getValue());
        wflTrdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());

        // 金融产品交易汇总扩展表Entity
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = this.genPaTrdFinProductDealTotal(entity);
        WflPaTrdFinDealTotal wflPaTrdFinDealTotal = new WflPaTrdFinDealTotal();
        BeanUtil.copyPropertiesIgnoreNull(wflPaTrdFinDealTotal, paTrdFinProductDealTotal);
        wflPaTrdFinDealTotal.setWorkflowStatus(DictCodeListExt1.画面数据状态_流程中.getValue());
        wflPaTrdFinDealTotal.setWorkflowId(entity.getWorkflowId());

        List<WflTrdProductDeal> wflTrdProductDealList = new ArrayList<>();
        List<WflTrdProductDealAdd> wflTrdProductDealAddList = new ArrayList<>();
        List<WflPaTrdProductDeal> wflPaTrdProductDealList = new ArrayList<>();

        if (this.isIsLay(entity)) {
            for (FinProductEstablishLayerDto finProductEstablishLayerDto : entity.getFinProductEstablishLayerDtoList()) {
                BaseTradeProductDealDto baseTradeProductDealDto = this.insertBaseTradeProductDealComm(entity,
                    trdFinProductDealTotal.getTotleTradeId(), finProductEstablishLayerDto.getFinprodId(),
                    finProductEstablishLayerDto.getCurrentSub());

                if (baseTradeProductDealDto.getTrdProductDeal() != null) {
                    WflTrdProductDeal wflTrdProductDeal = new WflTrdProductDeal();
                    BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDeal, baseTradeProductDealDto.getTrdProductDeal());
                    wflTrdProductDeal.setWorkflowStatus(DictCodeListExt1.画面数据状态_流程中.getValue());
                    wflTrdProductDealList.add(wflTrdProductDeal);
                }

                if (baseTradeProductDealDto.getTrdProductDealAdd() != null) {
                    WflTrdProductDealAdd wflTrdProductDealAdd = new WflTrdProductDealAdd();
                    BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDealAdd, baseTradeProductDealDto.getTrdProductDealAdd());
                    wflTrdProductDealAdd.setWorkflowStatus(DictCodeListExt1.画面数据状态_流程中.getValue());
                    wflTrdProductDealAddList.add(wflTrdProductDealAdd);
                }

                // 金融产品交易扩展表Entity
                PaTrdProductDeal paTrdProductDeal = this.genPaTrdProductDealInsert(entity,
                    baseTradeProductDealDto.getTrdProductDeal().getTradeId(), finProductEstablishLayerDto.getYield());
                WflPaTrdProductDeal wflPaTrdProductDeal = new WflPaTrdProductDeal();
                BeanUtil.copyPropertiesIgnoreNull(wflPaTrdProductDeal, paTrdProductDeal);
                wflPaTrdProductDeal.setWorkflowStatus(DictCodeListExt1.画面数据状态_流程中.getValue());
                wflPaTrdProductDealList.add(wflPaTrdProductDeal);
            }
        }
        else {
            BaseTradeProductDealDto baseTradeProductDealDto = this.insertBaseTradeProductDealComm(entity,
                trdFinProductDealTotal.getTotleTradeId(), entity.getFinprodId(), entity.getAccountingAmt());

            if (baseTradeProductDealDto.getTrdProductDeal() != null) {
                WflTrdProductDeal wflTrdProductDeal = new WflTrdProductDeal();
                BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDeal, baseTradeProductDealDto.getTrdProductDeal());
                wflTrdProductDeal.setWorkflowStatus(DictCodeListExt1.画面数据状态_流程中.getValue());
                wflTrdProductDealList.add(wflTrdProductDeal);
            }

            if (baseTradeProductDealDto.getTrdProductDealAdd() != null) {
                // 金融产品交易附属entity
                WflTrdProductDealAdd wflTrdProductDealAdd = new WflTrdProductDealAdd();
                BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDealAdd, baseTradeProductDealDto.getTrdProductDealAdd());
                wflTrdProductDealAdd.setWorkflowStatus(DictCodeListExt1.画面数据状态_流程中.getValue());
                wflTrdProductDealAddList.add(wflTrdProductDealAdd);
            }

            // 金融产品交易扩展表Entity
            PaTrdProductDeal paTrdProductDeal = this.genPaTrdProductDealInsert(entity,
                baseTradeProductDealDto.getTrdProductDeal().getTradeId(), entity.getYield());
            WflPaTrdProductDeal wflPaTrdProductDeal = new WflPaTrdProductDeal();
            wflPaTrdProductDeal.setWorkflowStatus(DictCodeListExt1.画面数据状态_流程中.getValue());
            BeanUtil.copyPropertiesIgnoreNull(wflPaTrdProductDeal, paTrdProductDeal);
            wflPaTrdProductDealList.add(wflPaTrdProductDeal);
        }
        wflTrdFinProductDealTotalMapper.insertSelective(wflTrdFinProductDealTotal);
        wflPaTrdFinDealTotalMapper.insertSelective(wflPaTrdFinDealTotal);
        wflTrdProductDealList.forEach(wflTrdProductDeal -> wflTrdProductDealMapper.insertSelective(wflTrdProductDeal));
        wflTrdProductDealAddList.forEach(wflTrdProductDeal -> wflTrdProductDealAddMapper.insertSelective(wflTrdProductDeal));
        wflPaTrdProductDealList.forEach(wflTrdProductDeal -> wflPaTrdProductDealMapper.insertSelective(wflTrdProductDeal));

    }

    /**
     * 撤销doComplete
     *
     * @param entity 参数
     * @see
     * @since 1.0
     */
    private void doInsertRevoke(FinProductEstablish002Dto entity) {
        // 根据汇总交易编号查询金融产品交易汇总
        TrdFinProductDealTotal trdFinProductDealTotal = productCommonService.genTrdFinProductDealTotalRevoke(entity.getTradeId());
        if (trdFinProductDealTotal != null) {
            WflTrdFinProductDealTotal wflTrdFinProductDealTotal = new WflTrdFinProductDealTotal();
            BeanUtil.copyPropertiesIgnoreNull(wflTrdFinProductDealTotal, trdFinProductDealTotal);
            wflTrdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
            wflTrdFinProductDealTotal.setWorkflowStatus(DictCodeListExt1.画面数据状态_流程中.getValue());
            wflTrdFinProductDealTotalMapper.insertSelective(wflTrdFinProductDealTotal);
        }

        // 根据汇总交易编号查询金融产品交易汇总扩展
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = productCommonService.genPaTrdFinProductDealTotalRevoke(
            entity.getTradeId());
        if (paTrdFinProductDealTotal != null) {
            WflPaTrdFinDealTotal wflPaTrdFinDealTotal = new WflPaTrdFinDealTotal();
            BeanUtil.copyPropertiesIgnoreNull(wflPaTrdFinDealTotal, paTrdFinProductDealTotal);
            wflPaTrdFinDealTotal.setWorkflowId(entity.getWorkflowId());
            wflPaTrdFinDealTotal.setWorkflowStatus(DictCodeListExt1.画面数据状态_流程中.getValue());
            wflPaTrdFinDealTotalMapper.insert(wflPaTrdFinDealTotal);
        }

        // 根据汇总交易编号查询划款指令
        List<StlPayOrdersDto> stlPayOrdersList = productCommonService.genStlPayOrdersRevoke(entity.getTradeId());
        if (!StringUtil.isEmptyList(stlPayOrdersList)) {
            for (StlPayOrders stlPayOrders : stlPayOrdersList) {
                WflStlPayOrders wflStlPayOrders = new WflStlPayOrders();
                BeanUtil.copyPropertiesIgnoreNull(wflStlPayOrders, stlPayOrders);
                wflStlPayOrders.setWorkflowId(entity.getWorkflowId());
                wflStlPayOrders.setWorkflowStatus(DictCodeListExt1.画面数据状态_流程中.getValue());
                wflStlPayOrdersMapper.insert(wflStlPayOrders);
            }
        }

        // 根据汇总交易编号查询交易信息
        List<TrdProductDeal> trdProductDealList = productCommonService.genTrdProductDealRevoke(entity.getTradeId(),
            entity.getRevokeReason());
        if (!StringUtil.isEmptyList(trdProductDealList)) {
            for (TrdProductDeal trdProductDeal : trdProductDealList) {
                WflTrdProductDeal wflTrdProductDeal = new WflTrdProductDeal();
                BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDeal, trdProductDeal);
                wflTrdProductDeal.setWorkflowId(entity.getWorkflowId());
                wflTrdProductDeal.setWorkflowStatus(DictCodeListExt1.画面数据状态_流程中.getValue());
                wflTrdProductDealMapper.insert(wflTrdProductDeal);
            }
        }

        // 根据汇总交易编号查询交易附属信息
        List<TrdProductDealAdd> trdProductDealAddList = productCommonService.genTrdProductDealAddRevoke(entity.getTradeId());
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
                wflTrdProductDealAddMapper.insert(wflTrdProductDealAdd);
            }
        }

        // 根据汇总交易编号查询金融产品交易扩展
        List<PaTrdProductDeal> paTrdProductDealList = productCommonService.genPaTrdProductDealRevoke(entity.getTradeId());
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
                wflPaTrdProductDealMapper.insert(wflPaTrdProductDeal);
            }
        }
        // add by gcl 2019/4/2 start
        // 将母产品的发行状态改为确认发行
        if (DictCodeList.交易方向_产品认购.getValue().equals(entity.getPs())) {
            // 检查除这笔认购外是否还存在其他有效认购交易,若存在，则不将发行状态修改为撤销状态
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("finprodId", entity.getFinprodId());
            map.put("tradeId", entity.getTradeId());
            Integer OtherSurDeal = restTemplate.postForObject(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductEstablish/selectOtherSurDeal", map, Integer.class);
            if (OtherSurDeal < 1) {
                FinProductAdd finProductAdd = new FinProductAdd();
                finProductAdd.setFinprodId(entity.getFinprodId());
                finProductAdd.setIssueStatus(DictCodeListExt1.发行状态_确认发行.getValue());
                finProductAdd.setIssueStatusRemark(DictCodeList.共通业务类型_收益型产品认申购管理.getCodeName());
                finProductAddMapper.updateByPrimaryKeySelective(finProductAdd);
            }
            // add by gcl 2019/4/2 end
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
    private PtlSecPositionFlowDto genPtlSecPositionFlow(FinProductEstablish002Dto entity, String tradeId, String finprodId,
                                                        BigDecimal prinamt) {
        PtlSecPositionFlowDto ptlSecPositionFlowDto = new PtlSecPositionFlowDto();
        ptlSecPositionFlowDto.setTradeId(tradeId);
        ptlSecPositionFlowDto.setPortfolioId(entity.getPtlId());
        ptlSecPositionFlowDto.setFinprodId(finprodId != null ? finprodId : entity.getFinprodId());
        // update by dingjunfeng start
        ptlSecPositionFlowDto.setBranch(0l); // Long.parseLong(DateUtil.format(entity.getVdate(), DateUtil.DEFAULT_OTHER_FORMAT))
        // update by dingjunfeng end
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
     * 根据产品代码模糊查询联系下拉框(发行成功的母产品)
     *
     * @param map 参数map
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryProdIdInfoIssueSuc(Map<String, Object> map) {
        map.put("userId", SecurityUtil.getLoginUser().getUserName());
        map.put("platDate", DateUtil.getSysDate());
        return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductEstablish/queryProdIdInfoIssueSuc",
            map, SelectOptionDto.class);
    }

    /**
     * 根据金融产品代码查询产品信息
     *
     * @param finprodId 金融产品代码
     * @return 产品信息
     * @see
     * @since 1.0
     */
    public FinProductBuild002Dto queryProdInfoByFinprodId(String finprodId, String vdate) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("vdate", vdate);
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        map.put("branch", Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
        return restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductEstablish/queryProdInfoByFinprodId", map,
            FinProductBuild002Dto.class);
    }

    /**
     * 根据金融产品代码查询产品信息
     *
     * @param finprodId 金融产品代码
     * @return 产品信息
     * @see
     * @since 1.0
     */
    public FinProductBuild002Dto queryProdInfoByFinprodId2(String finprodId) {
        return restTemplate.getForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductEstablish/queryProdInfoByFinprodId2?finprodId=" + finprodId,
            FinProductBuild002Dto.class);
    }

    public FinProductEstablish002Dto selectProdInfo(String finprodId, String tradeId, String effectStatus) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("tradeId", tradeId);
        map.put("effectStatus", effectStatus);
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        return restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductEstablish/selectProdInfoByFinprodId", map,
            FinProductEstablish002Dto.class);
    }

//	public BussTrdTransferOrderDto selectBussTrdTransferOrder(String tradeId) {
//		// TODO Auto-generated method stub
//		return stlPayOrdersMapper.selectByPrimaryKey(tradeId);
//	}

    private Integer selectIsBranch(String finprodId, Long branch) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("branch", branch);
        return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductEstablish/selectIsBranch", map,
            Integer.class);
    }

    /**
     * 是否是周期型产品
     *
     * @param entity 确认信息
     * @return 是否是周期型产品
     * @see
     * @since 1.0
     */
    private boolean isIsCycle(FinProductEstablish002Dto entity) {
        boolean flag = false;
        if (StringUtil.equals(entity.getDealMode(), DictCodeList.处理模式_周期型.getValue())
            || StringUtil.equals(entity.getDealMode(), DictCodeList.处理模式_分档周期型.getValue())) {
            flag = true;
        }
        return flag;
    }

    /**
     * 查询是否有交易通过产品代码
     */
    public Integer selectIsTrade(String finprodId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductEstablish/selectIsTrade", map,
            Integer.class);
    }

    /**
     * 根据交易编号查询指令信息
     */
    public List<BussTrdTransferOrderBase> selectOrdersByBizCode(String tradeId, String effectStatus) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("bizCode", tradeId);
        map.put("effectStatus", effectStatus);
        return restTemplate.postForList(
            ServiceApplication.SETTLE_SERVICE_NAME + "/BussSettleOrderComm/selectOrdersByBizCodeZhichu", map,
            BussTrdTransferOrderBase.class);
    }
}
