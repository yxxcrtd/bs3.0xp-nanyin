/*
 * 文件名：BooksetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年7月16日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeList;
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
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.dao.BooksetComMapper;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzhiang
 * @version 1.0 2018年7月16日
 * @see BooksetCommonService
 * @since 1.0
 */
@Service
public class BooksetCommonService extends BaseService<Product> implements ProductService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private BooksetComMapper  booksetComMapper;
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
        	return super.queryForPage(() ->  booksetComMapper.findProductByName(map));
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
        map.put("booksetType", DictCodeList.账套类别_账套模板.getValue());
        map.put("acctSubjectType", DictCodeListExt1.金融产品类型_通道.getValue());
        return booksetComMapper.selectAllBooksets(map);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.MASERT)
    public List<Product> findProductByIds(String[] prodGroupSeqNos) {
        return booksetComMapper.findProductByIds(prodGroupSeqNos);
    }
    //多级法人改造start add by hxy 20200617
    /**
     * 银行模式时，返回机构
     * @return
     */
    public String selectBankModeOrgName(){
    	return booksetComMapper.selectBankModeOrgName();

    }
    /**
     * 联社机构时，选择账套时返回机构
     * @return
     */
    public String selectAssociationModeOrgName(Map<String, Object> map){
    	return booksetComMapper.selectAssociationModeOrgName(map);
    }
    /**
     * 联社机构时，选择账套组时返回机构
     * @return
     */
    public String selectAssociationModeOrgGrpName(Map<String, Object> map){
    	return booksetComMapper.selectAssociationModeOrgGrpName(map);
    }
    
    //多级法人改造end add by hxy 20200617
}
