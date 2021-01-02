/*
 * 文件名：MstBankTranFeeManage002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年3月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.mstbanktranfeemanage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

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
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.invest.basicdata.mstbanktranfeemanage.dto.MstBankTranFeeManageDto;
import com.joyintech.tams.invest.basicdata.mstbanktranfeemanage.service.MstBankTranFeeManageService;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 银行间交易费用新建
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2019年3月21日
 * @see MstBankTranFeeManage002Controller
 * @since 1.0
 */
@Controller
@PageId("MstBankTranFeeManage002")
@RequestMapping("/invest/basicdata/mstbanktranfeemanage/MstBankTranFeeManage002")
public class MstBankTranFeeManage002Controller extends BaseController {
    
    /**
     * mstBankTranFeeManageService
     */
    @Autowired
    private MstBankTranFeeManageService mstBankTranFeeManageService;
    /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId bussGetBussinessIdService;
    
    /**
     * 初始化的查询条件
     */
//    private static final IDictCode[] DICT_CODES = new BussinessDictCode[] {BussinessDictCode.费用类型,
//        BussinessDictCode.平安资产类型, BussinessDictCode.登记托管机构,BussinessDictCode.期限品种,
//        BussinessDictCode.计费类型,BussinessDictCode.计费基数
//    };
    /**
     * 入口
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        setSelectOptionToModel(model,new BussinessDictCode[] {BussinessDictCode.费用类型,
                BussinessDictCode.平安资产类型, BussinessDictCode.登记托管机构,BussinessDictCode.期限品种,
                BussinessDictCode.计费类型,BussinessDictCode.计费基数, BussinessDictCode.交易市场
        });
        List<SelectOptionDto> psList = makeAllPs();
        model.addAttribute("PS", JsonUtils.objectArray2Json(psList.toArray()));
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String batchSeq = getParam("batchSeq");
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {// 判断是否存在于工作流中
            MstBankTranFeeManageDto dto = mstBankTranFeeManageService.getBussinessEntityByWorkflowId(
                workflowId);
            model.addAttribute("pageModel", dto.getPageModel());
            model.addAttribute("entity", dto);
        }
        else if(StringUtil.isNotEmpty(batchSeq)){
            //编辑页面
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("batchSeq", batchSeq);
            MstBankTranFeeManageDto dto = mstBankTranFeeManageService.getEntity(map);// 根据流水号查询数据
            model.addAttribute("entity", dto);
        }
        if(StringUtil.isEmpty(batchSeq) && WorkFlowUtil.isNotFromToDoList()){
            MstBankTranFeeManageDto dto = new MstBankTranFeeManageDto();
            dto.setChargeType(BussinessDictCodeList.计费类型_固定金额.getValue());
            model.addAttribute("entity", dto);
        }

        model.addAttribute("PINAN_ASSET_TYPE_F01", BussinessDictCodeList.平安资产类型_债券.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F07", BussinessDictCodeList.平安资产类型_回购.getValue());
        model.addAttribute("PINAN_ASSET_TYPE_F10", BussinessDictCodeList.平安资产类型_买断式回购.getValue());
        model.addAttribute("CHARGE_KIND_01", BussinessDictCodeList.计费类型_固定费率.getValue());
        model.addAttribute("CHARGE_KIND_02", BussinessDictCodeList.计费类型_固定金额.getValue());
        model.addAttribute("FEE_TYPE_B06", DictCodeListExt1.一次性费用类型_交易手续费.getValue());
        return super.init(model);
    }
    /**
     * 生成交易方向的枚举值
     * <功能详细描述>
     * @return SelectOptionDto
     * @see
     * @since 1.0
     */
    private List<SelectOptionDto> makeAllPs() {
        List<SelectOptionDto> listFloatRate = new ArrayList<SelectOptionDto>();
        SelectDto selectDto1 = this.getSelectDataFromDataDic(BussinessDictCode.交易方向, null);
        for (SelectOptionDto dto : selectDto1.getOptions()) {
            listFloatRate.add(dto);
        }
        SelectDto selectDto2 = this.getSelectDataFromDataDic(BussinessDictCode.回购拆借方向, null);
        for (SelectOptionDto dto : selectDto2.getOptions()) {
            listFloatRate.add(dto);
        }
        return listFloatRate;
    }
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
        ValidateGroupEdit.class}) MstBankTranFeeManageDto dto, BindingResult result) {
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
        ValidateGroupEdit.class}) MstBankTranFeeManageDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        BussinessCodeParameter param = new BussinessCodeParameter();
        param.setCommonBusinessType(DictCodeList.共同业务类型_银行间交易费用设置);
        String code = bussGetBussinessIdService != null ? bussGetBussinessIdService.buildBussinessCode(
                param) : IDGenerator.generate();
        for(MstBankTranFeeManageDto d:dto.getSelectedItem()){
            //生成交易费用配置表的流水号
            d.setSeqNo(IDGenerator.generate());
          //生成交易费用配置明细的流水号
            d.setDetailSeqNo(IDGenerator.generate());
        }
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
          //新建生成批次号
            dto.setBatchSeq(code);
            dto.setNewBatchSeq(code);
        }
        else{
          //编辑时重新生成批次号
            dto.setNewBatchSeq(code);
        }
        
        ProcessParameter<MstBankTranFeeManageDto> processParameter = new ProcessParameter<MstBankTranFeeManageDto>();
        WorkFlowParameter<MstBankTranFeeManageDto> params = new WorkFlowParameter<MstBankTranFeeManageDto>();
        params.setPageId("MstBankTranFeeManage002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getBatchSeq());
        params.setBusinessType(BussinessDictCodeList.共同业务类型_银行间交易费用设置.getValue());
        // 设置流程业务说明, entity即传入的dto
        // 业务说明：流水号 + "(" + 流水号 + ")" + "-" + "费用类型" + "-" + "费用类型"
        params.setComment(entity -> "批次号" + "(" + entity.getBatchSeq() + ")" + "-" + "费用类型" + "("
                                    + entity.getFeeTypeStr() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = mstBankTranFeeManageService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
