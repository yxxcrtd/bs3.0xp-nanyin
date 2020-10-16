/*
 * 文件名：MstIndexQuotationInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.mstindexquotationinfo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.MstExponentQuotationMapper;
import com.joyintech.tams.common.base.entity.MstExponentQuotation;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.mstindexquotationinfo.dao.MstIndexQuotationInfoMapper;
import com.joyintech.service.basicdata.mstindexquotationinfo.dto.MstIndexQuotationInfo001Dto;

/**
 * 指数行情信息service类
 * 
 * @author fanshuanghu
 * @version 1.0 2018年8月14日
 * @see MstIndexQuotationInfoService
 * @since 1.0
 */
@Service("backMstIndexQuotationInfoService")
public class MstIndexQuotationInfoService extends BaseService<MstIndexQuotationInfo001Dto>{

    /**
     * 实体类的Mapper注入
     */
    @Autowired
    private MstIndexQuotationInfoMapper mstIndexQuotationInfo001Mapper;

    /**
     * 表自动生成的实体类Mapper注入
     */
    @Autowired
    private MstExponentQuotationMapper mstExponentQuotationMapper;

    /**
     * 分页查询
     * @param length 数目
     * @param page 页数
     * 
     * @param map 查询参数
     * @return Page<MstIndexQuotationInfo001Dto> 实体类泛型的page类
     * @throws Exception 错误
     * @see
     * @since 1.0
     */
    public Page<MstIndexQuotationInfo001Dto> selectForPage(int page, int length, Map<String, Object> map)
        throws Exception {
        return super.queryForPage(page,length,() -> mstIndexQuotationInfo001Mapper.queryForPage(map));
    }

    /**
     * 实现docomplement方法
     */
    public void doComplete(MstIndexQuotationInfo001Dto entity) {
        String type = entity.getOperateType();
        if (StringUtil.equals(type, "DELETE")) {
            // 调用父类的主键删除方法
            mstExponentQuotationMapper.deleteByPrimaryKey(entity);
        }
        else if (StringUtil.equals(type, "NEW")) {
            // 调用父类的插入方法
            mstExponentQuotationMapper.insert(entity);
        }
        else {
            // 调用父类的主键修改方法
            mstExponentQuotationMapper.updateByPrimaryKey(entity);
        }

    }

    /**
     * 根据key获取对象
     * <功能详细描述>
     * @param dto key
     * @return 结果
     * @see
     * @since 1.0
     */
    public MstExponentQuotation selectByPrimaryKey(MstIndexQuotationInfo001Dto dto) {
        return mstExponentQuotationMapper.selectByPrimaryKey(dto);
    }
}
