package com.joyintech.tams.invest.basicdata.multiportfoliomaintenance.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListStl;
import com.joyintech.tams.invest.basicdata.multiportfoliomaintenance.dto.MultiPortfolioMaintenanceDto;
import com.joyintech.tams.invest.basicdata.multiportfoliomaintenance.dto.MutiptlPortfolioDetailDto;
import com.joyintech.tams.invest.basicdata.multiportfoliomaintenance.service.MultiPortfolioMaintenance002Service;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@PageId("MultiPortfolioMaintenance002")
@RequestMapping("/invest/basicdata/multiportfoliomaintenance/MultiPortfolioMaintenance002")
public class MultiPortfolioMaintenance002Controller extends BaseController {
    /**
     * 对账参数设置 - service
     */
    @Autowired
    private MultiPortfolioMaintenance002Service multiPortfolioMaintenance002Service;

    // 平台日期接口
    @Autowired
    private IPlatformDate iPlatformDate;

    /**
     * 查询页面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        // 平台日
        model.addAttribute("iPlatformDate", iPlatformDate.getSysDate());
        return super.init(model);
    }

    /**
     *
     * 查询按钮查询事件
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("getMutiptlPortfolioAcctnoTree")
    @ResponseBody
    public List<TreeNode> getMutiptlPortfolioAcctnoTree(){
        return multiPortfolioMaintenance002Service.getMutiptlPortfolioAcctnoTree(RequestUtil.getPageParam());
    }


    /**
     * 保存时check数据
     * @return
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ ValidateGroupEdit.class }) MultiPortfolioMaintenanceDto dto,
                            BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxResult(result);
        }
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存按钮点击
     *
     * @param dto dto
     * @param result BindingResult
     * @throws Exception Exception
     * @return check结果
     * @see
     * @since 1.0
     */
    @SuppressWarnings("deprecation")
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) MutiptlPortfolioDetailDto dto, BindingResult result)
            throws Exception {
        if (result.hasErrors()) {
            return new AjaxResult();
        }

        ProcessParameter<MutiptlPortfolioDetailDto> processParameter =
                new ProcessParameter<>();
        WorkFlowParameter<MutiptlPortfolioDetailDto> params =
                new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());

        // 主键
        if (StringUtil.isEmpty(dto.getSeqNo())) {
            dto.setSeqNo(IDGenerator.generate());
        }
        params.setBusinessCode(dto.getSeqNo());
        params.setBusinessType(DictCodeListStl.共通业务类型_券商对账参数设置.getValue());
        // 设置流程业务说明, entity即传入的dto
        //params.setComment(entity -> entity.getParaCodeStr() + ":" + entity.getParaValueStr());
        processParameter.setWorkFlowParameter(params);
        String msg = multiPortfolioMaintenance002Service.start (processParameter,dto);
        return new AjaxResult(msg);
    }




}
