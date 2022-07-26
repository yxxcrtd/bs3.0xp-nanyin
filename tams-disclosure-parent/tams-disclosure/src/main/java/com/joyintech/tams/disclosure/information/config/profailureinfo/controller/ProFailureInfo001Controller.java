package com.joyintech.tams.disclosure.information.config.profailureinfo.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.proelementadj.dto.ProElementAdj001Dto;
import com.joyintech.tams.disclosure.information.config.profailureinfo.dto.ProFailureInfo001Dto;
import com.joyintech.tams.disclosure.information.config.profailureinfo.service.ProFailureInfo001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@PageId("ProFailureInfo001")
@Controller
@RequestMapping("/disclosure/information/config/profailureinfo/ProFailureInfo001")
public class ProFailureInfo001Controller extends BaseController {

   @Autowired
   private ProFailureInfo001Service proFailureInfo001Service;

    @Override
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.是否);
        this.setSelectOptionToModel(model, DictCode.信披_数据状态);
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("findPage")
    public Page<ProFailureInfo001Dto> selectAll(){
        Page<ProFailureInfo001Dto> page = proFailureInfo001Service.selectForPage(
                RequestUtil.getParameters(getRequest()));
        return page;
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupDelete.class}) ProFailureInfo001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 行删除与删除按钮
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto 业务实体
     * @param br br
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({ValidateGroupDelete.class}) ProFailureInfo001Dto dto, BindingResult br) throws WorkFlowException {
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<ProFailureInfo001Dto> processParameter = new ProcessParameter<ProFailureInfo001Dto>();
        WorkFlowParameter<ProFailureInfo001Dto> params = new WorkFlowParameter<ProFailureInfo001Dto>();
        params.setPageId("ProFailureInfo002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getProdRegistNo());
        params.setBusinessType(DictCodeListExt3.共通业务类型_产品不成立信息维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> "产品登记编码"+ "(" + entity.getProdRegistNo()+ ")"
                        +  "-" + "子产品代码"+ "(" + entity.getProdId() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = proFailureInfo001Service.start(processParameter,
                dto.getSelectedItem().toArray(new ProFailureInfo001Dto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }


    /**
     * 导出
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export(){
        return proFailureInfo001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }

    /**
     * 导入
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("checkImportData")
    public AjaxResult checkImportData(String fileName) {
        return proFailureInfo001Service.checkImportData(fileName);
    }


    @ResponseBody
    @RequestMapping("importFile")
    public AjaxResult importFile(@RequestBody Map<String, Object> param)  {
        return proFailureInfo001Service.importFile(param);
    }


    @ResponseBody
    @RequestMapping("getRegistNoList")
    public Page<SelectOptionDto> getRegistNoList()
            throws Exception {
        Page<SelectOptionDto> temp = proFailureInfo001Service.getRegistNoList(RequestUtil.getPageParam());
        return temp;
    }


    @ResponseBody
    @RequestMapping("getProdIdlist")
    public Page<SelectOptionDto> getProdIdlist()
            throws Exception {
        Page<SelectOptionDto> temp = proFailureInfo001Service.getProdIdlist(RequestUtil.getPageParam());
        return temp;
    }


    @ResponseBody
    @RequestMapping("getRePord")
    public List<SelectOptionDto> getRePord() {
        return proFailureInfo001Service.getRePord(RequestUtil.getPageParam());
    }
}
