/*
 * 文件名：BooksetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年7月16日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.report.common.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.dto.Product;
import com.joyintech.framework.base.service.ProductService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.report.common.dao.ChbBaseCommonMapper;


/**
 * 中债增加ProductService实现类
 * 〈功能详细描述〉
 * @author leipeng
 * @version 1.0 2019年10月21日
 * @see ChbBaseCommonService
 * @since 1.0
 */
@Service
public class ChbBaseCommonService extends BaseService<Product> implements ProductService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ChbBaseCommonMapper  chbBaseCommonMapper;
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.MASERT)
    
    public Page<Product> findProductByName(String prodName, List<String> notInProductIds) {
        try {
        	
        	Map<String,Object> map = new HashMap<String,Object>();
        	map.put("notInProductIds", notInProductIds);
        	map.put("prodName", prodName);
        	return super.queryForPage(() ->  chbBaseCommonMapper.findProductByName(map));
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
         return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> findAllEffProductId() {
        return chbBaseCommonMapper.selectAllBooksets();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.MASERT)
    public List<Product> findProductByIds(String[] prodGroupSeqNos) {
        return chbBaseCommonMapper.findProductByIds(prodGroupSeqNos);
    }

}
