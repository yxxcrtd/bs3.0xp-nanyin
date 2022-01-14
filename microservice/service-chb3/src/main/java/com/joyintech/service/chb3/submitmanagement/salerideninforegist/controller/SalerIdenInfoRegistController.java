package com.joyintech.service.chb3.submitmanagement.salerideninforegist.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.submitmanagement.salerideninforegist.dto.SalerIdenInfoRegistDto;
import com.joyintech.tams.report.submitmanagement.salerideninforegist.service.SalerIdenInfoRegist001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/SalerIdenInfoRegist")
public class SalerIdenInfoRegistController {
    /**
     * 001Service
     */
    @Autowired
    private SalerIdenInfoRegist001Service salerIdenInfoRegist001Service;
    /**
     *
     * 查询功能
     * <功能详细描述>
     * @return 存储了查询结果的list
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<SalerIdenInfoRegistDto> search(@RequestBody Map<String, Object> param){
        return salerIdenInfoRegist001Service.searchForPage(param);
    }

    /**
     * 手工处理功能
     *
     * @return 提示信息
     * @param map 条件map
     * @seea
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("updateSumStaus")
    public void updateSumStaus(@RequestBody Map<String, Object> map) {
        salerIdenInfoRegist001Service.updateSumStaus(map);
    }

    /**
     * 取消手工处理功能
     *
     * @return 提示信息
     * @param map 条件map
     * @seea
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("cancelMannul")
    public void cancelMannul(@RequestBody Map<String, Object> map) {
        salerIdenInfoRegist001Service.cancelMannul(map);
    }

    @PostMapping("queryForInfoRegist")
    public List<SalerIdenInfoRegistDto> queryForInfoRegist(@RequestBody Map<String,Object> param){
    	return salerIdenInfoRegist001Service.queryForInfoRegist(param);
    }

    /**
     * 姓名下拉框
     */
    @PostMapping("getSalerName")
    public List<SuggestDto> getSalerName(@RequestBody Map<String, Object> map) {
        return salerIdenInfoRegist001Service.getSalerName(map);
    }
}
