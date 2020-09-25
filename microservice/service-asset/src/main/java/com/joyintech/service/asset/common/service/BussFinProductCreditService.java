package com.joyintech.service.asset.common.service;

import com.joyintech.service.asset.common.dao.BussFinProductCreditMapper;
import com.joyintech.tams.common.base.entity.FinProductCredit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BussFinProductCreditService {

    @Autowired
    private BussFinProductCreditMapper bussFinProductCreditMapper;
    /**
     * 修改/查看初始化
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public List<FinProductCredit> getFinProductCreditByProductId(String finprodId){
        return bussFinProductCreditMapper.getFinProductCreditByProductId(finprodId);
    }

    /**
     * By资产ID删除
     * <一句话功能简述>
     * <功能详细描述>
     * @param finprodId
     * @see
     * @since 1.0
     */
    public void deleteFinProductCreditByProductId(String finprodId){
        bussFinProductCreditMapper.deleteFinProductCreditByProductId(finprodId);
    }
}
