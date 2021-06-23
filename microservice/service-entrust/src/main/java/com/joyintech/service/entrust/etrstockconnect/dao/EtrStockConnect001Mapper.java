
package com.joyintech.service.entrust.etrstockconnect.dao;


import com.joyintech.service.entrust.etrstockconnect.dto.EtrStockConnectDto;
import com.joyintech.service.entrust.etrstockconnect.dto.EtrStockConnectPoDto;
import com.joyintech.service.entrust.etrstockconnect.dto.EtrTrdProductDealDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 *  港股通委托
 * @see EtrStockConnect001Mapper
 * @since 1.0
 */
@Component
public interface EtrStockConnect001Mapper {

    /**
     * 港股通委托列表查询
     * @param param
     * @return
     */
    List<EtrStockConnectDto> selectEtrStockConnect(Map<String, Object> param);
    /**
     * 产品持仓信息分页查询
     * <功能详细描述>
     * @param pageParam 查询参数
     * @return 分页结果
     * @see
     * @since 1.0
     */
    List<EtrStockConnectPoDto> findPositionsPage(Map<String, Object> pageParam);

    /**
     * 根据资产代码获取相关交易信息列表
     * @param map
     * @return
     */
    List<EtrTrdProductDealDto> getTradeListByFinprodId(Map<String, Object> map);
}
