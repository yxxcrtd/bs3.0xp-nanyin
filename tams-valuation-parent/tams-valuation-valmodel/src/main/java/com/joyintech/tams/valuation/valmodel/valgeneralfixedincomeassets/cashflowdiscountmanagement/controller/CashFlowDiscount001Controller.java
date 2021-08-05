
package com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.dto.CashFlowDiscount001Dto;
import com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.service.CashFlowDiscount001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.File;
import java.text.ParseException;
import java.util.*;

/**
 *
 * 现金流折现模型曲线管理
 * 
 * @author 刘华明
 * @version 1.0 2021年7月1日
 * @see CashFlowDiscount001Controller
 * @since 1.0
 */
@Controller
@PageId("CashFlowDiscount001")
@RequestMapping("/valmodel/valgeneralfixedincomeassets/cashflowdiscountmanagement/CashFlowDiscount001")
public class CashFlowDiscount001Controller extends BaseController {

    /**
     * CashFlowDiscount001Service
     */
    @Autowired
    private CashFlowDiscount001Service cashFlowDiscount001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.资产类型);
        this.setSelectOptionToModel(model, DictCode.资产分类);
        return super.init(model);
    }

    /**
     * 根据资产类别查询资产大类评级配置数据
     *
     * @return List<AssetCategories001Dto>
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("ValCashFlowDiscount001search")
    @ResponseBody
    public Page<CashFlowDiscount001Dto> search() throws Exception {
        return cashFlowDiscount001Service.getAllCashFlowDiscountPage(RequestUtil.getParameters(getRequest()));
    }

    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
            ValidateGroupDelete.class}) CashFlowDiscount001Dto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<CashFlowDiscount001Dto> processParameter = new ProcessParameter<CashFlowDiscount001Dto>();
        WorkFlowParameter<CashFlowDiscount001Dto> params = new WorkFlowParameter<CashFlowDiscount001Dto>();
        params.setPageId("CashFlowDiscount002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getSecTypeModel()+"_"+item.getSecDetailTypeModel()+"_"+item.getFinprodId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_现金流折现模型曲线配置.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment("资产类型"+
                    GlobalConstant.OPEN_PARENTHESIS+entity.getSecTypeModel()+GlobalConstant.CLOSE_PARENTHESIS+
                    "资产细分类"+GlobalConstant.OPEN_PARENTHESIS+
                    entity.getSecDetailTypeModel()+GlobalConstant.CLOSE_PARENTHESIS+
                    "资产代码"+GlobalConstant.OPEN_PARENTHESIS+
                    entity.getFinprodId()+GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = cashFlowDiscount001Service.start(processParameter,
                dto.getSelectedItem().toArray(
                        new CashFlowDiscount001Dto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
        String datas = this.getParam("datas");
        Map<String, Object> paraMap = this.getRequestMap();
        List<CashFlowDiscount001Dto> listdto = new ArrayList<>();
        if (datas == null) {
            listdto = cashFlowDiscount001Service.selectForExport(paraMap);
        } else {
            listdto = JsonUtils.json2List(datas, CashFlowDiscount001Dto.class);
        }
        File outFile = cashFlowDiscount001Service.makeFile(
                "/com/joyintech/tams/valmodel/valgeneralfixedincomeassets/cashflowdiscountmanagement/conf/export_cashflowdiscount.xml",
                listdto);
        return ExportUtil.export(
                this.getMessage(DateUtil.format(new Date())+"_现金流折现模型曲线配置")
                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }

    public Map<String, Object> getRequestMap() throws ParseException {
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        return paraMap;
    }

    /**
     * 通过资产类型和资产细分类查询满足条件的资产代码和名称
     * @return
     */
    @RequestMapping("getFinprodIdForValuation")
    @ResponseBody
    public List<SuggestDto> getFinprodIdForValuation() {
        // 资产类型
        String secTypeModel = this.getParam("secTypeModel");
        // 资产细分类
        String secDetailTypeModel = this.getParam("secDetailTypeModel");
        String param = this.getParam("param");

        Map<String,Object> map = new HashMap<>();
        map.put("secTypeModel",secTypeModel);
        map.put("secDetailTypeModel",secDetailTypeModel);
        map.put("search",param);// 输入的值模糊查询

        List<SuggestDto> rstlist = cashFlowDiscount001Service.getFinprodIdForValuation(map);
        return rstlist;

    }

}
