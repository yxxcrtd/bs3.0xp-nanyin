package com.joyintech.tams.invest.trade.bondmarkettrade.service;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.group.BasePtlSecPositionInfoService;
import com.joyintech.tams.invest.common.component.base.trade.BaseTradeProductDealService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.etr.EtrSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTradeInfoDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.common.trade.service.TrdCommonBaseService;
import com.joyintech.tams.invest.common.trade.service.impl.etrcash.EtrCommonCashPositionDealService;
import com.joyintech.tams.invest.trade.bondmarkettrade.dto.TrdBondMarketTradeDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 债券上市流通查询画面Service
 *
 * @author etliu
 * @version 1.0 2018年11月29日
 * @see com.joyintech.tams.invest.trade.bondmarkettrade.service.TrdBondMarketTrade001Service
 * @since 1.0
 */
@Service
public class TrdBondMarketTrade002Service extends BaseService<TrdBondMarketTradeDto> implements BaseProcessService<TrdBondMarketTradeDto> {

    @Autowired
    private WflTrdFinProductDealTotalMapper wflTrdFinProductDealTotalMapper;

    /**
     * BaseTradeProductDealCommService
     */
    @Autowired(required = false)
    private BaseTradeProductDealCommService baseTradeProductDealCommService;

    @Autowired(required = false)
    private BasePtlSecPositionInfoService basePtlSecPositionInfoService;

    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

    @Autowired
    private BaseTradeProductDealService baseTradeProductDealService;

    @Autowired
    private WflPaTrdFinDealTotalMapper wflPaTrdFinDealTotalMapper;

    @Autowired
    private WflTrdDealTotalAddMapper wflTrdDealTotalAddMapper;

    @Autowired
    private WflTrdProductDealMapper wflTrdProductDealMapper;

    @Autowired
    private WflPaTrdProductDealMapper wflPaTrdProductDealMapper;
    
    @Autowired
    private WflTrdProductDealAddMapper wflTrdProductDealAddMapper;

    @Autowired
    private WflStlPayOrdersMapper wflStlPayOrdersMapper;
    
    /**
     * 共通Service
     */
    @Autowired(required = false)
    private EtrCommonCashPositionDealService etrCommonCashPositionDealService;
    /**
     * 金融产品交易共通业务
     */
    @Autowired
    private TrdCommonBaseService trdCommonBaseService;

//    @DataSource(DataSourceType.MASERT)
//    @DTXTransaction
//    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void doComplete(TrdBondMarketTradeDto entity, OperationType operationType) {
        
        if (!operationType.equals(OperationType.REVOKE) && !operationType.equals(OperationType.DELETE)) {
            FinProductTradeDto finProductTradeDto = entity.getFinProductTradeDto();
            //7.1登录各业务表，包括金融产品汇总交易、金融产品实物汇总交易表
            finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_有效.getValue());
            setWorkflowId(entity);
            
            //7.2删除原业务流程表中的交易记录
            trdCommonBaseService.deleteByWorkflowId(entity.getWorkflowId());
            
            //7.3.a、处理各组合指令持仓数据
            List<EtrSecPositionFlowDto> etrSecList = new ArrayList<>();
            List<PtlSecPositionFlowDto> ptlSecList = new ArrayList<>();
            List<EtrSecPositionFlowDto> oldEtrSecList = new ArrayList<>();
            List<PtlSecPositionFlowDto> oldPtlSecList = new ArrayList<>();
            Map<String,Object> paraMap = new HashMap<>();
            paraMap.put("finprodId",entity.getFinprodId());
            paraMap.put("tradeDate", DateUtil.format(entity.getTradeDate()));
            Map<String,Object> positonMap = restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondMarketTrade/getPosition", paraMap,
                    Map.class);
            List<Map<String,Object>> ptlSecPositionMap = (List<Map<String, Object>>) positonMap.get("ptlSecPositions");
            List<PtlSecPosition> ptlSecPositions = new ArrayList<>();
            for (Map<String, Object> stringObjectMap : ptlSecPositionMap) {
                ptlSecPositions.add(BeanUtil.mapToObject(stringObjectMap,PtlSecPosition.class));
            }
            for (PtlSecPosition ptlSecPosition : ptlSecPositions){
                PtlSecPositionFlowDto ptlSecPositionFlowDto = new PtlSecPositionFlowDto();
                PtlSecPositionFlowDto ptlSecPositionFlowDtoNe = new PtlSecPositionFlowDto();
                ptlSecPositionFlowDto.setPortfolioId(ptlSecPosition.getPortfolioId());
                ptlSecPositionFlowDto.setSecAcctId(ptlSecPosition.getSecAcctId());
                ptlSecPositionFlowDto.setFinprodId(DictCodeListExt1.金融产品类型_投管用_债券.getValue() + "_" + entity.getAppearMarketId());
                ptlSecPositionFlowDto.setBranch(ptlSecPosition.getBranch());
                ptlSecPositionFlowDto.setInvAim(ptlSecPosition.getInvAim());
                ptlSecPositionFlowDto.setHodingType(ptlSecPosition.getHodingType());
                ptlSecPositionFlowDto.setCdate(entity.getTradeDate());
                ptlSecPositionFlowDto.setCcy(ptlSecPosition.getCcy());
                ptlSecPositionFlowDto.setAmount(ptlSecPosition.getAmount());
                ptlSecPositionFlowDto.setpFinprodId(ptlSecPosition.getpFinprodId());
                ptlSecPositionFlowDto.setTradeId(genTradeId());
                ptlSecPositionFlowDto.setSeqno(IDGenerator.generate()); 
                ptlSecList.add(ptlSecPositionFlowDto);
                BeanUtil.copyPropertiesIgnoreNull(ptlSecPositionFlowDtoNe,ptlSecPositionFlowDto);
                ptlSecPositionFlowDtoNe.setFinprodId(entity.getFinprodId());
                ptlSecPositionFlowDtoNe.setAmount(ptlSecPositionFlowDtoNe.getAmount().negate());
                ptlSecPositionFlowDtoNe.setSeqno(IDGenerator.generate());
                oldPtlSecList.add(ptlSecPositionFlowDtoNe);
            }


            List<Map<String,Object>> etrSecPositionMap = (List<Map<String, Object>>) positonMap.get("etrSecPositions");
            List<EtrSecPosition> etrSecPositions = new ArrayList<>();
            for (Map<String, Object> stringObjectMap : etrSecPositionMap) {
                etrSecPositions.add(BeanUtil.mapToObject(stringObjectMap,EtrSecPosition.class));
            }
            for (EtrSecPosition etrSecPosition : etrSecPositions){
                EtrSecPositionFlowDto etrSecPositionFlowDto = new EtrSecPositionFlowDto();
                EtrSecPositionFlowDto etrSecPositionFlowDtoNe = new EtrSecPositionFlowDto();
                etrSecPositionFlowDto.setPortfolioId(etrSecPosition.getPortfolioId());
                etrSecPositionFlowDto.setSecAcctId(etrSecPosition.getSecAcctId());
                etrSecPositionFlowDto.setFinprodId(DictCodeListExt1.金融产品类型_投管用_债券.getValue() + "_" + entity.getAppearMarketId());
                etrSecPositionFlowDto.setBranch(etrSecPosition.getBranch());
                etrSecPositionFlowDto.setInvAim(etrSecPosition.getInvAim());
                etrSecPositionFlowDto.setHodingType(etrSecPosition.getHodingType());
                etrSecPositionFlowDto.setCdate(entity.getTradeDate());
                etrSecPositionFlowDto.setCcy(etrSecPosition.getCcy());
                etrSecPositionFlowDto.setAmount(etrSecPosition.getAmount());
                etrSecPositionFlowDto.setpFinprodId(etrSecPosition.getpFinprodId());
                etrSecPositionFlowDto.setTradeId(entity.getTotleTradeId());
                etrSecList.add(etrSecPositionFlowDto);
                BeanUtil.copyPropertiesIgnoreNull(etrSecPositionFlowDtoNe,etrSecPositionFlowDto);
                etrSecPositionFlowDtoNe.setFinprodId(entity.getFinprodId());
                etrSecPositionFlowDtoNe.setAmount(etrSecPositionFlowDtoNe.getAmount().negate());
                oldEtrSecList.add(etrSecPositionFlowDtoNe);
            }
            
            //7.3.1根据上述返回结果组装2条持仓流水，数量、金融产品代码重新赋值，其余复制持仓数据
            List<EtrSecPositionFlowDto> etrSecListAll = new ArrayList<>();
            List<PtlSecPositionFlowDto> pltSecListAll = new ArrayList<>();
            
            etrSecListAll.addAll(etrSecList);
            etrSecListAll.addAll(oldEtrSecList);
            pltSecListAll.addAll(ptlSecList);
            pltSecListAll.addAll(oldPtlSecList);
            
            for (EtrSecPositionFlowDto etrSecPositionFlowDto :etrSecListAll){
                etrCommonCashPositionDealService.dealCommandPosition(etrSecPositionFlowDto);
            }
            for (PtlSecPositionFlowDto ptlSecPositionFlowDto: pltSecListAll){
                basePtlSecPositionInfoService.inertSecPositionFlow(ptlSecPositionFlowDto);
            }

            //分支0
            String branch = CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE);
            Long branchLong = Long.parseLong(branch);

            //7.3.2根据上述返回结果组装交易数据
            List<BaseTradeInfoDto> tradeInfoDtoList = new ArrayList<>();
            List<AllTradeCommonInputDto> allTradeCommonInputDtos = new ArrayList<>();
            for (PtlSecPositionFlowDto ptlSecPositionFlowDto: oldPtlSecList){
                BaseTradeInfoDto baseTradeInfoDto = new BaseTradeInfoDto();

                TrdProductDeal trdProductDeal = new TrdProductDeal();
                trdProductDeal.setTotleTradeId(entity.getTotleTradeId());
                trdProductDeal.setTradeId(ptlSecPositionFlowDto.getTradeId());
                trdProductDeal.setPs(DictCodeList.交易方向_上市流通.getValue());
                trdProductDeal.setBusiType(entity.getBusiType());
                trdProductDeal.setFinprodId(DictCodeListExt1.金融产品类型_投管用_债券.getValue() + "_" + entity.getAppearMarketId());
                trdProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
                trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
                trdProductDeal.setBranch(branchLong);
                trdProductDeal.setTradeDate(entity.getTradeDate());
                trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
                trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
                trdProductDeal.setInvAim(ptlSecPositionFlowDto.getInvAim());
                trdProductDeal.setCcy(DictCodeList.币种_人民币_CNY.getValue());
                baseTradeInfoDto.setTrdProductDeal(trdProductDeal);

                TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
                trdProductDealAdd.setTradeId(ptlSecPositionFlowDto.getTradeId());
                trdProductDealAdd.setPortfolioId(ptlSecPositionFlowDto.getPortfolioId());
                trdProductDealAdd.setrFinprodId(entity.getFinprodId());
                //根据生成方式设置值
                trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
                baseTradeInfoDto.setTrdProductDealAdd(trdProductDealAdd);

                TrdFinProductBarter trdFinProductBarter = new TrdFinProductBarter();
                trdFinProductBarter.setTradeId(ptlSecPositionFlowDto.getTradeId());
                trdFinProductBarter.setFinprodId(entity.getFinprodId());
                trdFinProductBarter.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
                trdFinProductBarter.setBranch(branchLong);
                trdFinProductBarter.setInvAim(ptlSecPositionFlowDto.getInvAim());
                baseTradeInfoDto.setTrdFinProductBarter(trdFinProductBarter);
                tradeInfoDtoList.add(baseTradeInfoDto);
                AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                inputDto.setProdid(trdProductDealAdd.getPortfolioId());
                inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
                inputDto.setTrdProductDeal(trdProductDeal);
                inputDto.setTrdProductDealAdd(trdProductDealAdd);
//                inputDto.setTrdFinProductBarter(trdFinProductBarter);
                allTradeCommonInputDtos.add(inputDto);
            }

            restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondMarketTrade/doInsert", tradeInfoDtoList,
                    List.class);

            restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondMarketTrade/doInsertTotal", entity,
                    TrdBondMarketTradeDto.class);

            //交易生成后调用共通生成入账信息
            for (AllTradeCommonInputDto allTradeCommonInputDto : allTradeCommonInputDtos) {
                AllTradeCommonUtil.handleAfterTradeComplete(allTradeCommonInputDto);
            }
            
            //债券如果没有加券，则自动加券
            if(StringUtil.equals("false",entity.getIsAdded())){
                restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondMarketTrade/autoAddBond", entity,
                        String.class);
            }
        }else{
            this.doCompleteRevoke(entity);    
        }
    }

//    @DataSource(DataSourceType.MASERT)
//    @DTXTransaction
//    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void doInsert(TrdBondMarketTradeDto entity, OperationType operationType){
        if ("save".equals(entity.getOperateType())) {
            return;
        }
        // 新建交易
        if (!operationType.equals(OperationType.REVOKE) && !operationType.equals(OperationType.DELETE)) {
            setWorkflowId(entity);
            this.handleBussFlowData(entity);
        }
        // 撤销交易
        else {
            this.doRevokeInsert(entity);
        } 
    }

    /**
     * 由画面输入的参数dto，构筑整个需要反映到数据库的实体
     *
     * @param dto 画面输入的参数dto
     * @see
     * @since 1.0
     */
    public void setEntity(TrdBondMarketTradeDto dto) {
        //分支0
        String branch = CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE);
        Long branchLong = Long.parseLong(branch);

        //构造金融产品实物汇总交易DTO
        TrdFinProductBarterTotal trdFinProductBarterTotal = new TrdFinProductBarterTotal();
        trdFinProductBarterTotal.setTradeId(dto.getTotleTradeId());
        trdFinProductBarterTotal.setFinprodId(dto.getFinprodId());
        trdFinProductBarterTotal.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
        trdFinProductBarterTotal.setBranch(branchLong);
        dto.setTrdFinProductBarterTotal(trdFinProductBarterTotal);

        //构造金融产品汇总交易DTO
        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        trdFinProductDealTotal.setTotleTradeId(dto.getTotleTradeId());
        trdFinProductDealTotal.setBusiType(dto.getBusiType());
        trdFinProductDealTotal.setFinprodId(DictCodeListExt1.金融产品类型_投管用_债券.getValue() + "_" + dto.getAppearMarketId());
        trdFinProductDealTotal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        trdFinProductDealTotal.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
        trdFinProductDealTotal.setBranch(branchLong);
        trdFinProductDealTotal.setTradeDate(dto.getTradeDate());
        trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
        trdFinProductDealTotal.setPs(DictCodeList.交易方向_上市流通.getValue());
        trdFinProductDealTotal.setCcy(DictCodeList.币种_人民币_CNY.getValue());
        //构造金融产品交易信息DTO
        BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
        baseTradeProductDealDto.setTrdFinProductDealTotal(trdFinProductDealTotal);
        
        //构造产品交易DTO
        FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
        finProductTradeDto.setBaseTradeProductDealDtos(Arrays.asList(baseTradeProductDealDto));
        
        dto.setFinProductTradeDto(finProductTradeDto);

        setWorkflowId(dto);
    }

    private void setWorkflowId(TrdBondMarketTradeDto entity) {
        entity.getFinProductTradeDto().setWorkflowId(entity.getWorkflowId());
        
        entity.getFinProductTradeDto().getBaseTradeProductDealDtos().get(0).getTrdFinProductDealTotal().setWorkflowId(entity.getWorkflowId());
        
        entity.getTrdFinProductBarterTotal().setWorkflowId(entity.getWorkflowId());
    }

    /**
     * 提交时登录业务流程表
     *
     * @param entity 画面参数dto
     * @see
     * @since 1.0
     */
    private void handleBussFlowData(TrdBondMarketTradeDto entity) {
        if (!StringUtil.equals("submit", entity.getOperateType())) {
            return;
        }
        WflTrdFinProductDealTotal wflTrdFinProductDealTotal = new WflTrdFinProductDealTotal();
        BeanUtil.copyPropertiesIgnoreNull(wflTrdFinProductDealTotal, entity.getFinProductTradeDto().getBaseTradeProductDealDtos().get(0).getTrdFinProductDealTotal());
        wflTrdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        wflTrdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
        wflTrdFinProductDealTotal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
        wflTrdFinProductDealTotalMapper.insertSelective(wflTrdFinProductDealTotal);
    }

    public String genTradeId() {
        BussinessCodeParameter codeParameter = new BussinessCodeParameter();
        codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_债券买入);
        return baseGetBusinessId.buildBussinessCode(codeParameter);
    }

    /**
     * 查询上市代码是否有其他流程中数据
     *
     * @param dto 画面参数dto
     * @see
     * @since 1.0
     */
    public int selectIsInWorkflow(TrdBondMarketTradeDto dto) {
           return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondMarketTrade/selectIsInWorkflow", dto,
                    Integer.class);
    }

    public int selectDealNum(TrdBondMarketTradeDto dto) {
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondMarketTrade/selectDealNum", dto,
                Integer.class);
    }

    /**
     * 撤销doComplete
     *
     * @param entity 参数
     * @see
     * @since 1.0
     */
    private void doCompleteRevoke(TrdBondMarketTradeDto entity) {
        Map<String, Object> params = new HashMap<>();
        params.put("workflowId", entity.getWorkflowId());
        String bizCode = entity.getTotleTradeId();
        if (bizCode.lastIndexOf('R') == bizCode.length() - 1) {
            bizCode = bizCode.substring(0, bizCode.length() - 1);
        }
        params.put("bizCode", bizCode);
        params.put("updateUser", SecurityUtil.getLoginUser().getUserName());

        //撤销后处理
        restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondMarketTrade/doCompleteRevoke", entity,
                TrdBondMarketTradeDto.class);
        
        restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/common/trdcommonbase/doRevokeComplete?", params,
                String.class);

        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doDelete(String workflowId) {
        baseTradeProductDealCommService.delete(workflowId);
    }

    /**
     * 撤销交易doInsert
     *
     * @param entity 参数entity
     * @see
     * @since 1.0
     */
    private void doRevokeInsert(TrdBondMarketTradeDto entity) {
        Map<String, Object> params = new HashMap<>();
        if (entity.getTotleTradeId().lastIndexOf('R') == entity.getTotleTradeId().length() - 1) {
            entity.setTotleTradeId(entity.getTotleTradeId().substring(0, entity.getTotleTradeId().length() - 1));
        }
        params.put("bizCode", entity.getTotleTradeId());
        params.put("workflowId", entity.getWorkflowId());
        params.put("workflowStatus", DictCodeList.数据状态_删除中.getValue());
        params.put("user", SecurityUtil.getLoginUser().getUserName());
        params.put("dept", SecurityUtil.getLoginUser().getDeptCode());
        params.put("revokeReason", entity.getRevokeReason());
        // 调用基础组合.【金融产品交易组件.查询金融产品交易信息】获取原交易信息
        restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/common/trdcommonbase/doRevokeInsertTotle", params,
                String.class);
    }

    /**
     * 删除业务流程数据
     *
     * @param workflowId 工作流代码
     * @see
     * @since 1.0
     */
    private void deleteBussFlowData(String workflowId) {
        wflTrdFinProductDealTotalMapper.deleteByWorkflowId(workflowId);
        wflPaTrdFinDealTotalMapper.deleteByWorkflowId(workflowId);
        wflTrdDealTotalAddMapper.deleteByWorkflowId(workflowId);
        wflTrdProductDealMapper.deleteByWorkflowId(workflowId);
        wflPaTrdProductDealMapper.deleteByWorkflowId(workflowId);
        wflTrdProductDealAddMapper.deleteByWorkflowId(workflowId);
        wflStlPayOrdersMapper.deleteByWorkflowId(workflowId);
    }

    /**
     * {@inheritDoc}
     */
//    @DataSource(DataSourceType.MASERT)
//    @DTXTransaction
//    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void doUpdate(TrdBondMarketTradeDto entity) {
        if (!StringUtil.equals("revoke", entity.getOperateType()) && !StringUtil.equals("delete", entity.getOperateType())) {
            // 先删除流程中数据
            this.deleteBussFlowData(entity.getWorkflowId());
            setWorkflowId(entity);
            // 重新insert
            this.handleBussFlowData(entity);
        }
    }

    /**
     * 根据汇总交易编号查询交易数据
     *
     * @param totalTradeId 汇总交易编号
     * @see
     * @since 1.0
     */
    public TrdBondMarketTradeDto selectByTotalTradeId(String totalTradeId) {
        // 调用基础组合.【金融产品交易组件.查询金融产品交易信息】获取原交易信息
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondMarketTrade/selectByTotalTradeId", totalTradeId,
                TrdBondMarketTradeDto.class);    
    }

    /**
     * 查询是否存在当天交易数据
     *
     * @param dto 
     * @see
     * @since 1.0
     */
    public int selectDealNums(TrdBondMarketTradeDto dto) {
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondMarketTrade/selectDealNums", dto,
                Integer.class);
    }
}

