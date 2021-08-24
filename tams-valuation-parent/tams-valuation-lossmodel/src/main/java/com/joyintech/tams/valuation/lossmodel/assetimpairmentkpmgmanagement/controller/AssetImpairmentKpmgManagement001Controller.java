package com.joyintech.tams.valuation.lossmodel.assetimpairmentkpmgmanagement.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
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
import com.joyintech.tams.valuation.lossmodel.assetimpairmentkpmgmanagement.dto.AssetImpairmentKpmgManagement001Dto;
import com.joyintech.tams.valuation.lossmodel.assetimpairmentkpmgmanagement.dto.AssetImpairmentKpmgManagement002Dto;
import com.joyintech.tams.valuation.lossmodel.assetimpairmentkpmgmanagement.service.AssetImpairmentKpmgManagement001Service;
import com.joyintech.tams.valuation.lossmodel.assetimpairmentkpmgmanagement.service.AssetImpairmentKpmgManagement002Service;
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
@PageId("AssetImpairmentKpmgManagement001")
@RequestMapping("/model/impairment/assetimpairmentkpmgmanagement/AssetImpairmentKpmgManagement001")
public class AssetImpairmentKpmgManagement001Controller extends BaseController {

    @Autowired
    private AssetImpairmentKpmgManagement001Service assetImpairmentKpmgManagement001Service;

    @Autowired
    private AssetImpairmentKpmgManagement002Service assetImpairmentKpmgManagement002Service;

    @Override
    public String init(Model model) {

        super.setSelectOptionToModel(model, DictCode.减值阶段);
        super.setSelectOptionToModel(model, DictCode.模型数据来源);
        super.setSelectOptionToModel(model, DictCode.减值数据来源);
        return super.init(model);
    }

    /**
     * 分页查询
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<AssetImpairmentKpmgManagement001Dto> findPage() throws Exception {
        Map<String, Object> params= RequestUtil.getPageParam();
        return  assetImpairmentKpmgManagement001Service.selectForPage(params);

    }

    /**
     * 获取减值结果表中所有业务编号
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getBusiNum")
    public Page<SelectOptionDto> getBusiNum() throws Exception {
        return  assetImpairmentKpmgManagement001Service.getBusiNum(RequestUtil.getPageParam());

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
        return assetImpairmentKpmgManagement001Service.getFinprodIdForImpairment(params);
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
        return assetImpairmentKpmgManagement001Service.getProdIdForImpairment(params);
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) AssetImpairmentKpmgManagement001Dto dto, BindingResult result) {
        if(dto.getOperateType() !=null ){
            String message ="";
            if("edit".equals(dto.getOperateType())){
                 message = "修改时,仅能修改减值日大于等于系统当前日期的数据!";
            }else if("delete".equals(dto.getOperateType())){
                 message = "删除时,仅能删除减值日大于等于系统当前日期的数据!";
            }
            if(DateUtil.compareDate(DateUtil.format(dto.getDecDate()),DateUtil.format(new Date()))==-1){
                return new AjaxResult(message, MessageType.错误消息);
            }
        }
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
            ValidateGroupDelete.class}) AssetImpairmentKpmgManagement002Dto dto, BindingResult br) {
        String msg = StringUtils.EMPTY;

        ProcessParameter<AssetImpairmentKpmgManagement002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<AssetImpairmentKpmgManagement002Dto> workFlowParameter = new WorkFlowParameter<>();

        try {
            dto = assetImpairmentKpmgManagement002Service.findByPrimaryKey(dto.getSeqNo());

            workFlowParameter.setPageId("AssetImpairmentManagement002");
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
            workFlowParameter.setBusinessCode(dto.getSeqNo());
            workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_资产减值管理.getValue());
            workFlowParameter.setComment(
                    entity -> WorkFlowUtil.concatComment("资产减值管理", entity.getSeqNo()));
            processParameter.setWorkFlowParameter(workFlowParameter);
            assetImpairmentKpmgManagement002Service.start(processParameter, dto);
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
        AssetImpairmentKpmgManagement001Dto dto = (AssetImpairmentKpmgManagement001Dto)JsonUtils.map2entity(paraMap, AssetImpairmentKpmgManagement001Dto.class);
        List<AssetImpairmentKpmgManagement001Dto> listdto;
        if (datas == null) {
            listdto = assetImpairmentKpmgManagement001Service.findPageout(paraMap);
        } else {
            listdto = JsonUtils.json2List(datas, AssetImpairmentKpmgManagement001Dto.class);
        }
        File outFile = assetImpairmentKpmgManagement001Service.makeFile(
                "/com/joyintech/tams/valuation/lossmodel/assetimpairmentkpmgmanagement/conf/export_assetimpairmentkpmgmanagement.xml",
                listdto);
        return ExportUtil.export(DateUtil.format(new Date()) + "-资产减值管理" + SystemGlobalConstant.POINT
                + FileNameUtil.getFileExtName(outFile), outFile, true);
    }

    /**
     * @Description:资产代码查询-下拉框-减值结果管理
     * @auther: yuzj
     * @date: 2021/8/23
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("getFinprodIdByLossResult")
    public List<SelectOptionDto> getFinprodIdByLossResult(@RequestParam Map<String, Object> params) throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        return assetImpairmentKpmgManagement001Service.getFinprodIdByLossResult(params);
    }

    /**
     * @Description:产品代码查询-减值结果管理
     * @auther: yuzj
     * @date: 2021/8/23
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("getProdIdByLossResult")
    public List<SelectOptionDto> getProdIdByLossResult(@RequestParam Map<String, Object> params) throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        return assetImpairmentKpmgManagement001Service.getProdIdByLossResult(params);
    }

    /**
     * @Description:有效业务编号查询-减值结果管理
     * @auther: yuzj
     * @date: 2021/8/23
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("getBusiNumByLossResult")
    public List<SelectOptionDto> getBusiNumByLossResult(@RequestParam Map<String, Object> params) throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        return assetImpairmentKpmgManagement001Service.getBusiNumByLossResult(params);
    }



}
