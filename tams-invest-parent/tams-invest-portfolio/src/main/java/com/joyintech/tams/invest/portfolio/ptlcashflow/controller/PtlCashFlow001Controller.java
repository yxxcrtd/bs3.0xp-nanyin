/*
 * 文件名：PtlCashFlow001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlcashflow.controller;

import java.util.List;

import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.settle.BussSettleOrderCommService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.portfolio.ptlcashflow.dto.PtlCashFlowDto;
import com.joyintech.tams.invest.portfolio.ptlcashflow.service.PtlCashFlowService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 自定义现金流
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年10月25日
 * @see PtlCashFlow001Controller
 * @since 1.0
 */
@Controller
@PageId("PtlCashFlow001")
@RequestMapping("/invest/portfolio/ptlcashflow/PtlCashFlow001")
public class PtlCashFlow001Controller extends BaseController{
    /**
     * ptlCashFlowService
     */
    @Autowired
    private PtlCashFlowService ptlCashFlowService;
    /**
     * 清算指令共通Service
     */
    @Autowired(required = false)
    private BussSettleOrderCommService bussSettleOrderCommService;
    /**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.交易方向);
        this.setSelectOptionToModel(model, DictCode.平安资产类型);
        this.setSelectOptionToModel(model, DictCode.自定义现金流类型);

        this.setSelectOptionToModel(model, DictCode.交易流程状态);

        return super.init(model);
    }
    /**
     *
     * 获得业务编号
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getBusiNo")
    public List<SuggestDto> getBusiNo(){
        return ptlCashFlowService.getBusiNo(getParam("param"));
    }
    /**
     *
     * 获得组合代码
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getPortfolioId")
    public Page<SelectOptionDto> getPortfolioId(){
        return ptlCashFlowService.getPortfolioId(getParam("search"));
    }
    /**
     *
     * 获得资产代码
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getFinprodMarketId")
    public List<SuggestDto> getFinprodMarketId(){
        return ptlCashFlowService.getFinprodMarketId(getParam("param"));
    }
    /**
     * 分页查询
     * @return Page<PtlActivityPeriod001Dto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/findPage")
    public Page<PtlCashFlowDto> findPage()
            throws Exception {
        return ptlCashFlowService.selectForPage(RequestUtil.getParameters(getRequest()));
    }
    /**
     * 撤销按钮点击时的check
     * @param dto 验证的实体类
     * @param result 验证的结果
     * @return 错误信息
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupDelete.class}) PtlCashFlowDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("revoke")
    public AjaxResult revoke(@RequestBody @Validated({
            ValidateGroupDelete.class}) PtlCashFlowDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<PtlCashFlowDto> processParameter = new ProcessParameter<PtlCashFlowDto>();
        WorkFlowParameter<PtlCashFlowDto> params = new WorkFlowParameter<PtlCashFlowDto>();
        String branch = CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE);
        dto.setBranch(Long.parseLong(branch));
        if(null != bussSettleOrderCommService){
            List<BussTrdTransferOrderBase> list = bussSettleOrderCommService.selectOrdersByBizCode(dto.getBusiNo());
            dto.setBussTrdTransferOrder(list);
        }
        dto.setBusiNo(dto.getBusiNo()+"R");
        params.setPageId("PtlCashFlow002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getBusiNo());
        params.setBusinessType(DictCodeList.共通业务类型_自定义现金流.getValue());
        // "自定义现金流交易编号：" + 上述取得的交易编号 + "业务发生日期：" + 画面.业务发生日期 + "组合：" + 画面.组合代码对应的组合名称
        params.setComment(
                entity -> this.getMessage("PtlCashFlow001.busiNo") + "("
                        + entity.getBusiNo() + ")" + "-"
                        + this.getMessage("PtlCashFlow001.settleDate") + "("
                        + DateUtil.format(entity.getSettleDate()) + ")" + "-"
                        + this.getMessage("PtlCashFlow001.portfolioName") + "("
                        + entity.getPortfolioName() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = ptlCashFlowService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return ptlCashFlowService.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));

    }
}
