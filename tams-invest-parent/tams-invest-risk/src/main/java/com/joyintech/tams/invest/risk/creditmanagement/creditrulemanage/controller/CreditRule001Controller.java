package com.joyintech.tams.invest.risk.creditmanagement.creditrulemanage.controller;

import java.util.Map;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.invest.risk.creditmanagement.creditrulemanage.dto.CreditRuleDto;
import com.joyintech.tams.invest.risk.creditmanagement.creditrulemanage.service.CreditRuleService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
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
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.workflow.engine.util.WorkFlowUtil;



/**
 * 额度校验规则管理
 * @author gejiaao
 * @version 1.0 2020年02月15日
 * @see CreditRule001Controller
 * @since 1.0
 */
@Controller
@PageId("CreditRule001")
@RequestMapping("/invest/risk/creditmanagement/creditrulemanage/CreditRule001")
public class CreditRule001Controller extends BaseController {
	 
	/**
     * creditRuleService
     */
    @Autowired
    private CreditRuleService creditRuleService;
    
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.额度分类,DictCode.授信资产类型, DictCode.授信资产二级分类,
                DictCode.授信资产三级分类, DictCode.授信资产四级分类, DictCode.授信资产五级分类);
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return super.init(model);
    }
  
    @ResponseBody
    @RequestMapping("/getLimitLabel")
    public Page<SelectOptionDto> getLimitLabel()
        throws Exception {
    	Map<String, Object> map = RequestUtil.getPageParam();
        Page<SelectOptionDto> temp = creditRuleService.getLimitLabel(map);
      
        return temp;
    }
    
    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<CreditRuleDto> selectAll()
        throws Exception {
        return creditRuleService.selectForPage(
            RequestUtil.getParameters(getRequest()));
    }
    
   
	
	/**
     * 保存按钮点击时的check
     * @return check结果
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) CreditRuleDto dto, BindingResult result ) {
        return commonCheck(result, dto.getOperateType());
    }
    
	 /**
    * 删除
    * @param dto
    * @return
    * @throws Exception
    */
   @ResponseBody
   @RequestMapping(ACTION_DELETE)
   public AjaxResult delete(@RequestBody @Validated({ValidateGroupDelete.class}) CreditRuleDto dto, BindingResult br) throws Exception{
       if (br.hasErrors()) {
           return new AjaxResult(br);
       }
       ProcessParameter<CreditRuleDto> processParameter = new ProcessParameter<CreditRuleDto>();
       WorkFlowParameter<CreditRuleDto> params = new WorkFlowParameter<CreditRuleDto>();
       params.setPageId("CreditRule002");
       params.setElementId(dto.getElementId());
       params.setBusinessCode(entity -> entity.getCreditRuleId());
       params.setBusinessType(DictCodeList.共通业务类型_额度扣减规则管理.getValue());
       // 设置流程业务说明, entity即传入的dto
       params.setComment(entity -> {
       	   return WorkFlowUtil.concatComment(getMessage("CreditRule001.delete"),
                      GlobalConstant.OPEN_PARENTHESIS, entity.getCreditRuleId(), GlobalConstant.CLOSE_PARENTHESIS,
                      getMessage("CreditRule001.delete"), GlobalConstant.OPEN_PARENTHESIS,entity.getCreditTagName(),
                       GlobalConstant.CLOSE_PARENTHESIS);
       });
       processParameter.setWorkFlowParameter(params);
       String msg = creditRuleService.start(processParameter,
           dto.getSelectedItem().toArray(
               new CreditRuleDto[dto.getSelectedItem().size()]));
       return new AjaxResult(msg);
   }
   
    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return creditRuleService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
}
