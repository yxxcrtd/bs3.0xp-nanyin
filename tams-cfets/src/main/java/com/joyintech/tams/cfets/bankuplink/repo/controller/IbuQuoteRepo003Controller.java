package com.joyintech.tams.cfets.bankuplink.repo.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.cfets.bankuplink.repo.dto.CfetsRepoDealQuoteCommonDto;
import com.joyintech.tams.cfets.bankuplink.repo.dto.QuotedPriceRequest;
import com.joyintech.tams.cfets.bankuplink.repo.service.CfetsRepoDealQuotedPrice002Service;
import com.joyintech.tams.cfets.bankuplink.repo.service.CfetsRepoQuotedPrice004Service;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.bankuplink.repo.dto.CfetsRepoDealQuotedPrice001Dto;
import com.joyintech.tams.invest.bankuplink.repo.dto.CfetsRepoDealQuotedPrice003Dto;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 现券买卖(对话)
 */
@Controller
@PageId("CfetsQuoteRepo003")
@RequestMapping("/cfets/bankuplink/repo/IbuQuoteRepo003")
@Api(tags = "CFETS质押式回购交易-对话接口")
public class IbuQuoteRepo003Controller extends BaseController {

    @Autowired
    private CfetsRepoDealQuotedPrice002Service cfetsRepoDealQuotedPrice002Service;

    @Autowired
    private CfetsRepoQuotedPrice004Service cfetsRepoQuotedPrice004Service;

    @Autowired
    private IPlatformDate iPlatformDate;

    @Autowired
    private BaseGetBussinessId bussGetBussinessIdService;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, BussinessDictCode.交易方向, BussinessDictCode.清算速度, DictCode.回购方式, DictCode.价格模式,
                BussinessDictCode.结算方式, BussinessDictCode.清算类型, BussinessDictCode.币种, DictCode.期限品种_回购);
        model.addAttribute("ACCOUNT_SPEED_0", BussinessDictCodeList.清算速度_T加0.getValue());
        model.addAttribute("ACCOUNT_SPEED_1", BussinessDictCodeList.清算速度_T加1.getValue());
        model.addAttribute("ACCOUNT_SPEED_2", BussinessDictCodeList.清算速度_T加2.getValue());
        model.addAttribute("yesNoFlag_yes", BussinessDictCodeList.是否_是.getValue());
        CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto = new CfetsRepoDealQuotedPrice001Dto();
        if (StringUtil.equals(this.getParam("workflowStatus"), DictCodeListExt1.有效状态_流程中.getValue())
                || (!WorkFlowUtil.isNotFromToDoList()
                && StringUtil.isNotBlank(getParam(GlobalConstant.WORKFLOW_ID)))) {
            model.addAttribute(GlobalConstant.WORKFLOW_ID, getParam(GlobalConstant.WORKFLOW_ID));
            cfetsRepoDealQuotedPrice001Dto = cfetsRepoDealQuotedPrice002Service.getBussinessEntityByWorkflowId(
                    getParam(GlobalConstant.WORKFLOW_ID));
            if (cfetsRepoDealQuotedPrice001Dto.getQuoteId() != null) {
                // 画面模式
                cfetsRepoDealQuotedPrice001Dto.setDialogueRecord(cfetsRepoDealQuotedPrice002Service.selectDialogueRecord(cfetsRepoDealQuotedPrice001Dto).getDialogueRecord());
                if (StringUtil.isNotEmpty(this.getParam("quotaPageModel"))) {
                    cfetsRepoDealQuotedPrice001Dto.setQuotaPageModel(this.getParam("quotaPageModel"));
                }
                model.addAttribute(GlobalConstant.ENTITY, cfetsRepoDealQuotedPrice001Dto);
            }
        }
        model.addAttribute("PRICE_MODEL_PRM01", DictCodeListExt2.价格模式_指定价格.getValue());
        model.addAttribute("PS_03", DictCodeListExt3.交易方向_正回购.getValue());
        model.addAttribute("PS_04", DictCodeListExt3.交易方向_逆回购.getValue());
        model.addAttribute("quotaPageModel", "new");
        model.addAttribute("ptlAllocations", cfetsRepoDealQuotedPrice001Dto.getPtlAllocationList());
        model.addAttribute("repoEntrustPledgeDtos", cfetsRepoDealQuotedPrice001Dto.getPledgeBondList());
        //分配方式
        model.addAttribute("distribution", cfetsRepoDealQuotedPrice001Dto.getDistribution());
        return super.init(model);
    }

    @ApiOperation(value = "报价提交检查", notes = "报价提交检查")
    @PostMapping("/check")
    @ResponseBody
    public Message<AjaxResult> check(@RequestBody CfetsRepoDealQuoteCommonDto cfetsRepoDealQuoteCommonDto) {
        //check必填项
        AjaxResult ajaxResult = cfetsRepoDealQuotedPrice002Service.checkSave(cfetsRepoDealQuoteCommonDto);
        return new Message<AjaxResult>().success().setData(ajaxResult);
    }

    @ApiOperation(value = "报价初始化", notes = "报价初始化")
    @ResponseBody
    @PostMapping("initPrice")
    public Message<CfetsRepoDealQuoteCommonDto> initPrice(@RequestBody QuotedPriceRequest request) {
        String operation = request.getOperation();
        String quoteId = request.getQuoteId();
        String quotaPageModel = request.getQuotaPageModel();

        CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto = new CfetsRepoDealQuotedPrice001Dto();
        if (StringUtil.isNotEmpty(quoteId)) {
            cfetsRepoDealQuotedPrice001Dto = this.queryRepoInfoByQuoteId(quoteId, operation);
            // 画面模式
            if (StringUtil.isNotEmpty(quotaPageModel)) {
                cfetsRepoDealQuotedPrice001Dto.setQuotaPageModel(quotaPageModel);
            }
            //多组合
            Map<String, Object> map = new HashMap<>();
            map.put("quoteId", quoteId);
            //组合分配列表初始化
            cfetsRepoDealQuotedPrice001Dto.setPtlAllocationList(cfetsRepoDealQuotedPrice002Service.selectPortfolioInit(map));
        }
        /*else if (StringUtil.isNotEmpty(entrustId)) {
            //多组合
            Map<String,Object> map = new HashMap<>();
            List<String> entrustIdList = Arrays.asList(entrustId.split(","));
            map.put("entrustIdList", entrustIdList);
            cfetsRepoDealQuotedPrice001Dto = cfetsRepoDealQuotedPrice002Service.queryEntrustInfoByEntrustId(entrustIdList.get(0), null);
            //组合分配列表初始化
            cfetsRepoDealQuotedPrice001Dto.setPtlAllocationList(cfetsRepoDealQuotedPrice003Service.selectPortfolioInit(map));
            // 画面模式
            if (StringUtil.isNotEmpty(this.getParam("quotaPageModel"))) {
                cfetsRepoDealQuotedPrice001Dto.setQuotaPageModel(this.getParam("quotaPageModel"));
            }
        }*/
        else {
            cfetsRepoDealQuotedPrice001Dto = new CfetsRepoDealQuotedPrice001Dto();
            // 画面模式
            if (StringUtil.isNotEmpty(quotaPageModel)) {
                cfetsRepoDealQuotedPrice001Dto.setQuotaPageModel(quotaPageModel);
            }
        }
//        cfetsRepoDealQuotedPrice002Service.单独处理页面质押券列表(cfetsRepoDealQuotedPrice001Dto);
        CfetsRepoDealQuoteCommonDto pageDto = cfetsRepoDealQuotedPrice002Service.back2PageDto(cfetsRepoDealQuotedPrice001Dto);
        return new Message<CfetsRepoDealQuoteCommonDto>().success().setData(pageDto);
    }

    /**
     * 根据报价代码查询报价信息
     *
     * @param quoteId   报价代码
     * @param operation 是否复制操作
     * @return com.joyintech.tams.invest.bankuplink.repo.dto.CfetsRepoDealQuotedPrice001Dto
     * @author jiangpeng
     * @date 2021/12/30 17:39
     */
    private CfetsRepoDealQuotedPrice001Dto queryRepoInfoByQuoteId(String quoteId, String operation) {
        // 根据报价id查询数据
        CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto = cfetsRepoDealQuotedPrice002Service.queryEntrustInfoByQuoteId(
                quoteId);
        if (StringUtil.equals(cfetsRepoDealQuotedPrice001Dto.getPs(), DictCodeList.回购拆借方向_正回购.getValue())) {
            Map<String, Object> map = new HashMap<>();
            map.put("quoteId", quoteId);
            //多组合质押券列表单独处理
            map.put("platDate", iPlatformDate.getSysDateStr());
            map.put("ps", cfetsRepoDealQuotedPrice001Dto.getPs());
            BussRepoSecDto bussRepoSecDto = new BussRepoSecDto();
            List<CfetsRepoDealQuotedPrice003Dto> cfetsRepoDealQuotedPrice003s = cfetsRepoDealQuotedPrice002Service.selectQuoteCrepPledgeForDzh(map);
            cfetsRepoDealQuotedPrice001Dto.setPledgeBondList(cfetsRepoDealQuotedPrice003s);
        }
        //逆回购  质押券列表  可质押以万元为单位
        if (StringUtil.equals(cfetsRepoDealQuotedPrice001Dto.getPs(), DictCodeList.回购拆借方向_逆回购.getValue()) && !StringUtil.isEmptyList(cfetsRepoDealQuotedPrice001Dto.getPledgeBondList())) {
            cfetsRepoDealQuotedPrice001Dto.getPledgeBondList().forEach(item -> {
                item.setPledgePrin(ArithmeticUtil.divide(item.getPledgePrin(), new BigDecimal(10000), 2));
            });
        }
        if (StringUtil.equals(operation, "copy")
                && StringUtil.isNotEmpty(quoteId)) {
            cfetsRepoDealQuotedPrice001Dto.setQuoteId(null);
        }
        if (cfetsRepoDealQuotedPrice001Dto.getQuoteId() != null) {
            // 交谈记录
            cfetsRepoDealQuotedPrice001Dto.setDialogueRecord(cfetsRepoDealQuotedPrice002Service.selectDialogueRecord(cfetsRepoDealQuotedPrice001Dto).getDialogueRecord());
        }
        return cfetsRepoDealQuotedPrice001Dto;
    }

    @ApiOperation(value = "报价提交(保存)", notes = "报价提交(保存)")
    @ResponseBody
    @PostMapping("save")
    public AjaxResult save(@RequestBody CfetsRepoDealQuoteCommonDto pageDto, BindingResult br) throws WorkFlowException {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto = cfetsRepoDealQuotedPrice002Service.page2BackDto(pageDto);
        cfetsRepoDealQuotedPrice001Dto.setPageId("CfetsQuoteRepo003");
        if (!StringUtil.isNotEmpty(cfetsRepoDealQuotedPrice001Dto.getQuoteId())) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            codeParameter.setSeqNo("IBU_003");
            String code = bussGetBussinessIdService != null
                    ? bussGetBussinessIdService.buildBussinessCode(codeParameter) : IDGenerator.generate();
            cfetsRepoDealQuotedPrice001Dto.setQuoteId(code);
        }
        ProcessParameter<CfetsRepoDealQuotedPrice001Dto> processParameter = new ProcessParameter<CfetsRepoDealQuotedPrice001Dto>();
        WorkFlowParameter<CfetsRepoDealQuotedPrice001Dto> params = cfetsRepoDealQuotedPrice002Service.getWorkFlowParameter(cfetsRepoDealQuotedPrice001Dto);
        params.setPageId(cfetsRepoDealQuotedPrice001Dto.getPageId());
        params.setElementId(cfetsRepoDealQuotedPrice001Dto.getElementId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_本币业务交易_质押式回购_对话.getValue());
        params.setBusinessCode(cfetsRepoDealQuotedPrice001Dto.getQuoteId());
        params.setComment(entity -> getMessage("对话报价编号") + "(" + entity.getQuoteId() + ")-"
                + "业务类型" + "(" + DictCodeListExt3.报价系统区分类型_质押式回购对话报价.getCodeName() + ")-" + "平台日" + "("
                + DateUtil.format(iPlatformDate.getSysDate()) + ")");
        processParameter.setWorkFlowParameter(params);

        cfetsRepoDealQuotedPrice001Dto.setInvManagerId(LoginUserUtil.getUserName());// 投资经理
        cfetsRepoDealQuotedPrice001Dto.setCreateUser(LoginUserUtil.getUserName());// 用户名
        cfetsRepoDealQuotedPrice001Dto.setCreateDept(LoginUserUtil.getDeptCode());// 用户部门
        cfetsRepoDealQuotedPrice001Dto.setCreateTime(new Date());// 创建时间
        String msg = cfetsRepoDealQuotedPrice002Service.start(processParameter, cfetsRepoDealQuotedPrice001Dto);
        return new AjaxResult(msg);
    }

    @ApiOperation(value = "撤回", notes = "撤回")
    @ResponseBody
    @PostMapping("revert")
    public boolean revert(@RequestBody QuotedPriceRequest request) {
        String operation = request.getOperation();
        String quoteId = request.getQuoteId();
        String quotaPageModel = request.getQuotaPageModel();

        CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto = this.queryRepoInfoByQuoteId(quoteId, operation);
        // 画面模式
        if (StringUtil.isNotEmpty(quotaPageModel)) {
            cfetsRepoDealQuotedPrice001Dto.setQuotaPageModel(quotaPageModel);
        }
        //多组合
        Map<String, Object> map = new HashMap<>();
        map.put("quoteId", quoteId);
        //组合分配列表初始化
        cfetsRepoDealQuotedPrice001Dto.setPtlAllocationList(cfetsRepoDealQuotedPrice002Service.selectPortfolioInit(map));
        Map<String, Object> pageParam = BeanUtil.object2Map(cfetsRepoDealQuotedPrice001Dto);
        return cfetsRepoDealQuotedPrice002Service.revert(pageParam);
    }

    @ApiOperation(value = "回退", notes = "回退")
    @ResponseBody
    @PostMapping("restoreDayOff")
    public boolean restoreDayOff(@RequestBody QuotedPriceRequest request) {
        String operation = request.getOperation();
        String quoteId = request.getQuoteId();
        String quotaPageModel = request.getQuotaPageModel();

        CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto = new CfetsRepoDealQuotedPrice001Dto();
        cfetsRepoDealQuotedPrice001Dto = this.queryRepoInfoByQuoteId(quoteId, operation);
        // 画面模式
        if (StringUtil.isNotEmpty(quotaPageModel)) {
            cfetsRepoDealQuotedPrice001Dto.setQuotaPageModel(quotaPageModel);
        }
        //多组合
        Map<String, Object> map = new HashMap<>();
        map.put("quoteId", quoteId);
        //组合分配列表初始化
        cfetsRepoDealQuotedPrice001Dto.setPtlAllocationList(cfetsRepoDealQuotedPrice002Service.selectPortfolioInit(map));
        return cfetsRepoDealQuotedPrice002Service.restoreDayOff(cfetsRepoDealQuotedPrice001Dto);
    }

    @ApiOperation(value = "对话报价验证", notes = "对话报价验证")
    @ResponseBody
    @PostMapping("checkEditList")
    public AjaxResult checkEditList(@RequestBody QuotedPriceRequest request) {
        List<QuotedPriceRequest> dataList = request.getRequests();
        AjaxResult ar = null;
        for (QuotedPriceRequest data : dataList) {
            //多组合标识或者本方询价方为回复方 均跳转多组合界面
            String quoteRoleType = data.getQuoteRoleType();
            if (StringUtil.equals(quoteRoleType, BussinessDictCodeList.银行间上行本方角色_回复方.getValue())) {
                data.setEntrustId(null);
            }
            ar = cfetsRepoDealQuotedPrice002Service.checkEdit(data);
            if (!StringUtil.isEmptyList(ar.getErrorList())) {
                return ar;
            }
        }
        return ar;
    }

}

