/*
 * 文件名：RskRelatedPartyService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年10月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.namelistmanage.relatedparty.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.risk.namelistmanage.relatedparty.dao.RskRelatedParty001Mapper;
import com.joyintech.service.risk.namelistmanage.relatedparty.dto.RskRelatedPartyDto;
import com.joyintech.tams.common.base.entity.RskRelatedParty;

/**
 * 关联方管理
 * 
 * @author zhangrui1
 * @version 1.0 2020年10月14日
 * @see RskRelatedPartyService
 * @since 1.0
 */
@Service
public class RskRelatedPartyService extends BaseService<RskRelatedPartyDto> {

    @Autowired
    private RskRelatedParty001Mapper mapper;

    /**
     * 分页查询关联方管理记录
     * 
     * @param page 页码
     * @param length 页长
     * @param pageParam 查询条件
     * @return 分页关联方管理记录
     */
    public Page<RskRelatedPartyDto> selectForPage(int page, int length,
                                                  Map<String, Object> pageParam) {
        return queryForPage(page, length, () -> mapper.queryForPage(pageParam));
    }

    /**
     * 分页查询关联方名称列表
     * 
     * @param page 页码
     * @param length 页长
     * @param pageParam 查询条件
     * @return 关联方名称列表
     */
    public Page<SuggestDto> selectInstituteName(int page, int length,
                                                Map<String, Object> pageParam) {
        return queryForPageForSelectReturn(page, length,
            () -> mapper.selectInstituteName(pageParam));
    }

    /**
     * 批量登录
     * 
     * @param dtoList 关联方管理记录
     * @return 处理结果
     */
    public int insertList(List<RskRelatedPartyDto> dtoList) {
        return mapper.insertList(dtoList);
    }

    /**
     * 批量删除
     * 
     * @param codeList 社会统一信用代码
     * @return 处理结果
     */
    public int deleteList(List<String> codeList) {
        return mapper.deleteList(codeList);
    }

    /**
     * 查询关联方信息
     * 
     * @param keyList 机构代码
     * @return 关联方信息
     */
    public List<RskRelatedParty> queryInfoByPrimaryKey(List<String> keyList) {
        if (CollectionUtils.isEmpty(keyList)) {
            return new ArrayList<>();
        }
        return mapper.queryInfoByPrimaryKey(keyList);
    }

    /**
     * 查询社会统一信用代码
     * 
     * @param keyList 社会统一信用代码
     * @return 社会统一信用代码
     */
    public List<String> selectInstituteCode(List<String> codeList) {
        return mapper.selectInstituteCode(codeList);
    }

    public int edit(RskRelatedPartyDto dto) {
        dto.setLimitAmt(dto.getLimitAmt().multiply(new BigDecimal(10000)));
        return mapper.update(dto);
    }
}
