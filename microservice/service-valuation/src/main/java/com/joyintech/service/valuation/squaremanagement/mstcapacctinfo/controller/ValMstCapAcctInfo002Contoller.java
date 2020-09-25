/*
 * 文件名：ValMstCapAcctInfo002Contoller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.squaremanagement.mstcapacctinfo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.squaremanagement.mstcapacctinfo.dao.ValMstCapAcctInfoMapper;
import com.joyintech.service.valuation.squaremanagement.mstcapacctinfo.dto.ValMstCapAcctInfoDto;

@Controller
@RestController("valMstCapAcctInfo002Ctl_dtx")
@RequestMapping("/ValMstCapAcctInfo002")
public class ValMstCapAcctInfo002Contoller extends BaseController {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    @Autowired
    private ValMstCapAcctInfoMapper valMstCapAcctInfoMapper;

    @GetMapping("/queryValMstAcctInfoByAcctno")
    public ValMstCapAcctInfoDto queryValMstAcctInfoByAcctno(String acctNo) {
        return valMstCapAcctInfoMapper.queryValMstAcctInfoByAcctno(acctNo);
    }
    
    @Transactional
    @DTXTransaction
    @PostMapping("/save")
    public String save(@RequestBody ValMstCapAcctInfoDto dto) {
        String msg = "";
        String dealType = dto.getDealType();
        ValMstCapAcctInfoDto ndto = new ValMstCapAcctInfoDto();
        if (!StringUtil.isNotEmpty(dto.getAcctNo())) {
            msg = "账号不能为空！";
            return msg;
        }
        if (!StringUtil.isNotEmpty(dto.getAcctName())) {
            msg = "户名不能为空！";
            return msg;
        }
        if (!StringUtil.isNotEmpty(dto.getBankDep())) {
            msg = "开户行不能为空！";
            return msg;
        }
        if (StringUtil.isNotEmpty(dealType) && dealType.equals("submit")) {
            ndto.setAcctName(dto.getAcctName());
            ndto.setAcctNo(dto.getAcctNo());
            ndto.setBankDep(dto.getBankDep());
            ndto.setCorrNo(dto.getCorrNo());
            ndto.setCyy(dto.getCyy());
            ndto.setInOut(dto.getInOut());
            ndto.setInOutBank(dto.getInOutBank());
            ndto.setIsDvp(dto.getIsDvp());
            ndto.setIsOverdraw(dto.getIsOverdraw());
            ndto.setLargePayno(dto.getLargePayno());
            int num = valMstCapAcctInfoMapper.updateValMstAcctInfoByAcctno(ndto);
            if (num > 0) {
                msg = "提交成功！";
            }
        }
        else {
            BaseEntity entity = new BaseEntity();
            ndto.setAcctName(dto.getAcctName());
            ndto.setAcctNo(dto.getAcctNo());
            ndto.setBankDep(dto.getBankDep());
            ndto.setCorrNo(dto.getCorrNo());
            ndto.setCyy(dto.getCyy());
            ndto.setInOut(dto.getInOut());
            ndto.setInOutBank(dto.getInOutBank());
            ndto.setIsDvp(dto.getIsDvp());
            ndto.setIsOverdraw(dto.getIsOverdraw());
            ndto.setLargePayno(dto.getLargePayno());
            Date date = new Date();
            ndto.setCreateTime(date);
            ndto.setCreateUser(entity.getCreateUser());
            ndto.setCreateDept(entity.getCreateDept());
            ndto.setUpdateTime(date);
            ndto.setUpdateUser("admin");
            int result = valMstCapAcctInfoMapper.checkValMstAcctInfoByAcctno(dto.getAcctNo());
            if (result > 0) {
                msg = "该账号已存在！";
                return msg;
            }
            int num = valMstCapAcctInfoMapper.insertValMstCapAcctInfo(ndto);
            if (num > 0) {
                msg = "保存成功！";
            }
        }
        return msg;
    }

}
