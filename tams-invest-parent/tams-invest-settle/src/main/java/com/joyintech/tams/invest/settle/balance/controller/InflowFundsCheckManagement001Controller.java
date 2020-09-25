package com.joyintech.tams.invest.settle.balance.controller;

import java.util.HashMap;
import java.util.Map;

import com.github.pagehelper.util.StringUtil;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.settle.balance.service.OutflowFundsCheckManagement001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.settle.balance.PortfolioUnmatchedInfo;
import com.joyintech.tams.invest.common.util.SettleCommonUtil;
import com.joyintech.tams.invest.settle.balance.dto.OutflowFundsSystemFlowDto;
import com.joyintech.tams.invest.settle.balance.service.InflowFundsCheckManagement001Service;

/**
 * @program: tams-invest-parent
 * @description: 流入现金流
 * @author: 强成西
 * @create: 2018-11-15 11:28
 **/
@Controller
@PageId("InflowFundsCheckManagement001")
@RequestMapping("/invest/settle/balance/InflowFundsCheckManagement001")
public class InflowFundsCheckManagement001Controller extends BaseController {

    @Autowired
    private InflowFundsCheckManagement001Service inflowFundsCheckManagement001Service;

    @Autowired
    private OutflowFundsCheckManagement001Service outflowFundsCheckManagement001Service;
    @Autowired
    private IPlatformDate iPlatformDate;

    /**
     * 需要初始化的下拉菜单
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.画面数据状态, DictCode.是否,
        DictCode.指令状态, DictCode.平安业务类型};

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        Map<String, Object> params = new HashMap<>();
        SettleCommonUtil.setParams(params);
        model.addAllAttributes(params);
        return super.init(model);
    }

    /**
     * 分页获取账户下组合的未匹配流水数量
     *
     * @return grid
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPortfolioUnmatched")
    public Page<PortfolioUnmatchedInfo> findPortfolioUnmatched() {
        return inflowFundsCheckManagement001Service.findPortfolioUnmatched();
    }

    /**
     * 查询系统流入流水
     *
     * @return grid
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findSysPage")
    public Page<OutflowFundsSystemFlowDto> findSysPage() {
        if (StringUtil.isEmpty(RequestUtil.getParam("acctNo"))
            || (DictCodeList.是否_是.getValue().equals(getParam("isMatched"))
                && StringUtil.isNotEmpty(getParam("statusValid"))
                && !DictCodeListExt1.画面数据状态_有效.getValue().equals(getParam("statusValid")))) {
            return new Page<>();
        }
        return inflowFundsCheckManagement001Service.findSysPage(RequestUtil.getPageParam());
    }

    /**
     * 分页获取非保本组合托管账户
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findEscrowAcctNo")
    public Page<SelectOptionDto> findEscrowAcctNo() {
        return outflowFundsCheckManagement001Service.findEscrowAcctNo();
    }

    /**
     * 查询托管流出流水
     *
     * @return grid
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findEnPage")
    public Page<OutflowFundsSystemFlowDto> findEnPage() {
        if (StringUtil.isEmpty(RequestUtil.getParam("acctNo"))) {
            return new Page<>();
        }
        return inflowFundsCheckManagement001Service.findEnPage(RequestUtil.getPageParam());
    }

    /**
     * 导出
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        Map<String, Object> searchParams = RequestUtil.getPageParam();
        String fileName = "流入资金核对-托管流水" + "(" + iPlatformDate.getSysDateStr() + ").xls";
        if (searchParams.get("isSys") != null && Boolean.parseBoolean((String)searchParams.get("isSys"))) {
            fileName = "来账分拣" + "(" + iPlatformDate.getSysDateStr() + ").xls";
        }
        return inflowFundsCheckManagement001Service.export(fileName, RequestUtil.getParameters(getRequest()));
    }


    /**
     * 流水更新
     *
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("cashInfSel")
    public AjaxResult cashInfSel() {
        return inflowFundsCheckManagement001Service.cashInfSel(RequestUtil.getParameters(getRequest()));
    }
}
