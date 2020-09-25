/*
 * 文件名：MstBankTranFeeManageMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年3月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.mstbanktranfeemanage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.basicdata.mstbanktranfeemanage.dto.MstBankTranFeeManageDto;
import com.joyintech.tams.component.base.core.dto.WflValidInputParameter;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2019年3月25日
 * @see MstBankTranFeeManageMapper
 * @since 1.0
 */
@Repository
public interface MstBankTranFeeManageMapper {

    /**
     * 分页查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<MstBankTranFeeManageDto> selectForPage(Map<String, Object> pageParam);

    /**
     * 根据流水号查询数据
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    MstBankTranFeeManageDto getEntity(Map<String, Object> map);

    /**
     * 根据批次号查询交易费用配置表的流水号和交易费用配置明细表的流水号
     * <功能详细描述>
     * @param batchSeq
     * @return
     * @see
     * @since 1.0
     */
    List<MstBankTranFeeManageDto> getSeqNoByBatchSeq(String batchSeq);

    /**
     * 查询所有数据
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<MstBankTranFeeManageDto> getAllDto(Map<String, Object> param);

    /**
     * 根据pageId查询所有流程中的数据
     * <功能详细描述>
     * @param parameter
     * @return
     * @see
     * @since 1.0
     */
    List<MstBankTranFeeManageDto> getBusinessData(WflValidInputParameter parameter);
    
    // add by wangguoxian 银行间费用设置 20190718 start
    /**
     * 
     * 根据交易品种，交易方向，期限品种查询已存在费用设置
     * @param param tradeType:交易品种，ps:交易方向，termType:期限品种
     * @return 已存在费用设置数量
     * @see
     * @since 1.0
     */
    Integer selExistCnt(Map<String, Object> param);
    // add by wangguoxian 银行间费用设置 20190718 end
    List<String> selExistCnt1(Map<String, Object> param);
}
