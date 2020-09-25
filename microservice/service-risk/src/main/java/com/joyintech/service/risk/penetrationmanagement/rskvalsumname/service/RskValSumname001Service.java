/*
 * 文件名：RskValSumname001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.penetrationmanagement.rskvalsumname.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.risk.penetrationmanagement.rskvalsumname.dao.RskValSumnameMapper;
import com.joyintech.service.risk.penetrationmanagement.rskvalsumname.dto.RskValSumnameDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年12月17日
 * @see RskValSumname001Service
 * @since 1.0
 */
@Service
public class RskValSumname001Service extends BaseService<RskValSumnameDto>{
    @Autowired
    private RskValSumnameMapper rskValSumnameMapper;
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<RskValSumnameDto> getSumName(Map<String, Object> map) {
        return rskValSumnameMapper.getSumName(map);
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param page
     * @param length
     * @param param
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<RskValSumnameDto> selectForPage(int page, int length, Map<String, Object> pageParam) throws Exception {
        return super.queryForPage(page, length, () -> {
            rskValSumnameMapper.selectForPage(pageParam);
        });
    }

    public List<RskValSumnameDto> selectForList(String sumId){
        return rskValSumnameMapper.selectForList(sumId);
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public boolean getCountById(RskValSumnameDto dto) {
        return rskValSumnameMapper.getCountById(dto)>0;
    }
    
}
