package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixmeninfo.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixmeninfo.dto.FxImixMenInfoDto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixmeninfo.service.FxImixMenInfo001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 银行间交易员信息查询
 *
 * @author zcf
 * @version 1.0
 * @date 2020-01-06
 */
@Controller
@PageId("FxImixMenInfo001")
@RequestMapping("/interf/cfetsup/imixbasicinfo/imixmeninfo/FxImixMenInfo001")
public class FxImixMenInfo001Controller extends BaseController {

    @Autowired
    private FxImixMenInfo001Service fxImixMenInfo001Service;

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
    public Page<FxImixMenInfoDto > selectForPage(){
        return fxImixMenInfo001Service.selectPageData(RequestUtil.getPageParam());
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
            throws Exception {
        return fxImixMenInfo001Service.export(this.getPageId(), RequestUtil.getPageParam());
    }

}
