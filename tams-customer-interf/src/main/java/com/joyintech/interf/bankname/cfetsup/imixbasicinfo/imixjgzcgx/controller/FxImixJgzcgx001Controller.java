package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixjgzcgx.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixjgzcgx.dto.FxImixJgzcgxDto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixjgzcgx.service.FxImixJgzcgx001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 机构主从关系信息列表
 *
 * @author zcf
 * @version 1.0
 * @date 2020-01-06
 */
@Controller
@PageId("FxImixJgzcgx001")
@RequestMapping("/interf/cfetsup/imixbasicinfo/imixjgzcgx/FxImixJgzcgx001")
public class FxImixJgzcgx001Controller extends BaseController {

    @Autowired
    private FxImixJgzcgx001Service fxImixJgzcgx001Service;

    @Override
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 分页查询
     * @return
     */
    @RequestMapping("selectForPage")
    @ResponseBody
    public Page<FxImixJgzcgxDto> selectForPage(){
        return fxImixJgzcgx001Service.selectPageData(RequestUtil.getPageParam());
    }
}
