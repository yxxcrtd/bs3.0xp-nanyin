package com.joyintech.tams.invest.portfolio.feeprovisionsetting.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.TrdAccountmfConfigdetailDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.portfolio.feeprovisionsetting.dto.FeeProvisionSetting001Dto;
import com.joyintech.tams.invest.portfolio.feeprovisionsetting.service.FeeProvisionSetting002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

/**
 * 费用预提设置新建 controller层
 * @author
 *
 */
@Controller
@PageId("FeeProvisionSetting002")
@RequestMapping("/invest/portfolio/feeprovisionsetting/FeeProvisionSetting002")
public class FeeProvisionSetting002Controller extends BaseController {

    @Autowired
    private FeeProvisionSetting002Service feeProvisionSetting002Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.日期号, BussinessDictCode.费用类型, BussinessDictCode.频率,DictCode.登记托管机构);
        if (StringUtil.equals(this.getParam("isTodoList"), "true")) {
            ProcessMark pro = feeProvisionSetting002Service.getBusinessInfo(this.getParam(GlobalConstant.WORKFLOW_ID));
            if (pro != null) {
                FeeProvisionSetting001Dto entity = (FeeProvisionSetting001Dto) JsonUtils.json2Object(
                        pro.getBusinessData(), FeeProvisionSetting001Dto.class);
                model.addAttribute(GlobalConstant.ENTITY, entity);
                model.addAttribute(GlobalConstant.WORKFLOW_ID,
                        this.getParam(GlobalConstant.WORKFLOW_ID));
            }
        }else if (StringUtil.isNotEmpty(this.getParam("seqNo"))) {
            FeeProvisionSetting001Dto entity = feeProvisionSetting002Service.selectEntityByID(this.getParam("seqNo"));
            model.addAttribute(GlobalConstant.ENTITY, entity);
            model.addAttribute("seqNo", this.getParam("seqNo"));
        } else {
            FeeProvisionSetting001Dto entity = new FeeProvisionSetting001Dto();
            entity.setFeeType(BussinessDictCodeList.费用类型_账户维护费.getValue());
            entity.setPayCycle(BussinessDictCodeList.频率_季.getValue());
            entity.setFeeAmtTotal(new BigDecimal(4500));    //默认
            model.addAttribute(GlobalConstant.ENTITY, entity);
        }
        return super.init(model);
    }

    /**
     *
     * 编辑修改页面表格查询
     * <功能详细描述>
     * @return Page<PaRskCustomerRatingDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdAccountmfConfigdetailDto> selectDetail()
            throws Exception {
        Page<TrdAccountmfConfigdetailDto> page = feeProvisionSetting002Service.selectDetail(RequestUtil.getPageParam());
        return page;
    }

    /**
     * 获取账套代码
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getBooksetId")
    public List<SelectOptionDto> getBooksetId() {
        return feeProvisionSetting002Service.getBooksetId(RequestUtil.getPageParam());
    }

    /**
     * 获取账套代码
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("selectOrgInfo")
    public String selectOrgInfo() {
        return feeProvisionSetting002Service.selectOrgInfo(RequestUtil.getPageParam());
    }

    /**
     * 保存check
     *
     * @param dto 费用新建信息
     * @param br 验证消息
     * @return 验证结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid @RequestBody FeeProvisionSetting001Dto dto, BindingResult br) {
        if (br != null && br.hasErrors()) {
            AjaxResult ar = new AjaxResult(br);
            List<ValidMessage> errorList = ar.getErrorList();
            // 流程中数据判断
            for (ValidMessage validMessage : errorList) {
                if (StringUtil.indexOf(validMessage.getMessage(),
                        this.getMessage("COMMON_E_0012").substring(0,
                                this.getMessage("COMMON_E_0012").length() - 3)) >= 0) {
                    validMessage.setMessage(this.getMessage(
                            "COMMON_E_0012", dto.getSeqNo() + "," + dto.getSecAcctId()));
                }
            }
            return ar;
        }
        dto.setActPositionEndDate(dto.getPositionEndDate());
        AjaxResult result = feeProvisionSetting002Service.check(dto);
        
        if(result.getErrorList().isEmpty()){
        	// 确认提醒
            if (br.getErrorCount() == 0) {
                AjaxResult ajaxResult = new AjaxResult();
                String confirmMsg = "";

                if(dto.getFeeAmtTotal().compareTo(new BigDecimal(4500)) != 0) {
                      confirmMsg += this.getMessage("每季费用金额不是4500元，请确认是否继续？");
                }
    	        if (StringUtil.isNotBlank(confirmMsg)) {
    	            ajaxResult.setData(confirmMsg);
    	        }
    	        if (StringUtil.isNotEmpty(ajaxResult.getData())) {
    	            return ajaxResult;
    	        }
            }
            return this.commonCheck(br, dto.getOperateType());
        }
        return result;
    }

    /**
     * 保存操作
     *
     * @param dto dto
     * @param br 验证信息
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody FeeProvisionSetting001Dto dto, BindingResult br)
            throws Exception {

        dto.setActPositionEndDate(dto.getPositionEndDate());
        dto.setDealStatus(DictCodeListExt1.有效状态_流程中.getValue());
        //生成流水号
        if(StringUtil.isEmpty(dto.getSeqNo())){
            String seqNo = UUID.randomUUID().toString().replace("-","").toUpperCase();
            dto.setSeqNo(seqNo);
            List<TrdAccountmfConfigdetailDto> list = dto.getTrdAccountmfConfigdetail();
            if(list != null && !list.isEmpty()){
                for (TrdAccountmfConfigdetailDto detailDto: list) {
                    detailDto.setSeqNo(UUID.randomUUID().toString().replace("-","").toUpperCase());
                    detailDto.setpSeqNo(seqNo);
                }
            }
            feeProvisionSetting002Service.insertData(dto);
        }
//        else{
//            feeProvisionSetting002Service.updateData(dto);
//        }
        dto.setActPositionEndDate(dto.getPositionEndDate());  //实际计提结束日等于计提结束日
        ProcessParameter<FeeProvisionSetting001Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<FeeProvisionSetting001Dto> params = new WorkFlowParameter<>();
        params.setElementId(dto.getElementId());
        params.setPageId(this.getPageId());
        params.setBusinessCode(dto.getSeqNo());
        params.setBusinessType(DictCodeListExt1.金融产品类型_预提费用.getValue());
        // 设置工作流注解
        params.setComment(
                entity -> this.getMessage("FeeProvisionSetting001.seqNo") + "(" + entity.getSeqNo()
                        + ")" + this.getMessage("FeeProvisionSetting001.secAcctId") + "("
                        + entity.getSecAcctId() + ")"
        );

        processParameter.setWorkFlowParameter(params);

        // 调用工作流事件
        String msg = feeProvisionSetting002Service.start(processParameter, dto);
        // 返回工作流错误信息
        return new AjaxResult(msg);
    }
}
