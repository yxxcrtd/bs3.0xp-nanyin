package com.joyintech.service.product.productplanning.informationMaintenance.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.productplanning.informationMaintenance.service.FinProductPlanningEstablishService;
import com.joyintech.service.product.productplanning.informationMaintenance.dto.FinProductPlanning2DetailDto;
import com.joyintech.service.product.productplanning.informationMaintenance.dto.FinProductPlanning2Dto;
import com.joyintech.service.product.productplanning.informationMaintenance.dto.FinProductPlanning3Dto;
import com.joyintech.service.product.productplanning.informationMaintenance.dto.FinProductPlanningDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 产品规划信息维护微服务
 * @author 代国富
 *20191228
 */
@RestController("backFinProductPlanningEstablishController_dtx")
@RequestMapping("/FinProductPlanning")
public class FinProductPlanningEstablishController {
    @Autowired
    private FinProductPlanningEstablishService finProductPlanningEstablishService;
    /**
     * 查询目标资产规模
     * @param map:开始结束时间
     * @return 基准日,目标资产规模
     */
    @RequestMapping("getTargetSize")
    public  List<FinProductPlanning3Dto> getTargetSize(@RequestBody Map<String,Object> map)  throws Exception{
      return  finProductPlanningEstablishService.getTargetSize(map);
    }

    /**
     * 查询页面查询
     * @param pageParam
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @RequestMapping("findPageAll/{page}/{length}")
    public Page<FinProductPlanningDto> findPageAll(@RequestBody Map<String, Object> pageParam,
                                                   @PathVariable("page") int page,
                                                   @PathVariable("length") int length)
            throws Exception {
        return finProductPlanningEstablishService.queryPageAll(page, length, pageParam);
    }

    /**
     * 新建页面查询
     * @param pageParam
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @RequestMapping("findPage/{page}/{length}")
    public Page<FinProductPlanning2DetailDto> findPage(@RequestBody Map<String, Object> pageParam,
                                                       @PathVariable("page") int page,
                                                       @PathVariable("length") int length)
            throws Exception {
        return finProductPlanningEstablishService.findPage(page, length, pageParam);
    }

    /**
     * 规划名称下拉框
     * @param paramMap 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    @RequestMapping("querySeriesName/{page}/{length}")
    public Page<SelectOptionDto> querySeriesName(@RequestBody Map<String, Object> paramMap,
                                                 @PathVariable("page") int page,
                                                 @PathVariable("length") int length) {
        return finProductPlanningEstablishService.querySeriesName(page, length,paramMap);
    }
    /**
     * 根据流水号查询研发方案详情
     *
     * @param
     * @return 研发方案详情
     * @see
     * @since 1.0
     */
    @GetMapping("getEntityByNo")
    public FinProductPlanning2Dto getEntityByNo(String planningNo) {
        return finProductPlanningEstablishService.getEntityByNo(planningNo);
    }



    /**
     * 新增或者修改数据
     * @param finProductPlanning2Dto
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("/updateOrAdd")
    public Integer updateOrAdd(@RequestBody FinProductPlanning2Dto finProductPlanning2Dto) throws Exception{
        return finProductPlanningEstablishService.updateOrAdd(finProductPlanning2Dto);
    }
    /**
     * 点击+号操作
     * 根据系列编号查询是否存在该数据
     * @param
     * @return
     */
    @PostMapping("findCountByNo")
    public Integer findCountByNo(@RequestBody Map<String,Object> params) {
        String planningSeriesNo = (String) params.get("planningSeriesNo");
       return  finProductPlanningEstablishService.findCountByNo(planningSeriesNo);
    }

    /**
     * 点击+号操作
     * 根据系列名称查询是否存在该数据
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping("findCountByName")
    public Integer  findCountByName( @RequestBody Map<String,Object> params) {
        String planningSeriesName = (String) params.get("planningSeriesName");
        return  finProductPlanningEstablishService.findCountByName(planningSeriesName);
    }
    /**
     * 删除操作
     * @param params
     * @return
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("/deleteByPrimary")
    public Integer  deleteByPrimary(@RequestBody Map<String,Object> params){
        return finProductPlanningEstablishService.deleteByPrimary(params);
    }
    /**
     * 产品规划信息维护 用于相同时间check
     * @param params
     * @return
     */
    @RequestMapping("/getConflictCount")
    public int getConflictCount(@RequestBody Map<String,Object> params){
        return finProductPlanningEstablishService.getConflictCount(params);
    }
    /**
     * 修改页面查询到期份额
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping("findProductsDue")
    public BigDecimal findProductsDue(@RequestBody Map<String,Object> params){
        return  finProductPlanningEstablishService.findProductsDue(params);
    }
    /**
     * 产品规划信息维护 用于重叠时间check
     * @param params
     * @return
     */
    @RequestMapping("/getOverlapCount")
    public int getOverlapCount(@RequestBody Map<String,Object> params){
        return finProductPlanningEstablishService.getOverlapCount(params);
    }

}
