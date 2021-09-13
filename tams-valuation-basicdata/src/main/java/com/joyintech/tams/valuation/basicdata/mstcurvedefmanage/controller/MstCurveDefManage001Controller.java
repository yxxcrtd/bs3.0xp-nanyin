
package com.joyintech.tams.valuation.basicdata.mstcurvedefmanage.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.valuation.basicdata.mstcurvedefmanage.dto.MstCurveDefManageDto;
import com.joyintech.tams.valuation.basicdata.mstcurvedefmanage.service.MstCurveDefManage001Service;
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
import java.util.Map;

/**
 * 收益率曲管理
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author songjun
 * @version 1.0 2021-06-16
 * @see MstCurveDefManage001Controller
 * @since 1.0
 */
@Controller
@PageId("MstCurveDefManage001")
@RequestMapping("/valuation/basicdata/mstcurvedefmanage/MstCurveDefManage001")
public class MstCurveDefManage001Controller extends BaseController{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    @Autowired
    private MstCurveDefManage001Service mstCurveDefManage001Service;
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.收益率曲线类型);
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
    public Page<MstCurveDefManageDto> selectAll()
        throws Exception {
        Page<MstCurveDefManageDto> page = mstCurveDefManage001Service.selectForPage(RequestUtil.getParameters(getRequest()));
        return page;
    }

    @ResponseBody
    @RequestMapping("getCurveCode")
    public Page<SelectOptionDto> getCurveCode(){
       return mstCurveDefManage001Service.getCurveCode(RequestUtil.getPageParam());
    }

    @ResponseBody
    @RequestMapping("updateEffStatus")
    public Integer updateEffStatus(@RequestBody MstCurveDefManageDto dto){
        mstCurveDefManage001Service.updateEffStatus(dto);
        return dto.getSelectedItem().size();
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
        return mstCurveDefManage001Service.export(this.getPageId(),
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
    public AjaxResult deleteCheck(@RequestBody @Valid MstCurveDefManageDto dto, BindingResult result) {
        AjaxResult ajaxResult = new AjaxResult();
        //判断处于工作流中数据不可删除操作
        WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
        wflValidInputParameter.setPageId("MstCurveDefManage002");
        wflValidInputParameter.setBusinessDataPk1(dto.getCurveCode());
        boolean is = WorkFlowUtil.isNotFromToDoList();
        if (WorkFlowUtil.isNotFromToDoList() && mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)) {
            ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
            errorList.add(new ValidMessage("所选对象存在有效或流程中的数据！"));
            AjaxResult ar = new AjaxResult();
            ar.setErrorList(errorList);
            return ar;
        }
        return new AjaxResult();
    }
}
