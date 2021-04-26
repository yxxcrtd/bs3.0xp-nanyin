package com.joyintech.tams.svreport.chbreport.common.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.svreport.chbreport.common.service.ChbreportCommonMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 共通Controller
 *
 * @author WanShiWei
 * @version 1.0 2021年04月19日
 * @see ChbreportCommonController
 * @since 1.0
 */
@Controller
@RequestMapping("/chbreport/common/ChbreportCommon")
public class ChbreportCommonController extends BaseController {

    @Autowired
    private ChbreportCommonMService chbreportCommonMService;

    /**
     * 检索产品代码
     *
     * @return 产品代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getPrdBaseinfo")
    public List<SelectOptionDto> getPrdBaseinfo() {
        return chbreportCommonMService.getPrdBaseinfo(RequestUtil.getPageParam());
    }
}
