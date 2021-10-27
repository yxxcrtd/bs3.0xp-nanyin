/*
 * 文件名：EtrBondFirstBiddingController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.cfets.etrib.controller;

import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.background.component.common.pack.common.validation.groups.ValidateGroupEdit;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.trade.investtrade.commanddispatcher.service.TrdCommandDispatcher001Service;
import com.joyintech.tams.business.common.pojo.BaseMap;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.business.common.util.ExcelUitl;
import com.joyintech.tams.business.common.util.PositionUtil;
import com.joyintech.tams.cfets.etrib.dto.*;
import com.joyintech.tams.cfets.etrib.service.CfetsEtrIbBondinfo002Service;
import com.joyintech.tams.cfets.etrib.service.CfetsEtrIbBondinfoService;
import com.joyintech.tams.cfets.etrib.service.CfetsEtribBondInfo003Service;
import com.joyintech.tams.common.base.dao.EtrEntrustMapper;
import com.joyintech.tams.common.base.entity.EtrDistribute;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.entrust.impl.BaseEtrInvestInfoServiceImpl;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.risk.dto.RiskCreditListCommonDto;
import com.joyintech.tams.invest.common.riskinfrds.util.EntrustCompleteUtil;
import com.joyintech.tams.invest.common.trade.CeLinesUpdate;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.CreditLinesDealCommon;
import com.joyintech.tams.invest.entrust.common.aop.EntrustRdsCommonAop;
import com.joyintech.tams.invest.entrust.common.service.EtrCommonService;
import com.joyintech.tams.invest.entrust.etrinvestbondinfo.dto.EtrInvestBondInfoDto;
import com.joyintech.tams.invest.entrust.etrinvestbondinfo.service.EtrInvestBondInfoService;
import com.joyintech.tools.util.ValidationUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 银行间债券指令
 * 
 * @author caiqun
 * @version 1.0 2021年10月14日
 * @see CfetsEtrIbBondinfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/cfets/etrib/cfetsEtrIbBondinfo")
@Api(tags = "银行间债券指令")
public class CfetsEtrIbBondinfoController  extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(CfetsEtrIbBondinfoController.class);
    /**
     * 业务编号组件
     */
    @Autowired
    private BaseGetBusinessId bussGetBussinessIdService;
    @Autowired
    private CommonBusinessService commonBusinessService;
    @Autowired
    private CfetsEtrIbBondinfo002Service cfetsEtrIbBondinfo002Service;
    @Autowired
    private CfetsEtribBondInfo003Service cfetsEtrIbBondinfo003Service;
    @Autowired
    private CfetsEtrIbBondinfoService cfetsEtrIbBondinfoService;
    /**
     * 债券投资委托service
     */
    @Autowired
    private EtrInvestBondInfoService etrInvestBondInfoService;
    /**
     * 委托共通
     */
    @Autowired
    private EtrCommonService etrCommonService;

    /**
     * 指令分发service注入
     */
    @Autowired
    private TrdCommandDispatcher001Service trdCommandDispatcher001Service;
    /**
     * 投资委托信息Service
     */
    @Autowired
    private BaseEtrInvestInfoServiceImpl etrInvestInfoServiceImpl;
    /**
     * 委托指令Mapper
     */
    @Autowired
    private EtrEntrustMapper etrEntrustMapper;

    @ApiOperation(value = "查询页面初始化参数", notes = "查询页面初始化参数")
    @PostMapping("/getPageParams")
    @ResponseBody
    public Message<Map<String, Object>> getPageParams(){
        Map<String, Object> initPageParams = new HashMap<>();
        initPageParams.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        initPageParams.put("SHORT_SELLING_SUPPORTED", CacheUtil.getSysParam("SHORT_SELLING_SUPPORTED"));
        initPageParams.put("CMDS_PATH", "ws://192.168.90.208:8888/dsp/cmds");//CacheUtil.getSysParam("CMDS_PATH"));
        initPageParams.put("NEED_WORKFLOW", false);
        initPageParams.put("SHARES_TRADE_MARKET_02",DictCodeListExt1.交易市场_二级市场.getValue());

    	return new Message<Map<String, Object>>(initPageParams).success();
    }

    /**
     * 检索产品持仓分页数据。 <功能详细描述>
     *
     * @param pageParam int
     * @param page int
     * @param length int
     * @return Page<EtrExchangeTrade002Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
//    @ApiOperation(value = "查询产品持仓分页数据", notes = "查询产品持仓分页数据")
//    @PostMapping("/findPositionsPage/{page}/{length}")
//    @ResponseBody
//    public Message<Page<CfetsEtrIbBondinfo001Dto>> findPositionsPage(@RequestBody Map<String, Object> pageParam,
//                                                                     @PathVariable("page") int page,
//                                                                     @PathVariable("length") int length)
//            throws Exception {
//        try {
//            Page<CfetsEtrIbBondinfo001Dto> list = cfetsEtrIbBondinfoService.findPositionsPage(page, length, pageParam);
//            return new Message<Page<CfetsEtrIbBondinfo001Dto>>().success().setData(list);
//        }catch (Exception e) {
//            logger.error("查询产品持仓分页数据异常",e);
//            return new Message<Page<CfetsEtrIbBondinfo001Dto>>().fail().setMessage("查询产品持仓分页数据异常");
//        }
//    }

    /**
     * 检索指令管理分页数据。
     *
     * @param pageParam int
     * @param page int
     * @param length int
     * @return Page<EtrExchangeTrade002Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ApiOperation(value = "查询指令管理分页数据", notes = "查询指令管理分页数据")
    @PostMapping("/findEtrinfoPage")
    @ResponseBody
    public Message<Page<CfetsEtrIbBondinfo001Dto>> findEtrinfoPage(@RequestBody CfetsEtrIbBondinfo001Dto dto)
            throws Exception {
        try {
            Page<CfetsEtrIbBondinfo001Dto> list = cfetsEtrIbBondinfoService.findEtrinfoPage(dto);
            return new Message<Page<CfetsEtrIbBondinfo001Dto>>().success().setData(list);
        }catch (Exception e) {
            logger.error("查询指令管理分页数据异常",e);
            return new Message<Page<CfetsEtrIbBondinfo001Dto>>().fail().setMessage("查询指令管理分页数据异常");
        }

    }
    /**
     * 保存按钮点击时的check
     *
     * @param dtoList dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody List<CfetsEtrIbBondinfo002Dto> dtoList, BindingResult result) {
        List<ValidMessage> messages = new ArrayList<>();
        StringBuffer confirmMessage = new StringBuffer();
        // 授信软控 start
        List<JoyinMessage> joyinMessages = new ArrayList<>();
        Map<String, BigDecimal> cusMap  = new HashMap<String, BigDecimal>();
        // 授信软控 end
        for (int i = 0; i < dtoList.size(); i++ ) {
            // 授信软控 start
            BigDecimal recode = dtoList.get(i).getPrinAmt();
            Map<String, Object> map  = new HashMap<String, Object>();
            map.put("finprodId", dtoList.get(i).getFinprodId());
            String customerId = commonBusinessService.selectCustomer(map);
            if(customerId != null) {
                if(cusMap.get(customerId) == null) {
                    cusMap.put(customerId, dtoList.get(i).getPrinAmt());
                }else {
                    dtoList.get(i).setPrinAmt(cusMap.get(customerId).add(dtoList.get(i).getPrinAmt()));
                    cusMap.put(customerId, dtoList.get(i).getPrinAmt());
                }
            }

            boolean isCreditSoftControl = Boolean.parseBoolean(CacheUtil.getSysParam("IS_CREDIT_SOFTCONTROL"));
            RiskCreditListCommonDto riskCreditListCommonDto = new RiskCreditListCommonDto();
            if (!StringUtil.equals(BussinessDictCodeList.监控授信开关_关.getValue(), CacheUtil.getSysParam(BussinessConstant.ISCREDIT))
                    && isCreditSoftControl) {
                TrdProductDeal trdProductDeal = cfetsEtrIbBondinfo002Service.prepareDeal(dtoList.get(i));
                if(trdProductDeal.getCounterId() == null) {
                    trdProductDeal.setCounterId("");
                }
                CeLinesUpdate ceLinesUpdate = SpringContextUtil.getBean(CeLinesUpdate.class);
                AllTradeCommonInputDto inputDto =new AllTradeCommonInputDto();
                inputDto.setTrdProductDeal(trdProductDeal);
                List<CreditLinesDealCommon> list = ceLinesUpdate.isNeedOccupyCre(inputDto, true,false,false);
                if(list !=null && list.size()>0){
                    if(list.get(0).getErrMessage() !=null && getMessage("BUSS_COM_CRE_001").equals(list.get(0).getErrMessage())){
                        list.get(0).setErrMessage(getMessage("BUSS_COM_CRE_004"));
                    }
                    JoyinMessage joyinMessage = new JoyinMessage(list.get(0).getErrMessage(), MessageType.确认消息);
                    joyinMessages.add(joyinMessage);
                }
//                }
            }
            // 授信软控 end
            CfetsEtrIbBondinfo002Dto dto = dtoList.get(i);
            ValidationUtil.ValidResult itemResult = ValidationUtil.validateBean(dto, ValidateGroupEdit.class);
            // 授信软控 start
            dtoList.get(i).setPrinAmt(recode);
            // 授信软控 end
            if (!StringUtil.isEmptyList(itemResult.getAllErrors())) {
                StringBuffer message = new StringBuffer();
                message.append(getMessage("ETR_E_0025", String.valueOf(i + 1)));
                for (ValidationUtil.ErrorMessage errorMessage : itemResult.getAllErrors()) {
                    message.append("<br />");
                    message.append(errorMessage.getMessage());
                }
                messages.add(new ValidMessage(message.toString()));
            }
            // 可用现金软校验
            if (StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH),
                    DictCodeListExt1.是或否_是.getValue())
                    && StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())
                    && dto.getProAvailableCash() != null
                    && dto.getEntrustNum().compareTo(dto.getProAvailableCash()) == 1) {
                if (StringUtil.isNotBlank(confirmMessage)) {
                    confirmMessage.append("<br />");
                }
                confirmMessage.append(getMessage("ETR_E_0025", String.valueOf(i + 1)));
                confirmMessage.append("<br />");
                confirmMessage.append(getMessage("TRD_C_0002"));
            }
            // 可用持仓软校验
            else if (StringUtil.equals(
                    CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_POS_SWITCH),
                    DictCodeListExt1.是或否_是.getValue())
                    && (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())
                    || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_行权_赎回.getValue())
                    || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_行权_回售.getValue()))
                    && dto.getAssetAvailablePosition() != null
                    && dto.getEntrustNum().compareTo(dto.getAssetAvailablePosition()) > 0) {
                if (StringUtil.isNotBlank(confirmMessage)) {
                    confirmMessage.append("<br />");
                }
                confirmMessage.append(getMessage("ETR_E_0025", String.valueOf(i + 1)));
                confirmMessage.append("<br />");
                confirmMessage.append(getMessage("ETR_BOND_002"));
            }
        }
        // 授信软控 start
        if (joyinMessages.size() > 0 || messages.size() > 0) {
            AjaxResult errorRs = new AjaxResult();
            errorRs.setErrorList(messages);
            // 授信软控 start
            errorRs.setJoyinMessages(joyinMessages);

            String btnName = "操作";
            if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SAVE, dtoList.get(0).getOperateType())) {
                btnName = "SysCommon.save";
            }
            else if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SUBMIT, dtoList.get(0).getOperateType())) {
                btnName = "SysCommon.submit";
            }
            else if (StringUtil.equalsIgnoreCase("revoke", dtoList.get(0).getOperateType())) {
                btnName = "SysCommon.revoke";
            }
            errorRs.setData(
                    SpringContextUtil.getTextValue("COMMON_C_0001", SpringContextUtil.getTextValue(btnName)));
            // 授信软控 end
            return errorRs;
        }
        if (StringUtil.isNotEmpty(confirmMessage.toString())) {
            AjaxResult wr = new AjaxResult();
            wr.setData(confirmMessage.toString());
            return wr;
        }
        return commonCheck(result, dtoList.get(0).getOperateType());
    }
    /**
     * 保存/提交
     *
     * @param allDto dto
     * @param br BindingResult
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @ApiOperation(value = "多组合指令新建保存/提交", notes = "多组合指令新建保存/提交")
    @PostMapping("save")
    public Message<AjaxResult> save(@RequestBody CfetsEtribBondInfo003Dto allDto, BindingResult br)
            throws Exception {
        allDto=packageEquity(allDto);

     /*  AjaxResult ajaxResult = this.check(dtoList, br);
        if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
            return new Message<AjaxResult>().success().setData(ajaxResult);
        }*/
        // ----------add by zhangrui1 20200715 预占功能 start----------
        List<CfetsEtribBondInfo003Dto> dataList = new ArrayList<>();
        // ----------add by zhangrui1 20200715 预占功能 end----------
        for(CfetsEtribBondInfo003Dto dto: allDto.getEtrInvestBondInfoDtos()) {
            if(!com.joyintech.framework.util.StringUtil.isNotEmpty(dto.getEntrustId())){
                BussinessCodeParameter param = new BussinessCodeParameter();
                param.setCommonBusinessType(DictCodeList.共通业务类型_债券投资委托);
                String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param): com.joyintech.framework.util.IDGenerator.generate();
                dto.setEntrustId(code);

            }
            //默认证券账户
            if (com.joyintech.framework.util.StringUtil.isNotEmpty(dto.getPortfolioId()) && com.joyintech.framework.util.StringUtil.isNotEmpty(dto.getFinprodId())) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("portfolioId", dto.getPortfolioId());
                map.put("finprodId", dto.getFinprodId());
                String secAcctId = cfetsEtrIbBondinfo002Service.qryPortfolioSecAcctId(map);
                if (com.joyintech.framework.util.StringUtil.isNotEmpty(secAcctId)) {
                    dto.setSecAcctId(secAcctId); // 证券账户
                }
            }
            dto.setEntrustOwnNum(dto.getEntrustNum());
            dto.setEntrustIntegrateNum(BigDecimal.ZERO);


            // ----------add by zhangrui1 20200715 预占功能 start----------
            if (com.joyintech.framework.util.StringUtil.isNotEmpty(dto.getEntrustId())) {
                dataList.add(dto);
            }
            // ----------add by zhangrui1 20200715 预占功能 end----------
        }
        if (StringUtil.equals(BussinessConstant.SUBMIT, allDto.getOperateType())) {
            /*AjaxResult ar = EntrustRdsCommonAop.doListPreOccupy(
                    dataList, DictCodeList.平安资产类型_债券.getValue(),
                    BussinessDictCodeListRds.接口系统ID_预占批量接口.getValue(),
                    true, allDto.getEtrInvestPersonInfo());*/
           /* if (com.joyintech.framework.util.StringUtil.isNotEmpty(ar)) {
                return ar;
            }*/
        }
     /*   EntrustRdsCommonAop.riskListToChildren(allDto.getRiskTrialRetDto(), allDto.getEtrInvestBondInfoDtos(),
                (dto, list) -> dto.setRiskTrialRetDto(list));*/

        // 临时主键
        BussinessCodeParameter param = new BussinessCodeParameter();
        param.setCommonBusinessType(DictCodeList.共通业务类型_债券投资委托);
        String generatedId = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param): IDGenerator.generate();
        allDto.setTotalEtrustId(generatedId);
        for (int i = 0; i < allDto.getBussCombDistribtList().size(); i++) {
            allDto.getBussCombDistribtList().get(i).setEntrustId(allDto.getEtrInvestBondInfoDtos().get(i).getEntrustId());
        }
        ProcessParameter<CfetsEtribBondInfo003Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<CfetsEtribBondInfo003Dto> params = new WorkFlowParameter<>();

        params.setPageId("CfetsEtrBondInfo001");
        params.setElementId(allDto.getEtrInvestBondInfoDtos().get(0).getElementId());
        params.setBusinessCode(CfetsEtribBondInfo003Dto::getTotalEtrustId);
        params.setBusinessType(DictCodeList.共通业务类型_债券投资委托.getValue());
        processParameter.setOperationType(OperationType.NEW);
        processParameter.setWorkFlowParameter(params);
      // allDto.setWorkflowParameter("new|wy1,wy2||1|CfetsEtrBondInfo001|CfetsEtrBondInfo001_btn_submit|false");

        String msg = null;
        try {
            msg = cfetsEtrIbBondinfo003Service.start(processParameter, allDto);
            AjaxResult m =new AjaxResult(msg);
            return new Message<AjaxResult>().success().setData(m);
        }catch (Exception e) {
            PositionUtil.ajaxErrorReturn(e);
            logger.error("保存银行间多组合指令新建失败",e);
            return new Message<AjaxResult>().fail().setMessage("保存银行间多组合指令新建失败");
        }
    }

//    @ApiOperation(value = "持仓列表查询", notes = "持仓列表查询")
//    @PostMapping("/queryPositionsPage/{page}/{length}")
//    @ResponseBody
//    public Message<Page<CfetsEtrIbBondinfo001Dto>> queryPositionsPage(@RequestBody Map<String, Object> pageParam,
//                                                                   @PathVariable("page") int page,
//                                                                   @PathVariable("length") int length)
//            throws Exception {
//        try {
//            Page<CfetsEtrIbBondinfo001Dto> list = cfetsEtrIbBondinfoService.findEtrinfoPage(page, length, pageParam);
//            return new Message<Page<CfetsEtrIbBondinfo001Dto>>().success().setData(list);
//        }catch (Exception e) {
//            logger.error("查询指令管理分页数据异常",e);
//            return new Message<Page<CfetsEtrIbBondinfo001Dto>>().fail().setMessage("查询指令管理分页数据异常");
//        }
//
//    }
    
    @ApiOperation(value = "持仓列表查询", notes = "持仓列表查询")
    @PostMapping("/queryPositionsPage")
    @ResponseBody
    public Message<Page<CfetsEtrIbBondPosinfo001Dto>> queryPositionsPage(@RequestBody CfetsEtrIbBondPosiPage001RequestDto requestDto) {
        try {
            Page<CfetsEtrIbBondPosinfo001Dto> list = cfetsEtrIbBondinfoService.queryPositionsPage(requestDto.getPageNo(), requestDto.getPageSize(), requestDto);
            return new Message<Page<CfetsEtrIbBondPosinfo001Dto>>().success().setData(list);
        }catch (Exception e) {
            logger.error("查询持仓信息分页列表异常",e);
            return new Message<Page<CfetsEtrIbBondPosinfo001Dto>>().fail().setMessage(e.getMessage());
        }
    }

    @ApiOperation(value = "头寸信息查询", notes = "头寸信息查询")
    @PostMapping("/queryPositionInfo")
    @ResponseBody
    public Message<List<BaseMap>> queryPositionInfo(@RequestBody CfetsEtrIbBondPosiInfoRequestDto requestDto) {
        try {
            List<BaseMap> m = cfetsEtrIbBondinfoService.queryPositionInfo(requestDto);
            return new Message<List<BaseMap>>().success().setData(m);
        }catch (Exception e) {
            logger.error("查询头寸信息分页列表异常",e);
            return new Message<List<BaseMap>>().fail().setMessage(e.getMessage());
        }
    }

    @PostMapping(value="/export")
    @ResponseBody
    @ApiOperation(value = "excel导出", notes = "excel导出")
    public void export(@RequestBody CfetsExportRequestDto requestDto, HttpServletResponse response) {
        try {
            List<BaseMap> header = requestDto.getHeader();
            List dataList = null;
            if (StringUtil.equals("checked", requestDto.getType())){
                dataList = (List) requestDto.getData();
            }else if (StringUtil.equals("condition", requestDto.getType())){
                // 数据
                CfetsEtrIbBondPosiPage001RequestDto dto = requestDto.getRequestDto(CfetsEtrIbBondPosiPage001RequestDto.class);
                dataList = cfetsEtrIbBondinfoService.queryPositionsPage(1, 500000, dto).getRoot();
            }else{
                // 导出方式不合法
                throw new RuntimeException("导出方式不合法");
            }
            ExcelUitl.doExport("投资指令", header, dataList, response);
        } catch (Exception e) {
            logger.error("excel导出异常:",e);
            e.printStackTrace();
        }
    }
    
    @PostMapping("/getEtrFinProductInfo")
    @ApiOperation(value = "债券信息查询功能", notes = "债券信息查询功能")
    @ResponseBody
    public Message<CfetsEtrFinProductDto> getEtrFinProductInfo(@RequestParam("secId") String secId) {
        try {
//            secId = "F01_lvzq001.IB";
            CfetsEtrFinProductDto dto = cfetsEtrIbBondinfoService.getEtrFinProductInfo(secId);
            return new Message<CfetsEtrFinProductDto>(dto).success().setData(dto);
        } catch (Exception e) {
            logger.error("查询功能异常", e);
            return new Message<CfetsEtrFinProductDto>().fail().setMessage("查询异常");
        }
    }
    private CfetsEtribBondInfo003Dto packageEquity(CfetsEtribBondInfo003Dto dto){
        dto.setTradeMarket("01");
        dto.setCalendarId("01");
        dto.setInvManagerId("system");
        dto.setCcy("CNY");
        dto.setSettleDate(dto.getEtrDate());
        dto.setEntrustNum(ArithmeticUtil.multiply(dto.getTradeAmt(),new BigDecimal("10000")));
        dto.setVdate(dto.getEtrDate());
        dto.setMdate(dto.getEtrDate());
        List<CfetsBussCombDistribtDto> bussCombDistribtList =dto.getBussCombDistribtList();
        List<CfetsEtribBondInfo003Dto> etrInvestBondInfoDtosList=new ArrayList<>();
        for(CfetsBussCombDistribtDto item:bussCombDistribtList){
            CfetsEtribBondInfo003Dto dd=new CfetsEtribBondInfo003Dto();
            BeanUtil.copyPropertiesIgnoreNull(dd,dto);
            dd.setPortfolioId(item.getPortfolioId());
            dd.setTradeAmt(item.getEntrustNum());
            dd.setModelType("01");
            dd.setEntrustOwnNum(item.getEntrustOwnNum());
            dd.setEntrustIntegrateNum(item.getEntrustIntegrateNum());
            dd.setElementId("CfetsEtrBondInfo001_btn_submit");
            etrInvestBondInfoDtosList.add(dd);
        }
        dto.setEtrInvestBondInfoDtos(etrInvestBondInfoDtosList);

        return dto;
    }

    @PostMapping("/getfinprodIdForAdd")
    @ApiOperation(value = "债券简称列表查询", notes = "债券简称列表查询")
    @ResponseBody
    public Message<Page<SelectOptionDto>> getfinprodIdForAdd(@RequestBody CfetsSearchInfoDto item) {
        try {
            Page<SelectOptionDto> dto = cfetsEtrIbBondinfoService.getfinprodIdForAdd(item);
            return new Message<Page<SelectOptionDto>>().success().setData(dto);
        } catch (Exception e) {
            logger.error("查询债券简称列表异常", e);
            return new Message<Page<SelectOptionDto>>().fail().setMessage("查询异常");
        }
    }
    @PostMapping("/selectPortfolioIdList")
    @ApiOperation(value = "组合列表查询", notes = "组合列表查询")
    @ResponseBody
    public Message<Page<SelectOptionDto>> selectPortfolioIdList(@RequestBody CfetsSearchInfoDto item) {
        try {
            Page<SelectOptionDto> dto = cfetsEtrIbBondinfoService.selectPortfolioIdList(item);
            return new Message<Page<SelectOptionDto>>().success().setData(dto);
        } catch (Exception e) {
            logger.error("查询组合列表异常", e);
            return new Message<Page<SelectOptionDto>>().fail().setMessage("查询异常");
        }
    }
    @PostMapping("/getManagers")
    @ApiOperation(value = "投资经理查询", notes = "投资经理查询")
    @ResponseBody
    public Message<List<SelectOptionDto>> getManagers(String search){
        try {
            List<SelectOptionDto> list = trdCommandDispatcher001Service.getManager(search);
            return new Message<List<SelectOptionDto>>().success().setData(list);
        } catch (Exception e) {
            logger.error("查询功能异常", e);
            return new Message<List<SelectOptionDto>>().fail().setMessage("查询异常");
        }
    }
    @ResponseBody
    @ApiOperation(value = "撤回委托", notes = "撤回委托")
    @PostMapping("recall")
    public Message<AjaxResult> recallDelegate(@RequestBody EtrInvestBondInfoDto dto) throws Exception {
        //对选择的数据进行check
        AjaxResult ajaxResult = this.recallDelegateCheckMethod(dto);
        if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
            return new Message<AjaxResult>().success().setData(ajaxResult);
        }
        List<EtrInvestBondInfoDto> dtolist = dto.getSelectedItem();
        if(dtolist != null && dtolist.size()>0){
            for(EtrInvestBondInfoDto entity : dtolist){
                this.recallDelegateMethod(entity);
            }
            // add by zhangrui1 批量撤销预占 start
            EntrustRdsCommonAop.doListPreOccupy(dtolist, DictCodeList.平安资产类型_债券.getValue(),
                    BussinessDictCodeListRds.接口系统ID_预占释放批量接口.getValue(), false, null);
            // add by zhangrui1 批量撤销预占 end
            return new Message<AjaxResult>().success().setData(new AjaxResult(this.getMessage("ETR_X_001")));

        }
        return new Message<AjaxResult>().success().setData(new AjaxResult(this.getMessage("ETR_X_002")));
    }
    @PostMapping("close")
    @ApiOperation(value = "关闭委托", notes = "关闭委托")
    @ResponseBody
    public Message<AjaxResult> closeDelegate(@RequestBody EtrInvestBondInfoDto dto) throws Exception{
        //对选择的数据进行check
        AjaxResult ajaxResult = this.closeDelegateCheckMethod(dto);
        if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
            return new Message<AjaxResult>().success().setData(ajaxResult);
        }
        List<EtrInvestBondInfoDto> dtolist = dto.getSelectedItem();

        if(dtolist != null && dtolist.size()>0){
            for(EtrInvestBondInfoDto entity : dtolist){
                this.closeDelegateMethod(entity.getEntrustId(), entity.getCloseWay());
            }
            // add by zhangrui1 批量委托关闭 start
            // 若预占下没有实占则预占信息清除，否则预占信息保留，撤销交易时可恢复原状态
            List<String> entrustIdList = dtolist.stream().map(
                    EtrInvestBondInfoDto::getEntrustId).collect(Collectors.toList());
            // ZGZGST-2330:委托关闭判断是否有完成额度，分情况处理 fuyongsheng 20210919 edit start
            if(CollectionUtils.isNotEmpty(entrustIdList)){
                Map<String, BigDecimal> etrFinishMap = EntrustCompleteUtil.getEtrFinishAmtList(entrustIdList);
                //委托没有做过交易的，和撤销相同逻辑，删除预占
                List<EtrInvestBondInfoDto> recallEntrustIdList = new ArrayList<>();
                //委托已有完成额度，做过交易，走关闭完成的逻辑，更新预占
                List<String> closeEntrustIdList = new ArrayList<>();
                for(EtrInvestBondInfoDto etrDto : dtolist) {
                    if(StringUtil.isNotEmpty(etrFinishMap) && StringUtil.isNotEmpty(etrFinishMap.get(etrDto.getEntrustId()))
                            && BigDecimal.ZERO.compareTo(etrFinishMap.get(etrDto.getEntrustId()))<0) {
                        closeEntrustIdList.add(etrDto.getEntrustId());
                    }
                    else if(entrustIdList.contains(etrDto.getEntrustId())){
                        recallEntrustIdList.add(etrDto);
                    }
                }

                //没有做过交易，直接关闭的
                if(!StringUtil.isEmptyList(recallEntrustIdList)) {
                    // add by yushilong 批量撤销预占 start
                    EntrustRdsCommonAop.doListPreOccupy(recallEntrustIdList, DictCodeList.平安资产类型_债券.getValue(),
                            BussinessDictCodeListRds.接口系统ID_预占释放批量接口.getValue(), false, null);
                    // add by yushilong 批量撤销预占 end
                }

                //已经有委托完成度的
                if(!StringUtil.isEmptyList(closeEntrustIdList)) {
                    EntrustCompleteUtil.doComplete(closeEntrustIdList);
                }
            }
            // ZGZGST-2330:委托关闭判断是否有完成额度，分情况处理 fuyongsheng 20210919 edit end
            // add by zhangrui1 批量委托关闭 end
            return new Message<AjaxResult>().success().setData(new AjaxResult(this.getMessage("ETR_X_003")));
        }
        return new Message<AjaxResult>().success().setData(new AjaxResult(this.getMessage("ETR_X_004")));
    }
    @ResponseBody
    @ApiOperation(value = "修改委托", notes = "修改委托")
    @PostMapping("modifyDelegate")
    public Message<AjaxResult> modifyDelegate(@RequestBody CfetsEtrInvestBondInfoReqDto dto) throws Exception {
        //对选择的数据进行check
        AjaxResult ajaxResult = this.modifyDelegateCheckMethod(dto);
        if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
            return new Message<AjaxResult>().fail().setData(ajaxResult);
        }
        try {
            CfetsEtrInvestBondInfoReqDto item = dto.getSelectedItem().get(0);
            CfetsEtrIbBondinfoDto result = cfetsEtrIbBondinfoService.findEtrinfo(item);
            AjaxResult m =new AjaxResult();
            m.setData(result);
            return new Message<AjaxResult>().success().setData(m);
        } catch (Exception e) {
            logger.error("查询功能异常", e);
            return new Message<AjaxResult>().fail().setMessage("查询异常");
        }
    }
    public AjaxResult closeDelegateCheckMethod(EtrInvestBondInfoDto dto){
        if(!StringUtil.isNotEmpty(dto)||StringUtil.isEmptyList(dto.getSelectedItem())){
            AjaxResult res=new AjaxResult();
            ValidMessage  validMessage= new ValidMessage();
            validMessage.setMessage(getMessage("COMMON_E_0002"));
            res.setErrorList(Arrays.asList(validMessage));
            return res;
        }
        List<EtrInvestBondInfoDto> selectedItem = dto.getSelectedItem();
        AjaxResult ajaxResult=new AjaxResult();
        if(StringUtil.isNotEmpty(selectedItem)){
            List<String> entrustIds = new ArrayList<>();
            for(EtrInvestBondInfoDto Item:selectedItem){
                if(StringUtil.equals(Item.getEntrustStatus(),BussinessDictCodeList.委托状态_流程中.getValue())){
                    AjaxResult res=new AjaxResult();
                    ValidMessage  validMessage= new ValidMessage();
                    validMessage.setMessage("流程中的数据不允许关闭");
                    res.setErrorList(Arrays.asList(validMessage));
                    return res;
                }
                entrustIds.add(Item.getEntrustId());
            }
            Map<String,Object> param= new HashMap<>();
            param.put("entrustIds",entrustIds);
            ajaxResult.setErrorList(etrCommonService.entrustCheckCloseList(param));
            return ajaxResult;
        }
        ValidMessage  validMessage= new ValidMessage();
        validMessage.setMessage("请选择列表中数据");
        ajaxResult.setErrorList(Arrays.asList(validMessage));
        return ajaxResult;
    }
    public AjaxResult recallDelegateCheckMethod(EtrInvestBondInfoDto dto){
        if(!StringUtil.isNotEmpty(dto)||StringUtil.isEmptyList(dto.getSelectedItem())){
            AjaxResult res=new AjaxResult();
            ValidMessage  validMessage= new ValidMessage();
            validMessage.setMessage(getMessage("COMMON_E_0002"));
            res.setErrorList(Arrays.asList(validMessage));
            return res;
        }
        List<EtrInvestBondInfoDto> selectedItem = dto.getSelectedItem();
        AjaxResult ajaxResult=new AjaxResult();
        if(StringUtil.isNotEmpty(selectedItem)){
            for(EtrInvestBondInfoDto Item:selectedItem){
                if(!StringUtil.equals(Item.getEntrustStatus(),BussinessDictCodeList.委托接收状态_待接收.getValue())
                        && !StringUtil.equals(Item.getEntrustStatus(),BussinessDictCodeList.委托状态_待分发.getValue())
                ){
                    AjaxResult res=new AjaxResult();
                    ValidMessage  validMessage= new ValidMessage();
                    validMessage.setMessage("只有指令状态为待分发、待接收的指令允许撤回");
                    res.setErrorList(Arrays.asList(validMessage));
                    return res;
                }
            };
        }
        return new AjaxResult();
    }
    public AjaxResult modifyDelegateCheckMethod(CfetsEtrInvestBondInfoReqDto dto){
        if(!StringUtil.isNotEmpty(dto)||StringUtil.isEmptyList(dto.getSelectedItem())){
            AjaxResult res=new AjaxResult();
            ValidMessage  validMessage= new ValidMessage();
            validMessage.setMessage(getMessage("COMMON_E_0002"));
            res.setErrorList(Arrays.asList(validMessage));
            return res;
        }
        List<CfetsEtrInvestBondInfoReqDto> selectedItem = dto.getSelectedItem();
        AjaxResult ajaxResult=new AjaxResult();
        if(StringUtil.isNotEmpty(selectedItem)){
            if(selectedItem.size()>1){
                AjaxResult res=new AjaxResult();
                ValidMessage  validMessage= new ValidMessage();
                validMessage.setMessage("委托指令修改仅允许一条数据");
                res.setErrorList(Arrays.asList(validMessage));
                return res;
            }
            for(CfetsEtrInvestBondInfoReqDto Item:selectedItem){
                if(!StringUtil.equals(Item.getEntrustStatus(),BussinessDictCodeList.委托接收状态_已撤回.getValue())
                        && !StringUtil.equals(Item.getEntrustStatus(),BussinessDictCodeList.委托状态_已退回.getValue())
                ){
                    AjaxResult res=new AjaxResult();
                    ValidMessage  validMessage= new ValidMessage();
                    validMessage.setMessage("只有指令状态为已撤回、已退回的指令允许修改");
                    res.setErrorList(Arrays.asList(validMessage));
                    return res;
                }
            };
        }
        return new AjaxResult();
    }
    public boolean recallDelegateMethod(EtrInvestBondInfoDto dto)
            throws InvocationTargetException, IllegalAccessException {
        BaseEtrInvestInfoDto entity=recallDelegatePackageEquity(dto);
        // 将传来dto中的委托指令的委托状态改为"已撤回"
        entity.getEtrEntrust().setEntrustStatus(DictCodeList.委托状态_已撤回.getValue());
        // 调用投资委托组件的更新委托状态接口，将委托状态设置为"已撤回"
        boolean flag = etrInvestInfoServiceImpl.updateDelegateStatus(entity.getEtrEntrust().getEntrustId(),
                entity.getEtrEntrust().getEntrustStatus());
        if (flag) {
            return true;
        }
        return false;
    }
    public BaseEtrInvestInfoDto recallDelegatePackageEquity(EtrInvestBondInfoDto dto)
            throws IllegalAccessException, InvocationTargetException {
        BaseEtrInvestInfoDto entity = new BaseEtrInvestInfoDto();
        EtrEntrust etrEntrust = new EtrEntrust();
        EtrEntrustAdd etrEntrustAdd = new EtrEntrustAdd();
        BeanUtil.copyProperties(etrEntrust, dto);
        BeanUtil.copyProperties(etrEntrustAdd, dto);
        List<EtrDistribute> etrDistributeList = new ArrayList<>();
        EtrDistribute etrDistribute = new EtrDistribute();
        BeanUtil.copyProperties(etrDistribute, dto);
        etrDistributeList.add(etrDistribute);
        entity.setEtrEntrust(etrEntrust);
        entity.setEtrEntrustAdd(etrEntrustAdd);
        entity.setEtrDistributeList(etrDistributeList);
        return entity;
    }
    public boolean closeDelegateMethod(String entrustId,String closeWay){
        EtrEntrust ee = etrEntrustMapper.selectByPrimaryKey(entrustId);
        if (ee != null) {
            // 调用投资委托组件的更新委托状态接口，将委托状态设置为“已关闭”
            String tempEntrustStatu = ee.getEntrustStatus();// 获取该委托原始状态，防止后续更改成关闭
            boolean flag = etrInvestInfoServiceImpl.updateDelegateStatus(entrustId, DictCodeList.委托状态_已关闭.getValue());
            // 判断关闭委托是否完成
            if (flag) {
                return true;
            }
        }
        return false;
    }

}