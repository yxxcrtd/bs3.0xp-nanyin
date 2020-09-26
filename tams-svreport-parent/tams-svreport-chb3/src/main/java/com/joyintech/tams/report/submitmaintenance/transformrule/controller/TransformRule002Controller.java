package com.joyintech.tams.report.submitmaintenance.transformrule.controller;

import java.util.Map;

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
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.submitmaintenance.transformrule.dto.TransformRule001Dto;
import com.joyintech.tams.report.submitmaintenance.transformrule.service.TransformRuleMService;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 *  变形转换规则新建界面Controller
 * 〈功能详细描述〉
 * @author zhengxiaolian
 * @version 1.0 2019年12月10日
 * @see TransformRule002Controller
 * @since 1.0
 */
@Controller
@PageId("SvrTransformRule002")
@RequestMapping("/svreport/submitmaintenance/transformrule/SvrTransformRule002")
public class TransformRule002Controller extends BaseController{
	
    /**
     * transformRule002Service
     */
    @Autowired
    private TransformRuleMService transformRuleMService;
    
	
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	super.setSelectOptionToModel(model, DictCode.生僻字类型);
    	model.addAttribute("TRANS_TYPE_UNC", DictCodeListExt3.生僻字类型_生僻字.getValue());
    	Map<String,Object>map=RequestUtil.getPageParam();
    	if(map.containsKey("seqno")){
        	TransformRule001Dto transformRule001Dto =transformRuleMService.getEntity(map);
        	if(transformRule001Dto!=null){
        		model.addAttribute("entity", transformRule001Dto);
        		model.addAttribute("seqno", transformRule001Dto.getSeqno());
        	}
    	}
        return super.init(model);
    }
    
    /**
     * 数据检查
     *
     * @param dto 参数
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkSave")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) TransformRule001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    
    /**
     * 添加保存
     *
     * @param dto 参数
     * @return 检查结果
     * @see
     * @since 1.0
     */
   @ResponseBody
   @RequestMapping("doSave")
   public AjaxResult doSave(@RequestBody TransformRule001Dto dto){
	   dto.setDataStatus(DictCodeListExt1.维护数据状态_有效.getValue());
	   dto.setIsModify(DictCodeListExt1.是或否_是.getValue());
	   dto.setCreateUser(SecurityUtil.getLoginUser().getUserName());
	   dto.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
	   dto.setCreateDept(CommonUtil.getDeptCode());
	   transformRuleMService.doSave(dto);
	   return new AjaxResult(this.getMessage("保存成功!"));
   }
   
   
   /**
    * 修改保存
    *
    * @param dto 参数
    * @return 检查结果
    * @see
    * @since 1.0
    */
   @ResponseBody
   @RequestMapping("doEditSave")
   public AjaxResult doEditSave(@RequestBody TransformRule001Dto dto){
	   dto.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
	   transformRuleMService.doEditSave(dto);
	   return new AjaxResult(this.getMessage("修改成功!"));
   }
   
   /**
    * 刷新待处理数据
    *
    * @return 检查结果
    * @see
    * @since 1.0
    */
   @ResponseBody
   @RequestMapping("doRefreshTrans")
   public void doRefreshTrans(){
	   transformRuleMService.excuteRefresh();
   }

}
