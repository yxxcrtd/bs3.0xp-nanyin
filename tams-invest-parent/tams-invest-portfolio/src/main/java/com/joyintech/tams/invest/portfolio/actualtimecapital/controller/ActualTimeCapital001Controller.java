package com.joyintech.tams.invest.portfolio.actualtimecapital.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.constant.GlobalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.portfolio.actualtimecapital.dto.ActualTimeCapital001Dto;
import com.joyintech.tams.invest.portfolio.actualtimecapital.service.ActualTimeCapital001Service;

/**
 * @program: tams-invest-parent
 * @description: ActualTimeAsset001Controller
 * @author: yanya
 * @create: 2019-10-15
 **/
@Controller
@PageId("ActualTimeCapital001")
@RequestMapping("/invest/portfolio/actualtimecapital/ActualTimeCapital001")
public class ActualTimeCapital001Controller extends BaseController {
    /**
     * 资金头寸查询service
     */
    @Autowired
    private ActualTimeCapital001Service actualTimeCapital001Service;

    /**
     * 资金头寸查询界面的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        //this.setSelectOptionToModel(model, DictCode.平安资产类型);
        Date sysDate = DateUtil.platDateFromCache();
        String morenDate = DateUtil.format(sysDate);
        model.addAttribute("sysDate", morenDate);
        return super.init(model);
    }

    /**
     * 资金账户的下拉框事件
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getAcctInfo")
    public List<SelectOptionDto> getAcctInfo(){
        Map<String,Object> map = RequestUtil.getPageParam();
        List<SelectOptionDto> temp = null;
        //组合代码
        // update by caiqun  工单YHZGCPB20200806001实时资金头寸里不能同时查询多个产品的现金,资金账户根据组合代码刷选并去重 20200814 start
        String portfolioId = (String)map.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)) {
            map.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA, portfolioId));
        }
        temp = actualTimeCapital001Service.getAcctInfo(map);
        // update by caiqun  工单YHZGCPB20200806001实时资金头寸里不能同时查询多个产品的现金,资金账户根据组合代码刷选并去重 20200814 end
//modify by yangwenjun 20201120 可以不输入组合代码查询全部资金账户 start
//        else {
//            temp = new ArrayList<SelectOptionDto>();
//        }
//modify by yangwenjun 20201120 可以不输入组合代码查询全部资金账户 end
        return temp;
    }

  
    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ActualTimeCapital001Dto> selectAll()
            throws Exception {
        Map<String,Object> map = RequestUtil.getPageParam();
        return actualTimeCapital001Service.selectForPage(map);
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("exportCheck")
    @ResponseBody
    public AjaxResult exportCheck()
            throws Exception {
        Page<ActualTimeCapital001Dto> result = actualTimeCapital001Service.selectForPage(RequestUtil.getPageParam());
        if (result.getRoot().isEmpty()) {
            return new AjaxResult(getMessage("PTL_COM_E_0012"), MessageType.错误消息);
        }
        return new AjaxResult();
    }

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return actualTimeCapital001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }
}
