
package com.joyintech.service.entrust.etrstockconnect.dao;


import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.entrust.etrstockconnect.dto.EtrStockConnectDto;
import com.joyintech.tams.common.base.entity.StlSecAcctInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 *  港股通委托
 * @see EtrStockConnect002Mapper
 * @since 1.0
 */
@Component
public interface EtrStockConnect002Mapper {

    /**
     *@description: 获取新建界面的资产代码联想下拉框
     */
    List<SelectOptionDto> getfinprodIdForAdd(Map<String, String> map);

    /**
     * 查询证券账户
     * @param param
     * @return
     */
    StlSecAcctInfo getSecManageAcctId(Map<String, Object> param);

    /**
     * 证券账户信息查询
     * @param param
     * @return
     */
    StlSecAcctInfo selectStlSecAcctInfo(Map<String, Object> param);

    /**
     * 配售代码
     */
    String getSaleCode(Map<String, Object> param);

    /**
     * 最小买卖单位
     */
    EtrStockConnectDto getFinProdInfo(Map<String, Object> param);

    /**
     * 参考汇率
     */
    EtrStockConnectDto getReferenceRate(Map<String, Object> param);

}
