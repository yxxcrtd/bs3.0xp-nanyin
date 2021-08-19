/*
 * 文件名：ValAssetBook001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.lossmodel.assetratingallocation.assetcategories.controller;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.lossmodel.assetratingallocation.assetcategories.dto.AssetCategories001Dto;
import com.joyintech.tams.valuation.lossmodel.assetratingallocation.assetcategories.dto.AssetCategories002Dto;
import com.joyintech.tams.valuation.lossmodel.assetratingallocation.assetcategories.service.AssetCategories001Service;
import com.joyintech.tams.valuation.lossmodel.assetratingallocation.assetcategories.service.AssetCategories002Service;
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
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * 模板示例<br>
 * 账套管理
 * 
 * @author 黄健
 * @version 1.0 2018年5月10日
 * @see AssetCategories001Controller
 * @since 1.0
 */
@Controller
@PageId("AssetCategories001")
@RequestMapping("/model/impairment/assetratingallocation/assetcategories/AssetCategories001")
public class AssetCategories001Controller extends BaseController {

    /**
     * ValAssetBook001Service
     */
    @Autowired
    private AssetCategories001Service valAssetCategories001Service;

    /**
     * ValAssetBook001Service
     */
    @Autowired
    private AssetCategories002Service valAssetCategories002Service;

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.资产类型);
        this.setSelectOptionToModel(model, DictCode.资产分类);
        this.setSelectOptionToModel(model, DictCode.证券品种);



        return super.init(model);
    }

    /**
     * 删除check
     *
     * @param dto 行数据
     * @param result check结果
     * @return WebResult
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("doDeleteCheck")
    @ResponseBody
    public AjaxResult doDeleteCheck(@RequestBody @Valid AssetCategories001Dto dto,
                                    BindingResult result) throws Exception {
        AjaxResult wr = new AjaxResult();
        if (valAssetCategories001Service.checkBusinessDataInFlow(dto)) {
            return new AjaxResult("存在流程中数据，不能删除！", MessageType.错误消息);
        }
        wr.setData(this.getMessage("COMMON_C_0001", "删除"));
        return wr;
    }

    /**
     * 根据资产类别查询资产大类评级配置数据
     *
     * @return List<AssetCategories001Dto>
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("ValAssetCategories001search")
    @ResponseBody
    public Page<AssetCategories001Dto> search() throws Exception {
        return valAssetCategories001Service.getAllAssetCategoriesPage(RequestUtil.getParameters(getRequest()));
    }

    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
            ValidateGroupDelete.class}) AssetCategories002Dto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<AssetCategories002Dto> processParameter = new ProcessParameter<AssetCategories002Dto>();
        WorkFlowParameter<AssetCategories002Dto> params = new WorkFlowParameter<AssetCategories002Dto>();
        params.setPageId("AssetCategories002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getAssetType()+"_"+item.getAssetDetailType());
        params.setBusinessType(DictCodeListExt3.共通业务类型_资产大类评级配置.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment("资产类别"+
                    GlobalConstant.OPEN_PARENTHESIS+entity.getAssetType()+GlobalConstant.CLOSE_PARENTHESIS+
                    "资产分类"+GlobalConstant.OPEN_PARENTHESIS+
                    entity.getAssetDetailType()+GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = valAssetCategories002Service.start(processParameter,
                dto.getSelectedItem().toArray(
                        new AssetCategories002Dto[dto.getSelectedItem().size()]));
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
        List<AssetCategories001Dto> listdto = new ArrayList<>();
        if (datas == null) {
            listdto = valAssetCategories001Service.selectForExport(paraMap);
        } else {
            listdto = JsonUtils.json2List(datas, AssetCategories001Dto.class);
        }
        File outFile = valAssetCategories001Service.makeFile(
                "/com/joyintech/tams/valuation/lossmodel/assetratingallocation/assetcategories/conf/export_assetcategories.xml",
                listdto);
        return ExportUtil.export(
                this.getMessage(DateUtil.format(new Date())+"_资产大类评级配置")
                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }

    public Map<String, Object> getRequestMap() throws ParseException {
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        return paraMap;
    }



    @ResponseBody
    @RequestMapping("getAssetDetailType")
    public List<SelectOptionDto> getAssetDetailType() throws Exception {
        List<SelectOptionDto> linkSelectOption = new ArrayList<>();
        Map<String, Object> param = RequestUtil.getPageParam();
        String secType = (String)param.get("assetType");
        //估值方法根据投资目的联动
        if(StringUtil.isNotEmpty(secType)){
            if(DictCodeListExt1.证券品种_债券.getValue().equals(secType)){
                param.put("dict_code_child", DictCode.债券类型.getValue());
            }else if(DictCodeListExt1.证券品种_存款.getValue().equals(secType)){
                param.put("dict_code_child", DictCode.存款类资产类型_GZ.getValue());
            }else if(DictCodeListExt1.证券品种_回购.getValue().equals(secType)){
                param.put("dict_code_child", DictCode.回购标的类型.getValue());
            }else if(DictCodeListExt1.证券品种_其他债权类.getValue().equals(secType)){
                param.put("dict_code_child", DictCode.计划类资产类型.getValue());
            }
            param.put("dict_code_parent", DictCode.证券品种.getValue());
            param.put("item_code_parent", param.get("secType"));
            linkSelectOption =valAssetCategories001Service.getLinkSelectOption(param);
        }
        return linkSelectOption;
    }

}
