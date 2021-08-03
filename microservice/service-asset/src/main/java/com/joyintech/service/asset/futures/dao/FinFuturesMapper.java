/*
 * 文件名：FinFuturesMapper.java
 * 版权：Copyright JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：广州银行poc
 * 修改人：余飞
 * 修改时间：2020年11月09日
 * 修改内容：新建
 * 系统名称：广州银行poc
 */

package com.joyintech.service.asset.futures.dao;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.asset.futures.dto.FinFuturesDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author 余飞
 * @version 1.0 2020年11月09日
 * @see FinFuturesMapper
 * @since 1.0
 */
@Repository
public interface FinFuturesMapper {

    /**
     * 页面查询 <功能详细描述>
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<FinFuturesDto> selectForPage(Map<String, Object> param);

    /**
     * 根据金融产品代码查询dto <功能详细描述>
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    FinFuturesDto selectByFinprodId(Map<String, Object> param);

    /**
     * 根据市场代码查询dto <功能详细描述>
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    FinFuturesDto findEntity(Map<String, Object> param);
    /**
     * 查询资产代码
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getFinProdMarketIdList(Map<String, Object> param);
    
    /**
     * 初始化页面查询
     * @param param
     * @return
     */
    List<FinFuturesDto> selectForPageInit(Map<String, Object> param);
}
