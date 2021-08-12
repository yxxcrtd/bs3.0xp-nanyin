package com.joyintech.tams.invest.risk.creditrisklimitmanagement.limitmanage.controller;

import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt3;
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
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.limitmanage.dto.LimitManageInfo;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.limitmanage.service.LimitManage001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;



/**
 * 额度管理
 * @author chenlei1
 * @version 1.0 2020年02月26日
 * @see LimitManage001Controller
 * @since 1.0
 */
@Controller
@PageId("LimitManage001")
@RequestMapping("/invest/risk/creditrisklimitmanagement/limitmanage/LimitManage001")
public class LimitManage001Controller extends BaseController {
	 
	/**
     * limitManageService
     */
    @Autowired(required = false)
    private LimitManage001Service limitManageService;
    
    @Override
    @RequestMapping(PAGE_INIT)    
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.币种,DictCode.额度管理状态,
        		DictCode.期限种类,DictCode.机构类型_风险,DictCode.机构层级_风险);

        LimitManageInfo lmi=new LimitManageInfo();
        lmi.setCcy(DictCodeList.币种_人民币_CNY.getValue());
        model.addAttribute(GlobalConstant.ENTITY, lmi);
        return super.init(model);
    }
  
    
    @ResponseBody
    @RequestMapping("/getInstitutionName")
    public Page<SelectOptionDto> getInstitutionName()
        throws Exception {
    	Map<String, Object> map = RequestUtil.getPageParam();
        map.put("customerType", DictCodeList.客户类型_法人.getValue());
        Page<SelectOptionDto> temp = limitManageService.getCustomerId(map);
      
        return temp;
    }
    
    
    @ResponseBody
    @RequestMapping("/getLimitLabel")
    public Page<SelectOptionDto> getLimitLabel()
        throws Exception {
    	Map<String, Object> map = RequestUtil.getPageParam();
        Page<SelectOptionDto> temp = limitManageService.getLimitLabel(map);
      
        return temp;
    }
    
    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<LimitManageInfo> selectAll()
        throws Exception {
        return limitManageService.selectForPage(
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
        ValidateGroupDelete.class}) LimitManageInfo dto, BindingResult result ) {
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
   public AjaxResult delete(@RequestBody @Validated({ValidateGroupDelete.class}) LimitManageInfo dto, BindingResult br) throws Exception{
       if (br.hasErrors()) {
           return new AjaxResult(br);
       }
       ProcessParameter<LimitManageInfo> processParameter = new ProcessParameter<LimitManageInfo>();
       WorkFlowParameter<LimitManageInfo> params = new WorkFlowParameter<LimitManageInfo>();
       params.setPageId("LimitManage002");
       params.setElementId(dto.getElementId());
       params.setBusinessCode(entity -> entity.getPflsh());
       params.setBusinessType(DictCodeListExt3.共通业务类型_额度管理.getValue());
       // 设置流程业务说明, entity即传入的dto
       params.setComment(entity -> {
       	   return WorkFlowUtil.concatComment(getMessage("LimitManage001"),
                      GlobalConstant.OPEN_PARENTHESIS, entity.getCustomerId(), GlobalConstant.CLOSE_PARENTHESIS,
                      getMessage("LimitManage001.creditAmt"), GlobalConstant.OPEN_PARENTHESIS,entity.getCreditAmt().toString(),
                       GlobalConstant.CLOSE_PARENTHESIS);
       });
       processParameter.setWorkFlowParameter(params);
       String msg = limitManageService.start(processParameter,
           dto.getSelectedItem().toArray(
               new LimitManageInfo[dto.getSelectedItem().size()]));
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
        return limitManageService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
}
