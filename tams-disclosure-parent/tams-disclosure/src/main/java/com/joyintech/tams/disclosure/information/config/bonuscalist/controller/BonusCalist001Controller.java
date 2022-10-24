package com.joyintech.tams.disclosure.information.config.bonuscalist.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.bonuscalist.dto.BonusCalist001Dto;
import com.joyintech.tams.disclosure.information.config.bonuscalist.service.BonusCalist001Service;
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

@Controller
@PageId("BonusCalist001")
@RequestMapping("/disclosure/information/config/bonuscalist/BonusCalist001")
public class BonusCalist001Controller extends BaseController {

    @Autowired
    private BonusCalist001Service bonusCalist001Service;

    @Override
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.维护数据状态);
        return super.init(model);
    }


    @ResponseBody
    @RequestMapping("findPage")
    public Page<BonusCalist001Dto> selectAll(){
        Page<BonusCalist001Dto> page = bonusCalist001Service.selectForPage(
                RequestUtil.getParameters(getRequest()));
        return page;
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupDelete.class}) BonusCalist001Dto dto, BindingResult result) {
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
    public AjaxResult delete(@RequestBody @Validated({ValidateGroupDelete.class}) BonusCalist001Dto dto, BindingResult br) throws WorkFlowException {
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<BonusCalist001Dto> processParameter = new ProcessParameter<BonusCalist001Dto>();
        WorkFlowParameter<BonusCalist001Dto> params = new WorkFlowParameter<BonusCalist001Dto>();
        params.setPageId("BonusCalist002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getProdId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_产品分红计算清单维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> "子产品代码"+ "(" + entity.getProdId()+ ")"
                        +  "-" + "收益分配基准日"+ "(" + entity.getProfitBaseDateStr() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = bonusCalist001Service.start(processParameter,
                dto.getSelectedItem().toArray(new BonusCalist001Dto[dto.getSelectedItem().size()]));
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
        return bonusCalist001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }



    @ResponseBody
    @RequestMapping("checkImportData")
    public AjaxResult checkImportData(String fileName) {
        return bonusCalist001Service.checkImportData(fileName);
    }


    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importFile(String fileName)  {
        return bonusCalist001Service.importData(fileName);
    }

    @ResponseBody
    @RequestMapping("changeStatus")
    public int changeStatus(@RequestBody BonusCalist001Dto[] list){
        for (BonusCalist001Dto bonusCalist001Dto : list){
            //将 状态 改为 有效
            bonusCalist001Dto.setDataStatus("E");
            bonusCalist001Service.updateStatus(bonusCalist001Dto);
        }
        //有返回值才会执行ajax的回调函数 所以此方法需定义一个返回值类型
        return 1;
    }
}
