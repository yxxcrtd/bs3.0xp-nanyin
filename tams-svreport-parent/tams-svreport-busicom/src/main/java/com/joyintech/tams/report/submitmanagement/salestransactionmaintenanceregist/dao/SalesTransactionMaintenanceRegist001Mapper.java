/*
 * 文件名：EquityInfoRegist001Dao.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：Administrator
 * 修改时间：2017年9月19日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.submitmanagement.salestransactionmaintenanceregist.dao;

import com.joyintech.tams.common.base.entity.ChbSubmitStatus;
import com.joyintech.tams.report.submitmaintenance.businessdetailed.dto.EquityBusiInfo001Dto;
import com.joyintech.tams.report.submitmanagement.salestransactionmaintenanceregist.dto.SalesTransactionMaintenanceRegist001Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2017年9月19日
 * @see EquityInfoRegist001Dao
 * @since 1.0
 */
@Repository
public interface SalesTransactionMaintenanceRegist001Mapper {
   /**
    * 查询界面上条件
    * @param param
    * @return
    * @see
    * @since 1.0
    */
    List<SalesTransactionMaintenanceRegist001Dto> queryForRegistPage(Map<String,Object> param);

 /**
  * 导出查询
  * @param
  * @return
  */
    List<SalesTransactionMaintenanceRegist001Dto> queryForRegist(Map<String,Object> pageParam);

    int insertDeleteEquityReg(SalesTransactionMaintenanceRegist001Dto SalesTransactionMaintenanceRegist001Dto);

    List<SalesTransactionMaintenanceRegist001Dto> queryForInfoRegist(Map<String,Object> param);

    List<SalesTransactionMaintenanceRegist001Dto> queryBatchForEquityInfoRegist(List<Map<String, String>> list);

    int insertBatchEquityRegist(List<SalesTransactionMaintenanceRegist001Dto> list);

    int deleteBatchEquityRegist(List<SalesTransactionMaintenanceRegist001Dto> list);

    int insertExcelEquityRegist(List<SalesTransactionMaintenanceRegist001Dto> list);

    int updateExportRegDel(@Param("submitStatus")String submitStatus,
                           @Param("remark")String remark,
                           @Param("equityInfoRegistList") List<SalesTransactionMaintenanceRegist001Dto> bankList);
    /**
     * 接口报送更新
     * @param bankList 输入条件
     * @return 更新条数
     * @see
     * @since 1.0
     */
    int updateInterfSubmit(@Param("submitStatus")String submitStatus,@Param("equityInfoRegistList") List<SalesTransactionMaintenanceRegist001Dto> bankList);

    /**
     * 接口报送更新(手工处理，将接口报送状态修改为手工处理)
     * @param bankList 输入条件
     * @return 更新条数
     * @see
     * @since 1.0
     */
    int updateInterfSubmitForAlreadySendData(@Param("submitStatus")String submitStatus,@Param("equityInfoRegistList") List<SalesTransactionMaintenanceRegist001Dto> bankList);


    /**
     * 接口报送更新(更新所有检查通过的数据)
     * @param bankList 输入条件
     * @return 更新条数
     * @see
     * @since 1.0
     */
    int updateAllInterfSubmit(@Param("submitStatus")String submitStatus);

    /**
     * 接口报送更新报送流水号（为解决报送过程中出现空流程号）
     * @param bankList 输入条件
     * @return 更新条数
     * @see
     * @since 1.0
     */
    int updateInterfSubmitSeq(@Param("equityInfoRegistList") List<SalesTransactionMaintenanceRegist001Dto> bankList);

    /**
     * 通过主键查询选择的数据
     * @param registList
     * @return 权益人登记信息列表
     * @see
     * @since 1.0
     */
    List<SalesTransactionMaintenanceRegist001Dto> queryExportListByKeys(@Param("registList") List<SalesTransactionMaintenanceRegist001Dto> registList);
     /**
      * 通过主键查询选择的数据
      * @param equityRegistList
      * @return 权益人登记信息列表
      * @see
      * @since 1.0
      */
     List<SalesTransactionMaintenanceRegist001Dto> queryRegistSubmitListByKeys(@Param("equityRegistList") List<SalesTransactionMaintenanceRegist001Dto> equityRegistList);

     /**
      * 查询登记表中所有已报送的数据
      * @return 权益人登记信息列表
      * @see
      * @since 1.0
      */
     List<SalesTransactionMaintenanceRegist001Dto> queryAllRegistSubmitList();

    /**
     * 接口批量导入
     * <一句话功能简述>
     * <功能详细描述>
     * @param list
     * @return
     * @see
     * @since 1.0
     */
    int insertBatchInterfEquityRegist(List<SalesTransactionMaintenanceRegist001Dto> list);
    /**
     * 批量插入履历表
     * @param list
     * @return
     * @see
     * @since 1.0
     */
    int insertEquityRegistHis(List<SalesTransactionMaintenanceRegist001Dto> list);
    /**
     *
     * 删除履历表中已报送数据
     * @param list 主键
     * @return 删除条数
     * @see
     * @since 1.0
     */
    int deleteSubmitRegist(List<SalesTransactionMaintenanceRegist001Dto> list);
    /**
     * 批量插入报送状态表
     * @param list 报送状态
     * @return 插入条数
     * @see
     * @since 1.0
     */
    int insertSubmitStatus(List<ChbSubmitStatus> list);

    /**
     * 批量更新报送状态表为中债通过
     * @param list 报送状态
     * @return 插入条数
     * @see
     * @since 1.0
     */
    int updateSubmitStatusToOk(List<String> list);

    /**
     * 根据识别标识
     * @param indentifyMarkList
     * @return匹配投资人信息
     */
    List<SalesTransactionMaintenanceRegist001Dto> getEirListByindentifyMarkList(@Param("indentifyMarkList")List<EquityBusiInfo001Dto> indentifyMarkList);
    /**
     * 根据识别标识 查询单条权益人是否已报送
     * @param indentifyMarkList
     * @return匹配投资人信息
     */
    SalesTransactionMaintenanceRegist001Dto getEirListByindentifyMark(@Param("registBankCode")String registBankCode,@Param("indentifyMark")String indentifyMark);
    /**
     * 通过机构ID与识别标识
     * @param orgCode 机构ID
     * @param mark
     * @return
     * @see
     * @since 1.0
     */
    SalesTransactionMaintenanceRegist001Dto getEquityRegistByOrgAndMark(@Param("orgCode") String orgCode,@Param("mark") String mark);

    /**
     * 取消手工报送更新登记表
     * @param list SalesTransactionMaintenanceRegist001Dto
     * @return 更新条数
     * @see
     * @since 1.0
     */
    int updateEquityRegist(@Param("list")List<SalesTransactionMaintenanceRegist001Dto> list);

    /**
     *
     * 取消手工报送删除已报送数据
     * @param list 主键
     * @return 删除条数
     * @see
     * @since 1.0
     */
    int deleteMannulSubmitRegist(@Param("list")List<ChbSubmitStatus> list);

    /**
     * 是否是已报送
     * <功能详细描述>
     * @param map 条件
     * @return 数量
     * @see
     * @since 1.0
     */
    int isNotSubmitP(Map<String, Object> map);

    /**
     * 统计识别标识
     * <功能详细描述>
     * @param temp 参数
     * @return 数值
     * @see
     * @since 1.0
     */
    int countIndentifyMark(Map<String, String> temp);

    /**
     * 取消手工处理时将履历表中的数据移回登记表
     * @param list
     * @see
     * @since 1.0
     */
    void insertEquityRegistFromHis(@Param("list")List<SalesTransactionMaintenanceRegist001Dto> list);
    /**
     * 查询中债通过的对比表中的数据
     * @param list
     * @return
     */
    List<SalesTransactionMaintenanceRegist001Dto> queryBatchForEquityInfoConStrast(List<Map<String, String>> list);
    /**
     * 查询中债通过的对比表中的数据
     * @param bankCode 登记银行编码
     * @param indentifyMark 识别标识
     * @return
     */
    SalesTransactionMaintenanceRegist001Dto queryBatchForEquityInfoSingle(@Param("bankCode") String bankCode,@Param("indentifyMark") String indentifyMark);

    /**
     * 身份信息修改之前查询反馈状态
     * <功能详细描述>
     * @param pageParam
     * @see
     * @since 1.0
     */
    List<String>  getSubmitFeedbackStatusList(List<String> subListUp);

    /**
     * 判断源识别标识以及识别标识能否在数据库中查询到数据
     * @param param
     * @return
     */
    int selectExistsByIndentifyMark(Map<String, Object> param);
    /**
     * 查询中债通过的对比表中的数据
     * @param bankCode 登记银行编码
     * @param indentifyMark 识别标识
     * @return
     */
    List<SalesTransactionMaintenanceRegist001Dto> queryEquityInfoByOrg(@Param("bankCode") String bankCode,@Param("indentifyMark") String indentifyMark);

    /**
     * 查询投资者身份信息待检查的数据
     * 
     * @param map 查询条件
     * @return 数据结果
     * @see
     * @since 1.0
     */
    List<SalesTransactionMaintenanceRegist001Dto> selectEquityRegistData(Map<String, Object> map);



    /**
     * 批量更新投资者身份信息检查序列号
     * 
     * @param equityInfoRegistUpdateList
     * @return
     * @see
     * @since 1.0
     */
    void updateEquityRegistCheckSeqno(List<SalesTransactionMaintenanceRegist001Dto> equityInfoRegistUpdateList);
    
    /**
    * @Title: updateEquityRegistSendStatus
    * @Description: (批量更新投资者身份信息发送状态)
    * @param @param equityInfoRegistUpdateList
    * @param @return    参数
    * @return int    返回类型
    * @throws
    */
    int updateEquityRegistSendStatus(List<SalesTransactionMaintenanceRegist001Dto> equityInfoRegistUpdateList);
    
    int selectNoendCountByTaskBeanName(String beanname);
    
    
    int countExportNum(Map<String,Object> map);
}
