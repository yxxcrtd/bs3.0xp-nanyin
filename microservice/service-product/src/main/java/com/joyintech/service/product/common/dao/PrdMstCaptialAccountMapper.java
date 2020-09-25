/*
 * 文件名：MstCaptialAccountMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.MstCounterAcct;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.product.common.dto.MstCaptialAccountDto;

/**
 *
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年8月20日
 * @see PrdMstCaptialAccountMapper
 * @since 1.0
 */
@Repository
public interface PrdMstCaptialAccountMapper {
    /**
     *
     * 模糊查询
     * <功能详细描述>
     * @param pageParam pageParam
     * @return List<MstCaptialAccountDto>
     * @see
     * @since 1.0
     */
    List<MstCaptialAccountDto> select(Map<String, Object> pageParam);

    /**
     * 联想下拉框
     * <一句话功能简述>
     * <功能详细描述>
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    List<SuggestDto> getAcctName(Map<String, Object> map);

    /**
     *
     * 删除
     * <功能详细描述>
     * @param dto 业务实体
     * @return int
     * @see
     * @since 1.0
     */
    int deleteByPrimaryKey(MstCaptialAccountDto dto);

    /**
     *
     * 新建
     * <功能详细描述>
     * @param dto 业务实体
     * @return int
     * @see
     * @since 1.0
     */
    int insert(MstCaptialAccountDto dto);

    /**
     *
     * 修改
     * <功能详细描述>
     * @param dto 业务实体
     * @return int
     * @see
     * @since 1.0
     */
    int updateByPrimaryKeySelective(MstCaptialAccountDto dto);

    /**
     *
     * 根据网点账号与币种查询详细信息
     * <功能详细描述>
     * @param map map
     * @return MstCaptialAccountDto
     * @see
     * @since 1.0
     */
    MstCaptialAccountDto selectDetail(Map<String, Object> map);

    /**
     *
     *删除时检查数据是否被使用过
     * <功能详细描述>
     * @param dto 业务实体
     * @return int
     * @see
     * @since 1.0
     */
    int checkCodeIsUse(MstCaptialAccountDto dto);

    /**
     *
     *批量删除时检查数据是否被使用过
     * <功能详细描述>
     * @param item List<MstCaptialAccountDto>
     * @return List<MstCaptialAccountDto>
     * @see
     * @since 1.0
     */
    List<MstCaptialAccountDto> checkAllCodeIsUse(List<MstCaptialAccountDto> item);

    /**
     *
     * 删除
     * <功能详细描述>
     * @param dto 业务实体
     * @return int
     * @author yejiejie 2019-4-2
     * @since 1.0
     */
    int deleteByPrimaryKey1(MstCaptialAccountDto dto);

    //add by yejiejie 2019-4-2 查找交易对手与资金账户关联表数据
    List<MstCounterAcct> selectMstCounterAcct(MstCounterAcct mstCounterAcct);

    //add by yejiejie 2019-4-8 根据交易对手id找资金账户信息
    List<MstCounterAcct> selectMstCounterAcctByCounterId(MstCounterAcct mstCounterAcct);
    /**
     * 检查资金账户账号是否重复
     *
     * @param dto 业务实体
     * @return 查询条数
     * @see
     * @since 1.0
     */
    int checkbussCode(MstCaptialAccountDto dto);

    //add by yejiejie 2019-4-8 找资金账户信息
    List<MstCaptialAccountDto> selectStlCapAcctInfo(Map<String, Object> pageParam);
}
