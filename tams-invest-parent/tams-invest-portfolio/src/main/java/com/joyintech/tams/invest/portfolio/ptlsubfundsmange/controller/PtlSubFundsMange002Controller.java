/*
 * 文件名：PtlSubFundsMange002Controller.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：zhangyf 修改时间：2019年8月19日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlsubfundsmange.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.portfolio.ptlsubfundsmange.dto.PtlSubFundsMange002Dto;
import com.joyintech.tams.invest.portfolio.ptlsubfundsmange.service.PtlSubFundsMange002Service;
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
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品交易资产分配-新建
 * 
 * @author zhangyf
 * @version 2019年8月19日
 * @see PtlSubFundsMange002Controller
 * @since 1.0
 */
@Controller
@PageId("PtlSubFundsMange002")
@RequestMapping("/invest/portfolio/ptlsubfundsmange/PtlSubFundsMange002")
public class PtlSubFundsMange002Controller extends BaseController {

    @Autowired
    private PtlSubFundsMange002Service ptlSubFundsMange002Service;

    /**
     * 业务编号生成类
     */
    @Autowired(required = false)
    public BaseGetBusinessId baseGetBusinessId;

    /**
     * 平台日期组件接口
     */
    @Autowired
    private IPlatformDate iPlatformDate;

    /**
     * 界面初始化 {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        // 平台日期
        model.addAttribute("finprodId", getParam("prodId"));
        String totleTradeId = getParam("totleTradeId");
        totleTradeId = StringUtil.equals(totleTradeId,"null")?"":totleTradeId;
        // 查询页面-有效状态
        String workStatus = getParam("workStatus");
        String workflowId = getParam("workflowId");
        String pageDataStatus = "";
        PtlSubFundsMange002Dto dto = new PtlSubFundsMange002Dto();
        dto.setTotleTradeId(totleTradeId);
        dto.setWorkflowId(workflowId);
        // 待办-流程中
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            dto = ptlSubFundsMange002Service.getBussinessEntityByWorkflowId(workflowId);
            if (null != dto) {
                totleTradeId = dto.getTotleTradeId();
                Map<String, Object> para = new HashMap<String, Object>();
                para.put("finprodId", dto.getFinprodId());
                FinProduct finProduct = ptlSubFundsMange002Service.queryProdName(para);
                dto.setFinprodName(finProduct == null ? "" : finProduct.getFinprodName());
                model.addAttribute("portfolioDetailTable", StringUtil.isEmptyList(dto.getPortfolioDetailTable()) ? null : JsonUtils.object2Json(dto.getPortfolioDetailTable()));
            }
        }
        // 新建 or 查询页面双击查看-待处理
        else if (StringUtil.isEmpty(totleTradeId)) {
            dto.setTradeDate(iPlatformDate != null ? DateUtil.platDateFromCache() : DateUtil.now());
            dto.setPortfolioId(getParam("portfolioId"));
            // dto.setSeqNoList(Arrays.asList(getParam("seqNoList").split(",")));
            dto.setSeqNo(getParam("seqNo"));
            dto.setCcy(getParam("ccy"));
            dto.setTradeAmt(getParam("tradeAmt") == null ? new BigDecimal(0) : new BigDecimal(getParam("tradeAmt").toString()));
            Map<String, Object> para = new HashMap<String, Object>();
            para.put("finprodId", getParam("prodId"));
            FinProduct finProduct = ptlSubFundsMange002Service.queryProdName(para);
            dto.setFinprodName(finProduct == null ? "" : finProduct.getFinprodName());
            dto.setFinprodId(getParam("prodId"));
            pageDataStatus = DictCodeListExt1.画面数据状态_新建.getValue();
        }
        // 查询页面双击查看-流程中
        else if (StringUtil.isNotEmpty(totleTradeId) && WorkFlowUtil.isNotFromToDoList() && "流程中".equals(workStatus)) {
            pageDataStatus = DictCodeListExt1.画面数据状态_流程中.getValue();
            dto.setPageDataStatus(pageDataStatus);
            dto = ptlSubFundsMange002Service.queryPtlSubFundsWithStatue(dto);
            Map<String, Object> para = new HashMap<String, Object>();
            para.put("finprodId", getParam("prodId"));
            FinProduct finProduct = ptlSubFundsMange002Service.queryProdName(para);
            dto.setFinprodName(finProduct == null ? "" : finProduct.getFinprodName());
            dto.setFinprodId(getParam("prodId"));
        }
        // 查询页面双击查看-有效
        else if (StringUtil.isNotEmpty(totleTradeId) && WorkFlowUtil.isNotFromToDoList() && "有效".equals(workStatus)) {
            pageDataStatus = DictCodeListExt1.画面数据状态_有效.getValue();
            dto.setPageDataStatus(pageDataStatus);
            dto = ptlSubFundsMange002Service.queryPtlSubFundsWithStatue(dto);
            Map<String, Object> para = new HashMap<String, Object>();
            para.put("finprodId", getParam("prodId"));
            FinProduct finProduct = ptlSubFundsMange002Service.queryProdName(para);
            dto.setFinprodName(finProduct == null ? "" : finProduct.getFinprodName());
            dto.setFinprodId(getParam("prodId"));
        }
        if (null != dto) {
            dto.setTradeDateStr(DateUtil.format(dto.getTradeDate()));

            // 隐藏域
            dto.setPageDataStatus(pageDataStatus);
            dto.setTotleTradeId(totleTradeId);
            dto.setWorkflowId(workflowId);
        }

        model.addAttribute("entity", dto);
        return super.init(model);
    }

    /**
     * 产品代码联想下拉框
     * 
     * @return 产品代码列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryProdIdInfo")
    public List<SelectOptionDto> queryProdIdInfo() {
        return ptlSubFundsMange002Service.queryProdIdInfo(RequestUtil.getPageParam());

    }

    /**
     * 通过产品代码，获取产品名称
     * 
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("queryProdName")
    public FinProduct queryProdName()
        throws Exception {
        return ptlSubFundsMange002Service.queryProdName(RequestUtil.getPageParam());
    }

    /**
     * 获取子组合资金分配明细列表
     * 
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("selectPortfolioDetail")
    public List<PtlSubFundsMange002Dto> selectPortfolioDetail() {
        String workflowId = RequestUtil.getParam("workflowId");
        if (StringUtil.isNotEmpty(workflowId)&&!StringUtil.equals("null", workflowId)) {
            PtlSubFundsMange002Dto dto = ptlSubFundsMange002Service.getBussinessEntityByWorkflowId(workflowId);
            if (dto != null) {
                return dto.getPortfolioDetailTable();
            }
        }
        return ptlSubFundsMange002Service.selectPortfolioDetail(RequestUtil.getPageParam());
    }

    /**
     * 分配按钮点击时的check
     */
    @ResponseBody
    @RequestMapping("checkadd")
    public AjaxResult checkadd(@RequestBody @Validated({ValidateGroupDelete.class}) PtlSubFundsMange002Dto dto,
                               BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存
     * 
     * @param dto
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody PtlSubFundsMange002Dto dto)
        throws Exception {
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            dto.setTotleTradeId(genBussinessCode());
            // 明细信息，设置交易编号
            dto.getPortfolioDetailTable().forEach(sub -> sub.setTradeId(genBussinessCode()));
        }
        ProcessParameter<PtlSubFundsMange002Dto> processParameter = new ProcessParameter<PtlSubFundsMange002Dto>();
        WorkFlowParameter<PtlSubFundsMange002Dto> params = new WorkFlowParameter<PtlSubFundsMange002Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getTotleTradeId());
        params.setBusinessType(DictCodeList.共通业务类型_产品交易资金分配.getValue());
        // 业务说明：“产品代码（” + 画面.产品代码+“）” +“产品名称（” + 画面.产品名称+“）” +“调配日期（”+画面.调配日期 +“）”
        params.setComment(
            entity -> this.getMessage("PtlSubFundsMange002.finprodId") + "（" + entity.getFinprodId() + "）" + "-"
                      + this.getMessage("PtlSubFundsMange002.finprodName") + "（" + entity.getFinprodName() + "）" + "-"
                      + this.getMessage("PtlSubFundsMange002.tradeDate") + "（" + DateUtil.format(entity.getTradeDate()) + "）");
        processParameter.setWorkFlowParameter(params);
        String msg = ptlSubFundsMange002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    /**
     * 生成业务编号
     * 
     * @return
     */
    private String genBussinessCode() {
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_产品交易资金分配);// 数据字典.业务类型.产品交易资金分配
        return baseGetBusinessId != null ? baseGetBusinessId.buildBussinessCode(parameter) : IDGenerator.generate();
    }
}
