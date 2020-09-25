/*
 * 文件名：TrdRepoTradeMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈志力
 * 修改时间：2019年1月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.trade.trdstandardbond.dao;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.trade.trdstandardbond.dto.TrdStandardBondInDto;
import com.joyintech.service.trade.trdstandardbond.dto.TrdStandardBondOutDto;
import com.joyintech.service.trade.trdstandardbond.dto.TrdStandardBondSearchDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 标准券微服务TrdStandardBondMapper
 */
@Repository
public interface TrdStandardBondMapper {

    /**
     * 标准券查询画面，查询标准券信息列表
     *
     * @param pageParam pageParam
     * @return List<TrdStandardBondSearchDto>
     */
    List<TrdStandardBondSearchDto> selectSearchDataList(Map<String, Object> pageParam);

    /**
     * 质押券入库画面，查询可质押债券列表
     *
     * @param pageParam pageParam
     * @return List<TrdStandardBondInDto>
     */
    List<TrdStandardBondInDto> selectAvaPledgeBondList(Map<String, Object> pageParam);

    /**
     * 质押券出库画面，查询可出库债券列表
     *
     * @param pageParam pageParam
     * @return List<TrdStandardBondOutDto>
     */
    List<TrdStandardBondOutDto> selectOutListData(Map<String, Object> pageParam);

    /**
     * 根据组合代码查询证券托管户：联想框文本框
     *
     * @param param 画面参数
     * @return 证券托管户：联想框文本框
     * @see
     * @since 1.0
     */
    List<SuggestDto> getSecManageAcctId(Map<String, Object> param);

    /**
     * 查询债券代码：下拉框
     *
     * @param param 画面参数
     * @return 债券代码：下拉框
     */
    List<SuggestDto> getBondCode(Map<String, Object> param);
}
