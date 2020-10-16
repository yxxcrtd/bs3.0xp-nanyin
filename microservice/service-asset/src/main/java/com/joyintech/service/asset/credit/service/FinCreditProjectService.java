/*
 * 文件名：FinCreditProjectService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.credit.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.asset.credit.dao.FinCreditProjectMapper;
import com.joyintech.service.asset.credit.dto.InterfCreditItemInfoDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月15日
 * @see FinCreditProjectService
 * @since 1.0
 */
@Service("backFinCreditProjectService")
public class FinCreditProjectService extends BaseService<InterfCreditItemInfoDto>{

    @Autowired
    private FinCreditProjectMapper finCreditProjectMapper;
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
    public Page<InterfCreditItemInfoDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page, length, () -> finCreditProjectMapper.queryForPage(pageParam));
    }
    
    
    public int update(InterfCreditItemInfoDto dto){
        return finCreditProjectMapper.update(dto);
    }
    /**
     * 出账编号模糊文本框
     * <功能详细描述>
     * @param search
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getCashOutNo(String search){
            return finCreditProjectMapper.getCashOutNo(search);
    }
    
}
