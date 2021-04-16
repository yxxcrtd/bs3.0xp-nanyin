package com.joyintech.tams.invest.basicdata.mstsharebonusnotice.controller;

import java.util.List;

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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.basicdata.mstsharebonusnotice.dto.MstShareBonusNotice001Dto;
import com.joyintech.tams.invest.basicdata.mstsharebonusnotice.service.MstShareBonusNotice001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * @Classname MstShareBonusNotice001Controller
 * @Description 股票分红
 * @author zhuhanlu 
 * @date 2020/8/6 14:16 
 * @version 1.0 
 */
@Controller
@PageId("MstShareBonusNotice001")
@RequestMapping("/invest/basicdata/mstsharebonusnotice/MstShareBonusNotice001")
public class MstShareBonusNotice001Controller extends BaseController {

    @Autowired
    private MstShareBonusNotice001Service mstShareBonusNotice001Service;
    @Override
    public String init(Model model) {
        return super.init(model);
    }




    /**
     *
     * @Description //分页查询
     * @Param []
     * @return com.joyintech.framework.grid.Page<com.joyintech.tams.invest.basicdata.mstsharebonusnotice.dto.MstShareBonusNotice001Dto>
     * @version 1.0
     * @see
    **/

    @ResponseBody
    @RequestMapping("findPage")
    public Page<MstShareBonusNotice001Dto> findPage()
            throws Exception {
        Page<MstShareBonusNotice001Dto> page = mstShareBonusNotice001Service.selectForPage(
                RequestUtil.getParameters(getRequest()));
        return page;
    }

    /** 资产代码下拉框
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/getShareId")
    public List<SelectOptionDto> getShareId(){
        List<SelectOptionDto> temp = mstShareBonusNotice001Service.getShareId(RequestUtil.getPageParam());
        return temp;
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
    public AjaxResult delete(@RequestBody  MstShareBonusNotice001Dto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<MstShareBonusNotice001Dto> processParameter = new ProcessParameter<MstShareBonusNotice001Dto>();
        WorkFlowParameter<MstShareBonusNotice001Dto> params = new WorkFlowParameter<MstShareBonusNotice001Dto>();
        params.setPageId("MstShareBonusNotice002");
//        params.setPageId(RequestUtil.getParam("pageId"));
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getSharesId());
        params.setBusinessType(DictCodeListExt2.共通业务类型_股票分红公告.getValue());
//        params.setBusinessType("MST30");
        // 设置流程业务说明, entity即传入的dtoSpringContextUtil.getTextValue("MstBondQuotationInfo001.secId")
        params.setComment(
                entity -> SpringContextUtil.getTextValue("MstShareBonusNotice001.sharesId")+"("+ entity.getSharesId() +")"+"-"+SpringContextUtil.getTextValue("MstShareBonusNotice001.regDate") + "(" + DateUtil.format(entity.getRegDate()) + ")"
        );
        processParameter.setWorkFlowParameter(params);
        String msg = mstShareBonusNotice001Service.start(processParameter,
                dto.getSelectedItem().toArray(
                        new MstShareBonusNotice001Dto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) MstShareBonusNotice001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
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
        return mstShareBonusNotice001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }
}
