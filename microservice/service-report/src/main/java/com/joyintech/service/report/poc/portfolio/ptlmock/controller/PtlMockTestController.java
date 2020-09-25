package com.joyintech.service.report.poc.portfolio.ptlmock.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.poc.portfolio.ptlmock.dto.PtlMockTestDto;
import com.joyintech.service.report.poc.portfolio.ptlmock.service.PtlMockTestService;

/**
 * 模拟试算
 * 
 * @author yjm
 * @version 1.0 2020/04/14
 * @see PtlMockTestController
 * @since 1.0
 */
@RestController
@RequestMapping("/PtlMockTest")
public class PtlMockTestController extends BaseService<PtlMockTestDto> {

    /**
     * PtlMockTestService
     */
    @Autowired
    private PtlMockTestService ptlMockTestService;

    /**
     * 组合代码下拉框选项获取
     * 
     * @param map 参数map
     * @return 组合代码下拉框选项
     */
    @PostMapping("getPtlInfo")
    public List<SelectOptionDto> getPtlInfo(@RequestBody Map<String, Object> map) {
        return ptlMockTestService.getPtlInfo(map);
    }

    /**
     * 画面查询方法
     * 
     * @param pageParam 请求参数
     * @param page 页面
     * @param length 长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("selectForPage/{page}/{length}")
    public Page<PtlMockTestDto> selectForPage(@RequestBody Map<String, Object> pageParam,
                                              @PathVariable("page") int page,
                                              @PathVariable("length") int length)
        throws Exception {
        return ptlMockTestService.selectForPage(page, length, pageParam);
    }

    /**
     * @param entity entity
     * @return 删除结果
     * @throws Exception Exception
     */
    @PostMapping("delete")
    @Transactional
    @DTXTransaction
    public Integer delete(@RequestBody PtlMockTestDto entity)
        throws Exception {
        return ptlMockTestService.delete(entity);
    }

    /**
     * 获取资产代码下拉框选项
     * @param map 参数map
     * @return 资产代码下拉框选项
     */
    @PostMapping("getFinProdId")
    public List<SelectOptionDto> getFinProdId(@RequestBody Map<String, Object> map) {
        return ptlMockTestService.getFinProdId(map);
    }

    /**
     * 工作流完成方法
     *
     * @param dto 数据dto
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public void doComplete(@RequestBody PtlMockTestDto dto) {
        ptlMockTestService.doComplete(dto, dto.getOperateType());
    }

    /**
     * 根据模拟编号查询模拟试算具体信息
     * @param map 参数map
     * @return 模拟试算具体信息
     */
    @PostMapping("getInitInfo")
    public PtlMockTestDto getInitInfo(@RequestBody Map<String, Object> map) {
        return ptlMockTestService.getInitInfo(map);
    }
}
