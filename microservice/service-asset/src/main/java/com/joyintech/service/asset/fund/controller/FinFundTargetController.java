/*
 * 文件名：FinFundTargetController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.fund.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.asset.fund.dto.BussFinIntPayDto;
import com.joyintech.service.asset.fund.dto.BussRepFundInfoDto;
import com.joyintech.service.asset.fund.dto.FinFundTargetDto;
import com.joyintech.service.asset.fund.service.FinFundTargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 基金类标的信息的微服务controller
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年9月10日
 * @see FinFundTargetController
 * @since 1.0
 */
@RestController("FinFundTargetController_dtx")
@RequestMapping("/FinFundTarget")
public class FinFundTargetController {
    @Autowired
    private FinFundTargetService finFundTargetService;
    
    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<FinFundTargetDto> findPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return finFundTargetService.selectForPage(page, length, param);
    }
    
    /**
     * 查询资产代码
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("/getFinprodMarketIdList")
    public List<SuggestDto> getFinprodMarketIdList(String finprodMarketId) 
        throws Exception {
        return finFundTargetService.getFinprodMarketIdList(finprodMarketId);
    }
    /**
     * 查询基金管理人
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("/getIssuer")
    public List<SelectOptionDto> getIssuer()
        throws Exception {
        return finFundTargetService.getIssuer();
    }
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto EtrBrowDepoInfoDto
     * @throws IllegalAccessException IllegalAccessException
     * @throws InvocationTargetException InvocationTargetException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/doInsert")
    public void doInsert(@RequestBody FinFundTargetDto dto) throws IllegalAccessException, InvocationTargetException {
        finFundTargetService.doInsert(dto);
    }
    /**
     * 删除
     * 
     * @param dto
     *            页面选中的数据
     * @param br
     *            br
     * @return 结果
     * @throws Exception
     *             异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody FinFundTargetDto dto) throws Exception {
        return finFundTargetService.delete(dto);
    }
    
    /**
     * 插入
     * 
     * @param dto
     *            页面选中的数据
     * @param br
     *            br
     * @return 结果
     * @throws Exception
     *             异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody FinFundTargetDto dto) throws Exception {
        return finFundTargetService.insert(dto);
    }

    /**
     * 更新
     * 
     * @param dto
     *            页面选中的数据
     * @param br
     *            br
     * @return 结果
     * @throws Exception
     *             异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody FinFundTargetDto dto) throws Exception {
        return finFundTargetService.update(dto);
    }
    /**
     * 
     * 通过金融产品代码查询dto
     * <功能详细描述>
     * @param finprodId
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @GetMapping("selectByFinprodId")
    public FinFundTargetDto selectByFinprodId(String finprodId) 
        throws Exception {
        return finFundTargetService.selectByFinprodId(finprodId);
    }
    /**
     * 通过市场代码查询dto
     * <一句话功能简述>
     * <功能详细描述>
     * @param finprodMarketId
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("findEntity")
    public FinFundTargetDto findEntity(@RequestBody Map<String,Object> param) 
        throws Exception {
        return finFundTargetService.findEntity(param);
    }
    /**
     * 
     * 给分红计划页面赋值
     * <功能详细描述>
     * @param finprodId
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
     @GetMapping("/getIntPayByFinprodId")
     public BussFinIntPayDto getIntPayByFinprodId(String finprodId) 
         throws Exception {
         return finFundTargetService.getIntPayByFinprodId(finprodId);
     }   
     /**
      * 
      * 给基金中债报送页面赋值
      * <功能详细描述> 
      * @param finprodId
      * @return
      * @throws Exception
      * @see
      * @since 1.0
      */
      @GetMapping("/getFundInfoByFinprodId")
      public BussRepFundInfoDto getFundInfoByFinprodId(String finprodId) 
          throws Exception {
          return finFundTargetService.getFundInfoByFinprodId(finprodId);
      } 
      @PostMapping("getInfoByIssuer")
      public BussRepFundInfoDto getInfoByIssuer(@RequestBody Map<String, Object> pageParam){
          return finFundTargetService.getInfoByIssuer(pageParam);
      }  
        
}
