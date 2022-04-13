/*
 * 文件名：BooksetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年7月16日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.invest.common.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.dto.Product;
import com.joyintech.framework.base.service.ProductService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.service.dao.ProductComMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzhiang
 * @version 1.0 2018年7月16日
 * @see ProductCommonService
 * @since 1.0
 */
@Service
public class ProductComService extends BaseService<Product> implements ProductService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ProductComMapper  productComMapper;
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.MASERT)
    
    public Page<Product> findProductByName(String prodName, List<String> notInProductIds) {
        try {
        	
        	/**modify by wujie on 20190328 start
            return super.queryForPage(() ->  booksetComMapper.findProductByName(notInProductIds,prodName));
            **/
        	Map<String,Object> map = new HashMap<String,Object>();
        	map.put("notInProductIds", notInProductIds);
        	map.put("prodName", prodName);
        	map.put("acctSubjectTypeNo", DictCodeListExt1.金融产品类型_通道.getValue());
        	map.put("PORTFOLIO_TYPE_02", DictCodeListExt1.组合类型_委外组合.getValue());
        	return super.queryForPage(() ->  productComMapper.findProductByName(map));
        	/**modify by wujie on 20190328 end**/
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
        Map<String, Object> map = new HashMap<>();
        List<String> portfolioIds = new ArrayList<>();
    	map.put("PORTFOLIO_TYPE_02", DictCodeListExt1.组合类型_委外组合.getValue());
        List<Product> productByName = productComMapper.findProductByName(map);
        if (productByName != null) {
            for (Product product : productByName) {
                portfolioIds.add(product.getProdId());
            }
        }
        return portfolioIds;
        /*map.put("booksetType", DictCodeList.账套类别_账套模板.getValue());
        map.put("acctSubjectType", DictCodeListExt1.金融产品类型_通道.getValue());
        return booksetComMapper.selectAllBooksets(map);*/
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.MASERT)
    public List<Product> findProductByIds(String[] prodGroupSeqNos) {
        return productComMapper.findProductByIds(prodGroupSeqNos);
    }

}
