package com.joyintech.tams.invest.entrust.etrprojectnet.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.trade.dto.EtrSecPositionDto;
import com.joyintech.tams.invest.entrust.etrprojectnet.dto.EtrProjectNetDto;
import com.joyintech.tams.invest.entrust.etrprojectnet.service.EtrProjectNetService;
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
import java.util.Date;
import java.util.Map;

/**
 *  权益类资产
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月8日
 * @see EtrProjectNet002Controller
 * @since 1.0
 */
@Controller
@PageId("EtrProjectNet002")
@RequestMapping("/invest/entrust/etrprojectnet/EtrProjectNet002")
public class EtrProjectNet002Controller extends BaseController {
    /**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;
    /**
     * 计划类净值委托service
     */
    @Autowired
    private EtrProjectNetService etrProjectNetService;

    /**
     * 初始化新建界面
     */
    @Override
    public String init(Model model) {

        this.setSelectOptionToModel(model, DictCode.计划类资产一级分类);
        this.setSelectOptionToModel(model, DictCode.计划类资产二级分类);
        /* add by zhangpuyu 2019-7-31 start*/
        this.setSelectOptionToModel(model, DictCode.行内资产二级分类,DictCode.行内资产三级分类,DictCode.行内资产四级分类);
        /* add by zhangpuyu 2019-7-31 end*/
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.交易方向);
        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("sysDate",DateUtil.format(sysDate));

        model.addAttribute("T04", DictCodeList.交易方向_申购.getValue());
        model.addAttribute("T06", DictCodeList.交易方向_赎回.getValue());
        model.addAttribute("T02", DictCodeList.交易方向_卖出.getValue());
        model.addAttribute("T01", DictCodeList.交易方向_买入.getValue());
        String entrustId = getParam("entrustId");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            EtrProjectNetDto dto = etrProjectNetService.getBussinessEntityByWorkflowId(
                    workflowId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("workflowId", workflowId);
            model.addAttribute("person_entity", JsonUtils.object2Json(dto.getEtrInvestPersonInfo()));
            model.addAttribute("businessId",dto.getWorkflowId());
            model.addAttribute("single", "1");
        }
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(entrustId)) {
            EtrProjectNetDto dto = etrProjectNetService.selectByKey(entrustId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("tradeId", dto.getEntrustId());
            model.addAttribute("person_entity", JsonUtils.object2Json(dto.getEtrInvestPersonInfo()));
            model.addAttribute("businessId", StringUtil.isNotEmpty(dto.getWorkflowId()) ? dto.getWorkflowId() : dto.getEntrustId());
            model.addAttribute("single", "1");
        }
        //新建初始化
        else if (WorkFlowUtil.isNotFromToDoList() && !StringUtil.isNotEmpty(entrustId)) {
            EtrProjectNetDto entity = new EtrProjectNetDto();
            model.addAttribute("single", "1");
            entity.setVdate(sysDate);
            entity.setMdate(sysDate);
            entity.setProfitType(DictCodeList.收益类型_净值型.getValue());
            entity.setCcy(DictCodeList.币种_人民币_CNY.getValue());
            entity.setEntrustStatus(DictCodeList.委托状态_新建.getValue());
            model.addAttribute(GlobalConstant.ENTITY, entity);
        }
        model.addAttribute("MY_SUP_TIME_TYPE_02", DictCodeListExt1.监督时分_委托监控.getValue());
        model.addAttribute("fintype", DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return super.init(model);
    }
    /**
     * 获取组合代码联想文本框信息 <功能详细描述>
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getentrustIdAdd")
    public Page<SelectOptionDto> getentrustIdAdd()
            throws Exception {
        Page<SelectOptionDto> temp = etrProjectNetService.getentrustIdAdd(RequestUtil.getPageParam());
        return temp;
    }
    /**
     *
     * 组合可用现金查询
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getProAvailableCash")
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param){
        return etrProjectNetService.getProAvailableCash(param);
    }
    /**
     *
     * 组合可用持仓查询
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getAssetAvailablePosition")
    public BigDecimal getAssetAvailablePosition(@RequestBody EtrSecPositionDto dto){
//        if (StringUtil.isNotEmpty(dto.getSecAcctId())) {
//            dto.setChannel(true);
//        }
        return etrProjectNetService.getAssetAvailablePosition(dto);
    }
    /**
     * 获取新建界面的资产代码联想下拉框框
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getfinprodIdForAdd")
    public Page<SelectOptionDto> getfinprodIdForAdd()
            throws Exception {
        Page<SelectOptionDto> temp = etrProjectNetService.getfinprodIdForAdd(RequestUtil.getPageParam());
        return temp;
    }

    /**
     *
     * 通过资产代码带出资产类型
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/searchType")
    public Map<String, String> searchType(String finprodId){
        return etrProjectNetService.searchType(finprodId);
    }
    /**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) EtrProjectNetDto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }

        // 可用现金软校验
        if (dto.getProAvailableCash() != null && dto.getEntrustAmt() != null) {
            if (dto.getEntrustAmt().compareTo(dto.getProAvailableCash()) == 1) {
                AjaxResult ar = new AjaxResult();
                ar.setData(this.getMessage("TRD_C_0002"));
                return ar;
            }
        }

        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 保存/提交
     *
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody EtrProjectNetDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(!StringUtil.isNotEmpty(dto.getEntrustId())){
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessType(DictCodeListExt3.共通业务类型_权益类资产委托);
            String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param):IDGenerator.generate();
            dto.setEntrustId(code);
        }
//        if (StringUtil.equals(BussinessConstant.SUBMIT, dto.getOperateType())) {
//            AjaxResult ar = EntrustRdsCommonAop.doPreOccupy(dto,
//                    BussinessDictCodeListRds.接口系统ID_预占接口.getValue(),
//                    DictCodeListExt3.平安资产类型_权益类资产.getValue());
//            if (StringUtil.isNotEmpty(ar)) {
//                return ar;
//            }
//        }
        ProcessParameter<EtrProjectNetDto> processParameter = new ProcessParameter<EtrProjectNetDto>();
        WorkFlowParameter<EtrProjectNetDto> params = new WorkFlowParameter<EtrProjectNetDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getEntrustId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_权益类资产委托.getValue());
        // 设置流程业务说明, entity即传入的dto
        if(dto.getEntrustAmt() != null){
            params.setComment(
                    entity -> this.getMessage("EtrProjectNet001") + "(" + entity.getPortfolioId() + ")"
                            + "-" + this.getMessage("EtrPlanAssetN001.ps")+"("+SysDataUtil.getNameFromDataDict(DictCode.交易方向, entity.getPs())+")" + "-" + "("
                            + this.getMessage("EtrPlanAssetN001.vdate") + ":" + DateUtil.format(entity.getVdate())
                            + ")-(" + this.getMessage("EtrPlanAssetN001.mdate") + ":"
                            + DateUtil.format(entity.getMdate()) + ")-"
                            + this.getMessage("EtrPlanAssetN001.entrustAmt") + ":"
                            + entity.getEntrustAmt());
        }else if(dto.getEntrustNum() != null){
            params.setComment(
                    entity -> this.getMessage("EtrProjectNet001") + "(" + entity.getPortfolioId() + ")"
                            + "-" + this.getMessage("EtrPlanAssetN001.ps")+"("+SysDataUtil.getNameFromDataDict(DictCode.交易方向, entity.getPs())+")" + "-" + "("
                            + this.getMessage("EtrPlanAssetN001.vdate") + ":" + DateUtil.format(entity.getVdate())
                            + ")-(" + this.getMessage("EtrPlanAssetN001.mdate") + ":"
                            + DateUtil.format(entity.getMdate()) + ")-"
                            + this.getMessage("EtrPlanAssetN002.entrustNum") + ":"
                            + entity.getEntrustNum());
        }
        processParameter.setWorkFlowParameter(params);
        String msg = etrProjectNetService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
