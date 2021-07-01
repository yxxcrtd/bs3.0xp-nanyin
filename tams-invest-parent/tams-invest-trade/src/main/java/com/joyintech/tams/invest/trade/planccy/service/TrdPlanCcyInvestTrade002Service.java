/*
 * 文件名：TrdPlanCcyInvestTrade002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.planccy.service;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.component.business.common.service.BasRiskMathCommService;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.risk.service.RiskCreditListCommonValidatorService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.invest.trade.fund.dto.WorkFlowDto;
import com.joyintech.tams.invest.trade.planccy.dto.TrdPlanCcyInvestTradeConfirmDto;
import com.joyintech.tams.invest.trade.planccy.dto.TrdPlanCcyInvestTradeDto;
import com.joyintech.tams.invest.trade.plannet.dto.TrdPlanNetInvestTradeDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2019年1月7日
 * @see TrdPlanCcyInvestTrade002Service
 * @since 1.0
 */
@Service
public class TrdPlanCcyInvestTrade002Service extends BaseService<TrdPlanCcyInvestTradeDto> implements BaseProcessService<TrdPlanCcyInvestTradeDto> {

    /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;
    
    @Autowired
    private TrdPlanCcyInvestTrade003Service trdPlanCcyInvestTrade003Service;
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

    @Autowired
    private CommonBusinessService commonBusinessService;
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
    private RiskCreditListCommonValidatorService riskCreditListCommonValidatorService;
    /**
     * 工作流完成
     */
    @Override
    public void doComplete(TrdPlanCcyInvestTradeDto entity, OperationType type) {
        FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
        if(StringUtil.equals(entity.getOperateType(), "submit")){
            prepareEntity(entity);
            finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_有效.getValue());
            List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
            BaseTradeProductDealDto dto = new BaseTradeProductDealDto();
            dto.setTrdProductDeal(entity);
            dto.setTrdProductDealAdd(entity.getTrdProductDealAdd());
            List<StlPayOrdersDto> spodList = new ArrayList<>();
            
            for(BussTrdTransferOrderDto bttod : entity.getBussTrdTransferOrder()){
                spodList.add(bttod);
                dto.setStlPayOrdersList(spodList);
            }
            baseTradeProductDealDtos.add(dto);
            finProductTradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
            //        委托代码不为空处理委托代码
            if(StringUtil.isNotEmpty(entity.getEntrustId())){
                dto.setEntrustId(entity.getEntrustId());
            }
            //basRiskMathCommService.insert(entity.getRiskTrialRetDto(), entity.getTradeId());

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
            restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/doComplete",
                entity, Integer.class);

            //当组合分配列表为空时 表示交易入口是单组合 不为空时表示 交易入口是多组合，多组合交易不直接在待办生成 确认的数据 add by wangcheng 20200116
            if(StringUtil.isEmptyList(entity.getBussTrdTransfenpeiDto())){
            	if(StringUtil.equals(entity.getPs(),DictCodeList.交易方向_申购.getValue()) || StringUtil.equals(entity.getPs(),DictCodeList.交易方向_赎回.getValue())){
            		TrdPlanCcyInvestTradeConfirmDto confirmDto = new TrdPlanCcyInvestTradeConfirmDto();
            		BeanUtil.copyPropertiesIgnoreNull(confirmDto,entity);
                    confirmDto.setTradeDate(entity.getPurCfmDate());
            		ProcessParameter<TrdPlanCcyInvestTradeConfirmDto> workFlowPatameter = getWorkFlowPatameter(confirmDto);
                    //confirmDto.setCreateUser(entity.getCreateUser());
                    //获取最原始留痕表数据
                    String createUser = restTemplate.getForObject(
                            ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanNetInvestTrade/getSysDateMark?workflowId="+ entity.getWorkflowId(),
                            String.class);
                    confirmDto.setCreateUser(createUser);
            		try {
            			WorkFlowUtil.startProcess(workFlowPatameter.getWorkFlowParameter(),"","", confirmDto.getCreateUser(),OperationType.NEW,false,confirmDto);
            		}catch (Exception e) {
            			e.printStackTrace();
            		}
            		
            	}
            }
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
    /**
     * 设置流程参数
     * @param dto
     * @return
     */
    public ProcessParameter<TrdPlanCcyInvestTradeConfirmDto> getWorkFlowPatameter(TrdPlanCcyInvestTradeConfirmDto dto) {
        if (StringUtil.equals(dto.getPs(),DictCodeList.交易方向_申购.getValue())){
            dto.setPs(DictCodeList.交易方向_申购确认.getValue());
        }else if (StringUtil.equals(dto.getPs(),DictCodeList.交易方向_赎回.getValue())){
            dto.setPs(DictCodeList.交易方向_赎回确认.getValue());
        }
        dto.setbTradeId(dto.getTradeId());
        dto.setWorkflowId(null);
        dto.setOperateType("save");
//        if (dto.getSettleSpeed() == 1 || dto.getSettleSpeed() == 2) {
//            int speedCount = dto.getSettleSpeed().intValue();
////            dto.setTradeDate(DateUtil.addDay(dto.getTradeDate(), 1));
//        	Date tradeDate = dto.getTradeDate();
//            List<String> holidayList = dateCalService.getDealCalendarHoliday(DateUtil.parse(DateUtil.format(tradeDate)),DateUtil.addDay(DateUtil.parse(DateUtil.format(tradeDate)), 15),dto.getCalendarId());
//            Date tradeDay = dateCalService.calculationTradingDay(DateUtil.parse(DateUtil.format(tradeDate)), speedCount, holidayList, "1");
//            dto.setTradeDate(tradeDay);
//        }
        if (baseGetBusinessId != null) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_计划类资产_货币型_交易);
            if(StringUtil.equals(dto.getType7(), DictCodeList.计划类资产二级分类_委托债权.getValue())){
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
        ProcessParameter<TrdPlanCcyInvestTradeConfirmDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdPlanCcyInvestTradeConfirmDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        processParameter.setPageId("TrdPlanCcyInvestTrade003");
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            processParameter.setOperationType(OperationType.NEW);
        }
        else {
            processParameter.setOperationType(OperationType.MODIFY);
        }
        workFlowParameter.setPageId("TrdPlanCcyInvestTrade003");
        workFlowParameter.setElementId("TrdPlanCcyInvestTrade003_btn_save");
        workFlowParameter.setBusinessCode((entity) -> entity.getTradeId());
        workFlowParameter.setServiceName("trdPlanCcyInvestTrade003Service");
        workFlowParameter.setBusinessType(DictCodeList.共通业务类型_计划类资产_货币型_交易.getValue());
        workFlowParameter.setComment((entity) -> WorkFlowUtil.concatComment(
            getMessage("TrdFundInvestTrade002.tradeId"), entity.getTradeId(),
            getMessage("TrdFundInvestTrade002.finprodName"),
            entity.getFinprodName(),
            getMessage("TrdFundInvestTrade002.tradeDate"),
            DateUtil.format(entity.getTradeDate())));
        dto.setPortfolioId(dto.getActPortfolioId());
        if(null!=dto.getTradeDate()){
        	workFlowParameter.setCrtDate(dto.getTradeDate());
        }
        return processParameter;
    }
    /**
     * 提交流程启动时
     */
    @Override
    public void doInsert(TrdPlanCcyInvestTradeDto entity, OperationType type) {
            restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/doInsert",
                entity, Integer.class);
        if(StringUtil.equals(entity.getOperateType(), "submit")){
            FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
            finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
            List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
            BaseTradeProductDealDto dto = new BaseTradeProductDealDto();
            dto.setTrdProductDeal(entity);
            dto.setTrdProductDealAdd(entity.getTrdProductDealAdd());
            List<StlPayOrdersDto> spodList = new ArrayList<>();
            
            for(BussTrdTransferOrderDto bttod : entity.getBussTrdTransferOrder()){
                spodList.add(bttod);
                dto.setStlPayOrdersList(spodList);
                baseTradeProductDealDtos.add(dto);
            }
            //赎回时没有划款指令，提交时需要增加指令可用持仓
            if (StringUtil.equals(entity.getPs(), DictCodeList.交易方向_赎回.getValue())){
                baseTradeProductDealDtos.add(dto);
            }
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
    public void doUpdate(TrdPlanCcyInvestTradeDto entity) {
        if(StringUtil.equals(entity.getOperateType(), "save")){
            restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/doUpdate",
                entity, Integer.class);
        }
        if(StringUtil.equals(entity.getOperateType(), "submit")){
            //流程提交过程中持仓先删后插，防止多次插入
            AllTradeCommonUtil.deletePosition(entity.getCancelRemark(), entity.getTradeId());
            prepareEntity(entity);
            restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/doUpdate",
                entity, Integer.class);
            FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
            finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
            List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
            BaseTradeProductDealDto dto = new BaseTradeProductDealDto();
            dto.setTrdProductDeal(entity);
            dto.setTrdProductDealAdd(entity.getTrdProductDealAdd());
            List<StlPayOrdersDto> spodList = new ArrayList<>();
            
            for(BussTrdTransferOrderDto bttod : entity.getBussTrdTransferOrder()){
                spodList.add(bttod);
                dto.setStlPayOrdersList(spodList);
                baseTradeProductDealDtos.add(dto);
            }
            //赎回时没有划款指令，提交时需要增加指令可用持仓
            if (StringUtil.equals(entity.getPs(), DictCodeList.交易方向_赎回.getValue())){
                baseTradeProductDealDtos.add(dto);    
            }
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
        }
    }
    /**
     * 
     * 流程作废时删除业务流程表
     * <功能详细描述>
     * @param workflowId
     * @see
     * @since 1.0
     */
    @Override
    public void doDelete(String workflowId) {
        baseTradeProductDealCommService.delete(workflowId);
        restTemplate.getForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/doDelete?workflowId="+
            workflowId, Integer.class);
    }
    /**
     * 资产代码下拉框
     * <功能详细描述>
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodId(Map<String, Object> params) {
        return restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/getFinprodId",
            params, SelectOptionDto.class);
    }

    /**
     * 查询组合可用现金
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Map<String, Object> getProAvailableCash(Map<String, Object> param) {
        Date vdate = DateUtil.parse((String)param.get("vdate"));
        String calId = (String)param.get("calId");
        if(StringUtil.isNotEmpty(calId)){
            List<String> holidayList = dateCalService.getDealCalendarHoliday(DateUtil.parse(DateUtil.format(vdate)),DateUtil.addDay(DateUtil.parse(DateUtil.format(vdate)), 15),calId);
            Date tradeDay = dateCalService.calculationTradingDay(DateUtil.parse(DateUtil.format(vdate)), 1, holidayList, "1");
            param.put("vdate1",DateUtil.format(tradeDay));
        }
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/getProAvailableCash",
            param, Map.class);
    }
    /**
     * 查询组合可用持仓
     * <功能详细描述>
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
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/getPtlSecPosition",
            dto, BigDecimal.class);
    }

    /**
     * 
     * 查询反显数据
     * <功能详细描述>
     * @param Tid
     * @return
     * @see
     * @since 1.0
     */
    public TrdPlanCcyInvestTradeDto getTrdFundDto(String Tid){
        return restTemplate.getForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/getTrdFundDto?tradId="+
            Tid,TrdPlanCcyInvestTradeDto.class);
    }
    /**
     * 
     * 查询投资委托DTO
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    public BaseEtrInvestInfoDto getEtrustDto(String entrustId){
        return restTemplate.getForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/getEtrustDto?entrustId="+
            entrustId,BaseEtrInvestInfoDto.class);
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
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/selectEntrustAmt",
            param,Map.class);
    }
    /**
     * 处理基金投资交易自定义dto <功能详细描述>
     * 
     * @param dto
     * @see
     * @since 1.0
     */
    public void prepareEntity(TrdPlanCcyInvestTradeDto dto) {
        dto.setActPortfolioId(dto.getPortfolioId());
        dto.setPortfolioId(AllTradeCommonUtil.exchangePortfolio(dto.getPortfolioId()));
//        dto.setTradeAmt(dto.getPrinAmt());
        dto.setYield(ArithmeticUtil.divide(dto.getYield(), new BigDecimal(100)));
        dto.setExerYield(ArithmeticUtil.divide(dto.getExerYield(), new BigDecimal(100)));
        dto.setBranch(Long.valueOf(CacheUtil.getSysParam("noBranchNoValue")));
        dto.setIsCancel(DictCodeList.是否_否.getValue());
        dto.setBusiType(DictCodeList.共通业务类型_计划类资产_货币型_交易.getValue());
        dto.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
//        if(StringUtil.equals(dto.getSettleSpreed(),DictCodeList.清算速度_T_0.getValue())){
//            dto.setSettleSpeed((long)0);
//        }else if(StringUtil.equals(dto.getSettleSpreed(), DictCodeList.清算速度_T_1.getValue())){
//            dto.setSettleSpeed((long)1);
//        }else if(StringUtil.equals(dto.getSettleSpreed(), DictCodeList.清算速度_T_2.getValue())){
//            dto.setSettleSpeed((long)2);
//        }
        dto.setSettleSpeed(Long.parseLong(StringUtil.isEmpty(dto.getSettleSpreed()) ? "0" : dto.getSettleSpreed()));
        if(StringUtil.isNotEmpty(dto.getRedCfmDate()) &&
                StringUtil.equals(DictCodeList.交易方向_赎回到账.getValue(), dto.getPs())){
            dto.setSettleDate(dto.getRedCfmDate());
        }else {
            dto.setSettleDate(dto.getTradeDate());
        }
        dto.setIssuePartyId(CacheUtil.getSysParam(BussinessConstant.ISSUE_PARTY_ID));
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
        if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())
                ||StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回.getValue())){
//            Map<String, Object> param = new HashMap<String, Object>();
//            param.put("finprodId", dto.getFinprodId());
//            Map<String, Object> result = trdPlanCcyInvestTrade003Service.getPurSpeed(param);
////            String speed = (String)result.get("SPEED");
//            String calId = (String)result.get("CALID");
//            if (dto.getSettleSpeed() == 1 || dto.getSettleSpeed() == 2) {
//                int speedCount = dto.getSettleSpeed().intValue();
//                Date tradeDate = dto.getTradeDate();
//                List<String> holidayList = dateCalService.getDealCalendarHoliday(DateUtil.parse(DateUtil.format(tradeDate)),DateUtil.addDay(DateUtil.parse(DateUtil.format(tradeDate)), 15),calId);
//                Date tradeDay = dateCalService.calculationTradingDay(DateUtil.parse(DateUtil.format(tradeDate)), speedCount, holidayList, "1");
//                trdProductDealAdd.setPurCfmDate(tradeDay);
//            }
            trdProductDealAdd.setPurCfmDate(dto.getPurCfmDate());
        }else{
//            trdProductDealAdd.setRedCfmDate(dto.getRedCfmDate());
            trdProductDealAdd.setPurCfmDate(dto.getTradeDate());
        }
        trdProductDealAdd.setRedCfmDate(dto.getRedCfmDate());
        trdProductDealAdd.setTradeId(dto.getTradeId());
        trdProductDealAdd.setPortfolioId(dto.getPortfolioId());
        trdProductDealAdd.setEntrustId(dto.getEntrustId());
//        trdProductDealAdd.setProdId(dto.getFinprodId());
        trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
        dto.setTrdProductDealAdd(trdProductDealAdd);
        // 组合交易表
        List<PtlTrade> ptlTrades = new ArrayList<>();
        PtlTrade ptlTrade = new PtlTrade();
        ptlTrade.setPortfolioId(dto.getPortfolioId());
        ptlTrade.setBusiNo(dto.getTradeId());
        ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        ptlTrade.setBusiId(dto.getFinprodId());
        ptlTrade.setBusiType(DictCodeList.共通业务类型_计划类资产_货币型_交易.getValue());
        ptlTrade.setSettleDate(dto.getSettleDate());
        ptlTrades.add(ptlTrade);
        if (!dto.getPortfolioId().equals(dto.getActPortfolioId())) {
            PtlTrade ptlTrade1 = new PtlTrade();
            ptlTrade1.setPortfolioId(dto.getActPortfolioId());
            ptlTrade1.setBusiNo(dto.getTradeId());
            ptlTrade1.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
            ptlTrade1.setBusiId(dto.getFinprodId());
            ptlTrade1.setBusiType(DictCodeList.共通业务类型_计划类资产_货币型_交易.getValue());
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
            paBussType = DictCodeListExt1.平安业务类型_计划类资产_货币型_申购申请.getValue();
        }
        else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())) {
            paBussType = DictCodeListExt1.平安业务类型_计划类资产_货币型_申购确认.getValue();
        }
        else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回.getValue())) {
            paBussType = DictCodeListExt1.平安业务类型_计划类资产_货币型_赎回申请.getValue();
        }
        else if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())) {
            paBussType = DictCodeListExt1.平安业务类型_计划类资产_货币型_赎回确认.getValue();
        }
        paTrdProductDeal.setPaBussType(paBussType);
        paTrdProductDeal.setDeptCode(personInfo==null?null:personInfo.getTraderDept());
        dto.setPaTrdProductDeal(paTrdProductDeal);
    }

    @Override
    public void doBack(TrdPlanCcyInvestTradeDto entity) {
        AllTradeCommonUtil.deletePosition(entity.getCancelRemark(), entity.getTradeId());
        
        //删除授信占用
        if (null != entity.getTradeId() && !"".equals(entity.getTradeId()) && !StringUtil.isNotEmpty(entity.getCancelRemark())){
            riskCreditListCommonValidatorService.deleteCredit(entity.getTradeId());
        }
    }

    @Override
    public void doRevoke(TrdPlanCcyInvestTradeDto entity) {
        AllTradeCommonUtil.deletePosition(entity.getCancelRemark(), entity.getTradeId());
        
        //删除授信占用
        if (null != entity.getTradeId() && !"".equals(entity.getTradeId()) && !StringUtil.isNotEmpty(entity.getCancelRemark())){
            riskCreditListCommonValidatorService.deleteCredit(entity.getTradeId());
        }
    }


    @Override
    public Map<String, Object> getRuntimeVariables(ProcessMark entity, String taskId) throws WorkFlowException {
        Map<String, Object> variables = new HashMap<>();
        TrdPlanCcyInvestTradeDto dto = this.getBussinessEntity(entity);
        if (taskId != null) {
        	try {
            	if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回.getValue())){
//            		return commonBusinessService.是否需要上级审批(dto.getCounterId(), dto.getShareAmt(), LoginUserUtil.getUserName(),
//            				DictCodeList.平安资产类型_计划类资产_货币型.getValue(), dto.getType6(), dto.getTradeMarket(), dto.getPs()
//            				// 资产类型改为授权类型 add by renping 2019-08-06  start
//            				, dto.getFinprodId()
//            				// 资产类型改为授权类型 add by renping 2019-08-06  end
//            				);
                    Map<String, Object> params = CommonBusinessService.setParams(dto.getCounterId(), dto.getShareAmt(),LoginUserUtil.getUserName(),
                        DictCodeList.平安资产类型_计划类资产_货币型.getValue(), dto.getType6(), dto.getTradeMarket(), dto.getPs()
                        , dto.getFinprodId() ,entity.getWorkflowId());
                    
                    return commonBusinessService.是否需要上级审批(params);
            	}else{
//            		return commonBusinessService.是否需要上级审批(dto.getCounterId(), dto.getTradeAmt(), LoginUserUtil.getUserName(),
//            				DictCodeList.平安资产类型_计划类资产_货币型.getValue(), dto.getType6(), dto.getTradeMarket(), dto.getPs()
//            				// 资产类型改为授权类型 add by renping 2019-08-06  start
//            				, dto.getFinprodId()
//            				// 资产类型改为授权类型 add by renping 2019-08-06  end
//            				);
                    Map<String, Object> params = CommonBusinessService.setParams(dto.getCounterId(), dto.getTradeAmt(),LoginUserUtil.getUserName(),
                        DictCodeList.平安资产类型_计划类资产_货币型.getValue(), dto.getType6(), dto.getTradeMarket(), dto.getPs()
                        , dto.getFinprodId() ,entity.getWorkflowId());
                    params.put("riskTrialRetDto", dto.getRiskTrialRetDto());
                    return commonBusinessService.是否需要上级审批(params);
            	}
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
}
