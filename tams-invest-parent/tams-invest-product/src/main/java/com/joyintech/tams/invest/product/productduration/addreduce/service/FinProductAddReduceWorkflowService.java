package com.joyintech.tams.invest.product.productduration.addreduce.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.trade.impl.BussTrdTransferOrderImplService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.product.addreduce.ProductTradeInfoDto;
import com.joyintech.tams.invest.common.dto.product.addreduce.ReduceInfoDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.BussTrdPositionDto;
import com.joyintech.tams.invest.common.trade.service.TradeProductDealCommService;
import com.joyintech.tams.invest.product.productduration.addreduce.dto.FinProductAddReduce002Dto;
import com.joyintech.tams.valuation.common.service.BooksetCommonService;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: pingan-parent
 * @Package com.joyintech.tams.invest.product.productduration.addreduce.service
 * @Description: 强增强减service
 * @date Date : 2018年09月07日 9:15
 */
@Service
public class FinProductAddReduceWorkflowService extends BaseService<FinProductAddReduce002Dto> implements BaseProcessService<FinProductAddReduce002Dto> {

    @Autowired
    private BussTrdTransferOrderImplService bussTrdTransferOrderImplService;

    @Autowired
    private BaseGetBusinessId baseGetBusinessIdImpl;

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
    private TradeProductDealCommService baseTradeProductDealCommService;
    
    @Autowired
    private TrdAsynHandleStatusMapper trdAsynHandleStatusMapper;
    
    @Autowired
    private BooksetCommonService booksetCommonService;

    @Override
    public void doComplete(FinProductAddReduce002Dto entity, OperationType type) {
        if (!type.equals(OperationType.REVOKE) && !type.equals(OperationType.DELETE)) {
            // 插入业务表
            saveDataWhenCompleteWorkflow(entity, false);
            // 插入报送表
            操作报送表(entity, false);
        }
        else {
            // 插入业务表 撤销数据
            saveDataWhenCompleteWorkflow(entity, true);
            // 删除报送表
            操作报送表(entity, true);
        }

        // 删除工作流表
        baseTradeProductDealCommService.deleteWflData(entity.getWorkflowId());
    }

    @Override
    public void doDelete(String workflowId) {
        // 删除工作流表
        baseTradeProductDealCommService.delete(workflowId);
    }

    private void 操作报送表(FinProductAddReduce002Dto entity, boolean isRevoke) {
        RepDuration repDuration = new RepDuration();
        repDuration.setRepFinprodId(entity.getFinprodId());
        repDuration.setRepDurationRegDate(DateUtil.parse(entity.getTradeDate()));
        repDuration = repDurationMapper.selectByPrimaryKey(repDuration);
        if (repDuration == null) {
            repDuration = gen存续期报送(entity);
        }
        else {
            // 汇总更新

        }
    }

    private RepDuration gen存续期报送(FinProductAddReduce002Dto entity) {

        RepDuration repDuration = new RepDuration();
        repDuration.setRepFinprodId(entity.getFinprodId());
        repDuration.setRepDurationRegDate(DateUtil.parse(entity.getTradeDate()));
        repDuration.setRepIssuePartyId(CacheUtil.getSysParam(BussinessConstant.ISSUE_PARTY_ID));
        //repDuration.setRepIssuePartyName(CacheUtil.getSysParam(BussinessConstant.ISSUE_PARTY_NAME));
        //多级法人改造start add by hxy 20200620
        if(MultilevelDataAuthorityUtil.isEnabled()) {
	        	 //联社模式
	        	 //根据选择的账套组或者账套，查询出它们的所属机构
	        	String booksetOrg=entity.getFinprodId();
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
        
        repDuration.setRepFinprodRegId(entity.getProdRegistCode());
        repDuration.setRepBankProdCode(entity.getFinprodId());

        return repDuration;
    }

    @Override
    public void doInsert(FinProductAddReduce002Dto entity, OperationType type) {
    	//当保存时产品流程相关表中未存流程id时赋值
        if (BussinessConstant.SUBMIT.equals(entity.getOperateType())||type.equals(OperationType.NEW)) {
            if (entity.getProductTradeInfoDto() != null) {
                if (entity.getProductTradeInfoDto().getTrdFinProductDealTotal() != null
                    && entity.getProductTradeInfoDto().getTrdFinProductDealTotal().getWorkflowId() == null) {
                    entity.getProductTradeInfoDto().getTrdFinProductDealTotal().setWorkflowId(entity.getWorkflowId());
                }
                if (entity.getProductTradeInfoDto().getPaTrdFinProductDealTotal() != null
                    && entity.getProductTradeInfoDto().getPaTrdFinProductDealTotal().getWorkflowId() == null) {
                    entity.getProductTradeInfoDto().getPaTrdFinProductDealTotal().setWorkflowId(entity.getWorkflowId());
                }
                if (entity.getProductTradeInfoDto().getTrdProductDeals() != null) {
                    entity.getProductTradeInfoDto().getTrdProductDeals().forEach(trdProductDeal -> {
                        trdProductDeal.setWorkflowId(entity.getWorkflowId());
                    });
                }
                if (entity.getProductTradeInfoDto().getTrdProductDealAdds() != null) {
                    entity.getProductTradeInfoDto().getTrdProductDealAdds().forEach(trdProductDeal -> {
                        trdProductDeal.setWorkflowId(entity.getWorkflowId());
                    });
                }
                if (entity.getProductTradeInfoDto().getPaTrdProductDeals() != null) {
                    entity.getProductTradeInfoDto().getPaTrdProductDeals().forEach(trdProductDeal -> {
                        trdProductDeal.setWorkflowId(entity.getWorkflowId());
                    });
                }
                if (entity.getProductTradeInfoDto().getStlPayOrders() != null) {
                    entity.getProductTradeInfoDto().getStlPayOrders().forEach(trdProductDeal -> {
                        trdProductDeal.setWorkflowId(entity.getWorkflowId());
                    });
                }
            }
        }
        if (!type.equals(OperationType.REVOKE) && !type.equals(OperationType.DELETE)) {
            saveDataWhenStartWorkflow(entity, false);
        }
        else if (type == OperationType.REVOKE) {
            doRevokeInsert(entity);
        }
    }

    @Override
    public void doUpdate(FinProductAddReduce002Dto entity) {
        baseTradeProductDealCommService.deleteWflData(entity.getWorkflowId());
        entity.setProductTradeInfoDto(genFinProductTradeDto(entity));
        ProcessMark newEntity;
        try {
            newEntity = WorkFlowUtil.getBusinessObjectFromRequest(FinProductAddReduce002Dto.class, true);
            // 工作流ID
            newEntity.setWorkflowId(entity.getWorkflowId());
            newEntity.setStatus(entity.getStatus());
            newEntity.setBusinessData(JsonUtils.object2Json(entity));
            // 更新业务表数据
            // 操作留痕表
            WorkflowContext.getProcessMarkService().insertIntoProcessMark(newEntity);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        saveDataWhenStartWorkflow(entity, false);
    }

    private void doRevokeInsert(FinProductAddReduce002Dto entity) {
        entity.setProductTradeInfoDto(findAllTrades(entity.getBusiCode()));
        saveDataWhenStartWorkflow(entity, true);
    }

    public ProductTradeInfoDto findAllTrades(String totalTradeId) {
        // 调用基础组合.【金融产品交易组件.查询金融产品交易信息】获取原交易信息
        // 一体化对应etliu start
        return restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductAddReduce/selectProductTradeInfo?totalTradeId=" + totalTradeId,
            totalTradeId, ProductTradeInfoDto.class);
        // 一体化对应etliu end
    }

    private void saveDataWhenStartWorkflow(FinProductAddReduce002Dto entity, boolean isRevoke) {
        // 插入Entity到对应的业务流程DB中
        ProductTradeInfoDto trade = entity.getProductTradeInfoDto();
        WflTrdFinProductDealTotal wflTrdFinProductDealTotal = null;
        if (trade.getTrdFinProductDealTotal() != null) {
            wflTrdFinProductDealTotal = new WflTrdFinProductDealTotal();
            BeanUtil.copyPropertiesIgnoreNull(wflTrdFinProductDealTotal, trade.getTrdFinProductDealTotal());
            if (isRevoke) {
                // 交易状态=“撤销”
                wflTrdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
                // 原交易编号=金融产品交易信息DTO.金融产品交易Entity.交易编号
                wflTrdFinProductDealTotal.setpTradeId(wflTrdFinProductDealTotal.getTotleTradeId());
                // 交易编号=金融产品交易信息DTO.金融产品交易Entity.交易编号+“R”
                wflTrdFinProductDealTotal.setTotleTradeId(
                    wflTrdFinProductDealTotal.getTotleTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                wflTrdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
            }
            else {
                wflTrdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
            }
            wflTrdFinProductDealTotal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
        }

        WflPaTrdFinDealTotal paTrdFinProductDealTotal = null;
        if (trade.getPaTrdFinProductDealTotal() != null) {
            paTrdFinProductDealTotal = new WflPaTrdFinDealTotal();
            BeanUtil.copyPropertiesIgnoreNull(paTrdFinProductDealTotal, trade.getPaTrdFinProductDealTotal());
            if (isRevoke) {
                // 交易编号=金融产品交易信息DTO.金融产品交易Entity.交易编号+“R”
                paTrdFinProductDealTotal.setTotleTradeId(
                    paTrdFinProductDealTotal.getTotleTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                paTrdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
            }
            paTrdFinProductDealTotal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
        }

        List<WflTrdProductDeal> wflTrdProductDeals = new ArrayList<>();
        if (trade.getTrdProductDeals() != null) {
            trade.getTrdProductDeals().forEach(trdProductDeal -> {
                WflTrdProductDeal wflTrdProductDeal = new WflTrdProductDeal();
                BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDeal, trdProductDeal);
                if (isRevoke) {
                    // 交易状态=“撤销”
                    wflTrdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
                    // 原交易编号=金融产品交易信息DTO.金融产品交易Entity.交易编号
                    wflTrdProductDeal.setpTradeId(wflTrdProductDeal.getTradeId());
                    // 交易编号=金融产品交易信息DTO.金融产品交易Entity.交易编号+“R”
                    wflTrdProductDeal.setTotleTradeId(
                        wflTrdProductDeal.getTotleTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                    wflTrdProductDeal.setTradeId(
                        wflTrdProductDeal.getTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                    // 撤销说明=画面.撤销说明
                    wflTrdProductDeal.setCancelRemark(entity.getRevokeReason());
                }
                else {
                    wflTrdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
                }
                wflTrdProductDeal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                wflTrdProductDeals.add(wflTrdProductDeal);
            });
        }
        List<WflTrdProductDealAdd> wflTrdProductDealAdds = new ArrayList<>();
        if (trade.getTrdProductDealAdds() != null) {
            trade.getTrdProductDealAdds().forEach(trdProductDeal -> {
                WflTrdProductDealAdd wflTrdProductDealAdd = new WflTrdProductDealAdd();
                BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDealAdd, trdProductDeal);
                if (isRevoke) {
                    wflTrdProductDealAdd.setTradeId(
                        wflTrdProductDealAdd.getTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                }
                wflTrdProductDealAdd.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                wflTrdProductDealAdds.add(wflTrdProductDealAdd);
            });
        }
        List<WflPaTrdProductDeal> wflPaTrdProductDeals = new ArrayList<>();
        if (trade.getPaTrdProductDeals() != null) {
            trade.getPaTrdProductDeals().forEach(trdProductDeal -> {
                WflPaTrdProductDeal wflPaTrdProductDeal = new WflPaTrdProductDeal();
                BeanUtil.copyPropertiesIgnoreNull(wflPaTrdProductDeal, trdProductDeal);
                if (isRevoke) {
                    wflPaTrdProductDeal.setTradeId(
                        wflPaTrdProductDeal.getTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                }
                wflPaTrdProductDeal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                wflPaTrdProductDeals.add(wflPaTrdProductDeal);
            });
        }
        List<WflStlPayOrders> wflStlPayOrders = new ArrayList<>();
        if (trade.getStlPayOrders() != null) {
            trade.getStlPayOrders().forEach(trdProductDeal -> {
                WflStlPayOrders wflStlPayOrders1 = new WflStlPayOrders();
                BeanUtil.copyPropertiesIgnoreNull(wflStlPayOrders1, trdProductDeal);
                if (isRevoke) {
                    wflStlPayOrders1.setTradeId(
                        wflStlPayOrders1.getTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                }
                wflStlPayOrders1.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                wflStlPayOrders.add(wflStlPayOrders1);
            });
        }

        if (wflTrdFinProductDealTotal != null) {
            wflTrdFinProductDealTotalMapper.insertSelective(wflTrdFinProductDealTotal);
        }
        if (paTrdFinProductDealTotal != null) {
            wflPaTrdFinDealTotalMapper.insertSelective(paTrdFinProductDealTotal);
        }
        wflTrdProductDeals.forEach(wflTrdProductDeal -> wflTrdProductDealMapper.insertSelective(wflTrdProductDeal));
        wflTrdProductDealAdds.forEach(wflTrdProductDeal -> wflTrdProductDealAddMapper.insertSelective(wflTrdProductDeal));
        wflPaTrdProductDeals.forEach(wflTrdProductDeal -> wflPaTrdProductDealMapper.insertSelective(wflTrdProductDeal));
        wflStlPayOrders.forEach(wflTrdProductDeal -> wflStlPayOrdersMapper.insertSelective(wflTrdProductDeal));

        // 调用业务组件.【金融产品交易共通.交易产品接口】,写入[可用]的组合持仓流水及现金流水
//        BussTrdPositionDto bussTrdPositionDto = new BussTrdPositionDto();
//        bussTrdPositionDto.setPortfolioId(entity.getPtlId());
//        bussTrdPositionDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
//        bussTrdPositionDto.setTrdProductDealList(trade.getTrdProductDeals());
//        bussTrdPositionDto.setStlPayOrders(trade.getStlPayOrders());
//        baseTradeProductDealCommService.trdProductInsertPosition(bussTrdPositionDto);

        // PtlPositionHoldDto ptlPositionHoldDto = new PtlPositionHoldDto();
        // setHoldingType(entity.getProductTradeInfoDto(), DictCodeList.持仓类型_可用, true);
        // ptlPositionHoldDto.setPtlSecPositionFlows(entity.getProductTradeInfoDto().getPtlSecPositionFlows());
        // ptlPositionHoldDto.setPtlCashPositionFlows(entity.getProductTradeInfoDto().getPtlCashPositionFlows());
        // restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/common/ptlposition/insertPosition",
        // ptlPositionHoldDto, Boolean.class);
    }

    private void setHoldingType(ProductTradeInfoDto productTradeInfoDto, DictCodeList 持仓类型, boolean needNegative) {
        if (productTradeInfoDto.getPtlSecPositionFlows() != null && !productTradeInfoDto.getPtlSecPositionFlows().isEmpty()) {
            productTradeInfoDto.getPtlSecPositionFlows().forEach(positionFlowDto -> {
                positionFlowDto.setHodingType(持仓类型.getValue());
                if (needNegative) {
                    positionFlowDto.setAmount(positionFlowDto.getAmount().negate());
                }
            });
        }
        if (productTradeInfoDto.getPtlCashPositionFlows() != null && !productTradeInfoDto.getPtlCashPositionFlows().isEmpty()) {
            productTradeInfoDto.getPtlCashPositionFlows().forEach(positionFlowDto -> {
                positionFlowDto.setHodingType(持仓类型.getValue());
                if (needNegative) {
                    positionFlowDto.setAmount(positionFlowDto.getAmount().negate());
                }
            });
        }

    }

    private void saveDataWhenCompleteWorkflow(FinProductAddReduce002Dto entity, boolean isRevoke) {
        // 插入Entity到对应的业务流程DB中
        ProductTradeInfoDto trade = entity.getProductTradeInfoDto();
        // List<PtlSecPositionFlowDto> list = entity.getProductTradeInfoDto().getPtlSecPositionFlows();
        if (isRevoke) {
            trade = findAllTrades(entity.getBusiCode());
            entity.setProductTradeInfoDto(trade);
        }
        TrdFinProductDealTotal trdFinProductDealTotal = trade.getTrdFinProductDealTotal();
        if (trdFinProductDealTotal != null) {
            if (isRevoke) {
                // 交易状态=“撤销”
                if (trdFinProductDealTotal != null) {
                    trdFinProductDealTotal.setIsCancel(DictCodeList.是否_是.getValue());
                    trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
                    trdFinProductDealTotalMapper.updateByPrimaryKeySelective(trdFinProductDealTotal);

                    // 原交易编号=金融产品交易信息DTO.金融产品交易Entity.交易编号
                    trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
                    trdFinProductDealTotal.setpTradeId(trdFinProductDealTotal.getTotleTradeId());
                    // 交易编号=金融产品交易信息DTO.金融产品交易Entity.交易编号+“R”
                    trdFinProductDealTotal.setTotleTradeId(
                        trdFinProductDealTotal.getTotleTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                    trdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
                }
            }
            trdFinProductDealTotalMapper.insertSelective(trdFinProductDealTotal);
        }

        PaTrdFinProductDealTotal paTrdFinProductDealTotal = trade.getPaTrdFinProductDealTotal();
        if (paTrdFinProductDealTotal != null) {
            if (isRevoke) {
                // 交易状态=“撤销”
                if (paTrdFinProductDealTotal != null) {
                    // 交易编号=金融产品交易信息DTO.金融产品交易Entity.交易编号+“R”
                    paTrdFinProductDealTotal.setTotleTradeId(
                        paTrdFinProductDealTotal.getTotleTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                    paTrdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
                }
            }
            paTrdFinProductDealTotalMapper.insertSelective(paTrdFinProductDealTotal);
        }
        if (trade.getTrdProductDeals() != null) {
            trade.getTrdProductDeals().forEach(trdProductDeal -> {
                if (isRevoke) {
                    // 交易状态=“撤销”
                    trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
                    trdProductDeal.setIsCancel(DictCodeList.是否_是.getValue());
                    trdProductDealMapper.updateByPrimaryKeySelective(trdProductDeal);

                    trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
                    // 原交易编号=金融产品交易信息DTO.金融产品交易Entity.交易编号
                    trdProductDeal.setpTradeId(trdProductDeal.getTradeId());
                    // 交易编号=金融产品交易信息DTO.金融产品交易Entity.交易编号+“R”
                    trdProductDeal.setTotleTradeId(
                        trdProductDeal.getTotleTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                    trdProductDeal.setTradeId(trdProductDeal.getTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                    // add by wm将带R的交易的创建时间与更新时间修改为最新的系统时间 start 2019-09-02
                    trdProductDeal.setCreateTime(DateUtil.now());
                    trdProductDeal.setUpdateTime(DateUtil.now());
                    // add by wm将带R的交易的创建时间与更新时间修改为最新的系统时间 end 2019-09-02
                    // 撤销说明=画面.撤销说明
                    trdProductDeal.setCancelRemark(entity.getRevokeReason());
                }
                if (!isRevoke && trdProductDeal.getPs().equals(DictCodeList.交易方向_产品违约赎回.getValue())) {
                    if (entity.getSdefaultAmt() != null && entity.getSdefaultAmt().compareTo(BigDecimal.ZERO) != 0) {
                        TrdTradeFeeDetail trdTradeFeeDetail = genTrdTradeFeeDetailCommon(entity);
                        trdTradeFeeDetail.setTradeId(trdProductDeal.getTradeId());
                        trdTradeFeeDetail.setFeeAmt(entity.getSdefaultAmt());
                        trdTradeFeeDetail.setFeeType(DictCodeListExt1.一次性费用类型_违约金.getValue());
                        trdTradeFeeDetailMapper.insertSelective(trdTradeFeeDetail);
                    }
                }
                trdProductDeal.setRemark(entity.getRemark());
                trdProductDealMapper.insertSelective(trdProductDeal);

                // 一体化对应etliu start
                // 所有交易生效后调用的共通方法
                if (trdProductDeal.getPs().equals(DictCodeList.交易方向_产品提前赎回.getValue())
                    || trdProductDeal.getPs().equals(DictCodeList.交易方向_产品违约赎回.getValue())) {
                    trdProductDeal.setPs(DictCodeList.交易方向_产品赎回.getValue());
                }
                AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                inputDto.setProdid(entity.getFinprodId());
                inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
                inputDto.setTrdProductDeal(trdProductDeal);
                // 查询产品的起息日

                try {
                    if (trdProductDeal.getBranch() != 0L) {
                        inputDto.setProdVdate(
                            DateUtil.parseDate(String.valueOf(trdProductDeal.getBranch()), DateUtil.DEFAULT_OTHER_FORMAT));
                    }
                    else {
                        // FinProductBuild002Dto prodinfo = finProductBuild002Service.queryProdInfoByFinprodId(trdProductDeal.getFinprodId());
                        inputDto.setProdVdate(DateUtil.parse(entity.getPrdVdate()));
                    }
                }
                catch (ParseException e) {
                    inputDto.setProdVdate(trdProductDeal.getVdate());
                }
                AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
                // 一体化对应etliu end
            });
        }
        if (trade.getTrdProductDealAdds() != null) {
            trade.getTrdProductDealAdds().forEach(trdProductDealAdd -> {
                if (isRevoke) {
                    trdProductDealAdd.setTradeId(
                        trdProductDealAdd.getTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                }
                trdProductDealAddMapper.insertSelective(trdProductDealAdd);
            });
        }
        if (trade.getPaTrdProductDeals() != null) {

            trade.getPaTrdProductDeals().forEach(paTrdProductDeal -> {
                if (isRevoke) {
                    paTrdProductDeal.setTradeId(
                        paTrdProductDeal.getTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                }
                paTrdProductDealMapper.insertSelective(paTrdProductDeal);
            });
        }
        if (trade.getStlPayOrders() != null) {
            trade.getStlPayOrders().forEach(stlPayOrders -> {
                if (isRevoke) {
                    // orderid +R ORDERSTATUS=02 PTRADEID = 原tradeid
                    stlPayOrders.setpTradeId(stlPayOrders.getTradeId());
                    stlPayOrders.setOrderStatus("02");
                    stlPayOrders.setOrderId(stlPayOrders.getOrderId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                    stlPayOrders.setTradeId(stlPayOrders.getTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                }
                stlPayOrdersMapper.insertSelective(stlPayOrders);
            });
        }
        // 组合交易入库
        if (trade.getPtlTrades() != null) {
            trade.getPtlTrades().forEach(ptlTrade -> {
                if (isRevoke) {
                    ptlTrade.setBusiNo(
                        StringUtil.defaultString(ptlTrade.getBusiNo()) + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                }
                ptlTradeMapper.insertSelective(ptlTrade);
            });
        }
        // 调用业务组件.【金融产品交易共通.交易产品接口】,写入[可用]的组合持仓流水及现金流水
        BussTrdPositionDto bussTrdPositionDto = new BussTrdPositionDto();
        bussTrdPositionDto.setPortfolioId(entity.getPtlId());
        bussTrdPositionDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_有效.getValue());
        if (isRevoke) {
            bussTrdPositionDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_撤销.getValue());
        }
        bussTrdPositionDto.setTrdProductDealList(trade.getTrdProductDeals());
        bussTrdPositionDto.setStlPayOrders(trade.getStlPayOrders());

        baseTradeProductDealCommService.trdProductInsertPosition(bussTrdPositionDto);

        // if (isRevoke) {
        // entity.getProductTradeInfoDto().setPtlSecPositionFlows(list);
        // entity.getProductTradeInfoDto().setPtlCashPositionFlows(new ArrayList<>());
        // entity.getProductTradeInfoDto().getStlPayOrders().forEach(orders -> {
        // genCashFlow(entity, entity.getProductTradeInfoDto().getPtlCashPositionFlows(), orders);
        // });
        // entity.getProductTradeInfoDto().getTrdProductDeals().forEach(deal -> {
        // gen组合持仓流水(deal, entity);
        // });
        //
        // }
        // PtlPositionHoldDto ptlPositionHoldDto = new PtlPositionHoldDto();
        // setHoldingType(entity.getProductTradeInfoDto(), DictCodeList.持仓类型_实际, isRevoke);
        // ptlPositionHoldDto.setPtlSecPositionFlows(entity.getProductTradeInfoDto().getPtlSecPositionFlows());
        // ptlPositionHoldDto.setPtlCashPositionFlows(entity.getProductTradeInfoDto().getPtlCashPositionFlows());
        // restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/common/ptlposition/insertPosition",
        // ptlPositionHoldDto, Boolean.class);

        // add by zhangJin 邮储项目个性化-TA交易发送 start
        if ("001".equals(CacheUtil.getSysParam("CUSTOMER_BANK_CODE"))) {
            List<TrdProductDeal> dealList = trade.getTrdProductDeals();
            if (!StringUtil.isEmptyList(dealList)) {
                BigDecimal tradeAmt = BigDecimal.ZERO;
                BigDecimal shareAmt = BigDecimal.ZERO;
                BigDecimal feeAmt = BigDecimal.ZERO;
                for (TrdProductDeal temp : dealList) {
                    tradeAmt = ArithmeticUtil.doAdd(tradeAmt, temp.getTradeAmt());
                    shareAmt = ArithmeticUtil.doAdd(shareAmt, temp.getShareAmt());
                    feeAmt = ArithmeticUtil.doAdd(feeAmt, temp.getFeeAmt());
                }
                TrdProductDeal deal = new TrdProductDeal();
                deal.setFinprodId(
                    StringUtil.isNotEmpty(entity.getFinprodId()) ? dealList.get(0).getFinprodId() : entity.getFinprodId());
                deal.setTradeAmt(tradeAmt);
                deal.setSettleDate(dealList.get(0).getSettleDate());
                deal.setPs(entity.getPs());
                deal.setFeeAmt(feeAmt);
                deal.setShareAmt(shareAmt);
                deal.setTradeId(entity.getTotleTradeId());
                deal.setStatus("0");
                if (isRevoke) {
                    deal.setStatus("3");
                }
                restTemplate.postForObject(ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/custodian/sendProdTADeal", deal,
                    String.class);
            }
        }
        // add by zhangJin 邮储项目个性化-TA交易发送 end
        
        //20191008 add
    	TrdAsynHandleStatus trdAsynHandleStatus = new TrdAsynHandleStatus();
        trdAsynHandleStatus.setTradeId(trdFinProductDealTotal.getTotleTradeId());		//交易汇总id
        trdAsynHandleStatus.setTrdTableType("01");		// 交易表类型(01：金融产品交易汇总表，02：金融产品交易表)
        trdAsynHandleStatus.setHandleStatus("01");		//01	待处理
        trdAsynHandleStatusMapper.insertSelective(trdAsynHandleStatus);
    }

    // 生成交易 kkkkk
    public ProductTradeInfoDto genFinProductTradeDto(FinProductAddReduce002Dto entity) {
        ProductTradeInfoDto result = new ProductTradeInfoDto();
        // result.setTrdFinProductDealTotal(gen汇总交易(entity));
        // result.setPaTrdFinProductDealTotal(gen汇总交易扩展(entity));
        // List<TrdProductDeal> trdProductDeals = new ArrayList<>();
        // TrdProductDeal trdProductDeal = gen产品交易收益(entity, result);
        // trdProductDeals.add(trdProductDeal);
        // result.setTrdProductDeals(trdProductDeals);
        // result.setTrdProductDealAdds(Arrays.asList(gen交易附属收益(entity, trdProductDeal)));
        // result.setPtlTrades(Arrays.asList(gen组合交易(entity, trdProductDeal)));
        // gen划款指令(entity, result);
        // 生成本金利息交易及相关表(entity, result);

        result.setTrdProductDeals(new ArrayList<>());
        result.setTrdProductDealAdds(new ArrayList<>());
        result.setPaTrdProductDeals(new ArrayList<>());
        result.setPtlTrades(new ArrayList<>());
        // result.setPtlSecPositionFlows(new ArrayList<>());

        result.setTrdFinProductDealTotal(gen汇总交易(entity));
        result.setPaTrdFinProductDealTotal(gen汇总交易扩展(entity));
        result.getPtlTrades().add(get组合交易(entity));
        gen划款指令(entity, result);
        生成本金利息交易及相关表(entity, result);
        return result;
    }

    private TrdFinProductDealTotal gen汇总交易非收益(FinProductAddReduce002Dto entity) {
        TrdFinProductDealTotal total = new TrdFinProductDealTotal();
        total.setTotleTradeId(entity.getTotleTradeId());
        total.setBusiType(DictCodeList.共通业务类型_收益型产品提前赎回管理.getValue());
        total.setFinprodId(entity.getFinprodId());
        total.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        // TODO deal.setFinprodType();
        total.setCcy(entity.getCcy());
        total.setShareAmt(entity.getShareAmt());
        total.setTradeDate(DateUtil.parse(entity.getTradeDate()));
        total.setVdate(DateUtil.parse(entity.getTradeDate()));
        total.setUnitCprice(entity.getUnitCprice());
        total.setIsCancel(DictCodeList.是否_否.getValue());
        total.setPrinAmt(entity.getShareAmt());
        total.setPs(entity.getPs());
        total.setTradeAmt(entity.getTradeAmt());
        total.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        // 一体化对应etliu start
        total.setPortfolioId(entity.getPtlId());
        // 一体化对应etliu end
        return total;
    }

    private void 生成费用交易及相关表(FinProductAddReduce002Dto entity, ProductTradeInfoDto result) {
        // 5.3费用：循环处理
        if (entity.getProductFeeInfoList() != null) {
            entity.getProductFeeInfoList().forEach(productFeeInfo -> {
                BussinessCodeParameter parameter = new BussinessCodeParameter();
                parameter.setCommonBusinessType(DictCodeList.共通业务类型_收益型产品提前赎回管理);
                String bizCode = baseGetBusinessIdImpl.buildBussinessCode(parameter);
                TrdProductDeal 交易 = new TrdProductDeal();
                交易.setTradeId(bizCode);
                交易.setFinprodId(entity.getFinprodId());
                交易.setFinprodType2(productFeeInfo.getFeeType());
                交易.setFinprodType(DictCodeList.业务类型_预期收益型TA交易.getValue());
                交易.setBranch(Long.parseLong(productFeeInfo.getIndexNo()));
                交易.setVdate(DateUtil.parse(productFeeInfo.getvDate()));
                交易.setMdate(DateUtil.parse(productFeeInfo.getmDate()));
                交易.setTradeDate(DateUtil.parse(entity.getTradeDate()));
                交易.setSettleDate(DateUtil.parse(entity.getTradeDate()));
                交易.setTradeAmt(productFeeInfo.getActFeeAmt());
                交易.setPs(DictCodeList.交易方向_产品付费.getValue());
                交易.setTotleTradeId(entity.getTotleTradeId());
                交易.setShareAmt(productFeeInfo.getPrinAmt());
                交易.setCcy(entity.getCcy());
                // 一体化对应etliu start
                交易.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
                交易.setIsCancel(DictCodeList.是否_否.getValue());
                // 一体化对应etliu end

                result.getTrdProductDeals().add(交易);

                PaTrdProductDeal 扩展 = new PaTrdProductDeal();
                扩展.setTradeId(bizCode);
                if (DictCodeList.交易方向_份额强减.getValue().equals(entity.getPs())) {
                    扩展.setPaBussType(DictCodeListExt1.平安业务类型_产品强减.getValue());
                }
                else {
                    扩展.setPaBussType(DictCodeListExt1.平安业务类型_产品强增.getValue());
                }
                扩展.setTheoryRate(ArithmeticUtil.divide(productFeeInfo.getYield(), 100, 8));
                扩展.setActRate(ArithmeticUtil.divide(productFeeInfo.getActYield(), 100, 8));
                扩展.setTheoryInt(productFeeInfo.getFeeAmt());
                扩展.setBasis(productFeeInfo.getBasis());
                result.getPaTrdProductDeals().add(扩展);

                result.getPtlTrades().add(gen组合交易(entity, 交易));
            });
        }
    }

    private void 生成本金利息交易及相关表(FinProductAddReduce002Dto entity, ProductTradeInfoDto result) {
        // 5.1本金：按强减区域数据，循环处理
        if (entity.getReduceInfoDtoList() != null) {
            entity.getReduceInfoDtoList().forEach(reduceInfoDto -> {
                生成本金交易相关表(reduceInfoDto, entity, result, DictCodeListExt1.现金流一级类型_本金);
                生成本金交易相关表(reduceInfoDto, entity, result, DictCodeListExt1.现金流一级类型_利息);
            });
        }

    }

    /**
     * 是否分层
     *
     * @param entity 确认信息
     * @return 是否分层并且有分层列表
     * @see
     * @since 1.0
     */
    private boolean isIsLay(FinProductAddReduce002Dto entity) {
        boolean flag = false;
        if (StringUtil.equals(DictCodeList.是否_是.getValue(), entity.getIsLay())) {
            flag = true;
        }
        return flag;
    }

    private void 生成本金交易相关表(ReduceInfoDto reduceInfoDto, FinProductAddReduce002Dto entity, ProductTradeInfoDto result,
                           DictCodeListExt1 本金或利息) {
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        if (DictCodeListExt1.现金流一级类型_本金 == 本金或利息) {
            parameter.setCommonBusinessType(DictCodeList.共通业务类型_收益型产品提前赎回管理);
        }
        else {
            parameter.setCommonBusinessType(DictCodeList.共通业务类型_收益型产品中间付息管理);
        }
        String bizCode = baseGetBusinessIdImpl.buildBussinessCode(parameter);
        TrdProductDeal 交易 = new TrdProductDeal();
        交易.setTradeId(bizCode);
        交易.setFinprodId(reduceInfoDto.getFinprodId());
        交易.setFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
        交易.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        if (this.isIsLay(entity)) {
            交易.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue());
        }
        交易.setBranch((long)0);
        交易.setTradeDate(DateUtil.parse(entity.getTradeDate()));
        交易.setSettleDate(交易.getTradeDate());
        if (DictCodeListExt1.现金流一级类型_本金 == 本金或利息) {
            交易.setShareAmt(reduceInfoDto.getReduceAmt());
            交易.setPrinAmt(reduceInfoDto.getReduceAmt());
            交易.setFpriceAmt(reduceInfoDto.getReduceAmt());
            交易.setTradeAmt(reduceInfoDto.getReduceAmt());
            交易.setMdate(null);
            交易.setPs(entity.getPs());
        }
        else {
            交易.setIntAmt(reduceInfoDto.getActInterest());
            交易.setTradeAmt(reduceInfoDto.getActInterest());
            交易.setPs(DictCodeList.交易方向_赎回收益确认.getValue());
            交易.setMdate(DateUtil.parse(entity.getmDate()));
            交易.setIsClean(DictCodeList.是否_是.getValue());
        }

        交易.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        交易.setVdate(DateUtil.parse(entity.getvDate()));
        交易.setTotleTradeId(entity.getTotleTradeId());

        交易.setCcy(entity.getCcy());
        // 一体化对应etliu start
        交易.setIsCancel(DictCodeList.是否_否.getValue());
        // 一体化对应etliu end

        result.getTrdProductDeals().add(交易);

        PaTrdProductDeal 扩展 = new PaTrdProductDeal();
        扩展.setTradeId(bizCode);
        扩展.setRemainPrinAmt(reduceInfoDto.getRemainPrinAmt());
        // 扩展.setTheoryRate(ArithmeticUtil.divide(reduceInfoDto.getYield(), 100, 8));
        // 扩展.setActRate(ArithmeticUtil.divide(reduceInfoDto.getActYield(), 100, 8));
        扩展.setTheoryInt(reduceInfoDto.getInterest());
        扩展.setBasis(reduceInfoDto.getBasis());
        扩展.setPaBussType(DictCodeListExt1.平安业务类型_产品强减.getValue());
        // lijun modiyf 20190322 SHBS-1241 清结算管理，提前赎回/违约赎回
        扩展.setOrderBussType(DictCodeListExt1.指令业务类型_产品赎回_提前赎回_违约赎回.getValue());
        result.getPaTrdProductDeals().add(扩展);

        TrdProductDealAdd 附属 = new TrdProductDealAdd();
        附属.setTradeId(bizCode);
        附属.setPortfolioId(entity.getPtlId());
        附属.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
        result.getTrdProductDealAdds().add(附属);
    }

    private PtlSecPositionFlowDto gen组合持仓流水(TrdProductDeal 交易, FinProductAddReduce002Dto entity) {
        PtlSecPositionFlowDto ptlSecPositionFlowDto = new PtlSecPositionFlowDto();
        ptlSecPositionFlowDto.setPortfolioId(entity.getPtlId());
        ptlSecPositionFlowDto.setBranch(交易.getBranch());
        ptlSecPositionFlowDto.setCdate(DateUtil.parse(entity.getTradeDate()));
        ptlSecPositionFlowDto.setCcy(entity.getCcy());
        ptlSecPositionFlowDto.setSecAcctId("999999");
        ptlSecPositionFlowDto.setInvAim("999999");
        if (!DictCodeList.交易方向_赎回收益确认.getValue().equals(交易.getPs())) {
            ptlSecPositionFlowDto.setAmount(交易.getPrinAmt().negate());
        }
        else {
            ptlSecPositionFlowDto.setAmount(交易.getIntAmt().negate());
        }
        ptlSecPositionFlowDto.setTradeId(交易.getTradeId());
        if (DictCodeList.是否_是.getValue().equals(entity.getIsLay())) {
            ptlSecPositionFlowDto.setFinprodId(交易.getInvAim()); // 子产品代码
            ptlSecPositionFlowDto.setpFinprodId(entity.getFinprodId());
        }
        else {
            ptlSecPositionFlowDto.setFinprodId(entity.getFinprodId());
        }
        return ptlSecPositionFlowDto;
    }

    private void gen划款指令(FinProductAddReduce002Dto entity, ProductTradeInfoDto result) {
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_收益型产品提前赎回管理);
        List<BussTrdTransferOrderDto> stlPayOrdersList = bussTrdTransferOrderImplService.save(entity.getBussTrdTransferOrder());
        List<StlPayOrdersDto> stlPayOrdersDtos = new ArrayList<>();
        // List<PtlCashPositionFlowDto> ptlCashPositionFlows = new ArrayList<>();
        if (stlPayOrdersList != null) {
            stlPayOrdersList.forEach(stlPayOrders -> {
                StlPayOrdersDto orders = new StlPayOrdersDto();
                BeanUtil.copyPropertiesIgnoreNull(orders, stlPayOrders);
                orders.setTradeId(entity.getTotleTradeId());
                orders.setOrderStatus(DictCodeListExt1.指令状态_新建.getValue());
                orders.setWorkflowId(entity.getWorkflowId());
                stlPayOrdersDtos.add(orders);

                // genCashFlow(entity, ptlCashPositionFlows, orders);
            });
        }
        result.setStlPayOrders(stlPayOrdersDtos);
        // result.setPtlCashPositionFlows(ptlCashPositionFlows);
    }

    private void genCashFlow(FinProductAddReduce002Dto entity, List<PtlCashPositionFlowDto> ptlCashPositionFlows,
                             StlPayOrders orders) {
        PtlCashPositionFlowDto ptlCashPositionFlow = new PtlCashPositionFlowDto();
        ptlCashPositionFlow.setPortfolioId(entity.getPtlId());
        ptlCashPositionFlow.setCdate(DateUtil.parse(entity.getTradeDate()));
        ptlCashPositionFlow.setCcy(entity.getCcy());
        ptlCashPositionFlow.setAmount(orders.getSettleAmt().negate());
        ptlCashPositionFlow.setAcctId(
            StringUtil.isEmpty(orders.getMyAcctNo()) ? orders.getCounterAcctNo() : orders.getMyAcctNo());
        ptlCashPositionFlow.setOrderId(orders.getOrderId());
        ptlCashPositionFlow.setFinprodId(entity.getFinprodId());
        ptlCashPositionFlows.add(ptlCashPositionFlow);
    }

    private PaTrdFinProductDealTotal gen汇总交易扩展(FinProductAddReduce002Dto entity) {
        PaTrdFinProductDealTotal paTotal = new PaTrdFinProductDealTotal();
        paTotal.setTotleTradeId(entity.getTotleTradeId());
        paTotal.setPaBussType(DictCodeListExt1.平安业务类型_产品赎回.getValue());
        paTotal.setBranch(entity.getEntrusterId());
        paTotal.setInterest(entity.getActCusProfit());
        return paTotal;
    }

    private TrdFinProductDealTotal gen汇总交易(FinProductAddReduce002Dto entity) {
        TrdFinProductDealTotal total = new TrdFinProductDealTotal();
        total.setTotleTradeId(entity.getTotleTradeId());
        total.setPortfolioId(entity.getPtlId());
        total.setBusiType(DictCodeList.共通业务类型_收益型产品提前赎回管理.getValue());
        total.setFinprodId(entity.getFinprodId());
        total.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        // TODO deal.setFinprodType();
        total.setCcy(entity.getCcy());
        total.setShareAmt(entity.getPrinAmt());
        total.setPrinAmt(entity.getPrinAmt());
        total.setPs(entity.getPs());
        total.setTradeDate(DateUtil.parse(entity.getTradeDate()));
        total.setSettleDate(DateUtil.parse(entity.getSettleDate()));
        total.setVdate(DateUtil.parse(entity.getvDate()));
        total.setMdate(DateUtil.parse(entity.getmDate()));
        total.setIsCancel(DictCodeList.是否_否.getValue());
        total.setIntAmt(entity.getActCusProfit());
        total.setFpriceAmt(ArithmeticUtil.doAdd(entity.getPrinAmt(), entity.getActCusProfit()));
        // BigDecimal 总费用 = BigDecimal.ZERO;
        // if (entity.getProductFeeInfoList() != null) {
        // for (ProductFeeInfo productFeeInfo : entity.getProductFeeInfoList()) {
        // 总费用 = ArithmeticUtil.doAdd(总费用, productFeeInfo.getActFeeAmt());
        // }
        // }
        total.setTradeAmt(ArithmeticUtil.doAdd(entity.getPrinAmt(), entity.getActCusProfit()));
        // total.setFeeAmtPay(总费用);
        // total.setFeeAmt(总费用);
        total.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        return total;
    }

    private PtlTrade get组合交易(FinProductAddReduce002Dto entity) {
        PtlTrade trade = new PtlTrade();
        trade.setPortfolioId(entity.getPtlId());
        trade.setBusiNo(entity.getTotleTradeId());
        trade.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        trade.setBusiType(DictCodeList.共通业务类型_收益型产品提前赎回管理.getValue());
        trade.setBusiId(entity.getFinprodId());
        trade.setSettleDate(DateUtil.parse(entity.getTradeDate()));
        trade.setWorkflowId(entity.getWorkflowId());
        return trade;
    }

    private PtlTrade gen组合交易(FinProductAddReduce002Dto entity, TrdProductDeal trdProductDeal) {
        PtlTrade trade = new PtlTrade();
        trade.setPortfolioId(entity.getPtlId());
        trade.setBusiNo(trdProductDeal.getTradeId());
        trade.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        trade.setBusiType(DictCodeList.共通业务类型_收益型产品提前赎回管理.getValue());
        trade.setBusiId(entity.getFinprodId());
        trade.setSettleDate(DateUtil.parse(entity.getTradeDate()));
        return trade;
    }

    private TrdProductDealAdd gen交易附属收益(FinProductAddReduce002Dto entity, TrdProductDeal trdProductDeal) {
        TrdProductDealAdd add = new TrdProductDealAdd();
        add.setTradeId(trdProductDeal.getTradeId());
        add.setPortfolioId(entity.getPtlId());
        add.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
        return add;
    }

    private List<StlPayOrders> gen划款指令非收益(FinProductAddReduce002Dto entity, ProductTradeInfoDto result) {
        List<BussTrdTransferOrderDto> stlPayOrdersList = bussTrdTransferOrderImplService.save(entity.getBussTrdTransferOrder());
        List<StlPayOrders> stlPayOrdersDtos = new ArrayList<>();
        List<PtlCashPositionFlowDto> ptlCashPositionFlows = new ArrayList<>();
        if (stlPayOrdersList != null) {
            stlPayOrdersList.forEach(stlPayOrders -> {
                StlPayOrders dto = new StlPayOrders();
                BeanUtil.copyPropertiesIgnoreNull(dto, stlPayOrders);
                dto.setTradeId(entity.getTotleTradeId());
                dto.setOrderStatus(DictCodeListExt1.指令状态_新建.getValue());
                dto.setWorkflowId(entity.getWorkflowId());
                stlPayOrdersDtos.add(dto);

                if (DictCodeList.交易方向_份额强减.getValue().equals(entity.getPs())) {
                    PtlCashPositionFlowDto ptlCashPositionFlow = new PtlCashPositionFlowDto();
                    ptlCashPositionFlow.setPortfolioId(entity.getPtlId());
                    ptlCashPositionFlow.setAcctId(stlPayOrders.getMyAcctNo());
                    ptlCashPositionFlow.setCdate(DateUtil.parse(entity.getTradeDate()));
                    ptlCashPositionFlow.setCcy(entity.getCcy());
                    ptlCashPositionFlow.setAmount(stlPayOrders.getSettleAmt().negate());
                    ptlCashPositionFlow.setOrderId(stlPayOrders.getOrderId());
                    ptlCashPositionFlow.setFinprodId(entity.getFinprodId());
                    ptlCashPositionFlows.add(ptlCashPositionFlow);
                }
            });
            result.setPtlCashPositionFlows(ptlCashPositionFlows);
        }
        return stlPayOrdersDtos;
    }

    private TrdProductDeal gen产品交易收益(FinProductAddReduce002Dto entity, ProductTradeInfoDto result) {
        TrdProductDeal deal = new TrdProductDeal();
        deal.setTradeId(entity.getTotleTradeId());
        deal.setBusiType(DictCodeList.共通业务类型_收益型产品提前赎回管理.getValue());
        deal.setFinprodId(entity.getFinprodId());
        deal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        // TODO deal.setFinprodType();
        deal.setCcy(entity.getCcy());
        deal.setTradeDate(DateUtil.parse(entity.getTradeDate()));
        deal.setVdate(DateUtil.parse(entity.getTradeDate()));
        deal.setSettleDate(DateUtil.parse(entity.getTradeDate()));
        // deal.setCpriceDate(DateUtil.parse(entity.getCpriceDate()));
        // deal.setUnitCprice(entity.getUnitCprice());
        deal.setShareAmt(entity.getPrinAmt());
        deal.setTradeAmt(entity.getPrinAmt());
        deal.setPs(entity.getPs());
        deal.setTotleTradeId(entity.getTotleTradeId());
        // 一体化对应etliu start
        deal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        deal.setIsCancel(DictCodeList.是否_否.getValue());
        // 一体化对应etliu end

        PtlSecPositionFlowDto ptlSecPositionFlowDto = new PtlSecPositionFlowDto();
        ptlSecPositionFlowDto.setBranch(Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
        ptlSecPositionFlowDto.setPortfolioId(entity.getPtlId());
        ptlSecPositionFlowDto.setCdate(DateUtil.parse(entity.getTradeDate()));
        ptlSecPositionFlowDto.setCcy(entity.getCcy());
        ptlSecPositionFlowDto.setAmount(entity.getPrinAmt().negate());
        ptlSecPositionFlowDto.setTradeId(deal.getTradeId());
        ptlSecPositionFlowDto.setFinprodId(entity.getFinprodId());

        result.setPtlSecPositionFlows(Arrays.asList(ptlSecPositionFlowDto));
        return deal;
    }

    // @Override
    // public Map<String, Object> getRuntimeVariables(ProcessMark entity, String taskId) {
    // Map<String, Object> result = new HashMap<>();
    // FinProductAddReduce002Dto dto = getBussinessEntity(entity);
    // result.put("isNeedWorkflow",
    // DictCodeListExt1.本金收益特征_非保本.getValue().equals(dto.getCapiIncomeFeature()));
    // return result;
    // }

    private TrdTradeFeeDetail genTrdTradeFeeDetailCommon(FinProductAddReduce002Dto dto) {
        TrdTradeFeeDetail trdTradeFeeDetail = new TrdTradeFeeDetail();
        // trdTradeFeeDetail.setIsPayWithTrade(DictCodeList.是否_否.getValue());
        trdTradeFeeDetail.setCreateUser(dto.getCreateUser());
        trdTradeFeeDetail.setCreateTime(dto.getCreateTime());
        trdTradeFeeDetail.setUpdateUser(dto.getCreateUser());
        trdTradeFeeDetail.setUpdateTime(dto.getCreateTime());
        return trdTradeFeeDetail;
    }
}
