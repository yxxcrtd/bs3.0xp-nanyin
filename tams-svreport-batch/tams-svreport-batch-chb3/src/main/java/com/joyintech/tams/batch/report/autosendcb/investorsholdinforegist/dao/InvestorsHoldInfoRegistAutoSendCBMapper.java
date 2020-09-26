/*
 * 文件名：InvestorsHoldInfoRegistAutoSendCBMapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：刘二通
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.batch.report.autosendcb.investorsholdinforegist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dto.InvestorsHoldInfoRegist001Dto;

/**
 * 投资者持有信息数据自动检查报送Mapper
 *
 * @author 刘二通
 * @version 1.0 2018年10月10日
 * @see InvestorsHoldInfoRegistAutoSendCBMapper
 * @since 1.0
 */
@Repository
public interface InvestorsHoldInfoRegistAutoSendCBMapper {

    /**
     * 查询所有未报送数据条数开始
     *
     * @param paramMap 参数
     * @return 所有未报送数据条数
     * @see
     * @since 1.0
     */
    int selectAllUnregistDataCount(Map<String, Object> paramMap);

    /**
     * 查询所有未报送、但对应身份信息未报送数据条数
     *
     * @param paramMap 参数
     * @return 所有未报送、但对应身份信息未报送数据条数
     * @see
     * @since 1.0
     */
    int selectAllUnregistCheckNgDataCount(Map<String, Object> paramMap);
    
    /**
     * 删除前数据进履历表
     * @param paramMap 参数
     * @see
     * @since 1.0
     */
    void insertIntoHisAllAlreadySubmitRegistData(Map<String, Object> paramMap);
    
    /**
     * 删除重复已报送数据
     * @param paramMap 参数
     * @see
     * @since 1.0
     */
    void deleteAllAlreadySubmitRegistData(Map<String, Object> paramMap);

    /**
     * 清空数据报送临时表
     *
     * @see
     * @since 1.0
     */
    void truncateSubTempTable();

    /**
     * 将待处理数据的报送流水号插入临时表
     *
     * @param paramMap 参数
     * @see
     * @since 1.0
     */
    void insertUnregistCheckOkDataToTempTable(Map<String, Object> paramMap);

    /**
    * 查询所有未报送、对应身份信息也已报送数据
    * @return 结果列表
    * @see
    * @since 1.0
    */
   List<InvestorsHoldInfoRegist001Dto> selectAllUnregistCheckOkData();

   /**
    * 接口报送状态更新(更新所有检查通过的数据)
    * @see
    * @since 1.0
    */
   void updateSubmitStatus();
   
   /**
    * 根据中债五要素查询当前持有日期下是否存在重复数据
    * @param paramMap
    * @return
    */
   List<String> selectUnregistDataRepeatDataByFiveElem(Map<String, Object> paramMap);
}
