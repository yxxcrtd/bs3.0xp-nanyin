/*
 * 文件名：NetWorthMarketInfoController002.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年7月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstfeevarieties.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.prod.dto.FinProductFeeInfoDto;
import com.joyintech.tams.invest.basicdata.mstfeevarieties.dto.MstFeeVarietiesDto;
import com.joyintech.tams.invest.basicdata.mstfeevarieties.service.MstFeeVarietiesService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 新建费用品种信息
 *
 * @author huwei
 * @version 1.0 2018年7月24日
 * @see MstFeeVarieties002Controller
 * @since 1.0
 */
@Controller
@PageId("MstFeeVarieties002")
@RequestMapping("/invest/basicdata/mstfeevarieties/MstFeeVarieties002")
public class MstFeeVarieties002Controller extends BaseController {

    /**
     * 定义service
     */
    @Autowired
    private MstFeeVarietiesService mstFeeVarietiesService;

    /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId bussGetBussinessIdService;

    @Override
    public String init(Model model) {
        // 下拉框初始化
        this.setSelectOptionToModel(model, DictCode.费用类型, DictCode.关联范围,
            DictCode.收费类型, DictCode.计费基数_费用品种,DictCode.费用品种类型);
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {// 判断是否存在于工作流中
            MstFeeVarietiesDto dto = mstFeeVarietiesService.getBussinessEntityByWorkflowId(
                workflowId);
            model.addAttribute("pageModel", dto.getPageModel());
            model.addAttribute("entity", dto);
        }
        // 判断初始化时是否有费用代码
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(getParam("feeId"))) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("feeId", getParam("feeId"));
            MstFeeVarietiesDto dto = mstFeeVarietiesService.selectByFeeId(map);// 根据画面费用代码查询数据
            model.addAttribute("entity", dto);
            //如果是画面编辑
            if(StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT, getParam("pageModel")) && StringUtil.equals("edit", getParam("operateType"))){
             // 通过费用代码判断记录是否被使用
                if (mstFeeVarietiesService.isUseByFeeId(getParam("feeId")) != 0) {
                    model.addAttribute("IS_USE", DictCodeList.是否_是.getValue());
                }
            }
        }
        // 关联范围
        if (StringUtil.equals(getParam("applyType"), "prod")) {
            model.addAttribute("defalutApplyType", DictCodeList.关联范围_产品.getValue());
//            model.addAttribute("defalutChargeType", DictCodeList.收费类型_计提式费用.getValue());
            model.addAttribute("finprodMarketId", this.getParam("finprodMarketId"));
            model.addAttribute("feeList", this.getParam("feeList"));
            model.addAttribute("profitType", this.getParam("profitType"));
            //如果是收益型产品计费基数默认为存续本金
            if(StringUtil.equals(DictCodeList.收益类型_收益型.getValue(), this.getParam("profitType"))){
                model.addAttribute("defalutBillBase", DictCodeList.计费基数_费用品种_存续本金.getValue());
            }
            //产品要素新建 不能新建浮动管理费
            SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.费用品种类型, null);
            List<SelectOptionDto> options = selectDto.getOptions();
            List<SelectOptionDto> optionsNew = new ArrayList<SelectOptionDto>();
            for (SelectOptionDto dto : options) {
                if (!StringUtil.equals(DictCodeList.费用品种类型_浮动差额中收.getValue(), dto.getId())
                        && !StringUtil.equals(DictCodeList.费用品种类型_浮动管理费.getValue(), dto.getId())) {
                    optionsNew.add(dto);
                }
            }
            model.addAttribute(DictCode.费用品种类型.getValue(),
                JsonUtils.objectArray2Json(optionsNew.toArray()));
        }
        List<SelectOptionDto> listtype = new ArrayList<SelectOptionDto>();
        SelectOptionDto dto1 = new SelectOptionDto();
        dto1.setId(DictCodeList.计息基础_A_365.getValue());
        dto1.setText(DictCodeList.计息基础_A_365.getCodeName());
        SelectOptionDto dto2 = new SelectOptionDto();
        dto2.setId(DictCodeList.计息基础_A_360.getValue());
        dto2.setText(DictCodeList.计息基础_A_360.getCodeName());
        SelectOptionDto dto3 = new SelectOptionDto();
        dto3.setId(DictCodeListExt1.计息基础_A_A_D.getValue());
        dto3.setText(DictCodeListExt1.计息基础_A_A_D.getCodeName());
        listtype.add(dto1);
        listtype.add(dto2);
        listtype.add(dto3);
        model.addAttribute("BASIS", JsonUtils.objectArray2Json(listtype.toArray()));
        model.addAttribute("CHARGE_TYPE_01", DictCodeList.收费类型_计提式费用.getValue());
        model.addAttribute("IS_USE_Y", DictCodeList.是否_是.getValue());
        model.addAttribute("FEETYPE_04", DictCodeList.费用品种类型_浮动差额中收.getValue());
        model.addAttribute("ORGTYPE_01", DictCodeListExt2.费用类型_本机构费用.getValue());
        model.addAttribute("FEENAME_04", DictCodeList.费用品种类型_浮动差额中收.getCodeName());

        model.addAttribute("pageModelBak", getParam(GlobalConstant.DETAIL_PAGE_MODEL_KEY));
        return super.init(model);
    }

    /**
     * 根据机构费用类型获得机构费用二级类型的下拉框 <功能详细描述>
     *
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getOrgType2")
    public List<SelectOptionDto> getOrgType2() {
        Map<String, Object> param = RequestUtil.getPageParam();
        String orgType = (String)param.get("orgType");
        List<SelectOptionDto> listtype = new ArrayList<SelectOptionDto>();
        if (orgType.equals(DictCodeListExt2.费用类型_本机构费用.getValue())) {
            SelectOptionDto dto1 = new SelectOptionDto();
            dto1.setId(DictCodeListExt2.费用二级类型_本机构托管费.getValue());
            dto1.setText(DictCodeListExt2.费用二级类型_本机构托管费.getCodeName());
            SelectOptionDto dto2 = new SelectOptionDto();
            dto2.setId(DictCodeListExt2.费用二级类型_本机构管理费.getValue());
            dto2.setText(DictCodeListExt2.费用二级类型_本机构管理费.getCodeName());
            SelectOptionDto dto3 = new SelectOptionDto();
            dto3.setId(DictCodeListExt2.费用二级类型_本机构销售手续费.getValue());
            dto3.setText(DictCodeListExt2.费用二级类型_本机构销售手续费.getCodeName());
            SelectOptionDto dto4 = new SelectOptionDto();
            dto4.setId(DictCodeListExt2.费用二级类型_本机构其他产品费用.getValue());
            dto4.setText(DictCodeListExt2.费用二级类型_本机构其他产品费用.getCodeName());
            listtype.add(dto1);
            listtype.add(dto2);
            listtype.add(dto3);
            listtype.add(dto4);
        }
        else if (orgType.equals(DictCodeListExt2.费用类型_其他机构费用.getValue())) {
            SelectOptionDto dto1 = new SelectOptionDto();
            dto1.setId(DictCodeListExt2.费用二级类型_其他机构托管费.getValue());
            dto1.setText(DictCodeListExt2.费用二级类型_其他机构托管费.getCodeName());
            SelectOptionDto dto2 = new SelectOptionDto();
            dto2.setId(DictCodeListExt2.费用二级类型_其他机构管理费.getValue());
            dto2.setText(DictCodeListExt2.费用二级类型_其他机构管理费.getCodeName());
            SelectOptionDto dto3 = new SelectOptionDto();
            dto3.setId(DictCodeListExt2.费用二级类型_其他机构销售手续费.getValue());
            dto3.setText(DictCodeListExt2.费用二级类型_其他机构销售手续费.getCodeName());
            SelectOptionDto dto4 = new SelectOptionDto();
            dto4.setId(DictCodeListExt2.费用二级类型_投资顾问费用.getValue());
            dto4.setText(DictCodeListExt2.费用二级类型_投资顾问费用.getCodeName());
            SelectOptionDto dto5 = new SelectOptionDto();
            dto5.setId(DictCodeListExt2.费用二级类型_其他机构其他产品费用.getValue());
            dto5.setText(DictCodeListExt2.费用二级类型_其他机构其他产品费用.getCodeName());
            listtype.add(dto1);
            listtype.add(dto2);
            listtype.add(dto3);
            listtype.add(dto4);
            listtype.add(dto5);
        }
        return listtype;
    }

    // =========2019年9月4日11:36:20 魏鹏 BUG号：YUCHUBS-535--程 娟娟，添加联动 start=========
    /**
     * 根据关联范围获得费用类型的下拉框 <功能详细描述>
     *
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getFeeType")
    public List<SelectOptionDto> getFeeType() {
        Map<String, Object> param = RequestUtil.getPageParam();
        String applyType = (String)param.get("applyType");
        String defalutApplyType = (String)param.get("defalutApplyType");
        List<SelectOptionDto> listtype = new ArrayList<SelectOptionDto>();
        if (applyType.equals(DictCodeList.关联范围_产品.getValue())) {
            SelectOptionDto dto1 = new SelectOptionDto();
            dto1.setId(DictCodeList.费用品种类型_托管费.getValue());
            dto1.setText(DictCodeList.费用品种类型_托管费.getCodeName());
            SelectOptionDto dto2 = new SelectOptionDto();
            dto2.setId(DictCodeList.费用品种类型_销售手续费.getValue());
            dto2.setText(DictCodeList.费用品种类型_销售手续费.getCodeName());
            SelectOptionDto dto3 = new SelectOptionDto();
            dto3.setId(DictCodeList.费用品种类型_固定管理费.getValue());
            dto3.setText(DictCodeList.费用品种类型_固定管理费.getCodeName());
            SelectOptionDto dto4 = new SelectOptionDto();
            dto4.setId(DictCodeList.费用品种类型_浮动管理费.getValue());
            dto4.setText(DictCodeList.费用品种类型_浮动管理费.getCodeName());
            SelectOptionDto dto5 = new SelectOptionDto();
            dto5.setId(DictCodeList.费用品种类型_浮动差额中收.getValue());
            dto5.setText(DictCodeList.费用品种类型_浮动差额中收.getCodeName());
            SelectOptionDto dto6 = new SelectOptionDto();
            dto6.setId(DictCodeList.费用品种类型_其他费用.getValue());
            dto6.setText(DictCodeList.费用品种类型_其他费用.getCodeName());
            SelectOptionDto dto7 = new SelectOptionDto();
            dto7.setId(DictCodeList.费用品种类型_代销费.getValue());
            dto7.setText(DictCodeList.费用品种类型_代销费.getCodeName());
            SelectOptionDto dto8 = new SelectOptionDto();
            dto8.setId(DictCodeList.费用品种类型_外包服务费.getValue());
            dto8.setText(DictCodeList.费用品种类型_外包服务费.getCodeName());
            
            SelectOptionDto dto9 = new SelectOptionDto();
            dto9.setId(DictCodeListExt2.费用品种类型_投资顾问费.getValue());
            dto9.setText(DictCodeListExt2.费用品种类型_投资顾问费.getCodeName());
            listtype.add(dto1);
            listtype.add(dto2);
            listtype.add(dto3);
            listtype.add(dto7);
            listtype.add(dto8);
            listtype.add(dto9);
            if (StringUtil.isEmpty(defalutApplyType) || !StringUtil.equals(defalutApplyType, DictCodeList.关联范围_产品.getValue())){
            	listtype.add(dto4);
            	listtype.add(dto5);
            	listtype.add(dto6);
            }
        }
        else if (applyType.equals(DictCodeList.关联范围_通道.getValue())) {
            SelectOptionDto dto1 = new SelectOptionDto();
            dto1.setId(DictCodeList.费用品种类型_通道托管费.getValue());
            dto1.setText(DictCodeList.费用品种类型_通道托管费.getCodeName());
            SelectOptionDto dto2 = new SelectOptionDto();
            dto2.setId(DictCodeList.费用品种类型_通道管理费.getValue());
            dto2.setText(DictCodeList.费用品种类型_通道管理费.getCodeName());
            SelectOptionDto dto3 = new SelectOptionDto();
            dto3.setId(DictCodeList.费用品种类型_其他费用.getValue());
            dto3.setText(DictCodeList.费用品种类型_其他费用.getCodeName());
            listtype.add(dto1);
            listtype.add(dto2);
            listtype.add(dto3);
        }
        else {
            SelectOptionDto dto1 = new SelectOptionDto();
            dto1.setId(DictCodeList.费用品种类型_交易手续费.getValue());
            dto1.setText(DictCodeList.费用品种类型_交易手续费.getCodeName());
            SelectOptionDto dto2 = new SelectOptionDto();
            dto2.setId(DictCodeList.费用品种类型_其他费用.getValue());
            dto2.setText(DictCodeList.费用品种类型_其他费用.getCodeName());
            SelectOptionDto dto3 = new SelectOptionDto();
            dto3.setId(DictCodeList.费用品种类型_结算服务费.getValue());
            dto3.setText(DictCodeList.费用品种类型_结算服务费.getCodeName());
            SelectOptionDto dto4 = new SelectOptionDto();
            dto4.setId(DictCodeList.费用品种类型_账户维护费.getValue());
            dto4.setText(DictCodeList.费用品种类型_账户维护费.getCodeName());
            SelectOptionDto dto5 = new SelectOptionDto();
            dto5.setId(DictCodeList.费用品种类型_增值附加税费.getValue());
            dto5.setText(DictCodeList.费用品种类型_增值附加税费.getCodeName());

            listtype.add(dto1);
            listtype.add(dto3);
            listtype.add(dto4);
            listtype.add(dto5);
            listtype.add(dto2);
        }
        return listtype;
    }
    // =========2019年9月4日11:36:20 魏鹏 BUG号：YUCHUBS-535--程 娟娟，添加联动 end=========

    /**
     * 画面点击保存按钮时check
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
        ValidateGroupEdit.class}) MstFeeVarietiesDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存
     *
     * @param dto 实体类
     * @param br 结果
     * @return 结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) MstFeeVarietiesDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if (StringUtil.isEmpty(dto.getFeeId())) {
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessType(DictCodeList.共通业务类型_费用品种管理);
            String code = bussGetBussinessIdService != null ? bussGetBussinessIdService.buildBussinessCode(
                param) : IDGenerator.generate();
            dto.setFeeId(code);
        }
        ProcessParameter<MstFeeVarietiesDto> processParameter = new ProcessParameter<MstFeeVarietiesDto>();
        WorkFlowParameter<MstFeeVarietiesDto> params = new WorkFlowParameter<MstFeeVarietiesDto>();
        params.setPageId("MstFeeVarieties002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getFeeId());
        params.setBusinessType(DictCodeList.共通业务类型_费用品种管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        // 业务说明：费用品种代码 + "(" + 费用品种代码 + ")" + "-" + "费用品种名称" + "-" + "费用品种名称"
        params.setComment(entity -> "费用品种代码" + "(" + entity.getFeeId() + ")" + "-" + "费用品种名称" + "("
                                    + entity.getFeeName() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = mstFeeVarietiesService.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    /**
     * 产品端新建临时费用信息
     *
     * @param dto 费用信息
     * @return 费用代码
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("finProductFeeSave")
    public AjaxResult finProductFeeSave(@RequestBody MstFeeVarietiesDto dto) {
        AjaxResult ar = new AjaxResult();
        // 生成费用代码
        BussinessCodeParameter param = new BussinessCodeParameter();
        param.setCommonBusinessType(DictCodeList.共通业务类型_费用品种管理);
        String feeId = bussGetBussinessIdService != null ? bussGetBussinessIdService.buildBussinessCode(
            param) : IDGenerator.generate();
        ar.setData(feeId);

        if (StringUtil.isNotEmpty(dto.getFinprodMarketId())) {
            // 设置缓存信息
            FinProductFeeInfoDto feeDto = new FinProductFeeInfoDto();
            feeDto.setFeeId(feeId);
            feeDto.setFeeName(dto.getFeeName());
            feeDto.setFeeType(dto.getFeeType());
            feeDto.setBasicBill(dto.getBasicBill());
            feeDto.setBasis(dto.getBasis());
            feeDto.setChargeType(dto.getChargeType());
            feeDto.setOrgType(dto.getOrgType());
            feeDto.setOrgType2(dto.getOrgType2());
            feeDto.setRemark(dto.getRemark());

            FinProductFeeInfoDto cacheDto = CacheUtil.getAppCache(dto.getFinprodMarketId(),
                FinProductFeeInfoDto.class);

            if (cacheDto != null && !StringUtil.isEmptyList(cacheDto.getSelectedItem())) {
                List<FinProductFeeInfoDto> selectedItem = cacheDto.getSelectedItem();
                selectedItem.add(feeDto);
                CacheUtil.putAppCache(dto.getFinprodMarketId(), cacheDto);
            }
            else {
                cacheDto = new FinProductFeeInfoDto();
                List<FinProductFeeInfoDto> selectedItem = new ArrayList<FinProductFeeInfoDto>();
                selectedItem.add(feeDto);
                cacheDto.setSelectedItem(selectedItem);
                CacheUtil.putAppCache(dto.getFinprodMarketId(), cacheDto);
            }
        }

        return ar;
    }
}
