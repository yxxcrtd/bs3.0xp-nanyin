package com.joyintech.tams.invest.trade.projectnet.service;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.settle.BussSettleOrderCommService;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.InvestInterfCommDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.service.InvestInterfCommService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.common.trade.service.MultiApproveTradeCheckService;
import com.joyintech.tams.invest.trade.fund.dto.WorkFlowDto;
import com.joyintech.tams.invest.trade.projectnet.dto.TrdProjectNetInvestTradeConfirmDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.entity.FlowEntity;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TrdProjectNetInvestTrade003Service extends BaseService<TrdProjectNetInvestTradeConfirmDto> implements BaseProcessService<TrdProjectNetInvestTradeConfirmDto> {

    /**
     * 附件信息共通Service
     */
    @Autowired(required = false)
    private FileAttachmentProcess fileAttachmentProcess;

    /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

    /**
     * 清算指令共通Service
     */
    @Autowired(required = false)
    private BussSettleOrderCommService bussSettleOrderCommService;

    /**
     * 划款指令业务组件service
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    /**
     * 金融产品交易共通
     */
    @Autowired(required = false)
    private BaseTradeProductDealCommService baseTradeProductDealCommService;

    /**
     * 交易及存续期交易，保存进待办，点批量审批时候检查service
     */
    @Autowired(required = false)
    private MultiApproveTradeCheckService multiApproveTradeCheckService;

    // 调用投管接口 add by renping 20200402 start
    /**
     * 投管接口共通组件
     */
    @Autowired
    private InvestInterfCommService investInterfCommService;
    // 调用投管接口 add by renping 20200402 end
    /**
     * 交易及存续期交易，保存进待办，点批量审批时候需要报错。
     * {@inheritDoc}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.MASERT)
    public String checkForSubmit(FlowEntity entity, boolean isMutiApproval) {
        if (isMutiApproval) {
            // 业务编号
            String tradeId = entity.getTradeId();
            if (!multiApproveTradeCheckService.multiApproveTradeCheck(tradeId)) {
                return getMessage("BUSS_COM_E_0100", tradeId);
            }
        }
        return null;
    }

    /**
     * 确认审批结束
     */
    @Override
    public void doComplete(TrdProjectNetInvestTradeConfirmDto entity, OperationType arg1) {
        FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
        if (StringUtil.equals(entity.getOperateType(), "submit")) {
            UploadFile file = null;
            List<SysUploadFileInfo> syfiList = entity.getFileArray();
            for (SysUploadFileInfo sufi : syfiList) {
                file = fileAttachmentProcess.fetch(sufi.getSeqNo());
                String generateId = IDGenerator.generate();
                file.setFileNo(generateId);
                fileAttachmentProcess.upload(file);
                sufi.setSeqNo(generateId);
            }
            if (StringUtil.equals(entity.getPs(), DictCodeList.交易方向_申购确认.getValue())) {
                prepareEntity(entity);
            }
            finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_有效.getValue());
            List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
            BaseTradeProductDealDto dto = new BaseTradeProductDealDto();
            // 费用明细
            List<TrdTradeFeeDetail> ttfdList = new ArrayList<>();
            if (StringUtil.equals(entity.getPs(), DictCodeList.交易方向_申购确认.getValue())) {

                TrdTradeFeeDetail ttfd = new TrdTradeFeeDetail();
                ttfd.setTradeId(entity.getTradeId());
                ttfd.setFeeType(DictCodeListExt1.一次性费用类型_申购费.getValue());
                ttfd.setIsPayWithTrade(DictCodeList.是否_是.getValue());
                ttfd.setFeeAmt(entity.getFeeAmtPay());
                ttfdList.add(ttfd);
            }
            else if (StringUtil.equals(entity.getPs(),DictCodeList.交易方向_赎回确认.getValue()) || StringUtil.equals(entity.getPs(),
                    DictCodeList.交易方向_赎回到账.getValue())) {
                TrdTradeFeeDetail ttfd1 = new TrdTradeFeeDetail();
                ttfd1.setTradeId(entity.getTradeId());
                ttfd1.setFeeType(DictCodeListExt1.一次性费用类型_赎回费.getValue());
                ttfd1.setIsPayWithTrade(DictCodeList.是否_是.getValue());
                ttfd1.setFeeAmt(entity.getRedemptionFee());
                ttfdList.add(ttfd1);

                // 一体化对应etliu start
//                TrdTradeFeeDetail ttfd3 = new TrdTradeFeeDetail();
//                ttfd3.setTradeId(entity.getTradeId());
//                ttfd3.setFeeType(DictCodeListExt2.费用类型_托管费.getValue());
//                ttfd3.setIsPayWithTrade(DictCodeList.是否_是.getValue());
//                ttfd3.setFeeAmt(entity.getHostingFee());
//                ttfdList.add(ttfd3);
                // 一体化对应etliu end
            }
            if (entity.getChannelFee() != null && BigDecimal.ZERO.compareTo(entity.getChannelFee()) < 0) {
                TrdTradeFeeDetail ttfd2 = new TrdTradeFeeDetail();
                ttfd2.setTradeId(entity.getTradeId());
                ttfd2.setFeeType(DictCodeListExt1.一次性费用类型_通道费.getValue());
                ttfd2.setIsPayWithTrade(DictCodeList.是否_是.getValue());
                ttfd2.setFeeAmt(entity.getChannelFee());
                ttfdList.add(ttfd2);
                entity.setTrdTradeFeeDetail(ttfd2);
            }
            dto.setTrdTradeFeeDetailList(ttfdList);
            dto.setTrdProductDeal(entity);
//            if (StringUtil.equals(entity.getPs(), DictCodeList.交易方向_申购确认.getValue())) {
//                dto.getTrdProductDeal().setTradeAmt(null);
//            }
            dto.setTrdProductDealAdd(entity.getTrdProductDealAdd());
            // 划款指令
            List<StlPayOrdersDto> spodList = new ArrayList<>();
            for (BussTrdTransferOrderDto bttod : entity.getBussTrdTransferOrder()) {
                spodList.add(bttod);
                dto.setStlPayOrdersList(spodList);
            }
            baseTradeProductDealDtos.add(dto);
            finProductTradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
            //        委托代码不为空处理委托代码
            finProductTradeDto.getBaseTradeProductDealDtos().forEach(item->{
                String entrustId = StringUtil.isNotEmpty(entity.getEntrustId())?entity.getEntrustId():item.getTrdProductDealAdd()!=null?item.getTrdProductDealAdd().getEntrustId():null;
                if(StringUtil.isEmpty(item.getEntrustId())&&StringUtil.isNotEmpty(entrustId)){
                    item.setEntrustId(entrustId);
                }
            });

            // 撤销时，子组合需要调估值共通
            if (entity.getActPortfolioId() != null && !entity.getActPortfolioId().equals(entity.getPortfolioId())) {
                finProductTradeDto.getBaseTradeProductDealDtos().forEach(finProductTrade -> {
                    finProductTrade.setActPortfolioId(entity.getActPortfolioId());
                });
            }
            baseTradeProductDealCommService.execute(finProductTradeDto);
            if (StringUtil.isNotEmpty(entity.getActPortfolioId()) && !entity.getActPortfolioId().equals(entity.getPortfolioId())) {
                finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                        baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(entity.getActPortfolioId()));
                baseTradeProductDealCommService.insertPosition(finProductTradeDto);
                finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                        baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(entity.getPortfolioId()));
            }

//            basRiskMathCommService.insert(entity.getRiskTrialRetDto(), entity.getTradeId());
            restTemplate.postForObject(
                    ServiceApplication.TRADE_SERVICE_NAME + "/TrdProjectNetInvestTrade/doComplete",
                    entity, Integer.class);


            //add by zhangnian 20200720  start
            if (StringUtil.equals(entity.getPs(), DictCodeList.交易方向_赎回确认.getValue())) {
                doCompleteRedemption(entity);
            }
            //add by zhangnian 20200720 end

//            // 调用投管接口 add by renping 20200402
//            investInterfCommService.doSendTradeToInterf(entity.getTradeId(),DictCodeListExt3.平安资产类型_权益类资产.getValue());


        } else if (StringUtil.equals(entity.getOperateType(), "REVOKE")) {
            Map<String, Object> param = new HashMap<>();
            param.put("state", DictCodeList.流程状态_新建.getValue());
            param.put("tradeId", entity.getTradeId());
            List<WorkFlowDto> workflowIds = restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME + "/TrdFundInvestTrade/selectTradeAfter",
                    param, WorkFlowDto.class);
            if (workflowIds != null && workflowIds.size() > 0) {
                for (WorkFlowDto flow : workflowIds) {
                    try {
                        WorkFlowUtil.deleteProcess(flow.getSeqNo(), flow.getWorkflowId());
                    } catch (WorkFlowException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
//        调用接口  add by zhuyongkang 20191127 start
        List<String> tradeIds = new ArrayList<>();
        tradeIds.add(entity.getTradeId());
        try {
            restTemplate.postForObject(
                    ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/SettleTransAccountInterf/TransAccountInfo", tradeIds,
                    Map.class);
        }catch (Exception e){

        }
//        调用接口  add by zhuyongkang 20191127 end
    }

    /**
     * 提交流程启动时
     */
    @Override
    public void doInsert(TrdProjectNetInvestTradeConfirmDto entity, OperationType type) {
        UploadFile file = null;
        List<SysUploadFileInfo> syfiList = entity.getFileArray();
        for (SysUploadFileInfo sufi : syfiList) {
            file = fileAttachmentProcess.fetch(sufi.getSeqNo());
            String generateId = IDGenerator.generate();
            file.setFileNo(generateId);
            fileAttachmentProcess.upload(file);
            sufi.setSeqNo(generateId);
        }
        restTemplate.postForObject(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdProjectNetInvestTrade/doInsert", entity,
                Integer.class);
        if (StringUtil.equals(entity.getOperateType(), "submit")) {
            if (StringUtil.equals(entity.getPs(), DictCodeList.交易方向_赎回确认.getValue())) {
//                doInsertRedemption(entity);
            }
            FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
            finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
            List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
            BaseTradeProductDealDto dto = new BaseTradeProductDealDto();
            dto.setTrdProductDeal(entity);
            dto.setTrdProductDealAdd(entity.getTrdProductDealAdd());
            List<StlPayOrdersDto> spodList = new ArrayList<>();

            for (BussTrdTransferOrderDto bttod : entity.getBussTrdTransferOrder()) {
                spodList.add(bttod);
                dto.setStlPayOrdersList(spodList);
                baseTradeProductDealDtos.add(dto);
            }
            finProductTradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
            //        委托代码不为空处理委托代码
            finProductTradeDto.getBaseTradeProductDealDtos().forEach(item->{
                String entrustId = StringUtil.isNotEmpty(entity.getEntrustId())?entity.getEntrustId():item.getTrdProductDealAdd()!=null?item.getTrdProductDealAdd().getEntrustId():null;
                if(StringUtil.isEmpty(item.getEntrustId())&&StringUtil.isNotEmpty(entrustId)){
                    item.setEntrustId(entrustId);
                }
            });

            // 撤销时，子组合需要调估值共通
            if (entity.getActPortfolioId() != null && !entity.getActPortfolioId().equals(entity.getPortfolioId())) {
                finProductTradeDto.getBaseTradeProductDealDtos().forEach(finProductTrade -> {
                    finProductTrade.setActPortfolioId(entity.getActPortfolioId());
                });
            }
            baseTradeProductDealCommService.execute(finProductTradeDto);

            entity.setActPortfolioId(entity.getPortfolioId());
            entity.setPortfolioId(AllTradeCommonUtil.exchangePortfolio(entity.getPortfolioId()));
            if (StringUtil.isNotEmpty(entity.getActPortfolioId()) && !entity.getActPortfolioId().equals(entity.getPortfolioId())) {
                finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                        baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(entity.getActPortfolioId()));
                baseTradeProductDealCommService.insertPosition(finProductTradeDto);
                finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                        baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(entity.getPortfolioId()));
            }
        }
    }

    /**
     * 代办中提交流程启动时
     */
    @Override
    public void doUpdate(TrdProjectNetInvestTradeConfirmDto entity) {
        if (StringUtil.equals(entity.getOperateType(), "save")) {
            restTemplate.postForObject(
                    ServiceApplication.TRADE_SERVICE_NAME + "/TrdProjectNetInvestTrade/doUpdate", entity,
                    Integer.class);
        }
        else if (StringUtil.equals(entity.getOperateType(), "submit")) {
            prepareEntity(entity);
            entity.setPortfolioId(entity.getActPortfolioId());
            if(StringUtil.isNotEmpty(entity.getActPortfolioId()) && StringUtil.isNotEmpty(entity.getTrdProductDealAdd())) {
                entity.getTrdProductDealAdd().setPortfolioId(entity.getActPortfolioId());
            }
            restTemplate.postForObject(
                    ServiceApplication.TRADE_SERVICE_NAME + "/TrdProjectNetInvestTrade/doUpdate", entity,
                    Integer.class);
            if (StringUtil.equals(entity.getPs(), DictCodeList.交易方向_赎回确认.getValue())) {
//                doInsertRedemption(entity);
            }
            FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
            finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
            List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
            BaseTradeProductDealDto dto = new BaseTradeProductDealDto();
            dto.setTrdProductDeal(entity);
            dto.setTrdProductDealAdd(entity.getTrdProductDealAdd());
            // 划款指令
            List<StlPayOrdersDto> spodList = new ArrayList<>();
            for (BussTrdTransferOrderDto bttod : entity.getBussTrdTransferOrder()) {
                spodList.add(bttod);
                dto.setStlPayOrdersList(spodList);
                baseTradeProductDealDtos.add(dto);
            }
            finProductTradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
            //        委托代码不为空处理委托代码
            finProductTradeDto.getBaseTradeProductDealDtos().forEach(item->{
                String entrustId = StringUtil.isNotEmpty(entity.getEntrustId())?entity.getEntrustId():item.getTrdProductDealAdd()!=null?item.getTrdProductDealAdd().getEntrustId():null;
                if(StringUtil.isEmpty(item.getEntrustId())&&StringUtil.isNotEmpty(entrustId)){
                    item.setEntrustId(entrustId);
                }
            });

            // 撤销时，子组合需要调估值共通
            if (entity.getActPortfolioId() != null && !entity.getActPortfolioId().equals(entity.getPortfolioId())) {
                finProductTradeDto.getBaseTradeProductDealDtos().forEach(finProductTrade -> {
                    finProductTrade.setActPortfolioId(entity.getActPortfolioId());
                });
            }
            baseTradeProductDealCommService.execute(finProductTradeDto);
            if (StringUtil.isNotEmpty(entity.getActPortfolioId()) && !entity.getActPortfolioId().equals(entity.getPortfolioId())) {
                finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                        baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(StringUtil.equals(baseTradeProductDealDto1.getTrdProductDealAdd().getPortfolioId(), entity.getPortfolioId())?entity.getActPortfolioId():entity.getPortfolioId()));
                baseTradeProductDealCommService.insertPosition(finProductTradeDto);
                finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                        baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(entity.getPortfolioId()));
            }
        }
    }

    /**
     * 流程作废时删除业务流程表 <功能详细描述>
     *
     * @param workflowId
     * @see
     * @since 1.0
     */
    @Override
    public void doDelete(String workflowId) {
        baseTradeProductDealCommService.delete(workflowId);
        restTemplate.getForObject(ServiceApplication.TRADE_SERVICE_NAME
                        + "/TrdProjectNetInvestTrade/doDelete?workflowId=" + workflowId,
                Integer.class);
    }

    /**
     * 查询反显数据 <功能详细描述>
     *
     * @param Tid
     * @return
     * @see
     * @since 1.0
     */
    public TrdProjectNetInvestTradeConfirmDto getTrdFundDto(String Tid) {
        return restTemplate.getForObject(ServiceApplication.TRADE_SERVICE_NAME
                        + "/TrdProjectNetInvestTrade/getTrdFundDto?tradId=" + Tid,
                TrdProjectNetInvestTradeConfirmDto.class);
    }

    /**
     * 查询单位净值 <功能详细描述>
     *
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal getPrice(Map<String, Object> map) {
        return restTemplate.postForObject(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdProjectNetInvestTrade/getPrice", map,
                BigDecimal.class);
    }

    /**
     * 获取资产代码的交易方式 <功能详细描述>
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Map<String, Object> getPurSpeed(Map<String, Object> param) {
        return restTemplate.postForObject(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdProjectNetInvestTrade/getPurSpeed", param,
                Map.class);
    }

    /**
     * 插入交易到账信息 <功能详细描述>
     *
     * @param entity
     * @see
     * @since 1.0
     */
    public void doInsertRedemption(TrdProjectNetInvestTradeConfirmDto entity) {
        UploadFile file = null;
        List<SysUploadFileInfo> syfiList = entity.getFileArray();
        for (SysUploadFileInfo sufi : syfiList) {
            file = fileAttachmentProcess.fetch(sufi.getSeqNo());
            String generateId = IDGenerator.generate();
            file.setFileNo(generateId);
            fileAttachmentProcess.upload(file);
            sufi.setSeqNo(generateId);
        }
        TrdProjectNetInvestTradeConfirmDto redEntity = new TrdProjectNetInvestTradeConfirmDto();
        BeanUtil.copyPropertiesIgnoreNull(redEntity, entity);
        redEntity.setbTradeId(entity.getTradeId());
        if (baseGetBusinessId != null) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            codeParameter.setCommonBusinessType(DictCodeListExt3.共通业务类型_权益类资产投资交易);
            redEntity.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
        }
        else {
            redEntity.setTradeId(IDGenerator.generate());
        }
        redEntity.setPs(DictCodeList.交易方向_赎回到账.getValue());
        String actportfolioId = entity.getActPortfolioId();
        prepareEntity(redEntity);
        if(StringUtil.isNotEmpty(actportfolioId)) {
            redEntity.setActPortfolioId(actportfolioId);
        }
        restTemplate.postForObject(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdProjectNetInvestTrade/doInsertRedemption",
                redEntity, void.class);
    }

    /**
     * 查询业务流程表交易到账信息 <功能详细描述>
     *
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    public TrdProjectNetInvestTradeConfirmDto getTrdFundDtoForAccount(String tradeId) {
        return restTemplate.getForObject(ServiceApplication.TRADE_SERVICE_NAME
                        + "/TrdProjectNetInvestTrade/getTrdFundDtoForAccount?tradeId="
                        + tradeId,
                TrdProjectNetInvestTradeConfirmDto.class);
    }

    /**
     * 查询业务流程表中的划款指令 <功能详细描述>
     *
     * @param bizCode
     * @return
     * @see
     * @since 1.0
     */
    public List<BussTrdTransferOrderBase> selectOrdersByBizCode(String bizCode) {
        return restTemplate.getForList(ServiceApplication.TRADE_SERVICE_NAME
                        + "/TrdProjectNetInvestTrade/selectOrdersByBizCode?bizCode="
                        + bizCode,
                BussTrdTransferOrderBase.class);
    }
    /**
     *
     * 反显费用明细
     * <功能详细描述>
     * @param tradId
     * @return
     * @see
     * @since 1.0
     */
    public Map<String, Object> getTradeRefrence(String tradId){
        return restTemplate.getForObject(ServiceApplication.TRADE_SERVICE_NAME
                + "/TrdProjectNetInvestTrade/getTradeRefrence?tradId="+ tradId,Map.class);
    }
    /**
     * 处理基金投资交易自定义dto <功能详细描述>
     *
     * @param dto
     * @see
     * @since 1.0
     */
    private void prepareEntity(TrdProjectNetInvestTradeConfirmDto dto) {
        dto.setActPortfolioId(dto.getPortfolioId());
        dto.setPortfolioId(AllTradeCommonUtil.exchangePortfolio(dto.getPortfolioId()));

        // 一体化对应etliu start
        dto.setFinprodType2(DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
        // 一体化对应etliu end

//        dto.setTradeAmt(dto.getPrinAmt());
        dto.setYield(ArithmeticUtil.divide(dto.getYield(), new BigDecimal(100)));
        dto.setExerYield(ArithmeticUtil.divide(dto.getExerYield(), new BigDecimal(100)));
        dto.setBranch(Long.valueOf(CacheUtil.getSysParam("noBranchNoValue")));
        dto.setIsCancel(DictCodeList.是否_否.getValue());
        dto.setBusiType(DictCodeListExt3.共通业务类型_权益类资产投资交易.getValue());
        dto.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        dto.setIsClean(DictCodeList.是否_是.getValue());
        dto.setFeeAmt(dto.getFeeAmtPay());
        if(StringUtil.isNotEmpty(dto.getRedCfmDate()) &&
                StringUtil.equals(DictCodeList.交易方向_赎回到账.getValue(), dto.getPs())){
            dto.setSettleDate(dto.getRedCfmDate());
        }else {
            dto.setSettleDate(dto.getTradeDate());
        }
        dto.setIssuePartyId(CacheUtil.getSysParam(BussinessConstant.ISSUE_PARTY_ID));
        if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue())
                || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回到账.getValue())) {
            BigDecimal redemptionFee = dto.getRedemptionFee();
            if (redemptionFee == null) {
                redemptionFee = BigDecimal.ZERO;
            }
            BigDecimal hostingFee = dto.getHostingFee();
            if (hostingFee == null) {
                hostingFee = BigDecimal.ZERO;
            }
            dto.setFeeAmtPay(redemptionFee.add(hostingFee));
            if (dto.getFeeAmtPay().compareTo(BigDecimal.ZERO) == 0) {
                dto.setFeeAmtPay(null);
            }
            dto.setFeeAmt(dto.getFeeAmtPay());
        }
        BigDecimal channelFee = dto.getChannelFee();
        if (channelFee == null) {
            channelFee = BigDecimal.ZERO;
        }
        dto.setFeeAmtPay(ArithmeticUtil.doAdd(dto.getFeeAmtPay(), channelFee));
        dto.setFeeAmt(ArithmeticUtil.doAdd(dto.getFeeAmt(), channelFee));

        TrdInvestPersonInfo002Dto personInfo = dto.getTrdInvestPersonInfo();
        if (personInfo != null) {
            dto.setTrader(personInfo.getTrader());
            dto.setCounterId(personInfo.getCounterId());
            dto.setIsOutTrade(personInfo.getIsOutTrade());
            dto.setCounterTrader(personInfo.getCounterTrader());
            dto.setCounterContact(personInfo.getContactInfo());
        }
        // 金融产品交易附属表
        TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
        trdProductDealAdd.setRedCfmDate(dto.getRedCfmDate());
        trdProductDealAdd.setPurCfmDate(dto.getTradeDate());
        trdProductDealAdd.setTradeId(dto.getTradeId());
        trdProductDealAdd.setPortfolioId(dto.getActPortfolioId());
        trdProductDealAdd.setEntrustId(dto.getEntrustId());
        //trdProductDealAdd.setProdId(dto.getFinprodId());
        trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
        dto.setTrdProductDealAdd(trdProductDealAdd);
        // 组合交易表
        List<PtlTrade> ptlTrades = new ArrayList<>();
        // 组合交易表
        PtlTrade ptlTrade = new PtlTrade();
        ptlTrade.setPortfolioId(dto.getPortfolioId());
        ptlTrade.setBusiNo(dto.getTradeId());
        ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        ptlTrade.setBusiId(dto.getFinprodId());
        ptlTrade.setBusiType(DictCodeListExt3.共通业务类型_权益类资产投资交易.getValue());
        ptlTrade.setSettleDate(dto.getSettleDate());
        ptlTrades.add(ptlTrade);
        if (!dto.getPortfolioId().equals(dto.getActPortfolioId())) {
            // 组合交易表
            PtlTrade ptlTrade1 = new PtlTrade();
            ptlTrade1.setPortfolioId(dto.getActPortfolioId());
            ptlTrade1.setBusiNo(dto.getTradeId());
            ptlTrade1.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
            ptlTrade1.setBusiId(dto.getFinprodId());
            ptlTrade1.setBusiType(DictCodeListExt3.共通业务类型_权益类资产投资交易.getValue());
            ptlTrade1.setSettleDate(dto.getSettleDate());
            ptlTrades.add(ptlTrade1);
        }
        dto.setPtlTrade(ptlTrades);
        // 划款指令
        if (StringUtil.equals(DictCodeList.交易方向_申购确认.getValue(), dto.getPs())) {
            List<BussTrdTransferOrderBase> bttob = bussSettleOrderCommService.selectOrdersByBizCode(
                    dto.getbTradeId());
            if (bttob != null && bttob.size() > 0) {
                List<BussTrdTransferOrderDto> bttodList = new ArrayList<>();
                BussTrdTransferOrderDto bttod = new BussTrdTransferOrderDto();
                BeanUtil.copyPropertiesIgnoreNull(bttod, bttob.get(0));
                bttodList.add(bttod);
                dto.setBussTrdTransferOrder(bttodList);
            }
        }
        if (dto.getBussTrdTransferOrder() != null && dto.getBussTrdTransferOrder().size() > 0) {
            dto.getBussTrdTransferOrder().get(0).setTradeId(dto.getTradeId());
            dto.setBussTrdTransferOrder(
                    bussTrdTransferOrderService.save(dto.getBussTrdTransferOrder()));
            dto.getBussTrdTransferOrder().get(0).setIsTotalTrade(DictCodeList.是否_否.getValue());
        }
        // 金融产品交易扩展
        PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
        paTrdProductDeal.setTradeId(dto.getTradeId());
        String paBussType = null;

        if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())) {
            paBussType = DictCodeListExt3.平安业务类型_权益类资产_申购申请.getValue();
        }
        else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购确认.getValue())) {
            paBussType = DictCodeListExt3.平安业务类型_权益类资产_申购确认.getValue();
        }
        else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回.getValue())) {
            paBussType = DictCodeListExt3.平安业务类型_权益类资产_赎回申请.getValue();
        }
        else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue())) {
            paBussType = DictCodeListExt3.平安业务类型_权益类资产_赎回确认.getValue();
        }
        else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回到账.getValue())){
            paBussType = DictCodeListExt3.平安业务类型_权益类资产_赎回到账.getValue();
        }
        paTrdProductDeal.setPaBussType(paBussType);
        paTrdProductDeal.setDeptCode(personInfo==null?null:personInfo.getTraderDept());
        dto.setPaTrdProductDeal(paTrdProductDeal);
    }

    @Override
    public void doBack(TrdProjectNetInvestTradeConfirmDto entity) {
        AllTradeCommonUtil.deletePosition(entity.getCancelRemark(), entity.getTradeId());
        if (StringUtil.equals(entity.getPs(), DictCodeList.交易方向_赎回确认.getValue())) {
            deleteAccountTrdIdinwfl(entity);
        }
    }

    @Override
    public void doRevoke(TrdProjectNetInvestTradeConfirmDto entity) {
        AllTradeCommonUtil.deletePosition(entity.getCancelRemark(), entity.getTradeId());
        if (StringUtil.equals(entity.getPs(), DictCodeList.交易方向_赎回确认.getValue())) {
            deleteAccountTrdIdinwfl(entity);
        }
    }

    /**
     *
     * 交易到账撤销
     * <功能详细描述>
     * @param entity
     * @return
     * @see
     * @since 1.0
     */
    public void deleteAccountTrdIdinwfl(TrdProjectNetInvestTradeConfirmDto entity){
        restTemplate.postForObject(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdProjectNetInvestTrade/doDeleteRedemption",
                entity, void.class);
    }

    /**
     * 赎回到账
     * @param entity
     */
    public void doCompleteRedemption(TrdProjectNetInvestTradeConfirmDto entity){
        UploadFile file = null;
        List<SysUploadFileInfo> syfiList = entity.getFileArray();
        for (SysUploadFileInfo sufi : syfiList) {
            file = fileAttachmentProcess.fetch(sufi.getSeqNo());
            String generateId = IDGenerator.generate();
            file.setFileNo(generateId);
            fileAttachmentProcess.upload(file);
            sufi.setSeqNo(generateId);
        }
        TrdProjectNetInvestTradeConfirmDto redEntity = new TrdProjectNetInvestTradeConfirmDto();
        BeanUtil.copyPropertiesIgnoreNull(redEntity, entity);
        redEntity.setbTradeId(entity.getTradeId());
        if (baseGetBusinessId != null) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            codeParameter.setCommonBusinessType(DictCodeListExt3.共通业务类型_权益类资产投资交易);
            redEntity.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
        }
        else {
            redEntity.setTradeId(IDGenerator.generate());
        }
        redEntity.setPs(DictCodeList.交易方向_赎回到账.getValue());
        String actportfolioId = entity.getActPortfolioId();
        prepareEntity(redEntity);
        if(StringUtil.isNotEmpty(actportfolioId)) {
            redEntity.setActPortfolioId(actportfolioId);
        }
        doComplete(redEntity,OperationType.NEW);
    }
}
