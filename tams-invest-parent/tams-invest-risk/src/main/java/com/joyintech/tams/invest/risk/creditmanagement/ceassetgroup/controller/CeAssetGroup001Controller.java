package com.joyintech.tams.invest.risk.creditmanagement.ceassetgroup.controller;

import java.util.HashMap;
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
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroup.dto.CreditLinesAstGrpDto;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroup.service.CeAssetGroup001Service;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroup.service.CeAssetGroup002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;



/**
 * 资产系列授信
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see CeAssetGroup001Controller
 * @since 1.0
 */
@Controller
@PageId("CeAssetGroup001")
@RequestMapping("/invest/risk/creditmanagement/ceassetgroup/CeAssetGroup001")
public class CeAssetGroup001Controller extends BaseController {
	 
	/**
     * CeAssetGroup001Service
     */
    @Autowired(required = false)
    private CeAssetGroup001Service ceAssetGroup001Service;
    
    /**
     * CeAssetGroup002Service
     */
    @Autowired(required = false)
    private CeAssetGroup002Service ceAssetGroup002Service;
    
    @Override
    public String init(Model model) {
        return super.init(model);
    }
  
    @ResponseBody
    @RequestMapping("/getAssetGroupAliasIds")
    public List<SelectOptionDto> getAssetGroupAliasIds()
        throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        /** modify by yanya on 20190930 param参数获取不到修复(名称改为search) start **/
        String param = super.getRequest().getParameter("search");
        /** modify by yanya on 20190930 param参数获取不到修复(名称改为search) end **/
        map.put("assetAliasIds", param);
        List<SelectOptionDto> temp = ceAssetGroup001Service.getAssetGroupAliasIds(map);
      
        return temp;
    }
    
    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<CreditLinesAstGrpDto> selectAll()
        throws Exception {
        return ceAssetGroup001Service.selectForPage(
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
        ValidateGroupDelete.class}) CreditLinesAstGrpDto dto, BindingResult result ) {
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
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) CreditLinesAstGrpDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<CreditLinesAstGrpDto> processParameter = new ProcessParameter<CreditLinesAstGrpDto>();
        WorkFlowParameter<CreditLinesAstGrpDto> params = new WorkFlowParameter<CreditLinesAstGrpDto>();
        params.setPageId("CeAssetGroup002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getSeqno());
        params.setBusinessType(DictCodeList.共通业务类型_资产系列授信.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
        	 return WorkFlowUtil.concatComment(getMessage("CeAssetGroup002.seqno"),
                     GlobalConstant.OPEN_PARENTHESIS, entity.getSeqno(), GlobalConstant.CLOSE_PARENTHESIS,
                     getMessage("CeAssetGroup002.customerId"), GlobalConstant.OPEN_PARENTHESIS,
                     entity.getCustomerId(), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = ceAssetGroup002Service.start(processParameter,
            dto.getSelectedItem().toArray(
                new CreditLinesAstGrpDto[dto.getSelectedItem().size()]));
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
        return ceAssetGroup001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
}
