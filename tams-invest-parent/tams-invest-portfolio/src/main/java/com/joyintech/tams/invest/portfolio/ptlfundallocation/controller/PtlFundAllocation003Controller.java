package com.joyintech.tams.invest.portfolio.ptlfundallocation.controller;

import com.joyintech.tams.common.base.dao.StlPayOrdersMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.portfolio.ptlfundallocation.dto.PtlFundAllocation001Dto;
import com.joyintech.tams.invest.portfolio.ptlfundallocation.dto.PtlFundAllocation002Dto;
import com.joyintech.tams.invest.portfolio.ptlfundallocation.dto.PtlFundAllocationDto;
import com.joyintech.tams.invest.portfolio.ptlfundallocation.service.PtlFundAllocation003Service;
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

/**
 * 资金划拨新建
 */
@Controller
@PageId("PtlFundAllocation003")
@RequestMapping("/invest/portfolio/ptlfundallocation/PtlFundAllocation003")
public class PtlFundAllocation003Controller extends BaseController {
    /**
     * 资金划拨Service
     */
    @Autowired
    private PtlFundAllocation003Service ptlFundAllocation003Service;
    /**
     * 平台日期实体类
     */
    @Autowired
    private IPlatformDate iPlatformDate;
    /**
     * 业务编号生成类
     */
    @Autowired(required =false)
    public BaseGetBusinessId baseGetBussinessId;
    /**
     * 基础Mapper代理Service
     */
    @Autowired
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;

//    @Autowired
//    private PtlFundAllocationMapper ptlFundAllocationMapper;

    /**
     * 重写init方法
     * @param model 模型
     * @return 返回pageId
     */
    @Override
    public String init(Model model) {
        super.setSelectOptionToModel(model, BussinessDictCode.指令类型,BussinessDictCode.币种);
        model.addAttribute("Fin_Type", BussinessDictCodeList.共同业务类型_资金划拨管理.getValue());
        model.addAttribute("YES_OR_NO_N", BussinessDictCodeList.是否_否.getValue());
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String orderId = getParam("orderId");
        String portfolioId = getParam("portfolioId");
        //add by 20190626
        if (!WorkFlowUtil.isNotFromToDoList()&&StringUtil.isNotEmpty(workflowId)) {
            PtlFundAllocation002Dto dto = ptlFundAllocation003Service.getBussinessEntityByWorkflowId(workflowId);
            if(dto.getBussTrdTransferOrder() != null){
                model.addAttribute("stlPayOrdersList", JsonUtils.object2Json(dto.getBussTrdTransferOrder()));
            }else{
                model.addAttribute("tradeId", dto.getTradeId());
                dto.setBusiNo(dto.getOrderId());
            }
            if(dto.getPtlFundAllocation001Dtos() != null){
                model.addAttribute("ptlFundAllocation001Dtos", dto.getPtlFundAllocation001Dtos());
            }
            model.addAttribute("entity", dto);
        }else if(StringUtil.isNotEmpty(orderId)){
            PtlFundAllocationDto dto = new PtlFundAllocationDto();
            if(bussCommonMapperDelegateService != null){
                StlPayOrders stlPayOrders = bussCommonMapperDelegateService.selectByPrimaryKey(StlPayOrdersMapper.class, orderId, StlPayOrders.class);
                BeanUtil.copyPropertiesIgnoreNull(dto, stlPayOrders);
                if(stlPayOrders != null){
                    TrdProductDeal trdProductDeal = bussCommonMapperDelegateService.selectByPrimaryKey(TrdProductDealMapper.class, stlPayOrders.getTradeId(), TrdProductDeal.class);
//                    dto.setPayType(trdProductDeal.getPs());
                    if (StringUtil.equals(trdProductDeal.getPs(), BussinessDictCodeList.交易方向_银证转账.getValue())) {
                        dto.setPayType(BussinessDictCodeList.指令类型_银证转账_银转证.getValue());
                    } else {
                        dto.setPayType(BussinessDictCodeList.指令类型_银证转账_证转银.getValue());
                    }

                    dto.setRemark(trdProductDeal.getRemark());
                }
                dto.setBusiNo(orderId);
                dto.setAmount(new BigDecimal("0"));
                dto.setPortfolioId(portfolioId);
                model.addAttribute("tradeId", dto.getTradeId());
                model.addAttribute("entity", dto);
            }
        }
        /* 导入后数据显示修改 add by liuwei 20210329 start */
        else if (StringUtil.isNotEmpty(getParam("isImportFlag")) && StringUtil.equals(getParam("isImportFlag"),"01")
                && StringUtil.isNotEmpty(RequestUtil.getPageParam().get("pageData"))){
            PtlFundAllocation002Dto dto = JsonUtils.json2Object((String) RequestUtil.getPageParam().get("pageData"),PtlFundAllocation002Dto.class);
            dto.setIsImportFlag("01");
            model.addAttribute("entity", dto);
            if(dto.getPtlFundAllocation001Dtos() != null){
                model.addAttribute("ptlFundAllocation001Dtos", dto.getPtlFundAllocation001Dtos());
            }
        }
        /* 导入后数据显示修改 add by liuwei 20210329 end */
        return super.init(model);
    }
    /**
     * 获取平台日期
     * @return 平台日期字符串
     * @see
     * @since 1.0
     */
    @RequestMapping("/getInitDate")
    @ResponseBody
    public String getInitDate(){
        String dateStr=  iPlatformDate.getSysDateStr() != null ? iPlatformDate.getSysDateStr() : DateUtil.format(new Date());
        return dateStr;
    }

    /**
     * 查询组合
     * @return 组合集合
     * @see
     * @since 1.0
     */
    @RequestMapping("/getPortfolioId")
    @ResponseBody
    public List<SelectOptionDto> getPortfolioIdBysecId(){
        return ptlFundAllocation003Service.getPortfolioIdBysecId(RequestUtil.getPageParam());
    }

    /**
     * 查询收款账号
     * @return 收款账号集合
     * @see
     * @since 1.0
     */
    @RequestMapping("/getAcctNo")
    @ResponseBody
    public List<SelectOptionDto> getCounterAcctNo(){
        return ptlFundAllocation003Service.getAcctNo(RequestUtil.getPageParam());
    }

    /**
     * 查询付款账号
     * @return 付款账号集合
     * @see
     * @since 1.0
     */
    @RequestMapping("/getMyAcctNo")
    @ResponseBody
    public List<SelectOptionDto> getMyAcctNo(){
        return ptlFundAllocation003Service.getMyAcctNo(RequestUtil.getPageParam());
    }

    /**
     * 获取账户余额
     * @return 账户余额
     * @see
     * @since 1.0
     */
    @RequestMapping("/getAmount")
    @ResponseBody
    public BigDecimal getAmount(){
        return ptlFundAllocation003Service.getAmount(RequestUtil.getPageParam());
    }


    /**
     * 画面点击保存按钮时check
     *
     * @param dto 实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) PtlFundAllocation002Dto dto, BindingResult result) {
        if(result.getErrorCount()<=0){
            if(!StringUtil.isEmptyList(dto.getPtlFundAllocation001Dtos())){
                for (int i = 0; i < dto.getPtlFundAllocation001Dtos().size(); i++ ) {
                    if (dto.getPtlFundAllocation001Dtos().get(i).getAmount().compareTo(dto.getPtlFundAllocation001Dtos().get(i).getSettleAmt())<0){
                        AjaxResult ar = new AjaxResult();
                        ar.setMessage("明细(第"+(i+1)+"行)划款金额大于账户剩余金额！是否继续？", MessageType.警告消息);
                        return ar;
                    }
                }
            }
        }
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
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) PtlFundAllocation002Dto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(StringUtil.isEmpty(dto.getBusiNo())){
            BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
            bussinessCodeParameter.setCommonBusinessType1(BussinessDictCodeList.共同业务类型_资金划拨管理);
            dto.setTradeId(baseGetBussinessId !=null ? baseGetBussinessId.buildBussinessCode(bussinessCodeParameter):IDGenerator.generate());
//            for (BussTrdTransferOrderDto bussTrdTransferOrderDto : dto.getBussTrdTransferOrder()){
//
//            }
//            if(dto.getBussTrdTransferOrder() != null && dto.getBussTrdTransferOrder().get(0) != null){
//                dto.setBusiNo(dto.getBussTrdTransferOrder().get(0).getOrderId());
//            }
        }
//        dto.setOrderId(dto.getBussTrdTransferOrder().get(0).getOrderId());
//        if(bussCommonMapperDelegateService != null){
//            PtlPortfolio ptlPortfolio = bussCommonMapperDelegateService.selectByPrimaryKey(PtlPortfolioMapper.class, dto.getPortfolioId(), PtlPortfolio.class);
//            if(ptlPortfolio != null){
//                dto.setPortfolioName(ptlPortfolio.getPortfolioName());
//            }
//        }
        ProcessParameter<PtlFundAllocation002Dto> processParameter = new ProcessParameter<PtlFundAllocation002Dto>();
        WorkFlowParameter<PtlFundAllocation002Dto> params = new WorkFlowParameter<PtlFundAllocation002Dto>();
        params.setPageId("PtlFundAllocation003");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getTradeId());
        params.setBusinessType(BussinessDictCodeList.共同业务类型_资金划拨管理.getValue());
        // 设置流程业务说明, 组合名称 + "(" + 组合名称 + ")" + "-"  + "组合代码" + "("  + "组合代码"+")"
        params.setComment(
                entity -> "资金划拨多组合管理,划拨金额:"+dto.getSettleAmt()+",交易日期:("+DateUtil.format(dto.getSettleDate())+")");
        processParameter.setWorkFlowParameter(params);
        String msg = ptlFundAllocation003Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    /**
     * 获取组合资金账户和账户余额
     * @return
     * @see
     */
    @RequestMapping("/getPosition")
    @ResponseBody
    public PtlFundAllocation001Dto getPosition(){
        return ptlFundAllocation003Service.getPosition(RequestUtil.getPageParam());
    }

    /**
     * 根据账户获取明细
     * @return
     * @see
     */
    @RequestMapping("getPtlFundAllocation001Dtos")
    @ResponseBody
    public List<PtlFundAllocation001Dto> getPtlFundAllocation001Dtos(@RequestBody Map<String,Object> paraMap){
        return ptlFundAllocation003Service.getPtlFundAllocation001Dtos(paraMap);
    }


}
