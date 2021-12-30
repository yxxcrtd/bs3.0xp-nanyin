package com.joyintech.tams.cfets.bankuplink.bond.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.business.common.pojo.BaseMap;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.business.common.util.PositionUtil;
import com.joyintech.tams.cfets.bankuplink.bond.dao.CfetsIbuQuoteBondEtrMapper;
import com.joyintech.tams.cfets.bankuplink.bond.dto.CfetsBondDealQuoteCommonDto;
import com.joyintech.tams.cfets.bankuplink.bond.service.CfetsBondDealQuotedPrice003Service;
import com.joyintech.tams.cfets.position.service.PositionInfoService;
import com.joyintech.tams.common.base.dao.StlSecAcctInfoMapper;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.bankuplink.bond.dto.CfetsBondDealDialogueQuoteDto;
import com.joyintech.tams.invest.bankuplink.bond.dto.CfetsBondDealRequestQuoteDto;
import com.joyintech.tams.invest.bankuplink.bond.dto.CounterInfoDto;
import com.joyintech.tams.invest.bankuplink.common.service.CfetsQuotedCommonService;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;

/**
 * 现券买卖(请求)
 */
@Controller
@RequestMapping("/cfets/bond/IbuQuoteBond003")
@Api(tags = "CFETS债券交易-请求")
public class IbuQuoteBond003Controller extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsBondDealQuotedPrice003Service cfetsBondDealQuotedPrice003Service;

    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsQuotedCommonService cfetsQuotedCommonService;

    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;

    /**
     * {@linkplain}
     */
    @Autowired
    private BaseGetBussinessId bussGetBussinessIdService;

    @Autowired
    private com.joyintech.tams.cfets.bankuplink.bond.service.CfetsQuotedCommonService cfetsQuotedCommonServiceVue;

    @Autowired
    private CfetsIbuQuoteBondEtrMapper cfetsIbuQuoteBondEtrMapper;

    @Autowired
    private StlSecAcctInfoMapper stlSecAcctInfoMapper;

    @Autowired
    private PositionInfoService positionInfoService;


    @ApiOperation(value = "请求报价初始化", notes = "请求报价初始化")
    @PostMapping("/initPrice")
    @ResponseBody
    public Message<CfetsBondDealQuoteCommonDto> initPrice(@RequestBody CfetsBondDealQuoteCommonDto cfetsBondDealQuoteCommonDto){
        CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto = new CfetsBondDealRequestQuoteDto();
        String quoteId = cfetsBondDealQuoteCommonDto.getQuoteId();
        String quotaPageModel = cfetsBondDealQuoteCommonDto.getQuotaPageModel();
        cfetsBondDealRequestQuoteDto = cfetsBondDealQuotedPrice003Service.getBondRequestQuote(quoteId,this.getParam("statusValid"));

        cfetsBondDealRequestQuoteDto.setAvaNum(ArithmeticUtil.divide(cfetsBondDealRequestQuoteDto.getAvaNum(),new BigDecimal(10000),2));
        //组合分配列表
        List <CfetsBondDealRequestQuoteDto> cfetsBondDealRequestQuoteDtoList =cfetsBondDealQuotedPrice003Service.getCfetsBondDealRequestQuoteDtoList(quoteId);
        /*for(CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuote:cfetsBondDealRequestQuoteDtoList){
            cfetsBondDealRequestQuote.setMatureYield(cfetsBondDealRequestQuoteDto.getMatureYield());
            cfetsBondDealRequestQuote.setExerYield(cfetsBondDealRequestQuoteDto.getExerYield());
        }*/
        cfetsBondDealRequestQuoteDto.setCfetsBondDealRequestQuoteDtoList(cfetsBondDealRequestQuoteDtoList);


        // 画面打开模式(区分做市报价还是请求报价)
        if (StringUtil.isNotEmpty(quotaPageModel)) {
            cfetsBondDealRequestQuoteDto.setQuotaPageModel(quotaPageModel);
        }
        // 区分校验类型
        if (StringUtil.isNotEmpty(this.getParam("checkType"))) {
            cfetsBondDealRequestQuoteDto.setCheckType(this.getParam("checkType"));
        }
        // 复制模式打开时需要把报价编号置空
        if (StringUtil.equals(cfetsBondDealQuoteCommonDto.getOpenModel(), "copy")) {
            cfetsBondDealRequestQuoteDto.setQuoteId(null);
            // 复制的场合券面总额默认为剩余额度
            if (cfetsBondDealRequestQuoteDto.getEntrustId() != null) {
                CfetsBondDealRequestQuoteDto entrustInfo = cfetsBondDealQuotedPrice003Service.queryEntrustInfoByEntrustId(
                        cfetsBondDealRequestQuoteDto.getEntrustId());
                if (entrustInfo != null && entrustInfo.getEntrustNum() != null) {
                    cfetsBondDealRequestQuoteDto.setAvaNum(entrustInfo.getAvaNum());
                }
            }
            else if (!StringUtil.isEmptyList(cfetsBondDealRequestQuoteDto.getCfetsBondDealRequestQuoteDtoList())) {
                // 多组合场景
                // 券面总额：默认未完成各单笔指令剩余额度总和
                // 组合信息：根据各指令带出，券面总额默认为各指令剩余额度。已完成指令不再显示在列表中。
                BigDecimal tempSumAvaAmt = BigDecimal.ZERO;
                List<CfetsBondDealRequestQuoteDto> tempEntrustList = new ArrayList<>();
                for (CfetsBondDealRequestQuoteDto tempDto : cfetsBondDealRequestQuoteDto.getCfetsBondDealRequestQuoteDtoList()) {
                    if (BigDecimal.ZERO.compareTo(tempDto.getEntrustAvaNum()) < 0) {
                        tempDto.setAvaNum(tempDto.getEntrustAvaNum());
                        tempSumAvaAmt = com.joyintech.framework.util.ArithmeticUtil.doAdd(tempSumAvaAmt, tempDto.getEntrustAvaNum());
                        tempEntrustList.add(tempDto);
                    }
                }
                cfetsBondDealRequestQuoteDto.setAvaNum(tempSumAvaAmt);
                cfetsBondDealRequestQuoteDto.setCfetsBondDealRequestQuoteDtoList(tempEntrustList);
            }
        }
        //回复操作
        if(StringUtil.equals(cfetsBondDealQuoteCommonDto.getQuotaPageModel(),"response")){
            //恢复指令额度
            for (CfetsBondDealRequestQuoteDto item : cfetsBondDealRequestQuoteDto.getCfetsBondDealRequestQuoteDtoList()) {
                //恢复指令剩余额度
                item.setEntrustAvaNum(ArithmeticUtil.doAdd(item.getEntrustAvaNum(),item.getAvaNum()));
            }
        }
        //复制操作 返回前台
        cfetsBondDealQuoteCommonDto = cfetsQuotedCommonServiceVue.createReueust2(cfetsBondDealRequestQuoteDto);
        if(CollectionUtils.isNotEmpty(cfetsBondDealRequestQuoteDto.getCfetsBondDealRequestQuoteDtoList())){
            cfetsBondDealQuoteCommonDto.setPtlAllocatios(cfetsQuotedCommonServiceVue.createRequestPtlAllocationList(cfetsBondDealRequestQuoteDto.getCfetsBondDealRequestQuoteDtoList()));
        }
        return new Message<CfetsBondDealQuoteCommonDto>().success().setData(cfetsBondDealQuoteCommonDto);
    }

    /**
     * 保存验证
     * @param cfetsBondDealQuoteCommonDto
     * @return
     */
    @ApiOperation(value = "请求报价提交检查", notes = "请求报价提交检查")
    @ResponseBody
    @RequestMapping("check")
    public Message<AjaxResult> check(@RequestBody CfetsBondDealQuoteCommonDto cfetsBondDealQuoteCommonDto) {
        AjaxResult ar = new AjaxResult();
        StringBuffer sb = new StringBuffer();
        ar = cfetsBondDealQuotedPrice003Service.checkNotEmpty(cfetsBondDealQuoteCommonDto);
        if(CollectionUtils.isNotEmpty(ar.getErrorList())){
            return new Message<AjaxResult>().success().setData(ar);
        }
        CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto = cfetsQuotedCommonServiceVue.createReueust(cfetsBondDealQuoteCommonDto);
        if(CollectionUtils.isNotEmpty(cfetsBondDealQuoteCommonDto.getPtlAllocatios())){
            cfetsBondDealRequestQuoteDto.setCfetsBondDealRequestQuoteDtoList(cfetsQuotedCommonServiceVue.createCfetsBondDealRequestQuoteDtoList(cfetsBondDealQuoteCommonDto.getPtlAllocatios()));
        }
        List<ValidMessage> valid = cfetsBondDealQuotedPrice003Service.valid(cfetsBondDealRequestQuoteDto);
        if(CollectionUtils.isNotEmpty(valid)){
            ar.setErrorList(valid);
            return new Message<AjaxResult>().success().setData(ar);
        }

        // 银行间报价-现券买卖报价-超委托的价格校验开关（01 硬阻挡 02 软阻挡）
        if (StringUtil.equals(CacheUtil.getSysParam("ibuFlag_bondOverEntrustPrice"), "02")) {
            cfetsBondDealQuotedPrice003Service.checkCalcFieldEntrust(cfetsBondDealRequestQuoteDto,
                    null, sb, false);
        }

        if (StringUtil.equals(CacheUtil.getSysParam("ibuFlag_bondOverEntrustAvaAmt"), "02")) {
            List<CfetsBondDealRequestQuoteDto> cfetsBondDealRequestQuoteDtoList = cfetsBondDealRequestQuoteDto.getCfetsBondDealRequestQuoteDtoList();
            for(CfetsBondDealRequestQuoteDto dto : cfetsBondDealRequestQuoteDtoList) {
                if (dto.getEntrustAvaNum() == null
                        || dto.getAvaNum().compareTo(dto.getEntrustAvaNum()) > 0) {
                    sb.append(this.getMessage("CFETS_COMMON_E_041",dto.getEntrustId()));
                    sb.append(GlobalConstant.HTML_NEWLINE);
                }
            }
        }
        if(CollectionUtils.isEmpty(ar.getErrorList())){
            ar= cfetsQuotedCommonServiceVue.reclasCheck(cfetsBondDealQuoteCommonDto);
        }
        /*if (sb.length() > 0) {
            sb.append("请确认是否继续？");
            ar.setData(sb);
        }
        else {
            String msg = this.getMessage("SysCommon.save");
            if (StringUtil.equals(cfetsBondDealRequestQuoteDto.getOperatorType(), "submit")) {
                msg = this.getMessage("SysCommon.submit");
            }
            ar.setData(this.getMessage("COMMON_C_0001", msg));
        }*/
        return new Message<AjaxResult>().success().setData(ar);
    }

    /**
     * 成交验证
     * @param cfetsBondDealQuoteCommonDto
     * @return
     */
    @ApiOperation(value = "请求报价成交检查", notes = "请求报价成交检查")
    @ResponseBody
    @RequestMapping("clinchDealCheck")
    public Message<AjaxResult> clinchDealCheck(@RequestBody CfetsBondDealQuoteCommonDto cfetsBondDealQuoteCommonDto) {
        AjaxResult ar = new AjaxResult();
        StringBuffer sb = new StringBuffer();
        List<CfetsBondDealRequestQuoteDto> cfetsBondDealRequestQuoteDtoList = new ArrayList<>();
        CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto = cfetsQuotedCommonServiceVue.createReueust(cfetsBondDealQuoteCommonDto);
        if(CollectionUtils.isNotEmpty(cfetsBondDealQuoteCommonDto.getPtlAllocatios())){
            cfetsBondDealRequestQuoteDto.setCfetsBondDealRequestQuoteDtoList(cfetsQuotedCommonServiceVue.createCfetsBondDealRequestQuoteDtoList(cfetsBondDealQuoteCommonDto.getPtlAllocatios()));
        }
        List<com.joyintech.tams.cfets.bankuplink.bond.dto.CounterInfoDto> counterInfoList = cfetsBondDealQuoteCommonDto.getCounterInfoList();
        if(CollectionUtils.isEmpty(counterInfoList)){
            sb.append(this.getMessage("请选择可选机构"));
        }else{
            if(counterInfoList.size()>1){
                sb.append(this.getMessage("COMMON_E_0058"));
            }
            counterInfoList.forEach(item ->{
                if(!StringUtil.equals(item.getTradeStatus(), BussinessDictCodeList.银行间上行报价状态_已回复.getValue())){
                    sb.append(this.getMessage("CFETS_COMMON_R_028"));
                    return;
                }
            });
        }
        // 银行间报价-现券买卖报价-超委托剩余额度校验开关（01 硬阻挡 02 软阻挡）
        if (StringUtil.equals(CacheUtil.getSysParam("ibuFlag_bondOverEntrustAvaAmt"), "02")
                && StringUtil.isNotEmpty(cfetsBondDealRequestQuoteDto.getEntrustId())) {
            List<CounterInfoDto> list = cfetsBondDealRequestQuoteDto.getCounterInfoList();
            if (!StringUtil.isEmptyList(list)) {
                CounterInfoDto counterInfoDto = list.get(0);
                BigDecimal shareAmt = counterInfoDto.getShareAmt();
                Map<String, Object> param = new HashMap<>();
                param.put("entrustId", cfetsBondDealRequestQuoteDto.getEntrustId());
                param.put("quoteId", cfetsBondDealRequestQuoteDto.getQuoteId());
                BigDecimal dealedAmt = cfetsBondDealQuotedPrice003Service.queryEntrustDealedAmt(
                        param);
                if (StringUtil.isNotEmpty(shareAmt)
                        && ArithmeticUtil.multiply(shareAmt, new BigDecimal(10000)).compareTo(
                        ArithmeticUtil.subtract(cfetsBondDealRequestQuoteDto.getEntrustAmt(),
                                dealedAmt)) > 0) {
                    sb.append(this.getMessage("CFETS_COMMON_E_021"));
                    sb.append(GlobalConstant.HTML_NEWLINE);
                }
            }
        }

        // 银行间报价-现券买卖报价-超委托的价格校验开关（01 硬阻挡 02 软阻挡）
        if (StringUtil.equals(CacheUtil.getSysParam("ibuFlag_bondOverEntrustPrice"), "02")) {
            cfetsBondDealQuotedPrice003Service.checkCalcFieldEntrust(cfetsBondDealRequestQuoteDto, null, sb, false);
        }
        if(sb.length()>0){
            ar.appendMessage(new ValidMessage(sb.toString()));
        }
        // 委托为空的场合只有新建模式且系统参数为软校验
        /*if (StringUtil.equals(CacheUtil.getSysParam("ibuFlag_startDialogueQuoteWithNoEntrust"), "02")
                && !StringUtil.isNotEmpty(cfetsBondDealRequestQuoteDto.getEntrustId())) {
            sb.append(this.getMessage("CFETS_COMMON_E_039"));
            sb.append(GlobalConstant.HTML_NEWLINE);
        }*/

        /*if (sb.length() > 0) {
            sb.append("请确认是否继续？");
            ar.setData(sb);
        }
        else {
            ar.setData(this.getMessage("COMMON_C_0001", this.getMessage("SysCommon.save")));
        }*/

        return new Message<AjaxResult>().success().setData(ar);
    }

    /**
     * 保存提交
     * @param cfetsBondDealRequestQuoteDto
     * @return
     */
    @ResponseBody
    @RequestMapping("saveData")
    public AjaxResult saveData(@RequestBody CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto) {

        cfetsBondDealQuotedPrice003Service.save(cfetsBondDealRequestQuoteDto);

        return new AjaxResult(this.getMessage("CFETS_COMMON_SAVE"));
    }

    /**
     * 保存提交
     * @param cfetsBondDealRequestQuoteDto
     * @return
     */
    @ResponseBody
    @RequestMapping("submit")
    public AjaxResult submit(@RequestBody CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto) {

        cfetsBondDealQuotedPrice003Service.submit(cfetsBondDealRequestQuoteDto);

        return new AjaxResult(this.getMessage("CFETS_COMMON_SUBMIT"));
    }

    /**
     * 成交
     * @param cfetsBondDealRequestQuoteDto
     * @return
     */
    @ResponseBody
    @RequestMapping("clinchDeal")
    public AjaxResult clinchDeal(@RequestBody CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto) {

        cfetsBondDealQuotedPrice003Service.clinchDeal(cfetsBondDealRequestQuoteDto);

        return new AjaxResult(this.getMessage("CFETS_COMMON_TRADE"));
    }

    /**
     * 现券买卖历史报价 撤销
     * @param cfetsBondDealQuoteCommonDto
     * @return
     * @throws WorkFlowException
     */
    @ApiOperation(value = "请求历史报价撤销", notes = "请求历史报价撤销")
    @ResponseBody
    @RequestMapping("bondRevoke")
    public Message<AjaxResult> bondRevoke(@RequestBody CfetsBondDealQuoteCommonDto cfetsBondDealQuoteCommonDto)
            throws WorkFlowException {
        CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto = new CfetsBondDealRequestQuoteDto();
        BeanUtil.copyPropertiesIgnoreNull(cfetsBondDealRequestQuoteDto,cfetsBondDealQuoteCommonDto);
        ProcessParameter<CfetsBondDealRequestQuoteDto> processParameter = new ProcessParameter<CfetsBondDealRequestQuoteDto>();
        WorkFlowParameter<CfetsBondDealRequestQuoteDto> workFlowParameter = new WorkFlowParameter<CfetsBondDealRequestQuoteDto>();
        workFlowParameter.setPageId("CfetsBondDealQuotedPrice003");
        workFlowParameter.setElementId("CfetsBondDealQuotedPrice003_btn_bondRevoke");
        workFlowParameter.setBusinessCode(cfetsBondDealRequestQuoteDto.getQuoteId());
        workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_银行间上行.getValue());
        workFlowParameter.setComment(
                entity -> getMessage("CFETSHIS_QUOTEID") + "(" + entity.getQuoteId() + ")");
        processParameter.setWorkFlowParameter(workFlowParameter);

        String msg = cfetsBondDealQuotedPrice003Service.start(processParameter,
                cfetsBondDealRequestQuoteDto);

        return new Message<AjaxResult>().success().setData(new AjaxResult(msg));
    }


    @ApiOperation(value = "请求报价提交(保存)", notes = "请求报价登记提交(保存)")
    @ResponseBody
    @RequestMapping("save")
    public Message<AjaxResult> save(@RequestBody CfetsBondDealQuoteCommonDto cfetsBondDealQuoteCommonDto, BindingResult br) throws WorkFlowException {
        if (br.hasErrors()) {
            return new Message<AjaxResult>().success().setData(new AjaxResult(br));
        }
        CfetsBondDealRequestQuoteDto cfetsBondDealRequestQuoteDto = cfetsQuotedCommonServiceVue.createReueust(cfetsBondDealQuoteCommonDto);
        if(CollectionUtils.isNotEmpty(cfetsBondDealQuoteCommonDto.getPtlAllocatios())){
            cfetsBondDealRequestQuoteDto.setCfetsBondDealRequestQuoteDtoList(cfetsQuotedCommonServiceVue.createCfetsBondDealRequestQuoteDtoList(cfetsBondDealQuoteCommonDto.getPtlAllocatios()));
            //处理下组合的可用现金和可用持仓  带到审批画面
            for (CfetsBondDealRequestQuoteDto item : cfetsBondDealRequestQuoteDto.getCfetsBondDealRequestQuoteDtoList()) {
                try{
                    Map<String,String> accnoParm = new HashMap<>();
                    accnoParm.put("ccy",cfetsBondDealQuoteCommonDto.getCcy());
                    accnoParm.put("portfolioId",item.getPortfolioId());
                    accnoParm.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
                    String accNo = cfetsIbuQuoteBondEtrMapper.getAccNo(accnoParm);
                    if(StringUtil.isEmpty(accNo)){
                        accNo = stlSecAcctInfoMapper.selectByPrimaryKey(cfetsBondDealQuoteCommonDto.getSecManageAcctId()).getTrdAcctId();
                    }
                    BaseMap baseMap = positionInfoService.queryTrdAvailableCash(Arrays.asList(item.getPortfolioId()), DateUtil.format(cfetsBondDealQuoteCommonDto.getTradeDate()), accNo, cfetsBondDealQuoteCommonDto.getCcy(), "03", Arrays.asList(DictCodeListExt1.资金账户类型_产品托管户.getValue()), null);
                    if(null!=baseMap&&baseMap.containsKey("cashT")){
                        item.setProAvailableCash((BigDecimal) baseMap.get("cashT"));
                    }
                    BaseMap baseMapSec = positionInfoService.queryTrdPosition(Arrays.asList(item.getPortfolioId()), DateUtil.format(cfetsBondDealQuoteCommonDto.getTradeDate()), cfetsBondDealQuoteCommonDto.getFinprodId());
                    if(null!=baseMapSec&&baseMapSec.containsKey("ZCTRKYCC")){
                        item.setSecManageAvailablePosition((BigDecimal)baseMapSec.get("ZCTRKYCC"));
                    }
                }catch (Exception e){

                }
            }
        }
        
        
        if (!StringUtil.isNotEmpty(cfetsBondDealRequestQuoteDto.getQuoteId())) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            codeParameter.setSeqNo("IBU_002");
            String code = bussGetBussinessIdService != null
                    ? bussGetBussinessIdService.buildBussinessCode(codeParameter) : IDGenerator.generate();
            cfetsBondDealRequestQuoteDto.setQuoteId(code);
        }
        ProcessParameter<CfetsBondDealRequestQuoteDto> processParameter = new ProcessParameter<CfetsBondDealRequestQuoteDto>();
        WorkFlowParameter<CfetsBondDealRequestQuoteDto> params = cfetsBondDealQuotedPrice003Service.getWorkFlowParameter(cfetsBondDealRequestQuoteDto);
        params.setPageId("CfetsBondDealQuotedPrice005");
        params.setElementId(cfetsBondDealRequestQuoteDto.getElementId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_银行间上行.getValue());
        params.setBusinessCode(cfetsBondDealRequestQuoteDto.getQuoteId());
        cfetsBondDealRequestQuoteDto.setQuoteType(DictCodeListExt3.报价系统区分类型_现券请求报价.getValue());
        params.setComment(entity -> getMessage("请求报价编号") + "(" + entity.getQuoteId() + ")-"
                + "业务类型" + "(现券买卖" + entity.getQuoteTypeStr() + ")-" + "平台日" + "("
                + DateUtil.format(iPlatformDate.getSysDate()) + ")");
        processParameter.setWorkFlowParameter(params);

        cfetsBondDealRequestQuoteDto.setInvManagerId(LoginUserUtil.getUserName());// 投资经理
        cfetsBondDealRequestQuoteDto.setCreateUser(LoginUserUtil.getUserName());// 用户名
        cfetsBondDealRequestQuoteDto.setCreateDept(LoginUserUtil.getDeptCode());// 用户部门
        cfetsBondDealRequestQuoteDto.setCreateTime(new Date());// 创建时间
        String msg = null;
        try{
            msg = cfetsBondDealQuotedPrice003Service.start(processParameter, cfetsBondDealRequestQuoteDto);
        }catch (Exception e){
            e.printStackTrace();
            return PositionUtil.ajaxErrorReturn(e);
        }
        return new Message<AjaxResult>().success().setData(new AjaxResult(msg));
    }

}

