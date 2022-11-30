package com.joyintech.tams.disclosure.information.config.assetinvestmentdetail.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.assetinvestmentdetail.dto.AssetInvestmentDetail001Dto;
import com.joyintech.tams.disclosure.information.config.assetinvestmentdetail.service.AssetInvestmentDetail001Service;
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


@PageId("AssetInvestmentDetail001")
@Controller
@RequestMapping("/disclosure/information/config/assetinvestmentdetail/AssetInvestmentDetail001")
public class AssetInvestmentDetail001Controller extends BaseController {


    @Autowired
    private AssetInvestmentDetail001Service assetInvestmentDetail001Service;

    @Override
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.维护数据状态);
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("findPage")
    public Page<AssetInvestmentDetail001Dto> selectAll(){
        Page<AssetInvestmentDetail001Dto> page = assetInvestmentDetail001Service.selectForPage(
                RequestUtil.getParameters(getRequest()));
        return page;
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
    public AjaxResult delete(@RequestBody @Validated({ValidateGroupDelete.class}) AssetInvestmentDetail001Dto dto, BindingResult br) throws WorkFlowException {
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<AssetInvestmentDetail001Dto> processParameter = new ProcessParameter<AssetInvestmentDetail001Dto>();
        WorkFlowParameter<AssetInvestmentDetail001Dto> params = new WorkFlowParameter<AssetInvestmentDetail001Dto>();
        params.setPageId("AssetInvestmentDetail002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getProdId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_科目余额信息维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> "产品主代码"+ "(" + entity.getProdId()+ ")"
                        +  "-" + "资产代码"+ "(" + entity.getAssetCode() + ")"
                        +  "-" + "日期"+ "(" + entity.getPubDateStr() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = assetInvestmentDetail001Service.start(processParameter,
                dto.getSelectedItem().toArray(new AssetInvestmentDetail001Dto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) AssetInvestmentDetail001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
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
        return assetInvestmentDetail001Service.export(this.getPageId(),
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
        return assetInvestmentDetail001Service.checkImportData(fileName);
    }


   /* @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importFile(String fileName)  {
        return assetInvestmentDetail001Service.importData(fileName);
    }*/

}
