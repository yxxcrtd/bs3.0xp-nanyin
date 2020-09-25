package com.joyintech.service.product.nolimit.totalMaintain.controller;


import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.product.nolimit.totalMaintain.dto.TotalMaintain001Dto;
import com.joyintech.service.product.nolimit.totalMaintain.service.TotalMaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: TotalMaintainController
 * @description: 总量/总比例维护
 * @author: jyl
 * @create: 2019-12-18 17:06
 **/

@RestController("backTotalMaintain_dtx")
@RequestMapping("/TotalMaintain")
public class TotalMaintainController {
    @Autowired
    private TotalMaintainService totalMaintainService;

    /**
     * 非标限额信息查询
     *
     * @param page
     * @param length
     * @param pageParam
     * @return Page
     * @throws Exception
     */
    @RequestMapping("findPage/{page}/{length}")
    public Page<TotalMaintain001Dto> queryProdIdInfo(@RequestBody Map<String, Object> paramMap,
                                                     @PathVariable("page") int page,
                                                     @PathVariable("length") int length) throws Exception {
        return totalMaintainService.findPage(page, length, paramMap);
    }

    /**
     * 新建生效日校验
     *
     * @param effDate
     * @return int
     */
    @RequestMapping("effDateCheck")
    public int effDateCheck(String effDate) {

        return totalMaintainService.effDateCheck(effDate);
    }

    /**
     * 新建非标限额
     *
     * @param dto
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("insertInfo")
    public void insertInfo(@RequestBody TotalMaintain001Dto dto) {

        totalMaintainService.insertInfo(dto);
    }

    /**
     * 修改非标限额
     *
     * @param dto
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("updateInfo")
    public void updateInfo(@RequestBody TotalMaintain001Dto dto) {

        totalMaintainService.updateInfo(dto);
    }

    /**
     * 删除非标限额
     *
     * @param effDate
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("deleteInfo")
    public void deleteInfo(String effDate) {
        totalMaintainService.deleteInfo(effDate);
    }
}
