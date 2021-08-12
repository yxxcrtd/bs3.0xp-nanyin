package com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitmanage.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitmanage.dto.ObjectLimitManageInfoDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitmanage.service.ObjectLimitManageService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;



/**
 * 项目额度管理
 * @author chenlei1
 * @version 1.0 2020年03月08日
 * @see ObjectLimitManage001Controller
 * @since 1.0
 */
@Controller
@PageId("ObjectLimitManage001")
@RequestMapping("/invest/risk/creditrisklimitmanagement/objectlimitmanage/ObjectLimitManage001")
public class ObjectLimitManage001Controller extends BaseController {
	 
	/**
     * objectLimitManageService
     */
    @Autowired(required = false)
    private ObjectLimitManageService objectLimitManageService;
    
    @Override
    @RequestMapping(PAGE_INIT)    
    public String init(Model model) {
        this.setSelectOptionToModel(model,DictCode.资产五级分类,DictCode.额度管理状态);
        ObjectLimitManageInfoDto olmi=new ObjectLimitManageInfoDto();
        olmi.setCcy(DictCodeListRsk.期限种类_月.getValue());
        olmi.setEndTermValue(new BigDecimal(2));
        model.addAttribute(GlobalConstant.ENTITY, olmi);
        return super.init(model);
    }
  
    @ResponseBody
    @RequestMapping("/getInstitutionName")
    public Page<SelectOptionDto> getInstitutionName()
        throws Exception {
    	Map<String, Object> map = RequestUtil.getPageParam();
        map.put("customerType", DictCodeList.客户类型_法人.getValue());
        Page<SelectOptionDto> temp = objectLimitManageService.getCustomerId(map);
      
        return temp;
    }
    
    
    /**
     * 
     * 获得审批编号
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */

    @ResponseBody
    @RequestMapping("/getApporId")
    public List<SuggestDto> getApporId(String param)
        throws Exception {
        List<SuggestDto> temp = objectLimitManageService.getApporId(param);
        return temp;
    }
    
    @ResponseBody
    @RequestMapping("/getCustomerName")
    public List<SuggestDto> getCustomerName(String param)
        throws Exception {
        List<SuggestDto> temp = objectLimitManageService.getCustomerName(param);
        return temp;
    }
    
    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ObjectLimitManageInfoDto> selectAll()
        throws Exception {
        return objectLimitManageService.selectForPage(
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
        ValidateGroupDelete.class}) ObjectLimitManageInfoDto dto, BindingResult result ) {
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
   public AjaxResult delete(@RequestBody @Validated({ValidateGroupDelete.class}) ObjectLimitManageInfoDto dto, BindingResult br) throws Exception{
       if (br.hasErrors()) {
           return new AjaxResult(br);
       }
       ProcessParameter<ObjectLimitManageInfoDto> processParameter = new ProcessParameter<ObjectLimitManageInfoDto>();
       WorkFlowParameter<ObjectLimitManageInfoDto> params = new WorkFlowParameter<ObjectLimitManageInfoDto>();
       params.setPageId("ObjectLimitManage002");
       params.setElementId(dto.getElementId());
       params.setBusinessCode(entity -> entity.getApproId());
       params.setBusinessType(DictCodeList.共通业务类型_机构授信.getValue());
       // 设置流程业务说明, entity即传入的dto
       params.setComment(entity -> {
       	   return WorkFlowUtil.concatComment(getMessage("ObjectLimitManage001"),
                      GlobalConstant.OPEN_PARENTHESIS, entity.getApproId(), GlobalConstant.CLOSE_PARENTHESIS,
                      getMessage("ObjectLimitManage001"), GlobalConstant.OPEN_PARENTHESIS,entity.getCustomerId(),
                       GlobalConstant.CLOSE_PARENTHESIS);
       });
       processParameter.setWorkFlowParameter(params);
       String msg = objectLimitManageService.start(processParameter,
           dto.getSelectedItem().toArray(
               new ObjectLimitManageInfoDto[dto.getSelectedItem().size()]));
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
        return objectLimitManageService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
    
    
    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @ResponseBody
    @RequestMapping("/importFile")
    public List<ExcelValidDto> importData(String fileName,String impType) {
        return this.objectLimitManageService.importData(fileName,impType);
    }
}
