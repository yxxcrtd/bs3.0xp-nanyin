package com.joyintech.tams.cfets.bankuplink.bond.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.business.common.util.PositionUtil;
import com.joyintech.tams.cfets.bankuplink.bond.dto.CfetsBondDealQuoteCommonDto;
import com.joyintech.tams.cfets.bankuplink.bond.service.CfetsBondDealQuotedPrice002Service;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.bankuplink.bond.dto.CfetsBondDealDialogueQuoteDto;
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
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 现券买卖(对话)
 */
@Controller
@RequestMapping("/cfets/bond/IbuQuoteBond002")
@Api(tags = "CFETS债券交易-对话")
public class IbuQuoteBond002Controller extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsBondDealQuotedPrice002Service cfetsBondDealQuotedPrice002Service;

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

    @ApiOperation(value = "对话报价初始化", notes = "对话报价初始化")
    @PostMapping("/initPrice")
    @ResponseBody
    public Message<CfetsBondDealQuoteCommonDto> initPrice(@RequestBody CfetsBondDealQuoteCommonDto cfetsBondDealQuoteCommonDto){
        CfetsBondDealDialogueQuoteDto cfetsBondDealDialogueQuoteDto = new CfetsBondDealDialogueQuoteDto();
        String quoteId = cfetsBondDealQuoteCommonDto.getQuoteId();
        String quotaPageModel = cfetsBondDealQuoteCommonDto.getQuotaPageModel();
        cfetsBondDealDialogueQuoteDto = cfetsBondDealQuotedPrice002Service.getBondDialogueQuote(
                quoteId,cfetsBondDealQuoteCommonDto.getValidStatus());
        cfetsBondDealDialogueQuoteDto.setAvaNum(com.joyintech.calculation.util.ArithmeticUtil.divide(cfetsBondDealDialogueQuoteDto.getAvaNum(),new BigDecimal(10000),2));
        //组合分配列表
        List <CfetsBondDealDialogueQuoteDto> cfetsBondDealDialogueQuoteDtoList =cfetsBondDealQuotedPrice002Service.getCfetsBondDealDialogueQuoteDtoList(quoteId);
        /*for(CfetsBondDealDialogueQuoteDto cfetsBondDealDialogueQuote:cfetsBondDealDialogueQuoteDtoList){
            cfetsBondDealDialogueQuote.setMatureYield(cfetsBondDealDialogueQuoteDto.getMatureYield());
            cfetsBondDealDialogueQuote.setExerYield(cfetsBondDealDialogueQuoteDto.getExerYield());
        }*/
        cfetsBondDealDialogueQuoteDto.setCfetsBondDealDialogueQuoteDtoList(cfetsBondDealDialogueQuoteDtoList);
        // 补充本方交易员(做市报价回复的场景)
        if (StringUtil.equals(quotaPageModel, "marketResponse")) {
            if (!StringUtil.isNotEmpty(cfetsBondDealDialogueQuoteDto.getMySideTrader())) {
                cfetsBondDealDialogueQuoteDto.setMySideTrader(LoginUserUtil.getUserName());
            }
            if (cfetsBondDealDialogueQuoteDto.getResponseAmt() == null) {
                cfetsBondDealDialogueQuoteDto.setResponseAmt(cfetsBondDealDialogueQuoteDto.getAvaNum());
            }
            // add by zhangliang 2020-07-20  此处特殊处理是因为撤回我方回复做市报价原来的委托代码依然会记录
            if (!StringUtil.equals(this.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_REFERENCE)) {
                cfetsBondDealDialogueQuoteDto.setEntrustId(null);
            }
        }
        // 复制模式打开时需要把报价编号置空
        if (StringUtil.equals(cfetsBondDealQuoteCommonDto.getOpenModel(), "copy")) {
            cfetsBondDealDialogueQuoteDto.setQuoteId(null);
            // 复制的场合券面总额默认为剩余额度
            if (cfetsBondDealDialogueQuoteDto.getEntrustId() != null) {
                CfetsBondDealDialogueQuoteDto entrustInfo = cfetsBondDealQuotedPrice002Service.queryEntrustInfoById(
                        cfetsBondDealDialogueQuoteDto.getEntrustId());
                if (entrustInfo != null && entrustInfo.getEntrustNum() != null) {
                    cfetsBondDealDialogueQuoteDto.setAvaNum(entrustInfo.getAvaNum());
                }
            }
            else if (!StringUtil.isEmptyList(cfetsBondDealDialogueQuoteDto.getCfetsBondDealDialogueQuoteDtoList())) {
                // 多组合场景
                // 券面总额：默认未完成各单笔指令剩余额度总和
                // 组合信息：根据各指令带出，券面总额默认为各指令剩余额度。已完成指令不再显示在列表中。
                BigDecimal tempSumAvaAmt = BigDecimal.ZERO;
                List<CfetsBondDealDialogueQuoteDto> tempEntrustList = new ArrayList<>();
                for (CfetsBondDealDialogueQuoteDto tempDto : cfetsBondDealDialogueQuoteDto.getCfetsBondDealDialogueQuoteDtoList()) {
                    if (BigDecimal.ZERO.compareTo(tempDto.getEntrustAvaNum()) < 0) {
                        tempDto.setAvaNum(tempDto.getEntrustAvaNum());
                        tempSumAvaAmt = com.joyintech.framework.util.ArithmeticUtil.doAdd(tempSumAvaAmt, tempDto.getEntrustAvaNum());
                        tempEntrustList.add(tempDto);
                    }
                }
                cfetsBondDealDialogueQuoteDto.setAvaNum(tempSumAvaAmt);
                cfetsBondDealDialogueQuoteDto.setCfetsBondDealDialogueQuoteDtoList(tempEntrustList);
            }
        }
        //修改
        if(StringUtil.equals(cfetsBondDealQuoteCommonDto.getOpenModel(), "edit")){
            for (CfetsBondDealDialogueQuoteDto item : cfetsBondDealDialogueQuoteDto.getCfetsBondDealDialogueQuoteDtoList()) {
                //恢复指令剩余额度
                item.setEntrustAvaNum(ArithmeticUtil.doAdd(item.getEntrustAvaNum(),item.getAvaNum()));
            }
        }
        //复制操作 返回前台
        cfetsBondDealQuoteCommonDto = cfetsQuotedCommonServiceVue.createDialogue2(cfetsBondDealDialogueQuoteDto);
        if(CollectionUtils.isNotEmpty(cfetsBondDealDialogueQuoteDto.getCfetsBondDealDialogueQuoteDtoList())){
            cfetsBondDealQuoteCommonDto.setPtlAllocatios(cfetsQuotedCommonServiceVue.createPtlAllocationList(cfetsBondDealDialogueQuoteDto.getCfetsBondDealDialogueQuoteDtoList()));
        }
        //信贷资产支持证券、资产支持票据锚定值应默认净价且不可修改
        if(StringUtil.equals(cfetsBondDealQuoteCommonDto.getType11(),DictCodeListExt3.行内资产四级分类_交易商协会ABN.getValue())||StringUtil.equals(cfetsBondDealQuoteCommonDto.getType11(),DictCodeListExt3.行内资产四级分类_银保监会主管ABS.getValue())){
            cfetsBondDealQuoteCommonDto.setCalculateField("01");
        }
        cfetsBondDealQuoteCommonDto.setMatureYield(ArithmeticUtil.divide(cfetsBondDealQuoteCommonDto.getMatureYield(),new BigDecimal(100),6));
        cfetsBondDealQuoteCommonDto.setExerYield(ArithmeticUtil.divide(cfetsBondDealQuoteCommonDto.getExerYield(),new BigDecimal(100),6));
        return new Message<CfetsBondDealQuoteCommonDto>().success().setData(cfetsBondDealQuoteCommonDto);
    }

     /**
     * 保存验证
     * @param cfetsBondDealQuoteCommonDto
     * @return
     */
    @ApiOperation(value = "报价提交检查", notes = "报价提交检查")
    @PostMapping("/check")
    @ResponseBody
    public Message<AjaxResult> check(@RequestBody  CfetsBondDealQuoteCommonDto cfetsBondDealQuoteCommonDto) {
        AjaxResult ar = new AjaxResult();
        ar = cfetsBondDealQuotedPrice002Service.checkNotEmpty(cfetsBondDealQuoteCommonDto);
        if(CollectionUtils.isNotEmpty(ar.getErrorList())){
            return new Message<AjaxResult>().success().setData(ar);
        }
        if(CollectionUtils.isEmpty(ar.getErrorList())){
            ar = cfetsQuotedCommonServiceVue.checkcheckAnchoring(cfetsBondDealQuoteCommonDto);
        }
        if(CollectionUtils.isEmpty(ar.getErrorList())){
            ar = cfetsQuotedCommonServiceVue.positionCheck(cfetsBondDealQuoteCommonDto);
        }
        CfetsBondDealDialogueQuoteDto cfetsBondDealDialogueQuoteDto = cfetsQuotedCommonServiceVue.createDialogue(cfetsBondDealQuoteCommonDto);
        if(CollectionUtils.isNotEmpty(cfetsBondDealQuoteCommonDto.getPtlAllocatios())){
            cfetsBondDealDialogueQuoteDto.setCfetsBondDealDialogueQuoteDtoList(cfetsQuotedCommonServiceVue.createCfetsBondDealDialogueQuoteDtoList(cfetsBondDealQuoteCommonDto.getPtlAllocatios()));
        }
        StringBuffer sb = new StringBuffer();
        String isDzh = "Y";
        List<ValidMessage> valid = cfetsBondDealQuotedPrice002Service.valid(cfetsBondDealDialogueQuoteDto);
        if(CollectionUtils.isNotEmpty(valid)){
            ar.setErrorList(valid);
            return new Message<AjaxResult>().success().setData(ar);
        }
        // 委托剩余额度
        List<CfetsBondDealDialogueQuoteDto> cfetsBondDealDialogueQuotes = cfetsBondDealDialogueQuoteDto.getCfetsBondDealDialogueQuoteDtoList();
        if (StringUtil.equals(CacheUtil.getSysParam("ibuFlag_bondOverEntrustAvaAmt"), "02")){
            if(StringUtil.equals(isDzh,"Y")){
                if(null!=cfetsBondDealDialogueQuotes){
                    cfetsBondDealDialogueQuotes.stream().forEach(item -> {
                        CfetsBondDealDialogueQuoteDto cfetsBondDealDialogueQuoteDto2 = new CfetsBondDealDialogueQuoteDto();
                        BeanUtil.copyPropertiesIgnoreNull(cfetsBondDealDialogueQuoteDto2,cfetsBondDealDialogueQuoteDto);
                        cfetsBondDealDialogueQuoteDto2.setEntrustId(item.getEntrustId());
                        cfetsBondDealDialogueQuoteDto2.setAvaNum(item.getAvaNum());
                        cfetsBondDealQuotedPrice002Service.checkEntrustAvaNum(cfetsBondDealDialogueQuoteDto2, null, sb, false);
                        if (sb.length() > 0){
                            return;
                        }
                    });
                }
            }else{
                cfetsBondDealQuotedPrice002Service.checkEntrustAvaNum(cfetsBondDealDialogueQuoteDto, null, sb, false);
            }
        }

        // 偏离度
        if (StringUtil.equals(CacheUtil.getSysParam("ibuFlag_dealDeviation"), "02")
                && StringUtil.isNotEmpty(CacheUtil.getSysParam("ibuVar_dealDeviationVal"))){
            cfetsBondDealQuotedPrice002Service.checkDealDeviation(cfetsBondDealDialogueQuoteDto, null, sb, false);
        }

        // 限价
        if (StringUtil.equals(CacheUtil.getSysParam("ibuFlag_bondOverEntrustPrice"), "02")) {
            if (StringUtil.equals(isDzh, "Y")) {
                if (null != cfetsBondDealDialogueQuotes) {
                    cfetsBondDealDialogueQuotes.stream().forEach(item -> {
                        CfetsBondDealDialogueQuoteDto cfetsBondDealDialogueQuoteDto2 = new CfetsBondDealDialogueQuoteDto();
                        BeanUtil.copyPropertiesIgnoreNull(cfetsBondDealDialogueQuoteDto2, cfetsBondDealDialogueQuoteDto);
                        cfetsBondDealDialogueQuoteDto2.setEntrustId(item.getEntrustId());
                        cfetsBondDealDialogueQuoteDto2.setPrice(item.getPrice());
                        cfetsBondDealQuotedPrice002Service.checkCalcFieldEntrust(cfetsBondDealDialogueQuoteDto2, null, sb, false);
                    });
                }
            }
            else {
                cfetsBondDealQuotedPrice002Service.checkCalcFieldEntrust(cfetsBondDealDialogueQuoteDto, null, sb, false);
            }
        }
        if(CollectionUtils.isEmpty(ar.getErrorList())){
            ar= cfetsQuotedCommonServiceVue.reclasCheck(cfetsBondDealQuoteCommonDto);
        }
        /*if (sb.length() > 0){
            sb.append("请确认是否继续？");
            ar.setData(sb);
        }
        else{
            String msg = this.getMessage("SysCommon.save");
            if (StringUtil.equals(cfetsBondDealDialogueQuoteDto.getOperatorType(), "submit")) {
                msg = this.getMessage("SysCommon.submit");
            }
            if (StringUtil.equals(cfetsBondDealDialogueQuoteDto.getOperatorType(), "done")) {
                msg = "成交";
            }
            if (StringUtil.equals(cfetsBondDealDialogueQuoteDto.getOperatorType(), "refuse")) {
                msg = "拒绝";
            }

            ar.setData(this.getMessage("COMMON_C_0001", msg));
        }*/
        return new Message<AjaxResult>().success().setData(ar);
    }

    @ApiOperation(value = "对话报价拒绝", notes = "对话报价拒绝")
    @ResponseBody
    @RequestMapping("saveData")
    public Message<AjaxResult> saveData(@RequestBody CfetsBondDealDialogueQuoteDto cfetsBondDealDialogueQuoteDto) {

        cfetsBondDealQuotedPrice002Service.save(cfetsBondDealDialogueQuoteDto);

        return new Message<AjaxResult>().success().setData(new AjaxResult(this.getMessage("CFETS_COMMON_SUCSESS")));
    }

    @ApiOperation(value = "对话报价提交(保存)", notes = "对话报价提交(保存)")
    @ResponseBody
    @RequestMapping("save")
    public Message<AjaxResult> save(@RequestBody CfetsBondDealQuoteCommonDto cfetsBondDealQuoteCommonDto, BindingResult br) throws WorkFlowException {
        if (br.hasErrors()) {
            return new Message<AjaxResult>().success().setData(new AjaxResult(br));
        }
        CfetsBondDealDialogueQuoteDto cfetsBondDealDialogueQuoteDto = cfetsQuotedCommonServiceVue.createDialogue(cfetsBondDealQuoteCommonDto);
        if(CollectionUtils.isNotEmpty(cfetsBondDealQuoteCommonDto.getPtlAllocatios())){
            cfetsBondDealDialogueQuoteDto.setCfetsBondDealDialogueQuoteDtoList(cfetsQuotedCommonServiceVue.createCfetsBondDealDialogueQuoteDtoList(cfetsBondDealQuoteCommonDto.getPtlAllocatios()));
        }
        if (!StringUtil.isNotEmpty(cfetsBondDealDialogueQuoteDto.getQuoteId())) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            codeParameter.setSeqNo("IBU_001");
            String code = bussGetBussinessIdService != null
                    ? bussGetBussinessIdService.buildBussinessCode(codeParameter) : IDGenerator.generate();
            cfetsBondDealDialogueQuoteDto.setQuoteId(code);
        }
        ProcessParameter<CfetsBondDealDialogueQuoteDto> processParameter = new ProcessParameter<CfetsBondDealDialogueQuoteDto>();
        WorkFlowParameter<CfetsBondDealDialogueQuoteDto> params = cfetsBondDealQuotedPrice002Service.getWorkFlowParameter(cfetsBondDealDialogueQuoteDto);
        params.setPageId("CfetsBondDealQuotedPrice004");
        params.setElementId(cfetsBondDealDialogueQuoteDto.getElementId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_本币业务交易_对话.getValue());
        params.setBusinessCode(cfetsBondDealDialogueQuoteDto.getQuoteId());
        cfetsBondDealDialogueQuoteDto.setQuoteType(DictCodeListExt3.报价系统区分类型_现券对话报价.getValue());
        //报价操作
        String quoteOperatorType = "发起";
        if (StringUtil.equals(cfetsBondDealDialogueQuoteDto.getQuotaPageModel(), "marketResponse")) {
            quoteOperatorType = "回复";
        }
        if (StringUtil.equals(cfetsBondDealDialogueQuoteDto.getQuotaPageModel(), "dialogueResponse")&&StringUtil.equals(cfetsBondDealDialogueQuoteDto.getOperatorType(), "done")) {
            quoteOperatorType = "成交";
        }
        if (StringUtil.equals(cfetsBondDealDialogueQuoteDto.getQuotaPageModel(), "new")) {
            quoteOperatorType = "发起";
        }
        if (StringUtil.equals(cfetsBondDealDialogueQuoteDto.getQuotaPageModel(), "edit")) {
            quoteOperatorType = "修改";
        }
        cfetsBondDealDialogueQuoteDto.setQuoteOperatorType(quoteOperatorType);
        DecimalFormat  df = new DecimalFormat("###,###.00");
        params.setComment(entity -> "报价日期"+ "(" + DateUtil.format(entity.getSettleDate()) + ")-"
                + "资产简称("+entity.getFinprodAbbr()+")-" + "对手方("+entity.getCounterName()+")-"+"报价操作("+entity.getQuoteOperatorType()+")-"+"券面总额("+df.format(entity.getAvaNum().setScale(2))+"万元)");
        processParameter.setWorkFlowParameter(params);

        cfetsBondDealDialogueQuoteDto.setInvManagerId(LoginUserUtil.getUserName());// 投资经理
        cfetsBondDealDialogueQuoteDto.setCreateUser(LoginUserUtil.getUserName());// 用户名
        cfetsBondDealDialogueQuoteDto.setCreateDept(LoginUserUtil.getDeptCode());// 用户部门
        cfetsBondDealDialogueQuoteDto.setCreateTime(new Date());// 创建时间
        String msg = null;
        try{
            msg = cfetsBondDealQuotedPrice002Service.start(processParameter, cfetsBondDealDialogueQuoteDto);
        }catch (Exception e){
            e.printStackTrace();
            return PositionUtil.ajaxErrorReturn(e);
        }
        return new Message<AjaxResult>().success().setData(new AjaxResult(msg));
    }


    /**
     * 现券买卖历史报价 撤销
     * @param cfetsBondDealQuoteCommonDto
     * @return
     * @throws WorkFlowException
     */
    @ApiOperation(value = "对话历史报价撤销", notes = "对话历史报价撤销")
    @ResponseBody
    @RequestMapping("bondRevoke")
    public Message<AjaxResult> bondRevoke(@RequestBody CfetsBondDealQuoteCommonDto cfetsBondDealQuoteCommonDto) throws WorkFlowException {
        CfetsBondDealDialogueQuoteDto cfetsBondDealDialogueQuoteDto = new CfetsBondDealDialogueQuoteDto();
        BeanUtil.copyPropertiesIgnoreNull(cfetsBondDealDialogueQuoteDto,cfetsBondDealQuoteCommonDto);
        ProcessParameter<CfetsBondDealDialogueQuoteDto> processParameter = new ProcessParameter<CfetsBondDealDialogueQuoteDto>();
        WorkFlowParameter<CfetsBondDealDialogueQuoteDto> workFlowParameter = new WorkFlowParameter<CfetsBondDealDialogueQuoteDto>();
        workFlowParameter.setPageId("CfetsBondDealQuotedPrice002");
        workFlowParameter.setElementId("CfetsBondDealQuotedPrice002_btn_bondRevoke");
        workFlowParameter.setBusinessCode(cfetsBondDealDialogueQuoteDto.getQuoteId());
        workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_本币业务交易_对话.getValue());
        //调用初始化接口  供描述用
        CfetsBondDealDialogueQuoteDto bondDialogueQuote = cfetsBondDealQuotedPrice002Service.getBondDialogueQuote(cfetsBondDealQuoteCommonDto.getQuoteId(), BussinessDictCodeList.有效状态_有效.getValue());
        cfetsBondDealDialogueQuoteDto.setSettleDate(bondDialogueQuote.getSettleDate());
        cfetsBondDealDialogueQuoteDto.setFinprodAbbr(bondDialogueQuote.getFinprodAbbr());
        cfetsBondDealDialogueQuoteDto.setCounterName(bondDialogueQuote.getCounterName());
        cfetsBondDealDialogueQuoteDto.setAvaNum(bondDialogueQuote.getAvaNum());
        DecimalFormat df = new DecimalFormat("###,###.00");
        workFlowParameter.setComment(entity -> "报价日期"+ "(" + DateUtil.format(entity.getSettleDate()) + ")-"
                + "资产简称("+entity.getFinprodAbbr()+")-" + "对手方("+entity.getCounterName()+")-"+"报价操作(撤销)-"+"券面总额("+df.format(entity.getAvaNum().setScale(2))+"万元)");
        processParameter.setWorkFlowParameter(workFlowParameter);

        String msg = cfetsBondDealQuotedPrice002Service.start(processParameter, cfetsBondDealDialogueQuoteDto);

        return new Message<AjaxResult>().success().setData(new AjaxResult(msg));
    }

}

