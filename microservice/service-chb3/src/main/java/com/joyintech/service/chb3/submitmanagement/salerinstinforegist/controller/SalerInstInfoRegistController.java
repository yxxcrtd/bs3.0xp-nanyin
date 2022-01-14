package com.joyintech.service.chb3.submitmanagement.salerinstinforegist.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.submitmanagement.salerinstinforegist.dto.SalerInstInfoRegistDto;
import com.joyintech.tams.report.submitmanagement.salerinstinforegist.service.SalerInstInfoRegist001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/SalerInstInfoRegist")
public class SalerInstInfoRegistController {
    /**
     * 001Service
     */
    @Autowired
    private SalerInstInfoRegist001Service salerInstInfoRegist001Service;
    /**
     *
     * 查询功能
     * <功能详细描述>
     * @return 存储了查询结果的list
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<SalerInstInfoRegistDto> search(@RequestBody Map<String, Object> param){
        return salerInstInfoRegist001Service.searchForPage(param);
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
        salerInstInfoRegist001Service.updateSumStaus(map);
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
        salerInstInfoRegist001Service.cancelMannul(map);
    }

    @PostMapping("queryForInfoRegist")
    public List<SalerInstInfoRegistDto> queryForInfoRegist(@RequestBody Map<String,Object> param){
    	return salerInstInfoRegist001Service.queryForInfoRegist(param);
    }

    /**
     * 销售机构代码下拉框
     */
    @PostMapping("getSalerInstCode")
    public List<SuggestDto> getSalerInstCode(@RequestBody Map<String, Object> map) {
        return salerInstInfoRegist001Service.getSalerInstCode(map);
    }

    /**
     * 总部或分支机构名称下拉框
     */
    @PostMapping("getSalerOrgName")
    public List<SuggestDto> getSalerOrgName(@RequestBody Map<String, Object> map) {
        return salerInstInfoRegist001Service.getSalerOrgName(map);
    }
}
