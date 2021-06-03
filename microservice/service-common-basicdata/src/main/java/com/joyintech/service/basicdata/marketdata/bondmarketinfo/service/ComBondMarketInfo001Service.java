/*
 * 文件名：ValBondMarketInfo001Service.java 
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.basicdata.marketdata.bondmarketinfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.basicdata.marketdata.bondmarketinfo.dao.ComBondMarketInfo001Mapper;
import com.joyintech.service.basicdata.marketdata.bondmarketinfo.dto.ComBondMarketInfo001Dto;

/**
 * 每日债券行情
 * @author Administrator
 * @version 1.0 2018年7月18日
 * @see ComBondMarketInfo001Service
 * @since 1.0
 */
@Service(value="serviceComBondMarketInfo001Service")
public class ComBondMarketInfo001Service extends CommonExportService<ComBondMarketInfo001Dto> {
    /**
     * 定义mapper(mine)
     */
    @Autowired
    private ComBondMarketInfo001Mapper comBondMarketInfo001Mapper;

    
    /**
     * 
     *每日债券行情查询
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public Page<ComBondMarketInfo001Dto> queryPageList(int page,int length,Map<String, Object> params) throws Exception{
        return super.queryForPage(page,length,()->comBondMarketInfo001Mapper.queryPageList(params));
//        return comBondMarketInfo001Mapper.queryPageList(dto);
        
    }
    
    
    public List<ComBondMarketInfo001Dto> queryAllList(Map<String, Object> params) throws Exception{
        
        return comBondMarketInfo001Mapper.queryPageList(params);
    }

    /**
     * 删除操作
     */
    public int delete(ComBondMarketInfo001Dto dto) {
        return comBondMarketInfo001Mapper.deleteByPrimaryKey(dto);
    }

    /**
     * 新建操作
     */
    public int insert(ComBondMarketInfo001Dto dto) {
        return comBondMarketInfo001Mapper.insert(dto);
    }

    /**
     * 更新操作
     */
    public int update(ComBondMarketInfo001Dto dto) {
        return comBondMarketInfo001Mapper.updateByPrimaryKey(dto);
    }

    /**
     * 查询资产代码
     * @return
     */
    public Page<SelectOptionDto> getFinProductList(Map<String, Object> pageParam, int page, int length){
        return this.queryForPageForSelectReturn(page, length,() -> comBondMarketInfo001Mapper.getFinProductList(pageParam));
    }

    /**
     * 查询符合条件的dto数据
     * @param pageParam
     * @return
     */
    public List<ComBondMarketInfo001Dto> queryDtoByKey(Map<String, Object> pageParam){
        return comBondMarketInfo001Mapper.queryDtoByKey(pageParam);
    }
    
    /**
     * 保存导入数据
     * 
     * @param list 需保存的数据
     * @see
     * @since 1.0
     */
    public void mergeInfoList(List<ComBondMarketInfo001Dto> list){
        int batchCount = 50;// 每批commit的个数
        int batchLastIndex = batchCount;// 每批最后一个的下标
        for (int index = 0; index < list.size();) {
            if (batchLastIndex > list.size() - 1) {
                batchLastIndex = list.size();
                comBondMarketInfo001Mapper.mergeInfoList(list.subList(index, batchLastIndex));
                break;// 数据插入完毕,退出循环
            }
            else {
            	comBondMarketInfo001Mapper.mergeInfoList(list.subList(index, batchLastIndex));
                index = batchLastIndex;// 设置下一批下标
                batchLastIndex = index + batchCount;
            }
        }
    }

    /**
     * 根据产品代码查找市场代码
     * @param finprodId
     * @return
     */
    public String queryFinprodMarketIdByFinprodId(String finprodId) {
        return comBondMarketInfo001Mapper.queryFinprodMarketIdByFinprodId(finprodId);
    }
}
