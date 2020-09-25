
 
package com.joyintech.tams.invest.portfolio.ptlpositionanalysis.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.common.base.entity.TargetDetail;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;

import com.joyintech.tams.invest.portfolio.ptlpositionanalysis.service.PtlPositionAnalysis003Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 * 组合持仓分析报表-》指标设置
 * @author nixiukuan
 * @version 1.0 2019年12月08日
 * @see PtlPositionAnalysis003Controller
 * @since 1.0
 */
@Controller
@PageId("PtlPositionAnalysis003")
@RequestMapping("/invest/portfolio/ptlpositionanalysis/PtlPositionAnalysis003")
public class PtlPositionAnalysis003Controller extends BaseController {
    @Autowired
    private PtlPositionAnalysis003Service ptlPositionAnalysis003Service;
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
        return ptlPositionAnalysis003Service.getAllTreeNode(map);
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

        boolean rst= ptlPositionAnalysis003Service.savaTableCols(map);
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
