package com.joyintech.tams.invest.period.trdprojint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.period.trdprojint.dto.TrdProjectDurationIntDto;
import com.joyintech.tams.invest.period.trdprojint.service.TrdProjectDurationInterest001Service;

/**
 * @program: tams-invest-parent
 * @description: 买断利率维护
 * @author: 强成西
 * @create: 2018-10-23 13:44
 **/
@Controller
@PageId("TrdProjectDurationInterest001")
@RequestMapping("/invest/period/trdprojint/TrdProjectDurationInterest001")
public class TrdProjectDurationInterest001Controller extends BaseController {

    @Autowired
    private TrdProjectDurationInterest001Service TrdProjectDurationInterest001Service;

    /**
     * 需要初始化的下拉菜单
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.项目类一级分类};

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        return super.init(model);
    }

    /**
     * 获取所有项目类资产
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdProjectDurationIntDto> findPage() {
        return TrdProjectDurationInterest001Service.findPage(RequestUtil.getPageParam());
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
        return TrdProjectDurationInterest001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
    }
}
