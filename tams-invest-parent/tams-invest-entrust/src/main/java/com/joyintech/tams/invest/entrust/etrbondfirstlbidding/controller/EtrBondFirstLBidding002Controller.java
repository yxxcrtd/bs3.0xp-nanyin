package com.joyintech.tams.invest.entrust.etrbondfirstlbidding.controller;

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
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.common.base.entity.EtrEntrustBidPrice;
import com.joyintech.tams.invest.entrust.etrbondfirstlbidding.dto.EtrBondFirstLBiddingDto;
import com.joyintech.tams.invest.entrust.etrbondfirstlbidding.service.EtrBondFirstBiddingService;
import com.joyintech.workflow.engine.constant.OperationType;
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
import java.util.List;
import java.util.Map;

@Controller
@PageId("EtrBondFirstLBidding002")
@RequestMapping("/invest/entrust/etrbondfirstlbidding/EtrBondFirstLBidding002")
public class EtrBondFirstLBidding002Controller extends BaseController {
    
    /**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;
    /**
     * 债券一级委托service
     */
    @Autowired
    private EtrBondFirstBiddingService firstService;
    
    
    
    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, BussinessDictCode.WIND一级分类_含债券一级分类等);
        //新增资产二、三、四级分类 王贵奇 2019/7/27 开始
        this.setSelectOptionToModel(model, DictCode.行内资产二级分类 );
        this.setSelectOptionToModel(model, DictCode.行内资产三级分类 );
        this.setSelectOptionToModel(model, DictCode.行内资产四级分类 );
        //新增资产二、三、四级分类 王贵奇 2019/7/27 结束
        this.setSelectOptionToModel(model, BussinessDictCode.缴款方式);
        this.setSelectOptionToModel(model, BussinessDictCode.币种);
        this.setSelectOptionToModel(model, BussinessDictCode.交易场所);
        this.setSelectOptionToModel(model, BussinessDictCode.交易市场);
        this.setSelectOptionToModel(model, BussinessDictCode.交易方向);
        this.setSelectOptionToModel(model, BussinessDictCode.清算速度);
        this.setSelectOptionToModel(model, BussinessDictCode.投资目的);
        this.setSelectOptionToModel(model, BussinessDictCode.交易平台);
        Date sysDate = DateUtil.platDateFromCache();
        model.addAttribute("sysDate", DateUtil.format(sysDate));
        String entrustId = getParam("entrustId");
        String finprodId = getParam("finprodId");
        String portfolioId = getParam("portfolioId");
        // by zhaodongdong 20191116 start 
        String mId = getParam("mId");
        String tradeMarket = getParam("tradeMarket");
        // by zhaodongdong 20191116 end
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        model.addAttribute("TodoList", !WorkFlowUtil.isNotFromToDoList());
        //流程代办
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {

            EtrBondFirstLBiddingDto dto = firstService.getBussinessEntityByWorkflowId(
                workflowId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("workflowId", workflowId);
            model.addAttribute("bidPriceList", StringUtil.isEmptyList(dto.getBidPriceList()) ? null : JsonUtils.object2Json(dto.getBidPriceList()));
            model.addAttribute("person_entity", JsonUtils.object2Json(dto.getEtrInvestPersonInfo()));
            model.addAttribute("businessId",workflowId);
        
        }else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(entrustId)) {
            //编辑页面
            EtrBondFirstLBiddingDto dto = firstService.selectByKey(entrustId,finprodId,portfolioId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("tradeId", dto.getEntrustId());
            model.addAttribute("bidPriceList", StringUtil.isEmptyList(dto.getBidPriceList()) ? null : JsonUtils.object2Json(dto.getBidPriceList()));
            model.addAttribute("person_entity", JsonUtils.object2Json(dto.getEtrInvestPersonInfo()));
            model.addAttribute("businessId",entrustId);
        }else{
            //新建页面初始化
            EtrBondFirstLBiddingDto entity = new EtrBondFirstLBiddingDto();
            entity.setVdate(sysDate);
            entity.setMdate(sysDate);
            // 从一级新债日历进入一级债券委托界面带出信息 by 赵东东  20191116 start
            entity.setmId(mId);
            if(StringUtil.isNotEmpty(mId)) {
            entity.setFinprodMarketId(finprodId);
            entity.setTradeMarket(tradeMarket);
            }
            // 从一级新债日历进入一级债券委托界面带出信息 by 赵东东  20191116 end
            entity.setEntrustStatus(BussinessDictCodeList.委托状态_新建.getValue());
            model.addAttribute(GlobalConstant.ENTITY, entity);
        }
        model.addAttribute("MY_SUP_TIME_TYPE_02", BussinessDictCodeList.监督时分_委托监控.getValue());
        model.addAttribute("fintype", BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue());
        model.addAttribute("etrPs", BussinessDictCodeList.交易方向_买入.getValue());
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
        return firstService.getentrustIdAdd(RequestUtil.getPageParam());
    }

    /**
     * 获取新建界面的资产代码联想下拉框框
     * 
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getfinprodIdForAdd")
    public Page<SelectOptionDto> getfinprodIdForAdd() 
        throws Exception {
        return firstService.getfinprodIdForAdd(RequestUtil.getPageParam());
    }
    /**
     * 获取新建界面的主承销商下拉框
     *
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getMainConsignee")
    public Page<SelectOptionDto> getMainConsignee(){
        return firstService.getMainConsignee(RequestUtil.getPageParam());
    }

    /**
     * 
     * 组合可用现金查询
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getProAvailableCash")
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param){
        return firstService.getProAvailableCash(param);
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
        ValidateGroupEdit.class}) EtrBondFirstLBiddingDto dto, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
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
    public AjaxResult save(@RequestBody EtrBondFirstLBiddingDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(!StringUtil.isNotEmpty(dto.getEntrustId())){
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessType(DictCodeList.共同业务类型_债券一级投标委托);
            String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param):IDGenerator.generate();
            dto.setEntrustId(code);
        }
        ProcessParameter<EtrBondFirstLBiddingDto> processParameter = new ProcessParameter<EtrBondFirstLBiddingDto>();
        WorkFlowParameter<EtrBondFirstLBiddingDto> params = new WorkFlowParameter<EtrBondFirstLBiddingDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getEntrustId());
        params.setBusinessType(BussinessDictCodeList.共同业务类型_债券一级投标委托.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> this.getMessage("EtrBondFirstLBidding001") + "(" + entity.getPortfolioId() + ")"
                      + "-" +  this.getMessage("EtrInvestAllInfo001.vdate") + ":" + DateUtil.format(entity.getVdate())
                      + ")-(" +  this.getMessage("EtrInvestAllInfo001.entrustDate") + ":" + DateUtil.format(entity.getEntrustDate())
                      + ")-(" + this.getMessage("EtrInvestAllInfo001.mdate") + ":"
                      + DateUtil.format(entity.getMdate())+ ")-"
                      + this.getMessage("EtrBondFirstLBidding002.finprodId") + ":"
                      + entity.getFinprodMarketId()
                    /*  + ")-(" + this.getMessage("EtrBondFirstLBidding002.bidMarkPosition") + ":"
                      + DateUtil.format(entity.getMdate())+ ")"*/
                      );
        processParameter.setWorkFlowParameter(params);
        String msg = firstService.start(processParameter, dto);
        return new AjaxResult(msg);
//        return new AjaxResult(getMessage("ETR_X_016"));
    }
}
