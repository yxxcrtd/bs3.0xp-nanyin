/*
 * 文件名：CSTPSelectService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.cstp.service;
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
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.trade.cstp.dao.CstpSelectMapper;
import com.joyintech.service.trade.cstp.dto.CstpSelectDto;

/**
 * cstp下行交易查询Service
 * @author fanshuanghu
 * @version 1.0 2018年12月3日
 * @see CstpSelectService
 * @since 1.0
 */
@Service
public class CstpSelectService extends BaseService<CstpSelectDto>{
    /**
     * 注入Mapper
     */
    @Autowired 
    private CstpSelectMapper cstpSelectMapper;
    /**
     * 分页查询
     * @param page 页面数
     * @param length 长度
     * @param map 请求参数
     * @return 分页查询结果
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<CstpSelectDto> findPage(int page,int length,Map<String,Object> map) throws Exception{
        return super.queryForPage(page, length, () -> cstpSelectMapper.findPage(map));
    }
    /**
     * 获取组合代码下拉框实体类
     * @param map 查询参数
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPortfolioId(Map<String,Object> map){
        return cstpSelectMapper.getPortfolioId(map);
    }
    /**
     * 获取成交编号下拉框实体类
     * @param map 查询参数
     * @return 下拉框集合
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getExecid(int page,int length,Map<String,Object> map){
        Page<SelectOptionDto> pages = this.queryForPageForSelectReturn(page, length, () -> cstpSelectMapper.getExecid(map));
        return pages;
    }
    
    
    public Page<SelectOptionDto> getBusiCode(int page,int length,Map<String,Object> map){
        Page<SelectOptionDto> pages = this.queryForPageForSelectReturn(page, length, () -> cstpSelectMapper.getBusiCode(map));
        return pages;
    }
    /**
     * 获取资产代码下拉框
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getFinprodId(Map<String,Object> map){
        return cstpSelectMapper.getFinprodId(map);
    }
}
