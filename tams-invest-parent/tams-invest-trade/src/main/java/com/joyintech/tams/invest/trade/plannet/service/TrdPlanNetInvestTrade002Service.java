/*
 * 文件名：TrdPlanNetInvestTrade002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年12月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.plannet.service;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.component.business.common.service.BasRiskMathCommService;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.risk.service.RiskCreditListCommonValidatorService;
import com.joyintech.tams.invest.common.threadservice.JasperUtil;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.common.trade.service.MultiApproveTradeCheckService;
import com.joyintech.tams.invest.trade.fund.dto.WorkFlowDto;
import com.joyintech.tams.invest.trade.plannet.dto.TrdPlanNetInvestTradeConfirmDto;
import com.joyintech.tams.invest.trade.plannet.dto.TrdPlanNetInvestTradeDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.entity.FlowEntity;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 计划类（净值型）新建service 〈功能详细描述〉
 * 
 * @author xiexinting
 * @version 1.0 2018年12月3日
 * @see TrdPlanNetInvestTrade002Service
 * @since 1.0
 */
@Service
public class TrdPlanNetInvestTrade002Service extends BaseService<TrdPlanNetInvestTradeDto> implements BaseProcessService<TrdPlanNetInvestTradeDto> {

    /**
     * 划款指令业务组件service
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    /**
     * 日期相关
     */
    @Autowired
    private DateCalService dateCalService;

    /**
     * 金融产品交易共通
     */
    @Autowired(required = false)
    private BaseTradeProductDealCommService baseTradeProductDealCommService;

    /**
     * 风险试算组件Service
     */
    @Autowired(required = false)
    private BasRiskMathCommService basRiskMathCommService;

    @Autowired
    private TrdPlanNetInvestTrade003Service trdPlanNetInvestTrade003Service;
    /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

    /**
     * 交易及存续期交易，保存进待办，点批量审批时候检查service
     */
    @Autowired(required = false)
    private MultiApproveTradeCheckService multiApproveTradeCheckService;
    
    @Autowired
    private RiskCreditListCommonValidatorService riskCreditListCommonValidatorService;

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
     * {@inheritDoc}
     */
    @Override
    public void doComplete(TrdPlanNetInvestTradeDto entity, OperationType type) {
        FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
        if (StringUtil.equals(entity.getOperateType(), "submit")) {
            prepareEntity(entity);
            finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_有效.getValue());
            List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
            BaseTradeProductDealDto dto = new BaseTradeProductDealDto();
            dto.setTrdProductDeal(entity);
            dto.setTrdProductDealAdd(entity.getTrdProductDealAdd());
            List<StlPayOrdersDto> spodList = new ArrayList<>();

            for (BussTrdTransferOrderDto bttod : entity.getBussTrdTransferOrder()) {
                spodList.add(bttod);
                dto.setStlPayOrdersList(spodList);
            }
            baseTradeProductDealDtos.add(dto);
            finProductTradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
            //        委托代码不为空处理委托代码
            if(StringUtil.isNotEmpty(entity.getEntrustId())){
                dto.setEntrustId(entity.getEntrustId());
            }

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

            //basRiskMathCommService.insert(entity.getRiskTrialRetDto(), entity.getTradeId());
            restTemplate.postForObject(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanNetInvestTrade/doComplete",
                entity, Integer.class);
            
//            // 一体化对应etliu start
//            // 所有交易生效后调用的共通方法
//            for (BaseTradeProductDealDto baseDto : baseTradeProductDealDtos) {
//                AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
//                inputDto.setProdid(entity.getPortfolioId());
//                inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
//                inputDto.setTrdProductDeal(baseDto.getTrdProductDeal());
//                AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
//            }
//            // 一体化对应etliu end
            if(StringUtil.equals(entity.getPs(),DictCodeList.交易方向_申购.getValue()) || StringUtil.equals(entity.getPs(),DictCodeList.交易方向_赎回.getValue())){
            	// 单组合申请交易，流程结束才生成确认流程
//            	if (StringUtil.isEmpty(entity.getTotleTradeId())) {
	                TrdPlanNetInvestTradeConfirmDto confirmDto = new TrdPlanNetInvestTradeConfirmDto();
	                BeanUtil.copyPropertiesIgnoreNull(confirmDto,entity);
	                //确认交易的确认日根据申请交易的实际确认日（申请交易确认日允许修改）生成交易 add by xueying 20200723 start
	                confirmDto.setTradeDate(entity.getPurCfmDate());
                    //确认交易的确认日根据申请交易的实际确认日（申请交易确认日允许修改）生成交易 add by xueying 20200723 end
	                ProcessParameter<TrdPlanNetInvestTradeConfirmDto> workFlowPatameter = getWorkFlowPatameter(confirmDto);
                    confirmDto.setCreateUser(entity.getCreateUser());
	                try {
	                    WorkFlowUtil.startProcess(workFlowPatameter.getWorkFlowParameter(),"","", confirmDto.getCreateUser(),OperationType.NEW,false,confirmDto);
	                }catch (Exception e) {
	                    e.printStackTrace();
	                }
//            	}
            }

            jasperExecute(entity);

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
            
            //删除授信占用
            if (null != entity.getTradeId() && !"".equals(entity.getTradeId())){
                riskCreditListCommonValidatorService.deleteCredit(entity.getTradeId());
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

    @Autowired
    private JasperUtil jasperUtil;

    public void jasperExecute(TrdPlanNetInvestTradeDto entity) {
        if("001".equals(CacheUtil.getSysParam("CUSTOMER_BANK_CODE"))){
            //生成划款指令附件:调用自定义接口项目生成Jasper报表 add by zhangJin 2019-07-29 start
            TrdProductDeal trdProductDeal = new TrdProductDeal();
            trdProductDeal.setTradeId(entity.getTradeId());
            trdProductDeal.setPs(entity.getPs());
            trdProductDeal.setFinprodId(entity.getFinprodId());
            jasperUtil.jasperExecute(Arrays.asList(trdProductDeal), restTemplate);
            //生成划款指令附件:调用自定义接口项目生成Jasper报表 add by zhangJin 2019-07-29 end
        }
    }

    /**
     * 设置流程参数
     * @param dto
     * @return
     */
    public ProcessParameter<TrdPlanNetInvestTradeConfirmDto> getWorkFlowPatameter(TrdPlanNetInvestTradeConfirmDto dto) {
        if (StringUtil.equals(dto.getPs(),DictCodeList.交易方向_申购.getValue())){
            dto.setPs(DictCodeList.交易方向_申购确认.getValue());
        }else if (StringUtil.equals(dto.getPs(),DictCodeList.交易方向_赎回.getValue())){
            dto.setPs(DictCodeList.交易方向_赎回确认.getValue());
        }
        dto.setbTradeId(dto.getTradeId());
        dto.setWorkflowId(null);
        dto.setOperateType("save");
        /*if (dto.getSettleSpeed() == 1 || dto.getSettleSpeed() == 2) {*/
        //int speedCount = dto.getSettleSpeed().intValue();
        int speedCount = Integer.parseInt(dto.getSettleSpreed());
        Date tradeDate = dto.getTradeDate();
        //申购申请和赎回申请申购确认日可修改，已修改的日期生成确认交易 update by xueying 20200723 satrt
       /* List<String> holidayList = dateCalService.getDealCalendarHoliday(DateUtil.parse(DateUtil.format(tradeDate)),DateUtil.addDay(DateUtil.parse(DateUtil.format(tradeDate)), 15),dto.getCalendarId());
        Date tradeDay = dateCalService.calculationTradingDay(DateUtil.parse(DateUtil.format(tradeDate)), speedCount, holidayList, "1");
        dto.setTradeDate(tradeDay);*/
        //申购申请和赎回申请申购确认日可修改，已修改的日期生成确认交易 update by xueying 20200723 end
        /*}*/
        if (baseGetBusinessId != null) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_计划类资产_净值型_交易);
            if(StringUtil.equals(dto.getType7(), DictCodeList.计划类资产二级分类_公募计划.getValue())){
                codeParameter.setType1(DictCodeList.计划类资产二级分类_公募计划);
            }else if(StringUtil.equals(dto.getType7(), DictCodeList.计划类资产二级分类_委托债权.getValue())){
                codeParameter.setType1(DictCodeList.计划类资产二级分类_委托债权);
            }else if(StringUtil.equals(dto.getType7(), DictCodeList.计划类资产二级分类_委托权益.getValue())){
                codeParameter.setType1(DictCodeList.计划类资产二级分类_委托权益);
            }else if(StringUtil.equals(dto.getType7(), DictCodeList.计划类资产二级分类_自主管理.getValue())){
                codeParameter.setType1(DictCodeList.计划类资产二级分类_自主管理);
            }
            dto.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
        }
        else {
            dto.setTradeId(IDGenerator.generate());
        }
        ProcessParameter<TrdPlanNetInvestTradeConfirmDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdPlanNetInvestTradeConfirmDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId("TrdPlanNetInvestTrade003");
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            processParameter.setOperationType(OperationType.NEW);
        }
        else {
            processParameter.setOperationType(OperationType.MODIFY);
        }
        workFlowParameter.setPageId("TrdPlanNetInvestTrade003");
        workFlowParameter.setElementId("TrdPlanNetInvestTrade003_btn_save");
        workFlowParameter.setBusinessCode((entity) -> entity.getTradeId());
        workFlowParameter.setServiceName("trdPlanNetInvestTrade003Service");
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_计划类资产_净值型_交易.getValue());
        workFlowParameter.setComment(
            (entity) -> WorkFlowUtil.concatComment(getMessage("TrdPlanNetInvestTrade002.tradeId"),
                entity.getTradeId(),
                getMessage("TrdPlanNetInvestTrade002.finprodName"),
                entity.getFinprodName(),
                getMessage("TrdPlanNetInvestTrade002.tradeDate"),
                DateUtil.format(entity.getTradeDate())));
        dto.setPortfolioId(dto.getActPortfolioId());
        //根据实际的申购确认日生成交易 update by xueying 20200723 start
       // workFlowParameter.setCrtDate(tradeDay);
        workFlowParameter.setCrtDate(dto.getTradeDate());//根据实际的申购确认日生成交易
        //根据实际的申购确认日生成交易 update by xueying 20200723 end
        return processParameter;
    }
    
    /**
     * 提交流程启动时
     */
    @Override
    public void doInsert(TrdPlanNetInvestTradeDto entity, OperationType type) {
        restTemplate.postForObject(
            ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanNetInvestTrade/doInsert", entity,
            Integer.class);
        if (StringUtil.equals(entity.getOperateType(), "submit")) {
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
            }
            //BSTOT-1857 yzg 赎回指令持仓未变化 20200305 start
            baseTradeProductDealDtos.add(dto);
            //BSTOT-1857 yzg 赎回指令持仓未变化 20200305 end
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
    public void doUpdate(TrdPlanNetInvestTradeDto entity) {
        if (StringUtil.equals(entity.getOperateType(), "save")) {
            restTemplate.postForObject(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanNetInvestTrade/doUpdate", entity,
                Integer.class);
        }
        if (StringUtil.equals(entity.getOperateType(), "submit")) {
            prepareEntity(entity);
            restTemplate.postForObject(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanNetInvestTrade/doUpdate", entity,
                Integer.class);
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
            }
            //BSTOT-1857 yzg 赎回指令持仓未变化 20200305 start
            baseTradeProductDealDtos.add(dto);
            //BSTOT-1857 yzg 赎回指令持仓未变化 20200305 end
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
//        restTemplate.getForObject(ServiceApplication.TRADE_SERVICE_NAME
//                                  + "/TrdPlanNetInvestTrade/doDelete?workflowId=" + workflowId,
//            Integer.class);
    }

    /**
     * 资产代码下拉框 <功能详细描述>
     * 
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodId(Map<String, Object> params) {
        return restTemplate.postForPage(
            ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanNetInvestTrade/getFinprodId", params,
            SelectOptionDto.class);
    }

    /**
     * 查询组合可用现金 <功能详细描述>
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Map<String, Object> getProAvailableCash(Map<String, Object> param) {
        Date vdate = DateUtil.parse((String)param.get("vdate"));
        String calId = (String)param.get("calId");
        if (StringUtil.isNotEmpty(calId)) {
            List<String> holidayList = dateCalService.getDealCalendarHoliday(
                DateUtil.parse(DateUtil.format(vdate)),
                DateUtil.addDay(DateUtil.parse(DateUtil.format(vdate)), 15), calId);
            Date tradeDay = dateCalService.calculationTradingDay(
                DateUtil.parse(DateUtil.format(vdate)), 1, holidayList, "1");
            param.put("vdate1", DateUtil.format(tradeDay));
        }
        return restTemplate.postForObject(
            ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanNetInvestTrade/getProAvailableCash",
            param, Map.class);
    }

    /**
     * 查询组合可用持仓 <功能详细描述>
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal getPtlSecPosition(PtlSecPositionFlowDto dto) {
        dto.setHodingType(DictCodeList.持仓类型_可用.getValue());
        if (StringUtil.isNotEmpty(dto.getSecAcctId())) {
            dto.setChannel(true);
        }
        return restTemplate.postForObject(
            ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanNetInvestTrade/getPtlSecPosition",
            dto, BigDecimal.class);
    }

    /**
     * 查询反显数据 <功能详细描述>
     * 
     * @param Tid
     * @return
     * @see
     * @since 1.0
     */
    public TrdPlanNetInvestTradeDto getTrdFundDto(String Tid) {
        return restTemplate.getForObject(ServiceApplication.TRADE_SERVICE_NAME
                                         + "/TrdPlanNetInvestTrade/getTrdFundDto?tradId=" + Tid,
            TrdPlanNetInvestTradeDto.class);
    }
    /**
     * 
     * 查询委托可用金额
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Map<String, Object> selectEntrustAmt(Map<String, Object> param){
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanNetInvestTrade/selectEntrustAmt",
            param,Map.class);
    }
    /**
     * 查询投资委托DTO <功能详细描述>
     * 
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    public BaseEtrInvestInfoDto getEtrustDto(String entrustId) {
        return restTemplate.getForObject(ServiceApplication.TRADE_SERVICE_NAME
                                         + "/TrdPlanNetInvestTrade/getEtrustDto?entrustId="
                                         + entrustId,
            BaseEtrInvestInfoDto.class);
    }

    /**
     * 处理基金投资交易自定义dto <功能详细描述>
     * 
     * @param dto
     * @see
     * @since 1.0
     */
    private void prepareEntity(TrdPlanNetInvestTradeDto dto) {
        dto.setActPortfolioId(dto.getPortfolioId());
        dto.setPortfolioId(AllTradeCommonUtil.exchangePortfolio(dto.getPortfolioId()));
//        dto.setTradeAmt(dto.getPrinAmt());
        dto.setPrinAmt(dto.getTradeAmt());
        dto.setYield(ArithmeticUtil.divide(dto.getYield(), new BigDecimal(100)));
        dto.setExerYield(ArithmeticUtil.divide(dto.getExerYield(), new BigDecimal(100)));
        dto.setBranch(Long.valueOf(CacheUtil.getSysParam("noBranchNoValue")));
        dto.setIsCancel(DictCodeList.是否_否.getValue());
        dto.setBusiType(DictCodeList.共通业务类型_计划类资产_净值型_交易.getValue());
        dto.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        /*if (StringUtil.equals(dto.getSettleSpreed(), DictCodeList.清算速度_T_0.getValue())) {
            dto.setSettleSpeed((long)0);
        }
        else if (StringUtil.equals(dto.getSettleSpreed(),
            DictCodeList.清算速度_T_1.getValue())) {
            dto.setSettleSpeed((long)1);
        }*/
        dto.setSettleSpeed(Long.parseLong(StringUtil.isEmpty(dto.getSettleSpreed()) ? "0" : dto.getSettleSpreed()));
        if(StringUtil.isNotEmpty(dto.getRedCfmDate()) &&
                StringUtil.equals(DictCodeList.交易方向_赎回到账.getValue(), dto.getPs())){
            dto.setSettleDate(dto.getRedCfmDate());
        }else {
            dto.setSettleDate(dto.getTradeDate());
        }
        if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue()) || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())){
            dto.setIsClean(DictCodeList.是否_是.getValue());
            dto.setIssuePartyId(CacheUtil.getSysParam(BussinessConstant.ISSUE_PARTY_ID));
            if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())) {
                BigDecimal zero = new BigDecimal(0);
                BigDecimal redemptionFee = dto.getRedemptionFee();
                if (redemptionFee == null) {
                    redemptionFee = zero;
                }
                BigDecimal channelFee = dto.getChannelFee();
                if (channelFee == null) {
                    channelFee = zero;
                }
                BigDecimal hostingFee = dto.getHostingFee();
                if (hostingFee == null) {
                    hostingFee = zero;
                }
                dto.setFeeAmtPay(redemptionFee.add(channelFee.add(hostingFee)));
                if (dto.getFeeAmtPay().equals(zero)) {
                    dto.setFeeAmtPay(null);
                }
            }
            dto.setFeeAmt(dto.getFeeAmtPay());
        }
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
        
        if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())
        ||StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回.getValue())) {
            /*Map<String, Object> param = new HashMap<String, Object>();
            param.put("finprodId", dto.getFinprodId());
            Map<String, Object> result = trdPlanNetInvestTrade003Service.getPurSpeed(param);
            String speed = (String)result.get("SPEED");
            String calId = (String)result.get("CALID");
            if (StringUtil.equals(speed, "1") || StringUtil.equals(speed, "2")) {
                int speedCount = Integer.parseInt(speed);
                Date tradeDate = dto.getTradeDate();
                List<String> holidayList = dateCalService.getDealCalendarHoliday(
                    DateUtil.parse(DateUtil.format(tradeDate)),
                    DateUtil.addDay(DateUtil.parse(DateUtil.format(tradeDate)), 15), calId);
                Date tradeDay = dateCalService.calculationTradingDay(
                    DateUtil.parse(DateUtil.format(tradeDate)), speedCount, holidayList, "1");
                trdProductDealAdd.setPurCfmDate(tradeDay);
            }*/
            trdProductDealAdd.setPurCfmDate(dto.getPurCfmDate());
        }
        else {
            trdProductDealAdd.setPurCfmDate(dto.getTradeDate());
        }
        trdProductDealAdd.setRedCfmDate(dto.getRedCfmDate());
        trdProductDealAdd.setTradeId(dto.getTradeId());
        trdProductDealAdd.setPortfolioId(dto.getPortfolioId());
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
        ptlTrade.setBusiType(DictCodeList.共通业务类型_计划类资产_净值型_交易.getValue());
        ptlTrade.setSettleDate(dto.getSettleDate());
        ptlTrades.add(ptlTrade);
        if (!dto.getPortfolioId().equals(dto.getActPortfolioId())) {
            // 组合交易表
            PtlTrade ptlTrade1 = new PtlTrade();
            ptlTrade1.setPortfolioId(dto.getActPortfolioId());
            ptlTrade1.setBusiNo(dto.getTradeId());
            ptlTrade1.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
            ptlTrade1.setBusiId(dto.getFinprodId());
            ptlTrade1.setBusiType(DictCodeList.共通业务类型_计划类资产_净值型_交易.getValue());
            ptlTrade1.setSettleDate(dto.getSettleDate());
            ptlTrades.add(ptlTrade1);
        }
        dto.setPtlTrade(ptlTrades);
        // 划款指令
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
            paBussType = DictCodeListExt1.平安业务类型_计划类资产_净值型_申购申请.getValue();
        }
        else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购确认.getValue())) {
            paBussType = DictCodeListExt1.平安业务类型_计划类资产_净值型_申购确认.getValue();
        }
        else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回.getValue())) {
            paBussType = DictCodeListExt1.平安业务类型_计划类资产_净值型_赎回申请.getValue();
        }
        else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回确认.getValue())) {
            paBussType = DictCodeListExt1.平安业务类型_计划类资产_净值型_赎回确认.getValue();
        }
        paTrdProductDeal.setPaBussType(paBussType);
        paTrdProductDeal.setDeptCode(personInfo==null?null:personInfo.getTraderDept());
        dto.setPaTrdProductDeal(paTrdProductDeal);
    }

    @Autowired
    private CommonBusinessService commonBusinessService;

    @Override
    public Map<String, Object> getRuntimeVariables(ProcessMark entity, String taskId) throws WorkFlowException {
        Map<String, Object> variables = new HashMap<>();
        TrdPlanNetInvestTradeDto dto = this.getBussinessEntity(entity);
        if (taskId != null) {
            BigDecimal amt = dto.getPrinAmt();
            if (amt == null || amt.compareTo(BigDecimal.ZERO) <= 0) {
                amt = dto.getShareAmt();
            } 
            try {
//              return commonBusinessService.是否需要上级审批(dto.getCounterId(), dto.getPrinAmt(), LoginUserUtil.getUserName(),
//                      DictCodeList.平安资产类型_计划类资产_净值型.getValue(), dto.getType6(), dto.getTradeMarket(), dto.getPs()
//                      // 资产类型改为授权类型 add by renping 2019-08-06  start
//                      , dto.getFinprodId()
//                      // 资产类型改为授权类型 add by renping 2019-08-06  end
//                      );
              Map<String, Object> params = CommonBusinessService.setParams(dto.getCounterId(), 
  					//金额不存在的时候取份额 wangguiqi 20200416
                  	dto.getPrinAmt() != null ? dto.getPrinAmt():dto.getShareAmt(),LoginUserUtil.getUserName(),
                      DictCodeList.平安资产类型_计划类资产_净值型.getValue(), dto.getType6(), dto.getTradeMarket(), dto.getPs()
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
//        return null;
    }

    @Override
    public void doBack(TrdPlanNetInvestTradeDto entity) {
        AllTradeCommonUtil.deletePosition(entity.getCancelRemark(), entity.getTradeId());
        
        //删除授信占用
        if (null != entity.getTradeId() && !"".equals(entity.getTradeId()) && !StringUtil.isNotEmpty(entity.getCancelRemark())){
            riskCreditListCommonValidatorService.deleteCredit(entity.getTradeId());
        }
    }

    @Override
    public void doRevoke(TrdPlanNetInvestTradeDto entity) {
        AllTradeCommonUtil.deletePosition(entity.getCancelRemark(), entity.getTradeId());
        
        //删除授信占用
        if (null != entity.getTradeId() && !"".equals(entity.getTradeId()) && !StringUtil.isNotEmpty(entity.getCancelRemark())){
            riskCreditListCommonValidatorService.deleteCredit(entity.getTradeId());
        }
    }
    
    /**
     * 检验该净值型计划的持有金额与交易金额之和是否超过该资产标的的发行金额
     * @param dto
     * @return
     */
	public boolean checkIssueAmt(TrdPlanNetInvestTradeDto dto) {
		BigDecimal tradeAmt = dto.getTradeAmt();
		BigDecimal holdAmt = BigDecimal.ZERO;
		BigDecimal issueAmt = BigDecimal.ZERO;
		holdAmt = restTemplate.getForObject(ServiceApplication.TRADE_SERVICE_NAME
				+ "/TrdPlanNetInvestTrade/queryHoldAmt?finprodId=" + dto.getFinprodId(), BigDecimal.class);
		issueAmt = restTemplate.getForObject(ServiceApplication.TRADE_SERVICE_NAME
				+ "/TrdPlanNetInvestTrade/queryIssueAmt?finprodId=" + dto.getFinprodId(), BigDecimal.class);
		 if (holdAmt == null) {
	            holdAmt = BigDecimal.ZERO;
	        }
	        if (issueAmt == null) {
	            issueAmt = BigDecimal.ZERO;
	        }
			if (tradeAmt != null && tradeAmt.add(holdAmt).compareTo(issueAmt) > 0) {
				return true;
			}
			return false;
		}
	}
