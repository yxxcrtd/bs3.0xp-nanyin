package com.joyintech.service.entrust.etribrepo.controller;

/**
 * 多组合
 * @author 王波
 * @date 2021年01月29日
 * @see EtrIbRepoinfo003Controller
 * @since 1.0
 */

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.service.entrust.etribrepo.dto.EtribRepoInfo003Dto;
import com.joyintech.service.entrust.etribrepo.service.EtrIbRepoinfo003Service;
import com.joyintech.tams.common.base.dao.WflEtrEntrustAddMapper;
import com.joyintech.tams.common.base.dao.WflEtrEntrustMapper;
import com.joyintech.tams.common.base.entity.WflEtrEntrustAddKey;
import com.joyintech.tams.common.base.entity.WflEtrEntrustKey;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 债券投资委托微服务controller
 */
@RestController("etrIbRepoinfo003Controllere_dtx")
@RequestMapping("EtrIbRepoinfo003")
public class EtrIbRepoinfo003Controller {

    @Autowired
    private EtrIbRepoinfo003Service etrIbRepoinfo003Service;



    /**
     * 工作流完成方法 <功能详细描述>
     *
     * @param entity 实体类
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public void doComplete(@RequestBody EtribRepoInfo003Dto entity)
            throws IllegalAccessException, InvocationTargetException {

        for (EtribRepoInfo003Dto dto: entity.getEtrInvestRepoInfoDtos()) {
            etrIbRepoinfo003Service.doComplete(dto, entity.getOperateType());
        }
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doInsert")
    public void doInsert(@RequestBody EtribRepoInfo003Dto dto){
        etrIbRepoinfo003Service.doInsert(dto, dto.getOperateType());
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doDeleteEtrFlow")
    public boolean doDeleteEtrFlow(@RequestBody
                                           Map<String,Object> param){
       return etrIbRepoinfo003Service.doDeleteEtrFlow(param);
    }
}
