package com.joyintech.tams.valuation.system.batchstart.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.system.batchstart.dao.ValBatchStart001Mapper;
import com.joyintech.tams.valuation.system.batchstart.dto.Product;

@Service
@Transactional(rollbackFor = Exception.class)
public class ValBatchStart002Service extends BaseService<Product> {

    @Autowired
    private ValBatchStart001Mapper valBatchStart001Mapper;


    public Page<Product> findAllProd(Map<String,Object> map)
        throws Exception {
        return super.queryForPage(()-> valBatchStart001Mapper.findAllProd(map));
   }
    

}
