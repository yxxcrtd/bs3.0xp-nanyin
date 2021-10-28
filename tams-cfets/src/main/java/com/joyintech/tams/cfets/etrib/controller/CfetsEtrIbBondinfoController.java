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
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.trade.investtrade.commanddispatcher.service.TrdCommandDispatcher001Service;
import com.joyintech.tams.business.common.dto.CfetsExportRequestDto;
import com.joyintech.tams.business.common.pojo.BaseMap;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.business.common.service.PositionHttpRequest;
import com.joyintech.tams.business.common.util.ExcelUitl;
import com.joyintech.tams.business.common.util.PositionUtil;
import com.joyintech.tams.business.common.util.UserUtil;
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

import com.joyintech.tams.component.business.riskrds.controller.InterfRiskRdsController;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.client.ClientData;
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
    /**
     * 风险接口组件Controller
     */
    @Autowired
    private InterfRiskRdsController interfRiskRdsController;

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
        initPageParams.put("platDate",DateUtil.platDateFromCache());

    	return new Message<Map<String, Object>>(initPageParams).success();
    }
    @ApiOperation(value = "查询指令管理分页数据", notes = "查询指令管理分页数据")
    @PostMapping("/findEtrinfoPage")
    @ResponseBody
    public Message<Page<CfetsEtrIbfindEtrinfoResponse>> findEtrinfoPage(@RequestBody CfetsEtrIbfindEtrinfoRequest dto)
            throws Exception {
        try {
            Page<CfetsEtrIbfindEtrinfoResponse> list = cfetsEtrIbBondinfoService.findEtrinfoPage(dto);
            return new Message<Page<CfetsEtrIbfindEtrinfoResponse>>().success().setData(list);
        }catch (Exception e) {
            logger.error("查询指令管理分页数据异常",e);
            return new Message<Page<CfetsEtrIbfindEtrinfoResponse>>().fail().setMessage("查询指令管理分页数据异常");
        }

    }
    @ResponseBody
    @ApiOperation(value = "多组合指令新建保存/提交", notes = "多组合指令新建保存/提交")
    @PostMapping("save")
    public Message<AjaxResult> save(@RequestBody CfetsEtribSaveRequest cfetsDto, BindingResult br)
            throws Exception {
       CfetsEtribBondInfo003Dto allDto=cfetsEtrIbBondinfoService.packageEquity(cfetsDto);
       AjaxResult ajaxResult = cfetsEtrIbBondinfoService.checkSave(cfetsDto, br);
        if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
            return new Message<AjaxResult>().success().setData(ajaxResult);
        }
        // ----------add by zhangrui1 20200715 预占功能 start----------
        List<CfetsEtribBondInfo003Dto> dataList = new ArrayList<>();
        // ----------add by zhangrui1 20200715 预占功能 end----------
        for(CfetsEtribBondInfo003Dto dto: allDto.getEtrInvestBondInfoDtos()) {
            if(!StringUtil.isNotEmpty(dto.getEntrustId())){
                BussinessCodeParameter param = new BussinessCodeParameter();
                param.setCommonBusinessType(DictCodeList.共通业务类型_债券投资委托);
                String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param): com.joyintech.framework.util.IDGenerator.generate();
                dto.setEntrustId(code);
            }
            //默认证券账户
            if (StringUtil.isNotEmpty(dto.getPortfolioId()) && StringUtil.isNotEmpty(dto.getFinprodId())) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("portfolioId", dto.getPortfolioId());
                map.put("finprodId", dto.getFinprodId());
                String secAcctId = cfetsEtrIbBondinfo002Service.qryPortfolioSecAcctId(map);
                if (com.joyintech.framework.util.StringUtil.isNotEmpty(secAcctId)) {
                    dto.setSecAcctId(secAcctId); // 证券账户
                }
            }
            // ----------add by zhangrui1 20200715 预占功能 start----------
            if (StringUtil.isNotEmpty(dto.getEntrustId())) {
                dataList.add(dto);
            }
            // ----------add by zhangrui1 20200715 预占功能 end----------
        }
        if (StringUtil.equals(BussinessConstant.SUBMIT, allDto.getOperateType())) {
            AjaxResult ar = EntrustRdsCommonAop.doListPreOccupy(
                    dataList, DictCodeList.平安资产类型_债券.getValue(),
                    BussinessDictCodeListRds.接口系统ID_预占批量接口.getValue(),
                    true, null);
            if (StringUtil.isNotEmpty(ar)) {
                return new Message<AjaxResult>().success().setData(ar);
            }
        }
       EntrustRdsCommonAop.riskListToChildren(allDto.getRiskTrialRetDto(), allDto.getEtrInvestBondInfoDtos(),
                (dto, list) -> dto.setRiskTrialRetDto(list));

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

        String msg = null;
        try {
            msg = cfetsEtrIbBondinfo003Service.start(processParameter, allDto);
            AjaxResult m =new AjaxResult(msg);
            return new Message<AjaxResult>().success().setData(m);
        }catch (Exception e) {
            logger.error("保存银行间多组合指令新建失败",e);
            return PositionUtil.ajaxErrorReturn(e);
        }
    }
    
    @ApiOperation(value = "持仓列表查询", notes = "持仓列表查询")
    @PostMapping("/queryPositionsPage")
    @ResponseBody
    public Message<Page<CfetsEtrIbBondPosinfo001Dto>> queryPositionsPage(@RequestBody CfetsEtrIbBondPosiPage001RequestDto requestDto) {
        try {
            Page<CfetsEtrIbBondPosinfo001Dto> list = cfetsEtrIbBondinfoService.queryPositionsPage(requestDto);
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

    @PostMapping(value="/exportPosition")
    @ResponseBody
    @ApiOperation(value = "持仓列表导出", notes = "持仓列表导出")
    public void exportPosition(@RequestBody CfetsExportRequestDto requestDto, HttpServletResponse response) {
        // 导出
        ExcelUitl.doExport(requestDto, response, "持仓列表", () -> {
            CfetsEtrIbBondPosiPage001RequestDto dto = requestDto.getDto(CfetsEtrIbBondPosiPage001RequestDto.class);
            List dataList = cfetsEtrIbBondinfoService.queryPositionsPage(dto).getRoot();
            return dataList;
        });
    }

    @PostMapping(value="/exportEtrInfo")
    @ResponseBody
    @ApiOperation(value = "指令管理导出", notes = "指令管理导出")
    public void exportEtrInfo(@RequestBody CfetsExportRequestDto req, HttpServletResponse response) {
        // 导出
        ExcelUitl.doExport(req, response, "指令管理", () -> {
            List dataList = cfetsEtrIbBondinfoService.findEtrinfoPage(req.getDto(CfetsEtrIbBondinfo001Dto.class)).getRoot();
            return dataList;
        });
    }
    
    @PostMapping("/getEtrFinProductInfo/{secId:.+}")
    @ApiOperation(value = "债券信息查询功能", notes = "债券信息查询功能")
    @ResponseBody
    public Message<CfetsEtrFinProductDto> getEtrFinProductInfo(@PathVariable String secId) {
        try {
//            secId = "F01_lvzq001.IB";
            CfetsEtrFinProductDto dto = cfetsEtrIbBondinfoService.getEtrFinProductInfo(secId);
            return new Message<CfetsEtrFinProductDto>(dto).success().setData(dto);
        } catch (Exception e) {
            logger.error("查询功能异常", e);
            return new Message<CfetsEtrFinProductDto>().fail().setMessage("查询异常");
        }
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

    @GetMapping("/getSecType1")
    @ApiOperation(value = "债券一级分类列表查询", notes = "债券一级分类列表查询")
    @ResponseBody
    public Message<SelectDto> getSecType1(String search) {
        try {
            SelectDto sdto = this.getSelectDataFromDataDic(DictCode.金融产品类型_投管用, search);
            return new Message<SelectDto>().success().setData(sdto);
        } catch (Exception e) {
            logger.error("查询债券简称列表异常", e);
            return new Message<SelectDto>().fail().setMessage("查询异常");
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
    @ResponseBody
    @ApiOperation(value = "风险试算", notes = "风险试算")
    @PostMapping("doRiskcal")
    public Message<AjaxResult> doRiskcal(@RequestBody CfetsEtrIbdoRiskcalRequest dto) throws Exception {
        try {
            CfetsEtribdoRiskcalReturn result=new  CfetsEtribdoRiskcalReturn();
            Map<String, Object> dataMap=new HashMap<>();
            //风险试算封装dto
            CfetsEtrIbdoRiskcalDto d=new CfetsEtrIbdoRiskcalDto();
            BeanUtil.copyPropertiesIgnoreNull(d,dto);
            d.setBussTrdTransfenpeiDto(dto.getBussCombDistribtList());
            d.setBusiType("02");
            d.setTradeMarket(BussinessDictCodeList.交易场所_银行间.getValue());
            d.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
            d.setVdate(dto.getEtrDate());
            d.setMdate(dto.getEtrDate());;
            d.setCcy("CNY");
            dataMap=BeanUtil.object2Map(d);
            //风险试算check

            List<String> resultArrList=interfRiskRdsController.doInfBatchData(dataMap,"0", "RSK002","F01");
            result.setRiskMathFlag("02");
            Boolean forbidFlag=false;
            Boolean cluflag=true;
            List<CfetsEtribdoRiskcalReturn> resultlist=new ArrayList<>();
            result.setRiskcalReturnList(resultlist);
            if(!StringUtil.isEmptyList(resultArrList) ){
                for(String row:resultArrList){
                    ClientData resultArr = JsonUtils.json2Object(row, ClientData.class);
                    if(StringUtil.isNotEmpty(resultArr) && StringUtil.isNotEmpty(resultArr.getHead())
                            && StringUtil.isNotEmpty(resultArr.getHead().get("std400msgt"))
                    ){
                        if (StringUtil.equals(resultArr.getHead().get("std400msgt"),"E")) {
                            return new Message<AjaxResult>().fail().setMessage(resultArr.getHead().get("stdrtninfo").toString());
                        }
                    }
                    if (!StringUtil.isNotEmpty(resultArr) || !StringUtil.isNotEmpty(resultArr.getBody())
                            || !StringUtil.isNotEmpty(resultArr.getBody().get("RSK_LIST"))
                            || !StringUtil.isNotEmpty(resultArr.getBody().get("rskStatus"))) {
                        return new Message<AjaxResult>().success().setMessage("无");
                    }
                    List<Map<String, Object>> riskList = (List<Map<String, Object>>)resultArr.getBody().get("RSK_LIST");
                    for(Map<String, Object> map: riskList){
                        if(StringUtil.equals(map.get("kriStatus"),"0") && !StringUtil.equals(map.get("kriTrend"),"向好")){
                            forbidFlag=true;
                        }
                        resultlist.add(BeanUtil.mapToObject(map,CfetsEtribdoRiskcalReturn.class));
                    }

                    //当置为不通过时就不再进来了
                    if(cluflag) {
                        if(!StringUtil.equals(resultArr.getBody().get("rskStatus"),"3")){
                            if(StringUtil.equals(resultArr.getBody().get("rskStatus"),"4")&&forbidFlag){
                                cluflag=false;
                                resultArr.getBody().put("rskStatus","3");
                            }
                        }else{
                            cluflag=false;
                        }
                        if (!cluflag) {
                            result.setRiskTrialSpan("试算不通过");
                        } else {
                            result.setRiskTrialSpan("试算通过");
                        }
                    }
                }

            }
            AjaxResult m =new AjaxResult();
            m.setData(result);
            return new Message<AjaxResult>().success().setData(m);
        } catch (Exception e) {
            logger.error("查询功能异常", e);
            return new Message<AjaxResult>().fail().setMessage("查询异常");
        }
    }
    @PostMapping("/getPortfolioIdLink")
    @ApiOperation(value = "组合代码链接", notes = "组合代码链接")
    @ResponseBody
    public Message<String> getPortfolioIdLink(@RequestParam("search")String search) {
        try {
            String  url ="invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init?dialog=1&pageModel=2&portfolioId="+search;
            return new Message<String>().success().setData(url);
        } catch (Exception e) {
            logger.error("查询组合代码链接异常", e);
            return new Message<String>().fail().setMessage("查询异常");
        }
    }
    @PostMapping("/getFinprodIdLink")
    @ApiOperation(value = "资产代码链接", notes = "资产代码链接")
    @ResponseBody
    public Message<String> getFinprodIdLink(@RequestParam("search")String search) {
        try {
            String  url ="invest/asset/bond/FinBondTargetAsset002/init?dialog=1&pageModel=2&finprodId="+search;
            return new Message<String>().success().setData(url);
        } catch (Exception e) {
            logger.error("查询资产代码链接异常", e);
            return new Message<String>().fail().setMessage("查询异常");
        }
    }

}