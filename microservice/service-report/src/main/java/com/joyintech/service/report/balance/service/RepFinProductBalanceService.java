/*
 * 文件名：RepFinProductBalanceService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年3月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.balance.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.balance.dao.RepFinProductBalanceMapper;
import com.joyintech.service.report.balance.dto.RepFinProductBalanceDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 产品余额表
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年3月1日
 * @see RepFinProductBalanceService
 * @since 1.0
 */
@Service("backRepFinProductBalanceService")
public class RepFinProductBalanceService extends BaseService<RepFinProductBalanceDto>{

    @Autowired
    private RepFinProductBalanceMapper repFinProductBalanceMapper;
    /**
     * 分页
     * @param page
     * @param length
     * @param pageParam
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<RepFinProductBalanceDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        if(pageParam.get("isLayer").equals(DictCodeList.是否_是.getValue())){
            return super.queryForPage(page, length, () -> repFinProductBalanceMapper.queryForPage2(pageParam));
        }
        return super.queryForPage(page, length, () -> repFinProductBalanceMapper.queryForPage1(pageParam));
    }
    
    public  List<SelectOptionDto> getFinprodIdByPortfolioId(Map<String,Object> map){
        return repFinProductBalanceMapper.getFinprodIdByPortfolioId(map);
    }
    
}
