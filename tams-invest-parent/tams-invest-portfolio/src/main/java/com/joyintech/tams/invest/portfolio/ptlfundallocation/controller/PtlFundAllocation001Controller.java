package com.joyintech.tams.invest.portfolio.ptlfundallocation.controller;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.trade.service.TrdCommonBaseService;
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
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.portfolio.ptlfundallocation.dto.PtlFundAllocationDto;
import com.joyintech.tams.invest.portfolio.ptlfundallocation.service.PtlFundAllocation001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 资金划拨管理
 */
@Controller
@PageId("PtlFundAllocation001")
@RequestMapping("/invest/portfolio/ptlfundallocation/PtlFundAllocation001")
public class PtlFundAllocation001Controller extends BaseController {
    
    @Autowired
    private PtlFundAllocation001Service ptlFundAllocation001Service;

    @Autowired
    private TrdCommonBaseService trdCommonBaseService;
    /**
     * 重写init方法
     * @param model 模型
     * @return 返回pageId
     */
    @Override
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.指令类型,DictCode.画面数据状态);
        model.addAttribute("pageStatus", DictCodeListExt1.画面数据状态_有效.getValue());
        return super.init(model);
    }
    
    /**
     * 查询组合
     * @return 组合集合
     * @see
     * @since 1.0
     */
    @RequestMapping("/getPortfolioList")
    @ResponseBody
    public List<SelectOptionDto> getPortfolioList(){
        return ptlFundAllocation001Service.getPortfolioList(RequestUtil.getPageParam());
    }
    
    /**
     * 获取账号
     * @return 账号下拉框集合
     * @see
     * @since 1.0
     */
    @RequestMapping("/getAcct")
    @ResponseBody
    public List<SuggestDto> getAcct(){
        return ptlFundAllocation001Service.getAcct(RequestUtil.getPageParam());
    }
    
    /**
     * 分页查询
     * @return Page<MstInterestRateQuotationInfoDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<PtlFundAllocationDto> findPage()
        throws Exception {
        return ptlFundAllocation001Service.findPage(RequestUtil.getPageParam());
    }
    
    /**
     * 删除按钮点击时的check
     * @param dto 验证的实体类
     * @param result 验证的结果
     * @return 错误信息
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) PtlFundAllocationDto dto, BindingResult result) {
    	if (ptlFundAllocation001Service.checkBusinessDataInFlow(dto)) {
    		AjaxResult res= new AjaxResult();
			res.setMessage(this.getMessage("COMMON_E_0012",dto.getOrderId()), MessageType.错误消息);
    		return res;
    	}

        if (StringUtil.equals(dto.getOperateType(), "revoke")) {
            if (trdCommonBaseService.isAlreadyRevoked(dto.getSelectedItem().get(0).getTradeId())) {
                ValidMessage validMessage = new ValidMessage(getMessage("TRD_W_0001",
                        getMessage("pingan.common.revoke"), getMessage("pingan.common.revoke")));
                ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
                errorList.add(validMessage);
                AjaxResult ar = new AjaxResult();
                ar.setErrorList(errorList);
                return ar;
            }
        }
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 删除操作
     * @param dto 验证的实体类
     * @param br 错误结果
     * @return 错误信息
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("revoke")
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) PtlFundAllocationDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<PtlFundAllocationDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<PtlFundAllocationDto> params = new WorkFlowParameter<>();
        params.setPageId("PtlFundAllocation002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getPortfolioId());
        params.setBusinessType(DictCodeList.共通业务类型_资金划拨管理.getValue());
        // 设置流程业务说明, 组合名称 + "(" + 组合名称 + ")" + "-"  + "组合代码" + "("  + "组合代码"+")"
        params.setComment(
            entity -> this.getMessage("PtlFundAllocation001.portfolioId") + "("
                      + entity.getPortfolioId() + ")" + "-"
                      + this.getMessage("PtlFundAllocation001.portfolioName") + "("
                      + entity.getPortfolioName() + ")" 
                      + this.getMessage("PtlFundAllocation001.orderId") + "("
                      + entity.getOrderId() + ")" );
        processParameter.setWorkFlowParameter(params);
        String msg = ptlFundAllocation001Service.start(processParameter,
            dto.getSelectedItem().toArray(
                new PtlFundAllocationDto[dto.getSelectedItem().size()]));
//        ArrayList<String> list = new ArrayList<String>();
//        for(PtlFundAllocationDto dt : dto.getSelectedItem()){
//            list.add(dt.getPortfolioId());
//        }
//        msg = this.getMessage("COMMON_I_0030",this.getMessage("PtlManagementInfo001.portfolioId") , list.toString());
//        msg = msg+this.getMessage("PtlManagementInfo001.portfolioId")+list.toString();
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
        return ptlFundAllocation001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
}
