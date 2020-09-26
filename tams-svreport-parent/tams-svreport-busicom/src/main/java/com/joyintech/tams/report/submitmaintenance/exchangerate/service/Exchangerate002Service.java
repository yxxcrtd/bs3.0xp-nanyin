/*
 * 文件名：EquityBaseInfo001Service.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中债报送子系统
 * 修改人：何阳阳
 * 修改时间：2017年9月14日
 * 修改内容：新建
 * 系统名称：中债报送子系统
 */

package com.joyintech.tams.report.submitmaintenance.exchangerate.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.ChbMstExchangeInfoMapper;
import com.joyintech.tams.common.base.entity.ChbMstExchangeInfo;
import com.joyintech.tams.report.submitmaintenance.exchangerate.dao.Exchangerate001Mapper;
import com.joyintech.tams.report.submitmaintenance.exchangerate.dto.Exchangerate002Dto;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 〈一句话功能简述〉
 *
 * @author zyf
 * @version 1.0 2017年9月18日
 * @see com.joyintech.submitmaintenance.exchangerate.service.Exchangerate002Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class Exchangerate002Service extends BaseService<Exchangerate002Dto> {

    /**
     * EquityBusiDetails001Mapper
     */
    @Autowired
    private Exchangerate001Mapper exchangerate001Mapper;
    /**
     * mstExchangeInfoMapper
     */
    @Autowired
    private ChbMstExchangeInfoMapper mstExchangeInfoMapper;
    /**
     * 根据主键信息查询用户信息
     *
     * @param paramMap 参数map
     * @return SystemUserDto
     */
    public Exchangerate002Dto getInfoData(Map<String,Object> paramMap) {
        Exchangerate002Dto dto = exchangerate001Mapper.getInfoDataByPrimaryKey(paramMap);
        if (dto != null && dto.getExchangeRate() != null) {
            dto.setExchangeRate(ArithmeticUtil.round(dto.getExchangeRate(), 6, RoundingMode.HALF_UP));
        }
        return dto;
    }

    /**
     * 新建页面的数据保存操作
     * @param dto 页面数据
     * @see
     * @since 1.0
     */
    public void saveData(Exchangerate002Dto dto) {
        ChbMstExchangeInfo mstExchangeInfo=new ChbMstExchangeInfo();
        //折算币种
        mstExchangeInfo.setCcyConvert(dto.getCcyConvert());
        //币种
        mstExchangeInfo.setCcyOriginal(dto.getCcyOriginal());
        //数据状态
        mstExchangeInfo.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
        //公布日期
        mstExchangeInfo.setPublishDate(DateUtil.parse(dto.getPublishDate()));
        //汇率
        mstExchangeInfo.setExchangePrice(new BigDecimal(dto.getExchangePrice()));

        // 不做权限控制
        mstExchangeInfo.setCreateDept(null);

        if(StringUtil.equals(dto.getPageModel(),GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            mstExchangeInfoMapper.insert(mstExchangeInfo);
        }
        else{
            mstExchangeInfoMapper.updateByPrimaryKey(mstExchangeInfo);
        }
    }
}
