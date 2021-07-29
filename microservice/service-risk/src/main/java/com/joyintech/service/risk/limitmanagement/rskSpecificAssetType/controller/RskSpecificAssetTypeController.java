package com.joyintech.service.risk.limitmanagement.rskSpecificAssetType.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.limitmanagement.rskSpecificAssetType.dto.RskSpecificAssetTypeDto;
import com.joyintech.service.risk.limitmanagement.rskSpecificAssetType.service.RskSpecificAssetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 特定资产类型设置
 * 
 * @author : zhangnian
 */
@RestController("RskSpecificAssetTypeController_dtx")
@RequestMapping("/RskSpecificAssetType")
public class RskSpecificAssetTypeController {

    @Autowired
    private RskSpecificAssetTypeService rskSpecificAssetTypeService;

    /**
     * 画面查询方法
     *
     * @param param 画面参数
     * @param page 页码数
     * @param length 页面长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<RskSpecificAssetTypeDto> selectAll(@RequestBody Map<String, Object> param,
                                                   @PathVariable("page") int page,
                                                   @PathVariable("length") int length)
        throws Exception {
        return rskSpecificAssetTypeService.selectForPage(page, length, param);
    }

    /**
     * 插入
     * 
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public void insert(@RequestBody RskSpecificAssetTypeDto dto)
        throws Exception {
        rskSpecificAssetTypeService.insert(dto);
    }

    /**
     * 更新
     * 
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public void update(@RequestBody RskSpecificAssetTypeDto dto)
        throws Exception {
        rskSpecificAssetTypeService.update(dto);
    }

    /**
     * 移除
     * 
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public void delete(@RequestBody RskSpecificAssetTypeDto dto)
        throws Exception {
        rskSpecificAssetTypeService.delete(dto);
    }

    /**
     * 获取基本信息--修改/详细
     * @param secId
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @GetMapping("getBasicInfo")
    public RskSpecificAssetTypeDto getBasicInfo(String specificId) throws Exception {
        return rskSpecificAssetTypeService.getBasicInfo(specificId);
    }

    @PostMapping("/getAssetIdOption/{page}/{length}")
    public Page<SelectOptionDto> getAssetIdOption(@RequestBody Map<String, Object> param,
                                                  @PathVariable("page") int page, @PathVariable("length") int length) {
        return rskSpecificAssetTypeService.getAssetIdOption(page,length,param);
    }

    @PostMapping("/getRepeatCount")
    public int getRepeatCount(@RequestBody  Map<String,Object> param){
        return rskSpecificAssetTypeService.getRepeatCount(param);
    }
}
