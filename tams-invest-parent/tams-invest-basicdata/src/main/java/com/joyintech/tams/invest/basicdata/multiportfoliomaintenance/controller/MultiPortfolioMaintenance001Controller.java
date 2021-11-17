package com.joyintech.tams.invest.basicdata.multiportfoliomaintenance.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListStl;
import com.joyintech.tams.invest.basicdata.multiportfoliomaintenance.dto.MultiPortfolioMaintenanceDto;
import com.joyintech.tams.invest.basicdata.multiportfoliomaintenance.dto.MutiptlPortfolioDetailDto;
import com.joyintech.tams.invest.basicdata.multiportfoliomaintenance.service.MultiPortfolioMaintenance001Service;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 多组合维护- 查询画面
 */
@Controller
@PageId("MultiPortfolioMaintenance001")
@RequestMapping("/invest/basicdata/multiportfoliomaintenance/MultiPortfolioMaintenance001")
public class MultiPortfolioMaintenance001Controller extends BaseController {

    /**
     * 对账参数设置 - service
     */
    @Autowired
    private MultiPortfolioMaintenance001Service multiPortfolioMaintenancea001Service;
    /**
     * 查询页面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        setSelectOptionToModel(model, new DictCode[]{
            DictCode.参数代码, DictCode.对账维度
        });
        model.addAttribute("RECON_PARA_CODE_01", DictCodeListStl.参数代码_误差值.getValue());
        model.addAttribute("RECON_PARA_CODE_02", DictCodeListStl.参数代码_对账维度.getValue());
        return super.init(model);
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
            ValidateGroupDelete.class}) MultiPortfolioMaintenanceDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 删除
     * @param dto
     * @param br
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
            ValidateGroupDelete.class}) MultiPortfolioMaintenanceDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<MultiPortfolioMaintenanceDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<MultiPortfolioMaintenanceDto> params = new WorkFlowParameter<>();
        params.setPageId("StlSecReconPara002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getSeqNo());
        params.setBusinessType(DictCodeListStl.共通业务类型_券商对账参数设置.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> entity.getParaCodeStr() + ":" + entity.getParaValueStr());
        processParameter.setWorkFlowParameter(params);
        String msg = multiPortfolioMaintenancea001Service.start(processParameter,
                dto.getSelectedItem().toArray(new MultiPortfolioMaintenanceDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }


    /**
     * 分页查询
     * @return
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<MutiptlPortfolioDetailDto> findPage() {
        return multiPortfolioMaintenancea001Service.findPage(RequestUtil.getPageParam());
    }

    /**
     * 获取组合树结构数据
     * @return 画面元素
     * @throws Exception Exception
     * @since 1.0
     */
    @SuppressWarnings("rawtypes")
    @ResponseBody
    @RequestMapping("/getMutiptlPortfolioTree")
    public List<TreeNode> getMutiptlPortfolioTree()
    {
        return multiPortfolioMaintenancea001Service.getMutiptlPortfolioTree(RequestUtil.getPageParam());
    }
    /**
     * 删除方法
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getDelete")
    @ResponseBody
    public AjaxResult getDelete(){
        String treeId = this.getParam("treeId");
        String parTreeId = this.getParam("parTreeId");
        AjaxResult aResult =multiPortfolioMaintenancea001Service.getDelete(treeId,parTreeId);
        return aResult;
    }
}
