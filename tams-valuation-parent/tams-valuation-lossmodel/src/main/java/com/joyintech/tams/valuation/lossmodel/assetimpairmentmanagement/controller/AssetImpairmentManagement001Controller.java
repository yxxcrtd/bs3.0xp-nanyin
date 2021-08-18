package com.joyintech.tams.valuation.lossmodel.assetimpairmentmanagement.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.lossmodel.assetimpairmentmanagement.dto.AssetImpairmentManagement001Dto;
import com.joyintech.tams.valuation.lossmodel.assetimpairmentmanagement.dto.AssetImpairmentManagement002Dto;
import com.joyintech.tams.valuation.lossmodel.assetimpairmentmanagement.service.AssetImpairmentManagement001Service;
import com.joyintech.tams.valuation.lossmodel.assetimpairmentmanagement.service.AssetImpairmentManagement002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 资产减值管理
 * @author xutong
 * @createTime 2021-06-24
 */
@Controller
@PageId("AssetImpairmentManagement001")
@RequestMapping("/model/impairment/assetimpairmentmanagement/AssetImpairmentManagement001")
public class AssetImpairmentManagement001Controller extends BaseController {

    @Autowired
    private AssetImpairmentManagement001Service assetImpairmentManagement001Service;

    @Autowired
    private AssetImpairmentManagement002Service assetImpairmentManagement002Service;

    @Override
    public String init(Model model) {

        super.setSelectOptionToModel(model, DictCode.减值阶段);
        super.setSelectOptionToModel(model, DictCode.模型数据来源);
        return super.init(model);
    }

    /**
     * 分页查询
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<AssetImpairmentManagement001Dto> findPage() throws Exception {
        Map<String, Object> params= RequestUtil.getPageParam();
        return  assetImpairmentManagement001Service.selectForPage(params);

    }

    /**
     * 获取减值结果表中所有业务编号
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getBusiNum")
    public Page<SelectOptionDto> getBusiNum() throws Exception {
        return  assetImpairmentManagement001Service.getBusiNum(RequestUtil.getPageParam());

    }

    /**
     * 资产代码：下拉框 资产减值管理
     *
     * @param
     * @return 业务编号：下拉框
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getFinprodIdForImpairment")
    public List<SelectOptionDto> getFinprodIdForImpairment(@RequestParam Map<String, Object> params) throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        return assetImpairmentManagement001Service.getFinprodIdForImpairment(params);
    }

    /**
     * 产品代码：下拉框 资产减值管理
     *
     * @param
     * @return 业务编号：下拉框
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getProdIdForImpairment")
    public List<SelectOptionDto> getProdIdForImpairment(@RequestParam Map<String, Object> params) throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        return assetImpairmentManagement001Service.getProdIdForImpairment(params);
    }

    /**
     *
     * 保存时，check操作
     * <功能详细描述>
     * @param dto MstSharePriceDto
     * @param result result
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) AssetImpairmentManagement001Dto dto, BindingResult result) {
        AjaxResult ar = commonCheck(result, dto.getOperateType());
        return ar;
    }

    /**
     * 删除资产减值管理
     *
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public AjaxResult delete(@RequestBody @Validated({
            ValidateGroupDelete.class}) AssetImpairmentManagement002Dto dto, BindingResult br) {
        String msg = StringUtils.EMPTY;

        ProcessParameter<AssetImpairmentManagement002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<AssetImpairmentManagement002Dto> workFlowParameter = new WorkFlowParameter<>();

        try {
            dto = assetImpairmentManagement002Service.findByPrimaryKey(dto.getSeqNo());

            workFlowParameter.setPageId("AssetImpairmentManagement002");
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
            workFlowParameter.setBusinessCode(dto.getSeqNo());
            workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_资产减值管理.getValue());
            workFlowParameter.setComment(
                    entity -> WorkFlowUtil.concatComment("资产减值管理", entity.getSeqNo()));
            processParameter.setWorkFlowParameter(workFlowParameter);
            assetImpairmentManagement002Service.start(processParameter, dto);
            msg = getMessage("COMMON_I_0005");
        }
        catch (Exception e) {
            this.getLogger().error("启动资产减值管理流程失败：", e);
            msg = e.getMessage();
        }
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
        Map<String, Object> paraMap= RequestUtil.getPageParam();
        AssetImpairmentManagement001Dto dto = (AssetImpairmentManagement001Dto)JsonUtils.map2entity(paraMap, AssetImpairmentManagement001Dto.class);
        List<AssetImpairmentManagement001Dto> listdto;
        if (datas == null) {
            listdto = assetImpairmentManagement001Service.findPageout(paraMap);
        } else {
            listdto = JsonUtils.json2List(datas, AssetImpairmentManagement001Dto.class);
        }
        File outFile = assetImpairmentManagement001Service.makeFile(
                "/com/joyintech/tams/valuation/lossmodel/assetimpairmentmanagement/conf/export_assetimpairmentmanagement.xml",
                listdto);
        return ExportUtil.export(DateUtil.format(new Date()) + "-资产减值管理" + SystemGlobalConstant.POINT
                + FileNameUtil.getFileExtName(outFile), outFile, true);
    }

}
