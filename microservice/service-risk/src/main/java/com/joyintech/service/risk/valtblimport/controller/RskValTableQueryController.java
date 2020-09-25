package com.joyintech.service.risk.valtblimport.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.risk.valtblimport.dto.RskValtblAssetSubDto;
import com.joyintech.service.risk.valtblimport.dto.RskValtblAssetSumDto;
import com.joyintech.service.risk.valtblimport.service.RskValTableQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 *  估值表查询
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月3日
 * @see RskValTableQueryController
 * @since 1.0
 */
@RestController("rskValTableQuery_dtx")
@RequestMapping("/RskValTableQuery")
public class RskValTableQueryController {

    @Autowired
    private RskValTableQueryService rskValTableQueryService;

    /**
     * 查询
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<RskValtblAssetSumDto> selectAll(@RequestBody Map<String, Object> param,
                                                @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return rskValTableQueryService.selectForPage(page, length, param);
    }

    @PostMapping("selectRskValTableDetaile")
    public List<RskValtblAssetSubDto> selectAll(@RequestBody Map<String, Object> param){
        return rskValTableQueryService.selectRskValTableDetaile(param);
    }
}
