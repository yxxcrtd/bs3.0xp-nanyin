/*
 * 文件名：mstMarketCalendarInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.marketdata.mstmarketcalendarinfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.MstCalendarInfo;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.basicdata.marketdata.mstmarketcalendarinfo.dao.ComMarketCalendarInfoMapper;
import com.joyintech.service.basicdata.marketdata.mstmarketcalendarinfo.dto.ComCalendarInfoDto;
import com.joyintech.service.basicdata.marketdata.mstmarketcalendarinfo.dto.ComMarketCalendarInfo001Dto;

/**
 * 市场日历信息service层 〈功能详细描述〉
 * 
 * @author xiexinting
 * @version 1.0 2018年8月20日
 * @see ComMarketCalendarInfoService
 * @since 1.0
 */
@Service("serviceComMarketCalendarInfoService")
public class ComMarketCalendarInfoService
    extends BaseService<ComMarketCalendarInfo001Dto>{

    /**
     * 
     */
    @Autowired
    private ComMarketCalendarInfoMapper comMarketCalendarInfoMapper;

    /**
     * 市场日历信息查询 <功能详细描述>
     * 
     * @param param 查询参数
     * @return 查询结果
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    public Page<ComMarketCalendarInfo001Dto> selectInfo(int page, int length, Map<String, Object> param)
        throws Exception {
        return super.queryForPage(page,length,()-> comMarketCalendarInfoMapper.selectInfo(param));
    }

    /**
     * 市场日历类型下拉框查询 <功能详细描述>
     * @param parm 参数
     * @return 返回结果
     * @see
     * @since 1.0
     */
    public List<ComCalendarInfoDto> selectCalendarInfo(Map<String,Object>parm) {
        return comMarketCalendarInfoMapper.selectCalendarInfo(parm);
    }

    /**
     * 
     * 根据key获取dto
     * <功能详细描述>
     * @param dto key
     * @return 数据
     * @see
     * @since 1.0
     */
    public ComMarketCalendarInfo001Dto getMstMarketCalendarInfo001DtoByKey(ComMarketCalendarInfo001Dto dto) {
        return comMarketCalendarInfoMapper.getMstMarketCalendarInfo001DtoByKey(dto);
    }
    /**
     * 
     * 插入数据
     * <功能详细描述>
     * @param list 参数
     * @see
     * @since 1.0
     */
    public void insertBtachEquityInfo(List<ComMarketCalendarInfo001Dto> list){
        comMarketCalendarInfoMapper.insertBtachEquityInfo(list);
    }
    /**
     * 
     * 更新数据
     * <功能详细描述>
     * @param list 参数
     * @see
     * @since 1.0
     */
    public void  updateBtachEquityInfo(List<ComMarketCalendarInfo001Dto> list){
        comMarketCalendarInfoMapper.updateBtachEquityInfo(list);
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param entity
     * @param type
     * @see
     * @since 1.0
     */
    public void doComplete(ComMarketCalendarInfo001Dto entity) {
      if (entity.getYorn()==0) {
          // 工作日设节假日
          entity.setYorn(1);
          comMarketCalendarInfoMapper.sethday(entity);
      }
      else if (entity.getYorn()==1) {
          // 节假日设工作日
          entity.setYorn(0);
          comMarketCalendarInfoMapper.setwday(entity);
      }
    }
    
    /**
     * 日历信息--用于wind 自动加券
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<MstCalendarInfo> getMstCalendarInfo(){
        return comMarketCalendarInfoMapper.getMstCalendarInfo();
    }
}
