/*
 * 文件名：EquityInfo001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：Administrator
 * 修改时间：2017年9月16日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.submitmaintenance.equityinfo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.submitmaintenance.equityinfo.dto.EquityInfo001Dto;
import com.joyintech.tams.report.submitmaintenance.equityinfo.dto.EquityInfoDto;
import com.joyintech.tams.common.base.entity.ChbEquHold;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2017年9月16日
 * @see EquityInfo001Mapper
 * @since 1.0
 */
@Repository
public interface EquityInfo001Mapper {
    /**
     * 权益基期分页列表查询
     * <p>根据指定条件查询列表数据</p>
     * @param param 查询参数
     * @return 查询结果列表
     */
    List<EquityInfo001Dto> queryForEquityInfoPage(Map<String, Object> param);
    /**
     * 
     * 查询所有已报送权益人信息
     * <p>根据传入的识别标识、登记银行代码</p>
     * @param equityList
     * @return
     * @see
     * @since 1.0
     */
    List<EquityInfoDto>  queryAlSumbitEquityList(@Param("equityList") List<ChbEquHold> equityList);
    /**
     * 删除选中的数据
     * @param param
     * @see
     * @since 1.0
     */
    void deleteEquityInfo(Map<String, Object> param);
    
    List<EquityInfoDto> querySumbitEquityByCode(ChbEquHold chbEquHold);
    
    int insertBtachEquityInfo(List<EquityInfo001Dto> list);
    
    int updateBatchEquityInfo(List<EquityInfo001Dto> list);
    /**
     * 临时方法
     * @param list
     * @return
     */
    int updateBatchEquityInfoTemp(EquityInfo001Dto list);
        
    int deleteBatchEquityInfo(List<EquityInfo001Dto> list);
    
    int insertEquityHis(List<EquityInfo001Dto> list);
    /**
     * 根据权益人主键查询权益人信息列表
     * @param eList 查询条件
     * @return 权益人list
     * @see
     * @since 1.0
     */
    List<EquityInfo001Dto> queryExpSelectEquityInfo(@Param("eList") List<EquityInfo001Dto> eList);
    
    int insertBtachInterfEquityInfo(List<EquityInfo001Dto> list);
    /**
     * 通过主键获取投资者身份登记信息
     * @param equityInfo001Dto 传入信息
     * @return  返回信息
     * @see
     * @since 1.0
     */
    EquityInfo001Dto getEquityInfo001DtoByKey(EquityInfo001Dto equityInfo001Dto);
    
    /**
     * 
     * @param list 数据列表
     * @see
     * @since 1.0
     */
    void insertIntoImportTable(List<Map<String, String>> list);
    
    /**
     * 删除临时表
     * @see
     * @since 1.0
     */
    void deleteTmpTable();
    
    /**
     * 查看临时表有没有数据
     * @return 数据条数
     */
    long selectTmpCount();
    
    /**
     * 通过导入临时表获取投资者身份登记信息
     * @return 返回信息
     */
    List<EquityInfo001Dto> selectEquityInfoForImpCheck();
    /**
     * 根据权益人主键查询权益人信息列表,不包含已经删除的数据
     * @param equityList 导入的list
     * @return 投资者list
     * @see
     * @since 1.0
     */
    List<EquityInfo001Dto> queryEquityInfoList(@Param("equityList") List<EquityInfo001Dto> equityList);
    /**
     * 批量更新维护表中数据
     * @param list  需要更新的内容
     * @return 更新条数
     * @see
     * @since 1.0
     */
    int updateBatchEquityInfoExcel(List<EquityInfo001Dto> list);
    /**
     * 识别标识变更时，将投资者表中数据，移至履历表中
     * @param list  需更新信息
     * @return 插入条数
     * @see
     * @since 1.0
     */
    int insertEquityIdHis(List<EquityInfo001Dto> list);
    /**
     * 检索是否有未报送的业务明细信息
     * <功能详细描述>
     * @param param 参数
     * @return 检查结果
     * @see
     * @since 1.0
     */
    List<Map<String, String>> checkDetailData(Map<String, Object> param);
    /**
     * 检索是否有未报送的持有信息
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<Map<String, String>> checkHoldData(Map<String, Object> param);
    /**
     * 检查已报送的数据的持有数量
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<Map<String, String>> checkHoldDataSubmit(Map<String, Object> param);
    /**
     * 查询投资者身份信息检查序列号
     * @param listU
     * @return
     * @see
     * @since 1.0
     */
    List<String> selectCheckSeqno(List<EquityInfo001Dto> listU);
    
    /**
     * 查询中债反馈状态为中债退回的对应身份信息
     * @param param
     * @return
     */
    List<EquityInfo001Dto> selectFeedBackData(Map<String, Object> param);
    
    List<EquityInfo001Dto> selectAllFeedBackData(Map<String, Object> map);
    /**
     * 通过传入主键查询维护表中是否有
     * @param list 传入列表
     * @return 结果
     */
    List<EquityInfo001Dto> selectEquityInfoForCheck(List<Map<String, String>> list);
    
    
	List<Map<String, String>> checkQtyData(Map<String, Object> param);
	
    /**
     * 查询所有待处理
     * @param list 传入列表
     * @return 结果
     */
    List<EquityInfo001Dto> selectEquityInfoForTrans();
}
