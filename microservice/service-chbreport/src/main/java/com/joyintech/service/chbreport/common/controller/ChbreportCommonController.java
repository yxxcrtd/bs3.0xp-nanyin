package com.joyintech.service.chbreport.common.controller;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.chbreport.common.service.ChbreportCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 报表共通
 *
 * @author WanShiwei
 * @version 2.1.6 2021年04月20日
 * @see
 * @since 2.1.6
 */
@RestController("ChbreportCommonCtr_dtx")
@RequestMapping("/ChbreportCommon")
public class ChbreportCommonController {

    /**
     * service
     */
    @Autowired
    private ChbreportCommonService chbreportCommonService;

    /**
     * 产品代码下拉框取得值
     *
     * @param map
     * @return 下拉框值
     * @see
     * @since 1.0
     */
    @PostMapping("getPrdBaseinfo")
    public List<SelectOptionDto> getPrdBaseinfo(@RequestBody Map<String, Object> map) {
        return chbreportCommonService.getPrdBaseinfo(map);
    }
}
