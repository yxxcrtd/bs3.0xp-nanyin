package com.joyintech.tams.invest.bankuplink.repo.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.bankuplink.repo.dto.CfetsRepoDealQuotedPrice001Dto;
import com.joyintech.tams.invest.bankuplink.repo.dto.CfetsRepoDealQuotedPrice003Dto;
import com.joyintech.tams.invest.bankuplink.repo.service.CfetsRepoDealQuotedPrice001Service;
import com.joyintech.tams.invest.bankuplink.repo.service.CfetsRepoDealQuotedPrice002Service;
import com.joyintech.tams.invest.bankuplink.repo.service.CfetsRepoDealQuotedPrice003Service;
import com.joyintech.tams.invest.bankuplink.repo.service.CfetsRepoDealQuotedPrice004Service;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 *  银行间上下行 回购  多组合
 * @auther zhangnian
 * @date 2021/2/25
 */
@Controller
@PageId("CfetsRepoDealQuotedPrice003")
@RequestMapping("/invest/bankuplink/repo/CfetsRepoDealQuotedPrice003")
public class CfetsRepoDealQuotedPrice003Controller extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsRepoDealQuotedPrice002Service cfetsRepoDealQuotedPrice002Service;

    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsRepoDealQuotedPrice003Service cfetsRepoDealQuotedPrice003Service;

    /**
     * {@linkplain}
     */
    @Autowired
    private CfetsRepoDealQuotedPrice004Service cfetsRepoDealQuotedPrice004Service;

    @Autowired
    private CfetsRepoDealQuotedPrice001Service cfetsRepoDealQuotedPrice001Service;

    @Autowired
    private IPlatformDate iPlatformDate;

    @Autowired
    private BaseGetBussinessId bussGetBussinessIdService;

    @Override
    public String init(Model model) {

        this.setSelectOptionToModel(model, BussinessDictCode.回购拆借方向, BussinessDictCode.清算速度,
                BussinessDictCode.结算方式, BussinessDictCode.清算类型, BussinessDictCode.币种,
                BussinessDictCode.计息基础, BussinessDictCode.回购期限品种, DictCode.分配方式);
        model.addAttribute("ACCOUNT_SPEED_1", BussinessDictCodeList.清算速度_T加1.getValue());
        model.addAttribute("yesNoFlag_yes", BussinessDictCodeList.是否_是.getValue());
        model.addAttribute("assetTypeOne_abs", BussinessDictCodeList.平安资产一级分类_资产支持证券.getValue());
        model.addAttribute("psZHG", BussinessDictCodeList.回购拆借方向_正回购.getValue());
        // 可否无委托回复
        model.addAttribute("ibuFlag_answerQuoteWithOutEntrust",
                CacheUtil.getSysParam("ibuFlag_answerQuoteWithOutEntrust"));
        // 本方交易员是否可编辑
        model.addAttribute("ibuFlag_ouTraderIdCanEdit",
                CacheUtil.getSysParam("ibuFlag_ouTraderIdCanEdit"));
        model.addAttribute("quoteRoleTypeEnquiry", BussinessDictCodeList.银行间上行本方角色_询价方.getValue());
        model.addAttribute("quoteRoleTypeReply", BussinessDictCodeList.银行间上行本方角色_回复方.getValue());
        String entrustId = this.getParam("entrustId");
        String operation = this.getParam("operation");
        String quoteId = this.getParam("quoteId");
        if (StringUtil.equals(operation, "CfetsRepoDealQuotedPrice001.reply")||StringUtil.equals(operation, "CfetsRepoDealQuotedPrice001.edit")) {
            model.addAttribute("operation", operation);
        }
        model.addAttribute("editOperation", this.getParam("editOperation"));//区分双击进去修改页面与点击修改进入页面
        CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto = new CfetsRepoDealQuotedPrice001Dto();
        if (StringUtil.equals(this.getParam("workflowStatus"), DictCodeListExt1.有效状态_流程中.getValue())
                || (!WorkFlowUtil.isNotFromToDoList()
                && StringUtil.isNotBlank(getParam(GlobalConstant.WORKFLOW_ID)))) {
            model.addAttribute(GlobalConstant.WORKFLOW_ID,getParam(GlobalConstant.WORKFLOW_ID));
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
        else if (StringUtil.isNotEmpty(quoteId)) {
            cfetsRepoDealQuotedPrice001Dto = this.queryRepoInfoByQuoteId(quoteId, operation);
            // 画面模式
            if (StringUtil.isNotEmpty(this.getParam("quotaPageModel"))) {
                cfetsRepoDealQuotedPrice001Dto.setQuotaPageModel(this.getParam("quotaPageModel"));
            }
            //多组合
            Map<String,Object> map = new HashMap<>();
            map.put("quoteId", quoteId);
            model.addAttribute(GlobalConstant.ENTITY,cfetsRepoDealQuotedPrice001Dto);
            //组合分配列表初始化
            cfetsRepoDealQuotedPrice001Dto.setPtlAllocationList(cfetsRepoDealQuotedPrice003Service.selectPortfolioInit(map));
        }
        else if (StringUtil.isNotEmpty(entrustId)) {
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
            // cfetsRepoDealQuotedPrice001Dto.setMySideTrader();// TODO 查询用户对应的交易员
            model.addAttribute(GlobalConstant.ENTITY, cfetsRepoDealQuotedPrice001Dto);
        }else{
            cfetsRepoDealQuotedPrice001Dto = new CfetsRepoDealQuotedPrice001Dto();
            // 画面模式
            if (StringUtil.isNotEmpty(this.getParam("quotaPageModel"))) {
                cfetsRepoDealQuotedPrice001Dto.setQuotaPageModel(this.getParam("quotaPageModel"));
            }
            // cfetsRepoDealQuotedPrice001Dto.setMySideTrader();// TODO 查询用户对应的交易员
            model.addAttribute(GlobalConstant.ENTITY, cfetsRepoDealQuotedPrice001Dto);
        }
        // 质押品超过剩余可质押面额是否继续
        model.addAttribute("ibuFlag_repoOverAvaCanContinue",
                CacheUtil.getSysParam("ibuFlag_repoOverAvaCanContinue"));
        model.addAttribute("settleSpreedT0", BussinessDictCodeList.清算速度_T加0.getValue());
        model.addAttribute("settleSpreedT1", BussinessDictCodeList.清算速度_T加1.getValue());
        cfetsRepoDealQuotedPrice002Service.单独处理页面质押券列表(cfetsRepoDealQuotedPrice001Dto);
        model.addAttribute("entity_bussRepoSecDto",cfetsRepoDealQuotedPrice001Dto.getBussRepoSec() == null ? null : JsonUtils.object2Json(cfetsRepoDealQuotedPrice001Dto.getBussRepoSec()));
        model.addAttribute("dialogueRecord", HtmlUtils.htmlUnescape(cfetsRepoDealQuotedPrice001Dto.getDialogueRecord()));
        return super.init(model);
    }

    /**
     * 根据报价代码查询报价信息
     *
     * @param quoteId 报价代码
     * @param operation 是否复制操作
     * @return 报价信息
     * @see
     * @since 1.0
     */
    private CfetsRepoDealQuotedPrice001Dto queryRepoInfoByQuoteId(String quoteId,String operation) {
        // 根据报价id查询数据
        CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto = cfetsRepoDealQuotedPrice002Service.queryEntrustInfoByQuoteId(
                quoteId);
        if(StringUtil.equals(cfetsRepoDealQuotedPrice001Dto.getPs(), DictCodeList.回购拆借方向_正回购.getValue())){
            Map<String,Object> map = new HashMap<>();
            map.put("quoteId",quoteId);
            //多组合质押券列表单独处理
            map.put("platDate",iPlatformDate.getSysDateStr());
            map.put("ps",cfetsRepoDealQuotedPrice001Dto.getPs());
            BussRepoSecDto bussRepoSecDto = new BussRepoSecDto();
            List<CfetsRepoDealQuotedPrice003Dto> cfetsRepoDealQuotedPrice003s = cfetsRepoDealQuotedPrice003Service.selectQuoteCrepPledgeForDzh(map);
            cfetsRepoDealQuotedPrice001Dto.setPledgeBondList(cfetsRepoDealQuotedPrice003s);
        }
        if (StringUtil.equals(operation, "CfetsRepoDealQuotedPrice001.copy")
                && StringUtil.isNotEmpty(quoteId)) {
            cfetsRepoDealQuotedPrice001Dto.setQuoteId(null);
        }
        return cfetsRepoDealQuotedPrice001Dto;
    }

    /**
     * 如果我方作为回复方，在切换组合代码时需要根据画面显示的质押券关联显示可质押和已质押
     *
     * @param cfetsRepoDealQuotedPrice001Dto 报价信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("handlePledgeList")
    public CfetsRepoDealQuotedPrice001Dto handlePledgeList(@RequestBody CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto){
        if (!StringUtil.isEmptyList(cfetsRepoDealQuotedPrice001Dto.getPledgeBondList())
                && StringUtil.isNotEmpty(cfetsRepoDealQuotedPrice001Dto.getPortfolioId())) {
            List<String> bondCodes = cfetsRepoDealQuotedPrice001Dto.getPledgeBondList().stream().map(
                    obj -> StringUtil.isNotEmpty(obj.getBondCode())?obj.getBondCode():obj.getFinprodId()).collect(Collectors.toList());
            // 查询债券相关信息
            List<CfetsRepoDealQuotedPrice003Dto> pledgeBondList = null;
            Map<String, Object> param = new HashMap<>();
            param.put("portfolioId", cfetsRepoDealQuotedPrice001Dto.getPortfolioId());
            param.put("mySide", cfetsRepoDealQuotedPrice001Dto.getMySide());
            param.put("quoteId", cfetsRepoDealQuotedPrice001Dto.getQuoteId());
            param.put("finprodMarketIds", bondCodes);
            if (StringUtil.equals(cfetsRepoDealQuotedPrice001Dto.getPs(),
                    BussinessDictCodeList.回购拆借方向_正回购.getValue())) {
                // 正回购
                try {
                    pledgeBondList = cfetsRepoDealQuotedPrice003Service.queryPledgeBondList(
                            param).getRoot();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            else if (StringUtil.equals(cfetsRepoDealQuotedPrice001Dto.getPs(),
                    BussinessDictCodeList.回购拆借方向_逆回购.getValue())) {
                // 逆回购
                pledgeBondList = cfetsRepoDealQuotedPrice004Service.queryPledgeBondList(
                        param).getRoot();
            }
            if (!StringUtil.isEmptyList(pledgeBondList)) {
                for (CfetsRepoDealQuotedPrice003Dto pledge : pledgeBondList) {
                    // pledge.setPledgeNumber(ArithmeticUtil.divide(pledge.getPledgeNumber(), new BigDecimal(10000), 2));// 今日已质押券面（万）
                    // pledge.setAvaPledgeNumber(ArithmeticUtil.divide(pledge.getAvaPledgeNumber(), new BigDecimal(10000), 2));// 剩余可质押券面（万）
                    cfetsRepoDealQuotedPrice001Dto.getPledgeBondList().forEach(obj -> {
                        if (obj.getBondCode().equals(pledge.getBondCode())) {
                            obj.setExerciseDate(pledge.getExerciseDate());
                            obj.setMdate(pledge.getMdate());
                            obj.setAvaPledgeNumber(pledge.getAvaPledgeNumber());
                            obj.setPledgeNumber(pledge.getPledgeNumber());
                            obj.setAvaPledgePrin(pledge.getAvaPledgePrin());
                            obj.setSecAcctName(pledge.getSecAcctName());
                            obj.setIsSus(pledge.getIsSus());
                            obj.setIsSecBond(pledge.getIsSecBond());
                        }
                    });
                }
            }
        }

        return cfetsRepoDealQuotedPrice001Dto;
    }


    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody CfetsRepoDealQuotedPrice001Dto cfetsRepoDealQuotedPrice001Dto, BindingResult br) throws WorkFlowException {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if (!StringUtil.isNotEmpty(cfetsRepoDealQuotedPrice001Dto.getQuoteId())) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            codeParameter.setSeqNo("IBU_003");
            String code = bussGetBussinessIdService != null
                    ? bussGetBussinessIdService.buildBussinessCode(codeParameter) : IDGenerator.generate();
            cfetsRepoDealQuotedPrice001Dto.setQuoteId(code);
        }
        ProcessParameter<CfetsRepoDealQuotedPrice001Dto> processParameter = new ProcessParameter<CfetsRepoDealQuotedPrice001Dto>();
        WorkFlowParameter<CfetsRepoDealQuotedPrice001Dto> params = cfetsRepoDealQuotedPrice002Service.getWorkFlowParameter(cfetsRepoDealQuotedPrice001Dto);
        params.setPageId("CfetsRepoDealQuotedPrice003");
        params.setElementId(cfetsRepoDealQuotedPrice001Dto.getElementId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_银行间上行.getValue());
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

}

