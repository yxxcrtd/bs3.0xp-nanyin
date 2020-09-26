package com.joyintech.tams.valuation.common.jixu.finproduct.service;

import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jinmaodong
 * 20190829
 * 提供几许的调用方法
 */
@Service
public class JxFinProductService {

    @Autowired
    private FinProductMapper finProductMapper;

    /**
     * 根据金融产品代码查询金融产品信息
     * @param finProductId
     * @return
     *  可以从FinProduct获取到市场代码：finprodMarketId
     */
    public FinProduct queryFinProduct(String finProductId){
        return finProductMapper.selectByPrimaryKey(finProductId);
    }

}
