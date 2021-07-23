package com.joyintech.service.riskrds.limitmanagement.rskredisparamconfig.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.riskrds.limitmanagement.rskredisparamconfig.dto.RskRedisParamConfigDto;
import com.joyintech.service.riskrds.limitmanagement.rskredisparamconfig.service.RskRedisParamConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * redis参数配置
 * @author : 裴家俊
 * @version V1.0
 */
@RestController("RskRedisParamConfigController_dtx")
@RequestMapping("/RskRedisParamConfig")
public class RskRedisParamConfigController {

    @Autowired
    private RskRedisParamConfigService rskRedisParamConfigService;

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
    public Page<RskRedisParamConfigDto> selectAll(@RequestBody Map<String, Object> param,
                                                  @PathVariable("page") int page,
                                                  @PathVariable("length") int length)
        throws Exception {
        return rskRedisParamConfigService.selectForPage(page, length, param);
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
    public void insert(@RequestBody RskRedisParamConfigDto dto)
        throws Exception {
        rskRedisParamConfigService.insert(dto);
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
    public void update(@RequestBody RskRedisParamConfigDto dto)
        throws Exception {
        rskRedisParamConfigService.update(dto);
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
    public void delete(@RequestBody RskRedisParamConfigDto dto)
        throws Exception {
        rskRedisParamConfigService.delete(dto);
    }

    /**
     * 查询基本信息
     * @param commonBusinessType
     * @param paraCode
     * @return
     */
    @GetMapping("getBasicInfo")
    public RskRedisParamConfigDto getBasicInfo(String commonBusinessType,String paraCode) {
        return rskRedisParamConfigService.getBasicInfo(commonBusinessType,paraCode);
    }

    @PostMapping("/getRepeatCount")
    public int getRepeatCount(@RequestBody  Map<String,Object> param){
        return rskRedisParamConfigService.getRepeatCount(param);
    }
}
