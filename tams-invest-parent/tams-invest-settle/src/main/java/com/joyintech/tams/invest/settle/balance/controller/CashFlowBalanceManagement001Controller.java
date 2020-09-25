package com.joyintech.tams.invest.settle.balance.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.util.SettleCommonUtil;
import com.joyintech.tams.invest.settle.balance.dto.CashFlowBalanceManagement001Dto;
import com.joyintech.tams.invest.settle.balance.service.CashFlowBalanceManagement001Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;

/**
 * @program: tams-invest-parent
 * @description: 现金流对账管理
 * @author: 强成西
 * @create: 2018-11-15 11:28
 **/
@Controller
@PageId("CashFlowBalanceManagement001")
@RequestMapping("/invest/settle/balance/CashFlowBalanceManagement001")
public class CashFlowBalanceManagement001Controller extends BaseController {

    @Autowired
    private CashFlowBalanceManagement001Service cashFlowBalanceManagement001Service;

    /**
     * 需要初始化的下拉菜单
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.托管流水借贷方向, DictCode.匹配状态,
        DictCode.平安业务类型};

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        Map<String, Object> params = new HashMap<>();
        SettleCommonUtil.setParams(params);
        model.addAllAttributes(params); 
        return super.init(model);
    }

    /**
     * 获取所有产品资产代码
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findCounterAcctNo")
    public Page<SelectOptionDto> findCounterAcctNo() {
        return cashFlowBalanceManagement001Service.findCounterAcctNo();
    }

    /**
     * 获取所有产品资产代码
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findCounterAcctNoForSugg")
    public List<SuggestDto> findCounterAcctNoForSugg() {
        return cashFlowBalanceManagement001Service.findCounterAcctNoForSugg();
    }

    /**
     * 获取所有产品托管户
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findAcctNoByTg")
    public List<SuggestDto> findAcctNoByTg() {
        return cashFlowBalanceManagement001Service.findAcctNoByTg();
    }
    
    /**
     * 获取所有满足条件的数据
     *
     * @return grid集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<CashFlowBalanceManagement001Dto> findPage() {
        return cashFlowBalanceManagement001Service.findPage(RequestUtil.getPageParam());
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
        return cashFlowBalanceManagement001Service.export(getPageId(), RequestUtil.getParameters(getRequest()));
    }
}
