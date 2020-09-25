package com.joyintech.tams.invest.product.productelement.findisclosuremodule.findisclosuremodule.controller;

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
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.findisclosuremodule.dto.FinDisclosureModuleDto;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.findisclosuremodule.service.FinDisclosureModuleService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 信息披露模板管理
 * @author 王泽慧
 *
 */
@Controller
@PageId("FinDisclosureModule001")
@RequestMapping("/invest/product/productelement/findisclosuremodule/findisclosuremodule/FinDisclosureModule001")
public class FinDisclosureModule001Controller extends BaseController{
    /**
     * 定义service
     */
    @Autowired
    private FinDisclosureModuleService finDisclosureModuleService;
    /**
     * 下拉框初始化
     */
    @Override
    public String init(Model model){
    	//modify by wm 2019-05-15 数字字典整合修改 start
  //      setSelectOptionToModel(model, DictCode.文件类型);
    	setSelectOptionToModel(model, DictCode.文件类型_投管);
      //modify by wm 2019-05-15 数字字典整合修改 end
        return super.init(model);
    }
    
    /**
     * 查询费用名称
     * 
     * @return list結果集
     * @throws Exception 異常
     */
    @ResponseBody
    @RequestMapping("/getFileName")
    public List<SuggestDto> getFileNameOption()
        throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        // 获取前台输入的值
        param.put("fileName", param.get("param"));
        param.put("fileType", param.get("fileType"));
        return finDisclosureModuleService.getFileNameOption(param);// 返回文件名
    }
    
    /**
     * 画面查詢方法
     * @return 所有數據
     * @throws Exception 異常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinDisclosureModuleDto> selectAll()
        throws Exception {
        Page<FinDisclosureModuleDto> page = finDisclosureModuleService.selectForPage(
            RequestUtil.getPageParam());
        return page;
    }
    
    /**
     * 删除按钮点击时的check
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
        ValidateGroupDelete.class}) FinDisclosureModuleDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     *  頁面刪除
     * @param dto 費用品種
     * @param br 結果
     * @return 刪除結果
     * @throws Exception 異常
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) FinDisclosureModuleDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FinDisclosureModuleDto> processParameter = new ProcessParameter<FinDisclosureModuleDto>();
        WorkFlowParameter<FinDisclosureModuleDto> params = new WorkFlowParameter<FinDisclosureModuleDto>();
        params.setPageId("FinDisclosureModule002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getRevealFileId());
        params.setBusinessType(DictCodeList.共通业务类型_信息披露模板管理.getValue());
        processParameter.setOperationType(OperationType.DELETE);
        // 设置流程业务说明, entity即传入的dto
        // 业务说明：文档类型 + "(" + 文档类型 + ")" + "-" + "文件名" + "-" + "文件名"
        params.setComment(entity -> WorkFlowUtil.concatComment(
            "文档类型" + GlobalConstant.OPEN_PARENTHESIS
            + entity.getFileTypeStr() + GlobalConstant.CLOSE_PARENTHESIS,
            "文件名" + GlobalConstant.OPEN_PARENTHESIS + entity.getFileName() + GlobalConstant.CLOSE_PARENTHESIS));
        processParameter.setWorkFlowParameter(params);
        String msg = finDisclosureModuleService.start(processParameter,
            dto.getSelectedItem().toArray(new FinDisclosureModuleDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }

    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return finDisclosureModuleService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
}
