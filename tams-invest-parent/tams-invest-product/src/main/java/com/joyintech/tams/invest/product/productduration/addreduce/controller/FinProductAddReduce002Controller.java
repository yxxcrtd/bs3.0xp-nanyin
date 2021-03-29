package com.joyintech.tams.invest.product.productduration.addreduce.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import com.joyintech.tams.constant.DictCodeListExt1;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.component.business.trade.impl.BussTrdAvailableCashQueryImplService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.product.addreduce.ReduceInfoDto;
import com.joyintech.tams.invest.product.productduration.addreduce.dto.FinProductAddReduce002Dto;
import com.joyintech.tams.invest.product.productduration.addreduce.dto.ProductFeeInfoDto;
import com.joyintech.tams.invest.product.productduration.addreduce.service.FinProductAddReduce002Service;
import com.joyintech.tams.invest.product.productduration.addreduce.service.FinProductAddReduceWorkflowService;
import com.joyintech.tams.invest.product.productduration.expire.service.FinProductExpire002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.service.LeaveMarkProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 *
 */
@Controller
@PageId("FinProductAddReduce002")
@RequestMapping("/invest/product/productduration/addreduce/FinProductAddReduce002")
public class FinProductAddReduce002Controller extends BaseController {

    @Autowired
    private FinProductAddReduce002Service finProductAddReduce002Service;

    @Autowired
    private FinProductAddReduceWorkflowService finProductAddReduceWorkflowService;

    @Autowired
    private BaseGetBusinessId baseGetBusinessIdImpl;

    @Autowired
    private IPlatformDate platformDateImpl;

    private static final String OPERATE_TYPE_REVOKE = "revoke";
    
    /**
     * BussTrdTransferOrderService
     */
    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderService;
    /**
     * {@linkplain}  add by dingjunfeng
     */
//    @Autowired
//    private FinProductExpire002Service finProductExpire002Service;
    /**
     * 查询托管户余额 add by dingjunfeng 
     */
//    @Autowired
//    private BussTrdAvailableCashQueryImplService bussTrdAvailableCashQueryImplService;
    
    @SuppressWarnings("rawtypes")
    @Autowired(required=false)
    private LeaveMarkProcessService leaveMarkProcessService;
    
    /**
     * 需要初始化的下拉菜单
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.交易方向, DictCode.币种,
        DictCode.是否, DictCode.销售部门};

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        setParasToModel(model);

        String totalTradeId = this.getParam("tradeId");
        if(totalTradeId==null){
        	totalTradeId = RequestUtil.getParam("busiCode");
        }       
        String finProdId = RequestUtil.getParam("finprodMarketId");
        if(finProdId==null){
        	finProdId = RequestUtil.getParam("marketId");
        }
        if(StringUtil.isEmpty(finProdId)){
            finProdId =  RequestUtil.getParam("finProdId");
        }
        if(!StringUtil.isNotEmpty(finProdId) && WorkFlowUtil.isNotFromToDoList()){
        	FinProductAddReduce002Dto entity = new FinProductAddReduce002Dto();
        	entity.setPs(DictCodeList.交易方向_产品提前赎回.getValue());
        	model.addAttribute(GlobalConstant.ENTITY, entity);
        }
        
        // 待办事项
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList()&&StringUtils.isNotEmpty(workflowId)) {
            FinProductAddReduce002Dto dto = finProductAddReduceWorkflowService.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
            // TODO 撤销流程中待办事项查看
        }
        // 双击打开
        else if (StringUtil.isNotEmpty(totalTradeId) && StringUtil.isNotEmpty(finProdId)) {
            if(!finProdId.startsWith(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue()+"_")){
                finProdId="F16_"+finProdId;
            }
            FinProductAddReduce002Dto entity = finProductAddReduce002Service.selectByTotalTradeId(totalTradeId, finProdId);
            if(entity.getPs().equals(DictCodeList.交易方向_产品违约赎回.getValue())){
                entity.setSdefault(DictCodeList.是否_是.getValue());
            }
            String profitType = RequestUtil.getParam("profitType");
            entity.setBussTrdTransferOrder(finProductAddReduce002Service.selectPayOrders(totalTradeId, profitType));
        	model.addAttribute(GlobalConstant.ENTITY, entity);
			String operateType = getParam("operateType");
			entity.setOperateType(operateType);
			model.addAttribute(GlobalConstant.ENTITY, entity);
        }
//        model.addAttribute("acctNoList", JsonUtils.object2Json(bussTrdTransferOrderService.queryAcctList(null)));
        return super.init(model);
    }

    private void setParasToModel(Model model) {
        model.addAttribute("profitType01", DictCodeList.收益类型_净值型.getValue());
        model.addAttribute("profitType02", DictCodeList.收益类型_收益型.getValue());
        model.addAttribute("profitType03", DictCodeList.收益类型_货币型.getValue());
        model.addAttribute("YES", DictCodeList.是否_是.getValue());
        model.addAttribute("NO", DictCodeList.是否_否.getValue());
        model.addAttribute("CLASSIFICATION_TYPE_01", DictCodeList.分层类型_金额分层.getValue());
        model.addAttribute("CLASSIFICATION_TYPE_02", DictCodeList.分层类型_期限分层.getValue());
        model.addAttribute("CLASSIFICATION_TYPE_03", DictCodeList.分层类型_客户类型.getValue());
        model.addAttribute("C6", DictCodeList.交易方向_份额强减.getValue());
        model.addAttribute("C17", DictCodeList.交易方向_产品提前赎回.getValue());
        model.addAttribute("sysDate", platformDateImpl.getSysDateStr());
        model.addAttribute("formTodoList", !WorkFlowUtil.isNotFromToDoList());
        model.addAttribute("F16", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        model.addAttribute("C03", DictCodeListExt1.金融产品类型_投管用_固定管理费.getValue());
        model.addAttribute("C05", DictCodeListExt1.金融产品类型_投管用_托管费.getValue());
        model.addAttribute("C06", DictCodeListExt1.金融产品类型_投管用_销售费.getValue());
     // 一体化对应etliu start
//        model.addAttribute("FT03", DictCodeList.费用品种类型_固定管理费.getValue());
//        model.addAttribute("FT05", DictCodeListExt2.费用类型_托管费.getValue());
//        model.addAttribute("FT06", DictCodeListExt1.一次性费用类型_投管_交易手续费.getValue());
        model.addAttribute("FT03", DictCodeList.费用品种类型_固定管理费.getValue());
        model.addAttribute("FT05", DictCodeList.费用品种类型_托管费.getValue());
        model.addAttribute("FT06", DictCodeList.费用品种类型_销售手续费.getValue());
     // 一体化对应etliu end
        model.addAttribute("basis360", DictCodeList.计息基础_A_360.getValue());
        model.addAttribute("acctNoList", JsonUtils.object2Json(bussTrdTransferOrderService.queryAcctList(null)));
    }

    /**
     * 获取子产品信息（强减信息区域）
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findReduceInfoData")
    public List<ReduceInfoDto> findReduceInfoData() {
        return finProductAddReduce002Service.findReduceInfoData();
    }
    
    /**
     * 
     * 获取子产品信息（强减信息区域）----新建页面强减本金和应付利息都置0
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findReduceInfoDataNoReduce")
    public List<ReduceInfoDto> findReduceInfoDataNoReduce() {
    	List<ReduceInfoDto> list = finProductAddReduce002Service.findReduceInfoData();
    	for (ReduceInfoDto reduceInfoDto : list) {
    		reduceInfoDto.setReduceAmt(null);
		}
        return list;
    }

    /**
     * 获取费用信息
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findProdFeeData")
    public List<ProductFeeInfoDto> findProdFeeData() {
        return finProductAddReduce002Service.findProdFeeData();
    }

    /**
     * 获取所有产品代码
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getProdId")
    public Page<SelectOptionDto> getProdId()
        throws Exception {
        return finProductAddReduce002Service.getProdId();
    }

    /**
     * 获取所有部门
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getSaleDepartment")
    public AjaxResult getSaleDepartment() {
        return finProductAddReduce002Service.getSaleDepartment();
    }

    @ResponseBody
    @RequestMapping("getValueByDate")
    public String getValueByDate() {
        return finProductAddReduce002Service.getValueByDate();
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) FinProductAddReduce002Dto dto,
                            BindingResult result) {
    	if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
       // AjaxResult ajaxResult= commonCheck(result, dto.getOperateType());
        AjaxResult ajaxResult=  new AjaxResult();
        //List<JoyinMessage> joyinMessages = new ArrayList<>();
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        if(ajaxResult.getErrorList() != null && ajaxResult.getErrorList().size()>0 ){
        	return  ajaxResult;
        }
        
        if (StringUtil.isNotEmpty(dto.getReduceInfoDtoList()) && dto.getReduceInfoDtoList().size() > 0){
            for (ReduceInfoDto ri : dto.getReduceInfoDtoList()){
                if (ri.getRemainPrinAmt() != null && ri.getRemainPrinAmt().compareTo(ri.getReduceAmt()) == -1){
                    errorList.add(new ValidMessage(this.getMessage("提前赎回本金不能大于剩余本金")));    
                }   
                break;
            }
            if (errorList.size()>0){
                ajaxResult.setErrorList(errorList);
                return  ajaxResult;       
            }
        }
        
		//判断方案是否在流程中
        //modify by wm 当在流程中不判断此校验 start
        if(WorkFlowUtil.isNotFromToDoList()){
        	ProcessMark processMarkb = new ProcessMark();
            processMarkb.setPageId("FinProductAddReduce002");
            processMarkb.setBusinessDataPk2(dto.getFinprodId());
            processMarkb.setBusinessDataPk3(dto.getPs());
            List<String> resultListb = leaveMarkProcessService.getBusinessDataInFlow(processMarkb);
            if (!StringUtil.isEmptyList(resultListb)) {
                errorList.add(new ValidMessage(this.getMessage("COMMON_E_0021", dto.getFinprodId(),dto.getPs())));
                ajaxResult.setErrorList(errorList);
                return  ajaxResult;
            }
        }
        //modify by wm 当在流程中不判断此校验 end
        //校验收付款账户
       /* BussTrdTransferOrderDto order = null;
        if(dto.getBussTrdTransferOrder().size()>0){
            order = dto.getBussTrdTransferOrder().get(0);
        }else{
            errorList.add(new ValidMessage("bussTrdTransferOrder", this.getMessage("划款指令不能为空")));
        }
        if(order!=null){
            if(!StringUtil.isNotEmpty(order.getRecAcctNo())){
                errorList.add(new ValidMessage("recAcctNo", this.getMessage("收款账户不能为空")));
            }
        }*/

        if (StringUtil.isEmptyList(errorList) ) {
            //String str1 = "";
           // String str2 = "";
            String btnName = "";
            ////update by wangcheng 2019/3/7
            /*if(!StringUtil.equals(OPERATE_TYPE_REVOKE ,dto.getOperateType())&&  dto.getVdate().compareTo(dto.getVdate2())!=0){
                int num = finProductEstablish002Service.selectIsTrade(dto.getFinprodId());
                if(num==0){
                    str1 = "产品成立日和起息日不是同一天,请确认是否认购?";
                }
            }*/
           /* if(order!=null){
                if(!StringUtil.isNotEmpty(order.getPayAcctNo())){
                    str2 = "付款账户未填,";
                }
            }*/
            if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SAVE,
                    dto.getOperateType())) {
                btnName = "请确认是否保存?";
            }
            else if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SUBMIT,
                    dto.getOperateType())) {
                btnName = "请确认是否提交?";
            }
            else if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_REVOKE,
                    dto.getOperateType())) {
                btnName = "请确认是否撤销?";
            }
           // ajaxResult.setData(this.getMessage(str1+str2+btnName));
            ajaxResult.setData(this.getMessage(btnName));
        }else{
            ajaxResult.setErrorList(errorList);
        }
       /* // add by dingjunfeng 2019-4-1 begin
     Map<String,Object> map =  finProductExpire002Service.checkpayAmt("", dto.getTradeDate(),dto.getPtlId(),dto.getFinprodId());
	 map.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
     map.put("tradeDate", dto.getSettleDate());
     map.put("ccy", dto.getCcy());
     map.put("portfolioId",dto.getPtlId());
     BigDecimal secAcctBalance =  bussTrdAvailableCashQueryImplService.getSecManageAvailableCash(map);
     if(secAcctBalance.compareTo(dto.getPrinAmt())<=0){
  	   if(StringUtil.isNotEmpty(dto.getPtlId())){
             //Map<String,Object> map =  finProductExpire002Service.checkpayAmt("", formatter.format(dto.getmDate()));
             FinProductFeePay001Dto dto1 =   (FinProductFeePay001Dto)map.get("finProductFeePay001Dto1");
             if(map.get("finProductFeePay001Dto") != null){
            	 joyinMessages.add(new JoyinMessage(this.getMessage("存在费用尚未支付，请先支付费用"),MessageType.确认消息));
             }
             if(dto1 != null){
           	  if(dto1.getFeeName() != null){
           		joyinMessages.add(new JoyinMessage(this.getMessage("存在费用尚未支付，请先支付费用"),MessageType.确认消息));
           	  }
           	 
             }
             if(secAcctBalance.compareTo(dto.getPrinAmt().add(dto.getActCusProfit()))<0){
            	 joyinMessages.add(new JoyinMessage(this.getMessage("产品剩余资金不足，请核实"),MessageType.确认消息));
           	   
             }else if(secAcctBalance.compareTo(dto.getPrinAmt().add(dto.getActCusProfit()))>0){
            	 joyinMessages.add(new JoyinMessage(this.getMessage("产品有多余剩余资金，请核实"),MessageType.确认消息));
             }
           } 
     }
     if(StringUtil.isNotEmpty(joyinMessages)){
    	 ajaxResult.setJoyinMessages(joyinMessages);
     }
     // add by dingjunfeng 2019-4-1 end
*/        return ajaxResult;
    }
    
    /**
     * 保存
     * 
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) FinProductAddReduce002Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FinProductAddReduce002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<FinProductAddReduce002Dto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_收益型产品提前赎回管理);
        String busiCode = baseGetBusinessIdImpl.buildBussinessCode(parameter);
        if (StringUtil.isEmpty(dto.getTotleTradeId())) {
            dto.setTotleTradeId(busiCode);
        }
        params.setBusinessCode(busiCode);
        params.setBusinessType(DictCodeList.共通业务类型_收益型产品提前赎回管理.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            String amt = null;
            DecimalFormat df2 = new DecimalFormat("###,###,###,###,###,###,##0.00#");
            if (entity.getTradeAmt() != null) {
                amt = df2.format(entity.getTradeAmt());
            }
            else {
                amt = df2.format(entity.getPrinAmt());
            }
            return WorkFlowUtil.concatComment(
                entity.getProdAbbr() + GlobalConstant.OPEN_PARENTHESIS + entity.getFinprodId() + GlobalConstant.CLOSE_PARENTHESIS,
                entity.getPsString(),
                this.getMessage("FinProductAddReduce001.cfmDate") + GlobalConstant.COLON + entity.getTradeDate(),
                this.getMessage("FinProductAddReduce002.tradeAmt") + ":" + amt);
        });
        processParameter.setWorkFlowParameter(params);
        dto.setProductTradeInfoDto(finProductAddReduceWorkflowService.genFinProductTradeDto(dto));
        String msg = finProductAddReduceWorkflowService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
