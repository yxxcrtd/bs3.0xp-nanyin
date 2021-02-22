
package com.joyintech.tams.invest.portfolio.ptlmock.controller;

import java.util.List;
import java.util.Map;

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

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.invest.portfolio.ptlmock.dto.PtlMockTest001Dto;
import com.joyintech.tams.invest.portfolio.ptlmock.service.PtlMockTest001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 模拟试算查询
 * 
 * @author yjm
 * @version 1.0 2020/04/13
 * @see PtlMockTest001Controller
 * @since 1.0
 */
@Controller
@PageId("PtlMockTest001")
@RequestMapping("/invest/portfolio/ptlmock/PtlMockTest001")
public class PtlMockTest001Controller extends BaseController {

    /**
     * PtlMockTest001Service
     */
    @Autowired
    private PtlMockTest001Service ptlMockTest001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 分页查询
     * 
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<PtlMockTest001Dto> findPage()
        throws Exception {
        return ptlMockTest001Service.selectForPage(RequestUtil.getPageParam());
    }

    /**
     * 组合代码下拉框选项获取
     */
    @RequestMapping("/getPtlInfo")
    @ResponseBody
    public List<SelectOptionDto> getPtlInfo(@RequestParam Map<String, Object> param) {
        // 查询组合添加权限限制
        param.put("loginUserName", LoginUserUtil.getUserName());
        return ptlMockTest001Service.getPtlInfo(param);
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
        return ptlMockTest001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }

    /**
     * 行删除与删除按钮
     * 
     * @param dto 业务实体
     * @param br br
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) PtlMockTest001Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<PtlMockTest001Dto> processParameter = new ProcessParameter<PtlMockTest001Dto>();
        WorkFlowParameter<PtlMockTest001Dto> params = new WorkFlowParameter<PtlMockTest001Dto>();
        params.setPageId("PtlMockTest001");
        params.setElementId("PtlMockTest001_btn_delete");
        params.setBusinessCode(item -> item.getMockId());
        // params.setBusinessType(DictCodeList.共通业务类型_资金账户管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> getMessage("PtlMockTest001.portfolioId") + "("
                                    + entity.getPortfolioId() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = ptlMockTest001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
