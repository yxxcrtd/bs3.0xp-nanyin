/*
 * 文件名：MstCustomerNature001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年8月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.mstcustomernature.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.mstcustomernature.dto.MstCustomerNatureDto;
import com.joyintech.service.basicdata.mstcustomernature.service.MstCustomerNatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 客服性质管理
 * 〈一句话功能简述〉 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年8月16日
 * @see MstCustomerNatureController
 * @since 1.0
 */
@RestController("MstCustomerNatureCtr_dtx")
@RequestMapping("/MstCustomerNature")
public class MstCustomerNatureController  {

    /**
     *  客服性质管理业务类
     */
    @Autowired
    private MstCustomerNatureService mstCustomerNatureService;

    /**
     * 客户名称的联想文本框的查询方法 
     * @param param 画面参数
     * @return list
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/getCustomerName")
    public List<SuggestDto> getCustomerName(@RequestBody Map<String, Object> param)
        throws Exception {
        List<SuggestDto> list = mstCustomerNatureService.getCustomerName(param);
        return list;
    }
    
    /**
     * 客户的联想文本框的查询方法
     * @param param map
     * @return List
     * @see
     * @since 1.0
     */
    @PostMapping("/getCustomerId")
    public List<SuggestDto> getCustomerId(@RequestBody Map<String, Object> param) {
        return mstCustomerNatureService.getCustomerId(param);
    }
    
    /**
     * 客户名称的联想文本框的查询方法
     * @param param map
     * @return List
     * @see
     * @since 1.0
     */
    @PostMapping("/getName")
    public List<SuggestDto> getName(@RequestBody Map<String, Object> param) {
        return mstCustomerNatureService.getName(param);
    }
    /**
     * 客服性质配置查询按钮的查询方法 
     * @param pageParam map
     * @return Page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/searchMstCustomerNature/{page}/{length}")
    public Page<MstCustomerNatureDto> searchMstCustomerNature(@RequestBody Map<String, Object> pageParam,@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return mstCustomerNatureService.searchMstCustomerNature(page,length,pageParam);
    }
    /**
     * 客服性质管理查询按钮的查询方法
     * @param pageParam map
     * @return Page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/selectCustomerInfo/{page}/{length}")
    public Page<MstCustomerNatureDto> selectCustomerInfo(@RequestBody Map<String, Object> pageParam,@PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return mstCustomerNatureService.selectCustomerInfo(page,length,pageParam);
    }
    /**
     * 通过主键的查询方法
     * @param param map
     * @return Page
     * @see
     * @since 1.0
     */
    @PostMapping("/selectByPrimaryKey")
    public MstCustomerNatureDto selectByPrimaryKey(@RequestBody Map<String, Object> param) {
        return mstCustomerNatureService.selectByPrimaryKey(param);
    }
    /**
     * 
     * 工作流完成操作
     * <功能详细描述>
     * @param entity MstCustomerNatureDto
     * @param type 类型
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("/doComplete/{type}")
    public void doComplete(@RequestBody MstCustomerNatureDto entity, @PathVariable("type") String type) {
         mstCustomerNatureService.doComplete(entity, type);
    }
    
    /**
     * 根据外部代码1获取客户代码（用于自动加券）
     * <一句话功能简述>
     * <功能详细描述>
     * @param linkId1
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("/getIssuer")
    public String getIssuer(String linkId1, String type){
        return mstCustomerNatureService.getIssuer(linkId1, type);
    }
    
    
    /**
     * check是否持有金融牌照
     * @param param customerId
     * @return 
     * @see
     * @since 1.0
     */
    @PostMapping("/checkIsFinLicenceStr")
    public String checkIsFinLicenceStr(@RequestBody String customerId) {
        return mstCustomerNatureService.checkIsFinLicenceStr(customerId);
    }
    
    /**
     *  check同一个客户是否已有在流程中的客户性质
     * @param param map
     * @return 
     * @see
     * @since 1.0
     */
    @PostMapping("/checkwflstatus")
    public int checkwflstatus(@RequestBody Map<String, Object> param) {
        int num = mstCustomerNatureService.checkwflstatus(param);
        return num;
    }
    
}
