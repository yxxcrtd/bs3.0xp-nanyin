/*
 * 文件名：EquityBaseInfo001Mapper.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中债报送子系统
 * 修改人：何阳阳
 * 修改时间：2017年9月15日
 * 修改内容：新建
 * 系统名称：中债报送子系统
 */
 
package com.joyintech.tams.report.submitmaintenance.businessdetailed.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.ChbEqudet;
import com.joyintech.tams.common.base.entity.ChbEqudetSub;
import com.joyintech.tams.report.investormerger.dto.ChbEquHoldCheckDto;
import com.joyintech.tams.report.submitmaintenance.businessdetailed.dto.EquiteBusiDetails001Dto;
import com.joyintech.tams.report.submitmaintenance.businessdetailed.dto.EquityBusiInfo001Dto;
import com.joyintech.tams.report.submitmaintenance.equityinfo.dto.EquityInfo001Dto;
import com.joyintech.tams.report.submitmanagement.equitybusiinforegist.dto.EquityBusiInfoRegist001Dto;

/**
 * 权益业务明细查询Mapper文件
 * 
 * @author 何阳阳
 * @version 1.0 2017年9月15日
 * @see EquityBusiDetails001Mapper
 * @since 1.0
 */
@Repository
public interface EquityBusiDetails001Mapper {

    /**
     * 投资者明细信息分页列表查询
     * <p>根据指定条件查询列表数据</p>
     * @param param 查询参数
     * @return 查询结果列表
     */
    List<EquiteBusiDetails001Dto> queryForPage(Map<String, Object> param);
    
    /**
     * 根据主键查询投资者明细信息
     * @param key
     * @return
     */
    EquiteBusiDetails001Dto selectByPrimaryKey(EquiteBusiDetails001Dto key);
    
    /**
     * 根据主键更新投资者明细信息状态
     * --批量逻辑删除
     */
    void updateByKeyList(@Param("stautsD")String stautsD, @Param("keyList")List<EquiteBusiDetails001Dto> keyList);
    
    /**
     * 根据产品登记编码获取产品代码
     */
    String queryProdidByRegCode(String prdRegistCode);
    
    /**
     * 根据主键更新投资者明细信息
     * @param equiteBusiDetails001Dto
     * @return
     */
    int updateByPrimaryKeySelective(EquiteBusiDetails001Dto equiteBusiDetails001Dto);
    
    /**
     * 通过主键批量获取维护表中数据
     * --查询导入文件中的数据在维护表中已经存在的数据
     * @param keyList 主键list
     * @return 数据库中已经存在的数据
     * @see
     * @since 1.0
     */
    List<EquiteBusiDetails001Dto> selectAllChbEqudetData(@Param("keyList") List<EquiteBusiDetails001Dto> keyList);

    /**
     * 通过主键批量获取投资者明细信息登记表中数据
     * --查询导入文件中的数据在报送表中已经存在的数据
     * @param keyList 主键list
     * @return 数据库中报送表已经存在的数据
     * @see
     * @since 1.0
     */
    List<EquityBusiInfoRegist001Dto> selectAllChbEqudetSubData(@Param("keyList") List<EquiteBusiDetails001Dto> keyList);

    /**
	 * 批量删除投资者明细信息登记表中未报送数据
	 * @param subList list
	 * @return
	 */
	int deleteByPrimaryBatch(@Param("subList") List<EquityBusiInfoRegist001Dto> subList);
    
	/**
	 * 批量更新删除投资者明细信息登记表数据
	 * @param subList list
	 * @return
	 */
	int updateByPrimaryKeySelectedBatch(@Param("status") String status, @Param("subList") List<EquityBusiInfoRegist001Dto> subList);
	
	/**
     * 查出所有有效的产品登记编码
     */
    List<Map<String,String>> queryAllRegCode();
    
    /**
     * 通过主键获取投资者身份登记信息
     * @param equityInfo001Dto
     * @return
     */
    EquityInfo001Dto selectEquityInfoByPrimaryKey(EquityInfo001Dto dto);
    
    /**
     * 通过主键获取权益人信息检查表数据
     * @param dto
     * @return
     */
    ChbEquHoldCheckDto selectChbEquHoldCheckByPrimaryKey(ChbEquHoldCheckDto dto);
	
    
    
    
//    old：
//    /**
//     * 画面中的产品名称下拉框数据获取
//     * <p>从数据库中查询出产品名称和产品代码，并以列表返回</p>
//     * @param param 查询参数
//     * @return 查询结果
//     */
//    List<SelectOptionDto> queryPrdName(Map<String, Object> param);

    /**
     * 导入时根据导入模板中产品登记编码获取产品代码及名称
     * @param param 产品登记编码
     * @return 返回结果
     */
    SelectOptionDto queryPrdIdAndName(Map<String, Object> param);
    
    /**
     * 插入导入数据
     * <一句话功能简述>
     * <功能详细描述>
     * @param equiteBusiDetails001Dto
     * @return
     * @see
     * @since 1.0
     */
    int insertSelective(EquiteBusiDetails001Dto equiteBusiDetails001Dto);
    
    /**
     * 根据产品代码和产品名称查出对应的产品登记编码
     * @param param 存储了查询状态的map
     * @return 产品登记编码
     * @see
     * @since 1.0
     */
    String queryRegCode(Map<String, Object> param);
    
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
    
    long selectTmpCount();
    
    /**
     * 检查是否存在已报送数据
     * @return 报送数据dto
     * @see
     * @since 1.0
     */
    List<EquityBusiInfo001Dto> checkBusiInfoExist();
    
    /**
     * 查询投资者列表（导入专用）
     * @return 投资者列表
     * @see
     * @since 1.0
     */
    List<ChbEqudet> selectChbEqudetByKey();

    /**
     * 查询业务流水号是否存在
     * @param map 查询条件
     * @return 条数
     * @see
     * @since 1.0
     */
    Integer selectBusiSeqno(Map<String, Object> map);

    /**
     * 判断是否存在对应的投资者
     * @param paramMap 参数
     * @return 数量
     * @see
     * @since 1.0
     */
    int isExistPeo(Map<String, Object> paramMap);
    
    /**
     * 批量登录业务明细数据
     *
     * @param dataList 业务明细数据
     * @see
     * @since 1.0
     */
    void insertChbEqudetData(@Param("dataList") List<EquiteBusiDetails001Dto> dataList);
    
    /**
     * 批量登录业务明细登记数据
     *
     * @param dataList 业务明细登记数据
     * @see
     * @since 1.0
     */
    void insertChbEqudetSubData(@Param("dataList") List<ChbEqudetSub> dataList);
    
    /**
     * 识别标识在投资者信息表中是否已存在
     * @return 结果集
     * @see
     * @since 1.0
     */
    List<Map<String,String>> getAllRegistBankCodeSet();

    /**
     * 币种是否在汇率行情中维护
     * @param ccy 币种代码
     * @return 
     * @see
     * @since 1.0
     */
    Integer isDefend(String ccy);
    
    
    List<EquityInfo001Dto> getAllChbEquInfoOneTime();
    
    List<EquiteBusiDetails001Dto> selectEquiteBusiDetailsForTrans();
    List<EquiteBusiDetails001Dto>selectBusiFeedBackData(Map<String, Object> map);
    List<EquiteBusiDetails001Dto>selectAllBusiFeedBackData(Map<String, Object> map);
}