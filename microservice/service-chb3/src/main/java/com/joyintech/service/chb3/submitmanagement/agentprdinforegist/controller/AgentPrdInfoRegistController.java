package com.joyintech.service.chb3.submitmanagement.agentprdinforegist.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.report.submitmanagement.agentprdinforegist.dto.AgentPrdInfoRegistDto;
import com.joyintech.tams.report.submitmanagement.agentprdinforegist.service.AgentPrdInfoRegist001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/AgentPrdInfoRegist")
public class AgentPrdInfoRegistController {
    /**
     * 001Service
     */
    @Autowired
    private AgentPrdInfoRegist001Service agentPrdInfoRegist001Service;

    /**
     *
     * 查询功能
     * <功能详细描述>
     * @return 存储了查询结果的list
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<AgentPrdInfoRegistDto> search(@RequestBody Map<String, Object> param){
        return agentPrdInfoRegist001Service.searchForPage(param);
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
        agentPrdInfoRegist001Service.updateSumStaus(map);
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
        agentPrdInfoRegist001Service.cancelMannul(map);
    }

    @PostMapping("queryForInfoRegist")
    public List<AgentPrdInfoRegistDto> queryForInfoRegist(@RequestBody Map<String,Object> param){
    	return agentPrdInfoRegist001Service.queryForInfoRegist(param);
    }

    /**
     * 代理销售机构代码下拉框
     */
    @PostMapping("getAgentInstitutionCode")
    public List<SuggestDto> getAgentInstitutionCode(@RequestBody Map<String, Object> map) {
        return agentPrdInfoRegist001Service.getAgentInstitutionCode(map);
    }

    /**
     * 产品代码下拉框
     */
    @PostMapping("getProdId")
    public List<SuggestDto> getProdId(@RequestBody Map<String, Object> map) {
        return agentPrdInfoRegist001Service.getProdId(map);
    }

    /**
     * 产品登记编码下拉框
     */
    @PostMapping("getProdRegistCode")
    public List<SuggestDto> getProdRegistCode(@RequestBody Map<String, Object> map) {
        return agentPrdInfoRegist001Service.getProdRegistCode(map);
    }
}
