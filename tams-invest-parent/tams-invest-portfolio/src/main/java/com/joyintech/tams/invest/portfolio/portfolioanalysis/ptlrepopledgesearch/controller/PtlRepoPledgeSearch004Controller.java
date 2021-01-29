package com.joyintech.tams.invest.portfolio.portfolioanalysis.ptlrepopledgesearch.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.invest.portfolio.portfolioanalysis.ptlrepopledgesearch.dto.PtlPldgeSummaryDto;
import com.joyintech.tams.invest.portfolio.portfolioanalysis.ptlrepopledgesearch.dto.PtlSubstitutionRepoPledgeDto;
import com.joyintech.tams.invest.portfolio.portfolioanalysis.ptlrepopledgesearch.service.PtlRepoPledgeSearch004Service;
import com.joyintech.tams.invest.portfolio.ptlaccountcurrentinterest.dto.PtlAccountCurrentInterest001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.soap.Addressing;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: 债券质押汇总查询
 * @description: PtlRepoPledgeSearch004Controller
 * @author: 马数凯
 * @create: 2021-01-28
 **/
@Controller
@PageId("PtlRepoPledgeSearch004")
@RequestMapping("/invest/portfolio/portfolioanalysis/ptlrepopledgesearch/PtlRepoPledgeSearch004")
public class PtlRepoPledgeSearch004Controller extends BaseController {
    @Autowired
    PtlRepoPledgeSearch004Service ptlRepoPledgeSearch004Service;
    
    /**
     * 质押明细查询界面的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        Map<String,Object> map = RequestUtil.getPageParam();
        return super.init(model);
    }

    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("pledgeSummaryFindPage")
    public Page<PtlPldgeSummaryDto> pledgeSummaryFindPage()
            throws Exception {
        Map<String,Object> map = RequestUtil.getPageParam();
        Page<PtlPldgeSummaryDto> ptlPledgeSummaryDtoPage = ptlRepoPledgeSearch004Service.selectForPage(map);
        return ptlPledgeSummaryDtoPage;
    }

    /**
     * 画面导出功能实现
     *
     * @return ResponseEntity 确认的实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        Map<String, Object> param = RequestUtil.getParameters(getRequest());
        //组合代码
        Object portfolioId = param.get("portfolioId");
        if (portfolioId != null) {
            param.put("groupcode",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String)portfolioId));
        }
        return ptlRepoPledgeSearch004Service.export(this.getPageId(),
                param);
    }

    @RequestMapping("exportCheck")
    @ResponseBody
    public AjaxResult exportCheck()
            throws Exception {
        Page<PtlPldgeSummaryDto> result = ptlRepoPledgeSearch004Service.selectForPage(RequestUtil.getPageParam());
//        if (result.getRoot().isEmpty()) {
//            return new AjaxResult(getMessage("PTL_COM_E_0012"), MessageType.错误消息);
//        }
        return new AjaxResult();
    }
}
