package com.joyintech.tams.invest.risk.creditmanagement.creditlinemanage.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
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
import com.joyintech.tams.invest.risk.creditmanagement.creditlinemanage.dto.CreditTagInfoDto;
import com.joyintech.tams.invest.risk.creditmanagement.creditlinemanage.service.CeLineService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;



/**
 * 额度标签管理
 * @author chenlei1
 * @version 1.0 2019年02月15日
 * @see CeLine001Controller
 * @since 1.0
 */
@Controller
@PageId("CeLine001")
@RequestMapping("invest/risk/creditmanagement/creditlinemanage/CeLine001")
public class CeLine001Controller extends BaseController {
	 
	/**
     * ceLineService
     */
    @Autowired(required = false)
    private CeLineService ceLineService;
    
    @Override
    @RequestMapping(PAGE_INIT)    
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.扣减规则,DictCode.额度分类);
        return super.init(model);
    }
  
    @ResponseBody
    @RequestMapping("/getLimitLabel")
    public Page<SelectOptionDto> getLimitLabel()
        throws Exception {
    	Map<String, Object> map = RequestUtil.getPageParam();
        Page<SelectOptionDto> temp = ceLineService.getLimitLabel(map);
      
        return temp;
    }
    
    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<CreditTagInfoDto> selectAll()
        throws Exception {
        return ceLineService.selectForPage(
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
        ValidateGroupDelete.class}) CreditTagInfoDto dto, BindingResult result ) {
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
   public AjaxResult delete(@RequestBody @Validated({ValidateGroupDelete.class}) CreditTagInfoDto dto, BindingResult br) throws Exception{
       if (br.hasErrors()) {
           return new AjaxResult(br);
       }
       ProcessParameter<CreditTagInfoDto> processParameter = new ProcessParameter<CreditTagInfoDto>();
       WorkFlowParameter<CreditTagInfoDto> params = new WorkFlowParameter<CreditTagInfoDto>();
       params.setPageId("CeLine002");
       params.setElementId(dto.getElementId());
       params.setBusinessCode(entity -> entity.getCreditTagId());
       params.setBusinessType(DictCodeList.共通业务类型_额度标签管理.getValue());
       // 设置流程业务说明, entity即传入的dto
       params.setComment(entity -> {
       	   return WorkFlowUtil.concatComment(getMessage("CeLine002"),
                      GlobalConstant.OPEN_PARENTHESIS, entity.getCreditTagId(), GlobalConstant.CLOSE_PARENTHESIS,
                      getMessage("CeLine002"), GlobalConstant.OPEN_PARENTHESIS,entity.getCreditTagName(),
                       GlobalConstant.CLOSE_PARENTHESIS);
       });
       processParameter.setWorkFlowParameter(params);
       String msg = ceLineService.start(processParameter,
           dto.getSelectedItem().toArray(
               new CreditTagInfoDto[dto.getSelectedItem().size()]));
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
        return ceLineService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }

    // add by yushilong 20200630 增加额度标签管理导入功能 start
    /**
     * 导入
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importData(String fileName)
            throws Exception{
        return ceLineService.importData(fileName);
    }
    // add by yushilong 20200630 增加额度标签管理导入功能 end
}
