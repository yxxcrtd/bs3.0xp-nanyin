
package com.joyintech.tams.valuation.basicdata.mstcurvegroupmanage.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.basicdata.mstcurvegroupmanage.dto.MstCurveGroupManageDto;
import com.joyintech.tams.valuation.basicdata.mstcurvegroupmanage.service.MstCurveGroupManage001Service;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * @Description:收益率曲线族管理
 * @auther: yuzj
 * @date: 2021/9/27
 * @param:
 * @return:
 */
@Controller
@PageId("MstCurveGroupManage001")
@RequestMapping("/valuation/basicdata/mstcurvegroupmanage/MstCurveGroupManage001")
public class MstCurveGroupManage001Controller extends BaseController{
    @Autowired
    private MstCurveGroupManage001Service mstCurveGroupManage001Service;
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.曲线来源);
        return super.init(model);
    }
    /**
     *
     * <一句话功能简述>
     * <功能详细描述>
     * @return Page<MstYieldCurveInfoDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<MstCurveGroupManageDto> selectAll()
        throws Exception {
        Page<MstCurveGroupManageDto> page = mstCurveGroupManage001Service.selectForPage(RequestUtil.getParameters(getRequest()));
        return page;
    }


    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception{
        //导出选中条数
        return mstCurveGroupManage001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }


    /**
     * @Description:删除检查校验
     * @auther: yuzj
     * @date: 2021/09/11
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("deleteCheck")
    public AjaxResult deleteCheck(@RequestBody @Valid MstCurveGroupManageDto dto, BindingResult result) {
        AjaxResult ajaxResult = new AjaxResult();
        //判断处于工作流中数据不可删除操作
        WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
        wflValidInputParameter.setPageId("MstCurveGroupManage002");
        wflValidInputParameter.setBusinessDataPk1(dto.getCurveGroupCode());
        boolean is = WorkFlowUtil.isNotFromToDoList();
        if (WorkFlowUtil.isNotFromToDoList() && mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)) {
            ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
            errorList.add(new ValidMessage("所选对象存在有效或流程中的数据！"));
            AjaxResult ar = new AjaxResult();
            ar.setErrorList(errorList);
            return ar;
        }

        //判断曲线族下是否含有曲线，如果有则不可删除
        int codeCount  = mstCurveGroupManage001Service.getCountCurveCode(dto.getCurveGroupCode());
        if(codeCount>0){
            return new AjaxResult("该曲线族已被曲线使用，不可删除！", MessageType.错误消息);
        }

        return new AjaxResult();
    }
}
