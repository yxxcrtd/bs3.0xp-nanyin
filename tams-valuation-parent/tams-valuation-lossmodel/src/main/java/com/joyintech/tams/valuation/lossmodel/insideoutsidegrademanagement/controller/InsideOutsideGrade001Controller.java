
package com.joyintech.tams.valuation.lossmodel.insideoutsidegrademanagement.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.lossmodel.insideoutsidegrademanagement.dto.InsideOutsideGrade001Dto;
import com.joyintech.tams.valuation.lossmodel.insideoutsidegrademanagement.service.InsideOutsideGrade001Service;
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
import com.joyintech.framework.grid.Page;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * 内外部评级映射管理
 * 
 * @author 刘华明
 * @version 1.0 2021年7月5日
 * @see InsideOutsideGrade001Controller
 * @since 1.0
 */
@Controller
@PageId("InsideOutsideGrade001")
@RequestMapping("/model/impairment/insideoutsidegrademanagement/InsideOutsideGrade001")
public class InsideOutsideGrade001Controller extends BaseController {

    /**
     * InsideOutsideGrade001Service
     */
    @Autowired
    private InsideOutsideGrade001Service insideOutsideGrade001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.评级机构);
        this.setSelectOptionToModel(model, DictCode.评级类型);
        return super.init(model);
    }

    /**
     * 根据评级机构和对象类型查询
     *
     * @return Page<InsideOutsideGrade001Dto>
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("ValInsideOutsideGrade001search")
    @ResponseBody
    public Page<InsideOutsideGrade001Dto> search() throws Exception {
        return insideOutsideGrade001Service.getAllInsideOutsideGradePage(RequestUtil.getParameters(getRequest()));
    }

    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
            ValidateGroupDelete.class}) InsideOutsideGrade001Dto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<InsideOutsideGrade001Dto> processParameter = new ProcessParameter<InsideOutsideGrade001Dto>();
        WorkFlowParameter<InsideOutsideGrade001Dto> params = new WorkFlowParameter<InsideOutsideGrade001Dto>();
        params.setPageId("InsideOutsideGrade002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getGradeInst()+"_"+item.getoType());
        params.setBusinessType(DictCodeListExt3.共通业务类型_内外部评级映射管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment("评级机构"+
                    GlobalConstant.OPEN_PARENTHESIS+SysDataUtil.getNameFromDataDict(DictCode.评级机构, entity.getGradeInst())+GlobalConstant.CLOSE_PARENTHESIS+
                    "对象类型"+GlobalConstant.OPEN_PARENTHESIS+
                    SysDataUtil.getNameFromDataDict(DictCode.评级类型, entity.getoType())+GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = insideOutsideGrade001Service.start(processParameter,
                dto.getSelectedItem().toArray(
                        new InsideOutsideGrade001Dto[dto.getSelectedItem().size()]));
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
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        List<InsideOutsideGrade001Dto> listdto = new ArrayList<>();
        if (datas == null) {
            listdto = insideOutsideGrade001Service.selectForExport(paraMap);
        } else {
            listdto = JsonUtils.json2List(datas, InsideOutsideGrade001Dto.class);
        }
        File outFile = insideOutsideGrade001Service.makeFile(
                "/com/joyintech/tams/valuation/lossmodel/insideoutsidegrademanagement/conf/export_insideoutsidegrade.xml",
                listdto);
        return ExportUtil.export(
                this.getMessage(DateUtil.format(new Date())+"_内外部评级映射管理")
                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }
}
