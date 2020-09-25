/*
 * 文件名：PerformanceBenchmark001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年7月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
/**
 * 
 */
package com.joyintech.service.basicdata.mstperformancebenchmark.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.mstperformancebenchmark.dto.ComponentInfoDto;
import com.joyintech.service.basicdata.mstperformancebenchmark.dto.MstPerformanceBenchMarkDto;
import com.joyintech.service.basicdata.mstperformancebenchmark.service.ComponentInfoService;
import com.joyintech.service.basicdata.mstperformancebenchmark.service.MstPerformanceBenchMarkService;
import com.joyintech.tams.common.base.entity.MstIndexComponent;

/**
 * 复合基准管理的基本信息管理
 * 显示业绩基准信息的基本页面
 * @author zhangjun
 * @version 1.0 2018年8月23日
 * @see MstPerformanceBenchMarkController
 * @since 1.0
 * 
 */
@RestController("MstPerformanceBenchMarkCtr_dtx")
@RequestMapping("/MstPerformanceBenchMark")
public class MstPerformanceBenchMarkController  {
    /**
     * 复合基准管理service注入
     */
    @Autowired
    private MstPerformanceBenchMarkService mstPerformanceBenchMarkService;
    /**
     * 基准成分service注入
     */
    @Autowired
    private ComponentInfoService componentInfoService;
    /**
     * 查询
     * @param pageParam Map
     * @return Page类
     * @throws Exception 异常
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<MstPerformanceBenchMarkDto> selectForPage(@RequestBody Map<String, Object> pageParam,@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return mstPerformanceBenchMarkService.selectForPage(page,length,pageParam);
    }
    /**
     * 基准成分查询
     * @param pageParam Map
     * @return Page类
     * @throws Exception 异常
     */
    @PostMapping("/selectComponentInfoPage")
    public List<MstIndexComponent> selectComponentInfoPage(@RequestBody Map<String, Object> pageParam) throws Exception{
        return mstPerformanceBenchMarkService.selectComponentInfoPage(pageParam);
    }
    /**
     * 联想文本框service层实现
     *
     * @param param String
     * @return SuggestDto
     */
    @PostMapping("/getIndexName")
    public List<SuggestDto> getIndexName(@RequestBody Map<String,String> map){
        return mstPerformanceBenchMarkService.getIndexName(map);
    }
    /**
     * 双击查看详细信息查询 修改页面信息查询
     * 
     * @param indexId String
     * @return MstPerformanceBenchMarkDto
     * @see
     * @since 1.0
     */
    @PostMapping("/selectDetail")
    public MstPerformanceBenchMarkDto selectDetail(@RequestBody String indexId){
        return mstPerformanceBenchMarkService.selectDetail(indexId);
    }
    /**
     * 删除检查数据是否被使用过 <功能详细描述>
     * 
     * @param dto MstPerformanceBenchMarkDto
     * @return int
     * @see
     * @since 1.0
     */
    @PostMapping("/checkCodeIsUse")
    public int checkCodeIsUse(@RequestBody MstPerformanceBenchMarkDto dto){
        return mstPerformanceBenchMarkService.checkCodeIsUse(dto);
    }
    /**
     * 批量删除检查数据是否被使用过 <功能详细描述>
     * 
     * @param item MstPerformanceBenchMarkDto
     * @return int
     * @see
     * @since 1.0
     */
    @PostMapping("/checkAllCodeIsUse")
    public int checkAllCodeIsUse(@RequestBody List<MstPerformanceBenchMarkDto> item){
        return mstPerformanceBenchMarkService.checkAllCodeIsUse(item);
    }
    /**
     * 新增
     * @param dto MstPerformanceBenchMarkDto
     * @return int
     * @see 
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/insert")
    public int insert(@RequestBody MstPerformanceBenchMarkDto dto) {
        return mstPerformanceBenchMarkService.insert(dto);
    }
    /**
     * 通过indexName查询
     * <功能详细描述>
     * @param dto 数据条件
     * @return 结果
     * @see
     * @since 1.0
     */
    @PostMapping("/selectByIndexName")
    public int selectByIndexName(@RequestBody MstPerformanceBenchMarkDto dto){
        return mstPerformanceBenchMarkService.selectByIndexName(dto);
    }
    /**
     * 通过主键查询
     * <功能详细描述>
     * @param mstIndexComponent 条件
     * @return 统计结果
     * @see
     * @since 1.0
     */
    @PostMapping("/selectByPrimaryKey")
    public int selectByPrimaryKey(@RequestBody MstIndexComponent mstIndexComponent) {
        return mstPerformanceBenchMarkService.selectByPrimaryKey(mstIndexComponent);
    }
    /**
     * 
     * 工作流完成
     * <功能详细描述>
     * @param map
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public void doComplete(@RequestBody MstPerformanceBenchMarkDto dto){
        mstPerformanceBenchMarkService.doComplete(dto);
    }
    
    /**
     * 通过基准编号查询表格信息 
     * @param dto MstPerformanceBenchMarkDto
     * @return int
     * @see 
     */
    @PostMapping("/selectComponentInfoByPrimaryKey")
    public List<ComponentInfoDto> selectComponentInfoByPrimaryKey(@RequestBody String indexId) {
        return componentInfoService.selectComponentInfoByPrimaryKey(indexId);
    }
}
