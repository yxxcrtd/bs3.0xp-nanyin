/*
 * 文件名：TrdFundInvestTrade003Service.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：xiexinting
 * 修改时间：2018年11月8日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.fund.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.workflow.engine.WorkflowContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.system.base.dao.SysUploadFileInfoMapper;
import com.joyintech.tams.common.base.dao.FinProductAddMapper;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.settle.BussSettleOrderCommService;
import com.joyintech.tams.component.business.settle.dto.BussSettleOrderParameterDto;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.trade.fund.dto.TrdFundInvestTradeConfirmDto;
import com.joyintech.tams.invest.trade.fund.dto.TrdFundInvestTradeDto;
import com.joyintech.tams.invest.trade.fund.dto.WorkFlowDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.entity.FlowEntity;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author xiexinting
 * @version 1.0 2018年11月8日
 * @see TrdFundInvestTrade003Service
 * @since 1.0
 */
@Service
public class TrdFundInvestTrade003Service extends BaseService<TrdFundInvestTradeConfirmDto> implements BaseProcessService<TrdFundInvestTradeConfirmDto> {

    /**
     * 附件信息共通Service
     */
    @Autowired(required = false)
    private FileAttachmentProcess fileAttachmentProcess;

    /**
     * SysUploadFileInfoMapper
     */
    @Autowired
    private SysUploadFileInfoMapper uploadFileInfoMapper;

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
     * finProductAddMapper
     */
    @Autowired(required = false)
    private FinProductAddMapper finProductAddMapper;

    /**
     * 日期相关
     */
    @Autowired
    private DateCalService dateCalService;

    /**
     * 查询待结转份额
     * 
     * @param map
     * @return
     */
    public BigDecimal getcarryAmt(Map<String, Object> map)
        throws Exception {
        Date carryDate = DateUtil.parse((String)map.get("carryDate"));
        String calId = (String)map.get("cid");
        if (StringUtil.isNotEmpty(calId)) {
            List<String> holidayList = dateCalService.getDealCalendarHoliday(DateUtil.parse(DateUtil.format(carryDate)),
                DateUtil.addDay(DateUtil.parse(DateUtil.format(carryDate)), 15), calId);
            String tradeDay = dateCalService.calculationTradingDayForBefore(DateUtil.format(carryDate), 1, holidayList, "1");
            map.put("carryDate", tradeDay);
        }
        map.put("tradeDate", DateUtil.format(carryDate));
        if (!StringUtil.isNotEmpty((String)map.get("secAcctId"))) {
            map.put("secAcctId", "999999");
        }
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdFundInvestTrade/getcarryAmt", map,
            BigDecimal.class);
    }

    private void setChannelFee(TrdFundInvestTradeConfirmDto entity) {
    	//赎回到账  使用赎回确认数据
    	if(!StringUtil.equals(entity.getPs(), DictCodeList.交易方向_赎回到账.getValue())){
            if (StringUtil.isNotEmpty(entity.getChlId()) && entity.getChannelAmt() != null
                && entity.getChannelAmt().compareTo(BigDecimal.ZERO) > 0) {
                if (StringUtil.equals(DictCodeListExt2.收取方式_回款前扣除.getValue(), entity.getCollectType())) {
                    entity.setFeeAmtPay(ArithmeticUtil.doAdd(entity.getFeeAmtPay(), entity.getChannelAmt()));
                    entity.setFeeAmt(entity.getFeeAmtPay());
                }
                else {
                    entity.setFeeAmtUnpay((ArithmeticUtil.doAdd(entity.getFeeAmtUnpay(), entity.getChannelAmt())));
                    entity.setFeeAmt(ArithmeticUtil.doAdd(entity.getFeeAmtPay(), entity.getChannelAmt()));
                }

            }
            else {
                entity.setFeeAmt(entity.getFeeAmtPay());
            }

            // 佣金
            if (entity.getIsPayWithTrade() != null && StringUtil.equals(entity.getIsPayWithTrade(), DictCodeList.是否_否.getValue())) {
                entity.setFeeAmtUnpay(ArithmeticUtil.doAdd(entity.getFeeAmtUnpay(), entity.getCommissionFee()));
            }
            // 默认为是
            else {
                entity.setFeeAmtPay(ArithmeticUtil.doAdd(entity.getFeeAmtPay(), entity.getCommissionFee()));
            }
            entity.setFeeAmt(ArithmeticUtil.doAdd(entity.getFeeAmt(), entity.getCommissionFee()));
        
    	}
    }

    /**
     * 确认审批结束
     */
    @Override
    public void doComplete(TrdFundInvestTradeConfirmDto entity, OperationType type) {
        if (StringUtil.equals(entity.getSingle(), "01")) {
            entity.setOperateType("submit");
        }
        if (StringUtil.equals(entity.getOperateType(), "submit")) {
            if (StringUtil.isEmpty(entity.getActPortfolioId()) || entity.getActPortfolioId().equals(entity.getPortfolioId())) {
                entity.setActPortfolioId(entity.getPortfolioId());
                entity.setPortfolioId(AllTradeCommonUtil.exchangePortfolio(entity.getPortfolioId()));
            }
            UploadFile file = null;
            List<SysUploadFileInfo> syfiList = entity.getFileArray();
            for (SysUploadFileInfo sufi : syfiList) {
                file = fileAttachmentProcess.fetch(sufi.getSeqNo());
                if (file != null) {
                    String generateId = IDGenerator.generate();
                    file.setFileNo(generateId);
                    // fileAttachmentProcess.upload(file);
                    com.joyintech.system.base.entity.SysUploadFileInfo uploadFileInfo = new com.joyintech.system.base.entity.SysUploadFileInfo();
                    uploadFileInfo.setSeqNo(file.getFileNo());
                    uploadFileInfo.setFileName(FileNameUtil.formatFileName(file.getTargetFile().getName()));
                    uploadFileInfo.setPath(file.getTargetFile().getPath());
                    uploadFileInfo.setBusinessId(entity.getTradeId());
                    uploadFileInfoMapper.insert(uploadFileInfo);
                    sufi.setSeqNo(generateId);
                    sufi.setFileName(FileNameUtil.formatFileName(file.getTargetFile().getName()));
                    sufi.setPath(file.getTargetFile().getPath());
                }
            }
            // 实时赎回自动生成确认交易时，指令需要后台生成
            if (StringUtil.equals(entity.getTrmFundType(), DictCodeListExt2.交易所基金类型_实时申赎货币基金.getValue())
                && StringUtil.equals(entity.getPs(), DictCodeList.交易方向_赎回确认.getValue())) {
                BussSettleOrderParameterDto param = new BussSettleOrderParameterDto();
                param.setDeal(entity);
                param.setIsTotalTrade(DictCodeList.是否_否.getValue());
                param.setPortfolioId(entity.getPortfolioId());
                BussTrdTransferOrderDto ret = bussTrdTransferOrderService.generateOrder(param);
                if (ret != null) {
                	//赎回本方账号未赋值 add by yzg 20200423 start
                	if(StringUtil.equals(entity.getPs(), DictCodeList.交易方向_赎回确认.getValue())) {
                		ret.setMyAcctNo(ret.getPayAcctNo());
                	}
                	//赎回本方账号未赋值 add by yzg 20200423 end
                    entity.setBussTrdTransferOrder(Arrays.asList(new BussTrdTransferOrderDto[] {ret}));
                }
            }

            entity.getTrdProductDealAdds().forEach(trdProductDealAdd -> {
                FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
                finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_有效.getValue());
                List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
                BaseTradeProductDealDto dto = new BaseTradeProductDealDto();

                // <!-- 通道交易 新增 强成西 2019/03/26 start -->
                setChannelFee(entity);
                // <!-- 通道交易 新增 强成西 2019/03/26 end -->

                if (entity.getTradeId().equals(trdProductDealAdd.getTradeId())) {
                    dto.setTrdProductDeal(entity);
                    //费用信息入库
                    dto.setTrdTradeFeeDetailList(entity.getTrdTradeFeeDetailList());
                }
                else {
                    dto.setTrdProductDeal(entity.getTrdProductDeal());
                }
                // if (StringUtil.equals(entity.getPs(), DictCodeList.交易方向_申购确认.getValue())) {
                // dto.getTrdProductDeal().setTradeAmt(null);
                // }
                dto.setTrdProductDealAdd(trdProductDealAdd);
                // 划款指令
                List<StlPayOrdersDto> spodList = new ArrayList<>();
                for (BussTrdTransferOrderDto bttod : entity.getBussTrdTransferOrder()) {
                    spodList.add(bttod);
                    dto.setStlPayOrdersList(spodList);
                }
                // 场内基金改造 画面交易金额即实际交易金额 delete by 20191117 renping start
                // // 交易金额修正(卖出及赎回确认时候)
                // if (StringUtil.equals(dto.getTrdProductDeal().getPs(), DictCodeList.交易方向_卖出.getValue())
                // || StringUtil.equals(dto.getTrdProductDeal().getPs(), DictCodeList.交易方向_赎回确认.getValue())) {
                // BigDecimal tradeAmt = new BigDecimal(0);
                // if (dto.getTrdProductDeal().getPrinAmt() != null) {
                // tradeAmt = tradeAmt.add(dto.getTrdProductDeal().getPrinAmt());
                // }
                // if (dto.getTrdProductDeal().getIntAmt() != null) {
                // tradeAmt = tradeAmt.add(dto.getTrdProductDeal().getIntAmt());
                // }
                // if (dto.getTrdProductDeal().getFeeAmtPay() != null) {
                // tradeAmt = tradeAmt.subtract(dto.getTrdProductDeal().getFeeAmtPay());
                // }
                // dto.getTrdProductDeal().setTradeAmt(tradeAmt);
                // }
                // 场内基金改造 画面交易金额即实际交易金额 delete by 20191117 renping end
                baseTradeProductDealDtos.add(dto);
                if (entity.getTrdFinProductDealTotal() != null) {
                    finProductTradeDto.setNeedSum(true);
                }
                finProductTradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
                // basRiskMathCommService.insert(entity.getRiskTrialRetDto(), entity.getTradeId());
                // 委托代码不为空处理委托代码
                if (StringUtil.isNotEmpty(entity.getEntrustId())) {
                    dto.setEntrustId(entity.getEntrustId());
                }

                baseTradeProductDealCommService.execute(finProductTradeDto);

                if (StringUtil.isNotEmpty(entity.getActPortfolioId())
                    && !entity.getActPortfolioId().equals(entity.getPortfolioId())) {
                    finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                        baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(
                            entity.getActPortfolioId()));
                    baseTradeProductDealCommService.insertPosition(finProductTradeDto);
                    finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                        baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(
                            entity.getPortfolioId()));
                }
            });

            restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdFundInvestTrade/doComplete", entity,
                Integer.class);

            if (StringUtil.equals(entity.getPs(), DictCodeList.交易方向_赎回确认.getValue())) {
                doInsertRedemption(entity);
            }
        }
        else if (StringUtil.equals(entity.getOperateType(), "REVOKE")) {
            Map<String, Object> param = new HashMap<>();
            param.put("state", DictCodeList.流程状态_新建.getValue());
            param.put("tradeId", entity.getTradeId());
            List<WorkFlowDto> workflowIds = restTemplate.postForList(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdFundInvestTrade/selectTradeAfter", param, WorkFlowDto.class);
            if (workflowIds != null && workflowIds.size() > 0) {
                for (WorkFlowDto flow : workflowIds) {
                    try {
                        WorkFlowUtil.deleteProcess(flow.getSeqNo(), flow.getWorkflowId());
                    }
                    catch (WorkFlowException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
        // 调接口 by 赵东东 20191206 start
        try {
            List<String> tradeIds = new ArrayList<String>();
            tradeIds.add(entity.getTradeId());
            restTemplate.postForObject(
                ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/SettleTransAccountInterf/TransAccountInfo", tradeIds,
                Map.class);
        }
        catch (Exception e) {

        }
        // 调接口 by 赵东东 20191206 end
    }

    /**
     * 交易及存续期交易，保存进待办，点批量审批时候需要报错。 {@inheritDoc}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.MASERT)
    public String checkForSubmit(FlowEntity entity, boolean isMutiApproval) {
        if (isMutiApproval && StringUtil.equals(entity.getCreateUser(), entity.getNextUser())) {
            return entity.getTradeId() + "待确认数据不能做批量审批。请点击链接进入详细页面点击提交处理。";
        }
        return null;
    }

    /**
     * 提交流程启动时
     */
    @Override
    public void doInsert(TrdFundInvestTradeConfirmDto entity, OperationType type) {
        UploadFile file = null;
        List<SysUploadFileInfo> syfiList = entity.getFileArray();
        for (SysUploadFileInfo sufi : syfiList) {
            file = fileAttachmentProcess.fetch(sufi.getSeqNo());
            String generateId = IDGenerator.generate();
            file.setFileNo(generateId);
            fileAttachmentProcess.upload(file);
            sufi.setSeqNo(generateId);
        }

        if (StringUtil.equals(entity.getOperateType(), "submit")) {
            entity.setActPortfolioId(entity.getPortfolioId());
            entity.setPortfolioId(AllTradeCommonUtil.exchangePortfolio(entity.getPortfolioId()));
            restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdFundInvestTrade/doInsert", entity, Integer.class);

            entity.getTrdProductDealAdds().forEach(trdProductDealAdd -> {
                FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
                finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
                BaseTradeProductDealDto dto = new BaseTradeProductDealDto();
                if (entity.getTradeId().equals(trdProductDealAdd.getTradeId())) {
                    dto.setTrdProductDeal(entity);
                }
                else {
                    dto.setTrdProductDeal(dto.getTrdProductDeal());
                }
                dto.setTrdProductDealAdd(trdProductDealAdd);
                List<StlPayOrdersDto> spodList = new ArrayList<>();

                for (BussTrdTransferOrderDto bttod : entity.getBussTrdTransferOrder()) {
                    spodList.add(bttod);
                    dto.setStlPayOrdersList(spodList);
                    baseTradeProductDealDtos.add(dto);
                }
                finProductTradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
                // 委托代码不为空处理委托代码
                if (StringUtil.isNotEmpty(entity.getEntrustId())) {
                    dto.setEntrustId(entity.getEntrustId());
                }

                if (entity.getTrdFinProductDealTotal() != null) {
                    finProductTradeDto.setNeedSum(true);
                }
                baseTradeProductDealCommService.execute(finProductTradeDto);

                if (!StringUtil.equals(entity.getPortfolioId(), entity.getActPortfolioId())) {
                    finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                        baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(
                            entity.getActPortfolioId()));
                    baseTradeProductDealCommService.insertPosition(finProductTradeDto);
                }
            });

        }
    }

    /**
     * 代办中提交流程启动时
     */
    @Override
    public void doUpdate(TrdFundInvestTradeConfirmDto entity) {
        if (StringUtil.equals(entity.getOperateType(), "save")) {
            entity.setActPortfolioId(entity.getPortfolioId());
            entity.setPortfolioId(AllTradeCommonUtil.exchangePortfolio(entity.getPortfolioId()));
            restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdFundInvestTrade/doUpdate", entity,
                Integer.class);
        }
        if (StringUtil.equals(entity.getSingle(), "01")) {
            entity.setOperateType("submit");
        }
        if (StringUtil.equals(entity.getOperateType(), "submit")) {
            // doDelete(entity.getWorkflowId());
            prepareEntity(entity, false);
            ProcessMark newEntity;
            try {
                newEntity = WorkFlowUtil.getBusinessObjectFromRequest(TrdFundInvestTradeConfirmDto.class, true);
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

            if (StringUtil.isNotEmpty(entity.getActPortfolioId())) {
                entity.getTrdProductDealAdds().forEach(
                    trdProductDealAdd -> trdProductDealAdd.setPortfolioId(entity.getActPortfolioId()));
            }
            restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdFundInvestTrade/doUpdate", entity,
                Integer.class);

            entity.getTrdProductDealAdds().forEach(trdProductDealAdd -> {
                FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
                finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
                BaseTradeProductDealDto dto = new BaseTradeProductDealDto();
                if (entity.getTradeId().equals(trdProductDealAdd.getTradeId())) {
                    dto.setTrdProductDeal(entity);
                }
                else {
                    dto.setTrdProductDeal(entity.getTrdProductDeal());
                }
                dto.setTrdProductDealAdd(trdProductDealAdd);
                // 划款指令
                List<StlPayOrdersDto> spodList = new ArrayList<>();
                for (BussTrdTransferOrderDto bttod : entity.getBussTrdTransferOrder()) {
                    spodList.add(bttod);
                    dto.setStlPayOrdersList(spodList);
                    baseTradeProductDealDtos.add(dto);
                }
                finProductTradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
                // 委托代码不为空处理委托代码
                if (StringUtil.isNotEmpty(entity.getEntrustId())) {
                    dto.setEntrustId(entity.getEntrustId());
                }

                if (entity.getTrdFinProductDealTotal() != null) {
                    finProductTradeDto.setNeedSum(true);
                }
                baseTradeProductDealCommService.execute(finProductTradeDto);

                if (!StringUtil.equals(entity.getPortfolioId(), entity.getActPortfolioId())) {
                    finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                        baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(
                            StringUtil.equals(baseTradeProductDealDto1.getTrdProductDealAdd().getPortfolioId(),
                                entity.getPortfolioId()) ? entity.getActPortfolioId() : entity.getPortfolioId()));
                    baseTradeProductDealCommService.insertPosition(finProductTradeDto);
                }
            });
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
    }

    /**
     * 查询反显数据 <功能详细描述>
     * 
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    public TrdFundInvestTradeConfirmDto getTrdFundDto(Map<String, Object> params) {
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdFundInvestTrade/getTrdFundDto", params,
            TrdFundInvestTradeConfirmDto.class);
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
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdFundInvestTrade/getPrice", map,
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
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdFundInvestTrade/getPurSpeed", param,
            Map.class);
    }

    /**
     * 插入交易到账信息 <功能详细描述>
     * 
     * @param entity
     * @see
     * @since 1.0
     */
    public void doInsertRedemption(TrdFundInvestTradeConfirmDto entity) {
        TrdFundInvestTradeConfirmDto redEntity = new TrdFundInvestTradeConfirmDto();
        BeanUtil.copyPropertiesIgnoreNull(redEntity, entity);
        redEntity.setbTradeId(entity.getTradeId());
        if (baseGetBusinessId != null) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_货币基金赎回);
            redEntity.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
        }
        else {
            redEntity.setTradeId(IDGenerator.generate());
        }
        List<SysUploadFileInfo> syfiList = entity.getFileArray();
        for (SysUploadFileInfo sufi : syfiList) {
            // file = fileAttachmentProcess.fetch(sufi.getSeqNo());
            String generateId = IDGenerator.generate();
            // file.setFileNo(generateId);
            // fileAttachmentProcess.upload(file);
            sufi.setSeqNo(generateId);
            com.joyintech.system.base.entity.SysUploadFileInfo uploadFileInfo = new com.joyintech.system.base.entity.SysUploadFileInfo();
            uploadFileInfo.setSeqNo(generateId);
            uploadFileInfo.setFileName(FileNameUtil.formatFileName(sufi.getFileName()));
            uploadFileInfo.setPath(sufi.getPath());
            uploadFileInfo.setBusinessId(redEntity.getTradeId());
            uploadFileInfoMapper.insert(uploadFileInfo);
        }
        redEntity.setPs(DictCodeList.交易方向_赎回到账.getValue());
        String actportfolioId = entity.getActPortfolioId();
        if (StringUtil.isNotEmpty(actportfolioId)) {
            redEntity.setPortfolioId(actportfolioId);
        }
		//正确显示赎回费  过滤通道费
        if(StringUtil.isNotEmpty(entity.getChannelAmt())){
        	if (StringUtil.equals(DictCodeListExt2.收取方式_回款前扣除.getValue(), entity.getCollectType())&&StringUtil.isNotEmpty(entity.getFeeAmtPay())) {
        		entity.setFeeAmtPay(ArithmeticUtil.subtract(entity.getFeeAmtPay(), entity.getChannelAmt()));
            }
        }
        prepareEntity(redEntity, false);
        if (StringUtil.isNotEmpty(entity.getChlId()) || StringUtil.isNotEmpty(entity.getSecManageAcctId())) {
            FinProductAdd payType = finProductAddMapper.selectByPrimaryKey(entity.getChlId());

            // 场内基金改造 modify by renping 20191117 start
            if ((payType != null && DictCodeListExt2.划款方式_一次性划款.getValue().equals(payType.getPayType()))
                || StringUtil.isNotEmpty(entity.getSecManageAcctId())
                || StringUtil.equals(entity.getTrmFundType(), DictCodeListExt2.交易所基金类型_实时申赎货币基金.getValue())) {
                // 场内基金改造 modify by renping 20191117 end
                doComplete(redEntity, OperationType.NEW);
                return;
            }
        }

        restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdFundInvestTrade/doInsertRedemption", redEntity,
            void.class);
    }

    /**
     * 查询业务流程表交易到账信息 <功能详细描述>
     * 
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    public TrdFundInvestTradeConfirmDto getTrdFundDtoForAccount(String tradeId) {
        return restTemplate.getForObject(
            ServiceApplication.TRADE_SERVICE_NAME + "/TrdFundInvestTrade/getTrdFundDtoForAccount?tradeId=" + tradeId,
            TrdFundInvestTradeConfirmDto.class);
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
        return restTemplate.getForList(
            ServiceApplication.TRADE_SERVICE_NAME + "/TrdFundInvestTrade/selectOrdersByBizCode?bizCode=" + bizCode,
            BussTrdTransferOrderBase.class);
    }

    /**
     * 处理基金投资交易自定义dto <功能详细描述>
     * 
     * @param dto
     * @see
     * @since 1.0
     */
    public void prepareEntity(TrdFundInvestTradeConfirmDto dto, boolean 还原组合代码) {
        dto.setActPortfolioId(dto.getPortfolioId());
        dto.setPortfolioId(AllTradeCommonUtil.exchangePortfolio(dto.getPortfolioId()));

        // 一体化对应etliu start
        dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        // 一体化对应etliu end

        // if(dto.getFeeAmtPay() != null){
        // if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购确认.getValue())){
        // dto.setTradeAmt(dto.getPrinAmt().add(dto.getFeeAmtPay()));
        // }else{
        // dto.setTradeAmt(dto.getPrinAmt().subtract(dto.getFeeAmtPay()));
        // }
        // }else{
        // dto.setTradeAmt(dto.getPrinAmt());
        // }
        dto.setYield(ArithmeticUtil.divide(dto.getYield(), new BigDecimal(100)));
        dto.setExerYield(ArithmeticUtil.divide(dto.getExerYield(), new BigDecimal(100)));
        dto.setBranch(Long.valueOf(CacheUtil.getSysParam("noBranchNoValue")));
        dto.setIsCancel(DictCodeList.是否_否.getValue());
        dto.setBusiType(DictCodeList.共通业务类型_基金类资产交易.getValue());
        dto.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        dto.setIsClean(DictCodeList.是否_是.getValue());
        if (StringUtil.isNotEmpty(dto.getRedCfmDate()) && StringUtil.equals(DictCodeList.交易方向_赎回到账.getValue(), dto.getPs())) {
            dto.setSettleDate(dto.getRedCfmDate());
        }
        else {
            dto.setSettleDate(dto.getTradeDate());
        }
        dto.setIssuePartyId(CacheUtil.getSysParam(BussinessConstant.ISSUE_PARTY_ID));
        TrdInvestPersonInfo002Dto personInfo = dto.getTrdInvestPersonInfo();
        if (personInfo != null) {
            dto.setTrader(personInfo.getTrader());
            dto.setCounterId(personInfo.getCounterId());
            // dto.setIsOutTrade(personInfo.getIsOutTrade());
            if (StringUtil.equals(DictCodeList.是否_否.getValue(), personInfo.getIsOutTrade())) {
                dto.setIsOutTrade(personInfo.getIsOutTrade());
            }
            else {
                dto.setIsOutTrade(DictCodeList.是否_是.getValue());
            }
            dto.setCounterTrader(personInfo.getCounterTrader());
            dto.setCounterContact(personInfo.getContactInfo());
        }

        // 金融产品交易附属表
        List<TrdProductDealAdd> trdProductDealAdds = new ArrayList<>();
        TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
        trdProductDealAdd.setRedCfmDate(dto.getRedCfmDate());
        trdProductDealAdd.setPurCfmDate(dto.getTradeDate());
        trdProductDealAdd.setTradeId(dto.getTradeId());
        trdProductDealAdd.setPortfolioId(dto.getPortfolioId());
        trdProductDealAdd.setEntrustId(dto.getEntrustId());
        // trdProductDealAdd.setProdId(dto.getFinprodId());
        trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
        trdProductDealAdd.setrFinprodId(dto.getrFinprodId());
        trdProductDealAdds.add(trdProductDealAdd);
        dto.setTrdProductDealAdds(trdProductDealAdds);
        // 组合交易表
        List<PtlTrade> ptlTrades = new ArrayList<>();
        PtlTrade ptlTrade = new PtlTrade();
        ptlTrade.setPortfolioId(dto.getPortfolioId());
        ptlTrade.setBusiNo(dto.getTradeId());
        ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        ptlTrade.setBusiId(dto.getFinprodId());
        ptlTrade.setBusiType(DictCodeList.共通业务类型_基金类资产交易.getValue());
        ptlTrade.setSettleDate(dto.getSettleDate());
        ptlTrades.add(ptlTrade);
        if (!StringUtil.equals(dto.getPortfolioId(), dto.getActPortfolioId())) {
            PtlTrade ptlTrade1 = new PtlTrade();
            BeanUtil.copyPropertiesIgnoreNull(ptlTrade1, ptlTrade);
            ptlTrade1.setPortfolioId(dto.getActPortfolioId());
            ptlTrades.add(ptlTrade1);
        }
        dto.setPtlTrade(ptlTrades);
        // 划款指令
        if (StringUtil.equals(DictCodeList.交易方向_申购确认.getValue(), dto.getPs())) {
            List<BussTrdTransferOrderBase> bttob = bussSettleOrderCommService.selectOrdersByBizCode(dto.getbTradeId());
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
            dto.setBussTrdTransferOrder(bussTrdTransferOrderService.save(dto.getBussTrdTransferOrder()));
            dto.getBussTrdTransferOrder().get(0).setIsTotalTrade(DictCodeList.是否_否.getValue());
        }
        // 金融产品交易扩展
        List<PaTrdProductDeal> paTrdProductDeals = new ArrayList<>();
        PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
        paTrdProductDeal.setTradeId(dto.getTradeId());
        String paBussType = null;
        if (StringUtil.isNotEmpty(dto.getType6())) {
            if (StringUtil.equals(dto.getType6(), DictCodeList.基金类资产类型_货币基金.getValue())) {
                if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())) {
                    paBussType = DictCodeListExt1.平安业务类型_货币基金申购申请.getValue();
                }
                else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购确认.getValue())) {
                    paBussType = DictCodeListExt1.平安业务类型_货币基金申购确认.getValue();
                }
                else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回.getValue())) {
                    paBussType = DictCodeListExt1.平安业务类型_货币基金赎回申请.getValue();
                }
                else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue())) {
                    paBussType = DictCodeListExt1.平安业务类型_货币基金赎回确认.getValue();
                }
                else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回到账.getValue())) {
                    paBussType = DictCodeListExt1.平安业务类型_货币基金赎回到账.getValue();
                }
                else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_认购确认.getValue())) {
                    paBussType = DictCodeListExt3.平安业务类型_货币基金认购确认.getValue();
                }
            }
            else if (StringUtil.equals(dto.getType6(), DictCodeList.基金类资产类型_非货币基金.getValue())) {
                if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())) {
                    paBussType = DictCodeListExt1.平安业务类型_非货币基金申购申请.getValue();
                }
                else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购确认.getValue())) {
                    paBussType = DictCodeListExt1.平安业务类型_非货币基金申购确认.getValue();
                }
                else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回.getValue())) {
                    paBussType = DictCodeListExt1.平安业务类型_非货币基金赎回申请.getValue();
                }
                else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue())) {
                    paBussType = DictCodeListExt1.平安业务类型_非货币基金赎回确认.getValue();
                }
                else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回到账.getValue())) {
                    paBussType = DictCodeListExt1.平安业务类型_非货币基金赎回到账.getValue();
                }
                else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_认购确认.getValue())) {
                    paBussType = DictCodeListExt3.平安业务类型_非货币基金认购确认.getValue();
                }
            }
        }
        paTrdProductDeal.setPaBussType(paBussType);
        dto.setTgBusiType(paBussType);
        paTrdProductDeal.setDeptCode(personInfo.getTraderDept());
        paTrdProductDeals.add(paTrdProductDeal);
        dto.setPaTrdProductDeals(paTrdProductDeals);
        dto.setTgBusiType(paBussType);

        if (StringUtil.equals(dto.getPs(), DictCodeListExt2.交易方向_转换确认.getValue())) {
            基金转换特殊处理(dto);
        }
        else {
            setFeeInfo(dto);
        }
        if (还原组合代码) {
            dto.setPortfolioId(dto.getActPortfolioId());
        }
    }

    /**
     * dto是赎回确认交易 再复制一份是申购确认交易
     * 
     * @param dto
     */
    private void 基金转换特殊处理(TrdFundInvestTradeConfirmDto dto) {
        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        BeanUtil.copyPropertiesIgnoreNull(trdFinProductDealTotal, dto);
        dto.setTrdFinProductDealTotal(trdFinProductDealTotal);
        trdFinProductDealTotal.setPortfolioId(dto.getActPortfolioId());

        TrdProductDeal 转换申购确认deal = new TrdProductDeal();
        BeanUtil.copyPropertiesIgnoreNull(转换申购确认deal, dto);
        BussinessCodeParameter codeParameter = new BussinessCodeParameter();
        codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_货币基金申购);
        转换申购确认deal.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
        dto.setTrdProductDeal(转换申购确认deal);
        转换申购确认deal.setFinprodId(dto.getrFinprodId());
        setChangeFeeInfo(dto);

        // 交易方向 数据字典缓存.交易方向.转换赎回确认
        dto.setPs(DictCodeListExt2.交易方向_转换赎回确认.getValue());
        // 交易日 画面.确认日
        // 交割日 画面.确认日
        // 交易份额 画面.交易份额
        dto.setShareAmt(dto.getShareAmt());
        // 交易本金 货基：画面.交易份额
        // 非货基：画面.交易份额*转出基金单位净值
        if (StringUtil.equals(dto.getOutFinProfitType(), BussinessDictCodeList.收益类型_货币.getValue())) {
            dto.setPrinAmt(dto.getShareAmt());
        }
        else {
            dto.setPrinAmt(ArithmeticUtil.multiply(dto.getShareAmt(), dto.getUnitCprice()));
        }

        // 全价总额 交易本金+画面.待结转份额
        dto.setFpriceAmt(ArithmeticUtil.doAdd(dto.getPrinAmt(), dto.getIntAmt()));
        // 交易金额 全价总额+画面.赎回费
        dto.setTradeAmt(ArithmeticUtil.subtract(dto.getFpriceAmt(), dto.getFeeAmtPay()));

        // 随交易支付费用 画面.赎回费+通道费 根据通道类型与回款无关的则不加，回款前扣除的则加
        BigDecimal 随交易支付费用 = BigDecimal.ZERO;
        BigDecimal 不随交易支付费用 = BigDecimal.ZERO;
        BigDecimal 申购费 = BigDecimal.ZERO;
        for (TrdTradeFeeDetail trdTradeFeeDetail : dto.getTrdTradeFeeDetailList()) {
            if (DictCodeListExt1.一次性费用类型_赎回费.getValue().equals(trdTradeFeeDetail.getFeeType())) {
                随交易支付费用 = ArithmeticUtil.doAdd(随交易支付费用, trdTradeFeeDetail.getFeeAmt());
            }
            else if (DictCodeListExt1.一次性费用类型_通道费.getValue().equals(trdTradeFeeDetail.getFeeType())
                     && DictCodeList.是否_是.getValue().equals(trdTradeFeeDetail.getIsPayWithTrade())) {
                随交易支付费用 = ArithmeticUtil.doAdd(随交易支付费用, trdTradeFeeDetail.getFeeAmt());
            }
            else if (DictCodeListExt1.一次性费用类型_通道费.getValue().equals(trdTradeFeeDetail.getFeeType())
                     && DictCodeList.是否_否.getValue().equals(trdTradeFeeDetail.getIsPayWithTrade())) {
                不随交易支付费用 = trdTradeFeeDetail.getFeeAmt();
            }
            else if (DictCodeListExt1.一次性费用类型_申购费.getValue().equals(trdTradeFeeDetail.getFeeType())) {
                申购费 = trdTradeFeeDetail.getFeeAmt();
            }
        }
        dto.setFeeAmtPay(随交易支付费用);
        // 未随交易支付费用 通道费 根据通道类型与回款无关的则为未随交易支付费用
        dto.setFeeAmtUnpay(不随交易支付费用);
        dto.setFeeAmt(ArithmeticUtil.doAdd(随交易支付费用, 不随交易支付费用));

        // 单位净价 画面.转入基金单位净值
        转换申购确认deal.setUnitCprice(dto.getUnitCpriceIn());
        // 交易方向 数据字典缓存.交易方向.转换申购确认
        转换申购确认deal.setPs(DictCodeListExt2.交易方向_转换申购确认.getValue());
        // 交易日 画面.确认日
        // 交割日 画面.确认日

        // 交易本金 货基：画面.交易份额+画面.待结转份额-赎回费-申购费-通道费
        if (StringUtil.equals(dto.getInFinProfitType(), BussinessDictCodeList.收益类型_货币.getValue())) {
            转换申购确认deal.setPrinAmt(ArithmeticUtil.subtract(
                ArithmeticUtil.subtract(ArithmeticUtil.doAdd(dto.getShareAmt(), dto.getIntAmt()), dto.getFeeAmt()), 申购费));
        }
        else {
            // 非货基：画面.交易份额*转出基金单位净值-赎回费-申购费-通道费
            转换申购确认deal.setPrinAmt(ArithmeticUtil.subtract(
                ArithmeticUtil.subtract(ArithmeticUtil.multiply(dto.getShareAmt(), dto.getUnitCprice()), dto.getFeeAmt()),
                申购费));
        }
        转换申购确认deal.setShareAmt(dto.getChangeInShareAmt());
        转换申购确认deal.setUnitCprice(dto.getUnitCpriceIn());
        // 利息总额
        转换申购确认deal.setIntAmt(BigDecimal.ZERO);
        // 全价总额 同交易本金
        转换申购确认deal.setFpriceAmt(转换申购确认deal.getPrinAmt());
        // 交易金额 全价总额+画面.申购费
        转换申购确认deal.setTradeAmt(ArithmeticUtil.doAdd(转换申购确认deal.getFpriceAmt(), 申购费));
        // 随交易支付费用 画面.申购费
        转换申购确认deal.setFeeAmtPay(申购费);
        // 未随交易支付费用 0
        转换申购确认deal.setFeeAmtUnpay(BigDecimal.ZERO);

        转换申购确认deal.setFeeAmt(申购费);

        转换申购确认deal.setPs(DictCodeListExt2.交易方向_转换申购确认.getValue());

        List<TrdProductDealAdd> adds = new ArrayList<>();
        dto.getTrdProductDealAdds().forEach(trdProductDealAdd -> {
            TrdProductDealAdd add = new TrdProductDealAdd();
            BeanUtil.copyPropertiesIgnoreNull(add, trdProductDealAdd);
            add.setTradeId(转换申购确认deal.getTradeId());
            add.setrFinprodId(dto.getFinprodId());
            adds.add(add);
        });
        dto.getTrdProductDealAdds().addAll(adds);

        List<PaTrdProductDeal> paAdds = new ArrayList<>();
        dto.getPaTrdProductDeals().forEach(pa -> {
            PaTrdProductDeal paDeal = new PaTrdProductDeal();
            BeanUtil.copyPropertiesIgnoreNull(paDeal, pa);
            paDeal.setTradeId(转换申购确认deal.getTradeId());
            paAdds.add(paDeal);
        });
        dto.getPaTrdProductDeals().addAll(paAdds);

        List<PtlTrade> ptlTrades = new ArrayList<>();
        dto.getPtlTrade().forEach(ptlTrade -> {
            PtlTrade ptlTrade1 = new PtlTrade();
            BeanUtil.copyPropertiesIgnoreNull(ptlTrade1, ptlTrade);
            ptlTrade1.setBusiNo(转换申购确认deal.getTradeId());
            ptlTrades.add(ptlTrade1);
        });
        dto.getPtlTrade().addAll(ptlTrades);
    }

    private void setChangeFeeInfo(TrdFundInvestTradeConfirmDto entity) {
        List<TrdTradeFeeDetail> ttfdList = new ArrayList<>();
        // 基金转换只有申购费和赎回费
        if (entity.getFeeAmtPayPurchase() != null && entity.getFeeAmtPayPurchase().compareTo(BigDecimal.ZERO) > 0) {
            TrdTradeFeeDetail ttfd = new TrdTradeFeeDetail();
            ttfd.setTradeId(entity.getTrdProductDeal().getTradeId());
            ttfd.setFeeType(DictCodeListExt1.一次性费用类型_申购费.getValue());
            ttfd.setIsPayWithTrade(DictCodeList.是否_是.getValue());
            ttfd.setFeeAmt(entity.getFeeAmtPayPurchase());
            ttfdList.add(ttfd);
        }

        if (entity.getFeeAmtPay() != null && entity.getFeeAmtPay().compareTo(BigDecimal.ZERO) > 0) {
            TrdTradeFeeDetail ttfd1 = new TrdTradeFeeDetail();
            ttfd1.setTradeId(entity.getTradeId());
            ttfd1.setFeeType(DictCodeListExt1.一次性费用类型_赎回费.getValue());
            ttfd1.setIsPayWithTrade(DictCodeList.是否_是.getValue());
            ttfd1.setFeeAmt(entity.getFeeAmtPay());
            ttfdList.add(ttfd1);
        }

        if ((StringUtil.isNotEmpty(entity.getChlId()) && entity.getChannelAmt() != null
             && entity.getChannelAmt().compareTo(BigDecimal.ZERO) > 0)) {
            TrdTradeFeeDetail detail = new TrdTradeFeeDetail();
            detail.setTradeId(entity.getTradeId());
            detail.setChlFinprodId(entity.getChlId());
            detail.setFeeAmt(entity.getChannelAmt());
            detail.setFeeType(DictCodeListExt1.一次性费用类型_通道费.getValue());
            if (StringUtil.equals(DictCodeListExt2.收取方式_回款前扣除.getValue(), entity.getCollectType())) {
                detail.setIsPayWithTrade(DictCodeList.是否_是.getValue());
            }
            else {
                detail.setIsPayWithTrade(DictCodeList.是否_否.getValue());
            }
            ttfdList.add(detail);
        }
        entity.setTrdTradeFeeDetailList(ttfdList);
    }

    private void setFeeInfo(TrdFundInvestTradeConfirmDto entity) {
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
        else if (StringUtil.equals(entity.getPs(), DictCodeList.交易方向_认购确认.getValue())) {
            TrdTradeFeeDetail ttfd = new TrdTradeFeeDetail();
            ttfd.setTradeId(entity.getTradeId());
            ttfd.setFeeType(DictCodeListExt1.一次性费用类型_认购费.getValue());
            ttfd.setIsPayWithTrade(DictCodeList.是否_是.getValue());
            ttfd.setFeeAmt(entity.getFeeAmtPay());
            ttfdList.add(ttfd);
        }
        else if (StringUtil.equals(entity.getPs(), DictCodeList.交易方向_赎回确认.getValue())||StringUtil.equals(entity.getPs(), DictCodeList.交易方向_赎回到账.getValue())) {
            TrdTradeFeeDetail ttfd = new TrdTradeFeeDetail();
            ttfd.setTradeId(entity.getTradeId());
            ttfd.setFeeType(DictCodeListExt1.一次性费用类型_赎回费.getValue());
            ttfd.setIsPayWithTrade(DictCodeList.是否_是.getValue());
            ttfd.setFeeAmt(entity.getFeeAmtPay());
            ttfdList.add(ttfd);

            if ((StringUtil.isNotEmpty(entity.getChlId()) && entity.getChannelAmt() != null
                 && entity.getChannelAmt().compareTo(BigDecimal.ZERO) > 0)) {
                TrdTradeFeeDetail detail = new TrdTradeFeeDetail();
                detail.setTradeId(entity.getTradeId());
                detail.setChlFinprodId(entity.getChlId());
                detail.setFeeAmt(entity.getChannelAmt());
                detail.setFeeType(DictCodeListExt1.一次性费用类型_通道费.getValue());
                if (StringUtil.equals(DictCodeListExt2.收取方式_回款前扣除.getValue(), entity.getCollectType())) {
                    detail.setIsPayWithTrade(DictCodeList.是否_是.getValue());
                }
                else {
                    detail.setIsPayWithTrade(DictCodeList.是否_否.getValue());
                }
                entity.setTrdTradeFeeDetail(detail);
                ttfdList.add(detail);
            }
        }

        // 场内基金改造 add by renping 20191117 start
        if (!StringUtil.equals(entity.getPs(), DictCodeList.交易方向_赎回到账.getValue()) && entity.getCommissionFee() != null
            && entity.getCommissionFee().compareTo(BigDecimal.ZERO) > 0) {
            // 2.2 费用明细表(佣金)
            TrdTradeFeeDetail trdTradeFeeDetail = new TrdTradeFeeDetail();
            trdTradeFeeDetail.setTradeId(entity.getTradeId());
            // 交易费用明细表.费用类型 = “佣金”
            trdTradeFeeDetail.setFeeType(DictCodeListExt1.一次性费用类型_佣金.getValue());
            // 交易费用明细表.是否随交易支付
            if (StringUtil.isEmpty(entity.getIsPayWithTrade())) {
                trdTradeFeeDetail.setIsPayWithTrade(DictCodeListExt1.是否_是.getValue());
            }
            else {
                trdTradeFeeDetail.setIsPayWithTrade(entity.getIsPayWithTrade());
            }
            // 交易费用明细表.交易金额 = 交易.佣金
            trdTradeFeeDetail.setFeeAmt(entity.getCommissionFee());
            ttfdList.add(trdTradeFeeDetail);
        }
        // 场内基金改造 add by renping 20191117 end

        entity.setTrdTradeFeeDetailList(ttfdList);
    }

    @Override
    public void doBack(TrdFundInvestTradeConfirmDto entity) {
        AllTradeCommonUtil.deletePosition(entity.getRevokeReason(), entity.getTradeId());
    }

    @Override
    public void doRevoke(TrdFundInvestTradeConfirmDto entity) {
        AllTradeCommonUtil.deletePosition(entity.getRevokeReason(), entity.getTradeId());
    }
    
    @Autowired
    private CommonBusinessService commonBusinessService;

    @Override
    public Map<String, Object> getRuntimeVariables(ProcessMark entity, String taskId)
        throws WorkFlowException {
        Map<String, Object> variables = new HashMap<>();
        TrdFundInvestTradeConfirmDto dto = this.getBussinessEntity(entity);
        if (taskId != null) {
            BigDecimal amt = dto.getTradeAmt();
            if (amt == null || amt.compareTo(BigDecimal.ZERO) <= 0) {
                amt = dto.getShareAmt();
            }
            try {
/*                return commonBusinessService.是否需要上级审批(dto.getCounterId(), amt, LoginUserUtil.getUserName(),
                    DictCodeList.平安资产类型_基金.getValue(), dto.getType6(), dto.getTradeMarket(), dto.getPs()
                    // 资产类型改为授权类型 add by renping 2019-08-06 start
                    , dto.getFinprodId()
                // 资产类型改为授权类型 add by renping 2019-08-06 end
                );*/
          Map<String, Object> params = CommonBusinessService.setParams(dto.getCounterId(), amt,LoginUserUtil.getUserName(),
              DictCodeList.平安资产类型_基金.getValue(), dto.getType6(), dto.getTradeMarket(), dto.getPs()
              , dto.getFinprodId() ,entity.getWorkflowId());
          params.put("riskTrialRetDto", dto.getRiskTrialRetDto());
          return commonBusinessService.是否需要上级审批(params);
            }
            catch (WorkFlowException e) {
                // 未设置授权或设置成不授权时，抛出异常，当前用户只能退回
                throw new WorkFlowException(e);
            }
        }
        else {
            return null;
        }
        // return null;
    }
}
