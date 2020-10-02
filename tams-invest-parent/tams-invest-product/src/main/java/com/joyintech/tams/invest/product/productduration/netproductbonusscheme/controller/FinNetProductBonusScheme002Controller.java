package com.joyintech.tams.invest.product.productduration.netproductbonusscheme.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.workflow.engine.WorkflowContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.productduration.netproductbonusscheme.dto.FinNetProductBonusSchemeDto;
import com.joyintech.tams.invest.product.productduration.netproductbonusscheme.service.FinNetProductBonusSchemeService;
import com.joyintech.tams.invest.product.productduration.netproductduration.dto.FinNetProductDurationLayerDto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 净值型分红方案登记controller
 *
 * @author liuwei
 * @version 1.0 2018年9月7日
 * @see FinNetProductBonusScheme002Controller
 * @since 1.0
 */
@Controller
@PageId("FinNetProductBonusScheme002")
@RequestMapping("/invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme002")
public class FinNetProductBonusScheme002Controller extends BaseController {
    /**
     * 净值型分红方案service
     */
    @Autowired(required=false)
    private FinNetProductBonusSchemeService finNetProductBonusSchemeService;

    /**
     * 平台日期组件接口
     */
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;

    /**
     * 与日期有关日期组件
     */
    @Autowired(required=false)
    private DateCalService dateCalService;

//    /**
//     * 业务编号共通组件
//     */
//    @Autowired(required=false)
//    private BaseGetBusinessId baseGetBussinessId;
    /**
     * 业务标号生成共通
     */
    @Autowired(required=false)
    private BaseGetBusinessId baseGetBusinessId;
    /**
     * 净值型分红方案登记界面的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.分红方案, DictCode.币种,DictCode.是否);
        String bonusPlanId = this.getParam("bonusPlanId");
        String workflowId = this.getParam("workflowId");
        Date date = iPlatformDate.getSysDate();
        String iPlatformDateStr = iPlatformDate.getSysDateStr();
        String bonuScalcFlag = DictCodeList.分红方案_按单位分红.getValue();
        if (!StringUtil.isNotEmpty(bonusPlanId) && WorkFlowUtil.isNotFromToDoList()) {
            FinNetProductBonusSchemeDto entity = new FinNetProductBonusSchemeDto();
            entity.setBonuScalcFlag(bonuScalcFlag);
            entity.setIsNetMormalize(DictCodeList.是否_否.getValue());
            //默认平台日期
            entity.setBaseDate(date);
            entity.setDelDate(date);
            //BSTOZ-1270 净值分红方案管理--分红方案登记，需求要求红利下发日默认为平台日 guyunfei start
            entity.setBonusDate(date);
            //BSTOZ-1270 净值分红方案管理--分红方案登记，需求要求红利下发日默认为平台日 guyunfei end
            model.addAttribute("entity", entity);
        } 
        else if (StringUtil.isNotEmpty(bonusPlanId) && WorkFlowUtil.isNotFromToDoList()) {
            FinNetProductBonusSchemeDto finNetProductBonusSchemeInfo = finNetProductBonusSchemeService.selectInfo(bonusPlanId);
            if(StringUtil.equals(finNetProductBonusSchemeInfo.getBonuScalcFlag(),DictCodeList.分红方案_按单位分红.getValue())
                && finNetProductBonusSchemeInfo.getUnitBonusVlu() != null
                && finNetProductBonusSchemeInfo.getTdyUnitValue() != null){
            	BigDecimal big = finNetProductBonusSchemeInfo.getTdyUnitValue().subtract(finNetProductBonusSchemeInfo.getUnitBonusVlu());
            	//add by gcl 2019/4/1 start
            	DecimalFormat df1 = new DecimalFormat("0.0000");
                String str =df1.format(big);
                BigDecimal bd=new BigDecimal(str);
                finNetProductBonusSchemeInfo.setNetvalue(bd);
              //add by gcl 2019/4/1 end
            }
            else{
//                Map<String,Object> map = new HashMap<>();
//                if(StringUtil.isNotEmpty(finNetProductBonusSchemeInfo.getProdStyle())){
//                    map.put("finprodId",finNetProductBonusSchemeInfo.getProdStyle());
//                }
//                else{
//                    map.put("finprodId",finNetProductBonusSchemeInfo.getFinprodId());
//                }
//                map.put("vluRefDate",DateUtil.format(finNetProductBonusSchemeInfo.getVluRefDate()));
//                FinNetProductBonusSchemeDto dto = finNetProductBonusSchemeService.getTdyUnitValueAndTdyShareAmt(map);
//                if(dto != null){
//                    BigDecimal tdyAssetAmt = dto.getTdyAssetAmt();
//                    if(tdyAssetAmt != null && finNetProductBonusSchemeInfo.getTotalAmt() != null
//                        && finNetProductBonusSchemeInfo.getTdyShareAmt() != null){
////                        finNetProductBonusSchemeInfo.setNetvalue((tdyAssetAmt.subtract(finNetProductBonusSchemeInfo.getTotalAmt()))
////                            .divide(finNetProductBonusSchemeInfo.getTdyShareAmt()));
//                        finNetProductBonusSchemeInfo.setNetvalue(ArithmeticUtil.divide(tdyAssetAmt.subtract(
//                                finNetProductBonusSchemeInfo.getTotalAmt()), finNetProductBonusSchemeInfo.getTdyShareAmt(), 4));
//                    }
//                }
                ProcessMark processMark;
                try {
                    finNetProductBonusSchemeInfo.setFinprodId(finNetProductBonusSchemeInfo.getFinprodId());
                    finNetProductBonusSchemeInfo.setDealType("bonusScheRegister");
                    finNetProductBonusSchemeInfo.setTradeId(bonusPlanId);
                    processMark = WorkflowContext.getProcessMarkService().getParameterByAnnotation(finNetProductBonusSchemeInfo);
                    processMark.setPageId("FinNetProductBonusScheme002");
                    finNetProductBonusSchemeInfo = finNetProductBonusSchemeService.getBussinessEntityByPk(processMark);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            model.addAttribute("entity", finNetProductBonusSchemeInfo);
        } 
        else if (!WorkFlowUtil.isNotFromToDoList()&&StringUtil.isNotBlank(workflowId)) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            FinNetProductBonusSchemeDto finNetProductBonusSchemeInfo = finNetProductBonusSchemeService.getBussinessEntityByWorkflowId(
                    workflowId);
            // 设置到model中
            model.addAttribute("entity", finNetProductBonusSchemeInfo);
        }
        model.addAttribute("iPlatformDateStr",iPlatformDateStr);
        model.addAttribute("iPlatformDate",DateUtil.format(date));
        model.addAttribute("iPlatformDatee",DateUtil.format(DateUtil.addDays(date,1)));
        model.addAttribute("bonuScalcFlag",bonuScalcFlag);
        return super.init(model);
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
            ValidateGroupEdit.class}) FinNetProductBonusSchemeDto dto, BindingResult result) {
        if(result.getAllErrors().isEmpty() && 
            (dto.getOperateType() != null && !StringUtil.equals("", dto.getOperateType()))) {
            List<JoyinMessage> joyinMessages = new ArrayList<>();
            JoyinMessage ms = new JoyinMessage(getMessage("FIN_PROD_E_0058"), MessageType.确认消息);
            JoyinMessage ms1 = new JoyinMessage(getMessage("BUSS_COM_E_0039",
                    getMessage("FinNetProductBonusScheme002.delDate")), MessageType.确认消息);
            JoyinMessage ms2 = new JoyinMessage(getMessage("FIN_PROD_E_0059"), MessageType.确认消息);
            JoyinMessage ms3 = new JoyinMessage(getMessage("FIN_PROD_E_0060"), MessageType.确认消息);
            if(StringUtil.isNotEmpty(dto.getNetvalue())&&dto.getNetvalue().compareTo(BigDecimal.ONE) < 0) {
                joyinMessages.add(ms);
            }
            if(dateCalService.isDealCalendarHoliday(dto.getDelDate(),
                    finNetProductBonusSchemeService.selectCalendarId(dto.getFinprodId()))){
                joyinMessages.add(ms1);
            }
            if((StringUtil.isNotEmpty(dto.getUnitBonusVlu())&&dto.getUnitBonusVlu().compareTo(BigDecimal.ZERO)>0)||
                    (StringUtil.isNotEmpty(dto.getTotalAmt())&&dto.getTotalAmt().compareTo(BigDecimal.ZERO)>0)){
                if(dto.getTdyUnitValue() == null || dto.getTdyShareAmt() == null || dto.getTdyUnitValue().compareTo(BigDecimal.ZERO)<=0||dto.getTdyShareAmt().compareTo(BigDecimal.ZERO)<=0){
                    joyinMessages.add(ms2);
                }
                //TODO 未分配利润占时未取到值 先注释 lijun modify BS1.9BSBS-308 2019-05-16 start
//                if(dto.getTdyUnpaidIntincexp().compareTo(BigDecimal.ZERO)<=0){
//                    joyinMessages.add(ms2);
//                }
                // 未分配利润占时未取到值 先注释 lijun modify BS1.9BSBS-308 2019-05-16 end
            }
            AjaxResult wr = commonCheck(result, dto.getOperateType());
            wr.setJoyinMessages(joyinMessages);
            return wr;
        }
        return commonCheck(result, dto.getOperateType());
    }
    
   /**
     * 保存操作
     *
     * @param dto dto
     * @param br BindingResult
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) FinNetProductBonusSchemeDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
        	// 新建页面时业务代码生成参数
            BussinessCodeParameter parameter = new BussinessCodeParameter();
            parameter.setCommonBusinessType(DictCodeList.共通业务类型_净值型产品分红方案管理);
            String planId = baseGetBusinessId.buildBussinessCode(parameter);
            dto.setBonusPlanId((planId==null) ? IDGenerator.generate():planId);
            dto.setTradeId(dto.getBonusPlanId());
        }
        ProcessParameter<FinNetProductBonusSchemeDto> processParameter = new ProcessParameter<FinNetProductBonusSchemeDto>();
        WorkFlowParameter<FinNetProductBonusSchemeDto> params = new WorkFlowParameter<FinNetProductBonusSchemeDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getBonusPlanId());
        params.setBusinessType(DictCodeList.共通业务类型_净值型产品分红方案管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        if(StringUtil.equals(dto.getBonuScalcFlag(),DictCodeList.分红方案_按单位分红.getValue())) {
            params.setComment(entity -> "产品代码" + "(" + entity.getFinprodMarketId() + ")-" + "分红方案编号" + "(" + entity.getBonusPlanId() + ")-"
                    + "按单位分红" + "(" + entity.getUnitBonusVlu() + ")-" + "除权日" + "(" + DateUtil.format(entity.getDelDate()) + ")");
        }
        else{
            params.setComment(entity -> "产品代码" + "(" + entity.getFinprodMarketId() + ")-" + "分红方案编号" + "(" + entity.getBonusPlanId() + ")-"
                    + "按总额分红" + "(" + entity.getTotalAmt() + ")-" + "除权日" + "(" + DateUtil.format(entity.getDelDate()) + ")");
        }
        processParameter.setWorkFlowParameter(params);
        String msg = finNetProductBonusSchemeService.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    /**
     * 查询基准日未分配余额
     *
     * @return 结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getTdyUnpaidIntincexp")
    public AjaxResult getTdyUnpaidIntincexp(){
        AjaxResult ar = new AjaxResult();
        Map<String,Object> map = RequestUtil.getPageParam();
        ar.setData(finNetProductBonusSchemeService.getTdyUnpaidIntincexp(map));
        return ar;
    }

    /**
     * 查询净值参考日
     *
     * @return 结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getVluRefDate")
    public AjaxResult getVluRefDate(){
        AjaxResult ar = new AjaxResult();
        Map<String,Object> map = RequestUtil.getPageParam();
        ar.setData(DateUtil.format(finNetProductBonusSchemeService.getVluRefDate(map)));
        return ar;
    }

    /**
     * 查询参考日净值和份额
     *
     * @return 结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getTdyUnitValueAndTdyShareAmt")
    public FinNetProductBonusSchemeDto getTdyUnitValueAndTdyShareAmt(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return finNetProductBonusSchemeService.getTdyUnitValueAndTdyShareAmt(map);
    }
    /**
     * 根据产品id查询分档产品列表
     * @return 分档产品列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getProdStyleList")
    public List<FinNetProductDurationLayerDto> getProdStyleList(@RequestBody FinNetProductBonusSchemeDto dto) {
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("finprodId", dto.getFinprodId());
        return finNetProductBonusSchemeService.getProdStyleList(map);
    }
}
