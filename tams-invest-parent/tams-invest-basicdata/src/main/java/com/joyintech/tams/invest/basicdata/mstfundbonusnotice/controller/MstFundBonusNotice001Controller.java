package com.joyintech.tams.invest.basicdata.mstfundbonusnotice.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.basicdata.mstfundbonusnotice.dto.MstFundBonusNotice001Dto;
import com.joyintech.tams.invest.basicdata.mstfundbonusnotice.service.MstFundBonusNotice001Service;
import com.joyintech.tams.invest.basicdata.mstsharebonusnotice.dto.MstShareBonusNotice001Dto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
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

/**
 * @Classname MstFundBonusNotice001Controller
 * @Description 基金分红公告
 * @author zhuhanlu 
 * @date 2020/8/7 9:52 
 * @version 1.0 
 */
@Controller
@PageId("MstFundBonusNotice001")
@RequestMapping("/invest/basicdata/mstfundbonusnotice/MstFundBonusNotice001")
public class MstFundBonusNotice001Controller extends BaseController {

    @Autowired
    MstFundBonusNotice001Service mstFundBonusNotice001Service;
    @Override
    public String init(Model model) {
        return super.init(model);
    }

    /** 分页查询
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<MstFundBonusNotice001Dto> findPage()
            throws Exception {
        Page<MstFundBonusNotice001Dto> page = mstFundBonusNotice001Service.selectForPage(
                RequestUtil.getParameters(getRequest()));
        return page;
    }

    /** 资产代码下拉框
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/getFundId")
    public List<SelectOptionDto> getShareId(){
        List<SelectOptionDto> temp = mstFundBonusNotice001Service.getFundId(RequestUtil.getPageParam());
        return temp;
    }

    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return mstFundBonusNotice001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }

    /**
     *
     * @Description //删除check
     * @Param [dto, result]
     * @return com.joyintech.framework.ajax.AjaxResult
     * @version 1.0
     * @see
    **/
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) MstFundBonusNotice001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 删除按钮点击
     * @return AjaxResult
     * @param dto MstShareBonusNotice001Dto
     * @throws Exception Exception
     * @param  br BindingResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody  MstFundBonusNotice001Dto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<MstFundBonusNotice001Dto> processParameter = new ProcessParameter<MstFundBonusNotice001Dto>();
        WorkFlowParameter<MstFundBonusNotice001Dto> params = new WorkFlowParameter<MstFundBonusNotice001Dto>();
        params.setPageId("MstFundBonusNotice002");
//        params.setPageId(RequestUtil.getParam("pageId"));
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFundId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_基金分红公告.getValue());
//        params.setBusinessType("MST30");
        // 设置流程业务说明, entity即传入的dtoSpringContextUtil.getTextValue("MstBondQuotationInfo001.secId")
        params.setComment(
                entity -> SpringContextUtil.getTextValue("MstFundBonusNotice001.fundId")+"("+ entity.getFundId() +")"+"-"+SpringContextUtil.getTextValue("MstFundBonusNotice001.regDate") + "(" + DateUtil.format(entity.getRegDate()) + ")"
        );
        processParameter.setWorkFlowParameter(params);
        String msg = mstFundBonusNotice001Service.start(processParameter,
                dto.getSelectedItem().toArray(
                        new MstFundBonusNotice001Dto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }



}
