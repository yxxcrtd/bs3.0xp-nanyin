

package com.joyintech.tams.invest.basicdata.mstcommissionset.controller;

import com.joyintech.tams.constant.DictCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.invest.basicdata.mstcommissionset.dto.MstCommissionSetDto;
import com.joyintech.tams.invest.basicdata.mstcommissionset.service.MstCommissionSetService;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 佣金设置
 * @author 裴家俊
 * @version 1.0 2019年3月22日
 * @see MstCommissionSet001Controller
 * @since 1.0
 */
@Controller
@PageId("MstCommissionSet001")
@RequestMapping("/invest/basicdata/mstcommissionset/MstCommissionSet001")
public class MstCommissionSet001Controller extends BaseController {

    @Autowired
    private MstCommissionSetService mstCommissionSetService;

    /**
     * 画面入口
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, BussinessDictCode.交易场所);
        this.setSelectOptionToModel(model, BussinessDictCode.费用类型);
        this.setSelectOptionToModel(model, DictCode.授信资产类型);//add by hudongyang 2020-08-04
        return super.init(model);
    }

    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<MstCommissionSetDto> selectAll()
        throws Exception {
        Page<MstCommissionSetDto> page = mstCommissionSetService.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
    }

    /**
     * 删除按钮点击时的check
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
        ValidateGroupDelete.class}) MstCommissionSetDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     *  删除
     * @param dto 页面选中的数据
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) MstCommissionSetDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<MstCommissionSetDto> processParameter = new ProcessParameter<MstCommissionSetDto>();
        WorkFlowParameter<MstCommissionSetDto> params = new WorkFlowParameter<MstCommissionSetDto>();
        params.setPageId("MstCommissionSet002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getBatchSeq());
        params.setBusinessType(BussinessDictCodeList.共同业务类型_佣金设置.getValue());
     // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("MstCommissionSet002.batchSeq"),
                GlobalConstant.OPEN_PARENTHESIS, dto.getBatchSeq(), GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("MstCommissionSet002.secAcct"), GlobalConstant.OPEN_PARENTHESIS,
                entity.getSecAcctId(), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = mstCommissionSetService.start(processParameter,
            dto.getSelectedItem().toArray(
                new MstCommissionSetDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
        
    }

    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return mstCommissionSetService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }

}
