package com.joyintech.tams.invest.portfolio.ptlperformanceanalysis.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.common.base.entity.TargetDetail;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.portfolio.ptlperformanceanalysis.dto.PtlPerformanceAnalysis002Dto;
import com.joyintech.tams.invest.portfolio.ptlperformanceanalysis.service.PtlPerformanceAnalysis002Service;

@Controller
@PageId("PtlPerformanceAnalysis002")
@RequestMapping("/invest/portfolio/ptlperformanceanalysis/PtlPerformanceAnalysis002")
public class PtlPerformanceAnalysis002Controller extends BaseController  {
	
    @Autowired
    private PtlPerformanceAnalysis002Service ptlPerformanceAnalysis002Service;
	

    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // 平台日期
        model.addAttribute("platDate", iPlatformDate.getSysDateStr());

        return super.init(model);
    }
    
    /**
     * 获取指标设置树结构
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping({"/getAllTreeNode"})
    public List<TreeNode<TargetDetail>> getAllTreeNode() throws Exception {
        Map<String,Object> map = RequestUtil.getParameters(getRequest());
        return ptlPerformanceAnalysis002Service.getAllTreeNode(map);
    }
 
    /**
     * 保存设置的列
     * @return 查询结果集
     * @throws Exception 异常
     */
    @Transactional(rollbackFor=Exception.class)
    @ResponseBody
    @RequestMapping("savaTableCols")
    public AjaxResult savaTableCols() throws Exception {
        Map<String,Object> map = RequestUtil.getParameters(getRequest());
        AjaxResult ajaxResult=new AjaxResult();

        boolean rst= ptlPerformanceAnalysis002Service.savaTableCols(map);
        if(rst)
        {
            ajaxResult.setData("保存成功");
        }
        else
        {
            ajaxResult.setData("保存失败");
        }
        return  ajaxResult;
    }
    
    
}
