package com.joyintech.service.portfolio.feeprovisionsetting.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.portfolio.feeprovisionsetting.dto.FeeProvisionSettingDto;
import com.joyintech.tams.common.base.entity.TrdAccountmfConfigdetailDto;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 费用预提设置DAO层
 * @author
 *
 */
@Repository
public interface FeeProvisionSettingMapper {

    /**
     * 账户维护费设置表
     *
     * @param map 参数
     * @return 费用计划信息
     */
    List<FeeProvisionSettingDto> selectForPage(Map<String, Object> map);

    /**
     * 证券账户账号联想下拉框
     * @return List
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getSecAcctId(Map<String, Object> map);

    /**
     * 账户维护费分配表
     * @param map
     * @return
     */
    List<TrdAccountmfConfigdetailDto> selectDetail(Map<String, Object> map);

    /**
     * 增加数据
     * @param dto
     * @return
     */
    int insert(FeeProvisionSettingDto dto);

    /**
     * 修改数据
     * @param dto
     * @return
     */
    int update(FeeProvisionSettingDto dto);

    /**
     * 批量增加明细数据
     * @param list
     * @return
     */
    int insertDetailBatch(List<TrdAccountmfConfigdetailDto> list);

    /**
     * 获取账套信息
     * @return 下拉框列表
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getBooksetId(Map<String,Object> param);

    /**
     * 获取账套信息
     * @return 下拉框列表
     * @see
     * @since 1.0
     */
    String selectOrgInfo(Map<String,Object> param);

    /**
     * 通过主键查询实体信息
     * @param seqNo
     * @return
     */
    FeeProvisionSettingDto selectEntityByID(String seqNo);

    /**
     * 删除
     * @param seqNo
     * @return
     */
    int deleteById(String seqNo);
    int deleteDetailById(String seqNo);

    /**
     * 获取最大产品起息日
     * @param list
     * @return
     */
    public Date getMaxVdate(List<TrdAccountmfConfigdetailDto> list);

    /**
     * 获取最小的产品到期日
     * @param list
     * @return
     */
    public Date getMinMdate(List<TrdAccountmfConfigdetailDto> list);

    /**
     * 查询重复区间
     * @param dto
     * @return
     */
    public Integer selectCount(FeeProvisionSettingDto dto);
    
    /**
     * 删除流程中的数据
     * @param seqNo
     * @return
     */
    int deleteNewDataDetailByPId(String pSeqNo);
    
    /**
     * 查询明细配置
     * @return param
     * @see
     * @since 1.0
     */
    List<TrdAccountmfConfigdetailDto> selectConfigDetail(Map<String,Object> param);
}
