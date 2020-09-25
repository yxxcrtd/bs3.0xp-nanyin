/*
 * 文件名：PtlSubFundsMange002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangyf
 * 修改时间：2019年8月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlsubfundsmange.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.settle.dto.BussSettleOrderParameterDto;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlPositionHoldDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.TrdCommonWflParam;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import com.joyintech.tams.invest.common.trade.service.impl.etrcash.EtrCommonCashPositionDealService;
import com.joyintech.tams.invest.portfolio.ptlsubfundsmange.dto.PtlSubFundsMange002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

import static com.joyintech.tams.invest.common.trade.service.impl.etrcash.EtrCommonCashPositionDealService.getSeqNo;
/**
 * 产品交易资产分配-新建service
 *
 * @author zhangyf
 * @version 2019年8月19日
 * @see PtlSubFundsMange002Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PtlSubFundsMange002Service extends CommonExportService<PtlSubFundsMange002Dto>
        implements BaseProcessService<PtlSubFundsMange002Dto> {

    /**
     * 通用Mapper共同代理方法
     */

    /**
     * 共通交易
     */
    @Autowired
    private BaseTradeProductDealCommService baseTradeProductDealCommService;


    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;

    @Autowired
    private PaTrdFinProductDealTotalMapper paTrdFinProductDealTotalMapper;

    @Autowired
    private TrdProductDealMapper trdProductDealMapper;

    @Autowired
    private TrdProductDealAddMapper trdProductDealAddMapper;

    @Autowired
    private PaTrdProductDealMapper paTrdProductDealMapper;

    @Autowired
    private PtlTradeMapper ptlTradeMapper;

    @Autowired(required = false)
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;

    @Autowired(required = false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;
    /**
     * 根据产品代码模糊查询联系下拉框
     *
     * @param map 参数map
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryProdIdInfo(Map<String, Object> map) {
        return restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSubFundsMange/queryProdIdInfo",
                map, SelectOptionDto.class);
    }

    /**
     * 通过产品代码，获取产品名称
     *
     * @param pageParam
     * @return
     */
    public FinProduct queryProdName(Map<String, Object> pageParam) {
        return restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSubFundsMange/queryProdName",
                pageParam, FinProduct.class);
    }

    /**
     * 检查：列表中数据存在有效或者流程中的数据
     *
     * @param dto
     * @return
     */
    public boolean isDataEffOrInWorkFlow(PtlSubFundsMange002Dto dto) {
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("PA_BUSS_TYPE", DictCodeListExt1.平安业务类型_产品交易资金分配.getValue());
        pageParam.put("FINPROD_MARKET_ID", dto.getProdId());
        pageParam.put("WORKFLOW_STATUS", DictCodeListExt1.交易流程状态_流程中.getValue());
        pageParam.put("SPLIT_TYPE", DictCodeListExt3.拆分标识_已拆分.getValue());
        pageParam.put("SEQNO", dto.getSeqNo().split(","));
        int num = restTemplate.postForObject(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSubFundsMange/queryDataEffOrInWorkFlowCount",
                pageParam, Integer.class);
        return num > 0;
    }

    /**
     * 获取资金分配主信息(不同的数据状态)
     *
     * @param dto
     * @return
     */
    public PtlSubFundsMange002Dto queryPtlSubFundsWithStatue(PtlSubFundsMange002Dto dto) {
        Map<String, Object> para = new HashMap<String, Object>();
        para.put("totleTradeId", dto.getTotleTradeId());
        para.put("pageDataStatus", dto.getPageDataStatus());
        para.put("workflowId", dto.getWorkflowId());
        return restTemplate.postForObject(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSubFundsMange/queryPtlSubFundsWithStatue", para,
                PtlSubFundsMange002Dto.class);
    }

    /**
     * 获取子组合资金分配明细列表
     *
     * @param pageParam
     * @return
     */
    public List<PtlSubFundsMange002Dto> selectPortfolioDetail(Map<String, Object> pageParam) {
        pageParam.put("HODING_TYPE", DictCodeList.持仓类型_实际.getValue());
        return restTemplate.postForList(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSubFundsMange/selectPortfolioDetail", pageParam,
                PtlSubFundsMange002Dto.class);
    }

    /**
     * 新建 {@inheritDoc}
     */
    @Override
    public void doInsert(PtlSubFundsMange002Dto entity, OperationType operationType) {
        // 启动流程的同时，登录交易信息进业务流程表
        this.saveDataWhenStartWorkflow(entity, operationType);
    }

    /**
     * 作废 {@inheritDoc}
     */
    @Override
    public void doDelete(String workflowId) {
        // 7.2删除原业务流程表中的交易记录
        this.deleteBussFlowData(workflowId);
    }

    /**
     * 工作流完成
     */
    @Override
    public void doComplete(PtlSubFundsMange002Dto entity, OperationType type) {
        // 7.1登录各业务表
        this.saveDataWhenCompleteWorkflow(entity, type);
        // 7.2删除原业务流程表中的交易记录
        this.deleteBussFlowData(entity.getWorkflowId());
        // 7.3更新组合现金流水中拆分标识
        this.updatePtlCashPositionFlowBySeqnos(entity);
    }

    /**
     * 更新组合现金流水中拆分标识  PTL_CASH_POSITION_FLOW
     *
     * @param dto
     * @return
     */
    public boolean updatePtlCashPositionFlowBySeqnos(PtlSubFundsMange002Dto dto) {
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("SPLIT_TYPE", DictCodeListExt3.拆分标识_已拆分.getValue());
        List<String> list = new ArrayList<String>(Arrays.asList(dto.getSeqNo().split(",")));
        pageParam.put("SEQNO", list);
        User user = SecurityUtil.getLoginUser();
        pageParam.put("updateUser", user.getUserName());
        int num = restTemplate.postForObject(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSubFundsMange/updatePtlCashPositionFlowBySeqnos",
                pageParam, Integer.class);
        return num > 0;
    }

    /**
     * 流程结束登录各业务表
     *
     * @param entity 画面参数dto
     * @see
     * @since 1.0
     */
    private void saveDataWhenCompleteWorkflow(PtlSubFundsMange002Dto entity, OperationType type) {

        // 5.构造调用组件的输入参数DTO
        FinProductTradeDto finProductTradeDto = setFinProductTradeDto(entity);

        // 7.1登录各业务表，包括金融产品交易、金融产品汇总交易、金融产品附属、组合现金流水表、金融产品汇总交易扩展表、金融产品交易扩展表
        // 金融产品汇总交易
        TrdFinProductDealTotal trdFinProductDealTotal = finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdFinProductDealTotal();
        trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        // 金融产品汇总交易扩展表
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = finProductTradeDto.getBaseTradeProductDealDtos().get(0).getPaTrdFinProductDealTotal();

        trdFinProductDealTotalMapper.insertSelective(trdFinProductDealTotal);
        paTrdFinProductDealTotalMapper.insertSelective(paTrdFinProductDealTotal);
        List<PtlCashPositionFlowDto> ptlCashPositionFlowDtos = new ArrayList<>();

        // 批量插入业务表 - 主信息
        for (BaseTradeProductDealDto baseTradeProductDealDto : finProductTradeDto.getBaseTradeProductDealDtos()) {
            // 金融产品交易
            trdProductDealMapper.insertSelective(baseTradeProductDealDto.getTrdProductDeal());
            // 金融产品附属
            trdProductDealAddMapper.insertSelective(baseTradeProductDealDto.getTrdProductDealAdd());
            ptlCashPositionFlowDtos.add(genPtlCashFlows(baseTradeProductDealDto.getTrdProductDeal(), baseTradeProductDealDto.getTrdProductDealAdd()));
            // 组合现金流水表
            // 不处理 --> 调用业务组件.【金融产品交易共通.交易产品接口】，共通登录了组合现金流水表、组合现金余额表
            // 金融产品交易扩展表
            paTrdProductDealMapper.insertSelective(baseTradeProductDealDto.getPaTrdProductDeal());

            // 所有交易生效后调用的共通方法
            AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
            inputDto.setProdid(baseTradeProductDealDto.getTrdProductDealAdd().getPortfolioId());
            inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
            inputDto.setTrdProductDeal(baseTradeProductDealDto.getTrdProductDeal());
            AllTradeCommonUtil.handleAfterTradeComplete(inputDto);

            // modyfi by yanya on 20191119 组合交易表Entity -> 组合交易信息表
            baseTradeProductDealDto.getPtlTrade().forEach(ptlTrade -> ptlTradeMapper.insertSelective(ptlTrade));

        }

//        // 调用业务组件.【金融产品交易共通.交易产品接口】
//        finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_有效.getValue());
//        finProductTradeDto.setNeedSum(true);
//        baseTradeProductDealCommService.execute(finProductTradeDto);

        // 同时登录组合交易表
        // 组合交易表Entity -> 组合交易信息表 放在上面循环内。
        //finProductTradeDto.getBaseTradeProductDealDtos().get(0).getPtlTrade().forEach(ptlTrade -> ptlTradeMapper.insertSelective(ptlTrade));

        PtlPositionHoldDto ptlPositionHoldDto = new PtlPositionHoldDto();
        ptlPositionHoldDto.setPtlCashPositionFlows(ptlCashPositionFlowDtos);
        if (ptlPositionHoldDto.getPtlCashPositionFlows() != null && !ptlPositionHoldDto.getPtlCashPositionFlows().isEmpty()) {
            for (PtlCashPositionFlowDto ptlCashPositionFlow : ptlPositionHoldDto.getPtlCashPositionFlows()) {
                basePtlCashPositionInfoService.insertCashPositionFlow(ptlCashPositionFlow);
            }
        }

        baseEtrCashPositionCommService.portfolioEtrCashPosition(ptlPositionHoldDto, false);
    }

    private PtlCashPositionFlowDto genPtlCashFlows(TrdProductDeal trdProductDeal, TrdProductDealAdd trdProductDealAdd) {
        PtlCashPositionFlowDto ptlCashPositionFlowDto = new PtlCashPositionFlowDto();
        ptlCashPositionFlowDto.setPortfolioId(trdProductDealAdd.getPortfolioId());
        ptlCashPositionFlowDto.setAmount(trdProductDeal.getTradeAmt());
        ptlCashPositionFlowDto.setAcctId(EtrCommonCashPositionDealService.select组合托管户(trdProductDealAdd.getPortfolioId(),
                trdProductDeal.getCcy(), DictCodeListExt2.资金账户类型_托管户.getValue()));
        ptlCashPositionFlowDto.setSeqno(getSeqNo());
        ptlCashPositionFlowDto.setHodingType(DictCodeList.持仓类型_实际.getValue());
        ptlCashPositionFlowDto.setCdate(trdProductDeal.getSettleDate());
        ptlCashPositionFlowDto.setOrderId(trdProductDeal.getTradeId());
        ptlCashPositionFlowDto.setCcy(trdProductDeal.getCcy());
        ptlCashPositionFlowDto.setSplitType(DictCodeListExt3.拆分标识_已拆分.getValue());
        return ptlCashPositionFlowDto;
    }

    /**
     * 删除业务流程数据
     *
     * @param workflowId 工作流代码
     * @see
     * @since 1.0
     */
    private void deleteBussFlowData(String workflowId) {
        baseTradeProductDealCommService.deleteWflData(workflowId);
    }

    /**
     * 保存时登录业务流程表
     *
     * @param entity 画面参数dto
     * @see
     * @since 1.0
     */
    private void saveDataWhenStartWorkflow(PtlSubFundsMange002Dto entity, OperationType type) {

        // 5.构造调用组件的输入参数DTO
        FinProductTradeDto finProductTradeDto = setFinProductTradeDto(entity);

        // 6.启动流程的同时，登录交易信息进业务流程表
        // 6.1.插入新建记录到金融产品业务流程信息表中

        //金融产品交易信息DTO.金融产品汇总交易Entity -> 金融产品交易汇总业务流程表
        WflTrdFinProductDealTotal wflTrdFinProductDealTotal = new WflTrdFinProductDealTotal();
        BeanUtil.copyPropertiesIgnoreNull(wflTrdFinProductDealTotal, finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdFinProductDealTotal());
        wflTrdFinProductDealTotal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
        wflTrdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        wflTrdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());

        //金融产品交易信息DTO.金融产品汇总交易扩展Entity -> 金融产品交易汇总扩展业务流程表
        WflPaTrdFinDealTotal wflPaTrdFinDealTotal = new WflPaTrdFinDealTotal();
        BeanUtil.copyPropertiesIgnoreNull(wflPaTrdFinDealTotal, finProductTradeDto.getBaseTradeProductDealDtos().get(0).getPaTrdFinProductDealTotal());
        wflPaTrdFinDealTotal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
        wflPaTrdFinDealTotal.setWorkflowId(entity.getWorkflowId());

        TrdCommonWflParam trdCommonWflParam = new TrdCommonWflParam();
        trdCommonWflParam.setWflTrdFinProductDealTotal(wflTrdFinProductDealTotal);
        trdCommonWflParam.setWflPaTrdFinDealTotal(wflPaTrdFinDealTotal);
        trdCommonWflParam.setWflTrdProductDeals(new ArrayList<>());
        trdCommonWflParam.setWflDealAdds(new ArrayList<>());
        trdCommonWflParam.setWflPaDeals(new ArrayList<>());

        // 6.2循环List将信息插入Entity对应的业务流程DB中
        for (BaseTradeProductDealDto baseTradeProductDealDto : finProductTradeDto.getBaseTradeProductDealDtos()) {
            // 金融产品交易Entity -> 金融产品交易业务流程表
            // 二次加工5中产品交易DTO.List<金融产品交易信息DTO>.金融产品交易表Entity
            // 金融产品交易信息DTO.金融产品交易Entity.交易状态=“新建”
            // 金融产品交易信息DTO.金融产品交易Entity.工作流代码=3发起流程中返回的工作流实例ID
            // 金融产品交易信息DTO.金融产品交易Entity.数据状态=“新建”

            WflTrdProductDeal wflTrdProductDeal = new WflTrdProductDeal();
            BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDeal, baseTradeProductDealDto.getTrdProductDeal());
            wflTrdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
            wflTrdProductDeal.setWorkflowId(entity.getWorkflowId());
            wflTrdProductDeal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());

            // 金融产品交易附属Entity -> 金融产品交易附属业务流程表
            WflTrdProductDealAdd wflTrdProductDealAdd = new WflTrdProductDealAdd();
            BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDealAdd, baseTradeProductDealDto.getTrdProductDealAdd());
            wflTrdProductDealAdd.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
            wflTrdProductDealAdd.setWorkflowId(entity.getWorkflowId());

            // 金融产品交易扩展Entity -> 金融产品交易扩展业务流程表
            WflPaTrdProductDeal wflPaTrdProductDeal = new WflPaTrdProductDeal();
            BeanUtil.copyPropertiesIgnoreNull(wflPaTrdProductDeal, baseTradeProductDealDto.getPaTrdProductDeal());
            wflPaTrdProductDeal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
            wflPaTrdProductDeal.setWorkflowId(entity.getWorkflowId());

            trdCommonWflParam.getWflTrdProductDeals().add(wflTrdProductDeal);
            trdCommonWflParam.getWflDealAdds().add(wflTrdProductDealAdd);
            trdCommonWflParam.getWflPaDeals().add(wflPaTrdProductDeal);
        }

        restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSubFundsMange/doInsert",
                trdCommonWflParam, String.class);

        // 6.2登录各业务表，包括写入[可用]的组合持仓流水及现金流水
        // 调用业务组件.【金融产品交易共通.交易产品接口】,写入[可用]的组合持仓流水及现金流水
        finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
        baseTradeProductDealCommService.execute(finProductTradeDto);
    }

    /**
     * 组装 产品交易DTO
     *
     * @param entity
     * @return
     */
    private FinProductTradeDto setFinProductTradeDto(PtlSubFundsMange002Dto entity) {
        // 产品交易DTO --- 调用业务组件.【金融产品交易共通.交易产品接口】的参数
        FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
        // List<金融产品交易信息DTO>
        List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<BaseTradeProductDealDto>();

        // 5.1.金融产品汇总交易Entity
        TrdFinProductDealTotal trdFinProductDealTotal;
        // 5.2.金融产品汇总交易扩展表Entity
        PaTrdFinProductDealTotal paTrdFinProductDealTotal;
        // 5.3.金融产品交易Entity
        TrdProductDeal trdProductDeal;
        // 5.4.金融产品交易附属表Entity
        TrdProductDealAdd trdProductDealAdd;
        // 5.5.金融产品交易扩展表Entity
        PaTrdProductDeal paTrdProductDeal;
        // 5.6.组合交易表Entity
        PtlTrade ptlTrade;
        // 5.7.划款指令表Entity
        StlPayOrders stlPayOrders;
        StlPayOrdersDto StlPayOrdersDto;
        // 金融产品交易信息DTO
        BaseTradeProductDealDto baseTradeProductDealDto;

        // 主信息是否已添加到入参dto
        boolean trdFinProductDealTotal_added = false;
        boolean paTrdFinProductDealTotal_added = false;
        //20191206 taoran add 子组合交易类型设值（非写死净值型），与母组合收益类型关联
        //查询母组合收益类型
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("profitId", entity.getPortfolioId());
    	String profitType = restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME 
    			+ "/PtlSubFundsAllocate002/getProfitType", map, String.class);
    	//业务类型
    	String busiType = "";
    	if(DictCodeList.收益类型_货币型.getValue().equals(profitType)) {
    		busiType = DictCodeList.业务类型_货币型TA交易.getValue();
    	}
    	else if(DictCodeList.收益类型_收益型.getValue().equals(profitType)) {
    		busiType = DictCodeList.业务类型_预期收益型TA交易.getValue();
    	}
    	else {
    		busiType = DictCodeList.业务类型_净值型TA交易.getValue();
    	}
        //遍历资金分配明细数据
        for (PtlSubFundsMange002Dto detail : entity.getPortfolioDetailTable()) {
            baseTradeProductDealDto = new BaseTradeProductDealDto();
            if (!trdFinProductDealTotal_added) {
                trdFinProductDealTotal = this.setTrdFinProductDealTotal(entity, busiType);
                baseTradeProductDealDto.setTrdFinProductDealTotal(trdFinProductDealTotal);
                trdFinProductDealTotal_added = true;
            }
            if (!paTrdFinProductDealTotal_added) {
                paTrdFinProductDealTotal = this.setPaTrdFinProductDealTotal(entity);
                baseTradeProductDealDto.setPaTrdFinProductDealTotal(paTrdFinProductDealTotal);
                paTrdFinProductDealTotal_added = true;
            }

            trdProductDeal = this.setTrdProductDeal(entity, detail, busiType, detail.getPortfolioIdSub());
            baseTradeProductDealDto.setTrdProductDeal(trdProductDeal);
            trdProductDealAdd = this.setTrdProductDealAdd(entity, detail);
            baseTradeProductDealDto.setTrdProductDealAdd(trdProductDealAdd);
            paTrdProductDeal = this.setPaTrdProductDeal(entity, detail);
            baseTradeProductDealDto.setPaTrdProductDeal(paTrdProductDeal);
            ptlTrade = this.setPtlTrade(entity, detail, busiType);
            baseTradeProductDealDto.setPtlTrade(Arrays.asList(ptlTrade));
//			stlPayOrders = this.setStlPayOrders(entity,detail);
//			baseTradeProductDealDto.setStlPayOrders(stlPayOrders);
            StlPayOrdersDto = this.setStlPayOrdersDto(entity, detail, trdProductDeal);
            baseTradeProductDealDto.setStlPayOrdersList(Arrays.asList(StlPayOrdersDto));
            baseTradeProductDealDtos.add(baseTradeProductDealDto);
        }
        finProductTradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
        return finProductTradeDto;
    }

    /**
     * 划款指令表Entity
     *
     * @param primary 主信息
     * @param sub     明细信息
     * @return
     */
    private StlPayOrders setStlPayOrders(PtlSubFundsMange002Dto primary, PtlSubFundsMange002Dto sub) {
        StlPayOrders entity = new StlPayOrders();
        // 结算日期 画面.交易日
        entity.setSettleDate(primary.getTradeDate());
        // 结算金额 画面.调配金额
        entity.setSettleAmt(sub.getTradeAmtfp());
        // 投资组合代码 画面.子组合代码
        entity.setPortfolioId(sub.getPortfolioIdSub());
        // 交易编号 金融产品交易Entity.交易编号
        entity.setTradeId(sub.getTradeId());
        return entity;
    }

    /**
     * 划款指令表Entity
     *
     * @param primary        主信息
     * @param sub            明细信息
     * @param trdProductDeal 金融产品交易Entity
     * @return
     */
    private StlPayOrdersDto setStlPayOrdersDto(PtlSubFundsMange002Dto primary, PtlSubFundsMange002Dto sub, TrdProductDeal trdProductDeal) {
        StlPayOrdersDto entity = new StlPayOrdersDto();
        // 结算日期 画面.交易日
        entity.setSettleDate(primary.getTradeDate());
        // 结算金额 画面.调配金额.abs()
        entity.setSettleAmt(sub.getTradeAmtfp().abs());
        // 投资组合代码 画面.子组合代码
        entity.setPortfolioId(sub.getPortfolioIdSub());
        // 交易编号 金融产品交易Entity.交易编号
        entity.setTradeId(sub.getTradeId());
        // 币种
        entity.setCcy(primary.getCcy());
        // 指令编号
        entity.setOrderId(sub.getTradeId());
        // 本方资金帐户（调用划款指令共通组件获取）
        if (bussTrdTransferOrderService != null) {
            // 调用划款指令共通组件
            BussSettleOrderParameterDto orderParameterDto = new BussSettleOrderParameterDto();
            orderParameterDto.setIsTotalTrade(DictCodeList.是否_否.getValue());
            orderParameterDto.setPortfolioId(sub.getPortfolioIdSub());
            orderParameterDto.setDeal(trdProductDeal);
            BussTrdTransferOrderDto resultOrderDto = bussTrdTransferOrderService.generateOrder(orderParameterDto);
            if (resultOrderDto != null) {
                // 获取共通取的托管户
                if (StringUtil.equals(DictCodeList.交易方向_产品申购.getValue(), trdProductDeal.getPs())) {
                    entity.setMyAcctNo(resultOrderDto.getRecAcctNo());
                } else {
                    entity.setMyAcctNo(resultOrderDto.getPayAcctNo());
                }
            }
        }
        return entity;
    }

    /**
     * 组合交易表Entity
     *
     * @param primary 主信息
     * @param sub     明细信息
     * @return
     */
    private PtlTrade setPtlTrade(PtlSubFundsMange002Dto primary, PtlSubFundsMange002Dto sub, String busiType) {
        PtlTrade entity = new PtlTrade();
        // 组合代码 =画面.子组合代码
        entity.setPortfolioId(sub.getPortfolioIdSub());
        // 业务编号= 金融产品交易Entity.业务编号
        entity.setBusiNo(sub.getTradeId());
        // 业务表= 数据字典缓存.生成方式.金融产品表
        entity.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        // 业务明细代码= 金融产品交易Entity.金融产品代码
        entity.setBusiId(primary.getFinprodId());
        // 业务类型= 根据母产品收益类型获取对应的业务类型
        entity.setBusiType(busiType);
        // 实际发生日= 画面.交易日
        entity.setSettleDate(primary.getTradeDate());
        return entity;
    }

    /**
     * 金融产品交易扩展表Entity
     *
     * @param primary 主信息
     * @param sub     明细信息
     * @return
     */
    private PaTrdProductDeal setPaTrdProductDeal(PtlSubFundsMange002Dto primary, PtlSubFundsMange002Dto sub) {
        PaTrdProductDeal entity = new PaTrdProductDeal();
        // 交易编号= 金融产品交易Entity.交易编号
        entity.setTradeId(sub.getTradeId());
        // 平安业务类型= 数据字典缓存.平安业务类型.产品交易资金分配
        entity.setPaBussType(DictCodeListExt1.平安业务类型_产品交易资金分配.getValue());
        return entity;
    }

    /**
     * 金融产品交易附属表Entity
     *
     * @param primary 主信息
     * @param sub     明细信息
     * @return
     */
    private TrdProductDealAdd setTrdProductDealAdd(PtlSubFundsMange002Dto primary, PtlSubFundsMange002Dto sub) {
        TrdProductDealAdd entity = new TrdProductDealAdd();
        // 交易编号 =金融产品交易Entity.交易编号
        entity.setTradeId(sub.getTradeId());
        // 投资组合代码= 画面.子组合代码
        entity.setPortfolioId(sub.getPortfolioIdSub());
        // 生成方式 =数据字典缓存.生成方式.画面录入
        entity.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
        return entity;
    }

    /**
     * 金融产品交易Entity
     *
     * @param primary 主信息
     * @param sub     明细信息
     * @return
     */
    private TrdProductDeal setTrdProductDeal(PtlSubFundsMange002Dto primary, PtlSubFundsMange002Dto sub, String busiType,String finprodId) {
        TrdProductDeal entity = new TrdProductDeal();
        // 交易编号 =业务编号 ※调用2生成的交易编号
        entity.setTradeId(sub.getTradeId());
        // 业务类型 =数据字典缓存.业务类型.净值型TA交易 BUSI_TYPE
        entity.setBusiType(busiType);
        // 金融产品代码= 组合代码
        entity.setFinprodId(finprodId);
        // 金融产品类型=（投管分类）=数据字典缓存.金融产品类型（投管用）数据字典.理财产品
        entity.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        // 金融产品类型=（估值核算） 调用各种业务共通组件.数据字典转换（目标数据代码）
        entity.setFinprodType("");//TODO
        // 分支序号 =系统参数.分支序号固定值（和估值核算保持一致，0）
        entity.setBranch(0L);
        // 交易币种 =画面.币种
        entity.setCcy(primary.getCcy());
        // 交易方向= 数据字典缓存.交易方向.产品申购/产品赎回（为正则为产品申购，为负则为产品赎回）
        BigDecimal tradeAmtfp = sub.getTradeAmtfp() == null ? BigDecimal.ZERO : sub.getTradeAmtfp();
        if (BigDecimal.ZERO.compareTo(tradeAmtfp) > 0) {
            entity.setPs(DictCodeList.交易方向_产品赎回.getValue());
        } else {
            entity.setPs(DictCodeList.交易方向_产品申购.getValue());
        }
        // 交割日= 画面.交易日
        entity.setSettleDate(primary.getTradeDate());
        // 交易份额= 画面.调配金额 (交易方向为产品赎回 则*（-1）)
        entity.setShareAmt(tradeAmtfp.abs());
        // 交易本金= 画面.调配金额  (交易方向为产品赎回 则*（-1）)
        entity.setPrinAmt(tradeAmtfp.abs());
        // 交易金额 =画面.调配金额  (交易方向为产品赎回 则*（-1）)
        entity.setTradeAmt(tradeAmtfp.abs());
        // 汇总交易编号= 汇总表.汇总交易编号
        entity.setTotleTradeId(primary.getTotleTradeId());
        //交易状态 add by yanya on 20191119
        entity.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        //交易日期 add by yanya on 20191119
        entity.setTradeDate(primary.getTradeDate());
        entity.setIsCancel(DictCodeList.是否_否.getValue());
        return entity;
    }

    /**
     * 金融产品汇总交易扩展表Entity
     *
     * @param primary 主信息
     * @return
     */
    private PaTrdFinProductDealTotal setPaTrdFinProductDealTotal(PtlSubFundsMange002Dto primary) {
        PaTrdFinProductDealTotal entity = new PaTrdFinProductDealTotal();
        // 汇总交易编号= 金融产品汇总交易Entity.汇总交易编号
        entity.setTotleTradeId(primary.getTotleTradeId());
        // 平安业务类型= 数据字典缓存.平安业务类型.产品交易资金分配 PINAN_BUSINESS_TYPE
        entity.setPaBussType(DictCodeListExt1.平安业务类型_产品交易资金分配.getValue());
        return entity;
    }

    /**
     * 金融产品汇总交易Entity
     *
     * @param primary 主信息
     * @return
     */
    private TrdFinProductDealTotal setTrdFinProductDealTotal(PtlSubFundsMange002Dto primary, String busiType) {
        TrdFinProductDealTotal entity = new TrdFinProductDealTotal();
        // 汇总交易编号=业务编号-※2生成的交易编号
        entity.setTotleTradeId(primary.getTotleTradeId());
        // 业务类型=根据母组合收益类型
        entity.setBusiType(busiType);
        // 金融产品代码 =业务编号 ※2生成的交易编号
        entity.setFinprodId(primary.getFinprodId());
        // 金融产品类型（投管分类） = 数据字典缓存.金融产品类型（投管用）数据字典.理财产品
        entity.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        // 金融产品类型（估值核算） 调用各种业务共通组件.数据字典转换（目标数据代码）
        entity.setFinprodType("");//TODO
        // 分支序号 =系统参数.分支序号固定值（和估值核算保持一致，0）
        entity.setBranch(0L);
        // 投资组合代码= 画面.组合代码
        entity.setPortfolioId(primary.getPortfolioId());
        // 交易币种= 画面.币种
        entity.setCcy(primary.getCcy());
        // 交易方向 =数据字典缓存.交易方向.资金调配
        entity.setPs(DictCodeList.交易方向_资金调配.getValue());
        // 交割日= 画面.交易日
        entity.setSettleDate(primary.getTradeDate());
        // 交易本金= 画面.交易金额
        entity.setPrinAmt(primary.getTradeAmt());
        // 交易金额= 画面.交易金额
        entity.setTradeAmt(primary.getTradeAmt());
        // 备注 =画面.备注
        entity.setRemark(primary.getRemark());
        // 是否已撤销，默认“否”，在交易发生撤销时更新成“是”
        entity.setIsCancel(DictCodeList.是否_否.getValue());
        return entity;
    }
}
