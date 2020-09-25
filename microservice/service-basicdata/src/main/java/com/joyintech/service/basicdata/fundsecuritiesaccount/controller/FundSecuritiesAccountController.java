/*
 * 文件名：MstSecuritiesAccountController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：lizhi
 * 修改时间：2020年1月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.fundsecuritiesaccount.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.fundsecuritiesaccount.dto.FundSecuritiesAccountDto;
import com.joyintech.service.basicdata.fundsecuritiesaccount.service.FundSecuritiesAccountService;
import com.joyintech.tams.common.base.entity.StlSecAcctInfo;


/**
 * 基金账户管理微服务
 * 〈功能详细描述〉
 * @author lizhi
 * @version 1.0 2020年1月14日
 * @see FundSecuritiesAccountController
 * @since 1.0
 */
@RestController("FundSecuritiesAccountInfoCtr_dtx")
@RequestMapping("/FundSecuritiesAccountInfo")
public class FundSecuritiesAccountController {
    /**
     * 基金管理微服务Service
     */
    @Autowired
    private FundSecuritiesAccountService fundSecuritiesAccountService;

    
    /**
     * 画面查询
     * 
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<FundSecuritiesAccountDto> selectAll(@RequestBody Map<String, Object> param,
        @PathVariable("page")int page,@PathVariable("length")int length)
        throws Exception {
        return fundSecuritiesAccountService.selectForPage(page,length,param);
    }
    
        /**
         * 根据基金账户账号进行查询
         * 
         * @return Page
         * @throws Exception Exception
         * @see
         * @since 1.0
         */
    @GetMapping("selectBySecAcctId")
        public StlSecAcctInfo selectBySecAcctId(String secAcctId)
            throws Exception {
            return fundSecuritiesAccountService.selectBySecAcctId(secAcctId);
        }

    /**
     * 基金账户账号联想文本框 <功能详细描述>
     * 
     * @return List
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @GetMapping("getSecAcctId")
    public List<SuggestDto> getSecAcctId(String secAcctId)
        throws Exception {
        List<SuggestDto> temp = fundSecuritiesAccountService.getSecAcctId(secAcctId);
        return temp;
    }
    
    /**
     * 基金管理人联想文本框 <功能详细描述>
     * 
     * @return List
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @GetMapping("getSecManager")
    public List<SuggestDto> getSecManager(String manager)
        throws Exception {
        List<SuggestDto> temp = fundSecuritiesAccountService.getSecManager(manager);
        return temp;
    }
    /**
     * 基金管理人联想文本框 <功能详细描述>
     * 
     * @return List
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @GetMapping("getSecManagerSelect")
    public List<SelectOptionDto> getSecManagerSelect(String manager)
        throws Exception {
        List<SelectOptionDto> tempList = fundSecuritiesAccountService.getSecManagerSelect(manager);
        
        return tempList ;
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
    public int delete(@RequestBody StlSecAcctInfo dto) throws Exception {
        return fundSecuritiesAccountService.delete(dto);
    }
    /**
     * 新建
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
    public int insert(@RequestBody FundSecuritiesAccountDto dto) throws Exception {
        return fundSecuritiesAccountService.insert(dto);
    }
    /**
     * 修改
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
    public int update(@RequestBody FundSecuritiesAccountDto dto) throws Exception {
        return fundSecuritiesAccountService.update(dto);
    }
    /**
     * 检查基金账户账号是否用过
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return List
     * @see
     * @since 1.0  
     */
    @PostMapping("checkBySecAcctId")
    public int checkBySecAcctId(@RequestBody FundSecuritiesAccountDto dto)
        throws Exception {
        int count = fundSecuritiesAccountService.checkBySecAcctId(dto);
        return count;
    }
    /**
     * 检查基金账户名称是否用过
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return List
     * @see
     * @since 1.0  
     */
    @PostMapping("checkBySecAcctName")
    public int checkBySecAcctName(@RequestBody FundSecuritiesAccountDto dto)
        throws Exception {
        int count = fundSecuritiesAccountService.checkBySecAcctName(dto);
        return count;
    }
    /**
     * 检查资金结算账户是否存在
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return List
     * @see
     * @since 1.0  
     */
    @PostMapping("checkByTrdAcctId")
    public int checkByTrdAcctId(@RequestBody FundSecuritiesAccountDto dto)
        throws Exception {
        int count = fundSecuritiesAccountService.checkByTrdAcctId(dto);
        return count;
    }
    /**
     * 
     *删除时检查数据是否被使用过
     * <功能详细描述>
     * @param dto 业务实体
     * @return int
     * @see
     * @since 1.0
     */
    @PostMapping("checkCodeIsUse")
    public int checkCodeIsUse(@RequestBody FundSecuritiesAccountDto dto)
        throws Exception {
        int count = fundSecuritiesAccountService.checkCodeIsUse(dto);
        return count;
    }
    /**
     * 
     *批量删除时检查数据是否被使用过
     * <功能详细描述>
     * @param item List<FundSecuritiesAccountDto>
     * @return int
     * @see
     * @since 1.0
     */
    @PostMapping("checkAllCodeIsUse")
    public List<FundSecuritiesAccountDto> checkAllCodeIsUse(@RequestBody List<FundSecuritiesAccountDto> item)
        throws Exception {
        return fundSecuritiesAccountService.checkAllCodeIsUse(item);
    }

}
