/*
 * 文件名：FinFutures002Controller.java
 * 版权：Copyright JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：广州银行poc
 * 修改人：余飞
 * 修改时间：2020年11月09日
 * 修改内容：新建
 * 系统名称：广州银行poc
 */

package com.joyintech.tams.invest.asset.futures.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.asset.futures.dto.FinFuturesDto;
import com.joyintech.tams.invest.asset.futures.service.FinFuturesService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 期货标的信息新建 〈功能详细描述〉
 *
 * @author yufei
 * @version 1.0 2020年11月09日
 * @see FinFutures002Controller
 * @since 1.0
 */
@PageId("FinFutures002")
@Controller
@RequestMapping("/invest/asset/futures/FinFutures002")
public class FinFutures002Controller extends BaseController {

    @Autowired
    private FinFuturesService finFuturesService;

    /**
     * 各种共通组件
     */
    @Autowired(required = false)
    private CommonBusinessService commonBusinessService;

    /**
     * 新建页面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.期货合约代码前缀, DictCode.币种, DictCode.交易场所,
            DictCode.交割方式, DictCode.期货类型,DictCode.合约标的);
        String finprodId = getParam("finprodId");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            FinFuturesDto dto = finFuturesService.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("businessId", workflowId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(finprodId)) {
            //获得页面展示元素
            FinFuturesDto dto = finFuturesService.selectByFinprodId(finprodId);
            if (dto != null && null != commonBusinessService) {
                if (commonBusinessService.isTransInAsset(dto.getFinprodId()) > 0) {
                    model.addAttribute("isTransInAsset", "Y");
                }
            }
            model.addAttribute("businessId", finprodId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        else if (WorkFlowUtil.isNotFromToDoList() && !StringUtil.isNotEmpty(finprodId)) {
            FinFuturesDto dto = new FinFuturesDto();
            dto.setCcy(DictCodeList.币种_人民币_CNY.getValue());
            dto.setFinprodType2(DictCodeListExt3.金融产品类型_投管用_期货.getValue());
            //dto.setCalendarId(DictCodeList.交易日历_上海交易所日历.getValue());
            dto.setCalendarId("04");
            model.addAttribute("entity", dto);
        }
        model.addAttribute("TRADE_MARKET_12",DictCodeListExt3.交易场所_中国金融期货交易所.getValue());
        return super.init(model);
    }

    /**
     * 获取下一个工作日
     *
     * @param params 画面参数
     * @return 下一个工作日
     * @see
     * @since 1.0
     */
    @RequestMapping("getNextWorkDay")
    @ResponseBody
    public String getNextWorkDay(
        @RequestParam
            Map<String, Object> params) {
        return finFuturesService.getNextWorkDay(params);
    }

    /**
     * 保存按钮点击时的check
     *
     * @param dto    dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(
        @RequestBody
        @Validated({ValidateGroupEdit.class})
            FinFuturesDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存
     *
     * @param dto dto
     * @param br  br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(
        @RequestBody
        @Validated({ValidateGroupEdit.class})
            FinFuturesDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FinFuturesDto> processParameter = new ProcessParameter<FinFuturesDto>();
        WorkFlowParameter<FinFuturesDto> params = new WorkFlowParameter<FinFuturesDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFinprodMarketId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_期货类资产.getValue());
        //修改时为画面所选记录的金融产品代码。新建时调用各种业务共通组件.获取金融产品代码
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            if (commonBusinessService != null) {
                //设置金融产品代码
                dto.setFinprodId(commonBusinessService.getFinProdId(dto.getFinprodMarketId(),
                    dto.getFinprodType2(), null));
            }
        }
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> SpringContextUtil.getTextValue("FinFutures001.finprodMarketId") + "("
                      + entity.getFinprodMarketId() + ")" + "-" + SpringContextUtil.getTextValue(
                "FinFutures001.futuresContractId") + "(" + entity.getFuturesContractIdStr() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = finFuturesService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
