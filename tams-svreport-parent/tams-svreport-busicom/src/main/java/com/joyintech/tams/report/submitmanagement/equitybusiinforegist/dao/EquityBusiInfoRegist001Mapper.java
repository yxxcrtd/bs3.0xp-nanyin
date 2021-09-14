/*
 * 文件名：InvestorsHoldInfoRegist001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年5月9日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.submitmanagement.equitybusiinforegist.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbEqudet;
import com.joyintech.tams.common.base.entity.ChbSubmitStatus;
import com.joyintech.tams.report.investormerger.dto.ChbEquHoldCheckDto;
import com.joyintech.tams.report.submitmaintenance.equityinfo.dto.EquityInfo001Dto;
import com.joyintech.tams.report.submitmanagement.equitybusiinforegist.dto.EquityBusiInfoRegist001Dto;


/**
 * 投资者持有信息登记mapper
 * @author zuoyinyi
 * @version 1.0 2018年5月9日
 * @see EquityBusiInfoRegist001Mapper
 * @since 1.0
 */
@Repository
public interface EquityBusiInfoRegist001Mapper {
    /**
     *
     * 查询页面数据
     * @param map 查询条件
     * @return 结果列表
     * @see
     * @since 1.0
     */
    List<EquityBusiInfoRegist001Dto> selectAllData(Map<String,Object> map);

    /**
     * 批量插入履历表
     * @param list 数据集
     * @see
     * @since 1.0
     */
    void insertInvestorsHoldInfoRegistHis(@Param("investList")List<EquityBusiInfoRegist001Dto> investList);

    /**
     * 删除履历表中已报送数据
     * @param list 数据集
     * @see
     * @since 1.0
     */
    void deleteSubmitRegist(@Param("investList")List<EquityBusiInfoRegist001Dto> investList);

    /**
     * 删除报送表中已报送数据(按照主键)
     * @param list 数据集
     * @see
     * @since 1.0
     */
    void deleteSubmitRegistByPK(List<EquityBusiInfoRegist001Dto> list);

    /**
     * 删除报送表中已报送数据(按照主键)
     * @param list 数据集
     * @see
     * @since 1.0
     */
    void deleteAllAlreadySubmitRegistData();

    /**
     * 接口报送更新
     * @param sendStatus 报送状态
     * @param investList 数据集
     * @see
     * @since 1.0
     */
    void updateInterfSubmit(@Param("sendStatus")String sendStatus, @Param("investList")List<EquityBusiInfoRegist001Dto> investList);
    /**
     * 接口报送更新
     * @param sendStatus 报送状态
     * @param investList 数据集
     * @see
     * @since 1.0
     */
    void updateInterfSubmitAndSeq(@Param("sendStatus")String sendStatus, @Param("investList")List<EquityBusiInfoRegist001Dto> investList);

    // add by etliu 20180818 start
    /**
     * 接口报送更新
     * @param sendStatus 报送状态
     * @param investList 数据集
     * @see
     * @since 1.0
     */
    void updateInterfSubmitAndSeqBatch(@Param("sendStatus")String sendStatus, @Param("investList")List<EquityBusiInfoRegist001Dto> investList);
    // add by etliu 20180818 end

    /**
     * 接口报送更新(更新所有检查通过的数据)
     * @param sendStatus 报送状态
     * @param investList 数据集
     * @see
     * @since 1.0
     */
    void updateAllInterfSubmitAndSeqBatch(@Param("sendStatus")String sendStatus);

    /**
     * 批量插入报送状态表
     * @param listStatus 报送状态
     * @return 插入条数
     * @see
     * @since 1.0
     */
    Integer insertSubmitStatus(@Param("list")List<ChbSubmitStatus> list);

    /**
     * 批量更新报送状态表为中债通过
     * @param list 报送状态
     * @return 插入条数
     * @see
     * @since 1.0
     */
    int updateSubmitStatusToOk(List<String> list);

    /**
     * 是否是已报送
     * @param map 条件
     * @return 数量
     * @see
     * @since 1.0
     */
    int isNotSubmitP(Map<String, Object> map);

    /**
     * 取消手工报送更新登记表
     * @param list EquityInfoRegist001Dto
     * @return 更新条数
     * @see
     * @since 1.0
     */
    Integer updateInvestRegist(List<EquityBusiInfoRegist001Dto> list);

    /**
     *
     * 取消手工报送删除已报送数据
     * @param list 主键
     * @return 删除条数
     * @see
     * @since 1.0
     */
    Integer deleteMannulSubmitRegist(@Param("list")List<ChbSubmitStatus> list);

    /**
     * 查询业务主键数据是否存在
     * @param map 查询条件
     * @return 条数
     * @see
     * @since 1.0
     */
    Integer selectIsExsit(Map<String, Object> map);

    /**
     * 识别标识在投资者信息表中是否已存在
     * @return 结果集
     * @see
     * @since 1.0
     */
    List<Map<String,String>> getAllRegistBankCodeSet();

    /**
     * 查询产品登记编码
     * @return 结果集
     * @see
     * @since 1.0
     */
    List<Map<String,String>> getAllRegistProdCodeSet();

    /**
     * 取消手工处理时将履历表中的数据移回登记表
     * @param list
     * @see
     * @since 1.0
     */
    void insertInvestorsHoldInfoRegist(List<EquityBusiInfoRegist001Dto> list);

    /**
     * 查询每一天已经报送的所有持有数据
     * @param paramMap paramMap
     * @param list
     * @see
     * @since 1.0
     */
    List<EquityBusiInfoRegist001Dto> selectAlreadySubmitData(Map<String, Object> paramMap);
    /**
     * 接口报送更新报送流水号
     * @param sendStatus 报送状态
     * @param investList 数据集
     * @see
     * @since 1.0
     */
    void updateInterfSeq(@Param("investList")List<EquityBusiInfoRegist001Dto> investList);


    /**
     * 批量更新投资者持有信息检查序列号
     *
     * @param investRegistUpdateList
     * @see
     * @since 1.0
     */
    void updateInvestRegistCheckSeqno(List<EquityBusiInfoRegist001Dto> investRegistUpdateList);


    int selectNoendCountByTaskBeanName(String beanname);


    /**
     * 分批量查询导出数据
     * @param investList
     * @see
     * @since 1.0
     */
    List<EquityBusiInfoRegist001Dto> selectAllDataByKey(@Param("investList")List<EquityBusiInfoRegist001Dto> investList);

    int insertBtachInterfEquityBusi(List<EquityBusiInfoRegist001Dto> list);

    int updateBatchEquityBusi(List<EquityBusiInfoRegist001Dto> list);

    int insertEquityBusiHis(List<EquityBusiInfoRegist001Dto> list);

    int insertBtachInterfEquityBusiSub(List<EquityBusiInfoRegist001Dto> list);

    int deleteBatchEquityBusi(List<EquityBusiInfoRegist001Dto> list);

    List<ChbEqudet> selectChbEqudetSubByKey();
    List<ChbEquHoldCheckDto> selectAllCheckEquHoldDto();

    /**
     * 查出所有数据状态为删除且报送状态为未报送的数据
     * @param list
     * @see
     * @since 1.0
     */
    List<EquityBusiInfoRegist001Dto> selectDeletedAndUnsubmittedData();
    /**
     * 查询所有存在的身份信息
     * @return 结果
     */
    List<EquityInfo001Dto> selectEquityInfoAll();

    /**
     * 批量更新报送状态表为中债通过
     * @param list 报送状态
     * @return 插入条数
     * @see
     * @since 1.0
     */
    int countExportNum(Map<String,Object> map);
    int selectCountSizeWithSameKey(Map<String,Object> map);
    Integer insertEquDetRegistHis(Map<String,Object> map);
    int deleteEquDetSubmitRegist(Map<String,Object> map);
    /**
     * 获取当前日期的前一个工作日
     * @param marketCode
     * @param date
     * @return
     */
    Date getLastWeekDay(@Param("marketCode") String marketCode,@Param("holidayDate") String date);
    /**
     * 当跑批类型为自动跑批时查询所有未报送的业务确认日
     * <一句话功能简述>
     * <功能详细描述>
     * @param cdate
     * @return
     */
    List<Date> selectAllUnRegistDate();
    /**
     * 查询当天所有需要报送的银行登记代码
     * <一句话功能简述>
     * <功能详细描述>
     * @param cdate
     * @return
     */
    List<String> selectAllBanksByDate(Date cdate);
    /**
     * 清空数据报送临时表
     *
     * @see
     * @since 1.0
     */
    void trunequdetSubTempTable();
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
    List<EquityBusiInfoRegist001Dto> selectAllUnregistCheckOkData();
    /**
     * 接口报送状态更新(更新所有检查通过的数据)
     * @see
     * @since 1.0
     */
    void updateSubmitStatus();

    String getChbEquSubSend(Map<String, Object> paramMap);
    /**
     * 查询当天所有通过检查的为报送数据（身份信息已报送）
     * <一句话功能简述>
     * <功能详细描述>
     * @param cdate
     * @return
     */
    Integer selectAllDataUnSendCount(@Param("cdate")Date cdate,@Param("bankcode")String bankcode);
    //add zhangjinyang 2020-12-21
    int updateBatchEquityBusiRegCodeByPart(List<EquityBusiInfoRegist001Dto> list);
}
